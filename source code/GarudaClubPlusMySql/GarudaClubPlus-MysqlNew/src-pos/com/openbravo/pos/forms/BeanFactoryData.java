

package com.openbravo.pos.forms;

import java.sql.SQLException;

/**
 *
 * @author adrianromero
 */
public class BeanFactoryData implements BeanFactoryApp {

    public static final String DB_HSQLDB = "HSQLDB";
    public static final String DB_MYSQL = "MySQL";
    public static final String DB_ORACLE = "Oracle";
    public static final String DB_POSTGRESQL = "PostgreSQL";
    
    private BeanFactoryApp bf;
    
    /** Creates a new instance of BeanFactoryData */
    public BeanFactoryData() {
    }
    
    public void init(AppView app) throws BeanFactoryException {  
        
        try {
            
            String sfactoryname = this.getClass().getName();
            if (sfactoryname.endsWith("Create")) {
                sfactoryname = sfactoryname.substring(0, sfactoryname.length() - 6);
            }
            bf = (BeanFactoryApp) Class.forName(sfactoryname + getDatabaseSufix(app.getSession().getDatabaseName())).newInstance();                    
            bf.init(app);                     
        } catch (SQLException ex) {
            throw new BeanFactoryException(ex);
        } catch (Exception ex) {
            throw new BeanFactoryException(ex);
         }
    }   
    
    public Object getBean() {
        return bf.getBean();
    }      
    
    public static String getDatabaseSufix(String sdbmanager) throws SQLException {

        if ("HSQL Database Engine".equals(sdbmanager)) {
            return DB_HSQLDB;
        } else if ("MySQL".equals(sdbmanager)) {
            return DB_MYSQL;
        } else if ("PostgreSQL".equals(sdbmanager)) {
            return DB_POSTGRESQL;
        } else if ("Oracle".equals(sdbmanager)) {
            return DB_ORACLE;
        } else {
            throw new SQLException(AppLocal.getIntString("message.databasenotsupported", sdbmanager));
        }          
    }    
}
