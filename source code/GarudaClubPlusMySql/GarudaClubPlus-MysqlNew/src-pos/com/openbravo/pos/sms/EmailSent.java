

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import com.openbravo.format.Formats;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;

public class EmailSent extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    
    private AppView m_App;
    private EmailSentTableModel EmailSent_Table_Model;
    private List<String> GroupNameList = new ArrayList<String>();
    private List<String> MemTypeNameList = new ArrayList<String>();
    private ComboBoxValModel GroupNameModel ; 
    private ComboBoxValModel MemTypeModel ; 
    
    
    
    
    public EmailSent() {
        initComponents();
        mail_panel.setVisible(true);
        month_radio.setSelected(true);
        jTable1.setVisible(false);
        
          groupName_Radio.setVisible(false);
          membertype_Radio.setVisible(false);
          memberno_radio.setVisible(false);
          group_combo.setVisible(false);
          membertype_combo.setVisible(false);
          groupName_Radio.setSelected(true);
          filter_Check.setSelected(false);
          memberno_text.setVisible(false);
          all_radio.setSelected(true);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mail_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        Month_cal = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        loadsentMails = new javax.swing.JButton();
        month_radio = new javax.swing.JRadioButton();
        period_radio = new javax.swing.JRadioButton();
        todate_cal = new javax.swing.JButton();
        fromdate_cal = new javax.swing.JButton();
        groupName_Radio = new javax.swing.JRadioButton();
        membertype_Radio = new javax.swing.JRadioButton();
        memberno_radio = new javax.swing.JRadioButton();
        group_combo = new javax.swing.JComboBox();
        membertype_combo = new javax.swing.JComboBox();
        memberno_text = new javax.swing.JTextField();
        filter_Check = new javax.swing.JRadioButton();
        all_radio = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 54, 231));
        jLabel1.setText("List of Email Sent ");

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

        Month_cal.setText("Month");
        Month_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel5.setText("From :");

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("To:");

        loadsentMails.setForeground(new java.awt.Color(238, 21, 21));
        loadsentMails.setText("Check Sent Mails ");
        loadsentMails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadsentMailsActionPerformed(evt);
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

        groupName_Radio.setText("By Group Name");
        groupName_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupName_RadioItemStateChanged(evt);
            }
        });

        membertype_Radio.setText("Membership Type");
        membertype_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                membertype_RadioItemStateChanged(evt);
            }
        });

        memberno_radio.setText("Membership No.");
        memberno_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memberno_radioItemStateChanged(evt);
            }
        });

        group_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        membertype_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        filter_Check.setText("Filter");
        filter_Check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filter_CheckItemStateChanged(evt);
            }
        });

        all_radio.setText("All");

        javax.swing.GroupLayout mail_panelLayout = new javax.swing.GroupLayout(mail_panel);
        mail_panel.setLayout(mail_panelLayout);
        mail_panelLayout.setHorizontalGroup(
            mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mail_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loadsentMails, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mail_panelLayout.createSequentialGroup()
                        .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(groupName_Radio)
                            .addComponent(membertype_Radio)
                            .addComponent(memberno_radio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(membertype_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(group_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, 223, Short.MAX_VALUE)))))
                .addGap(31, 31, 31))
            .addGroup(mail_panelLayout.createSequentialGroup()
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mail_panelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mail_panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(427, 427, 427))))
                    .addGroup(mail_panelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mail_panelLayout.createSequentialGroup()
                                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(mail_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(34, 34, 34)
                                        .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mail_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fromdate_text)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(mail_panelLayout.createSequentialGroup()
                                        .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(mail_panelLayout.createSequentialGroup()
                                .addComponent(month_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(period_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(all_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filter_Check)
                                .addGap(317, 317, 317)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        mail_panelLayout.setVerticalGroup(
            mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mail_panelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(period_radio)
                    .addComponent(filter_Check)
                    .addComponent(all_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(jLabel5)
                    .addComponent(fromdate_cal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(groupName_Radio)
                    .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(todate_cal)
                        .addComponent(group_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(membertype_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mail_panelLayout.createSequentialGroup()
                        .addComponent(membertype_Radio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGroup(mail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memberno_radio)
                    .addComponent(memberno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(loadsentMails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mail_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.add(Calendar.MONTH, 1);

            cal1.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate_text.setText(Formats.TIMESTAMP.formatValue(date));
           

          
            
            
         

        }
    }//GEN-LAST:event_Month_calActionPerformed

    private void loadsentMailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadsentMailsActionPerformed
       if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0 ){
            
           
           
           if(filter_Check.isSelected()){
               
                    if(groupName_Radio.isSelected()){
                       if(group_combo.getSelectedIndex()!=-1){
                           
                                String GroupName = group_combo.getSelectedItem().toString();
                                jTable1.setVisible(true); 
                                Date FmDate = null;
                                Date ToDate = null;
                                try {
                                         FmDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                                } catch (BasicException ex) {
                                        Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, ex);
                                    }


                                try{

                                EmailSent_Table_Model  = EmailSentTableModel.loadEmailByGroupNameInfo(m_App , FmDate , ToDate , GroupName);
                                jTable1.setModel(EmailSent_Table_Model.getTableModel());  
                                 }
                                catch(BasicException e){
                                    Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, e);


                                }
                           
                           
                           
                           
                           
                       }
                       else{
                            JOptionPane.showMessageDialog(this, "Please select Group Name. ", "Warning", JOptionPane.WARNING_MESSAGE);
                       }
                    } 
                    if(membertype_Radio.isSelected()){
                        if(membertype_combo.getSelectedIndex()!=-1){
                           
                           
                            String MemTypeName = membertype_combo.getSelectedItem().toString();
                                jTable1.setVisible(true); 
                                Date FmDate = null;
                                Date ToDate = null;
                                try {
                                         FmDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                                } catch (BasicException ex) {
                                        Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, ex);
                                    }


                                try{

                                        EmailSent_Table_Model  = EmailSentTableModel.loadEmailByMemTypeInfo(m_App , FmDate , ToDate , MemTypeName);
                                        jTable1.setModel(EmailSent_Table_Model.getTableModel());  
                                 }
                                catch(BasicException e){
                                    Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, e);


                                }
                            
                            
                            
                            
                           
                       }
                       else{
                            JOptionPane.showMessageDialog(this, "Please select Membership Type. ", "Warning", JOptionPane.WARNING_MESSAGE);
                       }
                    }
                    if(memberno_radio.isSelected()){
                        
                        if(memberno_text.getText()!=null || memberno_text.getText().trim().length()>0){
                           
                           
                            
                                String Memno = memberno_text.getText().trim();
                                jTable1.setVisible(true); 
                                Date FmDate = null;
                                Date ToDate = null;
                                try {
                                         FmDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                                } catch (BasicException ex) {
                                        Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, ex);
                                    }


                                try{

                                        EmailSent_Table_Model  = EmailSentTableModel.loadEmailByMembershipNoInfo(m_App , FmDate , ToDate , Memno);
                                        jTable1.setModel(EmailSent_Table_Model.getTableModel());  
                                 }
                                catch(BasicException e){
                                    Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, e);


                                }
                            
                            
                           
                            
                            
                            
                            
                            
                           
                       }
                       else{
                            JOptionPane.showMessageDialog(this, "Enter Member Number. ", "Warning", JOptionPane.WARNING_MESSAGE);
                       }
                        
                        
                        
                        
                    }
               
               
               
               
           }
           else{
               
                    jTable1.setVisible(true); 
                    Date FmDate = null;
                    Date ToDate = null;
                    try {
                             FmDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                             ToDate = (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                    } catch (BasicException ex) {
                            Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    try{

                    EmailSent_Table_Model  = EmailSentTableModel.loadEmailInfo(m_App , FmDate , ToDate);
                    jTable1.setModel(EmailSent_Table_Model.getTableModel());  
                     }
                    catch(BasicException e){
                        Logger.getLogger(EmailSent.class.getName()).log(Level.SEVERE, null, e);


                    }
               
               
           }
           
           
          
           
           
           
           
       }
       else{
            JOptionPane.showMessageDialog(this, "Enter monthly duration. ", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_loadsentMailsActionPerformed

    private void month_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_radioItemStateChanged
        if(month_radio.isSelected()){
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);

            fromdate_text.setText(null);
            todate_text.setText(null);
           
        }
        else{
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
    }//GEN-LAST:event_month_radioItemStateChanged

    private void period_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_radioItemStateChanged
        if(period_radio.isSelected()){
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
            
            todate_text.setText(null);
        }
        else{
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
    }//GEN-LAST:event_period_radioItemStateChanged

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
            
        }
    }//GEN-LAST:event_fromdate_calActionPerformed

    private void groupName_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupName_RadioItemStateChanged
       if(groupName_Radio.isSelected()){
           
           group_combo.setVisible(true);
           membertype_combo.setVisible(false);
           
       }
       else{
            group_combo.setVisible(false);
           
       }
    }//GEN-LAST:event_groupName_RadioItemStateChanged

    private void membertype_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_membertype_RadioItemStateChanged
      if(membertype_Radio.isSelected()){
          
           group_combo.setVisible(false);
           membertype_combo.setVisible(true);
          memberno_text.setVisible(false);
      }
      else{
           membertype_combo.setVisible(false);
      }
    }//GEN-LAST:event_membertype_RadioItemStateChanged

    private void memberno_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberno_radioItemStateChanged
       
      if(memberno_radio.isSelected()){
          
           group_combo.setVisible(false);
           membertype_combo.setVisible(false);
          memberno_text.setVisible(true);
      }
      else{
           memberno_text.setVisible(false);
      }
    }//GEN-LAST:event_memberno_radioItemStateChanged

    private void filter_CheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filter_CheckItemStateChanged
         if(filter_Check.isSelected()){

            groupName_Radio.setVisible(true);
            membertype_Radio.setVisible(true);
            memberno_radio.setVisible(true);
            if(groupName_Radio.isSelected()){
                group_combo.setVisible(true);
            }
            if(membertype_Radio.isSelected()){
                membertype_combo.setVisible(true);
            }
            if(memberno_radio.isSelected()){
                memberno_text.setVisible(true);
            }

        }
        else{

            groupName_Radio.setVisible(false);
            membertype_Radio.setVisible(false);
            memberno_radio.setVisible(false);
            group_combo.setVisible(false);
            membertype_combo.setVisible(false);
            memberno_text.setVisible(false);
        }

    }//GEN-LAST:event_filter_CheckItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Month_cal;
    private javax.swing.JRadioButton all_radio;
    private javax.swing.JRadioButton filter_Check;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JRadioButton groupName_Radio;
    private javax.swing.JComboBox group_combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton loadsentMails;
    private javax.swing.JPanel mail_panel;
    private javax.swing.JRadioButton memberno_radio;
    private javax.swing.JTextField memberno_text;
    private javax.swing.JRadioButton membertype_Radio;
    private javax.swing.JComboBox membertype_combo;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
      return "Sent Email List";
    }

    public void activate() throws BasicException {
        
        jTable1.setVisible(false);
        month_radio.setSelected(true);
        all_radio.setSelected(true);
        loaddata();
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        EmailSent_Table_Model = (EmailSentTableModel) m_App.getBean("com.openbravo.pos.sms.EmailSentTableModel");
        
        
        
    }

    public Object getBean() {
        return this;
    }



   public void loaddata() throws BasicException{
        mail_panel.setVisible(true);
        groupName_Radio.setVisible(false);
          membertype_Radio.setVisible(false);
          memberno_radio.setVisible(false);
          group_combo.setVisible(false);
          membertype_combo.setVisible(false);
           filter_Check.setSelected(false);
            memberno_text.setVisible(false);
        groupButton();
        
        
        
        MemTypeNameList =  getMemTypeList(m_App);
        MemTypeModel =new ComboBoxValModel(MemTypeNameList);
        membertype_combo.setModel(MemTypeModel);
        
        GroupNameList = new ArrayList();
        GroupNameList = getGroupList(m_App);
        GroupNameModel = new ComboBoxValModel(GroupNameList);
        group_combo.setModel(GroupNameModel);
   }
   
   
   
   public void reset(){
         jTable1.setVisible(false);
         fromdate_text.setText(null);
         todate_text.setText(null);
         groupName_Radio.setVisible(false);
          membertype_Radio.setVisible(false);
          memberno_radio.setVisible(false);
          group_combo.setVisible(false);
          membertype_combo.setVisible(false);
         filter_Check.setSelected(false);
          memberno_text.setVisible(false);
   }
  private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(groupName_Radio);
        bg2.add(membertype_Radio);
        bg2.add(memberno_radio);
        
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(all_radio);
        bg3.add(filter_Check);
       
    }
  
  
  
   public List getMemTypeList(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM MEMTYPE WHERE ACTIVE=1 ORDER BY NAME ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
   
    
  public List getGroupList(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT GROUP_NAME FROM email_group_list WHERE ACTIVE=1 ORDER BY GROUP_NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
}
    
