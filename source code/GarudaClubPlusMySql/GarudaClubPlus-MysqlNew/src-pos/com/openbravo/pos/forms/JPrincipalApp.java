package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.openbravo.beans.RoundedBorder;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.loader.Datas;
//import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.Networking.MsgDialog;
import com.openbravo.pos.Networking.SocketInfo;
//import com.openbravo.pos.Networking.wait;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.Hashcypher;

//import com.l2fprod.common.swing.JTaskPane;
//import com.l2fprod.common.swing.JTaskPaneGroup;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author adrianromero
 */
public class JPrincipalApp extends javax.swing.JPanel implements AppUserView {

    private JRootApp m_appview;
    private AppUser m_appuser;
    public static JRootApp m_approot;
    private DataLogicSystem m_dlSystem;
    private JLabel m_principalnotificator;
    private JPanelView m_jLastView;
    private Action m_actionfirst;
    private Map<String, JPanelView> m_aPreparedViews; // Prepared views   
    private Map<String, JPanelView> m_aCreatedViews;
    private Icon menu_open;
    private Icon menu_close;
    private CardReader cr;

    public JPrincipalApp() {
    }

    /**
     * Creates new form JPrincipalApp
     */
    public JPrincipalApp(JRootApp appview, AppUser appuser) {

        m_appview = appview;
        m_appuser = appuser;
        m_approot = appview;

        m_dlSystem = (DataLogicSystem) m_appview.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");

        // Cargamos los permisos del usuario
        m_appuser.fillPermissions(m_dlSystem);

        m_actionfirst = null;
        m_jLastView = null;
        m_aPreparedViews = new HashMap<String, JPanelView>();
        m_aCreatedViews = new HashMap<String, JPanelView>();

        initComponents();

        jPanel2.add(Box.createVerticalStrut(50), 0);

        applyComponentOrientation(appview.getComponentOrientation());

        m_principalnotificator = new JLabel();
        m_principalnotificator.applyComponentOrientation(getComponentOrientation());
        m_principalnotificator.setText(m_appuser.getName());
        m_principalnotificator.setIcon(m_appuser.getIcon());
//        m_principalnotificator.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("TextField.shadow")), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5)));        

        if (jButton1.getComponentOrientation().isLeftToRight()) {
            menu_open = new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/menu-right.png"));
            menu_close = new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/menu-left.png"));
        } else {
            menu_open = new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/menu-left.png"));
            menu_close = new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/menu-right.png"));
        }
        assignMenuButtonIcon();

        // m_jPanelTitle.setUI(new GradientUI());
        m_jPanelTitle.setBorder(RoundedBorder.createGradientBorder());
        m_jPanelTitle.setVisible(false);

        // Anado el panel nulo
        m_jPanelContainer.add(new JPanel(), "<NULL>");
        showView("<NULL>");

        try {

            ScriptMenu menu = new ScriptMenu();

            ScriptEngine eng = ScriptFactory.getScriptEngine(ScriptFactory.BEANSHELL);
            eng.put("menu", menu);
            eng.eval(m_dlSystem.getResourceAsText("Menu.Root"));

            m_jPanelLeft.setViewportView(menu.getTaskPane());
        } catch (ScriptException e) {
            e.printStackTrace();
            // Error Message 
        }
    }

    private void assignMenuButtonIcon() {
        jButton1.setIcon(m_jPanelLeft.isVisible()
                ? menu_close
                : menu_open);
    }

    public class ScriptMenu {
//        private JTaskPane taskPane = new JTaskPane();

        private JXTaskPaneContainer taskPane;

        private ScriptMenu() {
            taskPane = new JXTaskPaneContainer();
            taskPane.applyComponentOrientation(getComponentOrientation());
        }

        public ScriptGroup addGroup(String key) {

            ScriptGroup group = new ScriptGroup(key);
            taskPane.add(group.getTaskGroup());
            return group;
        }

//        public JTaskPane getTaskPane() {            
        public JXTaskPaneContainer getTaskPane() {
            return taskPane;
        }
    }

    public class ScriptGroup {
//        private JTaskPaneGroup taskGroup;

        private JXTaskPane taskGroup;

        private ScriptGroup(String key) {
//            taskGroup = new JTaskPaneGroup();
            taskGroup = new JXTaskPane();
            taskGroup.applyComponentOrientation(getComponentOrientation());
            taskGroup.setFocusable(false);
            taskGroup.setRequestFocusEnabled(false);
            taskGroup.setTitle(AppLocal.getIntString(key));
            taskGroup.setVisible(false); // Only groups with sons are visible.
        }

        public void addPanel(String icon, String key, String classname) {
            addAction(new MenuPanelAction(m_appview, icon, key, classname));
        }

        public void addExecution(String icon, String key, String classname) {
            addAction(new MenuExecAction(m_appview, icon, key, classname));
        }

        public ScriptSubmenu addSubmenu(String icon, String key, String classname) {

            ScriptSubmenu submenu = new ScriptSubmenu(key);
            //   if(key.equals("Salesreport")){

            m_aPreparedViews.put(classname, new JPanelMenu(submenu.getMenuDefinition()));
            addAction(new MenuPanelAction(m_appview, icon, key, classname));

            //  }
            return submenu;

        }

        public void addChangePasswordAction() {
            addAction(new ChangePasswordAction("/com/openbravo/images/yast_security.png", "Menu.ChangePassword"));
        }

        public void addExitAction() {
            addAction(new ExitAction("/com/openbravo/images/gohome.png", "Menu.Exit"));
        }

        private void addAction(Action act) {

            if (m_appuser.hasPermission((String) act.getValue(AppUserView.ACTION_TASKNAME))) {
                // add the action
                Component c = taskGroup.add(act);
                c.applyComponentOrientation(getComponentOrientation());
                c.setFocusable(false);
                //c.setRequestFocusEnabled(false);   

                taskGroup.setVisible(true);

                if (m_actionfirst == null) {
                    m_actionfirst = act;
                }
            }
        }

//        public JTaskPaneGroup getTaskGroup() {
        public JXTaskPane getTaskGroup() {
            return taskGroup;
        }
    }

    public class ScriptSubmenu {

        private MenuDefinition menudef;

        private ScriptSubmenu(String key) {
            menudef = new MenuDefinition(key);
        }

        public void addTitle(String key) {
            menudef.addMenuTitle(key);
        }

        public void addPanel(String icon, String key, String classname) {
            menudef.addMenuItem(new MenuPanelAction(m_appview, icon, key, classname));
        }

        public void addExecution(String icon, String key, String classname) {
            menudef.addMenuItem(new MenuExecAction(m_appview, icon, key, classname));
        }

        public ScriptSubmenu addSubmenu(String icon, String key, String classname) {
            ScriptSubmenu submenu = new ScriptSubmenu(key);
            m_aPreparedViews.put(classname, new JPanelMenu(submenu.getMenuDefinition()));
            menudef.addMenuItem(new MenuPanelAction(m_appview, icon, key, classname));
            return submenu;
        }

        public void addChangePasswordAction() {
            menudef.addMenuItem(new ChangePasswordAction("/com/openbravo/images/yast_security.png", "Menu.ChangePassword"));
        }

        public void addExitAction() {
            menudef.addMenuItem(new ExitAction("/com/openbravo/images/gohome.png", "Menu.Exit"));
        }

        public MenuDefinition getMenuDefinition() {
            return menudef;
        }
    }

    private void setMenuVisible(boolean value) {

        m_jPanelLeft.setVisible(value);
        assignMenuButtonIcon();
        revalidate();
    }

    public JComponent getNotificator() {
        return m_principalnotificator;
    }

    public void activate() {

        setMenuVisible(getBounds().width > 800);

        // arranco la primera opcion
        if (m_actionfirst != null) {
            m_actionfirst.actionPerformed(null);
            m_actionfirst = null;
        }
    }

    public boolean deactivate() {
        if (m_jLastView == null) {
            return true;
        } else if (m_jLastView.deactivate()) {
            m_jLastView = null;
            showView("<NULL>");
            return true;
        } else {
            return false;
        }

    }

    private class ExitAction extends AbstractAction {

        private waitDialog w;

        public ExitAction(String icon, String keytext) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(AppUserView.ACTION_TASKNAME, keytext);
        }

        private void settoNull() throws BasicException {
            new StaticSentence(m_appview.getSession(),
                     "UPDATE PEOPLE SET LOGINTIME = ?,IPADDR=? WHERE ID = ?",
                     new SerializerWriteBasic(new Datas[]{Datas.NULL, Datas.NULL, Datas.STRING})).exec(new Object[]{null, null, m_appuser.getId()});
        }

        private void exitApp() {
            try {
                String uname = m_appuser.getName();
                if (!uname.toUpperCase().equals("MEMBERS")) {
//                    String ipaddr = m_appuser.getIpAddr();
//                    String[] arr = ipaddr.split(" : ");
//                    JRootApp.sSocketActive = false;
//                    Socket s1 = new Socket(arr[0], Integer.parseInt(arr[1]));
//                    if (s1 != null) {
//                        s1.close();
//                    }
//                    //get the socket
//                    for (SocketInfo sinfo : JRootApp.socketList.values()) {
//                        if (sinfo != null) {
//                            Socket s = sinfo.getSocket();
//                            DataOutputStream out = new DataOutputStream(s.getOutputStream());
//                            DataInputStream in = new DataInputStream(s.getInputStream());
//                            out.writeUTF(uname + " has logged out");
//                            MsgDialog dialog = new MsgDialog(new JFrame(), false, s, in, out, "", "");
//                            dialog = sinfo.getMsgDialog();
//                            if (dialog != null) {
//                                dialog.dispose();
//                            }
//
//                            //settoNull();
//                            JRootApp.socketList.remove(uname);
//
//                            out.close();
//                            s.close();
//                        }
//                    }
                    settoNull();
                }
                // w.hideDialog();
                m_appview.closeAppView();

            } catch (Exception e) {
                w.hideDialog();
                e.printStackTrace();
                try {
                    settoNull();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                m_appview.closeAppView();

            }
        }

        public void actionPerformed(ActionEvent evt) {
            //  w=new waitDialog(new JFrame(), true);
            //  int h=w.getSize().height;
            // int w1=w.getSize().width;
            //  Toolkit toolkit = Toolkit.getDefaultToolkit();
            //	   Dimension scrnsize = toolkit.getScreenSize();
            // w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
            //  w.setBackground(new Color(255,255,255,0));

            // Thread t=new Thread(
            //        new Runnable() {
            //     public void run() {
            exitApp();
            //    }
            //  });
            // t.start();
            // w.showDialog("Please wait.Exiting...");
        }
    }

    // La accion de cambio de password..
    private class ChangePasswordAction extends AbstractAction {

        public ChangePasswordAction(String icon, String keytext) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(AppUserView.ACTION_TASKNAME, keytext);

        }

        public void actionPerformed(ActionEvent evt) {

            String sNewPassword = Hashcypher.changePassword(JPrincipalApp.this, m_appuser.getPassword());
            if (sNewPassword != null) {
                try {

                    m_dlSystem.execChangePassword(new Object[]{sNewPassword, m_appuser.getId()});
                    m_appuser.setPassword(sNewPassword);
                } catch (BasicException e) {
                    JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotchangepassword")));
                }
            }
        }
    }

    private void showView(String sView) {
        CardLayout cl = (CardLayout) (m_jPanelContainer.getLayout());
        cl.show(m_jPanelContainer, sView);
    }

    public AppUser getUser() {
        return m_appuser;
    }

    public void showTask(String sTaskClass) {

        m_appview.waitCursorBegin();

        if (m_appuser.hasPermission(sTaskClass)) {

            JPanelView m_jMyView = (JPanelView) m_aCreatedViews.get(sTaskClass);

            // cierro la antigua
            if (m_jLastView == null || (m_jMyView != m_jLastView && m_jLastView.deactivate())) {

                // Construct the new view
                if (m_jMyView == null) {

                    // Is the view prepared
                    m_jMyView = m_aPreparedViews.get(sTaskClass);
                    if (m_jMyView == null) {
                        // The view is not prepared. Try to get as a Bean...
                        try {
                            m_jMyView = (JPanelView) m_appview.getBean(sTaskClass);
                        } catch (BeanFactoryException e) {
                            m_jMyView = new JPanelNull(m_appview, e);
                        }
                    }

                    m_jMyView.getComponent().applyComponentOrientation(getComponentOrientation());
                    m_jPanelContainer.add(m_jMyView.getComponent(), sTaskClass);
                    m_aCreatedViews.put(sTaskClass, m_jMyView);
                }

                // ejecuto la tarea
                try {
                    m_jMyView.activate();
                } catch (BasicException e) {
                    e.printStackTrace();
                    JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notactive"), e));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // se tiene que mostrar el panel                
                m_jLastView = m_jMyView;

                setMenuVisible(getBounds().width > 800);

                showView(sTaskClass);
                // Y ahora que he cerrado la antigua me abro yo            
                String sTitle = m_jMyView.getTitle();
                m_jPanelTitle.setVisible(sTitle != null);
                m_jTitle.setText(sTitle);
            }
        } else {
            // No hay permisos para ejecutar la accion...
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notpermissions")));
        }
        m_appview.waitCursorEnd();
    }

    public void executeTask(String sTaskClass) {

        m_appview.waitCursorBegin();

        if (m_appuser.hasPermission(sTaskClass)) {
            try {
                ProcessAction myProcess = (ProcessAction) m_appview.getBean(sTaskClass);

                // execute the proces
                try {
                    MessageInf m = myProcess.execute();
                    if (m != null) {
                        // si devuelve un mensaje, lo muestro
                        JMessageDialog.showMessage(JPrincipalApp.this, m);
                    }
                } catch (BasicException eb) {
                    // Si se produce un error lo muestro.
                    JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(eb));
                }
            } catch (BeanFactoryException e) {
                JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("Label.LoadError"), e));
            }
        } else {
            // No hay permisos para ejecutar la accion...
            JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notpermissions")));
        }
        m_appview.waitCursorEnd();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        m_jPanelLeft = new javax.swing.JScrollPane();
        m_jPanelRight = new javax.swing.JPanel();
        m_jPanelTitle = new javax.swing.JPanel();
        m_jTitle = new javax.swing.JLabel();
        m_jPanelContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setMargin(new java.awt.Insets(14, 2, 14, 2));
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_END);
        jPanel1.add(m_jPanelLeft, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        m_jPanelRight.setLayout(new java.awt.BorderLayout());

        m_jPanelTitle.setLayout(new java.awt.BorderLayout());

        m_jTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        m_jTitle.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        m_jPanelTitle.add(m_jTitle, java.awt.BorderLayout.NORTH);

        m_jPanelRight.add(m_jPanelTitle, java.awt.BorderLayout.NORTH);

        m_jPanelContainer.setLayout(new java.awt.CardLayout());
        m_jPanelRight.add(m_jPanelContainer, java.awt.BorderLayout.CENTER);

        add(m_jPanelRight, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    setMenuVisible(!m_jPanelLeft.isVisible());

}//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel m_jPanelContainer;
    private javax.swing.JScrollPane m_jPanelLeft;
    private javax.swing.JPanel m_jPanelRight;
    private javax.swing.JPanel m_jPanelTitle;
    private javax.swing.JLabel m_jTitle;
    // End of variables declaration//GEN-END:variables

}
