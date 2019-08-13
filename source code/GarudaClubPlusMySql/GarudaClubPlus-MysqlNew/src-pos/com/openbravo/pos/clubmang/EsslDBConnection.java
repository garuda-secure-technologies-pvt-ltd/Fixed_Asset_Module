/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.microsoft.jdbcx.sqlserver.SQLServerDataSource;
import com.openbravo.pos.forms.AppProperties;
import com.openbravo.pos.forms.AppView;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class EsslDBConnection {

    private static Connection conn;
    private static AppView m_app;

    public static Connection getEsslDBConnection(AppView app) {
        m_app = app;
        try {
            if (conn == null || conn.isClosed() == true) {
                Connect();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    private static void Connect() {


        AppProperties props = m_app.getProperties();
        String dbName = props.getProperty("edbname");
        String ipaddr = props.getProperty("edbserver");
        String dbusername = props.getProperty("edbuser");
        String password = props.getProperty("edbpass");
        int portNo = Integer.valueOf(props.getProperty("edbport"));
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName(ipaddr + "\\SQLEXPRESS");
            ds.setPortNumber(portNo);
            ds.setDatabaseName(dbName);
            ds.setUser(dbusername);
            ds.setPassword(password);
            conn = ds.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
