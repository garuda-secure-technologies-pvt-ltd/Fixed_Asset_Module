

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.sms.EmailMasterTableModel;
import com.openbravo.pos.sms.MemberEmailList;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JComponent;

import javax.swing.table.TableCellRenderer;
import com.openbravo.pos.clubmang.PurchaseLoadFrameTableModel.PurchaseDetails;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class PurchaseLoadFrame extends javax.swing.JDialog{

    
    private AppView app;
     private boolean flag;
     private PurchaseLoadFrameTableModel PurchaseLoadFrame_Table_Model;
     private PurchaseLoadFrameTableModel PurchaseLoadFrame_Table_Model2;
     private String Warehouse; 
     public String PurchaseIdSelected = null;
     public PurchaseDetails showPurchaseDetailsSelected = null;
     public List<PurchaseLoadFrameTableModel.ItemDetails> ProdFullList;        
             
             
    public PurchaseLoadFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }

    
  
    
    public PurchaseLoadFrame(java.awt.Dialog parent,  AppView app, boolean flag , String Warehouse) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
        this.Warehouse=Warehouse;
    }
    
     public PurchaseLoadFrame(java.awt.Frame parent,  AppView app, boolean flag , String Warehouse) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
        this.Warehouse=Warehouse;
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
        warehouse_label = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(235, 24, 24));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(213, 26, 26));
        jLabel1.setText("Purchase Pending List");

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
        jTable1.setRowHeight(22);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        warehouse_label.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        warehouse_label.setForeground(new java.awt.Color(19, 29, 240));
        warehouse_label.setText("Warehouse : ");

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
        jTable2.setRowHeight(22);
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Purchase Details ");

        jLabel3.setText("Product Details");

        jButton1.setText("Load details ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(371, 371, 371)
                        .addComponent(warehouse_label))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(warehouse_label))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(jTable1.getSelectedRow()!=-1){
            int row = jTable1.getSelectedRow();
            PurchaseDetails showdata = PurchaseLoadFrame_Table_Model.getList().get(row);
            String Purchaseid= showdata.getID();
            
            try{
                PurchaseLoadFrame_Table_Model2=PurchaseLoadFrameTableModel.loadPurchasePendingInfoQtyWise(Purchaseid, app);
                jTable2.setModel(PurchaseLoadFrame_Table_Model2.getTableModel2()); 
                jTable2.setVisible(true);
                ProdFullList = new ArrayList<PurchaseLoadFrameTableModel.ItemDetails>();
                ProdFullList = PurchaseLoadFrame_Table_Model2.getList2();
                
            }
            catch(BasicException e){
                e.printStackTrace();
            }
            
           
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
            int row = jTable1.getSelectedRow();
            PurchaseDetails showdata = PurchaseLoadFrame_Table_Model.getList().get(row);
            PurchaseIdSelected= showdata.getID();
            setPurchaseIdSelected(PurchaseIdSelected);
            setPurchaseDetails(showdata);
            setPurchaseDetailsItemList(ProdFullList);
            dispose();
        }
        else{
             JOptionPane.showMessageDialog(this, "Select atleast one purchase.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public String getPurchaseIdSelected(){
        return PurchaseIdSelected;
    }
    public void setPurchaseIdSelected(String PurchaseIdSelected){
        this.PurchaseIdSelected=PurchaseIdSelected;
    }
    
    public PurchaseDetails getPurchaseDetails(){
        return showPurchaseDetailsSelected;
    }
    public void setPurchaseDetails(PurchaseDetails showPurchaseDetailsSelected){
        this.showPurchaseDetailsSelected=showPurchaseDetailsSelected;
    }
    
    public List<PurchaseLoadFrameTableModel.ItemDetails> getPurchaseDetailsItemList(){
        return ProdFullList;
    }
    public void setPurchaseDetailsItemList(List<PurchaseLoadFrameTableModel.ItemDetails> ProdFullList){
        this.ProdFullList=ProdFullList;
    }
    
    
      public static PurchaseLoadFrame getDialog(Component parent,  AppView app, boolean flag ,String Warehouse) throws BasicException {

        Window window = getWindow(parent);
        
        PurchaseLoadFrame bill;
        
       

        if (window instanceof Frame) {
            bill = new PurchaseLoadFrame((Frame) window , app, flag,Warehouse);
        } else {
            bill = new PurchaseLoadFrame((Dialog) window, app, flag,Warehouse);
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
        warehouse_label.setText("Warehouse :- " +Warehouse);
        PurchaseLoadFrame_Table_Model=PurchaseLoadFrameTableModel.loadPurchasePendingInfo(Warehouse, app);
        jTable1.setModel(PurchaseLoadFrame_Table_Model.getTableModel()); 
        jTable2.setVisible(false);

    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel warehouse_label;
    // End of variables declaration//GEN-END:variables
}
