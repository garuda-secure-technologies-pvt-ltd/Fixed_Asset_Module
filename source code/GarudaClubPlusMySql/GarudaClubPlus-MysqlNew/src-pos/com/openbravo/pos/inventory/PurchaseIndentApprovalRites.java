/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchaseIndentApprovalRites.java
 *
 * Created on 21-Oct-2011, 10:31:43
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.ticket.CategoryInfo;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class PurchaseIndentApprovalRites extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicSales m_dlSales;
    private ComboBoxValModel warehouseModel;
    private ComboBoxValModel forwarderModel;
    private ComboBoxValModel approverModel;
    private ComboBoxValModel initiaterModel;
    private Object[] obj;
    private String lid;
    private String fid ;
    private String aid;
    private String iid;
   CategoryInfo piar;

    public PurchaseIndentApprovalRites() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        save = new javax.swing.JButton();
        warehouse = new javax.swing.JComboBox();
        forwarder = new javax.swing.JComboBox();
        approver = new javax.swing.JComboBox();
        initiater = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Warehouse");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Forwarder");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Approver");
        jLabel3.setName("jLabel3"); // NOI18N

        edit.setText("Edit");
        edit.setName("edit"); // NOI18N
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.setName("save"); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        warehouse.setName("warehouse"); // NOI18N
        warehouse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                warehouseItemStateChanged(evt);
            }
        });

        forwarder.setName("forwarder"); // NOI18N

        approver.setName("approver"); // NOI18N

        initiater.setName("initiater"); // NOI18N

        jLabel4.setText("Initiater");
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(49, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(warehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(approver, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(initiater, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(forwarder, javax.swing.GroupLayout.Alignment.TRAILING, 0, 118, Short.MAX_VALUE)))
                                .addGap(76, 76, 76))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(edit)
                        .addGap(18, 18, 18)
                        .addComponent(save)))
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(warehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initiater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(forwarder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(approver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit)
                    .addComponent(save))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        try {
            // TODO add your handling code here:
            
            forwarderModel = new ComboBoxValModel(m_dlSales.getPeopleList1());
            forwarder.setModel(forwarderModel);
            approverModel = new ComboBoxValModel(m_dlSales.getPeopleList1());
            approver.setModel(approverModel);
            initiaterModel=new ComboBoxValModel(m_dlSales.getPeopleList1());
            initiater.setModel(initiaterModel);

            load();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_editActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        try {
            if(warehouse.getSelectedIndex()!=-1 && forwarder.getSelectedIndex()!=-1 && approver.getSelectedIndex()!=-1 &&  initiater.getSelectedIndex()!=-1){
            Transaction t = new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {

                    CategoryInfo l = (CategoryInfo) warehouse.getSelectedItem();
                     lid = l.getID();
                    RoleInfo rf = (RoleInfo) forwarder.getSelectedItem();
                    fid = rf.getID();
                    RoleInfo ra = (RoleInfo) approver.getSelectedItem();
                   aid = ra.getID();
                    RoleInfo ri = (RoleInfo) initiater.getSelectedItem();
                     iid = ri.getID();
                    

                    String res = insertIntoPurchaseIndentApprovalRites(lid, fid, aid,iid);
                    if ("success".equals(res)) {
                        JOptionPane.showMessageDialog(null, "Saved Successfully");
                       saved();
                    }
                    return null;
                }
            };

            t.execute();
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Select all the Fileds", null, JOptionPane.OK_OPTION);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Saved Successfully");
            e.printStackTrace();

        }


    }//GEN-LAST:event_saveActionPerformed

    public String insertIntoPurchaseIndentApprovalRites(String lid, String fid, String aid,String iid) {

        String msz = null;


        try {
            Object[] ob=(Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM PURCHASEINDENTAPPROVALRITES WHERE LOCATION=? AND FLAG=TRUE",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(piar.getID());
            if(ob==null){
            String id = UUID.randomUUID().toString();
            
            new PreparedSentence(m_App.getSession(), "INSERT INTO PURCHASEINDENTAPPROVALRITES(ID,LOCATION,FORWARDER,APPROVER,INITIATER,FLAG) VALUES (?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.BOOLEAN})).exec(new Object[]{id, lid, fid,aid,iid,true});
            msz = "success";
            
            }
            else{
                new StaticSentence(m_App.getSession(),"UPDATE PURCHASEINDENTAPPROVALRITES SET FORWARDER=?,APPROVER=?,INITIATER=? WHERE ID=?",new SerializerWriteBasic(new Datas[]{ Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING})).exec(new Object[]{fid, aid, iid,ob[0]});
                msz = "success";

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msz;

    }
    private void warehouseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_warehouseItemStateChanged
        // TODO add your handling code here:
        try{
            
        if (warehouse.getSelectedIndex() != -1) {
            piar=(CategoryInfo)warehouse.getSelectedItem();
           load();
           
             obj =  (Object[]) new StaticSentence(m_App.getSession(), "SELECT P.ID,P.FORWARDER,P.APPROVER,P.INITIATER FROM PURCHASEINDENTAPPROVALRITES P,ROLES R,ROLES R1,ROLES R2 WHERE P.LOCATION=? AND P.APPROVER=R1.ID AND P.FORWARDER=R.ID AND P.INITIATER=R2.ID AND P.FLAG=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).find(piar.getID());
            if(obj==null){
                forwarderModel = new ComboBoxValModel(m_dlSales.getPeopleList1());
                forwarder.setModel(forwarderModel);
                approverModel = new ComboBoxValModel(m_dlSales.getPeopleList1());
                approver.setModel(approverModel);
                initiaterModel=new ComboBoxValModel(m_dlSales.getPeopleList1());
                initiater.setModel(initiaterModel);
            }
            else{
                forwarderModel.setSelectedKey(obj[1]);
                approverModel.setSelectedKey(obj[2]);
                initiaterModel.setSelectedKey(obj[3]);
                saved();
            }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_warehouseItemStateChanged

    private void saved(){
             forwarder.setEnabled(false);
             approver.setEnabled(false);
             initiater.setEnabled(false);
             edit.setEnabled(true);
             save.setEnabled(false);
    }

    private void load(){
        forwarder.setEnabled(true);
        approver.setEnabled(true);
        warehouse.setEnabled(true);
        initiater.setEnabled(true);
        edit.setEnabled(false);
        save.setEnabled(true);

    }
    public String getTitle() {
        return "Purchase Indent Approval Rights";

    }

    public void activate() throws BasicException {
        warehouseModel = new ComboBoxValModel(m_dlSales.getMainWarehouses());
        warehouse.setModel(warehouseModel);
       forwarderModel = new ComboBoxValModel(m_dlSales.getPeopleList1());
       forwarder.setModel(forwarderModel);
       approverModel = new ComboBoxValModel(m_dlSales.getPeopleList1());
        approver.setModel(approverModel);
        initiaterModel=new ComboBoxValModel(m_dlSales.getPeopleList1());
        initiater.setModel(initiaterModel);

        load();
        
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        
            m_App = app;
            m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate"); 
    }

    public Object getBean() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox approver;
    private javax.swing.JButton edit;
    private javax.swing.JComboBox forwarder;
    private javax.swing.JComboBox initiater;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton save;
    private javax.swing.JComboBox warehouse;
    // End of variables declaration//GEN-END:variables
}
