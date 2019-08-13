/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchaseOrder.java
 *
 * Created on 17-Nov-2011, 18:13:54
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteInteger;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.PurchaseOrderModel.MyAbstractTableModel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class PurchaseOrder extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicFacilities dlfac;
    private DataLogicSales dlsales;
    private PurchaseOrderModel pomodel;
    private MyAbstractTableModel tablemodel;
    private AbstractTableModel tablemodel1;
    private ComboBoxValModel vendormodel;
    private ComboBoxValModel othersmodel;
    private ComboBoxValModel paymentsmodel;
    private ComboBoxValModel deliveryperiodmodel;
    private ComboBoxValModel taxmodel;
    private String textHeader;
    private boolean oamtFlag = false;
    private double lastEnteredAmt = 0.0;

    /** Creates new form PurchaseOrder */
    public PurchaseOrder() {
        initComponents();
    }

    public void loadTable2() {
        tablemodel1 = pomodel.getPurchaseModel1();
        potable.setModel(tablemodel1);
        TableColumnModel jColumns = potable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(200);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(80);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(100);
        jColumns.getColumn(3).setResizable(false);
        reset();
    }

    private int getPurchaseOrderNo() throws BasicException {
        int seqno = 1;
        Object object = new StaticSentence(m_App.getSession(), "SELECT MAX(PURCHASEORDERNO) FROM PURCHASEORDER", null, SerializerReadString.INSTANCE).find();
        if (object != null) {
            seqno = seqno + Integer.parseInt(object.toString());
        }
        return seqno;
    }

    private void print(int pono) {
        try {
            Date crdate = new Date();
            String createdby = null;
            String createdrole = null;
            pomodel = PurchaseOrderModel.loadPurchaseOrderProducts(m_App, pono);
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT P.NAME,PO.CRDATE FROM PURCHASEORDER PO,PEOPLE P  WHERE PO.PURCHASEORDERNO=? AND PO.CREATEDBY=P.ID", SerializerWriteInteger.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP})).find(pono);
            if (obj != null) {
                createdby = obj[0].toString();
                crdate = (Date) obj[1];
            }
            String vname = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getVname().toString();
            String vaddress = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getVaddress().toString();
            List<PurchaseOrderModel.PurchaseOrderLinePrint> list = pomodel.getPrintpo();
            Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());
            reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
            reportparams.put("date", crdate);
            reportparams.put("createdBy", createdby);
            reportparams.put("pono", pono);
            reportparams.put("vname", vname);
            reportparams.put("vaddress", vaddress);
            reportparams.put("header", headerarea.getText());
            reportparams.put("others", others.getText());
            reportparams.put("payment", paymentmode.getText());
            reportparams.put("deliver", deliveryperiod.getText());
            reportparams.put("tax", tax.getText());
            reportparams.put("remark", remarkarea.getText());
            reportparams.put("other", Double.parseDouble(oamt.getText().toString()));
            reportparams.put("grandtotal", Double.parseDouble(grandtotal.getText().toString()));
            DataSourceProvider data1 = new DataSourceProvider(list);
            DataSourceForPurchaseOrder ds = new DataSourceForPurchaseOrder(list);
            data1.setDataSource(ds);
            JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/PurchaseOrder.jrxml", reportparams, false, data1, true, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable(String vid) throws BasicException {
        ponor.setText(String.valueOf(getPurchaseOrderNo()));
        pomodel = PurchaseOrderModel.loadIndentedProducts(m_App, vid, dlfac);
        tablemodel = pomodel.getPurchaseModel();
        tablemodel.settext(grandtotal);
        potable.setModel(tablemodel);
        TableColumnModel jColumns = potable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(50);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(200);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(100);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(100);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(100);
        jColumns.getColumn(4).setResizable(false);
        grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(pomodel.getGrandTotal())));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        grandtotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        others = new javax.swing.JTextField();
        oamt = new javax.swing.JTextField();
        otherslist = new javax.swing.JComboBox();
        otherlabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        enter = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        potable = new javax.swing.JTable();
        back = new javax.swing.JButton();
        next = new javax.swing.JButton();
        print = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        taxlist = new javax.swing.JComboBox();
        tax = new javax.swing.JTextField();
        deliveryperiod = new javax.swing.JTextField();
        paymentmodelist = new javax.swing.JComboBox();
        paymentslabel = new javax.swing.JLabel();
        paymentmode = new javax.swing.JTextField();
        deliveryperiodlist = new javax.swing.JComboBox();
        deliverylabel = new javax.swing.JLabel();
        taxlabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarkarea = new javax.swing.JTextArea();
        remarklabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        headerarea = new javax.swing.JTextArea();
        header = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        ponor = new javax.swing.JTextField();
        vendor = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        createorder = new javax.swing.JButton();

        jPanel2.setName("jPanel2"); // NOI18N

        grandtotal.setName("grandtotal"); // NOI18N

        jLabel4.setText("Grand Total:");
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(grandtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        others.setName("others"); // NOI18N

        oamt.setName("oamt"); // NOI18N
        oamt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oamtMouseClicked(evt);
            }
        });

        otherslist.setName("otherslist"); // NOI18N
        otherslist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                otherslistItemStateChanged(evt);
            }
        });
        otherslist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherslistActionPerformed(evt);
            }
        });

        otherlabel.setText("Others:");
        otherlabel.setName("otherlabel"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(otherslist, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(others, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oamt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(others, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(otherslist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel8.setName("jPanel8"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        enter.setText("Enter");
        enter.setName("enter"); // NOI18N
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });

        jNumberKeys1.setName("jNumberKeys1"); // NOI18N
        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        jPanel7.setName("jPanel7"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        potable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        potable.setName("potable"); // NOI18N
        jScrollPane1.setViewportView(potable);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        back.setText("Back");
        back.setName("back"); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        next.setText("Next");
        next.setName("next"); // NOI18N
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        print.setText("Print");
        print.setName("print"); // NOI18N
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        taxlist.setName("taxlist"); // NOI18N
        taxlist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                taxlistItemStateChanged(evt);
            }
        });

        tax.setName("tax"); // NOI18N

        deliveryperiod.setName("deliveryperiod"); // NOI18N

        paymentmodelist.setName("paymentmodelist"); // NOI18N
        paymentmodelist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paymentmodelistItemStateChanged(evt);
            }
        });

        paymentslabel.setText("Payment:");
        paymentslabel.setName("paymentslabel"); // NOI18N

        paymentmode.setName("paymentmode"); // NOI18N

        deliveryperiodlist.setName("deliveryperiodlist"); // NOI18N
        deliveryperiodlist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                deliveryperiodlistItemStateChanged(evt);
            }
        });

        deliverylabel.setText("Delivery Period:");
        deliverylabel.setName("deliverylabel"); // NOI18N

        taxlabel.setText("Tax:");
        taxlabel.setName("taxlabel"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taxlabel)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(deliverylabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paymentslabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taxlist, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryperiodlist, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryperiod, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(paymentmodelist, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paymentmode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentmode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentmodelist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverylabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryperiod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliveryperiodlist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(taxlist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        remarkarea.setColumns(20);
        remarkarea.setRows(5);
        remarkarea.setName("remarkarea"); // NOI18N
        jScrollPane2.setViewportView(remarkarea);

        remarklabel.setText("Remark:");
        remarklabel.setName("remarklabel"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(remarklabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(remarklabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );

        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        headerarea.setColumns(20);
        headerarea.setRows(5);
        headerarea.setName("headerarea"); // NOI18N
        jScrollPane3.setViewportView(headerarea);

        header.setText("Header:");
        header.setName("header"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel6.setName("jPanel6"); // NOI18N

        jLabel2.setText("Date:");
        jLabel2.setName("jLabel2"); // NOI18N

        date.setName("date"); // NOI18N

        ponor.setName("ponor"); // NOI18N

        vendor.setName("vendor"); // NOI18N
        vendor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                vendorItemStateChanged(evt);
            }
        });

        jLabel1.setText("Vendor:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setText("PO.No:");
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ponor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ponor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createorder.setText("Create PO");
        createorder.setName("createorder"); // NOI18N
        createorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createorderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(print)
                                .addGap(18, 18, 18)
                                .addComponent(back)
                                .addGap(18, 18, 18)
                                .addComponent(next)
                                .addGap(18, 18, 18)
                                .addComponent(createorder))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(323, 323, 323))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(print)
                            .addComponent(back)
                            .addComponent(next)
                            .addComponent(createorder))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
}//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        // TODO add your handling code here:
        try {
            if (!oamtFlag) {
                int row = potable.getSelectedRow();
                System.out.println("-------- " + row + " --------");
                if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Please select any row to insert value", "Cannot insert", JOptionPane.OK_OPTION);
                } else if (row >= 0) {
                    int column = potable.getSelectedColumn();
                    if (jTextField1.getText().length() > 0) {
                        if (column == 2) {
                            try {
                                Double old = Double.valueOf(pomodel.getPurchaseModel().getValueAt(row, 4).toString());
                                int qty = Integer.parseInt(jTextField1.getText());
                                pomodel.getPurchaseModel().setValueAt(qty, row, column);
                                if (Boolean.valueOf(pomodel.getPurchaseModel().getValueAt(row, 0).toString())) {
                                    Double d = Double.valueOf(pomodel.getPurchaseModel().getValueAt(row, 4).toString());
                                    Double totalamt = Double.parseDouble(grandtotal.getText()) - old + d;
                                    grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(totalamt)));
                                }
                                if (potable.getRowCount() - 1 != row) {
                                    potable.setRowSelectionInterval(row + 1, row + 1);
                                }
                                jTextField1.setText(null);
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Enter only numbers");
                            }
                        }
                    }
                }
            } else {
                if (jTextField1.getText().length() > 0) {
                    Double amt = dlfac.roundTwoDecimals(Double.parseDouble(jTextField1.getText().toString()));
                    oamt.setText(amt.toString());
                    Double totalamt = Double.parseDouble(grandtotal.getText()) - lastEnteredAmt + amt;
                    grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(totalamt)));
                    lastEnteredAmt = amt;
                    jTextField1.setText(null);
                }
                oamtFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_enterActionPerformed

    private void createorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createorderActionPerformed
        // TODO add your handling code here:
        try {
            Transaction t = new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                    String purOrderId = UUID.randomUUID().toString();
                    int purchaseOrderSeq = Integer.parseInt(ponor.getText().toString());
                    String vid = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getId().toString();
                    String remark = others.getText()+" # "+paymentmode.getText()+" # "+deliveryperiod.getText()+" # "+tax.getText()+" # "+remarkarea.getText();
                    Object[] obj = new Object[]{purOrderId, purchaseOrderSeq, vid, m_App.getAppUserView().getUser().getId(), new Date(), remark};
                    new StaticSentence(m_App.getSession(), "INSERT INTO PURCHASEORDER(ID,PURCHASEORDERNO,VENDOR,CREATEDBY,CRDATE,REMARK) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING}), null).exec(obj);
                    for (PurchaseOrderModel.PurchaseOrderLine p : pomodel.getPurchaseLine()) {
                        if (p.isSelect()) {
                            Object[] obj1 = new Object[]{UUID.randomUUID().toString(), purOrderId, p.getPrdtid(), p.getOrderedqty(), p.getOrderedqty(), p.getRate(), p.getRemark()};
                            Object[] obj2 = new Object[]{p.getQty() - p.getOrderedqty(), purOrderId, p.getPid()};
                            if (p.getPoref() != null) {
                                obj2 = new Object[]{p.getQty() - p.getOrderedqty(), p.getPoref() + "#" + purOrderId, p.getPid()};
                            }
                            insertIntoPurchaseOrderdetail(obj1, obj2);
                        }
                    }
                    Object[] obj4 = new Object[]{paymentmode.getText(), deliveryperiod.getText(), tax.getText(), others.getText()};
                    dlsales.addToPurchaseOrderFooterDetails(obj4);
                    if (!textHeader.equals(headerarea.getText())) {
                        new StaticSentence(m_App.getSession(), "update generaltable set value=? where name=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), null).exec(new Object[]{headerarea.getText().toString(),"poheader"});
                   }
                    return null;
                }
            };
            t.execute();
            print.setVisible(true);
            createorder.setVisible(false);
//            load();
//            String vid = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getId().toString();
//            loadTable(vid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_createorderActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        try {
            print(Integer.parseInt(ponor.getText().toString()));
            load();
            String vid = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getId().toString();
            loadTable(vid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_printActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        try {
            boolean bool = false;
            for (PurchaseOrderModel.PurchaseOrderLine p : pomodel.getPurchaseLine()) {
                if (p.isSelect()) {
                    bool = true;
                }
            }
            if (others.getText().length() > 0) {
                if (Double.parseDouble(oamt.getText().toString()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Other amount should be greater than 0", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (bool) {
                        loadTable2();
                    } else {
                        JOptionPane.showMessageDialog(this, "select atleast one product", "empty order", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                if (bool) {
                    loadTable2();
                } else {
                    JOptionPane.showMessageDialog(this, "select atleast one product", "empty order", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        try {
            String vid = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getId().toString();
            loadTable(vid);
            set();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_backActionPerformed

    private void vendorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_vendorItemStateChanged
        // TODO add your handling code here:
        try {
            String vid = ((PurchaseOrderModel.PendingVendor) vendor.getSelectedItem()).getId().toString();
            loadTable(vid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_vendorItemStateChanged

    private void otherslistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_otherslistItemStateChanged
        // TODO add your handling code here:
        try {
            String name = ((OtherChargesInfo) othersmodel.getSelectedItem()).getName().toString();
            others.setText(name);
            otherslist.setSelectedIndex(0);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_otherslistItemStateChanged

    private void paymentmodelistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paymentmodelistItemStateChanged
        // TODO add your handling code here:
        try {
            String name = ((PaymentModeInfo) paymentsmodel.getSelectedItem()).getName().toString();
            paymentmode.setText(name);
            paymentmodelist.setSelectedIndex(0);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_paymentmodelistItemStateChanged

    private void deliveryperiodlistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_deliveryperiodlistItemStateChanged
        // TODO add your handling code here:
        try {
            String name = ((DeliveryPeriodInfo) deliveryperiodmodel.getSelectedItem()).getName().toString();
            deliveryperiod.setText(name);
            deliveryperiodlist.setSelectedIndex(0);
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }//GEN-LAST:event_deliveryperiodlistItemStateChanged

    private void taxlistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_taxlistItemStateChanged
        // TODO add your handling code here:
        try {
            String name = ((TaxlineInfo) taxmodel.getSelectedItem()).getName().toString();
            tax.setText(name);
            taxlist.setSelectedIndex(0);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_taxlistItemStateChanged

    private void oamtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oamtMouseClicked
        // TODO add your handling code here:
        oamtFlag = true;
    }//GEN-LAST:event_oamtMouseClicked

    private void otherslistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherslistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otherslistActionPerformed

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
    private javax.swing.JButton back;
    private javax.swing.JButton createorder;
    private javax.swing.JTextField date;
    private javax.swing.JLabel deliverylabel;
    private javax.swing.JTextField deliveryperiod;
    private javax.swing.JComboBox deliveryperiodlist;
    private javax.swing.JButton enter;
    private javax.swing.JTextField grandtotal;
    private javax.swing.JLabel header;
    private javax.swing.JTextArea headerarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton next;
    private javax.swing.JTextField oamt;
    private javax.swing.JLabel otherlabel;
    private javax.swing.JTextField others;
    private javax.swing.JComboBox otherslist;
    private javax.swing.JTextField paymentmode;
    private javax.swing.JComboBox paymentmodelist;
    private javax.swing.JLabel paymentslabel;
    private javax.swing.JTextField ponor;
    private javax.swing.JTable potable;
    private javax.swing.JButton print;
    private javax.swing.JTextArea remarkarea;
    private javax.swing.JLabel remarklabel;
    private javax.swing.JTextField tax;
    private javax.swing.JLabel taxlabel;
    private javax.swing.JComboBox taxlist;
    private javax.swing.JComboBox vendor;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Purchase Order";
    }

    public void activate() throws BasicException {
        date.setText(Formats.DATE.formatValue(new Date()));
        grandtotal.setText("0.0");
        oamt.setText("0.0");
        pomodel = PurchaseOrderModel.loadVendors(m_App);
        vendormodel = new ComboBoxValModel(pomodel.getVendorList());
        vendor.setModel(vendormodel);
        load();
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

    private void load() throws BasicException {
        date.setEditable(false);
        ponor.setEditable(false);
        grandtotal.setEditable(false);
        oamt.setEditable(false);
        List l1 = dlsales.getPaymentsList().list();
        l1.add(0, null);
        paymentsmodel = new ComboBoxValModel(l1);
        paymentmodelist.setModel(paymentsmodel);
        List l2 = dlsales.getDeliveryPeriodList().list();
        l2.add(0, null);
        deliveryperiodmodel = new ComboBoxValModel(l2);
        deliveryperiodlist.setModel(deliveryperiodmodel);
        List l3 = dlsales.getTaxlineList().list();
        l3.add(0, null);
        taxmodel = new ComboBoxValModel(l3);
        taxlist.setModel(taxmodel);
        List l4 = dlsales.getOtherChargesList().list();
        l4.add(0, null);
        othersmodel = new ComboBoxValModel(l4);
        otherslist.setModel(othersmodel);
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        GeneralSettingInfo purHeader = gs.get("poheader");
        textHeader = purHeader.getValue().toString();
//        String[] text1 = textHeader.split(",");
//        String headerString = text1[0] + " \r\n " + text1[1];
        headerarea.setText(textHeader);
        set();
    }

    private void set1() {
        grandtotal.setText("0.0");
        oamt.setText("0.0");
        vendor.setEnabled(true);
        header.setVisible(false);
        headerarea.setVisible(false);
        jNumberKeys1.setVisible(true);
        jTextField1.setVisible(true);
        enter.setVisible(true);
        paymentslabel.setVisible(false);
        paymentmode.setVisible(false);
        paymentmodelist.setVisible(false);
        deliverylabel.setVisible(false);
        deliveryperiod.setVisible(false);
        deliveryperiodlist.setVisible(false);
        taxlabel.setVisible(false);
        tax.setVisible(false);
        taxlist.setVisible(false);
        remarklabel.setVisible(false);
        remarkarea.setVisible(false);
        print.setVisible(false);
        createorder.setVisible(false);
        back.setVisible(false);
        next.setVisible(true);
    }

    private void set(){
        grandtotal.setText("0.0");
        oamt.setText("0.0");
        otherslist.setVisible(true);
        others.setEnabled(true);
        others.setText(null);
        paymentmode.setText(null);
        deliveryperiod.setText(null);
        tax.setText(null);
        jPanel1.setVisible(false);
        jPanel8.setVisible(true);
        jPanel5.setVisible(false);
        jPanel4.setVisible(false);
        print.setVisible(false);
        createorder.setVisible(false);
        back.setVisible(false);
        next.setVisible(true);
    }

    private void reset(){
        otherslist.setVisible(false);
        others.setEnabled(false);
        jPanel1.setVisible(true);
        jPanel8.setVisible(false);
        jPanel5.setVisible(true);
        jPanel4.setVisible(true);
        createorder.setVisible(true);
        back.setVisible(true);
        next.setVisible(false);
    }

    private void reset1() {
        vendor.setEnabled(false);
        header.setVisible(true);
        headerarea.setVisible(true);
        jNumberKeys1.setVisible(false);
        jTextField1.setVisible(false);
        enter.setVisible(false);
        paymentslabel.setVisible(true);
        paymentmode.setVisible(true);
        paymentmodelist.setVisible(true);
        deliverylabel.setVisible(true);
        deliveryperiod.setVisible(true);
        deliveryperiodlist.setVisible(true);
        taxlabel.setVisible(true);
        tax.setVisible(true);
        taxlist.setVisible(true);
        remarklabel.setVisible(true);
        remarkarea.setVisible(true);
        //print.setVisible(true);
        createorder.setVisible(true);
        back.setVisible(true);
        next.setVisible(false);
    }

    private void insertIntoPurchaseOrderdetail(Object[] val, Object[] val1) throws BasicException {
        new StaticSentence(m_App.getSession(), "INSERT INTO PURCHASEORDERDETAIL(ID,PURCHASEORDERID,PRODUCTID,ORDEREDQTY,BALANCEQTY,RATE,REMARK) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING})).exec(val);
        new StaticSentence(m_App.getSession(), "UPDATE PURCHASEINDENTDETAILS SET BALANCEINDQTY=?,PURCHASEORDERREF=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING, Datas.STRING}), null).exec(val1);
    }
}
