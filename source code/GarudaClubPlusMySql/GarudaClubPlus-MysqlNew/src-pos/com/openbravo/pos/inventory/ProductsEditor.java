
package com.openbravo.pos.inventory;

import com.openbravo.data.loader.Session;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.CategoryInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.openbravo.pos.inventory.ProductsApprovalTableModel;
import com.openbravo.pos.inventory.ProductsApprovalTableModel.PrdApvInfo;
import com.openbravo.pos.inventory.ProductsApproval;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.openbravo.pos.inventory.ProductRequestList ;//added by pratima
/**
 *
 * @author adrianromero
 */
public class ProductsEditor extends JPanel implements EditorRecord {
     private ProductRequestList ProductRequestListObj;  
    private SentenceList m_sentcat;
    private SentenceList m_sentprcat;

    private ComboBoxValModel m_CategoryModel;
    private ComboBoxValModel m_prModel;

    private SentenceList taxcatsent;
    private SentenceList taxcatsent2;
    private SentenceList taxcatsent3;
    
    private ComboBoxValModel taxcatmodel;  
    private ComboBoxValModel taxcatmodel2;  
    private ComboBoxValModel taxcatmodel3;  
    
    
    
    private SentenceList taxsent;
    private TaxesLogic taxeslogic;
    
    private ComboBoxValModel m_CodetypeModel;
    
     private SentenceList m_sentunit;
     private ComboBoxValModel m_UnitModel;
     private ComboBoxValModel accountModel;
     private ComboBoxValModel saccountModel;//sales account model
    private Object m_id;
    private Object unittype;
    private boolean reportlock = false;
    private Session s;
    private DataLogicFacilities dlfac;
    private DataLogicSales m_dlSales;
    private SentenceList m_sentloc;
    private ComboBoxValModel m_warehousemodel;
    private List<CategoryInfo> catinfo;
    private boolean flag=false;
     private ProductsApprovalTableModel ProductsApproval_Table_Model;
    private AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
    static boolean productcatfalg;
    /** Creates new form JEditProduct */
    public ProductsEditor(DataLogicSales dlSales, DirtyManager dirty,AppView app) {
        initComponents();
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate") ;
         m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        // The taxes sentence
        taxsent = dlSales.getTaxList();
        // The categories model
        m_sentcat = dlSales.getCategoriesList();
        m_CategoryModel = new ComboBoxValModel();
        m_sentloc = dlSales.getLocationsList();
        m_warehousemodel = new ComboBoxValModel();

        //print category model
        m_sentprcat = dlSales.getprCategoriesList();
        m_prModel = new ComboBoxValModel();
        
        // The taxes model
        taxcatsent = dlSales.getTaxCategoriesList();
        taxcatsent2 = dlSales.getTaxCategoriesList();
        taxcatsent3 = dlSales.getTaxCategoriesList();
        
        taxcatmodel = new ComboBoxValModel();
        taxcatmodel2 = new ComboBoxValModel();
        taxcatmodel3 = new ComboBoxValModel();
        
        
        m_sentunit=dlSales.getUnitList();
         m_UnitModel = new ComboBoxValModel();
        
        m_CodetypeModel = new ComboBoxValModel();
        m_CodetypeModel.add(null);
        m_CodetypeModel.add(CodeType.EAN13);
        m_CodetypeModel.add(CodeType.CODE128);
        m_jCodetype.setModel(m_CodetypeModel);
        m_jCodetype.setVisible(false);
        accountModel=new ComboBoxValModel();
        purchaseaccount.addActionListener(dirty);
        saccountModel=new ComboBoxValModel();
        salesaccount.addActionListener(dirty);
        m_jRef.getDocument().addDocumentListener(dirty);
        m_jCode.getDocument().addDocumentListener(dirty);
        m_jName.getDocument().addDocumentListener(dirty);
        m_jComment.addActionListener(dirty);
        m_jScale.addActionListener(dirty);
        m_jCategory.addActionListener(dirty);
        m_jprcategory.addActionListener(dirty);
        m_jTax.addActionListener(dirty);
        m_jTax2.addActionListener(dirty);                                           // edited by aakash
        m_jTax3.addActionListener(dirty);
       
        basic_btn2.addActionListener(dirty);                                        // edited by aakash
        basic_btn3.addActionListener(dirty);
        cascade_btn2.addActionListener(dirty);
        cascade_btn3.addActionListener(dirty);
        taxcat_radio1.addActionListener(dirty);
        taxcat_radio2.addActionListener(dirty);
        
        
        m_jPriceBuy.getDocument().addDocumentListener(dirty);
        m_jPriceSell.getDocument().addDocumentListener(dirty);
        m_jImage.addPropertyChangeListener("image", dirty);
        m_jstockcost.getDocument().addDocumentListener(dirty);
        m_jstockvolume.getDocument().addDocumentListener(dirty);
        m_jMinStockLevel.getDocument().addDocumentListener(dirty);
        m_jInCatalog.addActionListener(dirty);
        m_jReorderQty.getDocument().addDocumentListener(dirty);
        m_jCatalogOrder.getDocument().addDocumentListener(dirty);
        txtAttributes.getDocument().addDocumentListener(dirty);
        unitname.getDocument().addDocumentListener(dirty);
        unitlist.addActionListener(dirty);
        m_warehouse.addActionListener(dirty);
        m_jinmtn.addActionListener(dirty);
        

        FieldsManager fm = new FieldsManager();
        m_jPriceBuy.getDocument().addDocumentListener(fm);
        m_jPriceSell.getDocument().addDocumentListener(fm);
        m_jTax.addActionListener(fm);
        
        m_jPriceSellTax.getDocument().addDocumentListener(new PriceTaxManager());
        m_jmargin.getDocument().addDocumentListener(new MarginManager());
        m_jRef.getDocument().addDocumentListener(new ReferenceManager());//added by pratima
      //  unittype=unitlist.getSelectedItem().toString();
        writeValueEOF();
    }
    
    public void activate() throws BasicException {
        
        flag=false;
        m_warehousemodel = new ComboBoxValModel(m_sentloc.list());
        m_warehouse.setModel(m_warehousemodel);
        // Load the taxes logic
        taxeslogic = new TaxesLogic(taxsent.list());        
        
        
        
        
        
        m_CategoryModel = new ComboBoxValModel(m_sentcat.list());
        m_prModel=new ComboBoxValModel(m_sentprcat.list());
        m_jCategory.setModel(m_CategoryModel);
        m_jprcategory.setModel(m_prModel);
        taxcatmodel = new ComboBoxValModel(taxcatsent.list());
        
        
        taxcatmodel2 = new ComboBoxValModel(taxcatsent2.list());
        taxcatmodel3 = new ComboBoxValModel(taxcatsent3.list());
        
        m_jTax.setModel(taxcatmodel);
        // code edited by aakash
        m_jTax2.setModel(taxcatmodel2);
        m_jTax3.setModel(taxcatmodel3);
        
        
        
        m_UnitModel=new ComboBoxValModel(m_sentunit.list());
        unitlist.setModel(m_UnitModel);
        List<AccountMasterExt> acclist=dlfac.getaccounts();
        List<AccountMasterExt> sacclist=new ArrayList<AccountMasterExt>();
        sacclist.addAll(acclist);
        accountModel=new ComboBoxValModel(acclist);
        purchaseaccount.setModel(accountModel);
        saccountModel=new ComboBoxValModel(sacclist);
        salesaccount.setModel(saccountModel);
        

      //    unittype=unitlist.getSelectedItem().toString();
      /*  AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
          List<PdtUnitInfo> prinfo=   new StaticSentence(m_App.getSession()
                        , "SELECT ID,NAME FROM UNIT "
                       ,null
                       ,new SerializerReadClass(PdtUnitInfo.class)).list();
           if(prinfo!=null)
        {
       String[] strings=new String[prinfo.size()];
      int i=0;
            for(PdtUnitInfo p: prinfo)
            {
                strings[i]=p.getName();
                i++;
            }
      unitlist.setModel(new javax.swing.DefaultComboBoxModel(strings));
           }*/
        
        
        ButtonGroup();                                                                 // edited by aakash
        
        

    }
    
    public void writeValueEOF() {
      
        
        flag=false;
        reportlock = true;
        // Los valores
        m_jTitle.setText(AppLocal.getIntString("label.recordeof"));
        m_id = null;
        m_jRef.setText(null);
        m_jCode.setText(null);
        m_jName.setText(null);
        m_jComment.setSelected(false);
        m_jScale.setSelected(false);
        m_CategoryModel.setSelectedKey(null);
        m_warehousemodel.setSelectedKey(null);
        m_UnitModel.setSelectedKey(null);
        m_prModel.setSelectedKey(null);
        taxcatmodel.setSelectedKey(null);
        taxcatmodel2.setSelectedKey(null);                               // edited by aakash
        taxcatmodel3.setSelectedKey(null);
         
         
        m_jPriceBuy.setText(null);
        m_jPriceSell.setText(null);            
        m_jImage.setImage(null);
        m_jstockcost.setText(null);
        m_jstockvolume.setText(null);
        m_jMinStockLevel.setText(null);
        m_jInCatalog.setSelected(false);
        m_jReorderQty.setText(null);
        m_jCatalogOrder.setText(null);
        txtAttributes.setText(null);
        reportlock = false;
        unitname.setText(null);
        unitlist.setEnabled(false);
        unitname.setEnabled(false);
        // Los habilitados
        m_jRef.setEnabled(false);
        m_jCode.setEnabled(false);
        m_jName.setEnabled(false);
        m_jComment.setEnabled(false);
        m_jScale.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_warehouse.setEnabled(false);
        m_jprcategory.setEnabled(false);
        m_jTax.setEnabled(false);
        m_jPriceBuy.setEnabled(false);
        m_jPriceSell.setEnabled(false);
        m_jPriceSellTax.setEnabled(false);
        m_jmargin.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jstockcost.setEnabled(false);
        m_jstockvolume.setEnabled(false);
        m_jMinStockLevel.setEnabled(false);
        m_jInCatalog.setEnabled(false);
        m_jReorderQty.setEditable(false);
        m_jCatalogOrder.setEnabled(false);
        txtAttributes.setEnabled(false);
        unittype=null;
        accountModel.setSelectedKey(null);
        purchaseaccount.setVisible(false);
        saccountModel.setSelectedKey(null);
        salesaccount.setVisible(false);
        m_jinmtn.setSelected(false);
        calculateMargin();
        calculatePriceSellTax();

    }
    public void writeValueInsert() {
         flag=true;//when location selected it loads corresponding  categories.
        reportlock = true;
        // Los valores
        m_jTitle.setText(AppLocal.getIntString("label.recordnew"));
        m_id = null;
        m_jRef.setText(null);
        m_jCode.setText(null);
        m_jName.setText(null);
        m_jComment.setSelected(false);
        m_jScale.setSelected(false);
        m_CategoryModel.setSelectedKey(null);
        m_warehousemodel.setSelectedKey(null);
        m_UnitModel.setSelectedKey(null);
        m_prModel.setSelectedKey(null);
        taxcatmodel.setSelectedKey(null);
        taxcatmodel2.setSelectedKey(null);                          // edited by akash
        taxcatmodel3.setSelectedKey(null);
        
        
        m_jPriceBuy.setText(null);
        m_jPriceSell.setText(null);            
        m_jImage.setImage(null);
        m_jstockcost.setText(null);
        m_jstockvolume.setText(null);
        m_jMinStockLevel.setText(null);
        m_jInCatalog.setSelected(true);
        m_jReorderQty.setText(null);
        m_jCatalogOrder.setText(null);
        txtAttributes.setText(null);
        reportlock = false;
        
        // Los habilitados
        m_jRef.setEnabled(true);
        m_jCode.setEnabled(true);
        m_jName.setEnabled(true);
        m_jComment.setEnabled(true);
        m_jScale.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_warehouse.setEnabled(true);
        m_jprcategory.setEnabled(true);
        m_jTax.setEnabled(true);
        m_jPriceBuy.setEnabled(true);
        m_jPriceSell.setEnabled(true); 
        m_jPriceSellTax.setEnabled(true);
        m_jmargin.setEnabled(true);
        m_jImage.setEnabled(true);
        m_jstockcost.setEnabled(true);
        m_jstockvolume.setEnabled(true);
        m_jMinStockLevel.setEnabled(true);
        m_jInCatalog.setEnabled(true);
        m_jReorderQty.setEnabled(true);
        m_jCatalogOrder.setEnabled(true);
        txtAttributes.setEnabled(true);
         unitname.setText(null);
        unitlist.setEnabled(true);
        unitname.setEnabled(true);
        unittype=null;
        accountModel.setSelectedKey(null);
        purchaseaccount.setVisible(true);
        saccountModel.setSelectedKey(null);
        salesaccount.setVisible(true);
         m_jinmtn.setSelected(false);
        calculateMargin();
        calculatePriceSellTax();
   }
    public void writeValueDelete(Object value) {
        flag=false;
        reportlock = true;       
        Object[] myprod = (Object[]) value;
        m_jTitle.setText(Formats.STRING.formatValue(myprod[1]) + " - " + Formats.STRING.formatValue(myprod[3]) + " " + AppLocal.getIntString("label.recorddeleted"));
        m_id = myprod[0];
        m_jRef.setText(Formats.STRING.formatValue(myprod[1]));
        m_jCode.setText(Formats.STRING.formatValue(myprod[2]));
        m_jName.setText(Formats.STRING.formatValue(myprod[3]));
        m_jComment.setSelected(((Boolean)myprod[4]).booleanValue());
        m_jScale.setSelected(((Boolean)myprod[5]).booleanValue());
        m_jPriceBuy.setText(Formats.CURRENCY.formatValue(myprod[6]));
        m_jPriceSell.setText(Formats.CURRENCY.formatValue(myprod[7]));
         m_warehousemodel.setSelectedKey(myprod[21]);
        m_CategoryModel.setSelectedKey(myprod[8]);
      

        taxcatmodel.setSelectedKey(myprod[9]);
        m_jImage.setImage((BufferedImage) myprod[10]);
        m_jstockcost.setText(Formats.STRING.formatValue(myprod[11]));
        
         //  unittype=unitlist.getSelectedItem().toString();
        //m_jstockvolume.setText(Formats.DOUBLE.formatValue(myprod[12]));
        m_jstockvolume.setText(Formats.DOUBLE.formatValue(myprod[23]));
        m_jMinStockLevel.setText(Formats.DOUBLE.formatValue(myprod[24]));
        m_jInCatalog.setSelected(((Boolean)myprod[13]).booleanValue());
        m_jCatalogOrder.setText(Formats.INT.formatValue(myprod[14]));
        m_jReorderQty.setText(Formats.INT.formatValue(myprod[26]));
        m_jCatalogOrder.setText(Formats.INT.formatValue(myprod[25]));
        txtAttributes.setText(Formats.BYTEA.formatValue(myprod[15]));
        m_prModel.setSelectedKey(myprod[16]);
        unittype=  m_UnitModel.getElementByKey(Formats.STRING.formatValue(myprod[17]));
         m_UnitModel.setSelectedKey(Formats.STRING.formatValue(myprod[17]));
     /*   try{
         if(myprod[17]!=null)
         {
           AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT NAME FROM UNIT WHERE ID = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(myprod[17].toString());
            if(obj1!=null && obj1[0]!=null)
         m_UnitModel.setSelectedKey(Formats.STRING.formatValue(obj1[0]));
         }
         }
         catch(Exception e)
         {
         }*/
         accountModel.setSelectedKey(myprod[18]);
        purchaseaccount.setVisible(false);
        saccountModel.setSelectedKey(myprod[19]);
         
        m_jinmtn.setSelected(((Boolean) myprod[22]).booleanValue());
        
        
        Boolean basic2 = ((Boolean) myprod[29]).booleanValue();                                              // code edited by aakash
        if(basic2){
            basic_btn2.setSelected(true);
        }
        else{
            cascade_btn2.setSelected(true);
        }
        
        
        Boolean basic3 = ((Boolean) myprod[30]).booleanValue();                                             // code edited by aakash
        if(basic3){ 
            basic_btn3.setSelected(true);
        }
        else{
            cascade_btn3.setSelected(true);
        }
        
        
        
        
        
        salesaccount.setVisible(false);
        txtAttributes.setCaretPosition(0);
        reportlock = false;
        
        // Los habilitados
        m_jRef.setEnabled(false);
        m_jCode.setEnabled(false);
        m_jName.setEnabled(false);
        m_jComment.setEnabled(false);
        m_jScale.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jprcategory.setEnabled(false);
        m_jTax.setEnabled(false);
        m_jPriceBuy.setEnabled(false);
        m_jPriceSell.setEnabled(false);
        m_jPriceSellTax.setEnabled(false);
        m_jmargin.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jstockcost.setEnabled(false);
        m_jstockvolume.setEnabled(false);
        m_jMinStockLevel.setEnabled(false);
        m_jInCatalog.setEnabled(false);
        m_jReorderQty.setEnabled(false);
        m_jCatalogOrder.setEnabled(false);
        txtAttributes.setEnabled(false);
        unitname.setText(null);
        unitname.setEnabled(false);
        unitname.setEnabled(false);
        unittype=null;
        m_warehouse.setEnabled(false);
        
        calculateMargin();
        calculatePriceSellTax();
       
    }    
    private String unitkey;
    public void writeValueEdit(Object value) {
        flag=false;
        reportlock = true;
        Object[] myprod = (Object[]) value;
        m_jTitle.setText(Formats.STRING.formatValue(myprod[1]) + " - " + Formats.STRING.formatValue(myprod[3]));
        m_id = myprod[0];
        pid=myprod[0];
        m_jRef.setText(Formats.STRING.formatValue(myprod[1]));
        m_jCode.setText(Formats.STRING.formatValue(myprod[2]));
        m_jName.setText(Formats.STRING.formatValue(myprod[3]));
        m_jComment.setSelected(((Boolean)myprod[4]).booleanValue());
        m_jScale.setSelected(((Boolean)myprod[5]).booleanValue());
        m_jPriceBuy.setText(Formats.CURRENCY.formatValue(myprod[6]));
        m_jPriceSell.setText(Formats.CURRENCY.formatValue(myprod[7]));
         m_warehousemodel.setSelectedKey(myprod[21]);
        m_CategoryModel.setSelectedKey(myprod[8]);
        

        taxcatmodel.setSelectedKey(myprod[9]);
         taxcatmodel2.setSelectedKey(myprod[27]);                              // edited by aakash
         taxcatmodel3.setSelectedKey(myprod[28]);
        
        
        m_jImage.setImage((BufferedImage) myprod[10]);
        m_jstockcost.setText(Formats.STRING.formatValue(myprod[11]));
         unitkey=Formats.STRING.formatValue(myprod[17]);
       unittype=  m_UnitModel.getElementByKey(unitkey);
        
         m_UnitModel.setSelectedKey(unitkey);
        //  unittype=unitlist.getSelectedItem().toString();
        //m_jstockvolume.setText(Formats.DOUBLE.formatValue(myprod[12]));
         m_jstockvolume.setText(Formats.DOUBLE.formatValue(myprod[23]));
         m_jMinStockLevel.setText(Formats.DOUBLE.formatValue(myprod[24]));
        m_jInCatalog.setSelected(((Boolean)myprod[13]).booleanValue());
        m_jCatalogOrder.setText(Formats.INT.formatValue(myprod[14]));
        m_jReorderQty.setText(Formats.INT.formatValue(myprod[26]));
        m_jCatalogOrder.setText(Formats.INT.formatValue(myprod[25]));
        txtAttributes.setText(Formats.BYTEA.formatValue(myprod[15]));
         m_prModel.setSelectedKey(myprod[16]);
     /*   try{
         if(myprod[17]!=null)
         {
           AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT NAME FROM UNIT WHERE ID = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(myprod[17].toString());
            if(obj1!=null && obj1[0]!=null)
         m_UnitModel.setSelectedKey(Formats.STRING.formatValue(obj1[0]));
         }
         }
         catch(Exception e)
         {
         }*/
         accountModel.setSelectedKey(myprod[18]);
        purchaseaccount.setVisible(true);
        saccountModel.setSelectedKey(myprod[19]);       
        m_jinmtn.setSelected(((Boolean) myprod[22]).booleanValue());
        
       Boolean basic2 = ((Boolean) myprod[29]).booleanValue();                                              // code edited by aakash
        if(basic2){
            basic_btn2.setSelected(true);
        }
        else{
            cascade_btn2.setSelected(true);
        }
        
        
        Boolean basic3 = ((Boolean) myprod[30]).booleanValue();                                             // code edited by aakash
        if(basic3){ 
            basic_btn3.setSelected(true);
        }
        else{
            cascade_btn3.setSelected(true);
        }
        
        
        
        
        salesaccount.setVisible(true);
        txtAttributes.setCaretPosition(0);
        reportlock = false;
        
        // Los habilitados
        m_jRef.setEnabled(true);
        m_jCode.setEnabled(true);
        m_jName.setEnabled(true);
        m_jComment.setEnabled(true);
        m_jScale.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jprcategory.setEnabled(true);
        m_jTax.setEnabled(true);
         m_jTax2.setEnabled(true);                                      //  edited by aakash
         m_jTax3.setEnabled(true);
        
        m_jPriceBuy.setEnabled(true);
        m_jPriceSell.setEnabled(true); 
        m_jPriceSellTax.setEnabled(true);
        m_jmargin.setEnabled(true);
        m_jImage.setEnabled(true);
        m_jstockcost.setEnabled(true);
        m_jstockvolume.setEnabled(true);
        m_jMinStockLevel.setEnabled(true);
        m_jInCatalog.setEnabled(true);
        m_jReorderQty.setEditable(true);
        m_jCatalogOrder.setEnabled(m_jInCatalog.isSelected());  
        txtAttributes.setEnabled(true);
        unitname.setEnabled(true);
        unitlist.setEnabled(true);
         unitname.setText(null);
        m_warehouse.setEnabled(true);
        calculateMargin();
        calculatePriceSellTax();
        
    }
  private Object pid;
    public Object createValue() throws BasicException {
        flag=true;
        Object[] myprod = new Object[32];
       // pid=m_id == null ? UUID.randomUUID().toString() : m_id;
        myprod[0] =  m_id == null ? UUID.randomUUID().toString() : m_id;
        myprod[1] = m_jRef.getText();
        myprod[2] = m_jCode.getText();
        myprod[3] = m_jName.getText();
        myprod[4] = Boolean.valueOf(m_jComment.isSelected());
        myprod[5] = Boolean.valueOf(m_jScale.isSelected());
        myprod[6] = Formats.CURRENCY.parseValue(m_jPriceBuy.getText());
        myprod[7] = Formats.CURRENCY.parseValue(m_jPriceSell.getText());

        if(m_warehouse.getSelectedIndex()!= -1){
            if(m_jCategory.getSelectedIndex()!=-1){
                 myprod[8] = m_CategoryModel.getSelectedKey();
            }else{
                JOptionPane.showMessageDialog(this, "select category");
            throw new BasicException();
            }
        }else{
            JOptionPane.showMessageDialog(this, "select location");
            throw new BasicException();
        }

        myprod[9] = taxcatmodel.getSelectedKey();
        myprod[27] = taxcatmodel2.getSelectedKey();                                       // edited by aakash
        myprod[28] = taxcatmodel3.getSelectedKey();
        
        
        myprod[10] = m_jImage.getImage();
//        myprod[11] = Formats.CURRENCY.parseValue(m_jstockcost.getText());
        myprod[11] = m_jstockcost.getText();
       // myprod[12] = Formats.DOUBLE.parseValue(m_jstockvolume.getText());
        myprod[13] = Boolean.valueOf(m_jInCatalog.isSelected());       
        myprod[14] =Formats.INT.parseValue(m_jCatalogOrder.getText());
        myprod[15] = Formats.BYTEA.parseValue(txtAttributes.getText());
        myprod[16] = m_prModel.getSelectedKey();
        int flag=0;
    String unitid="";
    final String unitid1;
        if(m_UnitModel.getSelectedKey()==null && unitname.getText()!=null)
        {
            flag=1;
            unitid1=UUID.randomUUID().toString();
             SentenceExec unitInsert = new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                                   ,"INSERT INTO UNIT (ID, NAME) VALUES (?, ?)"
                                   ,SerializerWriteParams.INSTANCE);
                           unitInsert.exec(new DataParams() {
                               public void writeValues() throws BasicException {
                                   setString(1,unitid1);
                                   setString(2,unitname.getText() );
                               }
                           });
                           unitid=unitid1;


        }

           if(flag==0)
           {
                AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ID FROM UNIT WHERE NAME = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(m_UnitModel.getSelectedItem().toString());
            if(obj1!=null && obj1[0]!=null)
               myprod[17]=obj1[0].toString();

           }
          else
          {
                myprod[17]=unitid;
                 m_UnitModel.setSelectedKey(Formats.STRING.formatValue(unitid));
                  unitname.setEnabled(true);
          }
           myprod[18]=accountModel.getSelectedKey();
           myprod[19]=saccountModel.getSelectedKey();
           String parentcat=String.valueOf(m_CategoryModel.getSelectedKey());
           String pcat=null;
           while(!parentcat.equals("null")){
              pcat=parentcat;
              parentcat=String.valueOf(m_dlSales.getParentCategory(pcat));
           }
           myprod[20]=pcat;
           myprod[21] = m_warehousemodel.getSelectedKey();
           boolean inventrymaintain = Boolean.valueOf(m_jinmtn.isSelected());
           myprod[22] = inventrymaintain;
           
           
           
           Boolean basic2 = Boolean.valueOf(basic_btn2.isSelected());                                                 // code edited by aakash
           myprod[29] = basic2;
           
           Boolean basic3 = Boolean.valueOf(basic_btn3.isSelected());
           myprod[30] = basic3;
           
           
           
           
           
           
           
//sameer: writing code for newly added fields
           if(m_jstockvolume.getText().length()>0){
               try{
                  Object max=Formats.INT.parseValue(m_jstockvolume.getText());
          myprod[23]=max;
       
               }
               catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Enter Correct Values in Max Stock Level", "Error", JOptionPane.ERROR_MESSAGE);
               throw new BasicException();
               }
           }
           if(m_jMinStockLevel.getText().length()>0){
               try{
           Object min=Formats.INT.parseValue(m_jMinStockLevel.getText());
           myprod[24]=min;
          
           }
              catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Enter Correct Values in Min Stock Level", "Error", JOptionPane.ERROR_MESSAGE);
               throw new BasicException();
               }
           }
           if(m_jCatalogOrder.getText().length()>0){
               try{
           Object reorder=Formats.INT.parseValue(m_jCatalogOrder.getText());
           myprod[25]=reorder;
        
           }
               catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Enter Correct Values in Re Order Level", "Error", JOptionPane.ERROR_MESSAGE);
               throw new BasicException();
               }
           }
           if(m_jReorderQty.getText().length()>0){
               try{
           Object reorder=Formats.INT.parseValue(m_jReorderQty.getText());
           myprod[26]=reorder;
         
           }
               catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Enter Correct Values in RE Order Quantity", "Error", JOptionPane.ERROR_MESSAGE);
               throw new BasicException();
               }
           }

        return myprod;
    }    
    
    public Component getComponent() {
        return this;
    }
    
    private void calculateMargin() {
        
        if (!reportlock) {
            reportlock = true;
            
            Double dPriceBuy = readCurrency(m_jPriceBuy.getText());
            Double dPriceSell = readCurrency(m_jPriceSell.getText());

            if (dPriceBuy == null || dPriceSell == null) {
                m_jmargin.setText(null);
            } else {
                m_jmargin.setText(Formats.PERCENT.formatValue(new Double(dPriceSell.doubleValue() / dPriceBuy.doubleValue() - 1.0)));
            }    
            reportlock = false;
        }
    }
    
    private void calculatePriceSellTax() {                                                                        // edited by aakash
        
        if (!reportlock) {
            reportlock = true;
            
            Double dPriceSell = readCurrency(m_jPriceSell.getText());
            Double Total = dPriceSell;
            
            if (dPriceSell == null) {
                m_jPriceSellTax.setText(null);
            } else {               
                double dTaxRate = taxeslogic.getTaxRate((TaxCategoryInfo) taxcatmodel.getSelectedItem());
              //  m_jPriceSellTax.setText(Formats.CURRENCY.formatValue(new Double(dPriceSell.doubleValue() * (1.0 + dTaxRate))));
                Total = dPriceSell.doubleValue() * (1.0 + dTaxRate);
                
                
                
                
                    if(m_jTax2.getSelectedIndex()!=-1){
                       double dTaxRate2 = taxeslogic.getTaxRate((TaxCategoryInfo) taxcatmodel2.getSelectedItem()); 
                       if(basic_btn2.isSelected()){
                          Total = Total + (dPriceSell*dTaxRate2);
                       }
                       else{
                          Total = Total + (Total*dTaxRate2); 
                       }
                       
                    }
                    if(m_jTax3.getSelectedIndex()!=-1){
                        double dTaxRate3 = taxeslogic.getTaxRate((TaxCategoryInfo) taxcatmodel3.getSelectedItem());
                        if(basic_btn3.isSelected()){
                           Total = Total + (dPriceSell*dTaxRate3);
                         }
                         else{
                            Total = Total + (Total*dTaxRate3); 
                         }

                    }
                
               m_jPriceSellTax.setText(Formats.CURRENCY.formatValue(new Double(Total))); 
                
                
            }            
            reportlock = false;
        }
    }
    
    private void calculatePriceSellfromMargin() {
        
        if (!reportlock) {
            reportlock = true;
            
            Double dPriceBuy = readCurrency(m_jPriceBuy.getText());
            Double dMargin = readPercent(m_jmargin.getText());  
            
            if (dMargin == null || dPriceBuy == null) {
                m_jPriceSell.setText(null);
            } else {
                m_jPriceSell.setText(Formats.CURRENCY.formatValue(new Double(dPriceBuy.doubleValue() * (1.0 + dMargin.doubleValue()))));
            }
            reportlock = false;
        }
      
    }
    
    private void calculatePriceSellfromPST() {
        
        if (!reportlock) {
            reportlock = true;
                    
            Double dPriceSellTax = readCurrency(m_jPriceSellTax.getText());  

            if (dPriceSellTax == null) {
                m_jPriceSell.setText(null);
            } else {
                double dTaxRate = taxeslogic.getTaxRate((TaxCategoryInfo) taxcatmodel.getSelectedItem()); 
                m_jPriceSell.setText(Formats.CURRENCY.formatValue(new Double(dPriceSellTax.doubleValue() / (1.0 + dTaxRate))));
            }
            reportlock = false;
        }    
    }

    private class FieldsManager implements DocumentListener, ActionListener {
        public void changedUpdate(DocumentEvent e) {
            calculateMargin();
            calculatePriceSellTax();
        }
        public void insertUpdate(DocumentEvent e) {
            calculateMargin();
            calculatePriceSellTax();
        }    
        public void removeUpdate(DocumentEvent e) {
            calculateMargin();
            calculatePriceSellTax();
        }         
        public void actionPerformed(ActionEvent e) {
            calculateMargin();
            calculatePriceSellTax();
        }
    }

    private class PriceTaxManager implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            calculatePriceSellfromPST();
            calculateMargin();
        }
        public void insertUpdate(DocumentEvent e) {
            calculatePriceSellfromPST();
            calculateMargin();
        }    
        public void removeUpdate(DocumentEvent e) {
            calculatePriceSellfromPST();
            calculateMargin();
        }         
    }
    
    private class MarginManager implements DocumentListener  {
        public void changedUpdate(DocumentEvent e) {
            calculatePriceSellfromMargin();
            calculatePriceSellTax();
        }
        public void insertUpdate(DocumentEvent e) {
            calculatePriceSellfromMargin();
            calculatePriceSellTax();
        }    
        public void removeUpdate(DocumentEvent e) {
            calculatePriceSellfromMargin();
            calculatePriceSellTax();
        }         
    }
    
    //added by pratima : code to add pending request button
     private class ReferenceManager implements DocumentListener  {
        public void changedUpdate(DocumentEvent e) {
          
            try{
                pendingResultExist();
        
                }catch(BasicException ex){}
          
        }
        public void insertUpdate(DocumentEvent e) {
            try{
                 pendingResultExist();
            }catch(BasicException ex){}
        }    
        public void removeUpdate(DocumentEvent e) {
            try{
                 pendingResultExist();
            }catch(BasicException ex){}
        }         
    }//ended by pratima
    private final static Double readCurrency(String sValue) {
        try {
            return (Double) Formats.CURRENCY.parseValue(sValue);
        } catch (BasicException e) {
            return null;
        }
    }
        
    private final static Double readPercent(String sValue) {
        try {
            return (Double) Formats.PERCENT.parseValue(sValue);
        } catch (BasicException e) {
            return null;
        }
    }

//    private static class MyListData extends javax.swing.AbstractListModel {
//        
//        private java.util.List m_data;
//        
//        public MyListData(java.util.List data) {
//            m_data = data;
//        }
//        
//        public Object getElementAt(int index) {
//            return m_data.get(index);
//        }
//        
//        public int getSize() {
//            return m_data.size();
//        } 
//    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        m_jCode = new javax.swing.JTextField();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        jLabel3 = new javax.swing.JLabel();
        m_jPriceBuy = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        m_jPriceSell = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        m_jmargin = new javax.swing.JTextField();
        m_jPriceSellTax = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        m_jCodetype = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        m_jprcategory = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        m_warehouse = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        m_jTax2 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        m_jTax = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        m_jTax3 = new javax.swing.JComboBox();
        basic_btn2 = new javax.swing.JRadioButton();
        cascade_btn2 = new javax.swing.JRadioButton();
        basic_btn3 = new javax.swing.JRadioButton();
        cascade_btn3 = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        taxcat_radio1 = new javax.swing.JRadioButton();
        taxcat_radio2 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        m_jstockcost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        m_jstockvolume = new javax.swing.JTextField();
        m_jScale = new javax.swing.JCheckBox();
        m_jComment = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        m_jCatalogOrder = new javax.swing.JTextField();
        m_jInCatalog = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        unitlist = new javax.swing.JComboBox();
        unitname = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        purchaseaccount = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        salesaccount = new javax.swing.JComboBox();
        m_jinmtn = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        m_jMinStockLevel = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        m_jReorderQty = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAttributes = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        m_jRef = new javax.swing.JTextField();
        m_jName = new javax.swing.JTextField();
        m_jTitle = new javax.swing.JLabel();
        pendingReqButt = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setText(AppLocal.getIntString("label.prodref")); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(10, 50, 80, 14);

        jPanel1.setLayout(null);

        jLabel6.setText(AppLocal.getIntString("label.prodbarcode")); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 20, 150, 14);
        jPanel1.add(m_jCode);
        m_jCode.setBounds(160, 20, 170, 20);
        jPanel1.add(m_jImage);
        m_jImage.setBounds(366, 20, 200, 180);

        jLabel3.setText(AppLocal.getIntString("label.prodpricebuy")); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 50, 150, 14);

        m_jPriceBuy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(m_jPriceBuy);
        m_jPriceBuy.setBounds(160, 50, 80, 20);

        jLabel4.setText(AppLocal.getIntString("label.prodpricesell")); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 80, 102, 14);

        m_jPriceSell.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(m_jPriceSell);
        m_jPriceSell.setBounds(160, 80, 80, 20);

        jLabel5.setText(AppLocal.getIntString("label.prodcategory")); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 184, 150, 20);
        jPanel1.add(m_jCategory);
        m_jCategory.setBounds(172, 184, 170, 20);

        m_jmargin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(m_jmargin);
        m_jmargin.setBounds(250, 80, 80, 20);

        m_jPriceSellTax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(m_jPriceSellTax);
        m_jPriceSellTax.setBounds(160, 110, 80, 20);

        jLabel16.setText(AppLocal.getIntString("label.prodpriceselltax")); // NOI18N
        jPanel1.add(jLabel16);
        jLabel16.setBounds(10, 110, 150, 14);
        jPanel1.add(m_jCodetype);
        m_jCodetype.setBounds(250, 50, 80, 20);

        jLabel13.setText("Print Category");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(12, 221, 80, 14);

        jPanel1.add(m_jprcategory);
        m_jprcategory.setBounds(172, 216, 170, 20);

        jLabel19.setText("Warehouse");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(10, 148, 120, 20);

        m_warehouse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_warehouseItemStateChanged(evt);
            }
        });
        jPanel1.add(m_warehouse);
        m_warehouse.setBounds(170, 150, 170, 20);

        jTabbedPane1.addTab(AppLocal.getIntString("label.prodgeneral"), jPanel1); // NOI18N

        jPanel4.setLayout(null);

        m_jTax2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        m_jTax2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jTax2ItemStateChanged(evt);
            }
        });
        jPanel4.add(m_jTax2);
        m_jTax2.setBounds(170, 70, 266, 20);

        jLabel23.setText("Tax Category 2  : ");
        jPanel4.add(jLabel23);
        jLabel23.setBounds(12, 76, 129, 14);
        jLabel23.setVisible(true);

        m_jTax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jTaxItemStateChanged(evt);
            }
        });
        jPanel4.add(m_jTax);
        m_jTax.setBounds(168, 24, 266, 26);

        jLabel7.setText(AppLocal.getIntString("label.taxcategory")); // NOI18N
        jPanel4.add(jLabel7);
        jLabel7.setBounds(12, 24, 101, 14);

        jLabel24.setText("Tax Category 3 : ");
        jPanel4.add(jLabel24);
        jLabel24.setBounds(20, 170, 120, 14);

        m_jTax3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        m_jTax3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jTax3ItemStateChanged(evt);
            }
        });
        jPanel4.add(m_jTax3);
        m_jTax3.setBounds(170, 160, 266, 20);

        basic_btn2.setText("Basic");
        basic_btn2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                basic_btn2ItemStateChanged(evt);
            }
        });
        jPanel4.add(basic_btn2);
        basic_btn2.setBounds(170, 100, 49, 23);

        cascade_btn2.setText("Cascade");
        jPanel4.add(cascade_btn2);
        cascade_btn2.setBounds(260, 100, 130, 23);

        basic_btn3.setText("Basic");
        basic_btn3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                basic_btn3ItemStateChanged(evt);
            }
        });
        jPanel4.add(basic_btn3);
        basic_btn3.setBounds(170, 200, 49, 23);

        cascade_btn3.setText("Cascade");
        jPanel4.add(cascade_btn3);
        cascade_btn3.setBounds(260, 200, 130, 23);

        jLabel25.setText("Tax Category 3 to be cascade on  : ");
        jPanel4.add(jLabel25);
        jLabel25.setBounds(20, 240, 250, 14);
        jLabel25.setVisible(false);

        taxcat_radio1.setText("Taxcat 1");
        jPanel4.add(taxcat_radio1);
        taxcat_radio1.setBounds(280, 240, 110, 23);
        taxcat_radio1.setVisible(false);

        taxcat_radio2.setText("Taxcat 2");
        jPanel4.add(taxcat_radio2);
        taxcat_radio2.setBounds(390, 240, 67, 23);
        taxcat_radio2.setVisible(false);

        jTabbedPane1.addTab("Tax Category", jPanel4);
        jPanel4.setLayout(null);
        jPanel4.getAccessibleContext().setAccessibleParent(this);

        jPanel2.setLayout(null);

        jLabel9.setText(AppLocal.getIntString("label.prodstockcost")); // NOI18N
        jPanel2.add(jLabel9);
        jLabel9.setBounds(10, 20, 150, 0);

        m_jstockcost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jstockcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jstockcostActionPerformed(evt);
            }
        });
        jPanel2.add(m_jstockcost);
        m_jstockcost.setBounds(160, 20, 80, 20);

        jLabel10.setText(AppLocal.getIntString("label.prodstockvol")); // NOI18N
        jPanel2.add(jLabel10);
        jLabel10.setBounds(10, 50, 150, 14);

        m_jstockvolume.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(m_jstockvolume);
        m_jstockvolume.setBounds(160, 50, 80, 20);
        jPanel2.add(m_jScale);
        m_jScale.setBounds(160, 150, 40, 21);
        jPanel2.add(m_jComment);
        m_jComment.setBounds(160, 130, 50, 21);

        jLabel18.setText(AppLocal.getIntString("label.prodorder")); // NOI18N
        jPanel2.add(jLabel18);
        jLabel18.setBounds(260, 80, 100, 20);

        m_jCatalogOrder.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(m_jCatalogOrder);
        m_jCatalogOrder.setBounds(370, 80, 90, 20);

        m_jInCatalog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jInCatalogActionPerformed(evt);
            }
        });
        jPanel2.add(m_jInCatalog);
        m_jInCatalog.setBounds(160, 110, 50, 21);

        jLabel8.setText(AppLocal.getIntString("label.prodincatalog")); // NOI18N
        jPanel2.add(jLabel8);
        jLabel8.setBounds(10, 110, 150, 14);

        jLabel11.setText(AppLocal.getIntString("label.prodaux")); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 140, 150, 14);

        jLabel12.setText(AppLocal.getIntString("label.prodscale")); // NOI18N
        jPanel2.add(jLabel12);
        jLabel12.setBounds(10, 160, 150, 14);

        jLabel14.setText("Unit :");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(260, 110, 50, 20);

        unitlist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                unitlistItemStateChanged(evt);
            }
        });
        jPanel2.add(unitlist);
        unitlist.setBounds(370, 110, 90, 20);

        unitname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitnameActionPerformed(evt);
            }
        });
        jPanel2.add(unitname);
        unitname.setBounds(370, 140, 90, 20);

        jLabel15.setText("Purchase Account");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(10, 190, 140, 14);

        jPanel2.add(purchaseaccount);
        purchaseaccount.setBounds(160, 190, 300, 20);

        jLabel17.setText("Sales Account");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(10, 220, 120, 14);

        jPanel2.add(salesaccount);
        salesaccount.setBounds(160, 220, 300, 20);

        m_jinmtn.setText("Inventry maintain");
        jPanel2.add(m_jinmtn);
        m_jinmtn.setBounds(370, 20, 20, 23);

        jLabel20.setText("Inventory Maintain");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(260, 24, 110, 20);

        jLabel21.setText("Min Stock Level");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(10, 80, 100, 14);

        m_jMinStockLevel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(m_jMinStockLevel);
        m_jMinStockLevel.setBounds(160, 80, 80, 20);

        jLabel22.setText("Re Order Quantity");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(260, 50, 110, 20);

        m_jReorderQty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(m_jReorderQty);
        m_jReorderQty.setBounds(370, 50, 90, 20);

        jLabel26.setText("HSN/SAC Code");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(10, 20, 100, 14);

        jTabbedPane1.addTab(AppLocal.getIntString("label.prodstock"), jPanel2); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel3.setLayout(new java.awt.BorderLayout());

        txtAttributes.setFont(new java.awt.Font("DialogInput", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(txtAttributes);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(AppLocal.getIntString("label.attributes"), jPanel3); // NOI18N

        add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 90, 580, 310);

        jLabel2.setText(AppLocal.getIntString("label.prodname")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(180, 50, 70, 14);
        add(m_jRef);
        m_jRef.setBounds(90, 50, 70, 20);
        add(m_jName);
        m_jName.setBounds(250, 50, 220, 20);

        m_jTitle.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        add(m_jTitle);
        m_jTitle.setBounds(10, 10, 320, 30);

        pendingReqButt.setText("Pending Request");
        pendingReqButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingReqButtActionPerformed(evt);
            }
        });
        add(pendingReqButt);
        pendingReqButt.setBounds(490, 50, 113, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jInCatalogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jInCatalogActionPerformed
 
        if (m_jInCatalog.isSelected()) {
            productcatfalg=true;
            m_jCatalogOrder.setEnabled(true);   
        } else {
            productcatfalg=false;
            m_jCatalogOrder.setEnabled(false);   
            m_jCatalogOrder.setText(null);   
        }

    }//GEN-LAST:event_m_jInCatalogActionPerformed

    private void unitlistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_unitlistItemStateChanged
        // TODO add your handling code here:
       Object sel=unitlist.getSelectedItem();
         if(unittype!=null&& sel !=null)
        {
            if(!unittype.toString().equals(sel.toString()) && m_jstockvolume.getText()!=null)
            {
               // if(m_jstockvolume!=null)
                try{
                  Object[] obj2=(Object[])   new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                        , "SELECT ID FROM QTITEMS WHERE PRODUCT = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(pid);



               if(obj2 !=null )
               {
                   JOptionPane.showMessageDialog(this, "Cannot change the unittype", "Error", JOptionPane.OK_OPTION);
                   m_UnitModel.setSelectedKey(unitkey);
                 //  unitlist.setSelectedIndex(unitkey);
               }
                   }
                catch(Exception e){
                e.printStackTrace();
                }
      // if(o!=null)
            }
        }
    }//GEN-LAST:event_unitlistItemStateChanged

    private void m_warehouseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_warehouseItemStateChanged
        // TODO add your handling code here:
        //if(flag){//praveen:if i dont check this den it wont load the existing value
        Object loct1 = m_warehousemodel.getSelectedItem();
        if (loct1 != null) {
            String selectedItem = loct1.toString();           
            try {
                Object[] obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                        , "SELECT ID FROM LOCATIONS WHERE NAME = ?"
                        ,SerializerWriteString.INSTANCE
                        , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedItem);
                if (obj1 != null && obj1[0]!=null) {
                    // The categories model

                     catinfo = m_dlSales.getCategoriesListByParent(obj1[0].toString());
                     m_CategoryModel = new ComboBoxValModel(catinfo);
                     m_jCategory.setModel(m_CategoryModel);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           }
       // }

   
        
}//GEN-LAST:event_m_warehouseItemStateChanged

    private void unitnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitnameActionPerformed

    private void m_jTaxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jTaxItemStateChanged
       m_jTax2.setSelectedIndex(-1);
       m_jTax3.setSelectedIndex(-1);
       
       
    }//GEN-LAST:event_m_jTaxItemStateChanged

    private void m_jTax2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jTax2ItemStateChanged
        if(m_jTax2.getSelectedIndex()!=-1){
            calculatePriceSellTax();
        }
    }//GEN-LAST:event_m_jTax2ItemStateChanged

    private void m_jTax3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jTax3ItemStateChanged
        if(m_jTax3.getSelectedIndex()!=-1){
            calculatePriceSellTax();
        }
    }//GEN-LAST:event_m_jTax3ItemStateChanged

    private void basic_btn2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_basic_btn2ItemStateChanged
       calculatePriceSellTax();
    }//GEN-LAST:event_basic_btn2ItemStateChanged

    private void basic_btn3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_basic_btn3ItemStateChanged
       calculatePriceSellTax();
    }//GEN-LAST:event_basic_btn3ItemStateChanged

    private void pendingReqButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingReqButtActionPerformed
       String ref=m_jRef.getText();
        ProductRequestList prodReqList=ProductRequestList.getDialog(this, m_App,ref);
        prodReqList.showDialog();
      
    }//GEN-LAST:event_pendingReqButtActionPerformed

    private void m_jstockcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jstockcostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jstockcostActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton basic_btn2;
    private javax.swing.JRadioButton basic_btn3;
    private javax.swing.JRadioButton cascade_btn2;
    private javax.swing.JRadioButton cascade_btn3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField m_jCatalogOrder;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JTextField m_jCode;
    private javax.swing.JComboBox m_jCodetype;
    private javax.swing.JCheckBox m_jComment;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private javax.swing.JCheckBox m_jInCatalog;
    private javax.swing.JTextField m_jMinStockLevel;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jPriceBuy;
    private javax.swing.JTextField m_jPriceSell;
    private javax.swing.JTextField m_jPriceSellTax;
    private javax.swing.JTextField m_jRef;
    private javax.swing.JTextField m_jReorderQty;
    private javax.swing.JCheckBox m_jScale;
    private javax.swing.JComboBox m_jTax;
    private javax.swing.JComboBox m_jTax2;
    private javax.swing.JComboBox m_jTax3;
    private javax.swing.JLabel m_jTitle;
    private javax.swing.JCheckBox m_jinmtn;
    private javax.swing.JTextField m_jmargin;
    private javax.swing.JComboBox m_jprcategory;
    private javax.swing.JTextField m_jstockcost;
    private javax.swing.JTextField m_jstockvolume;
    private javax.swing.JComboBox m_warehouse;
    private javax.swing.JButton pendingReqButt;
    private javax.swing.JComboBox purchaseaccount;
    private javax.swing.JComboBox salesaccount;
    private javax.swing.JRadioButton taxcat_radio1;
    private javax.swing.JRadioButton taxcat_radio2;
    private javax.swing.JTextArea txtAttributes;
    private javax.swing.JComboBox unitlist;
    private javax.swing.JTextField unitname;
    // End of variables declaration//GEN-END:variables
    
    
    
    
    
    
    public void ButtonGroup(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(basic_btn2);
        bg.add(cascade_btn2);
        
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(basic_btn3);
        bg2.add(cascade_btn3);
        
                
    }
    
    public void pendingResultExist()throws BasicException{
      int i=0;

             String ref=m_jRef.getText();
             if((ref!=null)&&(!ref.equals(""))){
            System.out.println("ref"+ref);
                 List<ProductsApprovalTableModel.PrdApvInfo>   plist= new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
                     ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductRequestData(m_App, ref);
       i=ProductsApproval_Table_Model.getList().size(); 
         
    }
     if(i>0){pendingReqButt.setVisible(true);}
        else pendingReqButt.setVisible(false);
   
    }
}
