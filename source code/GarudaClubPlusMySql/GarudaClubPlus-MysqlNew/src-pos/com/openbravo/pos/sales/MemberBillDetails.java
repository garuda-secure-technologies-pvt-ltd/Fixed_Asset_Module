/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.MemDebtBillingTableModel;
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
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.reports.CustomerWiseSalesNew;
import static com.openbravo.pos.reports.JParamsLocation.locationname;
import com.openbravo.pos.reports.QTDetailsReportTableModel;
import com.openbravo.pos.sales.MemBillModel1.QtData;
import com.openbravo.pos.sales.restaurant.BillList;
import com.openbravo.pos.sales.restaurant.DebtBillList;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 *
 * @author USER
 */
public class MemberBillDetails extends javax.swing.JPanel implements  JPanelView, BeanFactoryApp, EditorCreator  {
    
    private Object bean;
   
    private AppView m_App;
    private DataLogicFacilities dmang;
  //  private DataLogicSales m_dlSales;
    private DataLogicCustomers dlCustomers = null;
    private DataLogicSales dlSales = null;
    private CustomerInfoExt customer;
    private CustomerInfoExt notes;
    private String initiator;
    private boolean flag = true;
    private CardReader cr;
    private CustomerInfo customerInfo;
    private SentenceList m_sentlocations;
    private ComboBoxValModel m_LocationsModel;
    public static String locationname;
    private String location;
     private MemBillTableModel fxd_table1;
     private MemBillModel fxd_table2;
     private DataLogicSystem dlsystem;
       private DataLogicReceipts dlReceipts;
        private  static String  cusid1;
        private BillLogic dlBill;
        
      private AppView app;
   private DataLogicFacilities dlfac;
    private MemBillQt1  dlfac1table;
       private  MemBillModel biimdl; 
       Date FmDate = null;
        Date ToDate = null;
        private List<String> WarehouseList = new ArrayList<String>();
        private List<MemBillTableModel.QtData> QtInfo_list;
        private MemBillTableModel QTDetailsReport_Table_Model;
        private DefaultTableModel dataModel;
private List<MemBillTableModel.QtData> productWiseList;
private List<MemBillTableModel.QtData> data2;
Date sdate = null;
Date edate = null;
     String id;
          String id1;
  //  private DataLogicSales dlSales;

    /**
     * Creates new customizer MemberBillDetails
     */
    public MemberBillDetails() {
        initComponents();
    }
    @Override
      public void init(AppView app) throws BeanFactoryException {
         m_App = app;
          dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
       // dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
   
       // session = AppViewConnection.createSession(m_props, cinfo.getUrl(), false);
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlReceipts = (DataLogicReceipts) app.getBean("com.openbravo.pos.sales.DataLogicReceipts");
          m_sentlocations = dlSales.getLocationsList();
        m_LocationsModel = new ComboBoxValModel();  
          jLabel8.setVisible(false);
       
  //  jTable1.setModel(new DefaultTableModel());
    
      
      }
    
    public void setObject(Object bean) {
        this.bean = bean;
    }
  
                                              

     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button3 = new java.awt.Button();
        button4 = new java.awt.Button();
        jButton6 = new javax.swing.JButton();
        All = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jbutton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

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

        jLabel1.setText("Mem Name");
        jLabel1.setToolTipText("");

        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Mem No.");

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

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("All Members");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Specified Member");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("From Date");

        jLabel8.setText("To Date");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Created Date", "Bill No.", "Mem No.", "Mem Name", " Amount", "Reciept No.", "WareHouse"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        button3.setLabel("Bill Detail");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setLabel("Qt Detail");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        All.setSelected(true);
        All.setText("All");
        All.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllActionPerformed(evt);
            }
        });

        jRadioButton6.setText("Select Warehouse");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jbutton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Warehouse:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(366, 366, 366))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(20, 20, 20)
                                        .addComponent(All)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(250, 250, 250))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(23, 23, 23)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addComponent(jRadioButton2)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)))
                                        .addGap(67, 67, 67)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(All)
                            .addComponent(jRadioButton6)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
          String card = jTextField3.getText();
        
        if (card.length() > 0) {

            try {
                 Object[] obj = dmang.getMamberbycard(card);
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setSearchkey(obj[1].toString());
                    customerInfo.setName(obj[2].toString());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    jTextField5.setText(obj[1].toString());
                    jTextField1.setText(obj[2].toString());
                    load();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
       
        //akshatha:to read a card from card reader without port num
    }//GEN-LAST:event_jTextField3ActionPerformed
public long startSec = 0;
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
      char c = evt.getKeyChar();
        if(jTextField3.getText()!=null){
            int length = jTextField3.getText().trim().length();
            if(length==1){
                startSec = System.currentTimeMillis();
            }
            else if(length>1){
                long Currsec = System.currentTimeMillis();
                long diff = Currsec-startSec;
                if(diff>750){
                    JOptionPane.showMessageDialog(this, "Do not use keyboard. Please swipe card.");
                    jTextField3.setText(null);
                     System.out.println("Time Taken : "+diff);
                }
                if(c==KeyEvent.VK_ENTER){
                    System.out.println("Time Taken : "+diff);
                }
            }
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
         JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
       finder.setVisible(true);
        CustomerInfo customerInfo = finder.getSelectedCustomer();
          //CustomerInfo customerInfo1 = finder.getSelectedCustomer();
         String cusid = customerInfo.getId();
         cusid1 =cusid;
            System.out.println("cusid to ID:::::"+cusid);
          if (customerInfo != null)  
            try {
             customer = dlSales.loadCustomerExt(customerInfo.getId());
              jTextField1.setText(customerInfo.toString());
              jTextField5.setText(customerInfo.getSearchkey());
          } catch (BasicException e) {
             MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
              msg.show(this);
          }

    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
        String custoid;

        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            String cust = jTextField5.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ? AND VISIBLE = TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());                      // #CHANGE BY AAKASH... ON 6TH DEC 2013
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                    cusid1=custoid;
                    jTextField1.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      

    }//GEN-LAST:event_jTextField5KeyPressed

    
     private void loadMemberDetails(String card) {
       
       card = cr.getData();
         String cust = jTextField3.getText();
        if (card.length() > 0) {

            try {
                 Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?  UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                     customerInfo = new CustomerInfo(obj[0].toString());
                   customerInfo.setSearchkey(obj[1].toString());
                    customerInfo.setName(obj[2].toString());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    jTextField5.setText(obj[1].toString());
                    jTextField1.setText(obj[2].toString());
                    load();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(cust.length()>0){
            try {
                 Object[] obj = dmang.getMamberbycard(cust);
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                     customerInfo = new CustomerInfo(obj[0].toString());
                   customerInfo.setSearchkey(obj[1].toString());
                    customerInfo.setName(obj[2].toString());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    jTextField5.setText(obj[1].toString());
                    jTextField1.setText(obj[2].toString());
                    load();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
    } 
     
      private void load() {
        try {
            
            String accid = dmang.getCustomerAccountByID(customerInfo.getId());
        } catch (BasicException ex) {
            Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
     //   jTable1.setModel(new DefaultTableModel());
      }
    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
           
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        
        try {
            // TODO add your handling code here:
            displayBillDetail1();
        } catch (BasicException ex) {
            Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

           
    }//GEN-LAST:event_button3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

       /////////////////////////////////////////////////////////////////////////////////////////////////////
               if(jRadioButton2.isSelected()){
               if(jTextField1.getText()!=null && jTextField1.getText().length()>0){
                    if(jTextField5.getText()!=null && jTextField5.getText().length()>0){
                               
                        try {
                            String id;
                            String search = jTextField5.getText();
                            Object obj = new StaticSentence(m_App.getSession(), "SELECT  id FROM customers WHERE searchkey=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(search);
                            if(obj!=null){
                                id = obj.toString();
                            }
                            else{
                                id="";
                            }
                         
                                if(jTextField8.getText()!=null && jTextField8.getText().length()>0){
                                    if(jTextField9.getText()!=null && jTextField9.getText().length()>0){
                                        Date sdate = (Date) Formats.TIMESTAMP.parseValue(jTextField8.getText());
                                        Date edate = (Date) Formats.TIMESTAMP.parseValue(jTextField9.getText());
                                        if(All.isSelected()){
                                            // Date edate = (Date) Formats.TIMESTAMP.parseValue(jTextField9.getText());
                                            fxd_table1 = MemBillTableModel.GetQt44(m_App, id,sdate,edate);
                                            jTable1.setModel(fxd_table1.getTableModel());
                                            jTable1.setVisible(true);
                                            
                                        }
                                    }
                                    else{
                       JOptionPane.showMessageDialog(this, "Please Select To Date", null, JOptionPane.OK_OPTION);
                   }
                                    
                                }
                                else{
                       JOptionPane.showMessageDialog(this, "Please Select From Date", null, JOptionPane.OK_OPTION);
                }
                           //}    
                        } catch (BasicException ex) {
                            Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
              }
         
               }  
         }
       //////////////////////////////////////////////////////////////////////////////////////////////////////
               if(jRadioButton2.isSelected()){
                   if(jTextField1.getText()!=null && jTextField1.getText().length()>0){
                   if(jTextField5.getText()!=null && jTextField5.getText().length()>0){
               
                       try {
                           String id;
                           
                           String search = jTextField5.getText();
                           Object obj = new StaticSentence(m_App.getSession(), "SELECT  id FROM customers WHERE searchkey=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(search);
                           if(obj!=null){
                               id = obj.toString();
                           }
                           else{
                               id="";
                           }
//                           if(period.isSelected()){
                               if(jTextField8.getText()!=null && jTextField8.getText().length()>0){
                                   if(jTextField9.getText()!=null && jTextField9.getText().length()>0){
                                       Date sdate = (Date) Formats.TIMESTAMP.parseValue(jTextField8.getText());
                                       Date edate = (Date) Formats.TIMESTAMP.parseValue(jTextField9.getText());
                                       if(jRadioButton6.isSelected()){
                                           if(jComboBox1.getSelectedIndex()!=-1){
                                               
                                               String id1;
                                               String WarehouseName = jComboBox1.getSelectedItem().toString();
                                               Object obj1;
                                               
                                               obj1 = new StaticSentence(m_App.getSession(), "SELECT  id FROM locations WHERE name=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(WarehouseName);
                                               
                                               if(obj1!=null){
                                                   id1 = obj1.toString();
                                               }
                                               else{
                                                   id1="";
                                               }
                                               
                                               // Date edate = (Date) Formats.TIMESTAMP.parseValue(jTextField9.getText());
                                               fxd_table1 = MemBillTableModel.GetQt33(m_App, id,sdate,edate,id1);
                                               jTable1.setModel(fxd_table1.getTableModel());
                                               jTable1.setVisible(true);
                                               
                                               
                                           }
                                           
                                       }
                                   }
                                    
                                else{
                       JOptionPane.showMessageDialog(this, "Please Select  To Date", null, JOptionPane.OK_OPTION);
                }
                               }
                       
                                else{
                       JOptionPane.showMessageDialog(this, "Please Select From Date", null, JOptionPane.OK_OPTION);
                }
                                    
                                }
                         //  }      
                        catch (BasicException ex) {
                           Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                       }
         
               }  
         }
}
       //////////////////////////////////////////////////////////////////////////////////////////////
       if(jRadioButton1.isSelected()){
        if(All.isSelected()){
                                    if(jTextField8.getText()!=null && jTextField8.getText().length()>0){
                                    if(jTextField9.getText()!=null && jTextField9.getText().length()>0){
                                       try {
                                           Date sdate = (Date) Formats.TIMESTAMP.parseValue(jTextField8.getText());
                                           Date edate = (Date) Formats.TIMESTAMP.parseValue(jTextField9.getText());
                                          
                                               fxd_table1 = MemBillTableModel.GetQt333(m_App, sdate,edate);
                                               jTable1.setModel(fxd_table1.getTableModel());
                                               jTable1.setVisible(true);
                                                                    } catch (BasicException ex) {
                                           Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                   
                                    }
                                      else{
                       JOptionPane.showMessageDialog(this, "Please Select To Date", null, JOptionPane.OK_OPTION);
                }
                                    
                                    }       
                                else{
                       JOptionPane.showMessageDialog(this, "Please Select From Date", null, JOptionPane.OK_OPTION);
                }
       }
       
       }
/////////////////////////////////////////////////////////////////////////////////////
     if(jRadioButton1.isSelected()){
//      
                                            if(jRadioButton6.isSelected()){
                                                if(jComboBox1.getSelectedIndex()!=-1){
                                                      
                                                    try {
                                                        String id1;
                                                        String WarehouseName = jComboBox1.getSelectedItem().toString();
                                                        Object obj1;
                                                        
                                                        obj1 = new StaticSentence(m_App.getSession(), "SELECT  id FROM locations WHERE name=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(WarehouseName);
                                                        
                                                        if(obj1!=null){
                                                            id1 = obj1.toString();
                                                        }
                                                        else{
                                                            id1="";
                                                        }
                                                        if(jTextField8.getText()!=null && jTextField8.getText().length()>0){
                                                            if(jTextField9.getText()!=null && jTextField9.getText().length()>0){
                                                                Date sdate = (Date) Formats.TIMESTAMP.parseValue(jTextField8.getText());
                                                                Date edate = (Date) Formats.TIMESTAMP.parseValue(jTextField9.getText());
                                                                
                                                                //  String WarehouseName = jComboBox1.getSelectedItem().toString();
                                                                fxd_table1 = MemBillTableModel.GetQt444(m_App, sdate,edate, id1);
                                                                jTable1.setModel(fxd_table1.getTableModel());
                                                                jTable1.setVisible(true);
                                                            }
                                                               else{
                       JOptionPane.showMessageDialog(this, "Please Select To Date", null, JOptionPane.OK_OPTION);
                }
                                                                
                                                        }  
                                                           else{
                       JOptionPane.showMessageDialog(this, "Please Select From Date", null, JOptionPane.OK_OPTION);
                }
                                                    
                                                    } catch (BasicException ex) {
                                                        Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                    }
                                   
                                    
                                }
                             
           
       
       }
        if(jRadioButton6.isSelected()){
//                       jComboBox3.setVisible(true);
                       if(jComboBox1.getSelectedIndex()!=-1){
                           try {
                               String id;
                               String WarehouseName = jComboBox1.getSelectedItem().toString();
                               Object obj;
                               
                               obj = new StaticSentence(m_App.getSession(), "SELECT  id FROM locations WHERE name=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(WarehouseName);
                               
                               if(obj!=null){
                                   id = obj.toString();
                               }
                               else{
                                   id="";
                               }
                             //  fxd_table1 = MemBillTableModel.GetQt55(m_App, id);
                               
                               jTable1.setModel(fxd_table1.getTableModel());
                               jTable1.setVisible(true);
                           } catch (BasicException ex) {
                               Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
        
       ////////////////////////////////////////////////////////////////////////////////////////////////
        }  
    }//GEN-LAST:event_jButton6ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:

          try {
            // TODO add your handling code here:
            displayBillDetail2();
        } catch (BasicException ex) {
            Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_button4ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            jLabel1.setVisible(false);
            jTextField1.setVisible(false);
            jButton3.setVisible(false);
            jLabel4.setVisible(false);
            jTextField5.setVisible(false);
          
            /////////////////////////////
               jLabel7.setVisible(true);
            jTextField8.setVisible(true);
            
            jLabel8.setVisible(true);
            jTextField9.setVisible(true);
            jbutton1.setVisible(true);
            jButton2.setVisible(true);
            /////////////////////
//            jTextField9.setText(null);
//            jTextField8.setText(null);
          
        }
        
            jTextField9.setText(null);
            jTextField8.setText(null);
            All.setSelected(true);
             jRadioButton6.setSelected(false);
          DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
             model.setRowCount(0);
            
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
            if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
            jLabel1.setVisible(true);
            jTextField1.setVisible(true);
            jButton3.setVisible(true);
            jLabel4.setVisible(true);
            jTextField5.setVisible(true);
          
        
           ////////////////////////////////////////////////
        
          
            
            jLabel7.setVisible(true);
            jTextField8.setVisible(true);
            
        
            jLabel8.setVisible(true);
            jTextField9.setVisible(true);
            jbutton1.setVisible(true);
            jButton2.setVisible(true);
         
          
        }
                jTextField1.setText(null);
             jTextField9.setText(null);
             jTextField8.setText(null);
            jTextField5.setText(null);
              All.setSelected(true);
             jRadioButton6.setSelected(false);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
             model.setRowCount(0);
       
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void AllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllActionPerformed
        // TODO add your handling code here:
        if(All.isSelected()){
            jRadioButton6.setSelected(false);
          
            jComboBox1.setVisible(false);
        }
    }//GEN-LAST:event_AllActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton6.isSelected()){
            All.setSelected(false);
           
            jComboBox1.setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton1ActionPerformed
        // TODO add your handling code here:
          Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jbutton1.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                jTextField8.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jbutton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jButton2.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                jTextField9.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed
 // public long startSec = 0;
    
     public  void displayBillDetail2() throws BasicException {
//       int QT_Bill_Flag = 0;
//       Date sdate = null;
//       Date edate = null;
        if (jTable1.getSelectedRow() != -1) {
             int row = jTable1.getSelectedRow();
         //   if (jTable1.getSelectedRow() < fxd_table1.getSize()) {
                MemBillTableModel.QtData    showdata = fxd_table1.getList().get(row);
       
         MemBillQt dbillList = MemBillQt.getDialog(this, dlSales, m_App, false,showdata.getId());
           dbillList.init(showdata);
        dbillList.showDialog();
       // dbillList.showDialog();
        }
       
//        MemBillQt1 dbillList = MemBillQt1.getDialog(this,biimdl,app,false);
//        dbillList.showDialog();
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton All;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton jbutton1;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
      return "Bill and Qt Wise Details";       
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activate() throws BasicException {
    
        loadData();
        startCardReader();
         List a = m_sentlocations.list();
           addFirst(a);
        m_LocationsModel = new ComboBoxValModel(a);
        m_LocationsModel.setSelectedFirst();
        jComboBox1.setModel(m_LocationsModel);
//    
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
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }
   private void loadData() throws BasicException {
       //////////////////////////////////////////////////////////////////////////////
            jLabel1.setVisible(false);
            jTextField1.setVisible(false);
            jButton3.setVisible(false);
            jLabel4.setVisible(false);
            jTextField5.setVisible(false);
           
            jComboBox1.setVisible(false);
            /////////////////////////////
           
             jLabel7.setVisible(true);
            jTextField8.setVisible(true);
            
        
            jLabel8.setVisible(true);
            jTextField9.setVisible(true);
            jbutton1.setVisible(true);
            jButton2.setVisible(true);
          ////////////////////////////////////////
             jTextField5.setText(null);
             jTextField1.setText(null);
             jTextField9.setText(null);
            jTextField8.setText(null);
           jRadioButton1.setSelected(true);
           jRadioButton2.setSelected(false);
            All.setSelected(true);
           jRadioButton6.setSelected(false);
           //////////////////////////////////////////////////
           
           
            fxd_table1 = MemBillTableModel.GetQt44(m_App, id,sdate,edate);
         jTable1.setModel(fxd_table1.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         
         fxd_table1 = MemBillTableModel.GetQt33(m_App, id,sdate,edate,id1);
       jTable1.setModel(fxd_table1.getTableModel());
        jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
         fxd_table1 = MemBillTableModel.GetQt333(m_App, sdate,edate);
       jTable1.setModel(fxd_table1.getTableModel());
       jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
       fxd_table1 = MemBillTableModel.GetQt444(m_App, sdate,edate, id1);
        jTable1.setModel(fxd_table1.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
             TableColumnModel cmodel = jTable1.getColumnModel();
            cmodel.getColumn(0).setPreferredWidth(100);
            cmodel.getColumn(1).setPreferredWidth(50);
            cmodel.getColumn(2).setPreferredWidth(50);
            cmodel.getColumn(3).setPreferredWidth(100);
            cmodel.getColumn(4).setPreferredWidth(50);
            cmodel.getColumn(5).setPreferredWidth(50);
            cmodel.getColumn(6).setPreferredWidth(100);
            jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
        customerInfo = null;
        //jButton5.setEnabled(false);
        jTable1.setVisible(false);
     
        jTextField5.setText(null);
        jTextField1.setText(null);
        jTextField3.setText(null);
        
        
          WarehouseList =  getWareHouseList(m_App);
        m_LocationsModel =new ComboBoxValModel(WarehouseList);
       jComboBox1.setModel(m_LocationsModel);
       
        //jCheckBox1.setSelected(false);
        try {
            fxd_table1 = MemBillTableModel.GetQt(m_App, 0);
        } catch (BasicException ex) {
            Logger.getLogger(MemberBillDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
            jTable1.setModel(fxd_table1.getTableModel());
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                 //akshatha:to read a card from card reader without port num
                String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
                if (cardReaderPortName == null || cardReaderPortName.trim().length() == 0) {
                    jTextField3.requestFocus();
                    jTextField3.setEditable(true);
                   
                 //   jLabel8.setVisible(true);
                } else {
                    jTextField5.requestFocus();
                    jTextField3.setEditable(false);
                   
                    jLabel8.setVisible(false);
                }
            }
        });
        

   jTable1.setModel(new DefaultTableModel());
   
    }

    
  public void addActionListener(ActionListener l) {
        jComboBox1.addActionListener(l);
    }
    
    public void removeActionListener(ActionListener l) {
        jComboBox1.removeActionListener(l);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getWareHouseList(AppView app ) throws BasicException{
          List<Object> WarehouseList = new ArrayList<Object>();
           WarehouseList  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM LOCATIONS  ORDER BY NAME ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return WarehouseList;
      }
    public String getWareHouseIdByName(AppView app , String ID) throws BasicException{
          Object o = null;
           o  =  new StaticSentence(app.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME =?   ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(ID);
          
          if(o!=null){
              return o.toString();
          }
          else{
              return null; 
          }
         
      }
    
    private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(All);
        bg1.add(jRadioButton6);
        
      
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
   
    
       
    @Override
    public boolean deactivate() {
   return true;        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JComponent getComponent() {
  return this;     
  // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

   // @Override
    public Object getBean() {
       return this;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void execute(String card) {
        card = String.valueOf(m_App.getReader().getVariance() + Double.valueOf(card));

    }
    
        //return this;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
     public Object createValue() throws BasicException {
        LocationInfo linfo=(LocationInfo)m_LocationsModel.getSelectedItem();
        if(linfo!=null){
            locationname=linfo.getDisplayName();    
        }
            
        return new Object[] {
            m_LocationsModel.getSelectedKey() == null ||  m_LocationsModel.getSelectedKey() == "All"? QBFCompareEnum.COMP_NONE : QBFCompareEnum.COMP_EQUALS, 
            m_LocationsModel.getSelectedKey()
        };

    } 
   
    
  
  
    
//    public Object createValue() throws BasicException {
//      return this;
//
//    }  

    private void addFirst(List a) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public  void displayBillDetail1() throws BasicException {
    //   int QT_Bill_Flag = 0;
        if (jTable1.getSelectedRow() != -1) {
             int row = jTable1.getSelectedRow();
         //   if (jTable1.getSelectedRow() < fxd_table1.getSize()) {
                MemBillTableModel.QtData    showdata = fxd_table1.getList().get(row);
       
         MemBillQt1 dbillList = MemBillQt1.getDialog(this, dlSales, m_App, false, showdata.getId());
          
           dbillList.init(showdata);
           dbillList.showDialog();
           
       
       
        }
       // }
   }
   
    public void cardswiped(String custCard) {
        try {
            loadMemberDetails(custCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
       
}
