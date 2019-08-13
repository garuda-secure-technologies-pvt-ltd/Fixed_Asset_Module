
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
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


public class GuestRoomLinkMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    private List<Object> Linked_Room_Nos_list = new ArrayList<Object>();
    private List<Object> Linked_Cust_Name_List = new ArrayList<Object>();
    private GuestRoomLinkTableModel GR_Linked_tableModel;
    private AppView m_App;
    private GuestRoomTableModel GR_table_model;
    private List<Object> RoomType_List = new ArrayList<Object>();
    private ComboBoxValModel RoomNo_link_listModel ;
    private ComboBoxValModel Cust_listModel ;
    private ComboBoxValModel RoomType_Model ;
    private ComboBoxValModel MemType_Model ;
    private List<Object> RoomNo_list = new ArrayList<Object>();
    private List<Object> Cust_List = new ArrayList<Object>();
    private List<Object> Linked_RoomNos_List = new ArrayList<Object>();
    private List<Object> MemTypeList = new ArrayList<Object>();
    
    
    public GuestRoomLinkMaster() {
        initComponents();
        roomType_combo.setSelectedIndex(-1);
        room_no_combo.setSelectedIndex(-1);
        cust_Name_combo.setSelectedIndex(-1);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        linking_panel = new javax.swing.JPanel();
        room_no_combo = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        cust_Name_combo = new javax.swing.JComboBox();
        set_link_btn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
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
        deactivate_btn = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        roomType_combo = new javax.swing.JComboBox();
        set_link_btn1 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        Memtype_combo = new javax.swing.JComboBox();

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 153));
        jLabel45.setText("Guest Room Linking Master ");

        linking_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        room_no_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Room no :");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Customer Name :");

        cust_Name_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        set_link_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        set_link_btn.setForeground(new java.awt.Color(255, 0, 51));
        set_link_btn.setText("Set Link");
        set_link_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_link_btnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Room No.", "Customer No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);

        deactivate_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deactivate_btn.setForeground(new java.awt.Color(204, 0, 0));
        deactivate_btn.setText("Deactivate");
        deactivate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivate_btnActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Room Type :");

        roomType_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        roomType_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                roomType_comboItemStateChanged(evt);
            }
        });

        set_link_btn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        set_link_btn1.setForeground(new java.awt.Color(0, 0, 204));
        set_link_btn1.setText("Change Account");
        set_link_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_link_btn1ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Account Type :");

        Memtype_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        Memtype_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Memtype_comboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout linking_panelLayout = new javax.swing.GroupLayout(linking_panel);
        linking_panel.setLayout(linking_panelLayout);
        linking_panelLayout.setHorizontalGroup(
            linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linking_panelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(deactivate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(linking_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addGroup(linking_panelLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roomType_combo, 0, 164, Short.MAX_VALUE)
                            .addComponent(room_no_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(50, 50, 50)
                .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(linking_panelLayout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cust_Name_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(linking_panelLayout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Memtype_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 24, Short.MAX_VALUE)
                .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(set_link_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(set_link_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        linking_panelLayout.setVerticalGroup(
            linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linking_panelLayout.createSequentialGroup()
                .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(linking_panelLayout.createSequentialGroup()
                        .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(linking_panelLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(roomType_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(set_link_btn1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linking_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52)
                                    .addComponent(Memtype_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(linking_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(room_no_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(cust_Name_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(set_link_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(linking_panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deactivate_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );

        deactivate_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/deactivate.png")));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(linking_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linking_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel45.setForeground(Color.BLUE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    String RoomType_ID;
    String RoomType;
    String F_Room_No;
    String F_Cust_Name;
    int Active;
    
    private void set_link_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_link_btnActionPerformed
      if(roomType_combo.getSelectedIndex()!=-1){
        if(room_no_combo.getSelectedIndex()!=-1){
            if(cust_Name_combo.getSelectedIndex()!=-1){
               
                
                int Count1=0;
                int Count2=0;
                String Exsist_Room_no;
                String Exsist_Cust_name;
                String Room_No = room_no_combo.getSelectedItem().toString();
                String Customer_Name = cust_Name_combo.getSelectedItem().toString();

                Linked_Room_Nos_list =  GR_Linked_tableModel.getLinked_RoomNos(m_App);
                Linked_Cust_Name_List=  GR_Linked_tableModel.getLinked_CustName(m_App);

                for(int i=0;i<Linked_Room_Nos_list.size();i++){
                    Exsist_Room_no = Linked_Room_Nos_list.get(i).toString();
                    if(Exsist_Room_no.equals(Room_No)){
                        Count1++;
                        break;
                    }
                }

                for(int i=0;i<Linked_Cust_Name_List.size();i++){
                    Exsist_Cust_name = Linked_Cust_Name_List.get(i).toString();
                    if(Exsist_Cust_name.equals(Customer_Name)){
                        Count2++;
                        break;
                    }
                }

                
                
                if(Count1==0){
                    if(Count2==0){
                        RoomType = roomType_combo.getSelectedItem().toString();
                        F_Room_No = room_no_combo.getSelectedItem().toString();
                        F_Cust_Name = cust_Name_combo.getSelectedItem().toString();
                        Active = 1;
                        
                        
                        RoomType_ID =  GR_table_model.getRoomType_ID(m_App, RoomType);
                        
                        
                        
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {

                                
                                   
                                    int insert_guestRoom_link  =  new PreparedSentence(m_App.getSession(), "INSERT INTO guestroom_link (ID ,ROOMTYPE ,ROOMNO, CUSTOMER_N, ACTIVE , CRBY, CRDATE , CRHOST) VALUES (?,?,?,?,?,?,?,?)"
                                                     , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING ,Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING})).exec
                                                     (new Object[]{UUID.randomUUID().toString() ,RoomType_ID ,F_Room_No , F_Cust_Name ,Active ,m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() });
                                return null;

                                

                             
                                }
                             };
                       

                        try {
                            t.execute();
                            JOptionPane.showMessageDialog(this, "Room Linked Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            loaddata();
                            reset();
                           

                        } catch (BasicException ex) {
                            Logger.getLogger(GuestRoomLinkMaster.class.getName()).log(Level.SEVERE, null, ex);
                            new MessageInf(ex).show(new JFrame());
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Customer Name Is already linked. Deactivate it first", " Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Room No. is already Linked. Deactivate it first", " Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            else{
                JOptionPane.showMessageDialog(this, "Select Guest Room No. from Customer Panel", " Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Select GuestRoom No. ", " Error", JOptionPane.ERROR_MESSAGE);
        }
      }
      else{
           JOptionPane.showMessageDialog(this, "Select Type of Guest room ", " Error", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_set_link_btnActionPerformed

    private void roomType_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomType_comboItemStateChanged
        if(roomType_combo.getSelectedIndex()!=-1){
            
            RoomNo_list = new ArrayList<Object>();
            
            String Roomtype = roomType_combo.getSelectedItem().toString();
            String room_nos =  GR_table_model.getRoomNos(m_App, Roomtype);
            
            String []RNo_arry = room_nos.split("#");
            
            for(int i=0;i<RNo_arry.length;i++){
                RoomNo_list.add(RNo_arry[i]);
            }
            Linked_RoomNos_List = new ArrayList<Object>();
            Linked_RoomNos_List = GR_table_model.getAlreadyLinked_RoomList_ByRoomType(m_App, Roomtype);
            
            for(int i=0;i<RoomNo_list.size();i++){
               String R = RoomNo_list.get(i).toString();
               for(int y=0;y<Linked_RoomNos_List.size();y++){
                   String R2 = Linked_RoomNos_List.get(y).toString();
                   if(R2.equals(R)){
                       RoomNo_list.remove(i);
                       i=0;
                       break;
                   }
               }
            }
            
            RoomNo_link_listModel = new ComboBoxValModel(RoomNo_list);
            room_no_combo.setModel(RoomNo_link_listModel);
            
        }
        
        room_no_combo.setSelectedIndex(-1);
        cust_Name_combo.setSelectedIndex(-1);
        
    }//GEN-LAST:event_roomType_comboItemStateChanged

    private void deactivate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_btnActionPerformed
         if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(linking_panel, " Do you want to deactivate ?? " , "link Deactivation ", JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
                 
             
              if(jTable1.getSelectedRow()<GR_Linked_tableModel.getSize()){
               int row = jTable1.getSelectedRow();
               GuestRoomLinkTableModel.GuestRoomLinkTableInfo showdata = GR_Linked_tableModel.getGuestRmList().get(row);
                
               String Linking_ID = showdata.getID();
               String RoomType = showdata.getROOMTYPE();
               String RoomNo = showdata.getROOMNO();
               String Cust_Name = showdata.getCUSTOMER_N();
                  
               
               
               
               
               // DEACTIVATE LINKED ROOM
               try {
                      int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_link  SET ACTIVE=0 , DEAC_BY=? , DEAC_DATE=? , DEAC_HOST=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING   })).exec
                                                                            (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , Linking_ID   });
                  
               
               
                      loaddata();
                      reset();
               } catch (BasicException ex) {
                      Logger.getLogger(GuestRoomLinkMaster.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  

               
              }
           }
         }
    }//GEN-LAST:event_deactivate_btnActionPerformed

    private void set_link_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_link_btn1ActionPerformed
         int temp = JOptionPane.showConfirmDialog(linking_panel, " Do you want to change Account Type ?? " , "link Deactivation ", JOptionPane.YES_NO_OPTION);
             if(temp == JOptionPane.YES_OPTION)
             {  
                 
                MemTypeList = GR_table_model.getMemTypeList(m_App);
                MemType_Model = new ComboBoxValModel(MemTypeList);
                Memtype_combo.setModel(MemType_Model); 
                 
                
                
                
                 
                jLabel52.setVisible(true);
                Memtype_combo.setVisible(true);
                
             }
    }//GEN-LAST:event_set_link_btn1ActionPerformed

    private void Memtype_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Memtype_comboItemStateChanged
      if(Memtype_combo.getSelectedIndex()!=-1){
          cust_Name_combo.setSelectedIndex(-1);
          String X = Memtype_combo.getSelectedItem().toString();
                  
                  
         Cust_List = GR_table_model.getRoomNos_Cust_LINKING(m_App ,X );
         Cust_listModel = new ComboBoxValModel(Cust_List);
         cust_Name_combo.setModel(Cust_listModel);         
                  
      }
    }//GEN-LAST:event_Memtype_comboItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Memtype_combo;
    private javax.swing.JComboBox cust_Name_combo;
    private javax.swing.JButton deactivate_btn;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel linking_panel;
    private javax.swing.JComboBox roomType_combo;
    private javax.swing.JComboBox room_no_combo;
    private javax.swing.JButton set_link_btn;
    private javax.swing.JButton set_link_btn1;
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
       GR_Linked_tableModel = (GuestRoomLinkTableModel) m_App.getBean("com.openbravo.pos.Booking.GuestRoomLinkTableModel");
       GR_table_model = (GuestRoomTableModel) m_App.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
       
    }

    public Object getBean() {
       return this;
    }
    
    
    public void loaddata() throws BasicException{
        
        jLabel52.setVisible(false);
        Memtype_combo.setVisible(false);
        
        
        RoomType_List = GR_table_model.RoomType_NamesList_Active(m_App);
        RoomType_Model = new ComboBoxValModel(RoomType_List);
        roomType_combo.setModel(RoomType_Model);
        
       
        
        MemTypeList = new ArrayList();
        
        MemTypeList = GR_table_model.getMemberTypeAlreadySet(m_App);
        MemType_Model = new ComboBoxValModel(MemTypeList);
        Memtype_combo.setModel(MemType_Model); 
        
        if(MemTypeList.size()>0){
            String X = MemTypeList.get(0).toString();
            Memtype_combo.setSelectedIndex(0);
            
            Cust_List = GR_table_model.getRoomNos_Cust_LINKING(m_App ,X );
            Cust_listModel = new ComboBoxValModel(Cust_List);
            cust_Name_combo.setModel(Cust_listModel);
            
        }
        
        
        
        
        
        GR_Linked_tableModel = GuestRoomLinkTableModel.loadInstanceGuestInfo(m_App);
        jTable1.setModel(GR_Linked_tableModel.getTableModel());
        
        
    }
    
    public void reset(){
        roomType_combo.setSelectedIndex(-1);
        room_no_combo.setSelectedIndex(-1);
        cust_Name_combo.setSelectedIndex(-1);
        
        
        
        
    }
    
}
