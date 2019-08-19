/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JIntroPageRest.java
 *@shiv
 * Created on aug 4, 2012, 11:08:50 AM
 */
package com.openbravo.pos.sales.restaurant;

import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.ticket.TicketInfo;
import java.util.*;
import java.awt.*;
//import java.awt.event.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.openbravo.pos.sales.*;
import com.openbravo.pos.forms.*;
import com.openbravo.data.loader.StaticSentence;
//import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
//import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.data.gui.ComboBoxValModel;
//import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.LocalRes;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
//import com.openbravo.data.loader.SerializerReadInteger;
//import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
//import com.openbravo.data.loader.Session;
//import com.openbravo.data.user.DirtyManager;
//import com.openbravo.pos.mant.FloorsInfo;
import com.openbravo.data.loader.Session;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.SerialConnection;
import com.openbravo.pos.clubmang.FacilityLimitCheck;
import com.openbravo.pos.customers.JCustomerFindernum1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
//import javax.swing.text.TableView;
//import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
 import cos.card.acs.Cosacs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
/**
 *
 * @author swathi
 */
public class JIntroPageRestnum1 extends JTicketsBagnum1 implements CardSwipeNotifier {

    private DataLogicReceipts dlReceipts = null;
    private DataLogicSales dlSales = null;
    private DataLogicCustomers dlCustomers = null;
    private BillLogic dlBill;
    private JTicketsBagRestaurantnum1 m_restaurantmap;
    private TicketInfo m_oTicket;
    private CustomerInfoExt customer;
    private ComboBoxValModel m_table;
    private ComboBoxValModel m_waiter;
    private ComboBoxValModel m_floor;
//    private SentenceList m_tlist;
//    private SentenceList m_wlist;
//    private SentenceList m_flist;
    Object table, wait;
    private ArrayList<TicketInfo> m_ticketList;
    private IntroTableModel m_introtablemodel;
    protected Qticket qTicket;
    private AppView m_App;
    public static boolean dflag = false;
    private FacilityLimitCheck flcheck;
//    private FacilityLimitCheck.FacilityLimitMaster facmas;
//    private String portNumber;
    private CardReader cr;
    //initiator changes - start
    private String initiator;
    private boolean flag = true;
    private Session session;
    public static boolean b = false;
    public boolean p = true;
    public static boolean ct_z = false;
    public static boolean ct_b = true;
    public boolean ct_a = false;
    public static boolean ct_s = true;
   // public static boolean ct_p = false;
    public static boolean ctlk = false;
    public static boolean cat = false;
    public static boolean cat_s = false;
    public static boolean cat_c = false;
    public static boolean cat_p = false;
    public static boolean ct_ent = false;
    public static boolean ctl_b1=false;
    public static boolean ctl_clk=true;
    public static boolean focus = true;
    public static boolean remark = true;

    

    public String getInitiator() {
        return initiator;
    }

    public void loadMemberDetails(String card) throws HeadlessException {
        // TODO add your handling code here:
        String custoid;
        //String card = cr.getData();
        if (card.length() > 0) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?  UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                    jTextField2.setText(obj[1].toString());
                    jTextField1.setText(obj[2].toString());
                    setInitiator(obj[4].toString());
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
    //initiator changes - end
    // private int typeOfUser;
    // private String id;

    public JIntroPageRestnum1(AppView app, TicketsEditor panelTicket) {
        super(app, panelTicket);
        m_App = app;
        // session = AppViewConnection.createSession(m_props, cinfo.getUrl(), false);
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlReceipts = (DataLogicReceipts) app.getBean("com.openbravo.pos.sales.DataLogicReceipts");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");

        this.dlBill = (BillLogic) app.getBean("com.openbravo.pos.sales.BillLogic");
        dlBill.setDataLogicSales(dlSales);
        dlBill.setAppView(app);

        qTicket = (Qticket) m_App.getBean("com.openbravo.pos.sales.Qticket");
        qTicket.setDataLogicSales(dlSales);
        qTicket.setAppView(m_App);
        initComponents();
        
          //m_jUp.addActionListener(new ActionListener(){  //code for Up button
   
    
   // m_jDown.addActionListener(new ActionListener(){  //code for down button
   
    
    
       
    
    jButton3.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
       }});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_P)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               if(!cat_p){
             cat_p = true;
             if(focus){
                 focus = false;
            pending();
                focus = true;
             }
             cat_p = false;
               }
          }  
          return false;}});
    
    jButton8.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
       Authrzn();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_S)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               if(!cat_s){
             cat_s = true;
             Authrzn();
             cat_s = false;
               }
          }  
          return false;}});
    
      jButton4.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
       Add();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_A) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))  
               if(!ct_a){
                   ct_a=true;
              //     ct_ent = true;
              p = false;
              ct_z = true;
              cat = true;
              JIntroPageRestnum1.ctlk = true;
              if(focus){
                     focus = false;
             Add();  
              }
                   ct_a=false;
              //     ct_ent = false;
               }
          }  
          return false;}}); 
    
    jButton1.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
        Find();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_M)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) 
               if(!ct_a){
                   ct_a=true;
                   if(focus){
                     focus = false;
              Find();
                    focus = true;
                   }
                  ct_a=false;
               }
          }  
          return false;}});
    
    jButton9.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
         Load();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_L)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               if(!ct_a){
                   ct_a=true;
                   if(focus){
                     focus = false;
              Load();
              focus = true;
                   }
                   ct_a=false;
               }
               
          }  
          return false;}});
    
        jButton2.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
        Select();}});  
     KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ENTER)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
               if(!ct_ent){
                   ct_ent = true;
              p = false;
              ct_z = true;
              cat = true;
              JIntroPageRestnum1.ctlk = true;
              if(focus){
                     focus = false;
              Select();
              }
              ct_ent = false;
               }
            }
          }  
          return false;}});
     
     jButton7.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
       }});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){  
                 if(!cat_c){
             cat_c = true;
             if(ctl_clk)
                 if(focus){
                     focus = false;
                 cnfrm();
                    focus = true;
                 }
             cat_c = false;
               }
                
            }
          }  
          return false;}});
     
     ///////////
    jButton6.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
       Bill();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_B) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){  
             //   if(ct_b){
                  //  ct_b = false;
                    Billpagenum1.t_error = true;
             b=true;  
            if(ctl_b1==false){
                ctl_b1=true;
                        ctl_clk = false;
                        if(focus){
                            focus = false;
                 Bill();
                        focus = true;
                        }
                        ctl_clk = true;
             ctl_b1=false;
            }
             // }
            }
          }  
          return false;}});
    //////////////////
    ///////////////////SHIV/////////////////////////////////////////////////////////////////////////////////
     //  m_jDown.addActionListener(new ActionListener(){  //code for Up button
 /*     jComboBox3.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
          
     Floor()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_DOWN)  )
               
             Floor(); 
            
          }  
          return false;}});    
   /*  try {
            loadCurrentSharedTickets(m_App);
            m_floor = new ComboBoxValModel(dlSales.getFloorsList().list());


        } catch (BasicException e) {
            e.printStackTrace();
            new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("message.table_waiter_list_failed")).show(this);
            throw new IllegalStateException(e.getMessage(), e);
        }

        jComboBox3.setModel(m_floor);*/
        //jComboBox2.setModel(m_waiter);*/
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
        guestlist.setSelectedIndex(-1);

        jTextField2.setText(null);
        //jButton7.setText("C List");


        m_restaurantmap = new JTicketsBagRestaurantnum1(app, this);

        m_introtablemodel = new IntroTableModel(m_ticketList);

        jTable1.setModel(m_introtablemodel);

        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(140);
        columnModel.getColumn(1).setMaxWidth(160);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(2).setMaxWidth(140);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(4).setMaxWidth(140);



        //jComboBox2.setEnabled(true);
        jComboBox3.setEnabled(true);
        //
        jComboBox1.setEnabled(false);


        jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         jButton4.setToolTipText("CTRL A");
         jButton8.setToolTipText("CTRL S");
         jButton9.setToolTipText("CTRL L");
         jButton1.setToolTipText("Member List(CTRL M)");
         jButton2.setToolTipText("CTRL ENTER");
         jButton6.setToolTipText("CTRL B");
         jButton5.setToolTipText("CTRL R");
         jButton3.setToolTipText("CTRL P");
         jButton7.setToolTipText("CTRL C");
    //removed table model from here
    //tableselectionmodel();
    //   pageobj=this;
        m_jUp.setVisible(false);

    }

    private void checkToCreateSharedTicket(CustomerInfoExt ac_cust, String id, String ac_place, String ac_waiter, String ac_floor, String pfid, Double amount, Double limit, String mid, String fid, Date cdate) throws HeadlessException, BasicException {
        //praveen:added to check facilitylimit
        Object[] obj = flcheck.checkFacilityLimit(m_App, ac_cust.getId(), id);
        Object[] objtotal = null;
        Object[] limittotal = null;
        Double totalDebtAmount = 0.0;
        Double limitAmount = 0.0;
        if (obj != null) {
            mid = (String) obj[0];
            pfid = (String) obj[1];
            fid = (String) obj[2];
            cdate = (Date) obj[5];
            amount = (Double) obj[4];
            limit = (Double) obj[6];
            if ((Boolean) obj[3] == true) {
                objtotal = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SUM(A.BALANCEAMOUNT) FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE C.ID=? AND C.ACCOUNT=A.ACCOUNTID AND TRANSTYPE='D'",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{ac_cust.getId()});
                limittotal = (Object[]) new StaticSentence(m_App.getSession(), "SELECT DEBTMAX FROM MEMTYPE WHERE ID=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{ac_cust.getMemtype()});
                if (objtotal != null && objtotal[0] != null && limittotal != null && limittotal[0] != null) {
                    totalDebtAmount = (Double) objtotal[0];
                    limitAmount = (Double) limittotal[0];
                    if(limitAmount>0.0){
                    if (totalDebtAmount >= limitAmount) {
                        JOptionPane.showMessageDialog(this, "This Member Crosses Debit Limit \r\n Total Amount : " + totalDebtAmount + "\r\n Amount Limit : " + limitAmount + " ", "Limit Reached", JOptionPane.WARNING_MESSAGE);
                    } else {
                        createSharedTicket(ac_cust, ac_place, ac_waiter, ac_floor);
                    }
                    }else {
                         createSharedTicket(ac_cust, ac_place, ac_waiter, ac_floor);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Define debitmax","warninig msg", JOptionPane.WARNING_MESSAGE);
                    createSharedTicket(ac_cust, ac_place, ac_waiter, ac_floor);
                }
            } else {
                try {
                    if (JOptionPane.showConfirmDialog(this, "Facility : " + pfid + " \r\n " + "Amount :" + amount + "Allowed Limit : " + limit + " \r\n .And press YES to send request for approval or NO to exit", "Limit Reached", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,STATUS_ FROM FACILITYLIMITAPPROVAL WHERE MEMID=? AND FACID=? ORDER BY RDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN})).find(new Object[]{mid, fid, cdate});
                        if (obj2 != null) {
                            if (obj2[1] == null) {
                                if (JOptionPane.showConfirmDialog(this, "request is under process!!! \r\n and wish to withdraw it and send request again click YES or No to exit", LocalRes.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), mid, fid, new Date(), amount};
                                    reasondetail reason = reasondetail.getDialog(this, m_App);
                                    boolean bool;
                                    try {
                                        bool = reason.showDialog(value);
                                        new PreparedSentence(m_App.getSession(), "DELETE FROM FACILITYLIMITAPPROVAL WHERE ID=?", SerializerWriteString.INSTANCE, null).exec(obj2[0].toString());
                                    } catch (BasicException e) {
                                        new MessageInf(e).show(this);
                                    }

                                }
                            } else if ((Boolean) obj2[1] == true) {
                                JOptionPane.showMessageDialog(this, "Already requested!!!!!!and Request is approved!!!!!!!");
                                createSharedTicket(ac_cust, ac_place, ac_waiter, ac_floor);
                            } else {
                                if (JOptionPane.showConfirmDialog(this, "request is rejected!!!!!!!! and wish to send it again...", LocalRes.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), mid, fid, new Date(), amount};
                                    reasondetail reason = reasondetail.getDialog(this, m_App);
                                    boolean bool;
                                    try {
                                        bool = reason.showDialog(value);
                                    } catch (BasicException e) {
                                        new MessageInf(e).show(this);
                                    }
                                }
                            }
                        } else {
                            Object[] value = new Object[]{UUID.randomUUID().toString(), mid, fid, new Date(), amount};
                            reasondetail reason = reasondetail.getDialog(this, m_App);
                            boolean bool;
                            try {
                                bool = reason.showDialog(value);
                            } catch (BasicException e) {
                                new MessageInf(e).show(this);
                            }

                        }
                    }
                } catch (BasicException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            createSharedTicket(ac_cust, ac_place, ac_waiter, ac_floor);
        }
    }

    private void createSharedTicket(CustomerInfoExt ac_cust, String ac_place, String ac_waiter, String ac_floor) {
        try {
            m_oTicket = new TicketInfo();
            m_oTicket.setCustomer(ac_cust);
            m_oTicket.setPlace(dlSales.getPlaceByID(ac_place));
            m_oTicket.setWaiter(dlSales.getWaiterByID(ac_waiter));
            m_oTicket.setFloor(dlSales.getFloorByID(ac_floor));
            m_oTicket.setUser(m_App.getAppUserView().getUser().getUserInfo());
            int flag = addNewTicket(m_oTicket);
            if (flag == 1) {
                m_panelticket.setActiveTicket(m_oTicket, ac_place);
            }
            if (cr.getSerialConnection() != null) {
                cr.getSerialConnection().setIncommingString("");
            }
        } catch (BasicException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_CAUTION, AppLocal.getIntString("message.ticketalreadyexists"), e);
            msg.show(this);
        }
    }

    private void loadCurrentSharedTickets(AppView app) {
        try {
            int typeOfUser;
            String id;
            typeOfUser = m_App.getAppUserView().getUser().getTypeOfUser();
            id = m_App.getAppUserView().getUser().getId();
            List<SharedTicketInfo> sti = dlReceipts.getSharedTicketList(m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);

            // sharedticketlist.addAll(sti);
            m_ticketList = new ArrayList<TicketInfo>(sti.size());
            for (Iterator<SharedTicketInfo> it = sti.iterator(); it.hasNext();) {
                SharedTicketInfo sharedTicketInfo = it.next();
                TicketInfo t = dlReceipts.getSharedTicket(sharedTicketInfo.getId(), m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
                if (t != null) {
                    //if (i == 1 && id.equals(t.getWaiterId())) {
                    if (typeOfUser == 1) {
                        if (id.equals(t.getWaiterId())) {
                            m_ticketList.add(t);
                        }
                    } else {
                        m_ticketList.add(t);
                    }
                }
            }

        } catch (BasicException e) {
            new MessageInf(e).show(this);
        }
    }

    private int addNewTicket(TicketInfo t) throws BasicException {
        String id = "", name = "";
        /*   Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession();
        String id=(String) new StaticSentence(s, "CALL NEXT VALUE FOR SHAREDTICKETSNUM", null, SerializerReadInteger.INSTANCE).find();
        String cid=t.getCustomerId();
        String cust,waiter,table,floor;
        int flag=0,cflag=0;;
        table =  m_table.getSelectedItem().toString();
        waiter= m_waiter.getSelectedItem().toString();
        floor= m_floor.getSelectedItem().toString();
        cust=jTextField1.getText();
        String cust1,waiter1,table1,floor1;
        Object[] obj = (Object [] )  new StaticSentence(m_App.getSession()
        , "SELECT NAME FROM SHAREDTICKETS WHERE CUSTID=? "
        , SerializerWriteString.INSTANCE
        ,new SerializerReadBasic(new Datas[] {Datas.STRING})).find( cid);
        if(obj==null )
        {
        cflag=0;
        }
        else
        {
        cflag=1;
        }
        if(cflag==1)
        {
        for(int i=0;i< m_introtablemodel.getRowCount();i++)
        {
        cust1= m_introtablemodel.getValueAt(i, 0).toString();
        waiter1=m_introtablemodel.getValueAt(i, 2).toString();
        table1=m_introtablemodel.getValueAt(i, 1).toString();
        floor1=m_introtablemodel.getValueAt(i, 3).toString();
        if(cflag==1 && waiter.equals(waiter1) && table.equals(table1) && floor.equals(floor1))
        {
        JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.duplicateuser"), AppLocal.getIntString("message.duplicatetitle"), JOptionPane.WARNING_MESSAGE);
        flag=1;
        break;
        }
        }
        // else
        if(cflag==1 )
        {
        if(JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.customerpresent"), AppLocal.getIntString("message.presenttitle"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
        flag=0;
        // break;
        }
        else
        {
        flag=1;
        }
        }
        //  }
        }
        if(flag==0)*/

        //dlReceipts.insertSharedTicket(t.getCustomerId(), m_oTicket,id);

        String customername;

        if (guestlist.getSelectedIndex() >= 0) {
            id = t.getCustomerId();
            name = m_oTicket.getCustomer().getName();
            customername = id + "#" + guestlist.getSelectedItem().toString();
            m_oTicket.getCustomer().setid(customername);
            m_oTicket.getCustomer().setName(name + " : " + guestlist.getSelectedItem().toString());

        } else {
            id = t.getCustomerId();
            name = t.getCustomer().getName();
            customername = t.getCustomerId();
        }
        int count = dlReceipts.checkSharedTicket(customername, m_oTicket, m_App.getAppUserView().getUser().getRole());
        if (count == 0) {
            //initiator changes - start
            dlReceipts.insertSharedTicket(customername, m_oTicket, m_App.getAppUserView().getUser().getRole(), getInitiator(),DataConstants.SALES);
            setInitiator("");
///aaa
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS where ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);
            String aid = obj[0].toString();
            Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ((OBDEBIT+CURDEBIT)-(OBCREDIT+CURCREDIT)) FROM trailbalance where ACCOUNTID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(aid);
             String opbs = obj1[0].toString();
             Double opb = new Double(opbs);
            Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM billingmember where ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);
            if(obj2==null){
            new PreparedSentence(m_App.getSession(), "INSERT INTO billingmember(ID, OPB, CURROPB) values(?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{id,opb,opb});
            }
            if(guestlist.getSelectedIndex()>=0){
                Object[] objg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM billingmember where ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(customername);
            if(objg==null){
                new PreparedSentence(m_App.getSession(), "INSERT INTO billingmember(ID, OPB, CURROPB) values(?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{customername,opb,opb});
                }
            }
///aaa
            //initiator changes - end
            customer = null;
            m_introtablemodel.addRow(t);
            return 1;
        } else {
            m_oTicket.getCustomer().setid(id);
            m_oTicket.getCustomer().setName(name);
            MessageInf msg = new MessageInf(MessageInf.SGN_CAUTION, AppLocal.getIntString("message.ticketalreadyexists"));
            msg.show(this);
            return 0;
        }

    }

    private void refreshlists() {
        try {
            //    m_table = new ComboBoxValModel(dlSales.getPlacesList().list());
            // System.out.println(m_App.getAppUserView().getUser().getRole());
            m_waiter = new ComboBoxValModel(dlSales.getActiveWaiterList(m_App.getAppUserView().getUser().getRole()));
            m_floor = new ComboBoxValModel(dlSales.getFloorsList().list());
            guestlist.setSelectedIndex(-1);
            loadCurrentSharedTickets(m_App);
        

        } catch (BasicException e) {
            new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("message.table_waiter_list_failed")).show(this);
            throw new IllegalStateException(e.getMessage(), e);
        }

        jComboBox3.setModel(m_floor);
        // jComboBox1.setModel(m_table);
        jComboBox2.setModel(m_waiter);
        int typeOfUser;
        typeOfUser = m_App.getAppUserView().getUser().getTypeOfUser();

        if (typeOfUser == 1) {
            jComboBox2.setSelectedIndex(0);
            jComboBox2.setEnabled(false);
        } else {
            jComboBox2.setEnabled(true);
        }

    }

    //praveen:cardreader function
    public void startCardReader() {
        try {
            String portNumber = m_App.getProperties().getProperty("card.portnumber");
            boolean cardAccessOnlyFlag = false;
            if (m_App.getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf(m_App.getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
            cr.setCardSwipeNotifier(this);
            System.out.println(portNumber);
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void activate() {
         
        startCardReader();
        refreshlists();
        loadCurrentSharedTickets(m_App);
        // guestlist.setSelectedIndex(-1);
        m_introtablemodel = new IntroTableModel(m_ticketList);
        jTable1.setModel(m_introtablemodel);
 

        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(1).setMaxWidth(140);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(2).setMaxWidth(140);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(120);
        jTextField1.setText(null);

        // jComboBox1.setSelectedIndex(-1);
        //jComboBox2.setSelectedIndex(-1);       
        jComboBox3.setSelectedIndex(0);
        m_panelticket.setActiveTicket(null, null);
        m_restaurantmap.activate();
        jTextField2.setText(null);
        jButton8.setVisible(false);
        jButton9.setText("Load");
          jTextField3.setText(null);
          
          
            b = false;
            p = true;
            ct_z = false;
            ct_b = true;
            ct_a = false;
            ct_s = true;
       //            ct_p = false;
            ctlk = false;
            cat = false;
            cat_s = false;
            ct_ent = false;
            ctl_b1=false;
            ctl_clk=true;
            focus = true;
            remark = true;
       
    
    
    
//    m_jUp.addActionListener(new ActionListener(){  //code for Up button
//      public void actionPerformed(ActionEvent ae){  
//          
//     UP()
//               ;}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_PAGE_UP)  )
//               
//             UP(); 
//            
//          }  
//          return false;}}); 
//    
//    m_jDown.addActionListener(new ActionListener(){  //code for down button
//      public void actionPerformed(ActionEvent ae){  
//              
//    DWN()
//              ;}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)  )
//               
//             DWN();  
//          }  
//          return false;}}); 
////    
//    
   
    
//    jButton3.addActionListener(new ActionListener(){  //code for Up button
//      public void actionPerformed(ActionEvent ae){  
//              
//       Pending();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_P)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
//               
//              Pending();  
//          }  
//          return false;}});
//    
//    
//        jButton7.addActionListener(new ActionListener(){  //code for Up button
//      public void actionPerformed(ActionEvent ae){  
//              
//        CrConf();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if(e.getKeyCode() == KeyEvent.VK_F1) 
//               
//              CrConf();  
//          }  
//          return false;}});
//    
    
        //   cust=null;
       // jTextField2.setCursor(this.getCursor());
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
        String CardRead = m_App.getProperties().getProperty("ACScard.port");
		if(cardReaderPortName.isEmpty() && CardRead.isEmpty()  ){
                    jTextField3.setEditable(true);
                    jButton10.setVisible(true);
                    jLabel8.setVisible(true);
                    jTextField3.requestFocus();


                } else {


                    jTextField2.requestFocus();
                    jTextField3.setEditable(false);
                    jButton10.setVisible(false);
                    jLabel8.setVisible(false);
                }
            }
        });
        
        try {
            int typeOfUser;
            String id;
            typeOfUser = m_App.getAppUserView().getUser().getTypeOfUser();
            id = m_App.getAppUserView().getUser().getId();
            if (typeOfUser == 1) {
                if (id != null) {
                    m_waiter = new ComboBoxValModel(dlSales.getActiveCardWaiter(id));
                    jComboBox2.setModel(m_waiter);
                    jComboBox2.setSelectedIndex(0);
                    jComboBox2.setEnabled(false);
                    jButton9.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "swipe a valid card");
                }

            } else {
                m_waiter = new ComboBoxValModel(dlSales.getActiveWaiterList(m_App.getAppUserView().getUser().getRole()));
                jComboBox2.setModel(m_waiter);
                jComboBox2.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    //   jTextField2.requestFocus();


    }
    
    public void UP(){
      //   int i = jTable1.getSelectedRow();
        if(p){
       int i = jTable1.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = jTable1.getModel().getRowCount() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < jTable1.getModel().getRowCount())) {
            // Solo seleccionamos si podemos.
         setSelectedIndex(i);
         
           // jTable1.getSelectionModel().setSelectionInterval(i, i);
        }        
//        
        
//////     
        }
    }
     public void setSelectedIndex(int i){
       
        // Seleccionamos
        jTable1.getSelectionModel().setSelectionInterval(i,i);

        // Hacemos visible la seleccion.
        Rectangle oRect =jTable1.getCellRect(i, 0, true);
        jTable1.scrollRectToVisible(oRect);
    }
    
    public void DWN(){
        
//   
     // int i = jTable1.getSelectedRow();   
        if(p){
      int i =jTable1.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= jTable1.getModel().getRowCount() ) {
                i = jTable1.getModel().getRowCount() - 1;
            }
        }

        if ((i >= 0) && (i < jTable1.getModel().getRowCount())) {
            // Solo seleccionamos si podemos.
     setSelectedIndex(i);
           // jTable1.getSelectionModel().setSelectionInterval(i, i);
        }        
//
////       
//        
        }
   }
    
    
   public void Bill(){
       if(p){
     QTListnum1 qtList = QTListnum1.getDialog(this, m_App, dlSales, qTicket);
        try {
            int row = jTable1.getSelectedRow();
            String id;
            int i = jTable1.getSelectedRow();
            if (i >= 0 && i < m_ticketList.size()) {
                m_oTicket = m_ticketList.get(i);
            }

            /*     String name= m_introtablemodel.getValueAt(row, 0).toString();
            String arr[]=name.split(" - ");
            name=arr[1];
            String searchkey=arr[0];
            
            Object[] obj = (Object [] )  new StaticSentence(m_App.getSession()
            , "SELECT CID FROM SHAREDTICKETS WHERE NAME = ? AND COUNTER=? "
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
            ,new SerializerReadBasic(new Datas[] {Datas.STRING})).find(new Object[]{ searchkey,m_App.getAppUserView().getUser().getRole()});
            if(obj != null && obj[0]!=null)*/
            {
                // id=obj[0].toString();
                id = m_oTicket.getCustomerId();
                CustomerInfo cinfo = new CustomerInfo(id);
                //  cinfo.setName(name);
                cinfo.setName(m_oTicket.getCustomer().getName());
                // cinfo.setSearchkey(searchkey);
                cinfo.setSearchkey(m_oTicket.getCustomer().getSearchkey());
                boolean flag = qtList.setCustomer(cinfo);
                if (flag == true) {
                    qtList.refreshModel();
                    qtList.displayBill();
                }
                // qtList.reloadPendingQTList(true);
            }
            //  else
            //      id="";
            //praveen:exit for every transaction for kiosk mode---start
            if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
                JPrincipalApp.m_approot.closeAppView();
            }
            //praveen:exit for every transaction for kiosk mode---end


        } catch (Exception e) {
            e.printStackTrace();
        }
      
   }
   }
   
    public void pending(){
        if(p){
          dflag = true;
        displayBillList();
        dflag = false;
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
    }
    }
    
    
     public void cnfrm(){
         if(p){
          DebtBillListnum1 dbillList = DebtBillListnum1.getDialog(this, dlSales, m_App, false);
        dbillList.showDialog();
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
     }
     }
     
    public void Select(){
//        int i=0;
   // if(i>=0){   
         int i = jTable1.getSelectedRow();
        if (i >= 0 && i < m_ticketList.size()) {
         
            m_oTicket = m_ticketList.get(i);
            m_panelticket.setActiveTicket(m_oTicket, m_oTicket.getPlace().getName());
        } else {
            p = true;
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.ticketnotselected"));
            msg.show(this);
        }
     //}
    }
    
    public void Load(){
        if(p){
        String portNumber = m_App.getProperties().getProperty("card.portnumber");
        String portValue = m_App.getProperties().getProperty("ACScard.port");
        if (!portNumber.isEmpty() && portValue.isEmpty()) {
            String card = cr.getData().toString();
               if (card != null) {
                try {
                    loadMemberDetails(card);
                } catch (HeadlessException ex) {
                    Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
                } 
                //catch (BasicException ex) {
                //    Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
                //}
            }
        } else if (portNumber.isEmpty() && !portValue.isEmpty()) {
                    final Cosacs cd = new Cosacs();

                    String card = cd.portOpen(portValue);
                    if(card.equals("FALSE")){
                         JOptionPane.showMessageDialog(this, "The Port is Already Open", null, JOptionPane.OK_OPTION);
                    }
                    card = cd.getCardNumber();
            if (card != null) {
                try {
                    loadMemberDetails(card);
                } catch (HeadlessException ex) {
                    Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
                } 
                //catch (BasicException ex) {
                  //  Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
                //}
        }
             card = cd.portClose();
        }else if (portNumber.isEmpty() && portValue.isEmpty()) {
                    String card = jTextField3.getText();
       if (card != null) {
                try {
                    loadMemberDetails(card);
                } catch (HeadlessException ex) {
                    Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
                } 
                //catch (BasicException ex) {
                   // Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
               // }
        }
    }  
        }
    }
    
    public void Find(){
        if(p){
        JCustomerFindernum1 finder = JCustomerFindernum1.getCustomerFinder(this, dlCustomers);
        //finder.search(m_oTicket.getCustomer());
        finder.setVisible(true);
        CustomerInfo customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                customer = dlSales.loadCustomerExt(customerInfo.getId());
                jTextField1.setText(customerInfo.toString());
            } catch (BasicException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
        }
    }
    
    public void Pending(){
       
        dflag = true;
        displayBillList();
        dflag = false;
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
    }
    
    
    public void CrConf(){
              DebtBillListnum1 dbillList = DebtBillListnum1.getDialog(this, dlSales, m_App, false);
        dbillList.showDialog();
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
    }
    
    public void Authrzn(){//Authrization method
       

    try {
            JUserAuthorisation juser = new JUserAuthorisation();
            if(!ct_s){
            juser.insertToUserAuthorisation(customer, (String) m_table.getSelectedKey(), (String) m_waiter.getSelectedKey(), (String) m_floor.getSelectedKey(), m_App.getAppUserView().getUser().getName(), m_App, m_App.getAppUserView().getUser().getRole());
            jButton8.setVisible(false);
            jTextField2.requestFocus();
            jTextField1.setText(null);
            jTextField2.setText(null);
             ct_s = true;
             newTicket();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    
      public void   Add()//Down method
  {   
      //    if(p){
          try {
           Timestamp ts = new Timestamp(new Date().getTime());
        try {

            Statement s = m_App.getSession().getConnection().createStatement();
            ResultSet r = s.executeQuery("select curdate() date"); //getting server time for the first time when app is started
            if (r.next()) {
                ts = r.getTimestamp("date");
               // System.out.println(ts);
            }


        } catch (SQLException ex) {
        }
            
            final Date d = ts;
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);

            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CALDATE FROM FACILITYLIMITMASTER", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find();
            if (obj != null) {
                Date cdate = (Date) obj[0];
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(cdate);
                cal2.set(Calendar.HOUR_OF_DAY, 00);
                cal2.set(Calendar.MINUTE, 00);
                cal2.set(Calendar.SECOND, 00);
                cal2.set(Calendar.MILLISECOND, 00);
                if (cal.compareTo(cal2) == 0) {
                    setActiveChoice(customer, (String) m_table.getSelectedKey(), (String) m_waiter.getSelectedKey(), (String) m_floor.getSelectedKey());
                } else {
                    JOptionPane.showMessageDialog(this, "Please calculate facility limit.........");
                }
            } else {
                setActiveChoice(customer, (String) m_table.getSelectedKey(), (String) m_waiter.getSelectedKey(), (String) m_floor.getSelectedKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    //      }
    }

    public boolean deactivate() {

        return true;
    }

    private boolean validateDeleteTicket() throws BasicException {
        List<QticketInfo> qts = LookupUtilityImpl.getInstance(null).getDataLogicQT().getPendingQTicketList(m_oTicket.getCustomerId());
        return qts.isEmpty();
    }

    public boolean deleteTicket() {
        boolean resultok = false;
        try {
            if (validateDeleteTicket()) {
                LookupUtilityImpl.getInstance(null).getDataLogicReceipts().deleteSharedTicket(m_oTicket.getCustomerId(), m_App.getAppUserView().getUser().getRole());
                resultok = true;
            } else {
                new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.pendingqt")).show(this);
            }
        } catch (BasicException e) {
            new MessageInf(e).show(this);
        }
        return resultok;
    }

    protected JComponent getBagComponent() {
        return m_restaurantmap;
    }

    protected JComponent getNullComponent() {
        return this;
    }

//    private void setActiveCustomer(CustomerInfo customer)  {
//        this.customer = customer;
//        m_oTicket = new TicketInfo();
//        try {
//            dlReceipts.insertSharedTicket(customer.getId(), m_oTicket);
//        } catch (BasicException e) {
//            new MessageInf(e).show(JIntroPageRest.this); // Glup. But It was empty.
//        }
//        m_panelticket.setActiveTicket(m_oTicket, customer.getName());
//
//    }
    private void refreshModel() {
        loadCurrentSharedTickets(m_App);
        ((IntroTableModel) jTable1.getModel()).refresh(m_ticketList);
    }

    private void updateCurrentSharedTicket() throws BasicException {
        if (m_oTicket != null) {
            dlReceipts.updateSharedTicket(m_oTicket.getCustomerId(), m_oTicket, m_App.getAppUserView().getUser().getRole());
        }
    }

    public void newTicket() {
        try {
            if(remark){
            if(JPanelTicketnum1.ct_t||QTListnum1.b){
            p=true;
            cat = false;
            JIntroPageRestnum1.ct_z = false;
            JIntroPageRestnum1.ctlk = false;
            //The ticket might be modified from ReceiptSplit
            m_oTicket = m_panelticket.getActiveTicket();
            updateCurrentSharedTicket();

            activate();
              //m_panelticket.setActiveTicket(m_oTicket, wait);
            // printState();
           m_panelticket.setActiveTicket(null, null);
            refreshModel();
            remark = true;
            focus = true;
            }
        }
        } catch (BasicException e) {
            new MessageInf(e).show(this);
        }
    }

    private void setActiveChoice(CustomerInfoExt ac_cust, String ac_place, String ac_waiter, String ac_floor) {
         if (ac_cust == null || ac_place == null || ac_waiter == null || ac_floor == null) {
            new MessageInf(MessageInf.SGN_CAUTION, AppLocal.getIntString("message.chooseallticketvalues")).show(this);
            return;
        }
        try {
            flcheck = new FacilityLimitCheck();
            String mid = null;
            String pfid = null;
            String fid = null;
            Date cdate = null;
            Double amount = null;
            Double limit = null;
            String id = null;
            Object s = m_App.getAppUserView().getUser().getWarehouse();
            String warehouse = null;
            if (s != null) {
                String[] warehouses = s.toString().split("#");
                warehouse = warehouses[0];
            }
            Object obj = new StaticSentence(m_App.getSession(), "SELECT FACILITY FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(warehouse);
            if (obj != null) {
                id = obj.toString();
            } else {
                JOptionPane.showMessageDialog(this, "select facility for warehouse that assigned to user");
            }
            Object[] fac = (Object[]) new StaticSentence(m_App.getSession(), "SELECT f.initcontrol from facility f where  F.ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(id);
            if ((Boolean) fac[0] == false) {
                checkToCreateSharedTicket(ac_cust, id, ac_place, ac_waiter, ac_floor, pfid, amount, limit, mid, fid, cdate);
            } //            else if(m_App.getAppUserView().getUser().getTypeOfUser()==1){
            //                checkToCreateSharedTicket(ac_cust, id, ac_place, ac_waiter, ac_floor, pfid, amount, limit, mid, fid, cdate);
            //            }
            else if (!flag) {
                checkToCreateSharedTicket(ac_cust, id, ac_place, ac_waiter, ac_floor, pfid, amount, limit, mid, fid, cdate);
                flag = true;
            } else {
                String portNumber = m_App.getProperties().getProperty("card.portnumber");
                String portValue = m_App.getProperties().getProperty("ACScard.port");
                if (!portNumber.isEmpty() || !portValue.isEmpty()) {
                    String card = cr.getData().toString();
                if (card == null || card.length() <= 0) {
                    //JOptionPane.showMessageDialog(this, "Please swipe a card");
                    //praveen:added to request authorisation for creation of  shared ticket----start
                    if (JOptionPane.showConfirmDialog(this, "If ur card is not issued, click YES to 'Send for Authorisation' OR \r\n if issued swipe a valid card", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        ct_s = false;
                        Object[] object = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MEMID,STATUS_ FROM USERAUTHORISATION WHERE MEMID=? AND COUNTER=? ORDER BY RDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN})).find(new Object[]{ac_cust.getId().toString(), m_App.getAppUserView().getUser().getRole()});
                        Object[] object1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CID,COUNTER FROM SHAREDTICKETS WHERE CID=? AND COUNTER=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(new Object[]{ac_cust.getId().toString(), m_App.getAppUserView().getUser().getRole()});
                        if (object != null && object[0] != null) {
                            if (object[1] != null) {
                                boolean bool = Boolean.valueOf(object[1].toString());
                                if (bool) {
                                    if (object1 != null && object1[0] != null) {
                                        JOptionPane.showMessageDialog(this, "Select Member from shared ticket list!!!!", "invalid operation", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(this, "press 'Send for Authorisation' button");
                                        jButton8.setVisible(true);
                                    }
                                } else {
                                    if (JOptionPane.showConfirmDialog(this, "request is rejected, click YES to send it again or NO to exit", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                        JOptionPane.showMessageDialog(this, "press 'Send for Authorisation' button");
                                        jButton8.setVisible(true);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Request is under process");
                            }

                        } else {
                            //JOptionPane.showMessageDialog(this, "press 'Send for Authorisation' button");
                            jButton8.setVisible(true);
                        }
                    }
                //praveen:added to request authorisation for creation of  shared ticket---end
                } else {
                    ct_s = true;
                    Object[] details = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.NAME,C.SEARCHKEY,C.ID,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=? UNION ALL SELECT C.NAME,C.SEARCHKEY,C.ID,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                            new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                    if (details != null) {
                        //initiator changes - start
                        if (details[2].toString().equals(ac_cust.getId()) && details[4] != null) {
                            setInitiator(details[4].toString());
                            //initiator changes - end
                            checkToCreateSharedTicket(ac_cust, id, ac_place, ac_waiter, ac_floor, pfid, amount, limit, mid, fid, cdate);
                        } else {
                            JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card does not match!!!", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card Not Registered!!!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
                if (portNumber.isEmpty() && portValue.isEmpty()) {
                    String cardd = jTextField3.getText();
                    if (cardd == null || cardd.length() <= 0) {
                        //JOptionPane.showMessageDialog(this, "Please swipe a card");
                        //praveen:added to request authorisation for creation of  shared ticket----start
                        if (JOptionPane.showConfirmDialog(this, "If ur card is not issued, click YES to 'Send for Authorisation' OR \r\n if issued swipe a valid card", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            ct_s = false;
                            Object[] object = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MEMID,STATUS_ FROM USERAUTHORISATION WHERE MEMID=? AND COUNTER=? ORDER BY RDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN})).find(new Object[]{ac_cust.getId().toString(), m_App.getAppUserView().getUser().getRole()});
                            Object[] object1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CID,COUNTER FROM SHAREDTICKETS WHERE CID=? AND COUNTER=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(new Object[]{ac_cust.getId().toString(), m_App.getAppUserView().getUser().getRole()});
                            if (object != null && object[0] != null) {
                                if (object[1] != null) {
                                    boolean bool = Boolean.valueOf(object[1].toString());
                                    if (bool) {
                                        if (object1 != null && object1[0] != null) {
                                            JOptionPane.showMessageDialog(this, "Select Member from shared ticket list!!!!", "invalid operation", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(this, "press 'Send for Authorisation' button");
                                            jButton8.setVisible(true);
                                        }
                                    } else {
                                        if (JOptionPane.showConfirmDialog(this, "request is rejected, click YES to send it again or NO to exit", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(this, "press 'Send for Authorisation' button");
                                            jButton8.setVisible(true);
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Request is under process");
                                }

                            } else {
                                //JOptionPane.showMessageDialog(this, "press 'Send for Authorisation' button");
                                jButton8.setVisible(true);
                            }
                        }
                    } //praveen:added to request authorisation for creation of  shared ticket---end
                    else {
                        ct_s = true;
                        Object[] details = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.NAME,C.SEARCHKEY,C.ID,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=? UNION ALL SELECT C.NAME,C.SEARCHKEY,C.ID,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{cardd, cardd});
                        if (details != null) {
                            //initiator changes - start
                            if (details[2].toString().equals(ac_cust.getId()) && details[4] != null) {
                                setInitiator(details[4].toString());
                                //initiator changes - end
                                checkToCreateSharedTicket(ac_cust, id, ac_place, ac_waiter, ac_floor, pfid, amount, limit, mid, fid, cdate);
                            } else {
                                JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card does not match!!!", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card Not Registered!!!", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            }

        } catch (BasicException ex) {
            ex.printStackTrace();
        }

    }

    private void displayBillList() {
        BillListnum1 billList = BillListnum1.getDialog(this, dlSales, dlBill, customer);
        billList.showDialog();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this metho
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        guestlist = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        m_jDown = new javax.swing.JButton();
        m_jUp = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();

        jLabel1.setText("Customer"); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton1.setToolTipText("Member list");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Table"); // NOI18N

        jLabel3.setText("Waiter"); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
        jButton4.setText("Add New"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Customer", "Table", "Waiter", "Floorl"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(5.0F);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setNextFocusableComponent(jComboBox2);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jComboBox2.setNextFocusableComponent(guestlist);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Existing Customers"); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
        jButton2.setText("Select"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pending Bill List");
        jButton3.setToolTipText("Bill List");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton5.setToolTipText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Bill");
        jButton6.setToolTipText("Bill");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cr.Confirm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setToolTipText("CTL P");
        jButton5.addActionListener(new ActionListener(){  //code for Up button
            public void actionPerformed(ActionEvent ae){

                refreshModel();}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_R) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))

                refreshModel();
            }
            return false;}});
jButton6.getAccessibleContext().setAccessibleName("");
jButton6.getAccessibleContext().setAccessibleDescription("null");

jLabel5.setText("Floor");

jComboBox3.setNextFocusableComponent(jComboBox1);
jComboBox3.addItemListener(new java.awt.event.ItemListener() {
    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        jComboBox3ItemStateChanged(evt);
    }
    });
    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
        }
    });
    jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jComboBox3KeyPressed(evt);
        }
    });

    jTextField2.setNextFocusableComponent(jComboBox3);
    jTextField2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField2ActionPerformed(evt);
        }
    });
    jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jTextField2KeyPressed(evt);
        }
    });

    jLabel6.setText("Mem No:");

    jLabel7.setText("Guest");

    guestlist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Guest 1", "Guest 2", "Guest 3", "Guest 4", "Guest 5" }));
    guestlist.setNextFocusableComponent(jTextField2);
    guestlist.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            guestlistActionPerformed(evt);
        }
    });
    guestlist.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            guestlistKeyPressed(evt);
        }
    });

    jButton9.setText("jButton9");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton9ActionPerformed(evt);
        }
    });

    jButton8.setText("Send For Authrzn.");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton8ActionPerformed(evt);
        }
    });

    jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png")));
    jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tortoise Respositry/source code/GarudaClubPlusMySql/GarudaClubPlus-MysqlNew/src-beans/com/openbravo/images/reload.png"))); // NOI18N
    jButton10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton10ActionPerformed(evt);
        }
    });

    jTextField3.setNextFocusableComponent(jComboBox3);
    jTextField3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField3ActionPerformed(evt);
        }
    });
    jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jTextField3KeyPressed(evt);
        }
    });

    jLabel8.setText("Card No:");

    m_jDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
    m_jDown.setFocusPainted(false);
    m_jDown.setFocusable(false);
    m_jDown.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jDown.setRequestFocusEnabled(false);
    m_jDown.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jDownActionPerformed(evt);
        }
    });

    m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
    m_jUp.setFocusPainted(false);
    m_jUp.setFocusable(false);
    m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jUp.setVisible(false);
    m_jUp.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jUpActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(jButton9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton4))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addComponent(jComboBox3, 0, 121, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addComponent(jTextField4))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2))
                                            .addGap(18, 18, 18))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, 0, 163, Short.MAX_VALUE))
                                        .addComponent(guestlist, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(63, 63, 63))
                .addComponent(jLabel4)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
                            .addComponent(jButton2)))
                    .addGap(72, 72, 72)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(m_jDown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(m_jUp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jButton1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(guestlist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))))
                    .addGap(8, 8, 8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton9)
                        .addComponent(jButton8))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(89, 89, 89)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(m_jDown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m_jUp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap())
    );

    jButton1.getAccessibleContext().setAccessibleDescription("null");
    jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
    m_jDown.addActionListener(new ActionListener(){  //code for down button

        public void actionPerformed(ActionEvent ae){

            DWN()
            ;}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
    public boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            if((e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)  )

            DWN();
        }
        return false;}});
m_jDown.setVisible(false);

m_jDown.setVisible(false);
m_jUp.addActionListener(new ActionListener(){  //code for UP

public void actionPerformed(ActionEvent ae){

    UP()
    ;}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_PAGE_UP)  )

                UP();

            }
            return false;}});
m_jUp.setVisible(false);
jTextField4.setEditable(false);
}// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
//        try {
//           Timestamp ts = new Timestamp(new Date().getTime());
//        try {
//
//            Statement s = m_App.getSession().getConnection().createStatement();
//            ResultSet r = s.executeQuery("select curdate() date"); //getting server time for the first time when app is started
//            if (r.next()) {
//                ts = r.getTimestamp("date");
//               // System.out.println(ts);
//            }
//
//
//        } catch (SQLException ex) {
//            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//            final Date d = ts;
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(d);
//            cal.set(Calendar.HOUR_OF_DAY, 00);
//            cal.set(Calendar.MINUTE, 00);
//            cal.set(Calendar.SECOND, 00);
//            cal.set(Calendar.MILLISECOND, 00);
//
//            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CALDATE FROM FACILITYLIMITMASTER", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find();
//            if (obj != null) {
//                Date cdate = (Date) obj[0];
//                Calendar cal2 = Calendar.getInstance();
//                cal2.setTime(cdate);
//                cal2.set(Calendar.HOUR_OF_DAY, 00);
//                cal2.set(Calendar.MINUTE, 00);
//                cal2.set(Calendar.SECOND, 00);
//                cal2.set(Calendar.MILLISECOND, 00);
//                if (cal.compareTo(cal2) == 0) {
//                    setActiveChoice(customer, (String) m_table.getSelectedKey(), (String) m_waiter.getSelectedKey(), (String) m_floor.getSelectedKey());
//                } else {
//                    JOptionPane.showMessageDialog(this, "Please calculate facility limit.........");
//                }
//            } else {
//                setActiveChoice(customer, (String) m_table.getSelectedKey(), (String) m_waiter.getSelectedKey(), (String) m_floor.getSelectedKey());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int i = jTable1.getSelectedRow();
        if (i >= 0 && i < m_ticketList.size()) {
            p = false;
              ct_z = true;
              cat = true;
              JIntroPageRestnum1.ctlk = true;
            m_oTicket = m_ticketList.get(i);
            m_panelticket.setActiveTicket(m_oTicket, m_oTicket.getPlace().getName());
        } else {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.ticketnotselected"));
            msg.show(this);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        dflag = true;
        displayBillList();
        dflag = false;
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
         
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        refreshModel();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
         String custoid;
         jTextField2.requestFocus();
            String cust = jTextField2.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                    jTextField1.setText(obj[1].toString());
                    jComboBox3.setFocusable(true);
                     jComboBox3.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
           // jTextField2.transferFocus();
             
            
         
    
    }//GEN-LAST:event_jTextField2ActionPerformed
//  public String cust;
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
//        // TODO add your handling code here:
//        // String cust=null;
//        String custoid;
//
//        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
//            jTextField2.requestFocus();
              
//            String cust = jTextField2.getText();
//            try {
//                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
//                if (obj == null) {
//                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
//                } else {
//                    custoid = obj[0].toString();
//                    customer = dlSales.loadCustomerExt(custoid);
//                    jTextField1.setText(obj[1].toString());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            
//        }
//        else
//            jTextField2.transferFocus();
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
//        if (evt.getClickCount() > 1) {
//            int i = jTable1.getSelectedRow();
//            if (i >= 0 && i < m_ticketList.size()) {
//                m_oTicket = m_ticketList.get(i);
//                m_panelticket.setActiveTicket(m_oTicket, m_oTicket.getPlace().getName());
//            } else {
//                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.ticketnotselected"));
//                msg.show(this);
//            }
//        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        String temp = m_floor.getSelectedItem().toString();
        String floor;
        try {
            if (temp != null) {
                // try{
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM FLOORS WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(temp);

                if (obj1 == null) {
                    floor = "";
                } else {
                    floor = obj1[0].toString();
                }
                m_table = new ComboBoxValModel(dlSales.getPlaceList(floor));
                jComboBox1.setModel(m_table);
                jComboBox1.setSelectedIndex(0);
                jComboBox1.setEnabled(true);
            // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    
    
    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    
    }//GEN-LAST:event_jButton6ActionPerformed

    
    
    
    
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        DebtBillListnum1 dbillList = DebtBillListnum1.getDialog(this, dlSales, m_App, false);
        dbillList.showDialog();
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
//        try {
//            JUserAuthorisation juser = new JUserAuthorisation();
//            juser.insertToUserAuthorisation(customer, (String) m_table.getSelectedKey(), (String) m_waiter.getSelectedKey(), (String) m_floor.getSelectedKey(), m_App.getAppUserView().getUser().getName(), m_App, m_App.getAppUserView().getUser().getRole());
//            jButton8.setVisible(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton8ActionPerformed

private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed

}//GEN-LAST:event_jButton4KeyPressed

private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
// TODO add your handling code here:
      // TODO add your handling code here:
   
        
}//GEN-LAST:event_jButton8KeyPressed

private void jButton7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyReleased

}//GEN-LAST:event_jButton7KeyReleased

private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
  //m_jUp.addActionListener(new ActionListener(){  //code for Up button
    jTable1.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
          
     UP()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_UP)  )
               
             UP(); 
            
          }  
          return false;}}); 
    
   // m_jDown.addActionListener(new ActionListener(){  //code for down button
    jTable1.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
              
    DWN()
              ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_DOWN)  )
               
             DWN();  
          }  
          return false;}}); 
    
     
}//GEN-LAST:event_jTable1KeyPressed

private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
// TODO add your handling code here:
    
}//GEN-LAST:event_jButton3KeyPressed

private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
// TODO add your handling code here:
    
}//GEN-LAST:event_jButton5KeyPressed

private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
// TODO add your handling code here:
   
     
}//GEN-LAST:event_jButton6KeyPressed

private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
// TODO add your handling code here:
  
        
}//GEN-LAST:event_jButton2KeyPressed

private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
// TODO add your handling code here:
  
     jComboBox1.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
          
     jComboBox2.requestFocus()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ENTER)  )
               
             jComboBox2.requestFocus();
           
            
            
          }  
          return false;}});  
}//GEN-LAST:event_jComboBox1KeyPressed

private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
// TODO add your handling code here:
   // jComboBox1.requestFocus();
     //jComboBox3.transferFocus();
/////////////////////////////////////////////////////////////////////  
    jComboBox3.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
          
     jComboBox1.requestFocus()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ENTER)  )
               
             jComboBox1.requestFocus();
           
            
            
          }  
          return false;}});  
//////////////////////////////////////////////////////////////////       
         //jComboBox3.transferFocus();
   
}//GEN-LAST:event_jComboBox3KeyPressed

private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
// TODO add your handling code here
   
     jComboBox2.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
          
     guestlist.requestFocus()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ENTER)  )
               
           guestlist.requestFocus();
           
            
            
          }  
          return false;}});  
    
}//GEN-LAST:event_jComboBox2KeyPressed

private void guestlistKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guestlistKeyPressed
// TODO add your handling code here:
    guestlist.addKeyListener(new java.awt.event.KeyAdapter(){
      public void actionPerformed(ActionEvent ae){  
          
     jTextField2.requestFocus()
               ;}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_ENTER)  )
               
       jTextField2.requestFocus();
           
            
            
          }  
          return false;}}); 
 
}//GEN-LAST:event_guestlistKeyPressed

private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
    // TODO add your handling code here:
    String custoid;
    String cust = jTextField3.getText();
    
     if(cust!=null && cust.length()>0)
    {
    try {
       Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=? AND C.VISIBLE=TRUE UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=? AND C.VISIBLE=TRUE",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{cust, cust});
       if (obj == null) {
            JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
        } else {
            custoid = obj[0].toString();
            customer = dlSales.loadCustomerExt(custoid);
            jTextField2.setText(obj[1].toString());
            jTextField1.setText(obj[2].toString());
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
    }
    //akshatha:to read a card from card reader without port num
    jTextField3.transferFocus();
}//GEN-LAST:event_jTextField3ActionPerformed

private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    // TODO add your handling code here:
    java.awt.EventQueue.invokeLater(new Runnable() {

        public void run() {

            jTextField3.requestFocus();
            jTextField3.setText(null);
            jTextField2.setText(null);
            jTextField1.setText(null);
            
            
        }
    }); //akshatha:to read a card from card reader without port num
}//GEN-LAST:event_jButton10ActionPerformed

private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
      
}//GEN-LAST:event_jButton9ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    
    
        JCustomerFindernum1 finder = JCustomerFindernum1.getCustomerFinder(this, dlCustomers);
        //finder.search(m_oTicket.getCustomer());
        finder.setVisible(true);
        CustomerInfo customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                customer = dlSales.loadCustomerExt(customerInfo.getId());
                jTextField1.setText(customerInfo.toString());
            } catch (BasicException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
}//GEN-LAST:event_jButton1ActionPerformed

private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
// TODO add your handling code here:
   // jComboBox3.transferFocus();
}//GEN-LAST:event_jComboBox3ActionPerformed

private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jDownActionPerformed

private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jUpActionPerformed

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
// TODO add your handling code here:
  //  jComboBox1.transferFocus();
}//GEN-LAST:event_jComboBox1ActionPerformed

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
// TODO add your handling code here:
  //  jComboBox2.transferFocus();
}//GEN-LAST:event_jComboBox2ActionPerformed

private void guestlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestlistActionPerformed
// TODO add your handling code here:
  //  guestlist.transferFocus();
}//GEN-LAST:event_guestlistActionPerformed
public long startSec = 0;
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        char c = evt.getKeyChar();
        if(jTextField3.getText()!=null){
            int length = jTextField3.getText().trim().length();
            if(length==1){
                startSec = System.currentTimeMillis();
            }
            else if(length>1){
                long Currsec = System.currentTimeMillis();
                long diff = Currsec-startSec;
                
                if(diff>700){
                    JOptionPane.showMessageDialog(this, "Do not use keyboard. Please swipe card.");
                    jTextField3.setText(null);
                }
                if(c==KeyEvent.VK_ENTER){
                    System.out.println("Time Taken : "+diff);
                }
            }
        }
    }//GEN-LAST:event_jTextField3KeyPressed

/**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox guestlist;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton m_jDown;
    private javax.swing.JButton m_jUp;
    // End of variables declaration//GEN-END:variables

    public void cardswiped(WaiterInfo waiter) {
        try {
            String id = waiter.getID();
            String role = waiter.getCounter();
            String name = waiter.getCardNo();
            //warehouse changes -start
            Object obj = new StaticSentence(m_App.getSession(), "SELECT P.PRCATEGORIES FROM PEOPLE P WHERE P.ROLE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(waiter.getCounter());
            String warehouse = null;
            if (obj != null) {
                warehouse = obj.toString();
            }
            //praveen or sameer write a query to get the print categories from the waiter counter
            AppUser user = new AppUser(id, name, role, warehouse);
            //warehouse changes -end
            user.setTypeOfUser(1);
            cr.setData("");
            JPrincipalApp.m_approot.openAppView(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cardswiped(String custCard) {
        try {
            loadMemberDetails(custCard);
        } catch (HeadlessException ex) {
            Logger.getLogger(JIntroPageRestnum1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
}
