

package com.openbravo.pos.sales.restaurant;

import javax.swing.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import com.openbravo.data.loader.SentenceExec;
public class JTicketsBagRestaurantnum1 extends javax.swing.JPanel {
    
    private AppView m_App;
    private JIntroPageRestnum1 m_restaurant;
    public static JIntroPageRestnum1 m_rest;
    public boolean cz1 = true;
   //  private SentenceList m_sentcat;
   // private ComboBoxValModel m_CategoryModel;

    /** Creates new form JTicketsBagRestaurantMap */
    public JTicketsBagRestaurantnum1()
    {
    }
    public JTicketsBagRestaurantnum1(AppView app, JIntroPageRestnum1 restaurant) {

      //  DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_App = app;
        m_restaurant = restaurant;
        m_rest=restaurant;
        
        initComponents();
         m_jDelTicket1.addActionListener(new ActionListener(){  //code for Delete ticket
      public void actionPerformed(ActionEvent ae){  
              
       DelTicket();
      }});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               if(JIntroPageRestnum1.ct_z){
                   if(cz1){
                       cz1=false;
            DelTicket();  
                       cz1=true;
         // JIntroPageRestnum1.ct_z = false;
                   }
               }
          }  
          return false;}}); 
    
        
        
        jButton1.setToolTipText("New Ticket(ESC)");
        m_jDelTicket1.setToolTipText("CTRL Z");
       // m_sentcat = dlSales.getWaiterList();
       // m_CategoryModel = new ComboBoxValModel();
        
    }

    public void activate() {
        
        
    
   
        
        // Authorization
        //m_prTicket.setEnabled(m_App.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits"));
        //List a = m_sentcat.list();
        //a.add(0, null); // The null item
       // m_CategoryModel = new ComboBoxValModel(a);
       
    }
     public void  DelTicket()//delete method
  {  
         int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.wannadelete"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (res == JOptionPane.YES_OPTION) {
            if (m_restaurant.deleteTicket()) {
                m_restaurant.newTicket();
            }
        }
     }
    
     public void  NTicket()//UP method
     {   
         m_restaurant.newTicket();
     }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        m_jDelTicket1 = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/redo16.png"))); // NOI18N
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setMargin(new java.awt.Insets(8, 14, 8, 14));
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/atlantikdesignersmall.png"))); // NOI18N
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.addActionListener(new ActionListener(){  //code for New Ticket
            public void actionPerformed(ActionEvent ae){

                NTicket();
            }});
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(new KeyEventDispatcher(){
                public boolean dispatchKeyEvent(KeyEvent e){
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                    {
                        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)

                        NTicket();

                    }
                    return false;}});

        m_jDelTicket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editdelete.png"))); // NOI18N
        m_jDelTicket1.setFocusPainted(false);
        m_jDelTicket1.setFocusable(false);
        m_jDelTicket1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDelTicket1.setRequestFocusEnabled(false);
        m_jDelTicket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDelTicket1ActionPerformed(evt);
            }
        });
        add(m_jDelTicket1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
//        m_restaurant.moveTicket();ysed for moving the table..
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        m_restaurant.newTicket();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void m_jDelTicket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDelTicket1ActionPerformed
      
    }//GEN-LAST:event_m_jDelTicket1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton m_jDelTicket1;
    // End of variables declaration//GEN-END:variables
    
}

