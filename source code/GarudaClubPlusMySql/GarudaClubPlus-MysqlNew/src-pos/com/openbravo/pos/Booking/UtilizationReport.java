
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
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
import net.sf.jasperreports.engine.JasperPrint;


public class UtilizationReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicSales m_dlSales;
    private DataLogicFacilities dmang;
    private String ReportHeader;
    private hallTableModel Hall;
    private List<hallTableModel.HallTableInfo> halllist ;
    private ComboBoxValModel HallListModel ;
    private UtilizationReportTableModel utilizationReport_model;
    private UtilizationReportTableModel utilizationReport_model2;
    private UtilizationReportRoomTableModel utilizationReport_model_Room;
    private UtilizationReportRoomTableModel utilizationReport_model_Room2;
    
    private List<UtilizationReportTableModel.HallStatusInfo> Hall_list;
   
    private List<UtilizationReportRoomTableModel.RoomInfoAll> Room_info_List_all;
    private List<UtilizationReportTableModel.HallStatusInfo_MemID> Hall_list_with_ID;
    private List<Object> roomType_list ;
    private GuestRoomTableModel RoomTableModel;
    private ComboBoxValModel RoomTypeListModel ; 
    
    public UtilizationReport() {
        initComponents();
        hall_select.setSelected(false);
        month_radio.setSelected(true);
        report_btn.setEnabled(false);
        hall_combo.setVisible(false);
        gen_report_room.setEnabled(false);
        room_combo.setVisible(false);
        
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Miriam Fixed", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Monthly and Periodic report for Hall/Room Utilization");

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
        hall_select.setText("Select by Hall Name ");
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
        room_select.setText("Select by Room Type");
        room_select.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                room_selectItemStateChanged(evt);
            }
        });

        room_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

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
                        .addGap(51, 51, 51)
                        .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(gen_report_room, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5))))
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(todate_text)
                                            .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(main_panelLayout.createSequentialGroup()
                                                .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(period_radio)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(room_select)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(room_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(hall_select)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hall_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(period_radio))
                .addGap(6, 6, 6)
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
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hall_select)
                    .addComponent(room_select)
                    .addComponent(hall_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gen_report_room, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reset_btn)))
                .addGap(41, 41, 41))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);

        jTabbedPane1.addTab("Utilization Report for Hall / Room Bookings", main_panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
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
            
            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.MONTH, 1);
            
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            
            todate_text.setText(Formats.TIMESTAMP.formatValue(date));

            ReportHeader = "Status of Booking for All Members ";
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

                ReportHeader = "Status of Booking for All Members ";
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
            todate_text.setText(null);
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
            header_text.setText(null);
        }
        else{
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
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
            header_text.setText(null);
            todate_text.setText(null);
        }
        else{
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);
            fromdate_text.setText(null);
            header_text.setText(null);
            todate_text.setText(null);
        }
    }//GEN-LAST:event_period_radioItemStateChanged

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
                           Hall_list_with_ID = new ArrayList<UtilizationReportTableModel.HallStatusInfo_MemID>();
                         
                         try {
                                utilizationReport_model = UtilizationReportTableModel.loadInstanceBooked_Hall_Status_HallID(m_App, FrmDate , ToDate , HallName );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_list_with_ID  =  (List<UtilizationReportTableModel.HallStatusInfo_MemID>) utilizationReport_model.getHallList_withID();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Hall_list_with_ID);
                         DataSourceForUtilizationReport1 dsfc = new DataSourceForUtilizationReport1(Hall_list_with_ID);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("hallName",hall_combo.getSelectedItem().toString());
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallUtilizationReport.jrxml", reportparams, false, data1, true, null); 
                         
            
                        
                        
                        }
                        else{
                             JOptionPane.showMessageDialog(this, "Select Hall First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
                        
                        
                        
                    }     
                    else{
                        
                        // FOR ALL HALLS BOOKING  UTILIZATION... #AAKASH
                        
                         Hall_list = new ArrayList<UtilizationReportTableModel.HallStatusInfo>();
                         
                         try {
                                utilizationReport_model = UtilizationReportTableModel.loadInstanceBooked_Hall_Status(m_App, FrmDate , ToDate  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Hall_list  =  (List<UtilizationReportTableModel.HallStatusInfo>) utilizationReport_model.getHallList();
                         
                         // LOAD FOR HALL DETAILS ...
                      
                         
                         DataSourceProvider data1 = new DataSourceProvider(Hall_list);
                    
                         DataSourceForUtilizationReport_Hall dsfc = new DataSourceForUtilizationReport_Hall(Hall_list);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("hallName"," All Hall Types");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallUtilizationReport.jrxml", reportparams, false, data1 ,true, null); 
                         
            
                        
                        
                        
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

    private void hall_selectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hall_selectItemStateChanged
       if(hall_select.isSelected()){
           hall_combo.setVisible(true);
           
       }
       else{
           hall_combo.setVisible(false);
           hall_combo.setSelectedIndex(-1);
       }
    }//GEN-LAST:event_hall_selectItemStateChanged

    private void room_selectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_room_selectItemStateChanged
        if(room_select.isSelected()){
            room_combo.setVisible(true);
            
        }
        else{
            room_combo.setVisible(false);
            room_combo.setSelectedIndex(-1);
    }
    }//GEN-LAST:event_room_selectItemStateChanged

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
                            String RoomType_Id = utilizationReport_model_Room2.getRoomTypeId(m_App, roomtype);
                            
                            try {
                                  utilizationReport_model_Room = UtilizationReportRoomTableModel.loadInstanceBooked_Room_Status_RoomID(m_App, FrmDate , ToDate  , RoomType_Id);
                                } 

                               catch (BasicException ex) {
                                   Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                              }
                            Room_info_List_all  =  (List<UtilizationReportRoomTableModel.RoomInfoAll>) utilizationReport_model_Room.getHallList();
                            DataSourceProvider data1 = new DataSourceProvider(Room_info_List_all);
                            DataSourceForUtilizationReportRoomAll dsfc = new DataSourceForUtilizationReportRoomAll(Room_info_List_all);
                            data1.setDataSource(dsfc);
                            Map reportparams = new HashMap();
                            reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                            reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                            String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                            reportparams.put("ReportHeader",RPH);
                            reportparams.put("date",new Date());
                            reportparams.put("hallName",roomtype);
                     
                           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ROOMUtilizationReport.jrxml", reportparams, false, data1, true, null); 

                     
                     
                     
                     
                       }
                       else{
                           JOptionPane.showMessageDialog(this, "Select Room Type First..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                       }
                     
                 }
                 else{
                
                    //  FOR ALL TYPES OF ROOMS 
                     
                     
                     
                     Room_info_List_all = new ArrayList<UtilizationReportRoomTableModel.RoomInfoAll>();
                         
                         try {
                                utilizationReport_model_Room = UtilizationReportRoomTableModel.loadInstanceBooked_Rooms_Status(m_App, FrmDate , ToDate  );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         Room_info_List_all  =  (List<UtilizationReportRoomTableModel.RoomInfoAll>) utilizationReport_model_Room.getHallList();
                         
                         DataSourceProvider data1 = new DataSourceProvider(Room_info_List_all);
                         DataSourceForUtilizationReportRoomAll dsfc = new DataSourceForUtilizationReportRoomAll(Room_info_List_all);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",new Date());
                         reportparams.put("hallName"," All Type of Rooms");
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ROOMUtilizationReport.jrxml", reportparams, false, data1, true, null); 
                         
            
                  
                     
                     
                
                
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Month_cal;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton gen_report_room;
    private javax.swing.JComboBox hall_combo;
    private javax.swing.JCheckBox hall_select;
    private javax.swing.JTextArea header_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JButton report_btn;
    private javax.swing.JButton reset_btn;
    private javax.swing.JComboBox room_combo;
    private javax.swing.JCheckBox room_select;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    // End of variables declaration//GEN-END:variables

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
           dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
           dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
           m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
           RoomTableModel = (GuestRoomTableModel)m_App.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
           utilizationReport_model_Room2 = (UtilizationReportRoomTableModel) m_App.getBean("com.openbravo.pos.Booking.UtilizationReportRoomTableModel");
    }

    public Object getBean() {
        return this;
    }
    
    
    public void loaddata() throws BasicException{
        groupButton();
        
        
         
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
        
    }
    
    
    
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
   
    }
     
     
     
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
        
     }
    
}
