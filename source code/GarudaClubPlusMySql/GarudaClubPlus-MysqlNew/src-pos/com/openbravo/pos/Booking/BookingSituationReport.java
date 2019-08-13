
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
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
import com.openbravo.pos.sms.DataSourceForSMSDelReport;
import java.awt.Color;
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


public class BookingSituationReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private String ReportHeader;
    private BookingSituationHallTableModel Bkng_hallModel;
    private BookingSituationHallTableModel Bkng_RoomModel;
    
    private List<BookingSituationHallTableModel.HallStatusInfo> Hall_list;
    private List<BookingSituationHallTableModel.RoomStatusInfo> Room_list;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicSales m_dlSales;
    private DataLogicFacilities dmang;
    
    
    
    
    public BookingSituationReport() {
        initComponents();
        report_btn.setEnabled(false);
        header_text.setText(null);
        month_radio.setSelected(true);
        report_btn_room.setEnabled(false);
       
        member_check.setSelected(false);
        member_panel.setVisible(false);
        member_panel.setVisible(false);
        
        
    }
     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        report_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        month_radio = new javax.swing.JRadioButton();
        period_radio = new javax.swing.JRadioButton();
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        Month_cal = new javax.swing.JButton();
        fromdate_cal = new javax.swing.JButton();
        todate_cal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        header_text = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        member_check = new javax.swing.JCheckBox();
        member_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        memno = new javax.swing.JTextField();
        mname = new javax.swing.JTextField();
        jButton2_hall = new javax.swing.JButton();
        report_btn_room = new javax.swing.JButton();

        report_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        report_btn.setForeground(new java.awt.Color(0, 51, 51));
        report_btn.setText("Generate Report For Hall Booking");
        report_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_btnActionPerformed(evt);
            }
        });

        reset_btn.setForeground(new java.awt.Color(153, 0, 51));
        reset_btn.setText("Reset");
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Miriam Fixed", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Monthly and Periodic report for booked halls/ Rooms.");

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

        Month_cal.setText("Month");
        Month_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calActionPerformed(evt);
            }
        });

        fromdate_cal.setText("From Date");
        fromdate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calActionPerformed(evt);
            }
        });

        todate_cal.setText("To Date");
        todate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calActionPerformed(evt);
            }
        });

        header_text.setColumns(20);
        header_text.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        header_text.setRows(5);
        jScrollPane1.setViewportView(header_text);
        header_text.setForeground(Color.BLUE);

        jLabel2.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel2.setText("Report Header ");

        member_check.setText("Search by Member Name ");
        member_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                member_checkItemStateChanged(evt);
            }
        });

        member_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Member No: ");

        jLabel6.setText("Member Name :");

        memno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memnoKeyPressed(evt);
            }
        });

        jButton2_hall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton2_hall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_hallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout member_panelLayout = new javax.swing.GroupLayout(member_panel);
        member_panel.setLayout(member_panelLayout);
        member_panelLayout.setHorizontalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2_hall, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)))
                .addGap(120, 120, 120))
        );
        member_panelLayout.setVerticalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2_hall)
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        report_btn_room.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        report_btn_room.setForeground(new java.awt.Color(0, 51, 51));
        report_btn_room.setText("Generate Report For Room Booking");
        report_btn_room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_btn_roomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(month_radio)
                            .addComponent(period_radio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(todate_text, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(fromdate_text))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(report_btn_room)
                        .addGap(158, 158, 158)
                        .addComponent(report_btn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(member_check, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(period_radio)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(todate_cal)))
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(member_check)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_btn)
                    .addComponent(report_btn_room, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        report_btn.setForeground(Color.BLUE);
        fromdate_text.setEditable(false);
        todate_text.setEditable(false);
        report_btn_room.setForeground(Color.BLUE);

        jTabbedPane1.addTab("Monthly and Periodic report for booked halls/ Rooms.", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 899, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_btnActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
               
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;
                
                try {
                     FrmDate =  (Date) Formats.DATE.parseValue(fromdate_text.getText());
                     ToDate =  (Date) Formats.DATE.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ReportHeader = header_text.getText();
                
                crr_date_str =  Formats.TIMESTAMP.formatValue(CurrDate);
                
                // GENERATING REPORT FOR BOOKED HALLL STATUS
                Map reportparams = new HashMap();
                
                
                
               if(member_check.isSelected()) {
                   if(memno.getText()!=null && memno.getText().trim().length()>0){
                  
                        // SEARCH BY MEMBER ID  ...... #aakasH
                   
                        try {
                                Bkng_hallModel = BookingSituationHallTableModel.loadInstanceBooked_Hall_StatusByHallID(m_App, FrmDate , ToDate , customerInfo.getId() );
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

                          Hall_list  =  (List<BookingSituationHallTableModel.HallStatusInfo>) Bkng_hallModel.getHallList();

                         DataSourceProvider data1 = new DataSourceProvider(Hall_list);
                         DataSourceForHallBookingSituation dsfc = new DataSourceForHallBookingSituation(Hall_list);
                         data1.setDataSource(dsfc);

                         reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                         reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                         String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("ReportHeader",RPH);
                         reportparams.put("date",crr_date_str);

                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallBookingReport.jrxml", reportparams, false, data1, true, null); 

                   
                        
                     }
                   else{
                        JOptionPane.showMessageDialog(this, "Please enter Member Details..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                   }
               }
               else{
                   
                   //   FOR ALL MEMBERS ....   #AAKASH
                   
                    try {
                        Bkng_hallModel = BookingSituationHallTableModel.loadInstanceBooked_Hall_Status(m_App, FrmDate , ToDate );
                      } 
                  
                    catch (BasicException ex) {
                         Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                  Hall_list  =  (List<BookingSituationHallTableModel.HallStatusInfo>) Bkng_hallModel.getHallList();
                   
                 DataSourceProvider data1 = new DataSourceProvider(Hall_list);
                 DataSourceForHallBookingSituation dsfc = new DataSourceForHallBookingSituation(Hall_list);
                 data1.setDataSource(dsfc);
                
                 reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                 reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                 String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                 reportparams.put("ReportHeader",RPH);
                 reportparams.put("date",crr_date_str);
                 
                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallBookingReport.jrxml", reportparams, false, data1, true, null); 


                   
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

    private void fromdate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_calActionPerformed
         Date date;

        try {
            date = (Date) Formats.DATE.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
        
            fromdate_text.setText(Formats.DATE.formatValue(date));
            todate_text.setText(null);
            header_text.setText(null);
        }
        
                      
    }//GEN-LAST:event_fromdate_calActionPerformed

    private void Month_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_calActionPerformed
         Date date;

        try {
            date = (Date) Formats.DATE.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate_text.setText(Formats.DATE.formatValue(date));
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate_text.setText(Formats.DATE.formatValue(date));
            
            ReportHeader = "Status of Booking for All Members ";
            header_text.setText(ReportHeader);
            report_btn.setEnabled(true);
            report_btn_room.setEnabled(true);
            
        }
    }//GEN-LAST:event_Month_calActionPerformed

    private void todate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calActionPerformed
       Date date;
       if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
           
       
        try {
            date = (Date) Formats.DATE.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {

            todate_text.setText(Formats.DATE.formatValue(date));
            
            
            ReportHeader = "Status of Booking for All Members ";
            header_text.setText(ReportHeader);
            report_btn.setEnabled(true);
            report_btn_room.setEnabled(true);
        }
       }
       else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_todate_calActionPerformed

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

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
       reset();
    }//GEN-LAST:event_reset_btnActionPerformed

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

    private void report_btn_roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_btn_roomActionPerformed
          if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;
                
                try {
                     FrmDate =  (Date) Formats.DATE.parseValue(fromdate_text.getText());
                     ToDate =  (Date) Formats.DATE.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ReportHeader = header_text.getText();
                
                crr_date_str =  Formats.TIMESTAMP.formatValue(CurrDate);
                
                // GENERATING REPORT FOR BOOKED HALLL STATUS
                Map reportparams = new HashMap();
                
                
                
                
                if(member_check.isSelected()){
                    if(memno.getText()!=null && memno.getText().trim().length()>0){
                    
                                try {
                                    Bkng_RoomModel = BookingSituationHallTableModel.loadInstanceBooked_Rooms_Status_MemID(m_App, FrmDate , ToDate , customerInfo.getId() );
                                } 

                              catch (BasicException ex) {
                                     Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                               }

                              Room_list  =  (List<BookingSituationHallTableModel.RoomStatusInfo>) Bkng_RoomModel.getRoomList();


                             DataSourceProvider data1 = new DataSourceProvider(Room_list);
                             DataSourceForRoomBookingReport dsfc = new DataSourceForRoomBookingReport(Room_list);
                             data1.setDataSource(dsfc);

                             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                             String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                             reportparams.put("ReportHeader",RPH);
                             reportparams.put("date",crr_date_str);

                            JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallBookingReport.jrxml", reportparams, false, data1, true, null); 

                    }
                    else{
                          JOptionPane.showMessageDialog(this, "Please enter Member Details..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }
                    
                    
                    
                    
                    
                }
                else{
                    
                   try {
                        Bkng_RoomModel = BookingSituationHallTableModel.loadInstanceBooked_Rooms_Status(m_App, FrmDate , ToDate );
                    } 
                  
                   catch (BasicException ex) {
                         Logger.getLogger(BookingSituationReport.class.getName()).log(Level.SEVERE, null, ex);
                   }
                        
                  Room_list  =  (List<BookingSituationHallTableModel.RoomStatusInfo>) Bkng_RoomModel.getRoomList();
           
                
                 DataSourceProvider data1 = new DataSourceProvider(Room_list);
                 DataSourceForRoomBookingReport dsfc = new DataSourceForRoomBookingReport(Room_list);
                 data1.setDataSource(dsfc);
                
                 reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                 reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                  String RPH = ReportHeader +" \n From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                 reportparams.put("ReportHeader",RPH);
                 reportparams.put("date",crr_date_str);
                 
                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallBookingReport.jrxml", reportparams, false, data1, true, null); 

                    
                    
                    
                    
                }
                
                
                
                
              
                  
                  
                
                
                
                
                
                
                
                
                
                
            }
            else{
                    JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
                JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_report_btn_roomActionPerformed

    private void member_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_member_checkItemStateChanged
      if(member_check.isSelected()){
          member_panel.setVisible(true);
       }
       else{
          
          memno.setText(null);
          member_panel.setVisible(false);
          mname.setText(null);
    }
    }//GEN-LAST:event_member_checkItemStateChanged

    private void jButton2_hallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_hallActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname.setText(customerInfo.toString());
                memno.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton2_hallActionPerformed

    private void memnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memnoKeyPressed
          // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno.getText().toUpperCase());
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
    }//GEN-LAST:event_memnoKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Month_cal;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JTextArea header_text;
    private javax.swing.JButton jButton2_hall;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox member_check;
    private javax.swing.JPanel member_panel;
    private javax.swing.JTextField memno;
    private javax.swing.JTextField mname;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JButton report_btn;
    private javax.swing.JButton report_btn_room;
    private javax.swing.JButton reset_btn;
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
    }

    public Object getBean() {
      return this;
    }
    
    
    
    public void loaddata(){
        
        groupButton();
        
    }
    
    public void reset(){
        header_text.setText(null);
        fromdate_text.setText(null);
        todate_text.setText(null);
        month_radio.setSelected(true);
   
        
        month_radio.setSelected(true);
        
        report_btn.setEnabled(false);
        report_btn_room.setEnabled(false);
        member_check.setSelected(false);
        member_check.setSelected(false);
        
        
        
    }
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
   
    }
    
}
