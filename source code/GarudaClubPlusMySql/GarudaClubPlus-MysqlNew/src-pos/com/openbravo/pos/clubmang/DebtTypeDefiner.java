/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DebtTypeDefiner.java
 *
 * Created on Mar 21, 2009, 1:53:26 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class DebtTypeDefiner extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form DebtTypeDefiner */
    private AppView m_App;
    private ComboBoxValModel pmodel;
    private ComboBoxValModel accmodel;
    private DataLogicFacilities dmang;
    private DebtTypeTableModel dmodel;

    public DebtTypeDefiner() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {
       m_App=app;
      dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
      type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "Days", "Months", "Years" }));
    //  date.setModel(new javax.swing.DefaultComboBoxModel(new String[] {null, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "Last Day" }));
   //   month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
      //  to_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
   //   billingtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "Begining Of Quater", "End Of Quater" }));
    }

    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
       loadData();
    }

    public void loadData() throws BasicException{
       // pmodel=new ComboBoxValModel(dmang.getPeriodicity().list());
        //debtperiod.setModel(pmodel);
        accmodel=new ComboBoxValModel(dmang.getaccounts());
     //   accounttype.setModel(accmodel);
      type.setSelectedIndex(0);
      jTabbedPane1.setSelectedIndex(0);
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               name.requestFocus();
            }
        });
        refresh();
    }
   private void refresh(){
        name.setText(null);
    //    maxdebt.setText(null);
       // debtperiod.setSelectedIndex(-1);
    //    accounttype.setSelectedIndex(-1);
        NOD.setText(null);
         type.setSelectedIndex(0);
   }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public Object getBean() {
         return this;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        NOD = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
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

        setAutoscrolls(true);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setLayout(null);

        jLabel1.setText("Name ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 50, 70, 14);
        jPanel1.add(name);
        name.setBounds(90, 50, 160, 20);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(430, 10, 80, 23);

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(520, 10, 100, 23);

        jLabel3.setText("Period Type");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 90, 70, 14);

        jPanel1.add(type);
        type.setBounds(90, 90, 160, 20);

        jLabel5.setText("Number");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(NOD, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(NOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(280, 80, 160, 40);

        jTabbedPane1.addTab("Create New", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String dname=name.getText();
      //  String max=maxdebt.getText();
        try{
        if(dname.length()>0 && type.getSelectedIndex()!=0){
         //   AccountMaster acc=(AccountMaster)accounttype.getSelectedItem();
           // Periodicity period=(Periodicity) debtperiod.getSelectedItem();
             int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM DEBTTYPE WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name.getText()).toString());
            if(count==0){
            Object[] value=new Object[]{UUID.randomUUID().toString(),true,dname,type.getSelectedItem(),Integer.parseInt(NOD.getText()),m_App.getAppUserView().getUser().getName(),new Date()};
            new PreparedSentence(m_App.getSession()
                  , "INSERT INTO DEBTTYPE(ID,ACTIVE,NAME,PERIODTYPE,NUMBER,CREATEDBY,CRDATE) VALUES (?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING,Datas.TIMESTAMP})
                  ).exec(value);
            refresh();
        }else{
            JOptionPane.showMessageDialog(this, "Debt Type with the name "+name.getText()+" already exist", null, JOptionPane.OK_OPTION);
        }
        }
        }
        catch(NumberFormatException e){
           //
            e.printStackTrace();
        }
        catch(Exception e){
          e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
        loadData();
        }
        catch(Exception e){
           e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        javax.swing.JTabbedPane tab=(javax.swing.JTabbedPane)evt.getSource();
        if(tab.getSelectedIndex()==1){
            try{
         //   Component a=evt.getComponent();

         dmodel=DebtTypeTableModel.loadInstance(m_App);
         jTable1.setModel(dmodel.getTableModel());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NOD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox type;
    // End of variables declaration//GEN-END:variables



}