
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import com.openbravo.pos.Booking.GuestRoomBookingRequstTableModel.Room_StatusInfo;
import com.openbravo.pos.Booking.GuestRoomCheckInRequestModel.Pending_StatusInfo;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GuestRoomBookingRequest extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private GuestRoomBookingRequstTableModel booking_request;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private GuestRoomCheckInRequestModel Pending_chkngRequest;
    
    public GuestRoomBookingRequest() {
        initComponents();
        main_panel.setVisible(true);
        roomDetail_panel.setVisible(false);
        note_panel.setVisible(false);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        Approv = new javax.swing.JButton();
        cancel_request = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        pending_label = new javax.swing.JLabel();
        message1_text = new javax.swing.JLabel();
        message2_text = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        Approv1 = new javax.swing.JButton();
        cancel_request1 = new javax.swing.JButton();
        roomDetail_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        mName = new javax.swing.JLabel();
        memberNo = new javax.swing.JLabel();
        NName = new javax.swing.JLabel();
        contactNo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Adress = new javax.swing.JTextArea();
        jLabel69 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        roomtype = new javax.swing.JLabel();
        capacity = new javax.swing.JLabel();
        tariff = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        bookingDate = new javax.swing.JLabel();
        booked_no_of_days = new javax.swing.JLabel();
        no_of_rooms_bk = new javax.swing.JLabel();
        tax_1 = new javax.swing.JLabel();
        tax_2 = new javax.swing.JLabel();
        tax_3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        mem_label = new javax.swing.JLabel();
        Approv2 = new javax.swing.JButton();
        cancel_request2 = new javax.swing.JButton();
        note_panel = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        note = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        hall_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bookby_label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bookdate_label = new javax.swing.JLabel();
        days_label = new javax.swing.JLabel();
        mes = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        room_label = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Room Type", "No. Of Rooms Booked", "No. of Days", "Booked By", "Booked Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        Approv.setText("Approve");
        Approv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApprovActionPerformed(evt);
            }
        });

        cancel_request.setText("Cancel Request");
        cancel_request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_requestActionPerformed(evt);
            }
        });

        jButton1.setText("View Booking Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pending_label.setText("pending list");

        message1_text.setForeground(new java.awt.Color(207, 11, 11));
        message1_text.setText("Message 1");

        message2_text.setForeground(new java.awt.Color(192, 10, 10));
        message2_text.setText("Message 2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pending_label)
                .addGap(133, 133, 133))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Approv, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(cancel_request, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(message2_text, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addComponent(message1_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pending_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(message1_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message2_text)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(cancel_request, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Approv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Approv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        cancel_request.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/viewmag.png"))); // NOI18N
        pending_label.setForeground(Color.RED);
        message1_text.setVisible(false);
        message2_text.setVisible(false);

        jTabbedPane1.addTab("Booking Request", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable2);

        Approv1.setText("Approve");
        Approv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Approv1ActionPerformed(evt);
            }
        });

        cancel_request1.setText("Cancel Request");
        cancel_request1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_request1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(Approv1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(cancel_request1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 286, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Approv1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancel_request1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
        );

        Approv1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        cancel_request1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        jTabbedPane1.addTab("Check-In & Check-Out  Request", jPanel3);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel11.setText("Room Booked Details");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 51, 51));
        jLabel59.setText("Member's Detail");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Member Name  : ");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Member No. :");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Address : ");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("Non_Member's Name : ");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Contact No. : ");

        mName.setText("N/A");

        memberNo.setText("N/A");

        NName.setText("N/A");

        contactNo.setText("N/A");

        Adress.setColumns(20);
        Adress.setRows(5);
        jScrollPane3.setViewportView(Adress);
        Adress.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberNo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactNo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mName)
                    .addComponent(NName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel63)
                    .addComponent(memberNo)
                    .addComponent(contactNo))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 51, 51));
        jLabel69.setText("Room Details");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Room Type :");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Max. Capacity : ");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Tariff : ");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setText("Taxes : ");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("(1)");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setText("(2)");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("(3)");

        roomtype.setText("N/A");

        capacity.setText("N/A");

        tariff.setText("N/A");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Booking Date : ");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText("Booked For No. of Days: ");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("No. Of Rooms Booked : ");

        bookingDate.setText("N/A");

        booked_no_of_days.setText("N/A");

        no_of_rooms_bk.setText("N/A");

        tax_1.setText("N/A");

        tax_2.setText("N/A");

        tax_3.setText("N/A");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomtype))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capacity))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tariff))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_2))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel73)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_3))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookingDate))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(no_of_rooms_bk))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(booked_no_of_days)))
                .addGap(133, 133, 133))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomtype)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capacity)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(booked_no_of_days))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tariff)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no_of_rooms_bk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Go Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        mem_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem_label.setText("( Booked For :- )");

        Approv2.setText("Approve");
        Approv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Approv2ActionPerformed(evt);
            }
        });

        cancel_request2.setText("Cancel Request");
        cancel_request2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_request2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roomDetail_panelLayout = new javax.swing.GroupLayout(roomDetail_panel);
        roomDetail_panel.setLayout(roomDetail_panelLayout);
        roomDetail_panelLayout.setHorizontalGroup(
            roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomDetail_panelLayout.createSequentialGroup()
                .addGap(0, 281, Short.MAX_VALUE)
                .addGroup(roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomDetail_panelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(276, 276, 276))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomDetail_panelLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(374, 374, 374))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomDetail_panelLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mem_label)
                        .addGap(259, 259, 259))))
            .addGroup(roomDetail_panelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(roomDetail_panelLayout.createSequentialGroup()
                            .addComponent(Approv2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cancel_request2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roomDetail_panelLayout.setVerticalGroup(
            roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomDetail_panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(mem_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roomDetail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Approv2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancel_request2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        Approv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        cancel_request.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        note_panel.setVisible(true);

        note.setColumns(20);
        note.setRows(5);
        jScrollPane2.setViewportView(note);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Cancellation Reason : ");

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Cancellation Note :- ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Room Type : ");

        hall_label.setText("name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Booked By :");

        bookby_label.setText("Bk_name");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Booking date :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Booking days :");

        bookdate_label.setText("date");

        days_label.setText("slot");

        mes.setText("*  Enter Cancellation reason");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Booked no. of Rooms :");

        room_label.setText("jLabel6");

        javax.swing.GroupLayout note_panelLayout = new javax.swing.GroupLayout(note_panel.getContentPane());
        note_panel.getContentPane().setLayout(note_panelLayout);
        note_panelLayout.setHorizontalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, note_panelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(note_panelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mes)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hall_label))
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookby_label))
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(room_label)))
                        .addGap(198, 198, 198)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(days_label))
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookdate_label)))
                        .addGap(0, 185, Short.MAX_VALUE))))
        );
        note_panelLayout.setVerticalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(hall_label)
                    .addComponent(jLabel7)
                    .addComponent(bookdate_label))
                .addGap(18, 18, 18)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bookby_label)
                    .addComponent(jLabel8)
                    .addComponent(days_label))
                .addGap(18, 18, 18)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(room_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 123, Short.MAX_VALUE)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mes)
                        .addGap(57, 57, 57)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addGap(37, 37, 37))
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        mes.setForeground(Color.RED);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomDetail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roomDetail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ApprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApprovActionPerformed
       if(jTable1.getSelectedRow()!=-1){
           int submit_room = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Approv Request .. ?? " , "Request Approval " , JOptionPane.YES_NO_OPTION);
             if(submit_room == JOptionPane.YES_OPTION){
           
                 Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                                                              
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                 
                                        int row = jTable1.getSelectedRow(); 

                                        Room_StatusInfo showdata = booking_request.getRoomList().get(row);

                                        String roomtype = showdata.getROOMTYPE_ID();
                                        String memberName = showdata.getMEMBER_ID();
                                        String Booking_date = showdata.getBOOKING_DATE();

                                        String OldReference=showdata.getOldReference();


                                         try {
                                                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET FLAG=1 , REQ_APPROV_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=?  WHERE ROOM_TYPE=? AND MEMBERNAME=? AND BOOKING_DATE=?  "
                                                                           , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING ,Datas.STRING , Datas.STRING  })).exec
                                                                            (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , roomtype ,memberName ,   Booking_date });



                                                //////////////////////// OLD REFERENCE 
                                                if(OldReference!=null && OldReference.trim().length()>0){

                                                     int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET STATUS=1 , REQ_CAN_BY=? , REQ_CAN_DATE=? , REQ_CAN_HOST=?  WHERE ID = ?"
                                                                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  })).exec
                                                                                             (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() ,OldReference });
                                                     int getBooked_rooms=0;
                                                     Date Booked_date_EX_TEMP = (Date) Formats.TIMESTAMP.parseValue(showdata.getBOOKED_DATE_EX());


                                                     for(int i=1;i<=showdata.getNO_OF_DAYS() ; i++ ){

                                                                       Calendar c = Calendar.getInstance(); 
                                                                       c.setTime(Booked_date_EX_TEMP);
                                                                       c.add(Calendar.DATE, i-1);
                                                                       c.set(Calendar.MILLISECOND, 0);
                                                                       c.set(Calendar.SECOND, 0);
                                                                       c.set(Calendar.MINUTE, 0);
                                                                       c.set(Calendar.HOUR, 0);
                                                                       Booked_date_EX_TEMP = c.getTime();   

                                                                      getBooked_rooms = booking_request.getRoom_Booked(m_App ,showdata.getROOMTYPE_ID() , Booked_date_EX_TEMP);

                                                                      if(getBooked_rooms >= showdata.getNO_OF_ROOMS_BOOKED()){ 

                                                                          int update_room_availibility = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=?  WHERE BOOKED_DATES=? AND ROOM_TYPE=?"
                                                                                                         , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                                                          (new Object[]{ (getBooked_rooms - showdata.getNO_OF_ROOMS_BOOKED() ) , Booked_date_EX_TEMP , OldReference  });

                                                                      }
                                                              }



                                                                  Date BookingDateEx=(Date) Formats.DATE.parseValue(showdata.getBOOKED_DATE_EX());

                                                                  int UpdateAdvanceDetails = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_advance_payment  SET BOOKING_ID=? , BOOKING_SEQ_NO=? , CHECK_IN_DATE=?   WHERE  BOOKING_ID=?  "
                                                                                                         , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                                                          (new Object[]{ showdata.getId() , showdata.getBOOKING_SEQ_NO() , BookingDateEx, OldReference });


                                                                  int UpdateAdvanceDetails2 = new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom  SET BOOKING_ID=?  WHERE  BOOKING_ID=?  "
                                                                                                         , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING  })).exec
                                                                                                          (new Object[]{ showdata.getId() ,OldReference });





                                                }



                                           
                                          } 
                                     catch (BasicException ex) {
                                    Logger.getLogger(GuestRoomBookingRequstTableModel.class.getName()).log(Level.SEVERE, null, ex);
                                }
             
                                         
                                         
                  
                                         return null;                                      
                            
                        }                            
                        
                 };                 
                          
                        
                 try {                 
                            
                     t.execute();          
                     loaddata();
                            
                        
                 }
                     
                 catch (BasicException ex) {                    
                                
                     Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                      ex.printStackTrace();
                      new MessageInf(ex).show(new JFrame());
                            
                            
                 }
                 
                 
             }
       }
    }//GEN-LAST:event_ApprovActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(jTable1.getSelectedColumn()!=-1){
           
          Approv.setEnabled(true);
          cancel_request.setEnabled(true);
          
          int row = jTable1.getSelectedRow(); 
          Room_StatusInfo showdata = booking_request.getRoomList().get(row);
                 
          if(showdata.getOldReference()!=null && showdata.getOldReference().trim().length()>0){
              
              String OldBookingID=showdata.getOldReference();
              int noofDaysBooked=0;
              Date BookingDateEx = null;
              String OldGrNo = null; 
              Date CheckoutDate = null;
              
              try{
                  Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT BOOKING_DAYS , BOOKING_DATE_EX , BOOKING_SEQ_NO,CDATECHANGEFLAG  FROM guestroom_booked_details where id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT ,Datas.TIMESTAMP , Datas.STRING,Datas.INT })).find(OldBookingID);//edited by pratima to add CDATECHANGEFLAG column
                  if(obj1!=null){
                      if(obj1[0]!=null){
                          noofDaysBooked=Integer.parseInt(obj1[0].toString());
                          BookingDateEx=(Date) obj1[1];
                          OldGrNo=obj1[2].toString();
                          
                          
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(BookingDateEx);
                            cal.add(Calendar.DATE, noofDaysBooked); //minus number would decrement the days
                            CheckoutDate =  cal.getTime();
                            String CheckOutDateStr = Formats.TIMESTAMP.formatValue(CheckoutDate);
                            String CheckInDateStr = Formats.TIMESTAMP.formatValue(BookingDateEx);
                            
                            message1_text.setVisible(true);
                            
                            message1_text.setText("Requested to change the booking date. ");
                            
                            String message = "Earlier it was booked from :"+CheckInDateStr +" to :"+CheckOutDateStr+". (Booking No: "+OldGrNo+").";
                            message2_text.setVisible(true);
                            message2_text.setText(message);
                            
                      }
                  }
                  else{
                      message1_text.setVisible(false);
                      message2_text.setVisible(false);
                  }
                  
                  
                  
              
              }
              catch(BasicException e){
                  e.printStackTrace();
              }
          }
          else{
                    message1_text.setVisible(false);
                    message2_text.setVisible(false);
          }
                 
                 
       }
       else{
          Approv.setEnabled(false);
          cancel_request.setEnabled(false);
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cancel_requestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_requestActionPerformed
         if(jTable1.getSelectedRow()!=-1){
        int submit_room = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Cancel Request .. ?? " , "Cancellation" , JOptionPane.YES_NO_OPTION);
             if(submit_room == JOptionPane.YES_OPTION){
                 
             
             
              
                 int row = jTable1.getSelectedRow(); 
                 Room_StatusInfo showdata = booking_request.getRoomList().get(row);
                 
                 
                 hall_label.setText(showdata.getROOM_TYPE());
                 
                 if(showdata.getMem_flag()==1){
                     bookby_label.setText(showdata.getMemberName());
                 }
                 else{
                     bookby_label.setText(showdata.getNON_MEM_NAME()+ " ( Ref:-  "+showdata.getMemberName()+")");
                 }
                 
                 room_label.setText(""+showdata.getNO_OF_ROOMS_BOOKED());
                 bookdate_label.setText(showdata.getBOOKING_DATE());
                 days_label.setText(""+showdata.getNO_OF_DAYS());
                 
                  note_panel.setVisible(true);
                  main_panel.setVisible(false);
                  mes.setVisible(false);
             }
         }
    }//GEN-LAST:event_cancel_requestActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
             int row = jTable1.getSelectedRow(); 
                 Room_StatusInfo showdata = booking_request.getRoomList().get(row);
                 
                 mName.setText(showdata.getMemberName());
                 memberNo.setText(showdata.getMem_No());
                 NName.setText(showdata.getNON_MEM_NAME());
                 contactNo.setText(showdata.getNON_MEM_CONTCT());
                
                 if(showdata.getMem_flag()==1){
                      Adress.setText(showdata.getCustAddress());
                 }
                 else{
                      Adress.setText(showdata.getNON_MEM_ADDR());
                 }
                 
                 roomtype.setText(showdata.getROOM_TYPE());
                 capacity.setText(""+showdata.getMAX_CAPACITY());
                 tariff.setText(decimalFormat.format(showdata.getCHARGES()));
                 tax_1.setText(showdata.getLUXURYTAX());
                 tax_2.setText(showdata.getTAX2());
                 tax_3.setText(showdata.getTAX3());
                 bookingDate.setText(showdata.getBOOKING_DATE());
                 booked_no_of_days.setText(""+showdata.getNO_OF_DAYS());
                 no_of_rooms_bk.setText(""+showdata.getNO_OF_ROOMS_BOOKED());
                 
                 roomDetail_panel.setVisible(true);
                 main_panel.setVisible(false);
                  
                 if(showdata.getMem_flag()==1){
                    mem_label.setText("( Booked For :- Member )");
                    mem_label.setForeground(Color.BLUE);
                }
                else{
                    mem_label.setText("( Booked For :- Non-Member )");
                    mem_label.setForeground(Color.BLUE);
                }
            
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       main_panel.setVisible(true);
       roomDetail_panel.setVisible(false);
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        note_panel.setVisible(false);
        main_panel.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if(note.getText()!=null && note.getText().trim().length()>0){
           
              mes.setVisible(false);
                int row = jTable1.getSelectedRow(); 
                 
                 Room_StatusInfo showdata = booking_request.getRoomList().get(row);
                 
                 String roomtype = showdata.getROOMTYPE_ID();
                 String memberName = showdata.getMEMBER_ID();
                 String Booking_date = showdata.getBOOKING_DATE();
                 String note_text = note.getText();
                 int no_of_days = showdata.getNO_OF_DAYS();
                 Date Booked_date_EX = new Date();
                    try {
                        Booked_date_EX = (Date) Formats.DATE.parseValue(showdata.getBOOKED_DATE_EX());
                    } catch (BasicException ex) {
                        Logger.getLogger(GuestRoomBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                 Date Booked_date_EX_TEMP = new Date();
                 int getBooked_rooms = 0;
                 int no_of_rooms_booked_by_mem = showdata.getNO_OF_ROOMS_BOOKED();
                 
                 
                  try {
                         int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET  FLAG=2 ,CANCEL_REASON=? , REQ_APPROV_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=?  WHERE ID=?  "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING  })).exec
                                                     (new Object[]{ note_text ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , showdata.getId()  });
            
                         
                   
                         
                    for(int i=1;i<=no_of_days ; i++ ){
                   
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(Booked_date_EX);
                    c.add(Calendar.DATE, i-1);
                    Booked_date_EX_TEMP = c.getTime();   
                   
                   getBooked_rooms = booking_request.getRoom_Booked(m_App ,roomtype , Booked_date_EX_TEMP);
                   
                  /*
                   if(getBooked_rooms >= no_of_rooms_booked_by_mem){ 
                   
                       int update_room_availibility = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=?  WHERE BOOKED_DATES=? AND ROOM_TYPE=?"
                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                       (new Object[]{ (getBooked_rooms - no_of_rooms_booked_by_mem ) , Booked_date_EX_TEMP , roomtype  });
                   
                        }
                        */
                   }
                       
                    
                    if(showdata.getOldReference()!=null && showdata.getOldReference().trim().length()>0){
                        int update_oldGuestroom =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET  bdatechangeflag=0  WHERE ID=?  "
                                                    , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                     (new Object[]{  showdata.getOldReference()  });
            
                        
                        
                    }
                    
            
                    loaddata();
                     JOptionPane.showMessageDialog(this, "Request Canceled..! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    main_panel.setVisible(true);
                    note_panel.setVisible(false);
                    
                   } 
                 catch (BasicException ex) {
                Logger.getLogger(GuestRoomBookingRequstTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
           
           
       }
       else{
           mes.setVisible(true);
       }
    }//GEN-LAST:event_jButton6ActionPerformed
    Pending_StatusInfo showdata1;
    Date Check_In;
    String chkInID;
    Date E_Check_In;
    Date E_check_Out;
    int E_Booking_Days;
    
    private void Approv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Approv1ActionPerformed
         if(jTable2.getSelectedRow()!=-1){
           int submit_room = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Approv Request .. ?? " , "Request Approval " , JOptionPane.YES_NO_OPTION);
             if(submit_room == JOptionPane.YES_OPTION){
           
                 int row = jTable2.getSelectedRow(); 
                 
                  showdata1 = Pending_chkngRequest.getRoomList().get(row);
                 
                 
                 chkInID = showdata1.getId();    
                 
                 
                
                 int Approve_Flag = showdata1.getApproved();
                 
                 if(Approve_Flag==0){
                     
                     
                     
                  try {
                         Check_In = (Date) Formats.TIMESTAMP.parseValue(showdata1.getcheck_in());
                         E_Check_In = (Date) Formats.TIMESTAMP.parseValue(showdata1.getE_Check_In());
                         E_check_Out = (Date) Formats.TIMESTAMP.parseValue(showdata1.getE_Check_Out());
                         
                         
                     } catch (BasicException ex) {
                         Logger.getLogger(GuestRoomBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
                     }
                  
                     chkInID = showdata1.getId();    
                      
                    E_Booking_Days = (int)( (E_check_Out.getTime() - E_Check_In.getTime()) / (1000 * 60 * 60 * 24));
                    System.out.println("new Days " +E_Booking_Days);
                    
                    
                    Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                        
                        
                        
                                                          
                        String RoomType = showdata1.getROOM_TYPE();
                        String RoomTypeID =    Pending_chkngRequest.getRoomTypeId(m_App, RoomType);
                        int bookingdays = showdata1.getNO_OF_DAYS();
                       
                        int roomsBooked = showdata1.getNO_OF_ROOMS_BOOKED();
                        

                       
                      
                       
                           
                        
                        
                        
                        
                        
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                            
                            
                            
                         int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET APPROVED=1  , REQ_APPROV_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING  })).exec
                                                     (new Object[]{   m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , chkInID  });
           
            
                       
                         
                         for(int i=1;i<=bookingdays;i++){
                             
                                Calendar c = Calendar.getInstance(); 
                                c.setTime(Check_In);
                                c.set(Calendar.HOUR_OF_DAY, 00);
                                c.set(Calendar.MINUTE, 00);
                                c.set(Calendar.SECOND, 00);
                                c.add(Calendar.DATE, i-1);
                                
                                Check_In = c.getTime();
                                System.out.println(Check_In);
                                
                                
                                int getBooked_rooms = 0;
                                getBooked_rooms = booking_request.getRoom_Booked(m_App ,RoomTypeID , Check_In);
                                System.out.println("no of rooms :"+getBooked_rooms); 
                                
                                 
                                 int update_Availibilty =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=? WHERE ROOM_TYPE =? AND BOOKED_DATES = ?"
                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.STRING , Datas.TIMESTAMP  })).exec
                                                       (new Object[]{(getBooked_rooms-1) ,RoomTypeID , Check_In  });
              
                                 
                             
                             }
                            
                         
                         
                          for(int i=1;i<=E_Booking_Days;i++){
                             
                                Calendar c = Calendar.getInstance(); 
                                c.setTime(E_Check_In);
                                c.set(Calendar.HOUR_OF_DAY, 00);
                                c.set(Calendar.MINUTE, 00);
                                c.set(Calendar.SECOND, 00);
                                c.add(Calendar.DATE, i-1);
                                
                                E_Check_In = c.getTime();
                                System.out.println(E_Check_In);
                                
                                
                                int getBooked_rooms = 0;
                                getBooked_rooms = booking_request.getRoom_Booked(m_App ,RoomTypeID , E_Check_In);
                                int getTotalRooms = 0;
                                getTotalRooms = booking_request.getTotalRooms(m_App, RoomTypeID);
                                
                                
                                System.out.println("no of rooms :"+getBooked_rooms); 
                                
                                 
                                  if( new PreparedSentence(m_App.getSession()
                                                , "UPDATE guestroom_availibility  SET BOOKED_ROOMS=? WHERE ROOM_TYPE =? AND BOOKED_DATES = ?"
                                                , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.STRING , Datas.TIMESTAMP  })).exec(
                                                new Object[]{(getBooked_rooms+1) ,RoomTypeID , E_Check_In})<=0){

                                            new PreparedSentence(m_App.getSession()
                                                    , "INSERT INTO guestroom_availibility(ID ,TOTAL_ROOMS, BOOKED_ROOMS, BOOKED_DATES , ROOM_TYPE ) VALUES(?,?,?,?,?)"
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.INT , Datas.INT , Datas.TIMESTAMP , Datas.STRING})).exec(
                                                    new Object[]{UUID.randomUUID().toString(),getTotalRooms ,1, E_Check_In  , RoomTypeID  });
                                        }
                                 
                                 
                                 
                             
                             }
                         
                         
                         
                         
                            
                            
                            
                            
                            
                            
                      
                          return null;                                      
                            }                            
                        };                 
                          
                        try {                 
                            t.execute();          
                            
                            loaddata();
                    
                           JOptionPane.showMessageDialog(this, "Request Approved..! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                           
                            
                        }
                     catch (BasicException ex) {                    
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                            
                            }
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                 }
                if(Approve_Flag==2){
                    
                    
                    
                    
                    
                    
                  
                    
                 try {
                         int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET E_CHK_OUT=X_CHK_OUT , APPROVED=4 , DISCOUNT=0.00 , REQ_APPROV_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING  })).exec
                                                     (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , chkInID  });
           
            
                    loaddata();
                    
                    JOptionPane.showMessageDialog(this, "Request Approved..! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                   } 
                        catch (BasicException ex) {
                            ex.printStackTrace();
                            new MessageInf(ex).show(this);
                       Logger.getLogger(GuestRoomBookingRequstTableModel.class.getName()).log(Level.SEVERE, null, ex);
                      }   
                    
                    
                    
                 
                 
                    
                    
                    
                    
                }
                 
                 
             }
       }
    }//GEN-LAST:event_Approv1ActionPerformed

    private void cancel_request1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_request1ActionPerformed
        if(jTable2.getSelectedRow()!=-1){
           int submit_room = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Reject Request .. ?? " , "Request Approval " , JOptionPane.YES_NO_OPTION);
             if(submit_room == JOptionPane.YES_OPTION){
           
                 int row = jTable2.getSelectedRow(); 
                 
                 Pending_StatusInfo showdata = Pending_chkngRequest.getRoomList().get(row);
                 
                 String ID = showdata.getId();
                
                 
                 
                 
                 
                  try {
                         int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET APPROVED=5 , REQ_APPROV_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=?  WHERE ID=?   "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING  })).exec
                                                     (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , ID  });
           
            
                         
                         
                    loaddata();
                    
                  JOptionPane.showMessageDialog(this, "Request Rejected..! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                   } 
                 catch (BasicException ex) {
                     ex.printStackTrace();
                     new MessageInf(ex).show(this);
                Logger.getLogger(GuestRoomBookingRequstTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                 
                 
                 
             }
       }
    }//GEN-LAST:event_cancel_request1ActionPerformed

    private void Approv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Approv2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Approv2ActionPerformed

    private void cancel_request2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_request2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancel_request2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Adress;
    private javax.swing.JButton Approv;
    private javax.swing.JButton Approv1;
    private javax.swing.JButton Approv2;
    private javax.swing.JLabel NName;
    private javax.swing.JLabel bookby_label;
    private javax.swing.JLabel bookdate_label;
    private javax.swing.JLabel booked_no_of_days;
    private javax.swing.JLabel bookingDate;
    private javax.swing.JButton cancel_request;
    private javax.swing.JButton cancel_request1;
    private javax.swing.JButton cancel_request2;
    private javax.swing.JLabel capacity;
    private javax.swing.JLabel contactNo;
    private javax.swing.JLabel days_label;
    private javax.swing.JLabel hall_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel mName;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel mem_label;
    private javax.swing.JLabel memberNo;
    private javax.swing.JLabel mes;
    private javax.swing.JLabel message1_text;
    private javax.swing.JLabel message2_text;
    private javax.swing.JLabel no_of_rooms_bk;
    private javax.swing.JTextArea note;
    private javax.swing.JInternalFrame note_panel;
    private javax.swing.JLabel pending_label;
    private javax.swing.JPanel roomDetail_panel;
    private javax.swing.JLabel room_label;
    private javax.swing.JLabel roomtype;
    private javax.swing.JLabel tariff;
    private javax.swing.JLabel tax_1;
    private javax.swing.JLabel tax_2;
    private javax.swing.JLabel tax_3;
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
        
         boolean flag;
         message1_text.setVisible(false);
         message2_text.setVisible(false);
         
        if( flag = m_App.getAppUserView().getUser().hasPermission("RoomBooking_Request")) 
        {
             jTabbedPane1.setEnabledAt(0, true);
             Approv.setEnabled(true);
             cancel_request.setEnabled(true);
        }
        else{
             jTabbedPane1.setEnabledAt(0, false);
             jTabbedPane1.setSelectedIndex(1);
             Approv.setEnabled(false);
             cancel_request.setEnabled(false);
         }
        
        if( flag = m_App.getAppUserView().getUser().hasPermission("Check_In_Request")) 
        {
             jTabbedPane1.setEnabledAt(1, true);
             Approv1.setEnabled(true);
             cancel_request1.setEnabled(true);
        }
        else{
             jTabbedPane1.setEnabledAt(1, false);
             jTabbedPane1.setSelectedIndex(0);
             Approv1.setEnabled(false);
             cancel_request1.setEnabled(false);
         }
        
        
        booking_request = GuestRoomBookingRequstTableModel.loadInstanceBooked_Room_Status(m_App);
        showPanelInfo(booking_request);
        
        int pending_list = booking_request.getRoomSize();
        if(pending_list > 0){
            pending_label.setVisible(true);
            pending_label.setText("( * "+pending_list+ " Request Pending. )");
        }
        else{
            pending_label.setVisible(false);
        }
        
        
        Pending_chkngRequest = GuestRoomCheckInRequestModel.load_PendingChkIn_Request(m_App);
        showPanelInfo2(Pending_chkngRequest);
        
        
    }
    
    public void showPanelInfo(GuestRoomBookingRequstTableModel booking_request){
        
        jTable1.setModel(booking_request.getTableModel());
     }
    
    public void showPanelInfo2(GuestRoomCheckInRequestModel Pending_chkngRequest){
        
        jTable2.setModel(Pending_chkngRequest.getTableModel());
     }  
    
    
    public void reset(){
        main_panel.setVisible(true);
        roomDetail_panel.setVisible(false);
         note_panel.setVisible(false);
        
        
    }
    
}
