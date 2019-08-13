/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * KitchenStatus.java
 *
 * Created on Oct 10, 2012, 11:08:50 AM
 */
package com.openbravo.pos.sales;

import com.openbravo.pos.ticket.TicketInfo;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.openbravo.pos.forms.*;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Networking.LoggedInUsers;
import com.openbravo.pos.Networking.NewWindow;
import com.openbravo.pos.Networking.SocketInfo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author a
 */
public class KitchenStatus extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private DataLogicSales dlSales = null;
    private Socket s;
    private DataInputStream buf;
    private DataOutputStream out;
    private BillLogic dlBill;
    private TicketInfo m_oTicket;
    private KitchenStatusTableModel qtkmodel;
    private ArrayList<TicketInfo> m_ticketList;
    protected Qticket qTicket;
    private ComboBoxValModel m_counter;
    private AppView m_App;
    private AbstractTableModel tablemodel;
    public static boolean dflag = false;
    private Session session;


    public KitchenStatus() {
        
        initComponents();

        refreshlists();
        jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }
    
    
    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        qTicket = (Qticket) m_App.getBean("com.openbravo.pos.sales.Qticket");
        qTicket.setDataLogicSales(dlSales);
   //     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        qTicket.setAppView(m_App);
    }
    
    
    public Object getBean() {
        return this;
    }
    
    
    public JComponent getComponent() {
        return this;
    }
    
    public String getTitle() {
       return "Kitchen Status";
    }   

    public void activate() throws BasicException {
        
   //     loadCombo();
        
        loadData();
        
        refreshlists();
        
        AppView App = LookupUtilityImpl.getInstance(null).getAppView();
        String rl = App.getAppUserView().getUser().getRole();
         Object[]objr = (Object[]) new StaticSentence(App.getSession()
           ,"SELECT NAME FROM ROLES WHERE ID = '"+rl+"'"
           ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
         
         Object[]obr = (Object[]) new StaticSentence(App.getSession()
           ,"SELECT NAME FROM PEOPLE WHERE ROLE = (SELECT ID FROM QTKASSIGN WHERE RCOUNTERS LIKE '%"+objr[0].toString()+"%')"
           ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
         
         jTextField1.setText(obr[0].toString());
         jTextField1.setEditable(false);
         

    }

    public boolean deactivate() {

        return true;
    }
    
    
    public void loadCombo() throws BasicException {
        AppUser a = m_App.getAppUserView().getUser();
        String str = a.toString();
        Object[]obj = (Object[]) new StaticSentence(m_App.getSession()
               ,"SELECT NAME FROM ROLES WHERE ID = (SELECT ROLE FROM PEOPLE WHERE NAME = '"+str+"')"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        String strr = obj[0].toString();
        List lst = dlSales.getRestRoleInfo(strr).list(strr);
        Object o = lst.get(0);
        str = o.toString();
        String[] temparr=str.split("\r\n");
        List l = Arrays.asList(temparr);
        
    }
    
    private void loadData() throws BasicException {
        qtkmodel = KitchenStatusTableModel.loadInstance(m_App);
        tablemodel = qtkmodel.getqtdetailsTableModel();
        jTable1.setModel(qtkmodel.getTableModel());
    //    jTable1.setModel(tablemodel);

    } 

    
    public void refreshQTModel() throws BasicException {
        ///added on 18th
       qtkmodel = KitchenStatusTableModel.loadInstance(m_App);
        tablemodel = qtkmodel.getqtdetailsTableModel();
        jTable1.setModel(qtkmodel.getTableModel());
        
        refreshlists();
    }
    
   ////have to see here
    public void qtReprint(String cust){
        QTKPrint qtPrint = QTKPrint.getDialog(this, m_App, dlSales, qTicket);
        try {
            boolean flag = qtPrint.showDialog(m_App,cust);
        } catch (BasicException ex) {
            new MessageInf(ex).show(this);
        }
    }
 /////////////////////////////   
    

    private void refreshlists() {
        
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(0).setMaxWidth(60);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(1).setMaxWidth(100);
        columnModel.getColumn(2).setPreferredWidth(140);
        columnModel.getColumn(2).setMaxWidth(150);
        columnModel.getColumn(3).setPreferredWidth(90);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(4).setPreferredWidth(90);
        columnModel.getColumn(4).setMaxWidth(100);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(5).setMaxWidth(100);
        columnModel.getColumn(6).setPreferredWidth(140);
        columnModel.getColumn(6).setMaxWidth(140);
        columnModel.getColumn(7).setPreferredWidth(90);
        columnModel.getColumn(7).setMaxWidth(120);
        columnModel.getColumn(8).setPreferredWidth(140);
        columnModel.getColumn(8).setMaxWidth(140);

    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this metho
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 13));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sl.No.", "MemNo", "Counter", "Waiter", "Floor", "Table", "QT Time", "CreatedBy", "PreparedTime", "DeliveredTime"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(5.0F);
        jTable1.setRowHeight(18);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton1.setToolTipText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Send Message To Kitchen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    try {
                   
                    qtkmodel = KitchenStatusTableModel.loadInstance(m_App);
                    tablemodel = qtkmodel.getqtdetailsTableModel();
                    jTable1.setModel(qtkmodel.getTableModel());
                    refreshlists();
        } catch (BasicException ex) {
            Logger.getLogger(KitchenStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
     
   
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    try {
        
        AppView App = LookupUtilityImpl.getInstance(null).getAppView();
        String rl = App.getAppUserView().getUser().getRole();
         Object[]objr = (Object[]) new StaticSentence(App.getSession()
           ,"SELECT NAME FROM ROLES WHERE ID = '"+rl+"'"
           ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
         
         Object[]obr = (Object[]) new StaticSentence(App.getSession()
           ,"SELECT ID FROM QTKASSIGN WHERE RCOUNTERS LIKE '%"+objr[0].toString()+"%'"
           ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        
            List<LoggedInUsers> objl= new StaticSentence(m_App.getSession(),
            "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL AND ROLE = '"+obr[0].toString()+"'"
            ,null,new SerializerReadClass(LoggedInUsers.class)).list();
        
    if(objl.size()>0){
           
               LoggedInUsers user=(LoggedInUsers)objl.get(0);
              try{
                  boolean flag=false;
                  if(JRootApp.socketList.containsKey(user.getName())){
                        SocketInfo sinfo=JRootApp.socketList.get(user.getName());
                        s=sinfo.getSocket();
                        flag=true;
                    }else{
                      s=new Socket(user.getIPaddr(),Integer.parseInt(user.getSocketNo()));

                    }
                  buf=new DataInputStream(s.getInputStream());
                  out=new DataOutputStream(s.getOutputStream());
                  String uname=m_App.getAppUserView().getUser().getName();
                  if(flag==false)
                   out.writeUTF(uname);
                  NewWindow obj=new NewWindow(s, buf, out,uname,user.getName(),flag);
                  Thread t=new Thread(obj);
                  t.start();
                  if(flag==false){
                   SocketInfo sinfo=new SocketInfo();
                   sinfo.setSocket(s);
                   sinfo.setMsgDialog(obj.getdialog());
                   JRootApp.socketList.put(user.getName(), sinfo);
                  }
              }catch(UnknownHostException e){
                  
                    try {
                        new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET LOGINTIME=?,IPADDR=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.NULL,Datas.NULL,Datas.STRING})).exec(new Object[]{null,null,user.getid()});
                    } catch (BasicException ex) {
                        Logger.getLogger(KitchenStatus.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  JOptionPane.showMessageDialog(this, "The user might have improperly closed the application","The System is turned off",JOptionPane.INFORMATION_MESSAGE);
              }catch(ConnectException e){
                  
                  try {
                        new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET LOGINTIME=?,IPADDR=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.NULL,Datas.NULL,Datas.STRING})).exec(new Object[]{null,null,user.getid()});
                    } catch (BasicException ex) {
                        Logger.getLogger(KitchenStatus.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 // this.revalidate();
                  JOptionPane.showMessageDialog(this, "The user might have improperly closed the application",null,JOptionPane.INFORMATION_MESSAGE);
              }catch(IOException e){
                  JOptionPane.showMessageDialog(this, "Error...");
                  e.printStackTrace();
              }
        }else{
        JOptionPane.showMessageDialog(this, "The Kitchen user has not logged in",null,JOptionPane.INFORMATION_MESSAGE);
    }
    } catch (BasicException ex) {
            Logger.getLogger(KitchenStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButton2ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
