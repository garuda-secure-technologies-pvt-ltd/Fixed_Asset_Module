
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;

import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sms.EmailMaster;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import net.sf.jasperreports.engine.JasperPrint;
import com.openbravo.pos.inventory.ProductsApprovalTableModel;
import com.openbravo.pos.inventory.ProductsApprovalTableModel.PrdApvInfo;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.openbravo.format.Formats;
import java.text.DateFormat ;
import java.util.UUID;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import java.awt.Color;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.*;

import java.io.*;
import java.util.Iterator;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;


public class ProductsApproval extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private ProductsApprovalTableModel ProductsApproval_Table_Model;
  
   private DataLogicSales m_dlSales = null;
    
    protected Datas[] productcatDatas;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    private Date dateTo;
    private Date dateFrom;
    private String strdateTo;
    private String strdateFrom;
    private ProductInfoExt nprodInfoExt;
    private ProductInfoExt oprodInfoExt;
    private int allReqFlag;
    private int pendingReqFlag=1;
    private int dateWiseReqFlag;
    private Date dateToOfFlag;
    private Date dateFromOfFlag;
    public ProductsApproval() {
        
     
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        main_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        selectButt = new javax.swing.JButton();
        AllReqButton = new javax.swing.JButton();
        pendingReqButton = new javax.swing.JButton();
        DatePanel = new javax.swing.JPanel();
        fromButton = new javax.swing.JButton();
        fromText = new javax.swing.JTextField();
        toText = new javax.swing.JTextField();
        toButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Diff_DetailPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        OLD = new javax.swing.JLabel();
        NEW = new javax.swing.JLabel();
        oBuyPriceText = new javax.swing.JTextField();
        nBuyPriceText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        oSellPriceText = new javax.swing.JTextField();
        nSellPriceText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        oRefText = new javax.swing.JTextField();
        nRefText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        oName = new javax.swing.JTextField();
        nName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        oCategory = new javax.swing.JTextField();
        nCategory = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        oPrintCatText = new javax.swing.JTextField();
        nPrintCatText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        oTaxCatText = new javax.swing.JTextField();
        nTaxCatText = new javax.swing.JTextField();
        oTax2Text = new javax.swing.JTextField();
        nTax2Text = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        oTax3Text = new javax.swing.JTextField();
        nTax3Text = new javax.swing.JTextField();
        oBasTax2Radio = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        oCasTax2Radio = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        nBasTax2Radio = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        nCasTax2Radio = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        oBarCode = new javax.swing.JTextField();
        nBarCode = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        oBasTax3Radio = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        oCasTax3Radio = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        nBasTax3Radio = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        nCasTax3Radio = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        oLocText = new javax.swing.JTextField();
        nLocText = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        oStockCost = new javax.swing.JTextField();
        nStockCost = new javax.swing.JTextField();
        oMinStockLevel = new javax.swing.JTextField();
        nMinStockLevel = new javax.swing.JTextField();
        oMaxStockLevel = new javax.swing.JTextField();
        nMaxStockLevel = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        oUnitText = new javax.swing.JTextField();
        nUnitText = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        oPAccountText = new javax.swing.JTextField();
        nPAccountText = new javax.swing.JTextField();
        nSAccountText = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        nReorderQtyText = new javax.swing.JTextField();
        nReorderLevelText = new javax.swing.JTextField();
        oInvMainCheckBox = new javax.swing.JCheckBox();
        nInvMainCheckBox = new javax.swing.JCheckBox();
        oAuxCheckBox = new javax.swing.JCheckBox();
        nAuxCheckBox = new javax.swing.JCheckBox();
        oScaleCheckBox = new javax.swing.JCheckBox();
        nScaleCheckBox = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jApproveBut = new javax.swing.JButton();
        jRejectBut = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        appByLabel = new javax.swing.JLabel();
        appHostLabel = new javax.swing.JLabel();
        appDateLabel = new javax.swing.JLabel();
        oReorderLevelText = new javax.swing.JTextField();
        oReorderQtyText = new javax.swing.JTextField();
        oSAccountText = new javax.swing.JTextField();
        actionLabel = new javax.swing.JLabel();
        actionLabel1 = new javax.swing.JLabel();
        actionByLabel = new javax.swing.JLabel();
        actionHostLabel = new javax.swing.JLabel();
        actionDateLabel = new javax.swing.JLabel();
        ophotoLabel = new javax.swing.JLabel();
        nphotoLabel = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        backButton = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(469, 419));

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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setMaximumSize(new java.awt.Dimension(3647, 200));
        jTable1.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable1.setPreferredSize(new java.awt.Dimension(200, 600));
        jScrollPane1.setViewportView(jTable1);

        selectButt.setText("Select");
        selectButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtActionPerformed(evt);
            }
        });

        AllReqButton.setText("All Requests");
        AllReqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllReqButtonActionPerformed(evt);
            }
        });

        pendingReqButton.setText("Pending Requests");
        pendingReqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingReqButtonActionPerformed(evt);
            }
        });

        fromButton.setText(" From Date");
        fromButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromButtonActionPerformed(evt);
            }
        });

        toButton.setText("To Date");
        toButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toButtonActionPerformed(evt);
            }
        });

        okButton.setText("View");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DatePanelLayout = new javax.swing.GroupLayout(DatePanel);
        DatePanel.setLayout(DatePanelLayout);
        DatePanelLayout.setHorizontalGroup(
            DatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DatePanelLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(DatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fromButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DatePanelLayout.createSequentialGroup()
                        .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(okButton))
                    .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        DatePanelLayout.setVerticalGroup(
            DatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromButton)
                    .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toButton)
                    .addComponent(okButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButton1.setText("Requests By Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addComponent(AllReqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(pendingReqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(242, 242, 242)
                                .addComponent(selectButt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AllReqButton)
                    .addComponent(pendingReqButton)
                    .addComponent(selectButt)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(DatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        statusLabel.setText("statusLabel");

        OLD.setText("OLD");

        NEW.setText("NEW");

        jLabel1.setText("Buy Price");

        jLabel2.setText("Sell Price");

        jLabel3.setText("Reference");

        jLabel4.setText("Name");

        jLabel5.setText("Category");

        jLabel6.setText("Print Category");

        jLabel7.setText("Tax Category");

        jLabel8.setText("Tax Category2");

        jLabel9.setText("Tax Category3");

        oBasTax2Radio.setText("jRadioButton2");

        jLabel10.setText("Baisc");

        oCasTax2Radio.setText("jRadioButton1");

        jLabel11.setText("Cascade");

        nBasTax2Radio.setText("jRadioButton4");

        jLabel12.setText("Basic");

        nCasTax2Radio.setText("jRadioButton5");

        jLabel13.setText("Cascade");

        jLabel14.setText("BarCode");

        jLabel15.setText("WareHouse");

        oBasTax3Radio.setText("jRadioButton3");

        jLabel16.setText("Basic");

        oCasTax3Radio.setText("jRadioButton1");

        jLabel17.setText("Cascade");

        nBasTax3Radio.setText("jRadioButton2");

        jLabel18.setText("Basic");

        nCasTax3Radio.setText("jRadioButton3");

        jLabel19.setText("Cascade");

        jLabel20.setText("HSN/SAC Code");

        jLabel21.setText("Min Stock Level");

        jLabel22.setText("Max Stock Level");

        jLabel25.setText("Unit Type");

        jLabel26.setText("Purchase Account");

        jLabel27.setText("Sale Account");

        jLabel28.setText("Reorder Quantity ");

        jLabel29.setText("Reorder Level");

        jLabel23.setText("Inventory Maintain");

        jLabel24.setText("Auxiliary");

        jLabel30.setText("Scale");

        jLabel31.setText("jLabel31");

        jApproveBut.setText("Approve");
        jApproveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jApproveButActionPerformed(evt);
            }
        });

        jRejectBut.setText("Reject");
        jRejectBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRejectButActionPerformed(evt);
            }
        });

        jLabel32.setText("Status:");

        appByLabel.setText(" By:");

        appHostLabel.setText(" Host:");

        appDateLabel.setText(" Date:");

        actionLabel.setText("Action Taken:");

        actionLabel1.setText("actionLabel1");

        actionByLabel.setText("actionByLabel");

        actionHostLabel.setText("actionHostLabel");

        actionDateLabel.setText("actionDateLabel");

        photo.setText("Photo");

        jLabel33.setText("In Catalog");

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Diff_DetailPanelLayout = new javax.swing.GroupLayout(Diff_DetailPanel);
        Diff_DetailPanel.setLayout(Diff_DetailPanelLayout);
        Diff_DetailPanelLayout.setHorizontalGroup(
            Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Diff_DetailPanelLayout.createSequentialGroup()
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20)
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(oSAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(oReorderLevelText)
                                                .addComponent(oTax2Text)
                                                .addComponent(oSellPriceText)
                                                .addComponent(oName)
                                                .addComponent(oRefText)
                                                .addComponent(oBuyPriceText)
                                                .addComponent(oCategory)
                                                .addComponent(oPrintCatText)
                                                .addComponent(oTaxCatText)
                                                .addComponent(oTax3Text)
                                                .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                                    .addComponent(oBasTax2Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel10)
                                                    .addGap(27, 27, 27)
                                                    .addComponent(oCasTax2Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel11))
                                                .addComponent(oBarCode)
                                                .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                                    .addComponent(oBasTax3Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel16)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(oCasTax3Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel17))
                                                .addComponent(oLocText)
                                                .addComponent(oStockCost)
                                                .addComponent(oMinStockLevel)
                                                .addComponent(oMaxStockLevel)
                                                .addComponent(oUnitText)
                                                .addComponent(oPAccountText)
                                                .addComponent(oReorderQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(appByLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(appHostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(appDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(actionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(actionByLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(actionHostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(actionDateLabel))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Diff_DetailPanelLayout.createSequentialGroup()
                                        .addComponent(nBasTax2Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)
                                        .addGap(26, 26, 26)
                                        .addComponent(nCasTax2Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Diff_DetailPanelLayout.createSequentialGroup()
                                        .addComponent(nBasTax3Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(nCasTax3Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19))
                                    .addComponent(nTax2Text, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nTax3Text, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nTaxCatText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nPrintCatText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nCategory, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nSellPriceText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nBuyPriceText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nBarCode, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nRefText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nLocText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nStockCost, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nMinStockLevel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nMaxStockLevel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nUnitText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nPAccountText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nSAccountText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nReorderQtyText, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nReorderLevelText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(148, 148, 148)
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(oAuxCheckBox)
                                            .addComponent(oInvMainCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(oScaleCheckBox)
                                            .addComponent(ophotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox1))
                                        .addGap(134, 134, 134)
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nAuxCheckBox)
                                            .addComponent(nInvMainCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nScaleCheckBox)
                                            .addComponent(jCheckBox2)
                                            .addComponent(nphotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                                .addGap(279, 279, 279)
                                                .addComponent(OLD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                                .addGap(223, 223, 223)
                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Diff_DetailPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(NEW, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(41, 41, 41)))))
                                .addGap(0, 1, Short.MAX_VALUE))))
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jApproveBut, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRejectBut, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        Diff_DetailPanelLayout.setVerticalGroup(
            Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(31, 31, 31)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OLD)
                    .addComponent(NEW))
                .addGap(13, 13, 13)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oRefText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(nRefText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(oBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(oName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(oBuyPriceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nBuyPriceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(oSellPriceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSellPriceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(oCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(oPrintCatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nPrintCatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(oTaxCatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nTaxCatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oTax2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nTax2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oBasTax2Radio)
                    .addComponent(jLabel10)
                    .addComponent(oCasTax2Radio)
                    .addComponent(jLabel11)
                    .addComponent(nBasTax2Radio)
                    .addComponent(jLabel12)
                    .addComponent(nCasTax2Radio)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oTax3Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(nTax3Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oBasTax3Radio)
                    .addComponent(jLabel16)
                    .addComponent(oCasTax3Radio)
                    .addComponent(jLabel17)
                    .addComponent(nBasTax3Radio)
                    .addComponent(jLabel18)
                    .addComponent(nCasTax3Radio)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(oLocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nLocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oStockCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nStockCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(oMinStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(oMaxStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addComponent(nMinStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nMaxStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(oUnitText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nUnitText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(oPAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nPAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nSAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(oSAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nReorderQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oReorderQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nReorderLevelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oReorderLevelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nInvMainCheckBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oInvMainCheckBox)
                        .addComponent(jLabel23)))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nAuxCheckBox)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oAuxCheckBox)
                        .addComponent(jLabel24)))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nScaleCheckBox)
                    .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oScaleCheckBox)
                        .addComponent(jLabel30)))
                .addGap(18, 18, 18)
                .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jCheckBox2))
                        .addGap(42, 42, 42)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nphotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ophotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                            .addComponent(photo))
                        .addGap(59, 59, 59)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(actionLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(appByLabel)
                            .addComponent(actionByLabel))
                        .addGap(18, 18, 18)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(appHostLabel)
                            .addComponent(actionHostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(appDateLabel)
                            .addComponent(actionDateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(Diff_DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRejectBut)
                            .addComponent(jApproveBut))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(Diff_DetailPanelLayout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addContainerGap())))
        );

        jScrollPane2.setViewportView(Diff_DetailPanel);

        backButton.setText("Go Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton)
                .addGap(1544, 1544, 1544))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jApproveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jApproveButActionPerformed
        // TODO add your handling code here:
        if(jTable1.getSelectedRow()!=-1){
           
         int bill = JOptionPane.showConfirmDialog(this, " Do you want to Approve this request ?? ", "Editing Menu", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

            try{  
                Transaction t = new Transaction(m_App.getSession()) {
                    @Override
                    protected Object transact() throws BasicException { 
                        int i=0; 
                        int row1 = jTable1.getSelectedRow();
                         PrdApvInfo showdata=ProductsApproval_Table_Model.getList().get(row1);
                         if(showdata.getCR_STATE()!=null){
                             if(showdata.getCR_STATE().equals("1")){
                                JOptionPane.showMessageDialog(null, "Already Approved this request", "Success", JOptionPane.INFORMATION_MESSAGE);
                             }else  JOptionPane.showMessageDialog(null, "Already Rejected this request", "Success", JOptionPane.INFORMATION_MESSAGE);
                         }else{
                         System.out.println("showdataid"+showdata.getID());
                         
                      if(showdata.getREQUESTTYPE().equals("new")) {
                        i = new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS ( ID, REFERENCE , NAME , PRICEBUY, PRICESELL , CATEGORY,PRCATEGORY , TAXCAT ,ISCOM , ISSCALE  ,LOCATION,INVENTRYMAINTAIN,BASIC2,BASIC3,TAX3CASCADE,Catalog_flag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
     new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING,Datas.DOUBLE, Datas.DOUBLE,Datas.STRING, Datas.STRING,Datas.STRING,Datas.INT,Datas.INT,Datas.STRING,Datas.INT,Datas.INT,Datas.INT,Datas.INT,Datas.INT })).exec(new Object[]{showdata.getPR_ID(),showdata.getREFERENCE(), showdata.getNAME(),showdata.getPRICEBUY(),showdata.getPRICESELL(),showdata.getCATEGORY(),showdata.getPRINTCAT(), showdata.getTAXCAT(),showdata.getISCOM(),showdata.getISSCALE(),showdata.getWAREHOUSE(),showdata.getINV_MAINTAIN(),showdata.getBASIC2(),showdata.getBASIC3(),showdata.getTAX3CASCADE(),showdata.getCatalog_flag()});
                          ////Added by guru
                          if (i > 0 && showdata.getCatalog_flag()==1) {
                              String orderlevel;
                              
                         if(showdata.getREORDERLEVEL()!=null){ 
                             orderlevel=showdata.getREORDERLEVEL();
                    int y= new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})).exec(new Object[]{showdata.getPR_ID(),Integer.parseInt(orderlevel)});
                         }
                         else
                             new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT) VALUES (?)", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{showdata.getPR_ID()});
                         } 
//                          else {
//                    return i;
//                }
                          ///Endded by guru
                      }
                      if(showdata.getREQUESTTYPE().equals("edit")) {
                          
                      i = new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET  REFERENCE = ?, NAME = ?, PRICEBUY = ?, PRICESELL = ? , CATEGORY = ? ,PRCATEGORY =?, TAXCAT = ?,ISCOM = ?, ISSCALE = ? ,LOCATION=?,INVENTRYMAINTAIN=?,BASIC2=?,BASIC3=?,TAX3CASCADE=?,Catalog_flag=?            WHERE ID = ?", 
     new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.DOUBLE, Datas.DOUBLE,Datas.STRING, Datas.STRING,Datas.STRING,Datas.INT,Datas.INT,Datas.STRING,Datas.INT,Datas.INT,Datas.INT,Datas.INT,Datas.INT,Datas.STRING })).exec(new Object[]{showdata.getREFERENCE(), showdata.getNAME(),showdata.getPRICEBUY(),showdata.getPRICESELL(),showdata.getCATEGORY(),showdata.getPRINTCAT(), showdata.getTAXCAT(),showdata.getISCOM(),showdata.getISSCALE(),showdata.getWAREHOUSE(),showdata.getINV_MAINTAIN(),showdata.getBASIC2(),showdata.getBASIC3(),showdata.getTAX3CASCADE(),showdata.getCatalog_flag(),showdata.getPR_ID()});
                      //Added by guru
                      
                      if (showdata.getCatalog_flag()==1) {
                        //  System.out.println("Catalog_flagAPP::::"+showdata.getCatalog_flag());
                          String orderlevel;
                          if(showdata.getREORDERLEVEL()!=null){
                          orderlevel=showdata.getREORDERLEVEL();
//orderlevel=null;
                          if (new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Integer.parseInt(orderlevel),showdata.getPR_ID()}) == 0) {
                            new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})).exec(new Object[]{showdata.getPR_ID(),Integer.parseInt(orderlevel)});
                        }
//                          if (new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{orderlevel,showdata.getPR_ID()}) == 0) {
//                            new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getPR_ID(),orderlevel});
//                        }
                          
                          }
                          else{
//                                orderlevel="0";
                              orderlevel=null;
//                            orderlevel="null";
                             // System.out.println("Catalog_f222fftlagAPP::::"+showdata.getCatalog_flag());
                          //System.out.println("edit part new to product_cat::::");
                        if (new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{orderlevel,showdata.getPR_ID()}) == 0) {
                            new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT) VALUES (?)", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{showdata.getPR_ID()});
                        }
//                         if (new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_CAT  WHERE PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{showdata.getPR_ID()}) == 0) {
//                            new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT) VALUES (?)", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{showdata.getPR_ID()});
//                        }
                          }
                    } else {
                         // System.out.println("delete new to product_cat::::");
                          //System.out.println("Catalog_f223332fftlagAPP::::"+showdata.getCatalog_flag());
                        new PreparedSentence(m_App.getSession(), "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{showdata.getPR_ID()});
                      }
                        //Ended by guru
                      
                      }
                      
                       if(showdata.getMAXSTOCK()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET MAXSTOCKLEVEL=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Integer.parseInt(showdata.getMAXSTOCK()),showdata.getPR_ID()});}
                     if(showdata.getMINSTOCK()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET MINSTOCKLEVEL=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Integer.parseInt(showdata.getMINSTOCK()),showdata.getPR_ID()});}
                     if(showdata.getREORDERLEVEL()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET REORDERLEVEL=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Integer.parseInt(showdata.getREORDERLEVEL()),showdata.getPR_ID()});}
                     if(showdata.getREORDERQTY()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET REORDERQUANTITY=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Integer.parseInt(showdata.getREORDERQTY()),showdata.getPR_ID()});}
                     
                     if(showdata.getCODETYPE()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET CODETYPE=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getCODETYPE(),showdata.getPR_ID()});}
                     if(showdata.getBARCODE()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET CODE=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getBARCODE(),showdata.getPR_ID()});}
//                     if(showdata.getSTOCKCOST()!=null)               
//                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET STOCKCOST=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{showdata.getHSN_Code(),showdata.getPR_ID()});}
                    if(showdata.getHSN_Code()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET HSN_Code=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getHSN_Code(),showdata.getPR_ID()});} 
                    if(showdata.getSTOCKVOL()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET STOCKVOLUME=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{showdata.getSTOCKVOL(),showdata.getPR_ID()});}
                      if(showdata.getUNITTYPE()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET UNITTYPE=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getUNITTYPE(),showdata.getPR_ID()});}
                     if(showdata.getPACCOUNT()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET PACCOUNT=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getPACCOUNT(),showdata.getPR_ID()});}
                     if(showdata.getSACCOUNT()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET SACCOUNT=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getSACCOUNT(),showdata.getPR_ID()});}
                  if(showdata.getTAXCAT2()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET TAXCAT2=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getTAXCAT2(),showdata.getPR_ID()});}
                  if(showdata.getTAXCAT3()!=null)               
                     { new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET TAXCAT3=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getTAXCAT3(),showdata.getPR_ID()});}
                  
                  if(showdata.getREQUESTTYPE()=="new") {
                      int x = new PreparedSentence(m_App.getSession(), "INSERT INTO PDT_PRCAT (ID,PRCAT,CATEGORY) VALUES (?,?,?)",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getID(),showdata.getPRINTCAT(),showdata.getCATEGORY()});
               //unable to perform below queries bcz data values[13] and values[14] are commented in inserting code of products table
//                      if (i > 0 && ((Boolean) values[13]).booleanValue()) {
//                    return new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 14})).exec(params);
//                } else {
//                    return i;
//                }
                  }
                  if(showdata.getREQUESTTYPE()=="edit") {  
                  if (i > 0) {
                    if (new PreparedSentence(m_App.getSession(), "UPDATE PDT_PRCAT SET CATEGORY = ? WHERE ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getCATEGORY(),showdata.getID()}) == 0) {
                        new PreparedSentence(m_App.getSession(), "INSERT INTO PDT_PRCAT (ID,PRCAT, CATEGORY) VALUES (?, ?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{showdata.getID(),showdata.getPRINTCAT(),showdata.getCATEGORY()});
                   
                
                    }
                   
                    //unable to perform below queries bcz data values[13] and values[14] are commented in inserting code of products table
//                    if (((Boolean) values[13]).booleanValue()) {
//
//                        if (new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?", new SerializerWriteBasic(productcatDatas, new int[]{14, 0})).exec(params) == 0) {
//                            new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 14})).exec(params);
//                        }
//                    } else {
//                        new PreparedSentence(m_App.getSession(), "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{0})).exec(params);
//                    /*   new PreparedSentence(s
//                    , "DELETE FROM PDT_PRCAT WHERE ID = ?"
//                    , new SerializerWriteBasicExt(productcatDatas, new int[] {0})).exec(params);*/
//                    }
                  }
                    
                }
                  if (i > 0) {
                      Object[] param1 = new Object[]{"1",LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(),LookupUtilityImpl.getInstance(null).getAppView().getProperties().getHost(),new Date(),showdata.getID()};
                        new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_APPROVAL set cr_state=?,disp_by=?,disp_host=?,disp_date=? where ID=?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(param1);
                   }
                         }  
                  return null;  
                    }
                
                };
                    t.execute();
                }catch(BasicException e){ 
                System.out.println(e);
                e.printStackTrace();
                }
            try{  
                Transaction t = new Transaction(m_App.getSession()) {
                    @Override
                    protected Object transact() throws BasicException { 
                         int row1 = jTable1.getSelectedRow();
                         PrdApvInfo showdata=ProductsApproval_Table_Model.getList().get(row1);
                          Object[] Obj= (Object[]) new StaticSentence(m_App.getSession(), "SELECT IMAGE,ATTRIBUTES FROM PRODUCTS_APPROVAL WHERE ID=?",new SerializerWriteBasic(new Datas[]{Datas.STRING}),new SerializerReadBasic(new Datas[]{Datas.IMAGE,Datas.BYTES})).find(new Object[]{showdata.getID()});
            if(Obj[0]!=null){        new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET IMAGE=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.IMAGE,Datas.STRING})).exec(new Object[]{Obj[0],showdata.getPR_ID()});
            } 
           if(Obj[1]!=null){ new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET ATTRIBUTES=? WHERE ID = ?",new SerializerWriteBasic(new Datas[]{Datas.BYTES,Datas.STRING})).exec(new Object[]{Obj[1],showdata.getPR_ID()});
           }
                    return null;
                    
                    }};
                    t.execute();
                }catch(BasicException e){ 
                System.out.println(e);
                e.printStackTrace();
                }
             try{
              JOptionPane.showMessageDialog(null, "Approved Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

           
                 if( allReqFlag==1){
                  ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductApprovalData(m_App);
                  jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
               main_panel.setVisible(true);
              Diff_DetailPanel.setVisible(false);
            fromText.setText(null);
            toText.setText(null);
                 }
                 if(  pendingReqFlag==1){
                  loaddata();
                 }
                if( dateWiseReqFlag==1){
                     
                 ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductRequestDataByDate(m_App,dateFromOfFlag,dateToOfFlag);
                  jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
          main_panel.setVisible(true);
           Diff_DetailPanel.setVisible(true);
          
                }
               
          }catch(BasicException e){
           e.printStackTrace();}
        }//yes option checkended
        }
         
        
    }//GEN-LAST:event_jApproveButActionPerformed

    private void jRejectButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRejectButActionPerformed
        // TODO add your handling code here:
         if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(this, " Do you want to Reject this request ?? ", "Editing Menu", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

            try{  
                Transaction t = new Transaction(m_App.getSession()) {
                    @Override
                    protected Object transact() throws BasicException { 
         
                         int row1 = jTable1.getSelectedRow();
            PrdApvInfo showdata=ProductsApproval_Table_Model.getList().get(row1);
         Object[] param1 = new Object[]{"0",LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(),LookupUtilityImpl.getInstance(null).getAppView().getProperties().getHost(),new Date(),showdata.getID()};
                      
                        new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS_APPROVAL set cr_state=?,disp_by=?,disp_host=?,disp_date=? where ID=?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(param1);
                  return null;  
                    }
                  
                };
                    t.execute();
                }catch(BasicException e){ 
                System.out.println(e);
                e.printStackTrace();
                }
            }
         }
         try{
             if( allReqFlag==1){
                  ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductApprovalData(m_App);
                  jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
          main_panel.setVisible(true);
           Diff_DetailPanel.setVisible(false);
           fromText.setText(null);
           toText.setText(null);
                 }
                 if(  pendingReqFlag==1){
                  loaddata();
                 }
                if( dateWiseReqFlag==1){
                     
                 ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductRequestDataByDate(m_App,dateFromOfFlag,dateToOfFlag);
                  jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
          main_panel.setVisible(true);
           Diff_DetailPanel.setVisible(true);
          
                }

         }catch(BasicException e){}
    }//GEN-LAST:event_jRejectButActionPerformed

    private void selectButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtActionPerformed
        // TODO add your handling code here:
        try{
        resetOldTextFields();
        resetNewTextFields();
        setAllTextUneditable();
        }catch(BasicException ex){}
          if(jTable1.getSelectedRow()!=-1){
             try{  
                Transaction t = new Transaction(m_App.getSession()) {
                    @Override
                    protected Object transact() throws BasicException { 
           int row1 = jTable1.getSelectedRow();
            PrdApvInfo showdata=ProductsApproval_Table_Model.getList().get(row1);
            
            
            if(showdata.getREQUESTTYPE().equals("new")){
                jLabel31.setText("Create New Product");
                jLabel31.setVisible(true);
                resetOldTextFields();
            }
             if(showdata.getREQUESTTYPE().equals("edit")){
                 jLabel31.setVisible(false);
            }
             
            if(showdata.getCR_STATE()!=null){
                jApproveBut.setVisible(false);
                jRejectBut.setVisible(false);
                if(showdata.getCR_STATE().equals("1"))
                { 
                    statusLabel.setText("Approved");
                    actionLabel1.setText("Approved");
                    statusLabel.setForeground(new Color(50, 200, 50));
                }
                else{
                    statusLabel.setText("Rejected");
                     actionLabel1.setText("Rejected");
                     statusLabel.setForeground(new Color(255, 50, 80));
                }
            
                Object[] param2=(Object[]) new StaticSentence(m_App.getSession(), "Select disp_by,disp_host,disp_date  from products_approval where id=?   ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).find(new Object[]{showdata.getID()});
            if(param2[0]!=null){ 
               actionByLabel.setText(param2[0].toString());
           }
           if(param2[1]!=null){ 
                actionHostLabel.setText(param2[1].toString());
           }
           if(param2[2]!=null){ 
               actionDateLabel.setText(param2[2].toString());
           }
           actionLabel.setVisible(true);
           actionLabel1.setVisible(true);
           actionByLabel.setVisible(true);
               actionHostLabel.setVisible(true);
                actionDateLabel.setVisible(true);

                appByLabel.setVisible(true);
                appHostLabel.setVisible(true);
                appDateLabel.setVisible(true);
            }
            else {
                statusLabel.setText("Pending");
                jApproveBut.setVisible(true);
                jRejectBut.setVisible(true);
               statusLabel.setForeground(new Color(255, 200, 0));
               
                
            }
            
            String  ncategory=( String )  new StaticSentence(m_App.getSession(), "Select name from categories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getCATEGORY());
              String  nprcategory=( String )  new StaticSentence(m_App.getSession(), "Select name from prcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getPRINTCAT());
             String  ntaxcategory=( String )  new StaticSentence(m_App.getSession(), "Select name from taxcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getTAXCAT());
         String nunit=( String)new StaticSentence(m_App.getSession(), "Select name from unit where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getUNITTYPE());
             String nlocation=( String)new StaticSentence(m_App.getSession(), "Select name from locations where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getWAREHOUSE());
              //inventry
              String nTax2=null,nTax3=null,nSAccount=null,nPAccount=null;
             if(showdata.getTAXCAT2()!=null){nTax2=( String)new StaticSentence(m_App.getSession(), "Select name from taxcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getTAXCAT2());}
               if(showdata.getTAXCAT3()!=null){nTax3=( String)new StaticSentence(m_App.getSession(), "Select name from taxcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getTAXCAT3());}
              //tax3cascade
              //codetype
            if(showdata.getSACCOUNT()!=null){nSAccount=( String)new StaticSentence(m_App.getSession(), "Select name from accountmaster  where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getSACCOUNT());}
              
            if(showdata.getPACCOUNT()!=null){nPAccount=( String)new StaticSentence(m_App.getSession(), "Select name from accountmaster  where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(showdata.getPACCOUNT());}
              
                   
                    nRefText.setText(showdata.getREFERENCE());
                   
                    nBarCode.setText(showdata.getBARCODE());
                  
                    nName.setText(showdata.getNAME());
                    
                    nBuyPriceText.setText(showdata.getPRICEBUY().toString());
                   
                    nSellPriceText.setText(showdata.getPRICESELL().toString());
                   
                    nCategory.setText(ncategory);
                   
                    nPrintCatText.setText(nprcategory);
                   
                    nTaxCatText.setText(ntaxcategory);
                    
                     if(showdata.getISCOM()==1) { nAuxCheckBox.setSelected(true);}
                   else  nAuxCheckBox.setSelected(false);
                   
                   if(showdata.getISSCALE()==1) {nScaleCheckBox.setSelected(true);}
                   else  nScaleCheckBox.setSelected(false);
                   
                   if(showdata.getCatalog_flag()==1) {jCheckBox2.setSelected(true);}
                   else  jCheckBox2.setSelected(false);
                   
                  
                    nUnitText.setText(nunit);
                    
                   
                    nLocText.setText(nlocation);
                   
                   if(showdata.getINV_MAINTAIN()==1) {nInvMainCheckBox.setSelected(true);}
                   else nInvMainCheckBox.setSelected(false);
                  
                   
                    if(nTax2!=null){nTax2Text.setText(nTax2);}
                    
                    if(nTax3!=null){nTax3Text.setText(nTax3);}
                  
                    if(showdata.getBASIC2()==1){ nBasTax2Radio.setSelected(true);}
                   else nCasTax2Radio.setSelected(true);
                   if(showdata.getBASIC3()==1){ nBasTax3Radio.setSelected(true);}
                   else nCasTax3Radio.setSelected(true);
                    //tax3cascade
                    //codetype
//                   if(showdata.getSTOCKCOST()!=null) { nStockCost.setText(showdata.getSTOCKCOST().toString());}
                    if(showdata.getHSN_Code()!=null) { nStockCost.setText(showdata.getHSN_Code());}
                   //stockvolume
                  if(nSAccount!=null) { nSAccountText.setText(nSAccount);}
                    if(nPAccount!=null) { nPAccountText.setText(nPAccount);}
                   if(showdata.getMAXSTOCK()!=null){ nMaxStockLevel.setText(showdata.getMAXSTOCK());}
                     if(showdata.getMINSTOCK()!=null){ nMinStockLevel.setText(showdata.getMINSTOCK());}
                    if(showdata.getREORDERQTY()!=null){ nReorderQtyText.setText(showdata.getREORDERQTY());}
                    if(showdata.getREORDERLEVEL()!=null){nReorderLevelText.setText(showdata.getREORDERLEVEL());}
                   nprodInfoExt=m_dlSales.getEditProductInfo(showdata.getID());
                   if(nprodInfoExt!=null){
                   if(nprodInfoExt.getImage()!=null){
                     nphotoLabel.setIcon(nResizeImage(nprodInfoExt.getImage()));
                   }}
                   if(showdata.getREQUESTTYPE().equals("edit")){
                         
                      Object[] param1 =( Object[])  new StaticSentence(m_App.getSession(), "Select reference,code,name,pricebuy,pricesell,category,prcategory,taxcat,iscom,isscale,unittype,location,inventrymaintain,taxcat2,taxcat3,basic2,basic3,tax3cascade,codetype,hsn_code,stockvolume,paccount,saccount,maxstocklevel,minstocklevel,reorderquantity,reorderlevel,catalog_flag from products where id=?",new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,
                      new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.INT,Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING,Datas.STRING,Datas.INT,Datas.INT,Datas.INT,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.INT,Datas.INT,Datas.INT,Datas.INT,Datas.INT})).find(new Object[]{showdata.getPR_ID()});
              
               String  ocategory=( String )  new StaticSentence(m_App.getSession(), "Select name from categories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[5].toString());
               String  oprcategory=( String )  new StaticSentence(m_App.getSession(), "Select name from prcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[6].toString());
               String  otaxcategory=( String )  new StaticSentence(m_App.getSession(), "Select name from taxcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[7].toString());
              String ounit=( String)new StaticSentence(m_App.getSession(), "Select name from unit where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[10].toString());
             String olocation=( String)new StaticSentence(m_App.getSession(), "Select name from locations where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[11].toString());
              //inventry
              String oTax2=null,oTax3=null,oSAccount=null,oPAccount=null;
               if(param1[13]!=null){ oTax2=( String)new StaticSentence(m_App.getSession(), "Select name from taxcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[13].toString());}
             if(param1[14]!=null){oTax3=( String)new StaticSentence(m_App.getSession(), "Select name from taxcategories where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[14].toString());}
             //tax3cascade
              //codetype
              if(param1[21]!=null){oPAccount=( String)new StaticSentence(m_App.getSession(), "Select name from accountmaster  where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[21].toString());}
              
              if(param1[22]!=null){oSAccount=( String)new StaticSentence(m_App.getSession(), "Select name from accountmaster  where id=?",SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(param1[22].toString());}
              
                    oRefText.setText( param1[0].toString());
                   
                    oBarCode.setText(param1[1].toString());
                    
                    oName.setText( param1[2].toString());
                    
                    oBuyPriceText.setText( param1[3].toString());  
                    
                    oSellPriceText.setText(param1[4].toString());
                   
                    oCategory.setText(ocategory);
                  
                    oPrintCatText.setText(oprcategory);
                  
                    oTaxCatText.setText(otaxcategory);
                   
                    
                     if(Integer.parseInt(param1[8].toString())==1) { oAuxCheckBox.setSelected(true);}
                   else  oAuxCheckBox.setSelected(false);
                   
                    if(Integer.parseInt(param1[9].toString())==1) {oScaleCheckBox.setSelected(true);}
                   else  oScaleCheckBox.setSelected(false);
                   
                    if(Integer.parseInt(param1[27].toString())==1) {jCheckBox1.setSelected(true);}
                   else jCheckBox1.setSelected(false);
                    
                    
                    oUnitText.setText(ounit);
                   
                    
                    oLocText.setText(olocation);
                   
                   
                    if(Integer.parseInt(param1[12].toString())==1) {oInvMainCheckBox.setSelected(true);}
                   else oInvMainCheckBox.setSelected(false);
                      
                    if(oTax2!=null){ oTax2Text.setText(oTax2);}
                   
                    if(oTax3!=null){oTax3Text.setText(oTax3);}
                   
                  
                    if(Integer.parseInt(param1[15].toString())==1){ oBasTax2Radio.setSelected(true);}
                   else oCasTax2Radio.setSelected(true);
                  if(Integer.parseInt(param1[16].toString())==1){ oBasTax3Radio.setSelected(true);}
                   else oCasTax3Radio.setSelected(true);
                  //tax3cascade
                    //codetype
                    if(param1[19]!=null) { oStockCost.setText(param1[19].toString());}
                    //stockvolume
                  if(oSAccount!=null) { oSAccountText.setText(oSAccount);}
                  
                   if(oPAccount!=null) { oPAccountText.setText(oPAccount);}
                   
                    if(param1[23]!=null) { oMaxStockLevel.setText(param1[23].toString());}
                   
                    if(param1[24]!=null) { oMinStockLevel.setText(param1[24].toString());}
                    
                     if(param1[25]!=null) { oReorderQtyText.setText(param1[25].toString());}
                   
                    if(param1[26]!=null) {oReorderLevelText.setText(param1[26].toString());}

                      oprodInfoExt= m_dlSales.getProductInfo(showdata.getPR_ID());
                       if(oprodInfoExt!=null){ 
                            if(oprodInfoExt.getImage()!=null){
                           ophotoLabel.setIcon(oResizeImage(oprodInfoExt.getImage()));
                         //  oPropText.setText(Formats.BYTEA.formatValue(oprodInfoExt.getProperties()));
                            }
                       }
                        highlightChanges();
 }
                  
                    return null;  
                    }
                  
                };
                    t.execute();
                }catch(BasicException e){ 
                System.out.println(e);
                e.printStackTrace();
                }
               jScrollPane2.getVerticalScrollBar().setValue(0);
            Diff_DetailPanel.setVisible(true);
             main_panel.setVisible(false);
          }
    }//GEN-LAST:event_selectButtActionPerformed

    private void pendingReqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingReqButtonActionPerformed
        // TODO add your handling code here:
        try{
         
         loaddata();
                    allReqFlag=0;
                  pendingReqFlag=1;
                dateWiseReqFlag=0;
        }catch(BasicException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_pendingReqButtonActionPerformed

    private void AllReqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllReqButtonActionPerformed
        // TODO add your handling code here:
        try{
         DatePanel.setVisible(false);
        ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductApprovalData(m_App);
         jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableColumnModel cmodel = jTable1.getColumnModel();
                 cmodel.getColumn(0).setPreferredWidth(100);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(150);
                
                 allReqFlag=1;
                  pendingReqFlag=0;
                dateWiseReqFlag=0;
  
  
        }catch(BasicException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_AllReqButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
           try{                                           
               main_panel.setVisible(true);
               loaddata();
           }catch(BasicException ex){
            Logger.getLogger(ProductsApproval.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }//GEN-LAST:event_backButtonActionPerformed

    private void fromButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromButtonActionPerformed
        // TODO add your handling code here:
        
                   dateFrom=new Date();
                  Calendar call = Calendar.getInstance();
                  call.setTimeInMillis(new Date().getTime());
                   dateFrom = JCalendarDialog.showCalendar(this, dateFrom);
                   fromText.setText(sdf.format(dateFrom));
                  strdateFrom=Formats.TIMESTAMP.formatValue(dateFrom);
                 
    }//GEN-LAST:event_fromButtonActionPerformed

    private void toButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toButtonActionPerformed
        // TODO add your handling code here:
                  dateTo=new Date();
                  Calendar call = Calendar.getInstance();
                  call.setTimeInMillis(new Date().getTime());             
                     
                 dateTo = JCalendarDialog.showCalendar(this, dateTo);
                 call.setTime(dateTo);
                  call.set(Calendar.HOUR_OF_DAY, 23);
	                    call.set(Calendar.MINUTE, 59);
	                    call.set(Calendar.SECOND, 59);
	                    call.set(Calendar.MILLISECOND, 00);
                            dateTo.setTime(call.getTimeInMillis());
                 toText.setText(sdf.format(dateTo));
                 strdateTo=Formats.TIMESTAMP.formatValue(dateTo);
                 
    }//GEN-LAST:event_toButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        if((fromText.getText().trim().length()>0)&&(toText.getText().trim().length()>0)){
          try{
                dateTo = (Date) Formats.TIMESTAMP.parseValue(strdateTo);
                      dateFrom =  (Date) Formats.TIMESTAMP.parseValue(strdateFrom);
                    System.out.println(dateTo);
                     System.out.println(dateFrom);
                 
         
        ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductRequestDataByDate(m_App,dateFrom,dateTo);
         jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         TableColumnModel cmodel = jTable1.getColumnModel();
                 cmodel.getColumn(0).setPreferredWidth(100);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(150);
                
                 allReqFlag=0;
                  pendingReqFlag=0;
                dateWiseReqFlag=1;
                dateFromOfFlag=dateFrom;
                dateToOfFlag=dateTo;
      
        }catch(BasicException ex){
            ex.printStackTrace();
        }
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DatePanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AllReqButton;
    private javax.swing.JPanel DatePanel;
    private javax.swing.JPanel Diff_DetailPanel;
    private javax.swing.JLabel NEW;
    private javax.swing.JLabel OLD;
    private javax.swing.JLabel actionByLabel;
    private javax.swing.JLabel actionDateLabel;
    private javax.swing.JLabel actionHostLabel;
    private javax.swing.JLabel actionLabel;
    private javax.swing.JLabel actionLabel1;
    private javax.swing.JLabel appByLabel;
    private javax.swing.JLabel appDateLabel;
    private javax.swing.JLabel appHostLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JButton fromButton;
    private javax.swing.JTextField fromText;
    private javax.swing.JButton jApproveBut;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jRejectBut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JCheckBox nAuxCheckBox;
    private javax.swing.JTextField nBarCode;
    private javax.swing.JRadioButton nBasTax2Radio;
    private javax.swing.JRadioButton nBasTax3Radio;
    private javax.swing.JTextField nBuyPriceText;
    private javax.swing.JRadioButton nCasTax2Radio;
    private javax.swing.JRadioButton nCasTax3Radio;
    private javax.swing.JTextField nCategory;
    private javax.swing.JCheckBox nInvMainCheckBox;
    private javax.swing.JTextField nLocText;
    private javax.swing.JTextField nMaxStockLevel;
    private javax.swing.JTextField nMinStockLevel;
    private javax.swing.JTextField nName;
    private javax.swing.JTextField nPAccountText;
    private javax.swing.JTextField nPrintCatText;
    private javax.swing.JTextField nRefText;
    private javax.swing.JTextField nReorderLevelText;
    private javax.swing.JTextField nReorderQtyText;
    private javax.swing.JTextField nSAccountText;
    private javax.swing.JCheckBox nScaleCheckBox;
    private javax.swing.JTextField nSellPriceText;
    private javax.swing.JTextField nStockCost;
    private javax.swing.JTextField nTax2Text;
    private javax.swing.JTextField nTax3Text;
    private javax.swing.JTextField nTaxCatText;
    private javax.swing.JTextField nUnitText;
    private javax.swing.JLabel nphotoLabel;
    private javax.swing.JCheckBox oAuxCheckBox;
    private javax.swing.JTextField oBarCode;
    private javax.swing.JRadioButton oBasTax2Radio;
    private javax.swing.JRadioButton oBasTax3Radio;
    private javax.swing.JTextField oBuyPriceText;
    private javax.swing.JRadioButton oCasTax2Radio;
    private javax.swing.JRadioButton oCasTax3Radio;
    private javax.swing.JTextField oCategory;
    private javax.swing.JCheckBox oInvMainCheckBox;
    private javax.swing.JTextField oLocText;
    private javax.swing.JTextField oMaxStockLevel;
    private javax.swing.JTextField oMinStockLevel;
    private javax.swing.JTextField oName;
    private javax.swing.JTextField oPAccountText;
    private javax.swing.JTextField oPrintCatText;
    private javax.swing.JTextField oRefText;
    private javax.swing.JTextField oReorderLevelText;
    private javax.swing.JTextField oReorderQtyText;
    private javax.swing.JTextField oSAccountText;
    private javax.swing.JCheckBox oScaleCheckBox;
    private javax.swing.JTextField oSellPriceText;
    private javax.swing.JTextField oStockCost;
    private javax.swing.JTextField oTax2Text;
    private javax.swing.JTextField oTax3Text;
    private javax.swing.JTextField oTaxCatText;
    private javax.swing.JTextField oUnitText;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel ophotoLabel;
    private javax.swing.JButton pendingReqButton;
    private javax.swing.JLabel photo;
    private javax.swing.JButton selectButt;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton toButton;
    private javax.swing.JTextField toText;
    // End of variables declaration//GEN-END:variables


public String getTitle() {
       return "Products Approval List";
    }

    public void activate() throws BasicException {
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
         m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }

    public Object getBean() {
        return this;
    }
    
    
 public void loaddata() throws  BasicException{
     
      try{
         
        ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductPendingRequestData(m_App);
         jTable1.setModel( ProductsApproval_Table_Model.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(100);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(150);
        }catch(BasicException ex){
            ex.printStackTrace();
        }
            
            main_panel.setVisible(true);
            Diff_DetailPanel.setVisible(false);
             fromText.setText(null);
           toText.setText(null);
           DatePanel.setVisible(false);
          jApproveBut.setVisible(false);
          jRejectBut.setVisible(false);
           actionLabel.setVisible(false);
          actionLabel1.setVisible(false);
          actionByLabel.setVisible(false);
               actionHostLabel.setVisible(false);
                actionDateLabel.setVisible(false);

        appByLabel.setVisible(false);
        appHostLabel.setVisible(false);
        appDateLabel.setVisible(false);
           setAllTextUneditable();
      
 }   
    
  
public void resetOldTextFields() throws BasicException{
oRefText.setText(null);
oBarCode.setText(null);
oName.setText(null);
oBuyPriceText.setText(null);
oSellPriceText.setText(null);
oCategory.setText(null);
oPrintCatText.setText(null);
oTaxCatText.setText(null);
oTax2Text.setText(null);

oBasTax2Radio.setSelected(false);
oCasTax2Radio.setSelected(false);

oTax3Text.setText(null);

oBasTax3Radio.setSelected(false);
oCasTax3Radio.setSelected(false);

oLocText.setText(null);
oStockCost.setText(null);
oMinStockLevel.setText(null);
oMaxStockLevel.setText(null);
oUnitText.setText(null);
oPAccountText.setText(null);
oSAccountText.setText(null);
oReorderQtyText.setText(null);
oReorderLevelText.setText(null);

oInvMainCheckBox.setSelected(false);
oAuxCheckBox.setSelected(false);
oScaleCheckBox.setSelected(false);
ophotoLabel.setIcon(null);

 actionLabel.setVisible(false);
          actionLabel1.setVisible(false);
          actionByLabel.setVisible(false);
               actionHostLabel.setVisible(false);
                actionDateLabel.setVisible(false);

        appByLabel.setVisible(false);
        appHostLabel.setVisible(false);
        appDateLabel.setVisible(false);
}
public void resetNewTextFields() throws BasicException{
nRefText.setText(null);
nBarCode.setText(null);
nName.setText(null);
nBuyPriceText.setText(null);
nSellPriceText.setText(null);
nCategory.setText(null);
nPrintCatText.setText(null);
nTaxCatText.setText(null);
nTax2Text.setText(null);

nBasTax2Radio.setSelected(false);
nCasTax2Radio.setSelected(false);

nTax3Text.setText(null);

nBasTax3Radio.setSelected(false);
nCasTax3Radio.setSelected(false);

nLocText.setText(null);
nStockCost.setText(null);
nMinStockLevel.setText(null);
nMaxStockLevel.setText(null);
nUnitText.setText(null);
nPAccountText.setText(null);
nSAccountText.setText(null);
nReorderQtyText.setText(null);
nReorderLevelText.setText(null);

nInvMainCheckBox.setSelected(false);
nAuxCheckBox.setSelected(false);
nScaleCheckBox.setSelected(false);
nphotoLabel.setIcon(null);
 actionLabel.setVisible(false);
          actionLabel1.setVisible(false);
          actionByLabel.setVisible(false);
               actionHostLabel.setVisible(false);
                actionDateLabel.setVisible(false);

        appByLabel.setVisible(false);
        appHostLabel.setVisible(false);
        appDateLabel.setVisible(false);
}

public void setAllTextUneditable() throws BasicException{
 
 fromText.setEditable(false);
 fromText.setDisabledTextColor(Color.BLACK) ;
 toText.setEditable(false);    
 toText.setDisabledTextColor(Color.BLACK) ;
//oldfields
 oRefText.setEnabled(false);
 oRefText.setDisabledTextColor(Color.BLACK) ;
oBarCode.setEnabled(false);
oBarCode.setDisabledTextColor(Color.BLACK) ;
oName.setEnabled(false);
oName.setDisabledTextColor(Color.BLACK) ;
oBuyPriceText.setEnabled(false);
oBuyPriceText.setDisabledTextColor(Color.BLACK) ;
oSellPriceText.setEnabled(false);
oSellPriceText.setDisabledTextColor(Color.BLACK) ;
oCategory.setEnabled(false);
oCategory.setDisabledTextColor(Color.BLACK) ;
oPrintCatText.setEnabled(false);
oPrintCatText.setDisabledTextColor(Color.BLACK) ;
oTaxCatText.setEnabled(false);
oTaxCatText.setDisabledTextColor(Color.BLACK) ;
oTax2Text.setEnabled(false);
oTax2Text.setDisabledTextColor(Color.BLACK) ;

oBasTax2Radio.setEnabled(false);
oCasTax2Radio.setEnabled(false);


oTax3Text.setEnabled(false);
oTax3Text.setDisabledTextColor(Color.BLACK) ;

oBasTax3Radio.setEnabled(false);
oCasTax3Radio.setEnabled(false);

oLocText.setEnabled(false);
oLocText.setDisabledTextColor(Color.BLACK) ;
oStockCost.setEnabled(false);
oStockCost.setDisabledTextColor(Color.BLACK) ;
oMinStockLevel.setEnabled(false);
oMinStockLevel.setDisabledTextColor(Color.BLACK) ;
oMaxStockLevel.setEnabled(false);
oMaxStockLevel.setDisabledTextColor(Color.BLACK) ;
oUnitText.setEnabled(false);
oUnitText.setDisabledTextColor(Color.BLACK) ;
oPAccountText.setEnabled(false);
oPAccountText.setDisabledTextColor(Color.BLACK) ;
oSAccountText.setEnabled(false);
oSAccountText.setDisabledTextColor(Color.BLACK) ;
oReorderQtyText.setEnabled(false);
oReorderQtyText.setDisabledTextColor(Color.BLACK) ;
oReorderLevelText.setEnabled(false);
oReorderLevelText.setDisabledTextColor(Color.BLACK) ;

oInvMainCheckBox.setEnabled(false);
oAuxCheckBox.setEnabled(false);
oScaleCheckBox.setEnabled(false);
jCheckBox1.setEnabled(false);


//new fields
nRefText.setEnabled(false);
nRefText.setDisabledTextColor(Color.BLACK) ;
nBarCode.setEnabled(false);
nBarCode.setDisabledTextColor(Color.BLACK) ;
nName.setEnabled(false);
nName.setDisabledTextColor(Color.BLACK) ;
nBuyPriceText.setEnabled(false);
nBuyPriceText.setDisabledTextColor(Color.BLACK) ;
nSellPriceText.setEnabled(false);
nSellPriceText.setDisabledTextColor(Color.BLACK) ;
nCategory.setEnabled(false);
nCategory.setDisabledTextColor(Color.BLACK) ;

nPrintCatText.setEnabled(false);
nPrintCatText.setDisabledTextColor(Color.BLACK) ;
nTaxCatText.setEnabled(false);
nTaxCatText.setDisabledTextColor(Color.BLACK) ;
nTax2Text.setEnabled(false);
nTax2Text.setDisabledTextColor(Color.BLACK) ;

nBasTax2Radio.setEnabled(false);
nCasTax2Radio.setEnabled(false);


nTax3Text.setEnabled(false);
nTax3Text.setDisabledTextColor(Color.BLACK) ;

nBasTax3Radio.setEnabled(false);
nCasTax3Radio.setEnabled(false);

nLocText.setEnabled(false);
nLocText.setDisabledTextColor(Color.BLACK) ;
nStockCost.setEnabled(false);
nStockCost.setDisabledTextColor(Color.BLACK) ;
nMinStockLevel.setEnabled(false);
nMinStockLevel.setDisabledTextColor(Color.BLACK) ;
nMaxStockLevel.setEnabled(false);
nMaxStockLevel.setDisabledTextColor(Color.BLACK) ;
nUnitText.setEnabled(false);
nUnitText.setDisabledTextColor(Color.BLACK) ;
nPAccountText.setEnabled(false);
nPAccountText.setDisabledTextColor(Color.BLACK) ;
nSAccountText.setEnabled(false);
nSAccountText.setDisabledTextColor(Color.BLACK) ;
nReorderQtyText.setEnabled(false);
nReorderQtyText.setDisabledTextColor(Color.BLACK) ;
nReorderLevelText.setEnabled(false);
nReorderLevelText.setDisabledTextColor(Color.BLACK) ;

nInvMainCheckBox.setEnabled(false);
nAuxCheckBox.setEnabled(false);
nScaleCheckBox.setEnabled(false);
jCheckBox2.setEnabled(false);

        }

public void highlightChanges(){
if(!(oRefText.getText().equals(nRefText.getText())))
{       
    oRefText.setDisabledTextColor(Color.BLUE) ;
    nRefText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oBarCode.getText().trim().equals(nBarCode.getText().trim())))
{       
    oBarCode.setDisabledTextColor(Color.BLUE) ;
    nBarCode.setDisabledTextColor(Color.BLUE) ;
}
if(!(oName.getText().trim().equals(nName.getText().trim())))
{       
    oName.setDisabledTextColor(Color.BLUE) ;
    nName.setDisabledTextColor(Color.BLUE) ;
}
if(!(oBuyPriceText.getText().trim().equals(nBuyPriceText.getText().trim())))
{       
    oBuyPriceText.setDisabledTextColor(Color.BLUE) ;
    nBuyPriceText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oSellPriceText.getText().trim().equals(nSellPriceText.getText().trim())))
{       
    oSellPriceText.setDisabledTextColor(Color.BLUE) ;
    nSellPriceText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oCategory.getText().trim().equals(nCategory.getText().trim())))
{       
    oCategory.setDisabledTextColor(Color.BLUE) ;
    nCategory.setDisabledTextColor(Color.BLUE) ;
}
if(!(oPrintCatText.getText().trim().equals(nPrintCatText.getText().trim())))
{       
    oPrintCatText.setDisabledTextColor(Color.BLUE) ;
    nPrintCatText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oTaxCatText.getText().trim().equals(nTaxCatText.getText().trim())))
{       
    oTaxCatText.setDisabledTextColor(Color.BLUE) ;
    nTaxCatText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oTax2Text.getText().trim().equals(nTax2Text.getText().trim())))
{       
    oTax2Text.setDisabledTextColor(Color.BLUE) ;
    nTax2Text.setDisabledTextColor(Color.BLUE) ;
}
if(!(oTax3Text.getText().trim().equals(nTax3Text.getText().trim())))
{       
    oTax3Text.setDisabledTextColor(Color.BLUE) ;
    nTax3Text.setDisabledTextColor(Color.BLUE) ;
}
if(!(oLocText.getText().trim().equals(nLocText.getText().trim())))
{       
    oLocText.setDisabledTextColor(Color.BLUE) ;
    nLocText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oStockCost.getText().trim().equals(nStockCost.getText().trim())))
{       
    oStockCost.setDisabledTextColor(Color.BLUE) ;
    nStockCost.setDisabledTextColor(Color.BLUE) ;
}
if(!(oMinStockLevel.getText().trim().equals(nMinStockLevel.getText().trim())))
{       
    oMinStockLevel.setDisabledTextColor(Color.BLUE) ;
    nMinStockLevel.setDisabledTextColor(Color.BLUE) ;
}
if(!(oMaxStockLevel.getText().trim().equals(nMaxStockLevel.getText().trim())))
{       
    oMaxStockLevel.setDisabledTextColor(Color.BLUE) ;
    nMaxStockLevel.setDisabledTextColor(Color.BLUE) ;
}
if(!(oUnitText.getText().trim().equals(nUnitText.getText().trim())))
{       
    oUnitText.setDisabledTextColor(Color.BLUE) ;
    nUnitText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oPAccountText.getText().trim().equals(nPAccountText.getText().trim())))
{       
    oPAccountText.setDisabledTextColor(Color.BLUE) ;
    nPAccountText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oSAccountText.getText().trim().equals(nSAccountText.getText().trim())))
{       
    oSAccountText.setDisabledTextColor(Color.BLUE) ;
    nSAccountText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oReorderQtyText.getText().trim().equals(nReorderQtyText.getText().trim())))
{       
    oReorderQtyText.setDisabledTextColor(Color.BLUE) ;
    nReorderQtyText.setDisabledTextColor(Color.BLUE) ;
}
if(!(oReorderLevelText.getText().trim().equals(nReorderLevelText.getText().trim())))
{       
    oReorderLevelText.setDisabledTextColor(Color.BLUE) ;
    nReorderLevelText.setDisabledTextColor(Color.BLUE) ;
}



}
private Icon nResizeImage(Image img) {

//        ImageIcon MyImage = new ImageIcon(filename);
//        Image img = MyImage.getImage();
        Image newimg = img.getScaledInstance(nphotoLabel.getWidth(), nphotoLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
        
    }
private Icon oResizeImage(Image img) {

//        ImageIcon MyImage = new ImageIcon(filename);
//        Image img = MyImage.getImage();
        Image newimg = img.getScaledInstance(ophotoLabel.getWidth(), ophotoLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
        
    }
public boolean catalogFlag()
{
    
    return true;
}
}
