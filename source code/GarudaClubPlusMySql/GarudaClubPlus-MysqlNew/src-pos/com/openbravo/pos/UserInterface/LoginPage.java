/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserLoginPage.java
 *
 * Created on May 6, 2009, 5:34:13 PM
 */
package com.openbravo.pos.UserInterface;
//package garudadbmanager;

//import javax.swing.JComponent;
//import javax.swing.JPanel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.*;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.JPrincipalApp;
import com.openbravo.pos.forms.JRootApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.AltEncrypter;
import com.openbravo.pos.util.Hashcypher;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author swathi
 */
public class LoginPage extends javax.swing.JPanel {

    /** Creates new form UserLoginPage */
    private JPanel panel;
    private JRootApp jrapp;
    private JPanel m_jPanelContainer;
    private AppUser user;

    public LoginPage(JPanel panel,JRootApp jrapp,JPanel m_jPanelContainer,AppUser user) {
       // try {
            initComponents();
            this.panel = panel;
            this.jrapp = jrapp;
            this.m_jPanelContainer = m_jPanelContainer;
            this.user = user;
    }

    public LoginPage(JPanel panel) {
        initComponents();
        this.panel=panel;
       /* try {
            FlashPanel.setRequiredFlashVersion("9");
            FlashPanel flashPanel = new FlashPanel(new File("C:\\flash.swf"));
            flashPanel.play();
            JDialog d=new JDialog(new JFrame());
            d.add(flashPanel);
            d.setVisible(true);
            jPanel2.add(flashPanel);
            jPanel2.repaint();
            jPanel2.validate();
        } catch (JFlashLibraryLoadFailedException ex) {
            Logger.getLogger(UserLoginPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JFlashInvalidFlashException ex) {
            Logger.getLogger(UserLoginPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserLoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    public JComponent getComponent(){
     return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Login "));

        jLabel1.setText("Member No:");

        jLabel2.setText("Password");

        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(258, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          String password=String.valueOf(jPasswordField1.getPassword());
        String ukey=jTextField1.getText();
        Object passw=null;
        String uname=null;
        boolean status=false;
        AltEncrypter alt = new AltEncrypter("cypherkey"+ukey.toUpperCase());
        try{
            
           Object[] obj= (Object[])new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "SELECT ID,PASSWORD FROM CUSTOMERS WHERE UCASE(SEARCHKEY)=?"
                    , SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING})).find(ukey.toUpperCase().trim());
           if(obj!=null){
             passw=obj[1];
             uname=String.valueOf(obj[0]);
            //passw = alt.decrypt(passw.toString());
           }
           if(password.equals(passw)){
                user.setMemid(uname);
                jrapp.insertIntoPanel3(user,uname);
           }else
               status=true;
           if(status){
//             JPrincipalApp papp=new JPrincipalApp(jrapp, user);
//             MembersFacility mf=new MembersFacility();
           }else{

           }

        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

/*
 *     String password=jEditorPassword1.getPassword();
        String ukey=jEditorString1.getText();
        Object passw=null;
        String uname=null;
        boolean status=false;
        try{
           Object[] obj= (Object[])new StaticSentence(ses, "SELECT ID,PASSWORD FROM CUSTOMERS WHERE UCASE(SEARCHKEY)=?"
                    , SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING})).find(ukey.toUpperCase().trim());
           if(obj!=null){
             passw=obj[1];
             uname=String.valueOf(obj[0]);
           }
           if(passw!=null){
              status=Hashcypher.authenticate(password, passw.toString());
           }else
               status=true;
           if(status){
              //JPrincipalApp papp=new JPrincipalApp(jrapp, appuser)

          //  MembersFacility mf=new MembersFacility();
           appuser.setMemid(uname);
            jrapp.insertIntoPanel3(appuser,uname);
           }else{

           }

        }catch(Exception e){
            e.printStackTrace();
        }
 * */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
