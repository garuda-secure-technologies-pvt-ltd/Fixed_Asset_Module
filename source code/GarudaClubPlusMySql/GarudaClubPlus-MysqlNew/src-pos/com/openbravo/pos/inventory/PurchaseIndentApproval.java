/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchaseIndentApprover.java
 *
 * Created on 19-Oct-2011, 15:24:09
 */
//com.openbravo.pos.inventory.PurchaseIndentApproval
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteInteger;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class PurchaseIndentApproval extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicSales dlsales;
    private AbstractTableModel tableModel;
    private PurchaseIndentApprovalModel purApprModel;
    private DataLogicFacilities dlfac;
    private JComboBox comboBox;
    private List<PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine> purDetaill;
    private String purchaseIndentId;
    private int user;
    private PurchaseIndentModel pumodel;
    private int seqNo;

    /** Creates new form PurchaseIndentApprover */
    public PurchaseIndentApproval() {
        initComponents();
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getPurchaseIndentId() {
        return purchaseIndentId;
    }

    public void setPurchaseIndentId(String purchaseIndentId) {
        this.purchaseIndentId = purchaseIndentId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public void createPurchaseOrder() throws BasicException {
        try {
            if (jTable1.getRowCount() > 0) {
                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        //int row = jTable1.getSelectedRow();
                        String purOrderId = null;
                        int purchaseOrderSeq = 1;
                        StringBuffer sb = new StringBuffer();
                        Object object = new StaticSentence(m_App.getSession(), "SELECT MAX(PURCHASEORDERNO) FROM PURCHASEORDER", null, SerializerReadString.INSTANCE).find();
                        if (object != null) {
                            purchaseOrderSeq = purchaseOrderSeq + Integer.parseInt(object.toString());
                        }
                        int row = 0;
                        Map<String, String> map = new HashMap<String, String>();
                        ArrayList list = new ArrayList();
                        String[] str = null;
                        list.add(jTable1.getValueAt(0, 6));
                        purDetaill = purApprModel.getPurcheApproverDetailsList();
                        for (PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine p : purDetaill) {
                            if (p.isSelect() && p.isOrderedFlag()) {
                                if (!list.contains(p.getVendor())) {
                                    list.add(p.getVendor());
                                }
                            }

                        }
                        for (int i = 0; i < list.size(); i++) {
                            purOrderId = UUID.randomUUID().toString();
                            Object[] obj1 = new Object[]{purOrderId, purchaseOrderSeq, list.get(i).toString(), new Date()};
                            new StaticSentence(m_App.getSession(), "INSERT INTO PURCHASEORDER(ID,PURCHASEORDERNO,VENDOR,CRDATE) VALUES (?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.STRING, Datas.TIMESTAMP}), null).exec(obj1);
                            purchaseOrderSeq++;
                            map.put(list.get(i).toString(), purOrderId);
                        }
                        purDetaill = purApprModel.getPurcheApproverDetailsList();
                        for (PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine p : purDetaill) {
                            if (p.isSelect()) {
                                if (p.getApprovedBy() != null) {
                                    if (p.isOrderedFlag()) {
                                        Object[] values = new Object[]{UUID.randomUUID().toString(), map.get(p.getVendor()), p.getProductId(), p.getOrderedQty(), p.getOrderedQty1(), p.getOrderedRate(), p.getRemark()};
                                        insertIntoPurchaseOrderdetail(values);
                                    } else {
                                        sb.append(p.getProduct());
                                        sb.append(",");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, p.getProduct() + " is not approved u cannot create order for this product");
                                }
                            }
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.lastIndexOf(","));
                            JOptionPane.showMessageDialog(null, "the following products are rejected,you cannot create purchaseorder!!!\r\n" + sb.toString(), "empty table", JOptionPane.OK_OPTION);
                        }
                        set();
                        return null;
                    }
                };
                t.execute();

            } else {
                JOptionPane.showMessageDialog(this, "click back button to Load another indent!!!", "empty table", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        forward = new javax.swing.JButton();
        reject = new javax.swing.JButton();
        Reprint = new javax.swing.JButton();
        details = new javax.swing.JButton();
        back = new javax.swing.JButton();
        approve = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

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
        jScrollPane1.setViewportView(jTable1);

        forward.setText("Forward");
        forward.setName("forward"); // NOI18N
        forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardActionPerformed(evt);
            }
        });

        reject.setText("Reject");
        reject.setName("reject"); // NOI18N
        reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectActionPerformed(evt);
            }
        });

        Reprint.setText("Reprint");
        Reprint.setName("Reprint"); // NOI18N
        Reprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReprintActionPerformed(evt);
            }
        });

        details.setText("Details");
        details.setName("details"); // NOI18N
        details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.setName("back"); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        approve.setText("Approve");
        approve.setName("approve"); // NOI18N
        approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.setName("delete"); // NOI18N
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jNumberKeys1.setName("jNumberKeys1"); // NOI18N
        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        jTextField1.setName("jTextField1"); // NOI18N

        jButton1.setText("Enter");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(delete)
                        .addGap(18, 18, 18)
                        .addComponent(details)
                        .addGap(18, 18, 18)
                        .addComponent(reject)
                        .addGap(18, 18, 18)
                        .addComponent(approve)
                        .addGap(18, 18, 18)
                        .addComponent(back)
                        .addGap(18, 18, 18)
                        .addComponent(forward)
                        .addGap(18, 18, 18)
                        .addComponent(Reprint)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Reprint)
                            .addComponent(forward)
                            .addComponent(back)
                            .addComponent(approve)
                            .addComponent(reject)
                            .addComponent(details)
                            .addComponent(delete))))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        try {
            details.setVisible(true);
            Reprint.setVisible(false);
            set();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_backActionPerformed

    private void forwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable1.getRowCount() > 0) {
                int count = 0;
                purDetaill = purApprModel.getPurcheApproverDetailsList();
                for (PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine p : purDetaill) {
                    if (p.isSelect()) {
                        Object[] obj = new Object[]{m_App.getAppUserView().getUser().getId(), new Date(), p.getId()};
                        new StaticSentence(m_App.getSession(), "UPDATE PURCHASEINDENTDETAILS SET FORWARDEDBY=?,FORWARDEDDATE=? WHERE ID=? AND FORWARDEDBY IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING}), null).exec(obj);
                        count++;
                    }
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(this, "select any row", "invalid action", JOptionPane.WARNING_MESSAGE);
                } else {
                    setDetail(getPurchaseIndentId());
                }
            } else {
                JOptionPane.showMessageDialog(this, "click back button to Load another indent!!!", "empty table", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_forwardActionPerformed

    private void rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable1.getRowCount() > 0) {
                int count = 0;
                purDetaill = purApprModel.getPurcheApproverDetailsList();
                for (PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine p : purDetaill) {
                    if (p.isSelect()) {
                        if (getUser() == 1) {
                            Object[] obj = new Object[]{m_App.getAppUserView().getUser().getId(), new Date(), false, p.getId()};
                            new StaticSentence(m_App.getSession(), "UPDATE PURCHASEINDENTDETAILS SET FORWARDEDBY=?,FORWARDEDDATE=?,ORDEREDFLAG=? WHERE ID=? AND FORWARDEDBY IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING}), null).exec(obj);
                            count++;
                        } else if (getUser() == 2) {
                            Object[] obj1 = new Object[]{m_App.getAppUserView().getUser().getId(), new Date(), false, p.getId()};
                            new StaticSentence(m_App.getSession(), "UPDATE PURCHASEINDENTDETAILS SET APPROVEDBY=?,APPROVEDDATE=?,ORDEREDFLAG=? WHERE ID=? AND APPROVEDBY IS NULL AND ORDEREDFLAG IS NULL ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING}), null).exec(obj1);
                            count++;
                        }
                    }
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(this, "select any row", "invalid action", JOptionPane.WARNING_MESSAGE);
                } else {
                    setDetail(getPurchaseIndentId());
                }
            } else {
                JOptionPane.showMessageDialog(this, "click back button to load another indent!!!", "empty table", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rejectActionPerformed

    private void ReprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReprintActionPerformed
        // TODO add your handling code here:
        try {
            print(getSeqNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_ReprintActionPerformed

    private void print(int indentno) {
        try {
            Date crdate = new Date();
            String createdby = null;
            String createdrole = null;
            Object obj1 = new StaticSentence(m_App.getSession(), "SELECT count(p.id) from purchaseindentdetails p,purchaseindent p1 WHERE p1.SEQNO=? and p1.id=p.purchaseindentid and p.forwardedby is not null", SerializerWriteInteger.INSTANCE,SerializerReadInteger.INSTANCE).find(indentno);
            int count=Integer.parseInt(obj1.toString());
            if(count==0){
            pumodel = PurchaseIndentModel.loadIndentsToPrint(m_App, indentno);
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT P.NAME,PI.CRDATE FROM PURCHASEINDENT PI,PEOPLE P  WHERE SEQNO=? AND PI.CREATEDBY=P.ID", SerializerWriteInteger.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP})).find(indentno);
            if (obj != null) {
                createdby = obj[0].toString();
                crdate = (Date) obj[1];
            }
            List<PurchaseIndentModel.PrintPurchaseIndent> list = pumodel.getPrintPurchaseIndents();
            Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());
            reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
            reportparams.put("date", crdate);
            reportparams.put("createdBy", createdby);
            reportparams.put("createdRole", createdrole);
            reportparams.put("pino", indentno);
            DataSourceProvider data1 = new DataSourceProvider(list);
            DataSourcePurchaseIndent ds = new DataSourcePurchaseIndent(list);
            data1.setDataSource(ds);
            JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/PurchaseIndentInit.jrxml", reportparams, false, data1, true, null);
            }else{
                JOptionPane.showMessageDialog(this, "the selected indent is forwarded..cannot take printout","invalid operation",JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable1.getSelectedRow() != -1) {
                int row = jTable1.getSelectedRow();
                String id = purApprModel.getPurchaseIndentApprover().getValueAt(row, 4).toString();
                int i = Integer.parseInt(purApprModel.getPurchaseIndentApprover().getValueAt(row, 0).toString());
                setSeqNo(i);
                setPurchaseIndentId(id);
                setDetail(getPurchaseIndentId());
                details.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "select any row", "invalid action", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_detailsActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable1.getRowCount() > 0) {
                int count = 0;
                String s = null;
                purDetaill = purApprModel.getPurcheApproverDetailsList();
                Object created = new StaticSentence(m_App.getSession(), "SELECT CREATEDBY FROM PURCHASEINDENT WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(getPurchaseIndentId());
                if (created != null && created.toString().equals(m_App.getAppUserView().getUser().getId())) {
                    for (PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine p : purDetaill) {
                        if (p.isSelect()) {
                            Object[] obj = new Object[]{p.getId()};
                            int i = new StaticSentence(m_App.getSession(), "DELETE FROM PURCHASEINDENTDETAILS WHERE ID=? AND FORWARDEDBY IS NULL AND APPROVEDBY IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING}), null).exec(obj);
                            if (i == 0) {
                                JOptionPane.showMessageDialog(this, p.getProduct() + " is forwarded or else approved", "invalid opeartion", JOptionPane.WARNING_MESSAGE);
                            }
                            count++;
                        }
                    }
                    if (count == 0) {
                        JOptionPane.showMessageDialog(this, "select any row", "invalid action", JOptionPane.WARNING_MESSAGE);
                    } else {
                        setDetail(getPurchaseIndentId());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Creater can only delete", "invalid action", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "click back button to Load another indent!!!", "empty table", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_deleteActionPerformed

    public String getVendorId(String name) throws BasicException {
        String vId = null;
        Object obj = new StaticSentence(m_App.getSession(), "SELECT ID FROM VENDOR WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(name);
        if (obj != null) {
            vId = obj.toString();
        }
        return vId;
    }
    

    private void approveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable1.getRowCount() > 0) {
                int count = 0;
                purDetaill = purApprModel.getPurcheApproverDetailsList();
                for (PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine p : purDetaill) {
                    if (p.isSelect()) {
                        Object[] obj = new Object[]{p.getOrderedQty1(),p.getOrderedQty1(),p.getOrderedRate(),getVendorId(p.getVendor()),p.getRemark(),m_App.getAppUserView().getUser().getId(), new Date(), p.getId()};
                        new StaticSentence(m_App.getSession(), "UPDATE PURCHASEINDENTDETAILS SET APPQTY=?,BALANCEINDQTY=?,APPRATE=?,APPVENDOR=?,REMARKS=?,APPROVEDBY=?, APPROVEDDATE=?  WHERE ID=? AND APPROVEDBY IS NULL", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.INT, Datas.DOUBLE,Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING}), null).exec(obj);
                        count++;
                    }
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(this, "select any row", "invalid action", JOptionPane.WARNING_MESSAGE);
                } else {
                    setDetail(getPurchaseIndentId());
                }
            } else {
                JOptionPane.showMessageDialog(this, "click back button to Load another indent!!!", "empty table", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_approveActionPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
}//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DecimalFormat dFormat = new DecimalFormat("#.##");
        try {
            int row = jTable1.getSelectedRow();
            System.out.println("-------- " + row + " --------");
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Please select any row to insert value", "Cannot insert", JOptionPane.OK_OPTION);
            } else if (row >= 0) {
                int column = jTable1.getSelectedColumn();
                if (jTextField1.getText().length() > 0) {
                    if (column == 3) {
                        try {
                            int qty = Integer.parseInt(jTextField1.getText());
                            purApprModel.getPurchaseIndentApproverDetails().setValueAt(qty, row, column);
                            if (jTable1.getRowCount() - 1 != row) {
                                jTable1.setRowSelectionInterval(row + 1, row + 1);
                            }
                            jTextField1.setText(null);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Enter only numbers");
                        }
                    } else if (column == 5) {
                        Double rate = dlfac.roundTwoDecimals(Double.valueOf(jTextField1.getText()).doubleValue());
                        purApprModel.getPurchaseIndentApproverDetails().setValueAt(rate, row, column);
                        if (jTable1.getRowCount() - 1 != row) {
                            jTable1.setRowSelectionInterval(row + 1, row + 1);
                        }
                        jTextField1.setText(null);
                    }
                }

            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            jTextField1.setText(null);
        } else if (cTrans == '+' || cTrans == '-') {
        } else if (cTrans == ' ' || cTrans == '=') {
        } else {

            jTextField1.setText(jTextField1.getText() + cTrans);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reprint;
    private javax.swing.JButton approve;
    private javax.swing.JButton back;
    private javax.swing.JButton delete;
    private javax.swing.JButton details;
    private javax.swing.JButton forward;
    private javax.swing.JButton jButton1;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton reject;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Purchase Indent Approval";
    }

    public void activate() throws BasicException {
        checkForUser();
        List vlist = dlfac.getVendorList();
        String[] str = new String[vlist.size()];
        for (int i = 0; i < vlist.size(); i++) {
            str[i] = vlist.get(i).toString();
        }
        comboBox = new JComboBox(str);
        details.setVisible(true);
        set();
    }

    public void set() throws BasicException {
        jButton1.setEnabled(false);
        Reprint.setVisible(false);
        approve.setVisible(false);
        delete.setVisible(false);
        forward.setVisible(false);
        reject.setVisible(false);
        back.setVisible(false);
        if (getUser() == 3) {
            setInitiater();
            details.setVisible(true);
        } else if (getUser() == 1) {
            setForwarder();
        } else if (getUser() == 2) {
            jButton1.setEnabled(true);
            setApprover();
        } else {
            purApprModel = PurchaseIndentApprovalModel.loademptyInstance();
            load();
        }

    }

    public void setDetail(String id) throws BasicException {
        if (getUser() == 3) {
            setInitiaterDetail(id);
            Reprint.setVisible(true);
        }
        if (getUser() == 1) {
            setForwarderDetail(id);
        }
        if (getUser() == 2) {
            setApproverDetail(id);
        }
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dlsales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

    }

    public Object getBean() {
        return this;
    }

    public void setForwarder() throws BasicException {
        purApprModel = PurchaseIndentApprovalModel.loadInstanceModelForwarder(m_App);
        load();
    }

    public void setInitiater() throws BasicException {
        purApprModel = PurchaseIndentApprovalModel.loadInstanceModelInitiater(m_App);
        load();
    }

    public void setApprover() throws BasicException {
        purApprModel = PurchaseIndentApprovalModel.loadInstanceModelApprover(m_App);
        load();
    }

    public void setForwarderDetail(String id) throws BasicException {
        forward.setVisible(true);
        reject.setVisible(true);
        back.setVisible(true);
        purApprModel = PurchaseIndentApprovalModel.loadinstanceModelFarwarder1(m_App, id);
        loadDetailsForwarder();
    }

    public void setInitiaterDetail(String id) throws BasicException {
        //createpurchaseorder.setVisible(true);
        delete.setVisible(true);
        back.setVisible(true);
        purApprModel = PurchaseIndentApprovalModel.loadinstanceModelInitiater1(m_App, id);
        loadDetailsInitiator();
    }

    public void setApproverDetail(String id) throws BasicException {
        approve.setVisible(true);
        reject.setVisible(true);
        back.setVisible(true);
        purApprModel = PurchaseIndentApprovalModel.loadinstanceModelApprover1(m_App, id);
        loadDetailsApprover();
    }

    public void load() throws BasicException {
        purApprModel.setUser(getUser());
        tableModel = purApprModel.getPurchaseIndentApprover();
        jTable1.setModel(tableModel);
        TableColumnModel jColumns = jTable1.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(50);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(80);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(100);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(100);
        jColumns.getColumn(3).setResizable(false);

    }

    public void loadDetailsForwarder() throws BasicException {
        tableModel = purApprModel.getPurchaseIndentForderDetails();
        jTable1.setModel(tableModel);
        TableColumnModel jColumns = jTable1.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(30);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(250);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(100);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(100);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(100);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(100);
        jColumns.getColumn(5).setResizable(false);
        jColumns.getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
        jColumns.getColumn(6).setPreferredWidth(250);
        jColumns.getColumn(6).setResizable(false);
        jColumns.getColumn(7).setPreferredWidth(250);
        jColumns.getColumn(7).setResizable(false);
    }

    public void loadDetailsInitiator() throws BasicException {
        tableModel = purApprModel.getPurchaseIndentInitiaterDetails();
        jTable1.setModel(tableModel);
        TableColumnModel jColumns = jTable1.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(30);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(250);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(100);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(100);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(100);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(100);
        jColumns.getColumn(5).setResizable(false);
        jColumns.getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
        jColumns.getColumn(6).setPreferredWidth(250);
        jColumns.getColumn(6).setResizable(false);
        jColumns.getColumn(7).setPreferredWidth(250);
        jColumns.getColumn(7).setResizable(false);
        jColumns.getColumn(8).setPreferredWidth(200);
        jColumns.getColumn(8).setResizable(false);
        jColumns.getColumn(9).setPreferredWidth(200);
        jColumns.getColumn(9).setResizable(false);
        jColumns.getColumn(10).setPreferredWidth(200);
        jColumns.getColumn(10).setResizable(false);
        jColumns.getColumn(11).setPreferredWidth(200);
        jColumns.getColumn(11).setResizable(false);
    }

    public void loadDetailsApprover() throws BasicException {
        tableModel = purApprModel.getPurchaseIndentApproverDetails();
        jTable1.setModel(tableModel);
        TableColumnModel jColumns = jTable1.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(30);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(250);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(100);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(100);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(100);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(100);
        jColumns.getColumn(5).setResizable(false);
        jColumns.getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
        jColumns.getColumn(6).setPreferredWidth(250);
        jColumns.getColumn(6).setResizable(false);
        jColumns.getColumn(7).setPreferredWidth(250);
        jColumns.getColumn(7).setResizable(false);
        jColumns.getColumn(8).setPreferredWidth(200);
        jColumns.getColumn(8).setResizable(false);
        jColumns.getColumn(9).setPreferredWidth(200);
        jColumns.getColumn(9).setResizable(false);
    }

    private void insertIntoPurchaseOrderdetail(Object[] val) throws BasicException {
        new StaticSentence(m_App.getSession(), "INSERT INTO PURCHASEORDERDETAIL(ID,PURCHASEORDERID,PRODUCTID,QTY,BALANCEQTY,RATE,REMARK) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING})).exec(val);
        new StaticSentence(m_App.getSession(), "UPDATE PURCHASEINDENTDETAILS SET PURCHASEORDERREF=? WHERE PRODUCTID=? AND PURCHASEORDERREF IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), null).exec(new Object[]{val[0], val[2]});
    }

    public Object[] getProductsCount(String id) throws BasicException {
        int i = 0;
        int j = 0;
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT, Datas.INT})).find(id);
        if (obj != null) {
            if (obj[0] != null) {
                i = Integer.parseInt(obj[0].toString());
            }
            if (obj[1] != null) {
                j = Integer.parseInt(obj[0].toString());
            }
        }
        return new Object[]{i, j};
    }

    public void checkForUser() throws BasicException {
        int i = 0;//default
        String userId = m_App.getAppUserView().getUser().getRole();
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT INITIATER,FORWARDER,APPROVER FROM PURCHASEINDENTAPPROVALRITES", null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find();
        if (obj != null) {
            if (obj[1] != null && obj[2] != null) {
                if (userId.equals(obj[1].toString())) {
                    i = 1;//forwarder
                } else if (userId.equals(obj[2].toString())) {
                    i = 2;//approver
                } else if (userId.equals(obj[0].toString())) {
                    i = 3;//initiator
                }
            }
        }
        setUser(i);
    }
}
