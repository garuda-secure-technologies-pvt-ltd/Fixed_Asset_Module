/*
 * jbankreconderations.java
 *
 * Created on April 30, 2012, 11:15 AM
 */

package com.openbravo.pos.Accounts;

import com.mysql.jdbc.PreparedStatement;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
//import com.openbravo.pos.clubmang.BREditorDialog;
//import com.openbravo.pos.clubmang.BREditor;
import com.openbravo.pos.clubmang.EditorDialog;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.JPanelView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import java.util.Calendar;
import com.openbravo.pos.clubmang.DataLogicFacilities;
//import com.openbravo.pos.clubmang.BREditorDialog;
import com.openbravo.pos.clubmang.EditorTableModel;
import com.openbravo.pos.forms.AppLocal;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.*;
/**
 *
 * @author  user
 */
public class JBankReconcilations extends javax.swing.JPanel implements JPanelView, BeanFactoryApp  {
    private AppView mapp;
    private ComboBoxValModel fModel;
    private List<BankReconcilation> details;
    private DataLogicFacilities dlfac;
    private waitDialog w;
    private BankReconcilationModel dmodel;
       DateFormat formatter;//added by pratima
        private boolean dateFlag=true;
     private boolean amountFlag=true;
     private int dateRow;
     private int amountRow;
       
    //private EditorTableModel etModel;
  
    Date fdate;
    Date todate;
    Date bdate;
    private String chequeaccount;
    private String accid;
     private AccountMaster accm;
     ArrayList al = new ArrayList();
    
      
    public JBankReconcilations() {
        initComponents();
    
    
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        fromdate = new javax.swing.JTextField();
        toDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        Update = new javax.swing.JButton();
        ShowAll = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(737, 279));

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1.setText("Bank  A/c");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2.setText("Bank limit");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Type Of Account ");

        fromdate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        toDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setText("From Date");

        jLabel3.setText(" To Date");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(49, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(49, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(49, 23));
        jButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton1StateChanged(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(49, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(49, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(49, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dates", "Narrate", "Deposite", "Withdraw", "RECL", "Amount", "BankDates"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 72));
        jTable1.setMinimumSize(new java.awt.Dimension(60, 72));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setRequestFocusEnabled(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Execute");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton4.setMaximumSize(new java.awt.Dimension(49, 23));
        jButton4.setMinimumSize(new java.awt.Dimension(49, 23));
        jButton4.setPreferredSize(new java.awt.Dimension(49, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.setPreferredSize(new java.awt.Dimension(71, 23));
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        ShowAll.setText("Show All");
        ShowAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShowAllItemStateChanged(evt);
            }
        });
        ShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllActionPerformed(evt);
            }
        });

        jLabel4.setText("Select From Date");

        jLabel5.setText("Select To Date");

        jLabel6.setText("Select Bank Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fromdate, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(toDate))
                                .addGap(0, 23, Short.MAX_VALUE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ShowAll)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(215, 215, 215)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(96, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(fromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        try {
            if(jRadioButton1.isSelected()==true)
            {   
                jRadioButton1.setVisible(true);
               
            }
            jRadioButton2.setSelected(false);
            List<BankReconcilation> anms =  dlfac.getAllNames();
            fModel = new ComboBoxValModel(anms);
            jComboBox1.setModel(fModel);
          
        } catch (BasicException ex) {
            Logger.getLogger(JBankReconcilations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        try {
            if(jRadioButton2.isSelected()==true)
            { jRadioButton2.setVisible(true);
                jRadioButton1.setSelected(false);
            }
            List<BankReconcilation> anms1 =  dlfac.getAllNames1();
            fModel = new ComboBoxValModel(anms1);
            jComboBox1.setModel(fModel);//GEN-LAST:event_jRadioButton2ActionPerformed
        } catch (BasicException ex) {
            Logger.getLogger(JBankReconcilations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                             

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(fromdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            fromdate.setText(Formats.DATE.formatValue(date));
        }
        jTable1.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
                
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(toDate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            toDate.setText(Formats.DATE.formatValue(date));
        }
        jTable1.repaint();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 if(jComboBox1.getSelectedIndex()!=-1){
        if (fromdate.getText().isEmpty() == true && toDate.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter both start and end date");

        } else if (fromdate.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter Start Date");
        } else if (toDate.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter End Date");
        }  
        else if (fromdate.getText().isEmpty() == false && toDate.getText().isEmpty() == false) {
            try {
               
                
             Object  accid1 =  jComboBox1.getSelectedItem();
                accid = accid1.toString();
                fdate = (Date) Formats.DATE.parseValue(fromdate.getText());
             
              //  todate = (Date) Formats.DATE.parseValue(toDate.getText());
               
                todate= getSecondDate(toDate.getText());
                 System.out.println("todate"+todate);
                  if(todate.after(fdate)){
            Object[] obj = (Object[]) new StaticSentence(mapp.getSession(), "SELECT id FROM accountmaster WHERE ACTIVE = TRUE AND NAME=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{accid});
                              
                accid= obj[0].toString();
           dmodel = BankReconcilationModel.loadInstance(mapp,2, accid, fdate, todate);
                jTable1.setModel(dmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                
               }else  JOptionPane.showMessageDialog(null, "To Date Should be after From Date.");
            } catch (BasicException ex) {
                Logger.getLogger(JBankReconcilations.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTable1.repaint();
    
        
        } 
        //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
       // jTable1.getColumnModel().getColumn(0).setMinWidth(76);
       
       


       
       
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
         jTable1.getColumnModel().getColumn(0).setMinWidth(10);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(76);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(2).setMinWidth(10);
         jTable1.getColumnModel().getColumn(2).setMaxWidth(520);
        jTable1.getColumnModel().getColumn(3).setMinWidth(10);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(78);
         jTable1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(4).setMinWidth(10);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(5).setMinWidth(10);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(56);
        jTable1.getColumnModel().getColumn(6).setMinWidth(10);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(78);
      
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer1);
       //jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(7).setMinWidth(10);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
   
         ShowAll.setSelected(false); 
  // TODO add your handling code here:
 }else{
  JOptionPane.showMessageDialog(null, "Please Select Bank");
 }
    }//GEN-LAST:event_jButton3ActionPerformed

private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       
        Date date1;
        String date2;
         String id;
        int i;
        dateFlag=true;
        amountFlag=true;
         

        
        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Would you like to update"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        SimpleDateFormat formatter;
        
       if (res == JOptionPane.YES_OPTION) {
           datesFilledCheck();
      //     if( atleast1RowSelectedCheck()){
        if(dateFlag){
            if(amountFlag){
          try {
              for( i= 0; i<jTable1.getRowCount();i++){
                //  if((Boolean)jTable1.isRowSelected(i)==true){
              Date date=   (Date) jTable1.getModel().getValueAt(i, 7);
             // date1 = (Date) Formats.DATE.parseValue(date);
              
          if(date!=null){
            
                   id =(String) jTable1.getModel().getValueAt(i, 9);
                  String accid = (String) jTable1.getModel().getValueAt(i, 8);
                  //date1 = (Date) Formats.DATE.parseValue(date);
                  //date2 =Formats.DATE.formatValue(date1);
                 //  bdate= (Date) jTable1.getModel().getValueAt(i, 7);
                  Double amount  = (Double) jTable1.getModel().getValueAt(i, 6);
                  if(id!=null)
                  {
                  if(amount!=null && date!=null){
                  
             int update_bankrecord=    new PreparedSentence(mapp.getSession()
                      ,"update bankrecord b set b.amount=? ,b.bankdate=? where b.id = ?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{amount,date,id});
                         amount  = (Double) jTable1.getModel().getValueAt(i, 6);
//                       date1 = (Date) Formats.DATE.parseValue(date);
//                           date2 =Formats.DATE.formatValue(date1);  
                           bdate= (Date) jTable1.getModel().getValueAt(i, 7);
                  }
                  }
                else {  
                      if(id==null){
                       id = UUID.randomUUID().toString();
                        accid = (String) jTable1.getModel().getValueAt(i, 8);
                        //date1 = (Date) Formats.DATE.parseValue(date);
                        amount  = (Double) jTable1.getModel().getValueAt(i, 6);
                        
                   new PreparedSentence(mapp.getSession()
                     , "INSERT INTO BANKRECORD(ID,ACCID,BANKDATE,AMOUNT) VALUES (?,?,?,?)"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.DOUBLE})).exec(new Object[]{id,accid,date,amount});
               }
                  
                  }
                }else{
                id =(String) jTable1.getModel().getValueAt(i, 9);
                    if(id!=null){
                    new PreparedSentence(mapp.getSession()
                    , "DELETE FROM BANKRECORD WHERE ID=?"
                    , SerializerWriteString.INSTANCE).exec(id);
                    }
                }
          
           //   } //ifended()
              }
          } catch (BasicException ex) {
                Logger.getLogger(JBankReconcilations.class.getName()).log(Level.SEVERE, null, ex);
           
     }
          
          ///////////////////////////////////////////////////////////////////////////////////
            if (fromdate.getText().isEmpty() == false && toDate.getText().isEmpty() == false) {
            try {
               
                
             Object  accid1 =  jComboBox1.getSelectedItem();
                accid = accid1.toString();
                fdate = (Date) Formats.DATE.parseValue(fromdate.getText());
                todate = (Date) Formats.DATE.parseValue(toDate.getText());
            Object[] obj = (Object[]) new StaticSentence(mapp.getSession(), "SELECT id FROM accountmaster WHERE ACTIVE = TRUE AND NAME=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{accid});
                accid= obj[0].toString();
               dmodel = BankReconcilationModel.loadInstance(mapp, 2,accid, fdate, todate);
                jTable1.setModel(dmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
               
            } catch (BasicException ex) {
                Logger.getLogger(JBankReconcilations.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            ShowAll.setSelected(false); 
        } 
        //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
       jTable1.getColumnModel().getColumn(2).setMinWidth(10);
           jTable1.getColumnModel().getColumn(2).setMaxWidth(350);
        //jTable1.getColumnModel().getColumn(2).setMaxWidth(136);
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(78);
           jTable1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(78);
           jTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(56);
        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(78);
       
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer1);
      //  jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
          ///////////////////////////////////////////////////////////////////////////////////
          
       }//ifamountflagcheck   
            else{JOptionPane.showMessageDialog(null, "Please fill amount for "+amountRow+"th entry.");}
        
        }else{JOptionPane.showMessageDialog(null, "Please fill Bank Date for "+dateRow+"th entry.");}
            //  } else{JOptionPane.showMessageDialog(null, "No row is selected!!!!!!.");}
//        if (fromdate.getText().isEmpty() == false && toDate.getText().isEmpty() == false) {
//            try {
//               
//                
//             Object  accid1 =  jComboBox1.getSelectedItem();
//                accid = accid1.toString();
//                fdate = (Date) Formats.DATE.parseValue(fromdate.getText());
//                todate = (Date) Formats.DATE.parseValue(toDate.getText());
//            Object[] obj = (Object[]) new StaticSentence(mapp.getSession(), "SELECT id FROM accountmaster WHERE ACTIVE = TRUE AND NAME=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{accid});
//                accid= obj[0].toString();
//               dmodel = BankReconcilationModel.loadInstance(mapp, 2,accid, fdate, todate);
//                jTable1.setModel(dmodel.getTableModel());
//                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//               
//            } catch (BasicException ex) {
//                Logger.getLogger(JBankReconcilations.class.getName()).log(Level.SEVERE, null, ex);
//            }
//         
//            ShowAll.setSelected(false); 
//        } 
//        //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
//        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
//        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(0).setMaxWidth(78);
//        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
//       jTable1.getColumnModel().getColumn(2).setMinWidth(10);
//           jTable1.getColumnModel().getColumn(2).setMaxWidth(350);
//        //jTable1.getColumnModel().getColumn(2).setMaxWidth(136);
//        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(3).setMaxWidth(78);
//           jTable1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
//        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(4).setMaxWidth(78);
//           jTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
//        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(5).setMaxWidth(56);
//        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(6).setMaxWidth(78);
//       
//        jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer1);
//      //  jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
//        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(7).setMaxWidth(78);
//        jTable1.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
//        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        
        return;
        
         }                                        
}//GEN-LAST:event_updateActionPerformed

private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
// TODO add your handling code here:
     try {
    int row = jTable1.getSelectedRow();
            if (row >= 0) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                   // String tid = etModel.getTableModel().getValueAt(row, 5).toString();
                    String tid = jTable1.getModel().getValueAt(row, 1).toString();
                    boolean status = CheckForEditRequest(tid);
                    if (status == true) {
                        BREditor editor = BREditor.getDialog(this,mapp, dlfac);
                        editor.showDialog(tid);
                       //etModel = EditorTableModel.loadInstance(mapp, accm.getid());
//                        BREditorDialog editor =  BREditorDialog.getDialog(this,mapp, dlfac);
//                       editor.showDialog(tid);
                        dmodel = BankReconcilationModel.loadInstance(mapp,2,accid, fdate, todate);
                     
                      
                       
                        //jTable1.setModel(jTable1.getModel());
                       //jLabel1.setText(accm.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "The voucher is already edited.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    
}//GEN-LAST:event_jTable1KeyPressed

private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

     try {
            int row = jTable1.getSelectedRow();
            if (row >= 0) {
                if (evt.getClickCount() == 2) {
                    String tid = jTable1.getModel().getValueAt(row, 1).toString();
                    boolean status = CheckForEditRequest(tid);
                    if (status == true) {
                         BREditor editor =  BREditor.getDialog(this,mapp, dlfac);
                        editor.showDialog(tid);
                        
                        
                         //dmodel = BankReconcilationModel.loadInstance(mapp, accid, fdate, todate);
//                        BREditorDialog editor = BREditorDialog.getDialog(this,mapp, dlfac);
//                        editor.showDialog(tid);
                    //etModel = EditorTableModel.loadInstance(mapp, accm.getid());
                        dmodel = BankReconcilationModel.loadInstance(mapp,2, accid, fdate, todate);
                       
                      //  jTable1.setModel(jTable1.getModel());
                  //jLabel1.setText(accm.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "The voucher is already edited.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    // TODO add your handling code here:
}//GEN-LAST:event_jTable1MousePressed

private void jButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton1StateChanged
// TODO add your handling code here:
}//GEN-LAST:event_jButton1StateChanged

private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed

    
    
    // TODO add your handling code here:
}//GEN-LAST:event_ShowAllActionPerformed

private void ShowAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShowAllItemStateChanged

    
    
    
     try {
         
          Object  accid1 =  jComboBox1.getSelectedItem();
                accid = accid1.toString();
                fdate = (Date) Formats.DATE.parseValue(fromdate.getText());
                todate = (Date) Formats.DATE.parseValue(toDate.getText());
            Object[] obj = (Object[]) new StaticSentence(mapp.getSession(), "SELECT id FROM accountmaster WHERE ACTIVE = TRUE AND NAME=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{accid});
                accid= obj[0].toString();
               dmodel = BankReconcilationModel.loadInstance(mapp,1, accid, fdate, todate);
                 dmodel = BankReconcilationModel.loadInstance(mapp,2, accid, fdate, todate);
                jTable1.setModel(dmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
               
            if (ShowAll.isSelected() == true) {
                dmodel = BankReconcilationModel.loadInstance(mapp,1, accid, fdate, todate);
                jTable1.setModel(dmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            } else if (ShowAll.isSelected() == false) {
                dmodel = BankReconcilationModel.loadInstance(mapp,2, accid, fdate, todate);
                jTable1.setModel(dmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
      DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(0).setMinWidth(10);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(2).setMinWidth(10);
          jTable1.getColumnModel().getColumn(2).setMaxWidth(350);
        //jTable1.getColumnModel().getColumn(2).setMaxWidth(136);
        jTable1.getColumnModel().getColumn(3).setMinWidth(10);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(78);
           jTable1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(4).setMinWidth(10);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(78);
           jTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(5).setMinWidth(10);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(56);
        jTable1.getColumnModel().getColumn(6).setMinWidth(10);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(78);
      
         jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer1);
       // jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(7).setMinWidth(10);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
    
    // TODO add your handling code here:
}//GEN-LAST:event_ShowAllItemStateChanged

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    Date date;
        try {
           date = (Date) Formats.DATE.parseValue(jTextField1.getText());
         
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
         jTextField1.setText(Formats.DATE.formatValue(date));
       
        }
      int row = jTable1.getSelectedRow();
            int col = 7; 
            
            try { 
                if (row >= 0 && row < jTable1.getRowCount()) {
                  jTable1.getModel().setValueAt(null,row,col);
                  //jTable1.getModel().setValueAt(bdate ,row, col);
                  bdate= (Date) jTable1.getModel().getValueAt(row, 7);
    
            if(bdate == null){
                Object date1=(Date) Formats.DATE.parseValue(jTextField1.getText());
             //  Formats.setDatePattern("yyyy-MM-dd");
                Object date2 =Formats.DATE.formatValue(date1);
                    
            
          //  Formats.setDatePattern("");
                            jTable1.getModel().setValueAt(date2, row, col);                      
                 jTable1.setModel(dmodel.getTableModel());  
            if(jTable1.editCellAt(row , 7) ==false)
                 {
               jTextField1.setText(null);
             }
     
            }                                  
              }else {
                  
                JOptionPane.showMessageDialog(this, "select any row to edit");
           }
            }catch (Exception e) 
      {
          e.printStackTrace();
      }
         
            
       // jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
       DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
   
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(2).setMinWidth(10);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(520);
        //jTable1.getColumnModel().getColumn(2).setMaxWidth(136);
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(78);
           jTable1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(78);
           jTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(56);
        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer1);
     //jTable1.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(78);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
//   
       // jButton4.repaint();
}//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ShowAll;
    private javax.swing.JButton Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField fromdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField toDate;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
         return "Bank Reconciliation";
    }

    public void activate() throws BasicException {
//        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {  
//   
//            public void valueChanged(ListSelectionEvent e) {  
//               
//               int i=(jTable1.getEditingRow());
//               //jTable1.addRowSelectionInterval(i-1,i);
//               System.out.println("row edited  "+i);
//            }  
//        });  
        toDate.setText(null);
        fromdate.setText(null);
        jTextField1.setText(null);
        jTextField1.setVisible(false);
        dmodel = BankReconcilationModel.emptyinstance();
        jTable1.setModel(dmodel.getTableModel());
        jTable1.repaint();
        jRadioButton1.setSelected(true);
         jRadioButton2.setSelected(false);
        List<BankReconcilation> anms2 = dlfac.getAllNames();
        fModel   = new ComboBoxValModel(anms2);
        jComboBox1.setModel(fModel);
        jComboBox1.setSelectedIndex(-1);
//        JPanel panel = new JPanel(new CardLayout()); 
 
          JPanel panel = new JPanel(new java.awt.CardLayout());
//          
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
//        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
       jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(2).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(2).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
////       
      }
  
    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }
    private boolean CheckForEditRequest(String tid) throws BasicException {
        boolean status = true;
        Object[] obj = (Object[]) new PreparedSentence(mapp.getSession(), "SELECT COUNT(A.ID) FROM ACCOUNTEDITDETAIL A JOIN ACCOUNTJOURNALDUP AD ON  AD.PAYMENTREF=? AND A.TID=AD.TID WHERE  A.CONFIRMEDBY IS  NULL", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(tid);
        if (obj != null && obj[0] != null) {
            if (Integer.parseInt(String.valueOf(obj[0])) > 0) {
                status = false;
            }
        }
        return status;
    }


    public void init(AppView app)  {
       jRadioButton1.setSelected(true);
       jTextField1.setVisible(false);
         mapp=app;
         ShowAll.setSelected(false);
       dlfac=(DataLogicFacilities) mapp.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
//        JPanel panel = new JPanel(new CardLayout()); 
      // Jpanel panel = new Jpanel(new java.awt.FlowLayout());
       JPanel panel = new JPanel(new java.awt.CardLayout());
       //jPanel1.setLayout(new java.awt.CardLayout());
      //  jPanel2.setLayout(new java.awt.CardLayout());
       }

    public Object getBean() {
        return this;
    }

 TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

     
     
    SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat df = new DecimalFormat("#0.00");
    public Component getTableCellRendererComponent(JTable jTable1,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        
        if( value instanceof Date) {
            value = f.format(value);
        }
         if( value instanceof Double) {
            value = df.format(value);
        }
        return super.getTableCellRendererComponent(jTable1, value, isSelected,
                hasFocus, row, column);
    }
};  
   
  TableCellRenderer tableCellRenderer1 = new DefaultTableCellRenderer() {
      DecimalFormat df = new DecimalFormat("#0.00");
      public Component getTableCellRendererComponent(JTable jTable1,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(
          jTable1, value, isSelected, hasFocus, row, column);

      //set Alignment
      cell.setHorizontalAlignment(SwingConstants.RIGHT);
      if( value instanceof Double) {
            value = df.format(value);
        }
      return cell;
      }
      @Override public void setValue(Object value) {
        setText(value instanceof Double ? df.format(value) : "");
    }
    
   
}; 


  public void datesFilledCheck(){
  for(int i=0;i<jTable1.getRowCount();i++){
    if(jTable1.getModel().getValueAt(i,7)==null){
            if(((double)jTable1.getModel().getValueAt(i,6))!=0.00){
            dateFlag=false;
            dateRow=i+1;
            break;
            }
    }
    if(jTable1.getModel().getValueAt(i,7)!=null){
            if(((double)jTable1.getModel().getValueAt(i,6))==0.00){
            amountFlag=false;
            amountRow=i+1;
            break;
            }
    }
  }//for
    
 
}

 private Date getSecondDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
       // cal.set(Calendar.AM_PM, Calendar.PM);
        d.setTime(cal.getTimeInMillis());
        return d;
    }


}


   
