/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MembersFacility.java
 *
 * Created on Jul 2, 2009, 3:01:09 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class MemberFacilityManger extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    //implements JPanelView,BeanFactoryApp
    /** Creates new form MembersFacility */
    private DataLogicCustomers dlCustomers;
    private DataLogicFacilities dmang;
    private CustomerInfo customerInfo;
    private MembersFacilityTableModel mfmodel;
    private AppView m_App;
    private TableColumnModel cmodel;
    private FacilityListModel flmodel;
    public MemberFacilityManger() throws BasicException {
        initComponents();
       // activate();
    }
     public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
   //    customerInfo=new CustomerInfo();
   //    AppUser user=m_App.getAppUserView().getUser();
  //     customerInfo.setId(user.getMemid());
     /*  load.setEnabled(false);
       mfmodel=MembersFacilityTableModel.emptyinstance();
       jTable1.setModel(mfmodel.getTableModel());
       cmodel=jTable1.getColumnModel();
       cmodel.getColumn(0).setMinWidth(70);
       cmodel.getColumn(0).setMaxWidth(100);
       cmodel.getColumn(1).setMinWidth(20);
       cmodel.getColumn(1).setMinWidth(30);
       flmodel=new FacilityListModel(new ArrayList<MembersFacilityTableModel.Facilityline>());
       jList1.setModel(flmodel);*/
      // loadData();
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       m_App=app;
       dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
       dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       jLabel1.setText("Member No");
       jLabel2.setText("Member Name");
       jButton3.setText("Search");
       jButton4.setText("Load Data");
       mfmodel=MembersFacilityTableModel.emptyinstance();
       jTable1.setModel(mfmodel.getTableModel());
    }

    public Object getBean() {
        return this;
    }

     private class FacilityListModel extends AbstractListModel {
        private java.util.List<MembersFacilityTableModel.Facilityline> flist;
        public FacilityListModel(java.util.List<MembersFacilityTableModel.Facilityline> flist) {
            this.flist = flist;
        }
        public int getSize() {
            return flist.size();
        }
        public Object getElementAt(int i) {
            return flist.get(i);
        }
        public void remove(int i){
             flist.remove(i);
        }

    }
     private void loadData(){
        try{
         load.setEnabled(false);
         //mfmodel=MembersFacilityTableModel.emptyinstance();
        // jTable1.setModel(mfmodel.getTableModel());
        /* cmodel=jTable1.getColumnModel();
         cmodel.getColumn(0).setMinWidth(70);
         cmodel.getColumn(0).setMaxWidth(100);
         cmodel.getColumn(1).setMinWidth(20);
         cmodel.getColumn(1).setMinWidth(30);*/
        // flmodel=new FacilityListModel(new ArrayList<MembersFacilityTableModel.Facilityline>());
       //  jList1.setModel(flmodel);
         mfmodel=MembersFacilityTableModel.loadInstance(m_App, customerInfo.getId());
         jTable1.setModel(mfmodel.getTableModel());
         List<MembersFacilityTableModel.Facilityline> faclist=dmang.getOtherFacility().list(customerInfo.getId());
         flmodel=new FacilityListModel(faclist);
         jList1.setModel(flmodel);
        }catch(Exception e){
          e.printStackTrace();
        }
     }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        load = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new JList(){
            public String getToolTipText(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (-1 < index) {
                    Object item = getModel().getElementAt(index);
                    return String.valueOf(item);
                } else {
                    return null;
                }
            }
        };
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setLayout(null);

        load.setText("Load");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });
        add(load);
        load.setBounds(470, 350, 90, 23);

        jLabel3.setText("Facilities Taken");
        add(jLabel3);
        jLabel3.setBounds(0, 110, 110, 14);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 140, 310, 200);

        jLabel5.setText("Other Available Facilities :");
        add(jLabel5);
        jLabel5.setBounds(360, 110, 160, 14);

        jButton2.setText("Discontinue");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(190, 350, 120, 23);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(jList1);

        add(jScrollPane2);
        jScrollPane2.setBounds(360, 140, 300, 200);

        jButton3.setText("Opt.");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(570, 350, 90, 23);
        add(jSeparator1);
        jSeparator1.setBounds(360, 130, 300, 10);
        add(jSeparator2);
        jSeparator2.setBounds(0, 130, 310, 10);

        jLabel1.setText("jLabel1");
        add(jLabel1);
        jLabel1.setBounds(10, 30, 80, 14);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        add(jTextField1);
        jTextField1.setBounds(100, 30, 140, 20);

        jLabel2.setText("jLabel2");
        add(jLabel2);
        jLabel2.setBounds(260, 30, 100, 14);
        add(jTextField2);
        jTextField2.setBounds(370, 30, 210, 20);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(590, 30, 80, 23);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(503, 70, 170, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        // TODO add your handling code here:
        //if(editIndicator.isVisible()==false)
           loadData();
      //  else
        //   JOptionPane.showMessageDialog(this, "Please press enter after typing the mem no", "Press Enter", JOptionPane.OK_OPTION);
}//GEN-LAST:event_loadActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(evt.getClickCount()>1){
           try{
          int row=jTable1.getSelectedRow();
          if(row>=0){
             FacilityDetailDialog f=FacilityDetailDialog.getDialog(this, m_App);

             MembersFacilityTableModel.Facilityline fac=mfmodel.getfacilityline().get(row);
             //Periodicity p=dmang.getPerioicitybyid(fac.getRperiodId());
             Object status=mfmodel.getTableModel().getValueAt(row, 2);
              if(status!=null)
                f.setTitle(status.toString());
             f.showDialog(fac,row,customerInfo,dmang);
             loadData();
          }else{
            JOptionPane.showMessageDialog(this, "Please select a facility to view the detail", null, JOptionPane.OK_OPTION);
         }
        }catch(Exception e){
           e.printStackTrace();
        }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
          int row=jTable1.getSelectedRow();
          if(row>=0){
             FacilityDetailDialog f=FacilityDetailDialog.getDialog(this, m_App);
             MembersFacilityTableModel.Facilityline fac=mfmodel.getfacilityline().get(row);
             Object status=mfmodel.getTableModel().getValueAt(row, 2);
              if(status!=null)
                f.setTitle(status.toString());
             f.showDialog(fac,row,customerInfo,dmang);
             loadData();
          }else{
            JOptionPane.showMessageDialog(this, "Please select a facility to view the detail", null, JOptionPane.OK_OPTION);
         }
        }catch(Exception e){
           e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
          int row=jList1.getSelectedIndex();
          if(row>=0){
             FacilityDetailDialog f=FacilityDetailDialog.getDialog(this, m_App);
             MembersFacilityTableModel.Facilityline fac=(MembersFacilityTableModel.Facilityline)flmodel.getElementAt(row);
             f.showDialog1(fac,row,customerInfo,dmang);
             loadData();
          }
        }catch(Exception e){
          e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
          try{
               Object[] obj= dmang.getMamberbySkey(jTextField1.getText().toUpperCase());

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                jTextField2.setText(customerInfo.toString());
                jTextField1.setText(customerInfo.getSearchkey());
                //load();
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadData();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton load;
    // End of variables declaration//GEN-END:variables



}
