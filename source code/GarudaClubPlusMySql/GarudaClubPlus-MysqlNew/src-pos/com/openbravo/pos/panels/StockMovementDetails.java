/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StockMovementDetails.java
 *
 * Created on Feb 1, 2009, 4:25:30 PM
 */
package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.BeanFactoryException;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import com.openbravo.data.gui.TableRendererBasic;

/**
 *
 * @author swathi
 */
public class StockMovementDetails extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form StockMovementDetails */
    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private StockTransferTable m_stockmodel = null;
    private DataLogicSales m_dlSales;

    public StockMovementDetails() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        //m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);
        stockdetail.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[]{Formats.DOUBLE, Formats.TIMESTAMP, Formats.STRING, Formats.STRING, Formats.DOUBLE, Formats.STRING, Formats.STRING, Formats.STRING, Formats.DOUBLE, Formats.STRING, Formats.STRING, Formats.STRING}));
        stockdetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25, 25));
        stockdetail.getTableHeader().setReorderingAllowed(false);
        stockdetail.setRowHeight(25);
        stockdetail.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        boolean permtemp = (m_App.getAppUserView().getUser().hasPermission("bar counter"));
        if (permtemp) {
            received.setEnabled(true);
            received.setVisible(true);
        } else {
            received.setEnabled(false);
            received.setVisible(false);
        }
    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public String getTitle() {
        return "Stock Transfer";
    }

    public void activate() throws BasicException {
        loadData();
    }

    public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }

    private void loadData() throws BasicException {
        Object o = m_App.getAppUserView().getUser().getWarehouse();
        String warehouse = null;
        if (o != null) {
            String[] warehouses = o.toString().split("#");
            warehouse = warehouses[0];
        }
        if (warehouse == null) {
            m_stockmodel = StockTransferTable.emptyinstance();
        } else {
            // INSERT TEMP ROWS IN NEW STOCKDIARY TABLE AS TAKING LONG TIME
             
             new PreparedSentence(m_App.getSession()
                       , "INSERT INTO stockdiary_rece  SELECT * FROM STOCKDIARY WHERE RECEIVEDBY IS NULL AND LOCATION IS NOT NULL AND LOCATION1 IS NOT NULL AND ID NOT IN (SELECT ID FROM STOCKDIARY_RECE)"
                       , null).exec();
            
             m_stockmodel = StockTransferTable.loadInstance(m_App, warehouse);
        }
        stockdetail.setModel(m_stockmodel.getdiscountTableModel());
        TableColumnModel jColumns = stockdetail.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(60);
        jColumns.getColumn(0).setResizable(true);
        jColumns.getColumn(1).setPreferredWidth(100);
        jColumns.getColumn(1).setResizable(true);
        jColumns.getColumn(2).setPreferredWidth(60);
        jColumns.getColumn(2).setResizable(true);
        jColumns.getColumn(3).setPreferredWidth(80);
        jColumns.getColumn(3).setResizable(true);
        jColumns.getColumn(4).setPreferredWidth(60);
        jColumns.getColumn(4).setResizable(true);
        jColumns.getColumn(5).setPreferredWidth(70);
        jColumns.getColumn(5).setResizable(true);
        jColumns.getColumn(6).setPreferredWidth(60);
        jColumns.getColumn(6).setResizable(true);
        jColumns.getColumn(7).setPreferredWidth(80);
        jColumns.getColumn(7).setResizable(true);
        jColumns.getColumn(8).setPreferredWidth(60);
        jColumns.getColumn(8).setResizable(true);
        jColumns.getColumn(9).setPreferredWidth(70);
        jColumns.getColumn(9).setResizable(true);
        jColumns.getColumn(10).setPreferredWidth(100);
        jColumns.getColumn(10).setResizable(true);
        jColumns.getColumn(11).setPreferredWidth(100);
        jColumns.getColumn(11).setResizable(true);

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        stockdetail = new javax.swing.JTable();
        received = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        stockdetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(stockdetail);

        received.setText("Receive");
        received.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receivedActionPerformed(evt);
            }
        });

        jButton1.setText("withdraw");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(57, 57, 57)
                        .addComponent(received))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(received, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    int temp = 0;
    private void receivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receivedActionPerformed
        // TODO add your handling code here:
       try{
            Transaction t = new Transaction(m_App.getSession()) {
          
                public Object transact() throws BasicException {
                    
                   int rowcount = stockdetail.getSelectedRowCount();
                   int row[] = stockdetail.getSelectedRows();
                   //  int row=stockdetail.getSelectedRow();
                   for (int j = 0; j < rowcount; j++) {
                       Double num = (Double) m_stockmodel.getdiscountTableModel().getValueAt(row[j], 0);
                       //String pdtname1 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 3).toString();
                       String pdtname1 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 15).toString();

                       //   Double qty1=(Double) m_stockmodel.getdiscountTableModel().getValueAt(row, 3);
                       Double qty2 = (Double) m_stockmodel.getdiscountTableModel().getValueAt(row[j], 8);
                       //String pdtname2 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 7).toString();
                       String pdtname2 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 16).toString();

                       //   String location=m_stockmodel.getdiscountTableModel().getValueAt(row, 10).toString();
                       String location1 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 13).toString();
                       String id = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 14).toString();
                       String user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
                       try {
                            temp = new StaticSentence(m_App.getSession(), "UPDATE STOCKDIARY SET RECEIVEDBY = ? WHERE   ID=? AND NUM=? AND CREATEDBY != ? AND RECEIVEDBY IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{user, id, num, user});
                           if (temp > 0) {
                               //Object[] pdtid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT P.ID,P1.ID FROM PRODUCTS P, PRODUCTS P1 WHERE P.NAME= ? AND P1.NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(new Object[]{pdtname1, pdtname2});
                               /*  Object[] param=new Object[]{qty1,location,pdtid[0]};
                               m_dlSales.getStockCurrentInsert().exec(param);*/
                               //Object[] param1 = new Object[]{qty2, location1, pdtid[1]};
                               Object[] param1 = new Object[]{qty2, location1, pdtname2};
                               int i= m_dlSales.getStockCurrentInsert().exec(param1);
                               if(i>0){
                                   new StaticSentence(m_App.getSession(), "DELETE FROM  STOCKDIARY_RECE WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{ id});
                               }
                               rowcount--;
                               j--;
                           } else {
                               //JOptionPane.showMessageDialog(this, "The initiator cannot receive or the product is already received", "Cannot Receive", JOptionPane.OK_OPTION);
                              // JOptionPane.showMessageDialog(this, "The initiator cannot receive or the product is already received",  JOptionPane.OK_OPTION);
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                   }


           return null;
            }
            };
            t.execute();
            loadData();
            
        }catch(Exception e){
                
                e.printStackTrace();
                new MessageInf(e).show(new JFrame());
                  Logger.getLogger(JPanelCloseMoney.class.getName()).log(Level.SEVERE, null, e);       
        }
        
        
        
        
        
       
}//GEN-LAST:event_receivedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int rowcount = stockdetail.getSelectedRowCount();
        int row[] = stockdetail.getSelectedRows();

        for (int j = 0; j < rowcount; j++) {
            Double num = (Double) m_stockmodel.getdiscountTableModel().getValueAt(row[j], 0);
            //String pdtname1 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 3).toString();
            String pdtname1 = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 15).toString();
            Double qty1 = (Double) m_stockmodel.getdiscountTableModel().getValueAt(row[j], 4);
            String location = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 12).toString();
            //  String location1=m_stockmodel.getdiscountTableModel().getValueAt(row, 11).toString();
            String id = m_stockmodel.getdiscountTableModel().getValueAt(row[j], 14).toString();
            String user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
            try {
                int temp = new StaticSentence(m_App.getSession(), "DELETE FROM STOCKDIARY WHERE CREATEDBY = ? AND ID=? AND RECEIVEDBY IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{user, id});
                if (temp == 1) {
                    //Object[] pdtid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT P.ID FROM PRODUCTS P WHERE P.NAME= ?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{pdtname1});
                        
                    // Object[] param1 = new Object[]{-qty1, location, pdtid[0]};
                    Object[] param1 = new Object[]{-qty1, location, pdtname1};
                    m_dlSales.getStockCurrentInsert().exec(param1);

                    rowcount--;
                    j--;
                } else {
                    JOptionPane.showMessageDialog(this, "Only the initiator can withdraw or the product is already received", "Cannot Withdraw", JOptionPane.OK_OPTION);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            loadData();
        } catch (Exception e1) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton received;
    private javax.swing.JTable stockdetail;
    // End of variables declaration//GEN-END:variables
}
