

package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JFlowPanel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class VotingForm extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    
    private AppView m_App;
    
  
    public VotingForm() {
        initComponents();
        selectedmember_panel.setVisible(false);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        nominee_panel = new javax.swing.JPanel();
        photo_lable = new javax.swing.JLabel();
        Nom_Name_Lable = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        nominee_panel1 = new javax.swing.JPanel();
        photo_lable1 = new javax.swing.JLabel();
        Nom_Name_Lable1 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        nominee_panel2 = new javax.swing.JPanel();
        photo_lable2 = new javax.swing.JLabel();
        Nom_Name_Lable2 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        nominee_panel3 = new javax.swing.JPanel();
        photo_lable3 = new javax.swing.JLabel();
        Nom_Name_Lable3 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        nominee_panel4 = new javax.swing.JPanel();
        photo_lable4 = new javax.swing.JLabel();
        Nom_Name_Lable4 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        nominee_panel5 = new javax.swing.JPanel();
        photo_lable5 = new javax.swing.JLabel();
        Nom_Name_Lable5 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        nominee_panel6 = new javax.swing.JPanel();
        photo_lable6 = new javax.swing.JLabel();
        Nom_Name_Lable6 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        nominee_panel7 = new javax.swing.JPanel();
        photo_lable7 = new javax.swing.JLabel();
        Nom_Name_Lable7 = new javax.swing.JLabel();
        jRadioButton8 = new javax.swing.JRadioButton();
        nominee_panel8 = new javax.swing.JPanel();
        photo_lable8 = new javax.swing.JLabel();
        Nom_Name_Lable8 = new javax.swing.JLabel();
        jRadioButton9 = new javax.swing.JRadioButton();
        nominee_panel9 = new javax.swing.JPanel();
        photo_lable9 = new javax.swing.JLabel();
        Nom_Name_Lable9 = new javax.swing.JLabel();
        jRadioButton10 = new javax.swing.JRadioButton();
        nominee_panel10 = new javax.swing.JPanel();
        photo_lable10 = new javax.swing.JLabel();
        Nom_Name_Lable10 = new javax.swing.JLabel();
        jRadioButton11 = new javax.swing.JRadioButton();
        nominee_panel11 = new javax.swing.JPanel();
        photo_lable11 = new javax.swing.JLabel();
        Nom_Name_Lable11 = new javax.swing.JLabel();
        jRadioButton12 = new javax.swing.JRadioButton();
        nominee_panel12 = new javax.swing.JPanel();
        photo_lable12 = new javax.swing.JLabel();
        Nom_Name_Lable12 = new javax.swing.JLabel();
        jRadioButton13 = new javax.swing.JRadioButton();
        nominee_panel13 = new javax.swing.JPanel();
        photo_lable13 = new javax.swing.JLabel();
        Nom_Name_Lable13 = new javax.swing.JLabel();
        jRadioButton14 = new javax.swing.JRadioButton();
        nominee_panel14 = new javax.swing.JPanel();
        photo_lable14 = new javax.swing.JLabel();
        Nom_Name_Lable14 = new javax.swing.JLabel();
        jRadioButton15 = new javax.swing.JRadioButton();
        nominee_panel15 = new javax.swing.JPanel();
        photo_lable15 = new javax.swing.JLabel();
        Nom_Name_Lable15 = new javax.swing.JLabel();
        jRadioButton16 = new javax.swing.JRadioButton();
        nominee_panel16 = new javax.swing.JPanel();
        photo_lable16 = new javax.swing.JLabel();
        Nom_Name_Lable16 = new javax.swing.JLabel();
        jRadioButton17 = new javax.swing.JRadioButton();
        nominee_panel17 = new javax.swing.JPanel();
        photo_lable17 = new javax.swing.JLabel();
        Nom_Name_Lable17 = new javax.swing.JLabel();
        jRadioButton18 = new javax.swing.JRadioButton();
        nominee_panel18 = new javax.swing.JPanel();
        photo_lable18 = new javax.swing.JLabel();
        Nom_Name_Lable18 = new javax.swing.JLabel();
        jRadioButton19 = new javax.swing.JRadioButton();
        nominee_panel19 = new javax.swing.JPanel();
        photo_lable19 = new javax.swing.JLabel();
        Nom_Name_Lable19 = new javax.swing.JLabel();
        jRadioButton20 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        poweredby_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        selectedmember_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        nominee_panel20 = new javax.swing.JPanel();
        photo_lable20 = new javax.swing.JLabel();
        Nom_Name_Lable20 = new javax.swing.JLabel();
        nominee_panel21 = new javax.swing.JPanel();
        photo_lable21 = new javax.swing.JLabel();
        Nom_Name_Lable21 = new javax.swing.JLabel();
        nominee_panel22 = new javax.swing.JPanel();
        photo_lable22 = new javax.swing.JLabel();
        Nom_Name_Lable22 = new javax.swing.JLabel();
        nominee_panel23 = new javax.swing.JPanel();
        photo_lable23 = new javax.swing.JLabel();
        Nom_Name_Lable23 = new javax.swing.JLabel();
        nominee_panel24 = new javax.swing.JPanel();
        photo_lable24 = new javax.swing.JLabel();
        Nom_Name_Lable24 = new javax.swing.JLabel();
        nominee_panel25 = new javax.swing.JPanel();
        photo_lable25 = new javax.swing.JLabel();
        Nom_Name_Lable25 = new javax.swing.JLabel();
        nominee_panel26 = new javax.swing.JPanel();
        photo_lable26 = new javax.swing.JLabel();
        Nom_Name_Lable26 = new javax.swing.JLabel();
        nominee_panel27 = new javax.swing.JPanel();
        photo_lable27 = new javax.swing.JLabel();
        Nom_Name_Lable27 = new javax.swing.JLabel();
        nominee_panel28 = new javax.swing.JPanel();
        photo_lable28 = new javax.swing.JLabel();
        Nom_Name_Lable28 = new javax.swing.JLabel();
        nominee_panel29 = new javax.swing.JPanel();
        photo_lable29 = new javax.swing.JLabel();
        Nom_Name_Lable29 = new javax.swing.JLabel();
        finalSubmit_btn = new javax.swing.JButton();
        gobacj_btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nominee_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable.setText("Name");

        javax.swing.GroupLayout nominee_panelLayout = new javax.swing.GroupLayout(nominee_panel);
        nominee_panel.setLayout(nominee_panelLayout);
        nominee_panelLayout.setHorizontalGroup(
            nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addContainerGap())
        );
        nominee_panelLayout.setVerticalGroup(
            nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panelLayout.createSequentialGroup()
                .addComponent(photo_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(nominee_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nom_Name_Lable)
                    .addComponent(jRadioButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable1.setText("Name");

        javax.swing.GroupLayout nominee_panel1Layout = new javax.swing.GroupLayout(nominee_panel1);
        nominee_panel1.setLayout(nominee_panel1Layout);
        nominee_panel1Layout.setHorizontalGroup(
            nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton2)
                .addContainerGap())
        );
        nominee_panel1Layout.setVerticalGroup(
            nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel1Layout.createSequentialGroup()
                .addComponent(photo_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(nominee_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(Nom_Name_Lable1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable2.setText("Name");

        javax.swing.GroupLayout nominee_panel2Layout = new javax.swing.GroupLayout(nominee_panel2);
        nominee_panel2.setLayout(nominee_panel2Layout);
        nominee_panel2Layout.setHorizontalGroup(
            nominee_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jRadioButton3)
                .addContainerGap())
        );
        nominee_panel2Layout.setVerticalGroup(
            nominee_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton3)
                    .addComponent(Nom_Name_Lable2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(photo_lable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        photo_lable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable3.setText("Name");

        javax.swing.GroupLayout nominee_panel3Layout = new javax.swing.GroupLayout(nominee_panel3);
        nominee_panel3.setLayout(nominee_panel3Layout);
        nominee_panel3Layout.setHorizontalGroup(
            nominee_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton4)
                .addContainerGap())
        );
        nominee_panel3Layout.setVerticalGroup(
            nominee_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton4)
                    .addComponent(Nom_Name_Lable3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(photo_lable3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        photo_lable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable4.setText("Name");

        javax.swing.GroupLayout nominee_panel4Layout = new javax.swing.GroupLayout(nominee_panel4);
        nominee_panel4.setLayout(nominee_panel4Layout);
        nominee_panel4Layout.setHorizontalGroup(
            nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable4, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton5)
                .addContainerGap())
        );
        nominee_panel4Layout.setVerticalGroup(
            nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButton5)
                    .addComponent(Nom_Name_Lable4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel4Layout.createSequentialGroup()
                .addComponent(photo_lable4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable5.setText("Name");

        javax.swing.GroupLayout nominee_panel5Layout = new javax.swing.GroupLayout(nominee_panel5);
        nominee_panel5.setLayout(nominee_panel5Layout);
        nominee_panel5Layout.setHorizontalGroup(
            nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable5, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton6)
                .addContainerGap())
        );
        nominee_panel5Layout.setVerticalGroup(
            nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton6)
                    .addComponent(Nom_Name_Lable5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel5Layout.createSequentialGroup()
                .addComponent(photo_lable5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable6.setText("Name");

        javax.swing.GroupLayout nominee_panel6Layout = new javax.swing.GroupLayout(nominee_panel6);
        nominee_panel6.setLayout(nominee_panel6Layout);
        nominee_panel6Layout.setHorizontalGroup(
            nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable6, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton7)
                .addContainerGap())
        );
        nominee_panel6Layout.setVerticalGroup(
            nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel6Layout.createSequentialGroup()
                .addGroup(nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nominee_panel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(photo_lable6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(nominee_panel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton7)
                            .addComponent(Nom_Name_Lable6))))
                .addGap(2, 2, 2))
        );

        photo_lable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable7.setText("Name");

        javax.swing.GroupLayout nominee_panel7Layout = new javax.swing.GroupLayout(nominee_panel7);
        nominee_panel7.setLayout(nominee_panel7Layout);
        nominee_panel7Layout.setHorizontalGroup(
            nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable7, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton8)
                .addContainerGap())
        );
        nominee_panel7Layout.setVerticalGroup(
            nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton8)
                    .addComponent(Nom_Name_Lable7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel7Layout.createSequentialGroup()
                .addComponent(photo_lable7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable8.setText("Name");

        javax.swing.GroupLayout nominee_panel8Layout = new javax.swing.GroupLayout(nominee_panel8);
        nominee_panel8.setLayout(nominee_panel8Layout);
        nominee_panel8Layout.setHorizontalGroup(
            nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable8, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton9)
                .addContainerGap())
        );
        nominee_panel8Layout.setVerticalGroup(
            nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel8Layout.createSequentialGroup()
                .addComponent(photo_lable8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton9)
                    .addComponent(Nom_Name_Lable8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable9.setText("Name");

        javax.swing.GroupLayout nominee_panel9Layout = new javax.swing.GroupLayout(nominee_panel9);
        nominee_panel9.setLayout(nominee_panel9Layout);
        nominee_panel9Layout.setHorizontalGroup(
            nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable9, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jRadioButton10)
                .addContainerGap())
        );
        nominee_panel9Layout.setVerticalGroup(
            nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton10)
                    .addComponent(Nom_Name_Lable9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel9Layout.createSequentialGroup()
                .addComponent(photo_lable9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable10.setText("Name");

        javax.swing.GroupLayout nominee_panel10Layout = new javax.swing.GroupLayout(nominee_panel10);
        nominee_panel10.setLayout(nominee_panel10Layout);
        nominee_panel10Layout.setHorizontalGroup(
            nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable10, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton11)
                .addContainerGap())
        );
        nominee_panel10Layout.setVerticalGroup(
            nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel10Layout.createSequentialGroup()
                .addComponent(photo_lable10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton11)
                    .addComponent(Nom_Name_Lable10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable11.setText("Name");

        javax.swing.GroupLayout nominee_panel11Layout = new javax.swing.GroupLayout(nominee_panel11);
        nominee_panel11.setLayout(nominee_panel11Layout);
        nominee_panel11Layout.setHorizontalGroup(
            nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable11, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton12)
                .addContainerGap())
        );
        nominee_panel11Layout.setVerticalGroup(
            nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel11Layout.createSequentialGroup()
                .addComponent(photo_lable11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton12)
                    .addComponent(Nom_Name_Lable11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable12.setText("Name");

        javax.swing.GroupLayout nominee_panel12Layout = new javax.swing.GroupLayout(nominee_panel12);
        nominee_panel12.setLayout(nominee_panel12Layout);
        nominee_panel12Layout.setHorizontalGroup(
            nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable12, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton13)
                .addContainerGap())
        );
        nominee_panel12Layout.setVerticalGroup(
            nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel12Layout.createSequentialGroup()
                .addComponent(photo_lable12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton13)
                    .addComponent(Nom_Name_Lable12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable13.setText("Name");

        javax.swing.GroupLayout nominee_panel13Layout = new javax.swing.GroupLayout(nominee_panel13);
        nominee_panel13.setLayout(nominee_panel13Layout);
        nominee_panel13Layout.setHorizontalGroup(
            nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable13, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton14)
                .addContainerGap())
        );
        nominee_panel13Layout.setVerticalGroup(
            nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel13Layout.createSequentialGroup()
                .addComponent(photo_lable13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton14)
                    .addComponent(Nom_Name_Lable13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable14.setText("Name");

        javax.swing.GroupLayout nominee_panel14Layout = new javax.swing.GroupLayout(nominee_panel14);
        nominee_panel14.setLayout(nominee_panel14Layout);
        nominee_panel14Layout.setHorizontalGroup(
            nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable14, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton15)
                .addContainerGap())
        );
        nominee_panel14Layout.setVerticalGroup(
            nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel14Layout.createSequentialGroup()
                .addComponent(photo_lable14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton15)
                    .addComponent(Nom_Name_Lable14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable15.setText("Name");

        javax.swing.GroupLayout nominee_panel15Layout = new javax.swing.GroupLayout(nominee_panel15);
        nominee_panel15.setLayout(nominee_panel15Layout);
        nominee_panel15Layout.setHorizontalGroup(
            nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable15, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton16)
                .addContainerGap())
        );
        nominee_panel15Layout.setVerticalGroup(
            nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel15Layout.createSequentialGroup()
                .addComponent(photo_lable15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton16)
                    .addComponent(Nom_Name_Lable15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable16.setText("Name");

        javax.swing.GroupLayout nominee_panel16Layout = new javax.swing.GroupLayout(nominee_panel16);
        nominee_panel16.setLayout(nominee_panel16Layout);
        nominee_panel16Layout.setHorizontalGroup(
            nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable16, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton17)
                .addContainerGap())
        );
        nominee_panel16Layout.setVerticalGroup(
            nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel16Layout.createSequentialGroup()
                .addComponent(photo_lable16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton17)
                    .addComponent(Nom_Name_Lable16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable17.setText("Name");

        javax.swing.GroupLayout nominee_panel17Layout = new javax.swing.GroupLayout(nominee_panel17);
        nominee_panel17.setLayout(nominee_panel17Layout);
        nominee_panel17Layout.setHorizontalGroup(
            nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable17, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton18)
                .addContainerGap())
        );
        nominee_panel17Layout.setVerticalGroup(
            nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel17Layout.createSequentialGroup()
                .addComponent(photo_lable17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton18)
                    .addComponent(Nom_Name_Lable17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable18.setText("Name");

        javax.swing.GroupLayout nominee_panel18Layout = new javax.swing.GroupLayout(nominee_panel18);
        nominee_panel18.setLayout(nominee_panel18Layout);
        nominee_panel18Layout.setHorizontalGroup(
            nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable18, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton19)
                .addContainerGap())
        );
        nominee_panel18Layout.setVerticalGroup(
            nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel18Layout.createSequentialGroup()
                .addComponent(photo_lable18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton19)
                    .addComponent(Nom_Name_Lable18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable19.setText("Name");

        javax.swing.GroupLayout nominee_panel19Layout = new javax.swing.GroupLayout(nominee_panel19);
        nominee_panel19.setLayout(nominee_panel19Layout);
        nominee_panel19Layout.setHorizontalGroup(
            nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable19, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton20)
                .addContainerGap())
        );
        nominee_panel19Layout.setVerticalGroup(
            nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel19Layout.createSequentialGroup()
                .addComponent(photo_lable19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton20)
                    .addComponent(Nom_Name_Lable19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        photo_lable19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nominee_panel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nominee_panel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        jButton1.setForeground(new java.awt.Color(228, 11, 11));
        jButton1.setText("Reset ");

        jButton2.setForeground(new java.awt.Color(8, 6, 148));
        jButton2.setText("Preview");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(148, 9, 9));
        jLabel1.setText("List of Contestants");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poweredby_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(poweredby_label, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        poweredby_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/clublogoevm.jpg"))); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(109, 15, 15));
        jLabel5.setText("Selected Candidates ");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nominee_panel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable20.setText("Name");

        javax.swing.GroupLayout nominee_panel20Layout = new javax.swing.GroupLayout(nominee_panel20);
        nominee_panel20.setLayout(nominee_panel20Layout);
        nominee_panel20Layout.setHorizontalGroup(
            nominee_panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable20, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        nominee_panel20Layout.setVerticalGroup(
            nominee_panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel20Layout.createSequentialGroup()
                .addComponent(photo_lable20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable21.setText("Name");

        javax.swing.GroupLayout nominee_panel21Layout = new javax.swing.GroupLayout(nominee_panel21);
        nominee_panel21.setLayout(nominee_panel21Layout);
        nominee_panel21Layout.setHorizontalGroup(
            nominee_panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable21, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        nominee_panel21Layout.setVerticalGroup(
            nominee_panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel21Layout.createSequentialGroup()
                .addComponent(photo_lable21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(nominee_panel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable21)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        photo_lable21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable22.setText("Name");

        javax.swing.GroupLayout nominee_panel22Layout = new javax.swing.GroupLayout(nominee_panel22);
        nominee_panel22.setLayout(nominee_panel22Layout);
        nominee_panel22Layout.setHorizontalGroup(
            nominee_panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable22, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        nominee_panel22Layout.setVerticalGroup(
            nominee_panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(photo_lable22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        photo_lable22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable23.setText("Name");

        javax.swing.GroupLayout nominee_panel23Layout = new javax.swing.GroupLayout(nominee_panel23);
        nominee_panel23.setLayout(nominee_panel23Layout);
        nominee_panel23Layout.setHorizontalGroup(
            nominee_panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable23, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        nominee_panel23Layout.setVerticalGroup(
            nominee_panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable23)
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(photo_lable23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        photo_lable23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable24.setText("Name");

        javax.swing.GroupLayout nominee_panel24Layout = new javax.swing.GroupLayout(nominee_panel24);
        nominee_panel24.setLayout(nominee_panel24Layout);
        nominee_panel24Layout.setHorizontalGroup(
            nominee_panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable24, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        nominee_panel24Layout.setVerticalGroup(
            nominee_panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel24Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Nom_Name_Lable24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel24Layout.createSequentialGroup()
                .addComponent(photo_lable24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable25.setText("Name");

        javax.swing.GroupLayout nominee_panel25Layout = new javax.swing.GroupLayout(nominee_panel25);
        nominee_panel25.setLayout(nominee_panel25Layout);
        nominee_panel25Layout.setHorizontalGroup(
            nominee_panel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable25, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        nominee_panel25Layout.setVerticalGroup(
            nominee_panel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel25Layout.createSequentialGroup()
                .addComponent(photo_lable25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable26.setText("Name");

        javax.swing.GroupLayout nominee_panel26Layout = new javax.swing.GroupLayout(nominee_panel26);
        nominee_panel26.setLayout(nominee_panel26Layout);
        nominee_panel26Layout.setHorizontalGroup(
            nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable26, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        nominee_panel26Layout.setVerticalGroup(
            nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel26Layout.createSequentialGroup()
                .addGroup(nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nominee_panel26Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(photo_lable26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(nominee_panel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Nom_Name_Lable26)))
                .addGap(2, 2, 2))
        );

        photo_lable26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable27.setText("Name");

        javax.swing.GroupLayout nominee_panel27Layout = new javax.swing.GroupLayout(nominee_panel27);
        nominee_panel27.setLayout(nominee_panel27Layout);
        nominee_panel27Layout.setHorizontalGroup(
            nominee_panel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable27, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        nominee_panel27Layout.setVerticalGroup(
            nominee_panel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel27Layout.createSequentialGroup()
                .addComponent(photo_lable27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable28.setText("Name");

        javax.swing.GroupLayout nominee_panel28Layout = new javax.swing.GroupLayout(nominee_panel28);
        nominee_panel28.setLayout(nominee_panel28Layout);
        nominee_panel28Layout.setHorizontalGroup(
            nominee_panel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable28, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        nominee_panel28Layout.setVerticalGroup(
            nominee_panel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel28Layout.createSequentialGroup()
                .addComponent(photo_lable28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(nominee_panel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable28)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        photo_lable28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Nom_Name_Lable29.setText("Name");

        javax.swing.GroupLayout nominee_panel29Layout = new javax.swing.GroupLayout(nominee_panel29);
        nominee_panel29.setLayout(nominee_panel29Layout);
        nominee_panel29Layout.setHorizontalGroup(
            nominee_panel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nom_Name_Lable29, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        nominee_panel29Layout.setVerticalGroup(
            nominee_panel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nom_Name_Lable29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel29Layout.createSequentialGroup()
                .addComponent(photo_lable29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nominee_panel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nominee_panel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nominee_panel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nominee_panel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nominee_panel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nominee_panel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel4);

        finalSubmit_btn.setText("Final Submit");

        gobacj_btn.setText("Go Back ");
        gobacj_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobacj_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectedmember_panelLayout = new javax.swing.GroupLayout(selectedmember_panel);
        selectedmember_panel.setLayout(selectedmember_panelLayout);
        selectedmember_panelLayout.setHorizontalGroup(
            selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedmember_panelLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(gobacj_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(finalSubmit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(selectedmember_panelLayout.createSequentialGroup()
                .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        selectedmember_panelLayout.setVerticalGroup(
            selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedmember_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finalSubmit_btn)
                            .addComponent(gobacj_btn))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/clublogoevm.jpg"))); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedmember_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectedmember_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    int cnl_req = JOptionPane.showConfirmDialog(jPanel2, " Are you sure you want to submit form ??" ,"Confirmation",JOptionPane.YES_NO_OPTION );
       if(cnl_req == JOptionPane.YES_OPTION){
               
         selectedmember_panel.setVisible(true);
         jPanel1.setVisible(false);
       }
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void gobacj_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobacj_btnActionPerformed
          selectedmember_panel.setVisible(false);
          jPanel1.setVisible(true);
    }//GEN-LAST:event_gobacj_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nom_Name_Lable;
    private javax.swing.JLabel Nom_Name_Lable1;
    private javax.swing.JLabel Nom_Name_Lable10;
    private javax.swing.JLabel Nom_Name_Lable11;
    private javax.swing.JLabel Nom_Name_Lable12;
    private javax.swing.JLabel Nom_Name_Lable13;
    private javax.swing.JLabel Nom_Name_Lable14;
    private javax.swing.JLabel Nom_Name_Lable15;
    private javax.swing.JLabel Nom_Name_Lable16;
    private javax.swing.JLabel Nom_Name_Lable17;
    private javax.swing.JLabel Nom_Name_Lable18;
    private javax.swing.JLabel Nom_Name_Lable19;
    private javax.swing.JLabel Nom_Name_Lable2;
    private javax.swing.JLabel Nom_Name_Lable20;
    private javax.swing.JLabel Nom_Name_Lable21;
    private javax.swing.JLabel Nom_Name_Lable22;
    private javax.swing.JLabel Nom_Name_Lable23;
    private javax.swing.JLabel Nom_Name_Lable24;
    private javax.swing.JLabel Nom_Name_Lable25;
    private javax.swing.JLabel Nom_Name_Lable26;
    private javax.swing.JLabel Nom_Name_Lable27;
    private javax.swing.JLabel Nom_Name_Lable28;
    private javax.swing.JLabel Nom_Name_Lable29;
    private javax.swing.JLabel Nom_Name_Lable3;
    private javax.swing.JLabel Nom_Name_Lable4;
    private javax.swing.JLabel Nom_Name_Lable5;
    private javax.swing.JLabel Nom_Name_Lable6;
    private javax.swing.JLabel Nom_Name_Lable7;
    private javax.swing.JLabel Nom_Name_Lable8;
    private javax.swing.JLabel Nom_Name_Lable9;
    private javax.swing.JButton finalSubmit_btn;
    private javax.swing.JButton gobacj_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JRadioButton jRadioButton19;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton20;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel nominee_panel;
    private javax.swing.JPanel nominee_panel1;
    private javax.swing.JPanel nominee_panel10;
    private javax.swing.JPanel nominee_panel11;
    private javax.swing.JPanel nominee_panel12;
    private javax.swing.JPanel nominee_panel13;
    private javax.swing.JPanel nominee_panel14;
    private javax.swing.JPanel nominee_panel15;
    private javax.swing.JPanel nominee_panel16;
    private javax.swing.JPanel nominee_panel17;
    private javax.swing.JPanel nominee_panel18;
    private javax.swing.JPanel nominee_panel19;
    private javax.swing.JPanel nominee_panel2;
    private javax.swing.JPanel nominee_panel20;
    private javax.swing.JPanel nominee_panel21;
    private javax.swing.JPanel nominee_panel22;
    private javax.swing.JPanel nominee_panel23;
    private javax.swing.JPanel nominee_panel24;
    private javax.swing.JPanel nominee_panel25;
    private javax.swing.JPanel nominee_panel26;
    private javax.swing.JPanel nominee_panel27;
    private javax.swing.JPanel nominee_panel28;
    private javax.swing.JPanel nominee_panel29;
    private javax.swing.JPanel nominee_panel3;
    private javax.swing.JPanel nominee_panel4;
    private javax.swing.JPanel nominee_panel5;
    private javax.swing.JPanel nominee_panel6;
    private javax.swing.JPanel nominee_panel7;
    private javax.swing.JPanel nominee_panel8;
    private javax.swing.JPanel nominee_panel9;
    private javax.swing.JLabel photo_lable;
    private javax.swing.JLabel photo_lable1;
    private javax.swing.JLabel photo_lable10;
    private javax.swing.JLabel photo_lable11;
    private javax.swing.JLabel photo_lable12;
    private javax.swing.JLabel photo_lable13;
    private javax.swing.JLabel photo_lable14;
    private javax.swing.JLabel photo_lable15;
    private javax.swing.JLabel photo_lable16;
    private javax.swing.JLabel photo_lable17;
    private javax.swing.JLabel photo_lable18;
    private javax.swing.JLabel photo_lable19;
    private javax.swing.JLabel photo_lable2;
    private javax.swing.JLabel photo_lable20;
    private javax.swing.JLabel photo_lable21;
    private javax.swing.JLabel photo_lable22;
    private javax.swing.JLabel photo_lable23;
    private javax.swing.JLabel photo_lable24;
    private javax.swing.JLabel photo_lable25;
    private javax.swing.JLabel photo_lable26;
    private javax.swing.JLabel photo_lable27;
    private javax.swing.JLabel photo_lable28;
    private javax.swing.JLabel photo_lable29;
    private javax.swing.JLabel photo_lable3;
    private javax.swing.JLabel photo_lable4;
    private javax.swing.JLabel photo_lable5;
    private javax.swing.JLabel photo_lable6;
    private javax.swing.JLabel photo_lable7;
    private javax.swing.JLabel photo_lable8;
    private javax.swing.JLabel photo_lable9;
    private javax.swing.JLabel poweredby_label;
    private javax.swing.JPanel selectedmember_panel;
    // End of variables declaration//GEN-END:variables



 public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
        selectedmember_panel.setVisible(false);
      
       
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       this.m_App = app;
     
       
      
    }

    public Object getBean() {
         return this;
    }





}
