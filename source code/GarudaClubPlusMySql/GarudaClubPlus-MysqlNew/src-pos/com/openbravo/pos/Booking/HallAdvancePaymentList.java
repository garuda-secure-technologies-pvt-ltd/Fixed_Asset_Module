
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.AdvanceRecieveTableModel.HallAdvInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class HallAdvancePaymentList extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private AdvanceRecieveTableModel AdvanceRecv;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    private TicketParser m_TTP;
     
    public HallAdvancePaymentList() {
        initComponents();
        main_panel.setVisible(true);
        detail_panel.setVisible(false);
        cancel_panel.setVisible(false);
        
     }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        Refund_Buttn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        detail_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        totamt = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        payment_recv = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        due_amt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        HALL_NAME = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        check_in_date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        slot = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        memname = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        nonmemname = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mem_no = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        cancel_panel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        sub = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        totamt1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        payment_recv1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        due_amt1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        HALL_NAME1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        check_in_date1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        slot1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        memname1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        contact1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        nonmemname1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        mem_no1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cancel_reason_text = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        totamt2 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        totamt3 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        due_amt2 = new javax.swing.JLabel();
        submit_c = new javax.swing.JButton();
        cancel_c = new javax.swing.JButton();

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

        jButton1.setText("View Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Refund_Buttn.setText("Refund Balance Amount ");
        Refund_Buttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refund_ButtnActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel Booking");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(Refund_Buttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Refund_Buttn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/viewmag.png"))); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton3.setVisible(false);

        jTabbedPane1.addTab("Advance Recieved ", jPanel1);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Hall Booked Details");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("(Booked By :-)");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total Amount :");

        totamt.setText("jLabel8");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Payment Recieved :");

        payment_recv.setText("jLabel8");

        jLabel13.setText("-------------------------------------------------------");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Due Amount :");

        due_amt.setText("jLabel8");

        jLabel8.setText(null);

        jLabel9.setText(null);

        jLabel10.setText(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(payment_recv))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totamt)))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(due_amt)
                        .addGap(24, 24, 24))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totamt)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(payment_recv)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(due_amt)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        totamt.setForeground(Color.RED);
        payment_recv.setForeground(Color.RED);
        due_amt.setForeground(Color.RED);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        HALL_NAME.setText("jLabel8");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Hall Name :");

        check_in_date.setText("jLabel8");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Check In Date : ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Slot Booked : ");

        slot.setText("jLabel12");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HALL_NAME))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_in_date))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slot)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HALL_NAME)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(check_in_date))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(slot))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        memname.setText("jLabel14");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Member Name :");

        contact.setText("jLabel16");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Contact No : ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Guest Name :");

        nonmemname.setText("jLabel10");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Member No :");

        mem_no.setText("jLabel8");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memname))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mem_no))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contact)
                            .addComponent(nonmemname))))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memname)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(mem_no))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(nonmemname))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contact)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(75, 75, 75))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jLabel6.setForeground(Color.BLUE);

        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detail_panelLayout = new javax.swing.GroupLayout(detail_panel);
        detail_panel.setLayout(detail_panelLayout);
        detail_panelLayout.setHorizontalGroup(
            detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_panelLayout.createSequentialGroup()
                .addGroup(detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(detail_panelLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(detail_panelLayout.createSequentialGroup()
                            .addGap(341, 341, 341)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detail_panelLayout.setVerticalGroup(
            detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/gohome.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("Booking Cancellation Menu");

        sub.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("(Booked By :-)");

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Total Amount :");

        totamt1.setText("jLabel8");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Payment Recieved :");

        payment_recv1.setText("jLabel8");

        jLabel21.setText("-------------------------------------------------------");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Due Amount :");

        due_amt1.setText("jLabel8");

        jLabel23.setText(null);

        jLabel24.setText(null);

        jLabel25.setText(null);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(payment_recv1))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totamt1)))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(due_amt1)
                        .addGap(24, 24, 24))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(totamt1)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(payment_recv1)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(due_amt1)
                    .addComponent(jLabel25))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        totamt.setForeground(Color.RED);
        payment_recv.setForeground(Color.RED);
        due_amt.setForeground(Color.RED);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        HALL_NAME1.setText("jLabel8");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Hall Name :");

        check_in_date1.setText("jLabel8");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Check In Date : ");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Slot Booked : ");

        slot1.setText("jLabel12");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HALL_NAME1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_in_date1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slot1)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HALL_NAME1)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(check_in_date1))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(slot1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        memname1.setText("jLabel14");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Member Name :");

        contact1.setText("jLabel16");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Contact No : ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Guest Name :");

        nonmemname1.setText("jLabel10");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Member No :");

        mem_no1.setText("jLabel8");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memname1))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mem_no1))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contact1)
                            .addComponent(nonmemname1))))
                .addContainerGap(286, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memname1)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(mem_no1))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(nonmemname1))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contact1)
                    .addComponent(jLabel30))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Cancellation Reason : ");

        cancel_reason_text.setColumns(20);
        cancel_reason_text.setRows(5);
        cancel_reason_text.setText("Enter Cancellation Reason\nhere....");
        cancel_reason_text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancel_reason_textMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(cancel_reason_text);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Cancellation Charge :");

        jLabel35.setText(null);

        totamt2.setText("jLabel8");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Payment Recieved :");

        jLabel37.setText(null);

        totamt3.setText("jLabel8");

        jLabel38.setText("----------------------------------------------------------------");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Due Amount :");

        jLabel40.setText(null);

        due_amt2.setText("jLabel8");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel34))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totamt3))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totamt2))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(due_amt2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(totamt3))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(totamt2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(due_amt2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        totamt.setForeground(Color.RED);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        totamt.setForeground(Color.RED);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        due_amt.setForeground(Color.RED);

        javax.swing.GroupLayout subLayout = new javax.swing.GroupLayout(sub);
        sub.setLayout(subLayout);
        subLayout.setHorizontalGroup(
            subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addGroup(subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(subLayout.createSequentialGroup()
                            .addGroup(subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(subLayout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subLayout.createSequentialGroup()
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(149, 149, 149)))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(subLayout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        subLayout.setVerticalGroup(
            subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel6.setForeground(Color.BLUE);

        submit_c.setText("Submit");
        submit_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_cActionPerformed(evt);
            }
        });

        cancel_c.setText("Cancel");
        cancel_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_cActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cancel_panelLayout = new javax.swing.GroupLayout(cancel_panel);
        cancel_panel.setLayout(cancel_panelLayout);
        cancel_panelLayout.setHorizontalGroup(
            cancel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancel_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(314, 314, 314))
            .addGroup(cancel_panelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(cancel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cancel_panelLayout.createSequentialGroup()
                        .addComponent(cancel_c, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submit_c, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cancel_panelLayout.setVerticalGroup(
            cancel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancel_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cancel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit_c)
                    .addComponent(cancel_c))
                .addGap(35, 35, 35))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/block.png"))); // NOI18N
        jLabel12.setForeground(Color.BLUE);
        submit_c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        cancel_c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cancel_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detail_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancel_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
           int row = jTable1.getSelectedRow(); 
           HallAdvInfo showData = AdvanceRecv.getHallList().get(row);
           
           String ID = showData.getBOOKING_ID();
           HALL_NAME.setText(showData.getHALLNAME());
           check_in_date.setText(showData.getCHECK_IN_DATE());
           memname.setText(showData.getMEMBERNAME());
           totamt.setText(decimalFormat.format(showData.getTOTAL_AMOUNT()));
           payment_recv.setText(decimalFormat.format(showData.getBAL_AMT()));
           due_amt.setText(decimalFormat.format((showData.getTOTAL_AMOUNT())-(showData.getBAL_AMT())));
           slot.setText(showData.getBOOKING_SLOT());
           mem_no.setText(showData.getMEMBER_NO());
           
           List<Object> Guest_list = new ArrayList<Object>();
           
           if(showData.getMEMBER_FLAG()!=1){
              Guest_list =   AdvanceRecv.getHall_Guest_Details(m_App, ID);
              jLabel6.setText("(Booked By :- Guest)");
           }
           else{
               jLabel6.setText("(Booked By :- Member)");
           }
           
          if(Guest_list.size()!=0){
             
              String x = Guest_list.get(0).toString();
              nonmemname.setText(Guest_list.get(0).toString());
              contact.setText(Guest_list.get(1).toString());
          }
          else{
              nonmemname.setText("N/A");
              contact.setText("");
           }
          
          
          main_panel.setVisible(false);
          detail_panel.setVisible(true);
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       main_panel.setVisible(true);
       detail_panel.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
         int cnl_req = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Cancel Booking ?? ");
         if(cnl_req == JOptionPane.YES_OPTION){  
           int row = jTable1.getSelectedRow(); 
           HallAdvInfo showData = AdvanceRecv.getHallList().get(row);
           
           String ID = showData.getBOOKING_ID();
           HALL_NAME1.setText(showData.getHALLNAME());
           check_in_date1.setText(showData.getCHECK_IN_DATE());
           slot1.setText(showData.getBOOKING_SLOT());
           memname1.setText(showData.getMEMBERNAME());
           mem_no1.setText(showData.getMEMBER_NO());
           int x = showData.getMEMBER_FLAG();
           if(x==1){
               jLabel31.setVisible(false);
               nonmemname1.setVisible(false);
               contact1.setVisible(false);
               jLabel30.setVisible(false);
           }
           else{
               jLabel31.setVisible(true);
               nonmemname1.setVisible(true);
               nonmemname1.setText(showData.getGuestName());
               contact1.setText(showData.getContact());
               
               contact1.setVisible(true);
               jLabel30.setVisible(true);
           }
           totamt1.setText(""+showData.getTOTAL_AMOUNT());
           payment_recv1.setText(""+showData.getBAL_AMT());
           
           due_amt1.setText(""+((showData.getTOTAL_AMOUNT())-(showData.getBAL_AMT())));
           totamt3.setText(""+showData.getTOTAL_AMOUNT());
           //CalculateCancellation_charge();
           cancel_panel.setVisible(true);
           main_panel.setVisible(false);
           
           
           
         }
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void submit_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_cActionPerformed
        if(jTable1.getSelectedRow()!=-1){
           int cnl_req = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Cancel Booking ?? ");
           if(cnl_req == JOptionPane.YES_OPTION){
            
               
               
               
           } 
        }
    }//GEN-LAST:event_submit_cActionPerformed

    private void cancel_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_cActionPerformed
       cancel_panel.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_cancel_cActionPerformed

    private void cancel_reason_textMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel_reason_textMousePressed
        
    }//GEN-LAST:event_cancel_reason_textMousePressed

     String Rfd_Voucher_No;
    Double AdvanceRecvd;
    Double BalAmt;
    Double TotalCharge;
    
    String Booking_SeqNo; 
    String CustName;  
    String AdvanceID;
    String MemberNo ;
    String CustomerID;
    String HallName;
    String TRANSREF;
    String NARRATION;
    String UserAccount;
    String Advance_Acct_ID;
    String HallID;
    String CheckInDate ;
    String SlotBooked;
    String AdvancePayDate;
    
    
    private void Refund_ButtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refund_ButtnActionPerformed
         if(jTable1.getSelectedRow()!=-1){
            int row = jTable1.getSelectedRow(); 
            HallAdvInfo showData = AdvanceRecv.getHallList().get(row);
            
            String ID = showData.getBOOKING_ID();
            
            Rfd_Voucher_No = "";
            
            AdvanceRecvd = showData.getADVANCE_RECV();
            BalAmt = showData.getBAL_AMT();
            TotalCharge = showData.getTOTAL_AMOUNT();
            
            
            Booking_SeqNo = showData.getBooking_SEQ_no();
            CustName = showData.getMEMBERNAME();
            
            AdvanceID = showData.getId();
            MemberNo = showData.getMEMBER_NO();
            HallName = showData.getHALLNAME();
            CustomerID = showData.getCustomerID();
            UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
            HallID = showData.getHallId();
            Advance_Acct_ID = getAdvance_Acct_Hall(m_App, HallID);
            CheckInDate = showData.getCHECK_IN_DATE();
           // CheckOutDate = showData.getCHECK_OUT_DATE();
            SlotBooked = showData.getBOOKING_SLOT();
            AdvancePayDate = showData.getADVANCE_PMT_DT();
            
            
            
           if(BalAmt>0){
            
           int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to refund  "+BalAmt+"/- to  \n  "+showData.getMEMBER_NO()+"-"+showData.getMEMBERNAME()+". ??    \n  " , "Refund Menu" , JOptionPane.YES_NO_OPTION);
            if(bill == JOptionPane.YES_OPTION)
           {   
               
               
               Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {  
                    String TID = UUID.randomUUID().toString(); 
                    Rfd_Voucher_No = getNextVoucherNo();
             
                        Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
               
               
              
                            Double Refund_Amt = BalAmt;

                            int Refund_Voucher =  new PreparedSentence(m_App.getSession(), "INSERT INTO room_hall_refund_voucher (ID , RV_NO , CUST_ID , MEM_NO , BOOKING_SEQ_NO ,BILLED_AMT , ADVNCE_AMT , REFUND_AMT , CHK_IN_ID , REFUND_BY  , REFUND_HOST ,  REFUND_DATE , ROOMTYPE ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                 , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING })).exec
                                                (new Object[]{UUID.randomUUID().toString() ,Rfd_Voucher_No ,  CustomerID , MemberNo , Booking_SeqNo ,TotalCharge , AdvanceRecvd ,Refund_Amt , AdvanceID , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() , new Date()  , HallName }); 

                               
                            
                             int x3 = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET  BAL_AMT=BAL_AMT-? WHERE ID = ? "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {Refund_Amt ,  AdvanceID});
                             
                             
                            
                            
                            
                            

                              TRANSREF = MemberNo + " , Refund Voucher No : "+Rfd_Voucher_No+" # amt :"+Refund_Amt+" /- for Hall  againts booking no: "+Booking_SeqNo ;     
                              NARRATION = "Refund of "+Refund_Amt+ " /- to member : "+MemberNo+" . Voucher no "+Rfd_Voucher_No+ " for Hall  againts booking no: "+Booking_SeqNo;




                            int  INSERT_INTO_ACCOUNT5  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                              , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                              ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_SeqNo ,Refund_Amt , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount , TID , new Date() , "C" , 1    });                                                                                                



                            int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                               , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Refund_Amt ,UserAccount ,AjPerDate});   


                            int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                                  , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Refund_Amt,UserAccount});


                            
                         //  DEBIT ADVANCE ACCOUNT.....................................................................................................................   
                            
                            
                               
                         int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                        ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_SeqNo ,Refund_Amt , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                         TRANSREF = CustName+","+MemberNo+", Guest Room Refund Amount :"+ Refund_Amt+" /-. Advance paid ="+AdvanceRecvd+"/-."  ;
                        
                         int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Refund_Amt ,Advance_Acct_ID ,AjPerDate});


                         int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Refund_Amt,Advance_Acct_ID});

                           
                            
                         //   for(int i=0;i<AdvanceReciept_list.size();i++){

                          //  String x = AdvanceReciept_list.get(0).toString();

                           // int updateadvnce_agnst_guestroom   = new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET ADVNCE_ADJUST=(ADVNCE_ADJUST+?) , BAL_AMT=(BAL_AMT-?)  where RECIEPT_NO=?  "
                          //                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.DOUBLE , Datas.STRING})).exec(new Object[]{Refund_Amt, Refund_Amt , x });


                          //  }


                           // generateRefundVoucher();

                          
               
                  return null;
                      }
                   };
                   
                    try {
                            t.execute();
                            JOptionPane.showMessageDialog(this, " Amount Refunded Successfully.!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                             PrintRefundReciept();
                             loaddata();
                                    
                    } catch (BasicException ex) {
                            Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                            new MessageInf(ex).show(new JFrame());
                        }
               
               
               
               
               
           } 
           }
           else{
                 JOptionPane.showMessageDialog(this, "Balance Amount is already zero..!", "Warning", JOptionPane.WARNING_MESSAGE); 
           }
            
            
      }
    }//GEN-LAST:event_Refund_ButtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HALL_NAME;
    private javax.swing.JLabel HALL_NAME1;
    private javax.swing.JButton Refund_Buttn;
    private javax.swing.JButton cancel_c;
    private javax.swing.JPanel cancel_panel;
    private javax.swing.JTextArea cancel_reason_text;
    private javax.swing.JLabel check_in_date;
    private javax.swing.JLabel check_in_date1;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel contact1;
    private javax.swing.JPanel detail_panel;
    private javax.swing.JLabel due_amt;
    private javax.swing.JLabel due_amt1;
    private javax.swing.JLabel due_amt2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel mem_no;
    private javax.swing.JLabel mem_no1;
    private javax.swing.JLabel memname;
    private javax.swing.JLabel memname1;
    private javax.swing.JLabel nonmemname;
    private javax.swing.JLabel nonmemname1;
    private javax.swing.JLabel payment_recv;
    private javax.swing.JLabel payment_recv1;
    private javax.swing.JLabel slot;
    private javax.swing.JLabel slot1;
    private javax.swing.JPanel sub;
    private javax.swing.JButton submit_c;
    private javax.swing.JLabel totamt;
    private javax.swing.JLabel totamt1;
    private javax.swing.JLabel totamt2;
    private javax.swing.JLabel totamt3;
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
    }

    public Object getBean() {
       return this;
    }
    
    public void loaddata() throws BasicException{
        
       AdvanceRecv = AdvanceRecieveTableModel.LoadHallAdvanceRecieve(m_App);
       showPanelInfo(AdvanceRecv); 
        
    }
    public void showPanelInfo(AdvanceRecieveTableModel AdvanceRecv){
         jTable1.setModel(AdvanceRecv.getTableModel());
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
    
    
       
       
         private String getNextVoucherNo() throws BasicException{
       //akash:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname= "REFUND VOUCHER";
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
         return "";
        }
       
     }
    
         
         
         
         
         public void PrintRefundReciept(){
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
            
            script.put("maintitle", "Booking Cancellation Slip");
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", CustName);
            script.put("cno", MemberNo);
            script.put("date", Formats.TIMESTAMP.formatValue(new Date())); 
            
            
           script.put("Reciept_title", "Refund Voucher Id : ");
           script.put("receipt", Rfd_Voucher_No);
           
            
            
            script.put("label_1", "Room Type :");
            script.put("text1", HallName);
            
            script.put("label_2", "Check In Date :");
            script.put("text2",CheckInDate);
            
            script.put("label_3", "Slot Booked  :");
            script.put("text3", SlotBooked);
            
          
            script.put("label_4", "Advance Paymnt Dt. :");
            script.put("text4", AdvancePayDate );
            
            script.put("label_5", "Fixed Cancel Chrg:");
            script.put("text5", "");
            
            script.put("Gname", "");
            
            script.put("label_6", "Advance Paid : ");
            script.put("text6", decimalFormat.format(AdvanceRecvd));
            
            
            script.put("label_7", "Total Billed Amount");
            script.put("text7", decimalFormat.format(TotalCharge)); 
            
            script.put("label_8", "Refund Amount : ");
            script.put("text8", decimalFormat.format(BalAmt));
            
            
            
            
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
         
         
         
         
         
}
