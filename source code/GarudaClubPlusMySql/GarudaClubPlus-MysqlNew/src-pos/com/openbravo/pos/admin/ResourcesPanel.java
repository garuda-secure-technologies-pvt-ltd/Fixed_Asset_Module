

package com.openbravo.pos.admin;

import javax.swing.ListCellRenderer;
import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.data.loader.ComparatorCreator;
import com.openbravo.pos.forms.*;
import com.openbravo.pos.panels.*;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.user.*;

/**
 *
 * @author adrianromero
 */
public class ResourcesPanel extends JPanelTable {

    private TableDefinition tresources;
    private ResourcesView jeditor;
    
    /** Creates a new instance of JPanelResources */
    public ResourcesPanel() {
    }
    
    protected void init() {
        DataLogicAdmin dlAdmin = (DataLogicAdmin) app.getBean("com.openbravo.pos.admin.DataLogicAdmin"); 
        tresources = dlAdmin.getTableResources();         
        jeditor = new ResourcesView(dirty);           
    }    
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(tresources);
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tresources);        
    }
    
    public Vectorer getVectorer() {
        return tresources.getVectorerBasic(new int[] {1});
    }
    
    public ComparatorCreator getComparatorCreator() {
        return tresources.getComparatorCreator(new int[] {1, 2});
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tresources.getRenderStringBasic(new int[] {1}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.Resources");
    }        
}
