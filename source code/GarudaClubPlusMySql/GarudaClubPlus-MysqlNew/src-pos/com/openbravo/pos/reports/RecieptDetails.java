/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RecieptDetails.java
 *
 * Created on 19-Oct-2013, 10:28:54
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.reports.RecieptDetailsTableModel.MyAbstractTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author user
 */
public class RecieptDetails extends javax.swing.JPanel implements JPanelView, BeanFactoryApp{

    private AppView m_app;
    private RecieptDetailsTableModel dbmodel;
    private MyAbstractTableModel tablemodel;
    private DataLogicFacilities dlfac;
    private String recno;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");
    
    public RecieptDetails() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        recieptno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.cyan);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.lightGray);
            }
            return c;
        }};
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        adjAmt = new javax.swing.JLabel();
        unadjAmt = new javax.swing.JLabel();
        totAmt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Reciept No:");
        jLabel1.setName("jLabel1"); // NOI18N

        recieptno.setName("recieptno"); // NOI18N
        recieptno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recieptnoKeyPressed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Facility Name", "Billed Date","Org. Amt", "Adjusted Amt"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel2.setText("Adjusted Bill Details :");
        jLabel2.setName("jLabel2"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel4.setText("As On :");
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setEditable(false);

        jLabel3.setText("Adjusted Amount :");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel5.setText("Unadjusted Amount :");
        jLabel5.setName("jLabel5"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N

        jLabel6.setText("Total Amount :");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setName("jLabel7"); // NOI18N

        adjAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        adjAmt.setText("");
        adjAmt.setName("adjAmt"); // NOI18N

        unadjAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        unadjAmt.setText("");
        unadjAmt.setName("unadjAmt"); // NOI18N

        totAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totAmt.setText("");
        totAmt.setName("totAmt"); // NOI18N

        jButton1.setText("Print");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Dated :");
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.setEditable(false);

        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("jLabel9");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2)
                                    .addComponent(recieptno, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                .addGap(72, 72, 72)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(totAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(unadjAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(adjAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(518, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(recieptno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(adjAmt))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unadjAmt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void recieptnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recieptnoKeyPressed
     if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) 
     {
         recno=recieptno.getText().trim();
        Date date=new Date();
        jTextField1.setText(date.toString());
        
       dbmodel=RecieptDetailsTableModel.loadInstance(recno,m_app,dlfac);
       if(dbmodel.getNosuchrecp()==null){
           
           if(dbmodel.getUnadjustedAmt()+dbmodel.getAdjustedAmt()!=dbmodel.getTotalAmt())
           {
               jLabel9.setVisible(true);
               jLabel9.setText("Incomplete data available.");
           }
           
           
       tablemodel=dbmodel.getTableModel();
       jTable1.setModel(tablemodel);
       adjAmt.setText(decimalFormat.format(dbmodel.getAdjustedAmt()));
      // unadjAmt.setText(Formats.DOUBLE.formatValue(dbmodel.getUnadjustedAmt()));
       unadjAmt.setText(decimalFormat.format(dbmodel.getUnadjustedAmt()));
       totAmt.setText(decimalFormat.format(dbmodel.getTotalAmt()));
       jTextField2.setText(Formats.TIMESTAMP.formatValue(dbmodel.getReceiptdate()));
       }
       else
       {
           JOptionPane.showMessageDialog(this,dbmodel.getNosuchrecp());
           jTextField1.setText(null);
           recieptno.setText(null);
       }
     }
     else
     {
         jLabel9.setText(null);
         jTextField2.setText(null); 
         jTextField1.setText(null);
       dbmodel=RecieptDetailsTableModel.emptyInstance(); 
       tablemodel=dbmodel.getTableModel();
       jTable1.setModel(tablemodel);
       adjAmt.setText(null);
       unadjAmt.setText(null);
       totAmt.setText(null);
        
     }
}//GEN-LAST:event_recieptnoKeyPressed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   
    generateJasperReport();
}//GEN-LAST:event_jButton1ActionPerformed

 public void generateJasperReport(){
     
          DataSourceProvider data1 = new DataSourceProvider(dbmodel.getRdblist());
           DataSourceForRecieptDetails  dsfc = new  DataSourceForRecieptDetails (dbmodel.getRdblist());
          data1.setDataSource(dsfc);
          Map reportparams = new HashMap();
           reportparams.put("companyName", m_app.getSession().getCompanyName());
          reportparams.put("companyAddress", m_app.getSession().getCompanyAddress());
           reportparams.put("reportType"," Details For Receipt NO ");
           reportparams.put("adjAmt",dbmodel.getAdjustedAmt());
           reportparams.put("unadjAmt",dbmodel.getUnadjustedAmt());
           reportparams.put("TotalAmt",dbmodel.getTotalAmt());
           reportparams.put("recno", recno.toUpperCase());
           reportparams.put("receiptDate", dbmodel.getReceiptdate());
           
     JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/Recdetail.jrxml", reportparams, false, data1, true, null);
 
 }





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adjAmt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField recieptno;
    private javax.swing.JLabel totAmt;
    private javax.swing.JLabel unadjAmt;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Reciept Detail";
    }

    public void activate() throws BasicException {
       loadData();
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       m_app=app;
       dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
    }

    public Object getBean() {
        return this;
    }
    
    public void loadData()
    {
       recieptno.setText(null);
       dbmodel=RecieptDetailsTableModel.emptyInstance(); 
       tablemodel=dbmodel.getTableModel();
       jTable1.setModel(tablemodel);
       jTextField1.setText(null);
       adjAmt.setText(null);
       unadjAmt.setText(null);
       totAmt.setText(null);
       jTextField2.setText(null);
    }    
    
    
}
