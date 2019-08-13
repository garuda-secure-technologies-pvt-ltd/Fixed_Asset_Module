/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.DataSourceProvider1;
import com.openbravo.pos.clubmang.DataSourceProvider2;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.clubmang.JasperReportNew1;
import com.openbravo.pos.clubmang.JasperReportNew2;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.CardSwipeNotifier;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.printer.TicketParser;
import static com.openbravo.pos.reports.JParamsLocation.locationname;
import com.openbravo.pos.sales.BillLogic;
import java.awt.Color;
import java.awt.Component;
//import static com.openbravo.pos.reports.productnew.locationname;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author User
 */
public class ProductNew1 extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {
    //    private Object bean;
     private SentenceList m_sentlocations;
    private ComboBoxValModel m_LocationsModel;
    private String location;
    private DataLogicSales dlSales;
    private AppView m_App;
    private ProductWiseTotalModel pmodel;
    public static String locationname;
    DataSourceProductTotal ds;
      private BillLogic dlBill;
       private CustomerInfoExt customer;
       private List<ProductWiseTotalModel.productTotalSales> productWiseList;
        private List<ProductWiseTotalModel.productTotalSales1> productWiseList1;
       private List<String> WarehouseList = new ArrayList<String>();
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        private DataLogicCustomers dlCustomers;
         private DataLogicFacilities dmang;
           private DataLogicSales m_dlSales;
            private DataLogicSystem dlsystem;
             private TicketParser ttp;
             Date FmDate = null;
        Date ToDate = null;
//       private ComboBoxValModel WarehouseListModel ; 
    private Object jLabel1;

    /**
     * Creates new customizer ProductNew1
     */
    public ProductNew1() {
        initComponents();
         jRadioButton1.setSelected(true);

    }
    private void loadData() throws BasicException {
//         jButton1.setVisible(true);
//         jTextField2.setEnabled(false);
//         jButton2.setVisible(false);
//         jTextField2.setVisible(false);
//         jTextArea1.setVisible(false);

        pmodel = ProductWiseTotalModel.loadInstance(m_App, FmDate, ToDate);
                            jTable1.setModel(pmodel.getTableModel());
                            jTable1.setRowSelectionAllowed(true);
                            jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // jLabel2.setVisible(false);
        
           reset();
             warehouse_panel.setVisible(false);
             jTable1.setVisible(false);
            
          jRadioButton3.setVisible(false);
           jRadioButton4.setVisible(false); 
          
           jRadioButton1.setSelected(false);
             
//             .setVisible(false);
              WarehouseList =  getWareHouseList(m_App);
        m_LocationsModel =new ComboBoxValModel(WarehouseList);
       warehouse_combo.setModel(m_LocationsModel);
       
       java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
              //akshatha:to read a card from card reader without port num
                  String portNumber = m_App.getProperties().getProperty("card.portnumber");
                if (portNumber == null || portNumber.trim().length() == 0) {
//                    cardno.requestFocus();
//                     cardno.setEditable(true);
//                    cardno.setVisible(true);
//                    jButton10.setVisible(true);
//                    jLabel9.setVisible(true);
                }else
                {
//                   memno.requestFocus();
//                   cardno.setEditable(false);
//                    cardno.setVisible(false);
//                    jButton10.setVisible(false);
//                     jLabel9.setVisible(true);
                    
                }

            }
        });
//              List a = m_sentlocations.list();
//                 LocationInfo l1=new LocationInfo();
//        l1.setID("All");
//        l1.setName("All");
//        a.add(0, l1); 
       jTable1.setModel(new DefaultTableModel());
     }
//    
//    public void setObject(Object bean) {
//        this.bean = bean;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent)c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                    if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                        jc.setBackground(Color.cyan);
                    }
                    else {
                        jc.setBackground(Color.white);
                    }
                    if(isCellSelected(rowIndex, vColIndex))
                    jc.setBackground(Color.lightGray);
                }
                return c;
            }};
            jButton2 = new javax.swing.JButton();
            jRadioButton1 = new javax.swing.JRadioButton();
            jRadioButton2 = new javax.swing.JRadioButton();
            warehouse_panel = new javax.swing.JPanel();
            jLabel8 = new javax.swing.JLabel();
            warehouse_combo = new javax.swing.JComboBox();
            jLabel2 = new javax.swing.JLabel();
            jTextField4 = new javax.swing.JTextField();
            jLabel4 = new javax.swing.JLabel();
            jTextField5 = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();
            jTextField6 = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            jTextField7 = new javax.swing.JTextField();
            jRadioButton3 = new javax.swing.JRadioButton();
            jRadioButton4 = new javax.swing.JRadioButton();

            setLayout(new java.awt.BorderLayout());

            jPanel1.setLayout(null);

            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/launch.png"))); // NOI18N
            jButton3.setText("Execute Report");
            jButton3.setToolTipText("");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });
            jPanel1.add(jButton3);
            jButton3.setBounds(180, 210, 160, 30);

            jTextField1.setToolTipText("");
            jTextField1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField1ActionPerformed(evt);
                }
            });
            jPanel1.add(jTextField1);
            jTextField1.setBounds(140, 50, 160, 20);

            jTextField2.setToolTipText("");
            jTextField2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField2ActionPerformed(evt);
                }
            });
            jPanel1.add(jTextField2);
            jTextField2.setBounds(140, 10, 160, 20);

            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
            jButton1.setToolTipText("");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            jPanel1.add(jButton1);
            jButton1.setBounds(310, 10, 30, 20);

            jLabel3.setText("Start:");
            jPanel1.add(jLabel3);
            jLabel3.setBounds(70, 10, 34, 20);

            jLabel5.setText("      To:");
            jPanel1.add(jLabel5);
            jLabel5.setBounds(60, 50, 70, 14);

            jLabel11.setText("Warehouse:");
            jPanel1.add(jLabel11);
            jLabel11.setBounds(30, 74, 90, 30);

            jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
            jButton5.setToolTipText("");
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });
            jPanel1.add(jButton5);
            jButton5.setBounds(310, 50, 30, 20);

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null}
                },
                new String [] {
                    "HSN_Code", "Product Name", "Qty", "Rate", "Amount", "Tax1", "Tax2", "Tax3", "Total", "Warehouse"
                }
            ));
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable1MouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(jTable1);

            jPanel1.add(jScrollPane1);
            jScrollPane1.setBounds(10, 250, 850, 270);

            jButton2.setText("Print");
            jButton2.setToolTipText("");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });
            jPanel1.add(jButton2);
            jButton2.setBounds(460, 210, 90, 30);

            jRadioButton1.setText("All");
            jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jRadioButton1ItemStateChanged(evt);
                }
            });
            jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButton1ActionPerformed(evt);
                }
            });
            jPanel1.add(jRadioButton1);
            jRadioButton1.setBounds(130, 80, 60, 23);

            jRadioButton2.setText("Select Warehouse");
            jRadioButton2.setToolTipText("");
            jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButton2ActionPerformed(evt);
                }
            });
            jPanel1.add(jRadioButton2);
            jRadioButton2.setBounds(240, 80, 140, 23);

            jLabel8.setText("WareHouse : ");

            warehouse_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
            warehouse_combo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    warehouse_comboActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout warehouse_panelLayout = new javax.swing.GroupLayout(warehouse_panel);
            warehouse_panel.setLayout(warehouse_panelLayout);
            warehouse_panelLayout.setHorizontalGroup(
                warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(warehouse_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel8)
                    .addGap(18, 18, 18)
                    .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            warehouse_panelLayout.setVerticalGroup(
                warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(warehouse_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel1.add(warehouse_panel);
            warehouse_panel.setBounds(390, 70, 410, 50);

            jLabel2.setText("Tax1 Total:");
            jPanel1.add(jLabel2);
            jLabel2.setBounds(90, 530, 70, 30);

            jTextField4.setToolTipText("");
            jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
            jTextField4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField4ActionPerformed(evt);
                }
            });
            jPanel1.add(jTextField4);
            jTextField4.setBounds(170, 530, 90, 30);
            jTextField4.setEditable(false);

            jLabel4.setText("Tax2 Total:");
            jLabel4.setToolTipText("");
            jPanel1.add(jLabel4);
            jLabel4.setBounds(270, 530, 80, 30);

            jTextField5.setEditable(false);
            jTextField5.setToolTipText("");
            jTextField5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField5ActionPerformed(evt);
                }
            });
            jPanel1.add(jTextField5);
            jTextField5.setBounds(350, 530, 80, 30);

            jLabel6.setText("Tax3 Total:");
            jPanel1.add(jLabel6);
            jLabel6.setBounds(440, 530, 70, 30);

            jTextField6.setEditable(false);
            jTextField6.setToolTipText("");
            jTextField6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField6ActionPerformed(evt);
                }
            });
            jPanel1.add(jTextField6);
            jTextField6.setBounds(520, 530, 80, 30);

            jLabel7.setText("Grand Total:");
            jPanel1.add(jLabel7);
            jLabel7.setBounds(610, 530, 80, 30);

            jTextField7.setEditable(false);
            jTextField7.setToolTipText("");
            jPanel1.add(jTextField7);
            jTextField7.setBounds(700, 530, 110, 30);

            jRadioButton3.setSelected(true);
            jRadioButton3.setText("Category_product wise");
            jRadioButton3.setToolTipText("");
            jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButton3ActionPerformed(evt);
                }
            });
            jPanel1.add(jRadioButton3);
            jRadioButton3.setBounds(130, 120, 170, 23);

            jRadioButton4.setText("HSN code wise");
            jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButton4ActionPerformed(evt);
                }
            });
            jPanel1.add(jRadioButton4);
            jRadioButton4.setBounds(350, 120, 170, 23);

            add(jPanel1, java.awt.BorderLayout.CENTER);
        }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTable1.setVisible(true);
       
//        Map reportparams = new HashMap();
//         reportparams.put("rname", JParamsLocation.locationname);
//  
//       
if(jTextField2.getText()!=null && jTextField1.getText()!=null && jTextField2.getText().trim().length()>0 && jTextField1.getText().trim().length()>0){
  // select member   
       
       
        try {
                 FmDate = (Date) Formats.TIMESTAMP.parseValue(jTextField2.getText());
                 ToDate = (Date) Formats.TIMESTAMP.parseValue(jTextField1.getText());
        } catch (BasicException ex) {
                Logger.getLogger(CustomerWiseSalesNew.class.getName()).log(Level.SEVERE, null, ex);
            }

       
       
       
          
              
                    if(jRadioButton1.isSelected()){
                        if(jRadioButton3.isSelected())
                        {
                            
                         productWiseList = new ArrayList<ProductWiseTotalModel.productTotalSales>();
                        try{
                            
                            pmodel = ProductWiseTotalModel.loadInstance(m_App, FmDate, ToDate);
                            jTable1.setModel(pmodel.getTableModel());
                              jTable1.setRowSelectionAllowed(true);
                            jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
  renderer.setHorizontalAlignment(jLabel11.RIGHT);
  
////table.getColumnModel().getColumn(???).setCellRenderer(rightRenderer);
jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(3).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(4).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(5).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(6).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(8).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(9).setCellRenderer(renderer);
//jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);


//          jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(20);
                cmodel.getColumn(1).setPreferredWidth(150);
                cmodel.getColumn(2).setPreferredWidth(30);
                cmodel.getColumn(3).setPreferredWidth(50);
                cmodel.getColumn(4).setPreferredWidth(50);
                cmodel.getColumn(5).setPreferredWidth(50);
                cmodel.getColumn(6).setPreferredWidth(50);
                  cmodel.getColumn(7).setPreferredWidth(50);
                    cmodel.getColumn(8).setPreferredWidth(50);
                       cmodel.getColumn(9).setPreferredWidth(150);
//                       DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
//jTable1.getColumn("QUANTITY").setCellRenderer( rightRenderer );
                         }
                        catch(BasicException ex){
                             Logger.getLogger(ProductWiseTotalModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
             if(jRadioButton4.isSelected())
             {
                   productWiseList1 = new ArrayList<ProductWiseTotalModel.productTotalSales1>();
                        try{
                            
                            pmodel = ProductWiseTotalModel.loadInstance2(m_App, FmDate, ToDate);
                            jTable1.setModel(pmodel.getTableModel1());
                              jTable1.setRowSelectionAllowed(true);
          DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
  renderer.setHorizontalAlignment(jLabel11.RIGHT);
  
////table.getColumnModel().getColumn(???).setCellRenderer(rightRenderer);
jTable1.getColumnModel().getColumn(1).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(3).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(4).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(5).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(6).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(8).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(9).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(10).setCellRenderer(renderer);
//jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);                  
jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  
//          jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(20);
                cmodel.getColumn(1).setPreferredWidth(70);
                cmodel.getColumn(2).setPreferredWidth(150);
                cmodel.getColumn(3).setPreferredWidth(30);
                cmodel.getColumn(4).setPreferredWidth(50);
                cmodel.getColumn(5).setPreferredWidth(50);
                cmodel.getColumn(6).setPreferredWidth(50);
                cmodel.getColumn(7).setPreferredWidth(50);
                  cmodel.getColumn(8).setPreferredWidth(50);
                    cmodel.getColumn(9).setPreferredWidth(50);
                       cmodel.getColumn(10).setPreferredWidth(150);
                        Double Tax11Total1 = 0.00;
                     Double Tax21Total1 = 0.00;
                     Double Tax31Total1= 0.00;
                     Double GrandTotal1 = 0.00;
          
          if(pmodel.GetSize1()>0){
              for(int i=0;i<pmodel.GetSize1();i++){
                  
                  Double x = pmodel.getProductlist1().get(i).getTax11();
                  Tax11Total1=Tax11Total1+x;
              }
          }
          
          if(pmodel.GetSize1()>0){
              for(int i=0;i<pmodel.GetSize1();i++){
                  Double x = pmodel.getProductlist1().get(i).getTax21();
                  Tax21Total1=Tax21Total1+x;
              }
          }
          if(pmodel.GetSize1()>0){
              for(int i=0;i<pmodel.GetSize1();i++){
                  Double x = pmodel.getProductlist1().get(i).getTax31();
                  Tax31Total1=Tax31Total1+x;
              }
          }
          if(pmodel.GetSize1()>0){
              for(int i=0;i<pmodel.GetSize1();i++){
                  Double x = pmodel.getProductlist1().get(i).getTotal1();
                  GrandTotal1=GrandTotal1+x;
              }
          }
          
          jTextField4.setText(decimalFormat.format(Tax11Total1));
          jTextField5.setText(decimalFormat.format(Tax21Total1));
          jTextField6.setText(decimalFormat.format(Tax31Total1));
           jTextField7.setText(decimalFormat.format(GrandTotal1));
                         }
                        catch(BasicException ex){
                             Logger.getLogger(ProductWiseTotalModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 
             }
        
//                        CustomerWiseSaleList = (List<CuetomerwiseSalesTableModel.CustSalesInfo>) CuetomerwiseSales_Table_Model.getCustomerWiseList();
//                        
//                         DataSourceProvider data1 = new DataSourceProvider(CustomerWiseSaleList);
//                         DataSourceForCustomerWiseSaleNew dsfc = new DataSourceForCustomerWiseSaleNew(CustomerWiseSaleList);
//                         data1.setDataSource(dsfc);
//                         Map reportparams = new HashMap();
//                       //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
//                       //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
//                       //  String ReportHeader = "Customerwise Sales ";
//                         String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
//                         reportparams.put("RHEADER",RPH);
//                         reportparams.put("WAREHOUSE","All");
//                       //  reportparams.put("date",new Date());
//                         
//                        
//                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CustomerWiseSalesNew.jrxml", reportparams, false, data1, true, null); 
//                         
                        



                    }
//                     else        {
//                            JOptionPane.showMessageDialog(this, "Please select Proper option ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
//                        }
                 else  if(jRadioButton2.isSelected()){
//                       jComboBox3.setVisible(true);
                       if(warehouse_combo.getSelectedIndex()!=-1){
                           String WarehouseName = warehouse_combo.getSelectedItem().toString();
                           String WID = null;
                                try{
                                    WID=getWareHouseIdByName(m_App, WarehouseName);
                                }
                                catch(BasicException e){

                                }
                                
                                 
                           
                            productWiseList = new ArrayList<ProductWiseTotalModel.productTotalSales>();
                                try{

                                    pmodel = ProductWiseTotalModel.loadInstance1(m_App, FmDate, ToDate , WID);
                                    jTable1.setModel(pmodel.getTableModel());
                                      jTable1.setRowSelectionAllowed(true);
   jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
  renderer.setHorizontalAlignment(jLabel11.RIGHT);
  
////table.getColumnModel().getColumn(???).setCellRenderer(rightRenderer);
jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(3).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(4).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(5).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(6).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(8).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(9).setCellRenderer(renderer);
//jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
//      jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(20);
                cmodel.getColumn(1).setPreferredWidth(150);
                cmodel.getColumn(2).setPreferredWidth(30);
                cmodel.getColumn(3).setPreferredWidth(50);
                cmodel.getColumn(4).setPreferredWidth(50);
                cmodel.getColumn(5).setPreferredWidth(50);
                cmodel.getColumn(6).setPreferredWidth(50);
                  cmodel.getColumn(7).setPreferredWidth(50);
                    cmodel.getColumn(8).setPreferredWidth(50);
                       cmodel.getColumn(9).setPreferredWidth(150);
                                 }
                                catch(BasicException ex){
                                     Logger.getLogger(ProductWiseTotalModel.class.getName()).log(Level.SEVERE, null, ex);
                                }
//                                CustomerWiseSaleList = (List<CuetomerwiseSalesTableModel.CustSalesInfo>) CuetomerwiseSales_Table_Model.getCustomerWiseList();
//
//                                 DataSourceProvider data1 = new DataSourceProvider(CustomerWiseSaleList);
//                                 DataSourceForCustomerWiseSaleNew dsfc = new DataSourceForCustomerWiseSaleNew(CustomerWiseSaleList);
//                                 data1.setDataSource(dsfc);
//                                 Map reportparams = new HashMap();
//                               //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
//                               //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
//                                 
//                                  String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
//                                 reportparams.put("RHEADER",RPH);
//                                 reportparams.put("WAREHOUSE",WarehouseName);
//                               //  reportparams.put("date",new Date());
//
//
//                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CustomerWiseSalesNew.jrxml", reportparams, false, data1, true, null); 


                           
                           
                           

                        }
                       
                       }
                    else        {
                            JOptionPane.showMessageDialog(this, "Please select proper option ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
                     Double Tax1Total = 0.00;
                     Double Tax2Total = 0.00;
                     Double Tax3Total = 0.00;
                     Double GrandTotal = 0.00;
          
          if(pmodel.GetSize()>0){
              for(int i=0;i<pmodel.GetSize();i++){
                  
                  Double x = pmodel.getProductlist().get(i).getTax1();
                  Tax1Total=Tax1Total+x;
              }
          }
          
          if(pmodel.GetSize()>0){
              for(int i=0;i<pmodel.GetSize();i++){
                  Double x = pmodel.getProductlist().get(i).getTax2();
                  Tax2Total=Tax2Total+x;
              }
          }
          if(pmodel.GetSize()>0){
              for(int i=0;i<pmodel.GetSize();i++){
                  Double x = pmodel.getProductlist().get(i).getTax3();
                  Tax3Total=Tax3Total+x;
              }
          }
          if(pmodel.GetSize()>0){
              for(int i=0;i<pmodel.GetSize();i++){
                  Double x = pmodel.getProductlist().get(i).getTotal();
                  GrandTotal=GrandTotal+x;
              }
          }
          
          jTextField4.setText(decimalFormat.format(Tax1Total));
          jTextField5.setText(decimalFormat.format(Tax2Total));
          jTextField6.setText(decimalFormat.format(Tax3Total));
           jTextField7.setText(decimalFormat.format(GrandTotal));
            
         
          
         
      }
          else        {
                            JOptionPane.showMessageDialog(this, "Please select Date ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTextField2.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

         Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField1.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTextField1.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        try {
//            if (jTextField2.getText().length() > 0 && jTextField1.getText().length() > 0 && location != null) {
//                generateReport(getDate(jTextField2.getText()), getDate(jTextField1.getText()), location);
//            }
//            else {
//                JOptionPane.showMessageDialog(this, "select fromdate,todate and warehouse", "incomplte form", JOptionPane.WARNING_MESSAGE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
if(jTextField2.getText()!=null && jTextField1.getText()!=null && jTextField2.getText().trim().length()>0 && jTextField1.getText().trim().length()>0){
  // select member   
       
       Date FmDate = null;
        Date ToDate = null;
        try {
                 FmDate = (Date) Formats.TIMESTAMP.parseValue(jTextField2.getText());
                 ToDate = (Date) Formats.TIMESTAMP.parseValue(jTextField1.getText());
        } catch (BasicException ex) {
                Logger.getLogger(CustomerWiseSalesNew.class.getName()).log(Level.SEVERE, null, ex);
            }

       
       
       
          
              
                    if(jRadioButton1.isSelected()){
                        if(jRadioButton3.isSelected())
                        {
                             productWiseList = new ArrayList<ProductWiseTotalModel.productTotalSales>();
                        try{
                            
                            pmodel = ProductWiseTotalModel.loadInstance(m_App, FmDate, ToDate);
                            jTable1.setModel(pmodel.getTableModel());
         jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
          DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
  renderer.setHorizontalAlignment(jLabel11.RIGHT);
  
////table.getColumnModel().getColumn(???).setCellRenderer(rightRenderer);
jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(3).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(4).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(5).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(6).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(8).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(9).setCellRenderer(renderer);
//jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
          TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(20);
                cmodel.getColumn(1).setPreferredWidth(150);
                cmodel.getColumn(2).setPreferredWidth(30);
                cmodel.getColumn(3).setPreferredWidth(50);
                cmodel.getColumn(4).setPreferredWidth(50);
                cmodel.getColumn(5).setPreferredWidth(50);
                cmodel.getColumn(6).setPreferredWidth(50);
                  cmodel.getColumn(7).setPreferredWidth(50);
                    cmodel.getColumn(8).setPreferredWidth(50);
                       cmodel.getColumn(9).setPreferredWidth(150);
                         }
                        catch(BasicException ex){
                             Logger.getLogger(ProductWiseTotalModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
 productWiseList = (List<ProductWiseTotalModel.productTotalSales>) pmodel.getProductlist();

//                                 DataSourceProvider data1 = new DataSourceProvider( productWiseList);
//                                 DataSourceProductTotal dsfc = new DataSourceProductTotal( productWiseList);
                                // data1.setDataSource(dsfc);
                                  DataSourceProvider1 data1 = new DataSourceProvider1(pmodel.getProductlist());
        DataSourceProductTotal ds = new DataSourceProductTotal(pmodel.getProductlist());
        data1.setDataSource1(ds);
                                 
                                 Map reportparams = new HashMap();
                                 reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                 reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                 
                                  String RPH = "  From  : "+jTextField2.getText()+ "  To: "+jTextField1.getText();
                                 reportparams.put("RHEADER",RPH);
                                 reportparams.put("WAREHOUSE","All");
           try {
               //                                 reportparams.put("date",new Date());
               reportparams.put("startdate", getDate(jTextField2.getText()));
           } catch (BasicException ex) {
               Logger.getLogger(ProductNew1.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               reportparams.put("enddate", getDate(jTextField1.getText()));
           } catch (BasicException ex) {
               Logger.getLogger(ProductNew1.class.getName()).log(Level.SEVERE, null, ex);
           }


                                JasperPrint jp = JasperReportNew1.runReport(m_App, "./reports/com/openbravo/reports/classic2.jrxml", reportparams, false, data1, true, null); 



                        }
                        if(jRadioButton4.isSelected())
                        {
                             productWiseList1 = new ArrayList<ProductWiseTotalModel.productTotalSales1>();
                        try{
                            
                            pmodel = ProductWiseTotalModel.loadInstance2(m_App, FmDate, ToDate);
                            jTable1.setModel(pmodel.getTableModel1());
         jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
          DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
  renderer.setHorizontalAlignment(jLabel11.RIGHT);
  
////table.getColumnModel().getColumn(???).setCellRenderer(rightRenderer);
jTable1.getColumnModel().getColumn(1).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(3).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(4).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(5).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(6).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(8).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(9).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(10).setCellRenderer(renderer);
//jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
          TableColumnModel cmodel = jTable1.getColumnModel();
               cmodel.getColumn(0).setPreferredWidth(20);
                cmodel.getColumn(1).setPreferredWidth(70);
                cmodel.getColumn(2).setPreferredWidth(150);
                cmodel.getColumn(3).setPreferredWidth(30);
                cmodel.getColumn(4).setPreferredWidth(50);
                cmodel.getColumn(5).setPreferredWidth(50);
                cmodel.getColumn(6).setPreferredWidth(50);
                cmodel.getColumn(7).setPreferredWidth(50);
                  cmodel.getColumn(8).setPreferredWidth(50);
                    cmodel.getColumn(9).setPreferredWidth(50);
                       cmodel.getColumn(10).setPreferredWidth(150);
                         }
                        catch(BasicException ex){
                             Logger.getLogger(ProductWiseTotalModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
 productWiseList1 = (List<ProductWiseTotalModel.productTotalSales1>) pmodel.getProductlist1();

//                                 DataSourceProvider data1 = new DataSourceProvider( productWiseList);
//                                 DataSourceProductTotal dsfc = new DataSourceProductTotal( productWiseList);
                                // data1.setDataSource(dsfc);
                                  DataSourceProvider2 data1 = new DataSourceProvider2(pmodel.getProductlist1());
        DataSourceProductTotal1 ds = new DataSourceProductTotal1(pmodel.getProductlist1());
        data1.setDataSource2(ds);
                                 
                                 Map reportparams = new HashMap();
                                 reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                 reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                 
                                  String RPH = "  From  : "+jTextField2.getText()+ "  To: "+jTextField1.getText();
                                 reportparams.put("RHEADER",RPH);
                                 reportparams.put("WAREHOUSE","All");
           try {
               //                                 reportparams.put("date",new Date());
               reportparams.put("startdate", getDate(jTextField2.getText()));
           } catch (BasicException ex) {
               Logger.getLogger(ProductNew1.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               reportparams.put("enddate", getDate(jTextField1.getText()));
           } catch (BasicException ex) {
               Logger.getLogger(ProductNew1.class.getName()).log(Level.SEVERE, null, ex);
           }


                                JasperPrint jp = JasperReportNew2.runReport(m_App, "./reports/com/openbravo/reports/classic4.jrxml", reportparams, false, data1, true, null); 



                        }

                        

                    }
                    else if(jRadioButton2.isSelected()){
//                       jComboBox3.setVisible(true);
                       if(warehouse_combo.getSelectedIndex()!=-1){

                           String WarehouseName = warehouse_combo.getSelectedItem().toString();
                           String WID = null;
                                try{
                                    WID=getWareHouseIdByName(m_App, WarehouseName);
                                }
                                catch(BasicException e){

                                }
                                
                                 
                           
                            productWiseList = new ArrayList<ProductWiseTotalModel.productTotalSales>();
                                try{

                                    pmodel = ProductWiseTotalModel.loadInstance1(m_App, FmDate, ToDate , WID);
                                    jTable1.setModel(pmodel.getTableModel());
         jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
          DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
  renderer.setHorizontalAlignment(jLabel11.RIGHT);
  
////table.getColumnModel().getColumn(???).setCellRenderer(rightRenderer);
jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(3).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(4).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(5).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(6).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(8).setCellRenderer(renderer);
jTable1.getColumnModel().getColumn(9).setCellRenderer(renderer);
//jTable1.getColumnModel().getColumn(2).setCellRenderer(renderer);
          TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(20);
                cmodel.getColumn(1).setPreferredWidth(150);
                cmodel.getColumn(2).setPreferredWidth(30);
                cmodel.getColumn(3).setPreferredWidth(50);
                cmodel.getColumn(4).setPreferredWidth(50);
                cmodel.getColumn(5).setPreferredWidth(50);
                cmodel.getColumn(6).setPreferredWidth(50);
                  cmodel.getColumn(7).setPreferredWidth(50);
                    cmodel.getColumn(8).setPreferredWidth(50);
                       cmodel.getColumn(9).setPreferredWidth(150);
                                 }
                                catch(BasicException ex){
                                     Logger.getLogger(ProductWiseTotalModel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                productWiseList = (List<ProductWiseTotalModel.productTotalSales>) pmodel.getProductlist();

                                 DataSourceProvider1 data1 = new DataSourceProvider1( productWiseList);
                                 DataSourceProductTotal ds = new DataSourceProductTotal( productWiseList);
                                 data1.setDataSource1(ds);
                                 Map reportparams = new HashMap();
                                 reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                 reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                 
                                  String RPH = "  From  : "+jTextField2.getText()+ "  To: "+jTextField1.getText();
                                 reportparams.put("RHEADER",RPH);
                                 reportparams.put("WAREHOUSE",WarehouseName);
//                                 reportparams.put("date",new Date());
 try {
              
               reportparams.put("startdate", getDate(jTextField2.getText()));
           } catch (BasicException ex) {
               Logger.getLogger(ProductNew1.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               reportparams.put("enddate", getDate(jTextField1.getText()));
           } catch (BasicException ex) {
               Logger.getLogger(ProductNew1.class.getName()).log(Level.SEVERE, null, ex);
           }


                                JasperPrint jp = JasperReportNew1.runReport(m_App, "./reports/com/openbravo/reports/classic2.jrxml", reportparams, false, data1, true, null); 


                           
                           
                           

                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Please select Warehouse ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
                    }
           }
else{
                            JOptionPane.showMessageDialog(this, "Please select Date ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }

        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
         if(jRadioButton1.isSelected()){
         
           warehouse_panel.setVisible(false);
             jRadioButton3.setVisible(true);
           jRadioButton4.setVisible(true); 
           
       }
       else{
           
           warehouse_panel.setVisible(false);
           
       }
        
        
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void warehouse_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouse_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_warehouse_comboActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton1.isSelected()){
             jRadioButton2.setSelected(false);
             jRadioButton3.setSelected(true);
             jRadioButton4.setSelected(false);
//             warehouse_panel.setVisible(false);
         }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton2.isSelected()){
             jRadioButton1.setSelected(false);
              jRadioButton3.setVisible(false);
           jRadioButton4.setVisible(false); 
             warehouse_panel.setVisible(true);
         }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
//         createAndShowUI();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton3.isSelected()){
             jRadioButton4.setSelected(false);
         }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton4.isSelected()){
             jRadioButton3.setSelected(false);
         }
    }//GEN-LAST:event_jRadioButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JComboBox warehouse_combo;
    private javax.swing.JPanel warehouse_panel;
    // End of variables declaration//GEN-END:variables


public String getTitle() {
        return "ProductwiseReport";
    }
public void activate() throws BasicException {
      
        List a = m_sentlocations.list();
//        LocationInfo l1=new LocationInfo();
//        l1.setID("All");
//        l1.setName("All");
//        a.add(0, l1);
       addFirst(a);
        m_LocationsModel = new ComboBoxValModel(a);
        m_LocationsModel.setSelectedFirst();
        warehouse_combo.setModel(m_LocationsModel);
         loadData();
//        List a = m_sentlocations.list();
//        LocationInfo l1=new LocationInfo();
//        l1.setID("All");
//        l1.setName("All");
//        a.add(0, l1);
////        addFirst(a);
//        m_LocationsModel = new ComboBoxValModel(a);
//        m_LocationsModel.setSelectedFirst();
//        jComboBox3.setModel(m_LocationsModel);  
    }
public boolean deactivate() {
        return true;
    }
 public JComponent getComponent() {
        return this;
    }
 public Component getComponent1() {
        return this;
    }

public void init(AppView app) throws BeanFactoryException {
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
//         dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
//        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
//        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
//        dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
//        ttp = new TicketParser(app.getDeviceTicket(), dlsystem);       
        // El modelo de locales
         m_sentlocations = dlSales.getLocationsList();
        m_LocationsModel = new ComboBoxValModel();   
                   m_App = app;
                   

    }
  public Object getBean() {
        return this;
    }
  private Date getDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        d.setTime(cal.getTimeInMillis());
        return d;
    }
      public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING});
    }
    protected void addFirst(List a) {
        // do nothing
    }
    
    public void addActionListener(ActionListener l) {
          warehouse_combo.addActionListener(l);
    }
    
    public void removeActionListener(ActionListener l) {
          warehouse_combo.removeActionListener(l);
    }
     
    
    public Object createValue() throws BasicException {
        LocationInfo linfo=(LocationInfo)m_LocationsModel.getSelectedItem();
        if(linfo!=null){
            locationname=linfo.getDisplayName();    
        }
            return new Object[] {
            m_LocationsModel.getSelectedKey() == null||m_LocationsModel.getSelectedKey() == "All" ? QBFCompareEnum.COMP_NONE : QBFCompareEnum.COMP_EQUALS, 
            m_LocationsModel.getSelectedKey()
        };

    }    
//    public void generateReport(Date from, Date to, String location) throws BasicException {
//        pmodel = new ProductWiseTotalModel();
//        Object[] values = new Object[]{from, to, location,from, to, location};
//        pmodel = pmodel.loadInstance(m_App, values);
//        launch(pmodel.getProductlist());
//    }
//     public void generateReport1(Date from, Date to) throws BasicException {
//          if(jRadioButton1.isSelected()){
//        pmodel = new ProductWiseTotalModel();
//        Object[] values = new Object[]{from, to,from, to};
//        pmodel = pmodel.loadInstance(m_App, values);
//         jTable1.setModel(pmodel.getTableModel());
//          jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//          TableColumnModel cmodel = jTable1.getColumnModel();
//                cmodel.getColumn(0).setPreferredWidth(30);
//                cmodel.getColumn(1).setPreferredWidth(100);
//                cmodel.getColumn(2).setPreferredWidth(100);
//                cmodel.getColumn(3).setPreferredWidth(100);
//                cmodel.getColumn(4).setPreferredWidth(100);
//                cmodel.getColumn(5).setPreferredWidth(100);
//                cmodel.getColumn(6).setPreferredWidth(70);
          
        
//        launch(pmodel.getProductlist());
//    }
//     }
//      public void generateReport2(Date from, Date to,String location) throws BasicException {
//          if(jRadioButton1.isSelected()){
//        pmodel = new ProductWiseTotalModel();
//        Object[] values = new Object[]{from, to, location, from, to, location};
//        pmodel = pmodel.loadInstance1(m_App, values);
//         jTable1.setModel(pmodel.getTableModel());
//          jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//          TableColumnModel cmodel = jTable1.getColumnModel();
//                cmodel.getColumn(0).setPreferredWidth(30);
//                cmodel.getColumn(1).setPreferredWidth(100);
//                cmodel.getColumn(2).setPreferredWidth(100);
//                cmodel.getColumn(3).setPreferredWidth(100);
//                cmodel.getColumn(4).setPreferredWidth(100);
//                cmodel.getColumn(5).setPreferredWidth(100);
//                cmodel.getColumn(6).setPreferredWidth(70);
//          
//        
////        launch(pmodel.getProductlist());
//    }
//     }
//    private void launch(List<ProductWiseTotalModel.productTotalSales> list) throws BasicException {
//        Map reportparams = new HashMap();
//        reportparams.put("companyName", m_App.getSession().getCompanyName());
//        reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
//        reportparams.put("startdate", getDate(jTextField2.getText()));
//        reportparams.put("enddate", getDate(jTextField1.getText()));
//        DataSourceProvider data1 = new DataSourceProvider(list);
//        DataSourceProductTotal ds = new DataSourceProductTotal(list);
////      data1.setDataSource(ds);
//        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/productwise1.jrxml", reportparams, false, data1, true, null);
//
//    }

//  private void displayProductList() {
//
//       // customer = dlSales.loadCustomerExt(TOOL_TIP_TEXT_KEY)
//        BillList productlist =ExecuteReport.getDialog(this, dlSales, dlBill, customer);
//        productlist.showDialog();
//    }

//    @Override
//    public JComponent getComponent() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(jRadioButton1);
        bg1.add(jRadioButton2);
        
      
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
public List getWareHouseList(AppView app ) throws BasicException{
          List<Object> WarehouseList = new ArrayList<Object>();
           WarehouseList  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM LOCATIONS  ORDER BY NAME ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return WarehouseList;
      }
    
     public void reset(){
        
        
        
        jTextField2.setText(null);
        jTextField1.setText(null);
     jTextField4.setText(null);
     jTextField5.setText(null);
     jTextField6.setText(null);
     jTextField7.setText(null);
        warehouse_combo.setSelectedIndex(-1);
        jRadioButton1.setSelected(true);
        warehouse_panel.setVisible(false);
     
   
    }
 private class ProductListModel extends AbstractListModel {

        private java.util.List product;

        public ProductListModel(java.util.List product) {
            this.product = product;
        }

        public int getSize() {
            return product.size();
        }

        public Object getElementAt(int i) {
            return product.get(i);
        }

        public void remove(int i) {
            product.remove(i);
        }
    }


// private void createAndShowUI() {
//    JFrame frame = new JFrame();
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////    initComponents(frame);
//    frame.pack();
//    frame.setVisible(true);
//    jTable1.selectAll();
//}




}
