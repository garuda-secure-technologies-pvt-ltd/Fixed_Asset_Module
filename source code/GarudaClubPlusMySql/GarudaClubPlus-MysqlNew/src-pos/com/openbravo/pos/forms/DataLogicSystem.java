
package com.openbravo.pos.forms;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.imageio.ImageIO;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.*;
import com.openbravo.format.Formats;
import com.openbravo.pos.payment.ChequeDetails;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.util.ThumbNailBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author adrianromero
 */
public class DataLogicSystem extends BeanFactoryDataSingle {
    
    private Session s;
    protected String m_sInitScript;
    private SentenceFind m_version;    
    private SentenceFind m_libreposversion;    
    private SentenceExec m_dummy;
    
    protected SentenceList m_peoplevisible;  
    protected SentenceFind m_peoplebycard;  
    protected SerializerRead peopleread;
    
    private SentenceFind m_rolepermissions; 
    private SentenceExec m_changepassword;    
    private SentenceFind m_locationfind;
    
    private SentenceFind m_resourcebytes;
    private SentenceExec m_resourcebytesinsert;
    private SentenceExec m_resourcebytesupdate;

    protected SentenceFind m_activecash;
    protected SentenceExec m_insertcash;
    protected  static boolean flag=false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag1) {
        flag = flag1;
    }

   
    
    /** Creates a new instance of DataLogicSystem */
    public DataLogicSystem() {            
    }
    
    public void activate(Session s){
        if(flag)
          init(s);
        setFlag(true);
    }
    
    public void init(Session s){
        this.s = s;
        
        m_version = new PreparedSentence(s, "SELECT VERSION FROM APPLICATIONS WHERE ID = ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE);
        m_libreposversion = new PreparedSentence(s, "SELECT VERSION FROM LIBREPOS", null, SerializerReadString.INSTANCE);
        m_dummy = new StaticSentence(s, "SELECT * FROM PEOPLE WHERE 1 = 0");
          //warehouse changes -start
        final ThumbNailBuilder tnb = new ThumbNailBuilder(32, 32, "com/openbravo/images/yast_sysadmin.png");        
        peopleread = new SerializerRead() {
            public Object readValues(DataRead dr) throws BasicException {
                AppUser u = new AppUser(
                        dr.getString(1),
                        dr.getString(2),
                        dr.getString(3),
                        dr.getString(4),
                        dr.getString(5),
                        new ImageIcon(tnb.getThumbNail(ImageUtils.readImage(dr.getBytes(6)))),
                        dr.getTimestamp(7),
                        dr.getTimestamp(8),
                        dr.getTimestamp(9),
                        dr.getTimestamp(10),
                        dr.getTimestamp(11),
                        dr.getString(12),
                        dr.getString(13),
                        dr.getString(14));
                return u;
            }
        };
         //warehouse changes -end
         
        m_resourcebytes = new PreparedSentence(s
            , "SELECT CONTENT FROM RESOURCES WHERE NAME = ?"
            , SerializerWriteString.INSTANCE
            , SerializerReadBytes.INSTANCE);
        
        Datas[] resourcedata = new Datas[] {Datas.STRING, Datas.STRING, Datas.INT, Datas.BYTES};
        m_resourcebytesinsert = new PreparedSentence(s
                , "INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES (?, ?, ?, ?)"
                , new SerializerWriteBasic(resourcedata));
        m_resourcebytesupdate = new PreparedSentence(s
                , "UPDATE RESOURCES SET NAME = ?, RESTYPE = ?, CONTENT = ? WHERE NAME = ?"
                , new SerializerWriteBasicExt(resourcedata, new int[] {1, 2, 3, 1}));
        
        m_rolepermissions = new PreparedSentence(s
                , "SELECT PERMISSIONS FROM ROLES WHERE ID = ?"
            , SerializerWriteString.INSTANCE
            , SerializerReadBytes.INSTANCE);     
        
        m_changepassword = new StaticSentence(s
                , "UPDATE PEOPLE SET APPPASSWORD = ? WHERE ID = ?"
                ,new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.STRING}));
        
        m_activecash = new StaticSentence(s
            , "SELECT HOST, HOSTSEQUENCE, DATESTART, DATEEND FROM CLOSEDCASH WHERE MONEY = ?"
            , SerializerWriteString.INSTANCE
            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.INT, Datas.TIMESTAMP, Datas.TIMESTAMP}));            
        m_insertcash = new StaticSentence(s
                , "INSERT INTO CLOSEDCASH(MONEY,  HOSTSEQUENCE,HOST, DATESTART, USER_) " +
                  "VALUES (?, ?, ?, ?, ?)"
                , new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.INT,Datas.STRING, Datas.TIMESTAMP, Datas.STRING}));
                                   //line-103 change:USER -> USER_
        m_locationfind = new StaticSentence(s
                , "SELECT NAME FROM LOCATIONS WHERE ID = ?"
                , SerializerWriteString.INSTANCE
                , SerializerReadString.INSTANCE);        
        
    }


    public String getInitScript() {
        return m_sInitScript;
    }
    
//    public abstract BaseSentence getShutdown();
    
    public final String findVersion() throws BasicException {
        return (String) m_version.find(AppLocal.APP_ID);
    }
    public final String findLibreposVersion() throws BasicException {
        return (String) m_libreposversion.find();
    }
    public final void execDummy() throws BasicException {
        m_dummy.exec();
    }
    public final List listPeopleVisible() throws BasicException {
        return m_peoplevisible.list();
    }

    public final AppUser findPeopleByCard(String card) throws BasicException {
        return (AppUser) m_peoplebycard.find(card);
    }   
    
    public final String findRolePermissions(String sRole) {
        
        try {
            return Formats.BYTEA.formatValue(m_rolepermissions.find(sRole));        
        } catch (BasicException e) {
            return null;                    
        }             
    }
    
    public final void execChangePassword(Object[] userdata) throws BasicException {
        m_changepassword.exec(userdata);
    }
    
    private final byte[] getResource(String sName) {

        byte[] resource;
        
        // Primero trato de obtenerlo de la tabla de recursos
        try {
            resource = (byte[]) m_resourcebytes.find(sName);
        } catch (BasicException e) {
            resource = null;
        }
        
        return resource;
    }
    
    public final void setResource(String sName, int iType, byte[] data) {
        
        Object[] value = new Object[] {UUID.randomUUID().toString(), sName, new Integer(iType), data};
        try {
            if (m_resourcebytesupdate.exec(value) == 0) {
                m_resourcebytesinsert.exec(value);
            }
        } catch (BasicException e) {
        }
    }
    
    public final void setResourceAsBinary(String sName, byte[] data) {
        setResource(sName, 2, data);
    }
    
    public final byte[] getResourceAsBinary(String sName) {
        return getResource(sName);
    }
    
    public final String getResourceAsText(String sName) {
        return Formats.BYTEA.formatValue(getResource(sName));
    }
    
    public final String getResourceAsXML(String sName) {
        return Formats.BYTEA.formatValue(getResource(sName));
    }    
    
    public final BufferedImage getResourceAsImage(String sName) {
        try {
            byte[] img = getResource(sName); // , ".png"
            return img == null ? null : ImageIO.read(new ByteArrayInputStream(img));
        } catch (IOException e) {
            return null;
        }
    }
    
    public final void setResourceAsProperties(String sName, Properties p) {
        if (p == null) {
            setResource(sName, 0, null); // texto
        } else {
            try {
                ByteArrayOutputStream o = new ByteArrayOutputStream();
                p.storeToXML(o, AppLocal.APP_NAME, "UTF8");
                setResource(sName, 0, o.toByteArray()); // El texto de las propiedades   
            } catch (IOException e) { // no deberia pasar nunca
            }            
        }
    }
     public List getTicketPaymentsNew(final String ticket) throws BasicException {
        return new PreparedSentence(s
                , "SELECT TOTAL,PAYMENT,CHEQUENO,BANK,AMOUNT FROM ( SELECT P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT,C.CHEQUENO AS CHEQUENO,C.BANK AS BANK,C.AMOUNT AS AMOUNT FROM PAYMENTS P,CHEQUE C WHERE P.RECEIPT = ? AND P.PAYMENT ='cheque' AND P.RECEIPT=C.RNO"+
                   " UNION   SELECT TOTAL AS TOTAL, PAYMENT AS PAYMENT,NULL  AS CHEQUENO,NULL AS BANK,0.0 AS AMOUNT FROM PAYMENTS WHERE  PAYMENT != 'cheque' AND RECEIPT = ?  ) as paymen"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                , new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        if(dr.getString(2)!=null && dr.getString(2).equals("cheque")){
                             ChequeDetails cd =new ChequeDetails();
                             cd.setAmount(dr.getDouble(5));
                             cd.setChequeno(dr.getString(3));
                             cd.setBank(dr.getString(4));
                         return new PaymentInfoTicket(
                                dr.getDouble(1),
                                dr.getString(2),cd);
                        }else{
                            return new PaymentInfoTicket(
                                dr.getDouble(1),
                                dr.getString(2));
                        }
                    }
                }).list(new Object[]{ticket,ticket});
    }
    public final Properties getResourceAsProperties(String sName) {
        
        Properties p = new Properties();
        try {
            byte[] img = getResourceAsBinary(sName);
            if (img != null) {
                p.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
        }
        return p;
    }    

    public final Object[] findActiveCash(String sActiveCashIndex) throws BasicException {
        return (Object[]) m_activecash.find(sActiveCashIndex);
    }
    
    public final void execInsertCash(Object[] cash) throws BasicException {
        m_insertcash.exec(cash);
    } 
    
    public final String findLocationName(String iLocation) throws BasicException {
        return (String) m_locationfind.find(iLocation);
    }

    public void updateUser(final AppUser u) throws BasicException {
        
         if(u.hasPermission("com.openbravo.pos.panels.JPanelCloseSale"))
        {
             new PreparedSentence(s
                , "UPDATE PEOPLE SET LOGINTIME = ?, OPENCASHTIME = ?, CLOSECASHTIME = ?, CLOSESALE = ?,IPADDR=? WHERE ID = ?"
                , SerializerWriteParams.INSTANCE).exec(new DataParams() {

            @Override
            public void writeValues() throws BasicException {
                setTimestamp(1, u.getLastLoginTime());
                setTimestamp(2, u.getOpenCashTime());
                setTimestamp(3, u.getCloseCashTime());
               //setTimestamp(4, u.getOpenSaleTime());
                setTimestamp(4, u.getCloseSaleTime());
                setString(5, u.getIpAddr());
                setString(6, u.getId());

            }
        });
        }
         else{
        
        new PreparedSentence(s
                , "UPDATE PEOPLE SET LOGINTIME = ?, OPENCASHTIME = ?, CLOSECASHTIME = ?, OPENSALE = ?, CLOSESALE = ?,IPADDR=? WHERE ID = ?"
                , SerializerWriteParams.INSTANCE).exec(new DataParams() {

            @Override
            public void writeValues() throws BasicException {
                setTimestamp(1, u.getLastLoginTime());
                setTimestamp(2, u.getOpenCashTime());
                setTimestamp(3, u.getCloseCashTime());
                setTimestamp(4, u.getOpenSaleTime());
                setTimestamp(5, u.getCloseSaleTime());
                setString(6, u.getIpAddr());
                setString(7, u.getId());

            }
        });
      }
       
        
      
    }
    
   
     
     
    
}
