/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
class Session {
     private String m_surl;
    private String m_suser;
    private String m_spassword;
    private String companyName;
    private String companyAddress;
    private Connection m_c;
    private boolean m_bInTransaction;
    
    /** Creates a new instance of Session */
    public Session(String url, String user, String password) throws SQLException {
        m_surl = url;
        m_suser = user;
        m_spassword = password;
        
        m_c = null;
        m_bInTransaction = false;
        
        connect(); // no lazy connection
    }
    
    public void connect() throws SQLException {
        
        // primero cerramos si no estabamos cerrados
        close();
        System.out.println(m_surl);
        // creamos una nueva conexion.
        m_c = (m_suser == null && m_spassword == null)
        ? DriverManager.getConnection(m_surl)
        : DriverManager.getConnection(m_surl, m_suser, m_spassword);         
        m_c.setAutoCommit(true);
        m_bInTransaction = false;
    }     

    public void close() {
        
        if (m_c != null) {
            try {
                if (m_bInTransaction) {
                    m_bInTransaction = false; // lo primero salimos del estado
                    m_c.rollback();
                    m_c.setAutoCommit(true);  
                }            
                m_c.close();
            } catch (SQLException e) {
                // me la como
            } finally {
                m_c = null;
            }
        }
    }
    
    public Connection getConnection() throws SQLException {
        
        if (!m_bInTransaction) {
            ensureConnection();
        }
        return m_c;
    }
    
    public void begin() throws SQLException {
        
        if (m_bInTransaction) {
            throw new SQLException("Already in transaction");
        } else {
            ensureConnection();
            m_c.setAutoCommit(false);
            m_bInTransaction = true;
        }
    }
    public void commit() throws SQLException {
        if (m_bInTransaction) {
            m_bInTransaction = false; // lo primero salimos del estado
            m_c.commit();
            m_c.setAutoCommit(true);          
        } else {
            throw new SQLException("Transaction not started");
        }
    }
    public void rollback() throws SQLException {
        if (m_bInTransaction) {
            m_bInTransaction = false; // lo primero salimos del estado
            m_c.rollback();
            m_c.setAutoCommit(true);            
        } else {
            throw new SQLException("Transaction not started");
        }
    }
    public boolean isTransaction() {
        return m_bInTransaction;
    }
    
    private void ensureConnection() throws SQLException {
        // solo se invoca si isTransaction == false
        
        boolean bclosed;
        try {
            bclosed = m_c == null || m_c.isClosed();
        } catch (SQLException e) {
            bclosed = true;
        }

        // reconnect if closed
        if (bclosed) {
            connect();
        }
    }  

    public String getCompanyName(){
       return companyName;
    }
    public String getCompanyAddress(){
       return companyAddress;
    }

    public void setCompanyName( String cname){
       companyName=cname;
    }
    public void setCompanyAddress(String caddr){
       companyAddress=caddr;
    }

    public String getDatabaseName() throws SQLException {
        
        return getConnection().getMetaData().getDatabaseProductName();                   
    }
    
    public String getURL() throws SQLException {
        return getConnection().getMetaData().getURL();
    }
    
}
