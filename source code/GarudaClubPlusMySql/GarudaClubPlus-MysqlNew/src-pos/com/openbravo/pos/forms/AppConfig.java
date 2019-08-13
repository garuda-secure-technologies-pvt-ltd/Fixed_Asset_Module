

package com.openbravo.pos.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;
//import com.mysql.jdbc.*;


/**
 *
 * @author adrianromero
 */
public class AppConfig implements AppProperties {
     
    private Properties m_propsconfig;
    private File configfile;
      
    public AppConfig(String[] args) {
        if (args.length == 0) {
            init(getDefaultConfig());
        } else {
            init(new File(args[0]));
        }
    }
    
    public AppConfig(File configfile) {
        init(configfile);
    }
    
    /** Creates a new instance of AppConfig */
    public AppConfig() {
        init(getDefaultConfig());
    }
    
    private void init(File configfile) {
        this.configfile = configfile;
        m_propsconfig = new Properties();        
    }
    
    private File getDefaultConfig() {
        return new File(new File(System.getProperty("user.home")), AppLocal.APP_ID + ".properties");
    }
    
    public String getProperty(String sKey) {
        return m_propsconfig.getProperty(sKey);
    }
    
    public String getHost() {
        return getProperty("machine.hostname");
    } 
    
    public File getConfigFile() {
        return configfile;
    }
    
    public void setProperty(String sKey, String sValue) {
        m_propsconfig.setProperty(sKey, sValue);
    }
    
    private String getLocalHostName() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (java.net.UnknownHostException eUH) {
            return "localhost";
        }
    }
   
    public boolean delete() {
        return configfile.delete();
    }
    
    public void load() {

        // Cargo las propiedades
        try {
            InputStream in = new FileInputStream(configfile);
            if (in != null) {
                m_propsconfig = new Properties();
                m_propsconfig.load(in);
                in.close();
            }
        } catch (IOException e){
            loadDefault();
        }
    
    }
    
    public void save() throws IOException {
        
        OutputStream out = new FileOutputStream(configfile);
        if (out != null) {
            m_propsconfig.store(out, AppLocal.APP_NAME + ". Configuration file.");
            out.close();
        }
    }
    
    private void loadDefault() {
        
        m_propsconfig = new Properties();
        
        String dirname = System.getProperty("dirname.path");
        dirname = dirname == null ? "./" : dirname;
        
       m_propsconfig.setProperty("db.driverlib", new File(new File(dirname), "lib/mysql.jar").getAbsolutePath());
       /*  m_propsconfig.setProperty("db.driver", "org.hsqldb.jdbcDriver");
         m_propsconfig.setProperty("db.URL", "jdbc:hsqldb:file:" + new File(new File(System.getProperty("user.home")), AppLocal.APP_ID + "-db").getAbsolutePath() + ";shutdown=true");
       // m_propsconfig.setProperty("db.URL", "jdbc:hsqldb:hsql://192.168.1.151/" + AppLocal.APP_ID + "-db");
        m_propsconfig.setProperty("db.user", "sa");         
        m_propsconfig.setProperty("db.password", "");    */
        
    
        m_propsconfig.setProperty("db.driver", "com.mysql.jdbc.Driver");
        m_propsconfig.setProperty("db.URL", "jdbc:mysql://localhost:3306/garudaconame");
        m_propsconfig.setProperty("db.user", "root");         
        m_propsconfig.setProperty("db.password", "garuda");
       
   /*     m_propsconfig.setProperty("db.driver", "org.postgresql.Driver");
        m_propsconfig.setProperty("db.URL", "jdbc:postgresql://localhost:5432/database");
        m_propsconfig.setProperty("db.user", "user");         
        m_propsconfig.setProperty("db.password", "password");    */

        m_propsconfig.setProperty("machine.hostname", getLocalHostName());
        
        Locale l = Locale.getDefault();
        m_propsconfig.setProperty("user.language", l.getLanguage());
        m_propsconfig.setProperty("user.country", l.getCountry());
        m_propsconfig.setProperty("user.variant", l.getVariant());     
        
        m_propsconfig.setProperty("swing.defaultlaf", System.getProperty("swing.defaultlaf", "javax.swing.plaf.metal.MetalLookAndFeel"));
        
        m_propsconfig.setProperty("machine.printer", "screen");
        m_propsconfig.setProperty("machine.printer.2", "Not defined");
        m_propsconfig.setProperty("machine.printer.3", "Not defined");
        m_propsconfig.setProperty("machine.display", "screen");
        m_propsconfig.setProperty("machine.scale", "Not defined");
        m_propsconfig.setProperty("machine.screenmode", "window"); // fullscreen / window
        m_propsconfig.setProperty("machine.ticketsbag", "restaurant");
        m_propsconfig.setProperty("machine.scanner", "Not defined");
        
        m_propsconfig.setProperty("payment.gateway", "external");
        m_propsconfig.setProperty("payment.magcardreader", "Not defined");
        m_propsconfig.setProperty("payment.testmode", "false");
        m_propsconfig.setProperty("payment.commerceid", "");
        m_propsconfig.setProperty("payment.commercepassword", "");
        m_propsconfig.setProperty("card.portnumber", "COM2");
         m_propsconfig.setProperty("ACScard.port", "COM1");
        m_propsconfig.setProperty("cardAccessOnly", "false");
        m_propsconfig.setProperty("OldAccountFilePath", "C");
        //sameer: adding properties to connect with essl db
        m_propsconfig.setProperty("edbserver", "192.168.1.151");
        m_propsconfig.setProperty("edbport", "1443");
        m_propsconfig.setProperty("edbname", "Fingerprint");
        m_propsconfig.setProperty("edbuser","as");
        m_propsconfig.setProperty("edbpass", "as");
    }

    public boolean getProperty(boolean bkey) {
        return bkey;
    }

    public String getProperty1(String bkey) {
         return m_propsconfig.getProperty(bkey);
    }
}
