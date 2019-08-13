/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
//import com.openbravo.pos.panels.JPanelTable;
import com.openbravo.pos.panels.JPanelTableUpdate;

/**
 *
 * @author sys
 */
public class ResetPanel extends JPanelTableUpdate{
      private TableDefinition tprcategories;
     private  ResetQtBillReceipt jeditorQBR;
  
  
    protected void init() {
       
       DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        tprcategories = dlSales.getWaiterCategories();
         jeditorQBR = new ResetQtBillReceipt(app, dirty);
        jeditorQBR.init(app);
         
    }
    
    
   
    @Override
     public void activate() throws BasicException {

        jeditorQBR.activate();
       super.activate();
    }

  
    public EditorRecord getEditor() {
        return jeditorQBR;
    }

   
    public ListProvider getListProvider() {
       return new ListProviderCreator(tprcategories);
    }

      public SaveProvider getSaveProvider() {
         return new SaveProvider(tprcategories);
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.Update Bill/QT/Receipt");
    }

  

  
    
    
    
    
    
}
