/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QTDeliveredList.java
 *
 * Created on Nov 20, 2012, 2:00:00 PM
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.ReceiptDetail;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
//import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
//import com.openbravo.possync.DataLogicIntegration;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author a
 */
public class QTDeliveredList extends javax.swing.JDialog {
   
    private DataLogicSystem m_dlSystem;
    private QTDTableModel qtdtmodel;
    private DataLogicSales dlSales;
    private DataLogicFacilities dlfac;
    private TicketParser m_TTP;
    private AppView m_App;
    /** Creates new form ChequeDetail */
    public QTDeliveredList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

     protected QTDeliveredList(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public QTDeliveredList(Frame parent,AppView app){
         super(parent,true);
  //       m_PaymentsToClose=m_Payments;
  //       List clines= m_PaymentsToClose.getChequeDetailLines();

         initComponents();
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         jTable1.getTableHeader().setReorderingAllowed(false);
         jTable1.setRowHeight(25);
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     }
     public QTDeliveredList(Dialog parent,AppView app){
         super(parent,true);
  //       m_PaymentsToClose=m_Payments;
         initComponents();
         jTable1.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[] {Formats.STRING, Formats.STRING, Formats.CURRENCY, Formats.STRING}));
         jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         jTable1.getTableHeader().setReorderingAllowed(false);
         jTable1.setRowHeight(25);
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     }

      protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

      public static QTDeliveredList getDialog(Component parent,AppView app) {
        Window window = getWindow(parent);
        QTDeliveredList cd;
        if (window instanceof Frame) {
            cd = new QTDeliveredList((Frame) window,app);
        } else {
            cd = new QTDeliveredList((Dialog) window,app);
        }
        return cd;
    }

       public  boolean showDialog(boolean status,String text) throws BasicException {
           m_App=LookupUtilityImpl.getInstance(null).getAppView();
           m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
           dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
           m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);
           dlfac=(DataLogicFacilities)m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
           
        
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
        
           
           displayModel(text,temparr);
           
  //         jTable1.setModel(tablemodel);
           jLabel1.setText(text);
           if(status==true){
               jComboBox1.setVisible(true);
             jButton1.setVisible(false);
             jTextField1.setVisible(false);
           }else{
               jComboBox1.setVisible(false);
               jButton1.setVisible(true);
               jTextField1.setVisible(true);
               jTextField1.setEditable(false);
           }
     //     TableColumnModel jColumns = jTable1.getColumnModel();
     //     jColumns.getColumn(0).setPreferredWidth(150);
      //    jColumns.getColumn(0).setResizable(false);
          this.setVisible(true);
          return true;
       }
       
       
       public final SentenceList getQtdInfo(String[] ct) {//in use
           SentenceList lst = null;
           for(int i=0;i<ct.length;i++){
             lst = new StaticSentence(m_App.getSession(), "SELECT distinct w.name FROM qtkitchen q join waiter w on q.waiter=w.id where q.counter = '"+ct[i]+"'", SerializerWriteString.INSTANCE, new SerializerReadClass(PeoplesRoleInfo.class));
           }
        return lst;
    }
       
       
       private void displayModel(String text,String[] temparr) throws BasicException {
           AppView App= LookupUtilityImpl.getInstance(null).getAppView();
           if(text.equals("Date")){
               
               Date d = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(d);
            String temp = s.substring(0,10);
               jTextField1.setText(Formats.DATE.formatValue(d));
               qtdtmodel = QTDTableModel.loadInstanceDate(App,temp,temparr);
             jTable1.setModel(qtdtmodel.getTableModel());
               
           }else if(text.equals("Floor")){
               
               Date d = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(d);
            String date = s.substring(0,10);
               qtdtmodel = QTDTableModel.loadInstanceFloorAll(App,date,temparr);
             jTable1.setModel(qtdtmodel.getTableModel());
               jComboBox1.setModel(new ComboBoxValModel(dlSales.getFloorsList().list()));
               
           }else if(text.equals("Counter")){
               Date d = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(d);
            String date = s.substring(0,10);
               qtdtmodel = QTDTableModel.loadInstanceCounterAll(App,date,temparr);
             jTable1.setModel(qtdtmodel.getTableModel());
               List l = Arrays.asList(temparr);
               jComboBox1.setModel(new ComboBoxValModel(l));
               
           }else if(text.equals("Waiter")){
               Date d = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(d);
            String date = s.substring(0,10);
               List l = new ArrayList();
               List lst = new ArrayList();
               for(int i=0;i<temparr.length;i++){
                l = dlSales.getQtdWInfo(temparr[i]).list(temparr[i]);
                lst.addAll(l);
               }
              qtdtmodel = QTDTableModel.loadInstanceWaiterAll(App,date,temparr);
             jTable1.setModel(qtdtmodel.getTableModel());
               jComboBox1.setModel(new ComboBoxValModel(lst));
               
           }else if(text.equals("Mem.No.")){
               
               Date d = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(d);
            String date = s.substring(0,10);
            List l = new ArrayList();
               List lst = new ArrayList();
               for(int i=0;i<temparr.length;i++){
                l = dlSales.getQtdMemInfo(temparr[i]).list(temparr[i]);
                lst.addAll(l);
               }
               qtdtmodel = QTDTableModel.loadInstanceMemNoAll(App,date,temparr);
             jTable1.setModel(qtdtmodel.getTableModel());
               jComboBox1.setModel(new ComboBoxValModel(lst));
           }
//        qtdtmodel = QTDTableModel.loadInstance(App);
//        jTable1.setModel(qtdtmodel.getTableModel());
       }
       
       
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl.No.", "MemNo", "Counter", "Waiter", "Floor", "Table", "QT Time", "CreatedBy", "PreparedTime", "DeliveredTime", "Reverse"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(5.0F);
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("QT Delivered List"); // NOI18N

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(501, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    try{
    Date date = null;
    AppView App = LookupUtilityImpl.getInstance(null).getAppView();
    date = JCalendarDialog.showCalendar(this, date);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String s = dateFormat.format(date);
   String temp = s.substring(0,10);
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
        if (date != null) {
            jTextField1.setText(Formats.DATE.formatValue(date));
        }
         if (jTextField1.getText().isEmpty() == true ) {
            JOptionPane.showMessageDialog(null, "Please Enter date");
        }else{
             
             qtdtmodel = QTDTableModel.loadInstanceDate(App,temp,temparr);
             jTable1.setModel(qtdtmodel.getTableModel());
                    
                    
             List dlist = new StaticSentence(m_App.getSession()
                ,"SELECT NAME FROM PEOPLE WHERE ROLE = (SELECT ID FROM ROLES WHERE NAME = '')"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).list();
             
         }
    }catch(Exception e){
        e.printStackTrace();
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
// TODO add your handling code here:
    String temp = jComboBox1.getSelectedItem().toString();
    AppView App = LookupUtilityImpl.getInstance(null).getAppView();
        try {
            if(temp != null){
                if(jLabel1.getText().equals("Floor")){
                    
              Object[]obj1 = (Object[]) new StaticSentence(m_App.getSession()
               ,"SELECT ID FROM FLOORS WHERE NAME = '"+temp+"'"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
              temp = obj1[0].toString();
              
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
                    
                    qtdtmodel = QTDTableModel.loadInstanceFloor(App,temp,temparr);
                    jTable1.setModel(qtdtmodel.getTableModel());
        
                }else if(jLabel1.getText().equals("Counter")){
                    
                    qtdtmodel = QTDTableModel.loadInstanceCounter(App,temp);
                    jTable1.setModel(qtdtmodel.getTableModel());
                    
                }else if(jLabel1.getText().equals("Waiter")){
                    
                    qtdtmodel = QTDTableModel.loadInstanceWaiter(App,temp);
                    jTable1.setModel(qtdtmodel.getTableModel());
                    
                }else if(jLabel1.getText().equals("Mem.No.")){
                    
                    qtdtmodel = QTDTableModel.loadInstanceMemNo(App,temp);
                    jTable1.setModel(qtdtmodel.getTableModel());
                    
                }
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_jComboBox1ItemStateChanged

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
