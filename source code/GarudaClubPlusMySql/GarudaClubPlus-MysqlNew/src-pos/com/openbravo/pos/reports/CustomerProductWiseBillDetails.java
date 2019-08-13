
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.panels.JProductFinder;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;


public class CustomerProductWiseBillDetails extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private DataLogicFacilities dmang;
    private CustomerInfo customerInfo;
    private String Contact;
    private DataLogicCustomers dlCustomers;
    private String pid;
    private DataLogicSales m_dlSales;
    private List<CustomerProductwiseTableModel.DetailBillInfo> CustDetailedList;
    private CustomerProductwiseTableModel CustomerProductwise_Table_Model;
    private List<String> WarehouseList = new ArrayList<String>();
    private ComboBoxValModel WarehouseBoxValModel;
    
    
    public CustomerProductWiseBillDetails() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        allCust_Radio = new javax.swing.JRadioButton();
        IndividualCust_Radio = new javax.swing.JRadioButton();
        customer_panel = new javax.swing.JPanel();
        memno_text = new javax.swing.JTextField();
        mname_text = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Warehousewise_Radio = new javax.swing.JRadioButton();
        findProd_Radio = new javax.swing.JRadioButton();
        product_Panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        month_radio = new javax.swing.JRadioButton();
        period_radio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        Month_cal = new javax.swing.JButton();
        todate_cal = new javax.swing.JButton();
        fromdate_cal = new javax.swing.JButton();
        warehouse_panel = new javax.swing.JPanel();
        warehouse_combo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        orderbyMemberNo_Radio = new javax.swing.JRadioButton();
        orderbyBillCrdate_radio = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("Customers / Productwise Detailed Report");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Member Details : ");

        allCust_Radio.setText("All");
        allCust_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                allCust_RadioItemStateChanged(evt);
            }
        });

        IndividualCust_Radio.setText("Individual");
        IndividualCust_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IndividualCust_RadioItemStateChanged(evt);
            }
        });

        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        mname_text.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                mname_textComponentAdded(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customer_panelLayout = new javax.swing.GroupLayout(customer_panel);
        customer_panel.setLayout(customer_panelLayout);
        customer_panelLayout.setHorizontalGroup(
            customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(customer_panelLayout.createSequentialGroup()
                        .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        customer_panelLayout.setVerticalGroup(
            customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customer_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(mname_text))
                .addContainerGap())
        );

        mname_text.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Product Details  : ");

        Warehousewise_Radio.setText("Warehousewise");
        Warehousewise_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Warehousewise_RadioItemStateChanged(evt);
            }
        });

        findProd_Radio.setText("Find Product");
        findProd_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                findProd_RadioItemStateChanged(evt);
            }
        });

        jLabel5.setText("Product Name : ");

        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout product_PanelLayout = new javax.swing.GroupLayout(product_Panel);
        product_Panel.setLayout(product_PanelLayout);
        product_PanelLayout.setHorizontalGroup(
            product_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(product_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        product_PanelLayout.setVerticalGroup(
            product_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(product_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(product_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField1.setEditable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Generate Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        month_radio.setText("Monthly ");
        month_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_radioItemStateChanged(evt);
            }
        });

        period_radio.setText("Periodly");
        period_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_radioItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("From :");

        jLabel7.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel7.setText("To:");

        Month_cal.setText("Month");
        Month_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calActionPerformed(evt);
            }
        });

        todate_cal.setText("To Date");
        todate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calActionPerformed(evt);
            }
        });

        fromdate_cal.setText("From Date");
        fromdate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calActionPerformed(evt);
            }
        });

        warehouse_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel4.setText("Select Warehouse :");

        javax.swing.GroupLayout warehouse_panelLayout = new javax.swing.GroupLayout(warehouse_panel);
        warehouse_panel.setLayout(warehouse_panelLayout);
        warehouse_panelLayout.setHorizontalGroup(
            warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, warehouse_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        warehouse_panelLayout.setVerticalGroup(
            warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        orderbyMemberNo_Radio.setText("Member No.  , Bill created date ");

        orderbyBillCrdate_radio.setText("Bill created date , Member No.");

        jLabel8.setText("Order by :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(customer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(allCust_Radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(IndividualCust_Radio))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Warehousewise_Radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(findProd_Radio))
                                    .addComponent(product_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(warehouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(month_radio)
                                .addGap(18, 18, 18)
                                .addComponent(period_radio))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderbyMemberNo_Radio)
                        .addGap(18, 18, 18)
                        .addComponent(orderbyBillCrdate_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(410, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(period_radio))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todate_cal))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(allCust_Radio)
                    .addComponent(IndividualCust_Radio))
                .addGap(18, 18, 18)
                .addComponent(customer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Warehousewise_Radio)
                    .addComponent(findProd_Radio))
                .addGap(18, 18, 18)
                .addComponent(warehouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(product_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderbyMemberNo_Radio)
                    .addComponent(orderbyBillCrdate_radio)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
 
    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno_text.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                    memno_text.setText(null);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno_text.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    mname_text.setText(obj[1].toString());
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mname_text.setText(null);
            customerInfo = null;

        }
    }//GEN-LAST:event_memno_textKeyPressed

    private void mname_textComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_mname_textComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_mname_textComponentAdded

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname_text.setText(customerInfo.toString());
                memno_text.setText(customerInfo.getSearchkey());
                Contact = customerInfo.getMobile();
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed
    ProductInfoExt productInfo_ext;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        productInfo_ext=JProductFinder.showMessage(this, m_dlSales );
        jTextField1.setText(productInfo_ext.getName());
        pid= productInfo_ext.getID(); //Akshatha : to fetch the product id
        
        
               

    }//GEN-LAST:event_jButton2ActionPerformed

    private void allCust_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_allCust_RadioItemStateChanged
        if(allCust_Radio.isSelected()){
            customer_panel.setVisible(false);
            customerInfo=null;
        }
        else{
            customer_panel.setVisible(true);
        }
    }//GEN-LAST:event_allCust_RadioItemStateChanged

    private void IndividualCust_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IndividualCust_RadioItemStateChanged
       if(IndividualCust_Radio.isSelected()){
           customer_panel.setVisible(true);
           memno_text.setText(null);
           mname_text.setText(null);
       }
       else{
           customer_panel.setVisible(false);
       }
    }//GEN-LAST:event_IndividualCust_RadioItemStateChanged

    private void Warehousewise_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Warehousewise_RadioItemStateChanged
       if(Warehousewise_Radio.isSelected()){
           
           product_Panel.setVisible(false);
           warehouse_combo.setSelectedIndex(-1);
           jTextField1.setText(null);
           warehouse_panel.setVisible(true);
       }
       else{
           warehouse_combo.setSelectedIndex(-1);
           warehouse_panel.setVisible(false);
       }
    }//GEN-LAST:event_Warehousewise_RadioItemStateChanged

    private void findProd_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_findProd_RadioItemStateChanged
        if(findProd_Radio.isSelected()){
            product_Panel.setVisible(true);
            warehouse_panel.setVisible(false);
            warehouse_combo.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_findProd_RadioItemStateChanged

    private void month_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_radioItemStateChanged
        if(month_radio.isSelected()){
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);

            fromdate_text.setText(null);
            todate_text.setText(null);
            
        }
        else{
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
            
            todate_text.setText(null);
            

        }
    }//GEN-LAST:event_month_radioItemStateChanged

    private void period_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_radioItemStateChanged
        if(period_radio.isSelected()){
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
            
            todate_text.setText(null);
            
        }
        else{
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);
            fromdate_text.setText(null);
            
            todate_text.setText(null);
            
        }
    }//GEN-LAST:event_period_radioItemStateChanged

    private void Month_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
           
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.add(Calendar.MONTH, 1);

            cal1.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());

            todate_text.setText(Formats.TIMESTAMP.formatValue(date));
           


        }
    }//GEN-LAST:event_Month_calActionPerformed

    private void todate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calActionPerformed
        Date date;
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                todate_text.setText(Formats.TIMESTAMP.formatValue(date));
               
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_todate_calActionPerformed

    private void fromdate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
           

            todate_text.setText(null);
          
        }
    }//GEN-LAST:event_fromdate_calActionPerformed
    String WarehouseName = "";
    
    int OrderFlag=0;
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
              if(allCust_Radio.isSelected() || customerInfo!=null){  
               
                
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(CustomerProductWiseBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                } 
                String CustomerID=null;
                
                if(orderbyMemberNo_Radio.isSelected()){
                    OrderFlag=0;
                }
                else{
                    OrderFlag=1;
                }
                
                
                
                // ************************************** ALL PRODUCTS **********************************************
                if(Warehousewise_Radio.isSelected()){
                   if(warehouse_combo.getSelectedIndex()!=-1){ 
                       WarehouseName= warehouse_combo.getSelectedItem().toString();
                       String WarehouseID=null;
                       
                       int custflag=0;
                        if(allCust_Radio.isSelected()){
                            custflag=1;
                        }
                        else{
                            CustomerID=customerInfo.getId();
                        }

                        
                        CustDetailedList= new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
                        
                        try {   
                            WarehouseID=getWarehouseIDByName(WarehouseName);
                            
                            if(custflag==1){
                                CustomerProductwise_Table_Model  = CustomerProductwiseTableModel.LoadBillDetails(m_App,WarehouseID,FrmDate,ToDate,OrderFlag);
                            }
                            else{
                                
                                 CustomerProductwise_Table_Model  = CustomerProductwiseTableModel.LoadBillDetailsForOneMember(m_App,WarehouseID,FrmDate,ToDate,CustomerID,OrderFlag);
                            }
                            
                            
                         } 

                         catch (BasicException ex) {
                           Logger.getLogger(CustomerProductWiseBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        CustDetailedList  =  (List<CustomerProductwiseTableModel.DetailBillInfo>) CustomerProductwise_Table_Model.getList();

                        if(CustDetailedList.size()>0){
                        DataSourceProvider data1 = new DataSourceProvider(CustDetailedList);
                        DataSourceForCustomerProductReport dsfc = new DataSourceForCustomerProductReport(CustDetailedList);
                        data1.setDataSource(dsfc);
                        Map reportparams = new HashMap();
                        reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                        reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                        String h = "( Bill details from  : "+fromdate_text.getText()+"  To: "+todate_text.getText()+" )";
                        reportparams.put("HEADING",h);
                        reportparams.put("WAREHOUSE",WarehouseName);
                        reportparams.put("date",new Date());
                        reportparams.put("TITLE"," Rooms Billed Details");


                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/customerProductDetailedReport.jrxml", reportparams, false, data1, true, null); 
                         
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "No records found ", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        
                   }
                   else{
                       JOptionPane.showMessageDialog(this, "Please select Warehouse ..!! ", "incomplete Form", JOptionPane.WARNING_MESSAGE); 
                   }
                    
                }
                
               
                
                // ********************************  PRODUCT WISE ***************************************************
                if(findProd_Radio.isSelected()){
                   int custflag=0;
                    if(allCust_Radio.isSelected()){
                        custflag=1;
                    }
                    else{
                        CustomerID=customerInfo.getId();
                    } 
                    if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0){
                    
                        
                        
                        
                         CustDetailedList= new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
                        
                        try { 
                            
                            String Wid = productInfo_ext.getWarehouse();
                            WarehouseName = getWarehouseNameByID(Wid);
                           
                            if(custflag==1){
                                CustomerProductwise_Table_Model  = CustomerProductwiseTableModel.LoadBillDetailsForPerticularProduct(m_App,pid,FrmDate,ToDate,OrderFlag);
                            }
                            else{
                                
                                 CustomerProductwise_Table_Model  = CustomerProductwiseTableModel.LoadBillDetailsForOneMemberByProductName(m_App,pid,FrmDate,ToDate,CustomerID,OrderFlag);
                            }
                            
                            
                         } 

                         catch (BasicException ex) {
                           Logger.getLogger(CustomerProductWiseBillDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        CustDetailedList  =  (List<CustomerProductwiseTableModel.DetailBillInfo>) CustomerProductwise_Table_Model.getList();

                        if(CustDetailedList.size()>0){
                        DataSourceProvider data1 = new DataSourceProvider(CustDetailedList);
                        DataSourceForCustomerProductReport dsfc = new DataSourceForCustomerProductReport(CustDetailedList);
                        data1.setDataSource(dsfc);
                        Map reportparams = new HashMap();
                        reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                        reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                        String h = "( Bill details from  : "+fromdate_text.getText()+"  To: "+todate_text.getText()+" )";
                        reportparams.put("HEADING",h);
                        reportparams.put("WAREHOUSE",WarehouseName);
                        reportparams.put("date",new Date());
                        reportparams.put("TITLE"," Rooms Billed Details");


                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/customerProductDetailedReport.jrxml", reportparams, false, data1, true, null); 
                         
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "No records found ", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        
                        
                        
                        
                        
                        
                        
                    
                    
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Please select Product ..!! ", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                
                
                
                
              }
              else{
                  JOptionPane.showMessageDialog(this, "Please select member ..!! ", "incomplete Form", JOptionPane.WARNING_MESSAGE);
              }
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }     
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton IndividualCust_Radio;
    private javax.swing.JButton Month_cal;
    private javax.swing.JRadioButton Warehousewise_Radio;
    private javax.swing.JRadioButton allCust_Radio;
    private javax.swing.JPanel customer_panel;
    private javax.swing.JRadioButton findProd_Radio;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField memno_text;
    private javax.swing.JTextField mname_text;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton orderbyBillCrdate_radio;
    private javax.swing.JRadioButton orderbyMemberNo_Radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JPanel product_Panel;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    private javax.swing.JComboBox warehouse_combo;
    private javax.swing.JPanel warehouse_panel;
    // End of variables declaration//GEN-END:variables

public String getTitle() {
       return "Customer/Productwise Detailed Report ";
    }

    public void activate() throws BasicException {
        ButtonGrp();
        reset();
        loaddata();
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
         
         
         dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
         m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }

    public Object getBean() {
        return this;
    }
    
    
    public void loaddata() throws BasicException{
        
       Warehousewise_Radio.setSelected(true);
       allCust_Radio.setSelected(true);
       
       WarehouseList = new ArrayList<String>();
       WarehouseList = GetWarehouseList(m_App);
       WarehouseBoxValModel=new ComboBoxValModel(WarehouseList);
       warehouse_combo.setModel(WarehouseBoxValModel);
       warehouse_combo.setSelectedIndex(-1);
       orderbyMemberNo_Radio.setSelected(true);
    }
    
    public void ButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(Warehousewise_Radio);
        
        bg.add(findProd_Radio);
        
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(allCust_Radio);
        bg1.add(IndividualCust_Radio);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(period_radio);
        bg2.add(month_radio);
        
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(orderbyMemberNo_Radio);
        bg3.add(orderbyBillCrdate_radio);
        
    }
    
    public void reset(){
       Warehousewise_Radio.setSelected(true);
       allCust_Radio.setSelected(true);
        memno_text.setText(null);
        mname_text.setText(null);
        warehouse_combo.setSelectedIndex(-1);
        jTextField1.setText(null);
        pid=null;
        customerInfo=null;
        month_radio.setSelected(true);
    }

    
    public String getWarehouseIDByName(String WarehouseName) throws BasicException{
         Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT ID FROM LOCATIONS WHERE NAME =? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(WarehouseName);
         
         
         
         if(obj7!=null){
             return obj7[0].toString();
         }
         else{
             return null;
         }
         
    }

    
     public List GetWarehouseList (AppView app){
         List<Object> warehouse_List = new ArrayList<Object>();
         try {
            warehouse_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT name from locations order by name ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
           return warehouse_List;
       }  
    
     
      public String getWarehouseNameByID(String Wid) throws BasicException{
         Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT NAME FROM LOCATIONS WHERE ID =? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Wid);
         
         
         
         if(obj7!=null){
             return obj7[0].toString();
         }
         else{
             return null;
         }
         
    }
}
