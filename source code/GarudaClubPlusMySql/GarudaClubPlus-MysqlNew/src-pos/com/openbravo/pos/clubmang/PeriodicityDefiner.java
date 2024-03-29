/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PeriodicityDefiner.java
 *
 * Created on Mar 14, 2009, 5:51:55 PM
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
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class PeriodicityDefiner extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private DataLogicSales m_dlSales;
    private ComboBoxValModel typeModel;
    private PeriodicityTableModel pmodel;
    /** Creates new form PeriodicityDefiner */
    public PeriodicityDefiner() {
        initComponents();
    }
    public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "Days", "Months", "Quaterly", "Years" }));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] {null, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "Last Day" }));
        from_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
      //  to_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
      //  billingtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null, "Begining Of Quater", "End Of Quater" }));
        // List type1=new ArrayList(){null,"Days","Months","Quaterly","Years"};
      /*  type.add(0, null);
        type.add(1, "Days");
        type.add(2,"Months");
        type.add(3, "Quaterly");
        type.add*/
      //  typeModel=new ComboBoxValModel(type1);
      //  type.setModel(typeModel);
    }

     public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

   public String getTitle() {
        return "Perodicity";
    }

    public void activate() throws BasicException {
        loadData();
    }
    private void loadData() throws BasicException {
         reset();
    }
    private void donotShow(){
        //  to_month.setVisible(false);
          from_month.setVisible(false);
          jLabel10.setVisible(false);
          date.setVisible(false);
          jLabel7.setVisible(false);
          nodays.setVisible(false);
          jLabel4.setVisible(false);
          jLabel5.setVisible(false);
       //   jLabel7.setVisible(false);
          jLabel8.setVisible(false);
          //jLabel9.setVisible(false);
         // billingtype.setVisible(false);
         // billingtype.setSelectedIndex(0);
          date.setSelectedIndex(0);
          from_month.setSelectedIndex(0);
        //  to_month.setSelectedIndex(0);
          nodays.setText(null);
         // nomonth.setText(null);
        //  noyears.setText(null);
    }
    private void reset(){

          name.setText(null);
          jCheckBox1.setSelected(false);
          jCheckBox3.setSelected(false);
           type.setSelectedIndex(0);
          donotShow();
         
    }
    public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }
 /*   private void check(){
       if(jCheckBox1.isSelected()){
           date.setVisible(false);
        }else
            date.setVisible(true);
    }*/
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        from_month = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        nodays = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
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
        setLayout(null);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel3.setLayout(null);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(570, 10, 70, 40);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel1);
        jPanel1.setBounds(410, 320, 290, 150);

        jLabel5.setText("Month");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 280, 82, 14);

        jPanel3.add(from_month);
        from_month.setBounds(190, 280, 78, 20);

        jLabel4.setText("Date");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(30, 240, 82, 14);

        jPanel3.add(date);
        date.setBounds(190, 240, 80, 20);

        jLabel6.setText("Proportionate for first billing period");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(30, 190, 210, 30);

        jCheckBox3.setText("Yes");
        jPanel3.add(jCheckBox3);
        jCheckBox3.setBounds(250, 190, 70, 30);

        jLabel1.setText("Type             ");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(30, 120, 80, 14);

        type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeItemStateChanged(evt);
            }
        });
        jPanel3.add(type);
        type.setBounds(190, 120, 150, 20);

        jLabel8.setText("No of Days");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodays, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nodays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel3.add(jPanel2);
        jPanel2.setBounds(350, 110, 410, 40);

        jCheckBox1.setText("Yes");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jPanel3.add(jCheckBox1);
        jCheckBox1.setBounds(240, 80, 70, 30);

        jLabel2.setText("Bill as per DOJ");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(30, 90, 130, 14);
        jPanel3.add(name);
        name.setBounds(190, 50, 150, 20);

        jLabel3.setText("Name");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(30, 50, 120, 14);

        jLabel7.setText("( * Inclusive of this date)");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(310, 240, 220, 14);

        jLabel10.setText("(* Inclusive of this month)");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(310, 280, 220, 14);

        jLabel9.setText("Billing Type");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(30, 160, 80, 20);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Pre Payment");
        jPanel3.add(jRadioButton1);
        jRadioButton1.setBounds(190, 160, 110, 23);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Post Payment");
        jPanel3.add(jRadioButton2);
        jRadioButton2.setBounds(330, 160, 130, 23);

        jTabbedPane1.addTab("Create New", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel4);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 770, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeItemStateChanged
        // TODO add your handling code here:
        if(type.getSelectedItem()!=null){
           if(type.getSelectedItem().toString().equals("Days")){
               donotShow();
               jLabel8.setVisible(true);
               jLabel8.setText("No Of Days");
               nodays.setVisible(true);
           }else if(type.getSelectedItem().toString().equals("Months")){
               donotShow();
             //  nomonth.setVisible(true);
               jLabel8.setVisible(true);
               nodays.setVisible(true);
               jLabel8.setText("No Of Months");
               if(!jCheckBox1.isSelected()){
                 jLabel4.setVisible(true);
                 date.setVisible(true);
                 date.setSelectedIndex(1);
                 jLabel7.setVisible(true);
               }else
                   date.setSelectedIndex(0);
           }else if(type.getSelectedItem().toString().equals("Quaterly")){
               donotShow();
             //  jLabel9.setVisible(true);
            //  billingtype.setVisible(true);
              nodays.setText("3");

           }else if(type.getSelectedItem().toString().equals("Years")){
               donotShow();
             //  noyears.setVisible(true);
               jLabel8.setVisible(true);
               jLabel8.setText("No Of Years");
               nodays.setVisible(true);
             //  jLabel5.setVisible(true);
           //    from_month.setVisible(true);
             //  from_month.setSelectedIndex(1);
             //   jLabel10.setVisible(true);
               if(!jCheckBox1.isSelected()){
                 jLabel4.setVisible(true);
                 date.setVisible(true);
                 date.setSelectedIndex(1);
                 jLabel7.setVisible(true);
                 jLabel5.setVisible(true);
                 from_month.setVisible(true);
                 from_month.setSelectedIndex(1);
               }
           }
        }
    }//GEN-LAST:event_typeItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            int datetemp=0,no=0;
           // String monthtemp=null;
        if(name.getText().length()>0 && type.getSelectedItem()!=null){
           //if(!type.getSelectedItem().toString().equals("Quaterly") && nodays.getText().)
             if(nodays.getText().length()>0){
             no=Integer.parseInt(nodays.getText());
            }
           int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM PERIODICITY WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name.getText()).toString());
            if(count==0){
                boolean flag=true;
                //"Days", "Months", "Quaterly", "Years"
                if(type.getSelectedItem().toString().equals("Days")){
                    if(nodays.getText().length()<=0 || (jRadioButton1.isSelected()==false && jRadioButton2.isSelected()==false)){
                      flag=false;
                    }
                }else if(type.getSelectedItem().toString().equals("Months") ){
                    if(nodays.getText().length()>0 ){
                        if(jCheckBox1.isSelected()==false && date.getSelectedIndex()==0){
                            flag=false;
                        }
                        if( jRadioButton1.isSelected()==false && jRadioButton2.isSelected()==false)
                            flag=false;
                    }else
                        flag=false;
                }else if(type.getSelectedItem().toString().equals("Quaterly")){
                    if( jRadioButton1.isSelected()==false && jRadioButton2.isSelected()==false){
                      flag=false;
                    }
                } else if(type.getSelectedItem().toString().equals("Years")){
                    if(nodays.getText().length()<=0 || date.getSelectedIndex()<=0 || (jCheckBox1.isSelected()==false && from_month.getSelectedIndex()<=0)){
                      flag=false;
                    }
                     if( jRadioButton1.isSelected()==false && jRadioButton2.isSelected()==false)
                            flag=false;
                }
               // String del=String.valueOf(from_month.getSelectedIndex());
                int mindex=-1;
                if(from_month.getSelectedIndex()!=-1){
                     mindex=from_month.getSelectedIndex()-1;
                }
                if(flag==true){
                    Boolean flag1=true;
                    if(jRadioButton1.isSelected()==true)
                        flag1=true;
                    else if(jRadioButton2.isSelected()==true)
                        flag1=false;
            Object[] value=new Object[]{UUID.randomUUID().toString(),true,name.getText(),type.getSelectedItem().toString(),no,date.getSelectedItem(),mindex ,flag1,jCheckBox1.isSelected(),jCheckBox3.isSelected(),m_App.getAppUserView().getUser().getName(),new Date()};
            new PreparedSentence(m_App.getSession()
                  , "INSERT INTO PERIODICITY(ID,ACTIVE,NAME,TYPE_,NO,DATE,FMONTH,BILLTYPE,DOJ,ACCURATE,CREATEDBY,CRDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"                //PRAVEEN:CHANGED TYPE TO TYPE_
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING,Datas.INT,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP})
                  ).exec(value);
           reset();
                }else{
                  JOptionPane.showMessageDialog(this, "Please fill the form completely", null, JOptionPane.OK_OPTION);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Periodicity with the name "+name.getText()+" already exist", null, JOptionPane.OK_OPTION);
            }
        }
        }
        catch(NumberFormatException e1){
          JOptionPane.showMessageDialog(this,"Please enter the numbers in proper format", "Not a number value", JOptionPane.OK_OPTION);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Cannot Insert.Ensure proper values are inserted", "Cannot Insert", JOptionPane.OK_OPTION);
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()==false){
                 
                 if(type.getSelectedItem()!=null){
                 if((!type.getSelectedItem().equals("Quaterly")) && (!type.getSelectedItem().equals("Days"))){
                   jLabel4.setVisible(true);
                   date.setVisible(true);
                   date.setSelectedIndex(1);
                   jLabel7.setVisible(true);
                 }
                 }
                  
                 jCheckBox3.setVisible(true);
                 jLabel6.setVisible(true);
       }else{
                 jLabel4.setVisible(false);
                 date.setVisible(false);
                  jLabel7.setVisible(false);
                 jCheckBox3.setVisible(false);
                 jLabel6.setVisible(false);
                 jLabel5.setVisible(false);
                 from_month.setVisible(false);
       }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        javax.swing.JTabbedPane tab=(javax.swing.JTabbedPane) evt.getSource();
        if(tab.getSelectedIndex()==1){
           try {
                 pmodel = PeriodicityTableModel.loadInstance(m_App);
                 jTable1.setModel(pmodel.getTableModel());
            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox date;
    private javax.swing.JComboBox from_month;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nodays;
    private javax.swing.JComboBox type;
    // End of variables declaration//GEN-END:variables

}
