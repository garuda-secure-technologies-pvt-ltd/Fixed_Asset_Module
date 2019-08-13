/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * discountPage.java
 *
 * Created on Dec 11, 2008, 12:53:41 PM
 */

package com.openbravo.pos.sales.restaurant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.BillLineInfo;
import com.openbravo.pos.sales.QTicketLineInfo;
import com.openbravo.pos.sales.Qticket;
import com.openbravo.pos.ticket.ProductInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class discountPagenum1 extends javax.swing.JDialog {

    private BillLineInfo bline;
    private List<BillLineInfo> blist;
    private int i = 0;
    private boolean resultok = false;
    private String cname;
    private Double rate=0.0;
    private int limit; //This is always negetive and discount > limit (because both are negetive)
    /** Creates new form discountPage */
    protected discountPagenum1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    protected discountPagenum1(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public discountPagenum1(Frame parent, List<BillLineInfo> blist, BillLineInfo bline, int limit) {
        this(parent, true);
        this.bline = bline;
        this.blist = blist;
        this.limit = limit > 0 ? -1 * limit : limit;
    }

    public discountPagenum1(Dialog parent,List<BillLineInfo> blist, BillLineInfo bline, int limit) {
        this(parent, true);
        this.bline = bline;
        this.blist = blist;
        this.limit = limit > 0 ? -1 * limit : limit;
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }


    public static discountPagenum1 getDialog(Component parent, List<BillLineInfo> blist, BillLineInfo bline, int limit) {

        Window window = getWindow(parent);

        discountPagenum1 dP;

        if (window instanceof Frame) {
            dP = new discountPagenum1((Frame) window, blist, bline, limit);
        } else {
            dP = new discountPagenum1((Dialog) window, blist, bline, limit);
        }

        return dP;
    }

    public String showDialogreason() throws BasicException{
         jTextField1.setVisible(false);
         jTextField2.setVisible(false);
         jTextField3.setVisible(false);
         jLabel1.setVisible(false);
         jLabel2.setVisible(false);
         jLabel3.setVisible(false);
         jButton3.setVisible(false);
         jButton4.setVisible(false);
            setVisible(true);
            return res;
    }

public void ADD(){
   if (i > limit) {
            i--;
            refresh();
        }
}

public void SUBTRACT(){
    
       if (i < -1) {
            i++;
            refresh();
        }
}

 public void smell(){
    reason.setText("Bad Smell");
 }
  public void   dust()
  {
      reason.setText("Dust ");
  }
 public void  bottleNeck(){
      reason.setText("Bottle Neck Breakage");
 }
  public void Cancel(){
           reason.setText("Order Canceled");
  }
    public void Cancel1(){
        dispose();
        resultok = false;
    }
  
    public  boolean showDialog(String cname) throws BasicException {

      
        ProductInfo pi =  bline.getProduct();
        jTextField1.setEnabled(true);
        jTextField1.setText(pi.getName());
        jTextField2.setEnabled(true);
        jTextField3.setText(""+pi.getPriceSell());
        jTextField2.setText("0");
        jButton3.setEnabled(true);
        jButton2.setEnabled(true);
       // bline.setProduct(pi);
        rate=pi.getPriceSell();
        this.cname=cname;
          setVisible(true);
       
//        double changerate = pi.getPriceSell()*qtline.getMultiply();
//        qtline.setRate(changerate);
        return resultok;
       }

     private boolean validateDiscount(List<BillLineInfo> bill, BillLineInfo discountbline) throws BasicException {
        int quantity = discountbline.getMultiply();
        for (BillLineInfo blInfo : bill) {
            if (blInfo.getProduct().getID().equals(discountbline.getProduct().getID()) && blInfo.getRate()==(discountbline.getRate())) {
               int del=blInfo.getMultiply();
                quantity += blInfo.getMultiply();
            }
        }

      Object obj[]=(Object[])  new StaticSentence(app.getSession()
                        , "SELECT SUM(QTY * -1) FROM REVERSEDBILL WHERE BILLID = ? AND PRODUCT=? AND RATE=? AND AUTHORISED=FALSE AND AUTHORISED IS NULL GROUP BY BILLID  "
                        ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE})
                ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{discountbline.getParentid(),discountbline.getProduct().getID(),discountbline.getRate()});
      if(obj!=null){
          Double temp=Double.parseDouble(obj[0].toString());
        quantity=quantity - temp.intValue();
      }
      if (quantity < 0) {
            new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.toomuchdiscount")).show(this);
            return false;
        }

        return true;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reason = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        smell = new javax.swing.JButton();
        dust = new javax.swing.JButton();
        bottleNeck = new javax.swing.JButton();
        orderCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Product");

        jTextField1.setEditable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantity");

        jTextField2.setEditable(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnplus.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Rate");

        jTextField3.setEditable(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnminus.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        reason.setColumns(20);
        reason.setRows(5);
        jScrollPane1.setViewportView(reason);

        jLabel4.setText("Reason");

        smell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/smell1.jpg"))); // NOI18N
        smell.setText("jButton4");
        smell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smellActionPerformed(evt);
            }
        });

        dust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/dust.jpg"))); // NOI18N
        dust.setText("jButton4");
        dust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dustActionPerformed(evt);
            }
        });

        bottleNeck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/bottle neck.jpg"))); // NOI18N
        bottleNeck.setText("jButton4");
        bottleNeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottleNeckActionPerformed(evt);
            }
        });

        orderCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/order cancel.jpg"))); // NOI18N
        orderCancel.setText("jButton5");
        orderCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(smell, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dust, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bottleNeck, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(orderCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)))))
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(smell, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dust, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderCancel)
                    .addComponent(bottleNeck, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jButton1.requestFocus();
            }
        });

        jButton1.setToolTipText("F1");
        jButton2.addActionListener(new ActionListener(){  //code for QT
            public void actionPerformed(ActionEvent ae){

                Cancel1();}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_ESCAPE) )

                Cancel1();
            }
            return false;}});

jButton2.setToolTipText("ESC");
jButton3.addActionListener(new ActionListener(){  //code for QT
    public void actionPerformed(ActionEvent ae){

        ADD();}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
public boolean dispatchKeyEvent(KeyEvent e){
    if(e.getID() == KeyEvent.KEY_PRESSED)
    {
        if((e.getKeyCode() == KeyEvent.VK_ADD) )

        ADD();
    }
    return false;}});

    jButton3.setToolTipText("+");
    ;
    jButton4.addActionListener(new ActionListener(){  //code for QT
        public void actionPerformed(ActionEvent ae){

            SUBTRACT();}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
    public boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            if((e.getKeyCode() == KeyEvent.VK_SUBTRACT) )

            SUBTRACT();
        }
        return false;}});
jButton4.setToolTipText("-");
smell.addActionListener(new ActionListener(){  //code for QT
public void actionPerformed(ActionEvent ae){

    smell();}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_F5) )

                smell();
            }
            return false;}});
smell.setToolTipText("F5");
dust.addActionListener(new ActionListener(){  //code for QT
    public void actionPerformed(ActionEvent ae){

        dust();}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
public boolean dispatchKeyEvent(KeyEvent e){
    if(e.getID() == KeyEvent.KEY_PRESSED)
    {
        if((e.getKeyCode() == KeyEvent.VK_F6) )

        dust();
    }
    return false;}});

    dust.setToolTipText("F6");
    bottleNeck.addActionListener(new ActionListener(){  //code for QT
        public void actionPerformed(ActionEvent ae){

            bottleNeck();}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
    public boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            if((e.getKeyCode() == KeyEvent.VK_F7) )

            bottleNeck();
        }
        return false;}});

bottleNeck.setToolTipText("F7");
orderCancel.addActionListener(new ActionListener(){  //code for QT
public void actionPerformed(ActionEvent ae){

    Cancel();}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_F8) )

                Cancel();
            }
            return false;}});

orderCancel.setToolTipText("F8");

pack();
}// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (i > limit) {
            i--;
            refresh();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        resultok = false;
    }//GEN-LAST:event_jButton2ActionPerformed
   private String res=null;
   private  AppView app;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code heqtline.setProduct(pi.getID());
//        app=LookupUtilityImpl.getInstance(null).getAppView();
//        if(jButton3.isVisible()){
//            res=null;
//        String res=reason.getText();
//        int qty1=Integer.parseInt(jTextField2.getText());
//        Double qty=Double.parseDouble(jTextField2.getText());
//        Double prate=Double.parseDouble(jTextField3.getText());
//        
//         bline.setRate(prate);
//         bline.setMultiply(qty1);
//         String bid=bline.getParentid();
//          try{
//        boolean valid= validateDiscount(blist,bline);
//
//        if(valid==true && res.length()>0){
//            Date dnow=new Date();
//           
//         Object[] value=new Object[]{UUID.randomUUID().toString(),bline.getParentid(),cname,bline.getProduct().getID(),qty,(prate),res,app.getAppUserView().getUser().getName(),dnow};
//         new PreparedSentence(app.getSession()
//                    , "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
//                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.TIMESTAMP})).exec(value);
//            
//             dispose();
//         resultok = true;
//        }else{
//            if(valid==true)
//             JOptionPane.showMessageDialog(this, "Please Enter the reason for discounting the bill", "Cannot Discount bill", JOptionPane.OK_OPTION);
//
//           // dispose();
//             resultok = false;
//        }
//        }catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else{
//            res=reason.getText();
//             dispose();
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refresh() {
        jTextField2.setText(""+i);
       //  double changerate = rate*Integer.parseInt(jTextField2.getText());
      //   jTextField3.setText(String.valueOf(changerate));
         jButton1.setEnabled(true);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (i < -1) {
            i++;
            refresh();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void smellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smellActionPerformed
        // TODO add your handling code here:
        reason.setText("Bad Smell");
}//GEN-LAST:event_smellActionPerformed

    private void dustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dustActionPerformed
        // TODO add your handling code here:
        reason.setText("Dust ");
}//GEN-LAST:event_dustActionPerformed

    private void bottleNeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottleNeckActionPerformed
        // TODO add your handling code here:
        reason.setText("Bottle Neck Breakage");
}//GEN-LAST:event_bottleNeckActionPerformed

    private void orderCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderCancelActionPerformed
        // TODO add your handling code here:
        reason.setText("Order Canceled");
}//GEN-LAST:event_orderCancelActionPerformed

private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
// TODO add your handling code here:
    
     if(evt.getKeyCode()==KeyEvent.VK_F1 ){
         app=LookupUtilityImpl.getInstance(null).getAppView();
        if(jButton3.isVisible()){
            res=null;
        String res=reason.getText();
        int qty1=Integer.parseInt(jTextField2.getText());
        Double qty=Double.parseDouble(jTextField2.getText());
        Double prate=Double.parseDouble(jTextField3.getText());
        
         bline.setRate(prate);
         bline.setMultiply(qty1);
         String bid=bline.getParentid();
          try{
        boolean valid= validateDiscount(blist,bline);

        if(valid==true && res.length()>0){
            Date dnow=new Date();
           
         Object[] value=new Object[]{UUID.randomUUID().toString(),bline.getParentid(),cname,bline.getProduct().getID(),qty,(prate),res,app.getAppUserView().getUser().getName(),dnow};
         new PreparedSentence(app.getSession()
                    , "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.TIMESTAMP})).exec(value);
            
             dispose();
         resultok = true;
        }else{
            if(valid==true)
             JOptionPane.showMessageDialog(this, "Please Enter the reason for discounting the bill", "Cannot Discount bill", JOptionPane.OK_OPTION);

           // dispose();
             resultok = false;
        }
        }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else{
            res=reason.getText();
             dispose();
        }
     }
}//GEN-LAST:event_jButton1KeyPressed

    /**
    * @param args the command line arguments
    */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottleNeck;
    private javax.swing.JButton dust;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton orderCancel;
    private javax.swing.JTextArea reason;
    private javax.swing.JButton smell;
    // End of variables declaration//GEN-END:variables

}
