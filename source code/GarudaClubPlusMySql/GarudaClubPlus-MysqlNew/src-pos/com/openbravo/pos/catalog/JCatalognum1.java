

package com.openbravo.pos.catalog;

import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.util.ThumbNailBuilder;import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;

import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.StockManagement;
import com.openbravo.pos.sales.JPanelButtonsnum1;
import com.openbravo.pos.sales.JPanelTicketnum1;
import com.openbravo.pos.sales.JPanelTicketnum1.ScriptArg;
import com.openbravo.pos.sales.JPanelTicketnum1.ScriptObject;
import com.openbravo.pos.sales.JTicketLines1;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.sales.TicketsEditor;
import com.openbravo.pos.sales.restaurant.JIntroPageRestnum1;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import javax.swing.KeyStroke;


/**
 *

 */
public class JCatalognum1 extends JPanel implements ListSelectionListener, CatalogSelector  {
    
    protected EventListenerList listeners = new EventListenerList();
    private java.util.List<javax.swing.JTextField> textboxlist=new ArrayList<javax.swing.JTextField>();
    private java.util.List<ProductInfoExt> pacclist=new ArrayList<ProductInfoExt>();
    private DataLogicSales m_dlSales;
    private javax.swing.JTextField activetextbox;

       private TaxesLogic taxeslogic;
       private Set<String> m_categoriesset = new HashSet<String>();
       private boolean pricevisible;
       private boolean taxesincluded;
       public String cid = null;
       private int edited=0;
       protected TicketInfo m_oTicket;
       protected AppView m_App;
       private ListKeyed taxcollection;
       private int m_iNumberStatusInput;
       private int m_iNumberStatusPor;
       private String wname;
           
            
            
            
            
    // Set of Products panels
    private Map<String, ProductInfoExt> m_productsset = new HashMap<String, ProductInfoExt>();

    
    // Set of Categoriespanels
    // private Set<String> m_categoriesset = new HashSet<String>();
         //protected TicketInfo m_oTicket
    private final static int NUMBERZERO = 0;
    private final static int NUMBERVALID = 1;
    private final static int NUMBER_INPUTZERO = 0;
    private final static int NUMBER_INPUTZERODEC = 1;
    private final static int NUMBER_INPUTINT = 2;
    private final static int NUMBER_INPUTDEC = 3;
    private final static int NUMBER_PORZERO = 4;
    private final static int NUMBER_PORZERODEC = 5;
    private final static int NUMBER_PORINT = 6;
    private final static int NUMBER_PORDEC = 7;
    protected Object m_oTicketExt;
    private ThumbNailBuilder tnbbutton;
    private ThumbNailBuilder tnbcat;
    protected JPanelButtonsnum1 m_jbtnconfig;
    private CategoryInfo showingcategory = null;
    private ComboBoxValModel fModel;
    protected JTicketLines1 m_ticketlines;
    private java.util.List<ProductInfoExt> categories;
    protected DataLogicSystem dlSystem;
    private AppView mapps;
    /** Creates new form JCatalog */
     
     
     
     
    public JCatalognum1(DataLogicSales dlSales) {
        this(dlSales, false, false, 64, 54);
    }
    
    public JCatalognum1(DataLogicSales dlSales, boolean pricevisible, boolean taxesincluded, int width, int height) {
       
        m_dlSales = dlSales;
        this.pricevisible = pricevisible;
        this.taxesincluded = taxesincluded;
   
        
       initComponents();
      
       // ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
       m_jUp.setToolTipText("PAGE UP");
       m_jDown.setToolTipText("PAGE DOWN");
    
//         m_ticketlines = new JTicketLines1(dlSystem.getResourceAsXML("Ticket.Line"));
       // m_jPanelCentral.add(m_ticketlines, java.awt.BorderLayout.CENTER);
       
        
        
        m_jListCategories.addListSelectionListener(this);                
        m_jscrollcat.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));
        
        tnbcat = new ThumbNailBuilder(32, 32, "com/openbravo/images/folder_yellow.png");           
        tnbbutton = new ThumbNailBuilder(width, height, "com/openbravo/images/package.png");
    }
    
    public Component getComponent() {
        return this;
    }
    
   

  
    
    public void showCatalogPanel(String id) {
           
        if (id == null) {
             
          //  showRootCategoriesPanel();
        } else {            
            showProductPanel(id);
            
        }
    }
    
    public void loadCatalog(java.util.List<CategoryInfo> categories) throws BasicException {
       
        m_productsset.clear();        
       showingcategory = null;
       taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
       m_jListCategories.removeAll();
       m_jListCategories.setCellRenderer(new SmallCategoryRenderer());
       m_jListCategories.setModel(new CategoriesListModel(categories)); // aCatList
       
        if (m_jListCategories.getModel().getSize() == 0) {
            m_jscrollcat.setVisible(false);
            jPanel2.setVisible(false);
        } else {
            m_jscrollcat.setVisible(true);
            jPanel2.setVisible(true);
             m_jListCategories.setEnabled(true);
             
            m_jListCategories.setSelectedIndex(0);
  ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
      Object  accid1 =  m_jListCategories.getSelectedValue();
            wname=accid1.toString();
         
         // Object[] obj1 = (Object[]) new StaticSentence(mapps.getSession(),
           Object[] obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                        , "SELECT prefix FROM LOCATIONS WHERE NAME = ?"
                        ,SerializerWriteString.INSTANCE
                        , new SerializerReadBasic(new Datas[]{Datas.STRING})).find( wname);
            if(obj1[0]!=null){          
                wname= obj1[0].toString();
                jTextField1.setText(wname);
            }
            
           //  jList1.setSelectedIndex(wname);
               
                 //fireSelectedProduct(prod);
            
             
           m_jListCategories.addKeyListener(new java.awt.event.KeyAdapter() { 
     public void actionPerformed(ActionEvent ae){  
              
        doUP();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  //code for delete
           
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_PAGE_UP)  )
               
                doUP();  
          }  
          if((e.getKeyCode() == KeyEvent.VK_ENTER)) {
           jTextField1.requestFocus();
           
          }
          
          return false;}}); 
    
    
            
  m_jListCategories.addKeyListener(new java.awt.event.KeyAdapter() { 
      public void actionPerformed(ActionEvent ae){  
              
        doDown();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  //code for delete
           
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)   )
               
                doDown();  
          }  
          if((e.getKeyCode() == KeyEvent.VK_ENTER)) {
         jTextField1.requestFocus();
          }
          return false;}}); 
    
        }
     
//     jTextField1.setText("1233");
//     
////      
//       java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//              jTextField1.requestFocus();
//            }
//       });
//     
 //}
        
       jList1.addKeyListener(new java.awt.event.KeyAdapter() {
      public void actionPerformed(ActionEvent ae){  
              
       // doUPCat()
                ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  //code for delete
           
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_F1)  )
               
               // doUPCat()
                ;  
          } 
           if((e.getKeyCode() == KeyEvent.VK_LEFT)) {
         jTextField1.requestFocus();
          }
          
          return false;}}); 
    
    
//            
//  jList1.addKeyListener(new java.awt.event.KeyAdapter() { 
//      public void actionPerformed(ActionEvent ae){  
//              
//        doDownCat();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  //code for delete
//           
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_F2)   )
//               
//                doDownCat();  
//          }  
//           if((e.getKeyCode() == KeyEvent.VK_LEFT)) {
//         jTextField1.requestFocus();
//          }
//         
//          return false;}}); 
//        
//    }
//    }
    
    
//     public void doUPCat(){
//     int i1 = jList1.getSelectionModel().getMinSelectionIndex();
//       int i=i1+1;
//        if (i < 0){
//            i = jList1.getModel().getSize() - 1; // No hay ninguna seleccionada
//        } else {
//            i --;
//            if (i < 0) {
//                i = 0;
//            }
//        }
//
//        if ((i >= 0) && (i < jList1.getModel().getSize())) {
//            // Solo seleccionamos si podemos.
//            jList1.getSelectionModel().setSelectionInterval(i, i);
//        } 
//       
// }
     
     
//      public void  doDownCat(){
//     int i1 = jList1.getSelectionModel().getMaxSelectionIndex();
//     int i=i1-1;
//        if (i < 0){
//            i =  0; // No hay ninguna seleccionada
//        } else {
//            i ++;
//            if (i >= jList1.getModel().getSize() ) {
//                i = jList1.getModel().getSize() - 1;
//            }
//        }
//
//        if ((i >= 0) && (i < jList1.getModel().getSize())) {
//            // Solo seleccionamos si podemos.
//            jList1.getSelectionModel().setSelectionInterval(i, i);
//        }       
   //  m_jDown.transferFocus(); */
 }
 //   
  public void doUP(){
      if(JIntroPageRestnum1.cat){
     int i = m_jListCategories.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = m_jListCategories.getModel().getSize() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < m_jListCategories.getModel().getSize())) {
            // Solo seleccionamos si podemos.
            m_jListCategories.getSelectionModel().setSelectionInterval(i, i);
        } 
      }
       Object  accid1 =  m_jListCategories.getSelectedValue();
            wname=accid1.toString();
         
         // Object[] obj1 = (Object[]) new StaticSentence(mapps.getSession(),
           Object[] obj1;
        try {
            obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                , "SELECT prefix FROM LOCATIONS WHERE NAME = ?"
                ,SerializerWriteString.INSTANCE
                , new SerializerReadBasic(new Datas[]{Datas.STRING})).find( wname);
             if(obj1[0]!=null){          
                wname= obj1[0].toString();
                jTextField1.setText(wname);
            }
        } catch (BasicException ex) {
            Logger.getLogger(JCatalognum1.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
 }
 
 
 public void  doDown(){
     if(JIntroPageRestnum1.cat){
     int i = m_jListCategories.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= m_jListCategories.getModel().getSize() ) {
                i = m_jListCategories.getModel().getSize() - 1;
            }
        }

        if ((i >= 0) && (i < m_jListCategories.getModel().getSize())) {
            // Solo seleccionamos si podemos.
            m_jListCategories.getSelectionModel().setSelectionInterval(i, i);
        }       
   //  m_jDown.transferFocus();
      
     }
      Object  accid1 =  m_jListCategories.getSelectedValue();
            wname=accid1.toString();
         
         // Object[] obj1 = (Object[]) new StaticSentence(mapps.getSession(),
           Object[] obj1;
        try {
            obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                , "SELECT prefix FROM LOCATIONS WHERE NAME = ?"
                ,SerializerWriteString.INSTANCE
                , new SerializerReadBasic(new Datas[]{Datas.STRING})).find( wname);
             if(obj1[0]!=null){          
                wname= obj1[0].toString();
                jTextField1.setText(wname);
            }
        } catch (BasicException ex) {
            Logger.getLogger(JCatalognum1.class.getName()).log(Level.SEVERE, null, ex);
        }
       
 }

    
    public void setComponentEnabled(boolean value) {

        m_jscrollcat.setEnabled(value);
        m_jUp.setEnabled(value);
        m_jDown.setEnabled(value);
        m_lblIndicator.setEnabled(value);
        m_btnBack.setEnabled(value);
       jList1.setEnabled(value);
        m_jListCategories.setEnabled(value);
        m_jListCategories.isCursorSet();//newly added code
        //setCursor(jTextField1);
      

        synchronized (jList1.getTreeLock()) {
          
            int compCount =jList1.getComponentCount();
            for (int i = 0 ; i < compCount ; i++) {
              jList1.getComponent(i).setEnabled(value);
               
            }
            
//           
        }
     
        this.setEnabled(value);
    }
    
    public void addActionListener(ActionListener l) {
      
        jTextField1.addActionListener(new ActionListener(){
            
        public void actionPerformed(ActionEvent e){
          //jTextField1.requestFocus();
            jTextField1.transferFocus();
       }
       
});
        listeners.add(ActionListener.class, l);
    }
    public void removeActionListener(ActionListener l) {
        listeners.remove(ActionListener.class, l);
    }

    
    
    
    
    private class ItemsListModel extends AbstractListModel {
        private java.util.List items;
        public ItemsListModel(java.util.List items) {
            this.items = items;
        }
        public int getSize() {
            return items.size();
        }
        public Object getElementAt(int i) {
            return items.get(i);
            }
            private class SelectedAction implements ActionListener {
        private ProductInfoExt prod;
        public SelectedAction(ProductInfoExt prod) {
            this.prod = prod;
        }
        public void actionPerformed(ActionEvent e) {
            fireSelectedProduct(prod);
        }
    }
            
        
    }

    public void valueChanged(ListSelectionEvent evt) {
         if (!evt.getValueIsAdjusting()) {
            int i = jList1.getSelectedIndex();
            if (i >= 0) {
                // Lo hago visible...
                Rectangle oRect =  jList1.getCellBounds(i, i);
               jList1.scrollRectToVisible(oRect);       
            }
        }
    }  
    
   protected void fireSelectedProduct(ProductInfoExt prod) {
        EventListener[] l = listeners.getListeners(ActionListener.class);
        ActionEvent e = null;
        for (int i = 0; i < l.length; i++) {
            if (e == null) {
                e = new ActionEvent(prod, ActionEvent.ACTION_PERFORMED, prod.getID());
               
            }
            ((ActionListener) l[i]).actionPerformed(e);	       
        }
    }   
   
   
 private int selectCategoryPanel(String catid,String rfc) {

        try {
            // Load categories panel if not exists
            System.out.println(catid);
            StockManagement sm = new StockManagement();
          
                 JCatalogTab jcurrTab = new JCatalogTab();
                jcurrTab.applyComponentOrientation(getComponentOrientation());
               jList1.add(jcurrTab, catid);
                m_categoriesset.add(catid);
//            
                 Session ses=LookupUtilityImpl.getInstance(null).getAppView().getSession();
                 String pid="";
                 Object pid1=catid;
                 while(pid1!=null){
                      Object[] obj1=(Object[]) new StaticSentence(ses
                      , "SELECT PARENTID FROM CATEGORIES WHERE ID= ? "
                      , SerializerWriteString.INSTANCE
                      , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(pid1);
                    
                      
                           java.util.List<ProductInfoExt> categories =  m_dlSales.getProductCatalog(catid,rfc);
                             fModel = new ComboBoxValModel(categories);
                            jList1.setModel(fModel);
                      if(pid1!=null && obj1!=null)
                        pid=pid1.toString();
                       pid1=obj1[0];
                       
                 Object[] obj=(Object[]) new StaticSentence(ses,
                "SELECT PRCATEGORIES FROM PEOPLE WHERE NAME = ?"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                int flag=0;
                 String[] prcat;
                if(obj!=null || obj[0]!=null)
                {
                    prcat=obj[0].toString().split("#");
                }
                else
                    prcat=new String[1];
             
                     if(obj!=null && obj[0]!=null)
                    {
                        for(int i=0;i<prcat.length;i++)
                        {
                           
                          if( catid.equals(prcat[i]) || pid.equals(prcat[i]))
                          {

                              flag=1;
                              break;

                          }
                           
                        }

                    }
                      if(flag==1)
                    {
                  

                
                  java.util.List<ProductInfoExt> products = m_dlSales.getProductCatalog(catid,rfc);
                for (ProductInfoExt prod : products) {
                  
                     jcurrTab.addButton(new ImageIcon(tnbbutton.getThumbNailText(prod.getImage(), getProductLabel(prod))), new SelectedAction(prod));
                     jTextField1.equals(rfc.toString());
                     
                    flag=0;
                }
          
            categories.subList(flag, WIDTH);
                   }
            }  
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notactive"), e));
        }
        return 1;
    }
   private String getProductLabel(ProductInfoExt product) {

        if (pricevisible) {
            if (taxesincluded) {
                TaxInfo tax = taxeslogic.getTaxInfo(product.getTaxCategoryInfo());
                return "<html><center>" + product.getName() + "<br>" + product.printPriceSellTax(tax);
            } else {
                return "<html><center>" + product.getName() + "<br>" + product.printPriceSell();
            }
        } else {
            return product.getName();
        }
    }

    private void selectIndicatorPanel(Icon icon, String label) {

        m_lblIndicator.setText(label);
        m_lblIndicator.setIcon(icon);
        
        // Show subcategories panel
        CardLayout cl = (CardLayout)(m_jCategories.getLayout());
        cl.show(m_jCategories, "subcategories");
    }
    
//   
    private void showSubcategoryPanel(CategoryInfo category) {
        
        selectIndicatorPanel(new ImageIcon(tnbbutton.getThumbNail(category.getImage())), category.getName());
        selectCategoryPanel(category.getID(),category.getName());
        showingcategory = category;
    }
     private void selectIndicatorCategories() {
         }
    private void showRootCategoriesPanel() {
        selectIndicatorCategories();
        // Show selected root category
        CategoryInfo cat = (CategoryInfo) jList1.getSelectedValue();
        
        if (cat != null) {            
           selectCategoryPanel(cat.getID(),cat.getName());
        }
        showingcategory = null;
    }
    
    
    
    private void showProductPanel(String id) {
            
        ProductInfoExt product = m_productsset.get(id);

        if (product == null) {
            if (m_productsset.containsKey(id)) {
                // It is an empty panel
                if (showingcategory == null) {
                                         
                } else {
                    showSubcategoryPanel(showingcategory);
                }
            } else {
                try {
                    // Create  products panel
                    java.util.List<ProductInfoExt> products = m_dlSales.getProductComments(id);

                    if (products.size() == 0) {
                        // no hay productos por tanto lo anado a la de vacios y muestro el panel principal.
                        m_productsset.put(id, null);
                        if (showingcategory == null) {
                            //showRootCategoriesPanel();                         
                        } else {
                            showSubcategoryPanel(showingcategory);
                        }
                    } else {

                        // Load product panel
                        product = m_dlSales.getProductInfo(id);
                        m_productsset.put(id, product);

                        JCatalogTab jcurrTab = new JCatalogTab(); 
                        
                        jcurrTab.applyComponentOrientation(getComponentOrientation());
                      jList1.add(jcurrTab, "PRODUCT." + id); 
                      

                        // Add products
                        for (ProductInfoExt prod : products) {
                            }                       

                        selectIndicatorPanel(new ImageIcon(tnbbutton.getThumbNail(product.getImage())), product.getName());

                       CardLayout cl = (CardLayout)(jList1.getLayout());
                        cl.show(jList1, "PRODUCT." + id); 
                    }
                } catch (BasicException eb) {
                    eb.printStackTrace();
                    m_productsset.put(id, null);
                    if (showingcategory == null) {
                                          
                    } else {
                        showSubcategoryPanel(showingcategory);
                    }
                }
            }
        } 
    }
    
    private class SelectedAction implements ActionListener {
        private ProductInfoExt prod;
        public SelectedAction(ProductInfoExt prod) {
            this.prod = prod;
        }
        public void actionPerformed(ActionEvent e) {
            fireSelectedProduct(prod);
        }
    }
    
    private class SelectedCategory implements ActionListener {
        private CategoryInfo category;
        public SelectedCategory(CategoryInfo category) {
            this.category = category;
        }
        public void actionPerformed(ActionEvent e) {
            showSubcategoryPanel(category);
        }
    }
    
    private class CategoriesListModel extends AbstractListModel {
        private java.util.List m_aCategories;
        public CategoriesListModel(java.util.List aCategories) {
            m_aCategories = aCategories;
        }
        public int getSize() { 
            return m_aCategories.size(); 
        }
        public Object getElementAt(int i) {
            return m_aCategories.get(i);
        }    
    }
//     
    private class SmallCategoryRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, null, index, isSelected, cellHasFocus);
            CategoryInfo cat = (CategoryInfo) value;
            setText(cat.getName());
            setIcon(new ImageIcon(tnbcat.getThumbNail(cat.getImage())));
            return this;
        }      
    }            
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jCategories = new javax.swing.JPanel();
        m_jRootCategories = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        m_jUp = new javax.swing.JButton();
        m_jDown = new javax.swing.JButton();
        m_jscrollcat = new javax.swing.JScrollPane();
        m_jListCategories = new javax.swing.JList();
        m_jSubCategories = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        m_lblIndicator = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        m_btnBack = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        m_jCategories.setLayout(new java.awt.CardLayout());

        m_jRootCategories.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));

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
        m_jDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDownActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jUp)
            .add(m_jDown)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(m_jUp)
                .add(5, 5, 5)
                .add(m_jDown))
        );

        m_jscrollcat.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        m_jscrollcat.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        m_jscrollcat.setPreferredSize(new java.awt.Dimension(210, 0));

        m_jListCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        m_jListCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        m_jListCategories.setDragEnabled(true);
        m_jListCategories.setFocusable(false);
        m_jListCategories.setNextFocusableComponent(jTextField1);
        m_jListCategories.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                m_jListCategoriesValueChanged(evt);
            }
        });
        m_jListCategories.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                m_jListCategoriesMouseMoved(evt);
            }
        });
        m_jListCategories.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                m_jListCategoriesFocusGained(evt);
            }
        });
        m_jListCategories.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                m_jListCategoriesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                m_jListCategoriesKeyReleased(evt);
            }
        });
        m_jscrollcat.setViewportView(m_jListCategories);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(m_jscrollcat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(m_jscrollcat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 211, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        m_jRootCategories.add(jPanel2, java.awt.BorderLayout.CENTER);

        m_jCategories.add(m_jRootCategories, "rootcategories");

        m_jSubCategories.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        m_lblIndicator.setText("jLabel1"); // NOI18N
        jPanel4.add(m_lblIndicator, java.awt.BorderLayout.NORTH);

        m_jSubCategories.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        m_btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/3uparrow.png"))); // NOI18N
        m_btnBack.setFocusPainted(false);
        m_btnBack.setFocusable(false);
        m_btnBack.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_btnBack.setRequestFocusEnabled(false);
        m_btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_btnBackActionPerformed(evt);
            }
        });
        jPanel5.add(m_btnBack);

        jPanel1.add(jPanel5, java.awt.BorderLayout.NORTH);

        m_jSubCategories.add(jPanel1, java.awt.BorderLayout.LINE_END);

        m_jCategories.add(m_jSubCategories, "subcategories");

        add(m_jCategories, java.awt.BorderLayout.LINE_START);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.setFocusable(false);
        jList1.setRequestFocusEnabled(false);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jList1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);
        jList1.setFocusable(true);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText("Item Code"); // NOI18N

        org.jdesktop.layout.GroupLayout panel1Layout = new org.jdesktop.layout.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel1Layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );

        add(panel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void m_btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_btnBackActionPerformed

      showRootCategoriesPanel();        
        
    }//GEN-LAST:event_m_btnBackActionPerformed

    private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed

        int i = m_jListCategories.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= m_jListCategories.getModel().getSize() ) {
                i = m_jListCategories.getModel().getSize() - 1;
            }
        }

        if ((i >= 0) && (i < m_jListCategories.getModel().getSize())) {
            // Solo seleccionamos si podemos.
            m_jListCategories.getSelectionModel().setSelectionInterval(i, i);
        }        
        
    }//GEN-LAST:event_m_jDownActionPerformed

    private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed

        int i = m_jListCategories.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = m_jListCategories.getModel().getSize() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < m_jListCategories.getModel().getSize())) {
            // Solo seleccionamos si podemos.
            m_jListCategories.getSelectionModel().setSelectionInterval(i, i);
        }        
        
        
    }//GEN-LAST:event_m_jUpActionPerformed

    private void m_jListCategoriesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_m_jListCategoriesValueChanged
        
        if (!evt.getValueIsAdjusting()) {
            CategoryInfo cat = (CategoryInfo) m_jListCategories.getSelectedValue();
            if (cat != null) {
                selectCategoryPanel(cat.getID(),cat.getName());
            }
       
        }
    
    }//GEN-LAST:event_m_jListCategoriesValueChanged

private void product1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_product1ValueChanged
// TODO add your handling code here:
if (!evt.getValueIsAdjusting()) {
            CategoryInfo cat = (CategoryInfo) jList1.getSelectedValue();
            if (cat != null) {
                selectCategoryPanel(cat.getID(),cat.getName());
            }
        }
   
    
}//GEN-LAST:event_product1ValueChanged

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
       // TODO add your handling code here:
   //shiv:ad
      jTextField1.requestFocus();
    
    try {
          
            CategoryInfo cat = (CategoryInfo) m_jListCategories.getSelectedValue();
            String rfc=jTextField1.getText().toUpperCase();
            edited=1;
            jList1.removeAll();
            jTextField1=(JTextField)evt.getSource();
            categories=  m_dlSales.getProductCatalogs(cat.getID(),rfc);
           
              
            
            jList1.setVisible(true);
            jScrollPane1.setVisible(true);
            jList1.removeAll();
            jList1.setModel(new ItemsListModel(categories));
            jList1.setSelectedIndex(0);
            
           if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
              java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   jList1.requestFocus();
                   //jTextField1.setText(wname);
                }
            });
            }
            jPanel1.validate();
        } catch (BasicException ex) {
            JOptionPane.showMessageDialog(this, "Please select correct item from the list");
            Logger.getLogger(JCatalognum1.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exception"+ex);
       
        jPanel1.validate();
            jPanel1.repaint();
        }

}//GEN-LAST:event_jTextField1KeyReleased

private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
// TODO add your handling code here:
//ad:shiv 
     if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                Object  accid1 =  m_jListCategories.getSelectedValue();
                  wname=accid1.toString();
               
               // Object[] obj1 = (Object[]) new StaticSentence(mapps.getSession(),
                 Object[] obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                              , "SELECT prefix FROM LOCATIONS WHERE NAME = ?"
                              ,SerializerWriteString.INSTANCE
                              , new SerializerReadBasic(new Datas[]{Datas.STRING})).find( wname);

           if(obj1[0]!=null){          
                      wname= obj1[0].toString();
                      jTextField1.setText(wname);
                      
             // try {
               // Object  accid1 =  m_jListCategories.getSelectedValue();
                 // wname=accid1.toString();
               
               // Object[] obj1 = (Object[]) new StaticSentence(mapps.getSession(),
                 //Object[] obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                          //    , "SELECT prefix FROM LOCATIONS WHERE NAME = ?"
                            //  ,SerializerWriteString.INSTANCE
                             // , new SerializerReadBasic(new Datas[]{Datas.STRING})).find( wname);
                     // if (obj1 != null && obj1[0]!=null) {
                          // The categories model
             
                        //"SELECT prefix from locations where name=?",null, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                        //find(m_jListCategories.getSelectedValue());
                        //SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find() ;
                      
                        //new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{wname});
                          // if(obj1[0]!=null){          
                     // wname= obj1[0].toString();
                    //  jTextField1.setText(wname);
                     // String b=wname.substring(0,5);
             // jTextField1.setText(b);
                     // jList1.getSelectedValue();
               
               
                ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
                 fireSelectedProduct(prod);
                  
                   //jTextField1.setText(null);
               jTextField1.setText(wname);
                    jTextField1.transferFocus();
                         
              
          }
              
         
      //      try{
      //     String a = jList1.getSelectedValue().toString().toUpperCase();
      //     String b=a.substring(0, 5);
      //        jTextField1.setText(b);
      //          ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
      //           fireSelectedProduct(prod);
      //            
      //             jTextField1.setText(null);
      //              jTextField1.transferFocus();
      //      }catch (Exception e){
      //        e.printStackTrace();
      //        }
              
          // }

       } catch (BasicException ex) {
                Logger.getLogger(JCatalognum1.class.getName()).log(Level.SEVERE, null, ex);
            }
  
     }
     
   
}//GEN-LAST:event_jTextField1KeyPressed

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
     
            // TODO add your handling code here:
              
//              ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
//          
//          fireSelectedProduct(prod);
//          jTextField1.transferFocus();

           
}//GEN-LAST:event_jTextField1ActionPerformed

private void jList1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyReleased
// TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           //  activetextbox.setBackground(Color.lightGray);
            //  particulars1.setText(jList1.getSelectedValue().toString());
           // activetextbox.setText(jList1.getSelectedValue().toString());
            int index=textboxlist.indexOf(activetextbox);
            focusindex=index+1;
            if(index/2>pacclist.size()-1)
            pacclist.add(index/2,(ProductInfoExt)jList1.getSelectedValue() );
            else
                pacclist.set(index/2,(ProductInfoExt)jList1.getSelectedValue() );
            // flag=1;
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    textboxlist.get(focusindex).requestFocus();
                }
            });
            // acclist.add(num,(AccountMaster)jList1.getSelectedValue());
           // jList1.setVisible(false);
          //  jScrollPane1.setVisible(false);
        }
    
    
    
// 
//       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//     java.awt.EventQueue.invokeLater(new Runnable() {
//        public void run() {
//                 //    jList1.requestFocus();
//                   jTextField1.requestFocus();
//                }
//            });
//    
//            
//       }
       // ending loop
//         try {
//      JTextField jT= new JTextField();
//       jT.addActionListener(getActionForKeyStroke(KeyStroke.getKeyStrokeForEvent(evt)));
//                CategoryInfo cat = (CategoryInfo) m_jListCategories.getSelectedValue();
//                String rfc=jTextField1.getText().toUpperCase();
//                
//             edited=1;
//                    jList1.removeAll();
//          //   jTextField1=(JTextField)evt.getSource();
//           if(evt.getKeyCode()!=KeyEvent.VK_LEFT  && evt.getKeyCode()!= KeyEvent.VK_ESCAPE && evt.getKeyCode()!=KeyEvent.VK_ENTER && evt.getKeyCode()!=KeyEvent.VK_CONTROL ){
//            categories=  m_dlSales.getProductCatalogs(cat.getID(),rfc);
//           jList1.setVisible(true);
//            jScrollPane1.setVisible(true);
//            jList1.removeAll();
//         //   jList1.setModel(new ItemsListModel(categories));
//         //   jList1.setSelectedIndex(0);
//                 
//            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//              java.awt.EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                   //jList1.requestFocus();
//                 jTextField1.transferFocus();
//                    
//                          
//                 
//                }
//            });
//              
//          }
//       /*    if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
//              java.awt.EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                   jList1.requestFocus();
//                                       
//                }
//            });
//               
//              
//          }*/
//            
//            jPanel1.validate();
//              jPanel1.repaint();     
//                       }
//      
//    
//           
//       } catch (BasicException ex) {
//            Logger.getLogger(JCatalognum1.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("exception"+ex);
//       
//        jPanel1.validate();
//            jPanel1.repaint();
//        } 
//   
}//GEN-LAST:event_jList1KeyReleased

private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
// TODO add your handling code here:

     /////////////////////////////////////////////////////////
    /*      int index=textboxlist.indexOf(activetextbox);
        focusindex=index+1;
        if(index/2>pacclist.size()-1)
        pacclist.add(index/2,(ProductInfoExt)jList1.getSelectedValue() );
        else
        pacclist.set(index/2,(ProductInfoExt)jList1.getSelectedValue() );
          String a = jList1.getSelectedValue().toString().toUpperCase();
          String b=a.substring(0, 5);
      
         jTextField1.setText(b);
          ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
          
          fireSelectedProduct(prod);
         java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
             jList1.requestFocus();
            }
    
      });  */
        
      ///////////////////////////////////////////////////////  
}//GEN-LAST:event_jList1MouseClicked

private void m_jListCategoriesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jListCategoriesKeyPressed
// TODO add your handling code here:
     
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
             
//            CategoryInfo cat = (CategoryInfo) m_jListCategories.getSelectedValue();
//            if (cat != null) {
//                selectCategoryPanel(cat.getID(),cat.getName());
//            }

//      
//         
             m_jListCategories.transferFocus();
             jList1.requestFocus();
        }
         //else
           // m_jListCategories.transferFocus();
             
       // }
//        
}//GEN-LAST:event_m_jListCategoriesKeyPressed

private void m_jListCategoriesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jListCategoriesKeyReleased
// TODO add your handling code here:
      if( evt.getKeyCode()!=KeyEvent.VK_UP && evt.getKeyCode()!=KeyEvent.VK_DOWN && evt.getKeyCode()!= KeyEvent.VK_ESCAPE && evt.getKeyCode()!=KeyEvent.VK_CONTROL){
         CategoryInfo cat = (CategoryInfo) m_jListCategories.getSelectedValue();
            if (cat != null) {
                selectCategoryPanel(cat.getID(),cat.getName());
            }
        }
//       m_jListCategories.addActionListener(new ActionListener(){
//        public void actionPerformed(ActionEvent e){
     // m_jListCategories.transferFocus();
}//GEN-LAST:event_m_jListCategoriesKeyReleased

private void m_jListCategoriesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_jListCategoriesMouseMoved
// TODO add your handling code here:
}//GEN-LAST:event_m_jListCategoriesMouseMoved

private void m_jListCategoriesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_m_jListCategoriesFocusGained
// TODO add your handling code here:

}//GEN-LAST:event_m_jListCategoriesFocusGained

private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
// TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
try{
     //String a = jList1.getSelectedValue().toString().toUpperCase();
     //String b=a.substring(0, 5);
       // jTextField1.setText(b);
          ProductInfoExt prod =  (ProductInfoExt) jList1.getSelectedValue();
           fireSelectedProduct(prod);
             
              jTextField1.setText(wname);
              jTextField1.transferFocus();
       
}catch (Exception e){
        e.printStackTrace();
        }
       }
}//GEN-LAST:event_jList1KeyPressed



private int focusindex;

    
    
    // Variables declaration - do not modify                     
      
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton m_btnBack;
    private javax.swing.JPanel m_jCategories;
    private javax.swing.JButton m_jDown;
    private javax.swing.JList m_jListCategories;
    private javax.swing.JPanel m_jRootCategories;
    private javax.swing.JPanel m_jSubCategories;
    private javax.swing.JButton m_jUp;
    private javax.swing.JScrollPane m_jscrollcat;
    private javax.swing.JLabel m_lblIndicator;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
 
 
 }

