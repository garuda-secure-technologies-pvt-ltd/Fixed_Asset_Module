/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.ESSLDisplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author Ratan
 */
public class Property {
    private String rdbServer;
    private String rdbport;
    private String rdbdbname;
    private String adbServer;
    private String adbport;
    private String adbdbname;
    private String serialNumber;
    private String licenseKey;
    private String countcheckval;
    private String DBusername;
    private String DBpassword;
    private String fingerprintfolder;
    private String vbexe;
    private String driver;
    private String url;
    private String MyUsername;
    private String MyPassword;



    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMyPassword() {
        return MyPassword;
    }

    public String getMyUsername() {
        return MyUsername;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public void setAdbServer(String adbServer) {
        this.adbServer = adbServer;
    }

    public void setAdbdbname(String adbdbname) {
        this.adbdbname = adbdbname;
    }

    public void setAdbport(String adbport) {
        this.adbport = adbport;
    }

    public void setRdbServer(String rdbServer) {
        this.rdbServer = rdbServer;
    }

    public void setRdbdbname(String rdbdbname) {
        this.rdbdbname = rdbdbname;
    }

    public void setRdbport(String rdbport) {
        this.rdbport = rdbport;
    }
    public void setSerailNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }
    public void setLicenseKey(String licenseKey){
        this.licenseKey = licenseKey;
    }
    public void setfingerprintFolder(String fingerprintfolder){
        this.fingerprintfolder = fingerprintfolder;
    }
    public void setvbExe(String vbexe){
        this.vbexe = vbexe;
    }
    public void setcountcheckval(String countcheckval){
        this.countcheckval = countcheckval;
    }


    public String getfingerprintFolder() {
        return fingerprintfolder;
    }
    public String getvbExe() {
        return vbexe;
    }
    public String getAdbServer() {
        return adbServer;
    }

    public String getAdbdbname() {
        return adbdbname;
    }

    public String getAdbport() {
        return adbport;
    }

    public String getRdbServer() {
        return rdbServer;
    }

    public String getRdbdbname() {
        return rdbdbname;
    }

    public String getRdbport() {
        return rdbport;
    }
    public String getSerailNumber(){
        return serialNumber;
    }
    public String getLicenseKey(){
        return licenseKey;
    }
    public String getcountcheckval(){
        return countcheckval;
    }
    public String getDBusername(){
        return DBusername;
    }
   public String getDBpassword(){
        return DBpassword;
    }
   public synchronized  void store() throws FileNotFoundException, IOException{
       Properties prop=new Properties();
       prop.setProperty("rServer",rdbServer);
       prop.setProperty("rDBName", rdbdbname);
       prop.setProperty("rDBPort", rdbport);
       prop.setProperty("aDBPort", adbport);
       prop.setProperty("aDBName", adbdbname);
       prop.setProperty("aDBServer", adbServer);
       prop.setProperty("DBusername", "as");
       prop.setProperty("DBpassword", "as");
       prop.setProperty("db.driver", driver);
       prop.setProperty("db.url", url);
       prop.setProperty("db.user",MyUsername);
       prop.setProperty("db.password",MyPassword);
       File f=new File("GarduaRegistration.properties");
       if(!f.exists())
       f.createNewFile();
       FileOutputStream fout=new FileOutputStream(f);
       prop.store(fout, "Properties");
       fout.close();

   }

    public synchronized void Read(){
        InputStreamReader reader = null;
        Properties prop = new Properties();
        try {
            reader = new InputStreamReader(new FileInputStream("C:/GarduaRegistration.properties"));
            prop.load(reader);
            rdbServer=prop.getProperty("rServer");
            rdbdbname=prop.getProperty("rDBName");
            rdbport=prop.getProperty("rDBPort");
            adbServer=prop.getProperty("aDBServer");
            adbdbname=prop.getProperty("aDBName");
            adbport=prop.getProperty("aDBPort");
            DBusername=prop.getProperty("DBusername");
            DBpassword=prop.getProperty("DBpassword");
            driver=prop.getProperty("mydriver");
            url=prop.getProperty("myurl");
            MyPassword=prop.getProperty("mypassword");
            MyUsername=prop.getProperty("myuser");

            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }  finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}