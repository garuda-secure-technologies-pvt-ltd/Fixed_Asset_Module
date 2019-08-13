/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.data.loader.ComparatorCreator;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.panels.JPanelTable;
import javax.swing.ListCellRenderer;

/**
 *
 * @author swathi
 */
public class VendorPanel extends JPanelTable {

    private TableDefinition tvendor;
    private VendorDetail1 jeditor;

    /** Creates a new instance of CustomersPanel */
    public VendorPanel() {
    }

    protected void init() {
        DataLogicFacilities dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        tvendor = dlfac.getTableVendor();
        jeditor = new VendorDetail1(app, dirty);
    }

    @Override
    public void activate() throws BasicException {
        jeditor.activate();
        super.activate();
    }

    public ListProvider getListProvider() {
        return new ListProviderCreator(tvendor);
    }

    public SaveProvider getSaveProvider() {
        SaveProvider s=new SaveProvider(tvendor);
        s.removeDelete();
        return s;
    }

    @Override
    public Vectorer getVectorer() {
        return tvendor.getVectorerBasic(new int[]{2});
    }

    @Override
    public ComparatorCreator getComparatorCreator() {
        return tvendor.getComparatorCreator(new int[] {2});
    }

    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tvendor.getRenderStringBasic(new int[]{2}));
    }

    public EditorRecord getEditor() {
        return jeditor;
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.VendorManagement");
    }
}

