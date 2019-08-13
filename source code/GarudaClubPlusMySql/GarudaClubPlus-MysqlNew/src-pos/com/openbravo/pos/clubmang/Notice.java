/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Notice.java
 *
 * Created on 25-Jan-2013, 14:42:43
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;

import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadImage;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.DueListNoticeTableModel;
import com.openbravo.pos.Accounts.DueListNoticeTableModel.NoticeTrackedBean;
import com.openbravo.pos.Accounts.DueListNoticeTableModel.ReportLine;
import com.openbravo.pos.Accounts.NoticeMasterTableModel;
import com.openbravo.pos.Accounts.NoticeMasterTableModel.NoticeMasterBean;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.clubmang.MemTypeTableModel.MemTypeline;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.util.JRViewer300;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.io.FileUtils;
import com.openbravo.pos.forms.DataLogicSystem;
import java.awt.Graphics2D;
import javax.swing.Icon;
/**
 *
 * @author swathi
 */
public class Notice extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    
    private AppView m_App;
    private DataLogicFacilities dlfac;
    private ComboBoxValModel noticemodel;
    private Session s;
    private NoticeMasterBean nmb;
    private CustomerInfo customerInfo;
    private NoticeMasterTableModel ntmt;
    private List<NoticeMasterBean> noticeList;
    private DueListNoticeTableModel dmodelForFirstNotice;
    private DueListNoticeTableModel dmodel2;
    private DueListNoticeTableModel dmodelForSecondNotice;
    private DueListNoticeTableModel dmodelForFinalNotice;
    private DueListNoticeTableModel dmodelForReports;
    private List<Facility> rflist;
    private CheckBoxHeader cbh;
    private int checkboxref = 0;
    private DataLogicCustomers dlCustomers;
    private DataLogicSystem m_dlSystem;
    private Date toDate;
    private String mouseClicked = null; //Created this variable to check status of check box in table when mouse clicked.
    private waitDialog w;
    private List<NoticeTrackedBean> ntbList = null;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    private int characterCount = 400;
    private ComboBoxValModel noticemodelForReport;
    private Image ig = null;
     private Image signImg = null;
    
    
    //private Object jTable1;
    /** Creates new form Notice */
    public Notice() {
        initComponents();
        waitingMess_text.setVisible(true);
           
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel29 = new javax.swing.JLabel();
        ShowAll4 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        MainButtons = new javax.swing.JPanel();
        Report = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Create = new javax.swing.JButton();
        CreatePanal = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        DateTextField = new javax.swing.JTextField();
        DateSelect = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        typeOfNotice = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        NoticeDetails = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MemType = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Due = new javax.swing.JLabel();
        PassDue = new javax.swing.JLabel();
        DP_num = new javax.swing.JTextField();
        Days = new javax.swing.JLabel();
        Months = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DP_num1 = new javax.swing.JTextField();
        Days1 = new javax.swing.JLabel();
        Months1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AdditionalNotes = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel30 = new javax.swing.JLabel();
        displayName = new javax.swing.JTextField();
        facility = new javax.swing.JTextField();
        Cancel5 = new javax.swing.JButton();
        Execute = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        charCount = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        dNo = new javax.swing.JRadioButton();
        dYes = new javax.swing.JRadioButton();
        jLabelSign = new javax.swing.JLabel();
        UpdatePanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        typeOfNotice2 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        UpdateTable = new javax.swing.JTable();
        Cancel4 = new javax.swing.JButton();
        UpdateSelectedNotice = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        nName = new javax.swing.JTextField();
        memName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        gDate = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        dAmount = new javax.swing.JTextField();
        pDate = new javax.swing.JTextField();
        memSKey = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        NoticeViewInUpdate = new javax.swing.JButton();
        noticePath = new javax.swing.JLabel();
        noticeId = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        CommMode = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        dateOfDispatch = new javax.swing.JTextField();
        DateSelect1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        recRef = new javax.swing.JComboBox();
        ReferenceDetails = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        recBy = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        recDate = new javax.swing.JTextField();
        DateSelect2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        selectFile = new javax.swing.JButton();
        viewAck = new javax.swing.JButton();
        targetFileName = new javax.swing.JLabel();
        ackPath = new javax.swing.JLabel();
        UpdateNotice = new javax.swing.JButton();
        Cancel1 = new javax.swing.JButton();
        sourceFile = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Generate = new javax.swing.JButton();
        scrolFirst = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                //        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    //            jc.setBackground(Color.lightGray);
                    //        }
                //        else {
                    //            jc.setBackground(Color.white);
                    //        }

            }
            return c;
        }

    };
    jLabel8 = new javax.swing.JLabel();
    jCheckBox1 = new javax.swing.JCheckBox();
    jLabel28 = new javax.swing.JLabel();
    scrolFinal = new javax.swing.JScrollPane();
    jTable5 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
        int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;
            jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

            //        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                //            jc.setBackground(Color.lightGray);
                //        }
            //        else {
                //            jc.setBackground(Color.white);
                //        }

        }
        return c;
    }

    protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
    }};
    scrolSecond = new javax.swing.JScrollPane();
    jTable3 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
        int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;
            jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

            //        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                //            jc.setBackground(Color.lightGray);
                //        }
            //        else {
                //            jc.setBackground(Color.white);
                //        }

        }
        return c;
    }

    protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
    }};
    waitingMess_text = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    Generate1 = new javax.swing.JButton();
    scrolFirstAG = new javax.swing.JScrollPane();
    jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
        int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;
            jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

            //        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                //            jc.setBackground(Color.lightGray);
                //        }
            //        else {
                //            jc.setBackground(Color.white);
                //        }

        }
        return c;
    }

    };
    jLabel10 = new javax.swing.JLabel();
    jCheckBox2 = new javax.swing.JCheckBox();
    ViewNotice = new javax.swing.JButton();
    scrolFinalAG = new javax.swing.JScrollPane();
    jTable6 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
        int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;
            jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

            //        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                //            jc.setBackground(Color.lightGray);
                //        }
            //        else {
                //            jc.setBackground(Color.white);
                //        }

        }
        return c;
    }

    protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
    }};
    scrolSecondAG = new javax.swing.JScrollPane();
    jTable4 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
        int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;
            jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

            //        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                //            jc.setBackground(Color.lightGray);
                //        }
            //        else {
                //            jc.setBackground(Color.white);
                //        }

        }
        return c;
    }

    protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
    }};
    jPanel10 = new javax.swing.JPanel();
    generateList = new javax.swing.JButton();
    detailsToGenerateList = new javax.swing.JPanel();
    noticeListForReport = new javax.swing.JComboBox();
    jLabel36 = new javax.swing.JLabel();
    jLabel37 = new javax.swing.JLabel();
    dateForReportNoticeList = new javax.swing.JComboBox();
    generateNotice = new javax.swing.JButton();
    jPanel11 = new javax.swing.JPanel();
    jLabel42 = new javax.swing.JLabel();
    jLabel40 = new javax.swing.JLabel();
    jScrollPane9 = new javax.swing.JScrollPane();
    AddNotes1 = new javax.swing.JTextArea(4, 100);
    jLabel38 = new javax.swing.JLabel();
    jLabel41 = new javax.swing.JLabel();
    jLabel39 = new javax.swing.JLabel();
    charCount1 = new javax.swing.JTextField();
    jLabel43 = new javax.swing.JLabel();
    Signature = new javax.swing.JTextField();
    jLabel45 = new javax.swing.JLabel();
    Signature1 = new javax.swing.JTextField();
    jLabel44 = new javax.swing.JLabel();
    Designation = new javax.swing.JTextField();
    jLabel46 = new javax.swing.JLabel();
    Designation1 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    memWiseNT = new javax.swing.JButton();
    memWiseNoticeTrackerPanel = new javax.swing.JPanel();
    jLabel47 = new javax.swing.JLabel();
    memno = new javax.swing.JTextField();
    jLabel48 = new javax.swing.JLabel();
    mname = new javax.swing.JTextField();
    jButton2 = new javax.swing.JButton();
    jLabel49 = new javax.swing.JLabel();
    jLabel50 = new javax.swing.JLabel();
    fDate = new javax.swing.JTextField();
    tDate = new javax.swing.JTextField();
    DateSelect3 = new javax.swing.JButton();
    DateSelect4 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    selectedMemberNoticeDetailsPanel = new javax.swing.JPanel();
    jLabel51 = new javax.swing.JLabel();
    currentDueIndicator = new javax.swing.JTextField();
    memNoticeDetailsTableScrolPanel = new javax.swing.JScrollPane();
    jTable7 = new javax.swing.JTable();
    viewNotice = new javax.swing.JButton();
    viewAcknowlwdgement = new javax.swing.JButton();
    generateReport = new javax.swing.JButton();
    jLabel52 = new javax.swing.JLabel();

    jPanel1.setName("jPanel1"); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );

    jPanel2.setName("jPanel2"); // NOI18N

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );

    jMenuBar1.setName("jMenuBar1"); // NOI18N

    jMenu1.setText("File");
    jMenu1.setName("jMenu1"); // NOI18N
    jMenuBar1.add(jMenu1);

    jMenu2.setText("Edit");
    jMenu2.setName("jMenu2"); // NOI18N
    jMenuBar1.add(jMenu2);

    jLabel29.setText("jLabel29");
    jLabel29.setName("jLabel29"); // NOI18N

    ShowAll4.setText("Show All");
    ShowAll4.setName("ShowAll4"); // NOI18N

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    jEditorPane1.setName("jEditorPane1"); // NOI18N
    jScrollPane1.setViewportView(jEditorPane1);

    jScrollPane12.setName("jScrollPane12"); // NOI18N

    jTabbedPane1.setName("jTabbedPane1"); // NOI18N

    MainButtons.setName("MainButtons"); // NOI18N

    Report.setBackground(new java.awt.Color(204, 204, 204));
    Report.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    Report.setForeground(new java.awt.Color(0, 51, 255));
    Report.setText("Report");
    Report.setName("Report"); // NOI18N
    Report.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ReportActionPerformed(evt);
        }
    });

    Update.setBackground(new java.awt.Color(204, 204, 204));
    Update.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    Update.setForeground(new java.awt.Color(0, 51, 204));
    Update.setText("Update");
    Update.setName("Update"); // NOI18N
    Update.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            UpdateActionPerformed(evt);
        }
    });

    Create.setBackground(new java.awt.Color(204, 204, 204));
    Create.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    Create.setForeground(new java.awt.Color(0, 0, 255));
    Create.setText("Create");
    Create.setName("Create"); // NOI18N
    Create.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            CreateActionPerformed(evt);
        }
    });

    CreatePanal.setName("CreatePanal"); // NOI18N

    jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Other", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 11))); // NOI18N
    jPanel4.setName("jPanel4"); // NOI18N

    jLabel9.setText("Date");
    jLabel9.setName("jLabel9"); // NOI18N

    DateTextField.setName("DateTextField"); // NOI18N

    DateSelect.setText("Date");
    DateSelect.setName("DateSelect"); // NOI18N
    DateSelect.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            DateSelectActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(59, 59, 59)
            .addComponent(DateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(DateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(203, 203, 203))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(DateSelect)
                .addComponent(DateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 11))); // NOI18N
    jPanel3.setName("jPanel3"); // NOI18N

    typeOfNotice.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
    typeOfNotice.setName("typeOfNotice"); // NOI18N
    typeOfNotice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            typeOfNoticeActionPerformed(evt);
        }
    });

    jLabel1.setText("Type of Notice");
    jLabel1.setName("jLabel1"); // NOI18N

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(54, 54, 54)
            .addComponent(typeOfNotice, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(303, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(typeOfNotice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    NoticeDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selected Notice Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 11))); // NOI18N
    NoticeDetails.setName("NoticeDetails"); // NOI18N

    jLabel2.setText("Facility");
    jLabel2.setName("jLabel2"); // NOI18N

    jLabel3.setText("Member Type");
    jLabel3.setName("jLabel3"); // NOI18N

    jScrollPane2.setName("jScrollPane2"); // NOI18N

    MemType.setColumns(20);
    MemType.setRows(5);
    MemType.setName("MemType"); // NOI18N
    jScrollPane2.setViewportView(MemType);
    MemType.setEditable(false);

    jLabel4.setName("jLabel4"); // NOI18N

    jLabel6.setName("jLabel6"); // NOI18N

    jLabel7.setName("jLabel7"); // NOI18N

    Due.setText("Due");
    Due.setName("Due"); // NOI18N

    PassDue.setText("Pass Due");
    PassDue.setName("PassDue"); // NOI18N

    DP_num.setName("DP_num"); // NOI18N

    Days.setText("Days");
    Days.setName("Days"); // NOI18N

    Months.setText("Months");
    Months.setName("Months"); // NOI18N

    jLabel5.setText("Payment Date");
    jLabel5.setName("jLabel5"); // NOI18N

    DP_num1.setName("DP_num1"); // NOI18N

    Days1.setText("Days");
    Days1.setName("Days1"); // NOI18N

    Months1.setText("Months");
    Months1.setName("Months1"); // NOI18N

    jLabel11.setText("Additional Notes");
    jLabel11.setName("jLabel11"); // NOI18N

    jScrollPane4.setName("jScrollPane4"); // NOI18N

    AdditionalNotes.setEditable(true);
    AdditionalNotes.setColumns(20);
    AdditionalNotes.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
    AdditionalNotes.setRows(5);
    AdditionalNotes.setName("AdditionalNotes"); // NOI18N
    jScrollPane4.setViewportView(AdditionalNotes);
    AdditionalNotes.setLineWrap(true);
    AdditionalNotes.setWrapStyleWord(true);

    jLabel12.setText("Signature");
    jLabel12.setName("jLabel12"); // NOI18N

    jLabel13.setText("Designation");
    jLabel13.setName("jLabel13"); // NOI18N

    jScrollPane5.setName("jScrollPane5"); // NOI18N

    jTextPane2.setName("jTextPane2"); // NOI18N
    jScrollPane5.setViewportView(jTextPane2);
    jTextPane2.setEditable(false);

    jScrollPane6.setName("jScrollPane6"); // NOI18N

    jTextPane3.setName("jTextPane3"); // NOI18N
    jScrollPane6.setViewportView(jTextPane3);
    jTextPane3.setEditable(false);

    jLabel30.setText("Display Name");
    jLabel30.setName("jLabel30"); // NOI18N

    displayName.setName("displayName"); // NOI18N

    facility.setName("facility"); // NOI18N

    Cancel5.setText("Cancel");
    Cancel5.setName("Cancel5"); // NOI18N
    Cancel5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Cancel5ActionPerformed(evt);
        }
    });

    Execute.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
    Execute.setText("Execute");
    Execute.setName("Execute"); // NOI18N
    Execute.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ExecuteActionPerformed(evt);
        }
    });

    jLabel31.setText("jLabel31");
    jLabel31.setName("jLabel31"); // NOI18N

    jLabel32.setText("You have ");
    jLabel32.setName("jLabel32"); // NOI18N

    charCount.setName("charCount"); // NOI18N

    jLabel33.setText("characters left.");
    jLabel33.setName("jLabel33"); // NOI18N

    jLabel34.setText("(Maximum Char:");
    jLabel34.setName("jLabel34"); // NOI18N

    jLabel35.setText("Deactivate Members");
    jLabel35.setName("jLabel35"); // NOI18N

    dNo.setText("No");
    dNo.setName("dNo"); // NOI18N

    dYes.setText("Yes");
    dYes.setName("dYes"); // NOI18N

    jLabelSign.setName("jLabelSign"); // NOI18N

    javax.swing.GroupLayout NoticeDetailsLayout = new javax.swing.GroupLayout(NoticeDetails);
    NoticeDetails.setLayout(NoticeDetailsLayout);
    NoticeDetailsLayout.setHorizontalGroup(
        NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(NoticeDetailsLayout.createSequentialGroup()
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                            .addComponent(Due)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(PassDue))
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel30))
                                    .addGap(60, 60, 60)
                                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(displayName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(DP_num1)
                                                .addComponent(DP_num, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                                    .addComponent(Days)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(Months))
                                                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                                    .addComponent(Days1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(Months1)))))
                                    .addGap(18, 18, 18)
                                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(facility, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                                            .addComponent(jLabel35)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dNo)
                                            .addGap(32, 32, 32)
                                            .addComponent(dYes)
                                            .addGap(12, 12, 12))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel31)
                                        .addComponent(jLabel34)
                                        .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)))
                                    .addGap(55, 55, 55)
                                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4)
                                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                .addComponent(jScrollPane6))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelSign, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(61, 61, 61)
                                            .addComponent(jLabel4))
                                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(Cancel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(Execute, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(187, 187, 187)
                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)))
                        .addGroup(NoticeDetailsLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                    .addGap(374, 374, 374)
                    .addComponent(jLabel32)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(charCount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel33)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    NoticeDetailsLayout.setVerticalGroup(
        NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(NoticeDetailsLayout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(displayName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel30)
                .addComponent(jLabel2)
                .addComponent(facility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Due)
                        .addComponent(PassDue))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel5))
                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DP_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Days)
                        .addComponent(Months)
                        .addComponent(dNo)
                        .addComponent(dYes)
                        .addComponent(jLabel35))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DP_num1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Days1)
                        .addComponent(Months1))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                    .addComponent(jLabel11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel34)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel31))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel32)
                .addComponent(charCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel33))
            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabelSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Execute, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Cancel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                    .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(NoticeDetailsLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel13)
                                    .addGap(71, 71, 71))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(66, 66, 66))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeDetailsLayout.createSequentialGroup()
                            .addGroup(NoticeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6))
                            .addGap(19, 19, 19)
                            .addComponent(jLabel7)
                            .addGap(44, 44, 44))))))
    );

    Due.setVisible(false);
    PassDue.setVisible(false);
    DP_num.setEditable(false);
    Days.setVisible(false);
    Days1.setVisible(false);
    Months.setVisible(false);
    Months1.setVisible(false);
    DP_num1.setEditable(false);
    Months1.setVisible(false);
    displayName.setEditable(false);
    facility.setEditable(false);
    dNo.setSelected(true);
    dYes.setSelected(false);
    dNo.setEnabled(false);
    dYes.setEnabled(false);

    javax.swing.GroupLayout CreatePanalLayout = new javax.swing.GroupLayout(CreatePanal);
    CreatePanal.setLayout(CreatePanalLayout);
    CreatePanalLayout.setHorizontalGroup(
        CreatePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(CreatePanalLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(CreatePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(NoticeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(CreatePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGap(34, 34, 34))
    );
    CreatePanalLayout.setVerticalGroup(
        CreatePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(CreatePanalLayout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(NoticeDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    NoticeDetails.setVisible(false);

    UpdatePanel.setName("UpdatePanel"); // NOI18N

    jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 11))); // NOI18N
    jPanel8.setName("jPanel8"); // NOI18N

    typeOfNotice2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
    typeOfNotice2.setName("typeOfNotice2"); // NOI18N
    typeOfNotice2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            typeOfNotice2ActionPerformed(evt);
        }
    });

    jLabel15.setText("Type of Notice");
    jLabel15.setName("jLabel15"); // NOI18N

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(54, 54, 54)
            .addComponent(typeOfNotice2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(308, Short.MAX_VALUE))
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(typeOfNotice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel15))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jScrollPane8.setName("jScrollPane8"); // NOI18N

    UpdateTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {},
            {},
            {},
            {}
        },
        new String [] {

        }
    ));
    UpdateTable.setColumnSelectionAllowed(true);
    UpdateTable.setName("UpdateTable"); // NOI18N
    UpdateTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            UpdateTableMouseClicked(evt);
        }
    });
    UpdateTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            UpdateTablePropertyChange(evt);
        }
    });
    jScrollPane8.setViewportView(UpdateTable);
    UpdateTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    UpdateTable.setVisible(false);

    Cancel4.setText("Cancel");
    Cancel4.setName("Cancel4"); // NOI18N
    Cancel4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Cancel4ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout UpdatePanelLayout = new javax.swing.GroupLayout(UpdatePanel);
    UpdatePanel.setLayout(UpdatePanelLayout);
    UpdatePanelLayout.setHorizontalGroup(
        UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(UpdatePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(Cancel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    UpdatePanelLayout.setVerticalGroup(
        UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(UpdatePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(Cancel4)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    UpdateSelectedNotice.setName("UpdateSelectedNotice"); // NOI18N

    jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Notice Details"));
    jPanel7.setName("jPanel7"); // NOI18N

    jLabel19.setText("Notice Name");
    jLabel19.setName("jLabel19"); // NOI18N

    nName.setName("nName"); // NOI18N

    memName.setName("memName"); // NOI18N

    jLabel14.setText("Member Name");
    jLabel14.setName("jLabel14"); // NOI18N

    jLabel17.setText("Generated Date");
    jLabel17.setName("jLabel17"); // NOI18N

    gDate.setName("gDate"); // NOI18N

    jLabel20.setText("Due Amount");
    jLabel20.setName("jLabel20"); // NOI18N

    dAmount.setName("dAmount"); // NOI18N

    pDate.setName("pDate"); // NOI18N

    memSKey.setName("memSKey"); // NOI18N

    jLabel16.setText("Membership No.");
    jLabel16.setName("jLabel16"); // NOI18N

    jLabel18.setText("Pay By Date");
    jLabel18.setName("jLabel18"); // NOI18N

    jLabel26.setText("View Notice");
    jLabel26.setName("jLabel26"); // NOI18N

    NoticeViewInUpdate.setText("View Notice");
    NoticeViewInUpdate.setName("NoticeViewInUpdate"); // NOI18N
    NoticeViewInUpdate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            NoticeViewInUpdateActionPerformed(evt);
        }
    });

    noticePath.setText(".");
    noticePath.setName("noticePath"); // NOI18N

    noticeId.setName("noticeId"); // NOI18N

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(jLabel20))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addComponent(gDate, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addComponent(jLabel19))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(memSKey)
                        .addComponent(nName, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))))
            .addGap(55, 55, 55)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel18)
                .addComponent(jLabel14)
                .addComponent(jLabel26))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(memName, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addComponent(pDate, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(noticeId)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(NoticeViewInUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                            .addComponent(noticePath)))
                    .addGap(41, 41, 41))))
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(memSKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(gDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(memName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14))
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(pDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(29, 29, 29)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(NoticeViewInUpdate)
                                                .addComponent(jLabel26)
                                                .addComponent(noticePath)))
                                        .addComponent(jLabel18)))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(nName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 10, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(178, Short.MAX_VALUE)
                    .addComponent(noticeId)))
            .addContainerGap())
    );

    nName.setEditable(false);
    memName.setEditable(false);
    gDate.setEditable(false);
    dAmount.setEditable(false);
    pDate.setEditable(false);
    memSKey.setEditable(false);
    noticePath.setText(null);
    noticePath.setVisible(false);
    noticeId.setText(null);
    noticeId.setVisible(false);

    jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Update"));
    jPanel9.setName("jPanel9"); // NOI18N

    jLabel21.setText("Communication Mode ");
    jLabel21.setName("jLabel21"); // NOI18N

    CommMode.setName("CommMode"); // NOI18N

    jLabel22.setText("Date Of Dispatch");
    jLabel22.setName("jLabel22"); // NOI18N

    dateOfDispatch.setName("dateOfDispatch"); // NOI18N

    DateSelect1.setText("Date");
    DateSelect1.setName("DateSelect1"); // NOI18N
    DateSelect1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            DateSelect1ActionPerformed(evt);
        }
    });

    jLabel23.setText("Received Ref");
    jLabel23.setName("jLabel23"); // NOI18N

    recRef.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes" }));
    recRef.setSelectedIndex(0);
    recRef.setName("recRef"); // NOI18N
    recRef.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            recRefActionPerformed(evt);
        }
    });

    ReferenceDetails.setName("ReferenceDetails"); // NOI18N

    jLabel24.setText("Received By");
    jLabel24.setName("jLabel24"); // NOI18N

    recBy.setName("recBy"); // NOI18N

    jLabel25.setText("Received Date");
    jLabel25.setName("jLabel25"); // NOI18N

    recDate.setName("recDate"); // NOI18N

    DateSelect2.setText("Date");
    DateSelect2.setName("DateSelect2"); // NOI18N
    DateSelect2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            DateSelect2ActionPerformed(evt);
        }
    });

    jLabel27.setText("Upload Reference");
    jLabel27.setName("jLabel27"); // NOI18N

    selectFile.setText("Select Acknowledgement");
    selectFile.setName("selectFile"); // NOI18N
    selectFile.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            selectFileActionPerformed(evt);
        }
    });

    viewAck.setText("View ACK");
    viewAck.setName("viewAck"); // NOI18N
    viewAck.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            viewAckActionPerformed(evt);
        }
    });

    targetFileName.setText(".");
    targetFileName.setName("targetFileName"); // NOI18N

    javax.swing.GroupLayout ReferenceDetailsLayout = new javax.swing.GroupLayout(ReferenceDetails);
    ReferenceDetails.setLayout(ReferenceDetailsLayout);
    ReferenceDetailsLayout.setHorizontalGroup(
        ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(ReferenceDetailsLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel24)
                .addComponent(jLabel27))
            .addGap(28, 28, 28)
            .addGroup(ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(targetFileName)
                .addGroup(ReferenceDetailsLayout.createSequentialGroup()
                    .addGroup(ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(selectFile, 0, 0, Short.MAX_VALUE)
                        .addComponent(recBy, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(18, 18, 18)
                    .addGroup(ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(viewAck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recDate, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(DateSelect2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    ReferenceDetailsLayout.setVerticalGroup(
        ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(ReferenceDetailsLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReferenceDetailsLayout.createSequentialGroup()
                    .addGroup(ReferenceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(DateSelect2)
                        .addComponent(recDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(viewAck))
                .addGroup(ReferenceDetailsLayout.createSequentialGroup()
                    .addComponent(recBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(selectFile))
                .addGroup(ReferenceDetailsLayout.createSequentialGroup()
                    .addComponent(jLabel24)
                    .addGap(27, 27, 27)
                    .addComponent(jLabel27)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(targetFileName)
            .addContainerGap())
    );

    viewAck.setEnabled(false);
    targetFileName.setText(null);
    targetFileName.setVisible(false);

    ackPath.setText(".");
    ackPath.setName("ackPath"); // NOI18N

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel21)
                .addComponent(jLabel23))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addComponent(CommMode, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel22)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateOfDispatch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(recRef, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(ackPath)
                    .addContainerGap(76, Short.MAX_VALUE))
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addComponent(DateSelect1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())))
        .addComponent(ReferenceDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
            .addContainerGap(23, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel21)
                .addComponent(CommMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateOfDispatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22)
                .addComponent(DateSelect1))
            .addGap(18, 18, 18)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(recRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ackPath))
            .addGap(18, 18, 18)
            .addComponent(ReferenceDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    ReferenceDetails.setVisible(false);
    if(recRef.getSelectedIndex()==1)
    {
        ReferenceDetails.setVisible(true);
    }
    else
    {
        ReferenceDetails.setVisible(false);
    }
    ackPath.setVisible(false);
    ackPath.setText(null);

    UpdateNotice.setText("Update");
    UpdateNotice.setName("UpdateNotice"); // NOI18N
    UpdateNotice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            UpdateNoticeActionPerformed(evt);
        }
    });

    Cancel1.setText("Cancel");
    Cancel1.setName("Cancel1"); // NOI18N
    Cancel1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Cancel1ActionPerformed(evt);
        }
    });

    sourceFile.setName("sourceFile"); // NOI18N

    javax.swing.GroupLayout UpdateSelectedNoticeLayout = new javax.swing.GroupLayout(UpdateSelectedNotice);
    UpdateSelectedNotice.setLayout(UpdateSelectedNoticeLayout);
    UpdateSelectedNoticeLayout.setHorizontalGroup(
        UpdateSelectedNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(UpdateSelectedNoticeLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(UpdateSelectedNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateSelectedNoticeLayout.createSequentialGroup()
                    .addComponent(sourceFile)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(UpdateNotice, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    UpdateSelectedNoticeLayout.setVerticalGroup(
        UpdateSelectedNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(UpdateSelectedNoticeLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(UpdateSelectedNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(UpdateSelectedNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateNotice, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel1))
                .addComponent(sourceFile))
            .addGap(18, 18, 18))
    );

    sourceFile.setText(null);
    sourceFile.setVisible(false);

    javax.swing.GroupLayout MainButtonsLayout = new javax.swing.GroupLayout(MainButtons);
    MainButtons.setLayout(MainButtonsLayout);
    MainButtonsLayout.setHorizontalGroup(
        MainButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(MainButtonsLayout.createSequentialGroup()
            .addGroup(MainButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MainButtonsLayout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(Create, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(44, 44, 44)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(Report, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(MainButtonsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(MainButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(UpdateSelectedNotice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(UpdatePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreatePanal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap(1654, Short.MAX_VALUE))
    );
    MainButtonsLayout.setVerticalGroup(
        MainButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(MainButtonsLayout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(MainButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Create, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Report, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(CreatePanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(76, 76, 76)
            .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(UpdateSelectedNotice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(523, Short.MAX_VALUE))
    );

    CreatePanal.setVisible(false);
    UpdatePanel.setVisible(false);
    UpdateSelectedNotice.setVisible(false);

    jTabbedPane1.addTab("Notice", MainButtons);

    jPanel5.setName("jPanel5"); // NOI18N

    Generate.setText("Generate");
    Generate.setName("Generate"); // NOI18N
    Generate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            GenerateActionPerformed(evt);
        }
    });

    scrolFirst.setName("scrolFirst"); // NOI18N

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null}
        },
        new String [] {
            "Search Key", "Name", "Cr. Available", "Facility", "Due Amount", "Over Due Amount", "All"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, true
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.setCellSelectionEnabled(true);
    jTable1.setName("jTable1"); // NOI18N
    jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jTable1MouseMoved(evt);
        }
    });
    jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jTable1PropertyChange(evt);
        }
    });
    scrolFirst.setViewportView(jTable1);
    jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    if (jTable1.getColumnModel().getColumnCount() > 0) {
        jTable1.getColumnModel().getColumn(6).setResizable(false);
    }

    jLabel8.setText("Please see Cr.Avbl column,Adjust credit and regenerate report if requiered. If ok tick yes to print it");
    jLabel8.setName("jLabel8"); // NOI18N

    jCheckBox1.setText("Yes");
    jCheckBox1.setName("jCheckBox1"); // NOI18N
    jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox1ItemStateChanged(evt);
        }
    });

    jLabel28.setText("Net Column value is  ((Due Amount+Over Due Amount)-Cr.Available)");
    jLabel28.setName("jLabel28"); // NOI18N

    scrolFinal.setName("scrolFinal"); // NOI18N

    jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
    jTable5.setName("jTable5"); // NOI18N
    jTable5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jTable5PropertyChange(evt);
        }
    });
    scrolFinal.setViewportView(jTable5);

    scrolSecond.setName("scrolSecond"); // NOI18N
    scrolSecond.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            scrolSecondPropertyChange(evt);
        }
    });

    jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
    jTable3.setName("jTable3"); // NOI18N
    jTable3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jTable3PropertyChange(evt);
        }
    });
    scrolSecond.setViewportView(jTable3);

    waitingMess_text.setForeground(new java.awt.Color(251, 33, 24));
    waitingMess_text.setText("Please Wait for few minutes after generating report.");
    waitingMess_text.setName("waitingMess_text"); // NOI18N

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(scrolFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrolSecond)
                    .addComponent(scrolFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)))
            .addContainerGap(1423, Short.MAX_VALUE))
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(waitingMess_text, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(37, 37, 37)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Generate)
                .addComponent(jCheckBox1))
            .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(scrolFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrolSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrolFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel28)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(waitingMess_text))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(Generate)))
            .addContainerGap(811, Short.MAX_VALUE))
    );

    Generate.setEnabled(false);

    jTabbedPane1.addTab("", jPanel5);

    jPanel6.setName("jPanel6"); // NOI18N

    Generate1.setText("Re Generate");
    Generate1.setName("Generate1"); // NOI18N
    Generate1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Generate1ActionPerformed(evt);
        }
    });

    scrolFirstAG.setName("scrolFirstAG"); // NOI18N

    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null}
        },
        new String [] {
            "Search Key", "Name", "Cr. Available", "Facility", "Due Amount", "Over Due Amount", "All"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, true
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable2.setCellSelectionEnabled(true);
    jTable2.setName("jTable2"); // NOI18N
    jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable2MouseClicked(evt);
        }
    });
    jTable2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jTable2MouseMoved(evt);
        }
    });
    jTable2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jTable2PropertyChange(evt);
        }
    });
    scrolFirstAG.setViewportView(jTable2);
    jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    if (jTable2.getColumnModel().getColumnCount() > 0) {
        jTable2.getColumnModel().getColumn(6).setResizable(false);
    }

    jLabel10.setText("Please see Cr.Avbl column,Adjust credit and regenerate report if requiered. If ok tick yes to print it");
    jLabel10.setName("jLabel10"); // NOI18N

    jCheckBox2.setText("Yes");
    jCheckBox2.setName("jCheckBox2"); // NOI18N
    jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox2ItemStateChanged(evt);
        }
    });
    jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCheckBox2ActionPerformed(evt);
        }
    });

    ViewNotice.setText("View");
    ViewNotice.setName("ViewNotice"); // NOI18N
    ViewNotice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ViewNoticeActionPerformed(evt);
        }
    });

    scrolFinalAG.setName("scrolFinalAG"); // NOI18N

    jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
    jTable6.setName("jTable6"); // NOI18N
    jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable6MouseClicked(evt);
        }
    });
    jTable6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jTable6MouseMoved(evt);
        }
    });
    jTable6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jTable6PropertyChange(evt);
        }
    });
    scrolFinalAG.setViewportView(jTable6);

    scrolSecondAG.setName("scrolSecondAG"); // NOI18N

    jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
    jTable4.setName("jTable4"); // NOI18N
    jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable4MouseClicked(evt);
        }
    });
    jTable4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jTable4MouseMoved(evt);
        }
    });
    jTable4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jTable4PropertyChange(evt);
        }
    });
    scrolSecondAG.setViewportView(jTable4);

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(scrolSecondAG, javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                    .addComponent(jLabel10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ViewNotice, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(Generate1))
                .addComponent(scrolFinalAG, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrolFirstAG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(1447, Short.MAX_VALUE))
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addComponent(scrolFirstAG, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrolSecondAG, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrolFinalAG, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Generate1)
                    .addComponent(ViewNotice)))
            .addContainerGap(784, Short.MAX_VALUE))
    );

    Generate1.setEnabled(false);
    ViewNotice.setEnabled(false);

    jTabbedPane1.addTab("", jPanel6);

    jPanel10.setName("jPanel10"); // NOI18N

    generateList.setText("Notice Board List");
    generateList.setName("generateList"); // NOI18N
    generateList.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            generateListActionPerformed(evt);
        }
    });

    detailsToGenerateList.setBorder(javax.swing.BorderFactory.createTitledBorder("Notice Details"));
    detailsToGenerateList.setName("detailsToGenerateList"); // NOI18N

    noticeListForReport.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
    noticeListForReport.setName("noticeListForReport"); // NOI18N
    noticeListForReport.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            noticeListForReportItemStateChanged(evt);
        }
    });

    jLabel36.setText("Select Notice");
    jLabel36.setName("jLabel36"); // NOI18N

    jLabel37.setText("Select Date of Generation");
    jLabel37.setName("jLabel37"); // NOI18N

    dateForReportNoticeList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
    dateForReportNoticeList.setName("dateForReportNoticeList"); // NOI18N
    dateForReportNoticeList.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            dateForReportNoticeListItemStateChanged(evt);
        }
    });

    generateNotice.setText("Generate");
    generateNotice.setName("generateNotice"); // NOI18N
    generateNotice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            generateNoticeActionPerformed(evt);
        }
    });

    jPanel11.setName("jPanel11"); // NOI18N

    jLabel42.setText("Characters left.");
    jLabel42.setName("jLabel42"); // NOI18N

    jLabel40.setText("500)");
    jLabel40.setName("jLabel40"); // NOI18N

    jScrollPane9.setName("jScrollPane9"); // NOI18N

    AddNotes1.setColumns(20);
    AddNotes1.setRows(5);
    AddNotes1.setName("AddNotes1"); // NOI18N
    jScrollPane9.setViewportView(AddNotes1);
    AddNotes1.setWrapStyleWord(true);
    AddNotes1.setLineWrap(true);
    String str = AddNotes1.getText();
    AddNotes1.setDocument(new FixedSizeDocument2(500));
    AddNotes1.setText(str);

    jLabel38.setText("Header to List ");
    jLabel38.setName("jLabel38"); // NOI18N

    jLabel41.setText("You have ");
    jLabel41.setName("jLabel41"); // NOI18N

    jLabel39.setText("(Max Characters");
    jLabel39.setName("jLabel39"); // NOI18N

    charCount1.setName("charCount1"); // NOI18N

    jLabel43.setText("Signature");
    jLabel43.setName("jLabel43"); // NOI18N

    Signature.setName("Signature"); // NOI18N

    jLabel45.setText("Signature");
    jLabel45.setName("jLabel45"); // NOI18N

    Signature1.setName("Signature1"); // NOI18N

    jLabel44.setText("Designation");
    jLabel44.setName("jLabel44"); // NOI18N

    Designation.setName("Designation"); // NOI18N

    jLabel46.setText("Designation");
    jLabel46.setName("jLabel46"); // NOI18N

    Designation1.setName("Designation1"); // NOI18N

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel43)
                        .addComponent(jLabel44))
                    .addGap(45, 45, 45)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Designation, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Signature, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel45)
                        .addComponent(jLabel46))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Designation1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Signature1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(charCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(30, Short.MAX_VALUE))
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(11, 11, 11)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(jLabel42)
                        .addComponent(charCount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jLabel38)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel39)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel40)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Signature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel44)
                        .addComponent(Designation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Signature1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Designation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46))))
            .addContainerGap(21, Short.MAX_VALUE))
    );

    charCount1.setEditable(false);

    javax.swing.GroupLayout detailsToGenerateListLayout = new javax.swing.GroupLayout(detailsToGenerateList);
    detailsToGenerateList.setLayout(detailsToGenerateListLayout);
    detailsToGenerateListLayout.setHorizontalGroup(
        detailsToGenerateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(detailsToGenerateListLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(detailsToGenerateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel36)
                .addComponent(jLabel37))
            .addGap(35, 35, 35)
            .addGroup(detailsToGenerateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(noticeListForReport, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateForReportNoticeList, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(166, Short.MAX_VALUE))
        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsToGenerateListLayout.createSequentialGroup()
            .addContainerGap(582, Short.MAX_VALUE)
            .addComponent(generateNotice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(35, 35, 35))
    );
    detailsToGenerateListLayout.setVerticalGroup(
        detailsToGenerateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(detailsToGenerateListLayout.createSequentialGroup()
            .addGroup(detailsToGenerateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel36)
                .addComponent(noticeListForReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(detailsToGenerateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel37)
                .addComponent(dateForReportNoticeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(generateNotice)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel11.setVisible(false);

    jButton1.setText("Cancel");
    jButton1.setName("jButton1"); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    memWiseNT.setText("Member Wise Notice Tracker");
    memWiseNT.setName("memWiseNT"); // NOI18N
    memWiseNT.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            memWiseNTActionPerformed(evt);
        }
    });

    memWiseNoticeTrackerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Member Wise Notice Details"));
    memWiseNoticeTrackerPanel.setName("memWiseNoticeTrackerPanel"); // NOI18N

    jLabel47.setText("Member No.");
    jLabel47.setName("jLabel47"); // NOI18N

    memno.setName("memno"); // NOI18N
    memno.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            memnoKeyPressed(evt);
        }
    });

    jLabel48.setText("Member Name");
    jLabel48.setName("jLabel48"); // NOI18N

    mname.setName("mname"); // NOI18N

    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
    jButton2.setName("jButton2"); // NOI18N
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jLabel49.setText("From Date");
    jLabel49.setName("jLabel49"); // NOI18N

    jLabel50.setText("To Date");
    jLabel50.setName("jLabel50"); // NOI18N

    fDate.setName("fDate"); // NOI18N

    tDate.setName("tDate"); // NOI18N

    DateSelect3.setText("Date");
    DateSelect3.setName("DateSelect3"); // NOI18N
    DateSelect3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            DateSelect3ActionPerformed(evt);
        }
    });

    DateSelect4.setText("Date");
    DateSelect4.setName("DateSelect4"); // NOI18N
    DateSelect4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            DateSelect4ActionPerformed(evt);
        }
    });

    jButton3.setText("Execute");
    jButton3.setName("jButton3"); // NOI18N
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    selectedMemberNoticeDetailsPanel.setName("selectedMemberNoticeDetailsPanel"); // NOI18N

    jLabel51.setText("Current Due Amount");
    jLabel51.setName("jLabel51"); // NOI18N

    currentDueIndicator.setName("currentDueIndicator"); // NOI18N

    memNoticeDetailsTableScrolPanel.setName("memNoticeDetailsTableScrolPanel"); // NOI18N

    jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
    jTable7.setName("jTable7"); // NOI18N
    jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable7MouseClicked(evt);
        }
    });
    jTable7.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jTable7FocusGained(evt);
        }
    });
    memNoticeDetailsTableScrolPanel.setViewportView(jTable7);

    viewNotice.setText("View Notice");
    viewNotice.setName("viewNotice"); // NOI18N
    viewNotice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            viewNoticeActionPerformed(evt);
        }
    });

    viewAcknowlwdgement.setText("View Ack");
    viewAcknowlwdgement.setName("viewAcknowlwdgement"); // NOI18N
    viewAcknowlwdgement.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            viewAcknowlwdgementActionPerformed(evt);
        }
    });

    generateReport.setText("Generate Report");
    generateReport.setName("generateReport"); // NOI18N
    generateReport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            generateReportActionPerformed(evt);
        }
    });

    jLabel52.setText("To know complete details of notices click on Generate Report.");
    jLabel52.setName("jLabel52"); // NOI18N

    javax.swing.GroupLayout selectedMemberNoticeDetailsPanelLayout = new javax.swing.GroupLayout(selectedMemberNoticeDetailsPanel);
    selectedMemberNoticeDetailsPanel.setLayout(selectedMemberNoticeDetailsPanelLayout);
    selectedMemberNoticeDetailsPanelLayout.setHorizontalGroup(
        selectedMemberNoticeDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(selectedMemberNoticeDetailsPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(selectedMemberNoticeDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(memNoticeDetailsTableScrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(selectedMemberNoticeDetailsPanelLayout.createSequentialGroup()
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(currentDueIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(137, 137, 137)
                    .addComponent(viewNotice, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(viewAcknowlwdgement, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(generateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel52))
            .addContainerGap(124, Short.MAX_VALUE))
    );
    selectedMemberNoticeDetailsPanelLayout.setVerticalGroup(
        selectedMemberNoticeDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(selectedMemberNoticeDetailsPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(memNoticeDetailsTableScrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(selectedMemberNoticeDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel51)
                .addComponent(currentDueIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(generateReport)
                .addComponent(viewAcknowlwdgement)
                .addComponent(viewNotice))
            .addGap(18, 18, 18)
            .addComponent(jLabel52)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    currentDueIndicator.setText(null);
    viewNotice.setEnabled(false);
    viewAcknowlwdgement.setEnabled(false);

    javax.swing.GroupLayout memWiseNoticeTrackerPanelLayout = new javax.swing.GroupLayout(memWiseNoticeTrackerPanel);
    memWiseNoticeTrackerPanel.setLayout(memWiseNoticeTrackerPanelLayout);
    memWiseNoticeTrackerPanelLayout.setHorizontalGroup(
        memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(memWiseNoticeTrackerPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(memWiseNoticeTrackerPanelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(memWiseNoticeTrackerPanelLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(44, 44, 44)
            .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel49)
                .addComponent(jLabel50))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tDate, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(fDate, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DateSelect3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(DateSelect4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(186, Short.MAX_VALUE))
        .addComponent(selectedMemberNoticeDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    memWiseNoticeTrackerPanelLayout.setVerticalGroup(
        memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(memWiseNoticeTrackerPanelLayout.createSequentialGroup()
            .addContainerGap(16, Short.MAX_VALUE)
            .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel49)
                .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateSelect3))
                .addGroup(memWiseNoticeTrackerPanelLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DateSelect4)))
                .addGroup(memWiseNoticeTrackerPanelLayout.createSequentialGroup()
                    .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(memWiseNoticeTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mname, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel50))))
            .addGap(21, 21, 21)
            .addComponent(jButton3)
            .addGap(18, 18, 18)
            .addComponent(selectedMemberNoticeDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    selectedMemberNoticeDetailsPanel.setVisible(false);

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(generateList, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(63, 63, 63)
                    .addComponent(memWiseNT, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(detailsToGenerateList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(memWiseNoticeTrackerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(1422, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGap(24, 24, 24)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(generateList)
                .addComponent(memWiseNT))
            .addGap(18, 18, 18)
            .addComponent(detailsToGenerateList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(memWiseNoticeTrackerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(27, 27, 27)
            .addComponent(jButton1)
            .addContainerGap(1380, Short.MAX_VALUE))
    );

    memWiseNoticeTrackerPanel.setVisible(false);

    jTabbedPane1.addTab("", jPanel10);

    jScrollPane12.setViewportView(jTabbedPane1);
    jTabbedPane1.setEnabledAt(2, false);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1967, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(2177, Short.MAX_VALUE))
    );
    }// </editor-fold>//GEN-END:initComponents

private void ExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteActionPerformed
try
{
    String fid=null,fname=null;
    List<Facility> flist=dlfac.getFacility();
    
     if(typeOfNotice.getSelectedIndex()!=-1)
     {
        if(!DateTextField.getText().toString().trim().equals(""))
        {
         nmb = (NoticeMasterBean) typeOfNotice.getSelectedItem();
         int overdue = 0;
         
         if(nmb.isDue_pass())
         {
            if(nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         else
         {
             if(!nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         Date d  =(Date) Formats.TIMESTAMP.parseValue(DateTextField.getText());
         toDate = d;
            Date d1 =(Date) Formats.TIMESTAMP.parseValue(DateTextField.getText());
            Calendar cal=Calendar.getInstance();
            cal.setTime(d);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            Date duedate=new Date(d.getTime());
            Calendar cald=Calendar.getInstance();
            cald.setTimeInMillis(duedate.getTime());
            int i=cald.get(Calendar.DATE);
            
            duedate.setTime(cald.getTimeInMillis());
            d.setTime(cal.getTimeInMillis());
            cal.add(Calendar.DATE, -overdue);
          
            d1.setTime(cal.getTimeInMillis());
            
            Facility f1=new Facility();
            f1.setID("All");
            f1.setName("All");
            flist.add(0,f1);
       
            for (Iterator<Facility> it = flist.iterator(); it.hasNext();) {
                Facility fa = it.next();
                if(fa.getName().equals(nmb.getFacility()))
                {
                    fid = fa.getid();
                    fname = fa.getName();
                    break;
                }
            }
         //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
         if(nmb.getParentId()==null)
         {
              scrolFirst.setVisible(true);
            scrolSecond.setVisible(false);
            scrolSecondAG.setVisible(false);
            scrolFirstAG.setVisible(true);
            scrolFinalAG.setVisible(false);
            scrolFinal.setVisible(false);
            
             dmodelForFinalNotice = null;
             dmodelForSecondNotice = null;
             
            List<MemTypeline> mlist=dlfac.getMemType();
       MemTypeline m1=new MemTypeline();
        m1.setID("All");
       m1.setName("All");
       mlist.add(0,m1);
         
       String [] memetype = nmb.getTypeofmem().split("\r\n,");
       //memetype = memetype.replaceAll(",", "");
       List<String> memtypeid = new ArrayList<String>();
       
       for(int a = 0; a < memetype.length ; a++)
       {
           for (Iterator<MemTypeline> it = mlist.iterator(); it.hasNext();) {
                MemTypeline memTypeline = it.next();
                if(memTypeline.getname().equals(memetype[a]))
                {
                    memtypeid.add(memTypeline.getid());
                    break;
                }
           }
       }

           dmodelForFirstNotice=DueListNoticeTableModel.loadData(m_App,fname,memtypeid,d,d1,dlfac,fid, nmb);
           dmodelForSecondNotice = null;
           List<NoticeTrackedBean> li = dmodelForFirstNotice.getFromNoticeTracker();
         
            
            showDialog(dmodelForFirstNotice, rflist, m_App);
            jTabbedPane1.setSelectedIndex(1);
            
            
        }
         //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
         else if(nmb.isFinalnotice()==false && nmb.getParentId()!=null && nmb.getReferencetoparent()!=null)
        {
             scrolFirst.setVisible(false);
            scrolSecond.setVisible(true);
            scrolSecondAG.setVisible(true);
            scrolFirstAG.setVisible(false);
            scrolFinalAG.setVisible(false);
            scrolFinal.setVisible(false);
            
            dmodelForFirstNotice = null;
            dmodelForFinalNotice = null;
            //Other than 1st And Final Notice
          //  List<NoticeMasterBean> noticeList1 = new StaticSentence(m_App.getSession(), "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(new Object[]{nmb.getReferencetoparent()});
           List<NoticeMasterBean> noticeList1 = new StaticSentence(m_App.getSession(), "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID ,SIGNIMGFLG " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(new Object[]{nmb.getReferencetoparent()});
           
          NoticeMasterBean firstNotice = noticeList1.get(0);
            
            List<NoticeTrackedBean> noticeTrackerList = new StaticSentence(m_App.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND NM.ID=? AND ACTIVE = TRUE ORDER BY 5", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{firstNotice.getId()});
            dmodelForSecondNotice = DueListNoticeTableModel.loadDataforSecondNotice(m_App, d, fid, fname, dlfac, nmb, noticeTrackerList);
            showDialog2(dmodelForSecondNotice);
            jTabbedPane1.setSelectedIndex(1);
            
        } 
        else if(nmb.isFinalnotice()==true)
        {
            
            scrolFirst.setVisible(false);
            scrolSecond.setVisible(false);
            scrolSecondAG.setVisible(false);
            scrolFirstAG.setVisible(false);
            scrolFinalAG.setVisible(true);
            scrolFinal.setVisible(true);
        dmodelForFirstNotice = null;
        dmodelForSecondNotice = null;
            //Other than 1st And Final Notice
       //     List<NoticeMasterBean> noticeList1 = new StaticSentence(m_App.getSession(), "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(new Object[]{nmb.getReferencetoparent()});
       //pratima
        List<NoticeMasterBean> noticeList1 = new StaticSentence(m_App.getSession(), "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID ,SIGNIMGFLG " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(new Object[]{nmb.getReferencetoparent()});
           
       NoticeMasterBean firstNotice = noticeList1.get(0);
            
            List<NoticeTrackedBean> noticeTrackerList = new StaticSentence(m_App.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND NM.ID=? AND ACTIVE = TRUE ORDER BY 5", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{firstNotice.getId()});
            dmodelForFinalNotice = DueListNoticeTableModel.loadDataforSecondNotice(m_App, d, fid, fname, dlfac, nmb, noticeTrackerList);
            showDialogForFinal(dmodelForFinalNotice);
            jTabbedPane1.setSelectedIndex(1);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select the Date!!" , JOptionPane.WANTS_INPUT_PROPERTY, JOptionPane.WARNING_MESSAGE);
        }
         
         
     }
     else
     {
         JOptionPane.showMessageDialog(this, "Please select the type of notice!!" , JOptionPane.WANTS_INPUT_PROPERTY, JOptionPane.WARNING_MESSAGE);
     }
}
catch(NumberFormatException e1){
           JOptionPane.showMessageDialog(null, "Error");
        }
        catch (BasicException ex) {
            new MessageInf(ex).show(new JFrame());
           ex.printStackTrace();
        }
catch (Exception ex) {
            new MessageInf(ex).show(new JFrame());
           ex.printStackTrace();
        }
     
}//GEN-LAST:event_ExecuteActionPerformed


private void typeOfNoticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeOfNoticeActionPerformed

    if(typeOfNotice.getSelectedIndex()!=-1)
    {
        reset();
        jPanel4.setVisible(true);
         nmb = (NoticeMasterBean) typeOfNotice.getSelectedItem();
         
         if(nmb!=null)
         {
         facility.setText(nmb.getFacility());
         displayName.setText(nmb.getNoticeNameToDisplay());
         String mem = nmb.getTypeofmem().replaceAll(",", "");
         
         MemType.setText(mem);
         
         if(!nmb.isDue_pass())
         {
             Due.setVisible(true);
         }
        else
         {
             PassDue.setVisible(true);
         }
         if(nmb.isFinalnotice())
         {
             jLabel31.setText("1200)");
             AdditionalNotes.setDocument(new FixedSizeDocument(1200));
             AdditionalNotes.setText(nmb.getAddNotes());
         }
         else
         {
             jLabel31.setText("500)");
             AdditionalNotes.setDocument(new FixedSizeDocument(500));
             AdditionalNotes.setText(nmb.getAddNotes());
         }
         
         DP_num.setText(""+nmb.getDp_num());
         if(!nmb.isDp_dm())
         {
             Days.setVisible(true);
         }
         else
         {
             Months.setVisible(true);
         }
         
         //AdditionalNotes.setText(nmb.getAddNotes());
         if(nmb.isSignImgFlg()){
           BufferedImage imgicon = m_dlSystem.getResourceAsImage("signature.secretary");
           Image newimg = imgicon.getScaledInstance(jLabelSign.getWidth(),jLabelSign.getHeight(), Image.SCALE_SMOOTH);
           ImageIcon image = new ImageIcon(newimg);
                 jLabelSign.setIcon(image);
                 signImg=newimg;
         }else{
         jTextPane2.setText(nmb.getSign());
          jLabelSign.setIcon(null);
         }
         jTextPane3.setText(nmb.getDesg());
         DP_num1.setText(""+nmb.getPayment_date_num());
         if(!nmb.isPayment_date_dm())
         {
             Days1.setVisible(true);
         }
         else
         {
             Months1.setVisible(true);
         }
         
         if(nmb.isDeactivateMem())
         {
             dNo.setSelected(false);
             
             dYes.setSelected(true);
         }
         else
         {
             dYes.setSelected(false);
            
             dNo.setSelected(true);
         }
         
         NoticeDetails.setVisible(true);
         }
    }
}//GEN-LAST:event_typeOfNoticeActionPerformed

private void DateSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelectActionPerformed

    Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(DateTextField.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            if(date.after(new Date()))
            {
              if(JOptionPane.showConfirmDialog(this, "Report will be calculated as on current date.  Do you wish to continue??", JOptionPane.MESSAGE_PROPERTY, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
              {
                  DateTextField.setText(Formats.TIMESTAMP.formatValue(date));
              }
              else
              {
                  JOptionPane.showMessageDialog(this, "Please select the date!");
              }
            }
            else
            {
                DateTextField.setText(Formats.TIMESTAMP.formatValue(date));
            }
            
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
    
}//GEN-LAST:event_DateSelectActionPerformed

private void CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateActionPerformed

    loaddata();
  
   MainButtons.setVisible(false);actDeaButtons(false);
  CreatePanal.setVisible(true);
}//GEN-LAST:event_CreateActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       
        Generate.setEnabled(!Generate.isEnabled());
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void GenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateActionPerformed
        
   int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to generate Report. ??  \n\n Note:- This is a time consuming process. \n            It may take 5-20 minutes depending on no. of members selected. \n            Do not close application abruptly. \n            It will display success message once the process get complete." , "Warning" , JOptionPane.YES_NO_OPTION);
   if(bill == JOptionPane.YES_OPTION) 
   {    
       if(nmb.getReferencetoparent()==null)
       {
       List<ReportLine> allList =  dmodelForFirstNotice.getReportList1();
       List<ReportLine> selectedList = new ArrayList();
       
        for (Iterator<ReportLine> it = allList.iterator(); it.hasNext();) {
            ReportLine reportLine = it.next();
            if(reportLine.isAll())
            {
                selectedList.add(reportLine);
            }
        }
        if(selectedList.size()<=0)
        {
            JOptionPane.showMessageDialog(this, "Please select atleast one member from the list", "Select Member", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            waitingMess_text.setVisible(true);  
            generateFirstNoticeToSelectedMemebers(selectedList);
           // w.hideDialog();
            JOptionPane.showMessageDialog(this, "Notice Generated for selected members.", "Notice Generated", JOptionPane.INFORMATION_MESSAGE);
            jTabbedPane1.setSelectedIndex(0);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            waitingMess_text.setVisible(false);
        }
        
       }
       else if(nmb.isFinalnotice()==false && nmb.getParentId()!=null && nmb.getReferencetoparent()!=null)
       {
           List<ReportLine> allMembersList =  dmodelForSecondNotice.getReportList1();
       List<ReportLine> selectedListRP = new ArrayList();
       List<NoticeTrackedBean> NTBAllList = dmodelForSecondNotice.getNoticeTrackerListForSecondNoticeSelectedMembers();
       List<NoticeTrackedBean> SelectedList1 = new ArrayList<NoticeTrackedBean>();
       
        for (int it=0 ; it < allMembersList.size(); it++) {
            ReportLine reportLine = allMembersList.get(it);
            if(reportLine.isAll())
            {
                SelectedList1.add(NTBAllList.get(it));
                selectedListRP.add(reportLine);
            }
        }
        
        if(selectedListRP.size()<=0)
        {
            JOptionPane.showMessageDialog(this, "Please select atleast one member from the list", "Select Member", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
             waitingMess_text.setVisible(true);  
            generateSecondNoticeToSelectedMemebers(selectedListRP, SelectedList1 );
            
            w.hideDialog();
            JOptionPane.showMessageDialog(this, "Notice Generated for selected members.", "Notice Generated", JOptionPane.INFORMATION_MESSAGE);
            jTabbedPane1.setSelectedIndex(0);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            waitingMess_text.setVisible(false);
        }
        
           
           
       }
       else if(nmb.isFinalnotice()==true)
       {
           
           List<ReportLine> allMembersList =  dmodelForFinalNotice.getReportList1();
       List<ReportLine> selectedListRP = new ArrayList();
       List<NoticeTrackedBean> NTBAllList = dmodelForFinalNotice.getNoticeTrackerListForSecondNoticeSelectedMembers();
       List<NoticeTrackedBean> SelectedList1 = new ArrayList<NoticeTrackedBean>();
       
        for (int it=0 ; it < allMembersList.size(); it++) {
            ReportLine reportLine = allMembersList.get(it);
            if(reportLine.isAll())
            {
                SelectedList1.add(NTBAllList.get(it));
                selectedListRP.add(reportLine);
            }
        }
        
        if(selectedListRP.size()<=0)
        {
            JOptionPane.showMessageDialog(this, "Please select atleast one member from the list", "Select Member", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
             waitingMess_text.setVisible(true);  
            generateFinalNoticeToSelectedMemebers(selectedListRP, SelectedList1 );
            
            w.hideDialog();
            JOptionPane.showMessageDialog(this, "Notice Generated for selected members.", "Notice Generated", JOptionPane.INFORMATION_MESSAGE);
            jTabbedPane1.setSelectedIndex(0);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            waitingMess_text.setVisible(false);
           
       }}
     }
    }//GEN-LAST:event_GenerateActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        
        JTable table = (JTable) evt.getSource();
       
    
        if(table.getEditingRow()!=-1)
        {
           
            if(  dmodelForSecondNotice==null && dmodelForFirstNotice!=null && dmodelForFinalNotice==null)
            {
            boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable1.getValueAt(row, 6);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable1.getValueAt(row, 5).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable1.getValueAt(row, 4).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable1.getValueAt(row, 2).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFirstNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFirstNotice.getReportList1().get(row).setAll(false);
                     jTable1.setValueAt(false, row, 6);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFirstNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFirstNotice.getReportList1().get(row).setAll(false);
                     jTable1.setValueAt(false, row, 6);
                 }  
             }
            
        }}
            else if(dmodelForFirstNotice==null && dmodelForSecondNotice!=null && dmodelForFinalNotice==null)
            {
                boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable1.getValueAt(row, 10);
            
            //private final static String[] TABLEHEADERSFOROTHERTHANSECONDNOTICE = {"Search Key","Name","DueDate", "Gen. Date", "PayBy Date", "Due Amount", "Cr. Available","Due Amount","Over Due Amount", "Net", null, "Active", };
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable1.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable1.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable1.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(false);
                     jTable1.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(false);
                     jTable1.setValueAt(false, row, 10);
                 }  
             }
            
        }
                
                
            }
            else if(dmodelForFirstNotice==null && dmodelForSecondNotice==null && dmodelForFinalNotice!=null)
            {
                 boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable1.getValueAt(row, 10);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable1.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable1.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable1.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(false);
                     jTable1.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(false);
                     jTable1.setValueAt(false, row, 10);
                 }  
             }
            
             }
                
            }
        
        }
    }//GEN-LAST:event_jTable1PropertyChange

    private void Generate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Generate1ActionPerformed
        
        if(JOptionPane.showConfirmDialog(this, "Notice Already Generated for the selected members, by regerating the Notice will override the existing Notice. Do You Want To Continue anyway?", "Notice Will Override", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
        {
            
         int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to re-generate Report. ??  \n\n Note:- This is a time consuming process. \n            It may take 5-20 minutes depending on no. of members selected. \n            Do not close application abruptly. \n            It will display success message once the process get complete." , "Warning" , JOptionPane.YES_NO_OPTION);
            if(bill == JOptionPane.YES_OPTION) 
            {    
            
            
            
            
            if(nmb.getReferencetoparent()==null)
            {
                List<NoticeTrackedBean> ntb = dmodelForFirstNotice.getFromNoticeTracker();
                List<ReportLine> allList1 =  dmodelForFirstNotice.getReportListForAlreadyGenerated();
                List<ReportLine> selectedList1 = new ArrayList();
           
            for (Iterator<ReportLine> it = allList1.iterator(); it.hasNext();) {
                ReportLine reportLine = it.next();
                if(reportLine.isAll())
                {
                    selectedList1.add(reportLine);
                }
            }
            if(selectedList1.size()<=0)
            {
                JOptionPane.showMessageDialog(this, "Please select atleast one member from the list", "Select Member", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                regenerateFirstNoticeToSelectedMemebers(selectedList1, ntb);
               
                JOptionPane.showMessageDialog(this, "Notice ReGenerated for selected members.", "Notice ReGenerated", JOptionPane.INFORMATION_MESSAGE);
                jTabbedPane1.setSelectedIndex(0);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            
            }
            }
            else if(nmb.isFinalnotice()==false && nmb.getParentId()!=null && nmb.getReferencetoparent()!=null)
            {
                List<NoticeTrackedBean> ntb = dmodelForSecondNotice.getFromNoticeTracker();
                List<ReportLine> allList1 =  dmodelForSecondNotice.getReportListForAlreadyGenerated();
                List<ReportLine> selectedList1 = new ArrayList();
           
            for (Iterator<ReportLine> it = allList1.iterator(); it.hasNext();) {
                ReportLine reportLine = it.next();
                if(reportLine.isAll())
                {
                    selectedList1.add(reportLine);
                }
            }
            if(selectedList1.size()<=0)
            {
                JOptionPane.showMessageDialog(this, "Please select atleast one member from the list", "Select Member", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                //Deactivating the current notice because in report this will cause conflict
                for (Iterator<ReportLine> it = selectedList1.iterator(); it.hasNext();) {
                                                        ReportLine selectedMembers = it.next();
                                                        NoticeTrackedBean noticeToUpdate = null;
                                                       // String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                                        for (Iterator<NoticeTrackedBean> it1 = ntb.iterator(); it1.hasNext();) {
                                                            NoticeTrackedBean noticeTrackedBean = it1.next();
                                                            if(noticeTrackedBean.getMemSearchKey().equals(selectedMembers.getSearchkey()))
                                                            {
                                                                noticeToUpdate = noticeTrackedBean;
                                                                break;
                                                            }
                                                        }
                        try {
                            int cnt = new PreparedSentence(s, "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING,})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), noticeToUpdate.getId()});
                        } catch (BasicException ex) {
                            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                
                
                regenerateSecondNoticeToSelectedMemebers(selectedList1, ntb);
                
                JOptionPane.showMessageDialog(this, "Notice ReGenerated for selected members.", "Notice ReGenerated", JOptionPane.INFORMATION_MESSAGE);
                jTabbedPane1.setSelectedIndex(0);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setEnabledAt(2, false);
            }
            }
            else if(nmb.isFinalnotice())
            {
                
                List<NoticeTrackedBean> ntb = dmodelForFinalNotice.getFromNoticeTracker();
                List<ReportLine> allList1 =  dmodelForFinalNotice.getReportListForAlreadyGenerated();
                List<ReportLine> selectedList1 = new ArrayList();
           
            for (Iterator<ReportLine> it = allList1.iterator(); it.hasNext();) {
                ReportLine reportLine = it.next();
                if(reportLine.isAll())
                {
                    selectedList1.add(reportLine);
                }
            }
            if(selectedList1.size()<=0)
            {
                JOptionPane.showMessageDialog(this, "Please select atleast one member from the list", "Select Member", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                 //Deactivating the current notice because in report this will cause conflict
                for (Iterator<ReportLine> it = selectedList1.iterator(); it.hasNext();) {
                                                        ReportLine selectedMembers = it.next();
                                                        NoticeTrackedBean noticeToUpdate = null;
                                                       // String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                                        for (Iterator<NoticeTrackedBean> it1 = ntb.iterator(); it1.hasNext();) {
                                                            NoticeTrackedBean noticeTrackedBean = it1.next();
                                                            if(noticeTrackedBean.getMemSearchKey().equals(selectedMembers.getSearchkey()))
                                                            {
                                                                noticeToUpdate = noticeTrackedBean;
                                                                break;
                                                            }
                                                        }
                        try {
                            int cnt = new PreparedSentence(s, "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING,})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), noticeToUpdate.getId()});
                        } catch (BasicException ex) {
                            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                
                
                regenerateFinalNoticeToSelectedMemebers(selectedList1, ntb);
                
                JOptionPane.showMessageDialog(this, "Notice ReGenerated for selected members.", "Notice ReGenerated", JOptionPane.INFORMATION_MESSAGE);
                jTabbedPane1.setSelectedIndex(0);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setEnabledAt(2, false);
            }
                
            }
        
            }
        }
    }//GEN-LAST:event_Generate1ActionPerformed

    private void jTable2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable2PropertyChange
        
         JTable table = (JTable) evt.getSource();
    
        if(table.getEditingRow()!=-1)
        {
            if( dmodelForSecondNotice==null && dmodelForFinalNotice==null && dmodelForFirstNotice!=null)
            {
            boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable2.getValueAt(row, 6);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable2.getValueAt(row, 5).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable2.getValueAt(row, 4).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable2.getValueAt(row, 2).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFirstNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFirstNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable2.setValueAt(false, row, 6);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFirstNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFirstNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable2.setValueAt(false, row, 6);
                 }  
             }
            
        }
        }
            else if(dmodelForSecondNotice!=null && dmodelForFinalNotice==null && dmodelForFirstNotice==null)
            {
                 boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable2.getValueAt(row, 10);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable2.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable2.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable2.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable2.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable2.setValueAt(false, row, 10);
                 }  
             }
            
        }
                
            }
            else if(dmodelForSecondNotice==null && dmodelForFinalNotice!=null && dmodelForFirstNotice==null)
            {
                 boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable2.getValueAt(row, 10);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable2.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable2.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable2.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable2.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable2.setValueAt(false, row, 10);
                 }  
             }
            
        }
 
            }
        
        }
        
    }//GEN-LAST:event_jTable2PropertyChange

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        Generate1.setEnabled(!Generate1.isEnabled());
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void ViewNoticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewNoticeActionPerformed
        try {
            int selectedRow =0;
            NoticeTrackedBean selectedMemberNotice = null;
            ReportLine rl = null;
            
            if(scrolFirstAG.isVisible())
            {
                selectedRow = jTable2.getSelectedRow();
              rl = dmodelForFirstNotice.getReportListForAlreadyGenerated().get(selectedRow);
             
           List<NoticeTrackedBean> ntb =  dmodelForFirstNotice.getFromNoticeTracker();
            for (Iterator<NoticeTrackedBean> it = ntb.iterator(); it.hasNext();) {
                NoticeTrackedBean noticeTrackedBean = it.next();
                if(noticeTrackedBean.getMemSearchKey().equals(rl.getSearchkey()))
                {
                    selectedMemberNotice = noticeTrackedBean;
                    break;
                }
            }
            }
            else if(scrolSecondAG.isVisible())
            {
                selectedRow = jTable4.getSelectedRow();
                 rl = dmodelForSecondNotice.getReportListForAlreadyGenerated().get(selectedRow);
             
           List<NoticeTrackedBean> ntb =  dmodelForSecondNotice.getFromNoticeTracker();
            for (Iterator<NoticeTrackedBean> it = ntb.iterator(); it.hasNext();) {
                NoticeTrackedBean noticeTrackedBean = it.next();
                if(noticeTrackedBean.getMemSearchKey().equals(rl.getSearchkey()))
                {
                    selectedMemberNotice = noticeTrackedBean;
                    break;
                }
            }
            }
            else if(scrolFinalAG.isVisible())
            {
                selectedRow = jTable6.getSelectedRow();
                 rl = dmodelForFinalNotice.getReportListForAlreadyGenerated().get(selectedRow);
             
           List<NoticeTrackedBean> ntb =  dmodelForFinalNotice.getFromNoticeTracker();
            for (Iterator<NoticeTrackedBean> it = ntb.iterator(); it.hasNext();) {
                NoticeTrackedBean noticeTrackedBean = it.next();
                if(noticeTrackedBean.getMemSearchKey().equals(rl.getSearchkey()))
                {
                    selectedMemberNotice = noticeTrackedBean;
                    break;
                }
            }
            }
            
            File file = new File(selectedMemberNotice.getLinkToNotice().replace("./", ""));
            Desktop.getDesktop().open(file);
            
        } catch (IOException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Unable to open Notice!", "Error in opening Notice", JOptionPane.ERROR_MESSAGE);
        } 
        
        
    }//GEN-LAST:event_ViewNoticeActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
      int num = jTable2.getSelectedRow();
       if(num!=-1)
       {
           if(jTable2.hasFocus())
           {
           ViewNotice.setEnabled(true);
           }
           else
           {
               ViewNotice.setEnabled(false);
           }
       }
       else
       {
           ViewNotice.setEnabled(false);
       }
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseMoved
       
        int num = jTable2.getSelectedRow();
       if(num!=-1)
       {
           if(jTable2.hasFocus())
           {
           ViewNotice.setEnabled(true);
           }
           else
           {
               ViewNotice.setEnabled(false);
           }
       }
       else
       {
           ViewNotice.setEnabled(false);
       }
    }//GEN-LAST:event_jTable2MouseMoved

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
   loaddata();
   reset();
   MainButtons.setVisible(false);
   actDeaButtons(false);
   UpdatePanel.setVisible(true);
      
    }//GEN-LAST:event_UpdateActionPerformed

    private void typeOfNotice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeOfNotice2ActionPerformed
        
        if(typeOfNotice2.getSelectedIndex()!=-1)
        {
            
            nmb = (NoticeMasterBean) typeOfNotice.getSelectedItem();
            dmodel2 = DueListNoticeTableModel.loadGeneratedNotice(m_App, nmb.getId());
            UpdateTable.setModel(dmodel2.getTableModel3());
            ntbList = dmodel2.getFromNoticeTracker();
            
            UpdateTable.setVisible(true);
            
        }
        
    }//GEN-LAST:event_typeOfNotice2ActionPerformed

    private void UpdateTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_UpdateTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateTablePropertyChange

    private void UpdateTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateTableMouseClicked
        
        if(evt.getClickCount()==2)
        {
            
            JTable tab = (JTable) evt.getSource();
            int row = tab.getSelectedRow();
            if(tab.getSelectedRow()!=-1)
            {
                reset();
                String searchkey = tab.getValueAt(row, 0).toString();
               // List<NoticeTrackedBean> ntbList = dmodel2.getFromNoticeTracker();
                for (Iterator<NoticeTrackedBean> it = ntbList.iterator(); it.hasNext();) {
                    NoticeTrackedBean nb = it.next();
                    
                    if(nb.getMemSearchKey().trim().equals(searchkey.trim()))
                    {
                        noticeId.setText(nb.getId());
                    nName.setText(nb.getNoticeName());
                    memName.setText(nb.getMemberName());
                    memSKey.setText(nb.getMemSearchKey());
                    gDate.setText(simpleDateFormat.format(nb.getGeneratedDate()).toString());
                    pDate.setText(simpleDateFormat.format(nb.getPayByDate()).toString());
                    dAmount.setText(Formats.ConvertDoubleToString(nb.getDueAmount()));
                    CommMode.setText(nb.getCommMadeBy());
                    noticePath.setText(nb.getLinkToNotice().toString());
                    
                    if(nb.getDataOfDispatch()!=null)
                    {
                    dateOfDispatch.setText(Formats.TIMESTAMP.formatValue(nb.getDataOfDispatch()));
                    }
                    else
                    {
                        dateOfDispatch.setText(null);
                    }
                    
                    if(nb.getReceivedDate()!=null && nb.getReceivedBy()!=null)
                    {
                    recDate.setText(Formats.TIMESTAMP.formatValue(nb.getReceivedDate()));
                    recBy.setText(nb.getReceivedBy());
                    }
                    else
                    {
                        recDate.setText(null);recBy.setText(null);
                        
                    }
                    if(nb.isReceivedRef())
                    {
                        recRef.setSelectedIndex(1);
                        ReferenceDetails.setVisible(true);
                    }
                    if(nb.getLinkToAck()!=null)
                    {
                        ackPath.setText(nb.getLinkToAck());
                        viewAck.setEnabled(true);
                    }
                    break;
                    }
                   }
                
                UpdatePanel.setVisible(false);
                UpdateSelectedNotice.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_UpdateTableMouseClicked

    private void DateSelect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect1ActionPerformed
       
         Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(dateOfDispatch.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            if(new Date().after(date))
            {
                dateOfDispatch.setText(Formats.TIMESTAMP.formatValue(date));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Selected Date Cannon be greater than current date.", "Select Date", JOptionPane.ERROR_MESSAGE);
            }
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
        
        
    }//GEN-LAST:event_DateSelect1ActionPerformed

    private void DateSelect2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect2ActionPerformed
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(recDate.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            if(new Date().after(date))
            {
                 recDate.setText(Formats.TIMESTAMP.formatValue(date));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Selected Date Cannon be greater than current date.", "Select Date", JOptionPane.ERROR_MESSAGE);
            }
               // recDate.setText(Formats.TIMESTAMP.formatValue(date));
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
    }//GEN-LAST:event_DateSelect2ActionPerformed

    private void recRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recRefActionPerformed
        if(recRef.getSelectedIndex()==1)
        {
            ReferenceDetails.setVisible(true);
        }
        else
        {
            ReferenceDetails.setVisible(false);
        }
    }//GEN-LAST:event_recRefActionPerformed

    private void selectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            
                File sourceFile1 = fileChooser.getSelectedFile();
                SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                String dateToFilename = sd.format(new Date()).toString();
                String filename = sourceFile1.getName();
                String Tfilename = "Notice/NoticeAcknowledgement/"+memSKey.getText()+"_"+ nName.getText()+"_"+dateToFilename+"_ACK_";
                File tragetFile = new File(Tfilename+filename);
                //FileUtils.copyFile(sourceFile,tragetFile );
                targetFileName.setText(tragetFile.toString());
                sourceFile.setText(sourceFile1.getAbsolutePath());
        }   
    }//GEN-LAST:event_selectFileActionPerformed

    private void viewAckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAckActionPerformed
       try {
            if(ackPath.getText()!=null)
            {
                String path = ackPath.getText();
            File file = new File(path);
           
            Desktop.getDesktop().open(file);
           
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_viewAckActionPerformed

    private void NoticeViewInUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoticeViewInUpdateActionPerformed
        try {
            if(noticePath.getText()!=null)
            {   
              
               File file = new File(noticePath.getText().replace("./", ""));
            
                System.out.println("file absolute"+file.getAbsolutePath());
                
                String projectPath1=new File(".").getAbsolutePath().toString();
                String filePath=file.getAbsolutePath().toString();
//                String filePath1=    filePath.substring(filePath.lastIndexOf('\\'),filePath.length());
//                projectPath1= projectPath1.substring(0, projectPath1.length()-1);
//                System.out.println("projectPath1"+projectPath1);
//                String filePath2= projectPath1+"Notice"+filePath1;
//               System.out.println("filepath2"+filePath2);
//               File file1 = new File(filePath2);
//                Desktop.getDesktop().open(file1);
          
                String filePath1=    filePath.substring(0,filePath.lastIndexOf('\\'));
                int index=filePath1.lastIndexOf('\\');
                filePath1=    filePath.substring(index+1,filePath.length());
                projectPath1= projectPath1.substring(0, projectPath1.length()-1);
                System.out.println("projectPath1"+projectPath1);
                String filePath2= projectPath1+""+filePath1;
               System.out.println("filepath2"+filePath2);
               File file1 = new File(filePath2);
                Desktop.getDesktop().open(file1);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_NoticeViewInUpdateActionPerformed

    private void UpdateNoticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateNoticeActionPerformed
       
        String ackName = targetFileName.getText();
        String ackName1 = targetFileName.getText();
        Date d = null;
        boolean ref = false;
        Object [] val = null;
        String commmode = null;
        try
        {
        if(recRef.getSelectedIndex()==1)
        {
            if(recBy.getText()!=null && recDate.getText()!=null )
            {
                if(recBy.getText().length()>1 &&  recDate.getText().length()>1)
                {
                    if(dateOfDispatch.getText()!=null && CommMode.getText()!=null)
                    {
                    d = (Date) Formats.TIMESTAMP.parseValue(dateOfDispatch.getText());
                    commmode = CommMode.getText();
                    }
                    Date receDate = (Date) Formats.TIMESTAMP.parseValue(recDate.getText());
                    new PreparedSentence(s, "UPDATE NOTICETRACKER SET COMMMODE=?, DATAOFDISPATCH=?, RECEIVEDREF=?, RECEIVEDBY=?, RECEIVEDDATE=?, LINKTOACK=?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{commmode,d,true, recBy.getText(), receDate, targetFileName.getText(), noticeId.getText() });
                    
                    if(sourceFile.getText()!=null && targetFileName.getText()!=null)
                    {
                    File sourceF = new File(sourceFile.getText());
                    File destFile = new File(targetFileName.getText());
                    FileUtils.copyFile(sourceF,destFile);
                    }
                    JOptionPane.showMessageDialog(this, "Notice Updated Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                   reset();
                UpdateSelectedNotice.setVisible(false);
                UpdatePanel.setVisible(true);
                typeOfNotice2.setSelectedIndex(-1);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please enter Notice ack received details.", "Enter details", JOptionPane.ERROR_MESSAGE);
            }
            
            }
            
        }
        else if(dateOfDispatch.getText()!=null && CommMode.getText()!=null)
        {       d = (Date) Formats.TIMESTAMP.parseValue(dateOfDispatch.getText());
                commmode = CommMode.getText();
                new PreparedSentence(s, "UPDATE NOTICETRACKER SET COMMMODE=?, DATAOFDISPATCH=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{commmode,d, noticeId.getText() });
                JOptionPane.showMessageDialog(this, "Notice Updated Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                reset();
                UpdateSelectedNotice.setVisible(false);
                UpdatePanel.setVisible(true);
                typeOfNotice2.setSelectedIndex(-1);
        }
        else
        {
           JOptionPane.showMessageDialog(this, "Please Enter the Notice Received Details.", "Enter details", JOptionPane.ERROR_MESSAGE); 
        }} catch (BasicException ex) {
                    Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                    new MessageInf(ex).show(getParent());
                    reset();
                UpdateSelectedNotice.setVisible(false);
                UpdatePanel.setVisible(true);
                typeOfNotice2.setSelectedIndex(-1);
                    
                }
          catch (IOException ex) {
                  Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                  new MessageInf(ex).show(getParent());
                  reset();
                UpdateSelectedNotice.setVisible(false);
                UpdatePanel.setVisible(true);
                typeOfNotice2.setSelectedIndex(-1);
                }
        finally
                {
                
                }
    }//GEN-LAST:event_UpdateNoticeActionPerformed

    private void Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel1ActionPerformed
       reset();
       UpdateSelectedNotice.setVisible(false);
       UpdatePanel.setVisible(true);
        typeOfNotice2.setSelectedIndex(-1);
       
        
    }//GEN-LAST:event_Cancel1ActionPerformed

    private void Cancel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel5ActionPerformed
        reset();
        CreatePanal.setVisible(false);
        UpdatePanel.setVisible(false);
        UpdateSelectedNotice.setVisible(false);
        MainButtons.setVisible(true);
        actDeaButtons(true);
    }//GEN-LAST:event_Cancel5ActionPerformed

    private void Cancel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel4ActionPerformed
        reset();
        CreatePanal.setVisible(false);
        UpdatePanel.setVisible(false);
        UpdateSelectedNotice.setVisible(false);
        MainButtons.setVisible(true);
        actDeaButtons(true);
    }//GEN-LAST:event_Cancel4ActionPerformed

    private void jTable1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseMoved
        
//         TableColumnModel columnModel = jTable1.getColumnModel();
//             int viewColumn = columnModel.getColumnIndexAtX(evt.getX());
//             int modelColumn = jTable1.convertColumnIndexToModel(viewColumn);
//             JTable tab = (JTable) evt.getSource();
//             TableCellRenderer tcr = tab.get
    }//GEN-LAST:event_jTable1MouseMoved

    private void ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportActionPerformed
        reset1();
        loaddata();
        dmodelForReports = DueListNoticeTableModel.loadInstanceForReports(m_App);
   reset();
   MainButtons.setVisible(false);
   actDeaButtons(false);
   UpdatePanel.setVisible(false);
   jTabbedPane1.setEnabledAt(0, false);
    jTabbedPane1.setEnabledAt(3, true);
    jTabbedPane1.setTitleAt(3, "Reports");
    jTabbedPane1.setSelectedIndex(3);
    
    
   noticemodelForReport = new ComboBoxValModel(dmodelForReports.getNoticeList());
   noticeListForReport.setModel(noticemodelForReport);
   noticeListForReport.setSelectedIndex(-1);
        
        
    }//GEN-LAST:event_ReportActionPerformed

    public void mainButInReports(boolean b)
    {
        generateList.setVisible(b);
        memWiseNT.setVisible(b);
    }
    
    private void generateListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateListActionPerformed
mainButInReports(false);
detailsToGenerateList.setVisible(true);
        
        
    }//GEN-LAST:event_generateListActionPerformed

    private void noticeListForReportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_noticeListForReportItemStateChanged
            AddNotes1.setText(null);
            AddNotes1.setDocument(new FixedSizeDocument2(500));
            jPanel11.setVisible(false);
            Signature.setText(null);
            Signature1.setText(null);
            Designation.setText(null);
            Designation1.setText(null);
        
        if(noticeListForReport.getSelectedIndex()!= -1)
        {
            try {
                NoticeMasterBean nmBean = (NoticeMasterBean) noticeListForReport.getSelectedItem();
                ComboBoxValModel cbvm = new ComboBoxValModel(new StaticSentence(m_App.getSession(), "SELECT DISTINCT DATE_FORMAT(GENERATEDDATE,'%Y-%M-%d') AS date FROM NOTICETRACKER WHERE NOTICEID=? AND ACTIVE  = TRUE ORDER BY GENERATEDDATE", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(nmBean.getId()));
                dateForReportNoticeList.setModel(cbvm);
                dateForReportNoticeList.setSelectedIndex(0);
                jLabel37.setVisible(true);
                dateForReportNoticeList.setVisible(true);
                AddNotes1.setText(nmBean.getHeaderToList());
                Signature1.setText(nmBean.getSign());
                Designation1.setText(nmBean.getDesg());
            } catch (BasicException ex) {
                Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else
        {jLabel37.setVisible(false);
            dateForReportNoticeList.setVisible(false);
            
        }
        
    }//GEN-LAST:event_noticeListForReportItemStateChanged

    private void dateForReportNoticeListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dateForReportNoticeListItemStateChanged
        if(dateForReportNoticeList.getSelectedIndex() != -1)
        {
            generateNotice.setVisible(true);
            jPanel11.setVisible(true);
        }
        else
        {
            generateNotice.setVisible(false);
            jPanel11.setVisible(false);
        }
        
    }//GEN-LAST:event_dateForReportNoticeListItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        reset();
        MainButtons.setVisible(true);actDeaButtons(true);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(0, true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void generateNoticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateNoticeActionPerformed
       
        if(noticeListForReport.getSelectedIndex()!=-1 && dateForReportNoticeList.getSelectedIndex()!=-1)
        {
            try {
                NoticeMasterBean nmBeanSelected = (NoticeMasterBean) noticeListForReport.getSelectedItem();
                String selDate = dateForReportNoticeList.getSelectedItem().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
                Date d = formatter.parse(selDate);
                
                Calendar cal2=Calendar.getInstance();
                cal2.setTime(d);
                cal2.set(Calendar.HOUR_OF_DAY, 0);
                cal2.set(Calendar.MINUTE, 0);
                cal2.set(Calendar.SECOND, 0);
                cal2.set(Calendar.MILLISECOND, 0);
                Date startDate =  cal2.getTime();
                Calendar cal=Calendar.getInstance();
                cal.setTime(d);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 59);
              
                Date endDate = cal.getTime();
                
                List<NoticeTrackedBean> noticeToMembersList = new StaticSentence(m_App.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND N.NOTICEID=? AND N.ACTIVE = TRUE AND N.GENERATEDDATE>=? AND N.GENERATEDDATE<=? ORDER BY C.SEARCHKEY", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{nmBeanSelected.getId(), startDate, endDate});
                
                SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yyyy");
            Map reportparam=new HashMap();
            reportparam.put("companyName",m_App.getSession().getCompanyName());
            reportparam.put("companyAddress",m_App.getSession().getCompanyAddress());
            reportparam.put("noticeName", nmBeanSelected.getNoticeNameToDisplay());
            reportparam.put("date", s.format(d));
            reportparam.put("sign1", Signature1.getText());
            reportparam.put("desg1", Designation1.getText());
             reportparam.put("sign", Signature.getText());
            reportparam.put("desg", Designation.getText());
            reportparam.put("header", AddNotes1.getText());
            reportparam.put("total", noticeToMembersList.size());
            
            reportparam.put("companyAddress", m_App.getSession().getCompanyAddress());
                DataSourceProvider data1=new DataSourceProvider(noticeToMembersList);
                DataSourceForNoticeToMembersList d11 = new DataSourceForNoticeToMembersList(noticeToMembersList);
                data1.setDataSource(d11);
                
                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembersList.jrxml", reportparam, false, data1, true, null);
                
            } catch (BasicException ex) {
                Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select Notice and Date!", "Select Date & Notice", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }//GEN-LAST:event_generateNoticeActionPerformed

    private void jTable4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseMoved
       int num = jTable4.getSelectedRow();
       if(num!=-1)
       {
           if(jTable4.hasFocus())
           {
           ViewNotice.setEnabled(true);
           }
           else
           {
               ViewNotice.setEnabled(false);
           }
       }
       else
       {
           ViewNotice.setEnabled(false);
       }
        
    }//GEN-LAST:event_jTable4MouseMoved

    private void jTable6MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseMoved
       int num = jTable6.getSelectedRow();
       if(num!=-1)
       {
           if(jTable6.hasFocus())
           {
           ViewNotice.setEnabled(true);
           }
           else
           {
               ViewNotice.setEnabled(false);
           }
       }
       else
       {
           ViewNotice.setEnabled(false);
       }
    }//GEN-LAST:event_jTable6MouseMoved

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
         int num = jTable4.getSelectedRow();
       if(num!=-1)
       {
           if(jTable4.hasFocus())
           {
           ViewNotice.setEnabled(true);
           }
           else
           {
               ViewNotice.setEnabled(false);
           }
       }
       else
       {
           ViewNotice.setEnabled(false);
       }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        int num = jTable6.getSelectedRow();
       if(num!=-1)
       {
           if(jTable6.hasFocus())
           {
           ViewNotice.setEnabled(true);
           }
           else
           {
               ViewNotice.setEnabled(false);
           }
       }
       else
       {
           ViewNotice.setEnabled(false);
       }
    }//GEN-LAST:event_jTable6MouseClicked

    private void memnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memnoKeyPressed

         if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,MEMTYPE,MOBILE,ACCOUNT FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(memno.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    mname.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mname.setText(null);
            customerInfo = null;

        }
        
        
        
    }
        // TODO add your handling code here:         if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {             try {                 Object[] obj = dmang.getMamberbySkey(memno.getText().toUpperCase());                  if (obj == null) {                     JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);                 } else {                     customerInfo = new CustomerInfo(obj[0].toString());                     customerInfo.setName(obj[1].toString());                     customerInfo.setSearchkey(memno.getText().toUpperCase());                     customerInfo.setMobile(String.valueOf(obj[3]));                     customerInfo.setAccno(obj[4].toString());                     System.out.println(customerInfo.getAccno());                     mname.setText(obj[1].toString());                 }             } catch (Exception e) {                 e.printStackTrace();             }         } else {             mname.setText(null);             customerInfo = null;          }     }//GEN-LAST:event_memnoKeyPressed

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname.setText(customerInfo.toString());
                memno.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
            
            
        }
            /*
         JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);         finder.setVisible(true);         customerInfo = finder.getSelectedCustomer();         if (customerInfo != null) {             try {                 mname.setText(customerInfo.toString());                 memno.setText(customerInfo.getSearchkey());             } catch (Exception e) {                 MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);                 msg.show(this);             }         }     }//GEN-LAST:event_jButton2ActionPerformed
*/
    private void DateSelect3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect3ActionPerformed
       
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fDate.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
                fDate.setText(Formats.TIMESTAMP.formatValue(date));
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
        
    }//GEN-LAST:event_DateSelect3ActionPerformed

    private void DateSelect4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect4ActionPerformed
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(tDate.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
                tDate.setText(Formats.TIMESTAMP.formatValue(date));
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
    }//GEN-LAST:event_DateSelect4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try
       {
           if(fDate.getText()!=null && fDate.getText().length()>0 && tDate.getText()!=null && tDate.getText().length()>0 && memno.getText().length()>0 && mname.getText().length()>0)
           {
               Date fromDate = (Date) Formats.TIMESTAMP.parseValue(fDate.getText());
               Date toDate = (Date) Formats.TIMESTAMP.parseValue(tDate.getText());
               
               dmodelForReports = DueListNoticeTableModel.loadInstanceForMemWiseReports(dlfac, m_App, customerInfo, fromDate, toDate);
               
               jTable7.setModel(dmodelForReports.getMemWiseTableModel());
               jTable7.getColumnModel().getColumn(0).setMaxWidth(100);
                jTable7.getColumnModel().getColumn(0).setPreferredWidth(50);
                jTable7.getColumnModel().getColumn(1).setMaxWidth(200);
                jTable7.getColumnModel().getColumn(1).setPreferredWidth(150);
                jTable7.getColumnModel().getColumn(2).setMaxWidth(200);
                jTable7.getColumnModel().getColumn(2).setPreferredWidth(100);
                jTable7.getColumnModel().getColumn(3).setMaxWidth(150);
                jTable7.getColumnModel().getColumn(3).setPreferredWidth(100);
                jTable7.getColumnModel().getColumn(4).setMaxWidth(150);
                jTable7.getColumnModel().getColumn(4).setPreferredWidth(100);
                jTable7.getColumnModel().getColumn(5).setMaxWidth(150);
                jTable7.getColumnModel().getColumn(5).setPreferredWidth(100);
                jTable7.getColumnModel().getColumn(6).setMaxWidth(150);
                jTable7.getColumnModel().getColumn(6).setPreferredWidth(100);
                jTable7.getColumnModel().getColumn(7).setMaxWidth(150);
                jTable7.getColumnModel().getColumn(7).setPreferredWidth(100);
                selectedMemberNoticeDetailsPanel.setVisible(true);
              //   com.openbravo.format.Formats.CURRENCY.formatValue($V{tdebit})
                List<ReportLine> selectedMem = dmodelForReports.getReportList1();
                if(selectedMem.size()>0)
                {
                    ReportLine rl = selectedMem.get(0);
                    
                    double due = rl.getDueAndOverdue()- rl.getCreditAmt();
                    currentDueIndicator.setText(com.openbravo.format.Formats.CURRENCY.formatValue(due));
                }
                else
                {
                    currentDueIndicator.setText(com.openbravo.format.Formats.CURRENCY.formatValue(0.0));
                }
           
           }
           else
           {
               JOptionPane.showMessageDialog(null, "Please fill the form and then click on Execute!!", "Fill the Form", JOptionPane.ERROR_MESSAGE);
           }
           
           
           
       }
       catch(Exception ex)
       {
           new MessageInf(ex).show(getParent());
       }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void scrolSecondPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_scrolSecondPropertyChange
        
        
    }//GEN-LAST:event_scrolSecondPropertyChange

    private void jTable3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable3PropertyChange
         JTable table = (JTable) evt.getSource();
        if(table.getEditingRow()!=-1)
        {
           if(dmodelForFirstNotice==null && dmodelForSecondNotice!=null && dmodelForFinalNotice==null)
            {
                boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable3.getValueAt(row, 10);
            if(selected)
            {
             double overdue = Double.parseDouble(jTable3.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable3.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable3.getValueAt(row, 6).toString().replaceAll(",", "").trim());
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(false);
                     jTable3.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportList1().get(row).setAll(false);
                     jTable3.setValueAt(false, row, 10);
                 }  
             }
            }
            }
           
        }
    }//GEN-LAST:event_jTable3PropertyChange

    private void jTable5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable5PropertyChange
        JTable table = (JTable) evt.getSource();
        if(table.getEditingRow()!=-1)
        {
            if(dmodelForFirstNotice==null && dmodelForSecondNotice==null && dmodelForFinalNotice!=null)
            {
                 boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable5.getValueAt(row, 10);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable5.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable5.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable5.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(false);
                     jTable5.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportList1().get(row).setAll(false);
                     jTable5.setValueAt(false, row, 10);
                 }  
             }
             }
            }
        }
    }//GEN-LAST:event_jTable5PropertyChange

    private void jTable4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable4PropertyChange
        JTable table = (JTable) evt.getSource();
    
        if(table.getEditingRow()!=-1)
        {
            if(dmodelForSecondNotice!=null && dmodelForFinalNotice==null && dmodelForFirstNotice==null)
            {
                boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable4.getValueAt(row, 10);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable4.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable4.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable4.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable4.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForSecondNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable4.setValueAt(false, row, 10);
                 }  
             }
        }    
            }
        }
    }//GEN-LAST:event_jTable4PropertyChange

    private void jTable6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable6PropertyChange
        JTable table = (JTable) evt.getSource();
    
        if(table.getEditingRow()!=-1)
        {
           if(dmodelForSecondNotice==null && dmodelForFinalNotice!=null && dmodelForFirstNotice==null)
            {
                 boolean b = table.hasFocus();
            int qq = table.getSelectedRow();
            
            int row = table.getEditingRow();
            boolean selected = (Boolean) jTable6.getValueAt(row, 10);
            
            if(selected)
            {
             double overdue = Double.parseDouble(jTable6.getValueAt(row, 8).toString().replaceAll(",", "").trim());
             double due = Double.parseDouble(jTable6.getValueAt(row, 7).toString().replaceAll(",", "").trim());
             double credit = Double.parseDouble(jTable6.getValueAt(row, 6).toString().replaceAll(",", "").trim());
                
             if(credit>(overdue+due))
             {
                 if(JOptionPane.showConfirmDialog(null, "Credit available is more than the due amount. Do you want to select this member??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable6.setValueAt(false, row, 10);
                 }
             }
             else if(credit==(overdue+due))
             {
               if(JOptionPane.showConfirmDialog(null, "Credit available is equal to due amount. Do you want to select this member??", "Cr.Available is equal to Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(true);
                 }
                 else
                 {
                     dmodelForFinalNotice.getReportListForAlreadyGenerated().get(row).setAll(false);
                     jTable6.setValueAt(false, row, 10);
                 }  
             }
        }
            }
        
        }
    }//GEN-LAST:event_jTable6PropertyChange

    private void memWiseNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memWiseNTActionPerformed
        mainButInReports(false);
        memWiseNoticeTrackerPanel.setVisible(true);
    }//GEN-LAST:event_memWiseNTActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
       if(jTable7.getSelectedRow()!=-1)
       {
           viewNotice.setEnabled(true);
           int row = jTable7.getSelectedRow();
        NoticeTrackedBean Sntb = dmodelForReports.getAllNoticeListForIndividualMembers().get(row);
        if(Sntb.getLinkToAck()!=null && Sntb.getLinkToAck().length()>0)
        {
            viewAcknowlwdgement.setEnabled(true);
        }
        else
        {
             viewAcknowlwdgement.setEnabled(false);
        }
           
       }
       else
       {
           viewNotice.setEnabled(false);
           viewAcknowlwdgement.setEnabled(false);
           viewAcknowlwdgement.setEnabled(false);
       }
        
    }//GEN-LAST:event_jTable7MouseClicked

    private void jTable7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable7FocusGained
         if(jTable7.getSelectedRow()!=-1)
       {
           viewNotice.setEnabled(true);
           int row = jTable7.getSelectedRow();
        NoticeTrackedBean Sntb = dmodelForReports.getAllNoticeListForIndividualMembers().get(row);
        if(Sntb.getLinkToAck()!=null && Sntb.getLinkToAck().length()>0)
        {
            viewAcknowlwdgement.setEnabled(true);
        }
        else
        {
             viewAcknowlwdgement.setEnabled(false);
        }
           
       }
       else
       {
           viewNotice.setEnabled(false);
           viewAcknowlwdgement.setEnabled(false);
           viewAcknowlwdgement.setEnabled(false);
       }
    }//GEN-LAST:event_jTable7FocusGained

    private void viewNoticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewNoticeActionPerformed
        
        if(jTable7.getSelectedRow()!=-1)
       {
            try {
                int row = jTable7.getSelectedRow();
             NoticeTrackedBean Sntb = dmodelForReports.getAllNoticeListForIndividualMembers().get(row);
             File file = new File(Sntb.getLinkToNotice().replace("./", ""));
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "File Does Not Exsist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
       }
       else
       {
           viewNotice.setEnabled(false);
           viewAcknowlwdgement.setEnabled(false);
           
       }
        
    }//GEN-LAST:event_viewNoticeActionPerformed

    private void viewAcknowlwdgementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAcknowlwdgementActionPerformed
        
        if(jTable7.getSelectedRow()!=-1)
       {
            try {
                int row = jTable7.getSelectedRow();
             NoticeTrackedBean Sntb = dmodelForReports.getAllNoticeListForIndividualMembers().get(row);
             File file = new File(Sntb.getLinkToAck());
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "File Does Not Exsist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
            }
       }
       else
       {
           viewNotice.setEnabled(false);
           viewAcknowlwdgement.setEnabled(false);
           
       }
    }//GEN-LAST:event_viewAcknowlwdgementActionPerformed

    private void generateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportActionPerformed
        try {
            SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
            Date fromDate = (Date) Formats.TIMESTAMP.parseValue(fDate.getText());
            Date toDate2 = (Date) Formats.TIMESTAMP.parseValue(tDate.getText());
            String frmDate = sdfFrom.format(fromDate);
            String tDate1 = sdfFrom.format(toDate2);
            String header2 = "Details of all notices issued between  " +frmDate+ "  and  " +tDate1+".";
            Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());
            reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
            reportparams.put("header2", header2);
            
            
       DataSourceProvider data1 = new DataSourceProvider(dmodelForReports.getAllNoticeListForIndividualMembers());
       DataSourceForNoticeToMemberWiseReports ds1 = new DataSourceForNoticeToMemberWiseReports(dmodelForReports.getAllNoticeListForIndividualMembers());
       data1.setDataSource(ds1);        
       JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers_MemWiseReport.jrxml", reportparams, false, data1, true, null);
        
        
        
        } catch (BasicException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_generateReportActionPerformed

    public void reset1(){
        detailsToGenerateList.setVisible(false);
        dateForReportNoticeList.setVisible(false);
        jLabel37.setVisible(false);
        generateNotice.setVisible(false);
        detailsToGenerateList.setVisible(false);
        mainButInReports(true);
       jPanel11.setVisible(false);
       Signature.setText(null);
       Signature1.setText(null);
       Designation.setText(null);
       Designation1.setText(null);
       selectedMemberNoticeDetailsPanel.setVisible(false);
       viewNotice.setEnabled(false);
       viewAcknowlwdgement.setEnabled(false);
       currentDueIndicator.setText(null);
       memWiseNoticeTrackerPanel.setVisible(false);
       memno.setText(null);
       fDate.setText(null);
       tDate.setText(null);
       mname.setText(null);
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AddNotes1;
    private javax.swing.JTextArea AdditionalNotes;
    private javax.swing.JButton Cancel1;
    private javax.swing.JButton Cancel4;
    private javax.swing.JButton Cancel5;
    private javax.swing.JTextField CommMode;
    private javax.swing.JButton Create;
    private javax.swing.JPanel CreatePanal;
    private javax.swing.JTextField DP_num;
    private javax.swing.JTextField DP_num1;
    private javax.swing.JButton DateSelect;
    private javax.swing.JButton DateSelect1;
    private javax.swing.JButton DateSelect2;
    private javax.swing.JButton DateSelect3;
    private javax.swing.JButton DateSelect4;
    private javax.swing.JTextField DateTextField;
    private javax.swing.JLabel Days;
    private javax.swing.JLabel Days1;
    private javax.swing.JTextField Designation;
    private javax.swing.JTextField Designation1;
    private javax.swing.JLabel Due;
    private javax.swing.JButton Execute;
    private javax.swing.JButton Generate;
    private javax.swing.JButton Generate1;
    private javax.swing.JPanel MainButtons;
    private javax.swing.JTextArea MemType;
    private javax.swing.JLabel Months;
    private javax.swing.JLabel Months1;
    private javax.swing.JPanel NoticeDetails;
    private javax.swing.JButton NoticeViewInUpdate;
    private javax.swing.JLabel PassDue;
    private javax.swing.JPanel ReferenceDetails;
    private javax.swing.JButton Report;
    private javax.swing.JCheckBox ShowAll4;
    private javax.swing.JTextField Signature;
    private javax.swing.JTextField Signature1;
    private javax.swing.JButton Update;
    private javax.swing.JButton UpdateNotice;
    private javax.swing.JPanel UpdatePanel;
    private javax.swing.JPanel UpdateSelectedNotice;
    private javax.swing.JTable UpdateTable;
    private javax.swing.JButton ViewNotice;
    private javax.swing.JLabel ackPath;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField charCount;
    private javax.swing.JTextField charCount1;
    private javax.swing.JTextField currentDueIndicator;
    private javax.swing.JTextField dAmount;
    private javax.swing.JRadioButton dNo;
    private javax.swing.JRadioButton dYes;
    private javax.swing.JComboBox dateForReportNoticeList;
    private javax.swing.JTextField dateOfDispatch;
    private javax.swing.JPanel detailsToGenerateList;
    private javax.swing.JTextField displayName;
    private javax.swing.JTextField fDate;
    private javax.swing.JTextField facility;
    private javax.swing.JTextField gDate;
    private javax.swing.JButton generateList;
    private javax.swing.JButton generateNotice;
    private javax.swing.JButton generateReport;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JEditorPane jEditorPane1;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSign;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextField memName;
    private javax.swing.JScrollPane memNoticeDetailsTableScrolPanel;
    private javax.swing.JTextField memSKey;
    private javax.swing.JButton memWiseNT;
    private javax.swing.JPanel memWiseNoticeTrackerPanel;
    private javax.swing.JTextField memno;
    private javax.swing.JTextField mname;
    private javax.swing.JTextField nName;
    private javax.swing.JLabel noticeId;
    private javax.swing.JComboBox noticeListForReport;
    private javax.swing.JLabel noticePath;
    private javax.swing.JTextField pDate;
    private javax.swing.JTextField recBy;
    private javax.swing.JTextField recDate;
    private javax.swing.JComboBox recRef;
    private javax.swing.JScrollPane scrolFinal;
    private javax.swing.JScrollPane scrolFinalAG;
    private javax.swing.JScrollPane scrolFirst;
    private javax.swing.JScrollPane scrolFirstAG;
    private javax.swing.JScrollPane scrolSecond;
    private javax.swing.JScrollPane scrolSecondAG;
    private javax.swing.JButton selectFile;
    private javax.swing.JPanel selectedMemberNoticeDetailsPanel;
    private javax.swing.JLabel sourceFile;
    private javax.swing.JTextField tDate;
    private javax.swing.JLabel targetFileName;
    private javax.swing.JComboBox typeOfNotice;
    private javax.swing.JComboBox typeOfNotice2;
    private javax.swing.JButton viewAck;
    private javax.swing.JButton viewAcknowlwdgement;
    private javax.swing.JButton viewNotice;
    private javax.swing.JLabel waitingMess_text;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "";
    }
    
    public void loaddata()
    {
        try {
            ntmt = NoticeMasterTableModel.loadInstance(m_App,2);
        } catch (BasicException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
        noticeList = ntmt.getfacilityline();
        if(noticeList==null)
        {
            noticeList = new ArrayList<NoticeMasterBean>();
        }
        noticemodel = new ComboBoxValModel(noticeList);
        typeOfNotice.setModel(noticemodel);
        typeOfNotice.setSelectedIndex(-1);
         typeOfNotice2.setModel(noticemodel);
        typeOfNotice2.setSelectedIndex(-1);
         
         ////////////////////////////////////////
           try {
         Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CONTENT FROM RESOURCES where NAME='Window.ClubLogo'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.IMAGE})).find();
        if(obj!=null){
            if(obj[0]!=null){
               ig=(Image) obj[0]; 
               
            }
        }
           } catch (BasicException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
           ////////////////////////////////////////////////////////////////added by pratima
        
    }

    public void activate() throws BasicException {
        
        MainButtons.setVisible(true);
        actDeaButtons(true);
        CreatePanal.setVisible(false);
        loaddata();
      //  dmodel=DueListNoticeTableModel.loadEmptyInstance();
        rflist=new ArrayList<Facility>();
        jTabbedPane1.setEnabledAt(1, false);
        typeOfNotice.setSelectedIndex(-1);
        NoticeDetails.setVisible(false);
        jPanel4.setVisible(false);
        UpdatePanel.setVisible(false);
        UpdateSelectedNotice.setVisible(false);
        dmodel2 = null;
        dmodelForFirstNotice = null;
        dmodelForSecondNotice = null;
        jTabbedPane1.setTitleAt(1, "");
        jTabbedPane1.setTitleAt(2, "");
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setSelectedIndex(0);
        reset();
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        
            m_App = app;
            dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
            dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
            m_dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
            s=m_App.getSession();
            buttonGoup();
            
          
          
    }

    public Object getBean() {
        return this;
    }
    
    public void actDeaButtons(Boolean b)
    {
        Create.setVisible(b);
        Update.setVisible(b);
        Report.setVisible(b);
    }
    public void reset()
    {
       // typeOfNotice.setSelectedIndex(-1);
        DateTextField.setText(null);
        facility.setText(null);
        displayName.setText(null);
        DP_num.setText(null);
        DP_num1.setText(null);
        MemType.setText(null);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
         jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setTitleAt(1, "");
       jTabbedPane1.setTitleAt(2, "");
       jTabbedPane1.setTitleAt(3, "");
        AdditionalNotes.setText(null);
        Days1.setVisible(false);
        Months.setVisible(false);
        Months1.setVisible(false);
        Days.setVisible(false);
        jTextPane2.setText(null);
        jTextPane3.setText(null);
        UpdateTable.setVisible(false);
        nName.setText(null);
        memName.setText(null);
        memSKey.setText(null);
        pDate.setText(null);
        gDate.setText(null);
        dAmount.setText(null);
        CommMode.setText(null);
        dateOfDispatch.setText(null);
        recBy.setText(null);
        recDate.setText(null);
        ReferenceDetails.setVisible(false);
        recRef.setSelectedIndex(0);
        viewAck.setEnabled(false);
        noticePath.setText(null);
        ackPath.setText(null);
        noticeId.setText(null);
        sourceFile.setText(null);
        targetFileName.setText(null);
       
        jLabel31.setText("650)");
        charCount.setText("650");
       
            scrolFirst.setVisible(true);
            scrolSecond.setVisible(false);
            scrolSecondAG.setVisible(false);
            scrolFirstAG.setVisible(true);
            scrolFinal.setVisible(false);
            scrolFinal.setVisible(false);
        
    }
    
    public void resetPanels()
    {
        //typeOfNotice.setSelectedIndex(-1);
        UpdatePanel.setVisible(false);
        CreatePanal.setVisible(false);
        //typeOfNotice2.setSelectedIndex(-1);
        
    }
    
    public void showDialog(DueListNoticeTableModel dmodelFN,List<Facility> rflist,AppView m_App){
       this.rflist=rflist;
       this.m_App=m_App;
     
         jTable1.setModel(dmodelFN.getTableModel());
         jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(56);
        jTable2.setModel(dmodelFN.getTableModel2());
         jTable2.getColumnModel().getColumn(6).setMinWidth(0);
        jTable2.getColumnModel().getColumn(6).setMaxWidth(56);
         TableColumn tc = jTable1.getColumnModel().getColumn(6);
         tc.setHeaderRenderer(null);
         tc.setCellEditor(jTable1.getDefaultEditor(Boolean.class));
         tc.setCellRenderer(jTable1.getDefaultRenderer(Boolean.class));
         tc.setHeaderRenderer(new CheckBoxHeader(jTable1.getTableHeader()));  
        jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
        TableColumn tc1 = jTable2.getColumnModel().getColumn(6);
         tc1.setCellEditor(jTable2.getDefaultEditor(Boolean.class));
         tc1.setCellRenderer(jTable2.getDefaultRenderer(Boolean.class));
         tc1.setHeaderRenderer(null);
         tc1.setHeaderRenderer(new CheckBoxHeader2(jTable2.getTableHeader()));  
        jTable2.setPreferredScrollableViewportSize(jTable2.getPreferredSize());
    
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setTitleAt(1, "DueList For Notice");
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setTitleAt(2, "DueList For Notice Generated");
       jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   
       this.dmodelForFirstNotice=dmodelFN;
            int columncnt=jTable1.getColumnModel().getColumnCount();
         if(columncnt>0){
              jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
              jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
              jTable1.getColumnModel().getColumn(1).setMaxWidth(120);
              jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable1.getColumnModel().getColumn(k).setMaxWidth(100);
                jTable1.getColumnModel().getColumn(k).setPreferredWidth(150);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
               }else if((k-2)%3==1){
                jTable1.getColumnModel().getColumn(k).setMaxWidth(60);
                jTable1.getColumnModel().getColumn(k).setPreferredWidth(60);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
               } else if((k-2)%3==2){
                jTable1.getColumnModel().getColumn(k).setMaxWidth(60);
                jTable1.getColumnModel().getColumn(k).setPreferredWidth(60);
              
                jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
                jTable1.getColumnModel().getColumn(6).setMaxWidth(60);
                jTable1.getColumnModel().getColumn(6).setPreferredWidth(60);
               }
              }
            }
         
         int columncnt1=jTable2.getColumnModel().getColumnCount();
         if(columncnt1>0){
              jTable2.getColumnModel().getColumn(0).setMaxWidth(100);
              jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
              jTable2.getColumnModel().getColumn(1).setMaxWidth(120);
              jTable2.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable2.getColumnModel().getColumn(k).setMaxWidth(100);
                jTable2.getColumnModel().getColumn(k).setPreferredWidth(150);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
               }else if((k-2)%3==1){
                jTable2.getColumnModel().getColumn(k).setMaxWidth(60);
                jTable2.getColumnModel().getColumn(k).setPreferredWidth(60);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
               } else if((k-2)%3==2){
                jTable2.getColumnModel().getColumn(k).setMaxWidth(60);
                jTable2.getColumnModel().getColumn(k).setPreferredWidth(60);
              
                jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
                jTable2.getColumnModel().getColumn(6).setMaxWidth(60);
                jTable2.getColumnModel().getColumn(6).setPreferredWidth(60);
               }
              }
            }
      
   }

    private void generateFirstNoticeToSelectedMemebers(List<ReportLine> selectedList) {
        try {
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
            final List<ReportLine> selList = selectedList;
            
                
            //    Thread t = new Thread(new Runnable() {

           //     public void run() {
                    
         try
            {
              Transaction t = new Transaction(m_App.getSession()) {
                            @Override
                            protected Object transact() throws BasicException {
                                String dayOrMonth = null;
            int payment =0;
            int overdue = 0;
            if(!nmb.isDp_dm())
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Days";
                }
                else
                {
                    dayOrMonth = "Day";
                }
            }
            else
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Months";
                }
                else
                {
                    dayOrMonth = "Month";
                }
            }
            if(!nmb.isPayment_date_dm())
            {
                payment = nmb.getPayment_date_num();
            }
            else
            {
                payment = nmb.getPayment_date_num()*30;
            }
            
            if(nmb.isDue_pass())
         {
            if(nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         else
         {
             if(!nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
            String header2 = " " + nmb.getDp_num() +" "+ dayOrMonth +" ";
           
            Calendar cal=Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            Date duedate=new Date(toDate.getTime());
            Calendar cald=Calendar.getInstance();
            cald.setTimeInMillis(duedate.getTime());
            int i=cald.get(Calendar.DATE);
            duedate.setTime(cald.getTimeInMillis());
            toDate.setTime(cal.getTimeInMillis());
            cal.add(Calendar.DATE, -overdue);
            Date dueDateAsOnGeneratedDate = cal.getTime();
            Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(new Date());
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3 = cal2;
            cal2.add(Calendar.DATE, payment);
           DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForFirstNotice(m_App, dlfac, toDate, nmb, selList);
           SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
           String dueDate = sdfFrom.format(cal.getTime());
       
           Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
           reportparams.put("duedate", dueDate);
                reportparams.put("note", AdditionalNotes.getText());
                reportparams.put("header2", header2);
                reportparams.put("noticeName", nmb.getNoticeNameToDisplay());
                reportparams.put("generatedDate", sdfFrom.format(toDate));
                reportparams.put("paymentDate", sdfFrom.format(cal2.getTime()));
              //  reportparams.put("sign", nmb.getSign());
                reportparams.put("designation", nmb.getDesg());
                reportparams.put("logo",ig);
                
           if(nmb.isSignImgFlg()){
               reportparams.put("signImg",signImg);
               reportparams.put("sign", "");
           }else { 
               reportparams.put("sign", nmb.getSign());
               reportparams.put("signImg",null);
           }
           DataSourceProvider data1 = new DataSourceProvider(dlntm.getfacilityline1());
                DataSourceForNoticeToMembers ds1 = new DataSourceForNoticeToMembers(dlntm.getfacilityline1());
                data1.setDataSource(ds1);
                
               final Date paymentdate = cal2.getTime();
               final Map reportparams1 = reportparams;
               final DataSourceProvider dsp = data1;
               
               if(nmb.isDeactivateMem())
               {
              boolean b =  deactivatemembers(selList, nmb);
               }
                    
                                                    List<ReportLine> individual ;
                                                    Date d = new Date();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                                                    String dateToFilename = sdf.format(d).toString();
                                                    for (Iterator<ReportLine> it = selList.iterator(); it.hasNext();) {
                                    try {
                                        ReportLine selectedMembers = it.next();
                                        String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                        
                                        individual = new ArrayList<ReportLine>();
                                        individual.add(selectedMembers);
                                       DueListNoticeTableModel dlntm2 = DueListNoticeTableModel.loadInstanceByIndividualMemberForFirstNotice(m_App, dlfac, toDate, nmb, individual);
                                        DataSourceProvider data2 = new DataSourceProvider(dlntm2.getfacilityline1());
                                        DataSourceForNoticeToMembers ds2 = new DataSourceForNoticeToMembers(dlntm2.getfacilityline1());
                                        data2.setDataSource(ds2);
                                        
                                        JasperPrint jp1 = new JasperPrint();
                                        JasperDesign jasperDesign = JRXmlLoader.load("./reports/com/openbravo/reports/NoticeToMembers.jrxml");
                                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                        JRDataSource data= data2.create(jasperReport);
                                        jp1 = JasperFillManager.fillReport(jasperReport, reportparams1, data);
                                        
                                        JRPdfExporter exporterPdf = new JRPdfExporter();
                                        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jp1);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filename );
                                        System.out.println("File name = " + filename);
                                        exporterPdf.exportReport();
                                        
                                     int num =    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO NOTICETRACKER(ID,NOTICEID,MEMID,GENERATEDDATE,DUEAMOUNT,PAYBYDATE,RECEIVEDREF,LINKTONOTICE, ACTIVE, DUEDATEONGENDATE, GENERATEDBY, DISPLAYNAME, SELECTEDDATE, LOCATIONOFCREATION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING,Datas.BOOLEAN, Datas.TIMESTAMP,Datas.STRING,Datas.STRING, Datas.TIMESTAMP,Datas.STRING})
                                        ).exec(new Object[]{UUID.randomUUID().toString(), nmb.getId(), selectedMembers.getId(), new Date(), selectedMembers.getDueAndOverdue(),paymentdate, false, filename , true, dueDateAsOnGeneratedDate, m_App.getAppUserView().getUser().getName().toString(), nmb.getNoticeNameToDisplay(), toDate, m_App.getProperties().getHost() });
                                        
                                      
                                      String tid = UUID.randomUUID().toString();
                                      String narration = "Amount for Postal Charge of "+nmb.getName() ;
                                     
                                      
                                       boolean flag = false;
                                       Object[] PostalFlag = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGFLAG FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(nmb.getId());
                                       if(PostalFlag!=null){
                                           flag=Boolean.parseBoolean(PostalFlag[0].toString());  
                                       }
                                      
                                      
                                      if(flag){
                                          
                                        Object[] PostalChrg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGAMT FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(nmb.getId()); 
                                        Double PostChrg  = Double.parseDouble(PostalChrg[0].toString());
                                        
                                        String AccountId = null;
                                        Object[] PostalAccount = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nmb.getId()); 
                                        if(PostalAccount!=null){
                                            AccountId = PostalAccount[0].toString();
                                        }
                                        
                                        if(AccountId!=null){
                                            
                                            
                                            
                                            Object[] CustAccountid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedMembers.getId()); 
                                            String CustAccId = CustAccountid[0].toString();
                                            
                                            
                                            Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, selectedMembers.getId(), new Date(), "D", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg, new Date(), false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, CustAccId ,PostChrg, null , null, true};
                                            dlfac.insertintoaccjoutnal2(cvalue);
                                            Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, new Date(), "C", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg , new Date() , true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, AccountId, PostChrg,null, null, true};
                                            dlfac.insertintoaccjoutnal2(dvalue);  
                                            
                                        }
                                          
                                        
                                          
                                      }
                                     
                                      
                                     
                                     
                                     
                                     if(num !=1)
                                     {
                                        JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                     }
                                    } catch (JRException ex) {
                                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                                        throw new BasicException(ex.getMessage());
                                    }
                                                    }
                                                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers.jrxml", reportparams1, false, dsp, true, null);
                                                    
                                                    w.hideDialog();    
                                return null;
                            }
                        } ; 
              t.execute();
                                                    
            }
            catch (Exception ex)
	    {
                w.hideDialog();
		ex.printStackTrace();
                new MessageInf(ex).show(getParent());
	    }
                                                
                    
         //       }
         //   });
               //     t.start();
               //    w.showDialog("Please wait.Loading Report...");
                   
					
                
                
        
        } catch (Exception ex) {
             w.hideDialog();
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
           
        }
        
     
    }
    
    private void generateSecondNoticeToSelectedMemebers(final List<ReportLine> selectedList, final List<NoticeTrackedBean> ntBeansForSelectedMembers1) {
        
      //  try {
            
            
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
            
                
             //   Thread t = new Thread(new Runnable() {

              //  public void run() {
                  
                            generateSecondNoticeForAllNewMethod(selectedList , ntBeansForSelectedMembers1 );
                    
             //   } 
          //  });
               //     t.start();
           //   w.showDialog("Please wait. Generating Report...");
     //   } catch (Exception ex) {
     //        if(w!=null)
      //              w.hideDialog();
      //      Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
      //      new MessageInf(ex).show(getParent());
     //   }
    }
    
    
      public void generateSecondNoticeForAllNewMethod(List<ReportLine> selectedList,  List<NoticeTrackedBean> ntBeansForSelectedMembers1){
        
          final List<ReportLine> selList = selectedList;
          final List<NoticeTrackedBean> ntBeansForSelectedMembers = ntBeansForSelectedMembers1;
          
          
          try
                {    
                    Transaction t1 = new Transaction(m_App.getSession()) {

                        @Override
                        protected Object transact() throws BasicException {
                             String dayOrMonth = null;
                        int payment =0;
                        int overdue = 0;
                        if(!nmb.isDp_dm())
                        {
                            if(nmb.getDp_num()>1)
                            {
                                dayOrMonth = "Days";
                            }
                            else
                            {
                                dayOrMonth = "Day";
                            }
                        }
                        else
                        {
                            if(nmb.getDp_num()>1)
                            {
                                dayOrMonth = "Months";
                            }
                            else
                            {
                                dayOrMonth = "Month";
                            }
                        }

                        if(!nmb.isPayment_date_dm())
                        {
                            payment = nmb.getPayment_date_num();
                        }
                        else
                        {
                            payment = nmb.getPayment_date_num()*30;
                        }

                        if(nmb.isDue_pass())
                     {
                        if(nmb.isDp_dm())
                        {
                            overdue = nmb.getDp_num();
                        }
                        else
                        {
                            overdue = nmb.getDp_num() * 30;
                        }
                     }
                     else
                     {
                         if(!nmb.isDp_dm())
                        {
                            overdue = nmb.getDp_num();
                        }
                        else
                        {
                            overdue = nmb.getDp_num() * 30;
                        }
                     }
                        String header2 = " " + nmb.getDp_num() +" "+ dayOrMonth +" ";

                        Calendar cal=Calendar.getInstance();
                        cal.setTime(toDate);
                        cal.set(Calendar.HOUR_OF_DAY, 23);
                        cal.set(Calendar.MINUTE, 59);
                        cal.set(Calendar.SECOND, 59);
                        cal.set(Calendar.MILLISECOND, 59);
                        Date duedate=new Date(toDate.getTime());
                        Calendar cald=Calendar.getInstance();
                        cald.setTimeInMillis(duedate.getTime());
                        int i=cald.get(Calendar.DATE);
                        duedate.setTime(cald.getTimeInMillis());
                        toDate.setTime(cal.getTimeInMillis());
                        cal.add(Calendar.DATE, -overdue);
                        Date dueDateAsOnGeneratedDate = cal.getTime();
                        Calendar cal2=Calendar.getInstance();
                        Calendar cal3=Calendar.getInstance();
                        cal2.setTime(new Date());
                        cal2.set(Calendar.HOUR_OF_DAY, 23);
                        cal2.set(Calendar.MINUTE, 59);
                        cal2.set(Calendar.SECOND, 59);
                        cal2.set(Calendar.MILLISECOND, 59);
                        cal3 = cal2;
                        cal2.add(Calendar.DATE, payment);

                       DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, selList, ntBeansForSelectedMembers);
                       SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
                       String dueDate = sdfFrom.format(cal.getTime());

                       Map reportparams = new HashMap();
                            reportparams.put("companyName", m_App.getSession().getCompanyName());
                            reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                            reportparams.put("duedate", dueDate);
                            reportparams.put("note", AdditionalNotes.getText());
                            reportparams.put("header2", header2);
                            reportparams.put("noticeName", nmb.getNoticeNameToDisplay());
                            reportparams.put("generatedDate", sdfFrom.format(toDate));
                            reportparams.put("paymentDate", sdfFrom.format(cal2.getTime()));
                           // reportparams.put("sign", nmb.getSign());
                            reportparams.put("designation", nmb.getDesg());
                             reportparams.put("logo", ig);
                              if(nmb.isSignImgFlg()){
               reportparams.put("signImg",signImg);
               reportparams.put("sign", "");
           }else { 
               reportparams.put("sign", nmb.getSign());
               reportparams.put("signImg",null);
           }
                            DataSourceProvider data1 = new DataSourceProvider(dlntm.getfacilityline1(), dlntm.getAllNoticeListForIndividualMembers());
                            DataSourceForNoticeToMembersOtherThanFirst ds1 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm.getfacilityline1(),dlntm.getAllNoticeListForIndividualMembers());
                            data1.setDataSource(ds1);
                           final Date paymentdate = cal2.getTime();
                           final Map reportparams1 = reportparams;
                           final DataSourceProvider dsp = data1;
                           final int overdue1 = overdue;
                           Calendar calA=Calendar.getInstance();
                            calA.setTime(toDate);
                            calA.set(Calendar.HOUR_OF_DAY, 23);
                            calA.set(Calendar.MINUTE, 59);
                            calA.set(Calendar.SECOND, 59);
                            calA.set(Calendar.MILLISECOND, 59);
                            List<ReportLine> individual ;
                            List<NoticeTrackedBean> individualNTB ;
                            if(nmb.isDeactivateMem())
                           {
                          boolean b1 =  deactivatemembers(selList, nmb);
                           }
                                                                Date d = new Date();
                                                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                                                                String dateToFilename = sdf.format(d).toString();
                                                                
                                                      try {
                                                                
                                                                
                                                                for (int it = 0 ; it < selList.size(); it++) {
                                                                      
                                                                            ReportLine selectedMembers = selList.get(it);
                                                                            NoticeTrackedBean ntb = ntBeansForSelectedMembers.get(it);

                                                                            String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";

                                                                            individual = new ArrayList<ReportLine>();
                                                                            individualNTB = new ArrayList<NoticeTrackedBean>();
                                                                            individual.add(selectedMembers);
                                                                            individualNTB.add(ntb);
                                                                            DueListNoticeTableModel dlntm2 = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, individual, individualNTB);
                                                                            DataSourceProvider data2 = new DataSourceProvider(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers());
                                                                            DataSourceForNoticeToMembersOtherThanFirst ds2 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers() );
                                                                            data2.setDataSource(ds2);

                                                                            JasperPrint jp1 = new JasperPrint();
                                                                            JasperDesign jasperDesign = JRXmlLoader.load("./reports/com/openbravo/reports/NoticeToMembers_Second.jrxml");
                                                                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                                                            JRDataSource data= data2.create(jasperReport);
                                                                            jp1 = JasperFillManager.fillReport(jasperReport, reportparams1, data);

                                                                            JRPdfExporter exporterPdf = new JRPdfExporter();
                                                                            exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jp1);
                                                                            exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                                                                            exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                                                                            exporterPdf.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filename );
                                                                            System.out.println("File name = " + filename);
                                                                            exporterPdf.exportReport();

                                                                         int num =    new PreparedSentence(m_App.getSession()
                                                                            , "INSERT INTO NOTICETRACKER(ID,NOTICEID,MEMID,GENERATEDDATE,DUEAMOUNT,PAYBYDATE,RECEIVEDREF,LINKTONOTICE, ACTIVE, DUEDATEONGENDATE, GENERATEDBY,PARENTNOTICE,DISPLAYNAME, SELECTEDDATE, LOCATIONOFCREATION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING,Datas.BOOLEAN, Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP, Datas.STRING})
                                                                            ).exec(new Object[]{UUID.randomUUID().toString(), nmb.getId(), selectedMembers.getId(), new Date(), selectedMembers.getDueAndOverdue(),paymentdate, false, filename , true, ntb.getDueDateAsOnGeneratedDate(), m_App.getAppUserView().getUser().getName().toString(), ntb.getId().toString(), nmb.getNoticeNameToDisplay(), ntb.getSelectedDate(), m_App.getProperties().getHost() });

                                       // code edited for postal charges............                                 
                                                                        
                                                                          String tid = UUID.randomUUID().toString();
                                                                          String narration = "Amount for Postal Charge of "+nmb.getName() ;


                                                                           boolean flag = false;
                                                                           Object[] PostalFlag = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGFLAG FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(nmb.getId());
                                                                           if(PostalFlag!=null){
                                                                               flag=Boolean.parseBoolean(PostalFlag[0].toString());  
                                                                           }


                                                                          if(flag){

                                                                            Object[] PostalChrg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGAMT FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(nmb.getId()); 
                                                                            Double PostChrg  = Double.parseDouble(PostalChrg[0].toString());

                                                                            String AccountId = null;
                                                                            Object[] PostalAccount = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nmb.getId()); 
                                                                            if(PostalAccount!=null){
                                                                                AccountId = PostalAccount[0].toString();
                                                                            }

                                                                            if(AccountId!=null){



                                                                                Object[] CustAccountid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedMembers.getId()); 
                                                                                String CustAccId = CustAccountid[0].toString();


                                                                                Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, selectedMembers.getId(), new Date(), "D", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg, new Date(), false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, CustAccId ,PostChrg, null , null, true};
                                                                                dlfac.insertintoaccjoutnal2(cvalue);
                                                                                Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, new Date(), "C", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg , new Date() , true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, AccountId, PostChrg,null, null, true};
                                                                                dlfac.insertintoaccjoutnal2(dvalue);  

                                                                            }



                                                                          }

                                                                          if(num !=1)
                                                                         {
                                                                            JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                                                         }


                                                                        

                                                                }
                                                                
                                                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers_Second.jrxml", reportparams1, false, dsp, true, null);
                                                           
                                                                if(w!=null)
                                                                w.hideDialog();
                                                                
                                                                
                                                                
                                                                } catch (JRException ex) {
                                                                            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                                                                            throw new BasicException(ex.getMessage());
                                                               }
                                                                
                                                             

                                        return null;
                                    }
                    };
                    t1.execute();
                }
            catch (Exception ex)
	        {
                   if(w!=null)
                    w.hideDialog();
                    ex.printStackTrace();
                    new MessageInf(ex).show(getParent());
                }
  }
  
    
    
    
    
    private void generateFinalNoticeToSelectedMemebers(List<ReportLine> selectedListRP, List<NoticeTrackedBean> SelectedList1) {
        
          try {
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
            final List<ReportLine> selList = selectedListRP;
            final List<NoticeTrackedBean> ntBeansForSelectedMembers = SelectedList1;
            
          //   Thread t = new Thread(new Runnable() {

            //    public void run() {
                    
         try
            {            
            
            
            Transaction t = new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                   
                    
                   
                    
                    String dayOrMonth = null;
            int payment =0;
            int overdue = 0;
            if(!nmb.isDp_dm())
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Days";
                }
                else
                {
                    dayOrMonth = "Day";
                }
            }
            else
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Months";
                }
                else
                {
                    dayOrMonth = "Month";
                }
                
                
            }
            
            if(!nmb.isPayment_date_dm())
            {
                payment = nmb.getPayment_date_num();
            }
            else
            {
                payment = nmb.getPayment_date_num()*30;
            }
            
            if(nmb.isDue_pass())
         {
            if(nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         else
         {
             if(!nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
           
            String header2 = " " + nmb.getDp_num() +" "+ dayOrMonth +" ";
           
            Calendar cal=Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            Date duedate=new Date(toDate.getTime());
            Calendar cald=Calendar.getInstance();
            cald.setTimeInMillis(duedate.getTime());
            int i=cald.get(Calendar.DATE);
            duedate.setTime(cald.getTimeInMillis());
            toDate.setTime(cal.getTimeInMillis());
            cal.add(Calendar.DATE, -overdue);
            Date dueDateAsOnGeneratedDate = cal.getTime();
            Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(new Date());
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3 = cal2;
            cal2.add(Calendar.DATE, payment);
           DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForFinalNotice(m_App, dlfac, nmb, selList, ntBeansForSelectedMembers);
           SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
           String dueDate = sdfFrom.format(cal.getTime());
         ////////////////////////////////////////
           try {
         Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CONTENT FROM RESOURCES where NAME='Window.ClubLogo'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.IMAGE})).find();
        if(obj!=null){
            if(obj[0]!=null){
               ig=(Image) obj[0]; 
               
            }
        }
           } catch (BasicException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
           ////////////////////////////////////////////////////////////////added by pratima
        
           Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
           reportparams.put("duedate", dueDate);
                reportparams.put("note", AdditionalNotes.getText());
                reportparams.put("header2", header2);
                reportparams.put("noticeName", nmb.getNoticeNameToDisplay());
                reportparams.put("generatedDate", sdfFrom.format(toDate));
                reportparams.put("paymentDate", sdfFrom.format(cal2.getTime()));
               // reportparams.put("sign", nmb.getSign());
                reportparams.put("designation", nmb.getDesg());
                 reportparams.put("logo", ig);
                  if(nmb.isSignImgFlg()){
               reportparams.put("signImg",signImg);
               reportparams.put("sign", "");
           }else { 
               reportparams.put("sign", nmb.getSign());
               reportparams.put("signImg",null);
           }
                DataSourceProvider data1 = new DataSourceProvider(dlntm.getfacilityline1(), dlntm.getAllNoticeListForIndividualMembers());
                DataSourceForNoticeToMembersFinalNotice ds1 = new DataSourceForNoticeToMembersFinalNotice(dlntm.getfacilityline1(),dlntm.getPreviousNoticeList());
                data1.setDataSource(ds1);
               final Date paymentdate = cal2.getTime();
               final Map reportparams1 = reportparams;
               final DataSourceProvider dsp = data1;
               final int overdue1 = overdue;
               Calendar calA=Calendar.getInstance();
                calA.setTime(toDate);
                calA.set(Calendar.HOUR_OF_DAY, 23);
                calA.set(Calendar.MINUTE, 59);
                calA.set(Calendar.SECOND, 59);
                calA.set(Calendar.MILLISECOND, 59);
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers_Final.jrxml", reportparams1, false, dsp, true, null);
                  
                     if(nmb.isDeactivateMem())
               {
              boolean b =  deactivatemembers(selList, nmb);
               }
                    
                    
                                          
                                                  List<ReportLine> individual ;
                                                    List<NoticeTrackedBean> individualNTB ;
                                                    Date d = new Date();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                                                    String dateToFilename = sdf.format(d).toString();
                                                    for (int it = 0 ; it < selList.size(); it++) {
                                    try {
                                        ReportLine selectedMembers = selList.get(it);
                                        NoticeTrackedBean ntb = ntBeansForSelectedMembers.get(it);
                                        
                                        String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                        
                                        individual = new ArrayList<ReportLine>();
                                        individualNTB = new ArrayList<NoticeTrackedBean>();
                                        individual.add(selectedMembers);
                                        individualNTB.add(ntb);
                                        DueListNoticeTableModel dlntm2 = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, individual, individualNTB);
                                        DataSourceProvider data2 = new DataSourceProvider(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers());
                                        DataSourceForNoticeToMembersOtherThanFirst ds2 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers() );
                                        data2.setDataSource(ds2);
                                        
                                        JasperPrint jp1 = new JasperPrint();
                                        JasperDesign jasperDesign = JRXmlLoader.load("./reports/com/openbravo/reports/NoticeToMembers_Final.jrxml");
                                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                        JRDataSource data= data2.create(jasperReport);
                                        jp1 = JasperFillManager.fillReport(jasperReport, reportparams1, data);
                                        
                                        JRPdfExporter exporterPdf = new JRPdfExporter();
                                        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jp1);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filename );
                                        System.out.println("File name = " + filename);
                                        exporterPdf.exportReport();
                                        
                                     int num =    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO NOTICETRACKER(ID,NOTICEID,MEMID,GENERATEDDATE,DUEAMOUNT,PAYBYDATE,RECEIVEDREF,LINKTONOTICE, ACTIVE, DUEDATEONGENDATE, GENERATEDBY,PARENTNOTICE,DISPLAYNAME, SELECTEDDATE, LOCATIONOFCREATION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING,Datas.BOOLEAN, Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
                                        ).exec(new Object[]{UUID.randomUUID().toString(), nmb.getId(), selectedMembers.getId(), new Date(), selectedMembers.getDueAndOverdue(),paymentdate, false, filename , true, ntb.getDueDateAsOnGeneratedDate(), m_App.getAppUserView().getUser().getName().toString(), ntb.getId().toString(), nmb.getNoticeNameToDisplay(), ntb.getSelectedDate(), m_App.getProperties().getHost() });
                                        
                                     
 // code edited for postal charges..... 
                                     
                                      String tid = UUID.randomUUID().toString();
                                      String narration = "Amount for Postal Charge of "+nmb.getName() ;
                                     
                                      
                                       boolean flag = false;
                                       Object[] PostalFlag = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGFLAG FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(nmb.getId());
                                       if(PostalFlag!=null){
                                           flag=Boolean.parseBoolean(PostalFlag[0].toString());  
                                       }
                                      
                                      
                                      if(flag){
                                          
                                        Object[] PostalChrg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGAMT FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(nmb.getId()); 
                                        Double PostChrg  = Double.parseDouble(PostalChrg[0].toString());
                                        
                                        String AccountId = null;
                                        Object[] PostalAccount = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nmb.getId()); 
                                        if(PostalAccount!=null){
                                            AccountId = PostalAccount[0].toString();
                                        }
                                        
                                        if(AccountId!=null){
                                            
                                            
                                            
                                            Object[] CustAccountid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedMembers.getId()); 
                                            String CustAccId = CustAccountid[0].toString();
                                            
                                            
                                            Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, selectedMembers.getId(), new Date(), "D", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg, new Date(), false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, CustAccId ,PostChrg, null , null, true};
                                            dlfac.insertintoaccjoutnal2(cvalue);
                                            Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, new Date(), "C", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg , new Date() , true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, AccountId, PostChrg,null, null, true};
                                            dlfac.insertintoaccjoutnal2(dvalue);  
                                            
                                        }
                                          
                                        
                                          
                                      }
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     if(num !=1)
                                     {
                                        JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                     }
                                    } catch (JRException ex) {
                                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                                        throw new BasicException(ex.getMessage());
                                    }
 
                                                    }
                                                    
                                                    
                                                 w.hideDialog();
                                                 
                                                 return null;
                                                }
                                                };
                                                t.execute();
                                                  
						}
						catch (Exception ex)
						{
                                                 w.hideDialog();
							ex.printStackTrace();
                                                        new MessageInf(ex).show(getParent());
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
                                                
                    
             //   }
         //   });
          //          t.start();
          //    w.showDialog("Please wait. Generating Report...");
                   
					
                
                
        
        } catch (Exception ex) {
             w.hideDialog();
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
           
        }
        
        
        
        
    }

   

    private void regenerateFirstNoticeToSelectedMemebers(List<ReportLine> selectedList1, List<NoticeTrackedBean> ntb) {
        
        try {
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
           
            final List<ReportLine> list = selectedList1;
             final List<NoticeTrackedBean> ntbList = ntb;
               
            
          //  Thread thread =
			//new Thread(
				//new Runnable()
				//{
					//public void run()
					//{
						try
						{
                                                    Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                String dayOrMonth = null;
            int payment =0;
            int overdue = 0;
            if(!nmb.isDp_dm())
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Days";
                }
                else
                {
                    dayOrMonth = "Day";
                }
            }
            else
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Months";
                }
                else
                {
                    dayOrMonth = "Month";
                }
            }
            
            if(!nmb.isPayment_date_dm())
            {
                payment = nmb.getPayment_date_num();
            }
            else
            {
                payment = nmb.getPayment_date_num()*30;
            }
            if(nmb.isDue_pass())
         {
            if(nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         else
         {
             if(!nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
           
            String header2 = " " + nmb.getDp_num() +" "+ dayOrMonth +" ";
           
            Calendar cal=Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            Date duedate=new Date(toDate.getTime());
            Calendar cald=Calendar.getInstance();
            cald.setTimeInMillis(duedate.getTime());
            int i=cald.get(Calendar.DATE);
            
            duedate.setTime(cald.getTimeInMillis());
            toDate.setTime(cal.getTimeInMillis());
            cal.add(Calendar.DATE, -overdue);
            Date dueDateAsOnGeneratedDate = cal.getTime();
            Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(new Date());
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3 = cal2;
            cal2.add(Calendar.DATE, payment);
            
            
           DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForFirstNotice(m_App, dlfac, toDate, nmb, list);
           SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
           String dueDate = sdfFrom.format(cal.getTime());
        
           Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
           reportparams.put("duedate", dueDate);
                reportparams.put("note", AdditionalNotes.getText());
                reportparams.put("header2", header2);
                reportparams.put("noticeName", nmb.getNoticeNameToDisplay());
                reportparams.put("generatedDate", sdfFrom.format(toDate));
                reportparams.put("paymentDate", sdfFrom.format(cal2.getTime()));
               // reportparams.put("sign", nmb.getSign());
                reportparams.put("designation", nmb.getDesg());
                reportparams.put("logo", ig);
                 if(nmb.isSignImgFlg()){
               reportparams.put("signImg",signImg);
               reportparams.put("sign", "");
           }else { 
               reportparams.put("sign", nmb.getSign());
               reportparams.put("signImg",null);
           }
           DataSourceProvider data1 = new DataSourceProvider(dlntm.getfacilityline1());
                DataSourceForNoticeToMembers ds1 = new DataSourceForNoticeToMembers(dlntm.getfacilityline1());
                data1.setDataSource(ds1);
                final Map reportparams3 = reportparams;
                final DataSourceProvider dsp = data1;
                 final Map reportparams2 = reportparams;
                final Date paymentdate = cal2.getTime();
               
                final Date dueDateAsOn = dueDateAsOnGeneratedDate;
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers.jrxml", reportparams3, false, dsp, true, null);
                   
                                                    List<ReportLine> individual ;
                                                    Date d = new Date();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                                                    String dateToFilename = sdf.format(d).toString();
                                                    NoticeTrackedBean noticeToUpdate = null;
                                                    for (Iterator<ReportLine> it = list.iterator(); it.hasNext();) {
                                    try {
                                        ReportLine selectedMembers = it.next();
                                        String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                        for (Iterator<NoticeTrackedBean> it1 = ntbList.iterator(); it1.hasNext();) {
                                            NoticeTrackedBean noticeTrackedBean = it1.next();
                                            if(noticeTrackedBean.getMemSearchKey().equals(selectedMembers.getSearchkey()))
                                            {
                                                noticeToUpdate = noticeTrackedBean;
                                                break;
                                            }
                                        }
                                        individual = new ArrayList<ReportLine>();
                                        individual.add(selectedMembers);
                                       DueListNoticeTableModel dlntm2 = DueListNoticeTableModel.loadInstanceByIndividualMemberForFirstNotice(m_App, dlfac, toDate, nmb, individual);
                                        DataSourceProvider data2 = new DataSourceProvider(dlntm2.getfacilityline1());
                                        DataSourceForNoticeToMembers ds2 = new DataSourceForNoticeToMembers(dlntm2.getfacilityline1());
                                        data2.setDataSource(ds2);
                                        
                                        JasperPrint jp1 = new JasperPrint();
                                        JasperDesign jasperDesign = JRXmlLoader.load("./reports/com/openbravo/reports/NoticeToMembers.jrxml");
                                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                        JRDataSource data= data2.create(jasperReport);
                                        jp1 = JasperFillManager.fillReport(jasperReport, reportparams2, data);
                                        
                                        JRPdfExporter exporterPdf = new JRPdfExporter();
                                        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jp1);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filename );
                                        System.out.println("File name = " + filename);
                                        exporterPdf.exportReport();
                                        
                                       String uID = UUID.randomUUID().toString();
                                       String notes = "Regenerated on :"+ new Date().toString();
                                        
                                     int num =    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO NOTICETRACKER(ID,NOTICEID,MEMID,GENERATEDDATE,DUEAMOUNT,PAYBYDATE,RECEIVEDREF,LINKTONOTICE, ACTIVE, DUEDATEONGENDATE, GENERATEDBY,DISPLAYNAME,SELECTEDDATE, LOCATIONOFCREATION, NOTES) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.BOOLEAN,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING, Datas.STRING})
                                        ).exec(new Object[]{uID, nmb.getId(), selectedMembers.getId(),noticeToUpdate.getGeneratedDate(), selectedMembers.getDueAndOverdue(),paymentdate, false, filename , true, dueDateAsOn, m_App.getAppUserView().getUser().getName(), nmb.getNoticeNameToDisplay(), toDate , m_App.getProperties().getHost(), notes });
                                     
                                     int cnt = new PreparedSentence(m_App.getSession(), "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=?, LOCATIONOFDEACT = ?, DEACTIVATEDREF =? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), uID, noticeToUpdate.getId()});
                                     
                                     
                                     
                                      String tid = UUID.randomUUID().toString();
                                      String narration = "Amount for Postal Charge of "+nmb.getName()+" Regenerated" ;
                                     
                                      
                                       boolean flag = false;
                                       Object[] PostalFlag = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGFLAG FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(nmb.getId());
                                       if(PostalFlag!=null){
                                           flag=Boolean.parseBoolean(PostalFlag[0].toString());  
                                       }
                                      
                                      
                                      if(flag){
                                          
                                        Object[] PostalChrg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGAMT FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(nmb.getId()); 
                                        Double PostChrg  = Double.parseDouble(PostalChrg[0].toString());
                                        
                                        String AccountId = null;
                                        Object[] PostalAccount = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nmb.getId()); 
                                        if(PostalAccount!=null){
                                            AccountId = PostalAccount[0].toString();
                                        }
                                        
                                        if(AccountId!=null){
                                            
                                            
                                            
                                            Object[] CustAccountid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedMembers.getId()); 
                                            String CustAccId = CustAccountid[0].toString();
                                            
                                            
                                            Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, selectedMembers.getId(), new Date(), "D", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg, new Date(), false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, CustAccId ,PostChrg, null , null, true};
                                            dlfac.insertintoaccjoutnal2(cvalue);
                                            Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, new Date(), "C", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg , new Date() , true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, AccountId, PostChrg,null, null, true};
                                            dlfac.insertintoaccjoutnal2(dvalue);  
                                            
                                        }
                                          
                                        
                                          
                                      }
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     if(cnt!=num)
                                     {
                                       // JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                        throw new BasicException("Error in saving generated details to database.");
                                     }
                                    } catch (JRException ex) {
                                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                                        w.hideDialog();
                                        throw new BasicException(ex.getMessage());
                                    }
 
                                                    }
                                                     w.hideDialog();
                                
                                return null;
                            }
                        };
                               t.execute();                     
            
            
						}
						catch (Exception ex)
						{
                                                         w.hideDialog();
                                                        new MessageInf(ex).show(getParent());
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
				//	}
			//	}
			//);
               
		//thread.start();
              //  w.showDialog("Please wait.Loading Report...");
                
        
        } catch (Exception ex) {
             w.hideDialog();
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
           
        }
        
    }
    
    private void regenerateSecondNoticeToSelectedMemebers(List<ReportLine> selectedList1, List<NoticeTrackedBean> ntb) {
         try {
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
            final List<ReportLine> list = selectedList1;
            final List<NoticeTrackedBean> ntbList = ntb;
            
          //  Thread thread =
			//new Thread(
				//new Runnable()
				//{
				//	public void run()
				//	{ 
                    try {
                        Transaction t = new Transaction(m_App.getSession()) {

    @Override
    protected Object transact() throws BasicException {
        
         String dayOrMonth = null;
            int payment =0;
            int overdue = 0;
            if(!nmb.isDp_dm())
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Days";
                }
                else
                {
                    dayOrMonth = "Day";
                }
            }
            else
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Months";
                }
                else
                {
                    dayOrMonth = "Month";
                }
            }
            
            if(!nmb.isPayment_date_dm())
            {
                payment = nmb.getPayment_date_num();
            }
            else
            {
                payment = nmb.getPayment_date_num()*30;
            }
            if(nmb.isDue_pass())
         {
            if(nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         else
         {
             if(!nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
           
            String header2 = " " + nmb.getDp_num() +" "+ dayOrMonth +" ";
           
            Calendar cal=Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            Date duedate=new Date(toDate.getTime());
            Calendar cald=Calendar.getInstance();
            cald.setTimeInMillis(duedate.getTime());
            int i=cald.get(Calendar.DATE);
            
            duedate.setTime(cald.getTimeInMillis());
            toDate.setTime(cal.getTimeInMillis());
            cal.add(Calendar.DATE, -overdue);
            Date dueDateAsOnGeneratedDate = cal.getTime();
          
            //d1.setTime(cal.getTimeInMillis());
            Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(new Date());
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3 = cal2;
            cal2.add(Calendar.DATE, payment);
            DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, list, ntbList);
         //  DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForFirstNotice(m_App, dlfac, toDate, nmb, selectedList1);
           SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
           String dueDate = sdfFrom.format(cal.getTime());
         ////////////////////////////////////////
           try {
         Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CONTENT FROM RESOURCES where NAME='Window.ClubLogo'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.IMAGE})).find();
        if(obj!=null){
            if(obj[0]!=null){
               ig=(Image) obj[0]; 
               
            }
        }
           } catch (BasicException ex) {
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
        }
           ////////////////////////////////////////////////////////////////added by pratima
        
           Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
           reportparams.put("duedate", dueDate);
                reportparams.put("note", AdditionalNotes.getText());
                reportparams.put("header2", header2);
                reportparams.put("noticeName", nmb.getNoticeNameToDisplay());
                reportparams.put("generatedDate", sdfFrom.format(toDate));
                reportparams.put("paymentDate", sdfFrom.format(cal2.getTime()));
             //   reportparams.put("sign", nmb.getSign());
                reportparams.put("designation", nmb.getDesg());
                  reportparams.put("logo",ig);
                   if(nmb.isSignImgFlg()){
               reportparams.put("signImg",signImg);
               reportparams.put("sign", "");
           }else { 
               reportparams.put("sign", nmb.getSign());
               reportparams.put("signImg",null);
           }
                DataSourceProvider data1 = new DataSourceProvider(dlntm.getfacilityline1(), dlntm.getAllNoticeListForIndividualMembers());
                DataSourceForNoticeToMembersOtherThanFirst ds1 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm.getfacilityline1(), dlntm.getAllNoticeListForIndividualMembers());
                data1.setDataSource(ds1);
                final Map reportparams3 = reportparams;
                final DataSourceProvider dsp = data1;
                
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers_Second.jrxml", reportparams3, false, dsp, true, null);
                
                
                final Map reportparams2 = reportparams;
                final Date paymentdate = cal2.getTime();
                
                final Date dueDateAsOn = dueDateAsOnGeneratedDate;
                final int od =overdue;
                
                
                                                    List<ReportLine> individual ;
                                                    Date d = new Date();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                                                    String dateToFilename = sdf.format(d).toString();
                                                    NoticeTrackedBean noticeToUpdate = null;
                                                    for (Iterator<ReportLine> it = list.iterator(); it.hasNext();) {
                                    try {
                                        ReportLine selectedMembers = it.next();
                                        String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                        for (Iterator<NoticeTrackedBean> it1 = ntbList.iterator(); it1.hasNext();) {
                                            NoticeTrackedBean noticeTrackedBean = it1.next();
                                            if(noticeTrackedBean.getMemSearchKey().equals(selectedMembers.getSearchkey()))
                                            {
                                                noticeToUpdate = noticeTrackedBean;
                                                break;
                                            }
                                        }
                                       
                                        
                                        individual = new ArrayList<ReportLine>();
                                        individual.add(selectedMembers);
                                        DueListNoticeTableModel dlntm2 = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, individual, ntbList);
                                        DataSourceProvider data2 = new DataSourceProvider(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers());
                                        DataSourceForNoticeToMembersOtherThanFirst ds2 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers() );
                                        data2.setDataSource(ds2);
                                        
                                        JasperPrint jp1 = new JasperPrint();
                                        JasperDesign jasperDesign = JRXmlLoader.load("./reports/com/openbravo/reports/NoticeToMembers_Second.jrxml");
                                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                        JRDataSource data= data2.create(jasperReport);
                                        jp1 = JasperFillManager.fillReport(jasperReport, reportparams2, data);
                                        
                                        JRPdfExporter exporterPdf = new JRPdfExporter();
                                        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jp1);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filename );
                                        System.out.println("File name = " + filename);
                                        exporterPdf.exportReport();
                                        String universalId = UUID.randomUUID().toString();
                                       String notes = "Regenerated on :"+ new Date().toString();
                                        
                                     int num =    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO NOTICETRACKER(ID,NOTICEID,MEMID,GENERATEDDATE,DUEAMOUNT,PAYBYDATE,RECEIVEDREF,LINKTONOTICE, ACTIVE, DUEDATEONGENDATE, GENERATEDBY,DISPLAYNAME, SELECTEDDATE, LOCATIONOFCREATION, NOTES) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.BOOLEAN,Datas.TIMESTAMP, Datas.STRING,Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})
                                        ).exec(new Object[]{universalId, nmb.getId(), selectedMembers.getId(), noticeToUpdate.getGeneratedDate(), selectedMembers.getDueAndOverdue(),paymentdate, false, filename , true, dueDateAsOn, m_App.getAppUserView().getUser().getName(), nmb.getNoticeNameToDisplay(), toDate, m_App.getProperties().getHost(), notes });
                                     int cnt = new PreparedSentence(s, "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=?, LOCATIONOFDEACT =?, DEACTIVATEDREF =?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), universalId, noticeToUpdate.getId()});
                                      
                                     
  // code edited for postal charges
                                     
                                      String tid = UUID.randomUUID().toString();
                                      String narration = "Amount for Postal Charge of "+nmb.getName()+" Regenerated" ;
                                     
                                      
                                       boolean flag = false;
                                       Object[] PostalFlag = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGFLAG FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(nmb.getId());
                                       if(PostalFlag!=null){
                                           flag=Boolean.parseBoolean(PostalFlag[0].toString());  
                                       }
                                      
                                      
                                      if(flag){
                                          
                                        Object[] PostalChrg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGAMT FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(nmb.getId()); 
                                        Double PostChrg  = Double.parseDouble(PostalChrg[0].toString());
                                        
                                        String AccountId = null;
                                        Object[] PostalAccount = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nmb.getId()); 
                                        if(PostalAccount!=null){
                                            AccountId = PostalAccount[0].toString();
                                        }
                                        
                                        if(AccountId!=null){
                                            
                                            
                                            
                                            Object[] CustAccountid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedMembers.getId()); 
                                            String CustAccId = CustAccountid[0].toString();
                                            
                                            
                                            Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, selectedMembers.getId(), new Date(), "D", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg, new Date(), false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, CustAccId ,PostChrg, null , null, true};
                                            dlfac.insertintoaccjoutnal2(cvalue);
                                            Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, new Date(), "C", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg , new Date() , true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, AccountId, PostChrg,null, null, true};
                                            dlfac.insertintoaccjoutnal2(dvalue);  
                                            
                                        }
                                          
                                        
                                          
                                      }
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     if(cnt!=num)
                                     {
                                       // JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                        throw new BasicException("Error in saving generated details to database.");
                                     }
                                     
                                    
                                        
                                    } catch (JRException ex) {
                                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);w.hideDialog();
                                        throw new BasicException(ex.getMessage());
                                        
                                    }
                                     
                                        }
                                                 
        
         w.hideDialog();
        
        return null;
    }
};
        t.execute();
                    
                    } catch (BasicException ex) {
                         w.hideDialog();
                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                    }
					 
			//	}}
			//);
		//thread.start();
              //  w.showDialog("Please wait.Loading Report...");
                
        
        } catch (Exception ex) {
             w.hideDialog();
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
           
        }
    }
    
    private void regenerateFinalNoticeToSelectedMemebers(List<ReportLine> selectedList1, List<NoticeTrackedBean> ntb) {
         try {
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
            final List<ReportLine> list = selectedList1;
            final List<NoticeTrackedBean> ntbList = ntb;
            
          //  Thread thread =
			//new Thread(
				//new Runnable()
				//{
				//	public void run()
				//	{ 
                    try {
                        Transaction t = new Transaction(m_App.getSession()) {

    @Override
    protected Object transact() throws BasicException {
        
         String dayOrMonth = null;
            int payment =0;
            int overdue = 0;
            if(!nmb.isDp_dm())
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Days";
                }
                else
                {
                    dayOrMonth = "Day";
                }
            }
            else
            {
                if(nmb.getDp_num()>1)
                {
                    dayOrMonth = "Months";
                }
                else
                {
                    dayOrMonth = "Month";
                }
            }
            
            if(!nmb.isPayment_date_dm())
            {
                payment = nmb.getPayment_date_num();
            }
            else
            {
                payment = nmb.getPayment_date_num()*30;
            }
            if(nmb.isDue_pass())
         {
            if(nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
         else
         {
             if(!nmb.isDp_dm())
            {
                overdue = nmb.getDp_num();
            }
            else
            {
                overdue = nmb.getDp_num() * 30;
            }
         }
           
            String header2 = " " + nmb.getDp_num() +" "+ dayOrMonth +" ";
           
            Calendar cal=Calendar.getInstance();
            cal.setTime(toDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            Date duedate=new Date(toDate.getTime());
            Calendar cald=Calendar.getInstance();
            cald.setTimeInMillis(duedate.getTime());
            int i=cald.get(Calendar.DATE);
            
            duedate.setTime(cald.getTimeInMillis());
            toDate.setTime(cal.getTimeInMillis());
            cal.add(Calendar.DATE, -overdue);
            Date dueDateAsOnGeneratedDate = cal.getTime();
          
            //d1.setTime(cal.getTimeInMillis());
            Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(new Date());
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3 = cal2;
            cal2.add(Calendar.DATE, payment);
            DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, list, ntbList);
         //  DueListNoticeTableModel dlntm = DueListNoticeTableModel.loadInstanceByIndividualMemberForFirstNotice(m_App, dlfac, toDate, nmb, selectedList1);
           SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
           String dueDate = sdfFrom.format(cal.getTime());
        
           Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
           reportparams.put("duedate", dueDate);
                reportparams.put("note", AdditionalNotes.getText());
                reportparams.put("header2", header2);
                reportparams.put("noticeName", nmb.getNoticeNameToDisplay());
                reportparams.put("generatedDate", sdfFrom.format(toDate));
                reportparams.put("paymentDate", sdfFrom.format(cal2.getTime()));
               // reportparams.put("sign", nmb.getSign());
                reportparams.put("designation", nmb.getDesg());
                reportparams.put("logo", ig);
                 if(nmb.isSignImgFlg()){
               reportparams.put("signImg",signImg);
               reportparams.put("sign", "");
           }else { 
               reportparams.put("sign", nmb.getSign());
               reportparams.put("signImg",null);
           }
                DataSourceProvider data1 = new DataSourceProvider(dlntm.getfacilityline1(), dlntm.getAllNoticeListForIndividualMembers());
                DataSourceForNoticeToMembersOtherThanFirst ds1 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm.getfacilityline1(), dlntm.getAllNoticeListForIndividualMembers());
                data1.setDataSource(ds1);
                final Map reportparams3 = reportparams;
                final DataSourceProvider dsp = data1;
                
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/NoticeToMembers_Final.jrxml", reportparams3, false, dsp, true, null);
                
                
                final Map reportparams2 = reportparams;
                final Date paymentdate = cal2.getTime();
                
                final Date dueDateAsOn = dueDateAsOnGeneratedDate;
                final int od =overdue;
                
                
                                                    List<ReportLine> individual ;
                                                    Date d = new Date();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
                                                    String dateToFilename = sdf.format(d).toString();
                                                    NoticeTrackedBean noticeToUpdate = null;
                                                    for (Iterator<ReportLine> it = list.iterator(); it.hasNext();) {
                                    try {
                                        ReportLine selectedMembers = it.next();
                                        String filename = "./Notice/"+selectedMembers.getSearchkey()+"_"+ nmb.getName()+"_"+dateToFilename+".pdf";
                                        for (Iterator<NoticeTrackedBean> it1 = ntbList.iterator(); it1.hasNext();) {
                                            NoticeTrackedBean noticeTrackedBean = it1.next();
                                            if(noticeTrackedBean.getMemSearchKey().equals(selectedMembers.getSearchkey()))
                                            {
                                                noticeToUpdate = noticeTrackedBean;
                                                break;
                                            }
                                        }
                                       
                                        
                                        individual = new ArrayList<ReportLine>();
                                        individual.add(selectedMembers);
                                        DueListNoticeTableModel dlntm2 = DueListNoticeTableModel.loadInstanceByIndividualMemberForSecondNotice(m_App, dlfac, nmb, individual, ntbList);
                                        DataSourceProvider data2 = new DataSourceProvider(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers());
                                        DataSourceForNoticeToMembersOtherThanFirst ds2 = new DataSourceForNoticeToMembersOtherThanFirst(dlntm2.getfacilityline1(), dlntm2.getAllNoticeListForIndividualMembers() );
                                        data2.setDataSource(ds2);
                                        
                                        JasperPrint jp1 = new JasperPrint();
                                        JasperDesign jasperDesign = JRXmlLoader.load("./reports/com/openbravo/reports/NoticeToMembers_Final.jrxml");
                                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                                        JRDataSource data= data2.create(jasperReport);
                                        jp1 = JasperFillManager.fillReport(jasperReport, reportparams2, data);
                                        
                                        JRPdfExporter exporterPdf = new JRPdfExporter();
                                        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jp1);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                                        exporterPdf.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filename );
                                        System.out.println("File name = " + filename);
                                        exporterPdf.exportReport();
                                        String universalId = UUID.randomUUID().toString();
                                        String notes = "Regenerated on :"+ new Date().toString();
                                       
                                     int num =    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO NOTICETRACKER(ID,NOTICEID,MEMID,GENERATEDDATE,DUEAMOUNT,PAYBYDATE,RECEIVEDREF,LINKTONOTICE, ACTIVE, DUEDATEONGENDATE, GENERATEDBY,DISPLAYNAME, SELECTEDDATE, LOCATIONOFCREATION, NOTES) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.BOOLEAN,Datas.TIMESTAMP, Datas.STRING,Datas.STRING, Datas.TIMESTAMP,Datas.STRING,Datas.STRING})
                                        ).exec(new Object[]{universalId, nmb.getId(), selectedMembers.getId(), noticeToUpdate.getGeneratedDate(), selectedMembers.getDueAndOverdue(),paymentdate, false, filename , true, dueDateAsOn, m_App.getAppUserView().getUser().getName(), nmb.getNoticeNameToDisplay(), toDate, m_App.getProperties().getHost(), notes });
                                     
                                     int cnt = new PreparedSentence(s, "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=?, LOCATIONOFDEACT =?, DEACTIVATEDREF =?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), universalId, noticeToUpdate.getId()});
                                     
                                     
 // code edited for postal charges ..... 
                                     
                                      String tid = UUID.randomUUID().toString();
                                      String narration = "Amount for Postal Charge of "+nmb.getName()+" Regenerated" ;
                                     
                                      
                                       boolean flag = false;
                                       Object[] PostalFlag = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGFLAG FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(nmb.getId());
                                       if(PostalFlag!=null){
                                           flag=Boolean.parseBoolean(PostalFlag[0].toString());  
                                       }
                                      
                                      
                                      if(flag){
                                          
                                        Object[] PostalChrg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT POSTALCHRGAMT FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(nmb.getId()); 
                                        Double PostChrg  = Double.parseDouble(PostalChrg[0].toString());
                                        
                                        String AccountId = null;
                                        Object[] PostalAccount = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID FROM NOTICEMASTER WHERE ID=? AND ACTIVATE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nmb.getId()); 
                                        if(PostalAccount!=null){
                                            AccountId = PostalAccount[0].toString();
                                        }
                                        
                                        if(AccountId!=null){
                                            
                                            
                                            
                                            Object[] CustAccountid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedMembers.getId()); 
                                            String CustAccId = CustAccountid[0].toString();
                                            
                                            
                                            Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, selectedMembers.getId(), new Date(), "D", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg, new Date(), false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, CustAccId ,PostChrg, null , null, true};
                                            dlfac.insertintoaccjoutnal2(cvalue);
                                            Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, new Date(), "C", "Postal Charge", nmb.getNoticeNameToDisplay(), PostChrg , new Date() , true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, AccountId, PostChrg,null, null, true};
                                            dlfac.insertintoaccjoutnal2(dvalue);  
                                            
                                        }
                                          
                                        
                                          
                                      }
                                     
                                     
                                     
                                     
                                     
                                     
                                     if(cnt!=num)
                                     {
                                       // JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                         w.hideDialog();
                                        throw new BasicException("Error in saving generated details to database.");
                                     }
                                        w.hideDialog();
                                    } catch (JRException ex) {
                                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                                        w.hideDialog();
                                        throw new BasicException(ex.getMessage());
                                    }
                                     
                                        }
                                                 
        
        
        
        return null;
    }
};
        t.execute();} catch (BasicException ex) {
                        Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                    }
					   
			//	}}
			//);
		//thread.start();
              //  w.showDialog("Please wait.Loading Report...");
                
        
        } catch (Exception ex) {
             w.hideDialog();
            Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
           
        }
    }

    private void showDialog2(DueListNoticeTableModel dmodel) {
        
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setTitleAt(1, "DueList For Notice");
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setTitleAt(2, "DueList For Notice Generated");
        jTable3.setModel(dmodelForSecondNotice.getTableModelForSecondNotice());
        jTable3.getColumnModel().getColumn(10).setMinWidth(0);
        jTable3.getColumnModel().getColumn(10).setMaxWidth(56);
       
         TableColumn tc = jTable3.getColumnModel().getColumn(10);
          tc.setHeaderRenderer(null);
         tc.setCellEditor(jTable3.getDefaultEditor(Boolean.class));
         tc.setCellRenderer(jTable3.getDefaultRenderer(Boolean.class));
         tc.setHeaderRenderer(new CheckBoxHeaderForSecondNotice(jTable3.getTableHeader()));  
        jTable3.setPreferredScrollableViewportSize(jTable3.getPreferredSize());
        
            int columncnt=jTable3.getColumnModel().getColumnCount();
         if(columncnt>0){
              jTable3.getColumnModel().getColumn(0).setMaxWidth(100);
              jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
              jTable3.getColumnModel().getColumn(1).setMaxWidth(200);
              jTable3.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable3.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable3.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
               }else if((k-2)%3==1){
                jTable3.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable3.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
               } else if((k-2)%3==2){
                jTable3.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable3.getColumnModel().getColumn(k).setPreferredWidth(80);
              
                jTable3.setAutoResizeMode(jTable5.AUTO_RESIZE_OFF);
                jTable3.getColumnModel().getColumn(6).setMaxWidth(150);
                jTable3.getColumnModel().getColumn(6).setPreferredWidth(80);
                jTable3.getColumnModel().getColumn(8).setMaxWidth(80);
                jTable3.getColumnModel().getColumn(8).setPreferredWidth(60);
               }
              }
            }
        
         jTable4.setModel(dmodelForSecondNotice.getTableModelForSecondNoticeForAG());
         jTable4.getColumnModel().getColumn(10).setMinWidth(0);
        jTable4.getColumnModel().getColumn(10).setMaxWidth(56);
        TableColumn tc1 = jTable4.getColumnModel().getColumn(10);
         tc1.setHeaderRenderer(null);
         tc1.setCellEditor(jTable4.getDefaultEditor(Boolean.class));
         tc1.setCellRenderer(jTable4.getDefaultRenderer(Boolean.class));
         tc1.setHeaderRenderer(new CheckBoxHeaderForSecondNoticeAG(jTable4.getTableHeader()));  
        jTable4.setPreferredScrollableViewportSize(jTable4.getPreferredSize());
        int columncnt1=jTable4.getColumnModel().getColumnCount();
        if(columncnt>0){
              jTable4.getColumnModel().getColumn(0).setMaxWidth(100);
              jTable4.getColumnModel().getColumn(0).setPreferredWidth(50);
              jTable4.getColumnModel().getColumn(1).setMaxWidth(200);
              jTable4.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable4.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable4.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
               }else if((k-2)%3==1){
                jTable4.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable4.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
               } else if((k-2)%3==2){
                jTable4.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable4.getColumnModel().getColumn(k).setPreferredWidth(80);
              
                jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_OFF);
                jTable4.getColumnModel().getColumn(6).setMaxWidth(150);
                jTable4.getColumnModel().getColumn(6).setPreferredWidth(80);
                jTable4.getColumnModel().getColumn(8).setMaxWidth(80);
                jTable4.getColumnModel().getColumn(8).setPreferredWidth(60);
               }
              }
            }
        
    }

    private void showDialogForFinal(DueListNoticeTableModel dmodelForFinalNotice) {
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setTitleAt(1, "DueList For Notice");
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setTitleAt(2, "DueList For Notice Generated");
        jTable5.setModel(dmodelForFinalNotice.getTableModelForSecondNotice());
        jTable5.getColumnModel().getColumn(10).setMinWidth(0);
        jTable5.getColumnModel().getColumn(10).setMaxWidth(56);
       
         TableColumn tc = jTable5.getColumnModel().getColumn(10);
          tc.setHeaderRenderer(null);
         tc.setCellEditor(jTable5.getDefaultEditor(Boolean.class));
         tc.setCellRenderer(jTable5.getDefaultRenderer(Boolean.class));
         tc.setHeaderRenderer(new CheckBoxHeaderForFinalNotice(jTable5.getTableHeader()));  
        jTable5.setPreferredScrollableViewportSize(jTable5.getPreferredSize());
        
            int columncnt=jTable5.getColumnModel().getColumnCount();
         if(columncnt>0){
              jTable5.getColumnModel().getColumn(0).setMaxWidth(100);
              jTable5.getColumnModel().getColumn(0).setPreferredWidth(50);
              jTable5.getColumnModel().getColumn(1).setMaxWidth(200);
              jTable5.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable5.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable5.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
               }else if((k-2)%3==1){
                jTable5.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable5.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
               } else if((k-2)%3==2){
                jTable5.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable5.getColumnModel().getColumn(k).setPreferredWidth(80);
              
                jTable5.setAutoResizeMode(jTable5.AUTO_RESIZE_OFF);
                jTable5.getColumnModel().getColumn(6).setMaxWidth(150);
                jTable5.getColumnModel().getColumn(6).setPreferredWidth(80);
                jTable5.getColumnModel().getColumn(8).setMaxWidth(80);
                jTable5.getColumnModel().getColumn(8).setPreferredWidth(60);
               }
              }
            }
        
         jTable6.setModel(dmodelForFinalNotice.getTableModelForSecondNoticeForAG());
         jTable6.getColumnModel().getColumn(10).setMinWidth(0);
        jTable6.getColumnModel().getColumn(10).setMaxWidth(56);
        TableColumn tc1 = jTable6.getColumnModel().getColumn(10);
         tc1.setHeaderRenderer(null);
         tc1.setCellEditor(jTable6.getDefaultEditor(Boolean.class));
         tc1.setCellRenderer(jTable6.getDefaultRenderer(Boolean.class));
         tc1.setHeaderRenderer(new CheckBoxHeaderForFinalNoticeAG(jTable6.getTableHeader()));  
        jTable6.setPreferredScrollableViewportSize(jTable6.getPreferredSize());
        int columncnt1=jTable6.getColumnModel().getColumnCount();
        if(columncnt>0){
              jTable6.getColumnModel().getColumn(0).setMaxWidth(100);
              jTable6.getColumnModel().getColumn(0).setPreferredWidth(50);
              jTable6.getColumnModel().getColumn(1).setMaxWidth(200);
              jTable6.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable6.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable6.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
               }else if((k-2)%3==1){
                jTable6.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable6.getColumnModel().getColumn(k).setPreferredWidth(80);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
               } else if((k-2)%3==2){
                jTable6.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable6.getColumnModel().getColumn(k).setPreferredWidth(80);
              
                jTable6.setAutoResizeMode(jTable6.AUTO_RESIZE_OFF);
                jTable6.getColumnModel().getColumn(6).setMaxWidth(150);
                jTable6.getColumnModel().getColumn(6).setPreferredWidth(80);
                jTable6.getColumnModel().getColumn(8).setMaxWidth(80);
                jTable6.getColumnModel().getColumn(8).setPreferredWidth(60);
               }
              }
            }
        
    }

    private void buttonGoup() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(dNo);
        bg1.add(dYes);
        
    }

    private boolean deactivatemembers(List<ReportLine> selectedList, NoticeMasterBean nmb) throws BasicException {
        
        String notes = "Deactivated when generating " + nmb.getName() + ". Deactivated By " + m_App.getAppUserView().getUser().getName();
        
        for (Iterator<ReportLine> it = selectedList.iterator(); it.hasNext();) {
            try {
                ReportLine reportLine = it.next();
                final ReportLine selectedMem = reportLine;
                final String notes1 = notes;
                Object value =  new StaticSentence(m_App.getSession(), "SELECT VISIBLE FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(reportLine.getSearchkey());
                
                String active = value.toString();
                if(active.equals("1"))
                {
                    final String noticeid = nmb.getId();
                    Transaction t = new Transaction(m_App.getSession()) {
                        @Override
                        protected Object transact() throws BasicException {
                            String memidDD = (String) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(selectedMem.getSearchkey());
                            
                            int num = new PreparedSentence(m_App.getSession(), "UPDATE CUSTOMERS SET VISIBLE = FALSE, NOTES = '"+notes1+"' WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{memidDD});
                          
                          int num2 =  new PreparedSentence(m_App.getSession()
                            , "INSERT INTO ntmemdeactvationlist(ID,NOTICEID,MEMID,GENERATEDDATE,NOTES,ACTIVE,DEACTIVATELOCATION, DEACTIVATEDBY) VALUES (?,?,?,?,?,?,?,?)"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.BOOLEAN,Datas.STRING,Datas.STRING})
                            ).exec(new Object[]{UUID.randomUUID().toString(), noticeid, memidDD, new Date(), notes1, true, m_App.getProperties().getHost(), m_App.getAppUserView().getUser().getName()});
                            return null;
                        }
                    };
                    t.execute();
                }
            } catch (BasicException ex) {
                Logger.getLogger(Notice.class.getName()).log(Level.SEVERE, null, ex);
                throw new BasicException("Transaction Error!!");
            }
        }
        return true;
    }

    
    class CheckBoxHeader2 implements TableCellRenderer{  
    protected  JCheckBox rendererComponent2 =  new JCheckBox("All", false) ;  
    protected int column;  
    
    
    public CheckBoxHeader2(JTableHeader header) {  
        rendererComponent2.setOpaque(true);
        rendererComponent2.setFont(header.getFont());
        rendererComponent2.setHorizontalAlignment(SwingConstants.CENTER);
        //cbh = this;
 
        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
           
             TableColumnModel columnModel = jTable2.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable2.convertColumnIndexToModel(viewColumn);
           if(modelColumn==6)
           {
                if(rendererComponent2.isSelected())
            {
            rendererComponent2.setSelected(false);
            int i2 = dmodelForFirstNotice.getReportListForAlreadyGenerated().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable2.setValueAt(false, i, 6); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent2.setSelected(true);
            int i2 = dmodelForFirstNotice.getReportListForAlreadyGenerated().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForFirstNotice.getReportListForAlreadyGenerated().get(i);
                 
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable2.setValueAt(true, i, 6);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(null, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        
                        
                        int i3 = dmodelForFirstNotice.getReportListForAlreadyGenerated().size();
                    for (int j = 0; j < i3; j++) {
                        jTable2.setValueAt(true, j, 6);
                    }
                    break;
                    }
                    else
                    {
                        
                        jTable2.setEditingRow(i);
                        break;
                    }
                }
                
            }
            }
            jTable2.repaint();
           }
  
        }});
    }
 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
        setColumn(column); 
            return rendererComponent2;
    } 
    protected void setColumn(int column) {  
        this.column = column;  
    }  
    public int getColumn() {  
        return column;  
    }

    } 
    
    class CheckBoxHeader implements TableCellRenderer{  
    protected  JCheckBox rendererComponent =  new JCheckBox("All", false) ;  
    protected int column;  
    
    
    public CheckBoxHeader(JTableHeader header) {  
        rendererComponent.setOpaque(true);
        rendererComponent.setFont(header.getFont());
        rendererComponent.setHorizontalAlignment(SwingConstants.CENTER);
        //cbh = this;
 
        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
           
             TableColumnModel columnModel = jTable1.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable1.convertColumnIndexToModel(viewColumn);
           if(modelColumn==6)
           {
                if(rendererComponent.isSelected())
            {
            rendererComponent.setSelected(false);
            int i2 = dmodelForFirstNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable1.setValueAt(false, i, 6); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent.setSelected(true);
            int i2 = dmodelForFirstNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForFirstNotice.getReportList1().get(i);
               
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable1.setValueAt(true, i, 6);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(null, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                      
                        int i3 = dmodelForFirstNotice.getReportList1().size();
                    for (int j = 0; j < i3; j++) {
                        jTable1.setValueAt(true, j, 6);
                    }
                    break;
                    }
                    else
                    {
                        
                        jTable1.setEditingRow(i);
                        break;
                    }
                }
            }
            }
            jTable1.repaint();
           }
  
        }});
    }
 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
        setColumn(column);
            return rendererComponent;
    } 
    protected void setColumn(int column) {  
        this.column = column;  
    }  
    public int getColumn() {  
        return column;  
    }

    
    } 
    
   class CheckBoxHeaderForSecondNotice implements TableCellRenderer{  
    protected  JCheckBox rendererComponent2 =  new JCheckBox("All", false);
    
    protected int column;  
    
    public CheckBoxHeaderForSecondNotice(JTableHeader header) {  
        
        rendererComponent2.setOpaque(true);
        rendererComponent2.setFont(header.getFont());
        rendererComponent2.setHorizontalAlignment(SwingConstants.CENTER);

     TableColumnModel cm = jTable3.getColumnModel();
     ColumnGroup g_name = new ColumnGroup("Previous notice details");
     g_name.add(cm.getColumn(2));
     g_name.add(cm.getColumn(3));
     g_name.add(cm.getColumn(4));
          g_name.add(cm.getColumn(5));
          
          ColumnGroup g_other = new ColumnGroup("Current Position of Dues");
          g_other.add(cm.getColumn(6));
           g_other.add(cm.getColumn(7));
            g_other.add(cm.getColumn(8));
             g_other.add(cm.getColumn(9));
             
             GroupableTableHeader header1 = (GroupableTableHeader)jTable3.getTableHeader();
             header1.addColumnGroup(g_name);
             header1.addColumnGroup(g_other);

        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(e.getClickCount()==1)
            {
           
             TableColumnModel columnModel = jTable3.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable3.convertColumnIndexToModel(viewColumn);
           if(modelColumn==10)
           {
                if(rendererComponent2.isSelected())
            {
            rendererComponent2.setSelected(false);
            int i2 = dmodelForSecondNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable3.setValueAt(false, i, 10); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent2.setSelected(true);
            int i2 = dmodelForSecondNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForSecondNotice.getReportList1().get(i);
               
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable3.setValueAt(true, i, 10);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(null, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                      
                        int i3 = dmodelForSecondNotice.getReportList1().size();
                    for (int j = 0; j < i3; j++) {
                        jTable3.setValueAt(true, j, 10);
                    }
                    break;
                    }
                    else
                    {
                      jTable3.setEditingRow(i);
                      break;
                    }
                }
            }
            }
            jTable3.repaint();
           }
  
            }}});
        
   /*     rendererComponent2.setOpaque(true);
        rendererComponent2.setFont(header.getFont());
        rendererComponent2.setHorizontalAlignment(SwingConstants.CENTER);
     
        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(e.getClickCount()==1)
            {
           
             TableColumnModel columnModel = jTable1.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable1.convertColumnIndexToModel(viewColumn);
           if(modelColumn==8)
           {
                if(rendererComponent2.isSelected())
            {
            rendererComponent2.setSelected(false);
            int i2 = dmodelForSecondNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable1.setValueAt(false, i, 8); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent2.setSelected(true);
            int i2 = dmodelForSecondNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForSecondNotice.getReportList1().get(i);
               
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable1.setValueAt(true, i, 8);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(jPanel5, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                      
                        int i3 = dmodelForSecondNotice.getReportList1().size();
                    for (int j = 0; j < i3; j++) {
                        jTable1.setValueAt(true, j, 8);
                    }
                    break;
                    }
                    else
                    {
                      jTable1.setEditingRow(i);
                      break;
                    }
                }
            }
            }
            jTable1.repaint();
           }
  
            }}});*/
    }
    
    
   
    protected void setColumn(int column) {  
        this.column = column;  
    }  
    public int getColumn() {  
        return column;  
    }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            
            return rendererComponent2;
        }
    }  
   
   class CheckBoxHeaderForFinalNotice implements TableCellRenderer{  
    protected  JCheckBox rendererComponent2 =  new JCheckBox("All", false);
    
    protected int column;  
    
    
    public CheckBoxHeaderForFinalNotice(JTableHeader header) {  
        rendererComponent2.setOpaque(true);
        rendererComponent2.setFont(header.getFont());
        rendererComponent2.setHorizontalAlignment(SwingConstants.CENTER);
     
        TableColumnModel cm = jTable5.getColumnModel();
     ColumnGroup g_name = new ColumnGroup("Previous notice details");
     g_name.add(cm.getColumn(2));
     g_name.add(cm.getColumn(3));
     g_name.add(cm.getColumn(4));
          g_name.add(cm.getColumn(5));
          
          ColumnGroup g_other = new ColumnGroup("Current Position of Dues");
          g_other.add(cm.getColumn(6));
           g_other.add(cm.getColumn(7));
            g_other.add(cm.getColumn(8));
             g_other.add(cm.getColumn(9));
             
             GroupableTableHeader header1 = (GroupableTableHeader)jTable5.getTableHeader();
             header1.addColumnGroup(g_name);
             header1.addColumnGroup(g_other);

        
        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(e.getClickCount()==1)
            {
           
             TableColumnModel columnModel = jTable5.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable5.convertColumnIndexToModel(viewColumn);
           if(modelColumn==10)
           {
                if(rendererComponent2.isSelected())
            {
            rendererComponent2.setSelected(false);
            int i2 = dmodelForFinalNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable5.setValueAt(false, i, 10); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent2.setSelected(true);
            int i2 = dmodelForFinalNotice.getReportList1().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForFinalNotice.getReportList1().get(i);
               
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable5.setValueAt(true, i, 10);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(null, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                      
                        int i3 = dmodelForFinalNotice.getReportList1().size();
                    for (int j = 0; j < i3; j++) {
                        jTable5.setValueAt(true, j, 10);
                    }
                    break;
                    }
                    else
                    {
                      jTable5.setEditingRow(i);
                      break;
                    }
                }
            }
            }
            jTable5.repaint();
           }
  
            }}});
    }
    
   
    protected void setColumn(int column) {  
        this.column = column;  
    }  
    public int getColumn() {  
        return column;  
    }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return rendererComponent2;
        }
    }  
    
   
   class CheckBoxHeaderForSecondNoticeAG implements TableCellRenderer{  
    protected  JCheckBox rendererComponent2 =  new JCheckBox("All", false);
    
    protected int column;  
    
    
    public CheckBoxHeaderForSecondNoticeAG(JTableHeader header) {  
        rendererComponent2.setOpaque(true);
        rendererComponent2.setFont(header.getFont());
        rendererComponent2.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel cm = jTable4.getColumnModel();
     ColumnGroup g_name = new ColumnGroup("Previous notice details");
     g_name.add(cm.getColumn(2));
     g_name.add(cm.getColumn(3));
     g_name.add(cm.getColumn(4));
          g_name.add(cm.getColumn(5));
          
          ColumnGroup g_other = new ColumnGroup("Current Position of Dues");
          g_other.add(cm.getColumn(6));
           g_other.add(cm.getColumn(7));
            g_other.add(cm.getColumn(8));
             g_other.add(cm.getColumn(9));
             
             GroupableTableHeader header1 = (GroupableTableHeader)jTable4.getTableHeader();
             header1.addColumnGroup(g_name);
             header1.addColumnGroup(g_other);
     
        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(e.getClickCount()==1)
            {
           
             TableColumnModel columnModel = jTable4.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable4.convertColumnIndexToModel(viewColumn);
           if(modelColumn==10)
           {
                if(rendererComponent2.isSelected())
            {
            rendererComponent2.setSelected(false);
            int i2 = dmodelForSecondNotice.getReportListForAlreadyGenerated().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable4.setValueAt(false, i, 10); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent2.setSelected(true);
            int i2 = dmodelForSecondNotice.getReportListForAlreadyGenerated().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForSecondNotice.getReportListForAlreadyGenerated().get(i);
               
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable4.setValueAt(true, i, 10);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(null, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                      
                        int i3 = dmodelForSecondNotice.getReportListForAlreadyGenerated().size();
                    for (int j = 0; j < i3; j++) {
                        jTable4.setValueAt(true, j, 10);
                    }
                    break;
                    }
                    else
                    {
                      jTable4.setEditingRow(i);
                      break;
                    }
                }
            }
            }
            jTable4.repaint();
           }
  
            }}});
    }
    
   
    protected void setColumn(int column) {  
        this.column = column;  
    }  
    public int getColumn() {  
        return column;  
    }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return rendererComponent2;
        }
    }  
   
    class CheckBoxHeaderForFinalNoticeAG implements TableCellRenderer{  
    protected  JCheckBox rendererComponent2 =  new JCheckBox("All", false);
    
    protected int column;  
    
    
    public CheckBoxHeaderForFinalNoticeAG(JTableHeader header) {  
        rendererComponent2.setOpaque(true);
        rendererComponent2.setFont(header.getFont());
        rendererComponent2.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel cm = jTable6.getColumnModel();
     ColumnGroup g_name = new ColumnGroup("Previous notice details");
     g_name.add(cm.getColumn(2));
     g_name.add(cm.getColumn(3));
     g_name.add(cm.getColumn(4));
          g_name.add(cm.getColumn(5));
          
          ColumnGroup g_other = new ColumnGroup("Current Position of Dues");
          g_other.add(cm.getColumn(6));
           g_other.add(cm.getColumn(7));
            g_other.add(cm.getColumn(8));
             g_other.add(cm.getColumn(9));
             
             GroupableTableHeader header1 = (GroupableTableHeader)jTable6.getTableHeader();
             header1.addColumnGroup(g_name);
             header1.addColumnGroup(g_other);

     
        header.addMouseListener(new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(e.getClickCount()==1)
            {
           
             TableColumnModel columnModel = jTable6.getColumnModel();
             int viewColumn = columnModel.getColumnIndexAtX(e.getX());
             int modelColumn = jTable6.convertColumnIndexToModel(viewColumn);
           if(modelColumn==10)
           {
                if(rendererComponent2.isSelected())
            {
            rendererComponent2.setSelected(false);
            int i2 = dmodelForFinalNotice.getReportListForAlreadyGenerated().size();
            for(int i =0; i<i2; i++)
            {
               // dmodel.getTableModel().setValueAt(true, i, 6);
            jTable6.setValueAt(false, i, 10); 
            //dmodel.getReportList1().get(i).setAll(false);
            }}
            else
            {
            rendererComponent2.setSelected(true);
            int i2 = dmodelForFinalNotice.getReportListForAlreadyGenerated().size();
            for(int i =0; i<i2; i++)
            {
                DueListNoticeTableModel.ReportLine rl = dmodelForFinalNotice.getReportListForAlreadyGenerated().get(i);
               
                if(!(rl.getCreditAmt()>=rl.getDueAndOverdue()))
                {
                    jTable6.setValueAt(true, i, 10);
                }
                else
                {
                    if(JOptionPane.showConfirmDialog(null, "Credit available is more than due amount for many members. Do you want to select ALL members??", "Cr.Available is more than Due Amount", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                      
                        int i3 = dmodelForFinalNotice.getReportListForAlreadyGenerated().size();
                    for (int j = 0; j < i3; j++) {
                        jTable6.setValueAt(true, j, 10);
                    }
                    break;
                    }
                    else
                    {
                      jTable6.setEditingRow(i);
                      break;
                    }
                }
            }
            }
            jTable6.repaint();
           }
  
            }}});
    }
    
   
    protected void setColumn(int column) {  
        this.column = column;  
    }  
    public int getColumn() {  
        return column;  
    }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return rendererComponent2;
        }
    }  
    
    
    class FixedSizeDocument extends PlainDocument{
private int max = 10;
public FixedSizeDocument(int max)
{
this.max = max;
charCount.setText(""+(max-getLength()));
}

@Override
public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
{
if(!str.equals("\n"))
{
if (getLength()+str.length()>max)
{
// If it does, then truncate it
str = str.substring(0, max - getLength());
}

super.insertString(offs, str, a);
charCount.setText(""+(max-getLength()));

}}

@Override
protected void removeUpdate(DefaultDocumentEvent chng) {
charCount.setText(""+(max-getLength()+1));
}

}
    
    class FixedSizeDocument2 extends PlainDocument{
private int max = 10;
public FixedSizeDocument2(int max)
{
this.max = max;
charCount1.setText(""+(max-getLength()));
}

@Override
public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
{
if(!str.equals("\n"))
{
if (getLength()+str.length()>max)
{
// If it does, then truncate it
str = str.substring(0, max - getLength());
}

super.insertString(offs, str, a);
charCount1.setText(""+(max-getLength()));

}}

@Override
protected void removeUpdate(DefaultDocumentEvent chng) {
charCount1.setText(""+(max-getLength()+1));
}

}
}

class GroupableTableHeader extends JTableHeader {
  private static final String uiClassID = "GroupableTableHeaderUI";
  protected Vector columnGroups = null;
    
  public GroupableTableHeader(TableColumnModel model) {
    super(model);
    setUI(new GroupableTableHeaderUI());
    setReorderingAllowed(false);
  }
    @Override
  public void updateUI(){
   setUI(new GroupableTableHeaderUI());
  }
  
    @Override
  public void setReorderingAllowed(boolean b) {
    reorderingAllowed = false;
  }
    
  public void addColumnGroup(ColumnGroup g) {
    if (columnGroups == null) {
      columnGroups = new Vector();
    }
    columnGroups.addElement(g);
  }

  public Enumeration getColumnGroups(TableColumn col) {
    if (columnGroups == null) return null;
    Enumeration e = columnGroups.elements();
    while (e.hasMoreElements()) {
      ColumnGroup cGroup = (ColumnGroup)e.nextElement();
      Vector v_ret = (Vector)cGroup.getColumnGroups(col,new Vector());
      if (v_ret != null) { 
  return v_ret.elements();
      }
    }
    return null;
  }
  
  public void setColumnMargin() {
    if (columnGroups == null) return;
    int columnMargin = getColumnModel().getColumnMargin();
    Enumeration e = columnGroups.elements();
    while (e.hasMoreElements()) {
      ColumnGroup cGroup = (ColumnGroup)e.nextElement();
      cGroup.setColumnMargin(columnMargin);
    }
  }
  
}

/*
 * (swing1.1beta3)
 * 
 */




class GroupableTableHeaderUI extends BasicTableHeaderUI {
  
  public void paint(Graphics g, JComponent c) {
    Rectangle clipBounds = g.getClipBounds();
    if (header.getColumnModel() == null) return;
    ((GroupableTableHeader)header).setColumnMargin();
    int column = 0;
    Dimension size = header.getSize();
    Rectangle cellRect  = new Rectangle(0, 0, size.width, size.height);
    Hashtable h = new Hashtable();
    int columnMargin = header.getColumnModel().getColumnMargin();
    
    Enumeration enumeration = header.getColumnModel().getColumns();
    while (enumeration.hasMoreElements()) {
      cellRect.height = size.height;
      cellRect.y      = 0;
      TableColumn aColumn = (TableColumn)enumeration.nextElement();
      Enumeration cGroups = ((GroupableTableHeader)header).getColumnGroups(aColumn);
      if (cGroups != null) {
        int groupHeight = 0;
        while (cGroups.hasMoreElements()) {
          ColumnGroup cGroup = (ColumnGroup)cGroups.nextElement();
          Rectangle groupRect = (Rectangle)h.get(cGroup);
          if (groupRect == null) {
            groupRect = new Rectangle(cellRect);
            Dimension d = cGroup.getSize(header.getTable());
            groupRect.width  = d.width;
            groupRect.height = d.height;    
            h.put(cGroup, groupRect);
          }
          paintCell(g, groupRect, cGroup);
          groupHeight += groupRect.height;
          cellRect.height = size.height - groupHeight;
          cellRect.y      = groupHeight;
        }
      }      
      cellRect.width = aColumn.getWidth() + columnMargin;
      if (cellRect.intersects(clipBounds)) {
        paintCell(g, cellRect, column);
      }
      cellRect.x += cellRect.width;
      column++;
    }
  }

  private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
    TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
    TableCellRenderer renderer = aColumn.getHeaderRenderer();
    //revised by Java2s.com
    renderer = new DefaultTableCellRenderer(){
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          JLabel header = new JLabel();
            header.setForeground(table.getTableHeader().getForeground());
            header.setBackground(table.getTableHeader().getBackground());
            header.setFont(table.getTableHeader().getFont());

          header.setHorizontalAlignment(JLabel.CENTER);
          if(value!=null)
          {
          header.setText(value.toString());
          }
          
          
          header.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return header;
        }
    
    };
    Component c = renderer.getTableCellRendererComponent(
        header.getTable(), aColumn.getHeaderValue(),false, false, -1, columnIndex);
        
        c.setBackground(UIManager.getColor("control"));
      
    rendererPane.add(c);
    rendererPane.paintComponent(g, c, header, cellRect.x, cellRect.y,
        cellRect.width, cellRect.height, true);
  }

  private void paintCell(Graphics g, Rectangle cellRect,ColumnGroup cGroup) {
    TableCellRenderer renderer = cGroup.getHeaderRenderer();
      //revised by Java2s.com
     // if(renderer == null){
//      return ;
  //    }

    Component component = renderer.getTableCellRendererComponent(
      header.getTable(), cGroup.getHeaderValue(),false, false, -1, -1);
    rendererPane.add(component);
    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
        cellRect.width, cellRect.height, true);
  }

  private int getHeaderHeight() {
    int height = 0;
    TableColumnModel columnModel = header.getColumnModel();
    for(int column = 0; column < columnModel.getColumnCount(); column++) {
      TableColumn aColumn = columnModel.getColumn(column);
      TableCellRenderer renderer = aColumn.getHeaderRenderer();
      //revised by Java2s.com
      if(renderer == null){
      return 60;
      }
      
      Component comp = renderer.getTableCellRendererComponent(
        header.getTable(), aColumn.getHeaderValue(), false, false,-1, column);
      int cHeight = comp.getPreferredSize().height;
      Enumeration e = ((GroupableTableHeader)header).getColumnGroups(aColumn);      
      if (e != null) {
        while (e.hasMoreElements()) {
          ColumnGroup cGroup = (ColumnGroup)e.nextElement();
          cHeight += cGroup.getSize(header.getTable()).height;
        }
      }
      height = Math.max(height, cHeight);
    }
    return height;
  }

  private Dimension createHeaderSize(long width) {
    TableColumnModel columnModel = header.getColumnModel();
    width += columnModel.getColumnMargin() * columnModel.getColumnCount();
    if (width > Integer.MAX_VALUE) {
      width = Integer.MAX_VALUE;
    }
    return new Dimension((int)width, getHeaderHeight());
  }

  public Dimension getPreferredSize(JComponent c) {
    long width = 0;
    Enumeration enumeration = header.getColumnModel().getColumns();
    while (enumeration.hasMoreElements()) {
      TableColumn aColumn = (TableColumn)enumeration.nextElement();
      width = width + aColumn.getPreferredWidth();
    }
    return createHeaderSize(width);
  }
}

class ColumnGroup {
  protected TableCellRenderer renderer;
  protected Vector v;
  protected String text;
  protected int margin=0;

  public ColumnGroup(String text) {
    this(null,text);
  }

  public ColumnGroup(TableCellRenderer renderer,String text) {
    if (renderer == null) {
      this.renderer = new DefaultTableCellRenderer() {
  public Component getTableCellRendererComponent(JTable table, Object value,
                         boolean isSelected, boolean hasFocus, int row, int column) {
    JTableHeader header = table.getTableHeader();
    if (header != null) {
      setForeground(header.getForeground());
      setBackground(header.getBackground());
      setFont(header.getFont());
    }
          setHorizontalAlignment(JLabel.CENTER);
          setText((value == null) ? "" : value.toString());
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    return this;
        }
      };
    } else {
      this.renderer = renderer;
    }
    this.text = text;
    v = new Vector();
  }

  
  /**
   * @param obj    TableColumn or ColumnGroup
   */
  public void add(Object obj) {
    if (obj == null) { return; }
    v.addElement(obj);
  }

  
  /**
   * @param c    TableColumn
   * @param v    ColumnGroups
   */
  public Vector getColumnGroups(TableColumn c, Vector g) {
    g.addElement(this);
    if (v.contains(c)) return g;    
    Enumeration e = v.elements();
    while (e.hasMoreElements()) {
      Object obj = e.nextElement();
      if (obj instanceof ColumnGroup) {
        Vector groups = 
          (Vector)((ColumnGroup)obj).getColumnGroups(c,(Vector)g.clone());
        if (groups != null) return groups;
      }
    }
    return null;
  }
    
  public TableCellRenderer getHeaderRenderer() {
    return renderer;
  }
    
  public void setHeaderRenderer(TableCellRenderer renderer) {
    if (renderer != null) {
      this.renderer = renderer;
    }
  }
    
  public Object getHeaderValue() {
    return text;
  }
  
  public Dimension getSize(JTable table) {
    Component comp = renderer.getTableCellRendererComponent(
        table, getHeaderValue(), false, false,-1, -1);
    int height = comp.getPreferredSize().height; 
    int width  = 0;
    Enumeration e = v.elements();
    while (e.hasMoreElements()) {
      Object obj = e.nextElement();
      if (obj instanceof TableColumn) {
        TableColumn aColumn = (TableColumn)obj;
        width += aColumn.getWidth();
        width += margin;
      } else {
        width += ((ColumnGroup)obj).getSize(table).width;
      }
    }
    return new Dimension(width, height);
  }

  public void setColumnMargin(int margin) {
    this.margin = margin;
    Enumeration e = v.elements();
    while (e.hasMoreElements()) {
      Object obj = e.nextElement();
      if (obj instanceof ColumnGroup) {
        ((ColumnGroup)obj).setColumnMargin(margin);
      }
    }
  }
  
  
  
 
  
}