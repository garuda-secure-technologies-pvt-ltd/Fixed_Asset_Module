

package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.FloorEditor;
import com.openbravo.pos.Booking.HallBookingMaster;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.openbravo.pos.EVM.ContestantMasterTableModel.ContestantInfo;



public class ContestantMaster extends javax.swing.JPanel  implements JPanelView,BeanFactoryApp{

    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicFacilities dmang;
    private ContestantMasterTableModel ContestantMaster_Table_Model;
     
    public ContestantMaster() {
        initComponents();
    }

   
     private AppView m_App;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        main_panel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        finalNoofContest_text = new javax.swing.JTextField();
        save_mainPanel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        save_Page2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        memname_Text = new javax.swing.JTextField();
        memno_text = new javax.swing.JTextField();
        srno_text = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NameEng_Image = new javax.swing.JLabel();
        Name_Knd_Image = new javax.swing.JLabel();
        selectEngImage = new javax.swing.JButton();
        selectKndImage = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        memPhoto_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        saveUpdates_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        date_text = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        minDue_amt = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        saveDueMaster = new javax.swing.JButton();

        jLabel1.setText("Contestant Master ");

        jLabel3.setText("Final No. of contestant to be selected : ");

        finalNoofContest_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                finalNoofContest_textKeyPressed(evt);
            }
        });

        save_mainPanel.setText("Save");
        save_mainPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_mainPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panel1Layout = new javax.swing.GroupLayout(main_panel1);
        main_panel1.setLayout(main_panel1Layout);
        main_panel1Layout.setHorizontalGroup(
            main_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panel1Layout.createSequentialGroup()
                .addGroup(main_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panel1Layout.createSequentialGroup()
                        .addGap(426, 426, 426)
                        .addComponent(jLabel1))
                    .addGroup(main_panel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(finalNoofContest_text, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(411, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(save_mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );
        main_panel1Layout.setVerticalGroup(
            main_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(120, 120, 120)
                .addGroup(main_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(finalNoofContest_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(save_mainPanel)
                .addGap(83, 83, 83))
        );

        jTabbedPane1.addTab("Main Master", main_panel1);

        save_Page2.setText("Save");
        save_Page2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_Page2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Membership No : ");

        jLabel5.setText("Membership Name : ");

        jLabel6.setText("Sr. No : ");

        memname_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memname_TextActionPerformed(evt);
            }
        });

        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel2.setText("Name Image (English )");

        jLabel7.setText("Name Image (Kannada) ");

        selectEngImage.setText("Select");
        selectEngImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEngImageActionPerformed(evt);
            }
        });

        selectKndImage.setText("Select");
        selectKndImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectKndImageActionPerformed(evt);
            }
        });

        jLabel8.setText("Photo :");

        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        saveUpdates_btn.setText("Save Updates");
        saveUpdates_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveUpdates_btnActionPerformed(evt);
            }
        });

        reset_btn.setText("Reset ");
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memname_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(srno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NameEng_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Name_Knd_Image, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectEngImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectKndImage, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(memPhoto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save_Page2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveUpdates_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(srno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(memname_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(NameEng_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectEngImage))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Name_Knd_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectKndImage)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memPhoto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_Page2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveUpdates_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_btn))
                .addGap(24, 24, 24))
        );

        memname_Text.setEditable(false);
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        saveUpdates_btn.setVisible(false);

        jTabbedPane1.addTab("Select Candidates", jPanel1);

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

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Deactivate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List of Candidates", jPanel2);

        jLabel9.setText("-- >  Select date after which you want to considered the receipt paid by member for due adjustment  : ");

        jLabel10.setText("-- >  Enter minimum amount for voting to be allowed for members : ");

        jButton4.setText("Date");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        saveDueMaster.setText("Save ");
        saveDueMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDueMasterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(minDue_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(date_text, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(saveDueMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel9)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(88, 88, 88)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(minDue_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(saveDueMaster)
                .addGap(74, 74, 74))
        );

        date_text.setEditable(false);

        jTabbedPane1.addTab("Due Amount Master ", jPanel3);

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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void finalNoofContest_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finalNoofContest_textKeyPressed
      char c = evt.getKeyChar();
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(finalNoofContest_text, "Please enter only numbers..");
    
            finalNoofContest_text.setText(null);
     
    }
  }
    }//GEN-LAST:event_finalNoofContest_textKeyPressed
    String Old_No_of_contestantID =null;
    private void save_mainPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_mainPanelActionPerformed
       
           if(finalNoofContest_text.getText()!=null && finalNoofContest_text.getText().trim().length()>0){
               try{
                   Old_No_of_contestantID = GetOldContestantNosID();
               }
               catch(BasicException ex){
                   
               }
               catch(ParseException ex){
                   
               }
               
               
               
               
               
                 Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                             int Finalnoofcontest = Integer.parseInt(finalNoofContest_text.getText().trim());     
                             int active=1;
                     
                           @Override      
                           protected Object transact() throws BasicException { 
          
                             if(Old_No_of_contestantID!=null){ 
                                        int x = new StaticSentence(m_App.getSession()
                                                , "UPDATE evm_candidatesmaster SET ACTIVE=0 , DEACBY=? , DEACDATE=? , DEACHOST=? WHERE ID=?"
                                                , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING }))
                                                .exec(new Object[] { m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() ,Old_No_of_contestantID});
                               
                              }
                             
                            
                              int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO evm_candidatesmaster (ID  , Final_no_of_contest , CRBY , CRDATE , CRHOST ,ACTIVE , REFID) VALUES (?,?,?,?,?,?,?)"                           
                                       , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT ,Datas.STRING, Datas.TIMESTAMP ,Datas.STRING ,Datas.INT ,Datas.STRING  })                         
                                        ).exec(new Object[]{UUID.randomUUID().toString(),Finalnoofcontest,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , active , Old_No_of_contestantID });                                                                                                
                         
                            
                            
                            
                             JOptionPane.showMessageDialog(null, " Updated Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);
                            
           
                            return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                           
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
           
           
           
           }
           else{
               JOptionPane.showMessageDialog(finalNoofContest_text, "Please enter no of final contestant.");
           }
      
    }//GEN-LAST:event_save_mainPanelActionPerformed

    private void memname_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memname_TextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memname_TextActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                memname_Text.setText(customerInfo.toString());
                memno_text.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno_text.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                    memno_text.setText(null);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno_text.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    memname_Text.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            memname_Text.setText(null);
            customerInfo = null;

        }
    }//GEN-LAST:event_memno_textKeyPressed

    FileInputStream EngNameFile = null;
    FileInputStream KndNameFile = null;
    FileInputStream PhotoFile = null;
    String Old_SrNo=null;
    String OldMemIdCheck = null;
    
    
    
    private void save_Page2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_Page2ActionPerformed
       if(customerInfo!=null){
           if(srno_text.getText()!=null && srno_text.getText().trim().length()>0){
           
           
           
                try {
                     if(sourceFile!=null){
                        EngNameFile = new FileInputStream(sourceFile.getAbsolutePath());
                     }
                     if(sourceFile2!=null){
                        KndNameFile = new FileInputStream(sourceFile2.getAbsolutePath());
                     }
                     if(sourceFile3!=null){
                        PhotoFile = new FileInputStream(sourceFile3.getAbsolutePath());
                     }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try{
                    Old_SrNo = getOldSrNo(srno_text.getText().toString());
                    OldMemIdCheck = getMemIdCheckForDup(customerInfo.getId());
                }
                catch(BasicException e){
                    
                }
                
                
                if(Old_SrNo==null){
                    
                    if(OldMemIdCheck==null){
                
            
                 Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                             int Finalnoofcontest = Integer.parseInt(finalNoofContest_text.getText().trim());     
                             int active=1;
                             
                           String SrNo = srno_text.getText().trim();
                         
                             
                             
                           @Override      
                           protected Object transact() throws BasicException { 
          
                            
                             
                            
                              int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO evm_candidates (ID  , SRNO , MEMNAME , MEMID , ENGNAME ,KNDNAME , PHOTO , ACTIVE , VALIDVOTES , CRBY , CRDATE , CRHOST ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                       , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.OBJECT ,Datas.OBJECT ,Datas.OBJECT ,Datas.INT,Datas.INT,Datas.STRING,Datas.TIMESTAMP,Datas.STRING   })                         
                                        ).exec(new Object[]{UUID.randomUUID().toString(),SrNo,customerInfo.getName() , customerInfo.getId(),EngNameFile,KndNameFile, PhotoFile , active , 0 ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() });                                                                                                
                         
                            
                                  Reset();
                                  try{ 
                                        loaddata();
                                   }
                                   catch(ParseException e){

                                   }
                             JOptionPane.showMessageDialog(null, " Updated Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);
                            
           
                            return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                           
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
                    }
                    else{
                         JOptionPane.showMessageDialog(finalNoofContest_text, "Selected Candidate  already present. \n Please enter another Candidate.");
                    }
           
                }
                else{
                    JOptionPane.showMessageDialog(finalNoofContest_text, "Serial No. already present. \n Please enter another Sr.No.");
                }
           
           
           
           }
           else{
            JOptionPane.showMessageDialog(finalNoofContest_text, "Please enter Sr.No.");
          }
           
       }
       else{
           JOptionPane.showMessageDialog(finalNoofContest_text, "Please select Member.");
       }
    }//GEN-LAST:event_save_Page2ActionPerformed
    File sourceFile = null;
    File sourceFile2 = null;
    File sourceFile3 = null;
    BufferedImage bi1 =null;
    BufferedImage bi2 =null;
    BufferedImage bi3 =null;
    private void selectEngImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectEngImageActionPerformed
       JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
               sourceFile = fileChooser.getSelectedFile();
               String filename = sourceFile.getName();
               try{
                    bi1 = ImageIO.read(sourceFile);
                    
                    
                    
               }
               catch(IOException e){
                   
               } 
              ImageIcon image = new ImageIcon(bi1);   
              NameEng_Image.setIcon(image);
        }
    }//GEN-LAST:event_selectEngImageActionPerformed

    private void selectKndImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectKndImageActionPerformed
      JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
               sourceFile2 = fileChooser.getSelectedFile();
               String filename = sourceFile2.getName();
               try{
                    bi2 = ImageIO.read(sourceFile2);
                    
                   
                    
                    
               }
               catch(IOException e){
                   
               } 
              ImageIcon image = new ImageIcon(bi2);   
              Name_Knd_Image.setIcon(image);
        }
    }//GEN-LAST:event_selectKndImageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
               sourceFile3 = fileChooser.getSelectedFile();
               String filename = sourceFile3.getName();
               try{
                    bi3 = ImageIO.read(sourceFile3);
                     if (bi3.getType() != BufferedImage.TYPE_INT_RGB) {
                        BufferedImage bi3_N = new BufferedImage(memPhoto_label.getWidth(), memPhoto_label.getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics big = bi3_N.getGraphics();
                        big.drawImage(bi3, 0, 0, memPhoto_label.getWidth(), memPhoto_label.getHeight(), null);
                        bi3 = bi3_N;
                       // imageLabel=new JLabel(image);
                        System.out.println("Image loaded to buffer.");
                      } 
                    
                    
                    
               }
               catch(IOException e){
                   
               } 
              ImageIcon image = new ImageIcon(bi3);   
              memPhoto_label.setIcon(image);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
                 
                  if(jTable1.getSelectedRow()<ContestantMaster_Table_Model.getSize()){
                 
                  int row = jTable1.getSelectedRow();
                  ContestantInfo showdata = ContestantMaster_Table_Model.getList().get(row);
                      
                   String id = showdata.getID();    
                      
                      // DEACTIVATE 
               try {
                            int update_Email_master =  new PreparedSentence(m_App.getSession(), "UPDATE evm_candidates  SET ACTIVE=0  , DEACBY=? , DEACDATE=? , DEACHOST=? WHERE ID = ? "
                                                                                 , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec
                                                                                  (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , id  });


                            Reset();
                            try{ 
                                 loaddata();
                            }
                             catch(ParseException e){

                             }

                            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                   } catch (BasicException ex) {
                        Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
                  
                 
                 
                 
                  }
                 
             }
             
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    public String Oldid;
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to edit details ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
                 
                  if(jTable1.getSelectedRow()<ContestantMaster_Table_Model.getSize()){
                 
                  int row = jTable1.getSelectedRow();
                  ContestantInfo showdata = ContestantMaster_Table_Model.getList().get(row);
                      
                   Oldid = showdata.getID();    
                   String  MemID =    showdata.getMEMID();
                   String SrNo = showdata.getSRNO();
                   
              /*     
                  if(showdata.getENGNAME()!=null){
                      bi1=showdata.getENGNAME();
                       ImageIcon image = new ImageIcon(bi1);   
                       NameEng_Image.setIcon(image);
                       sourceFile = new File("image1.jpg");
                       try{
                            ImageIO.write(bi1, "jpg", sourceFile);
                       }
                       catch(IOException e){
                           
                       }
                  }
                  else{
                       NameEng_Image.setIcon(null);
                  }
                  if(showdata.getKNDNAME()!=null){
                      bi2=showdata.getKNDNAME();
                      ImageIcon image = new ImageIcon(bi2);   
                      Name_Knd_Image.setIcon(image);
                      sourceFile2 = new File("image2.jpg");
                      try{
                            ImageIO.write(bi2, "jpg", sourceFile2);
                       }
                       catch(IOException e){
                           
                       }
                  }
                  else{
                      Name_Knd_Image.setIcon(null);
                  }
                  if(showdata.getPHOTO()!=null){
                       bi3=showdata.getPHOTO();
                       ImageIcon image = new ImageIcon(bi3);   
                       memPhoto_label.setIcon(image);
                       sourceFile3 = new File("image3.jpg");
                       try{
                            ImageIO.write(bi3, "jpg", sourceFile3);
                       }
                       catch(IOException e){
                           
                       }
                  }
                  else{
                       memPhoto_label.setIcon(null);
                  }
                 */
                 
                 
                    try {
                            Object[] obj = dmang.getMemberbyID(MemID);

                            if (obj == null) {
                                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                                memno_text.setText(null);
                            } else {
                                customerInfo = new CustomerInfo(MemID);
                                customerInfo.setName(obj[1].toString());
                                customerInfo.setSearchkey(obj[0].toString());
                                
                               // customerInfo.setAccno(obj[4].toString());
                               // System.out.println(customerInfo.getAccno());
                               // memname_Text.setText(obj[1].toString());
                            }
                        }
                    
                    catch (Exception e) {
                            e.printStackTrace();
                        }
                   
                   
                   
                    srno_text.setText(SrNo);
                    memno_text.setText(customerInfo.getSearchkey());
                    memname_Text.setText(customerInfo.getName());
                    
                    jTabbedPane1.setSelectedIndex(1);
                    saveUpdates_btn.setVisible(true);
                    save_Page2.setVisible(false);
                    jButton16.setEnabled(false);
                    memno_text.setEditable(false);
                    
                    
                    
                  }
                 
             }
             
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void saveUpdates_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveUpdates_btnActionPerformed
        if(srno_text.getText()!=null && srno_text.getText().trim().length()>0){
            
            
            
            try {
                     if(sourceFile!=null){
                        EngNameFile = new FileInputStream(sourceFile.getAbsolutePath());
                     }
                     if(sourceFile2!=null){
                        KndNameFile = new FileInputStream(sourceFile2.getAbsolutePath());
                     }
                     if(sourceFile3!=null){
                        PhotoFile = new FileInputStream(sourceFile3.getAbsolutePath());
                     }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            
                try{
                    Old_SrNo = getOldSrNoEditing(srno_text.getText().toString() ,Oldid);
                }
                catch(BasicException e){
                    
                } 
            
              if(Old_SrNo==null){
             
                Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                             int Finalnoofcontest = Integer.parseInt(finalNoofContest_text.getText().trim());     
                             int active=1;
                             
                           String SrNo = srno_text.getText().trim();
                         
                             
                             
                           @Override      
                           protected Object transact() throws BasicException { 
          
                            
                             
                               
                               
                            
                              int x = new StaticSentence(m_App.getSession()
                                                , "UPDATE evm_candidates SET SRNO=? , ENGNAME=? , KNDNAME=? , PHOTO=?  , CRBY=? , CRDATE=? , CRHOST=? WHERE ID=?"
                                                , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.OBJECT , Datas.OBJECT ,Datas.OBJECT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING }))
                                                .exec(new Object[] {SrNo , EngNameFile ,KndNameFile ,PhotoFile  , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()  , Oldid});
                            
                                  Reset();
                                  try{ 
                                        loaddata();
                                   }
                                   catch(ParseException e){

                                   }
                             JOptionPane.showMessageDialog(null, " Updated Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);
                            
           
                            return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                           
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
           
              }
              else{
                   JOptionPane.showMessageDialog(finalNoofContest_text, "Serial No. already present. \n Please enter another Sr.No.");
              }
            
        }
        else{
             JOptionPane.showMessageDialog(this, "Please enter Sr. No. !", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_saveUpdates_btnActionPerformed

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
       Reset();
    }//GEN-LAST:event_reset_btnActionPerformed

    Double MinDueAmt = 0.00;
    String MinDateStr = null;
    
    private void saveDueMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDueMasterActionPerformed
        if(date_text.getText().trim().length()>0  && minDue_amt.getText().trim().length()>0){
            
            Date d = new Date();
            
            MinDueAmt = Double.parseDouble(minDue_amt.getText());
            MinDateStr = date_text.getText();
            
             Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                           
                            
                            String Name1 = "Date For Voting";
                            String Name2 = "Min DueAmt For Voting"; 
                             
                           @Override      
                           protected Object transact() throws BasicException { 
          
                            
                             
                               
                               
                            
                             if( new PreparedSentence(m_App.getSession()
                                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                                        , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{MinDueAmt,Name2})<=0){

                                    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE})).exec(new Object[]{UUID.randomUUID().toString(),Name2,MinDueAmt});
                              }
                               
                             if( new PreparedSentence(m_App.getSession()
                                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{MinDateStr,Name1})<=0){

                                    new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name1,MinDateStr});
                              }
                             
                             
                             
                             
                             Reset();
                                  try{ 
                                        loaddata();
                                   }
                                   catch(ParseException e){

                                   }
                             JOptionPane.showMessageDialog(null, " Updated Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);
                            
           
                            return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                           
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
           
            
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter data correctly", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_saveDueMasterActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         Date date1=new Date();
       try {
            date1 = (Date) Formats.TIMESTAMP.parseValue(date_text.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, date1);
        if (date1 != null) {
           
                date_text.setText(Formats.TIMESTAMP.formatValue(date1));
           
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameEng_Image;
    private javax.swing.JLabel Name_Knd_Image;
    private javax.swing.JTextField date_text;
    private javax.swing.JTextField finalNoofContest_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel1;
    private javax.swing.JLabel memPhoto_label;
    private javax.swing.JTextField memname_Text;
    private javax.swing.JTextField memno_text;
    private javax.swing.JTextField minDue_amt;
    private javax.swing.JButton reset_btn;
    private javax.swing.JButton saveDueMaster;
    private javax.swing.JButton saveUpdates_btn;
    private javax.swing.JButton save_Page2;
    private javax.swing.JButton save_mainPanel;
    private javax.swing.JButton selectEngImage;
    private javax.swing.JButton selectKndImage;
    private javax.swing.JTextField srno_text;
    // End of variables declaration//GEN-END:variables



 public String getTitle() {
       return "Main Master";
    }

    public void activate() throws BasicException  {
       
       try{ 
            loaddata();
       }
       catch(ParseException e){
           
       }
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       this.m_App = app;
       dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
       dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
      
    }

    public Object getBean() {
         return this;
    }

    int OldFinalnoof_Candidates = 0;
    public void loaddata() throws BasicException , ParseException{
        
        Old_No_of_contestantID = GetOldContestantNosID();
        OldFinalnoof_Candidates = getFinalNoOfCandidates();
        finalNoofContest_text.setText(""+OldFinalnoof_Candidates);
      
        ContestantMaster_Table_Model  = ContestantMasterTableModel.loadEmailInfo(m_App);
        jTable1.setModel(ContestantMaster_Table_Model.getTableModel()); 
        
        
        Object[] obj15 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Date For Voting'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        if(obj15!=null){
            String Strdate = obj15[0].toString();
            
             Date date1 = (Date) Formats.TIMESTAMP.parseValue(Strdate);
             MinDateStr = Formats.TIMESTAMP.formatValue(date1);
             date_text.setText(MinDateStr);
        }
        
        Object[] obj16 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Min DueAmt For Voting'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        if(obj16!=null){
            String Str = obj16[0].toString();
            MinDueAmt = Double.parseDouble(Str);
            minDue_amt.setText(MinDueAmt+"");
        }
        
        
    }
    
    public void Reset(){
        srno_text.setText(null);
        memno_text.setText(null);
        memname_Text.setText(null);
        NameEng_Image.setIcon(null);
        Name_Knd_Image.setIcon(null);
        memPhoto_label.setIcon(null);
        
        jButton16.setEnabled(true);
        memno_text.setEditable(true);
        customerInfo=null;
        save_Page2.setVisible(true);
        saveUpdates_btn.setVisible(false);
        
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
 // Get Old Id of no of contestant    
 public String  GetOldContestantNosID() throws ParseException , BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select ID from evm_candidatesmaster WHERE ACTIVE=1  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find();
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
 // get final no of Candidates
     
      public int  getFinalNoOfCandidates() throws ParseException , BasicException{
      
       int opass = 0;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession(), "SELECT Final_no_of_contest FROM evm_candidatesmaster where ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find();
       if(o!=null){
            opass = Integer.parseInt(o.toString());
            return opass; 
       }
       else{
          return 0; 
       }
      }


   
      
      
          
 // Get Old Sr No.
 public String  getOldSrNo(String Srno) throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select SRNO from evm_candidates WHERE ACTIVE=1 AND SRNO=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(Srno);
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
    
      
    
  // Get Old Sr No. for Editing of exsisting record
 
 public String  getOldSrNoEditing(String Srno , String OldId) throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select SRNO from evm_candidates WHERE ACTIVE=1 AND SRNO=? AND ID!=?  ", new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING }) ,SerializerReadString.INSTANCE).find(new Object[] { Srno , OldId });
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
    
 
 
   //Check whether customer is present or not....... 
 
 public String  getMemIdCheckForDup(String Memid ) throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select MEMID from evm_candidates WHERE ACTIVE=1 AND  MEMID=?  ", new SerializerWriteBasic(new Datas[] {Datas.STRING  }) ,SerializerReadString.INSTANCE).find(new Object[] {  Memid });
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
    
 
      
}
