
package com.openbravo.pos.forms;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author adrianromero
 */
public interface AppProperties {

    public File getConfigFile(); 
    public String getHost();    
    public String getProperty(String sKey); // Config property.
    public void setProperty(String sKey, String sValue);
    public void save() throws IOException ;
     public String getProperty1(String bkey);
}
