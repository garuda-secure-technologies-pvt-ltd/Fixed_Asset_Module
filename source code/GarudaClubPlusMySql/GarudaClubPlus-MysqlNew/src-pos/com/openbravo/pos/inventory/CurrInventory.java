
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Booking.BilledReportsTableModel;
import com.openbravo.pos.Booking.BookingSituationReport;
import com.openbravo.pos.Booking.DataSourceForBilledReports_Halls;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import net.sf.jasperreports.engine.JasperPrint;

public class CurrInventory extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    List<String> WarehouseList = new ArrayList<String>();
    List<String> CategoryList = new ArrayList<String>();
    private ComboBoxValModel WarehouseModel;
    private ComboBoxValModel CategoryModel;
    public String WarehouseId;
     private List<CurrentInventoryTableModel.InventoryInfo> CurrStock_List;
    private CurrentInventoryTableModel CurrentInventory_Table_Model;
    public int ZeroStockFlag=0;
    
    
    
    public CurrInventory() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        all_warehouse_radio = new javax.swing.JRadioButton();
        select_warehouse_radio = new javax.swing.JRadioButton();
        generate_btn = new javax.swing.JButton();
        warehouse_Panel = new javax.swing.JPanel();
        warehouse_Combo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        categorywise_combo = new javax.swing.JComboBox();
        zeroStock_check = new javax.swing.JCheckBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Current Inventory ");

        jLabel2.setText("Warehouse :  ");

        all_warehouse_radio.setText("All ");
        all_warehouse_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                all_warehouse_radioItemStateChanged(evt);
            }
        });

        select_warehouse_radio.setText("Filter");
        select_warehouse_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_warehouse_radioItemStateChanged(evt);
            }
        });

        generate_btn.setText("Generate Report ");
        generate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_btnActionPerformed(evt);
            }
        });

        warehouse_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        warehouse_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                warehouse_ComboItemStateChanged(evt);
            }
        });

        jLabel3.setText("Category Wise : ");

        categorywise_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout warehouse_PanelLayout = new javax.swing.GroupLayout(warehouse_Panel);
        warehouse_Panel.setLayout(warehouse_PanelLayout);
        warehouse_PanelLayout.setHorizontalGroup(
            warehouse_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(warehouse_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(categorywise_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        warehouse_PanelLayout.setVerticalGroup(
            warehouse_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(warehouse_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warehouse_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(warehouse_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(categorywise_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        zeroStock_check.setText("Ignore Zero Stock ");
        zeroStock_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                zeroStock_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(generate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(all_warehouse_radio)
                        .addGap(18, 18, 18)
                        .addComponent(select_warehouse_radio))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(warehouse_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(zeroStock_check)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(all_warehouse_radio)
                    .addComponent(select_warehouse_radio))
                .addGap(18, 18, 18)
                .addComponent(warehouse_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zeroStock_check)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(generate_btn)
                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void all_warehouse_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_all_warehouse_radioItemStateChanged
      if(all_warehouse_radio.isSelected()){
          warehouse_Panel.setVisible(false);
      }
      else{
          warehouse_Panel.setVisible(true);
      }
    }//GEN-LAST:event_all_warehouse_radioItemStateChanged

    private void select_warehouse_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_warehouse_radioItemStateChanged
        if(select_warehouse_radio.isSelected()){
            warehouse_Panel.setVisible(true);
            warehouse_Combo.setSelectedIndex(-1);
        }
        else{
            warehouse_Panel.setVisible(false);
        }
    }//GEN-LAST:event_select_warehouse_radioItemStateChanged

    private void warehouse_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_warehouse_ComboItemStateChanged
        if(warehouse_Combo.getSelectedIndex()!=-1){
            String locationname = warehouse_Combo.getSelectedItem().toString();
            try {
                WarehouseId=getWarehouseIDbyName(locationname);
                CategoryList = getCategoriesByLocation(WarehouseId);
                CategoryModel = new ComboBoxValModel(CategoryList);
                categorywise_combo.setModel(CategoryModel);
                
                
                
            } catch (BasicException ex) {
                Logger.getLogger(CurrInventory.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }//GEN-LAST:event_warehouse_ComboItemStateChanged

    private void generate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_btnActionPerformed
      if(all_warehouse_radio.isSelected()){
          
           CurrStock_List= new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
          
               try {
                   if(ZeroStockFlag==0){
                        CurrentInventory_Table_Model  = CurrentInventoryTableModel.LoadStockValues(m_App);
                   }  
                   else{
                       CurrentInventory_Table_Model  = CurrentInventoryTableModel.LoadStockValuesZeroValueIgnored(m_App);
                   }
                   
                  
               } 

               catch (BasicException ex) {
                       Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }
                CurrStock_List  =  (List<CurrentInventoryTableModel.InventoryInfo>) CurrentInventory_Table_Model.getList();

                DataSourceProvider data1 = new DataSourceProvider(CurrStock_List);
                DataSourceForCurrentInventoryNew dsfc = new DataSourceForCurrentInventoryNew(CurrStock_List);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                
                
                reportparams.put("date",new Date());
              

               JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/currInventoryStcknew.jrxml", reportparams, false, data1, true, null); 
                         
          
 
          
          
          
          
      }
      if(select_warehouse_radio.isSelected()){
          if(warehouse_Combo.getSelectedIndex()!=-1){
                    
              if(categorywise_combo.getSelectedIndex()==-1){
              
                    String SelectedWarehouse=  warehouse_Combo.getSelectedItem().toString();
                    CurrStock_List= new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
                    try {
                          
                          if(ZeroStockFlag==0){
                           CurrentInventory_Table_Model  = CurrentInventoryTableModel.LoadStockValuesByWarehouse(m_App,SelectedWarehouse);
                          }
                          else{
                              CurrentInventory_Table_Model  = CurrentInventoryTableModel.LoadStockValuesByWarehouseZeroValueIgnored(m_App,SelectedWarehouse);
                          }
                     
                    
                    } 
                     catch (BasicException ex) {
                             Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      CurrStock_List  =  (List<CurrentInventoryTableModel.InventoryInfo>) CurrentInventory_Table_Model.getList();

                      DataSourceProvider data1 = new DataSourceProvider(CurrStock_List);
                      DataSourceForCurrentInventoryNew dsfc = new DataSourceForCurrentInventoryNew(CurrStock_List);
                      data1.setDataSource(dsfc);
                      Map reportparams = new HashMap();
                      reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                      reportparams.put("ADDR", m_App.getSession().getCompanyAddress());


                      reportparams.put("date",new Date());


                     JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/currInventoryStcknew.jrxml", reportparams, false, data1, true, null); 

                    
          
              }
              else{
                  
                    String selectCategory=  categorywise_combo.getSelectedItem().toString();
                    CurrStock_List= new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
                    try {
                          if(ZeroStockFlag==0){  
                           CurrentInventory_Table_Model  = CurrentInventoryTableModel.LoadStockValuesByCategory(m_App,selectCategory);
                          }
                          else{
                               CurrentInventory_Table_Model  = CurrentInventoryTableModel.LoadStockValuesByCategoryZeroValuesIgnored(m_App,selectCategory);
                          }
                     } 
                     catch (BasicException ex) {
                             Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      CurrStock_List  =  (List<CurrentInventoryTableModel.InventoryInfo>) CurrentInventory_Table_Model.getList();

                      DataSourceProvider data1 = new DataSourceProvider(CurrStock_List);
                      DataSourceForCurrentInventoryNew dsfc = new DataSourceForCurrentInventoryNew(CurrStock_List);
                      data1.setDataSource(dsfc);
                      Map reportparams = new HashMap();
                      reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                      reportparams.put("ADDR", m_App.getSession().getCompanyAddress());


                      reportparams.put("date",new Date());


                     JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/currInventoryStcknew.jrxml", reportparams, false, data1, true, null); 
                         
              }
          
          
          
          }
          
      }
    }//GEN-LAST:event_generate_btnActionPerformed

    private void zeroStock_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_zeroStock_checkItemStateChanged
        if(zeroStock_check.isSelected()){
            ZeroStockFlag=1;
        }
        else{
            ZeroStockFlag=0;
        }
                
    }//GEN-LAST:event_zeroStock_checkItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton all_warehouse_radio;
    private javax.swing.JComboBox categorywise_combo;
    private javax.swing.JButton generate_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton select_warehouse_radio;
    private javax.swing.JComboBox warehouse_Combo;
    private javax.swing.JPanel warehouse_Panel;
    private javax.swing.JCheckBox zeroStock_check;
    // End of variables declaration//GEN-END:variables


public String getTitle() {
       return "E-Mail Account Master";
    }

    public void activate() throws BasicException {
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
    }

    public Object getBean() {
        return this;
    }
    
    
 public void loaddata() throws  BasicException{
     ButtonGrp();
     select_warehouse_radio.setSelected(true);
     WarehouseList=getWarehouseList();
     WarehouseModel = new ComboBoxValModel(WarehouseList);
     warehouse_Combo.setModel(WarehouseModel);
     zeroStock_check.setSelected(false);
     ZeroStockFlag=0;
 }   
    
  public void ButtonGrp(){
      ButtonGroup BG= new ButtonGroup();
      BG.add(all_warehouse_radio);
      BG.add(select_warehouse_radio);
  }


   public List getWarehouseList() throws  BasicException{
       List<Object> Temp = new ArrayList();
       Temp =  new StaticSentence(m_App.getSession(), " SELECT NAME FROM LOCATIONS ORDER BY NAME ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
        return Temp;              
    }
  
  public String getWarehouseIDbyName(String Name) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS  WHERE NAME=? ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    
  
  public List getCategoriesByLocation(String Parentid) throws  BasicException{
       List<Object> Temp = new ArrayList();
       Temp =  new StaticSentence(m_App.getSession(), " SELECT NAME FROM CATEGORIES WHERE PARENTID=? ORDER BY NAME  ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list(Parentid);
        return Temp;              
    }
  
}
