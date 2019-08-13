/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VendorDetail.java
 *
 * Created on Apr 7, 2009, 12:02:50 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountCreatorDialog;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.JFind;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class VendorDetail1 extends javax.swing.JPanel implements EditorRecord{

    /** Creates new form VendorDetail */
    private ComboBoxValModel accmodel;
    private DataLogicFacilities dlfac;
    private AppView m_App;
    private VendorDetailTableModel vmodel;
    private Object id;
    private int status=0;//0-new,1-active,2-inactive;
    private Object[] oldvalues;
    public VendorDetail1(AppView app, DirtyManager dirty) {
         m_App=app;
         initComponents();
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         name.getDocument().addDocumentListener(dirty);
         address.getDocument().addDocumentListener(dirty);
         contactno.getDocument().addDocumentListener(dirty);
         ContactPerson.getDocument().addDocumentListener(dirty);
         panno.getDocument().addDocumentListener(dirty);
         tinno.getDocument().addDocumentListener(dirty);
         cstno.getDocument().addDocumentListener(dirty);
         instruction.getDocument().addDocumentListener(dirty);
         account.addActionListener(dirty);
         jCheckBox1.addActionListener(dirty);
         writeValueEOF();
         jButton1.setVisible(false);
    }

    

     public void writeValueEOF() {
         id=null;
         status=0;
        name.setText(null);
        address.setText(null);
        contactno.setText(null);
        ContactPerson.setText(null);
        panno.setText(null);
        tinno.setText(null);
        cstno.setText(null);
        instruction.setText(null);
        account.setSelectedIndex(-1);
        jCheckBox1.setSelected(true);
        setEnableComponents(false);
    }
    private void setEnableComponents(boolean status){
        name.setEnabled(status);
        address.setEnabled(status);
        contactno.setEnabled(status);
        ContactPerson.setEnabled(status);
        panno.setEnabled(status);
        tinno.setEnabled(status);
        cstno.setEnabled(status);
        instruction.setEnabled(status);
        jCheckBox1.setEnabled(status);
    }
    public void writeValueInsert() {
        id=null;
        status=0;
        name.setText(null);
        address.setText(null);
        contactno.setText(null);
        ContactPerson.setText(null);
        panno.setText(null);
        tinno.setText(null);
        cstno.setText(null);
        instruction.setText(null);
        account.setSelectedIndex(-1);
        jCheckBox1.setSelected(true);
        setEnableComponents(true);
    }

    public void writeValueEdit(Object value) {
        Object[] values=(Object[]) value;
        oldvalues=(Object[]) value;
        id=values[0];
        boolean flag=((Boolean) values[1]).booleanValue();
        if(flag)
            status=1;
        else
            status=2;
        jCheckBox1.setSelected(flag);
        name.setText(Formats.STRING.formatValue(values[2]));
        address.setText(Formats.STRING.formatValue(values[3]));
        ContactPerson.setText(Formats.STRING.formatValue(values[4]));
        contactno.setText(Formats.STRING.formatValue(values[5]));
        panno.setText(Formats.STRING.formatValue(values[6]));
        tinno.setText(Formats.STRING.formatValue(values[7]));
        cstno.setText(Formats.STRING.formatValue(values[8]));
        accmodel.setSelectedKey(values[9]);
        account.setModel(accmodel);
        instruction.setText(Formats.STRING.formatValue(values[10]));
        setEnableComponents(true);
    }

    public void writeValueDelete(Object value) {
        Object[] values=(Object[]) value;
        jCheckBox1.setSelected(((Boolean) values[1]).booleanValue());
        name.setText(Formats.STRING.formatValue(values[2]));
        address.setText(Formats.STRING.formatValue(values[3]));
        ContactPerson.setText(Formats.STRING.formatValue(values[4]));
        contactno.setText(Formats.STRING.formatValue(values[5]));
        panno.setText(Formats.STRING.formatValue(values[6]));
        tinno.setText(Formats.STRING.formatValue(values[7]));
        cstno.setText(Formats.STRING.formatValue(values[8]));
        accmodel.setSelectedKey(values[9]);
        account.setModel(accmodel);
        instruction.setText(Formats.STRING.formatValue(values[10]));
        setEnableComponents(false);
    }

    public Object createValue() throws BasicException {
        //ID,ACTIVE,NAME,ADDRESS,CONTACTPERSON,CONTACTNUM,PANNO,TINNO,CST,ACCOUNT,INSTRUCTION,CREATEDBY,CRDATE
       if(account.getSelectedIndex()!=-1 && name.getText().length()>0){
        Object[] vendor = new Object[15];
        vendor[0]=id==null?UUID.randomUUID().toString():id;
        boolean pstatus=jCheckBox1.isSelected();
        vendor[1]=jCheckBox1.isSelected();
        vendor[2]=name.getText().trim();
        vendor[3]=Formats.STRING.formatValue(address.getText().trim());
        vendor[4]=Formats.STRING.formatValue(ContactPerson.getText());
        vendor[5]=Formats.STRING.formatValue(contactno.getText());
        vendor[6]=Formats.STRING.formatValue(panno.getText());
        vendor[7]=Formats.STRING.formatValue(tinno.getText());
        vendor[8]=Formats.STRING.formatValue(cstno.getText());
        vendor[9]=accmodel.getSelectedKey();
        vendor[10]=Formats.STRING.formatValue(instruction.getText().trim());
        vendor[11]=Formats.STRING.formatValue(m_App.getAppUserView().getUser().getName());
        vendor[12]=new Date();
        if(status==0){
            vendor[13]=null;
            vendor[14]=null;
        }else if(status==1){
             if(pstatus){
                  vendor[13]=null;
                  vendor[14]=null;
             }else{
                  vendor[13]=Formats.STRING.formatValue(m_App.getAppUserView().getUser().getName());
                  vendor[14]=new Date();
             }
        }else if(status==2){
             if(pstatus){
                  vendor[13]=null;
                  vendor[14]=null;
             }else{
                  vendor[13]=oldvalues[13];
                  vendor[14]=oldvalues[14];
             }
        }
        if(panno.getText().length()>0 && tinno.getText().length()>0 && cstno.getText().length()>0){
            return vendor;
        }else{
            String missingnos=" ";
            if(panno.getText().length()<=0)
                missingnos +="PAN NO ";
            if(cstno.getText().length()<=0)
                missingnos +="CST NO ";
            if(tinno.getText().length()<=0)
                missingnos +="TIN NO ";
            if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                return vendor;
            }else{
               throw new BasicException();
            }
        }

       }else{
           JOptionPane.showMessageDialog(this, "Please ensure that account and vendor name is not empty", null, JOptionPane.OK_OPTION);
           throw new BasicException();
       }
       // return null;
    }
    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        accmodel=new ComboBoxValModel(dlfac.getaccounts());
        account.setModel(accmodel);
        //account.setSelectedIndex(-1);
    }
  /*  private void loadData() throws BasicException{
        accmodel=new ComboBoxValModel(dlfac.getaccounts());
        account.setModel(accmodel);
        //name.setText(null);
        //address.setText(null);
        //contactno.setText(null);
       // ContactPerson.setText(null);
       // panno.setText(null);
       // tinno.setText(null);
      //  cstno.setText(null);
      //  instruction.setText(null);
        account.setSelectedIndex(-1);
    }*/

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
  /*  private void insertVendor(){
      try{
          //String id=UUID.randomUUID().toString();
          if(name.getText().length()>0){
          AccountMaster acc=(AccountMaster)account.getSelectedItem();
          Object[] param=new Object[]{UUID.randomUUID().toString(),true,name.getText(),address.getText(),ContactPerson.getText(),contactno.getText(),panno.getText(),tinno.getText(),cstno.getText(),acc.getid(),instruction.getText(),m_App.getAppUserView().getUser().getName(),new Date()};
        new PreparedSentence(m_App.getSession()
                , "INSERT INTO VENDOR (ID,ACTIVE,NAME,ADDRESS,CONTACTPERSON,CONTACTNUM,PANNO,TINNO,CST,ACCOUNT,INSTRUCTION,CREATEDBY,CRDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP})).exec(param);
          }
        //  loadData();
      }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
    }*/
 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        ContactPerson = new javax.swing.JTextField();
        contactno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tinno = new javax.swing.JTextField();
        cstno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        account = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        instruction = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        jPanel3.setLayout(null);
        jPanel3.add(name);
        name.setBounds(120, 30, 220, 20);

        jLabel1.setText("Name");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 100, 14);

        address.setColumns(20);
        address.setRows(5);
        jScrollPane1.setViewportView(address);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(120, 70, 220, 96);

        jLabel2.setText("Address");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 70, 110, 14);
        jPanel3.add(ContactPerson);
        ContactPerson.setBounds(120, 190, 220, 20);
        jPanel3.add(contactno);
        contactno.setBounds(450, 190, 170, 20);

        jLabel9.setText("Contact No ");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(357, 190, 80, 14);

        jLabel3.setText("Contact Person");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 190, 110, 14);

        jLabel4.setText("PAN No");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 250, 100, 14);
        jPanel3.add(panno);
        panno.setBounds(120, 250, 140, 20);

        jLabel5.setText("TIN No");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(10, 280, 100, 14);
        jPanel3.add(tinno);
        tinno.setBounds(120, 280, 140, 20);
        jPanel3.add(cstno);
        cstno.setBounds(120, 310, 140, 20);

        jLabel6.setText("CST No");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(10, 310, 100, 14);

        jPanel3.add(account);
        account.setBounds(120, 350, 230, 20);

        jLabel7.setText("Account");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 350, 100, 14);

        instruction.setColumns(20);
        instruction.setRows(5);
        jScrollPane2.setViewportView(instruction);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(120, 390, 230, 96);

        jLabel8.setText("Instruction");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 390, 110, 14);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(400, 490, 90, 23);

        jButton2.setText("Create New Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(380, 350, 210, 23);

        jLabel10.setText("Status");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 220, 100, 20);

        jCheckBox1.setText("Active");
        jPanel3.add(jCheckBox1);
        jCheckBox1.setBounds(120, 220, 120, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
        if(account.getSelectedIndex()!=-1 && name.getText().length()>0){
            int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM VENDOR WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name.getText()).toString());
            if(count==0){
        if(panno.getText().length()>0 && tinno.getText().length()>0 && cstno.getText().length()>0){
           // insertVendor();
        }else{
            String missingnos=" ";
            if(panno.getText().length()<=0)
                missingnos +="PAN NO ";
            if(cstno.getText().length()<=0)
                missingnos +="CST NO ";
            if(tinno.getText().length()<=0)
                missingnos +="TIN NO ";
            if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
              //  insertVendor();
            }
        }
            }else{
                JOptionPane.showMessageDialog(this, "Vendor with the name "+name.getText()+" already exist", null, JOptionPane.OK_OPTION);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please ensure that account and vendor name is not empty", null, JOptionPane.OK_OPTION);
        }
        }catch(Exception e){
         e.printStackTrace();
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
          AccountCreatorDialog accdialog= AccountCreatorDialog.getDialog(this, m_App);
          accdialog.setTitle("Account Creator");
          accdialog.showDialog();
          accmodel=new ComboBoxValModel(dlfac.getaccounts());
          account.setModel(accmodel);
        }catch(Exception e){
          e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ContactPerson;
    private javax.swing.JComboBox account;
    private javax.swing.JTextArea address;
    private javax.swing.JTextField contactno;
    private javax.swing.JTextField cstno;
    private javax.swing.JTextArea instruction;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField panno;
    private javax.swing.JTextField tinno;
    // End of variables declaration//GEN-END:variables




}
