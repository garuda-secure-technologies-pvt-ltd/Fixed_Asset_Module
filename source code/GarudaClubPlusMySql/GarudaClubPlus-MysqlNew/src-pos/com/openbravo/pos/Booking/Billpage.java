
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadDouble;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.BankInfo;
import com.openbravo.pos.payment.ChequeDetails;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
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
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Billpage extends javax.swing.JDialog {

     private BookedRoomStatusTableModel.Room_StatusInfo rsi;
     private BookedHallStatusTableModel.HallStatusInfo hsi;
     Date CurrentDate = new Date();
     private BillTableModel BillModel;
     private DataLogicSales m_dlSales;
     private AppView m_App;
     private String custId;
     private double Final_Amount;
     private JPaymentSelect paymentdialogrefund;
     private JPaymentSelect paymentdialogreceipt;
     private CustomerInfoExt Customer;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     private BookHall BkHall;
     private BookGuestRoom BkGuestRoom;
     private TicketParser m_TTP;
     private int Payment_Flag=0;
     private BillInfo ticket;
     private DataLogicFacilities dmang;
     private Double BalAmt;
     
     public Billpage(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
    }
    
    
    public Billpage(BookedRoomStatusTableModel.Room_StatusInfo showdata , int Flag){
        
        initComponents();
        rsi = showdata;
        Payment_Flag = Flag;
        
       
         
         
    }
    
     public Billpage(BookedHallStatusTableModel.HallStatusInfo showdata , int Flag){
        
        initComponents();
        hsi = showdata;
        Payment_Flag = Flag;
        
        
        
       
        
     }
    
    
    public void showDialog() throws BasicException
    {
        init();
       // this.setSize(500, 500);
        m_jTendered.addEditorKeys(m_jKeys);
        m_jTendered.reset();
        m_jTendered.activate();
        setVisible(true);
        setFocusable(true);
        setFocusableWindowState(true);
       
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
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        date_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        mem_id_txt = new javax.swing.JTextField();
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
        pay_btn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        m_jTendered = new com.openbravo.editor.JEditorCurrencyPositive();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        m_jKeys = new com.openbravo.editor.JEditorKeys();
        sum_total = new javax.swing.JTextField();
        remaining = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        given = new javax.swing.JTextField();
        min_Adv_Panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Min_adv_Text = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Bal_Amt_Text = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Date : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Member ID :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Sr . No.", "Name", "Room/Hall Name", "No of rooms", "Rate", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        pay_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pay_btn.setForeground(new java.awt.Color(0, 0, 204));
        pay_btn.setText("Pay");
        pay_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_btnActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 0, 0));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Reprint");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        m_jTendered.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                m_jTenderedKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sum Total :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Remaining :");

        m_jKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jKeysActionPerformed(evt);
            }
        });

        sum_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        remaining.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Enter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        given.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setText("Minimum Advance Amt : ");

        Min_adv_Text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Min_adv_Text.setForeground(new java.awt.Color(153, 0, 51));

        javax.swing.GroupLayout min_Adv_PanelLayout = new javax.swing.GroupLayout(min_Adv_Panel);
        min_Adv_Panel.setLayout(min_Adv_PanelLayout);
        min_Adv_PanelLayout.setHorizontalGroup(
            min_Adv_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(min_Adv_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Min_adv_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        min_Adv_PanelLayout.setVerticalGroup(
            min_Adv_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(min_Adv_PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(min_Adv_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Min_adv_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Min_adv_Text.setEditable(false);
        Min_adv_Text.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Recieved :");

        Bal_Amt_Text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Pay : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sum_total, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(20, 20, 20))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(48, 48, 48)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(given, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(remaining, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                        .addComponent(Bal_Amt_Text))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(min_Adv_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(300, 300, 300)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(m_jTendered, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                    .addComponent(m_jKeys, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(sum_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Bal_Amt_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(remaining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 21, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(given, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(46, 46, 46)
                        .addComponent(min_Adv_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jKeys, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(m_jTendered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        sum_total.setEditable(false);
        sum_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        remaining.setEditable(false);
        remaining.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        given.setEditable(false);
        given.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Bal_Amt_Text.setEditable(false);
        Bal_Amt_Text.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pay_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(mem_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(51, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(mem_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pay_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        date_txt.setEditable(false);
        mem_id_txt.setEditable(false);
        pay_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash2.png"))); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/ark216.png"))); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // dispose();
      //added by pratima
        
                              
                              if(BkGuestRoom.getExtendDateFlag()==1){
                               if((Double.parseDouble(Bal_Amt_Text.getText())+((given.getText().trim().length()<1)? Double.parseDouble("0.00") : Double.parseDouble(given.getText())))<Double.parseDouble(sum_total.getText())){
                                   BkGuestRoom.cancleCheckoutDateExtend();
                                   dispose();
                                   try
                                   {
                                       BkGuestRoom.reset();
                                   BkGuestRoom.loaddata();
                                   }
                                    catch (BasicException ex) {                    
                                            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                        ex.printStackTrace();
                                                       new MessageInf(ex).show(new JFrame());

                                            }
                      JOptionPane.showMessageDialog(jPanel1, "Canceled the request to extend checkout date .", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                              }
                                 else
          {
             
              dispose();
           }
                              }
       else
          {
              
            dispose();
           }
        //ended by pratima
    }//GEN-LAST:event_jButton1ActionPerformed
               
    
    
                 String reciept_No;   
                 Date CheckIn_Date;
                 String MemberName;
                 String Member_No;
                 Double Advance_Recv ;
                 Date Booking_Date;
                 int Cash_Flag;
                 int Check_flag;
                 String Check_no;
                 String Bank_name;
                 String Booking_ID;
                 String hall_ID;
                 String Room_ID;
                 String Payment_Ratio;
                 String GuestName;
                 String Contact_no;
                 Double Tax_Total;
                 String MemberID;
                 String NARRATION=null;
                 String ACCOUNTID;
                 String TRANSTYPE;
                 String TRANSREF;
                 String Booking_seq_no;
                 String TID;
                 String PaymentType;
                 String UserAccount=null;
                 String Hall_Name;
                 String Room_Type;
                 
                 
    private void pay_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_btnActionPerformed
          given.setText(""+(m_jTendered.getText()));
          if(given.getText()!=null && given.getText().trim().length()>0){ 
         
                 List<Object> Sequence_Detail = new ArrayList<Object>();
                 String role =LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
                  boolean flag = m_App.getAppUserView().getUser().hasPermission("Billing Perm");        
                 if(flag){
                 
                 Advance_Recv = Double.parseDouble(given.getText());
                 
                 try {
                         reciept_No = getNextBillID();
                     } catch (BasicException ex) {
                         Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                  if(reciept_No!=null && reciept_No.trim().length()>0 && reciept_No!="")
                 
                  {    
                 
                     if(hsi!=null){
                         
                        
                        CheckIn_Date = hsi.getBOOKING_DATE_EX();
                        MemberName = hsi.getMemberName();
                        Member_No = hsi.getMem_No();
                        Booking_ID = hsi.getId();
                        hall_ID = hsi.getHall_ID();
                        MemberID = hsi.getMEMBER_ID();
                        
                        Booking_seq_no = hsi.getBOOKING_SEQ_NO();
                        TRANSREF = getTransRef(m_App);
                        ACCOUNTID = getAdvance_Acct_Hall(m_App, hall_ID);
                        Hall_Name = hsi.gethall_name();
                        Booking_Date = hsi.getBOOKING_DATE_EX();
                        
                        NARRATION = "Being Advance Recieved for booking Id: "+Booking_seq_no+". For "+Hall_Name+", by :- M.No :"+Member_No+ " , Booked for : " +Booking_Date+".";
                        TRANSTYPE ="D";
                       
                        
                        
                        if(hsi.getMem_flag()==1){
                            GuestName="";
                            Contact_no="";
                            
                        }
                        else{
                            GuestName = hsi.getNON_MEM_NAME();
                            Contact_no=hsi.getNON_MEM_CONTCT();
                        }
                        
                        Payment_Ratio = hsi.getADVANCE_PERC();
                        int min_Advance_Paymnt = getMinAdvPaymnt(Payment_Ratio);
                        Double Minimum_amount = Double.parseDouble(decimalFormat.format((Final_Amount * min_Advance_Paymnt)/100));
                       
                        
                        if(Payment_Flag==0 ? (Minimum_amount <= Advance_Recv):true ){
                        try {
                            Customer = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(hsi.getMEMBER_ID());
                        } catch (BasicException ex) {
                            Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                       JPaymentSelect paymentdialog =Final_Amount >= 0.0
                                        ? paymentdialogreceipt
                                        : paymentdialogrefund;
                        if (paymentdialog.showDialog(Advance_Recv , Customer , m_App.getAppUserView().getUser().getName() , true)){
                            
                    
                        final List<PaymentInfo> paymentList = paymentdialog.getSelectedPayments();
                       
                      
                        
                        for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }
                         } 
                        
                       if(UserAccount!=null && UserAccount.trim().length()>0) {
                        
                       Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                                                              
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                        String ID = UUID.randomUUID().toString();    
                        int Active =1; 
                        String Payment_ref = reciept_No+"#"+Advance_Recv+"-";
                        
                       Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
                        
                        
                        TID = UUID.randomUUID().toString();
                        
                        
                        
                        if(Payment_Flag==0){
                        
                                int   insert_advance_pay =  new PreparedSentence(m_App.getSession()  , "INSERT INTO hall_advance_payment (ID, CHECK_IN_DATE, MEMBERNAME, MEMBER_NO, TOTAL_AMOUNT, ADVANCE_RECV,  CRBY, CRDATE, CRHOST, RECIEPT_NO, CASH_FLAG, CHEQUE_FLAG, CHEQUE_NO, BANK_NAME, HALLNAME , BOOKING_ID , GUESTNAME , CONTACT , BAL_AMT , BOOKING_SEQ_NO , ADVNCE_REF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                               , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP ,Datas.STRING, Datas.STRING ,Datas.DOUBLE ,Datas.DOUBLE  ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING ,Datas.INT ,Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING, Datas.STRING , Datas.STRING , Datas.DOUBLE,Datas.STRING , Datas.STRING})                         
                               ).exec(new Object[]{ID, CheckIn_Date ,MemberName ,Member_No  ,Final_Amount, Advance_Recv ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , reciept_No , Cash_Flag ,Check_flag, Check_no , Bank_name , hall_ID ,  Booking_ID , GuestName , Contact_no , Advance_Recv , Booking_seq_no ,Payment_ref });                                                                                                


                                
                              int   INSERT_ADVANCE_AGAINST_HALL  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO advnce_agnst_hall (ID, BOOKING_ID, ADVNCE_AMT, BAL_AMT,  CRBY, CRDATE, CRHOST, ACTIVE , CUSTOMER , RECIEPT_NO , HALLNAME) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                               , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.DOUBLE, Datas.DOUBLE  ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING})                         
                               ).exec(new Object[]{ID ,Booking_ID ,Advance_Recv , Advance_Recv , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active ,MemberID ,reciept_No , hall_ID});                                                                                                
                              
                              
                              
                             ///  UPDATE ACCOUNT ENTRIES........................................................................................................................................................................................................................................................ 
                              
                             
                            int   INSERT_INTO_ACCOUNT_1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , TRANSREF ,reciept_No ,Advance_Recv , Booking_Date , Advance_Recv , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , ACCOUNTID , TID , new Date() , "C" , 1    });                                                                                                

                            int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,ACCOUNTID ,AjPerDate});   

                                    
                            int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,ACCOUNTID});
                            
//                           int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
//                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
//                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,p.getTotal() , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                
//
//                              
//                              int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
//                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{p.getTotal() ,UserAccount ,AjPerDate});
//                                
//                                
//                              int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
//                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{p.getTotal(),UserAccount});
                      
                            //above queries kept in forloop below  by pratima :in case one receipt has two payments, 1cash & 1cheque then in accountjournal two row will entered with different accountid column. 
                              for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }
                            int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,p.getTotal() , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                

                              
                              int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{p.getTotal() ,UserAccount ,AjPerDate});
                                
                                
                              int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{p.getTotal(),UserAccount});
                        }
                              
                               
                               // FOR INSERTING INTO PAYMENTS , RECIEPTS , CHEQUE ....#aakash...
                                     
                                    payBill(paymentList , reciept_No);
                               
                               
                              
                           
                              
                        }
                       else{
                           if(Payment_Flag==1){ 
                            
                            
                                       int x = new StaticSentence(m_App.getSession() , "UPDATE hall_advance_payment SET ADVANCE_RECV=ADVANCE_RECV+?   WHERE BOOKING_ID=?"
                                                                   , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING}))
                                                                   .exec(new Object[] {Advance_Recv , Booking_ID});

                                        int x1 = new StaticSentence(m_App.getSession() , "UPDATE hall_advance_payment SET BAL_AMT=BAL_AMT+? WHERE BOOKING_ID=?"
                                                                   , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING}))
                                                                   .exec(new Object[] {Advance_Recv , Booking_ID});


                                        String Payment_ref_O = getPayment_REF_Hall(m_App, Booking_ID);
                                        String paymnt_ref_N = Payment_ref_O + Payment_ref;

                                        int x2 = new StaticSentence(m_App.getSession() , "UPDATE hall_advance_payment SET ADVNCE_REF = ? WHERE BOOKING_ID=?"
                                                                   , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING}))
                                                                   .exec(new Object[] {paymnt_ref_N , Booking_ID});


                            
                            
                                    int   INSERT_ADVANCE_AGAINST_HALL  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO advnce_agnst_hall (ID, BOOKING_ID, ADVNCE_AMT, BAL_AMT,  CRBY, CRDATE, CRHOST, ACTIVE , CUSTOMER , RECIEPT_NO , HALLNAME) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                                     , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.DOUBLE, Datas.DOUBLE  ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING})                         
                                     ).exec(new Object[]{ID ,Booking_ID ,Advance_Recv , Advance_Recv , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active ,MemberID ,reciept_No , hall_ID});                                                                                                

                            
                                 
                                    
                                    
                                    int   INSERT_INTO_ACCOUNT_1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , TRANSREF ,reciept_No ,Advance_Recv , Booking_Date , Advance_Recv , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , ACCOUNTID , TID , new Date() , "C" , 1    });                                                                                                

                                    
                                    
                                 int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,ACCOUNTID ,AjPerDate});   

                                    
                                int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,ACCOUNTID});

                            
//                              int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
//                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
//                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,Advance_Recv, Booking_Date , 0.00 , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                
//
//                              
//                               int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
//                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,UserAccount ,AjPerDate});
//                                
//                                
//                              int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
//                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,UserAccount});
//                                     
                                    
                            //above queries kept in forloop below  by pratima :in case one receipt has two payments, 1cash & 1cheque then in accountjournal two row will entered with different accountid column. 
                             for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }       
                               int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,p.getTotal(), Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                

                              
                               int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{p.getTotal() ,UserAccount ,AjPerDate});
                                
                                
                              int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{p.getTotal(),UserAccount});
                              
                              }    
                                    
                              
                                    // FOR INSERTING INTO PAYMENTS , RECIEPTS , CHEQUE ....#aakash...
                                     
                                    payBill(paymentList , reciept_No);
                                    
                                    
                                    
                                    
                            
                           }
                        }
                         
                        UpdateRSeries();  
                        UpdateHallPaymentStatus(Booking_ID);
                        
                          return null;                                      
                            }                            
                        };                 
                          
                              try {                 
                                t.execute();          
                                JOptionPane.showMessageDialog(this, "Advance Payment Done Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                 
                                PrintHallBill(hsi ,Final_Amount);
                                dispose();
                                
                                 // FOR MESSAGE SENDING.....
                                    String MobNo = dmang.getcustMobileNoByCustID(hsi.getMEMBER_ID());
                                    String Msg = "Dear Member,\nPayment of Rs."+Advance_Recv+"/- Recvd for bkng of "+hsi.gethall_name()+" Hall on "+hsi.getBOOKING_DATE()+".";
                                    System.out.println(Msg.length());
                                    if(MobNo!=null && MobNo.trim().length()>0){
                                        dmang.InsertActiveMsgTable(Msg, hsi.getMEMBER_ID() , MobNo, 2);
                                    }
                                
                                
                            }
                             catch (BasicException ex) { 
                                  ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                           }
                          
                              
                              
                              
                              
                       }
                       else{
                           JOptionPane.showMessageDialog(this, " Please set user account first..!! ","User Account", JOptionPane.ERROR_MESSAGE);
                       }
                              
                          dispose();
                            
                          
                          try {
                                BkHall.loaddata();
                            } catch (BasicException ex) {
                                Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          
                        }
                        
                        /*
                        
                         */
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Minimum Advance Amount Should be : "+Minimum_amount+" /-", " Advace Paymnt ", JOptionPane.ERROR_MESSAGE);
                             m_jTendered.reset();
                        }
                     }
                     
                     
                     if(rsi!=null){
                        CheckIn_Date = rsi.getBOOKED_DATE_EX();
                        MemberName = rsi.getMemberName();
                        Member_No = rsi.getMem_No(); 
                        MemberID = rsi.getMEMBER_ID();
                        Booking_ID = rsi.getId();
                        Room_ID = rsi.getROOMTYPE_ID();
                        Booking_Date = rsi.getBOOKED_DATE_EX();
                        TRANSREF = getTransRef(m_App);
                        ACCOUNTID = getAdvance_Acct_Room(m_App, Room_ID);
                        Booking_seq_no = rsi.getBOOKING_SEQ_NO();
                        Room_Type = rsi.getROOM_TYPE();
                        
                        
                        NARRATION = "Being Advance recieved for booking Id: "+Booking_seq_no+". For "+Room_Type+" by :- M.No :"+Member_No+ " , Booked for : " +Booking_Date+"." ;
                        TRANSTYPE ="C";
                       
                        
                        if(rsi.getMem_flag()==1){
                            GuestName="";
                            Contact_no="";
                            
                        }
                        else{
                            GuestName = rsi.getNON_MEM_NAME();
                            Contact_no=rsi.getNON_MEM_CONTCT();
                        }
                        
                        Payment_Ratio = rsi.getADVANCE_PERC();
                        int min_Advance_Paymnt;
                        if(Payment_Ratio!=null){
                             min_Advance_Paymnt = getMinAdvPaymnt(Payment_Ratio);
                        }
                        else{
                             min_Advance_Paymnt = 100;
                        }
                        
                        
                        Double Minimum_amount = Double.parseDouble(decimalFormat.format((Final_Amount * min_Advance_Paymnt)/100));
                       
                        if(Payment_Flag==0 ? (Minimum_amount <= Advance_Recv):true ){
                        try {
                            Customer = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(rsi.getMEMBER_ID());
                        } catch (BasicException ex) {
                            Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                         JPaymentSelect paymentdialog =Final_Amount >= 0.0
                                        ? paymentdialogreceipt
                                        : paymentdialogrefund;
                        if (paymentdialog.showDialog(Advance_Recv , Customer , m_App.getAppUserView().getUser().getName() , true)){
                            
                        final List<PaymentInfo> paymentList = paymentdialog.getSelectedPayments();
                      
                        
                        
                        for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }
                         }
                        
                        if(UserAccount!=null && UserAccount.trim().length()>0){
                        
                        
                        Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                                                              
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                         String ID = UUID.randomUUID().toString();   
                         int Active =1;    
                         String Payment_ref = reciept_No+"#"+Advance_Recv+"-";
                        
                        Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
                        
                        TID = UUID.randomUUID().toString();
                         
                         
                        if(Payment_Flag==0){
                             
                         
                         int   insert_advance_paymnt =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_advance_payment  (ID, CHECK_IN_DATE, ROOMTYPE, MEMBERNAME, MEMBERNO, TOTAL_AMOUNT, ADVANCE_RECV, RECIEPT_NO, CRBY, CRDATE, CRHOST, CASH_FLAG, CHEQUE_FLAG, CHEQUE_NO, BANK_NAME,BOOKING_ID , BOOKING_SEQ_NO , GUESTNAME , CONTACT , BAL_AMT , TAX_TOTAL,ADVNCE_REF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING ,  Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.DOUBLE ,Datas.DOUBLE , Datas.STRING ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING ,Datas.INT ,Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING ,Datas.STRING , Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING})                         
                        ).exec(new Object[]{ID, CheckIn_Date , Room_ID ,MemberName ,Member_No  ,Final_Amount, Advance_Recv ,reciept_No ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() ,  Cash_Flag ,Check_flag, Check_no , Bank_name , Booking_ID ,Booking_seq_no , GuestName , Contact_no , Advance_Recv , Tax_Total , Payment_ref});                                                                                                
                         
                         
                         int   INSERT_ADVANCE_AGAINST_ROOM  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO advnce_agnst_guestroom (ID, BOOKING_ID, ADVNCE_AMT, BAL_AMT,  CRBY, CRDATE, CRHOST, ACTIVE , CUSTOMER , RECIEPT_NO , ROOMTYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.DOUBLE, Datas.DOUBLE  ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING})                         
                        ).exec(new Object[]{ID ,Booking_ID ,Advance_Recv , Advance_Recv , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active ,MemberID ,reciept_No ,Room_ID });                                                                                                
                         
                        
                      
                         
                         
                         
                         
                         
                       int   INSERT_INTO_ACCOUNT_1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , TRANSREF ,reciept_No ,Advance_Recv , Booking_Date , Advance_Recv , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , ACCOUNTID , TID , new Date() , "C" , 1    });                                                                                                

                          
                         int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,ACCOUNTID ,AjPerDate});   

                                    
                          int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,ACCOUNTID});

//                             int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
//                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
//                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,Advance_Recv , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                
//
//                              
//                       
//                       
//                        int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
//                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,UserAccount ,AjPerDate});
//                                
//                                
//                        int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
//                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,UserAccount});
//                              
                                      
                   //above queries kept in forloop below  by pratima :in case one receipt has two payments, 1cash & 1cheque then in accountjournal two row will entered with different accountid column. 
                                
                     for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }   
                                    
                                    
                       int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,p.getTotal() , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                

                              
                       
                       
                        int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{p.getTotal() ,UserAccount ,AjPerDate});
                                
                                
                        int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{p.getTotal(),UserAccount});
                              
                                    
                       
                       
                     }
                           
                          // FOR INSERTING INTO PAYMENTS , RECIEPTS , CHEQUE ....#aakash...
                                     
                                    payBill(paymentList , reciept_No);
                         
                         
                         UpdateRSeries();   
                         UpdateGuestRoomPaymentStatus(Booking_ID);  
                         
                         }
                         else{
                             if(Payment_Flag==1){
                              //added by pratima
                           
                            if(BkGuestRoom.getExtendDateFlag()==1){     
                                                            
                                  if((Double.parseDouble(Bal_Amt_Text.getText())+Double.parseDouble(given.getText()))>=Double.parseDouble(sum_total.getText())){
                             ///////////////////////////////////////////
                                    int x = new StaticSentence(m_App.getSession() , "UPDATE guestroom_advance_payment SET ADVANCE_RECV=ADVANCE_RECV+?   WHERE BOOKING_ID=?"
                                                                 , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING}))
                                                                 .exec(new Object[] {Advance_Recv , Booking_ID});
                                    
                                    int UpdateoldCheckout  = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET ADV_RECV=ADV_RECV+? WHERE  advnce_recv_id in(SELECT ID from guestroom_advance_payment WHERE BOOKING_ID=?)  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING  })).exec
                                                                                   (new Object[]{Advance_Recv, Booking_ID });
                             
                                      int x1 = new StaticSentence(m_App.getSession() , "UPDATE guestroom_advance_payment SET BAL_AMT=BAL_AMT+? WHERE BOOKING_ID=?"
                                                                 , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING}))
                                                                 .exec(new Object[] {Advance_Recv , Booking_ID});
                             
                                     
                                      String Payment_ref_O = getPayment_REF_Room(m_App, Booking_ID);
                                      String paymnt_ref_N = Payment_ref_O + Payment_ref;
                                      
                                      int x2 = new StaticSentence(m_App.getSession() , "UPDATE guestroom_advance_payment SET ADVNCE_REF = ? WHERE BOOKING_ID=?"
                                                                 , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING}))
                                                                 .exec(new Object[] {paymnt_ref_N , Booking_ID});
                                      
                                    
                                      int   INSERT_ADVANCE_AGAINST_GUESTROOM  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO advnce_agnst_guestroom (ID, BOOKING_ID, ADVNCE_AMT, BAL_AMT,  CRBY, CRDATE, CRHOST, ACTIVE , CUSTOMER , RECIEPT_NO , ROOMTYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.DOUBLE, Datas.DOUBLE  ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING})                         
                                        ).exec(new Object[]{ID ,Booking_ID ,Advance_Recv , Advance_Recv , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active ,MemberID ,reciept_No ,Room_ID });                                                                                                

                                      
                              
                                      
                                 int   INSERT_INTO_ACCOUNT_1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , TRANSREF ,reciept_No ,Advance_Recv , Booking_Date , Advance_Recv , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , ACCOUNTID , TID , new Date() , "C" , 1    });                                                                                                

                                    
                                    
                                  int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,ACCOUNTID ,AjPerDate});   

                                    
                                 int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,ACCOUNTID});

                            
                                 
                                 
                                    
//                                int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
//                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
//                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,Advance_Recv , Booking_Date , 0.00 , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                
//
//
//                                int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
//                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,UserAccount ,AjPerDate});
//
//
//                                int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
//                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,UserAccount});

                       //above queries kept in forloop below  by pratima :in case one receipt has two payments, 1cash & 1cheque then in accountjournal two row will entered with different accountid column. 
                                     for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }
                                  int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,p.getTotal() , Booking_Date , 0.00 , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                


                                int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{p.getTotal(),UserAccount ,AjPerDate});


                                int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{p.getTotal(),UserAccount});
                                 }
                                
                                 // FOR INSERTING INTO PAYMENTS , RECIEPTS , CHEQUE ....#aakash...
                                     
                                    payBill(paymentList , reciept_No);
                            
                                      
                                      UpdateRSeries();   
                                      
                                  /////////////////////////////////////////////////////////////// 
                                  }//if-remaining-ended
                                  else{
                                        int submit_req = JOptionPane.showConfirmDialog(jPanel1, "Please do the full payment. Do you want to pay again ?? " , "Blocking Confirmation" , JOptionPane.YES_NO_OPTION);
                     if(submit_req == JOptionPane.YES_OPTION){
                                                dispose();
                                              Billpage bp =   new Billpage(rsi , 1);
                                      try {
                                          bp.showDialog();
                                      } catch (BasicException ex) {
                                          Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                    } else{
                         BkGuestRoom.cancleCheckoutDateExtend();
                      JOptionPane.showMessageDialog(jPanel1, "Canceled the request to extend checkout date .", "Success", JOptionPane.INFORMATION_MESSAGE);
                     dispose();  
                            
                         try {
                                BkGuestRoom.loaddata();
                            } catch (BasicException ex) {
                                 ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                     }
            
                                  }
                              }//if-cdatechangeflag-ended
                              else{
                                        ///////////////////////////////////////////
                                    int x = new StaticSentence(m_App.getSession() , "UPDATE guestroom_advance_payment SET ADVANCE_RECV=ADVANCE_RECV+?   WHERE BOOKING_ID=?"
                                                                 , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING}))
                                                                 .exec(new Object[] {Advance_Recv , Booking_ID});
                             
                                      int x1 = new StaticSentence(m_App.getSession() , "UPDATE guestroom_advance_payment SET BAL_AMT=BAL_AMT+? WHERE BOOKING_ID=?"
                                                                 , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING}))
                                                                 .exec(new Object[] {Advance_Recv , Booking_ID});
                             
                                     
                                      String Payment_ref_O = getPayment_REF_Room(m_App, Booking_ID);
                                      String paymnt_ref_N = Payment_ref_O + Payment_ref;
                                      
                                      int x2 = new StaticSentence(m_App.getSession() , "UPDATE guestroom_advance_payment SET ADVNCE_REF = ? WHERE BOOKING_ID=?"
                                                                 , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING}))
                                                                 .exec(new Object[] {paymnt_ref_N , Booking_ID});
                                      
                                    
                                      int   INSERT_ADVANCE_AGAINST_GUESTROOM  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO advnce_agnst_guestroom (ID, BOOKING_ID, ADVNCE_AMT, BAL_AMT,  CRBY, CRDATE, CRHOST, ACTIVE , CUSTOMER , RECIEPT_NO , ROOMTYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.DOUBLE, Datas.DOUBLE  ,Datas.STRING   , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING})                         
                                        ).exec(new Object[]{ID ,Booking_ID ,Advance_Recv , Advance_Recv , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active ,MemberID ,reciept_No ,Room_ID });                                                                                                

                                      
                              
                                      
                                 int   INSERT_INTO_ACCOUNT_1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , TRANSREF ,reciept_No ,Advance_Recv , Booking_Date , Advance_Recv , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , ACCOUNTID , TID , new Date() , "C" , 1    });                                                                                                

                                    
                                    
                                  int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv ,ACCOUNTID ,AjPerDate});   

                                    
                                 int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,ACCOUNTID});

                            
//                                  int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
//                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
//                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,Advance_Recv, Booking_Date , 0.00 , 0 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                
//
//
//                                int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
//                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Advance_Recv,UserAccount ,AjPerDate});
//
//
//                                int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
//                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Advance_Recv,UserAccount});

                                 //above queries kept in forloop below  by pratima :in case one receipt has two payments, 1cash & 1cheque then in accountjournal two row will entered with different accountid column. 
                            for (final PaymentInfo p : paymentList) {
                            if ("cheque".equals(p.getName())) {
                                 UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                             }
                            else{
                                 UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            }
                                    
                                int   INSERT_INTO_ACCOUNT_2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID, MEMID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() ,MemberID , new Date() , Member_No ,reciept_No ,p.getTotal(), Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount ,TID , new Date() , "D" , 1    });                                                                                                


                                int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{p.getTotal() ,UserAccount ,AjPerDate});


                                int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{p.getTotal(),UserAccount});


                                 }
                                
                                
                                 // FOR INSERTING INTO PAYMENTS , RECIEPTS , CHEQUE ....#aakash...
                                     
                                    payBill(paymentList , reciept_No);
                            
                                      
                                      UpdateRSeries();   
                                  /////////////////////////////////////////////////////////////// 
                              }
                             }//if-payment==1 ended
                         }//if-payment==0else ended
                         
                         
                         
                         return null;                                      
                            }                            
                        };                 
                          
                              try {                 
                                t.execute();          
                                JOptionPane.showMessageDialog(this, "Advance Payment Done Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                PrintRoomBill(rsi , Final_Amount);
                                
                                
                                // FOR SMS  SENDING.....
                                
                                
                                String MobNo = dmang.getcustMobileNoByCustID(rsi.getMEMBER_ID());
                                String Msg = "Dear Member,\nPayment of Rs."+Advance_Recv+"/- Recvd for bkng of "+rsi.getROOM_TYPE()+"Room on "+rsi.getBOOKING_DATE()+".";
                                System.out.println(Msg.length());
                                if(MobNo!=null && MobNo.trim().length()>0){
                                    dmang.InsertActiveMsgTable(Msg, rsi.getMEMBER_ID() , MobNo, 2);
                                }
            
                                
                                
                            }
                             catch (BasicException ex) {                    
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex); 
                              ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                
                                
                           } 
                           
                        }
                        else{
                             JOptionPane.showMessageDialog(this, " Select User Account First..!!", " Advace Paymnt ", JOptionPane.ERROR_MESSAGE);
                        }
                              
                         dispose();  
                            
                         try {
                                BkGuestRoom.loaddata();
                            } catch (BasicException ex) {
                                 ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                          }
                         
                       }
                        else{
                            JOptionPane.showMessageDialog(this, "Minimum Advance Amount Should be : "+Minimum_amount+" /-", " Advace Paymnt ", JOptionPane.ERROR_MESSAGE);
                             m_jTendered.reset();
                        }
                     }
                     
                  }
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                 }
                 else{
                      JOptionPane.showMessageDialog(this, "Sorry , you don't have permissions..!!", " luxuryTax", JOptionPane.ERROR_MESSAGE); 
                 }
          }
          else{
              JOptionPane.showMessageDialog(this, "Please enter amount..!", " luxuryTax", JOptionPane.ERROR_MESSAGE);
          }
      
    }//GEN-LAST:event_pay_btnActionPerformed

    private void m_jKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jKeysActionPerformed
      
    }//GEN-LAST:event_m_jKeysActionPerformed

    private void m_jTenderedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jTenderedKeyReleased
           
                
    }//GEN-LAST:event_m_jTenderedKeyReleased
  
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
       Double x = Double.parseDouble(m_jTendered.getText());
       Double y = Double.parseDouble(sum_total.getText());
       Double z = Double.parseDouble(Bal_Amt_Text.getText());
            if(x<=y-z){
                Double temp = Double.parseDouble(decimalFormat.format(y-x-z));
               remaining.setText(decimalFormat.format(temp));
            }
            else{
                remaining.setText("0.00");
        }
        given.setText(decimalFormat.format(x));
            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Billpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Billpage dialog = new Billpage(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bal_Amt_Text;
    private javax.swing.JTextField Min_adv_Text;
    private javax.swing.JTextField date_txt;
    private javax.swing.JTextField given;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.openbravo.editor.JEditorKeys m_jKeys;
    private com.openbravo.editor.JEditorCurrencyPositive m_jTendered;
    private javax.swing.JTextField mem_id_txt;
    private javax.swing.JPanel min_Adv_Panel;
    private javax.swing.JButton pay_btn;
    private javax.swing.JTextField remaining;
    private javax.swing.JTextField sum_total;
    // End of variables declaration//GEN-END:variables

    private void init() throws BasicException {
        
     
     m_App = LookupUtilityImpl.getInstance(null).getAppView();
        
     paymentdialogreceipt = JPaymentSelectReceipt.getDialog(this);
     AppView app = LookupUtilityImpl.getInstance(null).getAppView();
     paymentdialogreceipt.init(app);
     m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
     BkHall = (BookHall) m_App.getBean("com.openbravo.pos.Booking.BookHall");
     BkGuestRoom = (BookGuestRoom) m_App.getBean("com.openbravo.pos.Booking.BookGuestRoom");
     dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
      
     
     if(rsi!=null){   
      BillModel = new BillTableModel(m_App);
      custId = rsi.getMEMBER_ID();
      BillModel.setRsi(rsi);
      Final_Amount = Double.parseDouble(decimalFormat.format(BillModel.getTotAmt_Room(rsi)));
      jTable1.setModel(BillModel.getTableModel());
      perf_opration2(rsi);
      
      Double rate = rsi.getCHARGES();
      int No_of_Days = rsi.getNO_OF_DAYS();
      int no_of_Booked_rooms = rsi.getNO_OF_ROOMS_BOOKED();
      Tax_Total = Final_Amount-(rate*No_of_Days*no_of_Booked_rooms);
      
      sum_total.setText(decimalFormat.format(Final_Amount));
      
      
      Payment_Ratio = rsi.getADVANCE_PERC();
      int min_Advance_Paymnt = getMinAdvPaymnt(Payment_Ratio);
      Double Minimum_amount = Double.parseDouble(decimalFormat.format((Final_Amount * min_Advance_Paymnt)/100));
         
      Min_adv_Text.setText(decimalFormat.format(Minimum_amount));
      remaining.setText(decimalFormat.format(Final_Amount));
    
       
      
      if(Payment_Flag==0){
            min_Adv_Panel.setVisible(true);
            Bal_Amt_Text.setText("0.00");
        }
        else{
           min_Adv_Panel.setVisible(false);
           BalAmt = getAdvancePaidAmtByBookingId_Room(rsi.getId());
           Bal_Amt_Text.setText(decimalFormat.format(BalAmt)); 
          
          
        }
        
        BalAmt = Double.parseDouble(Bal_Amt_Text.getText());
        Double t = (Final_Amount-BalAmt);
        if(t>0.00){
            remaining.setText(decimalFormat.format(Final_Amount-BalAmt)); 
        }
        else{
            remaining.setText("0.00"); 
        }
     
     
     
     }
    
     
     
      if(hsi!=null){
      custId = hsi.getMEMBER_ID();
      
      BillModel = new BillTableModel(m_App);
      BillModel.setHsi(hsi);
      Final_Amount = Double.parseDouble(decimalFormat.format(BillModel.getTotAmt_Hall(hsi)));
      jTable1.setModel(BillModel.getTableModel2());
      perf_opration(hsi);  
      sum_total.setText(decimalFormat.format(Final_Amount));
       
      
      Payment_Ratio = hsi.getADVANCE_PERC();
      int min_Advance_Paymnt = getMinAdvPaymnt(Payment_Ratio);
      Double Minimum_amount = Double.parseDouble(decimalFormat.format((Final_Amount * min_Advance_Paymnt)/100));
         
      Min_adv_Text.setText(decimalFormat.format(Minimum_amount));
     
      
       if(Payment_Flag==0){
            min_Adv_Panel.setVisible(true);
            Bal_Amt_Text.setText("0.00");
        }
        else{
            min_Adv_Panel.setVisible(false);
            BalAmt = getAdvancePaidAmtByBookingId_Hall(hsi.getId());
            Bal_Amt_Text.setText(decimalFormat.format(BalAmt));
            
        }
        
        BalAmt = Double.parseDouble(Bal_Amt_Text.getText());
        Double t = (Final_Amount-BalAmt);
        if(t > 0.00){
            remaining.setText(decimalFormat.format(Final_Amount-BalAmt)); 
        }
        else{
            remaining.setText("0.00"); 
        }
      
     }
    }
    
    
     public void perf_opration(BookedHallStatusTableModel.HallStatusInfo hsi){
        date_txt.setText(Formats.DATE.formatValue(CurrentDate));
        mem_id_txt.setText(hsi.getMem_No());
        
    }
    public void perf_opration2(BookedRoomStatusTableModel.Room_StatusInfo rsi){
        date_txt.setText(Formats.DATE.formatValue(CurrentDate));
        mem_id_txt.setText(rsi.getMem_No());
        
    }
    
   private String getNextBillID() throws BasicException{
       //akash:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
     Object[] obj=(Object[])new  StaticSentence(m_App.getSession()
            , "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=? AND ACTIVE=TRUE"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING})
            ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.DOUBLE})).find(new Object[]{uname,uname});
     if(obj!=null){
         Double max=Double.parseDouble(obj[1].toString());
         max++;
         billnum=obj[0].toString()+ max.intValue();
         return billnum;   
        }
     else{
          JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.WARNING_MESSAGE);
         return "";
     }
       
     }
     
     
       public void UpdateRSeries() throws BasicException{ 
         
        String billnum;
        String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
    
           int x = new StaticSentence(m_App.getSession()
                       // , "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME =(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) "
                  , "UPDATE SEQUENCEDETAIL SET RMAX=RMAX+1 WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=?"

                  , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING }))
                        .exec(new Object[] {uname,uname});
         
    }
    
     // UPDATE ROOM BOOKING DETAILS AFTER ADVANCE PAYMENT  
     public void UpdateHallPaymentStatus(String BookingHallID) throws BasicException{ 
         
      int x = new StaticSentence(m_App.getSession()
                      , "UPDATE hall_booked_details SET STATUS=2 , PAYMENT_FLAG=1 WHERE ID=?"
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING }))
                        .exec(new Object[] { BookingHallID });
         
    }    
     
    // UPDATE GUEST ROOM BOOKING DETAILS AFTER ADVANCE PAYMENT : AAKASH
     public void UpdateGuestRoomPaymentStatus(String BookingRoomID) throws BasicException{ 
         
       int x = new StaticSentence(m_App.getSession()
                      , "UPDATE guestroom_booked_details SET STATUS=2 , PAYMENT_FLAG=1 WHERE ID=?"
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING }))
                        .exec(new Object[] { BookingRoomID });
         
    }
     
    public int getMinAdvPaymnt(String Payment_Ratio){
        String[] temp = Payment_Ratio.split("-");
        int adv = Integer.parseInt(temp[0]);
        return adv;
    }
    
    
    
    
    public void PrintRoomBill(BookedRoomStatusTableModel.Room_StatusInfo rsi , Double Final_Amount){
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            Calendar call = Calendar.getInstance();
            call.setTimeInMillis(rsi.getBOOKED_DATE_EX().getTime());
            call.add(Calendar.DATE,rsi.getNO_OF_DAYS() );
            Date Check_Out = call.getTime();

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            
            
            script.put("maintitle", "ADVANCE BOOKING RECIEPT");
            script.put("cname", rsi.getMemberName());
            script.put("cno", rsi.getMem_No());
            script.put("date", Formats.TIMESTAMP.formatValue(new Date())); 
            script.put("receipt", reciept_No);
            script.put("Reciept_title", "Receipt No :");
            
            script.put("text1", rsi.getROOM_TYPE());
            script.put("text2", rsi.getBOOKING_DATE());
            script.put("text3", Formats.DATE.formatValue(Check_Out));
            script.put("text4", rsi.getNO_OF_ROOMS_BOOKED());
            script.put("text5", rsi.getNO_OF_DAYS());
            script.put("text6", decimalFormat.format(Final_Amount) );
            script.put("text7", decimalFormat.format(Advance_Recv));
            script.put("text8", "");
            script.put("text9", rsi.getBOOKING_SEQ_NO());
             
            script.put("Gname", rsi.getNON_MEM_NAME());  
            
            script.put("label_1", "Room Type :");
            script.put("label_2", "Check In Date :");
            script.put("label_3", "Check Out Date :");
            script.put("label_4", "No Of Rooms Booked :");
            script.put("label_5", "No Of Days :");
            script.put("label_6", "Total Amount :");
            script.put("label_7", "Advance Recv :");
            script.put("label_8", "");
            script.put("label_9", "Booking ID : ");
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            
            
            
            
            
            
            
            
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
    }
    
    
    
     public void PrintHallBill(BookedHallStatusTableModel.HallStatusInfo hsi , Double Final_Amount){
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            
            

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", hsi.getMemberName());
            script.put("cno", hsi.getMem_No());
            script.put("date", Formats.TIMESTAMP.formatValue(new Date())); 
            script.put("receipt", reciept_No);
            script.put("Reciept_title", "Receipt No : ");
            
            
            script.put("maintitle", "Advance Booking Reciept");
            script.put("label_1", "Hall Name :");
            script.put("label_2", "Booking Date :");
            script.put("label_3", "Slot Booked :");
            script.put("label_4", "Slot Timings :");
            script.put("label_5", "");
            script.put("label_6", "Total Amount :");
            script.put("label_7", "Advance Recv :");
            script.put("label_8", "");
            script.put("label_9", "Booking Id :");
            
            
            
            script.put("text1", hsi.gethall_name());
            script.put("text2", hsi.getBOOKING_DATE());
            if(hsi.getSLOT_FLAG()==1){
               script.put("text3", "Hourly Booked"); 
            }
            else if(hsi.getSLOT_FLAG() == 2){
                script.put("text3","Half Day");
            }
            else{
                script.put("text3", "Full Day");
            }
           
            script.put("text4", hsi.getTIMING_SLOTS());
            script.put("text5", "");
            script.put("text6", decimalFormat.format(Final_Amount));
            script.put("text7", decimalFormat.format(Advance_Recv));
            script.put("text8", "");
            script.put("text9", hsi.getBOOKING_SEQ_NO());
            script.put("Gname", hsi.getNON_MEM_NAME());
            
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            
            
            
            
            
           
           
            
            
            
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
    }
     
     
     // GET PAYMENT REFERENCE FOR ADVANCE PAYMENT for room #aakash
       public String getPayment_REF_Room(AppView app , String Booking_ID){
         Object note = null;
        
         try {
            note  = new StaticSentence(app.getSession(), "SELECT  ADVNCE_REF FROM guestroom_advance_payment WHERE BOOKING_ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_ID);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(note!=null){
             String x = note.toString();
             return x;
         }
         else{
             return "";
         }
        
      }
       
       
        // GET PAYMENT REFERENCE FOR ADVANCE PAYMENT for HALL  #AAKASH
       public String getPayment_REF_Hall(AppView app , String Booking_ID){
         Object note = null;
        
         try {
            note  = new StaticSentence(app.getSession(), "SELECT  ADVNCE_REF FROM hall_advance_payment WHERE BOOKING_ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_ID);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(note!=null){
             String x = note.toString();
             return x;
         }
         else{
             return "";
         }
        
      }
       
       
       
        // GET TRANS rEF FOR ACCOUNT JOURNAL
       public String getTransRef(AppView app){
         Object Transref = null;
        
         try {
            Transref  = new StaticSentence(app.getSession(), "SELECT ID FROM FACILITY WHERE NAME='Room & Hall Bk'", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(Transref!=null){
             String x = Transref.toString();
             return x;
         }
         else{
             return "";
         }
        
      } 
       
       // GET ACCOUNT ID FOR ROOM BOOKING TYPES
       public String getAdvance_Acct_Room(AppView app , String RoomType){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT ADVNCE_ACCT FROM guestroom_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(RoomType);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }  
       
     
       
       
        // GET ACCOUNT ID FOR ROOM HALL TYPES
       public String getAdvance_Acct_Hall(AppView app , String Hall_ID){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT ADVNCE_ACCT FROM hall_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Hall_ID);
          } 
         catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }   
       
       
      
      public void payBill(List<PaymentInfo> paymentList , final String reciept_no ) throws BasicException{
              
          
          
          
          int   INSERT_INTO_RECIEPT =  new PreparedSentence(m_App.getSession()  , "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)"                          
                               , new SerializerWriteBasic(new Datas[]{  Datas.STRING , Datas.TIMESTAMP , Datas.STRING})                         
                               ).exec(new Object[]{  reciept_no , new Date() ,  m_App.getAppUserView().getUser().getName()  });                                                                                                
                              
                               
                       SentenceExec paymentinsert = new PreparedSentence(m_App.getSession() , "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                                for (final PaymentInfo p : paymentList) {
                                    paymentinsert.exec(new DataParams() {

                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2,reciept_no);
                                            setString(3, p.getName());
                                            setDouble(4, p.getTotal());
                                            setString(5,  m_App.getAppUserView().getUser().getName() );
                                            setTimestamp(6, new Date());
                                            setString(7,MemberID);
                                        }
                                    });

                          
                                            if ("cheque".equals(p.getName())) {
                                                SentenceExec chequeInsert = new PreparedSentence(m_App.getSession() , "INSERT INTO CHEQUE(ID, CHEQUENO, BANK, RNO,HOLDER,AMOUNT) VALUES (?, ?, ?, ?,?,?)", SerializerWriteParams.INSTANCE);
                                                final ChequeDetails cd = p.getChequeDetails();
                                                chequeInsert.exec(new DataParams() {

                                                    @Override
                                                    public void writeValues() throws BasicException {
                                                        setString(1, UUID.randomUUID().toString());
                                                        setString(2, cd.getChequeno());
                                                        setString(3, cd.getBank());
                                                        setString(4,reciept_no);
                                                        setString(5, cd.getholder());
                                                        setDouble(6, cd.getAmount());
                                                    }
                                                });
                                                List<BankInfo> temp = m_dlSales.getBankList().list();
                                                boolean result = false;
                                                for (BankInfo b : temp) {
                                                    if ((cd.getBank().equals(b.getName()))) {
                                                        result = true;
                                                        break;
                                                    }
                                                }
                                                if (!result) {
                                                    SentenceExec bankInsert = new PreparedSentence(m_App.getSession(), "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                                    bankInsert.exec(new DataParams() {

                                                        public void writeValues() throws BasicException {
                                                            setString(1, UUID.randomUUID().toString());
                                                            setString(2, cd.getBank());
                                                        }
                                                    });
                                                }
                                            }
                                        }
      
      
      
      
      
      }
       
       // -----------------------------------------------------------------------------------------------------------------------------------------------
      
      public Double getAdvancePaidAmtByBookingId_Hall(String Bookingid) throws BasicException{
        Double AdvAmt = 0.00;
        Object o = null;
       
        o=new PreparedSentence(m_App.getSession(), "SELECT BAL_AMT FROM hall_advance_payment  WHERE BOOKING_ID=?", SerializerWriteString.INSTANCE , SerializerReadDouble.INSTANCE).find(Bookingid);
        if(o!=null){
            AdvAmt = Double.parseDouble(o.toString());
        }
        
        return AdvAmt;  
          
          
      }
      
      public Double getAdvancePaidAmtByBookingId_Room(String Bookingid) throws BasicException{
        Double AdvAmt = 0.00;
        Object o = null;
       
        o=new PreparedSentence(m_App.getSession(), "SELECT BAL_AMT FROM guestroom_advance_payment  WHERE BOOKING_ID=?", SerializerWriteString.INSTANCE , SerializerReadDouble.INSTANCE).find(Bookingid);
        
        if(o!=null){
            AdvAmt = Double.parseDouble(o.toString());
        }
        
        return AdvAmt;  
          
          
      }
      
   
       
      
}
