/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CardsRoomMain.java
 *
 * Created on 04-Feb-2010, 17:26:08
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.catalog.JCatalog1;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSource;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class CardsRoomMain extends javax.swing.JPanel {
    private double caltotal;
    private int status1;
    private int operator;
    private DataLogicFacilities dmang;
    private AppView m_App;
    private JCatalog1 m_cat;
    private DataLogicSales m_dlSales;
    private Component component;
    private ComboBoxValModel cmodel;
    private GameInfoExt selectedGame;
    private CardsRoomMainPanel panel;
    private AddMemberCR addMemberPanel;
    private List<TokenElements> finalList;
    private List<PlayeresData> playersList;
    private GameLog gamelog;
     private int line=0;
    private List<GameLog> glist1;
    private String[] Header=new String[]{"Mem No","Mem Name","Guest Name","Token Amount"};
    private int status;//0-create 1-edit
    private String gameid;
    private PlacesInfo place;
    private CatalogListener catalogListener;
    private TicketParser ttp;
    private DataLogicSystem dlsystem;
   // private int mode;//0-save 1-cancel
    public CardsRoomMain() {
        initComponents();
    }
    public String getTitle() {
        return null;
    }

    public void activate(CardsRoomMainPanel panel,CustomerInfo cinfo,GameLog glog,int status) throws BasicException {
        List<GameInfoExt> glist=dmang.getActiveParentGamesList();
        m_cat.loadCatalog(glist, null);
        cmodel=new ComboBoxValModel(m_dlSales.getPlacesList().list());
        jComboBox1.setModel(cmodel);
        jComboBox1.setSelectedIndex(-1);
        this.panel=panel;
        playersList=new ArrayList<PlayeresData>();
        this.gamelog=glog;
        playersList.addAll(gamelog.getPalyersList());
        jTable1.setModel(getTableModel());
        finalList=new ArrayList<TokenElements>();
        TokenElements ele=new TokenElements(null, null, 0.0, 0);
        finalList.add(ele);
        this.status=status;
        if(status==1){
          m_cat.removeActionListener(catalogListener);
        }
    }
    public GameLog getGameInstance(){
        return gamelog;
    }
    public int getGameStatus(){
        return status;
    }

    public boolean deactivate() {

       /* if(selectedGame!=null && place!=null){
            try {
                Object[] obj = new Object[]{gamelog.getPalyersList().size(), gamelog.getTableID(), gamelog.getPaymentRef(), gamelog.getGameID(),gamelog.getPalyersList().size()* selectedGame.getClubCollection(), gamelog.getID()};
                dmang.updateGameInstance(obj);
                return true;
            } catch (BasicException ex) {
                ex.printStackTrace();
                return false;
            }
        }else{
            if(selectedGame==null && place==null)
                JOptionPane.showMessageDialog(null, "Please Select a Game and a playing table", null, JOptionPane.WARNING_MESSAGE);
            else if(selectedGame==null )
                JOptionPane.showMessageDialog(null, "Please Select a Game ", null, JOptionPane.WARNING_MESSAGE);
            else if(place==null )
                JOptionPane.showMessageDialog(null, "Please Select a  playing table", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }*/
        if(gamelog!=null && gamelog.getID()==null){
            if(JOptionPane.showConfirmDialog(null, "Please start the game to save it else it will be discarded.Do you want to discard it?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
               return true;
            }else
                return false;
        }else
            return true;
    }

    public JComponent getComponent() {
       return this;
    }
    public void setValues(boolean status) throws BasicException{
        //this.game=game;
       jTextField3.setText(gamelog.getGameName());
       jTextField1.setText(gamelog.getNoOfPlayers()+"");
       selectedGame=dmang.getGamesInfoByID(gamelog.getGameID());
       if(selectedGame.isCCperPlayer())
             jTextField7.setText(String.valueOf(gamelog.getNoOfPlayers()*selectedGame.getClubCollection()));
       else
             jTextField7.setText(String.valueOf(selectedGame.getClubCollection()));
       //if(gamelog.getPalyersList().size()>gamelog.get)
       jTextField1.setText(gamelog.getPalyersList().size()+"");

       PlacesInfo p=m_dlSales.getPlaceByID(gamelog.getTableID());
       cmodel.setSelectedItem(p);

       jTextField1.setEditable(false);
       if(selectedGame.getprintref()==null){
        jButton6.setEnabled(false);
       }
       if(gamelog.getPaymentRef()!=null ){
              m_cat.removeActionListener(catalogListener);
             if(gamelog.getPaymentRef()!=null && selectedGame.isCCperPlayer()){
                 jButton3.setEnabled(false);
             }
             if(gamelog.getPaymentRef()!=null){
                  jButton1.setEnabled(false);
             }
       }
       if(status==false){
         m_cat.removeActionListener(catalogListener);
       }
       //m_cat.setEnabled(false);
       // if(selectedGame.getPaymentTime()==0 && status==1){

           //if(gamelog.getPaymentRef()!=null && selectedGame.isCCperPlayer()){
             // jButton4.setEnabled(false);

          // }
       // }

    }
    public void func() throws BasicException{
          playersList=dmang.getPlayersFromGameLogID(gamelog.getID());
          jTextField1.setText(playersList.size()+"");
          if(selectedGame.isCCperPlayer()==true){
             jTextField7.setText((playersList.size()*selectedGame.getClubCollection())+"");
          }else{
              jTextField7.setText((selectedGame.getClubCollection())+"");
          }
    }
    private void loadGameLog() throws BasicException{
        if(gamelog!=null && gamelog.getID()!=null){
            gamelog=dmang.getGameInstanceByID(gamelog.getID());
            playersList=dmang.getPlayersFromGameLogID(gamelog.getID());
            gamelog.getPalyersList().clear();
            gamelog.addPlayers(playersList);
        }
    }
    public void init(AppView app,int status) throws BeanFactoryException {
       jLabel3.setText("Game");
       jTextField3.setEditable(false);
       jLabel1.setText("Table ");
       jLabel2.setText("No Of Players");
       jLabel7.setText("List Of Users");
       jButton4.setText("Add Players");
       //jButton2.setText("Enter");
       jButton1.setText("Pay");
       jButton5.setText("Edit");
       jButton3.setText("Cancel");
       jButton6.setText("Print");
       jButton7.setText("Return");
       jLabel8.setText("Club Collection");
       jTextField1.setEditable(false);
       jTextField7.setEditable(false);
       jButton2.setText("Start Game");
       m_App=app;
       dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
       ttp = new TicketParser(app.getDeviceTicket(), dlsystem);
       m_cat = new JCatalog1(m_dlSales,dmang);
       m_cat.getComponent().setPreferredSize(new Dimension(0, 245));
       catalogListener=new CatalogListener();
       m_cat.addActionListener(catalogListener);
       component=m_cat.getComponent();
       jPanel4.add(component, BorderLayout.CENTER);
       jButton3.setVisible(false);
    }

    private class CatalogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            selectedGame=(GameInfoExt)e.getSource();
            jTextField3.setText(selectedGame.getName());
            if(selectedGame.isCCperPlayer())
             jTextField7.setText(String.valueOf(gamelog.getNoOfPlayers()*selectedGame.getClubCollection()));
            else
             jTextField7.setText(String.valueOf(selectedGame.getClubCollection()));
             jTextField1.setText(gamelog.getPalyersList().size()+"");
             gamelog.setNoOfPlayers(gamelog.getPalyersList().size());
             gamelog.setGameName(selectedGame.getName());
             gamelog.setGameID(selectedGame.getID());
             if(selectedGame.getprintref()==null){
               jButton6.setEnabled(false);
             }
        }
    }

    public Object getBean() {
        return this;
    }
    private AbstractTableModel getTableModel(){
        return new AbstractTableModel() {

            public int getRowCount() {
                return playersList.size();
            }

            public int getColumnCount() {
                return Header.length;
            }

            @Override
            public String getColumnName(int column) {
                return Header[column];
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PlayeresData player=playersList.get(rowIndex);
                switch(columnIndex){
                    case 0 :return player.getMemberName();
                    case 1 :return player.getMemberNo();
                    case 2 :return player.getGuestName();
                    case 3 :return player.getTokenTotalValue();
                    default : return null;
                }
            }
        };
    }

     private void calculateClubCollection(){
       try{
         if(selectedGame!=null){
          int value=Integer.parseInt(jTextField1.getText());
          if(value>=selectedGame.getMin() && value<=selectedGame.getMax()){
           if(selectedGame.isCCperPlayer())
               jTextField7.setText(dmang.ConvertDoubleToString(selectedGame.getClubCollection()* value));
           else
               jTextField7.setText(dmang.ConvertDoubleToString(selectedGame.getClubCollection()));
          }else{
              if(value<selectedGame.getMin()){
                 JOptionPane.showMessageDialog(null, "Specified No of Players is less the minimum allowed to play the game ", "", JOptionPane.WARNING_MESSAGE);
              }else if(value>selectedGame.getMax()){
                 JOptionPane.showMessageDialog(null, "Specified No of Players is greater than the maximum allowed to play the game ", "", JOptionPane.WARNING_MESSAGE);
              }
              jTextField1.setText(null);
              jTextField7.setText(null);
          }
         }else{
            jTextField1.setText(null);
         }
        }catch(Exception e){
            jTextField7.setText(null);
            jTextField1.setText(null);
        }
     }
     private void printTicket(List<PaymentInfo> pinfo,Double amount,String receiptno,String cname,String memno,String gname,int nop,double rate) {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.CardsRoomReceipt");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", dmang.ConvertDoubleToString(amount));
                script.put("cname", cname);
                script.put("cno", memno);
                script.put("gname", gname);
                script.put("nop", nop);
                script.put("rate", rate);
                script.put("receipt", receiptno);
                script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                ttp.printTicket(script.eval(sresource).toString());

            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            }
            catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

        setLayout(null);

        jLabel1.setText("jLabel1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(30, 30, 30)
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 207, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 10, 600, 40);

        jPanel2.setLayout(null);

        jLabel7.setText("jLabel7");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(20, 10, 250, 14);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 30, 450, 220);

        jPanel5.setLayout(null);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);
        jButton2.setBounds(0, 10, 110, 28);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);
        jButton4.setBounds(0, 40, 110, 31);

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(0, 70, 110, 30);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);
        jButton1.setBounds(0, 100, 110, 30);

        jButton6.setText("jButton5");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(0, 130, 110, 29);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);
        jButton3.setBounds(0, 160, 110, 28);

        jButton7.setText("jButton5");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);
        jButton7.setBounds(0, 190, 110, 30);

        jPanel2.add(jPanel5);
        jPanel5.setBounds(480, 10, 115, 241);

        add(jPanel2);
        jPanel2.setBounds(0, 360, 610, 260);

        jPanel4.setLayout(new java.awt.BorderLayout());
        add(jPanel4);
        jPanel4.setBounds(10, 60, 590, 240);

        jLabel3.setText("jLabel3");

        jLabel2.setText("jLabel2");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel8.setText("jLabel8");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .add(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel3);
        jPanel3.setBounds(0, 310, 610, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       try {
           int row=jTable1.getSelectedRow();
           if(row>=0){
             PlayeresData player=playersList.get(row);
             loadGameLog();
            if( gamelog.getID()!=null ){
             addMemberPanel = new AddMemberCR();
             addMemberPanel.init(m_App);
             addMemberPanel.setActivePlayer(player);
             addMemberPanel.activate(this,selectedGame,gamelog,1);
             JDialog frame=new JDialog(new JFrame(),true);
             frame.setTitle("Add Players");
             frame.add(addMemberPanel);
             frame.setSize(650, 650);
             frame.setVisible(true);
             gamelog.getPalyersList().clear();
             gamelog.addPlayers(dmang.getPlayersFromGameLogID(gamelog.getID()));
            }else{
                if(selectedGame==null && place==null)
                    JOptionPane.showMessageDialog(null, "Please Select a Game and a playing table", null, JOptionPane.WARNING_MESSAGE);
                else if(selectedGame==null )
                    JOptionPane.showMessageDialog(null, "Please Select a Game ", null, JOptionPane.WARNING_MESSAGE);
                else if(place==null )
                    JOptionPane.showMessageDialog(null, "Please Select a  playing table", null, JOptionPane.WARNING_MESSAGE);
                 else if(gamelog.getID()==null)
                    JOptionPane.showMessageDialog(null, "Please Start The Game First", null, JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Please start the game before adding players to it", gameid, status1);
            }
           }else{
               JOptionPane.showMessageDialog(null, "Please Select The Player To Be Edited", "Player Not Selected", JOptionPane.WARNING_MESSAGE);
           }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            loadGameLog();
            boolean flag1 =dmang.checkForPendingCancelRequest(gamelog.getID());
          if(gamelog.getID()!=null && !flag1 && gamelog.getPalyersList().size()<=selectedGame.getMax()){
              boolean flag=true;
              if(gamelog.getPaymentRef()!=null){
              Object obj=dmang.getPaymentRef(gamelog.getID());
              if(obj==null)
                  gamelog.setPaymentRef(null);
              else
                  gamelog.setPaymentRef(obj.toString());
              }
              if(selectedGame.isCCperPlayer() && gamelog.getPaymentRef()!=null){
                  flag=false;
              }
            if(flag){
             addMemberPanel = new AddMemberCR();
             addMemberPanel.init(m_App);
             addMemberPanel.activate(this,selectedGame,gamelog,0);
             JDialog frame=new JDialog(new JFrame(),true);
             frame.setTitle("Add Players");
             frame.add(addMemberPanel);
             frame.setSize(650, 650);
             frame.setVisible(true);
             gamelog.getPalyersList().clear();
             gamelog.addPlayers(dmang.getPlayersFromGameLogID(gamelog.getID()));
             gamelog.setNoOfPlayers(gamelog.getPalyersList().size());
             jTextField1.setText(gamelog.getNoOfPlayers()+"");
             if(selectedGame.isCCperPlayer())
               jTextField7.setText(String.valueOf(gamelog.getNoOfPlayers()*selectedGame.getClubCollection()));
             else
               jTextField7.setText(String.valueOf(selectedGame.getClubCollection()));
              playersList=gamelog.getPalyersList();
              jTable1.setModel(getTableModel());
            }
            }else{
                if(selectedGame==null && place==null)
                    JOptionPane.showMessageDialog(null, "Please Select a Game and a playing table", null, JOptionPane.WARNING_MESSAGE);
                else if(selectedGame==null )
                    JOptionPane.showMessageDialog(null, "Please Select a Game ", null, JOptionPane.WARNING_MESSAGE);
                else if(place==null )
                    JOptionPane.showMessageDialog(null, "Please Select a  playing table", null, JOptionPane.WARNING_MESSAGE);
                else if(gamelog.getID()==null)
                    JOptionPane.showMessageDialog(null, "Please Start The Game First", null, JOptionPane.WARNING_MESSAGE);
                else if(flag1)
                    JOptionPane.showMessageDialog(null, "Pending request for cancellation exist", "Sorry Cannot Add Players", JOptionPane.WARNING_MESSAGE);
            }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            loadGameLog();
            boolean flag1 =dmang.checkForPendingCancelRequest(gamelog.getID());
            /*if(gamelog.getPaymentRef()!=null){
              Object obj=dmang.getPaymentRef(gamelog.getID());
              if(obj==null)
                  gamelog.setPaymentRef(null);
              else
                  gamelog.setPaymentRef(obj.toString());
            }*/
        if(selectedGame!=null && jTextField7.getText()!=null && gamelog.getPaymentRef()==null && gamelog.getID()!=null && !flag1 && gamelog.getPalyersList().size()>=selectedGame.getMin()){
           CustomerInfoExt cinfo=new CustomerInfoExt(gamelog.getJackID());
           cinfo.setSearchkey(gamelog.getJackNo());
           cinfo.setName(gamelog.getJackID());
           List<PaymentInfo> pinfo=new ArrayList<PaymentInfo>();
           JPaymentSelect paymentdialog =JPaymentSelectReceipt.getDialog(JOptionPane.getRootFrame());
           paymentdialog.init(m_App);
           boolean flag=paymentdialog.showDialog(Double.parseDouble(jTextField7.getText()), cinfo, m_App.getAppUserView().getUser().getName(),true);

           if(flag==true){
               pinfo=paymentdialog.getSelectedPayments();
               BillInfo ticket=new BillInfo();
               ticket.setID(UUID.randomUUID().toString());
               ticket.setPayments(pinfo);
               ticket.setCustomer(cinfo);
               ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
               ticket.setCreatedDate(new Date());
               ticket.setActiveCash(m_App.getActiveCashIndex());
               ticket.setFloor("Cards Room");
              // ticket.setPlace("Cards Room Collection");
               ticket.setPlace(gamelog.getGameName());
               String rnum=m_dlSales.payaccount(ticket, m_App.getInventoryLocation(),true);
                if(!(rnum==null || rnum.equals("false"))){
                    dmang.updateGamesPayment(new Object[]{rnum,gamelog.getID()});
                    printTicket(pinfo,Double.parseDouble(jTextField7.getText()),rnum,gamelog.getJackName(),gamelog.getJackNo(),gamelog.getGameName(),gamelog.getNoOfPlayers(),gamelog.getClubCollection());
                    gamelog.setPaymentRef(rnum);
                    jButton1.setEnabled(false);
                    int row=jTable1.getSelectedRow();
                   if(row>-1){
                   try{
                   //if(JOptionPane.showConfirmDialog(null, "Do you want to set the game as completed", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    glist1=dmang.getActiveGameLog();
                    GameLog glogobj=glist1.get(row);
                    glogobj=dmang.getGameInstanceByID(glogobj.getID());
                    if(glogobj.getPaymentRef()!=null){
                    Object[] obj=new Object[]{new Date(),glogobj.getID()};
                    dmang.setGameInstanceAsCompleted(obj);
                    //dlfac.deleteGameLog(glogobj.getID());
                   }else{
                   JOptionPane.showMessageDialog(null, "Please create the receipt", null, JOptionPane.WARNING_MESSAGE);
                   }
                  }
                 catch(Exception e){
                  }
                    }
                    m_cat.removeActionListener(catalogListener);
                    if(selectedGame.isCCperPlayer()){
                       jButton3.setEnabled(false);
                    }
                }else{
                  if(rnum.equals("false")){
                    JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                  }else
                    JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.OK_OPTION);
               }
           }
         }else{
                if(flag1)
                    JOptionPane.showMessageDialog(null, "Request for cancellation is pending", null, JOptionPane.ERROR_MESSAGE);
                else if(gamelog.getPalyersList().size()<selectedGame.getMin()){
                    JOptionPane.showMessageDialog(null, "Player count is less than the actual minimum allowed for the game",null,JOptionPane.ERROR_MESSAGE);
                }else if(gamelog.getID()!=null)
                    JOptionPane.showMessageDialog(null, "Please start the game first",null,JOptionPane.ERROR_MESSAGE);
                else 
                    JOptionPane.showMessageDialog(null, "Please select a game");
         }
       
        }catch(Exception e){
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        calculateClubCollection();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    /*    try {
             if(selectedGame==null){
               dmang.deleteTokenLog(gamelog.getPalyersList().get(0).getTokenLogID());
               dmang.deleteGameLog(gamelog.getID());
               panel.restore(1);
             }else{
                JOptionPane.showMessageDialog(null, "Sorry Cannot Cancel the game", null, JOptionPane.WARNING_MESSAGE);
             }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        place=(PlacesInfo)jComboBox1.getSelectedItem();
        if(place!=null){
            gamelog.setTableID(place.getID());
            gamelog.setTableName(place.getName());
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            loadGameLog();
          /*if(selectedGame!=null && selectedGame.getprintref()!=null){
           Map reportparams = new HashMap();
           //reportparams.put("params",new Object[]{new Date(),gamelog.getGameName(),gamelog.getClubCollection(),gamelog.getGameAmount(),gamelog.getJackName(),gamelog.getSeqNo()} );
           reportparams.put("gname", gamelog.getGameName());
           reportparams.put("gameamt", gamelog.getGameAmount());
           reportparams.put("clubcollection", gamelog.getClubCollection());
           reportparams.put("Jack", gamelog.getJackName());
           reportparams.put("SeqNo",gamelog.getSeqNo());
           reportparams.put("date",new Date());
           List list=new ArrayList();
           for(int i=2;i<=16;i++)
               list.add(i);
           DataSourceProvider data1=new DataSourceProvider(list);
           DataSource3 ds=new DataSource3(15,list);
           data1.setDataSource(ds);
           JasperPrint jp= JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/jackPot1.jrxml",reportparams,false,data1,true,null);
           //JasperPrint jp= JasperReportNew.runReport(m_App, selectedGame.getprintref(),reportparams,false,data1,true,null);
         }*/
         if(selectedGame!=null)
          {
               Map reportparams = new HashMap();
           //reportparams.put("params",new Object[]{new Date(),gamelog.getGameName(),gamelog.getClubCollection(),gamelog.getGameAmount(),gamelog.getJackName(),gamelog.getSeqNo()} );
           reportparams.put("gname",gamelog.getGameName());
           reportparams.put("gameamt",gamelog.getGameAmount());
           //reportparams.put("clubcollection",null);
           reportparams.put("Jack", gamelog.getJackName());
           reportparams.put("SeqNo",gamelog.getSeqNo());
           reportparams.put("date",new Date());
           List list=new ArrayList();
           for(int i=2;i<=18;i++)
               list.add(i);
           DataSourceProvider data1=new DataSourceProvider(list);
           DataSource3 ds=new DataSource3(17,list);
           data1.setDataSource(ds);
           JasperPrint jp= JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/jackPot1.jrxml",reportparams,false,data1,true,null);
          }
        }catch(Exception e){
         e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
         if(gamelog.getID()==null){
            if(JOptionPane.showConfirmDialog(null, "Please start the game to save it else it will be discarded.Do you want to discard it?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             panel.restore(1);
            }
         }else{
             panel.restore(1);
         }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try{
          //loadGameLog();
       if(selectedGame!=null && place!=null && gamelog.getID()==null){
          //ID,CRDATE,CRBY,NOP,JACKID,GAMEID,CLUBCOLLECTION,TABLE,GAMESEQ
         String gameInstanceID=UUID.randomUUID().toString();
         List<TokenElements> tokenList=new ArrayList<TokenElements>();
         Date crdate=new Date();
         String crby=m_App.getAppUserView().getUser().getName();
         PlacesInfo pinfo=(PlacesInfo)jComboBox1.getSelectedItem();
         String gameSeq=null;
         if(selectedGame.getprintref()!=null){
          gameSeq=dmang.getGameSeq(selectedGame.getID());
          gamelog.setSeqNo(gameSeq);
         }
         gamelog.setGameID(selectedGame.getID());
         gamelog.setGameName(selectedGame.getName());
         gamelog.setGameAmount(selectedGame.getGameAmount());
         gamelog.setID(gameInstanceID);
         gamelog.setClubCollection(selectedGame.getClubCollection());
         Object[] obj=new Object[]{gameInstanceID,crdate,crby,1,gamelog.getJackID(),selectedGame.getID(),selectedGame.getClubCollection(),pinfo.getID(),gameSeq};
         dmang.insertNewGameInstance(obj);
         String tokenLogID=UUID.randomUUID().toString();
        //ID,MEMID,TOTALVALUE,CRDATE,CREATEDBY,GAMEREF,TYPE_
         obj=new Object[]{tokenLogID,gamelog.getJackID(),gamelog.getGameAmount(),crdate,crby,gamelog.getID(),0,null};
        // obj=new Object[]{tokenLogID,gamelog.getJackID(),0.0,crdate,crby,gamelog.getID(),0,null};
         dmang.insertIntoTokenLog(obj);
         
          for(TokenElements tele:finalList){
         if(tele.getProductID()!=null){
         Object[] obj1=new Object[]{UUID.randomUUID().toString(),tokenLogID,tele.getProductID(),tele.getRate(),tele.getQty(),line};
         dmang.insertIntoTokenLogDetail(obj1);
         String a="out";
         Object[] obj2=new Object[]{UUID.randomUUID().toString(),tele.getProductID(),tele.getRate(),tele.getQty(),a};
         dmang.insertIntoTokenStockDetail(obj2);
         TokenElements t=new TokenElements(tele.getProductID(), tele.getProductName(), tele.getRate(), tele.getQty());
         t.setLine(line);
         //player.getTokenList().add(t);
         tokenList.add(t);
         int cnt=m_dlSales.updateStockVolume3(Double.valueOf(String.valueOf(tele.getQty())), tele.getProductID());
         if(cnt==0){
         JOptionPane.showMessageDialog(null, "No Stock ***"+tele.getProductName()+"***", "No Stock", JOptionPane.ERROR_MESSAGE);
         throw new BasicException();
         }
        }
                        //amount+=tele.getTotalAmount();
      }

   }
      }catch(Exception e){
          e.printStackTrace();
      }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables



}
