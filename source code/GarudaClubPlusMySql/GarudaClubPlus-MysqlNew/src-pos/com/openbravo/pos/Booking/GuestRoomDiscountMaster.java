/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class GuestRoomDiscountMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private DiscountTableModel Discount_Table;
    private DiscountTableModel Discount_Hall_list;
    private List<Object> roomType_list ;
    private GuestRoomTableModel RoomTableModel;
    private ComboBoxValModel RoomTypeListModel ;
    
    private List<BookGuestRoomTableModel.GuestRoomTableInfo> RoomList;
    private BookGuestRoomTableModel Book_GuestRoomTable_Model;
    
    public GuestRoomDiscountMaster() {
        initComponents();
        main_panel.setVisible(true);
        fix_chrg_panel.setVisible(false);
        fixcharge_option.setSelected(false);
        save_changes.setVisible(false);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        room_combo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        days = new javax.swing.JTextField();
        perc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fixcharge_option = new javax.swing.JCheckBox();
        fix_chrg_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        fixed_chr = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        save_changes = new javax.swing.JButton();
        save = new javax.swing.JButton();
        rooml_id_label = new javax.swing.JTextField();
        ID_label = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        edit = new javax.swing.JButton();
        deactivate = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Guest Room Cancellation Master");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Select Room type ");

        room_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        room_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                room_comboItemStateChanged(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("*");

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("*");

        jLabel2.setText("If hall  Cancel Before   ");

        jLabel4.setText("Cancellation Charge : ");

        days.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                daysKeyReleased(evt);
            }
        });

        perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percKeyReleased(evt);
            }
        });

        jLabel5.setText("%   of total amount.");

        jLabel3.setText("Days. ");

        fixcharge_option.setText("Do you want to add fixed charges ?");
        fixcharge_option.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fixcharge_optionItemStateChanged(evt);
            }
        });

        jLabel9.setText("Fixed cancellation charge :");

        fixed_chr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fixed_chrKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout fix_chrg_panelLayout = new javax.swing.GroupLayout(fix_chrg_panel);
        fix_chrg_panel.setLayout(fix_chrg_panelLayout);
        fix_chrg_panelLayout.setHorizontalGroup(
            fix_chrg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fix_chrg_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fixed_chr, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        fix_chrg_panelLayout.setVerticalGroup(
            fix_chrg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fix_chrg_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fix_chrg_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fixed_chr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(102, 0, 0));
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        save_changes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save_changes.setForeground(new java.awt.Color(0, 102, 0));
        save_changes.setText("Save Changes");
        save_changes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_changesActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save.setForeground(new java.awt.Color(0, 102, 0));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(room_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(perc))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(days, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rooml_id_label)
                            .addComponent(ID_label, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fix_chrg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cancel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                        .addComponent(save_changes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)
                                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fixcharge_option)
                                .addGap(110, 110, 110))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(room_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(rooml_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(ID_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(fixcharge_option)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fix_chrg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(save_changes)
                    .addComponent(save))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jLabel6.setForeground(Color.RED);
        jLabel7.setForeground(Color.RED);
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        save_changes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        rooml_id_label.setVisible(false);
        ID_label.setVisible(false);

        jTabbedPane1.addTab("Menu", jPanel2);

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

        edit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        edit.setForeground(new java.awt.Color(0, 102, 51));
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        deactivate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deactivate.setForeground(new java.awt.Color(153, 0, 0));
        deactivate.setText("Deactivate");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deactivate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit)
                    .addComponent(deactivate))
                .addGap(0, 99, Short.MAX_VALUE))
        );

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
        deactivate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/deactivate.png"))); // NOI18N

        jTabbedPane1.addTab("Cancellation Charges List", jPanel3);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setForeground(Color.BLUE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fixcharge_optionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fixcharge_optionItemStateChanged
        if(fixcharge_option.isSelected()){
            fix_chrg_panel.setVisible(true);
            fixed_chr.setText(null);
        }
        else{
            fix_chrg_panel.setVisible(false);
            fixed_chr.setText(null);
        }
    }//GEN-LAST:event_fixcharge_optionItemStateChanged

    private void fixed_chrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fixed_chrKeyReleased
        char c = evt.getKeyChar();
        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {
            if(!Character.isDigit(c))
            {
                if(c!='.')
                {
                    JOptionPane.showMessageDialog(fixed_chr, "Please enter only numbers..");
                    fixed_chr.setText(null);
                }
            }
        }
    }//GEN-LAST:event_fixed_chrKeyReleased

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        reset();
    }//GEN-LAST:event_cancelActionPerformed

    private void save_changesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_changesActionPerformed
       if(days.getText()!=null && days.getText().trim().length()>0){
           if(perc.getText()!=null && perc.getText().trim().length()>0){
              Days = Integer.parseInt(days.getText());
              Cancld_Perc = Double.parseDouble(perc.getText());
              String ID = ID_label.getText();
              active=1; 
              String RoomId = rooml_id_label.getText();
              
              int x = 0;
              try {
                   x = CheckCancelledChargeDuplication(RoomId , Days);
               } catch (BasicException ex) {
                   Logger.getLogger(HallDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
               }
              
               if(x==1){
              
              try {
                              int Update_Data =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_cancel_offrs  SET DAYS=? , PERC_RATE=?   , CRBY=? ,  CRDATE=? , CRHOST=? , ACTIVE=?  WHERE ID = ? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.DOUBLE ,  Datas.STRING , Datas.TIMESTAMP ,Datas.STRING ,   Datas.INT ,Datas.STRING})).exec
                                                    (new Object[]{Days ,Cancld_Perc ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , active,  ID });
                      
                              loaddata();
                              JOptionPane.showMessageDialog(this, "Data Updated  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                              reset();
                              
                              
                 }
                 catch (BasicException ex) {
                             JOptionPane.showMessageDialog(this, ex.getCause() , " Error..! ", JOptionPane.ERROR_MESSAGE);       
                             Logger.getLogger(DiscountTableModel.class.getName()).log(Level.SEVERE, null, ex);
                 }
              
              
              
               }else{
                   JOptionPane.showMessageDialog(this, "Cancallation for Room already Exits ..!! \n Please edit or deactivate it to create new.  ", " Error", JOptionPane.ERROR_MESSAGE);
              }
                 
               
           }
       }
    }//GEN-LAST:event_save_changesActionPerformed

    private int Days;
    private Double Cancld_Perc;
    private String Room_ID;
    private Double Fixed_charges;
    private int active;
    
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
      if(room_combo.getSelectedIndex()!=-1){
          if(days.getText()!=null && days.getText().trim().length()>0){
              if(perc.getText()!=null && perc.getText().trim().length()>0){
                  
                Days = Integer.parseInt(days.getText());
                Cancld_Perc = Double.parseDouble(perc.getText());
                Room_ID = rooml_id_label.getText();
                
                 int x=0;
                
                try {
                    x = CheckCancelledChargeDuplication(Room_ID, Days);
                } catch (BasicException ex) {
                    Logger.getLogger(HallDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 if(x==1){
                
                if(fixcharge_option.isSelected()){
                    if(fixed_chr.getText()!=null && fixed_chr.getText().trim().length()>0){
                        Fixed_charges = Double.parseDouble(fixed_chr.getText());
                    }
                    else{
                        Fixed_charges = 0.00;
                    }
                }
                else{
                     Fixed_charges = 0.00;
                }
                
                
                active=1;
                  
                 try {
                   int  insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_cancel_offrs (ID ,ROOMTYPE , DAYS , PERC_RATE ,CRBY, CRDATE, CRHOST , ACTIVE , FIX_CHARGE) VALUES (?,?,?,?,?,?,?,?,?)"                           
                           , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.INT  , Datas.DOUBLE, Datas.STRING ,Datas.TIMESTAMP, Datas.STRING , Datas.INT , Datas.DOUBLE})                         
                           ).exec(new Object[]{UUID.randomUUID().toString() ,Room_ID ,  Days ,Cancld_Perc  ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , active , Fixed_charges});                                                                                                
               
                    JOptionPane.showMessageDialog(this, "Data Saved  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    loaddata();
                    reset();
               } catch (BasicException ex) {
                   Logger.getLogger(HallDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                }
                 
                 
                 
             }
            else{
                 JOptionPane.showMessageDialog(this, " Cancallation for Room already Exits ..!! \n Please edit or deactivate it to create new. ", " Error", JOptionPane.ERROR_MESSAGE);
             } 
              
              }
              else{
                  JOptionPane.showMessageDialog(this, " Enter percentage cancellation charge..!! ", " room Type", JOptionPane.ERROR_MESSAGE);
              }
          }
          else{
               JOptionPane.showMessageDialog(this, "Enter no of days ..! ", " room Type", JOptionPane.ERROR_MESSAGE);
          }
      }
      else{
           JOptionPane.showMessageDialog(this, "Select Room type first ", " room Type", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_saveActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        if(jTable1.getSelectedRow()!=-1){
               if(jTable1.getSelectedRow()<Discount_Table.getRoom_OfferSize()){
                   int row = jTable1.getSelectedRow();
                   DiscountTableModel.Room_DiscountInfo EditData = Discount_Table.getRoom_DiscountOfferList().get(row);
                   List<Object> RoomList = new ArrayList<Object>();
                   
                   String RoomType = EditData.getROOMTYPE();
                   
                   room_combo.setEnabled(false);
                   days.setText(""+EditData.getDAYS());
                   perc.setText(""+EditData.getPERC_RATE());
                   fixed_chr.setText(""+EditData.getFIX_CHARGE());
                   jTabbedPane1.setSelectedIndex(0);
                   ID_label.setText(EditData.getId());
                   save_changes.setVisible(true);
                   save.setVisible(false);
                   fix_chrg_panel.setVisible(true);
                   fixcharge_option.setSelected(true);
                   fixed_chr.setText(""+EditData.getFIX_CHARGE());
                   String roomtype = EditData.getROOMTYPE();
                   
                   for(int i=0;i<room_combo.getItemCount();i++){
                       String x = room_combo.getItemAt(i).toString();
                       if(x.equals(roomtype)){
                           room_combo.setSelectedIndex(i);
                       }
                       
                       
                   }
                   
                   
               }
        }
        
    }//GEN-LAST:event_editActionPerformed

    private void room_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_room_comboItemStateChanged
       if(room_combo.getSelectedIndex()!=-1){
          String RoomType = room_combo.getSelectedItem().toString();
           
          try {
               Book_GuestRoomTable_Model = BookGuestRoomTableModel.loadInstanceGuestInfo(m_App, RoomType);
           } catch (BasicException ex) {
               Logger.getLogger(GuestRoomDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
           }
          
          RoomList =  (List<BookGuestRoomTableModel.GuestRoomTableInfo>) Book_GuestRoomTable_Model.getGuestRoomPath();
            
          BookGuestRoomTableModel.GuestRoomTableInfo editData = (BookGuestRoomTableModel.GuestRoomTableInfo) ((BookGuestRoomTableModel.GuestRoomTableInfo)RoomList.get(0));
           
          rooml_id_label.setText(editData.getId());
          
           
       }
    }//GEN-LAST:event_room_comboItemStateChanged

    private void daysKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_daysKeyReleased
    char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(days, "Please enter only numbers..");
    
            days.setText(null);
         
    }
    }
    }//GEN-LAST:event_daysKeyReleased

    private void percKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percKeyReleased
    char c = evt.getKeyChar();
    Double Percentage;
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
        if(!Character.isDigit(c))
    {  
         if(c!='.')
          {
             JOptionPane.showMessageDialog(perc, "Please enter only numbers..");
             perc.setText(null);
          }
        
    }
        else{
        Percentage = Double.parseDouble(perc.getText());
        if(Percentage >= 101.00){
             JOptionPane.showMessageDialog(perc, "Please enter upto 100% only.");
             perc.setText(null);
        }
         
          if(Percentage==100.00 || Percentage>100.00){
          fixed_chr.setEditable(false);
          fixed_chr.setText(null);
            
         }
         else{
            
           fixed_chr.setEditable(true);
        }
    
        
         
    }
    } 
    
    }//GEN-LAST:event_percKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID_label;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField days;
    private javax.swing.JButton deactivate;
    private javax.swing.JButton edit;
    private javax.swing.JPanel fix_chrg_panel;
    private javax.swing.JCheckBox fixcharge_option;
    private javax.swing.JTextField fixed_chr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTextField perc;
    private javax.swing.JComboBox room_combo;
    private javax.swing.JTextField rooml_id_label;
    private javax.swing.JButton save;
    private javax.swing.JButton save_changes;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
       loaddata();
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
        RoomTableModel = (GuestRoomTableModel) app.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
    }

    public Object getBean() {
        return this;
    }
    
    public void reset(){
      jTabbedPane1.setSelectedIndex(0);
      days.setText(null);
      perc.setText(null);
      fix_chrg_panel.setVisible(false);
      rooml_id_label.setText(null);
      room_combo.setEnabled(true);
      fixed_chr.setText(null);
      fixcharge_option.setSelected(false);
      ID_label.setText(null);
      rooml_id_label.setText(null);
      room_combo.setSelectedIndex(-1);
     
       
    }
    public void loaddata() throws BasicException{
        
       
        save_changes.setVisible(false);
        save.setVisible(true);
        
        roomType_list = new ArrayList<Object>();
        roomType_list = RoomTableModel.RoomType_NamesList_Active(m_App);
        RoomTypeListModel = new ComboBoxValModel(roomType_list);
        room_combo.setModel(RoomTypeListModel);
        
        Discount_Table = DiscountTableModel.loadInstanceRoom_Discount_Offers(m_App);
        showPanelInfo(Discount_Table);
    }
     
    
    
    public void showPanelInfo(DiscountTableModel Discount_offers){
        jTable1.setModel(Discount_Table.getTableModel2());
   }
    
    
    
    
     public int CheckCancelledChargeDuplication(String RoomID , int Days) throws BasicException{
       Object o = null;
        int flag = 0;
       o=new PreparedSentence(m_App.getSession(), "SELECT ID FROM guestroom_cancel_offrs  WHERE ROOMTYPE = ? AND DAYS=? AND ACTIVE=1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT }) , SerializerReadString.INSTANCE).find(new Object[]{RoomID , Days } );
       if(o!=null){
            flag = 0;
        }
       else{
           flag = 1;
       }
       return flag;
   }
    
    
}
