package com.openbravo.pos.forms;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import java.net.InetAddress;

import com.openbravo.pos.printer.*;

import com.openbravo.beans.*;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ILookupUtility;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.LookupUtilityFactory;
import com.openbravo.data.loader.BatchSentence;
import com.openbravo.data.loader.BatchSentenceResource;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.LocalRes;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.CompanyCreator;
import com.openbravo.pos.Accounts.CompanyInfo;
import com.openbravo.pos.Accounts.OpenFinancialYear;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.Networking.ListenerSocket;
import com.openbravo.pos.Networking.SocketInfo;
import com.openbravo.pos.UserInterface.LoginPage;
import com.openbravo.pos.UserInterface.UserWelcomePage;
import com.openbravo.pos.clubmang.ReadCard;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.scale.DeviceScale;
import com.openbravo.pos.scanpal2.DeviceScanner;
import com.openbravo.pos.scanpal2.DeviceScannerFactory;
import com.openbravo.pos.sms.CardReader1;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.clubmang.CaluculateLimit;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.sales.restaurant.JIntroPageRest;
//import com.openbravo.pos.sms.SmsConnection;
import com.openbravo.pos.util.AltEncrypter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.List;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author adrianromero
 */
public class JRootApp extends JPanel implements AppView, CardSwipeNotifier {

    private AppProperties m_props;
    private Session session;
    private DataLogicSystem m_dlSystem;
    //private DataLogicSales m_dlSales;
    private Properties m_propsdb = null;
    private String m_sActiveCashIndex;
    private int m_iActiveCashSequence;
    private Date m_dActiveCashDateStart;
    private Date m_dActiveCashDateEnd;
    private String m_sInventoryLocation;
    private LoginPage ulpage;
    //private UserLoginFrame ulframe;
    private StringBuffer inputtext;
    private DeviceScale m_Scale;
    private DeviceScanner m_Scanner;
    private DeviceTicket m_TP;
    private CardReader1 rcard;
    private TicketParser m_TTP;
    public static Thread thread;
    private Map<String, BeanFactory> m_aBeanFactories;
    private JPrincipalApp m_principalapp = null;
    private static boolean flag;
    private static HashMap<String, String> m_oldclasses; // This is for backwards compatibility purposes
    public static Map<String, SocketInfo> socketList = new HashMap<String, SocketInfo>();
    public static boolean sSocketActive;
    private CardReader cr = null;
    boolean cardAccessOnlyFlag = false;
    private AppView m_App;
    private waitDialog w;
    private Connection con;
    private CardSwipeNotifier cardSwipeNotifier = null;
    private WaiterInfo waiterinfo;

    static {
        initOldClasses();
    }

    /**
     * Creates new form JRootApp
     */
    public JRootApp() {

        m_aBeanFactories = new HashMap<String, BeanFactory>();

        // Inicializo los componentes visuales
        initComponents();
        initDialog();
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));
        jButton1.setText("Create Company");
    }
    
    public void initDialog()
    {
        w = new waitDialog(new JFrame(), true);
        int h = w.getSize().height;
        int w1 = w.getSize().width;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);   
    }

    //praveen:added for waiter kiosk
    public void cardswiped(WaiterInfo waiter) {
        try {
            String id = waiter.getID();
            String role = waiter.getCounter();
            String name = waiter.getCardNo();
            //warehouse changes -start
            Object obj = new StaticSentence(session, "SELECT P.PRCATEGORIES FROM PEOPLE P WHERE P.ROLE=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(waiter.getCounter());
            String warehouse = null;
            if (obj != null) {
                warehouse = obj.toString();
            }
            //praveen or sameer write a query to get the print categories from the waiter counter
            AppUser user = new AppUser(id, name, role, warehouse);
            //warehouse changes -end
            user.setTypeOfUser(1);
            cr.setData("");
            openAppView(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cardswiped(String custCard) {
        AppUser user1 = new AppUser(custCard, "MEMBERS", null, null);
        user1.setTypeOfUser(2);
        cr.setData("");
        openAppView(user1);

    }
    //praveen:cardreader function

    private void startCardReader() {
        try {
            String portNumber = getProperties().getProperty("card.portnumber");

            if (getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf(getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
            cr.setCardSwipeNotifier(this);

            System.out.println(portNumber);
            System.out.println(getProperties().getProperty("cardAccessOnly"));
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    //praveen:end

    private String initClubPlus(final CompanyInfo cinfo) {
        try {

            session = AppViewConnection.createSession(m_props, cinfo.getUrl(), false);
            session.setCompanyName(cinfo.getDesc());
            session.setCompanyAddress(cinfo.getAddress());
            String temp = session.getURL();
            String host = null;
            String temp1[] = temp.split("/");
            if (temp1.length > 1) {
                host = temp1[2];
            }
            //ComputePi.Configure(host);

        } catch (Exception e1) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, e1.getMessage(), e1));
            return "false";
        }
        m_dlSystem = (DataLogicSystem) getBean("com.openbravo.pos.forms.DataLogicSystemCreate");

        m_dlSystem.activate(session);
        //Initialize LookupUtility
        // m_dlSales = (DataLogicSales) getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        startCardReader();

        // Create or upgrade the database if database version is not the expected
        String sDBVersion = readDataBaseVersion();
        if (!AppLocal.APP_VERSION.equals(sDBVersion)) {

            // Create or upgrade database
            String sScript = sDBVersion == null
                    ? m_dlSystem.getInitScript() + "-create.sql"
                    : m_dlSystem.getInitScript() + "-upgrade-" + sDBVersion + ".sql";

            if (JRootApp.class.getResource(sScript) == null) {
                // Upgrade script does not exist.
                JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("message.noupdatescript")));
                session.close();
                return "false";
            } else {
                // Create or upgrade script exists.
                if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString(sDBVersion == null ? "message.createdatabase" : "message.updatedatabase"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    try {
                        BatchSentence bsentence = new BatchSentenceResource(session, sScript);
                        bsentence.putParameter("APP_ID", Matcher.quoteReplacement(AppLocal.APP_ID));
                        //bsentence.putParameter("APP_NAME", Matcher.quoteReplacement("rag"));
                        bsentence.putParameter("APP_NAME", Matcher.quoteReplacement(AppLocal.APP_NAME));
                        bsentence.putParameter("APP_VERSION", Matcher.quoteReplacement(AppLocal.APP_VERSION));

                        java.util.List l = bsentence.list();
                        if (l.size() > 0) {
                            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("Database.ScriptWarning"), l.toArray(new Throwable[l.size()])));
                        }
                    } catch (BasicException e) {
                        JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("Database.ScriptError"), e));
                        session.close();
                        return "false";
                    }
                } else {
                    session.close();
                    return "false";
                }
            }
        }

        // Cargamos las propiedades de base de datos
        m_propsdb = m_dlSystem.getResourceAsProperties(m_props.getHost() + "/properties");

        // creamos la caja activa si esta no existe
        //   try {
        String sActiveCashIndex = m_propsdb.getProperty("activecash");
        if (sActiveCashIndex == null) {
            sActiveCashIndex = UUID.randomUUID().toString();
            setActiveCash(UUID.randomUUID().toString(), -1, new Date(), null);
        }

        // Leo la localizacion de la caja (Almacen).
        m_sInventoryLocation = m_propsdb.getProperty("location");
        if (m_sInventoryLocation == null) {
            m_sInventoryLocation = "0";
            m_propsdb.setProperty("location", m_sInventoryLocation);
            m_dlSystem.setResourceAsProperties(m_props.getHost() + "/properties", m_propsdb);
        }

        // Inicializo la impresora...
        m_TP = new DeviceTicket(m_props);
        double value = 0.0;//reader variance
        /*  rcard= new CardReader("COM5",value);
        try {
        rcard.ConfigurePort();
        } catch (Exception ex) {
        ex.printStackTrace();
        }*/
        // Inicializamos
        m_TTP = new TicketParser(getDeviceTicket(), m_dlSystem);
        //printerStart();

        // Inicializamos la bascula
        m_Scale = new DeviceScale(m_props);

        // Inicializamos la scanpal
        m_Scanner = DeviceScannerFactory.createInstance(m_props);

        // Leemos los recursos basicos
        BufferedImage imgicon = m_dlSystem.getResourceAsImage("Window.Logo");
        m_jLblTitle.setIcon(imgicon == null ? null : new ImageIcon(imgicon));
        m_jLblTitle.setText(m_dlSystem.getResourceAsText("Window.Title"));

        String sWareHouse = "";
        

        // Show Hostname, Warehouse and URL in taskbar
        String url;
        try {
            url = session.getURL();
        } catch (SQLException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("Database.ScriptError"), e));
            url = "";
        }
        m_jHost.setText("<html>" + m_props.getHost() + " - " + sWareHouse + "<br>" + url);

        try {
            ILookupUtility lu = LookupUtilityImpl.getInstance(this);
            lu.refresh();
            LookupUtilityFactory.init(lu);
        } catch (BasicException e) {
            e.printStackTrace();
            new MessageInf(e).show(this);
        }
        jButton1.setText("Company List");

        //praveen: added to load openfinancialyear
        
        showLogin1();
        return "true";
    }

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public String initApp(
            AppProperties props) {
        jButton1.setText("Create Company");
        flag = false;

        m_props
                = props;

        // support for different component orientation languages.
        applyComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));

        try {
            String url = m_props.getProperty("db.URL");
            session
                    = AppViewConnection.createSession(m_props, url, true);
        } catch (Exception e1) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, e1.getMessage(), e1));
            return "false";
        }

        showLogin();

        return "true";
    }

    public void insertIntoPanel3(AppUser user, String name) {
        if (ulpage != null) {
            jPanel3.remove(ulpage);
            jPanel3.revalidate();
            jPanel3.repaint();
            m_jPanelContainer.remove(ulpage);
            m_principalapp
                    = null;
        }

        m_principalapp = new JPrincipalApp(this, user);
        // The user status notificator
        jPanel3.add(m_principalapp.getNotificator());
        jPanel3.revalidate();
        // The main panel
        m_jPanelContainer.add(m_principalapp, "_" + m_principalapp.getUser().getId());
        showView("_" + m_principalapp.getUser().getId(), m_jPanelContainer);
        m_principalapp.activate();
    }

    private String readDataBaseVersion() {
        try {
            return m_dlSystem.findVersion();
        } catch (BasicException ed) {
            try {
                return m_dlSystem.findLibreposVersion();
            } catch (BasicException ed2) {
                return null;
            }

        }
    }

    public void tryToClose() {

        if (closeAppView()) {

            // success. continue with the shut down
            // apago el visor
            if (flag == true) {
                m_TP.getDeviceDisplay().clearVisor();
            }
            // me desconecto de la base de datos.
            session.close();
            if (cr == null) {
                cr = new CardReader();
            }
            if (cr.getSerialConnection() != null) {
                cr.closeconnection();
            }

            // Download Root form
            SwingUtilities.getWindowAncestor(this).dispose();
        }

    }

    // Interfaz de aplicacion
    public DeviceTicket getDeviceTicket() {
        return m_TP;
    }

    public CardReader1 getReader() {
        return rcard;
    }

    public DeviceScale getDeviceScale() {
        return m_Scale;
    }

    public DeviceScanner getDeviceScanner() {
        return m_Scanner;
    }

    public Session getSession() {
        return session;
    }

    public String getInventoryLocation() {
        return m_sInventoryLocation;
    }

    public String getActiveCashIndex() {
        return m_sActiveCashIndex;
    }

    public int getActiveCashSequence() {
        return m_iActiveCashSequence;
    }

    public Date getActiveCashDateStart() {
        return m_dActiveCashDateStart;
    }

    public Date getActiveCashDateEnd() {
        return m_dActiveCashDateEnd;
    }

    public void setActiveCash(String sIndex, int iSeq, Date dStart, Date dEnd) {
        m_sActiveCashIndex = sIndex;
        m_iActiveCashSequence
                = iSeq;
        m_dActiveCashDateStart
                = dStart;
        m_dActiveCashDateEnd
                = dEnd;

        m_propsdb.setProperty("activecash", m_sActiveCashIndex);
        m_dlSystem.setResourceAsProperties(m_props.getHost() + "/properties", m_propsdb);
    }

    public AppProperties getProperties() {
        return m_props;
    }

    public Object getBean(
            String beanfactory) throws BeanFactoryException {

        // For backwards compatibility
        beanfactory = mapNewClass(beanfactory);

        BeanFactory bf = m_aBeanFactories.get(beanfactory);
        if (bf == null) {

            // testing sripts
            if (beanfactory.startsWith("/")) {
                bf = new BeanFactoryScript(beanfactory);
            } else {
                // Class BeanFactory
                try {
                    Class bfclass = Class.forName(beanfactory);

                    if (BeanFactory.class.isAssignableFrom(bfclass)) {
                        bf = (BeanFactory) bfclass.newInstance();
                    } else {
                        // the old construction for beans...

                        Constructor constMyView = bfclass.getConstructor(new Class[]{AppView.class});

                        Object bean = constMyView.newInstance(new Object[]{this});

                        bf
                                = new BeanFactoryObj(bean);
                    }

                } catch (Exception e) {
                    // ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
                    throw new BeanFactoryException(e);
                }

            }

            // cache the factory
            m_aBeanFactories.put(beanfactory, bf);

            // Initialize if it is a BeanFactoryApp
            if (bf instanceof BeanFactoryApp) {
                ((BeanFactoryApp) bf).init(this);
            }

        }
        return bf.getBean();
    }

    private static String mapNewClass(String classname) {
        String newclass = m_oldclasses.get(classname);
        return newclass == null
                ? classname
                : newclass;
    }

    private static void initOldClasses() {
        m_oldclasses = new HashMap<String, String>();

        // update bean names from 2.00 to 2.20    
//        m_oldclasses.put("com.openbravo.pos.reports.JReportCustomers", "/com/openbravo/reports/customers.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportCustomersB", "/com/openbravo/reports/customersb.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportClosedPos", "/com/openbravo/reports/closedpos.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportClosedProducts", "/com/openbravo/reports/closedproducts.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JChartSales", "/com/openbravo/reports/chartsales.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportInventory", "/com/openbravo/reports/inventory.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportInventory2", "/com/openbravo/reports/inventoryb.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportInventoryBroken", "/com/openbravo/reports/inventorybroken.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportInventoryDiff", "/com/openbravo/reports/inventorydiff.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportPeople", "/com/openbravo/reports/people.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportTaxes", "/com/openbravo/reports/taxes.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportUserSales", "/com/openbravo/reports/usersales.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportProducts", "/com/openbravo/reports/products.bs");
//        m_oldclasses.put("com.openbravo.pos.reports.JReportCatalog", "/com/openbravo/reports/productscatalog.bs");

        // update bean names from 2.10 to 2.20
       // m_oldclasses.put("com.openbravo.pos.panels.JPanelTax", "com.openbravo.pos.inventory.TaxPanel");

    }

    public void waitCursorBegin() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public void waitCursorEnd() {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public AppUserView getAppUserView() {
        return m_principalapp;
    }

    private void printerStart() {

        String sresource = m_dlSystem.getResourceAsXML("Printer.Start");
        if (sresource == null) {
            m_TP.getDeviceDisplay().writeVisor(AppLocal.APP_NAME, AppLocal.APP_VERSION);
        } else {
            try {
                m_TTP.printTicket(sresource);
            } catch (TicketPrinterException eTP) {
                m_TP.getDeviceDisplay().writeVisor(AppLocal.APP_NAME, AppLocal.APP_VERSION);
            }

        }
    }

    private void listPeople() {

        try {

            jScrollPane1.getViewport().setView(null);

            JFlowPanel jPeople = new JFlowPanel();
            jPeople.applyComponentOrientation(getComponentOrientation());

            java.util.List people = m_dlSystem.listPeopleVisible();

            for (int i = 0; i < people.size(); i++) {

                AppUser user = (AppUser) people.get(i);

                JButton btn = new JButton(new AppUserAction(user));
                btn.applyComponentOrientation(getComponentOrientation());
                btn.setFocusPainted(false);
                btn.setFocusable(false);
                btn.setRequestFocusEnabled(false);
                btn.setHorizontalAlignment(SwingConstants.LEADING);
                btn.setMaximumSize(new Dimension(150, 50));
                btn.setPreferredSize(new Dimension(150, 50));
                btn.setMinimumSize(new Dimension(150, 50));

                jPeople.add(btn);
            }

            jScrollPane1.getViewport().setView(jPeople);

        } catch (BasicException ee) {
            ee.printStackTrace();
        }

    }

   
    public List<Object[]> geturl() throws BasicException {
        List<Object[]> obj = (List<Object[]>) new PreparedSentence(session, "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE,S.API_KEY,S.SENDERID,S.URLREF FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE AND S.URLREF='SEND_URL'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING})).list();

        return obj;
    }

    public List<Object[]> getmsg() {
        List<Object[]> obj = null;
        try {
            obj = new PreparedSentence(session, "SELECT A.ID,A.MESSAGE,A.SENDTO FROM activemsgtable A ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).list();

        } catch (BasicException ex) {
            Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public void updateToSentMsgTable(String details, Object[] obj) {
        try {
            new PreparedSentence(session, "INSERT INTO sentmsgtable(ID, MESSAGE, SENTTO, SENTDETAILS) VALUES (?,?,?,?) ",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})
            ).exec(new Object[]{obj[0], obj[1], obj[2], details});
        } catch (BasicException ex) {
            Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletefromactivemsg(String msgid) {
        try {
            new StaticSentence(session, "DELETE FROM activemsgtable WHERE ID = ?", SerializerWriteString.INSTANCE).exec(msgid);
        } catch (BasicException ex) {
            Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // GET FLAG FROM ACTIVE MESS TABLE
    public int getFlag(String ID) {
        Object obj = null;
        int Flag = 0;
        try {
            obj = new PreparedSentence(session, "SELECT A.FLAG FROM activemsgtable a WHERE A.ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(ID);

        } catch (BasicException ex) {
            Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        Flag = Integer.parseInt(obj.toString());
        return Flag;
    }

    // UPDATE FLAG TO 1 AFTER MESSEGE IS SEND.... 
    public void updateFlagToActiveMsg(String MesID) {
        try {
            new PreparedSentence(session, "UPDATE ACTIVEMSGTABLE SET FLAG=1 WHERE ID =? ",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING})
            ).exec(new Object[]{MesID});
        } catch (BasicException ex) {
            Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //SMS sending code-ends
    private java.util.List getActiveCompanies() throws BasicException {
        java.util.List<CompanyInfo> list = null;
        int timeRange = 10000;
        /*      Date d = new Date();
        Timestamp ts = new Timestamp(new Date().getTime());
        try {
            
          
            Statement s = session.getConnection().createStatement();
                ResultSet r = s.executeQuery("select now() date");
            while(r.next())
            {
               ts = r.getTimestamp("date");
            }
            
            long dif = ts.getTime() - d.getTime();
           
            if((dif>300000 || dif < -300000))
            {
                JOptionPane.showMessageDialog(null, "System Date is incorrect. Please change system date to  "+ts+"  and then try again.", ts.toString(), JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            
            
              
        
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
         */
        list = new StaticSentence(session, "SELECT ID,NAME,DESCR,DATABASEPATH,CRDATE,URL,ADDRESS,TODAYDATE,FLAG, TIMERANGE FROM company WHERE ACTIVE=TRUE", null, new SerializerReadClass(CompanyInfo.class)).list();
        if (list.size() > 0) {
            timeRange = list.get(0).getTimeRange();
        }

        final int timeDif = timeRange;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(new Runnable() {

            public void run() {

                try {
                    //sms sending
                    // Connection();
                    Statement s = session.getConnection().createStatement();
                    ResultSet r = s.executeQuery("select now() date");
                    Timestamp ts = new Timestamp(new Date().getTime());
                    Date d = new Date();
                    while (r.next()) {
                        ts = r.getTimestamp("date");
                    }

                    long dif = ts.getTime() - d.getTime();

                    if ((dif > timeDif || dif < -timeDif)) {
                        JOptionPane.showMessageDialog(null, "System Date is incorrect. Please change system date to  " + ts + "  and then try again.", ts.toString(), JOptionPane.ERROR_MESSAGE);
                        tryToClose();
                        System.exit(0);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

        return list;
    }

    private void listCompanies() {

        try {

            jScrollPane1.getViewport().setView(null);

            JFlowPanel jCompanies = new JFlowPanel();
            jCompanies.applyComponentOrientation(getComponentOrientation());

            java.util.List company = getActiveCompanies();

            for (int i = 0; i < company.size(); i++) {

                CompanyInfo comp = (CompanyInfo) company.get(i);

                JButton btn = new JButton(new CompanyAction(comp));
                btn.applyComponentOrientation(getComponentOrientation());
                btn.setFocusPainted(false);
                btn.setFocusable(false);
                btn.setRequestFocusEnabled(false);
                btn.setHorizontalAlignment(SwingConstants.LEADING);
                btn.setMaximumSize(new Dimension(150, 50));
                btn.setPreferredSize(new Dimension(150, 50));
                btn.setMinimumSize(new Dimension(150, 50));

                jCompanies.add(btn);
            }

            jScrollPane1.getViewport().setView(jCompanies);

        } catch (BasicException ee) {
            ee.printStackTrace();
        }

    }
    // La accion del selector

    private class AppUserAction extends AbstractAction {

        private AppUser m_actionuser;

        public AppUserAction(AppUser user) {
            m_actionuser = user;
            putValue(Action.SMALL_ICON, m_actionuser.getIcon());
            putValue(Action.NAME, m_actionuser.getName());
        }

        public AppUser getUser() {
            return m_actionuser;
        }

        public void actionPerformed(ActionEvent evt) {

            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {

                        if (cardAccessOnlyFlag) {
                            JOptionPane.showMessageDialog(null, "Please use your cad to Access", "Access Not Allowed", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            boolean appstatus = true;
                            
                            Object[] obj = (Object[]) new StaticSentence(session, "SELECT ACTIVE FROM APPLICATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(AppLocal.APP_ID);
                            if (obj != null && obj[0] != null) {
                                appstatus = Boolean.parseBoolean(String.valueOf(obj[0]));
                            }
                            
                            if (appstatus) {
                                if (m_actionuser.authenticate()) {
                                    openAppView(m_actionuser);
                                } else {
                                    String sPassword = JPasswordDialog.showEditPassword(JRootApp.this,
                                            AppLocal.getIntString("Label.Password"),
                                            m_actionuser.getName(),
                                            m_actionuser.getIcon());
                                    if (sPassword != null) {
                                        if (m_actionuser.authenticate(sPassword)) {
                                           
                                            openAppView(m_actionuser);
                                        } else {
                                            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.BadPassword"));
                                            msg.show(JRootApp.this);
                                        }
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "The server is in the process of shutdown", "Cannot Login", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                        w.hideDialog();
                    } catch (Exception ex) {
                        w.hideDialog();
                        Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                        msg.show(null);
                    }
                }
            });
            try {
                t.start();
                if (!w.isShowing()) {
                    w.showDialog("Processing Request. Please wait..");
                }

            } catch (Exception ex) {
                w.hideDialog();
                Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                msg.show(null);
            }

        }
    }

    private class CompanyAction extends AbstractAction {

        private CompanyInfo m_actionCompany;

        public CompanyAction(CompanyInfo user) {
            m_actionCompany = user;
            //   putValue(Action.SMALL_ICON, m_actionuser.getIcon());
            putValue(Action.NAME, m_actionCompany.getName());
        }

        public CompanyInfo getUser() {
            return m_actionCompany;
        }

        public void actionPerformed(ActionEvent evt) {

            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {

                        session.close();

                        initClubPlus(m_actionCompany);

                        w.hideDialog();
                    } catch (Exception ex) {
                        w.hideDialog();
                        Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                        msg.show(null);
                    }
                }
            });
            try {
                t.start();
                if (!w.isShowing()) {
                    w.showDialog("Processing Request. Please wait..");
                }

            } catch (Exception ex) {
                w.hideDialog();
                Logger.getLogger(JRootApp.class.getName()).log(Level.SEVERE, null, ex);
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                msg.show(null);
            }

        }
    }

    public void showView(String view, JPanel m_jPanelContainer) {
        CardLayout cl = (CardLayout) (m_jPanelContainer.getLayout());
        cl.show(m_jPanelContainer, view);
    }

    public void openAppView(AppUser user) {

        try {

            boolean flag1 = true;
            Date d = new Date();
            Date newdate
                    = null;
            Date ocashtime
                    = null;
            Date osaletime
                    = null;
            user.fillPermissions(m_dlSystem);
            Object[] log = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "SELECT LOGINTIME,OPENCASHTIME,OPENSALE FROM PEOPLE WHERE  ID= ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP})).find(user.getId());
            if (log != null) {
                if (log[0] != null) {
                    newdate = (Date) (log[0]);
                }
                if (log[1] != null) {
                    ocashtime = (Date) (log[1]);
                }
                if (log[2] != null) {
                    osaletime = (Date) (log[2]);
                }
            } else {
                osaletime = new Date();
            }

            if (newdate == null) {
                user.setLastLoginTime(d);

                if (osaletime == null) {
                    if (user.hasPermission("sales")) {
                        new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "UPDATE PEOPLE SET CLOSESALE=? WHERE ROLE=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{null, user.getRole()});

                    }

                    user.setOpenSaleTime(d);
                }
                if (flag1 == true) {
                    //JIntroPageRest intropage=new JIntroPageRest(this);

//                    int port = 0;
//                    sSocketActive = true;
//
//                    ListenerSocket l
//                            = new ListenerSocket(user.getName());
//                    Thread t
//                            = new Thread(l);
//                    t.start();
//
//                    t.sleep(2000);
//
//                    while (port == 0) {
//                        port = l.getPort();
//                    }
//
//                    InetAddress lhost = InetAddress.getLocalHost();
//                    user.setIpAdddr(lhost.getHostAddress() + " : " + port);
                    m_dlSystem.updateUser(user);

                    if (closeAppView()) {
                        m_principalapp = new JPrincipalApp(this, user);
                        // The user status notificator
                        jPanel3.add(m_principalapp.getNotificator());
                        jPanel3.revalidate();
                        // The main panel
                        m_jPanelContainer.add(m_principalapp, "_" + m_principalapp.getUser().getId());
                        showView("_" + m_principalapp.getUser().getId(), m_jPanelContainer);
                        m_principalapp.activate();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot login", JOptionPane.OK_OPTION);

                }

            } else {
                JOptionPane.showMessageDialog(this, "The user is already logged in", "Cannot Login", JOptionPane.OK_OPTION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            new MessageInf(e).show(this);
            return;

        }

    }

    public void closeMemMainPage() {
        if (ulpage != null) {
            jPanel3.remove(ulpage.getComponent());
            jPanel3.revalidate();
            jPanel3.repaint();

            // remove the card
            m_jPanelContainer.remove(ulpage);
            m_principalapp
                    = null;

            showLogin1();

        }

    }

    public boolean closeAppView() {

        if (m_principalapp == null) {
            return true;
        } else if (!m_principalapp.deactivate()) {
            return false;
        } else {
            // the status label
            jPanel3.remove(m_principalapp.getNotificator());
            jPanel3.revalidate();
            jPanel3.repaint();

            // remove the card
            m_jPanelContainer.remove(m_principalapp);
            m_principalapp
                    = null;

            showLogin1();

            return true;
        }

    }

    private void showLogin() {

        listCompanies();
        showView("login", m_jPanelContainer);
        inputtext = new StringBuffer();
        m_txtKeys.setText(null);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                m_txtKeys.requestFocus();
            }
        });
    }

//praveen
    private void showLogin1() {

        listPeople();      
        showView("login", m_jPanelContainer);

        inputtext = new StringBuffer();
        m_txtKeys.setText(null);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                m_txtKeys.requestFocus();
            }
        });
    }

    private void processKey(char c) {

        if (c == '\n') {

            AppUser user = null;
            String receptionString = inputtext.toString();
            try {
                // user = m_dlSystem.findPeopleByCard(inputtext.toString());

                if (cardAccessOnlyFlag) {
                    if (LookupUtilityImpl.getInstance(null).getAppView().getAppUserView() == null) {

                        waiterinfo = (WaiterInfo) new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "SELECT W.ID, W.NAME,W.COUNTER,P.NAME FROM WAITER W,PEOPLE P WHERE P.ROLE=W.COUNTER AND W.CARDNO = ? AND P.VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(receptionString);
                        if (waiterinfo != null) {
                            cardswiped(waiterinfo);
                        }

                    }
                }
            } catch (BasicException e) {
                e.printStackTrace();
            }

//            if (user == null) {
//                // user not found
//                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.nocard"));
//                msg.show(this);
//            } else {
//                openAppView(user);
//            }
            inputtext = new StringBuffer();
        } else {
            inputtext.append(c);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jPanelTitle = new javax.swing.JPanel();
        m_jLblTitle = new javax.swing.JLabel();
        poweredby = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        m_jPanelContainer = new javax.swing.JPanel();
        m_jPanelLogin = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        m_jLogonName = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        m_jClose = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        m_txtKeys = new javax.swing.JTextField();
        m_jPanelDown = new javax.swing.JPanel();
        panelTask = new javax.swing.JPanel();
        m_jHost = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1024, 768));
        setLayout(new java.awt.BorderLayout());

        m_jPanelTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
        m_jPanelTitle.setLayout(new java.awt.BorderLayout());

        m_jLblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_jLblTitle.setText("Window.Title");
        m_jPanelTitle.add(m_jLblTitle, java.awt.BorderLayout.CENTER);

        poweredby.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N
        poweredby.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        m_jPanelTitle.add(poweredby, java.awt.BorderLayout.LINE_END);

        jLabel2.setPreferredSize(new java.awt.Dimension(142, 34));
        m_jPanelTitle.add(jLabel2, java.awt.BorderLayout.LINE_START);

        add(m_jPanelTitle, java.awt.BorderLayout.NORTH);

        m_jPanelContainer.setLayout(new java.awt.CardLayout());

        m_jPanelLogin.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/logo.png"))); // NOI18N
        jLabel1.setText("<html><center>Garuda Club Plus is a Club Management System with integrated POS<br>" +
            "Copyright \u00A9 2007-2016 Garuda Secure Technologies Pvt.Ltd.<br>" +
            "<br>" +
            "<br>" +
            "See the License for more details.<br>" +
            "<br>" +
            "You should have received a copy of the License along with this program; if not, write to the Garuda Secure Technologies Pvt.Ltd, Bangalore, India ." +
            "<br>" +
            "</center>");
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 1024));
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel1);

        m_jPanelLogin.add(jPanel4, java.awt.BorderLayout.CENTER);

        m_jLogonName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        m_jLogonName.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(510, 118));
        m_jLogonName.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        m_jClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/exit.png"))); // NOI18N
        m_jClose.setText(AppLocal.getIntString("Button.Close")); // NOI18N
        m_jClose.setFocusPainted(false);
        m_jClose.setFocusable(false);
        m_jClose.setPreferredSize(new java.awt.Dimension(115, 35));
        m_jClose.setRequestFocusEnabled(false);
        m_jClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCloseActionPerformed(evt);
            }
        });
        jPanel8.add(m_jClose);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1);

        jPanel2.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(null);

        m_txtKeys.setPreferredSize(new java.awt.Dimension(0, 0));
        m_txtKeys.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_txtKeysKeyTyped(evt);
            }
        });
        jPanel1.add(m_txtKeys);
        m_txtKeys.setBounds(0, 0, 0, 0);

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        m_jLogonName.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel5.add(m_jLogonName);

        m_jPanelLogin.add(jPanel5, java.awt.BorderLayout.SOUTH);

        m_jPanelContainer.add(m_jPanelLogin, "login");

        add(m_jPanelContainer, java.awt.BorderLayout.CENTER);

        m_jPanelDown.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
        m_jPanelDown.setLayout(new java.awt.BorderLayout());

        m_jHost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/display.png"))); // NOI18N
        m_jHost.setText("*Hostname");
        panelTask.add(m_jHost);

        m_jPanelDown.add(panelTask, java.awt.BorderLayout.LINE_START);
        m_jPanelDown.add(jPanel3, java.awt.BorderLayout.LINE_END);

        add(m_jPanelDown, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCloseActionPerformed

        tryToClose();

    }//GEN-LAST:event_m_jCloseActionPerformed

    private void m_txtKeysKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_txtKeysKeyTyped

        m_txtKeys.setText("0");

        processKey(evt.getKeyChar());

    }//GEN-LAST:event_m_txtKeysKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jButton1.getText().equals("Create Company")) {
            CompanyCreator compcre = CompanyCreator.getDialog(this, session);
            compcre.ShowDialog(m_props);
        } else if (jButton1.getText().equals("Company List")) {
            session.close();
            initApp(m_props);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton m_jClose;
    private javax.swing.JLabel m_jHost;
    private javax.swing.JLabel m_jLblTitle;
    private javax.swing.JPanel m_jLogonName;
    private javax.swing.JPanel m_jPanelContainer;
    private javax.swing.JPanel m_jPanelDown;
    private javax.swing.JPanel m_jPanelLogin;
    private javax.swing.JPanel m_jPanelTitle;
    private javax.swing.JTextField m_txtKeys;
    private javax.swing.JPanel panelTask;
    private javax.swing.JLabel poweredby;
    // End of variables declaration//GEN-END:variables
}
