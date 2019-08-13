package com.openbravo.pos.mant;

import javax.swing.ListCellRenderer;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.data.loader.ComparatorCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.panels.*;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.pos.forms.DataLogicSales;



public class WaiterPanel extends JPanelTable {

    private TableDefinition tprcategories;
    private  waitereditor jeditor;

    /** Creates a new instance of JPanelCategories */
    public WaiterPanel() {
    }

    protected void init() {
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        tprcategories = dlSales.getWaiterCategories();
        jeditor = new waitereditor(app, dirty);
    }

    public void activate() throws BasicException {

        jeditor.activate();
        super.activate();
    }

    public ListProvider getListProvider() {
        return new ListProviderCreator(tprcategories);
    }

    public SaveProvider getSaveProvider() {
        return new SaveProvider(tprcategories);
    }

    public Vectorer getVectorer() {
        return tprcategories.getVectorerBasic(new int[]{1});
    }

    public ComparatorCreator getComparatorCreator() {
        return tprcategories.getComparatorCreator(new int[]{1});
    }

    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tprcategories.getRenderStringBasic(new int[]{1}));
    }

    public EditorRecord getEditor() {
        return jeditor;
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.Waiters");
    }
}

