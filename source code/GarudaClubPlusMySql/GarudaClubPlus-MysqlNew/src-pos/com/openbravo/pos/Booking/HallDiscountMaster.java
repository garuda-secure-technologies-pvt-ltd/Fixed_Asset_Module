
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
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
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class HallDiscountMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private DiscountTableModel Discount_Table;
    private DiscountTableModel Discount_Hall_list;

    public HallDiscountMaster() {
        initComponents();
        main_panel.setVisible(true);
        save.setVisible(true);
        save_changes.setVisible(false);
        fix_chrg_panel.setVisible(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        days = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        perc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        save_changes = new javax.swing.JButton();
        ID_label = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        hallCombo = new javax.swing.JComboBox();
        fixcharge_option = new javax.swing.JCheckBox();
        fix_chrg_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        fixed_chr = new javax.swing.JTextField();
        hall_id_label = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
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
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Hall Cancellation Master");

        jLabel2.setText("If hall  Cancel Before   ");

        days.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                daysKeyReleased(evt);
            }
        });

        jLabel3.setText("Days. ");

        jLabel4.setText("Cancellation Charge : ");

        perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percKeyReleased(evt);
            }
        });

        jLabel5.setText("%   of total amount.");

        save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save.setForeground(new java.awt.Color(0, 102, 51));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("*");

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("*");

        save_changes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save_changes.setForeground(new java.awt.Color(0, 153, 51));
        save_changes.setText("Save Changes");
        save_changes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_changesActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Select Hall ");

        hallCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        hallCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hallComboItemStateChanged(evt);
            }
        });

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
        cancel.setForeground(new java.awt.Color(204, 0, 0));
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(cancel)
                .addGap(18, 18, 18)
                .addComponent(save_changes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hallCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fixcharge_option)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(perc, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(fix_chrg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(181, 181, 181)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ID_label, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(hall_id_label))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(hallCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(26, 26, 26)
                        .addComponent(fixcharge_option)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fix_chrg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(hall_id_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ID_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_changes)
                    .addComponent(save)
                    .addComponent(cancel))
                .addGap(57, 57, 57))
        );

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        jLabel6.setForeground(Color.RED);
        jLabel7.setForeground(Color.RED);
        save_changes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        ID_label.setVisible(false);
        hall_id_label.setVisible(false);
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        jTabbedPane1.addTab("Menu", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sr No.", "Hall Name", "No of Days", "Cancellation Charge"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        edit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        edit.setForeground(new java.awt.Color(0, 153, 0));
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        deactivate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deactivate.setForeground(new java.awt.Color(102, 0, 0));
        deactivate.setText("Deactivate");
        deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(deactivate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit)
                    .addComponent(deactivate))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
        deactivate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/deactivate.png"))); // NOI18N

        jTabbedPane1.addTab("Cancellation charges list", jPanel2);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private int Days;
    private Double Cancld_Perc;
    private int active;
    private hallTableModel Hall;
    private List<hallTableModel.HallTableInfo> halllist ;
    private ComboBoxValModel HallListModel ;
    private BookHallTableModel hallBooking;
    private List<BookHallTableModel.HallAvailibilityInfo> hallDetails;
    private String Hall_ID;
    private Double Fixed_charges;
    
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
       if(hallCombo.getSelectedIndex()!=-1){
        if(days.getText()!=null && days.getText().trim().length()>0){
            if(perc.getText()!=null && perc.getText().trim().length()>0){
           
                Days = Integer.parseInt(days.getText());
                Cancld_Perc = Double.parseDouble(perc.getText());
                Hall_ID = hall_id_label.getText();
                int x=0;
                
                try {
                    x = CheckCancelledChargeDuplication(Hall_ID, Days);
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
                   int  insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO hall_cancellation_offeres (ID ,HALL_NAME , DAYS , PERC_RATE ,CRBY, CRDATE, CRHOST , ACTIVE , FIX_CHARGE) VALUES (?,?,?,?,?,?,?,?,?)"                           
                           , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.INT  , Datas.DOUBLE, Datas.STRING ,Datas.TIMESTAMP, Datas.STRING , Datas.INT , Datas.DOUBLE})                         
                           ).exec(new Object[]{UUID.randomUUID().toString() ,Hall_ID ,  Days ,Cancld_Perc  ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , active , Fixed_charges});                                                                                                
               
                    
                    JOptionPane.showMessageDialog(this, "Data Saved  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    loaddata();
                    reset();
               } catch (BasicException ex) {
                   Logger.getLogger(HallDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
                   JOptionPane.showMessageDialog(this, ex.getCause() , " Error .. !", JOptionPane.ERROR_MESSAGE);
                }
                 
            }
            else{
                 JOptionPane.showMessageDialog(this, " Cancallation for hall already Exits ..!! \n Please edit or deactivate it to create new. ", " Error", JOptionPane.ERROR_MESSAGE);
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
           JOptionPane.showMessageDialog(this, "Select Hall First..! ", " room Type", JOptionPane.ERROR_MESSAGE);
       }
     
    }//GEN-LAST:event_saveActionPerformed

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

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
          if(jTable1.getSelectedRow()!=-1){
               if(jTable1.getSelectedRow()<Discount_Table.getOfferSize()){
                   
               int row = jTable1.getSelectedRow();
                   DiscountTableModel.DiscountInfo EditData = Discount_Table.getDiscountOfferList().get(row);
                   List<Object> HallList = new ArrayList<Object>();
                   
                   String hallname = EditData.getHallName();
                   HallList = Discount_Hall_list.getHallNameList(m_App);
                   for(int i=0;i<HallList.size();i++){
                       String h = HallList.get(i).toString();
                       if(hallname.equals(h)){
                           hallCombo.setSelectedIndex(i);
                           break;
                       }
                   }
                   
                   hallCombo.setEnabled(false);
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
                   System.out.println(EditData.getHallName());
                   for(int i=0;i<hallCombo.getItemCount();i++){
                       String x = hallCombo.getItemAt(i).toString();
                       if(x.equals(EditData.getHallName()))
                       {
                           hallCombo.setSelectedIndex(i);
                       }
                   }
               }
          }
    }//GEN-LAST:event_editActionPerformed

    private void save_changesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_changesActionPerformed
         if(days.getText()!=null && days.getText().trim().length()>0){
           if(perc.getText()!=null && perc.getText().trim().length()>0){
           
              Days = Integer.parseInt(days.getText());
              Cancld_Perc = Double.parseDouble(perc.getText());
              String ID = ID_label.getText();
              active=1; 
              String HallID = hall_id_label.getText();
              
              int x = 0;
              try {
                   x = CheckCancelledChargeDuplication(HallID , Days);
               } catch (BasicException ex) {
                   Logger.getLogger(HallDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
               }
              
              if(x==1){
              
              
              
                 try {
                              int Update_Data =  new PreparedSentence(m_App.getSession(), "UPDATE hall_cancellation_offeres  SET DAYS=? , PERC_RATE=?   , CRBY=? ,  CRDATE=? , CRHOST=? , ACTIVE=?  WHERE ID = ? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.DOUBLE ,  Datas.STRING , Datas.TIMESTAMP ,Datas.STRING ,   Datas.INT ,Datas.STRING})).exec
                                                    (new Object[]{Days ,Cancld_Perc ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , active,  ID  });
                      
                              loaddata();
                              JOptionPane.showMessageDialog(this, "Data Updated  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                              reset();
                              
                              
                 }
                 catch (BasicException ex) {
                             JOptionPane.showMessageDialog(this, " Unable to Update!", " room Type", JOptionPane.ERROR_MESSAGE);       
                             Logger.getLogger(DiscountTableModel.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 
                 
              }else{
                   JOptionPane.showMessageDialog(this, "Cancallation for hall already Exits ..!! \n Please edit or deactivate it to create new.  ", " Error", JOptionPane.ERROR_MESSAGE);
              }
                 
            }
           else{
               JOptionPane.showMessageDialog(this, " Enter percentage cancellation charge..!! ", " room Type", JOptionPane.ERROR_MESSAGE);
           }
       }
       else{
           JOptionPane.showMessageDialog(this, "Enter no of days ..! ", " room Type", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_save_changesActionPerformed

    private void deactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateActionPerformed
        if(jTable1.getSelectedRow()!=-1){
               int deac = JOptionPane.showConfirmDialog(jPanel2, " Do you want to deactivate  ?? ");
               if(deac == JOptionPane.YES_OPTION){
               
               if(jTable1.getSelectedRow()<Discount_Table.getOfferSize()){
               int row = jTable1.getSelectedRow();
                   DiscountTableModel.DiscountInfo del_data = Discount_Table.getDiscountOfferList().get(row);  
                   
                   String ID = del_data.getId();
                   active=0; 
                   
                   
                 try {
                              int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE hall_cancellation_offeres  SET  DEACBY=? ,  DEACDATE=? , DEACHOST=? , ACTIVE=?  WHERE ID = ? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP ,Datas.STRING ,   Datas.INT ,Datas.STRING})).exec
                                                    (new Object[]{m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , active,  ID });
                      
                              
                              JOptionPane.showMessageDialog(this, " Deactivated  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                             
                              loaddata();
                              
                              
                 }
                 catch (BasicException ex) {
                             JOptionPane.showMessageDialog(this, " Unable to Deactivate..!", " room Type", JOptionPane.ERROR_MESSAGE);       
                             Logger.getLogger(DiscountTableModel.class.getName()).log(Level.SEVERE, null, ex);
                 }
               }
             }
        }
        
    }//GEN-LAST:event_deactivateActionPerformed

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

    private void hallComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hallComboItemStateChanged
        if(hallCombo.getSelectedIndex()!=-1){
          String hallname = hallCombo.getSelectedItem().toString();
          try{
               hallBooking = BookHallTableModel.loadInstanceHallInfo(m_App, hallname);
              
           } 
           catch (BasicException ex) {
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
           }
           hallDetails =  (List<BookHallTableModel.HallAvailibilityInfo>) hallBooking.getHallPath();
            
           BookHallTableModel.HallAvailibilityInfo editData = (BookHallTableModel.HallAvailibilityInfo) ((BookHallTableModel.HallAvailibilityInfo)hallDetails.get(0));
           
           hall_id_label.setText(editData.getId());
          
         
        }
    }//GEN-LAST:event_hallComboItemStateChanged

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID_label;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField days;
    private javax.swing.JButton deactivate;
    private javax.swing.JButton edit;
    private javax.swing.JPanel fix_chrg_panel;
    private javax.swing.JCheckBox fixcharge_option;
    private javax.swing.JTextField fixed_chr;
    private javax.swing.JComboBox hallCombo;
    private javax.swing.JTextField hall_id_label;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTextField perc;
    private javax.swing.JButton save;
    private javax.swing.JButton save_changes;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "";
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
        this.m_App = app;
        Discount_Hall_list = (DiscountTableModel) app.getBean("com.openbravo.pos.Booking.DiscountTableModel");
    }

    public Object getBean() {
       return this;
    }
    
    
   public void loaddata() throws BasicException{
       
       
       save_changes.setVisible(false);
       save.setVisible(true);
       
       
        try{
             Hall = hallTableModel.loadInstanceHallInfo(m_App);
         }
         catch (BasicException ex) {
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        halllist = (List<hallTableModel.HallTableInfo>) Hall.getHallPath();
        if(halllist==null){
             halllist = new ArrayList<hallTableModel.HallTableInfo>();
        }
        
        
        HallListModel  = new ComboBoxValModel(halllist);
        hallCombo.setModel(HallListModel);    
       
        
       
       Discount_Table = DiscountTableModel.loadInstanceHall_Discount_Offers(m_App);
       showPanelInfo(Discount_Table); 
   }
   
   public void showPanelInfo(DiscountTableModel Discount_offers){
        jTable1.setModel(Discount_Table.getTableModel());
   }
    
   public void reset(){
      jTabbedPane1.setSelectedIndex(0);
      days.setText(null);
      perc.setText(null);
      fix_chrg_panel.setVisible(false);
      hall_id_label.setText(null);
      hallCombo.setEnabled(true);
      fixed_chr.setText(null);
      fixcharge_option.setSelected(false);
      hallCombo.setSelectedIndex(-1);
   } 
    
   
   public int CheckCancelledChargeDuplication(String HallID , int Days) throws BasicException{
       Object o = null;
        int flag = 0;
       o=new PreparedSentence(m_App.getSession(), "SELECT ID  FROM hall_cancellation_offeres  WHERE HALL_NAME = ? AND DAYS=? AND ACTIVE=1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT }) , SerializerReadString.INSTANCE).find(new Object[]{HallID , Days } );
       if(o!=null){
            flag = 0;
        }
       else{
           flag = 1;
       }
       return flag;
   }
   
    
}
