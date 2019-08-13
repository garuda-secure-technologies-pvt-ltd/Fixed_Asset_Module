

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CreditConfirm_UsbCard extends javax.swing.JDialog {
    private AppView app;
    private boolean flag;
    public String CardNo;
    
    
    public CreditConfirm_UsbCard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cardNo_text.requestFocus();
    }

    
   public CreditConfirm_UsbCard(java.awt.Frame parent, DataLogicSales dlSales, AppView app, boolean flag) {
        super(parent, true);        
       
        this.app = app;
        this.flag = flag;
    }
  
    public CreditConfirm_UsbCard(java.awt.Dialog parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mani_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        save_text = new javax.swing.JButton();
        cancel_Text = new javax.swing.JButton();
        cardNo_text = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Please Swipe Your card");

        save_text.setText("Ok");
        save_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_textActionPerformed(evt);
            }
        });

        cancel_Text.setText("Cancel");
        cancel_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_TextActionPerformed(evt);
            }
        });

        cardNo_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cardNo_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cardNo_textKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cardNo_textKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout mani_panelLayout = new javax.swing.GroupLayout(mani_panel);
        mani_panel.setLayout(mani_panelLayout);
        mani_panelLayout.setHorizontalGroup(
            mani_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mani_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(mani_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mani_panelLayout.createSequentialGroup()
                        .addComponent(save_text)
                        .addGap(179, 179, 179)
                        .addComponent(cancel_Text))
                    .addGroup(mani_panelLayout.createSequentialGroup()
                        .addComponent(cardNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(mani_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mani_panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        mani_panelLayout.setVerticalGroup(
            mani_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mani_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mani_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cardNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mani_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_text)
                    .addComponent(cancel_Text))
                .addGap(86, 86, 86))
        );

        jTextField1.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mani_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(mani_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public long startSec = 0;
    private void cardNo_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardNo_textKeyReleased
           char c = evt.getKeyChar();

        if( c==KeyEvent.VK_ENTER )
        {

           CardNo = cardNo_text.getText();
           System.out.println(CardNo);
           dispose();

        }
        else{
            if(cardNo_text.getText()!=null){
            int length = cardNo_text.getText().trim().length();
            if(length==1){
                startSec = System.currentTimeMillis();
            }
            else if(length>1){
                long Currsec = System.currentTimeMillis();
                long diff = Currsec-startSec;
                if(diff>700){
                    JOptionPane.showMessageDialog(this, "Do not use keyboard. Please swipe card.");
                    cardNo_text.setText(null);
                     System.out.println("Time Taken : "+diff);
                }
               
              }
            }
        }
        
        
        
        
        
    }//GEN-LAST:event_cardNo_textKeyReleased

    private void cancel_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_TextActionPerformed
        CardNo = null;
        dispose();
        
    }//GEN-LAST:event_cancel_TextActionPerformed

    private void save_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_textActionPerformed
       if(cardNo_text.getText()!=null && cardNo_text.getText().trim().length()>0){
           dispose();
       }
       else{
           cardNo_text.requestFocus();
           
       }
    }//GEN-LAST:event_save_textActionPerformed

    private void cardNo_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardNo_textKeyPressed
      
    }//GEN-LAST:event_cardNo_textKeyPressed

    private void cardNo_textKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardNo_textKeyTyped
      
    }//GEN-LAST:event_cardNo_textKeyTyped

    
   protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    } 
    
    
   
    public static CreditConfirm_UsbCard getDialog(Component parent,  AppView app, boolean flag) {

        Window window = getWindow(parent);
       
        CreditConfirm_UsbCard bill;

        if (window instanceof Frame) {
            bill = new CreditConfirm_UsbCard((Dialog) window, app, flag);
        } else {
            bill = new CreditConfirm_UsbCard((Dialog) window, app, flag);
        }
        bill.setLocation(200, 255);
        bill.setSize(200, 200);
        return bill;
        
        
    }
    
     public boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_Text;
    private javax.swing.JTextField cardNo_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel mani_panel;
    private javax.swing.JButton save_text;
    // End of variables declaration//GEN-END:variables




 public void init() throws BasicException {
        initComponents();
        
        mani_panel.setVisible(true);
        cardNo_text.requestFocus();
      

    }

  
 
   public String getCardNo(){
       return CardNo;
   }
 

}
