/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MemberStatement.java
 *
 * Created on 29-Sep-2011, 15:26:18
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class MemberStatement_old extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicFacilities dmang;
    private DataLogicSales m_dlSales;
    private MemberStatementModel dbmodel;
    private Date datefm;
    private Date dateto;
    private ComboBoxValModel memmodel;
    String monthlist[] = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    /** Creates new form MemberStatement */
    public MemberStatement_old() {
        initComponents();
    }

    public void generateReport(String memtype) throws BasicException, ParseException {
        //Date d=getDate(TOOL_TIP_TEXT_KEY,month.getSelectedIndex(), TOOL_TIP_TEXT_KEY);
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        String mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        Date date = (Date) Formats.DATE.parseValue(todate.getText());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        String iYear = sdf.format(date);
        String header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        final Date duedate = new Date();
        int month = 0;
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
//        reportparams.put("memno", memno.getText().toUpperCase());
//        reportparams.put("membername", mname.getText().toString());
//        reportparams.put("memAddress", dbmodel.getAddress());
                reportparams.put("header", header);
//        reportparams.put("pdues", dbmodel.getPrevDues());
//        reportparams.put("pbal", dbmodel.getPrevBalance());
//        reportparams.put("totaldues", dbmodel.getPrevDues() + dbmodel.getdebtAmount());
//        reportparams.put("amountreceivable", dbmodel.getAmountReceivable());
//        reportparams.put("amountpayable", dbmodel.getPrevDues() + dbmodel.getdebtAmount() - dbmodel.getPrevBalance() - dbmodel.getAmountReceivable());
                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                if (memtype == null) {
                    String accid = dmang.getCustomerAccountByID(customerInfo.getId());
              //  dbmodel = MemberStatementModel.loadInstance(m_App, customerInfo.getId(), accid, dmang, datefm, dateto, memno.getText().toUpperCase(), Flag);
                } else {
                    if (memtype.equals("0000")) {
                   //  dbmodel = MemberStatementModel.loadInstanceforAllMembers(m_App, dmang, datefm, dateto,Flag);
                    } else {
                     // dbmodel = MemberStatementModel.loadInstance(m_App, dmang, datefm, dateto, memtype);
                    }
                }
                DataSourceProvider data1 = new DataSourceProvider(dbmodel.getfacilityline());
                DataSourceForMemberStatement ds1 = new DataSourceForMemberStatement(dbmodel.getfacilityline());
                data1.setDataSource(ds1);
                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null);
            }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        fromdate = new javax.swing.JTextField();
        todate = new javax.swing.JTextField();
        tdate = new javax.swing.JButton();
        fdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Days = new javax.swing.JLabel();
        days = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        months = new javax.swing.JTextField();
        message = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        memno = new javax.swing.JTextField();
        mname = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(600, 448));

        jButton3.setText("Generate");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("All");
        jRadioButton1.setName("AllRbttn"); // NOI18N
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });

        jRadioButton2.setText("Individual");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 12));
        jTextArea1.setRows(5);
        jTextArea1.setName("note"); // NOI18N
        jScrollPane2.setViewportView(jTextArea1);

        jLabel4.setText("Note");
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setName("jPanel2"); // NOI18N

        fromdate.setName("fromdate"); // NOI18N

        todate.setName("todate"); // NOI18N

        tdate.setText("To Date");
        tdate.setName("tdate"); // NOI18N
        tdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdateActionPerformed(evt);
            }
        });

        fdate.setText("Month");
        fdate.setName("fdate"); // NOI18N
        fdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdateActionPerformed(evt);
            }
        });

        jLabel5.setText("Due Period");
        jLabel5.setName("jLabel5"); // NOI18N

        Days.setText("Days");
        Days.setName("Days"); // NOI18N

        days.setName("days"); // NOI18N

        jLabel6.setText("Months");
        jLabel6.setName("jLabel6"); // NOI18N

        months.setName("months"); // NOI18N

        message.setText("** Enter months and days field in numbers.....**");
        message.setName("message"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(todate)
                            .addComponent(fromdate, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fdate)
                            .addComponent(tdate))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Days, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(todate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Days, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        memno.setName("memno"); // NOI18N
        memno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memnoKeyPressed(evt);
            }
        });

        mname.setName("mname"); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Member Name");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel1.setText("Member No.");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(209, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel4.setName("jPanel4"); // NOI18N

        jLabel3.setText("Member Type");
        jLabel3.setName("mtypelbl"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jRadioButton1)
                        .addGap(32, 32, 32)
                        .addComponent(jRadioButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (jRadioButton2.isSelected()) {
                if (memno.getText().length() > 0 && mname.getText().length() > 0 && fromdate.getText().length() > 0 && todate.getText().length() > 0) {
                    generateReport(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
            } else if (jRadioButton1.isSelected()) {
                MemCat mem = (MemCat) jComboBox1.getSelectedItem();
                generateReport(mem.getID());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

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
}//GEN-LAST:event_jButton2ActionPerformed

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

    private void tdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdateActionPerformed
        // TODO add your handling code here:
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(todate.getText());

        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            todate.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_tdateActionPerformed

    private void fdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdateActionPerformed
        // TODO add your handling code here:
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(fromdate.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate.setText(Formats.DATE.formatValue(date));
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_fdateActionPerformed

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
        // TODO add your handling code here:
        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(true);
            jPanel1.setVisible(true);
            jPanel2.setVisible(true);
            memno.setVisible(false);
            mname.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            jButton2.setVisible(false);
            jLabel3.setVisible(true);
            jComboBox1.setVisible(true);
        }

    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        // TODO add your handling code here:
        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(true);
            jPanel1.setVisible(true);
            jPanel2.setVisible(true);
            memno.setVisible(true);
            mname.setVisible(true);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            jButton2.setVisible(true);
            jLabel3.setVisible(false);
            jComboBox1.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton2StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Days;
    private javax.swing.JTextField days;
    private javax.swing.JButton fdate;
    private javax.swing.JTextField fromdate;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField memno;
    private javax.swing.JLabel message;
    private javax.swing.JTextField mname;
    private javax.swing.JTextField months;
    private javax.swing.JButton tdate;
    private javax.swing.JTextField todate;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Member's Statement";
    }

    public void activate() throws BasicException {
        fromdate.setEnabled(false);
        todate.setEnabled(false);
        tdate.setVisible(false);
        reset();

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        memmodel = new ComboBoxValModel();
    }

    public Object getBean() {
        return this;
    }

    public void reset() throws BasicException {
        jTextArea1.setText(null);
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        memno.setVisible(false);
        mname.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jButton2.setVisible(false);
        jLabel3.setVisible(false);
        jComboBox1.setVisible(false);
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        fromdate.setText(null);
        todate.setText(null);
        memno.setText(null);
        mname.setText(null);
        List<MemCat> mlist = dmang.getMemberCategory();
        MemCat m = new MemCat();
        m.setName("All");
        m.setId("0000");
        mlist.add(0, m);
        memmodel = new ComboBoxValModel(mlist);
        jComboBox1.setModel(memmodel);

    }

    private Date getDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        // cal.set(Calendar.AM_PM, Calendar.PM);
        d.setTime(cal.getTimeInMillis());
        return d;
    }

    private Date getSecondDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);

        Calendar cal = GregorianCalendar.getInstance();
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
