
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;
import com.openbravo.pos.Booking.HallBookingRequestTableModel.HallStatusInfo;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;


public class HallBookingRequest extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private HallBookingRequestTableModel bookstatus;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
   
    public HallBookingRequest() {
        initComponents();
        main_panel.setVisible(true);
        hall_detail_panel.setVisible(false);
        note_panel.setVisible(false);
        mes.setVisible(false);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pending_label = new javax.swing.JLabel();
        hall_detail_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        mName = new javax.swing.JLabel();
        memberNo = new javax.swing.JLabel();
        NName = new javax.swing.JLabel();
        contactNo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Adress = new javax.swing.JTextArea();
        jLabel69 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        bookingDate = new javax.swing.JLabel();
        bookingSlot = new javax.swing.JLabel();
        hallName = new javax.swing.JLabel();
        capacity = new javax.swing.JLabel();
        tariff = new javax.swing.JLabel();
        tax_1 = new javax.swing.JLabel();
        tax_2 = new javax.swing.JLabel();
        tax_3 = new javax.swing.JLabel();
        slot_label = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        mem_label = new javax.swing.JLabel();
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
        b_slot_label = new javax.swing.JLabel();
        mes = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Hall Name", "Floor", "Max. Capacity", "Booked By", "Booking Date", "Slot booked"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 51));
        jButton1.setText("Approve");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 51));
        jButton2.setText("Cancel request");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 255));
        jButton3.setText("View Details");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pending_label.setText("(Pending_list)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pending_label)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pending_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/viewmag.png"))); // NOI18N
        pending_label.setForeground(Color.RED);

        jTabbedPane1.addTab("Hall Booking request", jPanel2);

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
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Hall Booked Details");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 51, 51));
        jLabel59.setText("Booking's Detail");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Member Name  : ");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Member No. :");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("Non_Member's Name : ");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Contact No. : ");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Address : ");

        mName.setText("N/A");

        memberNo.setText("N/A");

        NName.setText("N/A");

        contactNo.setText("N/A");

        Adress.setColumns(20);
        Adress.setRows(5);
        jScrollPane3.setViewportView(Adress);
        Adress.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mName))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberNo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactNo))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NName))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mName)
                    .addComponent(NName))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel63)
                    .addComponent(memberNo)
                    .addComponent(contactNo))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 51, 51));
        jLabel69.setText("Hall Details");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Name : ");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Max. Capacity : ");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Tariff : ");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Taxes : ");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setText("(1)");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("(2)");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setText("(3)");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("Booking Date : ");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText("Booking Slot : ");

        bookingDate.setText("N/A");

        bookingSlot.setText("N/A");

        hallName.setText("N/A");

        capacity.setText("N/A");

        tariff.setText("N/A");

        tax_1.setText("N/A");

        tax_2.setText("N/A");

        tax_3.setText("N/A");

        slot_label.setText("(Slot)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hallName))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capacity))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tariff))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_2))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_3))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookingDate))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookingSlot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slot_label)))
                .addGap(180, 180, 180))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingDate)
                    .addComponent(hallName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingSlot)
                    .addComponent(capacity)
                    .addComponent(slot_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tariff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        mem_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem_label.setText("( Booked For :- )");

        javax.swing.GroupLayout hall_detail_panelLayout = new javax.swing.GroupLayout(hall_detail_panel);
        hall_detail_panel.setLayout(hall_detail_panelLayout);
        hall_detail_panelLayout.setHorizontalGroup(
            hall_detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hall_detail_panelLayout.createSequentialGroup()
                .addGroup(hall_detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hall_detail_panelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(hall_detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(hall_detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(hall_detail_panelLayout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel11))
                    .addGroup(hall_detail_panelLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mem_label))
                    .addGroup(hall_detail_panelLayout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jLabel69)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        hall_detail_panelLayout.setVerticalGroup(
            hall_detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hall_detail_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hall_detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(mem_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jPanel4.setSize(764, 165);
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        jPanel5.setSize(764, 230);
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/exit.png"))); // NOI18N

        note_panel.setVisible(true);

        note.setColumns(20);
        note.setRows(5);
        jScrollPane2.setViewportView(note);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Cancellation Reason : ");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 0, 51));
        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 102, 51));
        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 255));
        jLabel2.setText("Cancellation Note :- ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Hall Name :");

        hall_label.setText("name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Booked By :");

        bookby_label.setText("Bk_name");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Booking date :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Booking slot :");

        bookdate_label.setText("date");

        b_slot_label.setText("slot");

        mes.setText("*  Enter Cancellation reason");

        javax.swing.GroupLayout note_panelLayout = new javax.swing.GroupLayout(note_panel.getContentPane());
        note_panel.getContentPane().setLayout(note_panelLayout);
        note_panelLayout.setHorizontalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(note_panelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hall_label))
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookby_label)))
                        .addGap(248, 248, 248)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_slot_label))
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookdate_label)))
                        .addGap(0, 185, Short.MAX_VALUE))
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mes)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, note_panelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
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
                    .addComponent(b_slot_label))
                .addGap(18, 224, Short.MAX_VALUE)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mes)
                        .addGap(30, 30, 30)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hall_detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hall_detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTable1.getSelectedRow()!=-1){       
             int submit_hall = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Approv Request .. ?? " , "Booking Approval" , JOptionPane.YES_NO_OPTION);
             if(submit_hall == JOptionPane.YES_OPTION){
            
            int row = jTable1.getSelectedRow();
            HallStatusInfo showdata = bookstatus.getHallList().get(row);
        
            int status = showdata.getStatus();
            String hall_Name_ID = showdata.getHALL_ID();
            String Booking_Date = showdata.getBOOKING_DATE();
            String member_id = showdata.getMEMBER_ID();
            String Booking_slot = showdata.getTIMING_SLOTS();
           
            try {
                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE hall_booked_details  SET FLAG=1 , REQ_APPROVE_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=?  WHERE HALL_NAME=? AND MEMBERNAME=? AND BOOKING_DATE=?  AND  BOOKING_SLOT=?  "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING ,Datas.STRING , Datas.STRING , Datas.STRING })).exec
                                                     (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , hall_Name_ID ,member_id ,   Booking_Date , Booking_slot });
           
            
                
            JOptionPane.showMessageDialog(this, "Request Approved..! ", "Success", JOptionPane.INFORMATION_MESSAGE);    
            loaddata();
            
            
           } 
           catch (BasicException ex) {
                Logger.getLogger(HallBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
            
             }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTable1.getSelectedRow()!=-1){       
             int submit_hall = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Cancel Request .. ?? ", "Booking Cancallation" , JOptionPane.YES_NO_OPTION);
             if(submit_hall == JOptionPane.YES_OPTION){
            
            int row = jTable1.getSelectedRow();
            HallStatusInfo showdata = bookstatus.getHallList().get(row);
        
             hall_label.setText(showdata.gethall_name());
             if(showdata.getMem_flag()==1){
             bookby_label.setText(showdata.getMemberName());
             }
             else{
                 bookby_label.setText(showdata.getNON_MEM_NAME() + "   (Ref to:-  "+showdata.getMemberName()+")");
             }
             bookdate_label.setText(showdata.getBOOKING_DATE());
             b_slot_label.setText(showdata.getTIMING_SLOTS());
            
             
             note_panel.setVisible(true);
             main_panel.setVisible(false);
             
             }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(jTable1.getSelectedRow()!=-1){  
           int row = jTable1.getSelectedRow();
           HallStatusInfo showdata = bookstatus.getHallList().get(row);
           
           mName.setText(showdata.getMemberName());
           memberNo.setText(showdata.getMem_No());
           NName.setText(showdata.getNON_MEM_NAME());
           contactNo.setText(""+showdata.getNON_MEM_CONTCT());
           
           if(showdata.getMem_flag()==0){
               Adress.setText(showdata.getNON_MEM_ADDR());
           }
           else{
               Adress.setText(showdata.getCustAddress());
           }
           
           hallName.setText(showdata.gethall_name());
           capacity.setText(""+showdata.getMAX_CAPACITY());
           tariff.setText(decimalFormat.format(showdata.getCHARGES()));
           tax_1.setText(showdata.getLUXURYTAX());
           tax_2.setText(showdata.getTAX2());
           tax_3.setText(showdata.getTAX3());
           
           if(showdata.getSLOT_FLAG()==1){
               slot_label.setText("(Hourly Booked)");
           }
           if(showdata.getSLOT_FLAG()==2){
               slot_label.setText("(Half Day)");
           }
           if(showdata.getSLOT_FLAG()==3){
               slot_label.setText("(Full Day)");
           }
           
           if(showdata.getMem_flag()==1){
                mem_label.setText("( Booked For :- Member )");
                mem_label.setForeground(Color.BLUE);
           }
           else{
               mem_label.setText("( Booked For :- Non-Member )");
               mem_label.setForeground(Color.BLUE);
           }
           
           bookingDate.setText(showdata.getBOOKING_DATE());
           bookingSlot.setText(showdata.getTIMING_SLOTS());
           
           hall_detail_panel.setVisible(true);
           main_panel.setVisible(false);
           
           
           
           
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       main_panel.setVisible(true);
       hall_detail_panel.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      if(note.getText()!=null && note.getText().trim().length()>0){
           mes.setVisible(false);
           note_panel.setVisible(false);
          int row = jTable1.getSelectedRow();
            HallStatusInfo showdata = bookstatus.getHallList().get(row);
        
            int status = showdata.getStatus();
            String Hall_ID = showdata.getHALL_ID();
            String Booking_Date = showdata.getBOOKING_DATE();
            String Member_id = showdata.getMEMBER_ID();
            String Booking_slot = showdata.getTIMING_SLOTS();
            String Cancel_note = note.getText();
            String Booking_id = showdata.getId();
            
            try {
                int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE hall_booked_details  SET FLAG='2' , REQ_APPROVE_BY=? , REQ_APPROV_DATE=? , REQ_APPROV_HOST=? , CANCEL_REASON=?  WHERE HALL_NAME=? AND MEMBERNAME=? AND BOOKING_DATE=? AND BOOKING_SLOT=?  "
                                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  , Datas.STRING  , Datas.STRING ,Datas.STRING , Datas.STRING  , Datas.STRING})).exec
                                                     (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , Cancel_note , Hall_ID ,Member_id ,   Booking_Date , Booking_slot});
           
            main_panel.setVisible(true);
            note_panel.setVisible(false);
            JOptionPane.showMessageDialog(this, "Request Canceled..! ", "Success", JOptionPane.INFORMATION_MESSAGE);    
            loaddata();
           
            
           } 
           catch (BasicException ex) {
                Logger.getLogger(HallBookingRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      else{
          mes.setVisible(true);
      }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       note_panel.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Adress;
    private javax.swing.JLabel NName;
    private javax.swing.JLabel b_slot_label;
    private javax.swing.JLabel bookby_label;
    private javax.swing.JLabel bookdate_label;
    private javax.swing.JLabel bookingDate;
    private javax.swing.JLabel bookingSlot;
    private javax.swing.JLabel capacity;
    private javax.swing.JLabel contactNo;
    private javax.swing.JLabel hallName;
    private javax.swing.JPanel hall_detail_panel;
    private javax.swing.JLabel hall_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel mName;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel mem_label;
    private javax.swing.JLabel memberNo;
    private javax.swing.JLabel mes;
    private javax.swing.JTextArea note;
    private javax.swing.JInternalFrame note_panel;
    private javax.swing.JLabel pending_label;
    private javax.swing.JLabel slot_label;
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
        
        bookstatus = HallBookingRequestTableModel.loadInstanceBooked_Hall_Status(m_App);
        showPanelInfo(bookstatus);
        
        int pending_list = bookstatus.getHallSize();
        if(pending_list>0){
            pending_label.setText("( * "+pending_list+ " Request Pending. )");
            pending_label.setVisible(true);
        }
        else{
            pending_label.setVisible(false);
        }
    }
    
     public void showPanelInfo(HallBookingRequestTableModel bookedHall){
         
          jTable1.setModel(bookedHall.getTableModel());
         
         
         
         
     }
    
     public void reset(){
         hall_detail_panel.setVisible(false);
         main_panel.setVisible(true);
     }
     
    
}
