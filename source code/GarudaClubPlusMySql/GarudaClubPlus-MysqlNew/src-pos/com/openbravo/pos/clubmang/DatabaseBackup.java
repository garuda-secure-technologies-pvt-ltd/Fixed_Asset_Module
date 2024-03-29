/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DatabaseBackup.java
 *
 * Created on May 12, 2009, 2:23:45 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
//import com.openbravo.data.gui.LookupUtilityFactory;
//import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
//import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.forms.StartPOS;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.sql.Driver;
//import java.sql.DriverManager;
import java.util.ArrayList;
//import java.util.Enumeration;
import java.util.List;
import javax.swing.JOptionPane;
//import org.hsqldb.Server;

/**
 *
 * @author swathi
 */
public class DatabaseBackup extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /** Creates new form DatabaseBackup */
    public DatabaseBackup() {
        initComponents();
    }

     public String getTitle() {
       return null;
    }

    public void activate() throws BasicException {
        List names=new ArrayList();
         names=new StaticSentence(m_App.getSession()
                 , "SELECT NAME FROM PEOPLE WHERE ID != ? AND LOGINTIME IS NOT NULL"
                 , SerializerWriteString.INSTANCE
                 ,SerializerReadString.INSTANCE).list(m_App.getAppUserView().getUser().getId());
       for(int i=0;i<names.size();i++)
         jTextArea1.append(names.get(i).toString()+"\r\n");
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }
    private AppView m_App;
    public void init(AppView app) throws BeanFactoryException {
        m_App=app;

    }

    public Object getBean() {
        return this;
    }
    /* public static void main (final String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatabaseBackup dbbackup=new DatabaseBackup();
            }
        });
     }*/
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("Location :");

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Create DB Backup");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Users Online :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(130, 130, 130))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(375, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      // jDialog1.setVisible(true);
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jTextField1.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       //  m_App.getSession().ge
         java.io.File f1 = new java.io.File("");
         String path=f1.getAbsolutePath();
        try{

         String loc=m_App.getSession().getURL();
         List names=new StaticSentence(m_App.getSession()
                 , "SELECT NAME FROM PEOPLE WHERE ID != ? AND LOGINTIME IS NOT NULL"
                 , SerializerWriteString.INSTANCE
                 ,SerializerReadString.INSTANCE).list(m_App.getAppUserView().getUser().getId());
       if(names.size()<0){
         
         m_App.getSession().getConnection().close();
         String path1="";
        // String path1=StartPOS.server.getDatabasePath(0, true);
         path1=path1.replaceFirst("file:", "");
         StartPOS.server.stop();
         File inputFile = new File(path1 + ".script");
         File outputFile = new File(jTextField1.getText()+"//"+AppLocal.APP_ID + "-db.script");
         FileReader in = new FileReader(inputFile);
         FileWriter out = new FileWriter(outputFile);
         int c;
         while ((c = in.read()) != -1)
           out.write(c);
         in.close();
         out.close();
         // data file
         inputFile = new File(path1+ ".data");
         outputFile = new File(jTextField1.getText()+"//"+AppLocal.APP_ID + "-db.data");
         in = new FileReader(inputFile);
         out = new FileWriter(outputFile);
         while ((c = in.read()) != -1)
           out.write(c);
         in.close();
         out.close();
         //backup file
         inputFile = new File(path1+ ".backup");
         outputFile = new File(jTextField1.getText()+"//"+AppLocal.APP_ID + "-db.backup");
         in = new FileReader(inputFile);
         out = new FileWriter(outputFile);
         while ((c = in.read()) != -1)
           out.write(c);
         in.close();
         out.close();
         //property file
         inputFile = new File(path1+".properties");
         outputFile = new File(jTextField1.getText()+"//"+AppLocal.APP_ID + "-db.properties");
         in = new FileReader(inputFile);
         out = new FileWriter(outputFile);
         while ((c = in.read()) != -1)
           out.write(c);
         in.close();
         out.close();
      //   if(StartPOS.server==null){
        /*   StartPOS.server = new Server();
           StartPOS.server.setDatabaseName(0, "garudapos-db");
           StartPOS.server.setDatabasePath(0, "file:"+path+"\\garudapos-db");
           StartPOS.server.setLogWriter(null);
           StartPOS.server.setErrWriter(null);
           StartPOS.server.start();*/
       //  }else
           StartPOS.server.start();
        }else{
           JOptionPane.showMessageDialog(this, "Other User are using the application or they have not close the application properly.", "Cannot create the database backup", JOptionPane.OK_OPTION);
      }
        }
        catch(Exception e){
          e.printStackTrace();
       
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
