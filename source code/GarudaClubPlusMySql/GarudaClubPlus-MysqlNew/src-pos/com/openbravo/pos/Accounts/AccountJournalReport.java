/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AccountJournalReport.java
 *
 * Created on 25-May-2010, 13:20:56
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
public class AccountJournalReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_app;
    private DataLogicFacilities dlfac;
    private waitDialog w;
     private List<AccountJournalData> acclist=new ArrayList<AccountJournalData>();
    private Date startdate;
    private Date enddate;
    /** Creates new form AccountJournalReport */
    public AccountJournalReport() {
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

              acclist=new ArrayList<AccountJournalData>();
                Date d=new Date();
                Date d1=(Date)Formats.TIMESTAMP.parseValue(jTextField2.getText());
                String[] temp=d.toString().split(" ");
                String[] temp1=d1.toString().split(" ");
               
                  acclist=getStatement(sdate,edate);
                 DataSourceProvider data1=new DataSourceProvider(acclist);
                 DataSourceForAccountJournal ds=new DataSourceForAccountJournal(acclist);
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
   public static class AccountJournalData implements SerializableRead{
         private String name;
         private Double credit;
         private Double debit;
         private String narration;
         private String transno;
         private Timestamp date;
         private double amount;
         private String transtype;
         private String tid;
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
          tid=dr.getString(7);
       }
       public Timestamp getDate(){
         return date;
       }
       public String getTransactionId(){
          return tid;
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
    private List<AccountJournalData> getStatement(Date sdate,Date edate) throws BasicException{
       String statement=null,sent=null;
      statement="select aj.date as date,aj.transno as transno,aj.amount as amount,aj.narration as narration,am.name as name,aj.transtype as transtype,aj.tid as tid from accountjournal aj,accountmaster am " +
              "where aj.accountid=am.id and aj.active=true and aj.transref='Journal' and aj.date>=? and aj.date<=? order by aj.date,aj.transno,aj.tid";
      return createAccountJournalReport(statement,sdate,edate);
    }

     private List<AccountJournalData> createAccountJournalReport(String statement,Date sdate,Date edate) throws BasicException{//before there was sent
       //  gfhf

          List<AccountJournalData> acclistele= new StaticSentence(m_app.getSession()
                           , " SELECT  DATE,TRANSNO,AMOUNT,NARRATION,NAME,TRANSTYPE,TID"+
                                " FROM ( "+statement +
                                ")as ajr ORDER BY 1,2"
                           ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                           ,new SerializerReadClass(AccountJournalData.class)).list(new Object[]{sdate,edate});
                           //, new SerializerReadClass(TrailBalanceData.class)).list();
            List<AccountJournalData> list=new ArrayList<AccountJournalData>();

            list.addAll(acclistele);
            Map<String,AccountJournalData> tacclist=new HashMap<String, AccountJournalData>();
            int i=0;
          for(AccountJournalData t:acclistele){
          }
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

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

        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jLabel1.setText("From");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("To");
        jLabel2.setName("jLabel2"); // NOI18N

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jButton3)
                .addContainerGap(155, Short.MAX_VALUE))
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
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addContainerGap(144, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
     private  JRField[] getFields() throws JRException, UnsupportedOperationException {
         JRField[]  fields = new JRField[6];
         fields[0] = (JRField)new JRBasicField("date", "date", java.util.Date.class, "java.util.Date");
		 fields[1] = (JRField)new JRBasicField("transno", "transno", java.lang.String.class, "java.lang.String");
         fields[2] = (JRField)new JRBasicField("name", "name", java.lang.String.class, "java.lang.String");
         fields[3] = (JRField)new JRBasicField("narration", "narration", java.lang.String.class, "java.lang.String");
         fields[4] = (JRField)new JRBasicField("debit", "debit", java.lang.Double.class, "java.lang.Double");
         fields[5] = (JRField)new JRBasicField("credit", "credit", java.lang.Double.class, "java.lang.Double");
           return fields;
	}
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

    public String getTitle() {
        return "Account Journal Report";
    }

    public void activate() throws BasicException {
       
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
