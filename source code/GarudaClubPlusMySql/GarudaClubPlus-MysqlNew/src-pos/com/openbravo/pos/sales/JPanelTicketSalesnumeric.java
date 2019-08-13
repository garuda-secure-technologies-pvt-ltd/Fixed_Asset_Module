

package com.openbravo.pos.sales;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.catalog.CatalogSelector;
import com.openbravo.pos.catalog.JCatalog;
import com.openbravo.pos.catalog.JCatalognum1;
import com.openbravo.pos.catalog.JCatalog1;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class JPanelTicketSalesnumeric extends JPanelTicketnum1 {

 
    private CatalogSelector m_cat1;
   
    /** Creates a new instance of JPanelTicketSales */
    public JPanelTicketSalesnumeric() {
         
    }
    
    @Override
    public void init(AppView app) {
          Date dNow = new Date();
      //      String username=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
      /*      try{
         new StaticSentence(app.getSession()
                        , "UPDATE PEOPLE SET  CLOSESALE=NULL AND OPENSALE = ?   WHERE CLOSESALE IS NOT NULL AND NAME = ?  "
                        , new SerializerWriteBasic(new Datas[] {Datas.TIMESTAMP, Datas.STRING }))
                        .exec(new Object[] {dNow, username});

            }
            catch(BasicException e)
            {
            }*/
        super.init(app);
        m_ticketlines.addListSelectionListener(new CatalogSelectionListener());
    }
    
    public String getTitle() {
        return null;
    }
    
   
    
     protected Component getSouthComponentnum() {
        m_cat1 = new JCatalognum1(dlSales,
                "true".equals(m_jbtnconfig.getProperty("pricevisible")),
                "true".equals(m_jbtnconfig.getProperty("taxesincluded")),
                Integer.parseInt(m_jbtnconfig.getProperty("img-width", "64")),
                Integer.parseInt(m_jbtnconfig.getProperty("img-height", "54")));
        m_cat1.addActionListener(new CatalogListener());
        m_cat1.getComponent().setPreferredSize(new Dimension(
                0,
                Integer.parseInt(m_jbtnconfig.getProperty("cat-height", "245"))));
        return m_cat1.getComponent();
    }
    
    
    
    
    protected JTicketsBagnum1 getJTicketsBag() {
        return JTicketsBagnum1.createTicketsBagnum1(m_App.getProperties().getProperty("machine.ticketsbag"), m_App, this);
    }
    
    @Override
    public void activate() throws BasicException {      
        super.activate();
        //sameer:
        Object s=m_App.getAppUserView().getUser().getWarehouse();
         java.util.List<CategoryInfo> allCategories = new ArrayList<CategoryInfo>();
       
            if (s != null) {
                String[] warehouses = s.toString().split("#");
                for(String warehouse : warehouses){
                    java.util.List<CategoryInfo> categories = dlSales.getRootCategories(warehouse);
                    allCategories.addAll(categories);
                }
                
            }

        
        m_cat1.loadCatalog(allCategories);// loads category in bottom left
    }      
    
    private class CatalogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductInfoExt pdt= (ProductInfoExt) e.getSource();
            if(m_oTicket.getLines().size()>0){
              String warehouse = m_oTicket.getLines().get(0).getWarehouse();
              if(warehouse != null && warehouse.equals(pdt.getWarehouse())){
                  try{
                     buttonTransition(pdt);
                  }catch(BasicException EX) {
                      
                  }
              }else{
                JOptionPane.showMessageDialog(null, "Select Products from the same warehouse");
              }
            }else{
                try{
                    buttonTransition(pdt);
                }
                catch(BasicException EX){
                    
                }
            }
            
        }  
    }
    
    private class CatalogSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {      
            
            if (!e.getValueIsAdjusting()) {
                int i = m_ticketlines.getSelectedIndex();
                
                // Buscamos el primer producto no Auxiliar.
                while (i >= 0 && m_oTicket.getLine(i).isProductCom()) {
                    i--;
                }
                
                // Mostramos el panel de catalogo adecuado...
                if (i >= 0) {
                 /*   try {
                        //write acode here to check the stockDiary
                        new PreparedSentence(m_App.getSession(), "Select * from stockcurrent where ", null).exec();
                    } catch (BasicException ex) {
                        System.out.println("erorr!!");
                    } */
                  //  JOptionPane.showMessageDialog(null,"sdfg");
                  //   System.out.println("sad>"+m_oTicket.getLine(i).getProductID());
                 //    System.out.println("sad>"+m_oTicket.getLine(i).getProductName());
                    m_cat1.showCatalogPanel(m_oTicket.getLine(i).getProductID());
                } else {
                    m_cat1.showCatalogPanel(null);
                }
            }
        }  
    }
}
