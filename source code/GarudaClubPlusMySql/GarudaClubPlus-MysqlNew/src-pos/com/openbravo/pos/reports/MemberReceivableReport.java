

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
//import com.openbravo.pos.sms.EmailSent;
//import com.openbravo.pos.sms.MemberReceivableReportTableModel;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperPrint;
import static org.hsqldb.HsqlDateTime.toDate;

import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.TableCellRenderer;

public class MemberReceivableReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private Date fromDate;
    private Date toDat;
    
    private List<MemberReceivableReportTableModel.MemberInfo> Report_all;
    private MemberReceivableReportTableModel Member_Receivable_Report_Table_Model;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private ComboBoxValModel FacilityComboBoxModel ; 
    
    
    public MemberReceivableReport() {
        initComponents();
        
         jTable1.setVisible(false);
         String column_names[]= {"Serial Number","Medicine Name","Dose","Frequency"};
         DefaultTableModel  table_model=new DefaultTableModel(column_names,3);
    
         
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        frmdateText = new javax.swing.JTextField();
        todateText = new javax.swing.JTextField();
        monthButton = new javax.swing.JButton();
        toDateButton = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        fromDateButton = new javax.swing.JButton();
        executeButton = new javax.swing.JButton();
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
        jLabel4 = new javax.swing.JLabel();
        bill_date_Radio = new javax.swing.JRadioButton();
        memberno_Radio = new javax.swing.JRadioButton();
        facility_radio = new javax.swing.JRadioButton();
        clearDate_Radio = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        totalAmount_text = new javax.swing.JTextField();
        facility_combo = new javax.swing.JComboBox();

        jLabel2.setText("From");

        jLabel3.setText("To");

        frmdateText.setEditable(false);

        todateText.setEditable(false);

        monthButton.setText("Month");
        monthButton.setName(""); // NOI18N
        monthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthButtonActionPerformed(evt);
            }
        });

        toDateButton.setText("ToDate");
        toDateButton.setName(""); // NOI18N
        toDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toDateButtonActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(181, 70, 70));
        jRadioButton1.setText("Monthwise");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setForeground(new java.awt.Color(179, 92, 92));
        jRadioButton2.setText("Periodwise");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        fromDateButton.setText("From Date");
        fromDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromDateButtonActionPerformed(evt);
            }
        });

        executeButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        executeButton.setForeground(new java.awt.Color(23, 55, 185));
        executeButton.setText("Execute");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });

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

        jButton1.setForeground(new java.awt.Color(199, 19, 19));
        jButton1.setText("Generate Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(36, 27, 213));
        jLabel4.setText("Order by : ");

        bill_date_Radio.setText("Bill Date");
        bill_date_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bill_date_RadioItemStateChanged(evt);
            }
        });

        memberno_Radio.setText("Member No");
        memberno_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memberno_RadioItemStateChanged(evt);
            }
        });

        facility_radio.setText("Facility");
        facility_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                facility_radioItemStateChanged(evt);
            }
        });

        clearDate_Radio.setText("Clear Date");
        clearDate_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clearDate_RadioItemStateChanged(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(198, 12, 12));
        jLabel5.setText("Total Amount : ");

        facility_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jRadioButton1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jRadioButton2))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(1, 1, 1)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(frmdateText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .add(todateText))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(fromDateButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(monthButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(toDateButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(layout.createSequentialGroup()
                            .add(jLabel5)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(totalAmount_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(142, 142, 142)
                            .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 954, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(jLabel4)
                                .add(18, 18, 18)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(layout.createSequentialGroup()
                                        .add(facility_radio)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(memberno_Radio)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(clearDate_Radio))
                                    .add(facility_combo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(bill_date_Radio)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(executeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jRadioButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(frmdateText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(monthButton)
                    .add(fromDateButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(todateText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(toDateButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, facility_radio)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(executeButton)
                        .add(jLabel4)
                        .add(bill_date_Radio)
                        .add(clearDate_Radio)
                        .add(memberno_Radio)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(facility_combo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 289, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jLabel5)
                    .add(totalAmount_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(138, 138, 138))
        );

        totalAmount_text.setEditable(false);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
      
      
      
      toDateButton.setVisible(false);
      fromDateButton.setVisible(false);
      monthButton.setVisible(true);
      frmdateText.setText("");
      todateText.setText("");
      
       // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
      
     
      monthButton.setVisible(false);
      toDateButton.setVisible(true);
      fromDateButton.setVisible(true);
      frmdateText.setText("");
      todateText.setText("");
      //jButton4.setVisible(true);// TODO add your handling code here:
     
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void monthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthButtonActionPerformed
     
        
          if(jRadioButton1.isSelected())
          {
        
            Date date;

            try {
            date = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
            //System.out.println(date);
        } catch (BasicException ex)    {
            
             ex.printStackTrace();
             new MessageInf(ex).show(this);
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            frmdateText.setText(Formats.TIMESTAMP.formatValue(date));
            //jTextField1.setText(Formats.TIMESTAMP.formatValue(date));
            
            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.MONTH, 1);
            
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            
            todateText.setText(Formats.TIMESTAMP.formatValue(date)); 
            //jTextField2.setText(Formats.TIMESTAMP.formatValue(date));
        }
         
    }//GEN-LAST:event_monthButtonActionPerformed
           else
        {
            JOptionPane.showMessageDialog(this,"Please select the type month","Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void fromDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromDateButtonActionPerformed
        
        if(jRadioButton2.isSelected())
        {
        
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
        } catch (BasicException e) {
            date = null;
            e.printStackTrace();
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                frmdateText.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
        }
           else
        {
            JOptionPane.showMessageDialog(this,"Please select the type  period","Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_fromDateButtonActionPerformed

    private void toDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toDateButtonActionPerformed
       
        if(jRadioButton2.isSelected())
        {
            Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
        } catch (BasicException e) {
            date = null;
             e.printStackTrace();
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
               
                todateText.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Please select the type period","Warning", JOptionPane.WARNING_MESSAGE);
        }// TODO add your handling code here:
    }//GEN-LAST:event_toDateButtonActionPerformed

    public List<MemberReceivableReportTableModel.MemberInfo> MemberReceivable_list ;
    private List<Object> FacilityList ;
    
    
    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
      if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0 ){
        if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0 ) {   
            jTable1.setVisible(true); 
            Date FmDate = null;
            Date ToDate = null;
             try {
                       FmDate = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
                       ToDate = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
              } catch (BasicException ex) {
                      Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
                  }
           
                jTable1.setVisible(true);
                
                if(facility_radio.isSelected()){
                   if(facility_combo.getSelectedIndex()!=-1){ 
                       String Facility = facility_combo.getSelectedItem().toString();
                       if(Facility.equals("ALL")){
                           try{
                                Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfo(m_App , FmDate , ToDate);
                                jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                            }
                            catch(BasicException e){
                                     Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                            }
                           
                           
                       }
                       else{
                            
                           try{
                                Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoByFacility(m_App , FmDate , ToDate , Facility);
                                jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                            }
                            catch(BasicException e){
                                     Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                            }
                           
                       }
                   }
                    
                }
                if(clearDate_Radio.isSelected()){
                    try{
                        Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByClearDate(m_App , FmDate , ToDate);
                        jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                    }
                    catch(BasicException e){
                             Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                
                if(bill_date_Radio.isSelected()){
                    try{
                        Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByBillDate(m_App , FmDate , ToDate);
                        jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                    }
                    catch(BasicException e){
                             Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if(memberno_Radio.isSelected()){
                    try{
                        Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByMemberNo(m_App , FmDate , ToDate);
                        jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                    }
                    catch(BasicException e){
                             Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
           
           
                int size = Member_Receivable_Report_Table_Model.getSize();
                MemberReceivable_list = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
                MemberReceivable_list = Member_Receivable_Report_Table_Model.getMemberReceivableList();
                Double GTotal = 0.00;
                for(int i=0;i<MemberReceivable_list.size();i++){
                    Double amt = MemberReceivable_list.get(i).getAmount();
                    GTotal=GTotal+amt;
                }
                
                
                totalAmount_text.setText(decimalFormat.format(GTotal));
       
       
      
        }
        else{
          
             JOptionPane.showMessageDialog(this, "Enter To Date ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
      
      }  
      else{
            JOptionPane.showMessageDialog(this, "Enter From Date ", "Warning", JOptionPane.WARNING_MESSAGE);
       }// TODO add your handling code here:
    }//GEN-LAST:event_executeButtonActionPerformed
 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0 ){
        if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0 ) {   
            
            Date FmDate = null;
            Date ToDate = null;
            Date CurrDate = null;
            try {
                   FmDate = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
                   ToDate = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
                   CurrDate = new Date();
            } 
            catch (BasicException ex) {
                Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            if(facility_radio.isSelected()){
                  if(facility_combo.getSelectedIndex()!=-1){ 
                                String Facility = facility_combo.getSelectedItem().toString();
                                if(Facility.equals("ALL")){
                                    try{
                                         Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfo(m_App , FmDate , ToDate);
                                         jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                                     }
                                     catch(BasicException e){
                                              Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                                     }


                                }
                                else{

                                    try{
                                         Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoByFacility(m_App , FmDate , ToDate , Facility);
                                         jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                                     }
                                     catch(BasicException e){
                                              Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                                     }

                                }
                            }
            }
            if(clearDate_Radio.isSelected()){
                    try{
                        Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByClearDate(m_App , FmDate , ToDate);
                        
                    }
                    catch(BasicException e){
                             Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                    }
             }
            if(bill_date_Radio.isSelected()){
                    try{
                        Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByBillDate(m_App , FmDate , ToDate);
                        
                    }
                    catch(BasicException e){
                             Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                    }
                
            }
            if(memberno_Radio.isSelected()){
                    try{
                        Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByMemberNo(m_App , FmDate , ToDate);
                    }
                    catch(BasicException e){
                             Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                    }
             }
            
           
            
            MemberReceivable_list = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
            MemberReceivable_list = Member_Receivable_Report_Table_Model.getMemberReceivableList();
            
             DataSourceProvider data1 = new DataSourceProvider(MemberReceivable_list);
             DataSourceForMemberReceivableReport dsfc = new DataSourceForMemberReceivableReport(MemberReceivable_list);
             data1.setDataSource(dsfc);
             Map reportparams = new HashMap();
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             String RPH =  "From  : "+frmdateText.getText()+ "  To: "+todateText.getText();
             reportparams.put("ReportHeader",RPH);
             
             reportparams.put("CreatedDate",Formats.TIMESTAMP.formatValue(CurrDate));
             reportparams.put("TITLE","QT Detailed Report");
             JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberReceivableReport.jrxml", reportparams, false, data1, true, null); 
                         
           
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Enter To Date ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
     }
     else{
          JOptionPane.showMessageDialog(this, "Enter From Date ", "Warning", JOptionPane.WARNING_MESSAGE);
     }
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void facility_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_facility_radioItemStateChanged
       if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0){
           if(todateText.getText()!=null && todateText.getText().trim().length()>0){
               Date FmDate = null;
               Date ToDate = null;
               try {
                         FmDate = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                if(facility_radio.isSelected()){
                    facility_combo.setVisible(true);
                            if(facility_combo.getSelectedIndex()!=-1){ 
                                String Facility = facility_combo.getSelectedItem().toString();
                                if(Facility.equals("ALL")){
                                    try{
                                         Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfo(m_App , FmDate , ToDate);
                                         jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                                     }
                                     catch(BasicException e){
                                              Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                                     }


                                }
                                else{

                                    try{
                                         Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoByFacility(m_App , FmDate , ToDate , Facility);
                                         jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                                     }
                                     catch(BasicException e){
                                              Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                                     }

                                }
                            }
                }
           
       
           }
       
       }
       else{
           jTable1.setVisible(false);
           totalAmount_text.setText(null);
           
       }
       
    
    
    }//GEN-LAST:event_facility_radioItemStateChanged

    private void memberno_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberno_RadioItemStateChanged
      facility_combo.setVisible(false);
        if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0){
           if(todateText.getText()!=null && todateText.getText().trim().length()>0){
               Date FmDate = null;
               Date ToDate = null;
               try {
                         FmDate = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
                }  
        
        
                if(memberno_Radio.isSelected()){
                    
                            try{
                                Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByMemberNo(m_App , FmDate , ToDate);
                                jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                            }
                            catch(BasicException e){
                                     Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                            }

                }
           }
      }
      else{
          jTable1.setVisible(false);
          totalAmount_text.setText(null);
      }
      
    }//GEN-LAST:event_memberno_RadioItemStateChanged

    private void clearDate_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clearDate_RadioItemStateChanged
      facility_combo.setVisible(false);
        if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0){
           if(todateText.getText()!=null && todateText.getText().trim().length()>0){
               Date FmDate = null;
               Date ToDate = null;
               try {
                         FmDate = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
                } 
        
                if(clearDate_Radio.isSelected()){
                            try{
                                Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByClearDate(m_App , FmDate , ToDate);
                                jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                            }
                            catch(BasicException e){
                                     Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                            }
               }
           }
      }
      else{
          jTable1.setVisible(false);
          totalAmount_text.setText(null);
      }
    }//GEN-LAST:event_clearDate_RadioItemStateChanged

    private void bill_date_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bill_date_RadioItemStateChanged
     facility_combo.setVisible(false);
        if(frmdateText.getText()!=null && frmdateText.getText().trim().length()>0){
           if(todateText.getText()!=null && todateText.getText().trim().length()>0){
               Date FmDate = null;
               Date ToDate = null;
               try {
                         FmDate = (Date) Formats.TIMESTAMP.parseValue(frmdateText.getText());
                         ToDate = (Date) Formats.TIMESTAMP.parseValue(todateText.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
                } 
        
                if(bill_date_Radio.isSelected()){
                            try{
                                Member_Receivable_Report_Table_Model  = MemberReceivableReportTableModel.loadMemberInfoOrderByBillDate(m_App , FmDate , ToDate);
                                jTable1.setModel(Member_Receivable_Report_Table_Model.getTableModel());  
                            }
                            catch(BasicException e){
                                     Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, e);
                            }
               }
           }
      }
      else{
          jTable1.setVisible(false);
          totalAmount_text.setText(null);
      }
    }//GEN-LAST:event_bill_date_RadioItemStateChanged

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bill_date_Radio;
    private javax.swing.JRadioButton clearDate_Radio;
    private javax.swing.JButton executeButton;
    private javax.swing.JComboBox facility_combo;
    private javax.swing.JRadioButton facility_radio;
    private javax.swing.JTextField frmdateText;
    private javax.swing.JButton fromDateButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton memberno_Radio;
    private javax.swing.JButton monthButton;
    private javax.swing.JButton toDateButton;
    private javax.swing.JTextField todateText;
    private javax.swing.JTextField totalAmount_text;
    // End of variables declaration//GEN-END:variables
    
    private void groupButton() {

        ButtonGroup bg1 = new ButtonGroup( );
        bg1.add(jRadioButton1);
        bg1.add(jRadioButton2);

        ButtonGroup bg2 = new ButtonGroup( );
        bg2.add(bill_date_Radio);
        bg2.add(memberno_Radio);
        bg2.add(clearDate_Radio);
        bg2.add(facility_radio);
        
        
      
}
    
 @Override
    public String getTitle() {
   return "Member Receivable Report";  
    }

    @Override
    public void activate() throws BasicException {
    
        groupButton();
        
        jRadioButton1.setSelected(true);
        monthButton.setVisible(true);
        fromDateButton.setVisible(false);
        toDateButton.setVisible(false);
        jTable1.setVisible(false);
        facility_radio.setSelected(true);
        totalAmount_text.setText(null);
        jTable1.setVisible(false);
        facility_combo.setVisible(true);
        FacilityList = getFacilityList(m_App);
        FacilityList.add(0,"ALL");
        FacilityComboBoxModel = new ComboBoxValModel(FacilityList);
        facility_combo.setModel(FacilityComboBoxModel);
        facility_combo.setSelectedIndex(0);
        
    }

    @Override
    public boolean deactivate() {

     return true;
    
        }    
    @Override
    public JComponent getComponent() {

    return this;
    
    }

    @Override
    public void init(AppView app) throws BeanFactoryException {
      m_App=app;
      
     
    }

    @Override
    public Object getBean() {
  
    return this;
    }
    
    
    public List getFacilityList (AppView app){
         List<Object> RoomType_List = new ArrayList<Object>();
         try {
            RoomType_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM FACILITY WHERE ACTIVE=1 order by name", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(MemberReceivableReport.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomType_List;
       }

   
    

}
