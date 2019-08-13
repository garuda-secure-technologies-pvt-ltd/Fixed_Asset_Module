
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CheckInOutTimeDiff extends javax.swing.JPanel implements JPanelView,BeanFactoryApp  {
    private AppView m_App;
    private CheckInTableModel CheckInTable_Model;
    
   
    public CheckInOutTimeDiff() {
        initComponents();
        main_panel.setVisible(true);
        time_edit_Panel.setVisible(false);
        
               
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        main_panel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        time_edit_Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkin_hrs_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        checkout_hrs_text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        save_button = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cancel_button = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 153));
        jLabel34.setText("Check In & Out Time Difference");

        jButton1.setText("Add Time Difference ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("(Hours)");

        checkin_hrs_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                checkin_hrs_textKeyReleased(evt);
            }
        });

        jLabel4.setText("(Hours)");

        checkout_hrs_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                checkout_hrs_textKeyReleased(evt);
            }
        });

        jLabel1.setText("Check In  Time Difference from basic check In Time ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("*");

        jLabel3.setText("Check Out  Time Difference from basic check Out Time ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");

        save_button.setText("Save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        jLabel6.setText("Default time difference for check In and Check Out will be of zero hours .");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Note :-");

        cancel_button.setText("Cancel");
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout time_edit_PanelLayout = new javax.swing.GroupLayout(time_edit_Panel);
        time_edit_Panel.setLayout(time_edit_PanelLayout);
        time_edit_PanelLayout.setHorizontalGroup(
            time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(time_edit_PanelLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, time_edit_PanelLayout.createSequentialGroup()
                        .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, time_edit_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(41, 41, 41)
                                .addComponent(checkin_hrs_text, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, time_edit_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkout_hrs_text, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(130, 130, 130))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, time_edit_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(107, 107, 107))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, time_edit_PanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(cancel_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save_button)
                .addGap(63, 63, 63))
        );
        time_edit_PanelLayout.setVerticalGroup(
            time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(time_edit_PanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(checkin_hrs_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(checkout_hrs_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(time_edit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_button)
                    .addComponent(cancel_button))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(time_edit_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(time_edit_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menu", jPanel1);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N

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
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
       if(checkin_hrs_text.getText().trim()!=null && checkin_hrs_text.getText().trim().length()>0){
           if(checkout_hrs_text.getText().trim()!=null && checkout_hrs_text.getText().trim().length()>0){
           
            int RowCount = CheckInTable_Model.getRowCountForCheckInTimeDiff(m_App);
           
           
            
            if(RowCount==0){
                
                 
                
                Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {
                      Double CheckIn_Hrs = Double.parseDouble(checkin_hrs_text.getText());
                      Double CheckOut_Hrs = Double.parseDouble(checkout_hrs_text.getText());
                       
                        
                        
                        
                        
                            int insert_Bill  =  new PreparedSentence(m_App.getSession(), "INSERT INTO checkinout_timediff (ID , CHECKIN_HRS , CHECKOUT_HRS , CRBY , CRDATE ,CRHOST ) VALUES (?,?,?,?,?,?)"
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING  })).exec
                                                                       (new Object[]{ UUID.randomUUID().toString() ,CheckIn_Hrs , CheckOut_Hrs ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()}); 

                             
                          
                                
                                
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, " Data Saved Successfully..!! ", "Success Message", JOptionPane.INFORMATION_MESSAGE);  
                                    time_edit_Panel.setVisible(false);
                                 
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                           }
                
                
                
            }
            else{
                
              
                
                
                
                Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {
                      Double CheckIn_Hrs = Double.parseDouble(checkin_hrs_text.getText());
                      Double CheckOut_Hrs = Double.parseDouble(checkout_hrs_text.getText());
                       
                      String Old_ID = CheckInTable_Model.getIdFromCheckInTimeDiffence(m_App); 
                        
                        
                        
                              int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE checkinout_timediff  SET CHECKIN_HRS=? , CHECKOUT_HRS=?,CRBY=? ,  CRDATE=?, CRHOST=?  WHERE ID=? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE ,Datas.DOUBLE ,Datas.STRING , Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING})).exec
                                                    (new Object[]{ CheckIn_Hrs , CheckOut_Hrs , m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , Old_ID });
                                
                             
                          
                                
                                
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, " Data Updated Successfully..!! ", "Success Message", JOptionPane.INFORMATION_MESSAGE);  
                                    time_edit_Panel.setVisible(false);
                                 
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                           }
                
                
                
                
                
                
                
                  }
            
               
               
               
               
               
               
               
           
           }
           else{
                JOptionPane.showMessageDialog(main_panel, "Please enter CheckOut  Time difference " ,"Warning Message" , JOptionPane.WARNING_MESSAGE );
           }
           
       }
       else{
             JOptionPane.showMessageDialog(main_panel, "Please enter CheckIn  Time difference "  , "Warning Message" , JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_save_buttonActionPerformed

    private void cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_buttonActionPerformed
        time_edit_Panel.setVisible(false);
        
    }//GEN-LAST:event_cancel_buttonActionPerformed

    private void checkin_hrs_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkin_hrs_textKeyReleased
         char c = evt.getKeyChar();
         Double Hours;
        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {   
                if(c!='.')
                {
                    
                JOptionPane.showMessageDialog(checkin_hrs_text, "Please enter only numbers..");

                checkin_hrs_text.setText(null);

            }
           }
        else{
                
            Hours = Double.parseDouble(checkin_hrs_text.getText());
            if(Hours > 24.00){
                 JOptionPane.showMessageDialog(main_panel, "Time Difference Should be less than 24 hours only.");
                 checkin_hrs_text.setText(null);
            }
         
        }
        }
    }//GEN-LAST:event_checkin_hrs_textKeyReleased

    private void checkout_hrs_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_hrs_textKeyReleased
        char c = evt.getKeyChar();
        Double Hours;
        
        
        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {   
                if(c!='.')
                {
                    
                JOptionPane.showMessageDialog(checkout_hrs_text, "Please enter only numbers..");

                checkout_hrs_text.setText(null);

            }
        }
       else{
                
            Hours = Double.parseDouble(checkin_hrs_text.getText());
            if(Hours > 24.00){
                 JOptionPane.showMessageDialog(main_panel, "Time Difference Should be less than 24 hours only.");
                 checkin_hrs_text.setText(null);
            }
         
        }     
            
            
            
        }
    }//GEN-LAST:event_checkout_hrs_textKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       time_edit_Panel.setVisible(true);
        //added by pratima 
        
        try {
          Object[] obj =(Object[])new StaticSentence(m_App.getSession()
                           , "SELECT CHECKIN_HRS,CHECKOUT_HRS from checkinout_timediff "
                           ,  null
                           , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING})).find();
       
         if(obj!=null){
             if(obj[0]!=null)
             { 
              checkin_hrs_text.setText((obj[0]).toString());
             }else checkin_hrs_text.setText("");
             if(obj[1]!=null){
               checkout_hrs_text.setText((obj[1]).toString()); 
             }else  checkout_hrs_text.setText("");
         }
        
        }catch (BasicException ex) {}//ended by pratima 
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_button;
    private javax.swing.JTextField checkin_hrs_text;
    private javax.swing.JTextField checkout_hrs_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JButton save_button;
    private javax.swing.JPanel time_edit_Panel;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Check In & Out Time Difference";
    }

    public void activate() throws BasicException {
       time_edit_Panel.setVisible(false);
    }

    public boolean deactivate() {
      return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
        CheckInTable_Model = (CheckInTableModel) app.getBean("com.openbravo.pos.Booking.CheckInTableModel");
        
    }

    public Object getBean() {
       return this;
    }
}
