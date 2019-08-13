/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchaseJournalReport.java
 *
 * Created on 06-Aug-2011, 14:29:02
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataSourceForPurchaseDetail;
import com.openbravo.pos.clubmang.DataSourceForPurchaseJournal;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JRBasicField;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class PurchaseJournalReport extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_app;
    private DataLogicFacilities dlfac;
    private waitDialog w;
    private Date startdate;
    private Date enddate;
    private List<PurchaseJournalData> purchaselist = new ArrayList<PurchaseJournalData>();  


    /** Creates new form PurchaseJournalReport */
    public PurchaseJournalReport() {
        initComponents();
    }

    private List<PurchaseJournalData> getPurchaseData(Date sdate, Date edate) throws BasicException {
      /*   List<PurchaseJournalData> purlistele= new StaticSentence(m_app.getSession()
                 ,"SELECT  PTNO, PDC, PI, PDOC, PCR, VN, PT, PRI,PRDN, PRQTY, PRRATE, PRTOTAL, PRID, PID ,ATRAN, AAMT, ANAR, AMNAME,ETYPE " +
                 "FROM " +
                 "(SELECT P.TNO AS PTNO,P.DELIVERYCHALLAN AS PDC,P.INVOICENO AS PI,P.DOCUMENTREF AS PDOC,P.CRDATE AS PCR,V.NAME AS VN,P.TOTAL AS PT,PR.ITEM AS PRI,PRD.NAME AS PRDN,PR.QTY AS PRQTY,PR.RATE AS PRRATE,PR.TOTAL AS PRTOTAL,PR.ID AS PRID,P.ID AS PID , NULL AS ATRAN,NULL AS AAMT,NULL AS ANAR,NULL AS AMNAME , 'PJ' AS ETYPE " +
                 "FROM PURCHASEJOURNALMAIN P,PURCHASEJOURNAL PR,VENDOR V,PRODUCTS  PRD WHERE V.ID=P.VENDOR  AND P.CRDATE>=? AND P.CRDATE<=?   AND  PR.PARENT=P.ID AND PR.ITEM=PRD.ID  " +
                 " UNION ALL " +
                 "SELECT A.TRANSNO AS PTNO,NULL AS PDC,NULL AS PI,NULL AS PDOC,A.DATE AS PCR,NULL AS VN,NULL AS PT,NULL AS PRI,NULL AS PRDN,NULL AS PRQTY,NULL AS PRRATE,NULL AS PRTOTAL,NULL AS PRID,A.TID AS PID,A.TRANSTYPE AS ATRAN,A.AMOUNT AS AAMT,A.NARRATION AS ANAR,AM.NAME AS AMNAME , 'AC' AS ETYPE " +
                 "FROM ACCOUNTJOURNAL A, ACCOUNTMASTER AM WHERE A.ACCOUNTID=AM.ID AND A.TID IN (SELECT P.ID FROM PURCHASEJOURNALMAIN P WHERE P.CRDATE>=? AND P.CRDATE<=?))" +
                 " AS PURCHASE ORDER BY PCR,PTNO,PID,ATRAN DESC"
                 //,"SELECT P.TNO,P.DELIVERYCHALLAN,P.INVOICENO,P.DOCUMENTREF,P.CRDATE,V.NAME,P.TOTAL,PR.ITEM,PRD.NAME," +
                 //"PR.QTY,PR.RATE,PR.TOTAL,PR.ID,P.ID FROM PURCHASEJOURNALMAIN P,PURCHASEJOURNAL PR,VENDOR V,PRODUCTS  PRD " +
                // "WHERE V.ID=P.VENDOR AND P.CRDATE>=? AND P.CRDATE<=? AND PR.PARENT=P.ID AND PR.ITEM=PRD.ID  ORDER BY P.CRDATE,P.TNO"

                           //, "SELECT P.TNO,P.DELIVERYCHALLAN,P.INVOICENO,P.DOCUMENTREF,P.CRDATE,V.NAME,P.TOTAL,PR.ITEM,PRD.NAME,PR.QTY,PR.RATE,PR.TOTAL,PR.ID,P.ID,A.ID,A.TRANSTYPE,A.AMOUNT,A.NARRATION,AM.NAME " +
                           //"FROM PURCHASEJOURNALMAIN P,PURCHASEJOURNAL PR,VENDOR V,PRODUCTS  PRD,ACCOUNTJOURNAL A,ACCOUNTMASTER AM " +
                           //"WHERE V.ID=P.VENDOR AND P.CRDATE>=? AND P.CRDATE<=?   AND  PR.PARENT=P.ID AND PR.ITEM=PRD.ID AND A.TID=P.ID AND A.ACCOUNTID=AM.ID ORDER BY P.CRDATE,P.TNO,A.TID,A.TRANSTYPE"
                           ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                           ,new SerializerReadClass(PurchaseJournalData.class)).list(new Object[]{sdate,edate,sdate,edate}); */
                        
        
          List<PurchaseJournalData> purlistele= new StaticSentence(m_app.getSession()
                 ,"SELECT  PTNO, PDC, PI, PDOC, PCR, VN, PT, PRI,PRDN, PRQTY, PRRATE, PRTOTAL, PRID, PID ,ATRAN, AAMT, ANAR, AMNAME,ETYPE " +
                 "FROM " +
                 "(SELECT P.TNO AS PTNO,P.DELIVERYCHALLAN AS PDC,P.INVOICENO AS PI,P.DOCUMENTREF AS PDOC,P.CRDATE AS PCR,V.NAME AS VN,P.TOTAL AS PT,PR.ITEM AS PRI,PRD.NAME AS PRDN,PR.QTY AS PRQTY,PR.RATE AS PRRATE,PR.TOTAL AS PRTOTAL,PR.ID AS PRID,P.ID AS PID , NULL AS ATRAN,NULL AS AAMT,NULL AS ANAR,NULL AS AMNAME , 'PJ' AS ETYPE " +
                 "FROM PURCHASEJOURNALMAIN P,PURCHASEJOURNAL PR,VENDOR V,PRODUCTS  PRD WHERE V.ID=P.VENDOR  AND P.CRDATE>=? AND P.CRDATE<=?   AND  PR.PARENT=P.ID AND PR.ITEM=PRD.ID AND P.DEACTBY IS NULL " +
                 " UNION ALL " +
                 "SELECT A.TRANSNO AS PTNO,NULL AS PDC,NULL AS PI,NULL AS PDOC,A.DATE AS PCR,NULL AS VN,NULL AS PT,NULL AS PRI,NULL AS PRDN,NULL AS PRQTY,NULL AS PRRATE,NULL AS PRTOTAL,NULL AS PRID,A.TID AS PID,A.TRANSTYPE AS ATRAN,A.AMOUNT AS AAMT,A.NARRATION AS ANAR,AM.NAME AS AMNAME , 'AC' AS ETYPE " +
                 "FROM ACCOUNTJOURNAL A, ACCOUNTMASTER AM WHERE A.ACCOUNTID=AM.ID AND A.ACTIVE = TRUE AND A.TID IN (SELECT P.ID FROM PURCHASEJOURNALMAIN P WHERE P.CRDATE>=? AND P.CRDATE<=? AND P.DEACTBY IS NULL))" +
                 " AS PURCHASE ORDER BY PCR,PTNO,PID,ATRAN DESC"
                 //,"SELECT P.TNO,P.DELIVERYCHALLAN,P.INVOICENO,P.DOCUMENTREF,P.CRDATE,V.NAME,P.TOTAL,PR.ITEM,PRD.NAME," +
                 //"PR.QTY,PR.RATE,PR.TOTAL,PR.ID,P.ID FROM PURCHASEJOURNALMAIN P,PURCHASEJOURNAL PR,VENDOR V,PRODUCTS  PRD " +
                // "WHERE V.ID=P.VENDOR AND P.CRDATE>=? AND P.CRDATE<=? AND PR.PARENT=P.ID AND PR.ITEM=PRD.ID  ORDER BY P.CRDATE,P.TNO"

                           //, "SELECT P.TNO,P.DELIVERYCHALLAN,P.INVOICENO,P.DOCUMENTREF,P.CRDATE,V.NAME,P.TOTAL,PR.ITEM,PRD.NAME,PR.QTY,PR.RATE,PR.TOTAL,PR.ID,P.ID,A.ID,A.TRANSTYPE,A.AMOUNT,A.NARRATION,AM.NAME " +
                           //"FROM PURCHASEJOURNALMAIN P,PURCHASEJOURNAL PR,VENDOR V,PRODUCTS  PRD,ACCOUNTJOURNAL A,ACCOUNTMASTER AM " +
                           //"WHERE V.ID=P.VENDOR AND P.CRDATE>=? AND P.CRDATE<=?   AND  PR.PARENT=P.ID AND PR.ITEM=PRD.ID AND A.TID=P.ID AND A.ACCOUNTID=AM.ID ORDER BY P.CRDATE,P.TNO,A.TID,A.TRANSTYPE"
                           ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                           ,new SerializerReadClass(PurchaseJournalData.class)).list(new Object[]{sdate,edate,sdate,edate});
        
        
            List<PurchaseJournalData> list=new ArrayList<PurchaseJournalData>();
            list.addAll(purlistele);
            return list;
    }
    

    


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton4.setText("jButton4");
        jButton4.setName("jButton4"); // NOI18N

        jLabel1.setText("From");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("To");
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jButton2.setText("Date");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Date");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Execute");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(163, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            jTextField2.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
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
}//GEN-LAST:event_jButton1ActionPerformed

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
   

    public void loadReport(String from, String to, String filename, boolean display) {
        try {
           Date sdate = new Date();
            try {
                sdate = getDate(from);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Date edate = getSecondDate(to);
            startdate = sdate;
            enddate = edate;
            LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
            Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
            GeneralSettingInfo sinfo = gs.get("Datestart");
            GeneralSettingInfo einfo = gs.get("Dateend");
            Date fsdate = (Date) Formats.DATE.parseValue(sinfo.getValue());
            Date fedate = (Date) Formats.DATE.parseValue(einfo.getValue());
            Calendar fscal = Calendar.getInstance();//financial year sdate
            fscal.setTime(fsdate);
            Calendar fecal = Calendar.getInstance();//financial year edate
            fecal.setTime(fedate);
            Calendar rscal = Calendar.getInstance();//report start date
            Calendar rscal1 = Calendar.getInstance();
            Calendar recal = Calendar.getInstance();//report end date
            Calendar recal1 = Calendar.getInstance();//report end date
            rscal.setTimeInMillis(sdate.getTime());
            recal.setTimeInMillis(edate.getTime());
            rscal1.setTimeInMillis(rscal.getTimeInMillis());
            rscal1.add(Calendar.DATE, -1);
            recal1.setTimeInMillis(recal.getTimeInMillis());
            recal1.add(Calendar.DATE, -1);

            purchaselist = new ArrayList<PurchaseJournalData>();                                
            purchaselist = getPurchaseData(sdate, edate);
            Map reportparam = new HashMap();
            DataSourceProvider data1 = new DataSourceProvider(purchaselist);            
            DataSourceForPurchaseJournal ds = new DataSourceForPurchaseJournal(purchaselist);
            data1.setDataSource(ds);          
            reportparam.put("startdate", sdate);
            reportparam.put("enddate", edate);    
            reportparam.put("companyName", m_app.getSession().getCompanyName());
            JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/PurchaseJournal.jrxml", reportparam, false, data1, display, filename);
            w.hideDialog();//$F{transid}.equals("PJ")?$F{rate}:($F{transtype}.equals("D")?$F{accamount}:$F{zero})
            jTextField1.setText(null);
            jTextField2.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            w.hideDialog();
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        }
    }

    public static class PurchaseJournalData implements SerializableRead {

        private String transno;
        private String dcno;
        private String invno;
        private String docref;
        private Date date;
        private String vendor;
        private Double amount;
        private String item;
        private String name;
        private Integer qty;
        private Double rate;
        private Double tamount;
        private String pid;
        private String pmid;
        private String transid;
        private String transtype;
        private String narration;
        private String accname;
        private Double accamount;

        public Double getAccamount() {
            return accamount;
        }

        public String getAccname() {
            return accname;
        }

        public String getNarration() {
            return narration;
        }

        public String getTransid() {
            return transid;
        }

        public String getTranstype() {
            return transtype;
        }

        public String getPid() {
            return pid;
        }

        public String getPmid() {
            return pmid;
        }
        

        public String getItem() {
            return item;
        }

        public String getName() {
            return name;
        }

        public Integer getQty() {
            return qty;
        }

       

        public Double getRate() {
            return rate;
        }

        public Double getTamount() {
            return tamount;
        }

        public void readValues(DataRead dr) throws BasicException {
            transno = dr.getString(1);
            dcno = dr.getString(2);
            invno = dr.getString(3);
            docref = dr.getString(4);
            date = dr.getTimestamp(5);
            vendor = dr.getString(6);
            amount = dr.getDouble(7);
            item = dr.getString(8);
            name = dr.getString(9);
            qty = dr.getInt(10);
            rate = dr.getDouble(11);
            tamount = dr.getDouble(12);
            pid = dr.getString(13);
            pmid = dr.getString(14);
            transtype = dr.getString(15);
            accamount = dr.getDouble(16);
            narration = dr.getString(17);
           accname = dr.getString(18);
           transid = dr.getString(19);

        }
        

        public Double getAmount() {
            return amount;
        }

        public Date getDate() {
            return date;
        }

        public String getDcno() {
            return dcno;
        }

        public String getDocref() {
            return docref;
        }

        public String getInvno() {
            return invno;
        }

        public String getTransno() {
            return transno;
        }

        public String getVendor() {
            return vendor;
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().isEmpty() == true && jTextField2.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter both start an end date");

        } else if (jTextField1.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter Start Date");
        } else if (jTextField2.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter End Date");
        } else if (jTextField1.getText().isEmpty() == false && jTextField2.getText().isEmpty() == false) {
            w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);

            Thread t = new Thread(
                    new Runnable() {

                        public void run() {
                            try {

                                loadReport(jTextField1.getText(), jTextField2.getText(), null, true);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            //JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
                            }
                        }
                    });
            t.start();
            w.showDialog("Please wait.Loading Report...");
        }

}//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Purchase Journal Report";
    }

    public void activate() throws BasicException {
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_app = app;
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
    }

    public Object getBean() {
        return this;
    }    

}
