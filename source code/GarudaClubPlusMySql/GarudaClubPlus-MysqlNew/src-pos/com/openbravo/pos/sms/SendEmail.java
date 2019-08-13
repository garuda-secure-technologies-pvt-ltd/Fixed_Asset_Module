

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.util.AltEncrypter;
import com.openbravo.pos.util.Hashcypher;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import java.net.*;

public class SendEmail extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    List<Object> UserName_list = new ArrayList<Object>();
    private EmailMasterTableModel EmailMaster_Table_Model;
    private ComboBoxValModel UserName_Model ;
    
    private DataLogicFacilities dmang;
    private ComboBoxValModel Memtype_Model;
    private ComboBoxValModel GroupList_Model;
    List<Object> GroupList = new ArrayList<Object>();
    private int NoOfMailIds = 0;
    
    
    List<Object> SelectedGroupEmailIDs = new ArrayList<Object>();
    
    public SendEmail() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        main_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username_combo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        subject_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message_text = new javax.swing.JTextArea();
        file_button = new javax.swing.JButton();
        fileName_label = new javax.swing.JLabel();
        send_button = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        sendto_text = new javax.swing.JTextField();
        cancel_button = new javax.swing.JButton();
        Decr_Password_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        memno_text = new javax.swing.JTextField();
        memtype_radio = new javax.swing.JRadioButton();
        individual_radio = new javax.swing.JRadioButton();
        memtype_combobox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        groupwise_radio = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        GroupList_Combo = new javax.swing.JComboBox();
        noOfMailId_Label = new javax.swing.JLabel();
        setDrive_btn = new javax.swing.JButton();

        jTabbedPane1.setForeground(new java.awt.Color(153, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Email Menu ");

        jLabel2.setText("Select Email Account : ");

        username_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        username_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                username_comboItemStateChanged(evt);
            }
        });

        jLabel3.setText("Subject : ");

        jLabel4.setText("Message :");

        message_text.setColumns(20);
        message_text.setRows(5);
        jScrollPane1.setViewportView(message_text);

        file_button.setText("Select File to Attached ");
        file_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                file_buttonActionPerformed(evt);
            }
        });

        fileName_label.setText("file name");

        send_button.setText("Send Mail ..! ");
        send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_buttonActionPerformed(evt);
            }
        });

        jLabel6.setText("Send to : ");

        cancel_button.setText("Cancel ");
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_buttonActionPerformed(evt);
            }
        });

        Decr_Password_label.setText("jLabel5");

        jLabel5.setText("Mem No :");

        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        memtype_radio.setText("Member Type Wise ");
        memtype_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memtype_radioItemStateChanged(evt);
            }
        });

        individual_radio.setText("Individual ");
        individual_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                individual_radioItemStateChanged(evt);
            }
        });

        memtype_combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel7.setText("Member Type ");

        groupwise_radio.setText("Groupwise");
        groupwise_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupwise_radioItemStateChanged(evt);
            }
        });

        jLabel8.setText("Select Group : ");

        GroupList_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        GroupList_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GroupList_ComboItemStateChanged(evt);
            }
        });

        noOfMailId_Label.setText("jLabel9");

        setDrive_btn.setText("Set Drive for file storage ");
        setDrive_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDrive_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addComponent(memtype_radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(individual_radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groupwise_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(username_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                .addGap(354, 354, 354)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addComponent(cancel_button)
                                        .addGap(600, 600, 600)
                                        .addComponent(send_button)
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(memtype_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(subject_text)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                                                .addGroup(main_panelLayout.createSequentialGroup()
                                                    .addComponent(sendto_text, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(45, 45, 45))))))
                                .addComponent(Decr_Password_label))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                .addComponent(file_button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileName_label, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GroupList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(noOfMailId_Label))
                            .addComponent(setDrive_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(username_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memtype_radio)
                    .addComponent(individual_radio)
                    .addComponent(groupwise_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memtype_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(sendto_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(GroupList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOfMailId_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(subject_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Decr_Password_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileName_label)
                    .addComponent(file_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(setDrive_btn)
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_button)
                    .addComponent(send_button))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        sendto_text.setEditable(false);
        Decr_Password_label.setVisible(false);

        jTabbedPane1.addTab("Email Sending Menu ", main_panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    String Decr_Password ;
    
    private void username_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_username_comboItemStateChanged
        if(username_combo.getSelectedIndex()!=-1){
            
            String UserName = username_combo.getSelectedItem().toString();
            String EncrptPassword = null;
            Decr_Password=null;
            try {
                 EncrptPassword =  EmailMaster_Table_Model.getEmailPassword(m_App , UserName);
            } catch (BasicException ex) {
                Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
            
            Decr_Password=new AltEncrypter("key").decrypt(EncrptPassword);
            Decr_Password_label.setText(Decr_Password);
           
            System.out.println("passwd " + Decr_Password);
            
            
        }
    }//GEN-LAST:event_username_comboItemStateChanged

    public Double MaxEmailsCanSent = 0.00;
    public Double PerHours = 0.00;
    public String EmailIdFromEmailcheck=null;
    public Double NoOfEmailsSent = 0.00;
    public Double HourDifference=0.00;
    public String saveURL=null;
    
    private void send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_buttonActionPerformed
        
       if(individual_radio.isSelected()){ 
        
        
        
        if(username_combo.getSelectedIndex()!=-1){
         if(subject_text.getText()!=null && subject_text.getText().trim().length()>0){
              if(message_text.getText()!=null && message_text.getText().trim().length()>0){
        
        
        
                final String username = username_combo.getSelectedItem().toString();
		final String password = Decr_Password_label.getText();
                String SendTo = sendto_text.getText().trim();
                String mail_message = message_text.getText().trim();
                String Subject = subject_text.getText().trim();
                
                try{
                     MaxEmailsCanSent = getMaxNoOfEmails(username);
                     PerHours = getMaxHours(username);
                     EmailIdFromEmailcheck = getEmailIdFromEmailCheck(username);
                     saveURL = getGenEmailDocFolderPath();
                }
                catch(BasicException ex){
                    
                }
                
                if(EmailIdFromEmailcheck==null){
                     try{
                        
                          int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO emailcheck (ID ,EMAILID , NOOFMAILSENT , CURRTIME , HOURS , ACTIVE  , MAXEMAIL  ) VALUES (?,?,?,?,?,?,?)"                           
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.DOUBLE ,Datas.TIMESTAMP, Datas.DOUBLE ,Datas.INT  , Datas.DOUBLE })                         
                                    ).exec(new Object[]{UUID.randomUUID().toString(), username ,0.00 , new Date() , PerHours   , 1 , MaxEmailsCanSent });                                                                                                

                          
                        }
                        catch(BasicException e){
                             e.printStackTrace();
                             new MessageInf(e).show(new JFrame());
                        }
                }
                
                
                try{
                    NoOfEmailsSent = getNoOfEmailSent(username);
                    HourDifference = getHourDifferenceFromEmailCheck(username);
                }
                catch(BasicException ex){
                    
                }
                
                System.out.println("Get Max Hours : "+PerHours);
                System.out.println("Get  Hour Difference : "+HourDifference);
                
                if(NoOfEmailsSent<MaxEmailsCanSent){
                    if(HourDifference<PerHours){


                   //     String SMPTHost = null;
                   //     String SmtpPort = null;

                    //    try{
                    //         SMPTHost = getSMTPHost(username);
                    //         SmtpPort = getSMTPport(username);
                   //     }
                   //     catch(Exception e){
                    //         e.printStackTrace();
                    //         new MessageInf(e).show(new JFrame());
                    //    }

                        String Memno = memno_text.getText().trim().toString();

                     //   Properties props = new Properties();
                     //   props.put("mail.smtp.auth", "true");
                     //   props.put("mail.smtp.starttls.enable", "true");
                     //   props.put("mail.smtp.host", SMPTHost);
                   //     props.put("mail.smtp.port", SmtpPort);

                  //      Session session = Session.getInstance(props,
                     //     new javax.mail.Authenticator() {
                     //           protected PasswordAuthentication getPasswordAuthentication() {
                       //                 return new PasswordAuthentication(username, password);
                        //        }   
                      //    });

                    //    try {

                             //   Message message = new MimeMessage(session);
                             //   message.setFrom(new InternetAddress(username));
                              //  message.setRecipients(Message.RecipientType.TO,
                             //           InternetAddress.parse(SendTo));
                             //   message.setSubject(Subject);
                             //   message.setText(mail_message);

                             //   BodyPart messageBodyPart = new MimeBodyPart();

                              //  messageBodyPart.setText(mail_message);

                             //   Multipart multipart = new MimeMultipart();

                             //   multipart.addBodyPart(messageBodyPart);

                             //   messageBodyPart = new MimeBodyPart();


                               

                             
                                
                                try{
                                    
                                     if(fileName_label.getText()!=null && fileName_label.getText().trim().length()>0){
                                            
                                            String DriveName = getDriveNameForDoc();
                                            
                                            
                                            
                                            
                                            DataSource source = new FileDataSource(fileName_label.getText());
                                    
                                           // saveURL = "/home/dev3/Desktop/Emails Testing/";
                                            BufferedInputStream in = new BufferedInputStream (new FileInputStream(sourceFile)); 
                                            System.out.println("BufferedInputStream created");
                                            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(DriveName+":/"+saveURL+"//"+fileName));
                                            System.out.println("BufferedOutputStream created");

                                            int i = 0;
                                            byte[] bytesIn = new byte[1024];

                                            while ((i = in.read(bytesIn)) >= 0) {
                                                 out.write(bytesIn, 0, i);
                                            }
                                           in.close();
                                           out.close();
                                           
                                           
                                        }
                                           // insert into active email table
                                           
                                        try{

                                                   int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO activeemailtable (ID ,EMAILID , SENTTO , SUBJECT , MESSAGE , DOCUMENT  , PRIORITY , CRDATE , CRBY , CRHOST ,FLAG , MEMNO ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.STRING  , Datas.INT , Datas.TIMESTAMP , Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING})                         
                                                               ).exec(new Object[]{UUID.randomUUID().toString(), username ,SendTo ,Subject , mail_message, fileName ,1, new Date() ,  m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost()  , 0 , Memno    });                                                                                                


                                                     JOptionPane.showMessageDialog(this, "E-Mail Added to sent list. \nKindly note email sending menu should be working in order to send mails. !! ", "Warning", JOptionPane.INFORMATION_MESSAGE);
                                                     reset();


                                           }
                                           catch(BasicException e){
                                                e.printStackTrace();
                                                new MessageInf(e).show(new JFrame());
                                           }
                                      }catch(Exception e){
                                           System.out.println("Failed to Copy File from sourcePath");
                                           e.printStackTrace();
                                           new MessageInf(e).show(new JFrame());
                                       }
                                
                                
                                
                                
                             //   messageBodyPart.setDataHandler(new DataHandler(source));
                             //   messageBodyPart.setFileName(fileName_label.getText());

                             //   multipart.addBodyPart(messageBodyPart);
                             //   message.setContent(multipart);
                         

                              //  Transport.send(message);


                          //      try{

                           //       int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO sentemail (ID ,MEMNO , EMAILID , DATE , CRBY , CRHOST  , SUBJECT , SENTID ) VALUES (?,?,?,?,?,?,?,?)"                           
                               //             , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.TIMESTAMP, Datas.STRING ,Datas.STRING  , Datas.STRING , Datas.STRING })                         
                               //             ).exec(new Object[]{UUID.randomUUID().toString(), Memno ,SendTo , new Date() ,    m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost()  , Subject , username });                                                                                                


                              //    JOptionPane.showMessageDialog(this, "Mail sent successfully!! ", "Warning", JOptionPane.INFORMATION_MESSAGE);
                              //    reset();


                             //   }
                              //  catch(BasicException e){
                               //      e.printStackTrace();
                              //       new MessageInf(e).show(new JFrame());
                             //   }


                            




                       // } catch (MessagingException e) {

                            //    e.printStackTrace();
                           //     new MessageInf(e).show(new JFrame());
                             //   throw new RuntimeException(e);

                       // }




                    }
                    else{
                        
                        
                        
                    }
                
                }
                else{
                     JOptionPane.showMessageDialog(this, "Max. limit of emails that can be sent has reached..!! \n Can't send any more emails..!  ", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                
                
                
              }
              else{
                  JOptionPane.showMessageDialog(this, "Please enter message for Mail !! ", "Warning", JOptionPane.WARNING_MESSAGE);
              }
         }
         else{
              JOptionPane.showMessageDialog(this, "Please enter subject for Mail !! ", "Warning", JOptionPane.WARNING_MESSAGE);
         }
                
                
        }
        else{
             JOptionPane.showMessageDialog(this, "Select User Name ID !! ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
                
       }
       
       
       
       // FOR SENDING MAIL TO MULTIPLE PERSONS --------------------------------------------------------------------------------------
       
       
       if(memtype_radio.isSelected()){
           
           
           
           
           
           
        if(memtype_combobox.getSelectedIndex()!=-1){   
           
        if(username_combo.getSelectedIndex()!=-1){
         if(subject_text.getText()!=null && subject_text.getText().trim().length()>0){
              if(message_text.getText()!=null && message_text.getText().trim().length()>0){
        
        
                String Memtype =   memtype_combobox.getSelectedItem().toString();
                List<Object> MemEmailIdList = new ArrayList<Object>();
                
                try{
                
                   MemEmailIdList = getEmaiIDFromMemtype(Memtype);
                }
                catch(Exception e){
                     e.printStackTrace();
                     new MessageInf(e).show(new JFrame());
                }
                
                
                
                
                final String username = username_combo.getSelectedItem().toString();
		final String password = Decr_Password_label.getText();
                
                String mail_message = message_text.getText().trim();
                String Subject = subject_text.getText().trim();
                
               
                
                
                String smtphost = null;
                String SmtpPort = null;
                
                
             //   try{
              //       smtphost = getSMTPHost(username);
              //       SmtpPort = getSMTPport(username);
              //  }
             //   catch(Exception e){
             //        e.printStackTrace();
             //        new MessageInf(e).show(new JFrame());
              //  }
                
                
            //    
		//Properties props = new Properties();
	//	props.put("mail.smtp.auth", "true");
	//	props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host",smtphost); 
            //    props.put("mail.smtp.port",SmtpPort);
 
                
                if(MemEmailIdList.size()>0){
                
	//	Session session = Session.getInstance(props,
	//	  new javax.mail.Authenticator() {
	//		protected PasswordAuthentication getPasswordAuthentication() {
	//			return new PasswordAuthentication(username, password);
	//		}   
	///	  });
 
	//	try {
 
		//	Message message = new MimeMessage(session);
		//	message.setFrom(new InternetAddress(username));
			
                    try{
                    
                      if(fileName_label.getText()!=null && fileName_label.getText().trim().length()>0){

                            DataSource source = new FileDataSource(fileName_label.getText());
                            
                             // saveURL = "/home/dev3/Desktop/Emails Testing/";
                                            BufferedInputStream in = new BufferedInputStream (new FileInputStream(sourceFile)); 
                                            System.out.println("BufferedInputStream created");
                                            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveURL+"//"+fileName));
                                            System.out.println("BufferedOutputStream created");

                                            int i = 0;
                                            byte[] bytesIn = new byte[1024];

                                            while ((i = in.read(bytesIn)) >= 0) {
                                                 out.write(bytesIn, 0, i);
                                            }
                                           in.close();
                                           out.close();
                       
                            }   
                    
                    
                        
                        for(int i=0;i<MemEmailIdList.size() ; i++ ) {
                        
                        
                            String SendTo = MemEmailIdList.get(i).toString();
                            String Memno = null;
                            try{
                                 Memno = getMemnoFromEmailID(SendTo);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                                new MessageInf(e).show(new JFrame());
                            }
                            
                     //       message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SendTo));
                      //      message.setSubject(Subject);
                      //      message.setText(mail_message);

                       //     BodyPart messageBodyPart = new MimeBodyPart();

                        //    messageBodyPart.setText(mail_message);

                       //     Multipart multipart = new MimeMultipart();

                       //     multipart.addBodyPart(messageBodyPart);

                        //    messageBodyPart = new MimeBodyPart();


                           

                       //     Transport.send(message);


                            try{
                                                 int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO activeemailtable (ID ,EMAILID , SENTTO , SUBJECT , MESSAGE , DOCUMENT  , PRIORITY , CRDATE , CRBY , CRHOST ,FLAG , MEMNO ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.STRING  , Datas.INT , Datas.TIMESTAMP , Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING})                         
                                                               ).exec(new Object[]{UUID.randomUUID().toString(), username ,SendTo ,Subject , mail_message, fileName ,1, new Date() ,  m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost()  , 0 , Memno    });                                                                                                

                              
                              


                            }
                            catch(BasicException e){
                                 e.printStackTrace();
                                 new MessageInf(e).show(new JFrame());
                            }
                        
                     }
                        JOptionPane.showMessageDialog(this, "E-Mail Added to sent list. \nKindly note email sending menu should be working in order to send mails..!! ", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        
                        
                    }catch(Exception e ){
                        System.out.println("Failed to Copy File from sourcePath");
                        e.printStackTrace();
                        new MessageInf(e).show(new JFrame());
                    }
                        
                      
 
	//	} catch (MessagingException e) {
			
               //         e.printStackTrace();
                //        new MessageInf(e).show(new JFrame());
                //        throw new RuntimeException(e);
                        
		//}
                
              
              }
                else{
                    JOptionPane.showMessageDialog(this, "No Mail Id Registered", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                
                
                
                
                
                
              }
              else{
                  JOptionPane.showMessageDialog(this, "Please enter message for Mail !! ", "Warning", JOptionPane.WARNING_MESSAGE);
              }
         }
         else{
              JOptionPane.showMessageDialog(this, "Please enter subject for Mail !! ", "Warning", JOptionPane.WARNING_MESSAGE);
         }
                
                
        }
        else{
             JOptionPane.showMessageDialog(this, "Select User Name ID !! ", "Warning", JOptionPane.WARNING_MESSAGE);
        } 
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Select Member Type ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
       } 
       
       
     // for sending email to group list 
       
       if(groupwise_radio.isSelected()){
           
           
           
           if(GroupList_Combo.getSelectedIndex()!=-1){   
           
              if(username_combo.getSelectedIndex()!=-1){
                 if(subject_text.getText()!=null && subject_text.getText().trim().length()>0){
                        if(message_text.getText()!=null && message_text.getText().trim().length()>0){
        
        
                    //    String Memtype =   memtype_combobox.getSelectedItem().toString();
                    //    List<Object> MemEmailIdList = new ArrayList<Object>();

                     //   try{
//
                       //    MemEmailIdList = getEmaiIDFromMemtype(Memtype);
                      //  }
                      //  catch(Exception e){
                      //       e.printStackTrace();
                      //       new MessageInf(e).show(new JFrame());
                      //  }




                        final String username = username_combo.getSelectedItem().toString();
                        final String password = Decr_Password_label.getText();

                        String mail_message = message_text.getText().trim();
                        String Subject = subject_text.getText().trim();




                        String smtphost = null;
                        String SmtpPort = null;


                 //       try{
                  //           smtphost = getSMTPHost(username);
                  //           SmtpPort = getSMTPport(username);
                    //    }
                    //    catch(Exception e){
                     //        e.printStackTrace();
                     //        new MessageInf(e).show(new JFrame());
                     //   }



                 //       Properties props = new Properties();
                 //       props.put("mail.smtp.auth", "true");
                 //       props.put("mail.smtp.starttls.enable", "true");
                  //      props.put("mail.smtp.host",smtphost); 
                  //      props.put("mail.smtp.port",SmtpPort);


                        if(SelectedGroupEmailIDs.size()>0){

                 //       Session session = Session.getInstance(props,
                   //       new javax.mail.Authenticator() {
                   //             protected PasswordAuthentication getPasswordAuthentication() {
                     //                   return new PasswordAuthentication(username, password);
                    //            }   
                   //       });

                    //    try {

                            //    Message message = new MimeMessage(session);
                            //    message.setFrom(new InternetAddress(username));


                              


                                   

                                 //   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SendTo));
                                //    message.setSubject(Subject);
                                 //   message.setText(mail_message);

                                  //  BodyPart messageBodyPart = new MimeBodyPart();

                                //    messageBodyPart.setText(mail_message);

                                 //   Multipart multipart = new MimeMultipart();

                                 //   multipart.addBodyPart(messageBodyPart);

                                //    messageBodyPart = new MimeBodyPart();

                              
                                      try{
                                          
                                           if(fileName_label.getText()!=null && fileName_label.getText().trim().length()>0){
                                                 DataSource source = new FileDataSource(fileName_label.getText());
                                                // saveURL = "/home/dev3/Desktop/Emails Testing/";
                                                 BufferedInputStream in = new BufferedInputStream (new FileInputStream(sourceFile)); 
                                                 System.out.println("BufferedInputStream created");
                                                 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveURL+"//"+fileName));
                                                 System.out.println("BufferedOutputStream created");

                                                 int i1 = 0;
                                                 byte[] bytesIn = new byte[1024];

                                                 while ((i1 = in.read(bytesIn)) >= 0) {
                                                      out.write(bytesIn, 0, i1);
                                                 }
                                                in.close();
                                                out.close();
                                           }
                                           
                                           
                                           try{

                                              
                                              for(int i=0;i<SelectedGroupEmailIDs.size() ; i++ ) { 
                                               
                                                  
                                                    String SendTo = SelectedGroupEmailIDs.get(i).toString();
                                                    String Memno = null;
                                                    Memno = GroupList_Combo.getSelectedItem().toString();
                                                    
                                                    int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO activeemailtable (ID ,EMAILID , SENTTO , SUBJECT , MESSAGE , DOCUMENT  , PRIORITY , CRDATE , CRBY , CRHOST ,FLAG , MEMNO ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.STRING  , Datas.INT , Datas.TIMESTAMP , Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING})                         
                                                                         ).exec(new Object[]{UUID.randomUUID().toString(), username ,SendTo ,Subject , mail_message, fileName ,1, new Date() ,  m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost()  , 0 , Memno    });                                                                                                



                                                 }
                                                JOptionPane.showMessageDialog(this, "E-Mail Added to sent list. \nKindly note email sending menu should be working in order to send mails. !! ", "Warning", JOptionPane.INFORMATION_MESSAGE);
                                                reset();
                                                }
                                                catch(BasicException e){
                                                     e.printStackTrace();
                                                     new MessageInf(e).show(new JFrame());
                                                }     
                                        }catch(Exception e){
                                           System.out.println("Failed to Copy File from sourcePath");
                                           e.printStackTrace();
                                           new MessageInf(e).show(new JFrame());
                                       }
                                    
                                    
                                //    messageBodyPart.setDataHandler(new DataHandler(source));
                                 //   messageBodyPart.setFileName(fileName_label.getText());

                                 //   multipart.addBodyPart(messageBodyPart);
                                 //   message.setContent(multipart);
                                     

                               //     Transport.send(message);


                                 
                        
                 
                        
                       // JOptionPane.showMessageDialog(this, "Mail sent successfully!! ", "Warning", JOptionPane.INFORMATION_MESSAGE);
                       // reset();
                        
 
		//} catch (MessagingException e) {
			
                   //     e.printStackTrace();
                    //    new MessageInf(e).show(new JFrame());
                     //   throw new RuntimeException(e);
                        
		//}
                
              
              }
                else{
                    JOptionPane.showMessageDialog(this, "No Mail Id Registered", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                
                
                
                
                
                
              }
              else{
                  JOptionPane.showMessageDialog(this, "Please enter message for Mail !! ", "Warning", JOptionPane.WARNING_MESSAGE);
              }
         }
         else{
              JOptionPane.showMessageDialog(this, "Please enter subject for Mail !! ", "Warning", JOptionPane.WARNING_MESSAGE);
         }
                
                
        }
        else{
             JOptionPane.showMessageDialog(this, "Select User Name ID. !! ", "Warning", JOptionPane.WARNING_MESSAGE);
        } 
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Select Group Name. ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
           
           
           
           
           
           
           
           
           
       }
       
       
       
       
       
                
                
    }//GEN-LAST:event_send_buttonActionPerformed
    File sourceFile = null;
    String fileName=null;
    private void file_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_file_buttonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                                       
           
               sourceFile = fileChooser.getSelectedFile();
               String filename = sourceFile.getName();
               
               fileName_label.setText(sourceFile.getAbsolutePath().toString());
              
               fileName=filename.toString();
        }
               
    }//GEN-LAST:event_file_buttonActionPerformed

    private void cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_buttonActionPerformed
        reset();
    }//GEN-LAST:event_cancel_buttonActionPerformed

    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
        // TODO add your handling code here:
        // String cust=null;
        String custoid;
        String EmailID=null;
        

        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            String cust = memno_text.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME , EMAIL FROM CUSTOMERS WHERE SEARCHKEY = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING , Datas.STRING})).find(cust.toUpperCase());                      // #CHANGE BY AAKASH... ON 6TH DEC 2013
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    //customer = dlSales.loadCustomerExt(custoid);
                   // memno_text.setText(obj[1].toString());
                    
                    if(obj[2]!=null){
                       EmailID = obj[2].toString();
                       sendto_text.setText(EmailID);
                       
                       
                    }
                    else{
                       JOptionPane.showMessageDialog(this, "No Email Id defined. \n \n Please define Mail id for memnber !! ", "Warning", JOptionPane.WARNING_MESSAGE);  
                    }
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_memno_textKeyPressed

    private void memtype_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memtype_radioItemStateChanged
       if(memtype_radio.isSelected()){
           
        jLabel7.setVisible(true);
        memtype_combobox.setVisible(true);
           
           
        jLabel6.setVisible(false);
        sendto_text.setVisible(false);
        jLabel5.setVisible(false);
        memno_text.setVisible(false);
        jLabel8.setVisible(false);
        GroupList_Combo.setVisible(false);
        noOfMailId_Label.setVisible(false);
       }
       else{
           
        jLabel7.setVisible(false);
        memtype_combobox.setVisible(false); 
           
           
           
           
           
           
       }
    }//GEN-LAST:event_memtype_radioItemStateChanged

    private void individual_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_individual_radioItemStateChanged
       if(individual_radio.isSelected()){
            
            jLabel6.setVisible(true);
            sendto_text.setVisible(true);
            jLabel5.setVisible(true);
            memno_text.setVisible(true);  
           
           jLabel7.setVisible(false);
           memtype_combobox.setVisible(false); 
           jLabel8.setVisible(false);
           GroupList_Combo.setVisible(false);
            noOfMailId_Label.setVisible(false);
       }
       else{
           
           
        
           
           
           
       }
    }//GEN-LAST:event_individual_radioItemStateChanged

    private void groupwise_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupwise_radioItemStateChanged
       if(groupwise_radio.isSelected()){
           jLabel8.setVisible(true);
           GroupList_Combo.setVisible(true); 
           noOfMailId_Label.setVisible(true);
           noOfMailId_Label.setText(null);
           
           
           jLabel6.setVisible(false);
           sendto_text.setVisible(false);
           jLabel5.setVisible(false);
           memno_text.setVisible(false);
           jLabel6.setVisible(false);
           sendto_text.setVisible(false);
           
       }
       else{
           
           
           
           
       }
    }//GEN-LAST:event_groupwise_radioItemStateChanged

   
    
    private void GroupList_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GroupList_ComboItemStateChanged
     if(GroupList_Combo.getSelectedIndex()!=-1){
         String Name= GroupList_Combo.getSelectedItem().toString();
         try{
            SelectedGroupEmailIDs = getGroupEmailIds(Name);
            NoOfMailIds = SelectedGroupEmailIDs.size();
            noOfMailId_Label.setText(NoOfMailIds+". Email IDs. ");
         }
         catch(BasicException BE){
             
         }
     }
    }//GEN-LAST:event_GroupList_ComboItemStateChanged

    private void setDrive_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setDrive_btnActionPerformed
        String Drive = JOptionPane.showInputDialog(null, "Enter shared folder drive for Garuda club plus.   \n Note: Only enter  'X' or 'Y' without any symbols.", "Dialog for Input",
        JOptionPane.INFORMATION_MESSAGE);
        
        
        if(Drive!=null) {
           
                String CurrHost = m_App.getProperties().getHost();
                String crby = m_App.getAppUserView().getUser().getName() ;
                InetAddress addr = null;
                try{
                    addr = InetAddress.getLocalHost();
                }
                catch(UnknownHostException e){
                   e.printStackTrace();
                   new MessageInf(e).show(new JFrame()); 
                }
                String ipAddress = addr.getHostAddress();
                System.out.println("Local host ipaddress : "+ipAddress);
               
                
                try {
                   if( new PreparedSentence(m_App.getSession()
                       , "UPDATE email_fold_drive SET DRIVE=? , CRDATE =? , CRBY=? WHERE ipaddress=?"
                       , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP ,Datas.STRING, Datas.STRING})).exec(new Object[]{Drive,new Date() ,crby ,  ipAddress})<=0){

                   new PreparedSentence(m_App.getSession()
                       , "INSERT INTO email_fold_drive(ID,DRIVE,PC_NAME , ACTIVE , CRDATE , CRBY , ipaddress) VALUES(?,?,?,?,?,?,?)"
                       , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.INT , Datas.TIMESTAMP , Datas.STRING , Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Drive , CurrHost , 1 , new Date() , crby , ipAddress});

                     }
                  JOptionPane.showMessageDialog(this, "Updated Successfully. ", "Success Message", JOptionPane.INFORMATION_MESSAGE);
               } catch (BasicException e) {
                  e.printStackTrace();
                  new MessageInf(e).show(new JFrame());
               }
         
            
            
            
            
            
        }
            
            
   
        
        
        
    }//GEN-LAST:event_setDrive_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Decr_Password_label;
    private javax.swing.JComboBox GroupList_Combo;
    private javax.swing.JButton cancel_button;
    private javax.swing.JLabel fileName_label;
    private javax.swing.JButton file_button;
    private javax.swing.JRadioButton groupwise_radio;
    private javax.swing.JRadioButton individual_radio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTextField memno_text;
    private javax.swing.JComboBox memtype_combobox;
    private javax.swing.JRadioButton memtype_radio;
    private javax.swing.JTextArea message_text;
    private javax.swing.JLabel noOfMailId_Label;
    private javax.swing.JButton send_button;
    private javax.swing.JTextField sendto_text;
    private javax.swing.JButton setDrive_btn;
    private javax.swing.JTextField subject_text;
    private javax.swing.JComboBox username_combo;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "Email Sending Menu";
    }

    public void activate() throws BasicException {
        reset();
        loaddata();
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
         return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        EmailMaster_Table_Model = (EmailMasterTableModel) app.getBean("com.openbravo.pos.sms.EmailMasterTableModel");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        
    }

    public Object getBean() {
       return this;
    }
    
    
    public void loaddata() throws BasicException{
        
       UserName_list = new ArrayList<Object>();
       UserName_list = EmailMaster_Table_Model.getUsername(m_App);
       UserName_Model =  new ComboBoxValModel(UserName_list);
       username_combo.setModel(UserName_Model);
        
       buttongroup();
       memtype_radio.setSelected(true);
       memtype_combobox.setSelectedIndex(-1);
       GroupList_Combo.setSelectedIndex(-1);
       
       List<Object> Memtype = new ArrayList<Object>();
       
       Memtype = getMemtypeName();
       Memtype.add("All");
       
       
       Memtype_Model = new ComboBoxValModel(Memtype);
       memtype_combobox.setModel(Memtype_Model);
       
       // get group details
       
       GroupList = new ArrayList<Object>();
       GroupList = GetGroupList();
       GroupList_Model = new ComboBoxValModel(GroupList);
       GroupList_Combo.setModel(GroupList_Model);
       
        
    }
    public void reset(){
        
        
        sendto_text.setText(null);
        subject_text.setText(null);
        message_text.setText(null);
        fileName_label.setText(null);
        username_combo.setSelectedIndex(-1);
        memno_text.setText(null);
        GroupList_Combo.setSelectedIndex(-1);
    }
    
    
    
    public void buttongroup(){
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(memtype_radio);
        bg.add(individual_radio);
        bg.add(groupwise_radio);
        
    
    }
    
    
    
     public List getMemtypeName() throws BasicException{
       List<Object> GrpNameList = new ArrayList<Object>();
       
        GrpNameList=new PreparedSentence(m_App.getSession(), "SELECT NAME   FROM memtype WHERE ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).list();
       
        return GrpNameList;
        
       }
     
     // get Group list
     
      public List GetGroupList() throws BasicException{
       List<Object> GrpNameList = new ArrayList<Object>();
       
        GrpNameList=new PreparedSentence(m_App.getSession(), "SELECT GROUP_NAME FROM email_group_list WHERE ACTIVE=1 ORDER BY GROUP_NAME", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).list();
       
        return GrpNameList;
        
       }
       
      // get selected group email ids
      
      public List getGroupEmailIds(String GroupName) throws BasicException{
       List<Object> GrpNameList = new ArrayList<Object>();
       
        GrpNameList=new PreparedSentence(m_App.getSession(), "SELECT e.emailidlist  FROM email_grp_mem e  , email_group_list g where \n" +
                                                                "e.groupnameid=g.id and g.GROUP_NAME=?  and e.active=1 order by e.emailidlist", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).list(GroupName);
       
        return GrpNameList;
        
       }
     
     public List getEmaiIDFromMemtype(String Memtype) throws BasicException{
       List<Object> GrpNameList = new ArrayList<Object>();
       
        GrpNameList=new PreparedSentence(m_App.getSession(), "SELECT C.EMAIL   FROM CUSTOMERS C , MEMTYPE M  WHERE  M.ID = C.MEMTYPE AND M.NAME = ? AND C.EMAIL IS NOT NULL ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).list(Memtype);
       
        return GrpNameList;
        
   }
     
     
      public String  getSMTPHost(String UserName) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT SMTPSERVER FROM email_master WHERE USERNAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(UserName);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
   }
      
       public String  getSMTPport(String UserName) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT PORT FROM email_master WHERE USERNAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(UserName);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
   }
      
      
      
    public String  getMemnoFromEmailID(String EmailId) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT SEARCHKEY FROM CUSTOMERS WHERE EMAIL=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(EmailId);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
   } 
// get max emails can sent per hours from master
    
    public Double  getMaxNoOfEmails(String EmailId) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT NOOFMAILS FROM email_master WHERE USERNAME=? AND ACTIVE=1  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(EmailId);
       if(o!=null){
            return Double.parseDouble(o.toString());
       }
       else{
          return 0.00; 
       }
        
        
   } 
    // get max Hours  from master
    
    public Double  getMaxHours(String EmailId) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT HOURS FROM email_master WHERE USERNAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(EmailId);
       if(o!=null){
            return Double.parseDouble(o.toString());
       }
       else{
          return 0.00; 
       }
        
        
   } 
    
    
// get email id from email check 
    
     public String  getEmailIdFromEmailCheck(String EmailId) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT EMAILID FROM EMAILCHECK WHERE EMAILID=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(EmailId);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
   } 
     
// get max Hours  from master
    
    public Double  getNoOfEmailSent(String EmailId) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT NOOFMAILSENT FROM EMAILCHECK WHERE EMAILID=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(EmailId);
       if(o!=null){
            return Double.parseDouble(o.toString());
       }
       else{
          return 0.00; 
       }
        
        
   } 
    
 // get max Hours  from master
    
    public Double  getHourDifferenceFromEmailCheck(String EmailId) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT MINUTE(TIMEDIFF(MAX(NOW()),MAX(CURRTIME)))/60  FROM emailcheck;  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(EmailId);
       if(o!=null){
            return Double.parseDouble(o.toString());
       }
       else{
          return 0.00; 
       }
        
        
   } 
      
   // get url to save general email documents
    
     public String  getGenEmailDocFolderPath() throws BasicException{
       String name="General Email Documents";
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT VALUE  FROM GENERALTABLE WHERE NAME=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(name);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
   }  
     
     
      // get Drive name  to save general documents
    
     public String  getDriveNameForDoc() throws BasicException , UnknownHostException{
       String name= m_App.getProperties().getHost();
       InetAddress addr = InetAddress.getLocalHost();
       String ipAddress = addr.getHostAddress();
       System.out.println("IP address of localhost from Java Program: " + ipAddress);
       
       
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT DRIVE  FROM email_fold_drive WHERE ipaddress=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(ipAddress);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
   }  
     
     
     
}
