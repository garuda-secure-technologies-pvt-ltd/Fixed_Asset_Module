

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.util.ThumbNailBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class JPanelButtons extends javax.swing.JPanel {
    
    private static SAXParser m_sp = null;
    
    private Properties props;
    private Map<String, String> events;
    
    private ThumbNailBuilder tnbmacro;
    
    private JPanelTicket panelticket;
    
    /** Creates new form JPanelButtons */
    public JPanelButtons(String sConfigKey, JPanelTicket panelticket) {
        initComponents();
        
        // Load categories default thumbnail
        tnbmacro = new ThumbNailBuilder(16, 16, "com/openbravo/images/greenled.png");
        
        this.panelticket = panelticket;
        
        props = new Properties();
        events = new HashMap<String, String>();
        
        String sConfigRes = panelticket.getResourceAsXML(sConfigKey);
        
        if (sConfigRes != null) {
            try {
                if (m_sp == null) {
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    m_sp = spf.newSAXParser();
                }
                m_sp.parse(new InputSource(new StringReader(sConfigRes)), new ConfigurationHandler());

            } catch (ParserConfigurationException ePC) {
                System.out.println("Error en el analizador XML. Consulte con su administrador");
            } catch (SAXException eSAX) {
                System.out.println("El archivo no es un documento XML valido. Error de analisis.");
            } catch (IOException eIO) {
                System.out.println("Error al leer el archivo. Consulte con su administrador.");
            }
        }     
    
    }
    
    public void setPermissions(AppUser user) {
        for (Component c : this.getComponents()) {
            String sKey = c.getName();
            if (sKey == null || sKey.equals("")) {
                c.setEnabled(true);
            } else {
                c.setEnabled(user.hasPermission(c.getName()));
            }
        }
    }
    
    public String getProperty(String key) {
        return props.getProperty(key);
    }
    
     public String getProperty(String key, String defaultvalue) {
        return props.getProperty(key, defaultvalue);
    }
     
    public String getEvent(String key) {
        return events.get(key);
    }
    
    private class ConfigurationHandler extends DefaultHandler {       
        @Override
        public void startDocument() throws SAXException {}
        @Override
        public void endDocument() throws SAXException {}    
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
            if ("button".equals(qName)){
                
                // The template resource
                String stemplate = attributes.getValue("template");
                
                // The button title text
                String titlekey = attributes.getValue("titlekey");
                if (titlekey == null) {
                    titlekey = attributes.getValue("name");
                }
                String title = titlekey == null
                        ? attributes.getValue("title")
                        : AppLocal.getIntString(titlekey);
                
                // adding the button to the panel
                add(new JButtonFunc(
                        attributes.getValue("key"), 
                        attributes.getValue("image"), 
                        title,  
                        stemplate == null
                            ? panelticket.getResourceAsXML(attributes.getValue("code"))
                            : "sales.printTicket(\"" + stemplate + "\");"));
            } else if ("event".equals(qName)) {
                events.put(attributes.getValue("key"), panelticket.getResourceAsXML(attributes.getValue("code")));
            } else {
                String value = attributes.getValue("value");
                if (value != null) {                  
                    props.setProperty(qName, attributes.getValue("value"));
                }
            }
        }      
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {}
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {}
    }  
        
    private class JButtonFunc extends JButton {
        private String m_sCode;
        
        public JButtonFunc(String sKey, String sImage, String title, String sCode) {
            
            m_sCode = sCode;
            setName(sKey);
            setText(title);
            setIcon(new ImageIcon(tnbmacro.getThumbNail(panelticket.getResourceAsImage(sImage))));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setMargin(new Insets(8, 14, 8, 14));
  
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        panelticket.evalScriptAndRefresh(m_sCode);
                    } catch (BasicException ex) {
                        Logger.getLogger(JPanelButtons.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
        }         
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
