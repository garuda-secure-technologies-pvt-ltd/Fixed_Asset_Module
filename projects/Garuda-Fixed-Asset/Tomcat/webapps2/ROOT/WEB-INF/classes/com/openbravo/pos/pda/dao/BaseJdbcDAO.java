

package com.openbravo.pos.pda.dao;

import com.openbravo.pos.pda.util.PropertyUtils;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URLClassLoader;
/**
 *
 * @author jaroslawwozniak
 * @version 1.0
 */
public class BaseJdbcDAO {

    private PropertyUtils properties;
    private Connection conn=null;

    public BaseJdbcDAO() {
        properties = new PropertyUtils();
    }

    public Connection getConnection() {
        try {
            if(conn==null){
            ClassLoader cloader = new URLClassLoader(new URL[] {new File(properties.getLibrary()).toURI().toURL()});
//            Class.forName(properties.getDriverName(),true,cloader).newInstance();
//            conn=DriverManager.getConnection(properties.getUrl(), properties.getDBUser(), properties.getDBPassword());
            Class.forName(properties.getDriverName());
            conn = DriverManager.getConnection(properties.getUrl(), properties.getDBUser(), properties.getDBPassword());
            }
       } catch (SQLException sqlex) {
            sqlex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return conn;
    }

    
   
}