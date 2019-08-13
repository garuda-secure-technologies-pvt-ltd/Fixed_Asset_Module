
package com.openbravo.pos.customers;

//import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadImage;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author  adrianromero
 */
public class JCustomerFindernum1 extends javax.swing.JDialog implements EditorCreator {

    private CustomerInfo selectedCustomer;
    private ListProvider lpr;
  //  private ZoomIcon m_icon;
   
    /** Creates new form JCustomerFinder */
    private JCustomerFindernum1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    /** Creates new form JCustomerFinder */
    private JCustomerFindernum1(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
    }
    
    public static JCustomerFindernum1 getCustomerFinder(Component parent, DataLogicCustomers dlCustomers) {
        Window window = getWindow(parent);
        
        JCustomerFindernum1 myMsg;
        if (window instanceof Frame) { 
            myMsg = new JCustomerFindernum1((Frame) window, true);
        } else {
            myMsg = new JCustomerFindernum1((Dialog) window, true);
        }
        myMsg.init(dlCustomers);
        myMsg.applyComponentOrientation(parent.getComponentOrientation());
        return myMsg;
    }
    
    public CustomerInfo getSelectedCustomer() {
        return selectedCustomer;
    }

    private void init(DataLogicCustomers dlCustomers) {
        //m_jDown.setVisible(false);
       // m_jUp.setVisible(false);
        initComponents();
         jButton3.setToolTipText("CTRL E");
         jButton1.setToolTipText("CTRL R");
         jcmdOK.setToolTipText("F1");
         jcmdCancel.setToolTipText("ESCAPE");
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));

        m_jtxtTaxID.addEditorKeys(m_jKeys);
        m_jtxtSearchKey.addEditorKeys(m_jKeys);
        m_jtxtName.addEditorKeys(m_jKeys);

        m_jtxtTaxID.reset();
        m_jtxtSearchKey.reset();
        m_jtxtName.reset();
        
        m_jtxtTaxID.activate();

        lpr = new ListProviderCreator(dlCustomers.getCustomerList(), this);

        jListCustomers.setCellRenderer(new CustomerRenderer());

        getRootPane().setDefaultButton(jcmdOK);

        selectedCustomer = null;
        
        
        
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
         
         jButton1.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
        Reset();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_R)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) 
               
                Reset();  
          }  
          return false;}}); 
    
    jButton3.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
       Execute();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_E)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) 
               
                 Execute();  
          }  
          return false;}}); 
    
    jcmdOK.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
      OK();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_F1)) 
               
                 OK();  
          }  
          return false;}}); 
    
    jcmdCancel.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
      Cancel();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ESCAPE)) 
               
                 Cancel();  
          }  
          return false;}}); 
        
        
    }
    
    
     public void UP(){
      //   int i = jTable1.getSelectedRow();
       int i = jListCustomers.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = jListCustomers.getModel().getSize() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < jListCustomers.getModel().getSize())) {
            // Solo seleccionamos si podemos.
        //setSelectedIndex(i);
         
           jListCustomers.getSelectionModel().setSelectionInterval(i, i);
        }        
//        
        
//////     
    }
//     public void setSelectedIndex(int i){
//       
//        // Seleccionamos
//        jListCustomers.getSelectionModel().setSelectionInterval(i,i);
//
//        // Hacemos visible la seleccion.
//       // Rectangle oRect =jListCustomers.getCellBounds(i, 0, true);
//      //  jListCustomers.scrollRectToVisible(oRect);
//    }
    
    public void DWN(){
        
//   
     // int i = jTable1.getSelectedRow();   
      int i =jListCustomers.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= jListCustomers.getModel().getSize() ) {
                i = jListCustomers.getModel().getSize() - 1;
            }
        }

        if ((i >= 0) && (i <jListCustomers.getModel().getSize())) {
            // Solo seleccionamos si podemos.
             //setSelectedIndex(i);
        jListCustomers.getSelectionModel().setSelectionInterval(i, i);
        }        
//
////       
//        
   }
    
    
    
     public void Cancel(){
          dispose();
     }
    
    
    public void OK(){
         selectedCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
        dispose();
    }
    
   public void  Execute(){
       executeSearch();
   }
   
     public void  Reset(){
     m_jtxtTaxID.reset();
        m_jtxtSearchKey.reset();
        m_jtxtName.reset();

        m_jtxtTaxID.activate();    

        cleanSearch();  
     }
    
    public void search(CustomerInfo customer) {
        
        if (customer == null || customer.getName() == null || customer.getName().equals("")) {
            
            m_jtxtTaxID.reset();
            m_jtxtSearchKey.reset();
            m_jtxtName.reset();

            m_jtxtTaxID.activate();    
            
            cleanSearch();
        } else {
            
            m_jtxtTaxID.setText(customer.getTaxid());
            m_jtxtSearchKey.setText(customer.getSearchkey());
            m_jtxtName.setText(customer.getName());

            m_jtxtTaxID.activate();
            
            executeSearch();
        }
    }
    
    private void cleanSearch() {
        jListCustomers.setModel(new MyListData(new ArrayList()));
    }
    
    public void executeSearch() {
        try {
            jListCustomers.setModel(new MyListData(lpr.loadData()));
            if (jListCustomers.getModel().getSize() > 0) {
                jListCustomers.setSelectedIndex(0);
            }
        } catch (BasicException e) {
            e.printStackTrace();
        }        
    }
    
    public Object createValue() throws BasicException {
        
        Object[] afilter = new Object[6];
        
        // TaxID
        if (m_jtxtTaxID.getText() == null || m_jtxtTaxID.getText().equals("")) {
            afilter[0] = QBFCompareEnum.COMP_NONE;
            afilter[1] = null;
        } else {
            afilter[0] = QBFCompareEnum.COMP_RE;
            afilter[1] = "%" + m_jtxtTaxID.getText() + "%";
        }
        
        // SearchKey
        if (m_jtxtSearchKey.getText() == null || m_jtxtSearchKey.getText().equals("")) {
            afilter[2] = QBFCompareEnum.COMP_NONE;
            afilter[3] = null;
        } else {
            afilter[2] = QBFCompareEnum.COMP_RE;
            afilter[3] = "%" + m_jtxtSearchKey.getText() + "%";
        }
        
        // Name
        if (m_jtxtName.getText() == null || m_jtxtName.getText().equals("")) {
            afilter[4] = QBFCompareEnum.COMP_NONE;
            afilter[5] = null;
        } else {
            afilter[4] = QBFCompareEnum.COMP_RE;
            afilter[5] = "%" + m_jtxtName.getText() + "%";
        }
        
        return afilter;
    } 

    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
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

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        m_jtxtName = new com.openbravo.editor.JEditorString();
        jLabel6 = new javax.swing.JLabel();
        m_jtxtSearchKey = new com.openbravo.editor.JEditorString();
        jLabel7 = new javax.swing.JLabel();
        m_jtxtTaxID = new com.openbravo.editor.JEditorString();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        m_jDown = new javax.swing.JButton();
        m_jUp = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCustomers = new javax.swing.JList();
        m_jKeys = new com.openbravo.editor.JEditorKeys();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jcmdOK = new javax.swing.JButton();
        jcmdCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("form.customertitle")); // NOI18N

        jPanel7.setPreferredSize(new java.awt.Dimension(100, 140));
        jPanel7.setLayout(null);

        jLabel5.setText(AppLocal.getIntString("label.prodname")); // NOI18N
        jPanel7.add(jLabel5);
        jLabel5.setBounds(20, 100, 140, 14);

        m_jtxtName.setNextFocusableComponent(m_jtxtTaxID);
        jPanel7.add(m_jtxtName);
        m_jtxtName.setBounds(160, 100, 220, 25);

        jLabel6.setText(AppLocal.getIntString("label.searchkey")); // NOI18N
        jPanel7.add(jLabel6);
        jLabel6.setBounds(20, 60, 140, 14);
        jPanel7.add(m_jtxtSearchKey);
        m_jtxtSearchKey.setBounds(160, 60, 220, 25);

        jLabel7.setText(AppLocal.getIntString("label.taxid")); // NOI18N
        jPanel7.add(jLabel7);
        jLabel7.setBounds(20, 20, 140, 14);
        jPanel7.add(m_jtxtTaxID);
        m_jtxtTaxID.setBounds(160, 20, 220, 25);

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel7.add(jPanel2);
        jPanel2.setBounds(410, -200, 190, 0);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel1);
        jLabel1.setBounds(390, 10, 200, 120);

        jPanel6.setFocusable(false);

        jButton1.setText(AppLocal.getIntString("button.clean")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/launch.png"))); // NOI18N
        jButton3.setText(AppLocal.getIntString("button.executefilter")); // NOI18N
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.setRequestFocusEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
        m_jUp.setFocusPainted(false);
        m_jUp.setFocusable(false);
        m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(m_jUp)
                .addGap(18, 18, 18)
                .addComponent(m_jDown)
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(m_jDown, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(m_jUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        m_jDown.setVisible(false);
        m_jUp.setVisible(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jListCustomers.setFocusable(false);
        jListCustomers.setRequestFocusEnabled(false);
        jListCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListCustomersMouseClicked(evt);
            }
        });
        jListCustomers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListCustomersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListCustomers);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel4.add(m_jKeys, java.awt.BorderLayout.LINE_END);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jcmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jcmdOK.setText(AppLocal.getIntString("Button.OK")); // NOI18N
        jcmdOK.setEnabled(false);
        jcmdOK.setFocusPainted(false);
        jcmdOK.setFocusable(false);
        jcmdOK.setMargin(new java.awt.Insets(8, 16, 8, 16));
        jcmdOK.setRequestFocusEnabled(false);
        jcmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOKActionPerformed(evt);
            }
        });
        jPanel1.add(jcmdOK);

        jcmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jcmdCancel.setText(AppLocal.getIntString("Button.Cancel")); // NOI18N
        jcmdCancel.setFocusPainted(false);
        jcmdCancel.setFocusable(false);
        jcmdCancel.setMargin(new java.awt.Insets(8, 16, 8, 16));
        jcmdCancel.setRequestFocusEnabled(false);
        jcmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jcmdCancel);

        jPanel8.add(jPanel1, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-613)/2, (screenSize.height-610)/2, 613, 610);
    }// </editor-fold>//GEN-END:initComponents
    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed

        selectedCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
        dispose();
        
    }//GEN-LAST:event_jcmdOKActionPerformed

    private void jcmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdCancelActionPerformed

        dispose();
        
    }//GEN-LAST:event_jcmdCancelActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        executeSearch();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jListCustomersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListCustomersValueChanged

        jcmdOK.setEnabled(jListCustomers.getSelectedValue() != null);

    }//GEN-LAST:event_jListCustomersValueChanged

    private void jListCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCustomersMouseClicked
        BufferedImage img;
      //  String temp;
      //  Image tempimg;
        ImageIcon icon;
        byte[] arr;
        if (evt.getClickCount() == 1) {
            selectedCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
           // dispose();
            jLabel1.setEnabled(true);
            Session m_App=LookupUtilityImpl.getInstance(null).getAppView().getSession();
            try{
             Object obj[]=(Object[])   new StaticSentence(m_App
                        , "SELECT IMAGE FROM CUSTOMERS WHERE CUSTOMERS.SEARCHKEY = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.IMAGE})).find(selectedCustomer.getSearchkey());
            if(obj!=null)
            {
                img=(BufferedImage)obj[0];
                icon=new ImageIcon(img);
                jLabel1.setIcon(icon);
            }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                //temp="aaa";
            }
        }
        else if(evt.getClickCount() == 2)
        {
            selectedCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
            dispose();
        }
        
    }//GEN-LAST:event_jListCustomersMouseClicked


private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 
        m_jtxtTaxID.reset();
        m_jtxtSearchKey.reset();
        m_jtxtName.reset();

        m_jtxtTaxID.activate();    

        cleanSearch();           
}//GEN-LAST:event_jButton1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jListCustomers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jcmdCancel;
    private javax.swing.JButton jcmdOK;
    private javax.swing.JButton m_jDown;
    private com.openbravo.editor.JEditorKeys m_jKeys;
    private javax.swing.JButton m_jUp;
    private com.openbravo.editor.JEditorString m_jtxtName;
    private com.openbravo.editor.JEditorString m_jtxtSearchKey;
    private com.openbravo.editor.JEditorString m_jtxtTaxID;
    // End of variables declaration//GEN-END:variables
}
