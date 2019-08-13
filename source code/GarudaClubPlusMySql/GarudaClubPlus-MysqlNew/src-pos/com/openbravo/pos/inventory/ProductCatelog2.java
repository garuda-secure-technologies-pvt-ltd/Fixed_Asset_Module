/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.Booking.CheckInTableModel;
import com.openbravo.pos.Booking.GuestRoomBillModel;
import com.openbravo.pos.Booking.GuestRoomTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;
import javax.swing.ButtonGroup;    
import java.util.ArrayList;   
import com.openbravo.data.gui.ComboBoxValModel;
import java.util.List;    
 import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SerializerWriteString;
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
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;   
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;    
    
    
/**
 *
 * @author dev3
 */
public class ProductCatelog2 extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    
    private AppView m_App;
    private ComboBoxValModel WarehouseWise_Model ; 
    private ComboBoxValModel CategoryWiseModel ; 
    private List<String> WarehouseList;
    private List<String> CategoryList;
    private List<ProductCatelog2TableModel.ProdCatelogInfo> ProdCatelog2_List;
    private ProductCatelog2TableModel ProductCatelog2_Table_Model;
    
    
    public ProductCatelog2() {
        initComponents();
    }
  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        warehouseWise_radio = new javax.swing.JRadioButton();
        CategoryWise_radio = new javax.swing.JRadioButton();
        generate_btn = new javax.swing.JButton();
        warehouseWise_combo = new javax.swing.JComboBox();
        CategoryWise_Combo = new javax.swing.JComboBox();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 15, 244));
        jLabel1.setText("Current Stock with Value");

        warehouseWise_radio.setForeground(new java.awt.Color(110, 14, 14));
        warehouseWise_radio.setText("Warehouse wise ");
        warehouseWise_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                warehouseWise_radioItemStateChanged(evt);
            }
        });

        CategoryWise_radio.setForeground(new java.awt.Color(134, 19, 19));
        CategoryWise_radio.setText("Category Wise");
        CategoryWise_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CategoryWise_radioItemStateChanged(evt);
            }
        });

        generate_btn.setText("Generate Report ");
        generate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_btnActionPerformed(evt);
            }
        });

        warehouseWise_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CategoryWise_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warehouseWise_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(warehouseWise_radio)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(generate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CategoryWise_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CategoryWise_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warehouseWise_radio)
                    .addComponent(CategoryWise_radio))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warehouseWise_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CategoryWise_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(generate_btn)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        CategoryWise_radio.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void generate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_btnActionPerformed
       if(warehouseWise_radio.isSelected()){
          if(warehouseWise_combo.getSelectedIndex()!=-1){  
           
              
              String LocationName = warehouseWise_combo.getSelectedItem().toString();
              String LocationID =  getLocationIdByName(m_App, LocationName);
              
              ProdCatelog2_List = new ArrayList<ProductCatelog2TableModel.ProdCatelogInfo>();
              
                try {
                      ProductCatelog2_Table_Model  = ProductCatelog2TableModel.LoadProdCatelogWithStock(m_App , LocationID );
                    } 

                 catch (BasicException ex) {
                         Logger.getLogger(ProductCatelog2.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 ProdCatelog2_List  =  (List<ProductCatelog2TableModel.ProdCatelogInfo>) ProductCatelog2_Table_Model.getProdCatelogList();
                         
               DataSourceProvider data1 = new DataSourceProvider(ProdCatelog2_List);
               DataSourceForProdCatelog2 dsfc = new DataSourceForProdCatelog2(ProdCatelog2_List);
               data1.setDataSource(dsfc);
               Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
               reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
               String RPH = "Report Header";
               reportparams.put("ReportHeader",RPH);
               reportparams.put("date",new Date());
               
                        
               JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/currInventoryRate.jrxml", reportparams, false, data1, true, null); 
                         
              
              
              
              
           
           
          }
          else{
              JOptionPane.showMessageDialog(this, "Select Warehouse ...!! ", "incomplete Form", JOptionPane.WARNING_MESSAGE);
          }
          
          
       }
       
       
       
       
       
       
       if(CategoryWise_radio.isSelected()){
           
           
           
           
           
           
           
       }
    }//GEN-LAST:event_generate_btnActionPerformed

    private void warehouseWise_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_warehouseWise_radioItemStateChanged
      if(warehouseWise_radio.isSelected()==true){
            warehouseWise_combo.setVisible(true);
            CategoryWise_Combo.setVisible(false);
        }
        else{
            warehouseWise_combo.setVisible(false);
            CategoryWise_Combo.setVisible(true);
        }
      warehouseWise_combo.setSelectedIndex(-1);
      CategoryWise_Combo.setSelectedIndex(-1);
    }//GEN-LAST:event_warehouseWise_radioItemStateChanged

    private void CategoryWise_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CategoryWise_radioItemStateChanged
        if(CategoryWise_radio.isSelected()==true){
            warehouseWise_combo.setVisible(false);
            CategoryWise_Combo.setVisible(true);
        }
        else{
            warehouseWise_combo.setVisible(true);
            CategoryWise_Combo.setVisible(false);
        }
      warehouseWise_combo.setSelectedIndex(-1);
      CategoryWise_Combo.setSelectedIndex(-1);
    }//GEN-LAST:event_CategoryWise_radioItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CategoryWise_Combo;
    private javax.swing.JRadioButton CategoryWise_radio;
    private javax.swing.JButton generate_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox warehouseWise_combo;
    private javax.swing.JRadioButton warehouseWise_radio;
    // End of variables declaration//GEN-END:variables


 public String getTitle() {
         return "";
    }

    public void activate() throws BasicException {
       
        ButtonGrp();
        Loaddata();
        
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
       
    }

    public Object getBean() {
       return this;
    }
    

    public void ButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(warehouseWise_radio);
        bg.add(CategoryWise_radio);
    }

     public List getWarehouselist(AppView app ){
         
         List<Object> warehouselist = new ArrayList<Object>();
         
         try {
            warehouselist  = (List<Object>) new StaticSentence(app.getSession(), "SELECT Name from locations order by name",SerializerWriteString.INSTANCE    , SerializerReadString.INSTANCE).list();
       
         } catch (BasicException ex) {
            Logger.getLogger(ProductCatelog2.class.getName()).log(Level.SEVERE, null, ex);
        }
         return warehouselist;
     }
     
     
     public void Loaddata(){
         
         warehouseWise_radio.setSelected(true);
         warehouseWise_combo.setSelectedIndex(-1);
         CategoryWise_Combo.setSelectedIndex(-1);
         
         
         WarehouseList = new ArrayList<String>();
         WarehouseList = getWarehouselist(m_App);
         WarehouseWise_Model = new ComboBoxValModel(WarehouseList);
         warehouseWise_combo.setModel(WarehouseWise_Model);
         
         
         
         CategoryList = new ArrayList<String>();
         CategoryList = getCategoryList(m_App);
         CategoryWiseModel = new ComboBoxValModel(CategoryList);
         CategoryWise_Combo.setModel(CategoryWiseModel);
     }
    
     
     
     public List getCategoryList(AppView app ){
         
         List<Object> categorylist = new ArrayList<Object>();
         
         try {
            categorylist  = (List<Object>) new StaticSentence(app.getSession(), "SELECT Name from categories order by name",SerializerWriteString.INSTANCE    , SerializerReadString.INSTANCE).list();
       
         } catch (BasicException ex) {
            Logger.getLogger(ProductCatelog2.class.getName()).log(Level.SEVERE, null, ex);
        }
         return categorylist;
     }
     
     
      public String getLocationIdByName(AppView app , String Name ){
         
         Object o = null;
         String Locationname = null;
         
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ID from LOCATIONS WHERE NAME=? ",SerializerWriteString.INSTANCE    , SerializerReadString.INSTANCE).find(Name);
       
         } catch (BasicException ex) {
            Logger.getLogger(ProductCatelog2.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         if(o!=null){
             Locationname = o.toString();
         }
         
         
         return Locationname;
     }
     
    

}
