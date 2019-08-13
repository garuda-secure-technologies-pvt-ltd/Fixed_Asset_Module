/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
//import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
//import com.openbravo.data.user.DirtyManager;
//import com.openbravo.data.user.EditorRecord;
//import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
//import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


public class BillArchiveReset extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    AppView m_App;
    private ComboBoxValModel umodel;
    private DataLogicSales m_dlSales;
    private DataLogicSystem m_dlSystem;
    private String bcut=null;
    public String split2[];
    private int temp=0;
    private int sales=0;
    private int payment=0;
    List<String> obj9=new ArrayList<String>();
    String bill_arv_num=null;
    String bill_num=null;
    
    public BillArchiveReset() {
        
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        billseries = new javax.swing.JTextField();
        lbillarv = new javax.swing.JTextField();
        Bill = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setText("Counter");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel3.setText("Bill As Per Bill Table");
        jLabel3.setVisible(false);

        jLabel7.setText("Bill As Per Bill_ARV Table");
        jLabel7.setVisible(false);

        billseries.setVisible(false);
        billseries.setEditable(false);

        lbillarv.setVisible(false);
        lbillarv.setEditable(false);

        Bill.setText("UpdateBill");
        Bill.setVisible(false);
        Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Note: Be careful while using this menu. Only use when you are sure that Bill creation is not happening  due to 'Duplicate Entry' ");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel7))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, billseries, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, lbillarv, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(150, 150, 150)
                        .add(Bill)))
                .addContainerGap(517, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jLabel3, jLabel7}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.linkSize(new java.awt.Component[] {billseries, lbillarv}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(billseries, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(lbillarv, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(27, 27, 27)
                .add(Bill)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel2)
                        .add(26, 26, 26)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(27, 27, 27)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(125, 125, 125))
        );
    }// </editor-fold>//GEN-END:initComponents

 @Override
 public void init(AppView app) throws BeanFactoryException {
     m_App = app;
     m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
     m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate"); 
      
      List<RoleInfo> roleslist;
        try {
            umodel=new ComboBoxValModel();
            roleslist = m_dlSales.getRoleList().list();
            for(int i=0;i<roleslist.size();i++){
                 RoleInfo rinfo=roleslist.get(i);
                 umodel.add(rinfo);
            }
        } catch (BasicException ex) {
            Logger.getLogger(BillArchiveReset.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      jComboBox2.setModel(umodel);
     }
    
    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
       
        
        if(jComboBox2.getSelectedIndex()!=-1){
        try {
            // TODO add your handling code here:
            
            String role=jComboBox2.getSelectedItem().toString();
            Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                    , "SELECT QTSERIES,QTMAX,BSERIES,BMAX,RSERIES,RMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE AND CATEGORY=? "
                    ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                    ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).find(new Object[]{((RoleInfo)jComboBox2.getSelectedItem()).getID(),((RoleInfo)jComboBox2.getSelectedItem()).getID()});
           
    if(obj!=null){
                    
               if(obj[2]!=null){
                
                    
//                Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()//shiv:CHANGED BELOW QUERY
//                       // , "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE FROM PEOPLE,ROLES WHERE PEOPLE.ROLE=ROLES.ID AND ROLES.NAME=?"
//                       ,"SELECT P.ID,P.NAME,P.APPPASSWORD,P.CARD,P.ROLE,P.IMAGE,P.LOGINTIME,P.CLOSECASHTIME,P.OPENCASHTIME,P.CLOSESALE,P.OPENSALE,P.PRCATEGORIES FROM PEOPLE P,ROLES R WHERE P.ROLE=R.ID AND R.NAME=?"
//                       ,SerializerWriteString.INSTANCE
//                       ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})).find(jComboBox2.getSelectedItem().toString());
//               
//                if(obj1==null){
////                    JOptionPane.showMessageDialog(this, "User doesnot exist for this role.Create a user first", "cannot", JOptionPane.OK_OPTION);
////                    billseries.setText(null);
//                }
                Object name=jComboBox2.getSelectedItem();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
                
                obj3[0].toString();
                System.out.println(obj3[0].toString());
                
                
                bcut=obj[3].toString();
                split2=bcut.split("(?<=\\G..)");
                   for (String split21 : split2) {
                       System.out.println(split21);
                   }
                Object sp=split2[0].toString();
                
               obj9 =new StaticSentence(m_App.getSession(),"SELECT ID FROM bill where id like '"+obj[2].toString()+"%'",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).list();
               //Iterator itr=obj9.iterator();
               if(obj9!=null){

                   for (Iterator<String> it = obj9.iterator(); it.hasNext();) {
                      bill_num = it.next();
                      bill_arv_num= setBillArc(bill_num);
                      if(bill_arv_num!=null) {
                         break;
                      }
                          
                   }
                   
                   if(bill_arv_num!=null){
                       billseries.setVisible(true);
                       lbillarv.setVisible(true);
                       jLabel3.setVisible(true);
                       jLabel7.setVisible(true);
                       Bill.setVisible(true);
                       
                       billseries.setText(bill_num);
                       String billarvnew=bill_arv_num+"A";
                       lbillarv.setText(billarvnew);
                      // bill_num=bill_arv_num=null;
                   }else reset();
                }else{
                    billseries.setText("No Bill");
                    Bill.setEnabled(false);
                }
            }
               
               else billseries.setText("");
      }
        
       if(obj!=null){    
             
            payment=0;
            sales=0;
            temp=0;
            Object name=jComboBox2.getSelectedItem();
             //warehouse changes -start
            Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()//shiv:CHANGED BELOW QUERY
                       // , "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE FROM PEOPLE,ROLES WHERE PEOPLE.ROLE=ROLES.ID AND ROLES.NAME=?"
                       ,"SELECT P.ID,P.NAME,P.APPPASSWORD,P.CARD,P.ROLE,P.IMAGE,P.LOGINTIME,P.CLOSECASHTIME,P.OPENCASHTIME,P.CLOSESALE,P.OPENSALE,P.PRCATEGORIES FROM PEOPLE P,ROLES R WHERE P.ROLE=R.ID AND R.NAME=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})).find(jComboBox2.getSelectedItem().toString());
           
           
            
            if(obj1!=null){
                 String warehouse = null;
                if (obj1[11] != null) {
                    String[] wArr = obj1[11].toString().split("#");
                    warehouse = wArr[0];
                }
             AppUser appuser=new AppUser(obj1[0].toString(), obj1[1].toString(),obj1[4].toString(),warehouse);
              //warehouse changes -end
             appuser.fillPermissions(m_dlSystem);
             boolean pflag= appuser.hasPermission("payment");
             boolean qflag=appuser.hasPermission("sales");
             String del=jComboBox2.getSelectedItem().toString();
             if(pflag==true && qflag==true){
                     payment=1;
                     sales=1;
         
             }
               
          else
             if(pflag==false && qflag==false){
                payment=0;
        
                jLabel3.setVisible(false);
                billseries.setVisible(false);
                lbillarv.setVisible(false);
                jLabel7.setVisible(false);

                Bill.setVisible(false);
                            
         }
               
          else
             if(pflag==true || qflag==false){
                payment=1;
                jLabel3.setVisible(false);
                billseries.setVisible(false);
                lbillarv.setVisible(false);
                jLabel7.setVisible(false);
                Bill.setVisible(false);
                            
           }else
             if(qflag==true || pflag==false)
             {
              sales=1;
             }
               
             }else{
                reset();
                JOptionPane.showMessageDialog(this, "User doesnot exist for this role.Create a user first", "cannot", JOptionPane.OK_OPTION);
             }
              
              
           if(payment == 1 && sales==1){
                if(bill_num!=null && bill_arv_num!=null){
                    jLabel3.setVisible(true);
                    jLabel2.setVisible(true);
                    billseries.setVisible(true);
                    lbillarv.setVisible(true);
                    Bill.setVisible(true);
                    jLabel7.setVisible(true);
             }
      }else
      if(payment==0 && sales == 0){
         
            jLabel3.setVisible(false);
            jLabel2.setVisible(true);
            billseries.setVisible(false);
            lbillarv.setVisible(false);
            Bill.setVisible(false);
            jLabel7.setVisible(false);
           
      }
      else
      if(payment==1 || sales == 0){
         
            jLabel3.setVisible(false);
            billseries.setVisible(false);
            lbillarv.setVisible(false);
            Bill.setVisible(false);
            jLabel7.setVisible(false);
           
      }else
   
          if(sales==1 || payment==0){
              if(bill_num!=null && bill_arv_num!=null){
              
                billseries.setVisible(true);
                lbillarv.setVisible(true);
                Bill.setVisible(true);
                jLabel3.setVisible(true);
                jLabel7.setVisible(true);

             }     
       }
             
        
         }  else{
             
             jLabel3.setVisible(false);
            
             jLabel7.setVisible(false);
             
             billseries.setVisible(false);
             lbillarv.setVisible(false);
             Bill.setVisible(false);
             
             JOptionPane.showMessageDialog(this, "User doesnot exist for this role.Create a user first", "cannot", JOptionPane.OK_OPTION);
          } 
    
        }
        catch (BasicException ex) {
           
            Logger.getLogger(BillArchiveReset.class.getName()).log(Level.SEVERE, null, ex);     
           
        }
    }
       
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillActionPerformed
        
        if(lbillarv.getText()!=null){
         
        try {
             
            Transaction t = new Transaction(m_App.getSession()) {
                
             @Override
             protected Object transact() throws BasicException {
            // String billArv=lbillarv.getText();
             String billArvUpdated=lbillarv.getText();
             
            List cbanames= new PreparedSentence(m_App.getSession()
                         , "SELECT\n" +
                        "  constraint_name\n" +
                        "FROM\n" +
                        "  information_schema.REFERENTIAL_CONSTRAINTS\n" +
                        "WHERE\n" +
                        "  constraint_schema ='bci16thapr' AND table_name ='billitem_arv';"
                         ,null,SerializerReadString.INSTANCE).list(); 
            
           List cqtanames= new PreparedSentence(m_App.getSession()
                         , "SELECT\n" +
                        "  constraint_name\n" +
                        "FROM\n" +
                        "  information_schema.REFERENTIAL_CONSTRAINTS\n" +
                        "WHERE\n" +
                        "  constraint_schema ='bci16thapr' AND table_name ='qticket_arv';"
                         ,null,SerializerReadString.INSTANCE).list(); 
             
        if(cbanames!=null){
            for(int i=0;i<cbanames.size();i++){
             new PreparedSentence(m_App.getSession()
                         , "ALTER TABLE BILLITEM_ARV\n" +
                           "DROP FOREIGN KEY "+cbanames.get(i)+""
                                      ).exec(); 
            }
       } 
        
        if(cqtanames!=null){
            for(int i=0;i<cqtanames.size();i++){
             new PreparedSentence(m_App.getSession()
                         , "ALTER TABLE QTICKET_ARV\n" +
                           "DROP FOREIGN KEY "+cqtanames.get(i)+""
                                      ).exec(); 
             }
        } 
        
        
             new PreparedSentence(m_App.getSession()
                         , "UPDATE BILL_ARV SET ID=? WHERE ID=?"
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{billArvUpdated,billseries.getText()}); 
             
            
             new PreparedSentence(m_App.getSession()
                         , "UPDATE BILLITEM_ARV SET PARENTID=? WHERE PARENTID=?"
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{billArvUpdated,billseries.getText()}); 
             
             
             new PreparedSentence(m_App.getSession()
                         , "UPDATE QTICKET_ARV SET BILLREF=? WHERE BILLREF=?"
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{billArvUpdated,billseries.getText()}); 
             
             new PreparedSentence(m_App.getSession()
                         , "ALTER TABLE BILLITEM_ARV\n" +
                            "ADD CONSTRAINT FK_billitem_arv_1\n" +
                            "FOREIGN KEY (PARENTID)\n" +
                            "REFERENCES BILL_ARV(ID)"
                         ).exec(); 
             
             new PreparedSentence(m_App.getSession()
                         , "ALTER TABLE QTICKET_ARV\n" +
                            "ADD CONSTRAINT FK_qticket_arv_1\n" +
                            "FOREIGN KEY (BILLREF)\n" +
                            "REFERENCES BILL_ARV(ID)"
                         ).exec(); 
                    
              return null;
                 }
             };
             t.execute();
             JOptionPane.showMessageDialog(this, "Successfully Updated..");
            // bill_num=null;
            // bill_arv_num=null;
             jComboBox2.setSelectedIndex(0);
         } catch (BasicException ex) {
             Logger.getLogger(BillArchiveReset.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, "Update Failed..");
             
         }
     }
    }//GEN-LAST:event_BillActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bill;
    private javax.swing.JTextField billseries;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lbillarv;
    // End of variables declaration//GEN-END:variables

 public String setBillArc(String billnum){
        
        String billArID=null;
        if(billnum!=null){
       
            try {
                    billArID=(String) new StaticSentence(m_App.getSession()
                        ,"SELECT  ID FROM BILL_ARV WHERE ID=?" ,SerializerWriteString.INSTANCE
                        ,SerializerReadString.INSTANCE).find(billnum);
                } catch (BasicException ex) {
                     Logger.getLogger(BillArchiveReset.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
          return billArID;  
  }
    
    @Override
     public void activate() throws BasicException {
      
          
         billseries.setVisible(false);
         lbillarv.setVisible(false);
         billseries.setText(null);
         lbillarv.setText(null);
         jLabel3.setVisible(false);
         jLabel7.setVisible(false);
         Bill.setVisible(false);
         jComboBox2.setSelectedItem(null);
     }
 
    public void reset(){
         billseries.setVisible(false);
         lbillarv.setVisible(false);
         
         jLabel3.setVisible(false);
         jLabel7.setVisible(false);
         billseries.setText(null);
         lbillarv.setText(null);
         Bill.setVisible(false);
         
         bill_num=bill_arv_num=null;
    }
    @Override
    public String getTitle() {
        return "Bill Archive Reset";
    }

    @Override
    public boolean deactivate() {
        return true;
    }

    @Override
    public JComponent getComponent() {
       return this; 
    }

    @Override
    public Object getBean() {
       return this;
    }

}
