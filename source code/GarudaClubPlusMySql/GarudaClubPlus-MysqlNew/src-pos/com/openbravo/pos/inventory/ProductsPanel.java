

package com.openbravo.pos.inventory;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.data.loader.ComparatorCreator;
import com.openbravo.data.loader.ComparatorCreatorBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.RenderStringBasic;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.loader.VectorerBasic;
import com.openbravo.data.user.BrowsableData;
import com.openbravo.data.user.EditorListener;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.panels.JPanelTable;
import com.openbravo.pos.ticket.ProductFilter;
//import com.openbravo.pos.ticket.ProductFilter;

/**
 *
 * @author adrianromero
 * Created on 1 de marzo de 2007, 22:15
 *
 */
public class ProductsPanel extends JPanelTable implements EditorListener {
    
    private SentenceList liststock;
    private BrowsableData m_bdstock;

    private ProductsEditor jeditor;
    private ProductFilter jproductfilter;
    
    private DataLogicSales m_dlSales = null;
    
    /** Creates a new instance of ProductsPanel2 */
    public ProductsPanel() {
    }
    
    protected void init() {   
        m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        
        // el panel del filtro
        jproductfilter = new ProductFilter();
        jproductfilter.init(app);
        
        // el panel del editor
        jeditor = new ProductsEditor(m_dlSales, dirty,app);

        liststock = m_dlSales.getProductStock();

       //  El editable data del stock
        m_bdstock = new BrowsableData(null, new SaveProvider(
                m_dlSales.getStockUpdate(),
                null,
                null));    
    }

    
    public ListProvider getListProvider() {
        return new ListProviderCreator(m_dlSales.getProductCatQBF(), jproductfilter);

    }
    
//    public SaveProvider getSaveProvider() {
//        return new SaveProvider(
//            m_dlSales.getProductCatUpdate(), 
//            m_dlSales.getProductCatInsert(), 
//            m_dlSales.getProductCatDelete());        
//    }  
//commented by pratima
//added by pratima :send request to products_approval on creating new or edit product     
    public SaveProvider getSaveProvider() {
        return new SaveProvider(
            m_dlSales.getProductCatUpdateApprove(), 
            m_dlSales.getProductCatInsertApprove(), 
            m_dlSales.getProductCatDelete());        
    }  //ended by pratima 
    
    @Override
    public Vectorer getVectorer() {
        return  new VectorerBasic(
                new String[]{"ID", AppLocal.getIntString("label.prodref"), AppLocal.getIntString("label.prodbarcode"), AppLocal.getIntString("label.prodname"), "ISCOM", "ISSCALE", AppLocal.getIntString("label.prodpricebuy"), AppLocal.getIntString("label.prodpricesell"), AppLocal.getIntString("label.prodcategory"), AppLocal.getIntString("label.taxcategory"), "IMAGE", "HSN_Code", "STOCKVOLUME"},
                new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.BOOLEAN, Formats.BOOLEAN, Formats.CURRENCY, Formats.CURRENCY, Formats.STRING, Formats.STRING, Formats.NULL, Formats.STRING, Formats.DOUBLE},
                new int[] {1, 2, 3, 6, 7});
    }
    
    @Override
    public ComparatorCreator getComparatorCreator() {
        return new ComparatorCreatorBasic(
                new String[]{"ID", AppLocal.getIntString("label.prodref"), AppLocal.getIntString("label.prodbarcode"), AppLocal.getIntString("label.prodname"), "ISCOM", "ISSCALE", AppLocal.getIntString("label.prodpricebuy"), AppLocal.getIntString("label.prodpricesell"), AppLocal.getIntString("label.prodcategory"), AppLocal.getIntString("label.taxcategory"), "IMAGE", "HSN_Code", "STOCKVOLUME"},
                // El productCatDatas del SentenceContainer, igualito
                new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT}, 
                new int[]{1, 2, 3, 6, 7, 8, 9});
    }
    
    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(new RenderStringBasic(new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING}, new int[]{1, 3}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    @Override
    public Component getFilter() {
        return jproductfilter.getComponent();
    }  
    
    @Override
    public Component getToolbarExtras() {
        
        JButton btnScanPal = new JButton();
        btnScanPal.setText("ScanPal");
        btnScanPal.setVisible(app.getDeviceScanner() != null);
        btnScanPal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanPalActionPerformed(evt);
            }
        });      
        
        return btnScanPal;
    }
    
    private void btnScanPalActionPerformed(java.awt.event.ActionEvent evt) {                                           
  
        JDlgUploadProducts.showMessage(this, app.getDeviceScanner(), bd);
    }  
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.Products");
    } 
        
    @Override
    public void activate() throws BasicException {
        
        jeditor.activate(); 
        jproductfilter.activate();
        
        super.activate();
    } 
    
    public void updateValue(Object value) {
        
        // recargo 
        try {
            m_bdstock.loadList(liststock.list(value));
        } catch (BasicException e) {
            m_bdstock.loadList(null);
        }
    }    
}
