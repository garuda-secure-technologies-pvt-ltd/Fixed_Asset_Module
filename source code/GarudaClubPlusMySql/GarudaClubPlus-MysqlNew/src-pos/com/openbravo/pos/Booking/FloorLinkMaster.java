
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
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
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class FloorLinkMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private List<Object> RoomType_List = new ArrayList<Object>();
    private List<Object> Building_list = new ArrayList<Object>();
    private ComboBoxValModel RoomType_Model ;
    private ComboBoxValModel Building_model ;
    private GuestRoomTableModel GR_table_model;
    private BuildingTableModel Bldge_table_model;
    private List<Object> RoomNo_list = new ArrayList<Object>();
    private List<Object> Floor_list = new ArrayList<Object>();
    private ComboBoxValModel RoomNo_link_listModel ;
    private ComboBoxValModel Floor_link_listModel ;
    private FloorLinkTableModel Floor_Link_TableModel;
    
    
    public FloorLinkMaster() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        roomType_combo = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        room_no_combo = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        building_combo = new javax.swing.JComboBox();
        jLabel53 = new javax.swing.JLabel();
        floor_combo = new javax.swing.JComboBox();
        jLabel45 = new javax.swing.JLabel();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Room Type :");

        roomType_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        roomType_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                roomType_comboItemStateChanged(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Room no :");

        room_no_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        room_no_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                room_no_comboItemStateChanged(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Building Name :- ");

        building_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        building_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                building_comboItemStateChanged(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Floor : ");

        floor_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 153));
        jLabel45.setText("Floor Linking Master ");

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

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 51));
        jButton1.setText("Set Link ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 0, 0));
        jButton2.setText("Deactivate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel45))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGap(198, 198, 198)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel53))
                                .addGap(18, 18, 18)
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(floor_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addComponent(building_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(171, 171, 171)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel51)
                                            .addComponent(jLabel49))
                                        .addGap(18, 18, 18)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(room_no_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(roomType_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(28, 28, 28)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(building_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomType_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(floor_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_no_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton2)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jLabel45.setForeground(Color.BLUE);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/deactivate.png")));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roomType_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomType_comboItemStateChanged
        if(roomType_combo.getSelectedIndex()!=-1){
            
            RoomNo_list = new ArrayList<Object>();
            List<Object> RoomNo_List = new ArrayList<Object>();
            
            String Roomtype = roomType_combo.getSelectedItem().toString();
            String room_nos =  GR_table_model.getRoomNos(m_App, Roomtype);
            RoomNo_List = Floor_Link_TableModel.getRoomNo_Linked(m_App, Roomtype);
            
            
            String []RNo_arry = room_nos.split("#");
            int count=0;
            for(int i=0;i<RNo_arry.length;i++){
                String Str = RNo_arry[i];
                for(int y=0;y<RoomNo_List.size();y++){
                    String Str2 = RoomNo_List.get(y).toString();
                    if(Str.equals(Str2)){
                       count=1;
                       break;
                    }
                    else{
                       count=0; 
                    }
               }
                if(count==0){
                   RoomNo_list.add(RNo_arry[i]);
                }
               
            }
            
            RoomNo_link_listModel = new ComboBoxValModel(RoomNo_list);
            room_no_combo.setModel(RoomNo_link_listModel);
            
            
            
        }
        else{
            room_no_combo.setSelectedIndex(-1);
            
        }
        
    }//GEN-LAST:event_roomType_comboItemStateChanged

    private void room_no_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_room_no_comboItemStateChanged
     if(room_no_combo.getSelectedIndex()!=-1){ 
         if(roomType_combo.getSelectedIndex()!=-1){
           
           
             
             
           
           
       }
       else{
           JOptionPane.showMessageDialog(this, "Select Room Type First..!! ", " Error", JOptionPane.ERROR_MESSAGE);
       }
     }
     
    }//GEN-LAST:event_room_no_comboItemStateChanged

    private void building_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_building_comboItemStateChanged
       if(building_combo.getSelectedIndex()!=-1){
           
           Floor_list = new ArrayList<Object>();
           List<Object> Floor_Name_List = new ArrayList<Object>();
           
           String Building_name = building_combo.getSelectedItem().toString();
           String Floor_name = Bldge_table_model.getFloors(m_App, Building_name);
           String []FLR_arry = Floor_name.split(",");
           Floor_Name_List = Floor_Link_TableModel.getFloorNames_Linked(m_App, Building_name);
           int count=0;
           
           for(int i=0;i<FLR_arry.length;i++){
                Floor_list.add(FLR_arry[i]);
            }
            
          Floor_link_listModel = new ComboBoxValModel(Floor_list);
          floor_combo.setModel(Floor_link_listModel);
           
       }
       else{
           floor_combo.setSelectedIndex(-1);
           
       }
    }//GEN-LAST:event_building_comboItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(room_no_combo.getSelectedIndex()!=-1){
          if(floor_combo.getSelectedIndex()!=-1){
              Transaction t = new Transaction(m_App.getSession()) {      
                String RoomType = roomType_combo.getSelectedItem().toString();
                String RoomNo = room_no_combo.getSelectedItem().toString();
                String Buldge_Name = building_combo.getSelectedItem().toString();
                String Floor_name = floor_combo.getSelectedItem().toString();
                int Active=1;
                                                                                              

                                @Override      
                                protected Object transact() throws BasicException {   



                               int  insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO floor_link (ID, ROOMTYPE, ROOMNO, BUILDING , FLOOR , ACTIVE, CRBY, CRDATE, CRHOST) VALUES (?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING  })                         
                                ).exec(new Object[]{  UUID.randomUUID().toString() ,RoomType ,  RoomNo , Buldge_Name , Floor_name , Active ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()});                                                                                                

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
              JOptionPane.showMessageDialog(this, "Select Floor first ", " Error", JOptionPane.ERROR_MESSAGE);
          }
        }
       else{
         JOptionPane.showMessageDialog(this, "Select Room Type First..!! ", " Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
           
         int deac = JOptionPane.showConfirmDialog(main_panel, " Are you sure  you want to deactivate ?? ");
             if(deac == JOptionPane.YES_OPTION)
             {   
                 
                    if(jTable1.getSelectedRow()<Floor_Link_TableModel.getSize()){
                        int row = jTable1.getSelectedRow();
                        FloorLinkTableModel.FloorLinkTableInfo showdata = Floor_Link_TableModel.getFloorLinkList().get(row);
                 
                        String ID = showdata.getID();
                        
                         // DEACTIVATE LINKED ROOM
               try {
                      int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE floor_link  SET ACTIVE=0 , DEACBY=? , DEACHOST=? , DEACDATE=?   WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING ,Datas.TIMESTAMP , Datas.STRING })).exec
                                                                            (new Object[]{ m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,new Date()  , ID  });
                  
               
               
                         loaddata();
                         reset();
                     } catch (BasicException ex) {
                      Logger.getLogger(GuestRoomLinkMaster.class.getName()).log(Level.SEVERE, null, ex);
                  }
                 
                 
                 
                    }
             }  
           
           
       }
       else{
            JOptionPane.showMessageDialog(this, "Select Room No to deactivate ", " Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox building_combo;
    private javax.swing.JComboBox floor_combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JComboBox roomType_combo;
    private javax.swing.JComboBox room_no_combo;
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
        GR_table_model = (GuestRoomTableModel) m_App.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
        Bldge_table_model = (BuildingTableModel)  m_App.getBean("com.openbravo.pos.Booking.BuildingTableModel");
        Floor_Link_TableModel = (FloorLinkTableModel )  m_App.getBean("com.openbravo.pos.Booking.FloorLinkTableModel");
        
    }

    public Object getBean() {
              return this;

    }
    
    
    public void loaddata() throws BasicException{
        
       RoomType_List = GR_table_model.RoomType_NamesList_Active(m_App);
       RoomType_Model = new ComboBoxValModel(RoomType_List);
       roomType_combo.setModel(RoomType_Model);   
        
       Building_list = Bldge_table_model.getBuildingNames(m_App);
       Building_model = new ComboBoxValModel(Building_list);
       building_combo.setModel(Building_model);
       
       
       
         Floor_Link_TableModel = FloorLinkTableModel.loadInstanceGuestInfo(m_App);
         showPanelInfo(Floor_Link_TableModel);
       
    }
    
    
    public void reset(){
        roomType_combo.setSelectedIndex(-1);
        room_no_combo.setSelectedIndex(-1);
        building_combo.setSelectedIndex(-1);
        floor_combo.setSelectedIndex(-1);
        
        
        
    }
    
     public void showPanelInfo(FloorLinkTableModel Floor_Link_TableModel){
           jTable1.setModel(Floor_Link_TableModel.getTableModel()); 
      }
    
}
