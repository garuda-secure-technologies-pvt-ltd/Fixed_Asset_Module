/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QTKitchen.java
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
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author a
 */
public class QTKitchen extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private DataLogicSales dlSales = null;
    private Socket s;
    private DataInputStream buf;
    private DataOutputStream out;
    private String ipaddr;
    private MyList lmodel;
    private String[] temparr;
    private BillLogic dlBill;
    private TicketInfo m_oTicket;
    private QTKitchenTableModel qtkmodel;
    private ArrayList<TicketInfo> m_ticketList;
    protected Qticket qTicket;
    private ComboBoxValModel m_counter;
    private AppView m_App;
    private AbstractTableModel tablemodel;
    public static boolean dflag = false;
    private Session session;


    public QTKitchen() {
        
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
       return "QT Details";
    }   

    public void activate() throws BasicException {
        
        loadCombo();
        
        loadData();
        
        refreshlists();
        
        jCheckBox1.setSelected(true);
        
        
         AppUser a = m_App.getAppUserView().getUser();
        String str = a.toString();
        Object[]ob = (Object[]) new StaticSentence(m_App.getSession()
               ,"SELECT NAME FROM ROLES WHERE ID = (SELECT ROLE FROM PEOPLE WHERE NAME = '"+str+"')"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        String strr = ob[0].toString();
        List lst = dlSales.getRestRoleInfo(strr).list(strr);
        Object o = lst.get(0);
        str = o.toString();
        temparr=str.split("\r\n");
        
      List<LoggedInUsers> obj = new ArrayList();
      List<LoggedInUsers> dlst = new ArrayList();
      for(int i=0;i<temparr.length;i++){
        dlst = new StaticSentence(m_App.getSession(),
                      "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL AND ROLE = (SELECT ID FROM ROLES WHERE NAME = '"+temparr[i]+"')"
                      ,null,new SerializerReadClass(LoggedInUsers.class)).list();
        obj.addAll(dlst);
      }
         if(obj.size()>0){
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            jButton8.setEnabled(true);
         }else
            jButton8.setEnabled(false);
         try{
            InetAddress iaddr=InetAddress.getLocalHost();
            ipaddr=iaddr.getHostAddress();
         }catch(Exception e){
            e.printStackTrace();
         }
        

    }
    
    
    private class MyList extends DefaultListModel{
        private List<LoggedInUsers> data;
        public MyList(List<LoggedInUsers> list){
            data=list;
        }
        @Override
        public Object getElementAt(int index) {
            LoggedInUsers obj=data.get(index);
            String value=null;
            if(obj!=null){
            if(ipaddr.equals(obj.getIPaddr()))
                value=obj.getName()+"           "+"-"+"Same Machine";
            else
                value=obj.getName()+"           "+"-"+obj.getHostName();
            }
	        return value;
        }
        @Override
        public int getSize(){
          return data.size();
        }

        @Override
        public Object get(int index) {
            return data.get(index);
        }

        @Override
        public Object remove(int index) {
            return data.remove(index);
        }
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
    //    m_counter = new ComboBoxValModel(dlSales.getRestRoleInfo(strr).list(strr));
        jComboBox1.setModel(new ComboBoxValModel(l));
 //       jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(temparr));
        jComboBox1.setEnabled(true);
        
    }
    
    private void loadData() throws BasicException {
        qtkmodel = QTKitchenTableModel.loadInstance(m_App);
        tablemodel = qtkmodel.getqtdetailsTableModel();
        jTable1.setModel(qtkmodel.getTableModel());
    //    jTable1.setModel(tablemodel);

    } 

    
    public void refreshQTModel() throws BasicException {
        ///
       qtkmodel = QTKitchenTableModel.loadInstance(m_App);
        jTable1.setModel(qtkmodel.getTableModel());
        
        refreshlists();
    }
    
   ////
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
        columnModel.getColumn(1).setMaxWidth(200);
        columnModel.getColumn(2).setPreferredWidth(140);
        columnModel.getColumn(2).setMaxWidth(100);
        columnModel.getColumn(3).setPreferredWidth(140);
        columnModel.getColumn(3).setMaxWidth(150);
        columnModel.getColumn(4).setPreferredWidth(90);
        columnModel.getColumn(4).setMaxWidth(140);
        columnModel.getColumn(5).setPreferredWidth(90);
        columnModel.getColumn(5).setMaxWidth(100);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(6).setMaxWidth(140);
        columnModel.getColumn(7).setPreferredWidth(120);
        columnModel.getColumn(7).setMaxWidth(140);
        columnModel.getColumn(8).setPreferredWidth(90);
        columnModel.getColumn(8).setMaxWidth(120);
        columnModel.getColumn(9).setPreferredWidth(50);
        columnModel.getColumn(9).setMaxWidth(50);
        columnModel.getColumn(10).setPreferredWidth(75);
        columnModel.getColumn(10).setMaxWidth(75);
        columnModel.getColumn(11).setPreferredWidth(75);
        columnModel.getColumn(11).setMaxWidth(75);

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
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        Activate_product_btn = new javax.swing.JButton();

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sl.No.", "MemNo", "Counter", "Waiter", "Floor", "Table", "QT Time", "CreatedBy", "Print", "Prepared", "Delivered", "QTID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(5.0F);
        jTable1.setRowHeight(23);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Counter");

        jCheckBox1.setText("All Counters of this Kitchen");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton1.setToolTipText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("QT Delivered List As Per:");

        jButton2.setText("Date");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Floor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Counter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Waiter");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Mem.No.");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jButton7.setText("Refresh List");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Send Message");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("All Restuarant counters");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        Activate_product_btn.setForeground(new java.awt.Color(237, 6, 6));
        Activate_product_btn.setText("Active / Deactive Product(s)");
        Activate_product_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Activate_product_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jButton4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)
                                .addGap(73, 73, 73)
                                .addComponent(jScrollPane2, 0, 430, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(68, 68, 68))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)
                        .addGap(59, 59, 59)
                        .addComponent(jButton1)
                        .addGap(33, 33, 33)
                        .addComponent(jCheckBox2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Activate_product_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jCheckBox2)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton6)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton5)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Activate_product_btn)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
 // TODO add your handling code here:
            String temp = jComboBox1.getSelectedItem().toString();
        try {
            if (temp != null) {
                
//                List lst = dlSales.getRoleList().list();
//                
//                List lst3 = dlSales.getPeoplesRoleInfo(temp).list(temp);
//                Object[]o3 = new Object[lst3.size()];
//                String[]s3 = new String[lst3.size()];
//                
//                for(int i=0;i<lst3.size();i++){
//                    o3[i] = (Object) lst3.get(i);
//                    System.out.print(o3[i]+"\t");
//                }
//                for(int i=0;i<lst3.size();i++){
//                    s3[i] = o3[i].toString();
//                }
//                
//                
//                
//                
//              Object[] obj = (Object[]) new StaticSentence(m_App.getSession()
//                ,"SELECT NAME FROM PEOPLE WHERE ROLE = (SELECT ID FROM ROLES WHERE NAME = ?)"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(temp);
//              
//              Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession()
//                ,"SELECT NAME FROM PEOPLE WHERE ROLE = (SELECT ID FROM ROLES WHERE NAME = '"+temp+"')"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
//              
//                List dlist2 = new StaticSentence(m_App.getSession()
//                ,"SELECT NAME FROM PEOPLE WHERE ROLE = (SELECT ID FROM ROLES WHERE NAME = '"+temp+"')"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).list();
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                qtkmodel = QTKitchenTableModel.loadInstance1(m_App,temp);
        tablemodel = qtkmodel.getqtdetailsTableModel();
        jTable1.setModel(qtkmodel.getTableModel());
        refreshlists();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jComboBox1ItemStateChanged

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    try {
        if((jComboBox1.getSelectedIndex()==-1)&&(jCheckBox1.isSelected()==false)){
            qtkmodel = QTKitchenTableModel.emptyinstance();
         jTable1.setModel(qtkmodel.getTableModel());
         return;
        }
    if(jComboBox1.getSelectedIndex()==-1){
            
          refreshQTModel();
            
    }else{
        String temp = jComboBox1.getSelectedItem().toString();
        qtkmodel = QTKitchenTableModel.loadInstance1(m_App,temp);
        tablemodel = qtkmodel.getqtdetailsTableModel();
        jTable1.setModel(qtkmodel.getTableModel());
        refreshlists();
     }
    jButton7ActionPerformed(null);
    }catch(BasicException ex){
             Logger.getLogger(QTKitchen.class.getName()).log(Level.SEVERE, null, ex);
       }
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    QTDeliveredList cd=QTDeliveredList.getDialog(this, m_App);
        try{
            boolean flag=  cd.showDialog(false,"Date");
        }catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    QTDeliveredList cd=QTDeliveredList.getDialog(this, m_App);
        try{
            boolean flag=  cd.showDialog(true,"Floor");
        }catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
    QTDeliveredList cd=QTDeliveredList.getDialog(this, m_App);
        try{
            boolean flag=  cd.showDialog(true,"Counter");
        }catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
    QTDeliveredList cd=QTDeliveredList.getDialog(this, m_App);
        try{
            boolean flag=  cd.showDialog(true,"Waiter");
        }catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// TODO add your handling code here:
    QTDeliveredList cd=QTDeliveredList.getDialog(this, m_App);
        try{
            boolean flag=  cd.showDialog(true,"Mem.No.");
        }catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton6ActionPerformed

private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
// TODO add your handling code here:
    try {

        jCheckBox2.setSelected(false);
        if(jCheckBox1.isSelected()==true){
         qtkmodel = QTKitchenTableModel.loadInstance(m_App);
         tablemodel = qtkmodel.getqtdetailsTableModel();
         jTable1.setModel(qtkmodel.getTableModel());
         refreshlists();
        
        loadCombo();
        
//        AppUser a = m_App.getAppUserView().getUser();
//        String str = a.toString();
//        m_counter = new ComboBoxValModel(dlSales.getRestRoleInfo(str).list(str));
//        jComboBox1.setModel(m_counter);
//        jComboBox1.setEnabled(true);
        
        }else{
            qtkmodel = QTKitchenTableModel.emptyinstance();
         jTable1.setModel(qtkmodel.getTableModel());
          //  jCheckBox1.setSelected(true);
        }
        } catch (BasicException ex) {
         Logger.getLogger(QTKitchen.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jCheckBox1ItemStateChanged

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// TODO add your handling code here:
    try{
         List<LoggedInUsers> obj = new ArrayList();
        List<LoggedInUsers> dlst = new ArrayList();
        for(int i=0;i<temparr.length;i++){
            dlst = new StaticSentence(m_App.getSession(),
                      "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL AND ROLE = (SELECT ID FROM ROLES WHERE NAME = '"+temparr[i]+"')"
                      ,null,new SerializerReadClass(LoggedInUsers.class)).list();
        obj.addAll(dlst);
      }
           if(obj.size()>0){
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            jButton8.setEnabled(true);
           }else{
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            jButton8.setEnabled(false);
           }
        }catch(Exception e){
          e.printStackTrace();
        }
}//GEN-LAST:event_jButton7ActionPerformed

private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// TODO add your handling code here:
     if(jList1.getModel().getSize()>0){
           int row=jList1.getSelectedIndex();
           if(row>=0){
               LoggedInUsers user=(LoggedInUsers)lmodel.get(row);
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
                  lmodel.remove(row);
                  jList1.setModel(lmodel);
                  jList1.repaint();
                    try {
                        new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET LOGINTIME=?,IPADDR=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.NULL,Datas.NULL,Datas.STRING})).exec(new Object[]{null,null,user.getid()});
                    } catch (BasicException ex) {
                        Logger.getLogger(QTKitchen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  JOptionPane.showMessageDialog(this, "The user might have improperly closed the application","The System is turned off",JOptionPane.INFORMATION_MESSAGE);
              }catch(ConnectException e){
                  lmodel.remove(row);
                  jList1.setModel(lmodel);
                  jList1.repaint();
                  try {
                        new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET LOGINTIME=?,IPADDR=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.NULL,Datas.NULL,Datas.STRING})).exec(new Object[]{null,null,user.getid()});
                    } catch (BasicException ex) {
                        Logger.getLogger(QTKitchen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  //this.revalidate();
                  JOptionPane.showMessageDialog(this, "The user might have improperly closed the application",null,JOptionPane.INFORMATION_MESSAGE);
              }catch(IOException e){
                  JOptionPane.showMessageDialog(this, "Error...");
                  e.printStackTrace();
              }
           }
        }
}//GEN-LAST:event_jButton8ActionPerformed

private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        try {
            // TODO add your handling code here:
            if(jCheckBox2.isSelected()){
                 jCheckBox1.setSelected(false);
                List l = dlSales.getRestAllInfo().list();
                qtkmodel = QTKitchenTableModel.loadInstance2(m_App,l);
         tablemodel = qtkmodel.getqtdetailsTableModel();
         jTable1.setModel(qtkmodel.getTableModel());
         refreshlists();
            }else{
                qtkmodel = QTKitchenTableModel.emptyinstance();
         jTable1.setModel(qtkmodel.getTableModel());
         return;
            }
                
        } catch (BasicException ex) {
            Logger.getLogger(QTKitchen.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void Activate_product_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Activate_product_btnActionPerformed
        ActivateKitchenProd prod;
        try {
            prod = ActivateKitchenProd.getDialog(this, m_App,true);
            prod.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(QTKitchen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Activate_product_btnActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Activate_product_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
