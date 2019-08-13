
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class DiscountRequest extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private HallDiscountRequestTableModel HallDiscount;
    private GuestRoomDiscountTableModel RoomDiscount;
    
    
    
    
    public DiscountRequest() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hall_table = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel1 = new javax.swing.JLabel();
        h_accept = new javax.swing.JButton();
        h_reject = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        guestRoom_Table = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        room_pending = new javax.swing.JLabel();
        r_accept = new javax.swing.JButton();
        r_reject = new javax.swing.JButton();

        hall_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(hall_table);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("(Request Pending)");

        h_accept.setText("Approve");
        h_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h_acceptActionPerformed(evt);
            }
        });

        h_reject.setText("Reject");
        h_reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h_rejectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(h_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(h_reject, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(h_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(h_reject, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hall Discount Request", jPanel2);

        guestRoom_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(guestRoom_Table);

        room_pending.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        room_pending.setText("(Request Pending)");

        r_accept.setText("Approve");
        r_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_acceptActionPerformed(evt);
            }
        });

        r_reject.setText("Reject");
        r_reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_rejectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(room_pending)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(r_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(r_reject, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(room_pending)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(r_reject, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(r_accept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Guest Room Discount Request", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void h_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h_acceptActionPerformed
         if(hall_table.getSelectedRow()!=-1){       
             int submit_hall = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Approv Request .. ?? " , "Booking Approval" , JOptionPane.YES_NO_OPTION);
             if(submit_hall == JOptionPane.YES_OPTION){
            
            int row = hall_table.getSelectedRow();
            HallDiscountRequestTableModel.DiscountInfo showdata = HallDiscount.getHallList().get(row);
        
            String ChkIn_ID = showdata.getCHECK_IN_Id();
           
            
           
            try {
                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE hall_check_in  SET DISC_FLAG=1 , DISC_APP_BY=? , DISC_APP_DT=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP ,Datas.STRING})).exec
                                                     (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() , ChkIn_ID});
           
            
                
            JOptionPane.showMessageDialog(this, "Request Approved..! ", "Success", JOptionPane.INFORMATION_MESSAGE);    
            loaddata();
            
            
           } 
           catch (BasicException ex) {
                Logger.getLogger(HallBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
            
             }
        }
    }//GEN-LAST:event_h_acceptActionPerformed

    private void h_rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h_rejectActionPerformed
         if(hall_table.getSelectedRow()!=-1){       
             int submit_hall = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Reject Request .. ?? " , "Booking Approval" , JOptionPane.YES_NO_OPTION);
             if(submit_hall == JOptionPane.YES_OPTION){
            
            int row = hall_table.getSelectedRow();
            HallDiscountRequestTableModel.DiscountInfo showdata = HallDiscount.getHallList().get(row);
        
            String ChkIn_ID = showdata.getCHECK_IN_Id();
           
            
           
            try {
                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE hall_check_in  SET DISC_FLAG=1 , DISCOUNT=0.00 ,  DISC_APP_BY=? , DISC_APP_DT=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP ,Datas.STRING})).exec
                                                     (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() , ChkIn_ID});
           
            
                
            JOptionPane.showMessageDialog(this, "Request Rejected ..! ", "Success", JOptionPane.INFORMATION_MESSAGE);    
            loaddata();
            
            
           } 
           catch (BasicException ex) {
                Logger.getLogger(HallBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
            
             }
        }
    }//GEN-LAST:event_h_rejectActionPerformed

    private void r_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_acceptActionPerformed
        if(guestRoom_Table.getSelectedRow()!=-1){       
             int submit_hall = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Approv Request .. ?? " , "Booking Approval" , JOptionPane.YES_NO_OPTION);
             if(submit_hall == JOptionPane.YES_OPTION){
            
            int row = guestRoom_Table.getSelectedRow();
            GuestRoomDiscountTableModel.RoomDiscountInfo showdata = RoomDiscount.getHallList().get(row);
        
            String ChkIn_ID = showdata.getId();
           
            
           
            try {
                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET DISC_FLAG=1 , DISC_APP_BY=? , DISC_APP_DT=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP ,Datas.STRING})).exec
                                                     (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() , ChkIn_ID});
           
            
                
            JOptionPane.showMessageDialog(this, "Request Approved..! ", "Success", JOptionPane.INFORMATION_MESSAGE);    
            loaddata();
            
            
           } 
           catch (BasicException ex) {
                Logger.getLogger(HallBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
                
            }
                                
            
             }
        }
    }//GEN-LAST:event_r_acceptActionPerformed

    private void r_rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_rejectActionPerformed
         if(guestRoom_Table.getSelectedRow()!=-1){       
             int submit_hall = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Reject Request .. ?? " , "Booking Approval" , JOptionPane.YES_NO_OPTION);
             if(submit_hall == JOptionPane.YES_OPTION){
            
            int row = guestRoom_Table.getSelectedRow();
            GuestRoomDiscountTableModel.RoomDiscountInfo showdata = RoomDiscount.getHallList().get(row);
        
            String ChkIn_ID = showdata.getId();
           
            
           
            try {
                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET DISC_FLAG=1 , DISCOUNT=0.00 ,  DISC_APP_BY=? , DISC_APP_DT=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP ,Datas.STRING})).exec
                                                     (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() , ChkIn_ID});
           
            
                
            JOptionPane.showMessageDialog(this, "Request Rejected ..! ", "Success", JOptionPane.INFORMATION_MESSAGE);    
            loaddata();
            
            
           } 
           catch (BasicException ex) {
                Logger.getLogger(HallBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
            
             }
        }
    }//GEN-LAST:event_r_rejectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable guestRoom_Table;
    private javax.swing.JButton h_accept;
    private javax.swing.JButton h_reject;
    private javax.swing.JTable hall_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton r_accept;
    private javax.swing.JButton r_reject;
    private javax.swing.JLabel room_pending;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "Discount Request";
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
    }

    public Object getBean() {
         return this;
    }
    
    public void loaddata() throws BasicException{
        
        HallDiscount = HallDiscountRequestTableModel.loadInstanceHall_Discount(m_App);
        hall_table.setModel(HallDiscount.getTableModel());
        
        
        RoomDiscount  = GuestRoomDiscountTableModel.loadInstanceHall_Discount(m_App);
        guestRoom_Table.setModel(RoomDiscount.getTableModel());
        
        
    }
    
}
