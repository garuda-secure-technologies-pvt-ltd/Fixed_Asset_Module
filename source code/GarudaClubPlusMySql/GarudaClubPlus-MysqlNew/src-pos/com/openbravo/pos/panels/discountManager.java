/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * discountManager.java
 *
 * Created on Jan 9, 2009, 6:42:13 PM
 */

package com.openbravo.pos.panels;
import com.openbravo.pos.sales.restaurant.QTList;
import com.openbravo.pos.sales.QTicketLineInfo;
import com.openbravo.pos.sales.Qticket;
import com.openbravo.pos.sales.QticketInfo;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.sales.QTicketLineInfo;
import com.openbravo.pos.sales.Qticket;
import com.openbravo.pos.ticket.ProductInfo;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class discountManager extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form discountManager */
     private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private DataLogicSales m_dlSales;
     private TicketParser m_TTP;

    private discountmodel m_dismodel = null;
     private Qticket qticket;
    public discountManager() {
        initComponents();
    }
     public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);

     /*   jTable1.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.INT, Formats.CURRENCY,Formats.CURRENCY,Formats.STRING,Formats.BOOLEAN}));
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         jTable1.getTableHeader().setReorderingAllowed(false);
         jTable1.setRowHeight(25);*/
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String user= LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();

             jButton1.setEnabled(false);
             jButton2.setEnabled(false);
             jButton1.setVisible(false);
             jButton2.setVisible(false);
             jButton3.setVisible(true);
             jButton3.setEnabled(true);
        boolean permtemp=(m_App.getAppUserView().getUser().hasPermission("discount.request"));
        if(user.equals("Administrator") || permtemp)
        {
           jButton1.setVisible(true);
           jButton2.setVisible(true);
           jButton1.setEnabled(true);
           jButton2.setEnabled(true);
           jButton3.setEnabled(false);
           jButton3.setVisible(false);

        }
        // jTable1.getColumn(8).setCellEditor();
         //jTable1.
         
     }
       public Object getBean() {
        return this;
    }
       
     public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return new Double(twoDForm.format(d)).doubleValue();
    }

    public JComponent getComponent() {
        return this;
    }

   public String getTitle() {
        return "Discount Requests";
    }    

    public void activate() throws BasicException {
        loadData();
    }

   public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }
     private void loadData() throws BasicException {
           String user= LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
          jButton1.setEnabled(false);
          jButton2.setEnabled(false);
          jButton1.setVisible(false);
          jButton2.setVisible(false);
          jButton3.setVisible(true);
          jButton3.setEnabled(true);
          boolean permtemp=(m_App.getAppUserView().getUser().hasPermission("discount.request"));
       if(user.equals("Administrator") || permtemp)
       {
           jButton1.setVisible(true);
            jButton2.setVisible(true);

           jButton1.setEnabled(true);
           jButton2.setEnabled(true);
            jButton3.setEnabled(false);
            jButton3.setVisible(false);

       }

           m_dismodel = discountmodel.loadInstance(m_App);
            jTable1.setModel(m_dismodel.getdiscountTableModel());

     }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "QtItemid", "customer name", "user name", "productid", "quantity", "rate", "amount", "reason", "Authorised", "Tax Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton1.setText("Allow");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reject");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      int r=  jTable1.getSelectedRow();
     String id= (String)m_dismodel.getdiscountTableModel().getValueAt(r, 0);
     String product=(String)m_dismodel.getdiscountTableModel().getValueAt(r, 10);
      String user=(String)m_dismodel.getdiscountTableModel().getValueAt(r, 2);
     Date dnow=new Date();
     String multiply= (m_dismodel.getdiscountTableModel().getValueAt(r, 4)).toString();
     String did=(m_dismodel.getdiscountTableModel().getValueAt(r, 9)).toString();
     Double dmultiply=Double.parseDouble(multiply);
     dmultiply*=-1;
     Double rate=(Double)m_dismodel.getdiscountTableModel().getValueAt(r, 5);
     String tempauth="true";
///aaa
     try{
        String cname=(String)m_dismodel.getdiscountTableModel().getValueAt(r, 1);
         Double damt = (Double)m_dismodel.getdiscountTableModel().getValueAt(r, 6);
         roundTwoDecimals(damt);
         Double Taxtotal = (Double)m_dismodel.getdiscountTableModel().getValueAt(r, 11);
         String CustomerID = (String) m_dismodel.getdiscountTableModel().getValueAt(r, 12);
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
         
         String BMName="Credit Check for billing member";
         String BName2 = "Facility for billing member"; 
         
         Object[] FacObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(BMName);
         if(FacObj!=null){
             Boolean v5 = (Boolean)FacObj[0];
             if(v5){
                 
                 
                  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
                        Object[] objid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM customers where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(cname);
                        if(objid!=null){
                               String idt=objid[0].toString();
                            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CustomerID);
                               String popb=obj[0].toString();
                                  Double POPB = new Double(popb);
                                  roundTwoDecimals(POPB);
                                  Double COPB = POPB-(damt+Taxtotal);
                                  COPB=roundTwoDecimals(COPB);
                             new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,CustomerID});
                        }else{
                            String a1 = cname.substring(0, cname.length()-9);
                       Object[] objidg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM customers where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(a1);
                         String idt=objidg[0].toString();
                         String ag = cname.substring(cname.length()-7);
                               ag = idt + "#" +ag;
                            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CustomerID);
                               String popb=obj[0].toString();
                                  Double POPB = new Double(popb);
                                  roundTwoDecimals(POPB);
                                  Double COPB = POPB-(damt+Taxtotal);
                                  COPB=roundTwoDecimals(COPB);
                             new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,CustomerID});
                        }
         
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
                 
                 
                 
                 
                 
                 
                 
             }
             else{
                 
                             Object[] Fac2Obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BName2);
                             String FacStrFull  = Fac2Obj[0].toString();
                             String []strarr = FacStrFull.split("#");
                             String FinFacId = strarr[0];
                
                             
                             
                             Object[] QtFacIdObj = (Object[]) new StaticSentence(m_App.getSession(), "select f.id  from facility f , locations l , qticket q  where l.facility=f.id and q.warehouse=l.id and q.id= ?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);
                             String QtFacID  = QtFacIdObj[0].toString();
                             
                             if(FinFacId.equals(QtFacID)){
                                
                                 
                                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
                                                    Object[] objid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM customers where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(cname);
                                                    if(objid!=null){
                                                           String idt=objid[0].toString();
                                                        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CustomerID);
                                                           String popb=obj[0].toString();
                                                              Double POPB = new Double(popb);
                                                              roundTwoDecimals(POPB);
                                                              Double COPB = POPB-(damt+Taxtotal);
                                                              COPB=roundTwoDecimals(COPB);
                                                         new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,CustomerID});
                                                    }else{
                                                        String a1 = cname.substring(0, cname.length()-9);
                                                   Object[] objidg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM customers where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(cname);
                                                     String idt=objidg[0].toString();
                                                     String ag = cname.substring(cname.length()-7);
                                                           ag = idt + "#" +ag;
                                                        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CustomerID);
                                                           String popb=obj[0].toString();
                                                              Double POPB = new Double(popb);
                                                              roundTwoDecimals(POPB);
                                                              Double COPB = POPB-(damt+Taxtotal);
                                                              COPB=roundTwoDecimals(COPB);
                                                         new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,CustomerID});
                                                    }
         
                                              ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
                                 
                               }
                 
             }
             
         }
         else{
             
             
             
             /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
                        Object[] objid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM customers where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(cname);
                        if(objid!=null){
                               String idt=objid[0].toString();
                            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CustomerID);
                               String popb=obj[0].toString();
                                  Double POPB = new Double(popb);
                                  roundTwoDecimals(POPB);
                                  Double COPB = POPB-(damt+Taxtotal);
                                  COPB=roundTwoDecimals(COPB);
                             new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,CustomerID});
                        }else{
                            String a1 = cname.substring(0, cname.length()-9);
                       Object[] objidg = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM customers where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(a1);
                         String idt=objidg[0].toString();
                         String ag = cname.substring(cname.length()-7);
                               ag = idt + "#" +ag;
                            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CustomerID);
                               String popb=obj[0].toString();
                                  Double POPB = new Double(popb);
                                  roundTwoDecimals(POPB);
                                  Double COPB = POPB-(damt+Taxtotal);
                                  COPB=roundTwoDecimals(COPB);
                             new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,CustomerID});
                        }
         
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
         
        }
         
         
         
   //
         
         
///aaa                  
        int count=new StaticSentence(m_App.getSession()
                        , "UPDATE DISCOUNTLIST SET AUTHORISED = ?,CRDATE=?,WAREHOUSE=? , AUTHORISEDBY=? , AUTHORISEDDATE=? , AUTHORISEDHOST =? WHERE ID=? AND AUTHORISED IS NULL"
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.TIMESTAMP,Datas.STRING, Datas.STRING ,Datas.TIMESTAMP , Datas.STRING ,Datas.STRING }))
                        .exec(new Object[] {tempauth,dnow,m_App.getAppUserView().getUser().getWarehouse() , m_App.getAppUserView().getUser().getName() , new Date() , m_App.getProperties().getHost() , did });
        if(count>0){
        qticket = (Qticket) m_App.getBean("com.openbravo.pos.sales.Qticket");
        qticket.setAppView(m_App);
        List<QTicketLineInfo> qtlines = qticket.getQTicketLineList(id);

        int noOfQtItems=qtlines.size();
        //noOfQtItems++;
        String idtemp=java.util.UUID.randomUUID().toString();
        Object[] values = new Object[] {idtemp,noOfQtItems,id,product,dmultiply,rate};
        Datas[] datas = new Datas[] {Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE};
        new PreparedSentence(m_App.getSession()
                , "INSERT INTO QTITEMS (ID,LINE,PARENTID,PRODUCT,DMULTIPLY,RATE) VALUES (?,?,?,?,?,?)"
                , new SerializerWriteBasicExt(datas, new int[] {0,1, 2, 3, 4, 5})).exec(values);
          m_dlSales.updateStockVolume1((dmultiply * -1), product);
          loadData();
        }
     }
     catch(BasicException e){
        e.printStackTrace();
     }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
     //   discountrow();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
     int r=  jTable1.getSelectedRow();
     String id= (String)m_dismodel.getdiscountTableModel().getValueAt(r, 0);
     String product=(String)m_dismodel.getdiscountTableModel().getValueAt(r, 10);
     String multiply= (m_dismodel.getdiscountTableModel().getValueAt(r, 4)).toString();
     int temp=Integer.parseInt(multiply);
     Double dmultiply=Double.parseDouble(multiply);
     Double rate=(Double)m_dismodel.getdiscountTableModel().getValueAt(r, 5);
     String did=(m_dismodel.getdiscountTableModel().getValueAt(r, 9)).toString();
     String tempauth="false";
     try{
       new StaticSentence(m_App.getSession()
                        , "UPDATE DISCOUNTLIST SET AUTHORISED = ? WHERE ID=? AND AUTHORISED IS NULL"
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.STRING}))
                        .exec(new Object[] {tempauth,did});
         loadData();
     }
     catch (BasicException e)
     {
        e.printStackTrace();
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
     int r=  jTable1.getSelectedRow();
     String temp="";
     String id= (String)m_dismodel.getdiscountTableModel().getValueAt(r, 0);
      String product=(String)m_dismodel.getdiscountTableModel().getValueAt(r, 10);
      try{
       new StaticSentence(m_App.getSession()
                        , " DELETE FROM DISCOUNTLIST  WHERE QTITEMID = ? AND PRODUCT_ID =? AND (AUTHORISED = ? OR AUTHORISED IS NULL) AND USER_ID=? AND AUTHORISED IS NULL "
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING }))
                        .exec(new Object[] {id,product,temp,m_App.getAppUserView().getUser().getName()});
         loadData();
     }
      catch(BasicException e){
          e.printStackTrace();
      }

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

