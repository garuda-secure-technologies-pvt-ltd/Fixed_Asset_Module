/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.ESSLDisplay;

import com.microsoft.jdbcx.sqlserver.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ESSLDBSession {
private Connection conn;
    private String dbName;
    private int portNo;
    private String ipaddr;
    private String dbusername;
    private String password;

    public ESSLDBSession(){

    }

    public ESSLDBSession(String ipaddr,int portNo,String dbName,String dbusername,String password) throws SQLException {
        this.dbName=dbName;
        this.ipaddr=ipaddr;
        this.portNo=portNo;
        this.dbusername = dbusername;
         this.password=password;
       
    }



     public void Connect() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName(ipaddr+"\\SQLEXPRESS");
            ds.setPortNumber(portNo);
            ds.setDatabaseName(dbName);
            ds.setUser(dbusername);
             ds.setPassword(password);
            conn = ds.getConnection();
            
        } catch (Exception ex) {
           ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Connection to server failed"+ex.getMessage());
            conn=null;
        }
    }

    public Connection getConnection() throws SQLException{
        if(conn==null || conn.isClosed()==true){
            Connect();
        }
        return conn;
    }

    @Override
    protected void finalize() throws Throwable {
        conn.close();
    }
}
