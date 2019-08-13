
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DueListPanel.java
 *
 * Created on Sep 14, 2009, 10:34:06 AM
 */

/**
 *
 * @author swathi
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
//import com.openbravo.format.Formats;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.DueListTableModel.FacilityLine;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.clubmang.FacilityDetail;
import com.openbravo.pos.clubmang.FacilityInfo;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.clubmang.MemTypeTableModel.MemTypeline;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellRenderer;

public class DueListPanel1 extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private ComboBoxValModel fModel;
    private ComboBoxValModel memmodel;
    private DataLogicFacilities dlfac;
    private AppView m_App;
    private DueListTableModel dmodel;
    private ListModel flistmodel;
    private List<Facility> rflist;

    public DueListPanel1() {
        initComponents();
    }
   

    public void init(AppView app) {
       m_App=app;
       dlfac=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       jButton5.setText("Generate Report");
       jButton4.setText("Date");
       jButton2.setText("Execute ");
       jSpinner1.setModel(new SpinnerNumberModel(1, 1, 3, 1));
       jPanel1.setVisible(true);
       jPanel2.setVisible(true);
    }

    public void activate() throws BasicException {
       rflist=new ArrayList<Facility>();
       List<Facility> flist=dlfac.getFacility();
       //Facility f=null;
       fModel=new ComboBoxValModel(flist);
       jComboBox1.setModel(fModel);
       List<MemTypeline> mlist=dlfac.getMemType();
       memmodel=new ComboBoxValModel(mlist);
       jComboBox2.setModel(memmodel);
       jComboBox2.setSelectedIndex(-1);
       dmodel=DueListTableModel.loadEmptyInstance();
       jTable1.setModel(dmodel.getTableModel());
       jTable1.setAutoscrolls(true);
       jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
       flistmodel=new ListModel(new ArrayList());
       jList1.setModel(flistmodel);
       jCheckBox1.setSelected(false);
       jButton5.setEnabled(false);
       jPanel1.setVisible(true);
       jPanel2.setVisible(true);
    }

    public String getTitle() {
        return null;
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public Object getBean() {
       return this;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();

        setLayout(null);

        jPanel4.setName("jPanel4"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Facility"));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(null);

        jLabel1.setText("Facility");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 20, 90, 14);

        jComboBox1.setName("jComboBox1"); // NOI18N
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(130, 20, 220, 20);

        jLabel6.setText("Facility List");
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 50, 90, 14);

        jButton1.setText("Add");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(370, 20, 90, 23);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(130, 50, 220, 70);

        jButton3.setText("Remove");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(370, 50, 90, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Other"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setText("Member Type");
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox2.setName("jComboBox2"); // NOI18N

        jLabel3.setText("OverDue");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("30 days");
        jLabel4.setName("jLabel4"); // NOI18N

        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jTextField1.setName("jTextField1"); // NOI18N

        jLabel5.setText("days");
        jLabel5.setName("jLabel5"); // NOI18N

        jButton2.setText("Generate Report");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Date");
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jButton4.setText("jButton4");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 212, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel4);
        jPanel4.setBounds(0, 0, 0, 260);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 290, 660, 330);

        jLabel7.setText("Details   :");
        jLabel7.setName("jLabel7"); // NOI18N
        add(jLabel7);
        jLabel7.setBounds(10, 260, 60, 14);

        jSeparator1.setName("jSeparator1"); // NOI18N
        add(jSeparator1);
        jSeparator1.setBounds(10, 280, 40, 2);

        jLabel9.setText("Please check the status column for short or excess billing. If seen than tick yes to print it");
        jLabel9.setName("jLabel9"); // NOI18N
        add(jLabel9);
        jLabel9.setBounds(10, 630, 460, 14);

        jCheckBox2.setText("Yes");
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });
        add(jCheckBox2);
        jCheckBox2.setBounds(471, 620, 60, 30);

        jButton5.setText("jButton5");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5);
        jButton5.setBounds(550, 640, 130, 30);

        jLabel10.setText("No Of Facilities to be displayed in a report page");
        jLabel10.setName("jLabel10"); // NOI18N
        add(jLabel10);
        jLabel10.setBounds(10, 650, 270, 14);

        jSpinner1.setName("jSpinner1"); // NOI18N
        add(jSpinner1);
        jSpinner1.setBounds(290, 650, 29, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if(jComboBox1.getSelectedIndex()!=-1){
          Facility f=(Facility)jComboBox1.getSelectedItem();
          if(!flistmodel.getList().contains(f)){
            if(f.getName().equals("Bar")){
             List list=flistmodel.getList();
             list.add(f);
             flistmodel=new ListModel(list);
             jList1.setModel(flistmodel);
             jList1.validate();
             jComboBox1.setSelectedIndex(-1);
            }else{
               
            }
          }else
              JOptionPane.showMessageDialog(this, "Facility is already added to the list",null,JOptionPane.WARNING_MESSAGE);
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fname = null;
        try {
            if(jTextField2.getText().length()>0){
            List<Facility> flist = new ArrayList<Facility>();
            Facility f=dlfac.getFacilitybyID("1");
            ListModel mlist = (ListModel) jList1.getModel();
            flist.addAll(mlist.getList());
            for(int i=0;i<flist.size();i++){
                Facility f1=flist.get(i);
                if(f1.getid().equals("1")){
                    flist.remove(i);
                    flist.add(0,f);
                }
            }
            String facility=null;
            rflist.addAll(flist);
            String memtypeid=null;
            if(jComboBox2.getSelectedIndex() != -1)
            memtypeid= ((MemTypeline) jComboBox2.getSelectedItem()).getid();
            int overdue = 30;
            if (jCheckBox1.isSelected() && jTextField1.getText().length() > 0) {
                overdue = Integer.parseInt(jTextField1.getText());
            }
            Date d  =(Date) Formats.TIMESTAMP.parseValue(jTextField2.getText());
            Date d1 =(Date) Formats.TIMESTAMP.parseValue(jTextField2.getText());
            Calendar cal=Calendar.getInstance();
            cal.setTime(d);
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);
            if(cal.get(Calendar.DATE)<=15){
                cal.set(Calendar.DATE, 16);
                d.setTime(cal.getTimeInMillis());
                cal.set(Calendar.DATE, 1);
                d1.setTime(cal.getTimeInMillis());
            }else{
                cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                d.setTime(cal.getTimeInMillis());
                cal.set(Calendar.DATE, 16);
                d.setTime(cal.getTimeInMillis());
            }
            cal.add(Calendar.DATE, -overdue);
            d1.setTime(cal.getTimeInMillis());
            dmodel=DueListTableModel.loadData(m_App,fname,memtypeid,d,d1,dlfac,facility);
            jTable1.setModel(dmodel.getTableModel());
            int columncnt=jTable1.getColumnModel().getColumnCount();
            if(columncnt>0){
              jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
              jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
              jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
              jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
              for(int k=2;k<columncnt;k++){
               if((k-2)%3==0){
                jTable1.getColumnModel().getColumn(k).setMaxWidth(150);
                jTable1.getColumnModel().getColumn(k).setPreferredWidth(150);
               }else if((k-2)%3==1){
                jTable1.getColumnModel().getColumn(k).setMaxWidth(60);
                jTable1.getColumnModel().getColumn(k).setPreferredWidth(60);
               } else if((k-2)%3==2){
                jTable1.getColumnModel().getColumn(k).setMaxWidth(60);
                jTable1.getColumnModel().getColumn(k).setPreferredWidth(60);
               }
              }
            }
           /* Map reportparam=new HashMap();
            DataSourceProvider data1=new DataSourceProvider(dmodel.getReportList1());
            DueListDataSource ds=new DueListDataSource(dmodel.getReportList1());
            data1.setDataSource(ds);
           // JasperReportNew.runReport(m_App,  "./reports/com/openbravo/reports/DueReport.jrxml", reportparam, false, data1, true);
            //C:\Documents and Settings\swathi\Desktop\GarudaNew\reports\com\openbravo\reports
            JasperReportNew.runReport(m_App,  "C:\\Documents and Settings\\swathi\\Desktop\\GarudaNew\\reports\\com\\openbravo\\reports/DueReport.jrxml", reportparam, false, data1, true);
         //JasperPrint jp=JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/LedgerReport.jrxml", reportparam, false,data1,true);
            */
            //(AppView app,String reportFile,Map reportparams,boolean print,DataSourceProvider data1,boolean display)
            }
        }catch(NumberFormatException e1){
           JOptionPane.showMessageDialog(null, "Error");
        }
        catch (BasicException ex) {
           ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if(jCheckBox1.isSelected()){
            jTextField1.setVisible(true);
            jLabel5.setVisible(true);
        }else{
            jTextField1.setVisible(false);
            jLabel5.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row=jList1.getSelectedIndex();
        if(row>=0){
           ((ListModel)jList1.getModel()).removeElement(row);
           jList1.setModel(new ListModel(((ListModel)jList1.getModel()).getList()));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int n=Integer.parseInt(jSpinner1.getValue().toString());
        if(n>0 && n<4 && rflist.size()>0){
            Map reportparam=new HashMap();
            
            String path=null;
            //if(rflist.get(0).getid().equals("1")){
               
           // }
           /* if(n==1)
                    path="C:\\Documents and Settings\\swathi\\Desktop\\GarudaNew\\reports\\com\\openbravo\\reports/DueReport2.jrxml";
               else  if(n==2)
                    path="C:\\Documents and Settings\\swathi\\Desktop\\GarudaNew\\reports\\com\\openbravo\\reports/DueReport1.jrxml";
               else if(n==3)
                    path="C:\\Documents and Settings\\swathi\\Desktop\\GarudaNew\\reports\\com\\openbravo\\reports/DueReport.jrxml";
            */
            int cnt=rflist.size();
            int temp=1,m=n;
           // JasperReportNew.runReport(m_App,  "./reports/com/openbravo/reports/DueReport.jrxml", reportparam, false, data1, true);
            //C:\Documents and Settings\swathi\Desktop\GarudaNew\reports\com\openbravo\reports
            while(temp<=cnt){
               DataSourceProvider data1=new DataSourceProvider(dmodel.getReportList1());
               DueListDataSource ds=new DueListDataSource(dmodel.getReportList1());
               data1.setDataSource(ds);
               ds.setTemp(temp);
               if(m==1)
                    path="./reports/com/openbravo/reports/DueReport2.jrxml";
               else  if(m==2)
                    path="./reports/com/openbravo/reports/DueReport1.jrxml";
               else if(m==3)
                    path="./reports/com/openbravo/reports/DueReport.jrxml";
                JasperReportNew.runReport(m_App,  path, reportparam, false, data1, true,null);
                if(temp==cnt)
                    break;
                temp+=n;
                m=n;
                while(temp>cnt){
                    temp--;
                    m--;
                }
                if(temp==cnt)
                    m--;
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        jButton5.setEnabled(jCheckBox2.isSelected());
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTextField2.setText(Formats.TIMESTAMP.formatValue(date));
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
