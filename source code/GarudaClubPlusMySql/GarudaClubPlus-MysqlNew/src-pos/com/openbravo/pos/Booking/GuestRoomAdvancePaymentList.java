/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.openbravo.pos.Booking.AdvanceRecieveTableModel.RoomAdvInfo;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;


public class GuestRoomAdvancePaymentList extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private AdvanceRecieveTableModel AdvanceRecv;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    private TicketParser m_TTP;
    
    
    public GuestRoomAdvancePaymentList() {
        initComponents();
        main_panel.setVisible(true);
        detail_panel.setVisible(false);
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        detail_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tot_amt = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        amt_recv = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        due_amt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        room_type = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        check_in = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        no_of_rooms = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        check_out = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        mem_name = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        nonmemname = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mem_no = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

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

        jButton2.setText("Refund Balance Amount");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/viewmag.png"))); // NOI18N
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
        jLabel1.setText("Guest Room Booked Details");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("(Booked By :-)");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total Amount :");

        tot_amt.setText("jLabel8");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Payment Recieved :");

        amt_recv.setText("jLabel8");

        jLabel13.setText("--------------------------------------------------------");

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
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(due_amt)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tot_amt))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amt_recv)))
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tot_amt)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(amt_recv)
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

        tot_amt.setForeground(Color.RED);
        amt_recv.setForeground(Color.RED);
        due_amt.setForeground(Color.RED);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        room_type.setText("jLabel8");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Room Type :");

        check_in.setText("jLabel8");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Check In Date : ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("No of Rooms :");

        no_of_rooms.setText("jLabel12");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Check Out Date : ");

        check_out.setText("jLabel20");

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
                        .addComponent(room_type))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_in))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(no_of_rooms))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_out)))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(room_type)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(check_in))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(check_out))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(no_of_rooms))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        mem_name.setText("jLabel14");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Member Name :");

        contact.setText("jLabel16");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Contact No : ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Non Member Name : ");

        nonmemname.setText("jLabel10");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Member Name :");

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
                        .addComponent(mem_name))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nonmemname))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mem_no))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contact)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mem_name)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(mem_no))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(nonmemname))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(contact))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
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
                .addContainerGap(39, Short.MAX_VALUE)
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
                .addGroup(detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detail_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(detail_panelLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
            int row = jTable1.getSelectedRow(); 
            RoomAdvInfo showData = AdvanceRecv.getRoomList().get(row);
            
            
            String ID = showData.getBOOKING_ID();
            room_type.setText(showData.getROOMTYPE());
            check_in.setText(showData.getCHECK_IN_DATE());
            check_out.setText(showData.getCHECK_OUT_DATE());
            no_of_rooms.setText(""+showData.getNO_OF_ROOMS());
            
            mem_name.setText(showData.getMEMBERNAME());
            mem_no.setText(showData.getMEMBER_NO());
            
            tot_amt.setText(decimalFormat.format(showData.getTOTAL_AMOUNT()));
            amt_recv.setText(decimalFormat.format(showData.getBAL_AMT()));
            due_amt.setText(decimalFormat.format(showData.getTOTAL_AMOUNT()-showData.getBAL_AMT()));
            
            List<Object> Guest_list = new ArrayList<Object>();
            if(showData.getMEMBER_FLAG()!=1){
                 Guest_list =   AdvanceRecv.getGuestRoom_Guest_Details(m_App, ID);
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

    String Rfd_Voucher_No;
    Double AdvanceRecvd;
    Double BalAmt;
    Double TotalCharge;
    
    String Booking_SeqNo; 
    String CustName;  
    String AdvanceID;
    String MemberNo ;
    String CustomerID;
    String RoomType;
    String TRANSREF;
    String NARRATION;
    String UserAccount;
    String Advance_Acct_ID;
    String RoomTypeID;
    String CheckInDate ;
    String CheckOutDate;
    String AdvancePayDate;
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      if(jTable1.getSelectedRow()!=-1){
            int row = jTable1.getSelectedRow(); 
            RoomAdvInfo showData = AdvanceRecv.getRoomList().get(row);
            
            String ID = showData.getBOOKING_ID();
            
            Rfd_Voucher_No = "";
            
            AdvanceRecvd = showData.getADVANCE_RECV();
            BalAmt = showData.getBAL_AMT();
            TotalCharge = showData.getTOTAL_AMOUNT();
            
            
            Booking_SeqNo = showData.getBooking_SEQ_no();
            CustName = showData.getMEMBERNAME();
            
            AdvanceID = showData.getId();
            MemberNo = showData.getMEMBER_NO();
            RoomType = showData.getROOMTYPE();
            CustomerID = showData.getCustomerID();
            UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
            RoomTypeID = showData.getRoomTypeID();
            Advance_Acct_ID = getAdvance_Acct_Room(m_App, RoomTypeID);
            CheckInDate = showData.getCHECK_IN_DATE();
            CheckOutDate = showData.getCHECK_OUT_DATE();
            
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
                                                (new Object[]{UUID.randomUUID().toString() ,Rfd_Voucher_No ,  CustomerID , MemberNo , Booking_SeqNo ,TotalCharge , AdvanceRecvd ,Refund_Amt , AdvanceID , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() , new Date()  , RoomType }); 

                               
                            
                            int y = new StaticSentence(m_App.getSession(), "UPDATE guestroom_advance_payment SET BAL_AMT=BAL_AMT-? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {Refund_Amt , AdvanceID});

                          
                            
                            
                            
                            

                              TRANSREF = MemberNo + " , Refund Voucher No : "+Rfd_Voucher_No+" # amt :"+Refund_Amt+" /- for Guest Room  againts booking no: "+Booking_SeqNo ;     
                              NARRATION = "Refund of "+Refund_Amt+ " /- to member : "+MemberNo+" . Voucher no "+Rfd_Voucher_No+ " for Guest room  againts booking no: "+Booking_SeqNo;




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
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amt_recv;
    private javax.swing.JLabel check_in;
    private javax.swing.JLabel check_out;
    private javax.swing.JLabel contact;
    private javax.swing.JPanel detail_panel;
    private javax.swing.JLabel due_amt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel mem_name;
    private javax.swing.JLabel mem_no;
    private javax.swing.JLabel no_of_rooms;
    private javax.swing.JLabel nonmemname;
    private javax.swing.JLabel room_type;
    private javax.swing.JLabel tot_amt;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
         Boolean flag = m_App.getAppUserView().getUser().hasPermission("RefundPermission");
         if(flag){
             jButton2.setEnabled(true);
         }
         else{
              jButton2.setEnabled(false); 
         }
         
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
        
       AdvanceRecv = AdvanceRecieveTableModel.LoadGuestRoomAdvanceRecieve(m_App);
       showPanelInfo(AdvanceRecv); 
        
    }
    public void showPanelInfo(AdvanceRecieveTableModel AdvanceRecv){
         jTable1.setModel(AdvanceRecv.getTableModel2());
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
    
    
     
     
     // GENERATE JASPER FOR VOUCHER 
       public void generateRefundVoucher()
        {
         
            Map reportparams = new HashMap();
            List<Object> list = new ArrayList<Object>();
             
             DataSourceProvider data1 = new DataSourceProvider(list);
           
             String Message=null;
             
             String RoomType1 = RoomType;
             Double refundAmt = BalAmt ;
             
           //  Message = "Dear Member, \n        Refund of Rs. "+refundAmt +" /-  after adjustment of  all advances recieved for Hall / Room Booking.  \n (Booking ID : "+Booking_seq_id+ ") Booked By "+BillingName+ ". "  ;
             reportparams.put("V_NO",Rfd_Voucher_No );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             reportparams.put("MEM_NAME", CustName);
             reportparams.put("M_NO", MemberNo);
            // reportparams.put("MAIN_TEXT", Message);
             reportparams.put("CREATEBY",  m_App.getAppUserView().getUser().getName());
             reportparams.put("CRHOST" , m_App.getProperties().getHost());
             reportparams.put("MEMSIGN", CustName);
             
             reportparams.put("HALLNAME", RoomType);
             reportparams.put("bookingseqno", Booking_SeqNo);
             reportparams.put("bookingdateinfo", "");
             reportparams.put("advanceamt", decimalFormat.format(AdvanceRecvd));
             reportparams.put("totalbillamount", decimalFormat.format(TotalCharge));
            
             reportparams.put("cancellationperc", "0.00%");
             reportparams.put("fixedCharge", "0.00");
             reportparams.put("cancellationCharge", "0.00");
             reportparams.put("refundamt", decimalFormat.format(refundAmt));
             
             
             
             
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/Refund_voucher.jrxml", reportparams, false, data1, true, null); 


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
            script.put("text1", RoomType);
            
            script.put("label_2", "Check In Date :");
            script.put("text2",CheckInDate);
            
            script.put("label_3", "Check Out Date :");
            script.put("text3", CheckOutDate);
            
          
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
       
       
       
       
       
       
        // GET ADVANCE ACCOUNT FOR ROOM 
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
       
       
       
     
    
    
}
