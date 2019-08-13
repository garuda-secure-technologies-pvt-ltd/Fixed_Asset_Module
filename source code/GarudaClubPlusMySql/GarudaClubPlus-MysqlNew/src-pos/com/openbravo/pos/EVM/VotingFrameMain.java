
package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.pos.forms.AppView;


import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import com.openbravo.pos.EVM.VotingFrameMainTableModel.ContestantInfo;




public class VotingFrameMain extends  javax.swing.JDialog{

    private AppView app;
    private boolean flag;
    public VotingFrameMainTableModel VotingFrameMain_Table_Model;
    
    
    public VotingFrameMain(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }

    
  
    
    public VotingFrameMain(java.awt.Dialog parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
     public VotingFrameMain(java.awt.Frame parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        nominee_panel = new javax.swing.JPanel();
        photo_lable = new javax.swing.JLabel();
        SrNo_label2 = new javax.swing.JLabel();
        RadioButton2 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        nominee_panel1 = new javax.swing.JPanel();
        photo_lable1 = new javax.swing.JLabel();
        SrNo_label3 = new javax.swing.JLabel();
        RadioButton3 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        nominee_panel2 = new javax.swing.JPanel();
        photo_lable2 = new javax.swing.JLabel();
        SrNo_label1 = new javax.swing.JLabel();
        RadioButton1 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        nominee_panel3 = new javax.swing.JPanel();
        photo_lable3 = new javax.swing.JLabel();
        SrNo_label11 = new javax.swing.JLabel();
        RadioButton11 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        nominee_panel4 = new javax.swing.JPanel();
        photo_lable4 = new javax.swing.JLabel();
        SrNo_label12 = new javax.swing.JLabel();
        RadioButton12 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        nominee_panel5 = new javax.swing.JPanel();
        photo_lable5 = new javax.swing.JLabel();
        SrNo_label13 = new javax.swing.JLabel();
        RadioButton13 = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        nominee_panel6 = new javax.swing.JPanel();
        photo_lable6 = new javax.swing.JLabel();
        SrNo_label4 = new javax.swing.JLabel();
        RadioButton4 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        nominee_panel7 = new javax.swing.JPanel();
        photo_lable7 = new javax.swing.JLabel();
        SrNo_label14 = new javax.swing.JLabel();
        RadioButton14 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        nominee_panel8 = new javax.swing.JPanel();
        photo_lable8 = new javax.swing.JLabel();
        SrNo_label5 = new javax.swing.JLabel();
        RadioButton5 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        nominee_panel9 = new javax.swing.JPanel();
        photo_lable9 = new javax.swing.JLabel();
        SrNo_label15 = new javax.swing.JLabel();
        RadioButton15 = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        nominee_panel10 = new javax.swing.JPanel();
        photo_lable10 = new javax.swing.JLabel();
        SrNo_label16 = new javax.swing.JLabel();
        RadioButton16 = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        nominee_panel11 = new javax.swing.JPanel();
        photo_lable11 = new javax.swing.JLabel();
        SrNo_label6 = new javax.swing.JLabel();
        RadioButton6 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        nominee_panel12 = new javax.swing.JPanel();
        photo_lable12 = new javax.swing.JLabel();
        SrNo_label8 = new javax.swing.JLabel();
        RadioButton8 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        nominee_panel13 = new javax.swing.JPanel();
        photo_lable13 = new javax.swing.JLabel();
        SrNo_label17 = new javax.swing.JLabel();
        RadioButton17 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        nominee_panel14 = new javax.swing.JPanel();
        photo_lable14 = new javax.swing.JLabel();
        SrNo_label7 = new javax.swing.JLabel();
        RadioButton7 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        nominee_panel15 = new javax.swing.JPanel();
        photo_lable15 = new javax.swing.JLabel();
        SrNo_label9 = new javax.swing.JLabel();
        RadioButton9 = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        nominee_panel16 = new javax.swing.JPanel();
        photo_lable16 = new javax.swing.JLabel();
        SrNo_label18 = new javax.swing.JLabel();
        RadioButton18 = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        nominee_panel17 = new javax.swing.JPanel();
        photo_lable17 = new javax.swing.JLabel();
        SrNo_label19 = new javax.swing.JLabel();
        RadioButton19 = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        nominee_panel18 = new javax.swing.JPanel();
        photo_lable18 = new javax.swing.JLabel();
        SrNo_label10 = new javax.swing.JLabel();
        RadioButton10 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        nominee_panel19 = new javax.swing.JPanel();
        photo_lable19 = new javax.swing.JLabel();
        SrNo_label20 = new javax.swing.JLabel();
        RadioButton20 = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        priview_btn = new javax.swing.JButton();
        poweredby_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        minute_label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        secound_label = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        selectedmember_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        nominee_panel20 = new javax.swing.JPanel();
        photo_lable20 = new javax.swing.JLabel();
        F_SRNO_label2 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        nominee_panel21 = new javax.swing.JPanel();
        photo_lable21 = new javax.swing.JLabel();
        F_SRNO_label3 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        nominee_panel22 = new javax.swing.JPanel();
        photo_lable22 = new javax.swing.JLabel();
        F_SRNO_label1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        nominee_panel23 = new javax.swing.JPanel();
        photo_lable23 = new javax.swing.JLabel();
        F_SRNO_label6 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        nominee_panel24 = new javax.swing.JPanel();
        photo_lable24 = new javax.swing.JLabel();
        F_SRNO_label7 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        nominee_panel25 = new javax.swing.JPanel();
        photo_lable25 = new javax.swing.JLabel();
        F_SRNO_label8 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        nominee_panel26 = new javax.swing.JPanel();
        photo_lable26 = new javax.swing.JLabel();
        F_SRNO_label4 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        nominee_panel27 = new javax.swing.JPanel();
        photo_lable27 = new javax.swing.JLabel();
        F_SRNO_label9 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        nominee_panel28 = new javax.swing.JPanel();
        photo_lable28 = new javax.swing.JLabel();
        F_SRNO_label5 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        nominee_panel29 = new javax.swing.JPanel();
        photo_lable29 = new javax.swing.JLabel();
        F_SRNO_label10 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        finalSubmit_btn = new javax.swing.JButton();
        gobacj_btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        minute_label2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        secound_label2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nominee_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label2.setText("Name");

        RadioButton2.setForeground(new java.awt.Color(19, 166, 30));

        jLabel11.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panelLayout = new javax.swing.GroupLayout(nominee_panel);
        nominee_panel.setLayout(nominee_panelLayout);
        nominee_panelLayout.setHorizontalGroup(
            nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton2)
                .addContainerGap())
        );
        nominee_panelLayout.setVerticalGroup(
            nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panelLayout.createSequentialGroup()
                .addGroup(nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label2)
                        .addComponent(jLabel11)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label3.setText("Name");

        RadioButton3.setForeground(new java.awt.Color(14, 151, 39));

        jLabel12.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel1Layout = new javax.swing.GroupLayout(nominee_panel1);
        nominee_panel1.setLayout(nominee_panel1Layout);
        nominee_panel1Layout.setHorizontalGroup(
            nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton3)
                .addContainerGap())
        );
        nominee_panel1Layout.setVerticalGroup(
            nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel1Layout.createSequentialGroup()
                .addGroup(nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label3)
                        .addComponent(jLabel12)))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        photo_lable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label1.setText("Name");

        RadioButton1.setBackground(new java.awt.Color(244, 242, 231));
        RadioButton1.setForeground(new java.awt.Color(21, 146, 25));

        jLabel9.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel2Layout = new javax.swing.GroupLayout(nominee_panel2);
        nominee_panel2.setLayout(nominee_panel2Layout);
        nominee_panel2Layout.setHorizontalGroup(
            nominee_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(RadioButton1)
                .addContainerGap())
        );
        nominee_panel2Layout.setVerticalGroup(
            nominee_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(photo_lable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(nominee_panel2Layout.createSequentialGroup()
                .addGroup(nominee_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SrNo_label1)
                    .addComponent(jLabel9))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label11.setText("Name");

        jLabel20.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel3Layout = new javax.swing.GroupLayout(nominee_panel3);
        nominee_panel3.setLayout(nominee_panel3Layout);
        nominee_panel3Layout.setHorizontalGroup(
            nominee_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label11, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton11)
                .addContainerGap())
        );
        nominee_panel3Layout.setVerticalGroup(
            nominee_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(photo_lable3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(nominee_panel3Layout.createSequentialGroup()
                .addGroup(nominee_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SrNo_label11)
                    .addComponent(jLabel20))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton11.setForeground(Color.GREEN);

        nominee_panel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label12.setText("Name");

        jLabel21.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel4Layout = new javax.swing.GroupLayout(nominee_panel4);
        nominee_panel4.setLayout(nominee_panel4Layout);
        nominee_panel4Layout.setHorizontalGroup(
            nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label12, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton12)
                .addContainerGap())
        );
        nominee_panel4Layout.setVerticalGroup(
            nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel4Layout.createSequentialGroup()
                .addGroup(nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label12)
                        .addComponent(jLabel21)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton12.setForeground(Color.GREEN);

        nominee_panel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label13.setText("Name");

        jLabel22.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel5Layout = new javax.swing.GroupLayout(nominee_panel5);
        nominee_panel5.setLayout(nominee_panel5Layout);
        nominee_panel5Layout.setHorizontalGroup(
            nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label13, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton13)
                .addContainerGap())
        );
        nominee_panel5Layout.setVerticalGroup(
            nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel5Layout.createSequentialGroup()
                .addGroup(nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label13)
                        .addComponent(jLabel22)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton13.setForeground(Color.GREEN);

        nominee_panel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label4.setText("Name");

        RadioButton4.setForeground(new java.awt.Color(11, 147, 47));

        jLabel13.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel6Layout = new javax.swing.GroupLayout(nominee_panel6);
        nominee_panel6.setLayout(nominee_panel6Layout);
        nominee_panel6Layout.setHorizontalGroup(
            nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton4)
                .addContainerGap())
        );
        nominee_panel6Layout.setVerticalGroup(
            nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel6Layout.createSequentialGroup()
                .addGroup(nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nominee_panel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(RadioButton4))
                    .addGroup(nominee_panel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nominee_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(SrNo_label4)
                                .addComponent(jLabel13))
                            .addComponent(photo_lable6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2))
        );

        photo_lable6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label14.setText("Name");

        jLabel23.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel7Layout = new javax.swing.GroupLayout(nominee_panel7);
        nominee_panel7.setLayout(nominee_panel7Layout);
        nominee_panel7Layout.setHorizontalGroup(
            nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(11, 11, 11)
                .addComponent(SrNo_label14, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton14)
                .addContainerGap())
        );
        nominee_panel7Layout.setVerticalGroup(
            nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel7Layout.createSequentialGroup()
                .addGroup(nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label14)
                        .addComponent(jLabel23)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton14.setForeground(Color.GREEN);

        nominee_panel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label5.setText("Name");

        jLabel14.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel8Layout = new javax.swing.GroupLayout(nominee_panel8);
        nominee_panel8.setLayout(nominee_panel8Layout);
        nominee_panel8Layout.setHorizontalGroup(
            nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label5, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton5)
                .addContainerGap())
        );
        nominee_panel8Layout.setVerticalGroup(
            nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel8Layout.createSequentialGroup()
                .addGroup(nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label5)
                        .addComponent(jLabel14)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton5.setForeground(Color.GREEN);

        nominee_panel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label15.setText("Name");

        jLabel24.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel9Layout = new javax.swing.GroupLayout(nominee_panel9);
        nominee_panel9.setLayout(nominee_panel9Layout);
        nominee_panel9Layout.setHorizontalGroup(
            nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addGap(12, 12, 12)
                .addComponent(SrNo_label15, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton15)
                .addContainerGap())
        );
        nominee_panel9Layout.setVerticalGroup(
            nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel9Layout.createSequentialGroup()
                .addGroup(nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label15)
                        .addComponent(jLabel24)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton15.setForeground(Color.GREEN);

        nominee_panel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label16.setText("Name");

        jLabel25.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel10Layout = new javax.swing.GroupLayout(nominee_panel10);
        nominee_panel10.setLayout(nominee_panel10Layout);
        nominee_panel10Layout.setHorizontalGroup(
            nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label16, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton16)
                .addContainerGap())
        );
        nominee_panel10Layout.setVerticalGroup(
            nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel10Layout.createSequentialGroup()
                .addGroup(nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label16)
                        .addComponent(jLabel25)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton16.setForeground(Color.GREEN);

        nominee_panel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label6.setText("Name");

        jLabel15.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel11Layout = new javax.swing.GroupLayout(nominee_panel11);
        nominee_panel11.setLayout(nominee_panel11Layout);
        nominee_panel11Layout.setHorizontalGroup(
            nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton6)
                .addContainerGap())
        );
        nominee_panel11Layout.setVerticalGroup(
            nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel11Layout.createSequentialGroup()
                .addGroup(nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label6)
                        .addComponent(jLabel15)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton6.setForeground(Color.GREEN);

        nominee_panel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label8.setText("Name");

        jLabel17.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel12Layout = new javax.swing.GroupLayout(nominee_panel12);
        nominee_panel12.setLayout(nominee_panel12Layout);
        nominee_panel12Layout.setHorizontalGroup(
            nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton8)
                .addContainerGap())
        );
        nominee_panel12Layout.setVerticalGroup(
            nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel12Layout.createSequentialGroup()
                .addGroup(nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label8)
                        .addComponent(jLabel17)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton8.setForeground(Color.GREEN);

        nominee_panel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label17.setText("Name");

        jLabel26.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel13Layout = new javax.swing.GroupLayout(nominee_panel13);
        nominee_panel13.setLayout(nominee_panel13Layout);
        nominee_panel13Layout.setHorizontalGroup(
            nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label17, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton17)
                .addContainerGap())
        );
        nominee_panel13Layout.setVerticalGroup(
            nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel13Layout.createSequentialGroup()
                .addGroup(nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label17)
                        .addComponent(jLabel26)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton17.setForeground(Color.GREEN);

        nominee_panel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label7.setText("Name");

        jLabel16.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel14Layout = new javax.swing.GroupLayout(nominee_panel14);
        nominee_panel14.setLayout(nominee_panel14Layout);
        nominee_panel14Layout.setHorizontalGroup(
            nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton7)
                .addContainerGap())
        );
        nominee_panel14Layout.setVerticalGroup(
            nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel14Layout.createSequentialGroup()
                .addGroup(nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label7)
                        .addComponent(jLabel16)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton7.setForeground(Color.GREEN);

        nominee_panel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label9.setText("Name");

        jLabel18.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel15Layout = new javax.swing.GroupLayout(nominee_panel15);
        nominee_panel15.setLayout(nominee_panel15Layout);
        nominee_panel15Layout.setHorizontalGroup(
            nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton9)
                .addContainerGap())
        );
        nominee_panel15Layout.setVerticalGroup(
            nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel15Layout.createSequentialGroup()
                .addGroup(nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label9)
                        .addComponent(jLabel18)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton9.setForeground(Color.GREEN);

        nominee_panel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label18.setText("Name");

        jLabel27.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel16Layout = new javax.swing.GroupLayout(nominee_panel16);
        nominee_panel16.setLayout(nominee_panel16Layout);
        nominee_panel16Layout.setHorizontalGroup(
            nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label18, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton18)
                .addContainerGap())
        );
        nominee_panel16Layout.setVerticalGroup(
            nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel16Layout.createSequentialGroup()
                .addGroup(nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label18)
                        .addComponent(jLabel27)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton18.setForeground(Color.GREEN);

        nominee_panel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label19.setText("Name");

        jLabel28.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel17Layout = new javax.swing.GroupLayout(nominee_panel17);
        nominee_panel17.setLayout(nominee_panel17Layout);
        nominee_panel17Layout.setHorizontalGroup(
            nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label19, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton19)
                .addContainerGap())
        );
        nominee_panel17Layout.setVerticalGroup(
            nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel17Layout.createSequentialGroup()
                .addGroup(nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label19)
                        .addComponent(jLabel28)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton19.setForeground(Color.GREEN);

        nominee_panel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label10.setText("Name");

        jLabel19.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel18Layout = new javax.swing.GroupLayout(nominee_panel18);
        nominee_panel18.setLayout(nominee_panel18Layout);
        nominee_panel18Layout.setHorizontalGroup(
            nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label10, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton10)
                .addContainerGap())
        );
        nominee_panel18Layout.setVerticalGroup(
            nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel18Layout.createSequentialGroup()
                .addGroup(nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label10)
                        .addComponent(jLabel19)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton10.setForeground(Color.GREEN);

        nominee_panel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SrNo_label20.setText("Name");

        jLabel29.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel19Layout = new javax.swing.GroupLayout(nominee_panel19);
        nominee_panel19.setLayout(nominee_panel19Layout);
        nominee_panel19Layout.setHorizontalGroup(
            nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SrNo_label20, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton20)
                .addContainerGap())
        );
        nominee_panel19Layout.setVerticalGroup(
            nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioButton20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nominee_panel19Layout.createSequentialGroup()
                .addGroup(nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SrNo_label20)
                        .addComponent(jLabel29)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N
        RadioButton20.setForeground(Color.GREEN);

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
                .addContainerGap(63, Short.MAX_VALUE))
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        priview_btn.setForeground(new java.awt.Color(8, 6, 148));
        priview_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priview_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(148, 9, 9));
        jLabel1.setText("List of Contestants");

        minute_label.setForeground(new java.awt.Color(212, 23, 23));
        minute_label.setText("00");

        jLabel7.setText(":");

        secound_label.setForeground(new java.awt.Color(202, 20, 20));
        secound_label.setText("00");

        jLabel6.setForeground(new java.awt.Color(7, 51, 217));
        jLabel6.setText("Time Remaining :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minute_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secound_label)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minute_label)
                    .addComponent(jLabel7)
                    .addComponent(secound_label)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(poweredby_label, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(priview_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(priview_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(poweredby_label, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reset.jpg"))); // NOI18N
        priview_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/preview.jpg"))); // NOI18N
        poweredby_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/clublogoevm.jpg"))); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(109, 15, 15));
        jLabel5.setText("Selected Candidates ");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nominee_panel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label2.setText("Name");

        jLabel31.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel20Layout = new javax.swing.GroupLayout(nominee_panel20);
        nominee_panel20.setLayout(nominee_panel20Layout);
        nominee_panel20Layout.setHorizontalGroup(
            nominee_panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel20Layout.setVerticalGroup(
            nominee_panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel20Layout.createSequentialGroup()
                .addGroup(nominee_panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label2)
                        .addComponent(jLabel31)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label3.setText("Name");

        jLabel32.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel21Layout = new javax.swing.GroupLayout(nominee_panel21);
        nominee_panel21.setLayout(nominee_panel21Layout);
        nominee_panel21Layout.setHorizontalGroup(
            nominee_panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel21Layout.setVerticalGroup(
            nominee_panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel21Layout.createSequentialGroup()
                .addGroup(nominee_panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label3)
                        .addComponent(jLabel32)))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        photo_lable21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label1.setText("Name");

        jLabel30.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel22Layout = new javax.swing.GroupLayout(nominee_panel22);
        nominee_panel22.setLayout(nominee_panel22Layout);
        nominee_panel22Layout.setHorizontalGroup(
            nominee_panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        nominee_panel22Layout.setVerticalGroup(
            nominee_panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(photo_lable22, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
            .addGroup(nominee_panel22Layout.createSequentialGroup()
                .addGroup(nominee_panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(F_SRNO_label1)
                    .addComponent(jLabel30))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label6.setText("Name");

        jLabel35.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel23Layout = new javax.swing.GroupLayout(nominee_panel23);
        nominee_panel23.setLayout(nominee_panel23Layout);
        nominee_panel23Layout.setHorizontalGroup(
            nominee_panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel23Layout.setVerticalGroup(
            nominee_panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(photo_lable23, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
            .addGroup(nominee_panel23Layout.createSequentialGroup()
                .addGroup(nominee_panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(F_SRNO_label6)
                    .addComponent(jLabel35))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label7.setText("Name");

        jLabel36.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel24Layout = new javax.swing.GroupLayout(nominee_panel24);
        nominee_panel24.setLayout(nominee_panel24Layout);
        nominee_panel24Layout.setHorizontalGroup(
            nominee_panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel24Layout.setVerticalGroup(
            nominee_panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel24Layout.createSequentialGroup()
                .addGroup(nominee_panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label7)
                        .addComponent(jLabel36)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label8.setText("Name");

        jLabel37.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel25Layout = new javax.swing.GroupLayout(nominee_panel25);
        nominee_panel25.setLayout(nominee_panel25Layout);
        nominee_panel25Layout.setHorizontalGroup(
            nominee_panel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel25Layout.setVerticalGroup(
            nominee_panel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel25Layout.createSequentialGroup()
                .addGroup(nominee_panel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label8)
                        .addComponent(jLabel37)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label4.setText("Name");

        jLabel33.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel26Layout = new javax.swing.GroupLayout(nominee_panel26);
        nominee_panel26.setLayout(nominee_panel26Layout);
        nominee_panel26Layout.setHorizontalGroup(
            nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel26Layout.setVerticalGroup(
            nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel26Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nominee_panel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label4)
                        .addComponent(jLabel33))
                    .addComponent(photo_lable26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        photo_lable26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label9.setText("Name");

        jLabel38.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel27Layout = new javax.swing.GroupLayout(nominee_panel27);
        nominee_panel27.setLayout(nominee_panel27Layout);
        nominee_panel27Layout.setHorizontalGroup(
            nominee_panel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label9, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel27Layout.setVerticalGroup(
            nominee_panel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel27Layout.createSequentialGroup()
                .addGroup(nominee_panel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label9)
                        .addComponent(jLabel38)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        photo_lable27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label5.setText("Name");

        jLabel34.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel28Layout = new javax.swing.GroupLayout(nominee_panel28);
        nominee_panel28.setLayout(nominee_panel28Layout);
        nominee_panel28Layout.setHorizontalGroup(
            nominee_panel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nominee_panel28Layout.setVerticalGroup(
            nominee_panel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel28Layout.createSequentialGroup()
                .addGroup(nominee_panel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label5)
                        .addComponent(jLabel34)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        photo_lable28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/yast_sysadmin.png"))); // NOI18N

        nominee_panel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        F_SRNO_label10.setText("Name");

        jLabel39.setText("Sr. No. :");

        javax.swing.GroupLayout nominee_panel29Layout = new javax.swing.GroupLayout(nominee_panel29);
        nominee_panel29.setLayout(nominee_panel29Layout);
        nominee_panel29Layout.setHorizontalGroup(
            nominee_panel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo_lable29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(F_SRNO_label10, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        nominee_panel29Layout.setVerticalGroup(
            nominee_panel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominee_panel29Layout.createSequentialGroup()
                .addGroup(nominee_panel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo_lable29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nominee_panel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_SRNO_label10)
                        .addComponent(jLabel39)))
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
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel4);

        gobacj_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobacj_btnActionPerformed(evt);
            }
        });

        minute_label2.setForeground(new java.awt.Color(177, 5, 5));
        minute_label2.setText("00");

        jLabel8.setText(":");

        secound_label2.setForeground(new java.awt.Color(179, 11, 11));
        secound_label2.setText("00");

        jLabel10.setForeground(new java.awt.Color(38, 45, 240));
        jLabel10.setText("Time Remaining : ");

        javax.swing.GroupLayout selectedmember_panelLayout = new javax.swing.GroupLayout(selectedmember_panel);
        selectedmember_panel.setLayout(selectedmember_panelLayout);
        selectedmember_panelLayout.setHorizontalGroup(
            selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedmember_panelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(gobacj_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(finalSubmit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(selectedmember_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(selectedmember_panelLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(minute_label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secound_label2)
                .addGap(62, 62, 62))
        );
        selectedmember_panelLayout.setVerticalGroup(
            selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedmember_panelLayout.createSequentialGroup()
                .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(minute_label2)
                            .addComponent(jLabel8)
                            .addComponent(secound_label2)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(selectedmember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectedmember_panelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addComponent(finalSubmit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gobacj_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        finalSubmit_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/submit1.jpg"))); // NOI18N
        gobacj_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/go back.jpg"))); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/clublogoevm.jpg"))); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(selectedmember_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedmember_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int noOfCandidatesTobeSelected= 0;
    int noOfSelectedMember = 0;
    private void priview_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priview_btnActionPerformed
        int bill = JOptionPane.showConfirmDialog(jPanel2, "Are you sure you want to preview your selection ?? " , "Preview Menu" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
                 
                 
                
           // get max number of selection to be done...............................................      
                 try{
                     noOfCandidatesTobeSelected=getFinalNoOfCandidates();
                 }
                 catch(BasicException e){
                     
                 }
                 catch(ParseException e){
                     
                 }
                 noOfSelectedMember=0;
    // selected member counting.............................................................              
                 if(RadioButton1.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton2.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton3.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton4.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton5.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton6.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton7.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton8.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton9.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton10.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton11.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton12.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton13.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton14.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton15.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton16.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton17.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton18.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton19.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 if(RadioButton20.isSelected()){
                     noOfSelectedMember=noOfSelectedMember+1;
                 }
                 
                 
                 
                 if(noOfSelectedMember==noOfCandidatesTobeSelected){
                     
                         jPanel1.setVisible(false);
                         selectedmember_panel.setVisible(true);
                     
                 }
                 else{
                     
                      JOptionPane.showMessageDialog(this, "Minimum no. of total selection is "+noOfCandidatesTobeSelected+".  \n You "
                              + "have selected "+noOfSelectedMember+".  \n Please selected the correctly to avoid invalid Vote.  \n \n \n "
                              + "  ಮಿನಿಮಂ ನೋ. ಆಫ್ ಟೋಟಲ್ ಸೆಲೆಕ್ಷನ್ ಇಸ್ ೧೫ .  \n   ಯು ಹವೆ ಸೆಲೆಕ್ಟೆಡ್  "+noOfSelectedMember+" . \n  ಪ್ಲೀಸ್ ಸೆಲೆಕ್ಟ್  ಪ್ರೋಪೆರ್ಲ್ಯ್ ಟು ಅವಾಯ್ಡ್ ಇನ್ವಲಿದ್ ವೋಟು."
                              + "", "Warning", JOptionPane.WARNING_MESSAGE);
                     noOfSelectedMember=0;
                 }
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
             }

    }//GEN-LAST:event_priview_btnActionPerformed

    private void gobacj_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobacj_btnActionPerformed
        selectedmember_panel.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_gobacj_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         RadioButton1.setSelected(false);
         RadioButton2.setSelected(false);
         RadioButton3.setSelected(false);
         RadioButton4.setSelected(false);
         RadioButton5.setSelected(false);
         RadioButton6.setSelected(false);
         RadioButton7.setSelected(false);
         RadioButton8.setSelected(false);
         RadioButton9.setSelected(false);
         RadioButton10.setSelected(false);
         RadioButton11.setSelected(false);
         RadioButton12.setSelected(false);
         RadioButton13.setSelected(false);
         RadioButton14.setSelected(false);
         RadioButton15.setSelected(false);
         RadioButton16.setSelected(false);
         RadioButton17.setSelected(false);
         RadioButton18.setSelected(false);
         RadioButton19.setSelected(false);
         RadioButton20.setSelected(false);
                 
    }//GEN-LAST:event_jButton1ActionPerformed

    
      public static VotingFrameMain getDialog(Component parent,  AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);
        
        VotingFrameMain bill;
        
       

        if (window instanceof Frame) {
            bill = new VotingFrameMain((Frame) window , app, flag);
        } else {
            bill = new VotingFrameMain((Dialog) window, app, flag);
        }
       
        return bill;
        
        
    }
    
     public boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }
    
    

      protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    } 
    
   ScheduledExecutorService  executorService = Executors.newSingleThreadScheduledExecutor(); 
   
   
   int timeRemaining = 10;
   
   public void init() throws BasicException {
       
       
       
        initComponents();
        jPanel1.setVisible(true);
        selectedmember_panel.setVisible(false);
        
        secound_label.setText("60");
        minute_label.setText("1");
       
        loaddata();
        
        
         executorService.scheduleWithFixedDelay(new Runnable() {

                public void run() {
               
                    showTimer();
                }
                
              
            }, 1, 1, TimeUnit.SECONDS);
         
        

    }
   
   
   
   
   public void showTimer(){
       
       int CurrSec = Integer.parseInt(secound_label.getText());
       int Currmin = Integer.parseInt(minute_label.getText());
       
       if(CurrSec>0){
           CurrSec = CurrSec-1;
       }
       else{
           CurrSec=60;
           Currmin=Currmin-1;
       }
       
       
       secound_label.setText(CurrSec+"");
       secound_label2.setText(CurrSec+"");
       minute_label.setText(Currmin+"");
       minute_label2.setText(Currmin+"");
   }
   
    public void loaddata() throws BasicException{
         VotingFrameMain_Table_Model  = VotingFrameMainTableModel.loadEmailInfo(app);
         
         int size = VotingFrameMain_Table_Model.getSize();
         String Srno[]=new String[25];
         
         
         for(int i=0;i<size;i++){
           
           ContestantInfo showdata = VotingFrameMain_Table_Model.getList().get(i);  
             Srno[i]=showdata.getSRNO();
             
          }
         
        StoreSRNO(Srno);  
       
         
    }

   
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel F_SRNO_label1;
    private javax.swing.JLabel F_SRNO_label10;
    private javax.swing.JLabel F_SRNO_label2;
    private javax.swing.JLabel F_SRNO_label3;
    private javax.swing.JLabel F_SRNO_label4;
    private javax.swing.JLabel F_SRNO_label5;
    private javax.swing.JLabel F_SRNO_label6;
    private javax.swing.JLabel F_SRNO_label7;
    private javax.swing.JLabel F_SRNO_label8;
    private javax.swing.JLabel F_SRNO_label9;
    private javax.swing.JRadioButton RadioButton1;
    private javax.swing.JRadioButton RadioButton10;
    private javax.swing.JRadioButton RadioButton11;
    private javax.swing.JRadioButton RadioButton12;
    private javax.swing.JRadioButton RadioButton13;
    private javax.swing.JRadioButton RadioButton14;
    private javax.swing.JRadioButton RadioButton15;
    private javax.swing.JRadioButton RadioButton16;
    private javax.swing.JRadioButton RadioButton17;
    private javax.swing.JRadioButton RadioButton18;
    private javax.swing.JRadioButton RadioButton19;
    private javax.swing.JRadioButton RadioButton2;
    private javax.swing.JRadioButton RadioButton20;
    private javax.swing.JRadioButton RadioButton3;
    private javax.swing.JRadioButton RadioButton4;
    private javax.swing.JRadioButton RadioButton5;
    private javax.swing.JRadioButton RadioButton6;
    private javax.swing.JRadioButton RadioButton7;
    private javax.swing.JRadioButton RadioButton8;
    private javax.swing.JRadioButton RadioButton9;
    private javax.swing.JLabel SrNo_label1;
    private javax.swing.JLabel SrNo_label10;
    private javax.swing.JLabel SrNo_label11;
    private javax.swing.JLabel SrNo_label12;
    private javax.swing.JLabel SrNo_label13;
    private javax.swing.JLabel SrNo_label14;
    private javax.swing.JLabel SrNo_label15;
    private javax.swing.JLabel SrNo_label16;
    private javax.swing.JLabel SrNo_label17;
    private javax.swing.JLabel SrNo_label18;
    private javax.swing.JLabel SrNo_label19;
    private javax.swing.JLabel SrNo_label2;
    private javax.swing.JLabel SrNo_label20;
    private javax.swing.JLabel SrNo_label3;
    private javax.swing.JLabel SrNo_label4;
    private javax.swing.JLabel SrNo_label5;
    private javax.swing.JLabel SrNo_label6;
    private javax.swing.JLabel SrNo_label7;
    private javax.swing.JLabel SrNo_label8;
    private javax.swing.JLabel SrNo_label9;
    private javax.swing.JButton finalSubmit_btn;
    private javax.swing.JButton gobacj_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel minute_label;
    private javax.swing.JLabel minute_label2;
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
    private javax.swing.JButton priview_btn;
    private javax.swing.JLabel secound_label;
    private javax.swing.JLabel secound_label2;
    private javax.swing.JPanel selectedmember_panel;
    // End of variables declaration//GEN-END:variables



 // get final no of Candidates
     
      public int  getFinalNoOfCandidates() throws ParseException , BasicException{
      
       int opass = 0;
       Object o = null;
      
       o =(Object) new PreparedSentence(app.getSession(), "SELECT Final_no_of_contest FROM evm_candidatesmaster where ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find();
       if(o!=null){
            opass = Integer.parseInt(o.toString());
            return opass; 
       }
       else{
          return 0; 
       }
      }




  public void StoreSRNO(String SRNO[]){
      
      
       if(SRNO[0]!=null)
        SrNo_label1.setText(SRNO[0]);
      if(SRNO[1]!=null)
        SrNo_label2.setText(SRNO[1]);
      if(SRNO[2]!=null)
        SrNo_label3.setText(SRNO[2]);
      if(SRNO[3]!=null)
        SrNo_label4.setText(SRNO[3]);
      if(SRNO[4]!=null)
        SrNo_label5.setText(SRNO[4]);
      if(SRNO[5]!=null)
        SrNo_label6.setText(SRNO[5]);
      if(SRNO[6]!=null)
        SrNo_label7.setText(SRNO[6]);
      if(SRNO[7]!=null)
        SrNo_label8.setText(SRNO[7]);
      if(SRNO[8]!=null)
        SrNo_label9.setText(SRNO[8]);
      if(SRNO[9]!=null)
        SrNo_label10.setText(SRNO[9]);
      if(SRNO[10]!=null)
        SrNo_label11.setText(SRNO[10]);
      if(SRNO[11]!=null)
        SrNo_label12.setText(SRNO[11]);
      if(SRNO[12]!=null)
        SrNo_label13.setText(SRNO[12]);
      if(SRNO[13]!=null)
        SrNo_label14.setText(SRNO[13]);
      if(SRNO[14]!=null)
        SrNo_label15.setText(SRNO[14]);
      if(SRNO[15]!=null)
        SrNo_label16.setText(SRNO[15]);
      if(SRNO[16]!=null)
        SrNo_label17.setText(SRNO[16]);
      if(SRNO[17]!=null)
        SrNo_label18.setText(SRNO[17]);
      if(SRNO[18]!=null)
        SrNo_label19.setText(SRNO[18]);
      if(SRNO[19]!=null)
        SrNo_label20.setText(SRNO[19]);
      
      
      
      
      
      
      
      
      
      
  }


}
