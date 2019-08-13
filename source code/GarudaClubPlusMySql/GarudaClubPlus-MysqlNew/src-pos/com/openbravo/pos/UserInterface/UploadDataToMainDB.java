/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.UserInterface;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppProperties;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DriverWrapper;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.AltEncrypter;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class UploadDataToMainDB {
     private Connection conn;
     private String path;
     private String username;
     private String password;
     private AppView app;

    public UploadDataToMainDB(AppView app) {
        this.app=app;
    }

     public void connectToHSQLDB(String path,String username,String password){
        try {
            AppProperties props=app.getProperties();
            this.path = path;
            this.username=username;
            this.password=password;
            ClassLoader cloader = new URLClassLoader(new URL[]{new File(props.getProperty("db.driverlib")).toURI().toURL()});
            DriverManager.registerDriver(new DriverWrapper((Driver) Class.forName(props.getProperty("db.driver"),true,cloader).newInstance()));
            
            conn = DriverManager.getConnection("jdbc:hsqldb:file://" + path, username, password);
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
           ex.printStackTrace();
        }
            //connection = DriverManager.getConnection("jdbc:mysql://" + "www.busc.in/buscin1_buscuserdb", "buscin1", "RC7Y*Q9M");
     }
     public Connection getConnection() throws SQLException{
        if(conn!=null && conn.isClosed()==false)
            return conn;
        else{
           connectToHSQLDB(path,username,password);
           return conn;
        }

     }
     private ResultSet CreateStatementandExecute(String sql) throws SQLException{
         PreparedStatement stm=conn.prepareStatement(sql);
         ResultSet rs= stm.executeQuery();
         return rs;
     }
     public void copyData(){
        try {
            List<Object[]> feedbacklist=new ArrayList<Object[]>();
            List<Object[]> requestslist=new ArrayList<Object[]>();
            List<Object[]> maillist=new ArrayList<Object[]>();
            String sql="SELECT FEEDBACKID,NAME,EMAIL,COMMENT FROM FEEDBACKS";
            ResultSet rs=CreateStatementandExecute(sql);
            while(rs.next()){
                Object[] obj=new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                feedbacklist.add(obj);
            }
            /*sql="SELECT REQUESTID,USERID,FROM_,TO_ FROM REQUESTS";
            rs=CreateStatementandExecute(sql);
            while(rs.next()){
                Object[] obj=new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                requestslist.add(obj);
            }*/
            sql="SELECT ID,MSGID,SUBJECT,CONTENT,ATTACHLOC,_FROM,_TO,TOID FROM MAILS";
            rs=CreateStatementandExecute(sql);
            while(rs.next()){
                Object[] obj=new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
                maillist.add(obj);
            }
            for(Object[] obj:feedbacklist){
                new PreparedSentence(app.getSession(), "INSERT INTO FEEDBACKS(FEEDBACKID,NAME,EMAIL,COMMENT) VALUES (?,?,?,?) "
                        , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING})).exec(obj);
            }
           /* for(Object[] obj:feedbacklist){
                new PreparedSentence(app.getSession(), "INSERT INTO REQUESTS(REQUESTSID,USERID,_FROM,_TO) VALUES (?,?,?,?) "
                        , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING})).exec(obj);
            }*/
            for(Object[] obj:maillist){
                  new PreparedSentence(app.getSession(), "INSERT INTO MAILS (ID,MSGID,SUBJECT,CONTENT,ATTACHLOC,_FROM,_TO,TOID) VALUES (?,?,?,?,?,?,?,?) "
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(obj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }
}
