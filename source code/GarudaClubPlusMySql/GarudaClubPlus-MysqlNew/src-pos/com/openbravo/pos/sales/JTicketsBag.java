

package com.openbravo.pos.sales;

import com.openbravo.pos.sales.simple.JTicketsBagSimple;
import com.openbravo.pos.forms.*; 
import com.openbravo.pos.sales.restaurant.JIntroPageRest;
import javax.swing.*;
import com.openbravo.pos.sales.shared.JTicketsBagShared;

public abstract class JTicketsBag extends JPanel {
    
    protected AppView m_App;     
    protected DataLogicSales m_dlSales;
    protected TicketsEditor m_panelticket;    

    
    /** Creates new form JTicketsBag */
    public JTicketsBag(AppView oApp, TicketsEditor panelticket) {        
        m_App = oApp;     
        m_panelticket = panelticket;        
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }
    
    public abstract void activate();
    public abstract boolean deactivate();
    public abstract boolean deleteTicket();
    
    protected abstract JComponent getBagComponent();
    protected abstract JComponent getNullComponent();
    
    public static JTicketsBag createTicketsBag(String sName, AppView app, TicketsEditor panelticket) {
        
        if ("standard".equals(sName)) {
            // return new JTicketsBagMulti(oApp, user, panelticket);
            return new JTicketsBagShared(app, panelticket);
        } else if ("restaurant".equals(sName)) {
            return new JIntroPageRest(app, panelticket);
        } else { // "simple"
            return new JTicketsBagSimple(app, panelticket);
        }
    }   
}
