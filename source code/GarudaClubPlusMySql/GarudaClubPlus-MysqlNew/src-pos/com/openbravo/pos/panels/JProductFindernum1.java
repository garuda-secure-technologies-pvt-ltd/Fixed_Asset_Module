

package com.openbravo.pos.panels;

import com.openbravo.pos.ticket.ProductFilterSalesnum1;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.ProductRenderer;
import javax.swing.*;
import java.awt.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.basic.BasicException;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author adrianromero
 */
public class JProductFindernum1 extends javax.swing.JDialog {

    private ProductInfoExt m_ReturnProduct;
    private ListProvider lpr;
    
    /** Creates new form JProductFinder */
    private JProductFindernum1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    /** Creates new form JProductFinder */
    private JProductFindernum1(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
    }    
    
    private ProductInfoExt init(DataLogicSales dlSales) {
        
        initComponents();
        
           m_jUp.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
          
     UP()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_UP)  )
               
             UP(); 
            
          }  
          return false;}}); 
    
    m_jDown.addActionListener(new ActionListener(){  //code for down button
      public void actionPerformed(ActionEvent ae){  
              
    DWN()
              ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_DOWN)  )
               
             DWN();  
          }  
          return false;}}); 
        
         jButton3.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
        EXC();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_E) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               
               EXC();
          }  
          return false;}});
    
     jcmdOK.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
      OKp();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_F1)) 
               
                 OKp();  
          }  
          return false;}}); 
    
    jcmdCancel.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
      Cancelp();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ESCAPE)) 
               
                 Cancelp();  
          }  
          return false;}}); 
    
        
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));

        //ProductFilter jproductfilter = new ProductFilter(app);
        ProductFilterSalesnum1 jproductfilter = new ProductFilterSalesnum1(dlSales, m_jKeys);
        jproductfilter.activate();
        m_jProductSelect.add(jproductfilter, BorderLayout.CENTER);
        
        lpr = new ListProviderCreator(dlSales.getProductList(), jproductfilter);   
       
        jListProducts.setCellRenderer(new ProductRenderer());
        
        getRootPane().setDefaultButton(jcmdOK);   
   
        m_ReturnProduct = null;
        
        //show();
        setVisible(true);
        
        return m_ReturnProduct;
    }
    
    public void UP(){
      //   int i = jTable1.getSelectedRow();
       int i = jListProducts.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = jListProducts.getModel().getSize() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < jListProducts.getModel().getSize())) {
            // Solo seleccionamos si podemos.
        //setSelectedIndex(i);
         
           jListProducts.getSelectionModel().setSelectionInterval(i, i);
        }        
//        
        
//////     
    }
     public void DWN(){
        
//   
     // int i = jTable1.getSelectedRow();   
      int i =jListProducts.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= jListProducts.getModel().getSize() ) {
                i = jListProducts.getModel().getSize() - 1;
            }
        }

        if ((i >= 0) && (i <jListProducts.getModel().getSize())) {
            // Solo seleccionamos si podemos.
             //setSelectedIndex(i);
        jListProducts.getSelectionModel().setSelectionInterval(i, i);
        }        
//
////       
//        
   }
    
    
   public void Cancelp(){
      dispose();
    }
    
    public void OKp(){
         m_ReturnProduct = (ProductInfoExt) jListProducts.getSelectedValue();
        dispose();
    }
    
    public void EXC(){
          try {
            jListProducts.setModel(new MyListData(lpr.loadData()));
            if (jListProducts.getModel().getSize() > 0) {
                jListProducts.setSelectedIndex(0);
            }
        } catch (BasicException e) {
            e.printStackTrace();
        }
}
    
    
    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }    
    
    public static ProductInfoExt showMessage(Component parent, DataLogicSales dlSales) {
         
        Window window = getWindow(parent);
        
        JProductFindernum1 myMsg;
        if (window instanceof Frame) { 
            myMsg = new JProductFindernum1((Frame) window, true);
        } else {
            myMsg = new JProductFindernum1((Dialog) window, true);
        }
        return myMsg.init(dlSales);
    }    
    
    private static class MyListData extends javax.swing.AbstractListModel {
        
        private java.util.List m_data;
        
        public MyListData(java.util.List data) {
            m_data = data;
        }
        
        public Object getElementAt(int index) {
            return m_data.get(index);
        }
        
        public int getSize() {
            return m_data.size();
        } 
    }   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        m_jKeys = new com.openbravo.editor.JEditorKeys();
        jPanel2 = new javax.swing.JPanel();
        m_jProductSelect = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        m_jUp = new javax.swing.JButton();
        m_jDown = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListProducts = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jcmdOK = new javax.swing.JButton();
        jcmdCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("form.productslist")); // NOI18N

        jPanel4.setFocusable(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        m_jKeys.setFocusable(false);
        jPanel4.add(m_jKeys, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        m_jProductSelect.setLayout(new java.awt.BorderLayout());

        jPanel3.setFocusable(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/launch.png"))); // NOI18N
        jButton3.setText(AppLocal.getIntString("button.executefilter")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
        m_jUp.setFocusPainted(false);
        m_jUp.setFocusable(false);
        m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jUpActionPerformed(evt);
            }
        });

        m_jDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
        m_jDown.setFocusPainted(false);
        m_jDown.setFocusable(false);
        m_jDown.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDown.setRequestFocusEnabled(false);
        m_jDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDownActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(m_jUp)
                .add(18, 18, 18)
                .add(m_jDown)
                .add(18, 18, 18)
                .add(jButton3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(jButton3))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, m_jDown, 0, 0, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, m_jUp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setToolTipText("CTRL E");
        m_jUp.setVisible(false);
        m_jDown.setVisible(false);

        m_jProductSelect.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel2.add(m_jProductSelect, java.awt.BorderLayout.NORTH);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jListProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListProductsMouseClicked(evt);
            }
        });
        jListProducts.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListProductsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListProducts);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.setFocusable(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jcmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jcmdOK.setText(AppLocal.getIntString("Button.OK")); // NOI18N
        jcmdOK.setEnabled(false);
        jcmdOK.setFocusable(false);
        jcmdOK.setMargin(new java.awt.Insets(8, 16, 8, 16));
        jcmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOKActionPerformed(evt);
            }
        });
        jPanel1.add(jcmdOK);
        jcmdOK.setToolTipText("F1");

        jcmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jcmdCancel.setText(AppLocal.getIntString("Button.Cancel")); // NOI18N
        jcmdCancel.setFocusable(false);
        jcmdCancel.setMargin(new java.awt.Insets(8, 16, 8, 16));
        jcmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jcmdCancel);
        jcmdCancel.setToolTipText("ESCAPE");

        jPanel2.add(jPanel1, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-665)/2, (screenSize.height-565)/2, 665, 565);
    }// </editor-fold>//GEN-END:initComponents

    private void jListProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListProductsMouseClicked

        if (evt.getClickCount() == 2) {
            m_ReturnProduct = (ProductInfoExt) jListProducts.getSelectedValue();
            dispose();
        }
        
    }//GEN-LAST:event_jListProductsMouseClicked

    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed
        
        m_ReturnProduct = (ProductInfoExt) jListProducts.getSelectedValue();
        dispose();
        
    }//GEN-LAST:event_jcmdOKActionPerformed

    private void jcmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdCancelActionPerformed
        
        dispose();
        
    }//GEN-LAST:event_jcmdCancelActionPerformed

    private void jListProductsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListProductsValueChanged

        jcmdOK.setEnabled(jListProducts.getSelectedValue() != null);
        
    }//GEN-LAST:event_jListProductsValueChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            jListProducts.setModel(new MyListData(lpr.loadData()));
            if (jListProducts.getModel().getSize() > 0) {
                jListProducts.setSelectedIndex(0);
            }
        } catch (BasicException e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed
//     int i = jTable1.getSelectionModel().getMinSelectionIndex();
//        if (i < 0){
//            i = jTable1.getRowCount() - 1; 
//            // No hay ninguna seleccionada
//        } else {
//            i --;
//            if (i < 0) {
//                i = 0;
//            }
//        }
//
//        if ((i >= 0) && (i < jTable1.getRowCount())) {
//            // Solo seleccionamos si podemos.
//            setSelectedIndex(i);
//        }
}//GEN-LAST:event_m_jUpActionPerformed

private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed
//
//        int i =jTable1.getSelectionModel().getMaxSelectionIndex();
//        if (i < 0){
//            i =  0; // No hay ninguna seleccionada
//        } else {
//            i ++;
//            if (i >= jTable1.getRowCount()) {
//                i = jTable1.getRowCount() - 1;
//            }
//        }
//
//        if ((i >= 0) && (i <jTable1.getRowCount())) {
//            // Solo seleccionamos si podemos.
//     
//            setSelectedIndex(i);
//        }
}//GEN-LAST:event_m_jDownActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JList jListProducts;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jcmdCancel;
    private javax.swing.JButton jcmdOK;
    private javax.swing.JButton m_jDown;
    private com.openbravo.editor.JEditorKeys m_jKeys;
    private javax.swing.JPanel m_jProductSelect;
    private javax.swing.JButton m_jUp;
    // End of variables declaration//GEN-END:variables
    
}
