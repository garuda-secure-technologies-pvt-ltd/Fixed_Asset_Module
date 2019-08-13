/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TrialBalanceReport.java
 *
 * Created on 25-May-2010, 11:07:54
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountReports.TrailBalanceData;
import com.openbravo.pos.Accounts.AccountReports.TrailBalanceDataPeriod;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.DataSourceforTrailBalance;
import com.openbravo.pos.clubmang.DataSourceforTrailBalancePeriod;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JasperReportNew;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class TrialBalanceReport extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private waitDialog w;
    private Date enddate;
    private Date fromdate;
    private AppView m_app;
    private DataLogicFacilities dlfac;
    private List<TrailBalanceData> acclist = new ArrayList<TrailBalanceData>();
      private List<TrailBalanceDataPeriod> acclistPeriod = new ArrayList<TrailBalanceDataPeriod>();


    /** Creates new form TrialBalanceReport */
    public TrialBalanceReport() {
        initComponents();

    }

    public void init(AppView app) throws BeanFactoryException {
        m_app = app;
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jTextField1.setEditable(false);
    }

    public void loadReport(String to, String filename, boolean display) {
        try {
            Date edate = getSecondDate(to);
            enddate = edate;
            LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
            Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
            GeneralSettingInfo sinfo = gs.get("Datestart");
            GeneralSettingInfo einfo = gs.get("Dateend");
            Date fsdate = (Date) Formats.TIMESTAMP.parseValue(sinfo.getValue());
            Date fedate = (Date) Formats.TIMESTAMP.parseValue(einfo.getValue());
            Calendar fscal = Calendar.getInstance();//financial year sdate
            fscal.setTime(fsdate);
            Calendar fecal = Calendar.getInstance();//financial year edate
            fecal.setTime(fedate);
            Calendar rscal = Calendar.getInstance();//report start date
            Calendar rscal1 = Calendar.getInstance();
            Calendar recal = Calendar.getInstance();//report end date
            Calendar recal1 = Calendar.getInstance();//report end date
            recal.setTimeInMillis(edate.getTime());
            rscal1.setTimeInMillis(rscal.getTimeInMillis());
            rscal1.add(Calendar.DATE, -1);
            recal1.setTimeInMillis(recal.getTimeInMillis());
            recal1.add(Calendar.DATE, -1);

            acclist = new ArrayList<TrailBalanceData>();
            Date d = new Date();
            Date d1 = (Date) Formats.TIMESTAMP.parseValue(jTextField1.getText());
            String[] temp = d.toString().split(" ");
            String[] temp1 = d1.toString().split(" ");
            acclist = getStatement(edate);
            DataSourceProvider data1 = new DataSourceProvider();
            DataSourceforTrailBalance ds = null;
            if (jRadioButton1.isSelected()) {
                List<TrailBalanceData> li = new ArrayList<TrailBalanceData>();
                for (int i = 0; i < acclist.size(); i++) {
                    double cr = acclist.get(i).getcredit();
                    double dr = acclist.get(i).getdebit();
                    if (cr > 0.0 || dr > 0.0) {
                        li.add(acclist.get(i));
                    }
                }
                ds = new DataSourceforTrailBalance(li);
                data1.setDataSource(ds);
            } else {
                //DataSourceProvider data1=new DataSourceProvider(acclist);
                ds = new DataSourceforTrailBalance(acclist);
                data1.setDataSource(ds);
            }
            Map reportparam = new HashMap();
            reportparam.put("enddate", edate);
            JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/TrailBalanceNew.jrxml", reportparam, false, data1, display, filename);

            w.hideDialog();

        } catch (Exception e) {
            e.printStackTrace();
            w.hideDialog();
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        }
    }
public void loadReportforPeriod(String from,String to, String filename, boolean display) {
        try {
            Date edate = getSecondDate(to);
            enddate = edate;
            Date fdate = getDate(from);
            fromdate=fdate ;
            LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
            Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
            GeneralSettingInfo sinfo = gs.get("Datestart");
            GeneralSettingInfo einfo = gs.get("Dateend");
            Date fsdate = (Date) Formats.TIMESTAMP.parseValue(sinfo.getValue());
            Date fedate = (Date) Formats.TIMESTAMP.parseValue(einfo.getValue());
            Calendar fscal = Calendar.getInstance();//financial year sdate
            fscal.setTime(fsdate);
            Calendar fecal = Calendar.getInstance();//financial year edate
            fecal.setTime(fedate);
            Calendar rscal = Calendar.getInstance();//report start date
            Calendar rscal1 = Calendar.getInstance();
            Calendar recal = Calendar.getInstance();//report end date
            Calendar recal1 = Calendar.getInstance();//report end date
            recal.setTimeInMillis(edate.getTime());
            rscal1.setTimeInMillis(rscal.getTimeInMillis());
            rscal1.add(Calendar.DATE, -1);
            recal1.setTimeInMillis(recal.getTimeInMillis());
            recal1.add(Calendar.DATE, -1);

            acclistPeriod = new ArrayList<TrailBalanceDataPeriod>();
            Date d = new Date();
            Date d1 = (Date) Formats.TIMESTAMP.parseValue(jTextField1.getText());
            String[] temp = d.toString().split(" ");
            String[] temp1 = d1.toString().split(" ");
            acclistPeriod = getStatementPeriod(fdate,edate);
            DataSourceProvider data1 = new DataSourceProvider();
            DataSourceforTrailBalancePeriod ds = null;
           
                //DataSourceProvider data1=new DataSourceProvider(acclist);
                ds = new DataSourceforTrailBalancePeriod(acclistPeriod);
                data1.setDataSource(ds);
            
            Map reportparam = new HashMap();
            reportparam.put("enddate", edate);
             reportparam.put("fromdate", fdate);
            JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/TrailBalanceNewPeriod.jrxml", reportparam, false, data1, display, filename);

            w.hideDialog();

        } catch (Exception e) {
            e.printStackTrace();
            w.hideDialog();
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        }
    }
    private Date getSecondDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        cal.set(Calendar.AM_PM, Calendar.PM);
        d.setTime(cal.getTimeInMillis());
        return d;
    }
    private Date getDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 01);
        // cal.set(Calendar.AM_PM, Calendar.PM);
        d.setTime(cal.getTimeInMillis());
        return d;
    }

    private List<TrailBalanceData> getStatement(Date edate) throws BasicException {

//        String statement = "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DAMOUNT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CAMOUNT,AM.SUMMARY AS SUMMARY FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID AND AJ.EDATE<=? " +
//                "GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.SUMMARY ORDER BY AM.SEARCHKEY";
String statement = "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DAMOUNT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CAMOUNT,AM.SUMMARY AS SUMMARY FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID AND AJ.EDATE<=? " +
                "GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.SUMMARY,AM.ACTIVE HAVING ACTIVE=1 ORDER BY AM.SEARCHKEY";
        return createTrailBalanceReport2(statement, edate);
    }

    private List<TrailBalanceData> createTrailBalanceReport2(String statement, Date fedate) throws BasicException {//before there was sent
        //  gfhf

        List<TrailBalanceData> acclistele = new StaticSentence(m_app.getSession(), " SELECT  NAME,SEARCHKEY, CAMOUNT,DAMOUNT,PARENT,LEVEL_,SUMMARY " +
                " FROM ( " + statement +
                ")as trailbal ORDER BY 5,1 ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), new SerializerReadClass(TrailBalanceData.class)).list(new Object[]{fedate});
        //, new SerializerReadClass(TrailBalanceData.class)).list();
        List<TrailBalanceData> list = new ArrayList<TrailBalanceData>();

        list.addAll(acclistele);
        Map<String, TrailBalanceData> tacclist = new HashMap<String, TrailBalanceData>();
        int i = 0;
        for (TrailBalanceData t : acclistele) {


//              if(t.getSearchkey().startsWith("1.2")){
//                 System.out.println("Name : "+t.getName()+" :: Debit : "+t.getdebit()+" :: Credit : "+t.getcredit());
//              }

            if (t.getParent() != null) {
                if (t.getcredit() > 0 || t.getdebit() > 0) {
                    
                   
                    int flag = 0;
                    int countCheck = 1;
                    double Cvalue = 0.0;
                    double Dvalue = 0.00;
                    do {
                        TrailBalanceData t1 = tacclist.get(t.getParent());//parent
//                    System.out.println("--- > "+t.getParent());
//                   if(t1.getName().equals("Current Assets")){
//                    String asas="aasas";
//                   }
                        tacclist.remove(t1);
                        if (t1 != null) {
                            int pindex = 0, cnt = 0, nindex = 0;
                            if (flag == 0) {
                                list.remove(t);
                                pindex = list.indexOf(t1);

                                if (t1.getcount() == 0) {
                                    cnt = 1;
                                } else {
                                    cnt = t1.getcount() + 1;
                                }
                                nindex = pindex + cnt;
                            } else {
                                nindex = list.indexOf(t);
                                list.remove(t);
                                pindex = list.indexOf(t1);
                                cnt = t1.getcount();
                            }
                            flag = 1;
                            if (t.getcredit() > 0) {
                                if (countCheck == 1) {
                                    Cvalue = t.getcredit();
                                    
                                }
                                // t1.setcredit(t.getcredit());
                                t1.setcredit(Cvalue);
                            }
                            if (t.getdebit() > 0) {
                                if (countCheck == 1) {
                                    Dvalue = t.getdebit();
                                    
                                }
                                // t1.setdebit(t.getdebit());
                                t1.setdebit(Dvalue);
                            }
                            t1.setCount(cnt);
                            list.remove(pindex);
                            list.add(pindex, t1);
                            list.add(nindex, t);
                            tacclist.put(t.getSearchkey(), t);
                            tacclist.put(t1.getSearchkey(), t1);
                            if (t.getSearchkey().startsWith("1.2")) {
                                System.out.println("Name : " + t.getName() + "::Searchkey :" + t.getSearchkey() + " :: Debit : " + t.getdebit() + " :: Credit : " + t.getcredit() +" :: "+ t.getLevel1());
                                System.out.println("Name : " + t1.getName() + "::Searchkey :" + t1.getSearchkey() + " :: Debit : " + t1.getdebit() + " :: Credit : " + t1.getcredit()+" :: "+ t.getLevel1());
                            }
                        }
                     System.out.println("------------------");
                      System.out.println(t1.getName());
                      System.out.println(t1.getcount());
                      System.out.println(t1.getSearchkey());
                        t = new TrailBalanceData();

                        t = t1;
                        countCheck++;

                    } while (t.getParent() != null);


                } else {
                    System.out.println(t.getParent().toString());
                    TrailBalanceData t1 = tacclist.get(t.getParent());//parent
                    tacclist.remove(t1);
                    //Arun
                    String te = t1.getName();
                    String te1 = t.getName();
                   System.out.println(te);
                   System.out.println(te1);
                    double a = t1.getcredit();
                    double b = t1.getdebit();
                    double a1 = t.getcredit();
                    double b1 = t.getdebit();
                    list.remove(t);
                    int pindex = list.indexOf(t1);
                    int cnt = t1.getcount() + 1;
                    t1.setCount(cnt);
                    list.remove(pindex);

                    list.add(pindex, t1);
                    list.add(pindex + cnt, t);
                    tacclist.put(t.getSearchkey(), t);
                    tacclist.put(t1.getSearchkey(), t1);
                }
            //    tacclist.put(t.getSearchkey(), t);
            } else {
                tacclist.put(t.getSearchkey(), t);
            }
            t = new TrailBalanceData();
        }


        return list;
    }
    
    //////////////////////////////////////////////////////////////added by pratima
     private List<TrailBalanceDataPeriod> getStatementPeriod(Date fdate,Date edate) throws BasicException {

       
 List<TrailBalanceDataPeriod > acclistele = new StaticSentence(m_app.getSession(), "SELECT  trailbal.NAME,trailbal.SEARCHKEY, trailbal.CAMOUNT,trailbal.DAMOUNT,trailbal.PARENT,trailbal.LEVEL_,trailbal.SUMMARY,trailbal1.camount,trailbal1.damount,trailbal2.camount,trailbal2.damount\n" +
"                 FROM ( SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DAMOUNT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CAMOUNT,AM.SUMMARY AS SUMMARY,AM.ACTIVE AS ACTIVE FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID AND AJ.EDATE<?\n" +
"               GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.SUMMARY ORDER BY AM.PARENT,AM.NAME)as trailbal\n" +
"join ( SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DAMOUNT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CAMOUNT,AM.SUMMARY AS SUMMARY FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID AND AJ.EDATE>=? and AJ.EDATE<=?\n" +
"                GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.SUMMARY ORDER BY  AM.PARENT,AM.NAME)as trailbal1 on trailbal.id=trailbal1.id\n" +
"    join\n" +
"( SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DAMOUNT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CAMOUNT,AM.SUMMARY AS SUMMARY FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID AND AJ.EDATE<=?\n" +
"                GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.SUMMARY ORDER BY  AM.PARENT,AM.NAME)as trailbal2 on trailbal.id=trailbal2.id where trailbal.level_='S' and trailbal.active=1"
                , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadClass(TrailBalanceDataPeriod .class)).list(new Object[]{fdate,fdate,edate,edate});
       
    if(jRadioButton2.isSelected()){
        for (int i=0;i<acclistele.size();i++){
         TrailBalanceDataPeriod trailBalObj =  acclistele.get(i);
            if((trailBalObj.getdebit_f()==0.00)&&(trailBalObj.getcredit_f()==0.00)&&(trailBalObj.getdebit_d()==0.00 )&&(trailBalObj.getcredit_d()==0.00 )&&(trailBalObj.getdebit_t()==0.00 )&&(trailBalObj.getcredit_t()==0.00)){
            acclistele.remove(i);
            i--;
            }
        }
    }
        return acclistele;
    }

    
    /////////////////////////////////////////////////////////////////////////////end by pratima

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        jLabel1.setText("To Date");
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jButton1.setText("To Date");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Report");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Zero balance ignore");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Period Wise Report");
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel2.setText("From Date");
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jButton3.setText("FromDate");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Zero Balance and Zero Transaction ignore ");
        jRadioButton2.setName("jRadioButton2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jCheckBox1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jRadioButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(210, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(jTextField1.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            jTextField1.setText(Formats.DATE.formatValue(date));
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        /////////////////////////////////////////////////////pratima:periodwise
    if(jCheckBox1.isSelected()==true){ 
        if ((jTextField2.getText().isEmpty() == true)){
            JOptionPane.showMessageDialog(null, "Please Enter From Date");
        }else{
            if(jTextField1.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(null, "Please Enter End Date");
            }else{  w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);

            Thread t = new Thread(
                    new Runnable() {

                        public void run() {
                            try {

                                loadReportforPeriod(jTextField2.getText(),jTextField1.getText(), null, true);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            //JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
                            }
                        }
                    });
            t.start();
            w.showDialog("Please wait.Loading Report...");
            }
        }
        ///////////////////////////////////////////////////////
    }else{
        if (jTextField1.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please Enter End Date");
        } else if (jTextField1.getText().isEmpty() == false) {
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

                                loadReport(jTextField1.getText(), null, true);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            //JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
                            }
                        }
                    });
            t.start();
            w.showDialog("Please wait.Loading Report...");
        }
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
         jRadioButton1.setSelected(false);
         jRadioButton2.setSelected(false);
        if(jCheckBox1.isSelected()){
        jTextField2.setVisible(true);
        jLabel2.setVisible(true);
         jButton3.setVisible(true);
         jRadioButton1.setVisible(false);
         jRadioButton2.setVisible(true);
        }
        else{
            jTextField2.setText(null);
            jTextField2.setVisible(false);
            jLabel2.setVisible(false);
            jButton3.setVisible(false);
            jRadioButton1.setVisible(true);
            jRadioButton2.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(jTextField1.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            jTextField2.setText(Formats.DATE.formatValue(date));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Trial Balance";
    }

    public void activate() throws BasicException {
        acclist = new ArrayList<TrailBalanceData>();
        jCheckBox1.setSelected(false);
        jTextField2.setVisible(false);
        jButton3.setVisible(false);
        jLabel2.setVisible(false);
        jRadioButton2.setVisible(false);
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
}
