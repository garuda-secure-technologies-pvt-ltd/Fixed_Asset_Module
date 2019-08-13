package com.openbravo.pos.sales.restaurant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerWriteBasic;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.sales.QTicketLineInfo;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.ticket.ProductInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * discountdetail.java
 *
 * Created on Jan 9, 2009, 1:18:31 PM
 */
/**
 *
 * @author swathi
 */
public class discountDetailnum1 extends javax.swing.JDialog {

    /** Creates new form discountdetail */
    QTicketLineInfo qtline;
    DataLogicSales dlsales;
    public boolean resultok = false;
    private AppView app;
    private String discountid;
    private String customername = "";
    private String userid;
    private String productid;
    private int qty;
    private double rate;
    private double amount;
    private String reason;
    private String authorised = "";
    private String qtitemid;
    private String productname;
    private int i = 0;
    private int totalqty;
    private int limit;
    //  public discountDetail de=new discountDetail();

    protected discountDetailnum1(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButton4.setVisible(false);
//        jButton1.addActionListener(new ActionListener(){  //code for QT
//      public void actionPerformed(ActionEvent ae){  
//              
//        OK();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_F10) )
//               
//                OK();  
//          }  
//          return false;}});


 // jButton1.setToolTipText("F10");

       
    }

    public discountDetailnum1(Frame parent, DataLogicSales dlSales, QTicketLineInfo qtline, AppView app) {
        super(parent, true);
        dlsales = dlSales;
        this.qtline = qtline;
        this.app = app;
        this.limit = qtline.getMultiply();
        this.limit = limit > 0 ? -1 * limit : limit;
        //  i=qtline.getMultiply();*/
        initComponents();
        
    }
     public void Cancel(){
         jTextArea1.setText("Order Canceled");
     }
   public void  OK(){
       int qtytotal = 0;
        String tempstr;
        reason = jTextArea1.getText();
        qty = Integer.parseInt(jTextField4.getText());
        qty *= -1;
        List<detail> arr1 = new ArrayList<detail>();

        if (reason.equals(" ")) {
            JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.reasonwarning"), AppLocal.getIntString("message.reasontitle"), JOptionPane.WARNING_MESSAGE);
        // dispose();
        } else {
            /*  Object[] obj = (Object [] )  new StaticSentence(app.getSession()
            , "SELECT SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID=? AND PRODUCT=? "
            , SerializerWriteString.INSTANCE
            ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING})).find(qtitemid,productid);
            Object[] obj1=( Object []) new StaticSentence(app.getSession()
            ,"SELECT SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID=? AND PRODUCT=?"
            ,SerializerWriteString.INSTANCE
            ,new SerializerReadBasic (new Datas[] {Datas.STRING,Datas.STRING})).find(qtitemid,productid);*/
            try {

                // List<detail> arr=new ArrayList();
                List arr = new StaticSentence(app.getSession(), "SELECT PRODUCT_ID,SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID = ? AND (AUTHORISED is null or AUTHORISED='true') GROUP BY QTITEMID,PRODUCT_ID  " //sameeer: added authorised=true
                        , SerializerWriteString.INSTANCE, new SerializerReadClass(discountDetailnum1.detail.class)).list(qtitemid);

                String name, qty;
                detail detailtemp;
                int i = 0, j = 0;
                if (arr != null) {
                    arr1 = arr;
                }
                if (arr != null) {
                    for (i = 0; i < (arr1.size()); i++) {
                        detailtemp = arr1.get(i);
                        if (detailtemp.getname().equals(productid)) {
                            // qty=obj1[i+j+1].toString();
                            qtytotal = detailtemp.getqty();
                            break;
                        }
                    // j=2;
                    }
                } else {
                    qtytotal = 0;
                }




            } catch (BasicException e) {
                String aa = "aa";
            }
            if (totalqty < (qtytotal + qty)) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtywarning"), AppLocal.getIntString("message.qtytitle"), JOptionPane.WARNING_MESSAGE);
            // dispose();
            } else if (qty == 0) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtyzerowarning"), AppLocal.getIntString("message.qtytitle"), JOptionPane.WARNING_MESSAGE);
            // dispose();
            } else {
                //  customername="aaa";
                authorised = null;

                try {
                    Date d = new Date();
                    amount = rate * qty;
                    Object[] values = new Object[]{discountid, customername, userid, productid, qty, rate, amount, reason, authorised, qtitemid, d};
                    Datas[] datas = new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP};
                    new PreparedSentence(app.getSession(), "INSERT INTO DISCOUNTLIST (ID,CUSTOMER_ID,USER_ID,PRODUCT_ID,QTY,RATE,AMOUNT,REASON,AUTHORISED,QTITEMID,CRDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).exec(values);
                } catch (BasicException e) {
                    String aaa = "aswd";
                }
                dispose();
            }
        }

   }
 public void  SUBTRACT(){
     if (i < 0) {
            i++;
            refresh();
        }
 }
   public void ADD(){
       if (i > limit) {
            i--;
            refresh();
        }
   }
 public void bottleNeck(){
      jTextArea1.setText("Bottle Neck Breakage");
 }
    
    public void dust(){
        jTextArea1.setText("Dust ");
    }
    public void   smell(){
            jTextArea1.setText("Bad Smell");
}
    public discountDetailnum1(Dialog parent, DataLogicSales dlSales, QTicketLineInfo qtline, AppView app) {
        super(parent, true);
        dlsales = dlSales;
        this.qtline = qtline;
        this.app = app;
        this.limit = qtline.getMultiply();
        this.limit = limit > 0 ? -1 * limit : limit;
        initComponents();
    }

    protected discountDetailnum1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

    public static discountDetailnum1 getDialog(Component parent, DataLogicSales dlSales, QTicketLineInfo qtline, AppView app) {

        Window window = getWindow(parent);

        discountDetailnum1 dP;

        if (window instanceof Frame) {
            dP = new discountDetailnum1((Frame) window, dlSales, qtline, app);
        } else {
            dP = new discountDetailnum1((Dialog) window, dlSales, qtline, app);
        }

        return dP;
    }

    public boolean showDialog(String customer, String qtid) throws BasicException {

        discountid = java.util.UUID.randomUUID().toString();

//         this.jTextField2.setEnabled(true);
        QTListnum1.discount_panel = false;
        customername = customer;
        qtitemid = qtid;
        ProductInfoExt pi = dlsales.getProductInfo(qtline.getProduct());

        ProductInfo pInfo = LookupUtilityImpl.getInstance(null).getProductsMap().get(qtline.getProduct());
        userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        this.jTextField1.setEnabled(true);
        jTextField1.setText(customer);
        jTextField2.setEnabled(true);
        jTextField2.setText(userid);
        jTextField3.setEnabled(true);
        productid = qtline.getProduct();
        totalqty = qtline.getMultiply();
        //qtline.getProduct();
        jTextField3.setText(pInfo.getName());
        qty = qtline.getMultiply();
        jTextField4.setEnabled(true);
        jTextField4.setText("" + 0);
        jTextField5.setEnabled(true);
        rate = qtline.getRate();
        jTextField5.setText("" + rate);
        jTextField6.setEnabled(true);
        amount = qtline.getMultiply() * qtline.getRate();
        jTextField6.setText("" + amount);
        jTextArea1.setEnabled(true);
        jTextArea1.setEditable(true);
        jTextArea1.setText(" ");
        jButton1.setEnabled(true);

        this.setVisible(true);
        return resultok;

    }

    public static class detail implements SerializableRead {

        private String name;
        private int qty;

        public void readValues(DataRead dr) throws BasicException {
            name = dr.getString(1);
            qty = dr.getInt(2);
        }

        public String getname() {
            return name;
        }

        public int getqty() {
            return qty;
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        smell = new javax.swing.JButton();
        dust = new javax.swing.JButton();
        bottleNeck = new javax.swing.JButton();
        orderCancel = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Customer Name :");
        jLabel1.setFocusable(false);

        jLabel2.setText("User Name       : ");
        jLabel2.setFocusable(false);

        jLabel3.setText("Product Name  :");
        jLabel3.setFocusable(false);

        jTextField3.setEditable(false);
        jTextField3.setFocusable(false);

        jLabel4.setText("Quantity          :");
        jLabel4.setFocusable(false);

        jLabel5.setText("Rate               :");
        jLabel5.setFocusable(false);

        jTextField5.setEditable(false);
        jTextField5.setFocusable(false);

        jLabel6.setText("Amount          :");
        jLabel6.setFocusable(false);

        jTextField6.setEditable(false);
        jTextField6.setFocusable(false);

        jLabel7.setText("Reason         :");
        jLabel7.setFocusable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("OK");
        jButton1.setNextFocusableComponent(jButton1);
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

        jTextField1.setEditable(false);
        jTextField1.setFocusable(false);

        jTextField2.setEditable(false);
        jTextField2.setFocusable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnplus.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnminus.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField4.setBackground(new java.awt.Color(236, 233, 216));
        jTextField4.setEditable(false);
        jTextField4.setFocusable(false);

        smell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/smell1.jpg"))); // NOI18N
        smell.setText("jButton4");
        smell.setFocusable(false);
        smell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smellActionPerformed(evt);
            }
        });

        dust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/dust.jpg"))); // NOI18N
        dust.setText("jButton4");
        dust.setFocusable(false);
        dust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dustActionPerformed(evt);
            }
        });

        bottleNeck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/bottle neck.jpg"))); // NOI18N
        bottleNeck.setText("jButton4");
        bottleNeck.setFocusable(false);
        bottleNeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottleNeckActionPerformed(evt);
            }
        });

        orderCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/order cancel.jpg"))); // NOI18N
        orderCancel.setText("jButton5");
        orderCancel.setFocusable(false);
        orderCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderCancelActionPerformed(evt);
            }
        });

        jButton4.setText("esc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(jTextField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, 0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(smell, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dust, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bottleNeck, javax.swing.GroupLayout.PREFERRED_SIZE, 81, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(orderCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(17, 17, 17)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderCancel)
                    .addComponent(bottleNeck)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(smell, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dust, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        //jButton1.addActionListener(new ActionListener(){  //code for QT
            //      public void actionPerformed(ActionEvent ae){
                //
                //        OK();}});
    //    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    //     .addKeyEventDispatcher(new KeyEventDispatcher(){
        //        public boolean dispatchKeyEvent(KeyEvent e){
            //          if(e.getID() == KeyEvent.KEY_PRESSED)
            //          {
                //            if((e.getKeyCode() == KeyEvent.VK_F10) )
                //
                //                OK();
                //          }
            //          return false;}});
//
//

java.awt.EventQueue.invokeLater(new Runnable() {

    public void run() {
        jButton1.requestFocus();
    }
    });

    jButton1.setToolTipText("F1");
    jButton2.addActionListener(new ActionListener(){  //code for QT
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

jButton2.setToolTipText("+");
jButton3.addActionListener(new ActionListener(){  //code for QT
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
jButton3.setToolTipText("-");
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
    jButton4.addActionListener(new ActionListener(){  //code for QT
        public void actionPerformed(ActionEvent ae){

        }});
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new KeyEventDispatcher(){
            public boolean dispatchKeyEvent(KeyEvent e){
                if(e.getID() == KeyEvent.KEY_PRESSED)
                {
                    if((e.getKeyCode() == KeyEvent.VK_ESCAPE) )

                    dispose();
                }
                return false;}});

    jButton4.setVisible(false);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //  dispose();
//        int qtytotal = 0;
//        String tempstr;
//        reason = jTextArea1.getText();
//        qty = Integer.parseInt(jTextField4.getText());
//        qty *= -1;
//        List<detail> arr1 = new ArrayList<detail>();
//
//        if (reason.equals(" ")) {
//            JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.reasonwarning"), AppLocal.getIntString("message.reasontitle"), JOptionPane.WARNING_MESSAGE);
//        // dispose();
//        } else {
//            /*  Object[] obj = (Object [] )  new StaticSentence(app.getSession()
//            , "SELECT SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID=? AND PRODUCT=? "
//            , SerializerWriteString.INSTANCE
//            ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING})).find(qtitemid,productid);
//            Object[] obj1=( Object []) new StaticSentence(app.getSession()
//            ,"SELECT SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID=? AND PRODUCT=?"
//            ,SerializerWriteString.INSTANCE
//            ,new SerializerReadBasic (new Datas[] {Datas.STRING,Datas.STRING})).find(qtitemid,productid);*/
//            try {
//
//                // List<detail> arr=new ArrayList();
//                List arr = new StaticSentence(app.getSession(), "SELECT PRODUCT_ID,SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID = ? AND (AUTHORISED is null or AUTHORISED='true') GROUP BY QTITEMID,PRODUCT_ID  " //sameeer: added authorised=true
//                        , SerializerWriteString.INSTANCE, new SerializerReadClass(discountDetailnum1.detail.class)).list(qtitemid);
//
//                String name, qty;
//                detail detailtemp;
//                int i = 0, j = 0;
//                if (arr != null) {
//                    arr1 = arr;
//                }
//                if (arr != null) {
//                    for (i = 0; i < (arr1.size()); i++) {
//                        detailtemp = arr1.get(i);
//                        if (detailtemp.getname().equals(productid)) {
//                            // qty=obj1[i+j+1].toString();
//                            qtytotal = detailtemp.getqty();
//                            break;
//                        }
//                    // j=2;
//                    }
//                } else {
//                    qtytotal = 0;
//                }
//
//
//
//
//            } catch (BasicException e) {
//                String aa = "aa";
//            }
//            if (totalqty < (qtytotal + qty)) {
//                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtywarning"), AppLocal.getIntString("message.qtytitle"), JOptionPane.WARNING_MESSAGE);
//            // dispose();
//            } else if (qty == 0) {
//                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtyzerowarning"), AppLocal.getIntString("message.qtytitle"), JOptionPane.WARNING_MESSAGE);
//            // dispose();
//            } else {
//                //  customername="aaa";
//                authorised = null;
//
//                try {
//                    Date d = new Date();
//                    amount = rate * qty;
//                    Object[] values = new Object[]{discountid, customername, userid, productid, qty, rate, amount, reason, authorised, qtitemid, d};
//                    Datas[] datas = new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP};
//                    new PreparedSentence(app.getSession(), "INSERT INTO DISCOUNTLIST (ID,CUSTOMER_ID,USER_ID,PRODUCT_ID,QTY,RATE,AMOUNT,REASON,AUTHORISED,QTITEMID,CRDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).exec(values);
//                } catch (BasicException e) {
//                    String aaa = "aswd";
//                }
//                dispose();
//            }
//        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void smellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smellActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("Bad Smell");
    }//GEN-LAST:event_smellActionPerformed

    private void dustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dustActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("Dust ");
    }//GEN-LAST:event_dustActionPerformed

    private void bottleNeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottleNeckActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("Bottle Neck Breakage");
    }//GEN-LAST:event_bottleNeckActionPerformed

    private void orderCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderCancelActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("Order Canceled");
    }//GEN-LAST:event_orderCancelActionPerformed

private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
// TODO add your handling code here:
  if(evt.getKeyCode()==KeyEvent.VK_F1 ){
       int qtytotal = 0;
        String tempstr;
        reason = jTextArea1.getText();
        qty = Integer.parseInt(jTextField4.getText());
        qty *= -1;
        List<detail> arr1 = new ArrayList<detail>();

        if (reason.equals(" ")) {
            JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.reasonwarning"), AppLocal.getIntString("message.reasontitle"), JOptionPane.WARNING_MESSAGE);
        // dispose();
        } else {
            /*  Object[] obj = (Object [] )  new StaticSentence(app.getSession()
            , "SELECT SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID=? AND PRODUCT=? "
            , SerializerWriteString.INSTANCE
            ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING})).find(qtitemid,productid);
            Object[] obj1=( Object []) new StaticSentence(app.getSession()
            ,"SELECT SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID=? AND PRODUCT=?"
            ,SerializerWriteString.INSTANCE
            ,new SerializerReadBasic (new Datas[] {Datas.STRING,Datas.STRING})).find(qtitemid,productid);*/
            try {

                // List<detail> arr=new ArrayList();
                List arr = new StaticSentence(app.getSession(), "SELECT PRODUCT_ID,SUM(QTY) FROM DISCOUNTLIST WHERE QTITEMID = ? AND (AUTHORISED is null or AUTHORISED='true') GROUP BY QTITEMID,PRODUCT_ID  " //sameeer: added authorised=true
                        , SerializerWriteString.INSTANCE, new SerializerReadClass(discountDetailnum1.detail.class)).list(qtitemid);

                String name, qty;
                detail detailtemp;
                int i = 0, j = 0;
                if (arr != null) {
                    arr1 = arr;
                }
                if (arr != null) {
                    for (i = 0; i < (arr1.size()); i++) {
                        detailtemp = arr1.get(i);
                        if (detailtemp.getname().equals(productid)) {
                            // qty=obj1[i+j+1].toString();
                            qtytotal = detailtemp.getqty();
                            break;
                        }
                    // j=2;
                    }
                } else {
                    qtytotal = 0;
                }




            } catch (BasicException e) {
                String aa = "aa";
            }
            if (totalqty < (qtytotal + qty)) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtywarning"), AppLocal.getIntString("message.qtytitle"), JOptionPane.WARNING_MESSAGE);
            // dispose();
            } else if (qty == 0) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtyzerowarning"), AppLocal.getIntString("message.qtytitle"), JOptionPane.WARNING_MESSAGE);
            // dispose();
            } else {
                //  customername="aaa";
                authorised = null;

                try {
                    Date d = new Date();
                    amount = rate * qty;
                    Object[] values = new Object[]{discountid, customername, userid, productid, qty, rate, amount, reason, authorised, qtitemid, d};
                    Datas[] datas = new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP};
                    new PreparedSentence(app.getSession(), "INSERT INTO DISCOUNTLIST (ID,CUSTOMER_ID,USER_ID,PRODUCT_ID,QTY,RATE,AMOUNT,REASON,AUTHORISED,QTITEMID,CRDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).exec(values);
                } catch (BasicException e) {
                    String aaa = "aswd";
                }
                dispose();
            }
        }

  }
}//GEN-LAST:event_jButton1KeyPressed
    private void refresh() {
        jTextField4.setText("" + i);
        double changerate = Double.parseDouble(jTextField5.getText()) * Integer.parseInt(jTextField4.getText());
        jTextField6.setText(String.valueOf(changerate));
        jButton1.setEnabled(true);
    }
    /**
     * @param args the command line arguments
     */
    /*   public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
    discountdetail dialog = new discountdetail(new javax.swing.JFrame(), true);
    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
    public void windowClosing(java.awt.event.WindowEvent e) {
    System.exit(0);
    }
    });
    dialog.setVisible(true);
    }
    });
    }*/

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton orderCancel;
    private javax.swing.JButton smell;
    // End of variables declaration//GEN-END:variables
}
