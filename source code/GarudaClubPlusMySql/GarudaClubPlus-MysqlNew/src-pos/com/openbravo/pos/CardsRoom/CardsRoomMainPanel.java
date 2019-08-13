/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CardsRoomMainPanel.java
 *
 * Created on 06-Feb-2010, 15:32:32
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class CardsRoomMainPanel extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    private AppView m_App;
    private DataLogicFacilities dlfac;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private List<GameLog> glist;
    private List<GameLog> glist1;
    private String[] Header=new String[]{"Date","Jack Name","Jack No","Game","No Of Players","Table Name"};
    private GameLog glog;
    private CardsRoomMain crMainObject;
    private int seletedGameIndex=-1;
    private CardsRoomMainPanel thisPanel;
     private List<TokenElements> pdtList=new ArrayList<TokenElements>();
      private List<TokenElements> finalList;
    public CardsRoomMainPanel() {
        initComponents();

    }
    public String getTitle() {
        return null;
    }
    public javax.swing.JPanel getPanel(){
      return jPanel3;
    }
    public void activate() throws BasicException {
        jTextField1.setText(null);
        jTextField2.setText(null);
        glist=dlfac.getActiveGameLog();
        if(glist==null){
          glist=new ArrayList<GameLog>();
        }
        jTable1.setModel(getTableModel());
        customerInfo=null;
        crMainObject=null;
        restorePanel();
    }
    public void restorePanel(){
        jPanel3.removeAll();
        jPanel3.add(jPanel1,BorderLayout.CENTER);
        jPanel3.add(jPanel2,BorderLayout.PAGE_START);
        jPanel3.revalidate();
        jPanel3.repaint();
    }

    public void restore(int flag) throws BasicException{
           restorePanel();
           /* if(flag==2 && crMainObject.getGameStatus()==0){
               glist.add(crMainObject.getGameInstance());
            }else if(flag==2 && crMainObject.getGameStatus()==1){
               glist.remove(seletedGameIndex);
               glist.add(seletedGameIndex,crMainObject.getGameInstance());
            }*/
            activate();

    }

    public boolean deactivate() {
        if(crMainObject==null)
            return true;
        else
       return crMainObject.deactivate();
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        dlfac=(DataLogicFacilities)m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        jLabel1.setText("Member No");
        jLabel2.setText("Member Name");
        jButton1.setText("Search");
        jButton2.setText("Edit");
        jButton3.setText("Create New");
        jButton4.setText("Game Completed");
        jButton5.setText("Request for Cancellation");
        jButton6.setText("Delete Game");
        jButton6.setVisible(false);
        //jPanel1.set
        
    }

    public Object getBean() {
        return this;
    }
    private AbstractTableModel getTableModel(){
      return new AbstractTableModel() {

            public int getRowCount() {
                return glist.size();
            }

            public int getColumnCount() {
                return Header.length;
            }

            @Override
            public String getColumnName(int column) {
                return Header[column];
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                GameLog g=glist.get(rowIndex);
                switch(columnIndex){
                    case 0  : return g.getCreatedDate();
                    case 1  : return g.getJackName();
                    case 2  : return g.getJackNo();
                    case 3  : return g.getGameName();
                    case 4  : return g.getNoOfPlayers();
                    case 5  : return g.getTableName();
                    default : return null;
                }
            }
        };
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setLayout(null);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("List Of Games Being Played"));
        jPanel1.setLayout(null);

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

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 590, 340);

        jPanel5.setLayout(null);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);
        jButton4.setBounds(0, 50, 120, 30);

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(0, 90, 120, 30);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);
        jButton2.setBounds(0, 10, 120, 30);

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(0, 130, 120, 30);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(610, 20, 120, 160);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("jLabel1");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel7.setLayout(null);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3);
        jButton3.setBounds(10, 0, 80, 23);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .add(18, 18, 18)
                .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jButton1))))
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 740, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 210, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        add(jPanel3);
        jPanel3.setBounds(0, 0, 740, 640);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                jTextField2.setText(customerInfo.toString());
                jTextField1.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // if(customerInfo!=null){
      int row=jTable1.getSelectedRow();
      if(row>=0){
        try {
            seletedGameIndex=row;
            crMainObject = new CardsRoomMain();
            crMainObject.init(m_App, 0);
            glog=dlfac.getGameInstanceByID(glist.get(row).getID());
            boolean flag =dlfac.checkForPendingCancelRequest(glog.getID());
            int cnt=dlfac.checkForIssuedTokens(glog.getID());
          if(!flag){
            if(glog!=null){
              glog.addPlayers(dlfac.getPlayersFromGameLogID(glog.getID()));
              customerInfo=dlCustomers.getCustomerByID(glog.getJackID());
              crMainObject.activate(this,customerInfo,glog,1);
              boolean status=true;
              if(cnt>0)
                  status=false;
              crMainObject.setValues(status);
              jPanel3.removeAll();
              jPanel3.add(crMainObject, BorderLayout.CENTER);
              jPanel3.revalidate();
            }else{
              JOptionPane.showMessageDialog(null, "The Selected Game Instance Is Already Completed", "Warning", JOptionPane.WARNING_MESSAGE);
              glist=dlfac.getActiveGameLog();
              jTable1.setModel(getTableModel());
            }
          }else{
                JOptionPane.showMessageDialog(null, "Pending request already exist", null, JOptionPane.WARNING_MESSAGE);
          }
            //glist.add(glog);
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_CAUTION, "Error Occured while Loading the panel", e));
            e.printStackTrace();
        }
      }
    // }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
       if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){

            try{
               Object[] obj= dlfac.getMamberbySkey(jTextField1.getText().toUpperCase());
            if(obj==null){
                JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            }else{
               customerInfo=new CustomerInfo(obj[0].toString());
               customerInfo.setName(obj[1].toString());
               customerInfo.setSearchkey(jTextField1.getText().toUpperCase());
               customerInfo.setMobile(String.valueOf(obj[3]));
               jTextField2.setText(obj[1].toString());
            }
           }
          catch(Exception e){
          }
        }else{
            jTextField2.setText(null);
            customerInfo=null;
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      /*  */
         if(customerInfo!=null){
        try {
            thisPanel=this;
            Boolean flag=dlfac.checkForGameLog(customerInfo.getId());
            if(flag==false)
             {
          //  boolean flag =dlfac.checkForPendingCancelRequest(glog.getID());
          //  if(!flag){
          Transaction t=new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                      try{
                        seletedGameIndex=-1;
                        crMainObject = new CardsRoomMain();
                        crMainObject.init(m_App, 0);
                        //String gameInstanceID=UUID.randomUUID().toString();
                        String crby=m_App.getAppUserView().getUser().getName();
                        Date crdate=new Date();
                        glog=new GameLog(customerInfo.getId(),customerInfo.getName(),customerInfo.getSearchkey(),crby,crdate);
                        glog.setNoOfPlayers(1);
                        
                        glog.addPlayer(new PlayeresData(customerInfo.getName(), customerInfo.getId(), new ArrayList<TokenElements>(), 0.0, null, "", customerInfo.getSearchkey()));
                        crMainObject.activate(thisPanel,customerInfo,glog,0);
                        jPanel3.removeAll();
                        jPanel3.add(crMainObject, BorderLayout.CENTER);
                        jPanel3.revalidate();
                      
                         return null;
                      }catch(Exception e){
                          e.printStackTrace();
                        throw new BasicException();
                      }
                    }
            };
            t.execute();
         //   }else{
          //      JOptionPane.showMessageDialog(null, "Pending request for cancellation  exist", null, JOptionPane.WARNING_MESSAGE);
         // }
              }
                        else{
                             JOptionPane.showMessageDialog(null, "Member Already playing","press ok to continue", JOptionPane.ERROR_MESSAGE);
                        }
            //glist.add(glog);
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_CAUTION, "Error Occured while Loading the panel", e));
            e.printStackTrace();
        }
     }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      int row=jTable1.getSelectedRow();
      if(row>-1){
       try{
       if(JOptionPane.showConfirmDialog(null, "Do you want to set the game as completed", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        GameLog glogobj=glist.get(row);
        glogobj=dlfac.getGameInstanceByID(glogobj.getID());
        if(glogobj.getPaymentRef()!=null){
          Object[] obj=new Object[]{new Date(),glogobj.getID()};
          dlfac.setGameInstanceAsCompleted(obj);
          //dlfac.deleteGameLog(glogobj.getID());
        }else{
            JOptionPane.showMessageDialog(null, "Please create the receipt", null, JOptionPane.WARNING_MESSAGE);
        }
       }
       }catch(Exception e){
       }
      }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      try{
        int row=jTable1.getSelectedRow();
        if(row>-1){
          GameLog glogobj=glist.get(row);
          glogobj=dlfac.getGameInstanceByID(glogobj.getID());
          boolean flag =dlfac.checkForPendingCancelRequest(glogobj.getID());
          if(!flag){
           if(JOptionPane.showConfirmDialog(null, "Do you want to send a request for cancellation", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
              Object[] obj=new Object[]{UUID.randomUUID().toString(),glogobj.getID(),m_App.getAppUserView().getUser().getName(),new Date(),0};
              dlfac.addCancelRequest(obj);
           }
          }else{
             JOptionPane.showMessageDialog(null, "Pending request already exist", null, JOptionPane.WARNING_MESSAGE);
          }
        }else{
          JOptionPane.showMessageDialog(null, "Select a row from the list", null, JOptionPane.WARNING_MESSAGE);
        }
      }catch(Exception e){
          e.printStackTrace();
      }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
     /*   try{
        int row=jTable1.getSelectedRow();
      if(row>-1){

       if(JOptionPane.showConfirmDialog(null, "Do you want to delete this game", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        GameLog glogobj=glist.get(row);
        Object[] obj=new Object[]{new Date(),glogobj.getID()};
        dlfac.deleteGame(obj);
        activate();
         }
       }
        else{
            JOptionPane.showMessageDialog(null, "Select a row from the list", null, JOptionPane.WARNING_MESSAGE);
        }
      }
        catch(Exception e){
       }*/
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables



}
