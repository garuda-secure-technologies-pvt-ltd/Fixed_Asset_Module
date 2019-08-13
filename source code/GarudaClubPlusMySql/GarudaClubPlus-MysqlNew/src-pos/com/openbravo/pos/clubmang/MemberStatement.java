/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MemberStatement.java
 *
 * Created on 29-Sep-2011, 15:26:18
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.AltEncrypter;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterContext;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



/**
 *
 * @author swathi
 */
public class MemberStatement extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicFacilities dmang;
    private DataLogicFacilities dmang1;
    private DataLogicFacilities dmang2;
    private DataLogicSales m_dlSales;
    private MemberStatementModel dbmodel;
    private MemberStatementModel1 dbmodel1;
    private MemberStatementModel2 dbmodel2;
    private MemberStatementModel3 dbmodel3;
    private MemberStatementModel4 dbmodel4;
    private Date datefm;
    private Date dateto;
    private ComboBoxValModel memmodel; 
    private Date datefm1;
    private Date dateto1;
   
    
    String monthlist[] = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
 

    /** Creates new form MemberStatement */
    public MemberStatement() {
        initComponents();
    }
     

    public void generateReport(String memtype) throws BasicException, ParseException {
        //Date d=getDate(TOOL_TIP_TEXT_KEY,month.getSelectedIndex(), TOOL_TIP_TEXT_KEY);
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }
//        reportparams.put("memno", memno.getText().toUpperCase());
//        reportparams.put("membername", mname.getText().toString());
//        reportparams.put("memAddress", dbmodel.getAddress());
                reportparams.put("header", header);
//        reportparams.put("pdues", dbmodel.getPrevDues());
//        reportparams.put("pbal", dbmodel.getPrevBalance());
//        reportparams.put("totaldues", dbmodel.getPrevDues() + dbmodel.getdebtAmount());
//        reportparams.put("amountreceivable", dbmodel.getAmountReceivable());
//        reportparams.put("amountpayable", dbmodel.getPrevDues() + dbmodel.getdebtAmount() - dbmodel.getPrevBalance() - dbmodel.getAmountReceivable());
                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                
                
                reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                
                
                if (memtype == null) {
                    String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                   
                    
                    dbmodel = MemberStatementModel.loadInstance(m_App, customerInfo.getId(), accid, dmang, datefm, dateto, memno.getText().toUpperCase(),Flag);
                    
                  
                    
                    
                    
                } else {
                    if (memtype.equals("0000")) {
                        dbmodel = MemberStatementModel.loadInstanceforAllMembers(m_App, dmang, datefm, dateto,Flag);
                    } else {
                        dbmodel = MemberStatementModel.loadInstance(m_App, dmang, datefm, dateto, memtype,Flag);
                    }
                }
                DataSourceProvider data1 = new DataSourceProvider(dbmodel.getfacilityline());
                DataSourceForMemberStatement ds1 = new DataSourceForMemberStatement(dbmodel.getfacilityline());
                data1.setDataSource(ds1);
                if(monthlySelect.isSelected())
                {
                 if(ReportWithNoteOnly==0){    
                     JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null);
                 }
                 else{
                     JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, true, null);
                 }
                
               if(monthlySelect.isSelected()){  
                      if(all_Radiobtn.isSelected()){
                       
                    int x = JOptionPane.showConfirmDialog(jPanel1, "Do you want report for members not having any trasaction ??? ");
                     if(x==JOptionPane.YES_OPTION){
                             getReport2ForCust();
                                    }
                      }
                    }
                
                }
                else
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriod.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriodWithNoteOnly.jrxml", reportparams, false, data1, true, null); 
                    }
                }
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
            e.printStackTrace();
        }
    }
    
    
    
    
    
    public void generateReport2(String memtype) throws BasicException, ParseException {
        //Date d=getDate(TOOL_TIP_TEXT_KEY,month.getSelectedIndex(), TOOL_TIP_TEXT_KEY);
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }
//        reportparams.put("memno", memno.getText().toUpperCase());
//        reportparams.put("membername", mname.getText().toString());
//        reportparams.put("memAddress", dbmodel.getAddress());
                reportparams.put("header", header);
//        reportparams.put("pdues", dbmodel.getPrevDues());
//        reportparams.put("pbal", dbmodel.getPrevBalance());
//        reportparams.put("totaldues", dbmodel.getPrevDues() + dbmodel.getdebtAmount());
//        reportparams.put("amountreceivable", dbmodel.getAmountReceivable());
//        reportparams.put("amountpayable", dbmodel.getPrevDues() + dbmodel.getdebtAmount() - dbmodel.getPrevBalance() - dbmodel.getAmountReceivable());
                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                
                reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                
                
                if (memtype == null) {
                    String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                    dbmodel1 = MemberStatementModel1.loadInstance(m_App, customerInfo.getId(), accid, dmang, datefm, dateto, memno.getText().toUpperCase(),Flag);
                } else {
                    if (memtype.equals("0000")) {
                        dbmodel1 = MemberStatementModel1.loadInstanceforAllMembers(m_App, dmang, datefm, dateto,Flag);
                    } else {
                        dbmodel1 = MemberStatementModel1.loadInstance(m_App, dmang, datefm, dateto, memtype,Flag);
                    }
                }
                DataSourceProvider data1 = new DataSourceProvider(dbmodel1.getfacilityline());
                DataSourceForMemberStatement2 ds1 = new DataSourceForMemberStatement2(dbmodel1.getfacilityline());
                data1.setDataSource(ds1);
                
            
                
                
                
                if(monthlySelect.isSelected())
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, true, null);
                    }
                }
                else
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriod.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriodWithNoteOnly.jrxml", reportparams, false, data1, true, null); 
                    }
                }
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
            e.printStackTrace();
        }
    }
    
    
    
   
    public void generateReport3(String memtype) throws BasicException, ParseException {
        //Date d=getDate(TOOL_TIP_TEXT_KEY,month.getSelectedIndex(), TOOL_TIP_TEXT_KEY);
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }
//        reportparams.put("memno", memno.getText().toUpperCase());
//        reportparams.put("membername", mname.getText().toString());
//        reportparams.put("memAddress", dbmodel.getAddress());
                reportparams.put("header", header);
//        reportparams.put("pdues", dbmodel.getPrevDues());
//        reportparams.put("pbal", dbmodel.getPrevBalance());
//        reportparams.put("totaldues", dbmodel.getPrevDues() + dbmodel.getdebtAmount());
//        reportparams.put("amountreceivable", dbmodel.getAmountReceivable());
//        reportparams.put("amountpayable", dbmodel.getPrevDues() + dbmodel.getdebtAmount() - dbmodel.getPrevBalance() - dbmodel.getAmountReceivable());
                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                
                if (memtype == null) {
                    String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                    dbmodel2 = MemberStatementModel2.loadInstance(m_App, customerInfo.getId(), accid, dmang, datefm, dateto, memno.getText().toUpperCase(),Flag);
                } else {
                    if (memtype.equals("0000")) {
                        dbmodel2 = MemberStatementModel2.loadInstanceforAllMembers(m_App, dmang, datefm, dateto,Flag );
                    } else {
                        dbmodel2 = MemberStatementModel2.loadInstance(m_App, dmang, datefm, dateto, memtype,Flag);
                    }
                }
                DataSourceProvider data1 = new DataSourceProvider(dbmodel2.getfacilityline());
                DataSourceForMemberStatement3 ds1 = new DataSourceForMemberStatement3(dbmodel2.getfacilityline());
                data1.setDataSource(ds1);
              
                if(monthlySelect.isSelected())
                {
                    
                    if(ReportWithNoteOnly==0){
                    
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null);
                    String memno=customerInfo.getSearchkey();
                
                    JasperExportManager.exportReportToPdfFile(jp, "/home/dev3/PDF/"+memno+".pdf"); 
                    int x = JOptionPane.showConfirmDialog(jPanel1, "Do you want to Email member statement to Member ??? " , "Email Option Menu" , JOptionPane.YES_NO_OPTION);
                    if(x==JOptionPane.YES_OPTION){
                       EmailToMember(memno);
                    }
                
                    }
                    else{
                     JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, true, null);   
                     String memno=customerInfo.getSearchkey();
                
                    JasperExportManager.exportReportToPdfFile(jp, "/home/dev3/PDF/"+memno+".pdf"); 
                    int x = JOptionPane.showConfirmDialog(jPanel1, "Do you want to Email member statement to Member ??? " , "Email Optio------------------- Menu" , JOptionPane.YES_NO_OPTION);
                    if(x==JOptionPane.YES_OPTION){
                       EmailToMember(memno);
                    }   
                        
                    }
              
                
               if(monthlySelect.isSelected()){  
                      if(all_Radiobtn.isSelected()){
                       
                    int x123 = JOptionPane.showConfirmDialog(jPanel1, "Do you want report for members not having any trasaction ??? ");
                     if(x123==JOptionPane.YES_OPTION){
                             getReport3ForCust();
                                    }
                      }
                    }
                
                }
                
               
                else
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriod.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriodWithNoteOnly.jrxml", reportparams, false, data1, true, null);
                    }
                }
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
            e.printStackTrace();
        }
    }
    
    
    
    
     public void generateReport4(String memtype) throws BasicException, ParseException {
        //Date d=getDate(TOOL_TIP_TEXT_KEY,month.getSelectedIndex(), TOOL_TIP_TEXT_KEY);
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                
                reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }
//        reportparams.put("memno", memno.getText().toUpperCase());
//        reportparams.put("membername", mname.getText().toString());
//        reportparams.put("memAddress", dbmodel.getAddress());
                reportparams.put("header", header);
//        reportparams.put("pdues", dbmodel.getPrevDues());
//        reportparams.put("pbal", dbmodel.getPrevBalance());
//        reportparams.put("totaldues", dbmodel.getPrevDues() + dbmodel.getdebtAmount());
//        reportparams.put("amountreceivable", dbmodel.getAmountReceivable());
//        reportparams.put("amountpayable", dbmodel.getPrevDues() + dbmodel.getdebtAmount() - dbmodel.getPrevBalance() - dbmodel.getAmountReceivable());
                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                if (memtype == null) {
                    String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                    dbmodel3 = MemberStatementModel3.loadInstance(m_App, customerInfo.getId(), accid, dmang, datefm, dateto, memno.getText().toUpperCase() , Flag);
                } else {
                    if (memtype.equals("0000")) {
                        dbmodel3 = MemberStatementModel3.loadInstanceforAllMembers(m_App, dmang, datefm, dateto,Flag);
                    } else {
                        dbmodel3 = MemberStatementModel3.loadInstance(m_App, dmang, datefm, dateto, memtype , Flag);
                    }
                }
                DataSourceProvider data1 = new DataSourceProvider(dbmodel3.getfacilityline());
                DataSourceForMemberStatement4 ds1 = new DataSourceForMemberStatement4(dbmodel3.getfacilityline());
                data1.setDataSource(ds1);
                
            
                
                
                
                if(monthlySelect.isSelected())
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, true, null);   
                    }
                }
                else
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriod.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriodWithNoteOnly.jrxml", reportparams, false, data1, true, null);
                    }
                }
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
            e.printStackTrace();
        }
    }
    
    
    
      
    
   
  
    public void generateReport5(String memtype) throws BasicException, ParseException {
        //Date d=getDate(TOOL_TIP_TEXT_KEY,month.getSelectedIndex(), TOOL_TIP_TEXT_KEY);
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }
//        reportparams.put("memno", memno.getText().toUpperCase());
//        reportparams.put("membername", mname.getText().toString());
//        reportparams.put("memAddress", dbmodel.getAddress());
                reportparams.put("header", header);
//        reportparams.put("pdues", dbmodel.getPrevDues());
//        reportparams.put("pbal", dbmodel.getPrevBalance());
//        reportparams.put("totaldues", dbmodel.getPrevDues() + dbmodel.getdebtAmount());
//        reportparams.put("amountreceivable", dbmodel.getAmountReceivable());
//        reportparams.put("amountpayable", dbmodel.getPrevDues() + dbmodel.getdebtAmount() - dbmodel.getPrevBalance() - dbmodel.getAmountReceivable());
                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                 reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                
                if (memtype == null) {
                    String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                   dbmodel4 = MemberStatementModel4.loadInstance(m_App, customerInfo.getId(), accid, dmang, datefm, dateto, memno.getText().toUpperCase());          
                } else {
                    if (memtype.equals("0000")) {
                        dbmodel4 = MemberStatementModel4.loadInstanceforAllMembers(m_App, dmang, datefm, dateto);
                    } else {
                        dbmodel4 = MemberStatementModel4.loadInstance(m_App, dmang, datefm, dateto, memtype);
                    }
                }
                DataSourceProvider data1 = new DataSourceProvider(dbmodel4.getfacilityline());
                DataSourceForMemberStatement5 ds1 = new DataSourceForMemberStatement5(dbmodel4.getfacilityline());
                data1.setDataSource(ds1);
                if(monthlySelect.isSelected())
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, true, null);   
                    }
               
                
               if(monthlySelect.isSelected()){  
                      if(all_Radiobtn.isSelected()){
                       
                    int x = JOptionPane.showConfirmDialog(jPanel1, "Do you want report for members not having any trasaction ??? ");
                     if(x==JOptionPane.YES_OPTION){
                             getReport2ForCust();
                                    }
                      }
                    }
                
                }
                else
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriod.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriodWithNoteOnly.jrxml", reportparams, false, data1, true, null);
                    }
                }
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
            e.printStackTrace();
        }
    }
    
    
    public void getReport2ForCust() throws BasicException 
    {
       try {
            if (individiual_radioBtn.isSelected()) {
                
                
                if (memno.getText().length() > 0 && mname.getText().length() > 0 && (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
                    generateReport2(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
            } else if (all_Radiobtn.isSelected()) {
                MemCat mem = (MemCat) jComboBox1.getSelectedItem();
                generateReport2(mem.getID());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
    
    
      public void getReport3ForCust() throws BasicException 
    {
       try {
            if (individiual_radioBtn.isSelected()) {
                
                
                if (memno.getText().length() > 0 && mname.getText().length() > 0 && (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
                    generateReport4(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
            } else if (all_Radiobtn.isSelected()) {
                MemCat mem = (MemCat) jComboBox1.getSelectedItem();
                generateReport4(mem.getID());

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        all_Radiobtn = new javax.swing.JRadioButton();
        individiual_radioBtn = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Days = new javax.swing.JLabel();
        days = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        months = new javax.swing.JTextField();
        message = new javax.swing.JLabel();
        monthWise = new javax.swing.JPanel();
        fromdate = new javax.swing.JTextField();
        fdate = new javax.swing.JButton();
        todate = new javax.swing.JTextField();
        tdate = new javax.swing.JButton();
        PeriodWise = new javax.swing.JPanel();
        fromdate1 = new javax.swing.JTextField();
        todate1 = new javax.swing.JTextField();
        fdate1 = new javax.swing.JButton();
        tdate1 = new javax.swing.JButton();
        monthlySelect = new javax.swing.JRadioButton();
        periodSelect = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        memno = new javax.swing.JTextField();
        mname = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        sendMail_button = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        inactive_Checkbox = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ob_zero_check = new javax.swing.JCheckBox();

        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 448));

        all_Radiobtn.setText("All");
        all_Radiobtn.setName("AllRbttn"); // NOI18N
        all_Radiobtn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                all_RadiobtnStateChanged(evt);
            }
        });
        all_Radiobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                all_RadiobtnActionPerformed(evt);
            }
        });

        individiual_radioBtn.setText("Individual");
        individiual_radioBtn.setName("individiual_radioBtn"); // NOI18N
        individiual_radioBtn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                individiual_radioBtnStateChanged(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setName("note"); // NOI18N
        jScrollPane2.setViewportView(jTextArea1);

        jLabel4.setText("Note");
        jLabel4.setName("jLabel4"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel5.setText("Due Period");
        jLabel5.setName("jLabel5"); // NOI18N

        Days.setText("Days");
        Days.setName("Days"); // NOI18N

        days.setName("days"); // NOI18N

        jLabel6.setText("Months");
        jLabel6.setName("jLabel6"); // NOI18N

        months.setName("months"); // NOI18N

        message.setText("** Enter months and days field in numbers**");
        message.setName("message"); // NOI18N

        monthWise.setName("monthWise"); // NOI18N

        fromdate.setName("fromdate"); // NOI18N

        fdate.setText("Month");
        fdate.setName("fdate"); // NOI18N
        fdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdateActionPerformed(evt);
            }
        });

        todate.setName("todate"); // NOI18N

        tdate.setText("To Date");
        tdate.setName("tdate"); // NOI18N
        tdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout monthWiseLayout = new javax.swing.GroupLayout(monthWise);
        monthWise.setLayout(monthWiseLayout);
        monthWiseLayout.setHorizontalGroup(
            monthWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthWiseLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(monthWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(todate)
                    .addComponent(fromdate, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(monthWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fdate)
                    .addComponent(tdate))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        monthWiseLayout.setVerticalGroup(
            monthWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthWiseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(monthWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(monthWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PeriodWise.setName("PeriodWise"); // NOI18N

        fromdate1.setName("fromdate1"); // NOI18N

        todate1.setName("todate1"); // NOI18N

        fdate1.setText("From Date");
        fdate1.setName("fdate1"); // NOI18N
        fdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdate1ActionPerformed(evt);
            }
        });

        tdate1.setText("To Date");
        tdate1.setName("tdate1"); // NOI18N
        tdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PeriodWiseLayout = new javax.swing.GroupLayout(PeriodWise);
        PeriodWise.setLayout(PeriodWiseLayout);
        PeriodWiseLayout.setHorizontalGroup(
            PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PeriodWiseLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(todate1)
                    .addComponent(fromdate1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fdate1))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PeriodWiseLayout.setVerticalGroup(
            PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PeriodWiseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fdate1)
                        .addComponent(fromdate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PeriodWiseLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(PeriodWiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(todate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tdate1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        monthlySelect.setText("Month");
        monthlySelect.setName("monthlySelect"); // NOI18N
        monthlySelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthlySelectItemStateChanged(evt);
            }
        });
        monthlySelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlySelectActionPerformed(evt);
            }
        });

        periodSelect.setText("Period");
        periodSelect.setName("periodSelect"); // NOI18N
        periodSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                periodSelectItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monthlySelect)
                            .addComponent(periodSelect))
                        .addGap(28, 28, 28)
                        .addComponent(monthWise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PeriodWise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Days, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(message)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(monthlySelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(periodSelect)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PeriodWise, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(monthWise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Days, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        monthWise.setVisible(true);
        PeriodWise.setVisible(false);
        monthlySelect.setSelected(true);
        periodSelect.setSelected(false);

        jPanel3.setName("jPanel3"); // NOI18N

        memno.setName("memno"); // NOI18N
        memno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memnoKeyPressed(evt);
            }
        });

        mname.setName("mname"); // NOI18N
        mname.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                mnameComponentAdded(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Member Name");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel1.setText("Member No.");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(209, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel4.setName("jPanel4"); // NOI18N

        jLabel3.setText("Member Type");
        jLabel3.setName("mtypelbl"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

        jButton3.setText("Generate");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(69, 69, 69)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 51, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sendMail_button.setText("Send Mail ");
        sendMail_button.setName("sendMail_button"); // NOI18N
        sendMail_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMail_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(sendMail_button, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(sendMail_button)))
                .addGap(41, 41, 41))
        );

        jLabel7.setText("Statement Required for ");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("Member(s)");
        jLabel8.setName("jLabel8"); // NOI18N

        inactive_Checkbox.setName("inactive_Checkbox"); // NOI18N
        inactive_Checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inactive_CheckboxActionPerformed(evt);
            }
        });

        jLabel9.setText("Want To Display In Active Members ");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setForeground(new java.awt.Color(153, 0, 0));
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText("Want to display for member having zero opening balance for the period");
        jLabel12.setName("jLabel12"); // NOI18N

        ob_zero_check.setName("ob_zero_check"); // NOI18N
        ob_zero_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ob_zero_checkItemStateChanged(evt);
            }
        });
        ob_zero_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ob_zero_checkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ob_zero_check))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(all_Radiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(individiual_radioBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(inactive_Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(individiual_radioBtn)
                    .addComponent(all_Radiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(inactive_Checkbox, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ob_zero_check, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        try {
            if (inactive_Checkbox.isSelected()) 
            {
             
                
                if (individiual_radioBtn.isSelected()) {
                if (memno.getText().length() > 0 && mname.getText().length() > 0 && (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
                    generateReport3(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
               } else if (all_Radiobtn.isSelected()) {
                MemCat mem = (MemCat) jComboBox1.getSelectedItem();
                generateReport3(mem.getID());

                 }
                
            }
           
             else
             {
               if (individiual_radioBtn.isSelected()) {
                
                
                if (memno.getText().length() > 0 && mname.getText().length() > 0 && (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
                    generateReport(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
               } else if (all_Radiobtn.isSelected()) {
                MemCat mem = (MemCat) jComboBox1.getSelectedItem();
                generateReport(mem.getID());

             }
          } 
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname.setText(customerInfo.toString());
                memno.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void memnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memnoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    mname.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mname.setText(null);
            customerInfo = null;

        }
    }//GEN-LAST:event_memnoKeyPressed

    private void tdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdateActionPerformed
        // TODO add your handling code here:
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(todate.getText());

        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            todate.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_tdateActionPerformed

    private void fdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdateActionPerformed
        // TODO add your handling code here:
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(fromdate.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate.setText(Formats.DATE.formatValue(date));
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_fdateActionPerformed

    private void all_RadiobtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_all_RadiobtnStateChanged
        // TODO add your handling code here:
        if (all_Radiobtn.isSelected()) {
            individiual_radioBtn.setSelected(false);
            all_Radiobtn.setSelected(true);
            jPanel1.setVisible(true);
            jPanel2.setVisible(true);
            memno.setVisible(false);
            mname.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            jButton2.setVisible(false);
            jLabel3.setVisible(true);
            jComboBox1.setVisible(true);
            
            sendMail_button.setVisible(true);
          
        }
        else{
             sendMail_button.setVisible(false);
        }

    }//GEN-LAST:event_all_RadiobtnStateChanged

    private void individiual_radioBtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_individiual_radioBtnStateChanged
        // TODO add your handling code here:
        if (individiual_radioBtn.isSelected()) {
            all_Radiobtn.setSelected(false);
            individiual_radioBtn.setSelected(true);
            jPanel1.setVisible(true);
            jPanel2.setVisible(true);
            memno.setVisible(true);
            mname.setVisible(true);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            jButton2.setVisible(true);
            jLabel3.setVisible(false);
            jComboBox1.setVisible(false); 
         
        }
    }//GEN-LAST:event_individiual_radioBtnStateChanged

    private void fdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdate1ActionPerformed
       
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(fromdate1.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(date.getTime());
//            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
//            date.setTime(cal.getTimeInMillis());
            fromdate1.setText(Formats.DATE.formatValue(date));
//            Calendar cal1 = Calendar.getInstance();
//            cal1.setTimeInMillis(date.getTime());
//            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//            date.setTime(cal1.getTimeInMillis());
//            todate.setText(Formats.DATE.formatValue(date));
        }
        
    }//GEN-LAST:event_fdate1ActionPerformed

    private void tdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdate1ActionPerformed
        
        Date date;

        try {
            date = (Date) Formats.DATE.parseValue(todate1.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(date.getTime());
//            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
//            date.setTime(cal.getTimeInMillis());
            todate1.setText(Formats.DATE.formatValue(date));
//            Calendar cal1 = Calendar.getInstance();
//            cal1.setTimeInMillis(date.getTime());
//            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//            date.setTime(cal1.getTimeInMillis());
//            todate.setText(Formats.DATE.formatValue(date));
        }
        
        
    }//GEN-LAST:event_tdate1ActionPerformed

    private void monthlySelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlySelectActionPerformed
        
        
        
    }//GEN-LAST:event_monthlySelectActionPerformed

    private void monthlySelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthlySelectItemStateChanged
       
        if(monthlySelect.isSelected())
        {
            monthWise.setVisible(true);
            PeriodWise.setVisible(false);
            fromdate1.setText(null);
            todate1.setText(null);
        }
        else
        {
            monthWise.setVisible(false);
            PeriodWise.setVisible(true);
            fromdate.setText(null);
            todate.setText(null);
        }
        
        
    }//GEN-LAST:event_monthlySelectItemStateChanged

    private void periodSelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodSelectItemStateChanged
       
        if(!periodSelect.isSelected())
        {
            monthWise.setVisible(true);
            PeriodWise.setVisible(false);
            fromdate1.setText(null);
            todate1.setText(null);
            
        }
        else
        {
            monthWise.setVisible(false);
            PeriodWise.setVisible(true);
            fromdate.setText(null);
            todate.setText(null);
        }
        
    }//GEN-LAST:event_periodSelectItemStateChanged

    private void mnameComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_mnameComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_mnameComponentAdded

    private void inactive_CheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inactive_CheckboxActionPerformed
  
        if(inactive_Checkbox.isSelected()){
            String caution="Caution:In case any member has transactions during the period but is inactive";
            String  caution1= "when taking this report that member statement will not get displayed.";
            jLabel10.setText(caution);
            jLabel11.setText(caution1);
            jLabel10.setVisible(true);
            jLabel11.setVisible(true);
         
        }
            else
        {
          jLabel10.setVisible(false);
          jLabel11.setVisible(false);
        }
        
    }//GEN-LAST:event_inactive_CheckboxActionPerformed

    private void all_RadiobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_all_RadiobtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_all_RadiobtnActionPerformed

    private void ob_zero_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ob_zero_checkActionPerformed
        
     
    }//GEN-LAST:event_ob_zero_checkActionPerformed
    int Flag;
    private void ob_zero_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ob_zero_checkItemStateChanged
        
        if(ob_zero_check.isSelected()){
            Flag=1;
            
        }
        else{
            Flag=0;
        }
        
    }//GEN-LAST:event_ob_zero_checkItemStateChanged

   int Memactive_flag ;
    
   
   
   // EDITED BY AAKASH---------------------------------------------------------
   
    private void sendMail_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMail_buttonActionPerformed
        
        
        
        
        if(inactive_Checkbox.isSelected()){
           Memactive_flag = 0;
        }
        else{
           Memactive_flag=1; 
        }
        
        if(jComboBox1.getSelectedIndex()!=-1){
            
            String MemtypeName = jComboBox1.getSelectedItem().toString();
            
            
            
            
            if(MemtypeName.equals("All"))
            {
               
                
               MemberStatementEmail memList;
                try {
                    memList = MemberStatementEmail.getDialog(this, m_App,true , MemtypeName );
                    memList.showDialog();
                } catch (BasicException ex) {
                    Logger.getLogger(MemberStatementEmail.class.getName()).log(Level.SEVERE, null, ex);
                }  
                
                
                
                
                
                
                
            }
            else{
                
                
                MemberStatementEmail memList;
                try {
                    memList = MemberStatementEmail.getDialog(this, m_App,false , MemtypeName);
                    memList.showDialog();
                } catch (BasicException ex) {
                    Logger.getLogger(MemberStatementEmail.class.getName()).log(Level.SEVERE, null, ex);
                }  
                
                
                
                
                
                
                
                
            }
            
            
            
            
            
            
            
            
            
        }
        else{
           JOptionPane.showMessageDialog(this, "Select Member Type ", "incomplete form", JOptionPane.WARNING_MESSAGE);  
            
        }
        
        
        
    
        
        
        
        
        
        
        
    }//GEN-LAST:event_sendMail_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Days;
    private javax.swing.JPanel PeriodWise;
    private javax.swing.JRadioButton all_Radiobtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField days;
    private javax.swing.JButton fdate;
    private javax.swing.JButton fdate1;
    private javax.swing.JTextField fromdate;
    private javax.swing.JTextField fromdate1;
    private javax.swing.JCheckBox inactive_Checkbox;
    private javax.swing.JRadioButton individiual_radioBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField memno;
    private javax.swing.JLabel message;
    private javax.swing.JTextField mname;
    private javax.swing.JPanel monthWise;
    private javax.swing.JRadioButton monthlySelect;
    private javax.swing.JTextField months;
    private javax.swing.JCheckBox ob_zero_check;
    private javax.swing.JRadioButton periodSelect;
    private javax.swing.JButton sendMail_button;
    private javax.swing.JButton tdate;
    private javax.swing.JButton tdate1;
    private javax.swing.JTextField todate;
    private javax.swing.JTextField todate1;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Member's Statement";
    }
    int ReportWithNoteOnly=0;
    
    
    public void activate() throws BasicException {
        fromdate.setEnabled(false);
        todate.setEnabled(false);
        tdate.setVisible(false);
        reset();
        
        Image ig = null;
        
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CONTENT FROM RESOURCES where NAME='Window.ClubLogo'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.IMAGE})).find();
        if(obj!=null){
            if(obj[0]!=null){
               ig=(Image) obj[0]; 
               
               BufferedImage buffered = (BufferedImage) ig;
               ImageIcon icon = new ImageIcon(ig); 
               File outputfile = new File("out.jpg");
               try{
                   ImageIO.write(buffered, "JPEG", new File("./reports/com/openbravo/reports/ClubLogo.JPG"));
               }
               catch(IOException e){
                                
               }
               
            }
        }
        
        
        Object[] objMemStatNote = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='MemberStatement Report with only Note'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                    if(objMemStatNote!=null){ 
                        Boolean v21 = (Boolean)objMemStatNote[0];
                        if(v21){
                            ReportWithNoteOnly=1;
                        }
                        else{
                             ReportWithNoteOnly=0;
                             
                        }
                    }
                    else{
                        ReportWithNoteOnly=0;
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
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        memmodel = new ComboBoxValModel();
        groupButton();
    }

    public Object getBean() {
        return this;
    }

    public void reset() throws BasicException {
        jTextArea1.setText(null);
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        memno.setVisible(false);
        mname.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jButton2.setVisible(false);
        jLabel3.setVisible(false);
        jComboBox1.setVisible(false);
        all_Radiobtn.setSelected(false);
        individiual_radioBtn.setSelected(false);
       
        fromdate.setText(null);
        todate.setText(null);
        memno.setText(null);
        mname.setText(null);
        List<MemCat> mlist = dmang.getMemberCategory();
        MemCat m = new MemCat();
        m.setName("All");
        m.setId("0000");
        mlist.add(0, m);
        memmodel = new ComboBoxValModel(mlist);
        jComboBox1.setModel(memmodel);

    }

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
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(monthlySelect);
        bg1.add(periodSelect);
      
    }
     
     
     
     
    // Email to member ........................ 
     
     
     public void EmailToMember(String memno) throws BasicException{
         
      
       
       
                           
                            
                            String Folder_Path = getFolderPath(m_App);    
       
                            final String username = getEmailIdUserName(m_App);
                            final String password = getEmailPassword(m_App, username);
                            
                            String SendTo = getEmailIdCustomers(m_App, memno);
                            
                            if(SendTo!=null && SendTo.trim().length()>0 ){
                                
                              
                            String EmailAccount = null;
                
                            try{
                                 EmailAccount = getMailAccountByUsername(username);
                            }
                            catch(Exception e){
                                 e.printStackTrace();
                                 new MessageInf(e).show(new JFrame());
                            }    

                           
                            
                            String mail_message = "Please find member statement from attached file ";
                            String Subject = m_App.getSession().getCompanyName() + " - Member Statememt";

                            Properties props = new Properties();
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.enable", "true");
                            
                            
                            if(EmailAccount.equals("Yahoo Account")){
                                  props.put("mail.smtp.host", "smtp.mail.yahoo.com"); 
                            }
                            if(EmailAccount.equals("Gmail Account")){
                                  props.put("mail.smtp.host", "smtp.gmail.com");
                            }
                            
                            
                            
                            props.put("mail.smtp.port", "587");

                            Session session = Session.getInstance(props,
                              new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                            return new PasswordAuthentication(username, password);
                                    }
                              });

                            try {

                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress(username));
                                    message.setRecipients(Message.RecipientType.TO,
                                            InternetAddress.parse(SendTo));
                                    message.setSubject(Subject);
                                    message.setText(mail_message);

                                    BodyPart messageBodyPart = new MimeBodyPart();

                                    messageBodyPart.setText(mail_message);

                                    Multipart multipart = new MimeMultipart();

                                    multipart.addBodyPart(messageBodyPart);

                                    messageBodyPart = new MimeBodyPart();

                                    String x11 = Folder_Path+memno+".pdf" ;

                                    DataSource source = new FileDataSource(x11);

                                    messageBodyPart.setDataHandler(new DataHandler(source));

                                    messageBodyPart.setFileName(memno);

                                    multipart.addBodyPart(messageBodyPart);
                                    message.setContent(multipart);


                                    Transport.send(message);

                                   // JOptionPane.showMessageDialog(this, "Mail sent successfully!! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    
                                    
                                    int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO sentemail (ID ,MEMNO , EMAILID , DATE , CRBY , CRHOST  , SUBJECT , SENTID ) VALUES (?,?,?,?,?,?,?,?)"                           
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.TIMESTAMP, Datas.STRING ,Datas.STRING  , Datas.STRING , Datas.STRING })                         
                                    ).exec(new Object[]{UUID.randomUUID().toString(), memno ,SendTo , new Date() ,    m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost()  , Subject , username });                                                                                                

                                    
                                    

                                     } catch (MessagingException e) {

                                    e.printStackTrace();
                                    new MessageInf(e).show(new JFrame());
                                    throw new RuntimeException(e);

                                  }
                            
                            }
                            
                            
                            
                            else{
                                
                                
                                JOptionPane.showMessageDialog(this, "Member's Email Id is not Registered.  \n\n  ", "Warning", JOptionPane.WARNING_MESSAGE); 
                                
                            }
         
         
         
         
     }
     
     
     
     
      //Email Password
        public String getEmailIdUserName(AppView app ) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable WHERE NAME='Mem Stat Email Account' ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find();
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }   
     
     
        
        
        
        //Email Password
        public String getEmailPassword(AppView app , String username) throws BasicException{
          Object o = null;
          String Pass = null;
          String Decr_Password = null;
          
           o  = new StaticSentence(app.getSession(), "SELECT PASSWORD FROM email_master  WHERE active=1 AND USERNAME=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(username);
          
          if(o!=null){
              Pass = o.toString();
              Decr_Password=new AltEncrypter("key").decrypt(Pass);
              
          } 
          else{
              Pass = null;
          }
           
           
          return Decr_Password;
      }    
        
        
        // GET PATH FOR FOLDER .........................................................   
        
        
       //Email Password
        public String getFolderPath(AppView app ) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable WHERE NAME='Member Stat PDF Folder Path' ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find();
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }    
        
        
        
    // GET EMAIL ID FROM CUSTOMERS .........................................
        
        
         
        public String getEmailIdCustomers(AppView app , String memno) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT EMAIL FROM CUSTOMERS  WHERE SEARCHKEY=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(memno);
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }   
     
     
        
        
        
        
     // MemberStatement For member to EMAIL
     // CODE EDITED BY AAKASH--------------
        
        
    public void generateReportForEmail(String CustID , String AccID , String MemNo , int GenerateReportFlag){
        
        
         
        try {
          //  if (inactive_Checkbox.isSelected()) 
          //  {
             
                
           
                if ( (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
                    GenerateReport3_Email(CustID , AccID , MemNo , GenerateReportFlag);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
                
              
                
                
           // }
           
       //      else
       //      {
              // if (individiual_radioBtn.isSelected()) {
                
                
               // if ( (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
               //     generateReport(null);
               // } else {
               //     JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
              //  }
              // } else if (all_Radiobtn.isSelected()) {
               // MemCat mem = (MemCat) jComboBox1.getSelectedItem();
              //  generateReport(mem.getID());

            // }
        //  } 
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
        
        
        
    
    
    
    public void GenerateReport3_Email(String CustID , String AccID , String MemNo , int GenerateReportFlag)  throws BasicException, ParseException {
        
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }

                
                
                reportparams.put("header", header);

                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                 reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                 //String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                 dbmodel2 = MemberStatementModel2.loadInstance(m_App, CustID, AccID, dmang, datefm, dateto, MemNo ,Flag);
                
                
                
                DataSourceProvider data1 = new DataSourceProvider(dbmodel2.getfacilityline());
                DataSourceForMemberStatement3 ds1 = new DataSourceForMemberStatement3(dbmodel2.getfacilityline());
                data1.setDataSource(ds1);
              
                if(monthlySelect.isSelected())
                {
                    
                JasperPrint jp = null;    
                    
                if(GenerateReportFlag==1){
                    if(ReportWithNoteOnly==0){
                         jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, true, null); 
                    }
                    else{
                         jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, true, null);   
                    }
                    
                }     
                else{
                    if(ReportWithNoteOnly==0){
                        jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, false, null); 
                    }
                    else{
                        jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, false, null);   
                    }
                }   
                    
               
                
               // String memno=customerInfo.getSearchkey();
                
                JasperExportManager.exportReportToPdfFile(jp, "/home/dev3/PDF/"+MemNo+".pdf"); 
                
                
                
                
                
                int x12 = JOptionPane.showConfirmDialog(jPanel1, "Do you want to Email member statement to Member ??? " , "Email Option Menu" , JOptionPane.YES_NO_OPTION);
                     
                  
                
                if(x12==JOptionPane.YES_OPTION){
                            
                          
                       EmailToMember(MemNo);
                       
                       
                       
                     }
                
                 
              
                
               if(monthlySelect.isSelected()){  
                      
                   
                    }
                
                }
                
               
                else
                {
                    if(ReportWithNoteOnly==0){
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriod.jrxml", reportparams, false, data1, true, null);
                    }
                    else{
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementForPeriodWithNoteOnly.jrxml", reportparams, false, data1, true, null);
                    }
                }
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
             e.printStackTrace();
             new MessageInf(e).show(new JFrame());
        }
        
        
        
        
    }
        
        
        
        
      
    
    
   public void generateReportForEmail_Multiple(List<String> MemnoList){
        
        
         
        try {
            if (inactive_Checkbox.isSelected()) 
            {
             
                
           
                if ( (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
                    GenerateReport3_Email_multiple(MemnoList);
                } else {
                    JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
                }
                
              
                
                
            }
           
             else
             {
              // if (individiual_radioBtn.isSelected()) {
                
                
               // if ( (fromdate.getText().length()  > 0 || fromdate1.getText().length()  > 0)&& ( todate.getText().length() > 0 || todate1.getText().length() > 0)) {
               //     generateReport(null);
               // } else {
               //     JOptionPane.showMessageDialog(this, "Form is incomplete", "incomplete form", JOptionPane.WARNING_MESSAGE);
              //  }
              // } else if (all_Radiobtn.isSelected()) {
               // MemCat mem = (MemCat) jComboBox1.getSelectedItem();
              //  generateReport(mem.getID());

            // }
          } 
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
        
        
        
        
      public void GenerateReport3_Email_multiple(List<String> MemnoList)  throws BasicException, ParseException {
        
        
        datefm = null;
        dateto = null;
        String mon = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String iYear = null;
        String header = null;
        final Date duedate = new Date();
        int month = 0;
        
        if(monthlySelect.isSelected())
        {
        datefm = getDate(fromdate.getText());
        dateto = getSecondDate(todate.getText());
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        date = (Date) Formats.DATE.parseValue(todate.getText());
         iYear = sdf.format(date);
         header = "We have pleasure in submitting the statement of bill/s for the month of " + mon + " " + iYear + " for favour of an early payment.";
        
        }
        else if(periodSelect.isSelected())
        {
        datefm = getDate(fromdate1.getText());
        dateto = getSecondDate(todate1.getText());
        
        Calendar cal2=Calendar.getInstance();
            Calendar cal3=Calendar.getInstance();
            cal2.setTime(datefm);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            cal2.set(Calendar.MINUTE, 59);
            cal2.set(Calendar.SECOND, 59);
            cal2.set(Calendar.MILLISECOND, 59);
            cal3.setTime(dateto);
            cal3.set(Calendar.HOUR_OF_DAY, 23);
            cal3.set(Calendar.MINUTE, 59);
            cal3.set(Calendar.SECOND, 59);
            cal3.set(Calendar.MILLISECOND, 59);
        
        mon = monthlist[datefm.getMonth()]; //+" "+ datefm.getYear();
        String mon2 = monthlist[dateto.getMonth()];
        Date date1 = (Date) Formats.DATE.parseValue(fromdate1.getText());
        date = (Date) Formats.DATE.parseValue(todate1.getText());
         iYear = sdf.format(date1);
         String iYear2 = sdf.format(date);
         SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MM-yyyy");
         String Fdate = sdfFrom.format(datefm);
         String Tdate = sdfFrom.format(dateto);
         
         
         header = "We have pleasure in submitting the statement of bill/s for the period of " + Fdate + " to "  + Tdate + " for favour of an early payment.";
         
        // header = "We have pleasure in submitting the statement of bill/s for the period of " + mon + " " + iYear + " to "  + mon2 + " " + iYear2 + " for favour of an early payment.";
        
            
        }
    int day = 0;
        try {
            if (months.getText().length() > 0 || days.getText().length() > 0) {
                if (months.getText().length() > 0) {
                    month = Integer.parseInt(months.getText().toString());
                }
                if (days.getText().length() > 0) {
                    day = Integer.parseInt(days.getText().toString());
                }

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(dateto);
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if(month>0){
                cal1.add(Calendar.MONTH, month);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                }
                cal1.add(Calendar.DATE, day);
                Date ss = cal1.getTime();
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                String telandemail = "";
                try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='telephone'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='email'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj2!=null)
                {
                    telandemail = telandemail+"Tel: "+ obj2[0].toString()+",  ";
                }
                if(obj3!=null)
                {
                    
                    telandemail = telandemail+"E-mail: "+ obj3[0].toString();
                }
                
                reportparams.put("telandemail",telandemail);
                }
                catch(Exception e)
                {
                    
                }

                
                
                reportparams.put("header", header);

                reportparams.put("duedate", cal1.getTime());
                reportparams.put("note", jTextArea1.getText().toString());
                 reportparams.put("CLUBICON","./reports/com/openbravo/reports/ClubLogo.JPG");
                 //String accid = dmang.getCustomerAccountByID(customerInfo.getId());
                
               
                
                for(int i=0;i<MemnoList.size();i++){
                    
                  String  MemberNo =  MemnoList.get(i).toString();
                  String  CustID  = getCustomerID(m_App, MemberNo);
                  String  AccountId =  getCustAccountID(m_App, MemberNo);
                    
                  dbmodel2 = MemberStatementModel2.loadInstance(m_App, CustID, AccountId, dmang, datefm, dateto, MemberNo ,Flag);
                
                
                
                DataSourceProvider data1 = new DataSourceProvider(dbmodel2.getfacilityline());
                DataSourceForMemberStatement3 ds1 = new DataSourceForMemberStatement3(dbmodel2.getfacilityline());
                data1.setDataSource(ds1);
              
                if(monthlySelect.isSelected())
                {
                    
                JasperPrint jp = null;    
                    
                if(ReportWithNoteOnly==0){
                      jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatement.jrxml", reportparams, false, data1, false, null); 
                }
                else{
                     jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberStatementWithNoteOnly.jrxml", reportparams, false, data1, false, null);   
                }
                    
               
                    
               
                
                // String memno=customerInfo.getSearchkey();
                String Pathdest = getFolderPath(m_App);
                JasperExportManager.exportReportToPdfFile(jp, Pathdest+MemberNo+".pdf"); 
                
                EmailToMember(MemberNo);
                       
                       
                       
                     
                  
                  
                  
                  
                    
                  }
                
                }
                
                
                
                
                 
              
                
               if(monthlySelect.isSelected()){  
                      
                   
                    }
                
                
                
               
               
                }else{
                JOptionPane.showMessageDialog(this, "Specify due period");
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(this, "Enter months and days field in numbers");
           // e.printStackTrace();
             e.printStackTrace();
             new MessageInf(e).show(new JFrame());
        }
        
        
        
        
    }  
        
        
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    // DATA FOR MEMBER------------------------------------------------ 
      
       public String getCustomerID(AppView app , String Memno ) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Memno);
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }   
      public String getCustAccountID(AppView app , String Memno ) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE SEARCHKEY=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Memno);
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }   
     
      
      
      
      public String  getMailAccountByUsername(String UserName) throws BasicException{
      
       Object o = new Object(); 
        o=new PreparedSentence(m_App.getSession(), "SELECT EMAILACCOUNT FROM email_master WHERE USERNAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(UserName);
       if(o!=null){
            return o.toString();
       }
       else{
          return null; 
       }
        
        
     }
      
      
      
      
     
}
