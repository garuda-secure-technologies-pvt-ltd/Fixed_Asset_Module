

package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.sales.CreditConfirm_UsbCard;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static org.omg.CORBA.ORB.init;


public class Discount_MasterDialog extends javax.swing.JDialog {
    private AppView app;
    private boolean flag;
    public String CardNo;
    public Double Fixed_amt;
    public Double Perc_Amt;
    public int Perc_flag;
    public int flag_X=0;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     
     
    public Discount_MasterDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        jPanel1.setVisible(true);
        
        initComponents();
        
    }
    public Discount_MasterDialog(java.awt.Frame parent, DataLogicSales dlSales, AppView app, boolean flag) {
        super(parent, true);        
       
        this.app = app;
        this.flag = flag;
    }
    
    public Discount_MasterDialog(java.awt.Dialog parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    public Discount_MasterDialog(java.awt.Frame parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
    
     protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    } 
     
     
     
    public static Discount_MasterDialog getDialog(Component parent,  AppView app, boolean flag) {

        Window window = getWindow(parent);
       
        Discount_MasterDialog bill;

        if (window instanceof Frame) {
            bill = new Discount_MasterDialog((Frame) window, app, flag);
        } else {
            bill = new Discount_MasterDialog((Dialog) window, app, flag);
        }
       // bill.setLocation(200, 255);
        //bill.setSize(200, 200);
        return bill;
        
        
    }
     
     
     
     
     public boolean showDialog() throws BasicException {
         init();
        setVisible(true);
        return true;
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        perc_btn = new javax.swing.JRadioButton();
        fix_amt_btn = new javax.swing.JRadioButton();
        perc_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        perc_text = new javax.swing.JTextField();
        fixedamt_panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fixed_amt_text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cancel_Btn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("General Discount  ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Give Discount on bill by :  ");

        perc_btn.setText("Percentage % on billed amount ");
        perc_btn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perc_btnItemStateChanged(evt);
            }
        });

        fix_amt_btn.setText("fixed amount");
        fix_amt_btn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fix_amt_btnItemStateChanged(evt);
            }
        });

        jLabel3.setText("% discount on billed amount : ");

        perc_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                perc_textKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout perc_panelLayout = new javax.swing.GroupLayout(perc_panel);
        perc_panel.setLayout(perc_panelLayout);
        perc_panelLayout.setHorizontalGroup(
            perc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perc_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(perc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        perc_panelLayout.setVerticalGroup(
            perc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perc_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(perc_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(perc_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Fixed Discount on billed Amount : ");

        fixed_amt_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fixed_amt_textKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout fixedamt_panelLayout = new javax.swing.GroupLayout(fixedamt_panel);
        fixedamt_panel.setLayout(fixedamt_panelLayout);
        fixedamt_panelLayout.setHorizontalGroup(
            fixedamt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fixedamt_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(fixed_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        fixedamt_panelLayout.setVerticalGroup(
            fixedamt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fixedamt_panelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(fixedamt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fixed_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cancel_Btn.setText("Cancel ");
        cancel_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_BtnActionPerformed(evt);
            }
        });

        jPanel6.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel6AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jButton12.setText("Enter");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(perc_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fixedamt_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2)
                                .addGap(42, 42, 42)
                                .addComponent(perc_btn)
                                .addGap(35, 35, 35)
                                .addComponent(fix_amt_btn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addComponent(cancel_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(perc_btn)
                            .addComponent(fix_amt_btn))
                        .addGap(33, 33, 33)
                        .addComponent(perc_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fixedamt_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(cancel_Btn))))
                .addGap(42, 42, 42))
        );

        jLabel1.setForeground(Color.BLUE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void perc_btnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perc_btnItemStateChanged
        if(perc_btn.isSelected()){
            perc_panel.setVisible(true);
            fixedamt_panel.setVisible(false);
            fixed_amt_text.setText(""+0.00);

        }
        else{
            perc_panel.setVisible(false);
            fixedamt_panel.setVisible(true);

        }
    }//GEN-LAST:event_perc_btnItemStateChanged

    private void fix_amt_btnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fix_amt_btnItemStateChanged
        if(fix_amt_btn.isSelected()){
            perc_panel.setVisible(false);
            fixedamt_panel.setVisible(true);

            perc_text.setText(""+0.00);
        }
        else{
            perc_panel.setVisible(true);
            fixedamt_panel.setVisible(false);
        }
    }//GEN-LAST:event_fix_amt_btnItemStateChanged

    private void perc_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_perc_textKeyReleased
        char c = evt.getKeyChar();
        Double Percentage;
        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {
            if(!Character.isDigit(c))
            {
                if(c!='.')
                {
                    JOptionPane.showMessageDialog(perc_text, "Please enter only numbers..");
                    perc_text.setText(null);
                }

            }
            else{
                Percentage = Double.parseDouble(perc_text.getText());
                if(Percentage > 100.00){
                    JOptionPane.showMessageDialog(perc_text, "Please enter upto 100% only.");
                    perc_text.setText(null);
                }

            }
        }
    }//GEN-LAST:event_perc_textKeyReleased

    private void fixed_amt_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fixed_amt_textKeyReleased
        char c = evt.getKeyChar();
        Double Percentage;
        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {
            if(!Character.isDigit(c))
            {
                if(c!='.')
                {
                    JOptionPane.showMessageDialog(fixed_amt_text, "Please enter only numbers..");
                    fixed_amt_text.setText(null);
                }

            }

        }
    }//GEN-LAST:event_fixed_amt_textKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(perc_btn.isSelected()){
           Perc_flag = 1;
           Perc_Amt = Double.parseDouble(perc_text.getText());
           Fixed_amt = Double.parseDouble(fixed_amt_text.getText());
           
           
       }
       else{
           
           Perc_flag = 0; 
           Perc_Amt = Double.parseDouble(perc_text.getText());
           Fixed_amt = Double.parseDouble(fixed_amt_text.getText());
           
           
       }
       
       flag_X = 1;
       dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cancel_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_BtnActionPerformed
       dispose();
    }//GEN-LAST:event_cancel_BtnActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
      Double d =  Double.parseDouble(jTextField3.getText());
      if(perc_btn.isSelected()){
                if(d<=100){
                     perc_text.setText(decimalFormat.format(d));
                 }
                 else{
                     perc_text.setText("0.00");
                     JOptionPane.showMessageDialog(this, " Percentage Should be less than 100.", "Warning", JOptionPane.WARNING_MESSAGE);  
                 }
         
         
      }
      else{
          fixed_amt_text.setText(decimalFormat.format(d));
      }
      
      
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

     private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            jTextField3.setText(null);
        } else if (cTrans == '+' || cTrans == '-') {
        } else if (cTrans == ' ' || cTrans == '=') {
        } else {

            jTextField3.setText(jTextField3.getText() + cTrans);
        }
    } 
    
    
    private void jPanel6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6AncestorAdded

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
         if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
             
             Double d = Double.parseDouble(jTextField3.getText().trim());
             
             if(perc_btn.isSelected()){
                 
                 if(d<=100){
                     perc_text.setText(decimalFormat.format(d));
                 }
                 else{
                     perc_text.setText("0.00");
                     jTextField3.setText(null);
                     JOptionPane.showMessageDialog(this, " Percentage Should be less than 100.", "Warning", JOptionPane.WARNING_MESSAGE);  
                 }
                 
                 
                 
             }
             else{
                 fixed_amt_text.setText(decimalFormat.format(d));
             }
             
             
             
             
             
             
         }
    }//GEN-LAST:event_jTextField3KeyReleased

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_Btn;
    private javax.swing.JRadioButton fix_amt_btn;
    private javax.swing.JTextField fixed_amt_text;
    private javax.swing.JPanel fixedamt_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton perc_btn;
    private javax.swing.JPanel perc_panel;
    private javax.swing.JTextField perc_text;
    // End of variables declaration//GEN-END:variables

 public void init() throws BasicException {
        initComponents();
        
        jPanel1.setVisible(true);
        perc_btn.setSelected(true);
        getButtonGrp();
        flag_X = 0;
    }
 
 
 
   public Double getPerc_Amt(){
       return Perc_Amt;
   }
   public Double getFixed_amt(){
       return Fixed_amt;
   }
   
   public int getPerc_Flag(){
       return Perc_flag;
   }

    public int getFlag_X(){
       return flag_X;
   }
   
   
   
 public void getButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(perc_btn);
        bg.add(fix_amt_btn);
    }

}
