

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.panels.JProductFinder;
import com.openbravo.pos.ticket.ProductInfoExt;


import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.table.TableCellRenderer;
import com.openbravo.pos.sales.DeactivatedProductTableModel.ProdInfo;


public class ActivateKitchenProd extends javax.swing.JDialog {

    private AppView app;
    private DataLogicSales m_dlSales;
    private DeactivatedProductTableModel DeactivatedProduct_Table_Model;
    
    public ActivateKitchenProd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }

    
  
    
    public ActivateKitchenProd(java.awt.Dialog parent,  AppView app) {
        super(parent, true);
       
        this.app = app;
        
    }
    
     public ActivateKitchenProd(java.awt.Frame parent,  AppView app) {
        super(parent, true);
       
        this.app = app;
       
    }
    
    
    
    
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        productname_text = new javax.swing.JTextField();
        findprod_btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        reference_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Image_Label = new javax.swing.JLabel();
        save_Button = new javax.swing.JButton();
        deactivate_chkbx = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
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
        jLabel5 = new javax.swing.JLabel();
        activate_btn = new javax.swing.JButton();
        close_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(17, 17, 222));
        jLabel1.setText("Kitchen item(s) activate/deactivate Menu");

        jLabel2.setText("product Name :");

        findprod_btn.setText("Find");
        findprod_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findprod_btnActionPerformed(evt);
            }
        });

        jLabel3.setText("Product Reference No : ");

        jLabel4.setText("Product Image : ");

        save_Button.setForeground(new java.awt.Color(13, 39, 229));
        save_Button.setText("Save ");
        save_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_ButtonActionPerformed(evt);
            }
        });

        deactivate_chkbx.setText("Deactivate product for One day.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(save_Button)
                .addGap(76, 76, 76))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(productname_text)
                                    .addComponent(reference_text, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(findprod_btn))
                            .addComponent(Image_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(deactivate_chkbx, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(reference_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(productname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findprod_btn))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Image_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(deactivate_chkbx)
                .addGap(28, 28, 28)
                .addComponent(save_Button)
                .addGap(16, 16, 16))
        );

        productname_text.setEditable(false);

        jTabbedPane1.addTab("Activate/Deactivate", jPanel2);

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

        jLabel5.setForeground(new java.awt.Color(20, 22, 238));
        jLabel5.setText("Deactivated item(s) list for day.");

        activate_btn.setForeground(new java.awt.Color(28, 26, 251));
        activate_btn.setText("Activate the product");
        activate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activate_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(jLabel5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(activate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 269, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(activate_btn)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List of deactivated Product(s)", jPanel3);

        close_btn.setForeground(new java.awt.Color(247, 8, 8));
        close_btn.setText("Close");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(close_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(149, 149, 149)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(close_btn)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
     dispose();
    }//GEN-LAST:event_close_btnActionPerformed

    String pid=null;
    
    private void findprod_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findprod_btnActionPerformed
      ProductInfoExt pd=JProductFinder.showMessage(this, m_dlSales );
         
         
        String warehouse = null;
        String[] warehouses = null;
        boolean Prodflag=false;
        String SelectedProdWarehouse = pd.getWarehouse();
        
        
        Object obj = app.getAppUserView().getUser().getWarehouse();
        
        if (obj != null) {
            warehouses = obj.toString().split("#");
            
            for(int i=0;i<warehouses.length;i++){
                warehouse = warehouses[i];
                if(SelectedProdWarehouse.equals(warehouse)){
                    Prodflag = true;
                    break;
                }
                
            }
        }
      
      
        if(Prodflag){
        
         productname_text.setText(pd.getName());
         reference_text.setText(pd.getReference());
         if(pd.getImage()!=null){
             Image_Label.setIcon(new ImageIcon(pd.getImage()));
             pid= pd.getID(); 
         }
         else{
             Image_Label.setIcon(new ImageIcon());
             pid=null;
         }
         
        
        }
        else{
            reset();
            JOptionPane.showMessageDialog(this, "Please select Product  belonging  to your warehouse..!! ", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
         
     
    }//GEN-LAST:event_findprod_btnActionPerformed

    private void save_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_ButtonActionPerformed
        if(productname_text!=null && productname_text.getText().trim().length()>0  && pid!=null ){
            
            if(deactivate_chkbx.isSelected()){
            
            
            String ProdName = productname_text.getText();
            
            Transaction t = new Transaction(app.getSession()) {                                                                                     

                    @Override      
                    protected Object transact() throws BasicException {   
                        
                        
                        Date CurrDate = new Date();
                        String User = app.getAppUserView().getUser().getId();
                        String ProductId = pid;

                        int Active = 1;


                         int   insert_data =  new PreparedSentence(app.getSession()  , "INSERT INTO deactiveproduct (ID ,PRODUCTID , DEACDATE , DEACBY , DEACHOST , ACTIVE  ) VALUES (?,?,?,?,?,?)"                           
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.TIMESTAMP ,Datas.STRING, Datas.STRING ,Datas.INT  })                         
                         ).exec(new Object[]{UUID.randomUUID().toString(), ProductId ,CurrDate ,  app.getAppUserView().getUser().getName() , app.getProperties().getHost() , Active  });                                                                                                


                          return null;                                      
                            }                            
                        };                 

                        try {                 
                            t.execute();          

                            JOptionPane.showMessageDialog(this, "Deactivated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reset();
                            Loaddata();


                        }
                     catch (BasicException ex) {                    
                                ex.printStackTrace();
                                new MessageInf(ex).show(new JFrame());

                     } 
            
            
            }
        else{
               JOptionPane.showMessageDialog(this, "Please select for deactivation..!! ", "Warning", JOptionPane.WARNING_MESSAGE);
          }
        }
        else{
             JOptionPane.showMessageDialog(this, "Please select product to be deactivated..!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_save_ButtonActionPerformed

    private void activate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activate_btnActionPerformed
         if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel3, " Do you want to Activate ?? " , "Activation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
             
              if(jTable1.getSelectedRow()<DeactivatedProduct_Table_Model.getSize()){
               int row = jTable1.getSelectedRow();
               ProdInfo showdata = DeactivatedProduct_Table_Model.getList().get(row);
                 
               
               String id = showdata.getID();
               
               
               
                // DEACTIVATE LINKED ROOM
               try {
                      int update_Email_master =  new PreparedSentence(app.getSession(), "UPDATE deactiveproduct  SET ACTIVE=0  , ACTIVATEDBY=? , ACTIVATEDATE=? , ACTIVATEDHOST=? WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{  app.getAppUserView().getUser().getName() ,new Date(), app.getProperties().getHost() , id  });
                  
               
                      reset();
                      Loaddata();
                      
                      JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                      
               } catch (BasicException ex) {
                      Logger.getLogger(ActivateKitchenProd.class.getName()).log(Level.SEVERE, null, ex);
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
               
               
                 
             
              }
             }
         }
    }//GEN-LAST:event_activate_btnActionPerformed

   public static ActivateKitchenProd getDialog(Component parent,  AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);
        
        ActivateKitchenProd bill;
        
       

        if (window instanceof Frame) {
            bill = new ActivateKitchenProd((Frame) window , app);
        } else {
            bill = new ActivateKitchenProd((Dialog) window, app);
        }
       
        return bill;
        
        
    }
    
     public boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }
   
     
     
     
       
   protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    } 
    
   
   public void init() throws BasicException {
        initComponents();
         m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         Loaddata();
        
      

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image_Label;
    private javax.swing.JButton activate_btn;
    private javax.swing.JButton close_btn;
    private javax.swing.JCheckBox deactivate_chkbx;
    private javax.swing.JButton findprod_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField productname_text;
    private javax.swing.JTextField reference_text;
    private javax.swing.JButton save_Button;
    // End of variables declaration//GEN-END:variables


    public void Loaddata()throws BasicException{
        
       
        deactivate_chkbx.setSelected(false);
        DeactivatedProduct_Table_Model  = DeactivatedProductTableModel.LoadDeactivatedProducts(app);
        jTable1.setModel(DeactivatedProduct_Table_Model.getTableModel()); 
    }

  

   public void reset(){
       reference_text.setText(null);
       productname_text.setText(null);
       Image_Label.setIcon(new ImageIcon());
       deactivate_chkbx.setSelected(false);
       
       
   }
   

}
