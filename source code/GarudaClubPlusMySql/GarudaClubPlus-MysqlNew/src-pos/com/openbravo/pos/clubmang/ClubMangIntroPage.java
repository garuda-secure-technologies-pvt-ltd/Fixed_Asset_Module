/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * ClubMangIntroPage.java
 *
 * Created on Mar 16, 2009, 1:49:33 PM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author swathi
 */
public class ClubMangIntroPage extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

  
    private AppView m_App;
    private waitDialog w;

    public ClubMangIntroPage() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        loadData();
    }

    private void loadData() throws BasicException {
        
    }

    public boolean deactivate() {
    
        return true;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome");
        add(jLabel1);
        jLabel1.setBounds(60, 40, 550, 70);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 64)); // NOI18N
        jLabel3.setText("Garuda");
        add(jLabel3);
        jLabel3.setBounds(100, 140, 220, 60);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(220, 230, 14, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(220, 270, 14, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(220, 320, 14, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Restaurant & Bar Billing");
        add(jLabel7);
        jLabel7.setBounds(270, 222, 230, 40);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Club Management ");
        add(jLabel8);
        jLabel8.setBounds(270, 270, 200, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Financial Management ");
        add(jLabel9);
        jLabel9.setBounds(270, 310, 220, 40);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        add(jPanel1);
        jPanel1.setBounds(0, 0, 0, 0);

        jPanel2.setLayout(null);
        add(jPanel2);
        jPanel2.setBounds(0, 0, 0, 0);

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 64)); // NOI18N
        jLabel10.setText("Club Plus");
        add(jLabel10);
        jLabel10.setBounds(320, 140, 320, 60);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("* Please choose from the menu on the left");
        add(jLabel2);
        jLabel2.setBounds(200, 420, 390, 20);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel11);
        jLabel11.setBounds(220, 360, 30, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Inventory Management");
        add(jLabel12);
        jLabel12.setBounds(270, 360, 210, 20);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    // End of variables declaration//GEN-END:variables

    public List getMemberNos() throws BasicException {
        List<Object> GrpNameList = new ArrayList<Object>();

        GrpNameList = new PreparedSentence(m_App.getSession(), "SELECT MOBILE FROM CUSTOMERS WHERE LENGTH(MOBILE)=10 OR LENGTH(MOBILE)=13  AND VISIBLE=TRUE", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return GrpNameList;

    }

}
