
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
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
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class BuildingMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    List<Object> Floor_name_list = new ArrayList<Object>();
    private Floor_Name_Model Floor_NameList_Model;
    private BuildingTableModel Building_table_Model;

    
    public BuildingMaster() {
        initComponents();
        menu_panel.setVisible(false);
        jTabbedPane1.setSelectedIndex(1);
        save_btn.setVisible(true);
        save2_btn.setVisible(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        menu_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        buildingname_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        floor_no_text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        floor_name_label = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        save_btn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        remove_btn = new javax.swing.JButton();
        save2_btn = new javax.swing.JButton();
        building_id_label = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Building Master ");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 0, 0));
        jButton2.setText("Add Building");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        menu_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Building Name : ");

        buildingname_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buildingname_textKeyReleased(evt);
            }
        });

        jLabel4.setText("No of Floors :");

        floor_no_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                floor_no_textKeyReleased(evt);
            }
        });

        jLabel5.setText("Floor name :");

        floor_name_label.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                floor_name_labelKeyReleased(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        save_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save_btn.setForeground(new java.awt.Color(0, 153, 51));
        save_btn.setText("Save ");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 51));
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        remove_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        remove_btn.setForeground(new java.awt.Color(51, 51, 255));
        remove_btn.setText("Remove ");
        remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_btnActionPerformed(evt);
            }
        });

        save2_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save2_btn.setForeground(new java.awt.Color(0, 153, 51));
        save2_btn.setText("Save Changes ");
        save2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save2_btnActionPerformed(evt);
            }
        });

        building_id_label.setText("jLabel1");

        javax.swing.GroupLayout menu_panelLayout = new javax.swing.GroupLayout(menu_panel);
        menu_panel.setLayout(menu_panelLayout);
        menu_panelLayout.setHorizontalGroup(
            menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menu_panelLayout.createSequentialGroup()
                        .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menu_panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buildingname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menu_panelLayout.createSequentialGroup()
                                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(floor_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(floor_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(remove_btn)
                                    .addComponent(building_id_label))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menu_panelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(save2_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        menu_panelLayout.setVerticalGroup(
            menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_panelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(buildingname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(floor_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(floor_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menu_panelLayout.createSequentialGroup()
                        .addComponent(building_id_label)
                        .addGap(32, 32, 32)
                        .addComponent(remove_btn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_btn)
                    .addComponent(jButton3)
                    .addComponent(save2_btn))
                .addContainerGap())
        );

        building_id_label.setVisible(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel2)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(menu_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Create Floors ", jPanel3);

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
        jScrollPane2.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 0, 0));
        jButton1.setText("Deactivate ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 0));
        jButton4.setText("Edit ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/deactivate.png")));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/color_line.png")));

        jTabbedPane1.addTab("Floor List ", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void floor_no_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_floor_no_textKeyReleased
          char c = evt.getKeyChar();
    
        if(c!=KeyEvent.VK_BACK_SPACE  && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE &&  c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {
   
        if(!Character.isDigit(c) && c!=KeyEvent.VK_ENTER)
        {  
        JOptionPane.showMessageDialog(floor_no_text, "Please enter only numbers..");
    
            floor_no_text.setText(null);
         
            }
        }
        
        if(c==KeyEvent.VK_ENTER){
            
            floor_name_label.requestFocus();
            
        }
        
        
        
    }//GEN-LAST:event_floor_no_textKeyReleased

    private void floor_name_labelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_floor_name_labelKeyReleased
      int count = 0;
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            if (floor_name_label.getText().length() > 0) { 
                if(Floor_name_list.size() < Integer.parseInt(floor_no_text.getText())){
                   
                        if(Floor_name_list.size()>0){
                                for(int i=0;i<Floor_name_list.size();i++){
                                  String x = Floor_name_list.get(i).toString();
                                  if(x.equals(floor_name_label.getText())){
                                     count++;
                                     break;
                                  }
                                }
                                
                                if(count==0){
                                    Floor_name_list.add(floor_name_label.getText());
                                    Floor_NameList_Model = new Floor_Name_Model(Floor_name_list);
                                    jList1.setModel(Floor_NameList_Model);
                                    floor_name_label.setText(null);
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "Floor Name already Entered..!!", " Floor", JOptionPane.ERROR_MESSAGE);
                                    floor_name_label.setText(null);
                                }
                                
                          }
                    
                        else{
                            Floor_name_list.add(floor_name_label.getText());
                            Floor_NameList_Model = new Floor_Name_Model(Floor_name_list);
                            jList1.setModel(Floor_NameList_Model);
                            floor_name_label.setText(null);
                        }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Names Exceeds the current nos.", "Exceeds the specified number", JOptionPane.OK_OPTION);
                    floor_name_label.setText(null);
                }
            }
        }
    }//GEN-LAST:event_floor_name_labelKeyReleased

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_btnActionPerformed
         int row = jList1.getSelectedIndex();
        if (row >= 0) {
          Floor_name_list.remove(row);
          Floor_NameList_Model = new Floor_Name_Model(Floor_name_list);
          jList1.setModel(Floor_NameList_Model);  
            
        }
    }//GEN-LAST:event_remove_btnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      menu_panel.setVisible(false);
      reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      menu_panel.setVisible(true);
       Floor_name_list = new ArrayList<Object>();
       Floor_NameList_Model = new Floor_Name_Model(null);
      
      
    }//GEN-LAST:event_jButton2ActionPerformed

    
     String Building_Name;
     String Floor_name="";
     int Floor_nos;
     int Active;     
     
     
    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
       if(buildingname_text.getText()!=null && buildingname_text.getText().trim().length()>0){
           if(floor_no_text.getText()!=null && floor_no_text.getText().trim().length()>0){
               Floor_nos = Integer.parseInt(floor_no_text.getText());
               if(Floor_name_list.size()==Floor_nos) {
               
               Floor_name=""; 
               Building_Name = buildingname_text.getText();
               Active = 1;
                for(int i=0;i<Floor_name_list.size();i++){
                   String x = Floor_name_list.get(i).toString();
                   Floor_name = Floor_name + x + ",";
                   
                }
               
               
                        Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                                @Override      
                                protected Object transact() throws BasicException {   



                               int  insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO building (ID, B_NAME, FLOOR_NOS, FLOOR_NAME, ACTIVE, CRBY, CRDATE, CRHOST) VALUES (?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING , Datas.INT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING  })                         
                                ).exec(new Object[]{  UUID.randomUUID().toString() , Building_Name , Floor_nos , Floor_name , Active ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()});                                                                                                

                                  return null;                                      
                                    }                            
                                };                 



                                try {                 
                                    t.execute();          
                                    JOptionPane.showMessageDialog(this, "Data Saved  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                                    reset();
                                    loaddata();
                                } 
                                catch (BasicException ex) {                    
                                        Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);  
                                        JOptionPane.showMessageDialog(this, "Error While Saving ..!! ", " Error", JOptionPane.ERROR_MESSAGE);
                                } 




               
               
               
               
               
           
               }
               else{
                   JOptionPane.showMessageDialog(this, "No. of floor names does not match with Floor Nos. !", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
                JOptionPane.showMessageDialog(this, "No of floor should not be empty..!", "Error", JOptionPane.ERROR_MESSAGE);
           }
       }
       else{
            JOptionPane.showMessageDialog(this, "Building Name should not be empty..!!", " Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_save_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel4, " Do you want to deactivate ?? ");
             if(bill == JOptionPane.YES_OPTION)
             {   
             
              if(jTable1.getSelectedRow()<Building_table_Model.getSize()){
               int row = jTable1.getSelectedRow();
               BuildingTableModel.BuildingTableInfo showdata = Building_table_Model.getGuestRmList().get(row);
                 
               
               String Building_id = showdata.getBuilding_ID();
               String B_name = showdata.getBuilding_Name();
               
               
                // DEACTIVATE LINKED ROOM
               try {
                      int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE building  SET ACTIVE=0   WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                            (new Object[]{  Building_id  });
                  
               
               
                      loaddata();
                      reset();
               } catch (BasicException ex) {
                      Logger.getLogger(GuestRoomLinkMaster.class.getName()).log(Level.SEVERE, null, ex);
                  }
               
               
                 
             
              }
             }
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
             if(jTable1.getSelectedRow()<Building_table_Model.getSize()){
               int row = jTable1.getSelectedRow();
               BuildingTableModel.BuildingTableInfo showdata = Building_table_Model.getGuestRmList().get(row);
               
               Floor_name_list = new ArrayList<Object>();
               
               String B_name = showdata.getBuilding_Name();
               String Floor_name = showdata.getFLOOR_NAME();
               int Floor_nos = showdata.getFLOOR_NOS();
               String B_ID = showdata.getBuilding_ID();
               building_id_label.setText(showdata.getBuilding_ID());
               
               buildingname_text.setText(B_name);
               floor_no_text.setText(""+Floor_nos);
               
               String []x = Floor_name.split(",");
               for(int i=0;i<x.length;i++){
                  Floor_name_list.add(x[i]);
               }
             
              Floor_NameList_Model = new Floor_Name_Model(Floor_name_list);
              jList1.setModel(Floor_NameList_Model); 
             
              
              menu_panel.setVisible(true);
              jTabbedPane1.setSelectedIndex(0);
              save2_btn.setVisible(true);
              save_btn.setVisible(false);
              
              
             }
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void save2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save2_btnActionPerformed
         if(buildingname_text.getText()!=null && buildingname_text.getText().trim().length()>0){
           if(floor_no_text.getText()!=null && floor_no_text.getText().trim().length()>0){
              Floor_nos = Integer.parseInt(floor_no_text.getText());
               if(Floor_name_list.size()==Floor_nos) {
               
                Floor_name=""; 
                Building_Name = buildingname_text.getText();
                Active = 1;
                for(int i=0;i<Floor_name_list.size();i++){
                   String x = Floor_name_list.get(i).toString();
                   Floor_name = Floor_name + x + ",";
                   
                }
                   
                String B_ID = building_id_label.getText();
               
               
                 // SAVE CHAMGES DONE
               try {
                      int EDIT_BUILDING  =  new PreparedSentence(m_App.getSession(), "UPDATE building  SET B_NAME=?, FLOOR_NOS=?, FLOOR_NAME=? , ACTIVE=1, CRBY=?, CRDATE=?, CRHOST=?   WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.INT , Datas.STRING ,Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING})).exec
                                                                            (new Object[]{  Building_Name , Floor_nos ,  Floor_name , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , B_ID });
                  
               
               
                     JOptionPane.showMessageDialog(this, "Data Saved  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
                      loaddata();
                      reset();
               } catch (BasicException ex) {
                      Logger.getLogger(GuestRoomLinkMaster.class.getName()).log(Level.SEVERE, null, ex);
                  }
               
               
               
               
               
               
                }
               else{
                   JOptionPane.showMessageDialog(this, "No. of floor names does not match with Floor Nos. !", "Error", JOptionPane.ERROR_MESSAGE);
               }
               
            }
           else{
                JOptionPane.showMessageDialog(this, "No of floor should not be empty..!", "Error", JOptionPane.ERROR_MESSAGE);
           }
       }
       else{
            JOptionPane.showMessageDialog(this, "Building Name should not be empty..!!", " Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_save2_btnActionPerformed

    private void buildingname_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buildingname_textKeyReleased
        if(buildingname_text.getText().trim().length()>10){
             JOptionPane.showMessageDialog(this, " Name should not be more than 10 characters", "Exceeds the limit", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buildingname_textKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel building_id_label;
    private javax.swing.JTextField buildingname_text;
    private javax.swing.JTextField floor_name_label;
    private javax.swing.JTextField floor_no_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JButton remove_btn;
    private javax.swing.JButton save2_btn;
    private javax.swing.JButton save_btn;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
        menu_panel.setVisible(false);
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
    }

    public Object getBean() {
         return this;
    }
    
    
    
    
      public class Floor_Name_Model extends AbstractListModel {

        private java.util.List RoomNo;

        public Floor_Name_Model(java.util.List RNo) {
            this.RoomNo = RNo;
        }

        public int getSize() {
            return RoomNo.size();
        }

        public Object getElementAt(int i) {
            return RoomNo.get(i);
        }

        public void remove(int i) {
            RoomNo.remove(i);
        }
    }
      
      
      public void loaddata() throws BasicException{
           Building_table_Model = BuildingTableModel.loadInstanceGuestInfo(m_App);
            showPanelInfo(Building_table_Model);
          
       }
      public void showPanelInfo(BuildingTableModel Building_table_Model){
           jTable1.setModel(Building_table_Model.getTableModel()); 
      }
      
      
      
      
      public void reset(){
          buildingname_text.setText(null);
          floor_no_text.setText(null);
         
          menu_panel.setVisible(false);
          Floor_name_list = new ArrayList<Object>();
          Floor_NameList_Model = new Floor_Name_Model(Floor_name_list);
          jList1.setModel(Floor_NameList_Model); 
          
          jTabbedPane1.setSelectedIndex(1);
          
          save2_btn.setVisible(false);
          save_btn.setVisible(true);
      }
    
    
}
