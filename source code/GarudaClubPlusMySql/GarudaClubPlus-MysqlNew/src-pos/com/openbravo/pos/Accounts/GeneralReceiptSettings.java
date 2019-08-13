/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Booking.CheckInTableModel;
import com.openbravo.pos.Booking.GuestRoomBillModel;
import com.openbravo.pos.Booking.GuestRoomTableModel;
import com.openbravo.pos.Booking.hallTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.ButtonGroup;


/**
 *
 * @author dev3
 */
public class GeneralReceiptSettings extends javax.swing.JPanel implements JPanelView,BeanFactoryApp  {

    private AppView m_App;
    private DataLogicSales m_dlSales;
    private String AccountId = "";
    private ComboBoxValModel Account_andrd;
    private ComboBoxValModel Account_andrd2;
    private ComboBoxValModel JRole_List_Model;
    private DataLogicFacilities dlfac;
    
    
  //  List<Object> TempAccList = new ArrayList<Object>();
    List<AccountMasterExt> TempAccList =  new ArrayList<AccountMasterExt>();
    
    public GeneralReceiptSettings() {
        initComponents();
        main_panel.setVisible(true);
        JRole_List.setSelectedIndex(-1);
        Account_Combo.setSelectedIndex(-1);
        Account_combo2.setSelectedIndex(-1);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        JRole_List = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        Account_Combo = new javax.swing.JComboBox();
        Account_combo2 = new javax.swing.JComboBox();
        Add_button = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JAccount_list = new javax.swing.JList();
        jLabel35 = new javax.swing.JLabel();
        Save_5 = new javax.swing.JButton();

        jLabel33.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(81, 72, 201));
        jLabel33.setText("***------------------General Receipt  Settings------------------------***");

        jLabel37.setText("1)  Select Role  : ");

        JRole_List.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        JRole_List.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JRole_ListItemStateChanged(evt);
            }
        });
        JRole_List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRole_ListActionPerformed(evt);
            }
        });

        jLabel34.setText("2)  Select Account(s) for General Receipt  : ");

        Account_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        Account_combo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        Add_button.setForeground(new java.awt.Color(250, 21, 21));
        Add_button.setText("Add");
        Add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_buttonActionPerformed(evt);
            }
        });

        jButton3.setForeground(new java.awt.Color(248, 11, 11));
        jButton3.setText("Remove ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        JAccount_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(JAccount_list);

        jLabel35.setForeground(new java.awt.Color(125, 16, 16));
        jLabel35.setText("List of Selected Account(s).");

        Save_5.setForeground(new java.awt.Color(23, 29, 238));
        Save_5.setText("Save ");
        Save_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(26, 26, 26)
                        .addComponent(JRole_List, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addGap(0, 68, Short.MAX_VALUE)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Save_5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Account_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Account_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Add_button)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(62, 62, 62))))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)
                        .addGap(123, 123, 123))))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addGap(51, 51, 51)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(JRole_List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Account_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Add_button))
                        .addGap(134, 134, 134)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Account_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Save_5)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JRole_ListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JRole_ListItemStateChanged
        if(JRole_List.getSelectedIndex()!=-1){
            String role = JRole_List.getSelectedItem().toString();

            List<RoleInfo> Rlist = new ArrayList<>();

            try{
                Rlist=m_dlSales.getRoleList().list();
            }
            catch(BasicException e){

            }

            String RoleID= null;

            for(int i=0;i<Rlist.size();i++){
                String RoleName = Rlist.get(i).getName().toString();
                if(RoleName.equals(role)){
                    RoleID = Rlist.get(i).getID().toString();
                    break;
                }
            }

           // TempAccList = new ArrayList();//commented by pratima 
          TempAccList =  new ArrayList<AccountMasterExt>();

            try{
                TempAccList = getAccountList(m_App, RoleID);
            }
            catch(BasicException e){

            }

            Account_andrd2 = new ComboBoxValModel(TempAccList);
            JAccount_list.setModel(Account_andrd2);
            Account_combo2.setModel(Account_andrd2);

        }
    }//GEN-LAST:event_JRole_ListItemStateChanged

    private void Add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_buttonActionPerformed
        if(Account_Combo.getSelectedIndex()!=-1){
          //  String Account = Account_Combo.getSelectedItem().toString();
          AccountMasterExt Account = (AccountMasterExt)Account_Combo.getSelectedItem();
          
          int flag=0;

            for(int i=0;i<TempAccList.size();i++){
                String t  = TempAccList.get(i).getId().toString();
                if(t.equals(Account.getId().toString())){
                    flag=1;
                    break;
                }
                else{
                    flag=0;

                }
            }

            if(flag==0){
                TempAccList.add(Account);
                Account_andrd2 = new ComboBoxValModel(TempAccList);
                JAccount_list.setModel(Account_andrd2);
                Account_combo2.setModel(Account_andrd2);
            }
            else{
                JOptionPane.showMessageDialog(this, "Account name  already entered.. !!" , "Warning", JOptionPane.WARNING_MESSAGE);
            }

            //   AccountMasterExt z = Account_Combo.getSelectedItem() ;
            //   for(int i=0;i<sacclist.size();i++){
                //      if(Account.equals(sacclist.get(i).getName().toString())){
                    //     AccountId =    sacclist.get(i).getId().toString() + "#";
                    //    }
                // }

            // sacclist
        }
        else{
            JOptionPane.showMessageDialog(this, "Please select Account..!!" , "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Add_buttonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(Account_combo2.getSelectedIndex()!=-1){
           // String Account = Account_combo2.getSelectedItem().toString();
  AccountMasterExt Account = (AccountMasterExt)Account_combo2.getSelectedItem();
            for(int i=0;i<TempAccList.size();i++){
                String t = TempAccList.get(i).getId().toString();
                if(t.equals(Account.getId())){
                    TempAccList.remove(i);
                    break;
                }

            }

            Account_andrd2 = new ComboBoxValModel(TempAccList);
            JAccount_list.setModel(Account_andrd2);
            Account_combo2.setModel(Account_andrd2);

        }
        else{
            JOptionPane.showMessageDialog(this, "Please select Account..!!" , "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Save_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_5ActionPerformed
        if(JRole_List.getSelectedIndex()!=-1){
            if(TempAccList.size()>0){

                String role = JRole_List.getSelectedItem().toString();

                List<RoleInfo> Rlist = new ArrayList<>();

                try{
                    Rlist=m_dlSales.getRoleList().list();
                }
                catch(BasicException e){

                }

                String RoleID= null;

                for(int i=0;i<Rlist.size();i++){
                    String RoleName = Rlist.get(i).getName().toString();
                    if(RoleName.equals(role)){
                        RoleID = Rlist.get(i).getID().toString();
                        break;
                    }
                }

                String AccountIDs = "";

                for(int j=0;j<TempAccList.size();j++){
                   // String AName = TempAccList.get(j).toString();//by pratima
                    String AccID = null;

                    try{
                       // AccID = getAccountID(m_App, AName);
                      AccID = TempAccList.get(j).getId().toString();
                    }catch(Exception e){

                    }

                    AccountIDs = AccountIDs + AccID+"#";

                }

                System.out.println("AccountIDs" + AccountIDs);

                try {
//                     new PreparedSentence(m_App.getSession()
//                        , "UPDATE generalreceiptperm SET  ACTIVE=0 , DEACBY=? , DEACDATE=? , DEACHOST=? WHERE NAME=? AND ACTIVE=1"
//                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING})).exec(new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),RoleID});
//
//                    new PreparedSentence(m_App.getSession()
//                        , "INSERT INTO generalreceiptperm(ID,NAME,VALUE,ACTIVE,CRBY,CRDATE,CRHOST) VALUES(?,?,?,?,?,?,?)"
//                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.INT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),RoleID , AccountIDs , 1 ,m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() });
        //pratima:above changes are not implemented in jar so above lines are replaced by old code in jar (to add new changes in this class)
        if (new PreparedSentence(this.m_App.getSession(), "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?", new SerializerWriteBasic(new Datas[] { Datas.STRING, Datas.STRING })).exec(new Object[] { AccountIDs, RoleID }) <= 0)
          {
            new PreparedSentence(this.m_App.getSession(), "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)", new SerializerWriteBasic(new Datas[] { Datas.STRING, Datas.STRING, Datas.STRING })).exec(new Object[] { UUID.randomUUID().toString(), RoleID, AccountIDs });
          }
                
               } catch (BasicException ex) {
                Logger.getLogger(GeneralReceiptSettings.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(this, "Updated Successfully" , "Success", JOptionPane.INFORMATION_MESSAGE);
            JRole_List.setSelectedIndex(-1);
            Account_Combo.setSelectedIndex(-1);

            TempAccList = new ArrayList<>();
            Account_andrd2 = new ComboBoxValModel(TempAccList);
            JAccount_list.setModel(Account_andrd2);
            Account_combo2.setModel(Account_andrd2);

        }
        else{
            JOptionPane.showMessageDialog(this, "Please select atleast one account..!" , "Warning", JOptionPane.WARNING_MESSAGE);
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Role should not be empty..!!" , "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Save_5ActionPerformed

    private void JRole_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRole_ListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JRole_ListActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Account_Combo;
    private javax.swing.JComboBox Account_combo2;
    private javax.swing.JButton Add_button;
    private javax.swing.JList JAccount_list;
    private javax.swing.JComboBox JRole_List;
    private javax.swing.JButton Save_5;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    // End of variables declaration//GEN-END:variables


public void reset(){
      
        
        
     }

    public String getTitle() {
         return "***------------------General Receipt  Settings------------------------***";
    }

    public void activate() throws BasicException {
        reset();
        loaddata();
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
    }

    public Object getBean() {
       return this;
    }
    

    public void loaddata() throws BasicException{
        
       List<AccountMasterExt> sacclist=dlfac.getaccounts(); 
       Account_andrd=new ComboBoxValModel(sacclist);
        Account_Combo.setModel(Account_andrd);
        
        List<Object> GeneralReceiptAccList = new ArrayList<Object>();
        Account_andrd2=new ComboBoxValModel(GeneralReceiptAccList);
        Account_combo2.setModel(Account_andrd2);
        
        List<RoleInfo> Rlist=m_dlSales.getRoleList().list();
        JRole_List_Model = new ComboBoxValModel(Rlist);
        JRole_List.setModel(JRole_List_Model);
        
       
        
      
        
        
    }
    
 // public List getAccountList(AppView app , String RoleId) throws BasicException{
    public List  <AccountMasterExt> getAccountList(AppView app , String RoleId) throws BasicException{
         Object o = null; 
         
         List<Object> Mem_list = new ArrayList<Object>();
           List<AccountMasterExt> TempAccList1 = new ArrayList<AccountMasterExt>();
      //   o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generalreceiptperm WHERE NAME =? AND ACTIVE=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(RoleId);
       o = new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE WHERE NAME =? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(RoleId);
     
      if(o!=null){
            String o2 = o.toString();
            String []Str = o2.split("#");
            for(int i=0;i<Str.length;i++){
                String x = Str[i].toString();
                
//                Object AccName = null;
//                AccName  = new StaticSentence(app.getSession(), "SELECT  NAME FROM ACCOUNTMASTER WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(x);
//                String AccStr = AccName.toString();
//                
//                Mem_list.add(AccStr);
                 AccountMasterExt acc = (AccountMasterExt) new StaticSentence(app.getSession(), "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.ID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).find(x);
                TempAccList1.add(acc);
            }
             
             
         }         
         else{
            // Mem_list = new ArrayList<Object>();
            TempAccList1 = new ArrayList<AccountMasterExt>();
         }
           
          //return Mem_list;
         return TempAccList1;
      }

  
      
   // edited for general Receipts
     public String getAccountID(AppView app , String Name ) throws BasicException{
          Object  Accountid = null;
          Accountid  =  new StaticSentence(app.getSession(), "SELECT ID FROM ACCOUNTMASTER WHERE NAME=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(Accountid!=null){
              String x = Accountid.toString();  
              
              return x;  
          }
          else{
              return "";
          }
      }
     
  
  
  
}
