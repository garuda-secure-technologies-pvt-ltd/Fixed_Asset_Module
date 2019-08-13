/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BillList.java
 *
 * Created on Dec 12, 2008, 11:47:36 AM
 */
package com.openbravo.pos.inventory;

import com.openbravo.pos.sales.restaurant.*;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillLineInfo;
import com.openbravo.pos.sales.BillListTableModel;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.sales.BillTaxesLogic;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.sales.CreditConfirm_UsbCard;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import com.openbravo.pos.inventory.ProductsApprovalTableModel;
import com.openbravo.pos.inventory.ProductsApprovalTableModel.PrdApvInfo;

/**
 *
 * @author swathi
 */
public class ProductRequestList extends javax.swing.JDialog {

   
     
     private ProductsApprovalTableModel ProductsApproval_Table_Model;

    
    private List<ProductsApprovalTableModel.PrdApvInfo> plist;
   
    private DataLogicSales dlSales;
    private boolean resultok = false;
    
    private AppView app;
  
    
    private boolean flag;
   
   
    private DataLogicSystem dlSystem;
    
    
   
     //praveen:confirmer changes---start
    private String confirmer;
    private DataLogicFacilities dlfac;
   
     private String  reference;
    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }
    //praveen:confirmer changes---end

    /** Creates new form BillList */
    public ProductRequestList(java.awt.Frame parent, AppView app,String reference) {
        super(parent, true);        
       this.reference=reference;
        this.app = app;
        
       
    }

    public ProductRequestList(java.awt.Dialog parent, AppView app,String reference) {
        super(parent, true);
        this.reference=reference;
        this.app = app;
        
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    public static ProductRequestList getDialog(Component parent, AppView app,String reference) {

        Window window = getWindow(parent);

        ProductRequestList bill;

        if (window instanceof Frame) {
            bill = new ProductRequestList((Frame) window, app,reference);
        } else {
            bill = new ProductRequestList((Dialog) window, app,reference);
        }

        return bill;
    }
    
   

    private void loadData() throws BasicException {
       
//      plist=  ProdAppTabMod.getList();
 ProductsApproval_Table_Model=ProductsApprovalTableModel.LoadProductRequestData(app, reference);
   jTable2.setModel( ProductsApproval_Table_Model.getTableModel());
   TableColumnModel cmodel = jTable2.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(100);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(150);
    }

    

    public void init() throws BasicException {
        initComponents();
        
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       
        loadData();
       
       

//        TableColumnModel columnModel = jTable1.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(40);
//        columnModel.getColumn(0).setMaxWidth(40);
//        columnModel.getColumn(2).setPreferredWidth(60);
//        columnModel.getColumn(2).setMaxWidth(60);
//        columnModel.getColumn(3).setPreferredWidth(80);
//        columnModel.getColumn(3).setMaxWidth(80);
//        columnModel.getColumn(4).setPreferredWidth(100);
//        columnModel.getColumn(4).setMaxWidth(100);
//        columnModel.getColumn(5).setPreferredWidth(120);
//        columnModel.getColumn(5).setMaxWidth(120);
//        columnModel.getColumn(6).setPreferredWidth(80);
//        columnModel.getColumn(6).setMaxWidth(80);
//        columnModel.getColumn(6).setPreferredWidth(80);
//        columnModel.getColumn(6).setMaxWidth(80);
      

    }

    public boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return resultok;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pending Requests List");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jScrollPane1.setViewportView(jScrollPane2);

        jLabel1.setText("Pending Requests");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Pending Requests List");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables








}
