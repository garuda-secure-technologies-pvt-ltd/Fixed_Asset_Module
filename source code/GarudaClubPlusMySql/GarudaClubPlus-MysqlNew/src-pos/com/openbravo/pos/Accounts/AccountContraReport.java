/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AccountContraReport.java
 *
 * Created on 29-May-2010, 12:08:38
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceForAccountContra;
import com.openbravo.pos.clubmang.DataSourceForAccountJournal;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.DataSourceforTrailBalance;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JRBasicField;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
public class AccountContraReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_app;
    private DataLogicFacilities dlfac;
    private waitDialog w;
     private List<AccountContraData> acclist=new ArrayList<AccountContraData>();
    private Date startdate;
    private Date enddate;
    /** Creates new form AccountJournalReport */
    public AccountContraReport() {
        initComponents();
    }
 public void init(AppView app) throws BeanFactoryException {
     m_app=app;
     dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
    }
      public void loadReport(String from ,String to,String filename,boolean display){
        try{
          Date  sdate=new Date();
          try{
             sdate= getDate(from);
          }catch(Exception e){
            e.printStackTrace();
          }
          Date edate=getSecondDate(to);
          startdate=sdate;
          enddate=edate;
          LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
          Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
          GeneralSettingInfo sinfo=gs.get("Datestart");
          GeneralSettingInfo einfo=gs.get("Dateend");
          Date fsdate=(Date)Formats.DATE.parseValue(sinfo.getValue());
          Date fedate=(Date)Formats.DATE.parseValue(einfo.getValue());
          Calendar fscal=Calendar.getInstance();//financial year sdate
          fscal.setTime(fsdate);
          Calendar fecal=Calendar.getInstance();//financial year edate
          fecal.setTime(fedate);
          Calendar rscal=Calendar.getInstance();//report start date
          Calendar rscal1=Calendar.getInstance();
          Calendar recal=Calendar.getInstance();//report end date
          Calendar recal1=Calendar.getInstance();//report end date
          rscal.setTimeInMillis(sdate.getTime());
          recal.setTimeInMillis(edate.getTime());
          rscal1.setTimeInMillis(rscal.getTimeInMillis());
          rscal1.add(Calendar.DATE, -1);
          recal1.setTimeInMillis(recal.getTimeInMillis());
          recal1.add(Calendar.DATE, -1);

              acclist=new ArrayList<AccountContraData>();
                Date d=new Date();
                Date d1=(Date)Formats.TIMESTAMP.parseValue(jTextField2.getText());
                String[] temp=d.toString().split(" ");
                String[] temp1=d1.toString().split(" ");

                  acclist=getStatement(sdate,edate);
                 DataSourceProvider data1=new DataSourceProvider(acclist);
                 DataSourceForAccountContra ds=new DataSourceForAccountContra(acclist);
                 data1.setDataSource(ds);
                 Map reportparam=new HashMap();
                 reportparam.put("startdate",sdate);
                 reportparam.put("enddate",edate);
                JasperPrint jp=JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/AccountJournal.jrxml", reportparam, false,data1,display,filename);
                w.hideDialog();
        }catch(Exception e){
          e.printStackTrace();
          w.hideDialog();
          MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
          msg.show(this);
        }
     }
 private Date getDate(String date) throws BasicException{
       Date d=(Date)Formats.TIMESTAMP.parseValue(date);

       Calendar cal=GregorianCalendar.getInstance();
       cal.setTimeInMillis(d.getTime());
       cal.set(Calendar.HOUR_OF_DAY, 00);
       cal.set(Calendar.MINUTE, 00);
       cal.set(Calendar.SECOND, 00);
       cal.set(Calendar.MILLISECOND, 00);
       // cal.set(Calendar.AM_PM, Calendar.PM);
       d.setTime(cal.getTimeInMillis());
       return d;
    }
  private Date getSecondDate(String date) throws BasicException{
       Date d=(Date)Formats.TIMESTAMP.parseValue(date);
       Calendar cal=GregorianCalendar.getInstance();
       cal.setTimeInMillis(d.getTime());
       cal.set(Calendar.HOUR_OF_DAY, 23);
       cal.set(Calendar.MINUTE, 59);
       cal.set(Calendar.SECOND, 59);
       cal.set(Calendar.MILLISECOND, 59);
      // cal.set(Calendar.AM_PM, Calendar.PM);
       d.setTime(cal.getTimeInMillis());       return d;
    }

    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void activate() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deactivate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JComponent getComponent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getBean() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   public static class AccountContraData implements SerializableRead{
         private String name;
         private Double credit;
         private Double debit;
         private String narration;
         private String transno;
         private Timestamp date;
         private double amount;
         private String transtype;
       public void readValues(DataRead dr) throws BasicException
       {
         // id=dr.getString(1);
          date=dr.getTimestamp(1);
          transno=dr.getString(2);
          amount=dr.getDouble(3);
          narration=dr.getString(4);
          name=dr.getString(5);
          try{
          transtype=dr.getString(6);
          if(transtype.equals("D"))
          {
              debit=amount;
              credit=0.0;
          }
          else if(transtype.equals("C")){
              credit=amount;
              debit=0.0;
          }
          }catch(Exception e){

          }
       }
       public Timestamp getDate(){
         return date;
       }

       public String getTransno(){
         return transno;
       }
       public String getNarration(){
         return narration;
       }
       public Double getcredit(){
           if(credit==null)
               return 0.0;
           else
               return credit;
       }
       public Double getdebit(){
           if(debit==null)
               return 0.0;
           else
              return debit;
       }
      public  String getName(){
         return name;
       }
       public Double getAmount(){
         return amount;
       }
       public String getTranstype(){
           return transtype;
       }


     }
    private List<AccountContraData> getStatement(Date sdate,Date edate) throws BasicException{
       String statement=null,sent=null;
      statement="select aj.date as date,aj.transno as transno,aj.amount as amount,aj.narration as narration,am.name as name,aj.transtype as transtype from accountjournal aj,accountmaster am " +
              "where aj.accountid=am.id and aj.transref='Contra' and aj.date>=? and aj.date<=? order by aj.date,aj.transno";
      return createAccountJournalReport(statement,sdate,edate);
    }

     private List<AccountContraData> createAccountJournalReport(String statement,Date sdate,Date edate) throws BasicException{//before there was sent
       //  gfhf

          List<AccountContraData> acclistele= new StaticSentence(m_app.getSession()
                           , " SELECT  DATE,TRANSNO,AMOUNT,NARRATION,NAME,TRANSTYPE"+
                                " FROM ( "+statement +
                                ") ORDER BY 1,2"
                           ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                           ,new SerializerReadClass(AccountContraData.class)).list(new Object[]{sdate,edate});
                           //, new SerializerReadClass(TrailBalanceData.class)).list();
            List<AccountContraData> list=new ArrayList<AccountContraData>();

            list.addAll(acclistele);
            Map<String,AccountContraData> tacclist=new HashMap<String,AccountContraData>();
            int i=0;
          for(AccountContraData t:acclistele){
          }
              return list;

     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("From");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("To");
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jButton1.setText("Date");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Date");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Report");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jButton3)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(166, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if(jTextField1.getText().isEmpty()==true && jTextField2.getText().isEmpty()==true)
      {
           JOptionPane.showMessageDialog(null, "Please Enter both start an end date");

      }
      else if(jTextField1.getText().isEmpty()==true)
      {
           JOptionPane.showMessageDialog(null, "Please Enter Start Date");
      }
       else if(jTextField2.getText().isEmpty()==true)
      {
           JOptionPane.showMessageDialog(null, "Please Enter End Date");
      }
      else if(jTextField1.getText().isEmpty()==false && jTextField2.getText().isEmpty()==false){
            w=new waitDialog(new JFrame(), true);
        int h=w.getSize().height;
           int w1=w.getSize().width;
           Toolkit toolkit = Toolkit.getDefaultToolkit();
		   Dimension scrnsize = toolkit.getScreenSize();
           w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);

       Thread t=new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{

							loadReport(jTextField1.getText(),jTextField2.getText(),null,true);
						}
						catch (Exception ex){
							ex.printStackTrace();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);
            t.start();
            w.showDialog("Please wait.Loading Report...");
      }

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
