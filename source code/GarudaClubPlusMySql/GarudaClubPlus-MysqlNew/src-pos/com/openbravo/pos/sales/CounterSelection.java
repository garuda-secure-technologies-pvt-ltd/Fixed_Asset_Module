
package com.openbravo.pos.sales;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.CategoryInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a
 */
public class CounterSelection extends JPanel implements JPanelView, BeanFactoryApp {

    
    private ComboBoxValModel m_kitchrole;
    private DataLogicSales dlSales = null;
    private SentenceList m_sentrole;
    private AppView m_App;
    
    
    public CounterSelection() {
        initComponents();
         
    //    writeValueEOF();
    }
    
    
    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        
    }
    
    
    public Object getBean() {
        return this;
    }
    

    public void activate() throws BasicException {
        
         jTextArea1.setEditable(false);
        
        m_kitchrole = new ComboBoxValModel(dlSales.getKitchenRoleInfo().list());
        jComboBox.setModel(m_kitchrole);
        jComboBox.setSelectedIndex(0);
        jComboBox.setEnabled(true);
        
        loadData();
        
    }
    
    
    public void loadData() throws BasicException {
        AppView m_app=LookupUtilityImpl.getInstance(null).getAppView();
//        Object[]obj = (Object[]) new StaticSentence(m_app.getSession()
//              ,"select id from locations where name like '%restaurant%'"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        
        List l = dlSales.getRestWarehouse().list();
        List p = new ArrayList();
        List pri = new ArrayList();
        for(int i=0;i<l.size();i++){
            Object obj = l.get(i);
          p = new StaticSentence(m_app.getSession()
                   , "select distinct role from people where prcategories like '%"+obj.toString()+"%'"
                   ,null
                   ,new SerializerReadClass(PeoplesRoleInfo.class)).list();
          pri.addAll(p);
        }
        
            Object[]o3 = new Object[pri.size()];
                String[]s3 = new String[pri.size()];
                
                for(int i=0;i<pri.size();i++){
                    o3[i] = (Object) pri.get(i);
                    System.out.print(o3[i]+"\t");
                }
                for(int i=0;i<pri.size();i++){
                    s3[i] = o3[i].toString();
                }
        
           String[] str = new String[s3.length];
        for(int i=0;i<s3.length;i++){
            Object[]obj1 = (Object[]) new StaticSentence(m_app.getSession()
              ,"select name from roles where id = ?"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(s3[i]);
            str[i] = obj1[0].toString();
          }
        
//        List<CategoryInfo> prinfo=   new StaticSentence(m_app.getSession()
//                        , "SELECT ID,NAME,PARENTID,IMAGE FROM CATEGORIES WHERE PARENTID IS NULL"
//                       ,null
//                       ,new SerializerReadClass(CategoryInfo.class)).list();
//        if(prinfo!=null)
//        {
//       String[] strings=new String[prinfo.size()];
//      int i=0;
//            for(CategoryInfo p: prinfo)
//            {
//                strings[i]=p.getName();
//                i++;
//            }
      jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(str));
      String textareadata=jTextArea1.getText();
      str=textareadata.split("\r\n");
      jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(str));
            
 //       }
    }
    
    public String getTitle() {
        return "Assign Counter To Kitchen";
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }
    
    
    
    
    public void writeValueEOF() {
        
         jTextArea1.setText(null);
         jTextArea1.setEnabled(false);
         jTextArea1.setEditable(false);
        jComboBox1.setEnabled(false);
        jButton4.setEnabled(false);
         jComboBox2.setEnabled(false);
        jButton5.setEnabled(false);
    }
    
    public void writeValueInsert() {
        jTextArea1.setText(null);
         jTextArea1.setEnabled(true);
          jTextArea1.setEditable(false);
        jComboBox1.setEnabled(true);
        jButton4.setEnabled(true);
        jComboBox2.setEnabled(true);
        jButton5.setEnabled(true);
    }
    
        
    
    public Object createValue() throws BasicException {
        Object[] people = new Object[10];
        
        String prdata=jTextArea1.getText();
        Object[] obj=prdata.split("\r\n");
        AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
        String prid="";
        for(int i=0;i< obj.length;i++)
        {
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ID FROM CATEGORIES WHERE NAME = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(obj[i].toString());

        if(obj1== null)
            prid += "";
        else{
             if(prid.equals(""))
           prid = obj1[0].toString();
             else
                 prid=prid+ "#" +obj1[0].toString();
        }
        }
        return people;
    }    
     
    
    
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Kitchen:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(jLabel1)
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(60, 60, 60)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(40, 40, 40)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton4)
                        .add(46, 46, 46)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton5))
                    .add(jScrollPane2))
                .addContainerGap(224, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String selectedpr=jComboBox1.getSelectedItem().toString();
        int flag=0;
        String sel = jComboBox.getSelectedItem().toString();
        String data=jTextArea1.getText();
        String[] temparr=data.split("\r\n");
    try{
            
        Object[]o = (Object[]) new StaticSentence(m_App.getSession()
                   ,"SELECT KNAME FROM QTKASSIGN WHERE RCOUNTERS LIKE '%"+selectedpr+"%'"
                  ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
            
        if(o!=null){
            JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.prcatwarning"), AppLocal.getIntString("message.prcattitle"), JOptionPane.WARNING_MESSAGE);
                flag=1;
        }
//        for(int i=0;i<temparr.length; i++)
//        {
//            if(temparr[i].equals(selectedpr))
//            {
//                 JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.prcatwarning"), AppLocal.getIntString("message.prcattitle"), JOptionPane.WARNING_MESSAGE);
//                 flag=1;
//            }
//        }
        if(flag==0){
        data += selectedpr+"\r\n";
        jTextArea1.setText(data);
            
            Object[]oid = (Object[]) new StaticSentence(m_App.getSession()
               ,"SELECT ID FROM ROLES WHERE NAME = '"+sel+"'"
              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
            
          String id = oid[0].toString();
            
            Object[]obj = (Object[]) new StaticSentence(m_App.getSession()
               ,"SELECT KNAME FROM QTKASSIGN WHERE KNAME = '"+sel+"'"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
            if(obj==null){
                new PreparedSentence(m_App.getSession(), "INSERT INTO QTKASSIGN(ID,KNAME,RCOUNTERS) VALUES(?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{id,sel,data});
            }else{
                new PreparedSentence(m_App.getSession(), "UPDATE QTKASSIGN SET RCOUNTERS=? WHERE KNAME='"+sel+"'", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{data});
             }
            loadData();
           }
         }catch(BasicException ex){
                Logger.getLogger(CounterSelection.class.getName()).log(Level.SEVERE, null, ex);
            }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
          String selectedpr=jComboBox2.getSelectedItem().toString();
      //  int flag=0;
          String sel = jComboBox.getSelectedItem().toString();
        String data=jTextArea1.getText();
        String newdata="";
        String[] temparr=data.split("\r\n");
        for(int i=0;i<temparr.length; i++)
        {
            if(!temparr[i].equals(selectedpr))
                newdata += temparr[i]+"\r\n";

        }
       // if(flag==0){
      //  data += selectedpr+"\r\n";
        jTextArea1.setText(newdata);
        try {
            new PreparedSentence(m_App.getSession(), "UPDATE QTKASSIGN SET RCOUNTERS=? WHERE KNAME='"+sel+"'", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{newdata});
            loadData();
        } catch (BasicException ex) {
            Logger.getLogger(CounterSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
     //   }

    }//GEN-LAST:event_jButton5ActionPerformed

private void jComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxItemStateChanged
// TODO add your handling code here:
    String temp = m_kitchrole.getSelectedItem().toString();
        try {
            List lst = dlSales.getPeoplesRoleInfo(temp).list(temp);
            
            Object[]obj = (Object[]) new StaticSentence(m_App.getSession()
               ,"SELECT RCOUNTERS FROM QTKASSIGN WHERE KNAME = '"+temp+"'"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
            
            if(temp != null){
              if(obj != null){
            String data= obj[0].toString();
            String[] strtemp= Formats.STRING.formatValue(data).split("#");
            
                for(int i=0;i<strtemp.length;i++){
                    AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
                
                if(obj!=null){
                 //   data=data+obj[0]+"\r\n";
                    }
                }
            jTextArea1.setText(data);
            
              }else{
                  jTextArea1.setText(null);
              }
              loadData();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jComboBoxItemStateChanged

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables


}


//extends javax.swing.JPanel implements JPanelView, BeanFactoryApp
