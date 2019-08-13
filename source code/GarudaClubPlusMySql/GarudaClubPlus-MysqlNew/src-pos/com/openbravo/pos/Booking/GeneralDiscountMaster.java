
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.clubmang.GeneralSettingsAccounting;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GeneralDiscountMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private Double Fixed_Amount;
    private Double Perc_Amount;
    private int Perc_flag;
    private  String  Name = "Booking Discount";
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");
   
    public GeneralDiscountMaster() {
        initComponents();
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("General Discount Master ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(perc_btn)
                        .addGap(35, 35, 35)
                        .addComponent(fix_amt_btn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(perc_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fixedamt_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                .addGap(80, 80, 80)
                .addComponent(jButton1)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jLabel1.setForeground(Color.BLUE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void perc_btnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perc_btnItemStateChanged
       if(perc_btn.isSelected()){
          perc_panel.setVisible(true);
          fixedamt_panel.setVisible(false);
          fixed_amt_text.setText(null);
          
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
          
          perc_text.setText(null);
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
        
        String x = null;
        try {
             x = getDiscountPermission();
        } catch (BasicException ex) {
            Logger.getLogger(GeneralDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       if(x.equals(m_App.getAppUserView().getUser().getName()))
       {
           
           
           
           
    
        if(perc_btn.isSelected()){
            Perc_flag = 1;
            if(perc_text.getText().trim().length()>0 && perc_text.getText()!=null){
                Perc_Amount = Double.parseDouble(perc_text.getText().trim().toString());
            }
            else{
                Perc_Amount=0.00;
            }
            
            Fixed_Amount = 0.00;
            
        }
        else{
            Perc_flag = 0;
            Perc_Amount = 0.00;
            if(fixed_amt_text.getText().trim().length()>0 && fixed_amt_text.getText()!=null){
                Fixed_Amount = Double.parseDouble(fixed_amt_text.getText().trim().toString());
            }
            else{
                Fixed_Amount=0.00;
            }
           
        }
        
        
       String  Name = "Booking Discount";
        
        
        
         try {
              if( new PreparedSentence(m_App.getSession()
                      , "UPDATE general_discount SET PERC_FLAG=? , PERC=? , AMOUNT=? , CRBY=? , CRDATE=? , CRHOST=? WHERE NAME=?"
                      , new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.DOUBLE ,Datas.DOUBLE , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec(
                      new Object[]{Perc_flag ,Perc_Amount  , Fixed_Amount , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() ,Name})<=0){
                  
                  new PreparedSentence(m_App.getSession()
                          , "INSERT INTO general_discount(ID ,NAME, PERC_FLAG, PERC , AMOUNT , CRBY , CRDATE , CRHOST) VALUES(?,?,?,?,?,?,?,?)"
                          , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT ,Datas.DOUBLE , Datas.DOUBLE  , Datas.STRING , Datas.TIMESTAMP , Datas.STRING})).exec(
                          new Object[]{UUID.randomUUID().toString(),Name,Perc_flag ,Perc_Amount , Fixed_Amount , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()   });
              }
              
              
              JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
              
              
          } catch (BasicException ex) {
              Logger.getLogger(GeneralDiscountMaster.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
               new MessageInf(ex).show(new JFrame());
              
          }
        
        
       }
       else{
           
            JOptionPane.showMessageDialog(this, "Sorry , You don't have permissions..!! ", "Warning", JOptionPane.WARNING_MESSAGE); 
           
           
       }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton fix_amt_btn;
    private javax.swing.JTextField fixed_amt_text;
    private javax.swing.JPanel fixedamt_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton perc_btn;
    private javax.swing.JPanel perc_panel;
    private javax.swing.JTextField perc_text;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "General Discount Master";
    }

    public void activate() throws BasicException {
        
        getButtonGrp();
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
    
    
    public void getButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(perc_btn);
        bg.add(fix_amt_btn);
    }
    
    
    public void reset(){
        
        
        perc_btn.setSelected(true);
        fixed_amt_text.setText(null);
        perc_text.setText(null);
        
        
    }
    
    public void loaddata() throws BasicException{
        
       int x = getPerc_Flag_For_GeneralDisc();
       
       if(x==1){
           perc_btn.setSelected(true);
           Double perc_amt = getPerc_Amount();
           perc_text.setText(decimalFormat.format(perc_amt));
           
           
       }
       else if(x==0){
           
         fix_amt_btn.setSelected(true);
         Double Fixed_Amt = getFixed_Amount();
         fixed_amt_text.setText(decimalFormat.format(Fixed_Amt));  
           
       }
       else{
           
           perc_btn.setSelected(true);
           perc_text.setText(null);
           fixed_amt_text.setText(null);
           
           
           
           
       }
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
   public String getDiscountPermission() throws BasicException{
       Object o = null;
       String x;
       o=new PreparedSentence(m_App.getSession(), "SELECT VALUE FROM generaltable  WHERE NAME = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING }) , SerializerReadString.INSTANCE).find(new Object[]{"Booking Discount Permission" } );
       if(o!=null){
            x = o.toString();
        }
       else{
           x = null;
       }
       return x;
   }
    
   
   
    public int getPerc_Flag_For_GeneralDisc() throws BasicException{
       Object o = null;
       int x;
       o=new PreparedSentence(m_App.getSession(), "SELECT PERC_FLAG FROM general_discount  WHERE NAME = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING }) , SerializerReadString.INSTANCE).find(new Object[]{Name} );
       if(o!=null){
            x = Integer.parseInt(o.toString());
        }
       else{
           x = 5;
       }
       return x;
   }
   
   
    
    
    
    public Double getPerc_Amount() throws BasicException{
       Object o = null;
       Double x;
       o=new PreparedSentence(m_App.getSession(), "SELECT PERC FROM general_discount  WHERE NAME = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING }) , SerializerReadString.INSTANCE).find(new Object[]{Name} );
       if(o!=null){
            x = Double.parseDouble(o.toString());
        }
       else{
           x = 0.00;
       }
       return x;
   }
    
    
    
    
      public Double getFixed_Amount() throws BasicException{
       Object o = null;
       Double x;
       o=new PreparedSentence(m_App.getSession(), "SELECT AMOUNT FROM general_discount  WHERE NAME = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING }) , SerializerReadString.INSTANCE).find(new Object[]{Name} );
       if(o!=null){
            x = Double.parseDouble(o.toString());
        }
       else{
           x = 0.00;
       }
       return x;
   }
    
    
    
    
   
}
