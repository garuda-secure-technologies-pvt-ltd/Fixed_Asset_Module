/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.ESSLDisplay;


import java.sql.SQLException;

/**
 *
 * @author user
 */
public class DBConnection {

    private static ESSLDBSession esslSession;

    public static ESSLDBSession getEsslConnection() throws SQLException {

        if (esslSession == null) {
            Property prop = new Property();
            prop.Read();
            String eServer = prop.getAdbServer();
            String eDBName = prop.getRdbdbname();
            String pno = prop.getAdbport();
            String eDBuser = prop.getDBusername();
            String epass = prop.getDBpassword();
            int eportNo = -1;
            if (pno != null) {
                eportNo = Integer.valueOf(String.valueOf(pno));
            }
            esslSession = new ESSLDBSession(eServer, eportNo, eDBName, eDBuser, epass);
           
        }
        return esslSession;
    }

  
}
