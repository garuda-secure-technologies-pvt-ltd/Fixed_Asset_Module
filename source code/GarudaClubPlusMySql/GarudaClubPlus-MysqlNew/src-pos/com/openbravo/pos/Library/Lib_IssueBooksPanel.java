/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Library.Lib_IssueTableModel.lib_Issueline;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.CardSwipeNotifier;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.DataLogicReceiptsstd1;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import cos.card.acs.Cosacs;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author dev1
 */
public class Lib_IssueBooksPanel extends javax.swing.JPanel implements JPanelView,BeanFactoryApp,CardSwipeNotifier{

    protected DataLogicCustomers dlCustomers;
    protected DataLogicReceiptsstd1 dlReceipts;
    protected DataLogicSales dlSales;
    public CustomerInfoExt customer;
    private AppView m_App;
    private String initiator;
    private CardReader cr;
     private boolean flag = true;
    private Lib_IssueTableModel imodel;
     List<Object> book_id = new ArrayList<Object>();
    List<Object> memid = new ArrayList<Object>();
    List<Object> cmemtype =new ArrayList<Object>();
    private List<Object> allbook_id;
    private List<Object> loadbook= new ArrayList<Object>();
    private Object[] obj1;
    private List<Object> selectbook;
    private List<Object> retnid;
    private Object[] retnobj;
    public double fine;
    private CustomerInfo customerInfo;
    private DataLogicFacilities dmang;
    private TicketParser ttp;
    private DataLogicSystem dlsystem;
    public List<Object> book_name;
    private List<Object> nor_copies;
    private String retn_id;
    private int rw;
    private List<Object> allmemtype;
    private String[] arry;
    Object[] member;
    Object[] spodep;
    private List<Object> memb;
    private Integer memberint;
    private int spouse;
    private Integer memberint1;
    private int spouse1;
    private int dept;
    private List<Object> membobj;
    private List<Object> catgobj;
    private List<Object> issuememtype;
    private String memidstr;
    private Object[] allmemissuerls;
    private String max_books;
    private String issue_flag;
    private String catg_books;
    private String[] catary;
    private String[] bookary;
    private List<Object> bookcatid;
    private int norofbooks;
    private List<Object> bookcategory;
    private int bookscount;
    private List<Object> allissue_id;
    private int issue_flag_count;
    public boolean editable;
    private int memflag;
    private String deptype;
    private int memtypeflag;
    //public static List<String> Falcility_id=new ArrayList<String>();;
    //private AllFacilityListModel afmodel;
    /** Creates new form Lib_IssueBooksPanel */
    public Lib_IssueBooksPanel() {
        initComponents();
        jTextField3.setText(null);
        jTextField2.setVisible(false);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        issuedview = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        bar_ld = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        issue_rb = new javax.swing.JRadioButton();
        return_rb = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        bar_ld1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();

        issuedview.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                issuedviewStateChanged(evt);
            }
        });

        jLabel3.setText("Name:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel4.setText("Member No:");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Card No:");

        jTextField5.setEditable(false);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton2.setToolTipText("Member list");
        jButton2.setMargin(new java.awt.Insets(8, 14, 8, 14));
        jButton2.setPreferredSize(new java.awt.Dimension(48, 36));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton10.setToolTipText("Card refresh");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel1.setText("Book Issue/Return Details:");

        jButton1.setText("LoadDetails");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("IssueBook");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("BarCode No:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        bar_ld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        bar_ld.setToolTipText("Barcode refresh");
        bar_ld.setFocusPainted(false);
        bar_ld.setFocusable(false);
        bar_ld.setMargin(new java.awt.Insets(8, 14, 8, 14));
        bar_ld.setRequestFocusEnabled(false);
        bar_ld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bar_ldActionPerformed(evt);
            }
        });

        jButton5.setText("loadbook");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        buttonGroup1.add(issue_rb);
        issue_rb.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        issue_rb.setText("Issue Books");
        issue_rb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                issue_rbItemStateChanged(evt);
            }
        });
        issue_rb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issue_rbActionPerformed(evt);
            }
        });

        buttonGroup1.add(return_rb);
        return_rb.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        return_rb.setText("Return Books");
        return_rb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                return_rbItemStateChanged(evt);
            }
        });
        return_rb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return_rbActionPerformed(evt);
            }
        });

        jButton7.setText("select");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("ReturnBook");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton9.setText("Pay");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setText("Debit Bill");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel6.setText("Nor. of Copies Available:");

        jTextField6.setEditable(false);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jButton9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(34, 34, 34))
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane6)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel4)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(18, 18, 18)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(issue_rb)
                                        .add(54, 54, 54)
                                        .add(return_rb))
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 306, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(jButton10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 306, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 306, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(0, 0, Short.MAX_VALUE))))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel6)
                        .add(18, 18, 18)
                        .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel2)
                        .add(18, 18, 18)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 305, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(bar_ld, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(82, 82, 82)
                        .add(jButton5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                        .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(90, 90, 90))))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {jButton11, jButton3, jButton4, jButton8, jButton9}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(13, 13, 13)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jButton10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel5)
                                .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(27, 27, 27)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(26, 26, 26)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(return_rb)
                    .add(issue_rb))
                .add(13, 13, 13)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(bar_ld, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton5)
                        .add(jButton7)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 30, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton4)
                    .add(jButton8)
                    .add(jButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton9)
                    .add(jButton11))
                .add(36, 36, 36))
        );

        issuedview.addTab("Issue/Return", jPanel2);

        jLabel7.setText("BarCode No: ");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        bar_ld1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        bar_ld1.setToolTipText("QT Split");
        bar_ld1.setFocusPainted(false);
        bar_ld1.setFocusable(false);
        bar_ld1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        bar_ld1.setRequestFocusEnabled(false);
        bar_ld1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bar_ld1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Issued Books Details: ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jLabel9.setText("Nor. of Copies Available: ");

        jTextField8.setEditable(false);

        jLabel10.setText("Total Nor. of Copies:");

        jTextField9.setEditable(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel7)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 321, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(18, 18, 18)
                                        .add(bar_ld1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jLabel8))
                                .add(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel10)
                        .add(18, 18, 18)
                        .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 90, Short.MAX_VALUE)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(276, 276, 276))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(50, 50, 50)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(bar_ld1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel7)
                        .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10)
                    .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        issuedview.addTab("Issued ListView", jPanel1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, issuedview)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(issuedview))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        // finder.search(m_oTicket.getCustomer());
        finder.setVisible(true);
       customerInfo = finder.getSelectedCustomer();
        if (customerInfo!= null) {
            try {
                    
                   reset();
                    jTextField3.setText(customerInfo.getSearchkey());
                   jTable1.setVisible(true);
                      jTextField4.setText(customerInfo.toString());
                      memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                      imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                      jTable1.setModel(imodel.getTableModel1());
                      jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                        jTextField1.setText(null);
                        jTextField1.requestFocus();
                /*customer = dlSales.loadCustomerExt(customerInfo.getId());

                //          jTextField1.setText(customerInfo.toString());

                jTextField3.setText(customerInfo.getSearchkey());
                //StringBuffer name = new StringBuffer();
                //    m_jTicketId1.setText(customerInfo.toString());
                jTextField4.setText(customerInfo.toString());*/
                //dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                //jTextField3ActionPerformed(evt);
            } catch (BasicException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }

        //        try {
            //            m_oTicket.setCustomer(finder.getSelectedCustomer() == null
                //                  ? null
                //                    : dlSales.loadCustomerExt(finder.getSelectedCustomer().getId()));
            //        } catch (BasicException e) {
            //            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
            //            msg.show(this);
            //        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            
            loadData();
            //refreshModel();
            jTable1.setModel(new DefaultTableModel());
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            jTextField3.setText(null);
            jTextField2.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        
    if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            //String cust = jTextField3.getText();
            try {
                //Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                Object[] obj = dmang.getMamberbySkey(jTextField3.getText().toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                    jTextField4.setText(null);
                    jTextField3.setText(null);
                    reset();
                } else {
             
                    reset();
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(jTextField3.getText().toUpperCase());
                    
                      customerInfo.setMobile(obj[2].toString());
                    // customerInfo.setId(obj[0].toString());
                    //mname.setText(obj[1].toString());
                                    
                    /*custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                     custname =obj[1].toString();
                     customer = dlSales.loadCustomerExt(custname);
                     
                //    jTextField1.setText(obj[1].toString());
                       try{
                
                 Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj1== null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                         customer=dlSales.loadCustomerExt(obj1[0].toString());
                        //dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                           //setInitiator("");
                        //   dlReceipts.updateSharedTicketstd(customer.getId(),customer,m_App.getAppUserView().getUser().getRole());
                  }
             }catch(BasicException e){
                
                    }*/
                    
                           
                     /*StringBuffer name = new StringBuffer();
                    if (custoid != null) {
                     name.append(obj[1].toString());
                    name.append(" - ");*/
                   //  m_jTicketId1.setText(obj[1].toString());
                      
                     loadbook.clear();
                    jTable1.setVisible(true);
                      jTextField4.setText(obj[1].toString());
                    List<Object> cust=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT card FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                      if(!cust.contains(null)){
                    member=(Object[]) new StaticSentence(m_App.getSession(), "SELECT c.card FROM customers c where c.card=? AND C.VISIBLE = TRUE",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{cust.get(0).toString()});
                      
                      memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                      imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                      jTable1.setModel(imodel.getTableModel1());
                      jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                     //}
                        jTextField1.setText(null);
                        jTextField1.requestFocus();
                    
                      }else{
                        JOptionPane.showMessageDialog(this, "Please check... Card number is not assigned to this member");  
                      }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String portNumber = m_App.getProperties().getProperty("card.portnumber");
        String portValue = m_App.getProperties().getProperty("ACScard.port");
        if (!portNumber.isEmpty() && portValue.isEmpty()) {
            String card1 = cr.getData().toString();
               if (card1 != null) {
                try {
                    loadMemberDetails(card1);
                    
                } catch (HeadlessException ex) {
                    Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (portNumber.isEmpty() && !portValue.isEmpty()) {
                    final Cosacs cd = new Cosacs();

                    String card1 = cd.portOpen(portValue);
                    if(card1.equals("FALSE")){
                         JOptionPane.showMessageDialog(this, "The Port is Already Open", null, JOptionPane.OK_OPTION);
                    }
                    card1 = cd.getCardNumber();
            if (card1 != null) {
                try {
                    loadMemberDetails(card1);
                    
                } catch (HeadlessException ex) {
                    Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
             card1 = cd.portClose();
        }else if (portNumber.isEmpty() && portValue.isEmpty()) {
                    String card1 = jTextField2.getText();
       if (card1 != null) {
                try {
                    loadMemberDetails(card1);
                  
                } catch (HeadlessException ex) {
                    Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bar_ldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bar_ldActionPerformed
       jTextField1.setText(null);
       jTextField1.requestFocus();
       jTextField6.setText(null);
       if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
       refreshModel();
       }else{
          jTable1.setModel(new DefaultTableModel()); 
       }
        
        
    }//GEN-LAST:event_bar_ldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if(jTextField3.getText()!=null){
            if(jTable1.getSelectedRow()!=-1 && jTextField1.getText()!=null){
            try {
                memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                 //memid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(jTextField3.getText().toString());
                allbook_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT book_id FROM lib_bookissue_retn WHERE mem_id = ? and flag=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memid.get(0).toString());
                //if(book_id.size()>0 && memid.size()>0){
                    if(jTable1.getSelectedRow() < imodel.getSize()){
                        //if(!allbook_id.contains(book_id.get(0).toString())){
                     int row = jTable1.getSelectedRow(); 
                     if (jTable1.getModel().getValueAt(row,7).toString().equalsIgnoreCase("Not Issued")){//&&!allbook_id.contains(book_id.get(0).toString()) ) {
                     //lib_Issueline showdata = imodel.getList().get(row);
                     //deact_id=showdata.getId();
                         
                         int dys = getpidcty_days();
                         Date date = get_tobertn_dt(dys);
                     insertlib_issuebook(date);
                     /*loadbook.clear();
                      jTable1.setRowSelectionAllowed(true);
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null);
                      jTextField6.setText(null);*/
                      refreshModel();
                      JOptionPane.showMessageDialog(this, "Issued Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                     
                     }/*else {
                    JOptionPane.showMessageDialog(this, "selected Book is already issued.");
                        }*/
                    /*}else{
            JOptionPane.showMessageDialog(this, "selected Book is already issued.");
        }*/
                    }  
               /* }else{
            JOptionPane.showMessageDialog(this, "No Book is selected for issue");
        }*/
            } catch (BasicException ex) {
                Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
            JOptionPane.showMessageDialog(this, "Please read the bar-code of book");
        }
        }else{
            JOptionPane.showMessageDialog(this, "Member Not Present");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
        try {

            if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
                memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
            if(jTextField1.getText()!=null && !jTextField1.getText().equals("")){
            loadbook.clear();
            deptype="";
           book_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
           book_name = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
           nor_copies = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT copies FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(jTextField1.getText());
           membobj = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.memtype FROM lib_memtypeissuerules m where m.active=true", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           if(book_id.size()>0){
           issuememtype=(List<Object>) new StaticSentence(m_App.getSession(), "select appmemtype from lib_issuerules c,lib_bookmaster b where b.issuerules=c.ID and b.id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());
           bookcatid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT category FROM lib_bookmaster WHERE id = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());
           if(membobj!=null){
             memflag = memtypeflag= norofbooks=0;
           if(membobj.contains("All")){
               membobj.remove("All");
               memflag=1;
               //memidstr="All";
           }
               for(int i=0;i<membobj.size();i++){
                   memidstr=membobj.get(i).toString();
                String[]   memarry=memidstr.split("#");
               for(int j=0;j<memarry.length;j++){
                    if(issuememtype.get(0).toString().equals(memarry[j])){
                        memtypeflag=1;
                        retnid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_memtypeissuerules WHERE memtype= ? and active=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memidstr);
                        allmemissuerls = (Object[]) new StaticSentence(m_App.getSession(), "SELECT max_books,issue_flag,catg_nrbooks FROM lib_memtypeissuerules WHERE id= ? and active=true",  SerializerWriteString.INSTANCE , new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(retnid.get(0).toString());
                        max_books=allmemissuerls[0].toString();
                        issue_flag=allmemissuerls[1].toString();
                        catg_books=allmemissuerls[2].toString();
                        catary=catg_books.split("#");
                        for(int k=0;k<catary.length;k++){
                            String booknor=catary[k].toString();
                            bookary=booknor.split(":");
                            for(int l=0;l<bookary.length;l++){
                            if(bookcatid.get(0).equals(bookary[l])){
                                norofbooks=Integer.parseInt(bookary[l+1]);
                                
                            }
                            }
                        }
                        retnid.clear();
                        
                    }
                }
                   
               }
           } if(membobj!=null && norofbooks==0 && memtypeflag==0){
               if(memflag==1){
                   memidstr="All";
                   retnid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_memtypeissuerules WHERE memtype= ? and active=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memidstr);
                        allmemissuerls = (Object[]) new StaticSentence(m_App.getSession(), "SELECT max_books,issue_flag,catg_nrbooks FROM lib_memtypeissuerules WHERE id= ? and active=true",  SerializerWriteString.INSTANCE , new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(retnid.get(0).toString());
                        max_books=allmemissuerls[0].toString();
                        issue_flag=allmemissuerls[1].toString();
                        catg_books=allmemissuerls[2].toString();
                        catary=catg_books.split("#");
                        for(int k=0;k<catary.length;k++){
                            String booknor=catary[k].toString();
                                    
                            bookary=booknor.split(":");
                            for(int l=0;l<bookary.length;l++){
                            if(bookcatid.get(0).equals(bookary[l])){
                                norofbooks=Integer.parseInt(bookary[l+1]);
                                
                            }
                            }
                        }
                        retnid.clear();
               }
           }
           
               int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "select count(*) from lib_bookissue_retn where book_id =? and flag=true"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(book_id.get(0)).toString());
               Object[] maxbooks = (Object[]) new StaticSentence(m_App.getSession(), "SELECT count(*) FROM lib_bookissue_retn WHERE flag=true and mem_id=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{memid.get(0).toString()}); 
                     int cntmax=Integer.parseInt(maxbooks[0].toString());
               jTable1.setRowSelectionAllowed(true);
               
                obj1=null;
                obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT b.name,a.name FROM lib_bookmaster b,lib_author a WHERE a.id=b.author and b.id =? and b.active=true and b.available_flag=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(book_id.get(0).toString());
                loadbook.add(obj1[0]);
                loadbook.add(obj1[1]);
                allissue_id=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_bookissue_retn WHERE mem_id = ? and flag=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memid.get(0).toString());
                allbook_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT book_id FROM lib_bookissue_retn WHERE mem_id = ? and flag=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memid.get(0).toString());
                selectbook=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT b.name FROM lib_bookmaster b WHERE b.id =? and b.active=true and b.available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());       
                allmemtype = (List<Object>) new StaticSentence(m_App.getSession(), "select memtypes from lib_categories c,lib_bookmaster b where b.Category=c.ID and b.id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());
                issue_flag_count=bookscount=0;
                if(allbook_id.size()>0){
                    for(int i=0;i<allbook_id.size();i++){
                         bookcategory=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT b.category FROM lib_bookmaster b WHERE b.id =? and b.active=true and b.available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(allbook_id.get(i).toString());       
                         if(bookcatid.get(0).equals(bookcategory.get(0))){
                             bookscount=bookscount+1;
                         }
                         
                         
                    }
                   
                }
                if(allissue_id.size()>0){
                    for(int i=0 ;i<allissue_id.size();i++){
                        retn_id=allissue_id.get(i).toString();
                      double fine=getFineCharge();
                      if(fine>0){
                          issue_flag_count=issue_flag_count+1;
                      }
                    }
                    
                    
                }
                String allmem=allmemtype.get(0).toString();
                if(!allmem.equals("All")){
                arry=allmem.split("#");
                allmemtype.clear();
                for(int i=0;i<arry.length;i++){
                    
                    allmemtype.add(arry[i]);
                }
                 }
                int copies=Integer.parseInt(nor_copies.get(0).toString());
                jTextField6.setText(String.valueOf(copies-count));
                        
                        
                if(issue_rb.isSelected()){
                if (!allbook_id.contains(book_id.get(0).toString())){
                if(copies-count>0){
                    
                    memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                 cmemtype=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT memtype FROM customers c WHERE c.id =? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memid.get(0).toString());
                    if(issuememtype.get(0).toString().equals(cmemtype.get(0).toString())){
                 /*if(issuememtype.get(0).toString().equals(customerInfo.getMobile())){*/
                      if(cntmax<Integer.parseInt(max_books)){
                    if(bookscount<norofbooks){
                        if(issue_flag_count==0){
                            
                       
              if(allmemtype.contains(cmemtype.get(0).toString())||allmemtype.contains("All")){
                  memberint=memberint1=0;
               if(member!=null){
                    memberint=Integer.valueOf(new StaticSentence(m_App.getSession(), "select l.memberint from lib_issuerules l,lib_bookmaster b where b.IssueRules=l.id and b.id=? and l.active=true",SerializerWriteString.INSTANCE,SerializerReadInteger.INSTANCE).find(book_id.get(0)).toString());
                   //memb = (List<Object>) new StaticSentence(m_App.getSession(), "select l.memberint from lib_issuerules l,lib_bookmaster b where b.IssueRules=l.id and b.id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());
               }else if(spodep!=null){
                   memberint1=Integer.valueOf(new StaticSentence(m_App.getSession(), "select l.memberint from lib_issuerules l,lib_bookmaster b where b.IssueRules=l.id and b.id=? and l.active=true",SerializerWriteString.INSTANCE,SerializerReadInteger.INSTANCE).find(book_id.get(0)).toString());
                   if(spodep[1].equals("Spouse")){
                       spouse=2;
                       spouse1=3;
                   }else{
                       dept=3;
                   }
               }
               if(memberint!=null && (memberint==1||memberint==2||memberint==3)){
                   
               
                //jTable1.setEnabled(true);
                imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                  
                        for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && jTable1.getModel().getValueAt(i,7).toString().equals("Not Issued")){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setRowSelectionAllowed(false);
             
                         //jTable1.setEnabled(false);
                         
                     }
                 }
               
                        jButton3.requestFocus();
                        //jTable1.setRowSelectionAllowed(true);
                         deptype="Member";
                }else if(memberint1==spouse ||memberint1==spouse1){
                    imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                  
                        for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && jTable1.getModel().getValueAt(i,7).toString().equals("Not Issued")){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setEnabled(false);
                         //jTable1.setRowSelectionAllowed(false);
                     }
                 }
               
                        jButton3.requestFocus();
                        //jTable1.setRowSelectionAllowed(true);
                        deptype=spodep[1].toString();
                }
                else if(memberint1==dept){
                    imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                  
                        for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && jTable1.getModel().getValueAt(i,7).toString().equals("Not Issued")){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setEnabled(false);
                         //jTable1.setRowSelectionAllowed(false);
                     }
                 }
               
                        jButton3.requestFocus();
                        //jTable1.setRowSelectionAllowed(true);
                        deptype=spodep[1].toString();
                }
               else{
                    JOptionPane.showMessageDialog(this, "This Book is not Issuable ");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
               }else{
                    JOptionPane.showMessageDialog(this, "This Book Category is not Applicable to this member");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
                    }else{
                            if(Integer.parseInt(issue_flag)==1){
                                //if(allmemtype.contains(customerInfo.getMobile())||allmemtype.contains("All")){
                                if(allmemtype.contains(cmemtype.get(0).toString())||allmemtype.contains("All")){
                  memberint=memberint1=0;
               if(member!=null){
                    memberint=Integer.valueOf(new StaticSentence(m_App.getSession(), "select l.memberint from lib_issuerules l,lib_bookmaster b where b.IssueRules=l.id and b.id=? and l.active=true",SerializerWriteString.INSTANCE,SerializerReadInteger.INSTANCE).find(book_id.get(0)).toString());
                   //memb = (List<Object>) new StaticSentence(m_App.getSession(), "select l.memberint from lib_issuerules l,lib_bookmaster b where b.IssueRules=l.id and b.id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());
               }else if(spodep!=null){
                   memberint1=Integer.valueOf(new StaticSentence(m_App.getSession(), "select l.memberint from lib_issuerules l,lib_bookmaster b where b.IssueRules=l.id and b.id=? and l.active=true",SerializerWriteString.INSTANCE,SerializerReadInteger.INSTANCE).find(book_id.get(0)).toString());
                   if(spodep[1].equals("Spouse")){
                       spouse=2;
                       spouse1=3;
                   }else{
                       dept=3;
                   }
               }
               if(memberint!=null && (memberint==1||memberint==2||memberint==3)){
                   
               
                //jTable1.setEnabled(true);
                imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                  
                        for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && jTable1.getModel().getValueAt(i,7).toString().equals("Not Issued")){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setRowSelectionAllowed(false);
             
                         //jTable1.setEnabled(false);
                         
                     }
                 }
               
                        jButton3.requestFocus();
                        //jTable1.setRowSelectionAllowed(true);
                        deptype="Member";
                }else if(memberint1==spouse ||memberint1==spouse1){
                    imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                  
                        for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && jTable1.getModel().getValueAt(i,7).toString().equals("Not Issued")){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setEnabled(false);
                         //jTable1.setRowSelectionAllowed(false);
                     }
                 }
               
                        jButton3.requestFocus();
                        //jTable1.setRowSelectionAllowed(true);
                        deptype=spodep[1].toString();
                }
                else if(memberint1==dept){
                    imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                  
                        for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && jTable1.getModel().getValueAt(i,7).toString().equals("Not Issued")){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setEnabled(false);
                         //jTable1.setRowSelectionAllowed(false);
                     }
                 }
               
                        jButton3.requestFocus();
                        deptype=spodep[1].toString();
                        //jTable1.setRowSelectionAllowed(true);
                }
               else{
                    JOptionPane.showMessageDialog(this, "This Book is not Issuable ");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
               }else{
                    JOptionPane.showMessageDialog(this, "This Book Category is not Applicable to this member");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
                            }else{
                                JOptionPane.showMessageDialog(this, "Books cannot be issued.Because of overdue");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                            }
                        }
                    }else{
                        if(norofbooks==0){
                            JOptionPane.showMessageDialog(this, "Book Category is not existing for this MemType in MemTypeIssueRules");
                        }else{
                    JOptionPane.showMessageDialog(this, "Book Category wise maximum nor. of Books reached");
                        }
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
                      }else{
                    JOptionPane.showMessageDialog(this, "Maximum nor. of Books reached");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
                    
                      }else{
                    JOptionPane.showMessageDialog(this, "this book memtype is not applicable to this member");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
                
                }else{
                    JOptionPane.showMessageDialog(this, "No stock");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
           }else{
                    JOptionPane.showMessageDialog(this, "selected Book is already issued.");
                    jTextField1.setText(null);
                    jTextField6.setText(null);
                        }
               
           }else if(return_rb.isSelected()){
               if (allbook_id.contains(book_id.get(0).toString())){
                   
                   loadbook.clear();
                    //jTable1.setEnabled(true);
                imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                    for(int i =0;i<jTable1.getRowCount();i++){
                        Object[] coun = (Object[]) new StaticSentence(m_App.getSession(), "SELECT count(*) FROM lib_bookissue_retn WHERE book_id = ? and flag=true and mem_id=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{book_id.get(0).toString(),memid.get(0).toString()}); 
                     int cnt=Integer.parseInt(coun[0].toString());
                        if(cnt>1){
                        //String rw=String.valueOf(i);
                        //int k=Integer.parseInt(jTable1.getModel().getValueAt(i,0).toString());
                        if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString()) && Integer.parseInt(jTable1.getModel().getValueAt(i,0).toString())==i+1){
                         
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         rw=jTable1.getSelectedRow();
                         //jTable1.setEnabled(false);
                         
                         break;
                        }
                     }else{
                            if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString())){
                         
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setEnabled(false);
                         rw=jTable1.getSelectedRow();
                         break;
                        }
                     }
                 }
                    jButton8.requestFocus();
           }else{
                  JOptionPane.showMessageDialog(this, "No book found to return");
                  jTextField1.setText(null);
                  jTextField6.setText(null);
                  
               }
                }
                
           }else{
            JOptionPane.showMessageDialog(this, "No book found");
            jTextField1.setText(null);
            jTextField6.setText(null);
        }
            }else{
            JOptionPane.showMessageDialog(this, "Please read the bar-code of book");
        }
        }else{
            JOptionPane.showMessageDialog(this, "please Swipe a Member Card ");
        }
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
                memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
            if(jTextField1.getText()!=null && !jTextField1.getText().equals("")){
            loadbook.clear();
           book_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
           //allbook_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(jTextField5.getText().toString());
           if(book_id.size()>0){
                obj1=null;
                obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT b.name,a.name FROM lib_bookmaster b,lib_author a WHERE a.id=b.author and b.id =? and b.active=true and b.available_flag=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(book_id.get(0).toString());
                loadbook.add(obj1[0]);
                loadbook.add(obj1[1]);
                jTable1.setEnabled(true);
                imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
           }else{
            JOptionPane.showMessageDialog(this, "No book find");
        }
            }else{
            JOptionPane.showMessageDialog(this, "Please select the bar-code of book");
        }
        }/*else{
            JOptionPane.showMessageDialog(this, "Swipe a Card ");
        }*/
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            loadData();
            jTable1.setModel(new DefaultTableModel());
            jTextField3.setText(null);
            jTextField2.setVisible(false);
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void return_rbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_return_rbItemStateChanged
      
        if(return_rb.isSelected()){
        jButton9.setVisible(false);
        jButton8.setVisible(true);
        jButton4.setVisible(true);
        jButton11.setVisible(false);
        jButton3.setVisible(false);
        jTable1.clearSelection();
        jTable1.setEnabled(true);
        jTable1.setRowSelectionAllowed(true);
        jTextField2.setVisible(false);
        jTextField1.setText(null);
        jLabel2.setVisible(false);
        jTextField1.setVisible(false);
        bar_ld.setVisible(false);
        jLabel6.setVisible(false);
        jTextField6.setVisible(false);
        
        if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
            jTextField1.requestFocus();
            refreshModel();
        }else{
        jTextField2.requestFocus();
       }
       }/*else{
        jButton9.setVisible(false);
        jButton8.setVisible(false);
        jButton11.setVisible(false);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jTable1.setRowSelectionAllowed(false);
        
        jTextField2.setVisible(false);
        jTextField1.setText(null);
        jLabel2.setVisible(true);
        jTextField1.setVisible(true);
        bar_ld.setVisible(true);
        jLabel6.setVisible(true);
        jTextField6.setVisible(true);
        if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
            jTextField1.requestFocus();
        }else{
        jTextField2.requestFocus();
       }
       }*/
       
    }//GEN-LAST:event_return_rbItemStateChanged

    private void issue_rbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_issue_rbItemStateChanged
        
        if(issue_rb.isSelected()){
        jButton9.setVisible(false);
        jButton8.setVisible(false);
        jButton11.setVisible(false);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jTable1.clearSelection();
        jTable1.setRowSelectionAllowed(false);
        //jTable1.setEnabled(false);
        jTextField2.setVisible(false);
        jTextField1.setText(null);
        jLabel2.setVisible(true);
        jTextField1.setVisible(true);
        bar_ld.setVisible(true);
        jLabel6.setVisible(true);
        jTextField6.setVisible(true);
        if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
            jTextField1.requestFocus();
        }else{
        jTextField2.requestFocus();
       }
       }
        
    }//GEN-LAST:event_issue_rbItemStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
        try {
            if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
                memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
            if(jTextField1.getText()!=null && !jTextField1.getText().equals("")){
            loadbook.clear();
           book_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
           book_name = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
           if(book_id.size()>0){
              selectbook=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT b.name FROM lib_bookmaster b WHERE b.id =? and b.active=true and b.available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString());
                //obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT b.name FROM lib_bookmaster b WHERE b.id =? and b.active=true", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(book_id.get(0).toString());
                
                 for(int i =0;i<jTable1.getRowCount();i++){
                     if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString())){
                         //jTable1.setRowSelectionAllowed(true);
                         jTable1.setRowSelectionInterval(i, i);
                         //jTable1.setEnabled(true);
                         break;
                     }
                 }
//loadbook.add(obj1[0]);
                //loadbook.add(obj1[1]);
               /* imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(60);*/
           }else{
            JOptionPane.showMessageDialog(this, "No book find");
        }
            }else{
            JOptionPane.showMessageDialog(this, "Please read the bar-code of book");
        }
        }else{
            JOptionPane.showMessageDialog(this, "Member Not Present");
        }
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     if(jTextField3.getText()!=null){
            if(jTable1.getSelectedRow()!=-1){
            try {
                memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                 //memid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(jTextField3.getText().toString());
                allbook_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT book_id FROM lib_bookissue_retn WHERE mem_id = ? and flag=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memid.get(0).toString());
                //if(book_id.size()>0 && memid.size()>0){
                    if(jTable1.getSelectedRow() < imodel.getSize()){
                        //if(!allbook_id.contains(book_id.get(0).toString())){
                     //int row = jTable1.getSelectedRow(); 
                     //if (book_id.size()>0) {
                    
                
                 //for(int i =0;i<jTable1.getRowCount();i++){
                     //if(jTable1.getModel().getValueAt(i,2).toString().equalsIgnoreCase(selectbook.get(0).toString())){
                         if(jTable1.getSelectedRow()!=-1){
                             
                            int row =jTable1.getSelectedRow();
                        lib_Issueline data=imodel.getList().get(row);
                          retn_id=data.getId();
                          String bookid=data.getBook_id();
                          String depnt_type=data.getDep_type();
                          fine=getFineCharge();
                          int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT value FROM generaltable WHERE NAME=?"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find("Return Books through BarCode :").toString());
                          String bar;
                          if(count==1){
                         
                          BarCodeManage barcode=BarCodeManage.getDialog(this, dlSales,null, customerInfo,0);
                          barcode.init();
                          barcode.showDialog(); 
                          bar=barcode.barcodeaction();
                         if(bar.equals(bookid)){
                             
                         
                             //retnobj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",
                        //new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{book_id.get(0).toString(),memid.get(0).toString()}); 
                            //retnid = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_bookissue_retn WHERE book_id = ? and flag=true and mem_id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book_id.get(0).toString(),memid.get(0).toString());
                         if(fine==0){
                             if(depnt_type.equals("Member") && member!=null){
                                 updatelib_returnbook();
                                 JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }/*else{
                                  JOptionPane.showMessageDialog(this, "Cannot return..Selected book is not matching with Card swiped");
                             }*/
                             else if(depnt_type.equals("") && member!=null)
                             {
                                 updatelib_returnbook();
                                 JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }
                             else if(spodep!=null && depnt_type.equals(spodep[1]) ){
                                  updatelib_returnbook();
                                  JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }else{
                                 JOptionPane.showMessageDialog(this, "Cannot return..please Check Selected book M-Type and Card swiped");
                             }
                             //updatelib_returnbook();
                             loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);       
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null); 
                      jTextField6.setText(null);
                      //jTable1.setRowSelectionAllowed(true);
                      //JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                             book_name = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM lib_bookmaster WHERE id = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(bookid);
                                if(depnt_type.equals("Member") && member!=null){
                                Lib_BillPages bill=Lib_BillPages.getDialog(this, dlSales,null, customerInfo,0);
                                bill.init(customerInfo);
                                bill.showDialog(book_name,fine,retn_id);
                             }else if(depnt_type.equals(spodep[1]) && spodep!=null){
                                  Lib_BillPages bill=Lib_BillPages.getDialog(this, dlSales,null, customerInfo,0);
                                bill.init(customerInfo);
                                bill.showDialog(book_name,fine,retn_id);
                             }
                             /*else if(depnt_type.equals("") (&& member!=null)
                             {
                                 updatelib_returnbook();
                                 JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }*/
                             else{
                                 JOptionPane.showMessageDialog(this, "Cannot return..please Check Selected book M-Type and Card swiped");
                             }
                                //updatelib_returnbook();
                                loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null);
                      jTextField6.setText(null);
                      jTextField1.requestFocus();
                      //jTable1.setRowSelectionAllowed(true);
                      //JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }
                         }else {
                            JOptionPane.showMessageDialog(this, "Selected book is not matching");
                        }
                          }else{
                              if(fine==0){
                                  if(depnt_type.equals("Member") && member!=null){
                                 updatelib_returnbook();
                                 JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }else if(depnt_type.equals(spodep[1]) && spodep!=null){
                                  updatelib_returnbook();
                                  JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }else if(depnt_type.equals("") && member!=null)
                             {
                                 updatelib_returnbook();
                                 JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }else{
                                 JOptionPane.showMessageDialog(this, "Cannot return..please Check Selected book M-Type and Card swiped");
                             }
                             loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null); 
                      jTextField6.setText(null);
                      jTable1.setRowSelectionAllowed(true);
                      
                            }else{
                             if(depnt_type.equals("Member") && member!=null){
                                Lib_BillPages bill=Lib_BillPages.getDialog(this, dlSales,null, customerInfo,0);
                                bill.init(customerInfo);
                                bill.showDialog(book_name,fine,retn_id);
                             }else if(depnt_type.equals(spodep[1]) && spodep!=null){
                                  Lib_BillPages bill=Lib_BillPages.getDialog(this, dlSales,null, customerInfo,0);
                                bill.init(customerInfo);
                                bill.showDialog(book_name,fine,retn_id);
                             }/*else if(depnt_type.equals("") && member!=null)
                             {
                                 updatelib_returnbook();
                                 JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }*/else{
                                 JOptionPane.showMessageDialog(this, "Cannot return..please Check Selected book M-Type and Card swiped");
                             }
                                
                                //updatelib_returnbook();
                                loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null);
                      jTextField6.setText(null);
                      jTextField1.requestFocus();
                      //jTable1.setRowSelectionAllowed(true);

                      //JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }
                          }
                        }         
                     //}
                     //break;
                 //}
                     
                     /*loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null);  */
                   /* }else {
                    JOptionPane.showMessageDialog(this, "Please read Bar-Code Of Book");
                        }*/
                    /*}else{
            JOptionPane.showMessageDialog(this, "selected Book is already issued.");
        }*/
                    }  
               /* }else{
            JOptionPane.showMessageDialog(this, "No Book is selected for issue");
        }*/
            } catch (BasicException ex) {
                Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Member Not Present");
        }  
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
       
        String cust = jTextField2.getText();
        jTextField2.setVisible(false);
        member=null;
        spodep=null;
        try {
            //Object[] obj = dmang.getMamberbycard(cust);
             Object[] obj = dmang.getMemberByCard1(cust);
            if (obj == null) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
     
            } 
            
            else {
                 customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setSearchkey(obj[1].toString());
                    customerInfo.setName(obj[2].toString());
                    customerInfo.setMobile(obj[3].toString());
                    customerInfo.setAccno(obj[4].toString());
                    jTextField3.setText(obj[1].toString());
                    jTextField4.setText(obj[2].toString());
                    jTable1.setVisible(true);
                    memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                    //String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                    loadbook.clear();
                    imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                    jTable1.setModel(imodel.getTableModel1());
                      jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                        jTextField2.setVisible(false);
                        jTextField1.setText(null);
                        jTextField1.requestFocus();
                /*custoid = obj[0].toString();
                        
                customer = dlSales.loadCustomerExt(custoid);
              //jTextField1.setText(obj[1].toString());
                jTextField3.setText(obj[2].toString());
                jTextField4.setText(obj[1].toString());*/
               //dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
            
             member=(Object[]) new StaticSentence(m_App.getSession(), "SELECT c.card FROM customers c where c.card=? AND C.VISIBLE = TRUE",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{cust});
            if(member==null){
            spodep=(Object[]) new StaticSentence(m_App.getSession(), "SELECT m.card,m.dtype FROM memdependent m,customers c where m.memno=c.id and m.card=? AND C.VISIBLE = TRUE and m.active=true",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(new Object[]{cust});
            }
            }
            /* if (obj1 == null) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            }else{
                
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void return_rbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return_rbActionPerformed
        if(return_rb.isSelected()){
        jButton9.setVisible(false);
        jButton8.setVisible(true);
        jButton4.setVisible(true);
        jButton11.setVisible(false);
        jButton3.setVisible(false);
        jTable1.clearSelection();
        jTable1.setRowSelectionAllowed(true);
        jTable1.setEnabled(true);
        jTextField2.setVisible(false);
        jTextField1.setText(null);
        jLabel2.setVisible(false);
        jTextField1.setVisible(false);
        bar_ld.setVisible(false);
        jLabel6.setVisible(false);
        jTextField6.setVisible(false);
        
        if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
            jTextField1.requestFocus();
        }else{
        jTextField2.requestFocus();
       }
       }else{
        jButton9.setVisible(false);
        jButton8.setVisible(false);
        jButton11.setVisible(false);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jTable1.setRowSelectionAllowed(false);
        
        jTextField2.setVisible(false);
        jTextField1.setText(null);
        jLabel2.setVisible(true);
        jTextField1.setVisible(true);
        bar_ld.setVisible(true);
        jLabel6.setVisible(true);
        jTextField6.setVisible(true);
        if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
            jTextField1.requestFocus();
        }else{
        jTextField2.requestFocus();
       }
       }
    }//GEN-LAST:event_return_rbActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        /*String custoid;
        String custname;
        String cust = jTextField3.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                             
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                     custname =obj[1].toString();
                     customer = dlSales.loadCustomerExt(custname);
                     
                //    jTextField1.setText(obj[1].toString());
                       try{
                
                 Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj1== null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                         customer=dlSales.loadCustomerExt(obj1[0].toString());
                        //dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                           //setInitiator("");
                        //   dlReceipts.updateSharedTicketstd(customer.getId(),customer,m_App.getAppUserView().getUser().getRole());
                  }
             }catch(BasicException e){
                
                    }
                    
                           
                     StringBuffer name = new StringBuffer();
                    if (custoid != null) {
                     name.append(obj[1].toString());
                    name.append(" - ");
                   //  m_jTicketId1.setText(obj[1].toString());
                    jTable1.setVisible(true);
                      jTextField4.setText(obj[1].toString());
                      memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                      imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                      jTable1.setModel(imodel.getTableModel1());
                      jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                     }
                    
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void issue_rbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issue_rbActionPerformed
        
    }//GEN-LAST:event_issue_rbActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       if(jTextField3.getText()!=null){
         if(jTable1.getSelectedRow()!=-1){ 
        if(jTable1.getSelectedRow() < imodel.getSize()){
        if (book_id.size()>0) {
        try {
            Transaction t = new Transaction(m_App.getSession()) {
                
                @Override
                protected Object transact() throws BasicException {
                    String name = "";
                    List<PaymentInfo> pinfo = new ArrayList<PaymentInfo>();
                    List lnames = new ArrayList();
                    if (jTextField1.getText().length()>0)  {
                        Date d = new Date();
                        CustomerInfoExt cinfo = new CustomerInfoExt(customerInfo.getId());
                        cinfo.setSearchkey(customerInfo.getSearchkey());
                        cinfo.setName(customerInfo.getName());
                        JPaymentSelect paymentdialog = JPaymentSelectReceipt.getDialog(new JFrame());
                        paymentdialog.init(m_App);
                        boolean flag = paymentdialog.showDialog(getFineCharge(), cinfo, m_App.getAppUserView().getUser().getName(), true);
                        if (flag == true) {
                            pinfo = paymentdialog.getSelectedPayments();
                            BillInfo ticket = new BillInfo();
                            ticket.setID(UUID.randomUUID().toString());
                            ticket.setPayments(pinfo);
                            ticket.setCustomer(cinfo);
                            ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
                            ticket.setCreatedDate(getdate());
                            ticket.setActiveCash(m_App.getActiveCashIndex());
                            ticket.setFloor("Library");
                            //Guest cat changes-start
                            
                            //Guest cat changes-end
                            String rnum = dlSales.payaccount(ticket, m_App.getInventoryLocation(), true);
                            if (!(rnum == null
                                    || rnum.equals("false"))) {
                                updatelib_returnbook();
                                printTicket(book_name.get(0).toString(),rnum, cinfo.getName(), pinfo, fine, cinfo.getSearchkey());
                                
                            }else {
                                if (rnum.equals("false")) {
                                    JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.OK_OPTION);
                                }
                            }
                        }
                        
                        
                    }
                    return null;
                }
            };
            t.execute();
        
        loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null);
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }              
    }
    }
        }
       }else{
            JOptionPane.showMessageDialog(this, "Member Not Present");
        } 
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(jTextField3.getText()!=null){ 
        if(jTable1.getSelectedRow()!=-1){ 
        if(jTable1.getSelectedRow() < imodel.getSize()){
        if (book_id.size()>0) {
        try {
            if (JOptionPane.showConfirmDialog(this, "do u need to create a debit bill", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        String name = "";
                        List<PaymentInfo> pinfo = new ArrayList<PaymentInfo>();
                        List lnames = new ArrayList();
                          Date d = new Date();
                                
                                    CustomerInfoExt cinfo = new CustomerInfoExt(customerInfo.getId());
                                    cinfo.setSearchkey(customerInfo.getSearchkey());
                                    cinfo.setName(customerInfo.getName());
                                    JPaymentSelect paymentdialog = JPaymentSelectReceipt.getDialog(new JFrame());
                                    paymentdialog.init(m_App);
                                    boolean flag=paymentdialog.showDialog(/*Double.parseDouble(amount.getText())*/getFineCharge(), cinfo, m_App.getAppUserView().getUser().getName(),true);
                                    if(flag==true){
                                    PaymentInfoTicket payment = new PaymentInfoTicket(fine, "debt");

                                    pinfo.add(payment);
                                    BillInfo ticket = new BillInfo();
                                    ticket.setID(UUID.randomUUID().toString());
                                    ticket.setPayments(pinfo);
                                    ticket.setCustomer(cinfo);
                                    ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
                                    ticket.setCreatedDate(getdate());
                                    ticket.setActiveCash(m_App.getActiveCashIndex());
                                    ticket.setFloor("Library");
                                    //Guest cat changes-start
                                    
                                    //Guest cat changes-end
                                    String rnum = dlSales.payaccount(ticket, m_App.getInventoryLocation(), true);
                                    //  }
                                    if (!(rnum == null || rnum.equals("false"))) {
                                        
                                        String tid = UUID.randomUUID().toString();
                                        Date dnow = new Date();
                                        String custAcc = null;
                                        Object obj = new PreparedSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(customerInfo.getId());
                                        if (obj != null) {
                                            custAcc = obj.toString();
                                        }
                                        //Object[] value = new Object[]{UUID.randomUUID().toString(), customerInfo.getId(), d, gcat.getid(), amt, gno, name, rnum, m_App.getAppUserView().getUser().getName()};
                                        //new PreparedSentence(m_App.getSession(), "INSERT INTO GUESTLOG(ID,MEMNO,DATE,GUESTCAT,AMOUNT,NUM,NAMES,RECEIPTNO,CREATEDBY) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING})).exec(value);
                                        Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Library Fine", "LF", fine, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Library Fine Amount", custAcc, 0.0, dnow, true};
                                        dmang.insertintoaccjoutnal1(value1);
                                        Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, customerInfo.getId(), dnow, "D", "Library Fine", "LF", fine, dnow, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Library Fine Amount", custAcc, fine, true};
                                        dmang.insertintoaccjoutnal(value2);
                                        //TODO add the accountjournal entry here
                                        updatelib_returnbook();
                                        printTicket(book_name.get(0).toString(), rnum, cinfo.getName(), pinfo, fine, cinfo.getSearchkey());
                                        //loadData();
                                    } else {
                                        if (rnum.equals("false")) {
                                            JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.OK_OPTION);
                                        }
                                    }
                                 }
                           
                        return null;
                    }
                };
                t.execute();
                loadbook.clear();
                     imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                     jTable1.setModel(imodel.getTableModel1());
                     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);
                      jTextField1.setText(null);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       }
        }
         }
         }else{
            JOptionPane.showMessageDialog(this, "Member Not Present");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void bar_ld1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bar_ld1ActionPerformed
        jTextField7.setText(null);
        jTextField7.requestFocus();
        jTextField8.setText(null);
        jTextField9.setText(null);
        jTable2.setModel(new DefaultTableModel());
    }//GEN-LAST:event_bar_ld1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        if(jPanel1.isShowing()){
       if(jTextField7.getText()!=null && !jTextField7.getText().equals("")){
           try {
               loadbook.clear();
               book_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField7.getText());
               book_name = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField7.getText());
               nor_copies = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT copies FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(jTextField7.getText());
               int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                       , "select count(*) from lib_bookissue_retn where book_id =? and flag=true"
                       ,SerializerWriteString.INSTANCE
                       ,SerializerReadInteger.INSTANCE).find(book_id.get(0)).toString());
               if(book_id.size()>0){
                   int copies=Integer.parseInt(nor_copies.get(0).toString());
                jTextField9.setText(String.valueOf(copies));
                jTextField8.setText(String.valueOf(copies-count));
                imodel = Lib_IssueTableModel.loadInstance(m_App,book_id);
                jTable2.setModel(imodel.getTableModel());
                //if(jTable2.selectedRow())
               }else{
            JOptionPane.showMessageDialog(this, "No book find");
        }
           } catch (BasicException ex) {
               Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
            JOptionPane.showMessageDialog(this, "Please read the bar-code of book");
        }
        }
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void issuedviewStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_issuedviewStateChanged
        
        /*if(issuedview.getSelectedIndex()==0){
            try {
                loadData();
            } catch (BasicException ex) {
                Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        if(issuedview.getSelectedIndex()==1){
            jTextField7.setText(null); 
            jTextField7.requestFocus();
            jTextField8.setText(null);
            jTextField9.setText(null);
            jTable2.setModel(new DefaultTableModel());
            
        }
        if(jTextField3.getText()!=null && !jTextField3.getText().equals("")){
        jTextField1.requestFocus();
        }else{
        jTextField2.requestFocus();
        }
        //jTextField2.setVisible(false);
    }//GEN-LAST:event_issuedviewStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int i=jTable1.getSelectedRow();
        if(jTable1.getModel().getValueAt(i,7).equals("Not Issued") && jTable1.isCellSelected(i,5) && jTable1.isColumnSelected(5) ){
          try {
               int x=jTable1.getSelectedRow();
               int y=jTable1.getSelectedColumn();
              //editable=true;
               //refreshModel();
               int dys = getpidcty_days();
               Date date = get_tobertn_dt(dys);
               ReturnDateChange retndt=ReturnDateChange.getDialog(this, dlSales,null, customerInfo,0);
               retndt.init(date); 
               retndt.showDialog();
               Date changedt=retndt.chngdate;
               if(changedt!=null){
               DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
               String str2 = df2.format(changedt);
               if (JOptionPane.showConfirmDialog(this, "Are you sure.. "+str2+" would be the return date of this Book ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                   insertlib_issuebook(changedt);
                   refreshModel();
                   JOptionPane.showMessageDialog(this, "Issued Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
               }
           //}
               
               //loadbook.add(str2);
               /*imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
           jTable1.setModel(imodel.getTableModel1());
           jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);*/
               /*DefaultTableModel dtm=imodel.getTableModel1();
               if(dtm.isCellEditable(x, y)==false){
                   
               }
               boolean df=jTable1.isCellEditable(x, y);
               //jTable1.setEditingColumn(y);
               jTable1.setEnabled(true);
              //boolean flag= jTable1.editCellAt(x, y);
              //jTable1.setFocusable(true);
               jTable1.setValueAt((Object)str2, x, y);
               dtm.fireTableCellUpdated(x, y);*/
               
               }
           } catch (BasicException ex) {
               Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
           }
                         }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bar_ld;
    private javax.swing.JButton bar_ld1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton issue_rb;
    private javax.swing.JTabbedPane issuedview;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JRadioButton return_rb;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
        return "Issue/Return Books";
    }
    


    @Override
    public void activate() throws BasicException {
        
        
        startCardReader();
        //if(jPanel2.isShowing()){
        jTextField2.setVisible(false);
         if(getFacilityId()==0){
           jTextField3.setEditable(true);
           jTextField4.setEditable(false);
           //jTextField3.requestFocus();
           //jButton2.setEnabled(true);
           jButton2.setVisible(true);
        }else{
            jTextField3.setEditable(false);
            jTextField4.setEditable(false);
            //jButton2.setEnabled(false);
            jButton2.setVisible(false);
        }
        loadData(); 
        jTextField3.setText(null);
        
    //}
        /*else if(jPanel1.isShowing()){
      jTextField7.setText(null); 
      jTextField7.requestFocus();
    }*/
        //jButton2.setVisible(false);
        //jButton7.setVisible(false);
        //jButton1.setVisible(false);
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
        m_App=app;
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlCustomers = (DataLogicCustomers) m_App.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        ttp = new TicketParser(app.getDeviceTicket(), dlsystem);
        
        
       
    }

    @Override
    public Object getBean() {
        return this;
    }
   
    /*public void setCellEditable(int row, int col, boolean value) {
        this.editable_cells[row][col] = value; // set cell true/false
        this.fireTableCellUpdated(row, col);
    }*/
     public void reset(){
         
         jTextField5.setText(null);
         jTextField2.setText(null);
         jTextField6.setText(null);
         jTextField4.setText(null);
         jTextField1.setText(null);
         issuedview.setSelectedIndex(0);
         issue_rb.setSelected(true);
         return_rb.setSelected(false);
        jTable1.setModel(new DefaultTableModel());
        jTextField2.setVisible(false);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jButton9.setVisible(false);
        jButton11.setVisible(false);
        jButton5.setVisible(false);
        jButton7.setVisible(false);
        
        if(issue_rb.isSelected()){
             jTable1.setRowSelectionAllowed(false);
             jTable1.clearSelection();
        }else{
            jTable1.setRowSelectionAllowed(true);
        }
        
     }
         private void loadData() throws BasicException {
           reset();  
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                 //akshatha:to read a card from card reader without port num
                String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
                if (cardReaderPortName == null || cardReaderPortName.trim().length() == 0) {
                    jTextField2.setVisible(true);
                    jTextField2.requestFocus();
                    jTextField2.setEditable(true);
                    jButton10.setVisible(true);
                    jLabel5.setVisible(true);
                } else {
                    jTextField3.requestFocus();
                    jTextField2.setEditable(false);
                    jTextField2.setVisible(false);
                    jButton10.setVisible(false);
                    //jLabel8.setVisible(false);
                }
                if(issue_rb.isSelected()){
             jTable1.setRowSelectionAllowed(false);
             jTable1.clearSelection();
        }else{
            jTable1.setRowSelectionAllowed(true);
        }
                
            }
        });
        
        /*if(getFacilityId()==1){
           jTextField3.setEnabled(true);
           jButton2.setEnabled(true);
           jButton2.setVisible(true);
        }else{
            jTextField3.setEnabled(false);
            jButton2.setEnabled(false);
            jButton2.setVisible(false);
        }*/
        
       jTextField2.setVisible(false);
     }
    public String getInitiator() {
        return initiator;
    }
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
    public void loadMemberDetails(String card) throws HeadlessException {
        // TODO add your handling code here:
        String custoid;
        /// card = cr.getData();


        if (card.length() > 0) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=? UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                      jTextField3.setText(obj[1].toString());
                  jTextField4.setText(obj[2].toString());
                  memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                  //dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                    //setInitiator(obj[4].toString());
                    flag = false;
                    
                  if(issue_rb.isSelected()||return_rb.isSelected()){
                     jTable1.setVisible(true);
                     memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
                    imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
                    jTable1.setModel(imodel.getTableModel1());
                            
                    
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        TableColumnModel cmodel=jTable1.getColumnModel();
                        cmodel.getColumn(0).setPreferredWidth(50);
                        cmodel.getColumn(1).setPreferredWidth(150);
                        cmodel.getColumn(2).setPreferredWidth(200);
                        cmodel.getColumn(3).setPreferredWidth(150);
                        cmodel.getColumn(4).setPreferredWidth(100);
                        cmodel.getColumn(5).setPreferredWidth(100);
                        cmodel.getColumn(6).setPreferredWidth(60);  
                  }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
    }
    
    private void insertlib_issuebook(Date to_rtndt) {
        
         try{
          if(book_id.size()>0){
              
             Object[] param=new Object[]{UUID.randomUUID().toString(),memid.get(0).toString(),book_id.get(0).toString(),new Date(),to_rtndt,true,deptype};
             new PreparedSentence(m_App.getSession()
                , "INSERT INTO lib_bookissue_retn (ID,mem_id,book_id,issue_date,to_be_retn_dt,flag,d_type) VALUES (?,?,?,?,?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.STRING})).exec(param);
     
             jTable1.setRowSelectionAllowed(false);
          //JOptionPane.showMessageDialog(this, "Issued Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           }
  
         }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
    }
    
    public void updatelib_returnbook() {
        
         try{
             //book_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
          
              
             Object[] param=new Object[]{new Date(),getFineCharge(),false,retn_id};
             new PreparedSentence(m_App.getSession()
                , "UPDATE lib_bookissue_retn SET return_date=?,due_charge=?,flag=? WHERE ID=?"
                , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.DOUBLE,Datas.BOOLEAN,Datas.STRING})).exec(param);
          //JOptionPane.showMessageDialog(this, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           
  
         }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
    }
    
    public double getFineCharge() throws BasicException{
        
         Date date = new Date();
         /*Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 10);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.HOUR,13);
        cal.set(Calendar.MINUTE,45);
        cal.set(Calendar.SECOND,52);
        date = cal.getTime();*/
        //System.out.println(myDate);
         //date.after(15-10-2015);
        //memid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField3.getText().toString());
        //book_id = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT ID FROM lib_bookmaster WHERE RefNo = ? and active=true and available_flag=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(jTextField1.getText());
        Object[] obj= (Object[])new  StaticSentence(m_App.getSession()
            , "select to_be_retn_dt from lib_bookissue_retn where id=? and flag=true"
            , new SerializerWriteBasic(new Datas[] {Datas.STRING})
            ,new SerializerReadBasic(new Datas[] {Datas.TIMESTAMP})).find(new Object[]{retn_id}); 
        
//Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "select ( cast() - cast(to_be_retn_dt as date) ) * 3 as fine from lib_bookissue_retn where book_id=? and mem_id=?",
                        //new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.STRING})).find(new Object[]{date, book_id.get(0).toString(),memid.get(0).toString()});
         //SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("dd-MM-yyyy:HH:mm:SS");
            //String date_to_string = dateformatyyyyMMdd.format((Date)obj[0]);
        Date date1 = null;
          if(obj!=null){
          date1=(Date)obj[0];
          }else{
              JOptionPane.showMessageDialog(this, "cannot return Book");
          }
        /*try {
            date2 = dateformatyyyyMMdd.parse(date_to_string);
          Calendar Cal = Calendar.getInstance();  
        Cal.setTime(date2);
        Cal.add(Calendar.DATE, 1);
        date2=Cal.getTime();
            //date2=DateUtil.addDays(date2, 1);
        } catch (ParseException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }*/
         int days=getDifferenceDays(date1,date);
         if(days>=0){
            fine=days*3; //calculating fine with rs.3 per day
         }else{
             fine=0;
         }
      
          return fine;
          
      }
        /*public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {  
        Calendar startCal;  
        Calendar endCal;  
        startCal = Calendar.getInstance();  
        startCal.setTime(startDate);
        //startCal.add(Calendar.DATE, 1);
        //startDate=startCal.getTime();
        //startCal.setTime(startDate);
        endCal = Calendar.getInstance();  
        endCal.setTime(endDate);  
        int workDays = 0;  
      
        //Return 0 if start and end are the same  
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {  
            return 0;  
        }  
      
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {  
            startCal.setTime(endDate);  
            endCal.setTime(startDate);  
        }  
      
        do {  
            startCal.add(Calendar.DAY_OF_MONTH, 1);  
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY   
           && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {  
                ++workDays;  
            }  
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());  
      
        return workDays;  
    }  */
   /*public void displayBill() {
        try {
            int i = jTable1.getSelectedRow();
            if (i >= 0 && i < blist.size()) {
                Lib_Billinfo b = blist.get(i);
                AppView app = LookupUtilityImpl.getInstance(null).getAppView();
                //praveen:checking bill is paid or not 
                
                    lib_BillPage billpage = lib_BillPage.getDialog(this, dlSales, null, b.getCustomer(),0);
                    billpage.init(b);
                    billpage.showDialog();
                   
                
            }
        } catch (BasicException e) {
            e.printStackTrace();
            new MessageInf(e).show(this);
        }
    }*/
    
    private void refreshModel() {
        try {
            jTextField1.setText(null);
            jTextField1.requestFocus();
            jTextField6.setText(null);
            jTable1.setRowSelectionAllowed(true);
            loadbook.clear();
            
            imodel = Lib_IssueTableModel.loadIssuedetails(m_App,memid,loadbook);
            jTable1.setModel(imodel.getTableModel1());
            jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            TableColumnModel cmodel=jTable1.getColumnModel();
            cmodel.getColumn(0).setPreferredWidth(50);
            cmodel.getColumn(1).setPreferredWidth(150);
            cmodel.getColumn(2).setPreferredWidth(200);
            cmodel.getColumn(3).setPreferredWidth(150);
            cmodel.getColumn(4).setPreferredWidth(100);
            cmodel.getColumn(5).setPreferredWidth(100);
            cmodel.getColumn(6).setPreferredWidth(60);
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  /* public List getIssuerules() throws BasicException{
          List<Object> issue_list = new ArrayList<Object>();
           issue_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_issuerules l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return issue_list;
      }*/
   
   private Date getdate() {
        Date dnow = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(dnow.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.MILLISECOND, 00);
        dnow.setTime(cal.getTimeInMillis());
        return dnow;
    }
   
   public  int getpidcty_days(){
           int norofdys=0;
           
        try {
              
              Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT p.no,p.type_ FROM periodicity p,lib_bookmaster b ,lib_issuerules l WHERE p.id=l.Periodicity and l.id =b.IssueRules and p.active=true and b.refno=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(jTextField1.getText());  
            
            if(obj1[1].toString().equals("Days")){
                norofdys=Integer.parseInt(obj1[0].toString());
            }
            else if(obj1[1].toString().equals("Months")){
                norofdys=Integer.parseInt(obj1[0].toString());
                norofdys=norofdys*30;
            }
            else{
                norofdys=Integer.parseInt(obj1[0].toString());
                norofdys=norofdys*365;
            }
          //period_no =Integer.parseInt(prd_list1.get(0).toString());
         //period_no =Integer.parseInt(obj2[0].toString());
            
        
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return norofdys;
      }
   
   private Date get_tobertn_dt(int days){
       Date dt=new Date();
       //dt = dateformatyyyyMMdd.parse(date_to_string);
        Calendar Cal = Calendar.getInstance();  
        Cal.setTime(dt);
        Cal.add(Calendar.DATE, days);
        dt=Cal.getTime();
       return dt;
   }
       
   public int getDifferenceDays(Date d1, Date d2) {
int daysdiff=0;
long diff = d2.getTime() - d1.getTime();
long diffDays = diff / (24 * 60 * 60 * 1000);
 daysdiff = (int) diffDays;

return daysdiff;
 }
    private int getFacilityId() {
        int fid = 0;
        List<Object> facid=new ArrayList<Object>();
        try {
           facid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT initcontrol FROM facility WHERE name like 'Library Subscription' and active=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
             //facid=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT initcontrol FROM facility WHERE name =? and active=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find("Library Subscription");
           fid=Integer.parseInt(facid.get(0).toString());
           
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueBooksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fid;
    }
    
     public void startCardReader() {
        try {
            String portNumber = m_App.getProperties().getProperty("card.portnumber");
            boolean cardAccessOnlyFlag = false;
            if (m_App.getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf(m_App.getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
            cr.setCardSwipeNotifier((CardSwipeNotifier) this);
            System.out.println(portNumber);
            //cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
     
     private void printTicket(String book_name,String receiptno, String cname, List<PaymentInfo> pinfo, Double amount, String skey) {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.LibraryReceipt");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", amount);
                script.put("name", book_name);
                script.put("cname", cname);
                script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
                script.put("receipt", receiptno);
                script.put("ckey", skey);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                ttp.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
    }

    @Override
    public void cardswiped(WaiterInfo waiter) {
        
    }

    @Override
    public void cardswiped(String custCard) {
        
        try {
            loadMemberDetails(custCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
