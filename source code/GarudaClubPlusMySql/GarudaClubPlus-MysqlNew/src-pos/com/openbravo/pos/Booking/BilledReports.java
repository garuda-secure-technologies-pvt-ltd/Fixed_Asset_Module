
package com.openbravo.pos.Booking;

import java.util.Date;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;

public class BilledReports extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    
    private String ReportHeader;
    private AppView m_App;
    private List<BilledReportsTableModel.RoomBillInfo> Room_info_List_all;
    private BilledReportsTableModel BilledReports_Table_Model;
     private List<BilledReportsTableModel.HallBillInfo> Hall_info_List_all;
     
     
    private List<hallTableModel.HallTableInfo> halllist ;
    private ComboBoxValModel HallListModel ;
    private hallTableModel Hall;
    private List<Object> roomType_list ;
    private GuestRoomTableModel RoomTableModel;
    private ComboBoxValModel RoomTypeListModel ; 
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicSales m_dlSales;
    private DataLogicFacilities dmang;
    public  String Mesg = null;
    private CheckInTableModel CheckInTable_Model;
    private HallBillModel hallbill_Model;
    private List<HallBillModel.HallBillInfo> hall_Bill_list;
    private List<GuestRoomBillModel.RoomAdvInfo> GR_BillList; 
    private GuestRoomBillModel GRBillModel;
     
    public BilledReports() {
        initComponents();
        
        hall_select.setSelected(false);
        month_radio.setSelected(true);
        report_btn.setEnabled(false);
        hall_combo.setVisible(false);
        gen_report_room.setEnabled(false);
        room_combo.setVisible(false);
        member_check.setSelected(false);
        member_panel2.setVisible(false);
        view_panel.setVisible(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        main_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        Month_cal = new javax.swing.JButton();
        todate_cal = new javax.swing.JButton();
        fromdate_cal = new javax.swing.JButton();
        month_radio = new javax.swing.JRadioButton();
        period_radio = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        header_text = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        hall_select = new javax.swing.JCheckBox();
        hall_combo = new javax.swing.JComboBox();
        report_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        gen_report_room = new javax.swing.JButton();
        room_select = new javax.swing.JCheckBox();
        room_combo = new javax.swing.JComboBox();
        member_panel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        memno2 = new javax.swing.JTextField();
        mname = new javax.swing.JTextField();
        jButton2_hall = new javax.swing.JButton();
        member_check = new javax.swing.JCheckBox();
        viewList_Room_Btn = new javax.swing.JButton();
        viewList_btn_Halls = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fromdate_text2 = new javax.swing.JTextField();
        todate_text2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        view_panel = new javax.swing.JPanel();
        msg_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        billStatement_Reprint = new javax.swing.JButton();
        billReprnt_btn1 = new javax.swing.JButton();
        hall_status = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Miriam Fixed", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Monthly and Periodic report for Hall/Room Billed.");

        Month_cal.setText("Month");
        Month_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calActionPerformed(evt);
            }
        });

        todate_cal.setText("To Date");
        todate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calActionPerformed(evt);
            }
        });

        fromdate_cal.setText("From Date");
        fromdate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calActionPerformed(evt);
            }
        });

        month_radio.setText("Month ");
        month_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_radioItemStateChanged(evt);
            }
        });

        period_radio.setText("Period");
        period_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_radioItemStateChanged(evt);
            }
        });

        header_text.setColumns(20);
        header_text.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        header_text.setRows(5);
        jScrollPane1.setViewportView(header_text);
        header_text.setForeground(Color.BLUE);

        jLabel2.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel2.setText("Report Header ");

        hall_select.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hall_select.setText("Search by Hall Name ");
        hall_select.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hall_selectItemStateChanged(evt);
            }
        });

        hall_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        report_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        report_btn.setForeground(new java.awt.Color(0, 0, 255));
        report_btn.setText("Generate Report For Hall");
        report_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_btnActionPerformed(evt);
            }
        });

        reset_btn.setForeground(new java.awt.Color(255, 51, 51));
        reset_btn.setText("Reset");
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        gen_report_room.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gen_report_room.setForeground(new java.awt.Color(0, 0, 255));
        gen_report_room.setText("Generate Report For Room");
        gen_report_room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gen_report_roomActionPerformed(evt);
            }
        });

        room_select.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        room_select.setText("Search by Room Type");
        room_select.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                room_selectItemStateChanged(evt);
            }
        });

        room_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        member_panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Member No: ");

        jLabel10.setText("Member Name :");

        memno2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno2KeyPressed(evt);
            }
        });

        jButton2_hall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton2_hall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_hallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout member_panel2Layout = new javax.swing.GroupLayout(member_panel2);
        member_panel2.setLayout(member_panel2Layout);
        member_panel2Layout.setHorizontalGroup(
            member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(memno2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(member_panel2Layout.createSequentialGroup()
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2_hall, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)))
                .addGap(120, 120, 120))
        );
        member_panel2Layout.setVerticalGroup(
            member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2_hall)
                    .addGroup(member_panel2Layout.createSequentialGroup()
                        .addGroup(member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(memno2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(member_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        member_check.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        member_check.setText("Search By Member Name ");
        member_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                member_checkItemStateChanged(evt);
            }
        });

        viewList_Room_Btn.setText("View List For Rooms");
        viewList_Room_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewList_Room_BtnActionPerformed(evt);
            }
        });

        viewList_btn_Halls.setText("View List For Halls");
        viewList_btn_Halls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewList_btn_HallsActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel3.setText("From :");

        jLabel4.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel4.setText("To:");

        jLabel5.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel5.setText("From :");

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("To:");

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(room_select)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(room_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(hall_select)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hall_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(member_check)
                                    .addComponent(member_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGap(211, 211, 211)
                                        .addComponent(month_radio))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))))
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(todate_text)
                                            .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(main_panelLayout.createSequentialGroup()
                                                .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(period_radio)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(gen_report_room)
                .addGap(34, 34, 34)
                .addComponent(viewList_Room_Btn)
                .addGap(83, 83, 83)
                .addComponent(report_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewList_btn_Halls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromdate_text2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todate_text2)
                .addGap(224, 224, 224))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(period_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todate_cal)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(todate_text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromdate_text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hall_select)
                    .addComponent(room_select)
                    .addComponent(hall_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(member_check)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(member_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gen_report_room, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_btn)
                    .addComponent(report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewList_Room_Btn)
                    .addComponent(viewList_btn_Halls))
                .addGap(11, 11, 11))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);
        fromdate_text2.setEditable(false);
        todate_text2.setEditable(false);

        jTabbedPane1.addTab("Billed Reports for Hall / Rooms", main_panel);

        msg_label.setFont(new java.awt.Font("Miriam Fixed", 1, 18)); // NOI18N
        msg_label.setForeground(new java.awt.Color(102, 0, 0));
        msg_label.setText("Monthly and Periodic report for Hall/Room Billed.");

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
        jScrollPane2.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        billStatement_Reprint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        billStatement_Reprint.setForeground(new java.awt.Color(0, 0, 153));
        billStatement_Reprint.setText("Bill Statement  Reprint");
        billStatement_Reprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billStatement_ReprintActionPerformed(evt);
            }
        });

        billReprnt_btn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        billReprnt_btn1.setForeground(new java.awt.Color(0, 0, 153));
        billReprnt_btn1.setText("Bill Reprint");
        billReprnt_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billReprnt_btn1ActionPerformed(evt);
            }
        });

        hall_status.setText("hall or room");

        javax.swing.GroupLayout view_panelLayout = new javax.swing.GroupLayout(view_panel);
        view_panel.setLayout(view_panelLayout);
        view_panelLayout.setHorizontalGroup(
            view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view_panelLayout.createSequentialGroup()
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(hall_status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(billReprnt_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(billStatement_Reprint, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(msg_label, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)))
                .addContainerGap())
        );
        view_panelLayout.setVerticalGroup(
            view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(msg_label)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(billStatement_Reprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billReprnt_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(view_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(hall_status)))
                .addGap(29, 29, 29))
        );

        hall_status.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(view_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Month_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
            fromdate_text2.setText(Formats.TIMESTAMP.formatValue(date));
            
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.add(Calendar.MONTH, 1);
            
            cal1.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate_text.setText(Formats.TIMESTAMP.formatValue(date));
            todate_text2.setText(Formats.TIMESTAMP.formatValue(date));
            
            ReportHeader = "List of billed report for All Members ";
            header_text.setText(ReportHeader);
            report_btn.setEnabled(true);
            gen_report_room.setEnabled(true);

        }
    }//GEN-LAST:event_Month_calActionPerformed

    private void todate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calActionPerformed
        Date date;
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                todate_text.setText(Formats.TIMESTAMP.formatValue(date));
                todate_text2.setText(Formats.TIMESTAMP.formatValue(date));
                ReportHeader = "List of billed report for All Members ";
                header_text.setText(ReportHeader);
                report_btn.setEnabled(true);
                gen_report_room.setEnabled(true);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_todate_calActionPerformed

    private void fromdate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
            fromdate_text2.setText(Formats.TIMESTAMP.formatValue(date));
            todate_text.setText(null);
            todate_text2.setText(null);
            header_text.setText(null);
        }
    }//GEN-LAST:event_fromdate_calActionPerformed

    private void month_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_radioItemStateChanged
        if(month_radio.isSelected()){
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);

            fromdate_text.setText(null);
            todate_text.setText(null);
            fromdate_text2.setText(null);
            todate_text2.setText(null);
            header_text.setText(null);
        }
        else{
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
            fromdate_text2.setText(null);
            todate_text2.setText(null);
            header_text.setText(null);
            todate_text.setText(null);
        }
    }//GEN-LAST:event_month_radioItemStateChanged

    private void period_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_radioItemStateChanged
        if(period_radio.isSelected()){
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
            fromdate_text2.setText(null);
            todate_text2.setText(null);
            header_text.setText(null);
            todate_text.setText(null);
        }
        else{
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);
            fromdate_text.setText(null);
            header_text.setText(null);
            fromdate_text2.setText(null);
            todate_text2.setText(null);
            todate_text.setText(null);
        }
    }//GEN-LAST:event_period_radioItemStateChanged

    private void hall_selectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hall_selectItemStateChanged
        if(hall_select.isSelected()){
            hall_combo.setVisible(true);
            member_check.setSelected(false);
        }
        else{
            hall_combo.setVisible(false);
            hall_combo.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_hall_selectItemStateChanged

    private void report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_btnActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;

                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(hall_select.isSelected()){
                    if(hall_combo.getSelectedIndex()!=-1){

                        // FOR PERTICULAR  HALL BOOKING  UTILIZATION... #AAKASH
                        String HallName = hall_combo.getSelectedItem().toString();
                      
                        
                         Hall_info_List_all = new ArrayList<BilledReportsTableModel.HallBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadHallBills_HallName(m_App, FrmDate , ToDate , HallName  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_info_List_all  =  (List<BilledReportsTableModel.HallBillInfo>) BilledReports_Table_Model.getHallBilledList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Hall_info_List_all);
                         DataSourceForBilledReports_Halls dsfc = new DataSourceForBilledReports_Halls(Hall_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("TITLE"," Hall Billed Details");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/Hall_Biiled_Report.jrxml", reportparams, false, data1, true, null); 
                         
                    
                        
                        
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Select Hall First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }

                }  
                //-------------------------------------------------------------------------------------------------------------
                
                else if(member_check.isSelected()){
                    if(memno2.getText()!=null && memno2.getText().trim().length()>0 && customerInfo!=null){
                    
                        String CustID = customerInfo.getId();
                        
                        
                         Hall_info_List_all = new ArrayList<BilledReportsTableModel.HallBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadHallBills_MemberID(m_App, FrmDate , ToDate , CustID  );
                           } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_info_List_all  =  (List<BilledReportsTableModel.HallBillInfo>) BilledReports_Table_Model.getHallBilledList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Hall_info_List_all);
                         DataSourceForBilledReports_Halls dsfc = new DataSourceForBilledReports_Halls(Hall_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("TITLE"," Hall Billed Details");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/Hall_Biiled_Report.jrxml", reportparams, false, data1, true, null); 
                         
                    
                       
                        
                    }
                    
                    
                    else{
                     JOptionPane.showMessageDialog(this, "Please Select Member First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }
                }
                // ---------------------------------------------------------------------------------------------------------------------
                
                else{

                    // FOR ALL HALLS BOOKING  ... #AAKASH

                  
                     Hall_info_List_all = new ArrayList<BilledReportsTableModel.HallBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadHallBills_All(m_App, FrmDate , ToDate  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_info_List_all  =  (List<BilledReportsTableModel.HallBillInfo>) BilledReports_Table_Model.getHallBilledList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Hall_info_List_all);
                         DataSourceForBilledReports_Halls dsfc = new DataSourceForBilledReports_Halls(Hall_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                          String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("TITLE"," Rooms Billed Details");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/Hall_Biiled_Report.jrxml", reportparams, false, data1, true, null); 
                         
                    
                    
                    
                    
                    
                    
                    
                }
                
               
                
                
                

            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_report_btnActionPerformed

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
        reset();
    }//GEN-LAST:event_reset_btnActionPerformed

    private void gen_report_roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_report_roomActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;

                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(room_select.isSelected()){
                    if(room_combo.getSelectedIndex()!=-1){

                        // FOR PERTICULAR ROOM TYPES .....

                        String roomtype = room_combo.getSelectedItem().toString();
                        
                        Room_info_List_all = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadGuestRoomBilledReport_roomType(m_App, FrmDate , ToDate , roomtype );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<BilledReportsTableModel.RoomBillInfo>) BilledReports_Table_Model.getRoomBillList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Room_info_List_all);
                         DataSourceForBilledReports_Rooms dsfc = new DataSourceForBilledReports_Rooms(Room_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("TITLE"," Rooms Billed Details");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/GuestRoom_Biiled_Report.jrxml", reportparams, false, data1, true, null); 
                         
                        
                        
                        
                        
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Select Room Type First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }

                }
                // -------------------------------------------------------------------------------------------------------------------
                else if(member_check.isSelected()){
                      if(memno2.getText()!=null && memno2.getText().trim().length()>0 && customerInfo!=null){
                    
                    
                       String CustId = customerInfo.getId();
                          
                          
                    
                       Room_info_List_all = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadGuestRoomBilledReport_MemberId(m_App, FrmDate , ToDate , CustId );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<BilledReportsTableModel.RoomBillInfo>) BilledReports_Table_Model.getRoomBillList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Room_info_List_all);
                         DataSourceForBilledReports_Rooms dsfc = new DataSourceForBilledReports_Rooms(Room_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("TITLE"," Rooms Billed Details");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/GuestRoom_Biiled_Report.jrxml", reportparams, false, data1, true, null); 
                            
                          
                          
                          
                    
                    
                      }
                      else{
                          JOptionPane.showMessageDialog(this, "Please Select Member First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
                }
                
                // -----------------------------------------------------------------------------------------------------------------------------
                
                else{

                    //  FOR ALL TYPES OF ROOMS

                       Room_info_List_all = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadGuestRoomBills_All(m_App, FrmDate , ToDate  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<BilledReportsTableModel.RoomBillInfo>) BilledReports_Table_Model.getRoomBillList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Room_info_List_all);
                         DataSourceForBilledReports_Rooms dsfc = new DataSourceForBilledReports_Rooms(Room_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("TITLE"," Rooms Billed Details");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/GuestRoom_Biiled_Report.jrxml", reportparams, false, data1, true, null); 
                         
                        
                    
                    
                    
                }

            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_gen_report_roomActionPerformed

    private void room_selectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_room_selectItemStateChanged
        if(room_select.isSelected()){
            room_combo.setVisible(true);
            member_check.setSelected(false);
        }
        else{
            room_combo.setVisible(false);
            room_combo.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_room_selectItemStateChanged

    private void memno2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno2.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno2.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    mname.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mname.setText(null);
            customerInfo = null;

        }
    }//GEN-LAST:event_memno2KeyPressed

    private void jButton2_hallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_hallActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname.setText(customerInfo.toString());
                memno2.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton2_hallActionPerformed

    private void member_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_member_checkItemStateChanged
        if(member_check.isSelected()){
          member_panel2.setVisible(true);
          room_select.setSelected(false);
          hall_select.setSelected(false);
       }
       else{
          
          memno2.setText(null);
          member_panel2.setVisible(false);
          mname.setText(null);
    }
    }//GEN-LAST:event_member_checkItemStateChanged

    private void viewList_Room_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewList_Room_BtnActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;
                hall_status.setText("ROOM");
                
                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(room_select.isSelected()){
                    if(room_combo.getSelectedIndex()!=-1){

                        // FOR PERTICULAR ROOM TYPES .....

                        String roomtype = room_combo.getSelectedItem().toString();
                        
                        Room_info_List_all = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadGuestRoomBilledReport_roomType(m_App, FrmDate , ToDate , roomtype );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<BilledReportsTableModel.RoomBillInfo>) BilledReports_Table_Model.getRoomBillList();
                         
                         
                         Mesg = header_text.getText();
                         
                         jTable1.setModel(BilledReports_Table_Model.getTableModel());
                         msg_label.setText(Mesg);
                         view_panel.setVisible(true);
                         jTabbedPane1.setVisible(false);
                        
                        
                        
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Select Room Type First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }

                }
                // -------------------------------------------------------------------------------------------------------------------
                else if(member_check.isSelected()){
                      if(memno2.getText()!=null && memno2.getText().trim().length()>0 && customerInfo!=null){
                    
                    
                       String CustId = customerInfo.getId();
                          
                          
                    
                       Room_info_List_all = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadGuestRoomBilledReport_MemberId(m_App, FrmDate , ToDate , CustId );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<BilledReportsTableModel.RoomBillInfo>) BilledReports_Table_Model.getRoomBillList();
                         Mesg = header_text.getText();
                         jTable1.setModel(BilledReports_Table_Model.getTableModel());
                         view_panel.setVisible(true);
                         msg_label.setText(Mesg);
                         jTabbedPane1.setVisible(false);
                          
                          
                    
                    
                      }
                      else{
                          JOptionPane.showMessageDialog(this, "Please Select Member First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
                }
                
                // -----------------------------------------------------------------------------------------------------------------------------
                
                else{

                    //  FOR ALL TYPES OF ROOMS

                       Room_info_List_all = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadGuestRoomBills_All(m_App, FrmDate , ToDate  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<BilledReportsTableModel.RoomBillInfo>) BilledReports_Table_Model.getRoomBillList();
                         
                         
                         Mesg = header_text.getText();
                         jTable1.setModel(BilledReports_Table_Model.getTableModel());
                         view_panel.setVisible(true);
                         msg_label.setText(Mesg);
                         jTabbedPane1.setVisible(false);
                    
                }

            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_viewList_Room_BtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       view_panel.setVisible(false);
       jTabbedPane1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void viewList_btn_HallsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewList_btn_HallsActionPerformed
         if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;
                hall_status.setText("HALL");
                
                
                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(hall_select.isSelected()){
                    if(hall_combo.getSelectedIndex()!=-1){

                        // FOR PERTICULAR  HALL BOOKING  UTILIZATION... #AAKASH
                        String HallName = hall_combo.getSelectedItem().toString();
                      
                        
                         Hall_info_List_all = new ArrayList<BilledReportsTableModel.HallBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadHallBills_HallName(m_App, FrmDate , ToDate , HallName  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_info_List_all  =  (List<BilledReportsTableModel.HallBillInfo>) BilledReports_Table_Model.getHallBilledList();
                         
                         
                         Mesg  = header_text.getText();
                         jTable1.setModel(BilledReports_Table_Model.getTableModel2());
                         msg_label.setText(Mesg);
                         view_panel.setVisible(true);
                         jTabbedPane1.setVisible(false);
                        
                        
                        
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Select Hall First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }

                }  
                //-------------------------------------------------------------------------------------------------------------
                
                else if(member_check.isSelected()){
                    if(memno2.getText()!=null && memno2.getText().trim().length()>0 && customerInfo!=null){
                    
                        String CustID = customerInfo.getId();
                        
                        
                         Hall_info_List_all = new ArrayList<BilledReportsTableModel.HallBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadHallBills_MemberID(m_App, FrmDate , ToDate , CustID  );
                           } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_info_List_all  =  (List<BilledReportsTableModel.HallBillInfo>) BilledReports_Table_Model.getHallBilledList();
                         
                          Mesg  = header_text.getText();
                         jTable1.setModel(BilledReports_Table_Model.getTableModel2());
                         msg_label.setText(Mesg);
                         view_panel.setVisible(true);
                         jTabbedPane1.setVisible(false);
                       
                        
                    }
                    
                    
                    else{
                     JOptionPane.showMessageDialog(this, "Please Select Member First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }
                }
                // ---------------------------------------------------------------------------------------------------------------------
                
                else{

                    // FOR ALL HALLS BOOKING  ... #AAKASH

                  
                     Hall_info_List_all = new ArrayList<BilledReportsTableModel.HallBillInfo>();
                         
                         try {
                               BilledReports_Table_Model  = BilledReportsTableModel.LoadHallBills_All(m_App, FrmDate , ToDate  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_info_List_all  =  (List<BilledReportsTableModel.HallBillInfo>) BilledReports_Table_Model.getHallBilledList();
                         
                         Mesg  = header_text.getText();
                         jTable1.setModel(BilledReports_Table_Model.getTableModel2());
                         msg_label.setText(Mesg);
                         view_panel.setVisible(true);
                         jTabbedPane1.setVisible(false);
                    
                    
                    
                    
                    
                }
          
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_viewList_btn_HallsActionPerformed

    private void billReprnt_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billReprnt_btn1ActionPerformed
       if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
          
           String status = hall_status.getText();
           
           
           if(status.equals("HALL")){
               String Bill_No  = String.valueOf(BilledReports_Table_Model.getTableModel2().getValueAt(Row_Count, 0).toString()).toUpperCase();  
               String avail = CheckInTable_Model.getHallBillID(m_App, Bill_No);
                
               
               if(avail.equals(Bill_No)){
                      Launch_Bill_HALL(Bill_No); 
                  }
                else{
                   JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
               }
            
            
            
        
           }
        
           else{
            
               String Bill_No  = String.valueOf(BilledReports_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString()).toUpperCase();  
                String avail = CheckInTable_Model.getRoomBillID(m_App, Bill_No);
                    if(avail.equals(Bill_No)){
                         Launch_Bill_ROOM(Bill_No); 
                    }
                    else{
                       JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
                    }
         
            
            
            
        
           }
       
       
       
       
       }
       
    }//GEN-LAST:event_billReprnt_btn1ActionPerformed

    private void billStatement_ReprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billStatement_ReprintActionPerformed
         if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
          
           String status = hall_status.getText();
           
           
           if(status.equals("HALL")){
               String Bill_No  = String.valueOf(BilledReports_Table_Model.getTableModel2().getValueAt(Row_Count, 0).toString()).toUpperCase();  
               String avail = CheckInTable_Model.getHallBillID(m_App, Bill_No);
                
               
               if(avail.equals(Bill_No)){
                      Launch_Bill_Statement_HALL(Bill_No); 
                  }
                else{
                   JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
               }
            
            
            
        
           }
        
           else{
            
               String Bill_No  = String.valueOf(BilledReports_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString()).toUpperCase();  
               String avail = CheckInTable_Model.getRoomBillID(m_App, Bill_No);
                   if(avail.equals(Bill_No)){
                         Launch_Bill_Statement_ROOM(Bill_No); 
                    }
                    else{
                       JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
                    }
         
           }
       
       }
    }//GEN-LAST:event_billStatement_ReprintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Month_cal;
    private javax.swing.JButton billReprnt_btn1;
    private javax.swing.JButton billStatement_Reprint;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JTextField fromdate_text2;
    private javax.swing.JButton gen_report_room;
    private javax.swing.JComboBox hall_combo;
    private javax.swing.JCheckBox hall_select;
    private javax.swing.JLabel hall_status;
    private javax.swing.JTextArea header_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2_hall;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JCheckBox member_check;
    private javax.swing.JPanel member_panel2;
    private javax.swing.JTextField memno2;
    private javax.swing.JTextField mname;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JLabel msg_label;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JButton report_btn;
    private javax.swing.JButton reset_btn;
    private javax.swing.JComboBox room_combo;
    private javax.swing.JCheckBox room_select;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    private javax.swing.JTextField todate_text2;
    private javax.swing.JButton viewList_Room_Btn;
    private javax.swing.JButton viewList_btn_Halls;
    private javax.swing.JPanel view_panel;
    // End of variables declaration//GEN-END:variables




 public void reset(){
        header_text.setText(null);
        fromdate_text.setText(null);
        todate_text.setText(null);
        month_radio.setSelected(true);
        month_radio.setSelected(true);
        report_btn.setEnabled(false);
        hall_select.setSelected(false);
        gen_report_room.setEnabled(false);
        room_select.setSelected(false);
        member_check.setSelected(false);
        jTabbedPane1.setVisible(true);
        view_panel.setVisible(false);
         fromdate_text2.setText(null);
         todate_text2.setText(null);
        
        
     }

    public String getTitle() {
         return "";
    }

    public void activate() throws BasicException {
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
        RoomTableModel = (GuestRoomTableModel)m_App.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         CheckInTable_Model = (CheckInTableModel) app.getBean("com.openbravo.pos.Booking.CheckInTableModel");
         GRBillModel = (GuestRoomBillModel) app.getBean("com.openbravo.pos.Booking.GuestRoomBillModel");
    }

    public Object getBean() {
       return this;
    }
    

    public void loaddata() throws BasicException{
        
        
        
        Hall = hallTableModel.loadInstanceHallInfo(m_App);
        halllist = (List<hallTableModel.HallTableInfo>) Hall.getHallPath();
        if(halllist==null){
             halllist = new ArrayList<hallTableModel.HallTableInfo>();
        }
        HallListModel  = new ComboBoxValModel(halllist);
        hall_combo.setModel(HallListModel);    
        
       
        
        roomType_list = new ArrayList<Object>();
        roomType_list = RoomTableModel.RoomType_NamesList_Active(m_App);
        RoomTypeListModel = new ComboBoxValModel(roomType_list);
        room_combo.setModel(RoomTypeListModel);
        
        groupButton();
        
        
    }
    
    
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
   
    }
     
     //  LAUCH BILL FOR HALL ID -----------------------------------------------------------------------------------------------
     
      public void  Launch_Bill_HALL(String Billid){
            
         Date edate = new Date();
         Map reportparam = new HashMap();
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("enddate", edate);
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForHallCheckIn ds= null;
         
        try {
                hallbill_Model = HallBillModel.LoadHallCheckInDetail(m_App, Billid);
             } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             hall_Bill_list  =  (List<HallBillModel.HallBillInfo>) hallbill_Model.getHallInfoList();
           
         ds = new DataSourceForHallCheckIn(hall_Bill_list);
         data1.setDataSource(ds);
         
         JasperPrint jp = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/HallBill.jrxml", reportparam, false, data1, true, "HallBill");
           
     
        }
          
      
      public void  Launch_Bill_Statement_HALL(String Billid){
            
         Date edate = new Date();
          
         Map reportparam = new HashMap();
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("enddate", edate);
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForHallCheckIn ds= null;
         
       
        
         hall_Bill_list = new ArrayList();
         try {
                hallbill_Model = HallBillModel.LoadHallCheckInDetail(m_App, Billid);
             } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             hall_Bill_list  =  (List<HallBillModel.HallBillInfo>) hallbill_Model.getHallInfoList();
           
         ds = new DataSourceForHallCheckIn(hall_Bill_list);
         data1.setDataSource(ds);
         
        
        JasperPrint jp1 = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/HallBillStatment.jrxml", reportparam, false, data1, true, "HallBill");   
            
        }
      
       public void Launch_Bill_ROOM(String Billid){
         Date edate = new Date();
         String newDate = Formats.TIMESTAMP.formatValue(new Date());
         Map reportparam = new HashMap();
         reportparam.put("enddate", edate);
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForGuestRoomCheckIN ds= null;
         
       try {
                GRBillModel = GuestRoomBillModel.LoadGuestRoomCheckInDetail(m_App, "name" , Billid);
             } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             GR_BillList  =  (List<GuestRoomBillModel.RoomAdvInfo>) GRBillModel.getGuestRoomPath();
           
         ds = new DataSourceForGuestRoomCheckIN(GR_BillList);
         data1.setDataSource(ds);
         
        
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("date",newDate);
         
         
         
        JasperPrint jp = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/GuestRm.jrxml", reportparam, false, data1, true, "GuestRoomBill");
     
      } 
      
       public void Launch_Bill_Statement_ROOM(String Billid){
         Date edate = new Date();
         String newDate = Formats.TIMESTAMP.formatValue(new Date());
         Map reportparam = new HashMap();
         reportparam.put("enddate", edate);
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForGuestRoomCheckIN ds= null;
         
        
         try {
                GRBillModel = GuestRoomBillModel.LoadGuestRoomCheckInDetail(m_App, "Name" , Billid);
             } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             GR_BillList  =  (List<GuestRoomBillModel.RoomAdvInfo>) GRBillModel.getGuestRoomPath();
           
         ds = new DataSourceForGuestRoomCheckIN(GR_BillList);
         data1.setDataSource(ds);
         
        
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("date",newDate);
         
         
         
       JasperPrint jp1 = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/GuestRmStatement.jrxml", reportparam, false, data1, true, "GuestRoomBill");  
      } 
      
      
      

}


