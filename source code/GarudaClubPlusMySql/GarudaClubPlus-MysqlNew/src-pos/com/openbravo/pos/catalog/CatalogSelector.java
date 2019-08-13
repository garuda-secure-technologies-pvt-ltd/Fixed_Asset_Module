
package com.openbravo.pos.catalog;

import java.awt.Component;
import java.awt.event.ActionListener;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.ticket.CategoryInfo;
import java.util.List;

/**
 *
 * @author adrianromero
 */
public interface CatalogSelector {
    
    public void loadCatalog(List<CategoryInfo> category) throws BasicException;
    public void showCatalogPanel(String id);
    public void setComponentEnabled(boolean value);
    public Component getComponent();
    
    public void addActionListener(ActionListener l);  
    public void removeActionListener(ActionListener l);    
}
