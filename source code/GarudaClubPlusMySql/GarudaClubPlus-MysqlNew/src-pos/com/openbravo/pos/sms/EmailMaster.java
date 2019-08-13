

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
//import com.openbravo.pos.Booking.HallBookingMaster;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMasterTableForCreateGroup.Emailgroup;
import com.openbravo.pos.util.Hashcypher;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.openbravo.pos.sms.EmailMasterTableModel.EmailInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;

public class EmailMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private String m_sPassword;
    private EmailMasterTableModel EmailMaster_Table_Model;
    private EmailMasterTableForCreateGroup EmailMasterTableForCreateGroup;
    private ComboBoxValModel groupname;
    private ComboBoxValModel UserName;
    List<Object> selectedEmailIdList=new ArrayList<Object>();
    List<Object> UserNameList=new ArrayList<Object>();
    List<Object> SelectedUserName_List=new ArrayList<Object>();
    List<Object> Group_TempMemList=new ArrayList<Object>();
    
    public EmailMaster() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userName_text = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password_button = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        passwordfield = new javax.swing.JPasswordField();
        save_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        mail_account_combo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        smtp_text = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        port_text = new javax.swing.JTextField();
        saveChanges_Button = new javax.swing.JButton();
        Id_label = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        noOfmails_text = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        hours_text = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        edit_Text = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        group_Name_text = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        group_Save = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        activate_Btn = new javax.swing.JButton();
        deactivate_btn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        groupName_combo = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        email_id = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Group_Final_JList = new javax.swing.JList();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        members_radio = new javax.swing.JRadioButton();
        others_radio = new javax.swing.JRadioButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        group_temp_JList = new javax.swing.JList();
        jButton12 = new javax.swing.JButton();
        permissions_email = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        UserName_Combo = new javax.swing.JComboBox();
        AddUser_btn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        SelectedUser_JList = new javax.swing.JList();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jTabbedPane1.setForeground(new java.awt.Color(153, 0, 51));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 51));
        jLabel1.setText("E - Mail Account Master ");

        jLabel2.setText("User Name :  ");

        jLabel3.setText("Password : ");

        password_button.setText("Enter Password ");
        password_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_buttonActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*  Note :  Please Note SMTP  Host Names for different accounts . ");

        passwordfield.setText("jPasswordField1");

        save_button.setText("Save ");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("List of members not having registered Email ID");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        mail_account_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gmail Account", "Yahoo Account" }));

        jLabel5.setText("Select Mail Account ");

        jLabel6.setText("SMTP Host Name : ");

        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(22, 32, 238));
        jTextArea1.setRows(5);
        jTextArea1.setText(" 1 )  Gmail   :-                   (smtp.gmail.com) Port : 587\n 2 )  Yahoo  :-                   (smtp.mail.yahoo.com)  Port : 587\n 3 )  Yahoo Mail Plus : -   (plus.smtp.mail.yahoo.com)  Port : 465\n 4 )  Hotmail  :-                 (smtp.live.com)  Port : 465");
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);

        jLabel7.setText("Port : ");

        saveChanges_Button.setText("Save Changes ");
        saveChanges_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChanges_ButtonActionPerformed(evt);
            }
        });

        Id_label.setText("jLabel8");

        jLabel12.setText("No. of mails should be sent : ");

        noOfmails_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                noOfmails_textKeyReleased(evt);
            }
        });

        jLabel13.setText("Per/");

        hours_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hours_textKeyReleased(evt);
            }
        });

        jLabel14.setText("Hour(s).");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(269, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(501, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mail_account_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Id_label)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveChanges_Button)
                            .addGap(18, 18, 18)
                            .addComponent(save_button))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(noOfmails_text, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(hours_text, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(userName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(passwordfield, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(password_button, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(smtp_text, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(port_text)))))
                            .addGap(20, 20, 20))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mail_account_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(userName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(password_button)
                    .addComponent(passwordfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(smtp_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(port_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(noOfmails_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(hours_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_button)
                    .addComponent(jButton2)
                    .addComponent(saveChanges_Button))
                .addGap(18, 18, 18)
                .addComponent(Id_label)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        passwordfield.setEnabled(false);
        mail_account_combo.setVisible(false);
        jLabel5.setVisible(false);
        Id_label.setVisible(false);

        jTabbedPane1.addTab("E-Mail Account  Master", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setForeground(new java.awt.Color(153, 0, 0));
        jButton3.setText("Deactivate ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        edit_Text.setForeground(new java.awt.Color(4, 19, 232));
        edit_Text.setText("Edit ");
        edit_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_TextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edit_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(115, 115, 115))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(edit_Text))
                .addContainerGap(282, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List of Active Email Accounts ", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Group Name:-");

        group_Name_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group_Name_textActionPerformed(evt);
            }
        });

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        group_Save.setText("Save");
        group_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group_SaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(group_Name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(group_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {group_Save, jButton5});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(group_Name_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(group_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {group_Name_text, jLabel8});

        jButton1.setText("Create Group");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("Group Details : ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jCheckBox1.setText("Show Deactivated List");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        activate_Btn.setForeground(new java.awt.Color(37, 165, 22));
        activate_Btn.setText("Activate");
        activate_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activate_BtnActionPerformed(evt);
            }
        });

        deactivate_btn.setForeground(new java.awt.Color(161, 8, 8));
        deactivate_btn.setText("Deactivate");
        deactivate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivate_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(activate_Btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deactivate_btn)
                                .addGap(20, 20, 20))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(314, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(315, 315, 315))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {activate_Btn, deactivate_btn});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(jCheckBox1)
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deactivate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activate_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {activate_Btn, deactivate_btn});

        jTabbedPane1.addTab("Create Group For Email", jPanel4);

        jLabel10.setText("Group Name:");

        groupName_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        groupName_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupName_comboItemStateChanged(evt);
            }
        });

        jLabel11.setText("Enter Email Id:");

        jButton9.setForeground(new java.awt.Color(175, 10, 10));
        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(Group_Final_JList);

        jButton10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton10.setForeground(new java.awt.Color(61, 185, 58));
        jButton10.setText("Save");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton11.setForeground(new java.awt.Color(131, 13, 13));
        jButton11.setText("Remove");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        members_radio.setForeground(new java.awt.Color(10, 36, 245));
        members_radio.setText("Members");
        members_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                members_radioItemStateChanged(evt);
            }
        });

        others_radio.setForeground(new java.awt.Color(9, 35, 243));
        others_radio.setText("Others");
        others_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                others_radioItemStateChanged(evt);
            }
        });

        group_temp_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(group_temp_JList);

        jButton12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton12.setForeground(new java.awt.Color(157, 20, 20));
        jButton12.setText("Reset");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(email_id, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(members_radio)
                .addGap(18, 18, 18)
                .addComponent(others_radio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(301, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(members_radio)
                    .addComponent(others_radio))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12)
                        .addComponent(jButton10)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupName_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupName_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List Of Group", jPanel5);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 0, 51));
        jLabel15.setText("E - Mail Permissions");

        jLabel16.setText("Select User to access email sending permission :   ");

        UserName_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        AddUser_btn.setForeground(new java.awt.Color(251, 20, 20));
        AddUser_btn.setText("Add");
        AddUser_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUser_btnActionPerformed(evt);
            }
        });

        SelectedUser_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(SelectedUser_JList);

        jLabel17.setForeground(new java.awt.Color(28, 156, 185));
        jLabel17.setText("Selected Users :  ");

        jButton6.setForeground(new java.awt.Color(14, 64, 231));
        jButton6.setText("Remove");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout permissions_emailLayout = new javax.swing.GroupLayout(permissions_email);
        permissions_email.setLayout(permissions_emailLayout);
        permissions_emailLayout.setHorizontalGroup(
            permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permissions_emailLayout.createSequentialGroup()
                .addGroup(permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(permissions_emailLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(permissions_emailLayout.createSequentialGroup()
                        .addGroup(permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(permissions_emailLayout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(UserName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddUser_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(permissions_emailLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, permissions_emailLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(120, 120, 120)
                        .addGroup(permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        permissions_emailLayout.setVerticalGroup(
            permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permissions_emailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(19, 19, 19)
                .addGroup(permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGroup(permissions_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(permissions_emailLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(UserName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(AddUser_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addGroup(permissions_emailLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Permissions", permissions_email);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void password_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_buttonActionPerformed
        String sNewPassword = Hashcypher.changePasswordforSMS(this);
        if (sNewPassword != null) {
            m_sPassword = sNewPassword; 
            passwordfield.setText(m_sPassword);
            
        }
    }//GEN-LAST:event_password_buttonActionPerformed
  String Username ; 
  int Active = 0; 
  String smtp;
  String smtp_port;
  String ID;
  Double NoOfEmails = 0.00;
  Double Hours=0.00;
  
    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
       
     if(port_text.getText()!=null && port_text.getText().trim().length()>0){ 
        
      if(smtp_text.getText()!=null && smtp_text.getText().trim().length()>0){
        
        if(userName_text.getText()!=null && userName_text.getText().trim().length()>0){
           if(m_sPassword.trim().length()>0 && m_sPassword!=null){
               if(noOfmails_text.getText()!=null && noOfmails_text.getText().trim().length()>0  && hours_text.getText()!=null && hours_text.getText().trim().length()>0){
               
                   
                    NoOfEmails = Double.parseDouble(noOfmails_text.toString());
                    Hours = Double.parseDouble(hours_text.toString());
                    smtp = smtp_text.getText().trim();
                    Username = userName_text.getText().trim();
                    Active=1;
                    smtp_port = port_text.getText().trim();

                 //  final String username = "akkipatel5@gmail.com";
                      //   final String password = "Akki@tommy1598";

                         Properties props = new Properties();
                         props.put("mail.smtp.auth", "true");
                         props.put("mail.smtp.starttls.enable", "true");
                         props.put("mail.smtp.host", "smtp.gmail.com");
                         props.put("mail.smtp.port", "587");   


                         Session session = Session.getInstance(props,
                           new javax.mail.Authenticator() {
                                 protected PasswordAuthentication getPasswordAuthentication() {
                                         return new PasswordAuthentication(Username, m_sPassword);
                                 }
                           });




                    Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                             @Override      
                             protected Object transact() throws BasicException {   



                                  int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO email_master (ID ,USERNAME , PASSWORD , ACTIVE , CRBY , CRDATE , CRHOST , SMTPSERVER , PORT , NOOFMAILS , HOURS ) VALUES (?,?,?,?,?,?,?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.INT, Datas.STRING ,Datas.TIMESTAMP  , Datas.STRING , Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE})                         
                                  ).exec(new Object[]{UUID.randomUUID().toString(), Username ,m_sPassword , Active ,    m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , smtp , smtp_port , NoOfEmails , Hours });                                                                                                


                                   return null;                                      
                                     }                            
                                 };                 

                                 try {                 
                                     t.execute();          

                                     JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                     reset();
                                     loaddata();


                                 }
                              catch (BasicException ex) {                    
                                         Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                         ex.printStackTrace();
                                         new MessageInf(ex).show(new JFrame());

                              } 




               
               }
               else{
                     JOptionPane.showMessageDialog(this, "Enter No. of mails per hour.. !! ", "Warning", JOptionPane.WARNING_MESSAGE);
               }
           
           }
           else{
                JOptionPane.showMessageDialog(this, "Enter Password.... !! ", "Warning", JOptionPane.WARNING_MESSAGE);
           }
           
       }
       else{
            JOptionPane.showMessageDialog(this, "Enter User Name.... !! ", "Warning", JOptionPane.WARNING_MESSAGE);
       }
        
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter SMTP Host Name.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
     }
     else{
          JOptionPane.showMessageDialog(this, "Please enter SMTP Port.", "Warning", JOptionPane.WARNING_MESSAGE);
     }
        
    }//GEN-LAST:event_save_buttonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MemberEmailList memList;
        try {
            memList = MemberEmailList.getDialog(this, m_App,true);
            memList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
       
                                
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
             
              if(jTable1.getSelectedRow()<EmailMaster_Table_Model.getSize()){
               int row = jTable1.getSelectedRow();
               EmailInfo showdata = EmailMaster_Table_Model.getList().get(row);
                 
               
               String id = showdata.getID();
               
               
               
                // DEACTIVATE LINKED ROOM
               try {
                      int update_Email_master =  new PreparedSentence(m_App.getSession(), "UPDATE email_master  SET ACTIVE=0  , DEACBY=? , DEACDATE=? , DEACHOST=? WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , id  });
                  
               
                      reset();
                      loaddata();
                      
                      JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                      
               } catch (BasicException ex) {
                      Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
               
               
                 
             
              }
             }
         }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void edit_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_TextActionPerformed
       if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
             
              if(jTable1.getSelectedRow()<EmailMaster_Table_Model.getSize()){
               int row = jTable1.getSelectedRow();
               EmailInfo showdata = EmailMaster_Table_Model.getList().get(row);
                 
               
               
               
               String id = showdata.getID();
               String UserName = showdata.getNAME();
               String Password = showdata.getPASSWORD();
               System.out.println("OldPassword ... : "+Password);
                
               
              
                
              
             
                
                        String SMTPhost = showdata.getSMTPSERVER();
                        String Port = showdata.getPORT();
                        NoOfEmails = showdata.getNoOfEmails();
                        Hours = showdata.getHours();


                        noOfmails_text.setText(NoOfEmails+"");
                        hours_text.setText(Hours+"");
                        userName_text.setText(UserName);
                        smtp_text.setText(SMTPhost);
                        port_text.setText(Port);
                        Id_label.setText(id);
                        jTabbedPane1.setSelectedIndex(0);
                        saveChanges_Button.setVisible(true);
                        save_button.setVisible(false);
                        m_sPassword=null; 
                 
               
                
                
                
              }
             }
         }
    }//GEN-LAST:event_edit_TextActionPerformed
   
    private void saveChanges_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChanges_ButtonActionPerformed
     if(port_text.getText()!=null && port_text.getText().trim().length()>0){ 
        
      if(smtp_text.getText()!=null && smtp_text.getText().trim().length()>0){
        
        if(userName_text.getText()!=null && userName_text.getText().trim().length()>0){
           if( m_sPassword!=null  && m_sPassword.trim().length()>0 ){
           
           smtp = smtp_text.getText().trim();
           Username = userName_text.getText().trim();
           Active=1;
           smtp_port = port_text.getText().trim();
           
           ID = Id_label.getText();
           NoOfEmails = Double.parseDouble(noOfmails_text.getText().toString());
           Hours = Double.parseDouble(hours_text.getText().toString());
           
		
 
		
           Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                    @Override      
                    protected Object transact() throws BasicException {   



                        int update_Email_master =  new PreparedSentence(m_App.getSession(), "UPDATE email_master  SET USERNAME=? , PASSWORD=? , SMTPSERVER=? , PORT=?, ACTIVE=1  , CRBY=? , CRDATE=? , CRHOST=? , NOOFMAILS=? , HOURS=? WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING  , Datas.STRING , Datas.STRING ,Datas.TIMESTAMP , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE, Datas.STRING })).exec
                                                                            (new Object[]{Username , m_sPassword ,smtp , smtp_port  , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() ,NoOfEmails , Hours ,  ID  });
                  
               

                          return null;                                      
                            }                            
                        };                 

                        try {                 
                            t.execute();          

                            JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reset();
                            loaddata();


                        }
                     catch (BasicException ex) {                    
                                Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                ex.printStackTrace();
                                new MessageInf(ex).show(new JFrame());

                     } 
                      
               
               
               
               
               
           
           }
           else{
                JOptionPane.showMessageDialog(this, "Enter Password.... !! ", "Warning", JOptionPane.WARNING_MESSAGE);
           }
           
       }
       else{
            JOptionPane.showMessageDialog(this, "Enter User Name.... !! ", "Warning", JOptionPane.WARNING_MESSAGE);
       }
        
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter SMTP Host Name.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
     }
     else{
          JOptionPane.showMessageDialog(this, "Please enter SMTP Port.", "Warning", JOptionPane.WARNING_MESSAGE);
     }
    }//GEN-LAST:event_saveChanges_ButtonActionPerformed

    private void group_Name_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_group_Name_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_group_Name_textActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        jPanel6.setVisible(true);
         group_Name_text.setText("");
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void group_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_group_SaveActionPerformed
        // TODO add your handling code here:
        
         if(group_Name_text.getText()!=null && group_Name_text.getText().trim().length()>0)
         {    int count=0;
             jCheckBox1.setSelected(false);
             for (int i = 0; i < EmailMasterTableForCreateGroup.getSize(); i++)
             {
             if(group_Name_text.getText().trim().equals(EmailMasterTableForCreateGroup.getTableModel().getValueAt(i,1).toString().trim()) ){
              
                 count++;
             }
             }
             
              //jPanel6.setVisible(false);
           if(count>0){
               
            JOptionPane.showMessageDialog(this, "Enter different Group Name ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
                   }
           else   {
               String s=group_Name_text.getText();
                  Date date=new Date();
                   UUID.randomUUID().toString();
                   
                   Object obj = null;
                     System.out.println(m_App.getAppUserView().getUser().getName());
        
                     
                    try {
                     
            
            int info=new PreparedSentence(m_App.getSession()
                    , "INSERT INTO email_group_list(GROUP_NAME,DATE,ID,ACTIVE,Created_By,Created_host) VALUES(?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING, Datas.STRING})).exec(new Object[]{s,date, UUID.randomUUID().toString(),1,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost()});
                     
                     JOptionPane.showMessageDialog(this, "Saved Successfully..!! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                   
                      try {

                            EmailMasterTableForCreateGroup = EmailMasterTableForCreateGroup.loademailGroupNameList(m_App);
                            jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }  
                     
                     
                     
                     
           } catch (BasicException ex) {
              Logger.getLogger(SmsSendernew.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    
              }
              group_Name_text.setText(""); 
           
         }
   
       else{
             JOptionPane.showMessageDialog(this, "Enter Group Name First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
       }
           
    }//GEN-LAST:event_group_SaveActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        
         if(jCheckBox1.isSelected())
         {
            
            try {
                     jPanel6.setVisible(false);
                    EmailMasterTableForCreateGroup = EmailMasterTableForCreateGroup.loademailGroupNameList_Deactivated(m_App);
                    jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
                    activate_Btn.setVisible(true);
                    deactivate_btn.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
            }  
            
        }
        
         else{
            
                try {

                    EmailMasterTableForCreateGroup = EmailMasterTableForCreateGroup.loademailGroupNameList(m_App);
                    jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
                    activate_Btn.setVisible(false);
                    deactivate_btn.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }  
            
            
        }
        
        
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
          if(groupName_combo.getSelectedIndex()!=-1){  
            
          
             try{
               Transaction t=new Transaction(m_App.getSession()) {
                    String GroupName = groupName_combo.getSelectedItem().toString();
                    String Group_ID = null;
                           @Override
                           
                           
               protected Object transact() throws BasicException {
                   Object o = null; 
                              o =  new StaticSentence(m_App.getSession(), "SELECT ID  FROM email_group_list WHERE GROUP_NAME =  ?", 
                                             SerializerWriteString.INSTANCE,
                                             SerializerReadString.INSTANCE).find(GroupName);

                               if(o!=null){
                                   Group_ID = o.toString();
                                }
                               
                              
                               if( Group_ID!=null){
                                   new PreparedSentence(m_App.getSession(), "DELETE  FROM email_grp_mem  WHERE GroupNameId=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Group_ID});
                                   
                               for(int i=0;i<selectedEmailIdList.size();i++){
                                   
                                String SelectedMember = selectedEmailIdList.get(i).toString();
                                
                                    boolean found = false;
                                    for (int i1=0; i1<SelectedMember.length(); i1++) {
                                       if (SelectedMember.charAt(i1)=='@' ) {
                                          found = true;
                                          break;
                                       }
                                    }
                                if(found){
                                    
                                    String EmailId = SelectedMember;
                                    String Memno =   GroupName;

                                    new PreparedSentence(m_App.getSession()
                                      , "INSERT INTO email_grp_mem ( ID , GroupNameId , Active, EmailIdList, CreatedBy , CreatedHost ,  CRDATE  , MEMID , MEMFLAG ) VALUES (?,?,?,?,?,?,?,?,?)"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP , Datas.STRING , Datas.INT})
                                      ).exec(new Object[]{UUID.randomUUID().toString() , Group_ID , 1 , EmailId  , m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost() , new Date() , Memno , 0});

                                    
                                    
                                }
                                else{
                                    
                                    
                                    String EmailId = getEmailIdFromMemberName(SelectedMember);
                                    String Memno =  getSearchkeyByMemName(SelectedMember);

                                    new PreparedSentence(m_App.getSession()
                                      , "INSERT INTO email_grp_mem ( ID , GroupNameId , Active, EmailIdList, CreatedBy , CreatedHost ,  CRDATE  , MEMID , MEMFLAG) VALUES (?,?,?,?,?,?,?,?,?)"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP , Datas.STRING , Datas.INT})
                                      ).exec(new Object[]{UUID.randomUUID().toString() , Group_ID , 1 , EmailId  , m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost() , new Date() , Memno , 1});

                                }
                                
                             
                            }
                               }
                              return null;
              }
            };
            t.execute();  
                     JOptionPane.showMessageDialog(this, "Saved Successfully..!! ", "Success", JOptionPane.INFORMATION_MESSAGE);                               
                               
          
        }
        catch(Exception e){
            new MessageInf(e).show(getParent());
            e.printStackTrace();
            
            
        }
             
             }  
             
      
    }//GEN-LAST:event_jButton10ActionPerformed

    private void deactivate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_btnActionPerformed
        // TODO add your handling code here:
         if(jTable2.getSelectedRow()!=-1){
            
            if(jTable2.getSelectedRow()< EmailMasterTableForCreateGroup.getSize()){
              
                
               int x = JOptionPane.showConfirmDialog(jPanel4, "Do You Want to Deativate Group ???? ", "Confirm", JOptionPane.YES_NO_OPTION); 
                if(x == JOptionPane.YES_OPTION){
                    
                    
                            
                int row = jTable2.getSelectedRow();
                
               Emailgroup Showdata = EmailMasterTableForCreateGroup.getEmailgroupList().get(row);
                
                String GroupId =  Showdata.getId();
                
                
                 try {



                     int update=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set Active=0 where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{   GroupId   });

                     int update1=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set DeacBy=? where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{  m_App.getAppUserView().getUser().getName(),  GroupId   });

                     
                     int update2=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set DeacHost=? where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getProperties().getHost() ,  GroupId   });

                     int update3=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set DeacDate=? where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{ new Date() ,  GroupId   });

                     
                     
                     
                     
                        JOptionPane.showMessageDialog(this, " deactivated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        
                            
                       try {

                            EmailMasterTableForCreateGroup = EmailMasterTableForCreateGroup.loademailGroupNameList(m_App);
                            jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
                            jCheckBox1.setSelected(false);
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }  
                        
                        
                        
                      // jTable2.getSelectedRows();

                    } 


                 catch (BasicException ex) {
                  Logger.getLogger(SmsSendernew.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
                }}
      }   
    }//GEN-LAST:event_deactivate_btnActionPerformed

    private void activate_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activate_BtnActionPerformed
        // TODO add your handling code here:
        
         if(jTable2.getSelectedRow()!=-1){
            
            if(jTable2.getSelectedRow()<  EmailMasterTableForCreateGroup.getSize()){
              
                
               int x = JOptionPane.showConfirmDialog(jPanel4, "Do You Want to Activate Group ???? ", "Confirm", JOptionPane.YES_NO_OPTION); 
                if(x == JOptionPane.YES_OPTION){
                    
                    
                            
                int row = jTable2.getSelectedRow();
                
                 Emailgroup Showdata =  EmailMasterTableForCreateGroup.getEmailgroupList().get(row);
                
                String GroupId =  Showdata.getId();
                
                
                 try {



                     int update=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set Active=1 where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{   GroupId   });

                     int update1=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set Created_By=? where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{  m_App.getAppUserView().getUser().getName(),  GroupId   });

                     
                     int update2=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set Created_Host=? where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{ m_App.getProperties().getHost() ,  GroupId   });

                     int update3=new PreparedSentence(m_App.getSession()
                        , "UPDATE email_group_list set Date=? where ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{ new Date() ,  GroupId   });

                     
                     
                     
                     
                        JOptionPane.showMessageDialog(this, " Activated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        
                            
                       try {

                            EmailMasterTableForCreateGroup= EmailMasterTableForCreateGroup.loademailGroupNameList(m_App);
                            jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
                            jCheckBox1.setSelected(false);
                            int count=0;
                           for (int i = 1; i < EmailMasterTableForCreateGroup.getSize(); i++)
             {
                  for (int j = 0; j < EmailMasterTableForCreateGroup.getSize()-i; j++){
             if(EmailMasterTableForCreateGroup.getTableModel().getValueAt(j,1).toString().trim().equals(EmailMasterTableForCreateGroup.getTableModel().getValueAt(j+1,1).toString().trim()) ){
              
                 count++;
             }
                  }
             }
             
              //jPanel6.setVisible(false);
           if(count>0){
               
            JOptionPane.showMessageDialog(this, " Group Name Conflict Deactivate group with the same Name ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
                   } 
              
                        } catch (Exception e) {
                            e.printStackTrace();
                        }  
                        
                        
                        
                      // jTable2.getSelectedRows();

                    } 


                 catch (BasicException ex) {
                  Logger.getLogger(SmsSendernew.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
                }
              }
             } 
    }//GEN-LAST:event_activate_BtnActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
      
       if(members_radio.isSelected()){
           
           String SelectedMember = group_temp_JList.getSelectedValue().toString();
           
           selectedEmailIdList.add(SelectedMember);
           Group_Final_JList.setModel(new EmailMaster.ItemsListModel(selectedEmailIdList));
           Group_TempMemList.remove(SelectedMember);
           group_temp_JList.setModel(new EmailMaster.ItemsListModel(Group_TempMemList));
           
           
           
       } 
       else{
        
        
             if(email_id.getText()!=null && email_id.getText().trim().length()>0)
                {   int count=0;
                   if(selectedEmailIdList.isEmpty())
                   {
                       selectedEmailIdList.add(email_id.getText().trim());
                       Group_Final_JList.setModel(new EmailMaster.ItemsListModel(selectedEmailIdList));
                   }
                   else{
                    for (int i = 0; i < selectedEmailIdList.size(); i++)
                    {
                     if (email_id.getText().trim().equals(selectedEmailIdList.get(i).toString().trim()))

                    { 
                         count++;
                  //String  temp=email_id.getText();
                  //selectedMemList.add(email_id.getText());
                  //jList1.setModel(new EmailMaster.ItemsListModel(selectedEmailIdList));
                   //email_id.setText(""); 
                     }}

                     if(count>0)
                     {

                          JOptionPane.showMessageDialog(this, "Select different email id");
                     }

                     else {
                         selectedEmailIdList.add(email_id.getText().trim());
                          Group_Final_JList.setModel(new EmailMaster.ItemsListModel(selectedEmailIdList));


                    }


                    }
                    email_id.setText("");
                    } 
                else {
                        JOptionPane.showMessageDialog(this, "enter email id");
                }
       }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        if(jTabbedPane1.getSelectedIndex()==2){
           
            jPanel3.setVisible(false);
          //groupName_panel.setVisible(false); 
           jCheckBox1.setSelected(false);
           try {

                    EmailMasterTableForCreateGroup = EmailMasterTableForCreateGroup.loademailGroupNameList(m_App);
                    jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
                    activate_Btn.setVisible(false);
                    deactivate_btn.setVisible(true);
                    jCheckBox1.setSelected(false);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }  
        }
        
        if(jTabbedPane1.getSelectedIndex()==3){
           groupName_combo.setSelectedIndex(-1);
           
           
        lst1 = new ArrayList<Object>();
           
           try {
              

                          lst1 =  new StaticSentence(m_App.getSession(), "SELECT GROUP_NAME , ACTIVE FROM email_group_list WHERE ACTIVE=TRUE", 
                                 SerializerWriteString.INSTANCE,
                                 SerializerReadString.INSTANCE).list();

                



            } catch (Exception e) {
                e.printStackTrace();
            }  
             
           
           
          groupname = new ComboBoxValModel(lst1);
          groupName_combo.setModel(groupname);
          groupName_combo.setSelectedIndex(-1);
           
           
          
       }
       
       
        
        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void groupName_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupName_comboItemStateChanged
        // TODO add your handling code here:
        if(groupName_combo.getSelectedIndex()!=-1) 
       {
        
           jPanel3.setVisible(true);
           selectedEmailIdList = new ArrayList<Object> ();
           
            String GroupName = groupName_combo.getSelectedItem().toString();
            
             try {
                 selectedEmailIdList = new StaticSentence(m_App.getSession(),  " SELECT concat(c.searchkey ,' - '  , c.name ) \n" +
                                                                                "from CUSTOMERS C , email_grp_mem L  , email_group_list G \n" +
                                                                                "WHERE L.ACTIVE=1 AND L.GroupNameId = G.ID AND  \n" +
                                                                                "C.EMAIL=L.EmailIdList AND G.GROUP_NAME = ? AND L.MEMFLAG=1\n" +
                                                                                "UNION \n" +
                                                                                "SELECT L.EMAILIDLIST\n" +
                                                                                "from  email_grp_mem L  , email_group_list G \n" +
                                                                                "WHERE L.ACTIVE=1 AND L.GroupNameId = G.ID AND  \n" +
                                                                                "G.GROUP_NAME = ? AND L.MEMFLAG=0\n" +
                                                                                "order by 1", new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING}),
                                                                                 SerializerReadString.INSTANCE).list(new Object[]{GroupName , GroupName});
              }
             catch (BasicException ex) {
            Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
            }
             Group_Final_JList.setModel(new ItemsListModel( selectedEmailIdList)); 
             
             for(int i =0;i<selectedEmailIdList.size(); i++){
                 String x = selectedEmailIdList.get(i).toString();
                 Group_TempMemList.remove(x);
             }
             
             group_temp_JList.setModel(new ItemsListModel( Group_TempMemList));
       }
        else{
        jPanel3.setVisible(false);
    }
        
    }//GEN-LAST:event_groupName_comboItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        groupName_combo.setSelectedIndex(-1);
       jPanel3.setVisible(false);
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       if(members_radio.isSelected()){
           
           // TODO add your handling code here:
            try {
                int row = Group_Final_JList.getSelectedIndex();
                if (row >= 0) {

                 String lst=Group_Final_JList.getSelectedValue().toString();
                 selectedEmailIdList.remove(lst);
                 Group_Final_JList.setModel(new ItemsListModel(selectedEmailIdList));
                 Group_TempMemList.add(lst);
                 group_temp_JList.setModel(new EmailMaster.ItemsListModel2(Group_TempMemList));

                  } else {
                    JOptionPane.showMessageDialog(this, "Select any emailid");

                }
             } catch (Exception e) {
                e.printStackTrace();
             }   
           
           
           
           
       }
       else{

       // TODO add your handling code here:
        try {
            int row = Group_Final_JList.getSelectedIndex();
            if (row >= 0) {
               
             String lst=Group_Final_JList.getSelectedValue().toString();
             selectedEmailIdList.remove(lst);
             Group_Final_JList.setModel(new ItemsListModel(selectedEmailIdList));
             
             
              } else {
                JOptionPane.showMessageDialog(this, "Select any emailid");

            }
         } catch (Exception e) {
            e.printStackTrace();
         }   
       }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void noOfmails_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noOfmails_textKeyReleased
       char c = evt.getKeyChar();
     
       
     
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(noOfmails_text, "Please enter only numbers..");
    
            noOfmails_text.setText(null);
     
    }
    
           
    }
    }//GEN-LAST:event_noOfmails_textKeyReleased

    private void hours_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hours_textKeyReleased
         char c = evt.getKeyChar();
        
       
     
    
        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {  
                JOptionPane.showMessageDialog(hours_text, "Please enter only numbers..");

                    hours_text.setText(null);

            }


        }
    }//GEN-LAST:event_hours_textKeyReleased
     String UserID = null;
    private void AddUser_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUser_btnActionPerformed
       if(UserName_Combo.getSelectedIndex()!=-1){
            int usedflag=0;
            UserID = null;
           
            String UserName = UserName_Combo.getSelectedItem().toString();
            
            
             SelectedUserName_List  = new ArrayList<>();
             
            
            String AlreadyUsed = null;
            try{
                 UserID = getUserId(UserName);
                 SelectedUserName_List = getSelectedUserList();
                
            }
            catch(BasicException ex){

             }
           SelectedUser_JList.setModel(new EmailMaster.ItemsListModel2(SelectedUserName_List));
           
           
           for(int i=0;i<SelectedUserName_List.size();i++){
               String x = SelectedUserName_List.get(i).toString();
               if(UserName.equals(x)){
                   usedflag=1;
                   break;
               }
               
           }
           
           
           
           if(UserID!=null){
               if(usedflag==0){
                
               
               
               
               Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                   
                           
                   
                             @Override      
                             protected Object transact() throws BasicException {   

                                 

                                  int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO email_permissions (ID ,USER , ACTIVE ,  CRBY , CRDATE , CRHOST  ) VALUES (?,?,?,?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT, Datas.STRING ,Datas.TIMESTAMP  , Datas.STRING })                         
                                  ).exec(new Object[]{UUID.randomUUID().toString(), UserID ,1 , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()  });                                                                                                


                                   return null;                                      
                                     }                            
                                 };                 

                                 try {                 
                                     t.execute();          

                                     JOptionPane.showMessageDialog(this, "User added  Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                     UserName_Combo.setSelectedIndex(-1);
                                     SelectedUserName_List  = new ArrayList<>();
                                     SelectedUserName_List = getSelectedUserList();
                                     SelectedUser_JList.setModel(new EmailMaster.ItemsListModel2(SelectedUserName_List));

                                 }
                              catch (BasicException ex) {                    
                                         Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                         ex.printStackTrace();
                                         new MessageInf(ex).show(new JFrame());

                              } 
  
               }
               else{
                    JOptionPane.showMessageDialog(this, "Already selected. Please select different user. " ,"Error", JOptionPane.ERROR_MESSAGE);
                }
               
           }
           else{
                JOptionPane.showMessageDialog(this, "No such User. Please select again. " ,"Error", JOptionPane.ERROR_MESSAGE);
           }
           
       }
       else{
            JOptionPane.showMessageDialog(this, "Select User name first " ,"Error", JOptionPane.ERROR_MESSAGE);
       }
        
    
    }//GEN-LAST:event_AddUser_btnActionPerformed
 String RemoveUser = null;
 
 
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(SelectedUser_JList.getSelectedIndex()!=-1){
            try {
            int row = SelectedUser_JList.getSelectedIndex();
            if (row >= 0) {
               
            RemoveUser=SelectedUser_JList.getSelectedValue().toString();
            UserID = null; 
            try{
                 UserID = getUserId(RemoveUser);
            }
            catch(BasicException ex){

             }     
             
             
             
               Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                    @Override      
                    protected Object transact() throws BasicException {   



                        int update_Email_master =  new PreparedSentence(m_App.getSession(), "UPDATE email_permissions  SET ACTIVE=0  , DEACBY=? , DEACDATE=? , DEACHOST=?  WHERE USER = ? AND ACTIVE=1 "
                                                                           , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , UserID  });
                  
                        

                          return null;                                      
                            }                            
                        };                 

                        try {                 
                            t.execute();          

                            JOptionPane.showMessageDialog(this, " Removed Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                           
                            
                             SelectedUserName_List  = new ArrayList<>();
                             SelectedUserName_List = getSelectedUserList();
                             SelectedUser_JList.setModel(new EmailMaster.ItemsListModel2(SelectedUserName_List));
                            
                            
                        }
                     catch (BasicException ex) {                    
                                Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                ex.printStackTrace();
                                new MessageInf(ex).show(new JFrame());

                     } 
                  
             
                
              } 
            
            else {
                JOptionPane.showMessageDialog(this, "Select any user first");

            }
         } catch (Exception e) {
            e.printStackTrace();
         }   
         
            
            
            
        }
        else{
            
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void members_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_members_radioItemStateChanged
      if(members_radio.isSelected()){
          jLabel11.setVisible(false);
          email_id.setVisible(false);
          group_temp_JList.setEnabled(true);
          
      }
      else{
          
          
      }
    }//GEN-LAST:event_members_radioItemStateChanged

    private void others_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_others_radioItemStateChanged
        if(others_radio.isSelected()){
             jLabel11.setVisible(true);
             email_id.setVisible(true);
            
             group_temp_JList.setEnabled(false);
            
        }
        else{
            
        }
    }//GEN-LAST:event_others_radioItemStateChanged

     List<Object> lst1=new ArrayList<Object>();     
     
     
     public void grouplst(){
         try {
                      lst1 =  new StaticSentence(m_App.getSession(), "SELECT GROUP_NAME,ACTIVE FROM email_group_list WHERE ACTIVE=TRUE", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
                      
                    } catch (BasicException ex) {
                        Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
              }
                        // jList1.setModel(new ItemsListModel(lst1));
          
    }
     
     
     private class ItemsListModel extends AbstractListModel {

        private java.util.List items;

        public ItemsListModel(java.util.List items) {
            this.items = items;
        }

        @Override
        public int getSize() {
            return items.size();
        }

        @Override
        public Object getElementAt(int i) {
            
            return items.get(i);
        }
     }
     
     
     private class ItemsListModel2 extends AbstractListModel {

        private java.util.List items2;

        public ItemsListModel2(java.util.List items2) {
            this.items2 = items2;
        }

        @Override
        public int getSize() {
            return items2.size();
        }

        @Override
        public Object getElementAt(int i) {
            
            return items2.get(i);
        }
     }
     
     
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddUser_btn;
    private javax.swing.JList Group_Final_JList;
    private javax.swing.JLabel Id_label;
    private javax.swing.JList SelectedUser_JList;
    private javax.swing.JComboBox UserName_Combo;
    private javax.swing.JButton activate_Btn;
    private javax.swing.JButton deactivate_btn;
    private javax.swing.JButton edit_Text;
    private javax.swing.JTextField email_id;
    private javax.swing.JComboBox groupName_combo;
    private javax.swing.JTextField group_Name_text;
    private javax.swing.JButton group_Save;
    private javax.swing.JList group_temp_JList;
    private javax.swing.JTextField hours_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox mail_account_combo;
    private javax.swing.JRadioButton members_radio;
    private javax.swing.JTextField noOfmails_text;
    private javax.swing.JRadioButton others_radio;
    private javax.swing.JButton password_button;
    private javax.swing.JPasswordField passwordfield;
    private javax.swing.JPanel permissions_email;
    private javax.swing.JTextField port_text;
    private javax.swing.JButton saveChanges_Button;
    private javax.swing.JButton save_button;
    private javax.swing.JTextField smtp_text;
    private javax.swing.JTextField userName_text;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "E-Mail Account Master";
    }

    public void activate() throws BasicException {
        jPanel6.setVisible(false);
        ButtonGrp();
        try {
            
            EmailMasterTableForCreateGroup = EmailMasterTableForCreateGroup.loademailGroupNameList(m_App);
            jTable2.setModel(EmailMasterTableForCreateGroup.getTableModel());
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        activate_Btn.setVisible(false);
        deactivate_btn.setVisible(true);
        jCheckBox1.setSelected(false);
        grouplst();
        reset();
        loaddata();
         groupname = new ComboBoxValModel(lst1);
         groupName_combo.setModel(groupname);
         groupName_combo.setSelectedIndex(-1);
         
         
         UserNameList = new ArrayList<>();
         UserNameList = getUserNameAll();
         
         UserName = new ComboBoxValModel(UserNameList);
         UserName_Combo.setModel(UserName);
         UserName_Combo.setSelectedIndex(-1);
         
         SelectedUserName_List  = new ArrayList<>();
         SelectedUserName_List = getSelectedUserList();
         
         SelectedUser_JList.setModel(new EmailMaster.ItemsListModel2(SelectedUserName_List));
          
         
         // for group master edited by akash
         Group_TempMemList = getMemberListAllWithEmailId();
         group_temp_JList.setModel(new EmailMaster.ItemsListModel2(Group_TempMemList));
         
         
         
          jPanel3.setVisible(false);
          
          
          
     
          
        //reset();
       // loaddata();
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
    }

    public Object getBean() {
        return this;
    }
    
    
    public void loaddata() throws BasicException{
        
       
       members_radio.setSelected(true);
        
       EmailMaster_Table_Model  = EmailMasterTableModel.loadEmailInfo(m_App);
       jTable1.setModel(EmailMaster_Table_Model.getTableModel()); 
       mail_account_combo.setSelectedIndex(-1);
        
    }
    
    public void ButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(members_radio);
        bg.add(others_radio);
        
    }
    
    public void reset(){
        
        userName_text.setText(null);
        passwordfield.setText(null);
        m_sPassword=null;
        mail_account_combo.setSelectedIndex(-1);
        smtp_text.setText(null);
        port_text.setText(null);
        saveChanges_Button.setVisible(false);
        save_button.setVisible(true);
        noOfmails_text.setText(null);
        hours_text.setText(null);
        
    }
    
  // Code for email Permissions
    
    public List getUserNameAll(){
       List<Object> Temp = new ArrayList();
        
        try {
                      Temp =  new StaticSentence(m_App.getSession(), "SELECT NAME FROM PEOPLE WHERE VISIBLE=1 ORDER BY NAME  ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
                      
                    } catch (BasicException ex) {
                        Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
              }
        return Temp;              
          
    }
     public String getUserId(String Name) throws BasicException{
       Object o = null;
       String t = null;
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM PEOPLE WHERE NAME=? AND VISIBLE=1  ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
                 
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    
  // get List of selected users for permissions. 
     
      public List getSelectedUserList() throws  BasicException{
       List<Object> Temp = new ArrayList();
        
   
        Temp =  new StaticSentence(m_App.getSession(), "SELECT P.NAME FROM EMAIL_PERMISSIONS E , PEOPLE P  WHERE E.USER = P.ID AND E.ACTIVE=1 AND P.VISIBLE=1 ORDER BY P.NAME ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
                    
        return Temp;              
          
    }
     
  public String getUserIdByName(String Name) throws BasicException{
       Object o = null;
       String t = null;
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM email_permissions WHERE user=? AND VISIBLE=1  ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
       
        
        System.out.println("Boolean for userId  : "+o);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
     
  
  
  
  // code edited for group master 
  
  
    // get List of all members 
     
      public List getMemberListAllWithEmailId() throws  BasicException{
       List<Object> Temp = new ArrayList();
        
   
        Temp =  new StaticSentence(m_App.getSession(), "SELECT concat(c.searchkey ,' - '  , c.name ) FROM CUSTOMERS C WHERE C.EMAIL IS NOT NULL  AND C.EMAIL!='' order by 1 ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
                    
        return Temp;              
          
    }
  
    public String getEmailIdFromMemberName(String Name) throws BasicException{
       Object o = null;
       String t = null;
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT EMAIL FROM CUSTOMERS  WHERE concat(searchkey ,' - '  , name ) =? ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
                 
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
    
    
      public String getSearchkeyByMemName(String Name) throws BasicException{
       Object o = null;
       String t = null;
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT SEARCHKEY FROM CUSTOMERS  WHERE concat(searchkey ,' - '  , name ) =? ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
                 
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    }
}
