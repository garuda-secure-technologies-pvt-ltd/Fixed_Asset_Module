package com.openbravo.pos.pda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jaroslawwozniak
 */
public class PropertyUtils {

    private Properties m_propsconfig;
    private File configFile;
    private final String APP_ID = "garudaposPDA";

    public PropertyUtils() {
        init(getDefaultConfig());
    }

    private void init(File configfile) {
        this.configFile = configfile;
        load();
    }

    private File getDefaultConfig() {
        return new File(new File(System.getProperty("user.home")), APP_ID + ".properties");
    }

    private void load() {
    	
        // Cargo las propiedades
        try {
            InputStream in = new FileInputStream(configFile);
            if (in != null) {
                m_propsconfig = new Properties();
                m_propsconfig.load(in);
                in.close();
            }
        } catch (IOException e) {
        }
    }

    public String getProperty(String sKey) {
        return m_propsconfig.getProperty(sKey);
    }

    public String getDriverName() {
        return m_propsconfig.getProperty("db.driver");
    }

    public String getUrl() {
        return m_propsconfig.getProperty("db.URL");
    }

    public String getLibrary(){
         return m_propsconfig.getProperty("db.driverlib");
    }

    public String getDBUser() {
        return m_propsconfig.getProperty("db.user");
    }

    public String getDBPassword() {
    	
//        CryptUtils cypher = new CryptUtils("cypherkey" + getDBUser());
//
//        return cypher.decrypt(m_propsconfig.getProperty("db.password").substring(6));
    	return m_propsconfig.getProperty("db.password");
    }
    public String getCompName()
    {
         return m_propsconfig.getProperty("db.compName");
    }

}
