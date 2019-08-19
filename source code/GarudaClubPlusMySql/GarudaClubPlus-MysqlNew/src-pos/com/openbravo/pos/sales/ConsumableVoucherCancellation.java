

package com.openbravo.pos.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import net.sf.jasperreports.engine.JasperPrint;
import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import com.openbravo.beans.JCalendarDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;


import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.InventoryLine;
import com.openbravo.pos.inventory.JPanelInventory1;
import com.openbravo.pos.panels.ConsumableSalesModel;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.util.UUID;
import javax.swing.JFrame;



public class ConsumableVoucherCancellation extends javax.swing.JPanel implements JPanelView,BeanFactoryApp  {

    private AppView m_App;
    private List<ConsumableVoucherCancellationTableModel.BillInfo> VoucherList;
    private List<ConsumableVoucherCancellationTableModel.ProductInfo> ProductList;
    private ConsumableVoucherCancellationTableModel ConsumableVoucherCancellation_Table_Model;
    private ConsumableVoucherCancellationTableModel ProductListTableModel; 
     
    private List<ConsumableBillReprintTableModel.BillInfo> BillInfoList;
    private ConsumableBillReprintTableModel ConsumableBillReprint_Table_Model;
    
    public ConsumableVoucherCancellation() {
        initComponents();
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(98, 6, 6));
        jLabel1.setText("Voucher Cancellation Menu ");

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Cancel Selected Voucher");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Pending Vouchers");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("Products Details");

        jButton1.setText("Print Material Voucher Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(115, Short.MAX_VALUE))
        );

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       jTable2.setVisible(false);
       
       if(jTable1.getSelectedRow()!=-1){  
           
           int Row_Count = jTable1.getSelectedRow();
           String BillID  = String.valueOf(ConsumableVoucherCancellation_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  
           ProductList = new ArrayList<ConsumableVoucherCancellationTableModel.ProductInfo>();
                try {
                      ProductListTableModel  = ConsumableVoucherCancellationTableModel.LoadProductInfo(m_App, BillID);
                }
                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                ProductList  =  (List<ConsumableVoucherCancellationTableModel.ProductInfo>) ProductListTableModel.GetProdList();
                jTable2.setVisible(true);
                jTable2.setModel(ProductListTableModel.getTableModel2());
               
        }
       
    }//GEN-LAST:event_jTable1MouseClicked
    String BillID;
    List<Object> BillItemList = new ArrayList<Object>();
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          if(jTable1.getSelectedRow()!=-1){  
              int cnl_req = JOptionPane.showConfirmDialog(jPanel1, "Do You Want to Cancel Voucher issued..? " ,"Material Issued Voucher cancellation",JOptionPane.YES_NO_OPTION );
                if(cnl_req == JOptionPane.YES_OPTION){ 
              
              
              
                    int Row_Count = jTable1.getSelectedRow();
                     BillID = String.valueOf(ConsumableVoucherCancellation_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  

                    try{  
                            BillItemList = new ArrayList<Object>();
                            BillItemList = getBillItemList(m_App, BillID);
                        
                            Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), " SELECT BILLID FROM consumereversedbill where billid=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BillID);
                            if(obj2==null){
                                
                                Transaction t = new Transaction(m_App.getSession()) {
                                Double RevAmount=0.00;    
                                @Override
                                protected Object transact() throws BasicException {


                                    for(int i=0;i<BillItemList.size();i++){
                                    
                                        String Currbillid = BillItemList.get(i).toString();
                                        
                                         new PreparedSentence(m_App.getSession(), "insert into  cpbillitem (id, billid,productid,qty,rate,taxvalue,deptid)\n" +
                                                                                "select ? , billid , productid , qty*(-1) , rate*(1) , 0.00 , deptid from cpbillitem where id=?", 
                                                                            new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING }))
                                                                            .exec(new Object[]{UUID.randomUUID().toString()  , Currbillid });

                                         
                                         Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT productid , qty FROM cpbillitem WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.INT })).find(Currbillid);
                                         if(obj1!=null){
                                             String ProdID = obj1[0].toString();
                                             Double Qty = Double.parseDouble(obj1[1].toString());
                                             
                                             new PreparedSentence(m_App.getSession(), "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE  PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING}), null).exec(new Object[]{ Qty ,  ProdID });
                                         }
                                         
                                         
                                    }

                                    
                                    
                                      new PreparedSentence(m_App.getSession(), "insert into consumereversedbill (id,billid,amount,cancelleddate , cancelledby , createddate , createdby)\n" +
                                                                                "select ? , ? , amount , ? , ? , createddate , createdby from cpbill where id=?", 
                                                                            new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING}))
                                                                            .exec(new Object[]{UUID.randomUUID().toString()  , BillID , new Date() , m_App.getAppUserView().getUser().getName() , BillID });


                                     new PreparedSentence(m_App.getSession(), "UPDATE CPBILL  SET AMOUNT=0.00 , taxamount=0.00 WHERE  ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING })).exec(new Object[]{ BillID });





                                     return null;
                                   }
                                };
                                t.execute();
                                JOptionPane.showMessageDialog(this, "Cancelled Successfully. " );
                                loaddata();
                            }
                            else{
                                 JOptionPane.showMessageDialog(this, "Already Cancelled..!");
                            }


                    }
                    catch(BasicException ex){
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                       Logger.getLogger(ConsumableVoucherCancellation.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
                
          }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(jTable1.getSelectedRow()!=-1){  
              int Row_Count = jTable1.getSelectedRow();
               String BillIDTemp = String.valueOf(ConsumableVoucherCancellation_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  
               
               
             
               
                BillInfoList = new ArrayList<ConsumableBillReprintTableModel.BillInfo>();

                try {
                      ConsumableBillReprint_Table_Model  = ConsumableBillReprintTableModel.loadEmailInfo(m_App, BillIDTemp );
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                BillInfoList  =  (List<ConsumableBillReprintTableModel.BillInfo>) ConsumableBillReprint_Table_Model.getBillList();

                DataSourceProvider data1 = new DataSourceProvider(BillInfoList);
                DatasourceForConsumeBillReprint dsfc = new DatasourceForConsumeBillReprint(BillInfoList);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());

                reportparams.put("TITLE","Consumable Material Issued Voucher");


                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ConsumableBill.jrxml", reportparams, false, data1, true, null);

              }
             
             
             
             
         
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables


 public String getTitle() {
       return "Consumable Voucher Cancellation";
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
    
    
    public void loaddata() throws BasicException{
        
      jTable2.setVisible(false);  
        
       VoucherList = new ArrayList<ConsumableVoucherCancellationTableModel.BillInfo>();

                try {
                      ConsumableVoucherCancellation_Table_Model  = ConsumableVoucherCancellationTableModel.LoadVoucherInfo(m_App);
                }
                catch (BasicException ex) {
                     Logger.getLogger(ConsumableVoucherCancellation.class.getName()).log(Level.SEVERE, null, ex);
                }
                VoucherList  =  (List<ConsumableVoucherCancellationTableModel.BillInfo>) ConsumableVoucherCancellation_Table_Model.getBillList();
      
                jTable1.setVisible(true);
                jTable1.setModel(ConsumableVoucherCancellation_Table_Model.getTableModel());
                
                
    }
    
    public void reset(){
         
    }
    

      public List getBillItemList(AppView app , String BillID) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM CPBILLITEM WHERE BILLID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(BillID);
          
          return Mem_list;
      }



}
