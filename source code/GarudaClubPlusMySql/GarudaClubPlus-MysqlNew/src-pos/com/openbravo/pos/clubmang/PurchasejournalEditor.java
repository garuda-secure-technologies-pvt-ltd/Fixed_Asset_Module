/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchasejournalEditor.java
 *
 * Created on 03-Dec-2009, 10:05:48
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AdditionalEntry;
import com.openbravo.pos.Accounts.PurchaseVoucherline;
import com.openbravo.pos.clubmang.purchasejournalEditorTableModel.PurchasejournalTable;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class PurchasejournalEditor extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form PurchasejournalEditor */
    private AppView app;
    private purchasejournalEditorTableModel pjEditorTableModel;
    private List<PurchaseVoucherline> plist=new ArrayList<PurchaseVoucherline>();
    private List<AdditionalEntry> addlList=new ArrayList<AdditionalEntry>();
    public PurchasejournalEditor() {
        initComponents();
    }

     public String getTitle() {
       return null;
    }

    public void activate() throws BasicException {
          pjEditorTableModel=purchasejournalEditorTableModel.emptyInstance();
          jTable1.setModel(pjEditorTableModel.getTableModel());
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.app=app;
        jLabel1.setText("From");
        jLabel2.setText("To");
        jButton1.setText("Date");
        jButton2.setText("To");
        jButton3.setText("Load Data");
        jButton4.setText("Select");
    }

    public Object getBean() {
        return this;
    }
    private void getPurchasedDetail(String id) throws BasicException{
        plist=new ArrayList<PurchaseVoucherline>();
        List<PurchaseVoucherline> list=new PreparedSentence(app.getSession()
               // , "SELECT PDT.NAME,P.QTY,P.RATE,P.TOTAL,P.TAXTOTAL,PDT.PACCOUNT,P.ITEM,TC.ID,TC.NAME,T.RATE FROM PURCHASEJOURNAL P JOIN PRODUCTS PDT ON P.ITEM=PDT.ID JOIN taxcategories TC ON TC.ID=PDT.TAXCAT JOIN TAXES T ON T.CATEGORY=TC.ID WHERE P.PARENT=? ORDER BY PDT.NAME"//edited by pratima to add tax2 and tax3 values 
//                 , "SELECT PDT.NAME,P.QTY,P.RATE,P.TOTAL,P.TAX1,PDT.PACCOUNT,P.ITEM,\n" +
//"pdt.taxcat,(select tc.name  from taxcategories tc where TC.ID=PDT.TAXCAT) as taxname,(select t.rate  from taxes t where T.CATEGORY=PDT.TAXCAT) as taxrate,'temp','temp',\n" +
//"(select tc2.name  from taxcategories tc2 where TC2.ID=PDT.TAXCAT2) as tax2name,P.TAX2,(select t2.rate  from taxes t2 where T2.CATEGORY=PDT.TAXCAT2) as tax2rate,pdt.taxcat2,\n" +
//"(select tc3.name  from taxcategories tc3 where TC3.ID=PDT.TAXCAT3) as tax3name,P.TAX3,(select t3.rate  from taxes t3 where T3.CATEGORY=PDT.TAXCAT3) as tax3rate,pdt.taxcat3,\n" +
//"pdt.basic2,pdt.basic3,'temp'\n" +
//"FROM PURCHASEJOURNAL P JOIN PRODUCTS PDT ON P.ITEM=PDT.ID JOIN taxcategories TC ON TC.ID=PDT.TAXCAT or TC.ID=PDT.TAXCAT2 or TC.ID=PDT.TAXCAT3 JOIN TAXES T ON T.CATEGORY=PDT.TAXCAT or T.CATEGORY=PDT.TAXCAT2 or T.CATEGORY=PDT.TAXCAT3 WHERE P.PARENT=? ORDER BY PDT.NAME "             
      //Added By GuruGani  
                , "SELECT PDT.NAME,P.QTY,P.RATE,P.TOTAL,P.TAX1,PDT.PACCOUNT,P.ITEM,\n" +
"pdt.taxcat,(select tc.name  from taxcategories tc where TC.ID=PDT.TAXCAT) as taxname,(select t.rate  from taxes t where T.CATEGORY=PDT.TAXCAT) as taxrate,'temp','temp',\n" +
"(select tc2.name  from taxcategories tc2 where TC2.ID=PDT.TAXCAT2) as tax2name,P.TAX2,(select t2.rate  from taxes t2 where T2.CATEGORY=PDT.TAXCAT2) as tax2rate,pdt.taxcat2,\n" +
"(select tc3.name  from taxcategories tc3 where TC3.ID=PDT.TAXCAT3) as tax3name,P.TAX3,(select t3.rate  from taxes t3 where T3.CATEGORY=PDT.TAXCAT3) as tax3rate,pdt.taxcat3,\n" +
"pdt.basic2,pdt.basic3,'temp'\n" +
"FROM PURCHASEJOURNAL P JOIN PRODUCTS PDT ON P.ITEM=PDT.ID WHERE P.PARENT=? ORDER BY PDT.NAME "             
                , SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseVoucherline.class)).list(id);
        if(list!=null)
            plist.addAll(list);
        addlList=new ArrayList<AdditionalEntry>();
        List<AdditionalEntry> list1=new PreparedSentence(app.getSession()
                , "SELECT AM.NAME,A.AMOUNT,A.ACCOUNTID,AM.PARENT,A.TRANSTYPE,A.NARRATION FROM ACCOUNTJOURNAL A JOIN ACCOUNTMASTER AM ON A.ACCOUNTID=AM.ID WHERE A.TID=? AND (A.NARRATION =? OR AM.PARENT='1.1.1' OR AM.PARENT='1.2.1') ORDER BY AM.NAME"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(AdditionalEntry.class)).list(new Object[]{id,"Additional charges"});
        if(list1!=null)
            addlList.addAll(list1);
     }
    private void loadData(){
      try {
            pjEditorTableModel = purchasejournalEditorTableModel.loadData(app, (Date) Formats.DATE.parseValue(jTextField1.getText()), (Date) Formats.DATE.parseValue(jTextField2.getText()));
            jTable1.setModel(pjEditorTableModel.getTableModel());
        } catch (BasicException ex) {
           ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jTextField2)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButton1)
                            .add(jButton2))
                        .add(235, 235, 235))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(91, 91, 91))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton3)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton4)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       loadData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField1.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTextField1.setText(Formats.DATE.formatValue(date));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
       
        if (date != null) {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            date.setTime(cal.getTimeInMillis());
            jTextField2.setText(Formats.DATE.formatValue(date));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       int row=jTable1.getSelectedRow();
       if(row>=0){
           PurchasejournalTable pjEntry = pjEditorTableModel.getPurchaseList().get(row);
         //  if(pjEntry.getDeactivatedby()!=null){
            try {
               // boolean flag=false;
                

                
                JDialog dialog = new JDialog(new JFrame(), true);
                dialog.setTitle("Purchase Journal Editor");
                dialog.setSize(790, 700);
                getPurchasedDetail(pjEntry.getID());
                String paymentAcc=null;
                AdditionalEntry temp=null;
                List<AdditionalEntry> tempAddlList=new ArrayList<AdditionalEntry>();
                tempAddlList.addAll(addlList);
                for(AdditionalEntry line:tempAddlList){
                    if(!line.getNarration().equals("Additional charges")){
                       if((line.getParentSearchKey().equals("1.1.1") || line.getParentSearchKey().equals("1.2.1")) && line.getTransType().equals("C") ){
                         temp=line;
                         paymentAcc=line.getAccount();
                       }
                    }
                    if(temp!=null)
                      addlList.remove(temp);
                }
                // PurchaseJournalTable ptable=new PurchaseJournalTable();
                //ptable.setlist(plist);
                // ptable.setadditionalEntrylist(addlList);
                Object[] obj = new Object[]{pjEntry.getVendorID(), pjEntry.getWarehouse(), pjEntry.getInvoiceNo(), pjEntry.getDate(), pjEntry.getDocref(), pjEntry.getDeliveryChallan(), pjEntry.getTNO(),paymentAcc,pjEntry.getID()};
                PurchaseJournal p = new PurchaseJournal();
                p.init(app);
                try {
                    p.activate();
                    p.setValue(obj, plist, addlList,dialog);
                } catch (BasicException ex) {
                    JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, "Error Occured while loading...", ex));
                    ex.printStackTrace();
                }
                dialog.add(p);
                dialog.setVisible(true);
                loadData();
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
          // }else{
          //     JOptionPane.showMessageDialog(null, "Sorry cannot edit a deactived entry", TOOL_TIP_TEXT_KEY, WIDTH);
         //  }
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount()>1){

        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables



}
