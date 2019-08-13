

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import com.openbravo.pos.sales.ConsumableProdAccLinkTableModel.ConsumableProdInfo;
import javax.swing.ButtonGroup;


public class ConsumableProdAccLink extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    
     private AppView m_App;
     public List<Object> WarehouseList=new ArrayList<Object>();
     public List<Object> ProdList=new ArrayList<Object>();
     public List<Object> DepartmentList=new ArrayList<Object>();
     public List<Object> AccountList=new ArrayList<Object>();
     
     private ComboBoxValModel warehouseMoel;
     private ComboBoxValModel ProdModel;
     private ComboBoxValModel DepartmentModel;
     private ComboBoxValModel AccountModel;
   
     
     
     
     ConsumableProdAccLinkTableModel ConsumableProdAccLink_Table_Model;
   
    public ConsumableProdAccLink() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        warehouse_combo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        prod_combo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        account_combo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
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
        save_btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        department_combo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        prod_radio = new javax.swing.JRadioButton();
        department_radio = new javax.swing.JRadioButton();
        account_radio = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(77, 6, 6));
        jLabel1.setText("Consumable Products Account linking menu");

        jLabel2.setText("Select Warehouse :  ");

        warehouse_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        warehouse_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                warehouse_comboItemStateChanged(evt);
            }
        });

        jLabel3.setText("Select Product : ");

        prod_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel4.setText("Select Account to Dr.: ");

        account_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

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
        jScrollPane1.setViewportView(jTable1);

        save_btn.setText("Save");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        jLabel5.setText("Select Department : ");

        department_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel6.setText("List of products ");

        jButton1.setText("Deactivate ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Order by :  ");

        prod_radio.setText("Product");
        prod_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                prod_radioItemStateChanged(evt);
            }
        });
        prod_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prod_radioActionPerformed(evt);
            }
        });

        department_radio.setText("Department");
        department_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                department_radioItemStateChanged(evt);
            }
        });

        account_radio.setText("Account name");
        account_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                account_radioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(prod_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(warehouse_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(account_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(department_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(prod_radio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(department_radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(account_radio)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(department_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(prod_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(account_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(save_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(prod_radio)
                    .addComponent(department_radio)
                    .addComponent(account_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(10, 10, 10))
        );

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

    String DepartmentID ;
    String AccountID;
    String ProductID;
    
    
    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
       if(warehouse_combo.getSelectedIndex()!=-1){
         if(prod_combo.getSelectedIndex()!=-1){
           if(department_combo.getSelectedIndex()!=-1){
               if(account_combo.getSelectedIndex()!=-1){
             
                   String WarehouseName = warehouse_combo.getSelectedItem().toString();
                   String ProductName = prod_combo.getSelectedItem().toString();
                   String DepartmentName = department_combo.getSelectedItem().toString();
                   String AccountName = account_combo.getSelectedItem().toString();
                   
                   try{
                  
                   DepartmentID = getDepartmentIdByName(DepartmentName);
                   WarehouseID = getWarehouseIDByName(WarehouseName);
                   ProductID = getProductIdByName(ProductName , WarehouseID);
                   
                   AccountID = getAccountIdByName(AccountName);
                   
                   String  Flag = getProductDupCheck(ProductID, DepartmentID);
                   if(Flag==null){
                       
                        
                       
                       int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO consprdacc (ID ,proid , deptid , accid , active , crby , crdate , WAREHOUSEID ) VALUES (?,?,?,?,?,?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.INT ,Datas.STRING  , Datas.TIMESTAMP , Datas.STRING })                         
                                  ).exec(new Object[]{UUID.randomUUID().toString(), ProductID ,DepartmentID , AccountID , 1 ,    m_App.getAppUserView().getUser().getName() ,new Date() , WarehouseID });                                                                                                

                       
                       loaddata();
                       prod_combo.setSelectedIndex(-1);
                       
                   }
                   else{
                       
                       //JOptionPane.showMessageDialog(this, "Product already in use with same department.\n Deactivate product in case if you want to change.");
                   
                        int cnl_req = JOptionPane.showConfirmDialog(jPanel1, "Product already in use with same department.\n Do you want to de-activate the earlier product ??  " ,"Product Deactivation",JOptionPane.YES_NO_OPTION );
                        if(cnl_req == JOptionPane.YES_OPTION){
                            
                                 
                            
                              int update_hall_cancel_request =  new PreparedSentence(m_App.getSession(), "UPDATE consprdacc  SET ACTIVE=0 , DEACBY=? , DEACHOST=?   WHERE  ID =  ? "
                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                   (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() , Flag });


                               int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO consprdacc (ID ,proid , deptid , accid , active , crby , crdate , WAREHOUSEID ) VALUES (?,?,?,?,?,?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.INT ,Datas.STRING  , Datas.TIMESTAMP , Datas.STRING })                         
                                  ).exec(new Object[]{UUID.randomUUID().toString(), ProductID ,DepartmentID , AccountID , 1 ,    m_App.getAppUserView().getUser().getName() ,new Date() , WarehouseID });                                                                                                

                       
                                loaddata();
                                prod_combo.setSelectedIndex(-1);
                            
                            
                            
                            
                            
                        }
               
                      
                   }
                   
                   
                   
                   
                   
                   
                   }
                   catch(BasicException ex ){
                        Logger.getLogger(ConsumableProdAccLink.class.getName()).log(Level.SEVERE, null, ex);             
                        ex.printStackTrace();
                        new MessageInf(ex).show(new JFrame());
                   }
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
             
             
           
               }
               else{
                    JOptionPane.showMessageDialog(this, "Please select Account.");
               }
           }
           else{
                JOptionPane.showMessageDialog(this, "Please select Department.");
           }
         }
         else{
             JOptionPane.showMessageDialog(this, "Please select Product.");
         }
       }
       else{
            JOptionPane.showMessageDialog(this, "Please select warehouse.");
       }
    }//GEN-LAST:event_save_btnActionPerformed
    String WarehouseID = null;
    private void warehouse_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_warehouse_comboItemStateChanged
        if(warehouse_combo.getSelectedIndex()!=-1){
           String WarehouseName = warehouse_combo.getSelectedItem().toString();
           
           try{ 
            WarehouseID = getWarehouseIDByName(WarehouseName);    
            ProdList = getProductListByWareHouse(WarehouseID);
            ProdModel = new ComboBoxValModel(ProdList);
            prod_combo.setModel(ProdModel);
            
            
           }
           catch(BasicException ex) {
               Logger.getLogger(ConsumableProdAccLink.class.getName()).log(Level.SEVERE, null, ex);             
               ex.printStackTrace();
               new MessageInf(ex).show(new JFrame());
           }
            
            
            
        }
    }//GEN-LAST:event_warehouse_comboItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
            
        int cnl_req = JOptionPane.showConfirmDialog(jPanel1, "Do you want to deactivate the product from department ? " ,"Product Deactivation",JOptionPane.YES_NO_OPTION );
           if(cnl_req == JOptionPane.YES_OPTION){
            
            
                 if(jTable1.getSelectedRow()<ConsumableProdAccLink_Table_Model.getSize()){
                     int row = jTable1.getSelectedRow();
                     final ConsumableProdInfo showdata = ConsumableProdAccLink_Table_Model.getList().get(row);     
            
                      String CurrID = showdata.getID();
                      
                      try{
                          
                          
                      
                        int update_hall_cancel_request =  new PreparedSentence(m_App.getSession(), "UPDATE consprdacc  SET ACTIVE=0 , DEACBY=? , DEACHOST=?   WHERE  ID =  ? "
                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                   (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() , CurrID });

                      
            
                        loaddata();
                        
            
                      }
                      catch(BasicException ex){
                          Logger.getLogger(ConsumableProdAccLink.class.getName()).log(Level.SEVERE, null, ex);             
                          ex.printStackTrace();
                          new MessageInf(ex).show(new JFrame()); 
                      }
            
            
            
                 }
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void prod_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prod_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prod_radioActionPerformed

    private void department_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_department_radioItemStateChanged
       if(department_radio.isSelected()){
           
           try{  
            ConsumableProdAccLink_Table_Model  = ConsumableProdAccLinkTableModel.LoadProdInfoOrderbyDepartment(m_App);
            jTable1.setModel(ConsumableProdAccLink_Table_Model.getTableModel());  
            
            
             DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
            jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
            jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
            jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer ); 
            jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer ); 

            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(46);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
            
            
            
          }
          catch(BasicException ex){
              Logger.getLogger(ConsumableProdAccLink.class.getName()).log(Level.SEVERE, null, ex);             
                          ex.printStackTrace();
                          new MessageInf(ex).show(new JFrame()); 
          }
           
       }
    }//GEN-LAST:event_department_radioItemStateChanged

    private void account_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_account_radioItemStateChanged
       if(account_radio.isSelected()){
           try{  
            ConsumableProdAccLink_Table_Model  = ConsumableProdAccLinkTableModel.LoadProdInfoOrderbyAccountwise(m_App);
            jTable1.setModel(ConsumableProdAccLink_Table_Model.getTableModel());  
            
             DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
            jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
            jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
            jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer ); 
            jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer ); 

            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(46);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
            
            
          }
          catch(BasicException ex){
              Logger.getLogger(ConsumableProdAccLink.class.getName()).log(Level.SEVERE, null, ex);             
                          ex.printStackTrace();
                          new MessageInf(ex).show(new JFrame()); 
          }
       }
    }//GEN-LAST:event_account_radioItemStateChanged

    private void prod_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_prod_radioItemStateChanged
        if(prod_radio.isSelected()){
            
          try{  
            ConsumableProdAccLink_Table_Model  = ConsumableProdAccLinkTableModel.LoadProdInfoOrderbyProduct(m_App);
            jTable1.setModel(ConsumableProdAccLink_Table_Model.getTableModel());  
            
            
             DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
                jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
                jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
                jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer ); 
                jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer ); 

                jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(46);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
            
            
          }
          catch(BasicException ex){
              Logger.getLogger(ConsumableProdAccLink.class.getName()).log(Level.SEVERE, null, ex);             
                          ex.printStackTrace();
                          new MessageInf(ex).show(new JFrame()); 
          }
        }
    }//GEN-LAST:event_prod_radioItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox account_combo;
    private javax.swing.JRadioButton account_radio;
    private javax.swing.JComboBox department_combo;
    private javax.swing.JRadioButton department_radio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox prod_combo;
    private javax.swing.JRadioButton prod_radio;
    private javax.swing.JButton save_btn;
    private javax.swing.JComboBox warehouse_combo;
    // End of variables declaration//GEN-END:variables


 public String getTitle() {
       return "Consumable Products Account link Menu";
    }

    public void activate() throws BasicException {
       
        warehouse_combo.setSelectedIndex(-1);
        department_combo.setSelectedIndex(-1);
        account_combo.setSelectedIndex(-1);
        prod_combo.setSelectedIndex(-1);
        
        
        WarehouseList = getWarehouseList();
        warehouseMoel = new ComboBoxValModel(WarehouseList);
        warehouse_combo.setModel(warehouseMoel);
        
        DepartmentList = GetDepartmentList();
        DepartmentModel = new ComboBoxValModel(DepartmentList);
        department_combo.setModel(DepartmentModel);
        
        AccountList = getAccountList();
        AccountModel = new ComboBoxValModel(AccountList);
        account_combo.setModel(AccountModel);
        
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
    }

    public Object getBean() {
        return this;
    }
    
    
    public void reset(){
        
        
    }
    
    public void loaddata() throws BasicException{
        
        
       ConsumableProdAccLink_Table_Model  = ConsumableProdAccLinkTableModel.LoadProdInfoOrderbyProduct(m_App);
       jTable1.setModel(ConsumableProdAccLink_Table_Model.getTableModel()); 
        
       
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
        jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer ); 
        jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer ); 
       
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(46);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
        
        Buttongroup();
        prod_radio.setSelected(true);
    }
    
    
    public List getWarehouseList() throws  BasicException{
        List<Object> Temp = new ArrayList();
        Temp =  new StaticSentence(m_App.getSession(), "SELECT NAME FROM LOCATIONS L  ORDER BY NAME  ", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).list();
        return Temp;              
    }

    public List getAccountList() throws  BasicException{
        List<Object> Temp = new ArrayList();
        Temp =  new StaticSentence(m_App.getSession(), "SELECT NAME FROM ACCOUNTMASTER   ORDER BY NAME  ", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).list();
        return Temp;              
    }
    public List GetDepartmentList() throws  BasicException{
        List<Object> Temp = new ArrayList();
        Temp =  new StaticSentence(m_App.getSession(), "SELECT NAME FROM department  WHERE ACTIVE=1  ORDER BY NAME  ", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).list();
        return Temp;              
    }
     
    public List getProductListByWareHouse(String WareHouse) throws  BasicException{
        List<Object> Temp = new ArrayList();
        Temp =  new StaticSentence(m_App.getSession(), "SELECT NAME FROM PRODUCTS P WHERE LOCATION=?  ORDER BY NAME  ", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).list(WareHouse);
        return Temp;              
    }
    public String getWarehouseIDByName(String WarehouseName) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME =? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(WarehouseName);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    public String getProductIdByName(String ProdName , String WarehouseID) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS WHERE NAME =? and location='"+WarehouseID+"'", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(ProdName);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    public String getAccountIdByName(String AccountName) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM accountmaster WHERE NAME =? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(AccountName);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
     public String getDepartmentIdByName(String DepartName) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM department WHERE NAME =? AND Active=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(DepartName);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    
     
     public String getProductDupCheck(String ProdID , String DepartmentID) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM consprdacc WHERE proid =? and active=1 AND deptid='"+DepartmentID+"'", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(ProdID);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    
     
     
     public void Buttongroup(){
         ButtonGroup bg = new ButtonGroup();
         bg.add(prod_radio);
         bg.add(department_radio);
         bg.add(account_radio);
     }
     
     
     
}
