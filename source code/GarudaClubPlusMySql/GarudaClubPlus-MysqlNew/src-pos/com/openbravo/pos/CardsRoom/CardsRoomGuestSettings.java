/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CardsRoomGuestSettings.java
 *
 * Created on 15-Feb-2010, 13:42:15
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.pos.Accounts.ListModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.GuestCategory;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;

/**
 *
 * @author swathi
 */
public class CardsRoomGuestSettings extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form CardsRoomGuestSettings */
    private AppView m_App;
    private ComboBoxValModel guestModel;
    private DataLogicFacilities dlfac;
    private ListModel guestListModel;
    private List<GuestCategory> guestcatList;
    public CardsRoomGuestSettings() {
        initComponents();
    }

    public String getTitle() {
        return "Cards Room Settings";
    }

    public void activate() throws BasicException {

       getSelectGuestCatList();
       guestListModel=new ListModel(guestcatList);
       jList1.setModel(guestListModel);
       List<GuestCategory> glist=dlfac.getGuestCategory();
       for(GuestCategory gcat:guestcatList){
          for(GuestCategory ugcat:glist){
               if(gcat.getid().equals(ugcat.getid())){
                   glist.remove(ugcat);
                   break;
               }
          }
       }
       guestModel=new ComboBoxValModel(glist);
       jComboBox1.setModel(guestModel);
       jComboBox1.setSelectedIndex(-1);
    }
    private void getSelectGuestCatList() {
      try{
          guestcatList=new ArrayList<GuestCategory>();
      Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
      GeneralSettingInfo sinfo=gs.get("CardsRoomGuestCategories");
      String data=sinfo.getValue();
      
       if(data!=null){
          String[] arr=data.split("#:#");
          for(int i=0;i<arr.length;i++){
             guestcatList.add(dlfac.getGuestCategoryByID(arr[i]));
          }
      }
      }catch(Exception e){
      }
     // return guestcatList;
    }
    public boolean deactivate() {
        return true;
    }
    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        dlfac=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jLabel1.setText("Guest Categories");
        jLabel2.setText("Guest Category List");
        jButton1.setText("Add");
        jButton2.setText("Remove");
        jButton3.setText("Save");
    }

    public Object getBean() {
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("jLabel2");

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .add(28, 28, 28)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jComboBox1.getSelectedItem()!=null){
           GuestCategory gcat=(GuestCategory)jComboBox1.getSelectedItem();
           int status=0;
           for(GuestCategory gcat1:guestcatList){
               if(gcat.getid().equals(gcat1.getid())){
                   status=1;
                   break;
               }
           }
           if(status==0){
           guestListModel.addElement(jComboBox1.getSelectedItem());
           guestListModel=new ListModel(guestListModel.getList());
           jList1.setModel(guestListModel);
           //jList1.revalidate();
           }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row=jList1.getSelectedIndex();
        if(row>-1){
           guestListModel.removeElement(row);
           guestListModel=new ListModel(guestListModel.getList());
           jList1.setModel(guestListModel);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      try{
        String data=null;
        for(Object obj:guestListModel.getList()){
            GuestCategory gcat=(GuestCategory)obj;
            if(data==null)
               data=gcat.getid();
            else
                data+="#:#"+gcat.getid();
        }
        if(data!=null){
            dlfac.updateCardsRoomGuestCat("CardsRoomGuestCategories", data);
            LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
            //guestcatList=new
            //jList1.setModel();
        }
      }catch(Exception e){
        e.printStackTrace();
      }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables



}
