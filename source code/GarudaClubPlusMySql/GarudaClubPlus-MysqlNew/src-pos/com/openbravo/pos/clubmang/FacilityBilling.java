/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacilityBilling.java
 *
 * Created on Mar 16, 2009, 2:14:02 P\
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.inventory.TaxCategoryInfo1;
import com.openbravo.pos.inventory.TaxCategoryInfo2;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.sms.SMSgeneralDBSettings;
import com.openbravo.pos.ticket.TaxInfo;
import java.awt.Color;
import java.awt.Component;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class FacilityBilling extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form FacilityBilling */
    private FacilityBillingTableModel fmodel;
    private AppView m_App;
    private DataLogicFacilities dmang;
    private SentenceList sen;
    private ComboBoxValModel famodel;
    private TicketParser m_TTP;
    private String facility;
    private double rate;
    private double taxrate;
    private double taxrate1;
    private double taxrate2;
    private double taxamt;
    private double taxamt1;
    private double taxamt2;
    private double taxamt3;
    private String taxid;
    private String taxid1;
    private DataLogicSales dlsales;
    //private double frate;
    private String period;
    private String billnum;
    private TaxesLogic taxeslogic;
    private DataLogicSales m_dlSales;
    private String id;
    private String id1;
    private String id2;
    private Boolean cascade1;
    private Boolean cascade2;
    String name=null;
    String name1=null;
    String name2=null;
        
    private SMSgeneralDBSettings smsDBSettings;
    
    public FacilityBilling() {
        initComponents();

    }

   public void init(AppView app) throws BeanFactoryException {
       dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       smsDBSettings = (SMSgeneralDBSettings) app.getBean("com.openbravo.pos.sms.SMSgeneralDBSettings");
       dlsales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       m_App=app;
       jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       
    }

  public Object getBean() {
      return this;
  }

  public JComponent getComponent() {
      return this;
  }
  public String getfacility(){
      return facility;
  }
   public String getPeriod(){
      return period;
  }
  public double getRate(){
      return rate;
  }
  public String getTitle() {
        return null;
  }
  public String getBillNum(){
    return billnum;
  }
  public double gettax(){
    return taxrate;
  }
  public double gettaxValue(){
    return taxamt3;
  }
   public double gettax2(){
    return taxrate1;
  }
//  public double gettaxValue1(){
//    return taxamt1;
//  }
   public double gettax3(){
    return taxrate2;
  }
//  public double gettaxValue2(){
//    return taxamt2;
//  }
//  public double gettax3(){
//    return taxrate3;
//  }
//  public double gettaxValue3(){
//    return taxamt3;
//  }
  public double gettotalrate(){
     return taxamt3+rate;
  }
  public void activate() throws BasicException {
         try{
             taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
            List temp=dmang.getFacility();
            temp.add(0, null);
          
             famodel=new ComboBoxValModel(temp);
             facilityType.setModel(famodel);
//            loadData();
       }
       catch(Exception e){
       }
        dnow=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dnow.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        dnow.setTime(cal.getTimeInMillis());
       loadData();
  }
  private void loadData() throws BasicException {
     //   jSeparator1.setVisible(false);
//       taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
//            List temp=dmang.getFacility();
//               System.out.println("temp:::::::::::::"+temp);
//            temp.add(0, null);
          
//            System.out.println("cascade1:::::"+f.getCascade1()); 
        jTable1.setVisible(false);
        facilityType.setModel(famodel);
        jLabel3.setText("0");
        jLabel5.setText("0");
        jLabel16.setText("0");
        jLabel17.setText("0");
        jLabel15.setText("0");
        jTextField1.setText(null);
        jTextField2.setText(null);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
//        jLabel8.setText(null);
//        jLabel11.setText(null);
//        jLabel12.setText(null);
//        jLabel10.setText(null);
//        jLabel9.setText(null);
//       jLabel3.setText(null);
       tax.setText(" ");
       jLabel14.setText(" ");
       jLabel13.setText(" ");
       trate.setText("0");
       taxvalue.setText("0");
//       jLabel5.setText("0");
    }
     public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }
     private Date dnow;
     private boolean updatememusage(final int i,final String billno,final Facility factemp,final String tid) throws BasicException{
         
         //Added under one transaction.. Lokesh
          Transaction t = new Transaction(m_App.getSession()) {
             @Override
             protected Object transact() throws BasicException {
                 Date bdate=new Date(((Timestamp)fmodel.getTableModel().getValueAt(i,7 )).getTime());
         String memid=fmodel.getTableModel().getValueAt(i,11 ).toString();
         Object userid=fmodel.getTableModel().getValueAt(i,12 );
         if(!billno.equals("")){
             if(userid!=null){
              Object[] params=new Object[]{bdate,dnow,billno,memid,factemp.getid(),userid};
              dmang.UpdateMemberFacilityUsage(params);
             }else{
               Object[] params=new Object[]{bdate,dnow,billno,memid,factemp.getid()};
              dmang.UpdateMemberFacilityUsage1(params);
             }
             createbill(i,billno,factemp,bdate, tid,memid);
         return true;
         }else{
            throw  new BasicException("Define a bill sequence");
         }
                 
             }
         };
         
         t.execute();
         return true;
        
//         Date bdate=new Date(((Timestamp)fmodel.getTableModel().getValueAt(i,7 )).getTime());
//         String memid=fmodel.getTableModel().getValueAt(i,11 ).toString();
//         Object userid=fmodel.getTableModel().getValueAt(i,12 );
//         if(!billno.equals("")){
//             if(userid!=null){
//              Object[] params=new Object[]{bdate,dnow,billno,memid,factemp.getid(),userid};
//              dmang.UpdateMemberFacilityUsage(params);
//             }else{
//               Object[] params=new Object[]{bdate,dnow,billno,memid,factemp.getid()};
//              dmang.UpdateMemberFacilityUsage1(params);
//             }
//             createbill(i,billno,factemp,bdate, tid,memid);
//         return true;
//         }else{
//            JOptionPane.showMessageDialog(this, "Define a bill sequence", "Create bill", JOptionPane.OK_OPTION);
//           return false;
//         }
     }
     
     private void createbill(int index,String billno,Facility fac,Date bdate,String tid,String memid) throws BasicException
     {
        Double totalDebt=0.00;//added by pratima
        Object mobile=fmodel.getTableModel().getValueAt(index, 14);
        Timestamp bt=new Timestamp(bdate.getTime());
        Date duedate=new Date();
        duedate=(Date)fmodel.getTableModel().getValueAt(index,13);
        String customeraccount=fmodel.getTableModel().getValueAt(index,10).toString();
        String amt=fmodel.getTableModel().getValueAt(index,5).toString();
        String user=fmodel.getTableModel().getValueAt(index,1).toString();

        Date sdate=(Date) fmodel.getTableModel().getValueAt(index,16);
        if(sdate==null)
        {
          sdate=(Date)fmodel.getTableModel().getValueAt(index,15);
        }
        
        Calendar cal=Calendar.getInstance();
        //cal.setTimeInMillis(((Date)fmodel.getTableModel().getValueAt(index,3)).getTime());
        cal.setTimeInMillis(sdate.getTime());
        cal.add(Calendar.DATE, 1);
        sdate.setTime(cal.getTimeInMillis());
       
        String to=Formats.DATE.formatValue(bdate);
        String narration="Renewal fees for period :"+Formats.DATE.formatValue(sdate)+" To "+to +" : "+user;
        Double amt1=Double.parseDouble(amt);
        Object[] value=new Object[]{UUID.randomUUID().toString(),tid,memid,dnow,"D",fac.getid(),billno,amt1,duedate,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration,customeraccount,amt1,true};
      
        dmang.insertintoaccjoutnal(value);
        totalsum += amt1;
        dmang.setmemberDebt(memid,fac.getid(),amt1);
        totalDebt=dmang.getmemberDebt(memid,fac.getid());//added by pratima
        
        String smsmsg="Dear Member,\rYour a/c "+fmodel.getTableModel().getValueAt(index, 0) +" with us has been debited by Rs "+dmang.ConvertDoubleToString(amt1)+" for "+fac.getName()+" due on "+Formats.DATE.formatValue(duedate)+" bill no "+billno+".Total debit amount is "+totalDebt+".Thank u for using the facility";
       
        CustomerInfoExt customerInfo = dlsales.loadCustomerExt(memid);
        checkForSMS(billno, dmang.ConvertDoubleToString(amt1), fac.getName(), customerInfo, Formats.DATE.formatValue(duedate));
        
        //if(mobile!=null && mobile.toString().trim().length()==10)
          //  dmang.updatetosendMsg(smsmsg, memid, mobile.toString(),2);
     }
     
     public void checkForSMS(String billNo, String FacilityAmount, String FacilityName, CustomerInfoExt customerInfo, String dueDate)
     {
        boolean sendSMSforActDebit =  smsDBSettings.getSMSvalue(SMSgeneralDBSettings.SMS_FACILITY_ID);
        if(sendSMSforActDebit)
        {
            createSMS(SMSgeneralDBSettings.SMS_FACILITY_ID,billNo,FacilityAmount, FacilityName, customerInfo, dueDate);
        }
     }
  
     public void createSMS(String messageID, String billNo, String FacilityAmount, String FacilityName, CustomerInfoExt customerInfo, String dueDate)
    {
        String smsString = smsDBSettings.getMessage(messageID);
        if(smsString != null)
        {
            
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_BILL_KEY, billNo);
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_DTM_KEY , Formats.TIMESTAMP.formatValue(new Date()));
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_TOT_AMOUNT_KEY , FacilityAmount);
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_FACILITY_KEY , FacilityName);
             
            String x = m_App.getAppUserView().getUser().getRole();
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_ROLE_KEY ,  LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            
            if(customerInfo != null)
            {
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NAME_KEY, customerInfo.getName()); 
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NO_KEY, customerInfo.getSearchkey()); 
            }
            if(smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE) || smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER))
            {
               smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE, "");
               smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER, "");
            }
            if(smsString.contains(SMSgeneralDBSettings.SMS_DUE_DATE_KEY))
            {
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_DUE_DATE_KEY, dueDate);
            }
            if(customerInfo != null && customerInfo.getmobile() != null && customerInfo.getmobile().trim().length() > 0)
            {
                smsDBSettings.insertSMStoActiveMsgTable(smsString, customerInfo.getmobile(), customerInfo.getId());
            }
        }
        
        
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        facilityType = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tax = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        taxvalue = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        trate = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        jLabel1.setText("Facility");

        facilityType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                facilityTypeItemStateChanged(evt);
            }
        });
        facilityType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilityTypeActionPerformed(evt);
            }
        });

        jButton1.setText("Display");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Bill");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mem No", "Mem Name", "Facility Type", "Start Date", "Last Billed date", "Createdby", "Pending Qty", "Rate", "Amount", "Bill"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel3.setText("0");

        jLabel5.setText("0");

        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Std Period");

        jLabel2.setText("Rate");

        jLabel6.setText("Bill No");

        jLabel7.setText("Date Of Billing");

        jTextField1.setEditable(false);

        jTextField2.setEditable(false);

        jLabel8.setText("Tax1");

        jLabel9.setText("Tax Amount");

        taxvalue.setText("0");

        jLabel10.setText("Total");

        trate.setText("0");

        jLabel11.setText("Tax2");

        jLabel12.setText("Tax3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                                .addGap(46, 46, 46))
                                            .addComponent(trate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(111, 111, 111))
                                    .addComponent(facilityType, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton1)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(201, 201, 201)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(taxvalue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(33, 33, 33)))))
                                .addGap(21, 21, 21))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(facilityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(taxvalue)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trate))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      //  jSeparator1.setVisible(true);
        try{
         jTable1.setVisible(true);
         Facility fac=(Facility)facilityType.getSelectedItem();
         if(fac.getramt()>0){
             Periodicity p=dmang.getPerioicitybyid(fac.getrperiod());
             if(p.getqbtype()==false){
              try{
                 fmodel = FacilityBillingTableModel.loadInstance(m_App,fac,dmang);
                 jTable1.setModel(fmodel.getTableModel());
                // jTable1.getModel().addTableModelListener(this);
               }
              catch(Exception e){
                e.printStackTrace();
              }
              jButton2.setEnabled(true);
              jButton2.setVisible(true);
             }else{
                  fmodel = FacilityBillingTableModel.loadInstance1(m_App,fac,dmang,m_dlSales,taxrate,taxrate1,taxrate2,id,id1,id2,taxamt,taxamt1,taxamt2);
                  //////////////////shiv
                  
              
                  /////////////////shiv
             
                 
                 
                 jTable1.setModel(fmodel.getTableModel());
                 jButton2.setEnabled(true);
                 jButton2.setVisible(true);
             }
         }else{
             fmodel = FacilityBillingTableModel.emptyinstance();
             jTable1.setModel(fmodel.getTableModel());
         }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private Double totalsum;
      private Double totalreg;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try
        {
            totalsum=0.0;
            totalreg=0.0;
            String tid=UUID.randomUUID().toString();
            Facility factemp=(Facility)facilityType.getSelectedItem();
            String billno=dmang.getnewbillno(factemp.getid());
            jTextField1.setText(billno);
            billnum=billno;
            int noofrows=  fmodel.getTableModel().getRowCount();
            int j=0;
            String user=null;
            Date sdate = null;
            Date  bdate=null;
            String to=null;
            for(int i=0;i<noofrows;i++)
            {
                // FacilityBillingTableModel.Facilityline fline=fmodel.getfacilityline().get(i);
                boolean selected=(Boolean)fmodel.getTableModel().getValueAt(i,6);
                String amt=fmodel.getTableModel().getValueAt(i,5).toString();
                if(selected==true && !amt.equals("0.0"))
                {
                    updatememusage(i,billno,factemp,tid);
                
                    user=fmodel.getTableModel().getValueAt(i,1).toString();
                    sdate=(Date) fmodel.getTableModel().getValueAt(i,16);
                    bdate=new Date(((Timestamp)fmodel.getTableModel().getValueAt(i,7 )).getTime());
                    to=Formats.DATE.formatValue(bdate);
                    j++;
                }
            }

     
            dmang.updatebillno(factemp.getid());

            double taxamt=0.0;
            double taxamt1=0.0;
            double taxamt2=0.0;
            double taxamtt=0.0;
            double taxamt11=0.0;
            double taxamt21=0.0;
         
            if(factemp.getramt()>0)
            {
                TaxCategoryInfo tinfo=(TaxCategoryInfo)m_dlSales.getTaxCategoryByid(factemp.getservicetax());
                name=tinfo.getName();
                TaxInfo taxinfo1 = taxeslogic.getTaxInfo(tinfo);
                taxamt=taxinfo1.getRate()*factemp.getramt();
                taxamtt=taxamt*j;
          
                if(factemp.getTaxcat_2()!=null)
                {
                    TaxCategoryInfo1 tinfo1=(TaxCategoryInfo1)m_dlSales.getTaxCategoryByid1(factemp.getTaxcat_2());
                    name1=tinfo1.getName();
                    TaxInfo taxinfo2 = taxeslogic.getTaxInfo(tinfo1);
                    if(factemp.getCascade1()==true)
                    {
                        taxamt1=taxinfo2.getRate()*(factemp.getramt()+taxamt);
                        taxamt11=taxamt1*j;
                    }
                    else
                    {
                        taxamt1=taxinfo2.getRate()*factemp.getramt(); 
                        taxamt11=taxamt1*j;
                    }
                }
                if(factemp.getTaxcat_3()!=null)
                {
                    TaxCategoryInfo2 tinfo3=(TaxCategoryInfo2)m_dlSales.getTaxCategoryByid2(factemp.getTaxcat_3());
                    name2=tinfo3.getName();
                    TaxInfo taxinfo3 = taxeslogic.getTaxInfo(tinfo3);
                    if(factemp.getCascade2()==true)
                    {
                        taxamt2=taxinfo3.getRate()*(factemp.getramt()+taxamt+taxamt1);
                        taxamt21=taxamt2*j;
                    }
                    else
                    {
                        taxamt2=taxinfo3.getRate()*factemp.getramt();
                        taxamt21=taxamt2*j;
                    }
                }
            }
            if(factemp.getjamt()>0)
            {
                TaxCategoryInfo tinfo=(TaxCategoryInfo)m_dlSales.getTaxCategoryByid(factemp.getservicetax());
                name=tinfo.getName();
                TaxInfo taxinfo1 = taxeslogic.getTaxInfo(tinfo);
                taxamt=taxinfo1.getRate()*factemp.getjamt();
                taxamtt=taxamt*j;
          
                if(factemp.getTaxcat_2()!=null)
                {
                    TaxCategoryInfo1 tinfo1=(TaxCategoryInfo1)m_dlSales.getTaxCategoryByid1(factemp.getTaxcat_2());
                    name1=tinfo1.getName();
                    TaxInfo taxinfo2 = taxeslogic.getTaxInfo(tinfo1);
                    if(factemp.getCascade1()==true)
                    {
                        taxamt1=taxinfo2.getRate()*(factemp.getjamt()+taxamt);
                        taxamt11=taxamt1*j;
                    }
                    else
                    {
                        taxamt1=taxinfo2.getRate()*factemp.getjamt();
                        taxamt11=taxamt1*j;
                    }
                }
                if(factemp.getTaxcat_3()!=null)
                {
                    TaxCategoryInfo2 tinfo3=(TaxCategoryInfo2)m_dlSales.getTaxCategoryByid2(factemp.getTaxcat_3());
                    name2=tinfo3.getName();
                    TaxInfo taxinfo3 = taxeslogic.getTaxInfo(tinfo3);
                    if(factemp.getCascade2()==true)
                    {
                        taxamt2=taxinfo3.getRate()*(factemp.getjamt()+taxamt+taxamt1);
                        taxamt21=taxamt2*j;
                    }
                    else
                    {
                        taxamt2=taxinfo3.getRate()*factemp.getjamt(); 
                        taxamt21=taxamt2*j;
                    }
                }
            }
       
          
            totalreg=factemp.getramt()*j;
//          
            String servicetaxacc=null;
            String servicetaxacc1=null;
            String servicetaxacc2=null;
            if(name!=null)
            {
              

                Object stacc=new StaticSentence(m_App.getSession()
                               , "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? "
                               , SerializerWriteString.INSTANCE
                               ,SerializerReadString.INSTANCE).find(name);
                 servicetaxacc=stacc.toString();
            }
            if(name1!=null)
            {
            

                Object stacc1=new StaticSentence(m_App.getSession()
                               , "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? "
                               , SerializerWriteString.INSTANCE
                               ,SerializerReadString.INSTANCE).find(name1);
                servicetaxacc1=stacc1.toString();
            }
     
            if(name2!=null)
            {
             

                Object stacc2=new StaticSentence(m_App.getSession()
                               , "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? "
                               , SerializerWriteString.INSTANCE
                               ,SerializerReadString.INSTANCE).find(name2);
                  servicetaxacc2=stacc2.toString();
            }
         

            String narration=Formats.DATE.formatValue(sdate)+" To "+to;
            Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,dnow,"C",factemp.getid(),billno,fmodel.getTotal(),dnow,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),factemp.getName()+" "+"for period : "+" " +narration,factemp.getRenewalacc(),0.0,dnow,true};
            dmang.insertintoaccjoutnal1(value1);



            if(fmodel.getTaxtotal()>0 && servicetaxacc!=null)
            {
                Object[] value2=new Object[]{UUID.randomUUID().toString(),tid,dnow,"C",factemp.getid(),billno,fmodel.getTaxtotal(),dnow,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),name+" "+"on bill number"+" "+billno,servicetaxacc,0.0,dnow,true};
                dmang.insertintoaccjoutnal1(value2);
       
       
            }

            if(fmodel.getTaxtotal1()>0 && servicetaxacc1!=null)
            {
                Object[] value21=new Object[]{UUID.randomUUID().toString(),tid,dnow,"C",factemp.getid(),billno,fmodel.getTaxtotal1(),dnow,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),name1+" "+ "on bill number"+" "+billno,servicetaxacc1,0.0,dnow,true};
                dmang.insertintoaccjoutnal1(value21);
            }

            if(fmodel.getTaxtotal2()>0 && servicetaxacc2!=null)
            {
               Object[] value22=new Object[]{UUID.randomUUID().toString(),tid,dnow,"C",factemp.getid(),billno,fmodel.getTaxtotal2(),dnow,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),name2+" "+"on bill number"+" "+billno,servicetaxacc2,0.0,dnow,true};
               dmang.insertintoaccjoutnal1(value22);
            }

            jTextField2.setText(String.valueOf(dnow));
            jButton2.setVisible(false);
            jButton3.setVisible(true);
        }
        catch(BasicException e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         //TableCellEditor temp=jTable1.getCellEditor(1, 9).;
    }//GEN-LAST:event_jTable1MouseClicked

    private void facilityTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_facilityTypeItemStateChanged
        // TODO add your handling code here:
        tax.setText(" ");
       jLabel14.setText(" ");
       jLabel13.setText(" ");
        try{
        if(facilityType.getSelectedIndex()!=-1){
          taxrate=0;
           taxrate1=0;
            taxrate2=0;
          taxamt=0;
          taxamt1=0;
            taxamt2=0;
//            TaxDetail();
//   Boolean cascade1=facline.getCascade1();
//     Boolean cascade2=facline.getCascade2();
//System.out.println("cascade1:::::::::::"+cascade1);

          Facility fac=(Facility)facilityType.getSelectedItem();
          facility=fac.getName();
          int cnt=Integer.parseInt(dmang.getPendingRequestCountForFacility(fac.getid()).toString());
          boolean flag=true;
          if(cnt>0){
              int temp=JOptionPane.showConfirmDialog(null, "There are "+cnt+" pending request for this facility.Do you want to continue",null, JOptionPane.YES_NO_OPTION);
              if(temp!=JOptionPane.YES_OPTION){
                 flag=false;
              }
          }
          if(flag){
          rate=dmang.roundTwoDecimals(fac.getramt());//fac.getramt().toString();
          TaxCategoryInfo tinfo=(TaxCategoryInfo)m_dlSales.getTaxCategoryByid(fac.getservicetax());
          TaxInfo taxinfo = taxeslogic.getTaxInfo(tinfo);
          taxrate=taxinfo.getRate();
            tax.setText(tinfo.getName());
          
//          tax.setText(dmang.roundTwoDecimals((taxrate*100))+"%");
//      added by shweta
if(fac.getTaxcat_2()!=null){
    taxid=fac.getTaxcat_2();
//              System.out.println("taxid:::"+taxid);
          TaxCategoryInfo1 tinfo1=(TaxCategoryInfo1)m_dlSales.getTaxCategoryByid1(taxid);
            TaxInfo taxinfo1 = taxeslogic.getTaxInfo(tinfo1);
             taxrate1=taxinfo1.getRate();
              id1=taxinfo1.getId();
//            taxid1=fac.getTaxcat_3();
if(fac.getCascade1()==true){
                 jLabel14.setText(tinfo1.getName()+" C");
           }else{
                  jLabel14.setText(tinfo1.getName());
           }
}
//          taxid=fac.getTaxcat_2();
////              System.out.println("taxid:::"+taxid);
//          TaxCategoryInfo1 tinfo1=(TaxCategoryInfo1)m_dlSales.getTaxCategoryByid1(taxid);
//            TaxInfo taxinfo1 = taxeslogic.getTaxInfo(tinfo1);
//             taxrate1=taxinfo1.getRate();
//             
             
             
            
            if(fac.getTaxcat_3()!=null){
                taxid1=fac.getTaxcat_3();
           TaxCategoryInfo2 tinfo2=(TaxCategoryInfo2)m_dlSales.getTaxCategoryByid2(taxid1);
             TaxInfo taxinfo2 = taxeslogic.getTaxInfo(tinfo2);
             taxrate2=taxinfo2.getRate();
               id2=taxinfo2.getId();
               if(fac.getCascade2()==true){
                 jLabel13.setText(tinfo2.getName()+" C");
            }else{
                 jLabel13.setText(tinfo2.getName());
            }
            }
//           tax.setText(dmang.roundTwoDecimals(((taxrate)*100))+"%");
               
//           if(fac.getCascade1()==true){
//                 jLabel14.setText(dmang.roundTwoDecimals(((taxrate1)*100))+"%"+" C");
//           }else{
//                  jLabel14.setText(dmang.roundTwoDecimals(((taxrate1)*100))+"%");
//           }
//         
//            if(fac.getCascade2()==true){
//                 jLabel13.setText(dmang.roundTwoDecimals(((taxrate2)*100))+"%"+" C");
//            }else{
//                 jLabel13.setText(dmang.roundTwoDecimals(((taxrate2)*100))+"%");
//            }
           
//           ended by shweta
//          FacilityDetail fdetail=new FacilityDetail();
//          fdetail.getTaxRateByTaxCatID();
          
          //////////////////////////////////////shiv
             id=taxinfo.getId();
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(taxinfo.getId());
            if(obj[0]==null){
              taxamt=taxrate*rate;   
            }
            if(obj[0]!=null){
                if(obj[0].equals("yes")){
                    taxamt=taxrate*rate; 
                    Object f= new Float(Math.round(taxamt));
                     String st= f.toString();
                         taxamt = new Double(st);
                              
                  }else if(obj[0].equals("yesnearest")){
                      taxamt=taxrate*rate;
                      Object f= new Float(Math.round(taxamt));
                      String st= f.toString();
                         taxamt = new Double(st);
                      
                  }else if(obj[0].equals("yesnext")){
                      taxamt=taxrate*rate;
                      Object f= new Float(Math.round(taxamt)+1);
                      String st= f.toString();
                         taxamt = new Double(st);
                      
                  }else if(obj[0].equals("yesprevious")){
                      taxamt=taxrate*rate;
                      Object f= new Float(Math.round(taxamt)-1);
                      String st= f.toString();
                         taxamt = new Double(st);
                         }
                             else{
                                  taxamt=taxrate*rate;
                                 }
            }
//            boolean value1=fac.getCascade1();
//             boolean value2=fac.getCascade2();
            
          ///////////////////////////////////////shiv
//          added by shweta
//  id1=taxinfo1.getId();
if(fac.getTaxcat_2()!=null){
     
     Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id1);
                if(obj1[0]==null){
                    if(fac.getCascade1()==true){
                          taxamt1=taxrate1*(rate+taxamt); 
                    }
                    else{
                         taxamt1=taxrate1*rate;  
                    }
//              taxamt1=taxrate1*rate;   
            }
            if(obj1[0]!=null){
            if(obj1[0].equals("yes")){
                if(fac.getCascade1()==true){
                          taxamt1=taxrate1*(rate+taxamt); 
                    }
                    else{
                         taxamt1=taxrate1*rate;  
                    }
//                    taxamt1=taxrate1*rate; 
                    Object f= new Float(Math.round(taxamt1));
                     String st= f.toString();
                         taxamt1 = new Double(st);
                              
                  }else if(obj1[0].equals("yesnearest")){
                      if(fac.getCascade1()==true){
                          taxamt1=taxrate1*(rate+taxamt); 
                    }
                    else{
                         taxamt1=taxrate1*rate;  
                    }
//                      taxamt1=taxrate1*rate;
                      Object f= new Float(Math.round(taxamt1));
                      String st= f.toString();
                         taxamt1 = new Double(st);
                      
                  }else if(obj1[0].equals("yesnext")){
                      if(fac.getCascade1()==true){
                          taxamt1=taxrate1*(rate+taxamt); 
                    }
                    else{
                         taxamt1=taxrate1*rate;  
                    }
//                      taxamt1=taxrate1*rate;
                      Object f= new Float(Math.round(taxamt1)+1);
                      String st= f.toString();
                         taxamt1 = new Double(st);
                      
                  }else if(obj1[0].equals("yesprevious")){
                      if(fac.getCascade1()==true){
                          taxamt1=taxrate1*(rate+taxamt); 
                    }
                    else{
                         taxamt1=taxrate1*rate;  
                    }
//                      taxamt1=taxrate1*rate;
                      Object f= new Float(Math.round(taxamt1)-1);
                      String st= f.toString();
                         taxamt1 = new Double(st);
                         }
                             else{
                       if(fac.getCascade1()==true){
                         taxamt1=taxrate1*(rate+taxamt);   
                    }else{
                            taxamt1=taxrate1*rate; 
                       }
                                
                                 }
            }
}
           if(fac.getTaxcat_3()!=null){
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id2);
                if(obj2[0]==null){
                    if(fac.getCascade2()==true){
                         taxamt2=taxrate2*(rate+taxamt+taxamt1);   
                    }
                    else{
                          taxamt2=taxrate2*rate;   
                    }
//              taxamt2=taxrate2*rate;   
            }
            
            if(obj2[0]!=null){
            if(obj2[0].equals("yes")){
                 if(fac.getCascade2()==true){
                         taxamt2=taxrate2*(rate+taxamt+taxamt1);   
                    }
                    else{
                          taxamt2=taxrate2*rate;   
                    }
//                    taxamt2=taxrate2*rate; 
                    Object f= new Float(Math.round(taxamt2));
                     String st= f.toString();
                         taxamt2 = new Double(st);
                              
                  }else if(obj2[0].equals("yesnearest")){
                       if(fac.getCascade2()==true){
                         taxamt2=taxrate2*(rate+taxamt+taxamt1);   
                    }
                    else{
                          taxamt2=taxrate2*rate;   
                    }
//                      taxamt2=taxrate2*rate;
                      Object f= new Float(Math.round(taxamt2));
                      String st= f.toString();
                         taxamt2 = new Double(st);
                      
                  }else if(obj2[0].equals("yesnext")){
                       if(fac.getCascade2()==true){
                         taxamt2=taxrate2*(rate+taxamt+taxamt1);   
                    }
                    else{
                          taxamt2=taxrate2*rate;   
                    }
//                      taxamt2=taxrate2*rate;
                      Object f= new Float(Math.round(taxamt2)+1);
                      String st= f.toString();
                         taxamt2 = new Double(st);
                      
                  }else if(obj2[0].equals("yesprevious")){
                       if(fac.getCascade2()==true){
                         taxamt2=taxrate2*(rate+taxamt+taxamt1);   
                    }
                    else{
                          taxamt2=taxrate2*rate;   
                    }
//                      taxamt2=taxrate2*rate;
                      Object f= new Float(Math.round(taxamt2)-1);
                      String st= f.toString();
                         taxamt2 = new Double(st);
                         }
                             else{
                       if(fac.getCascade2()==true){
                         taxamt2=taxrate2*(rate+taxamt+taxamt1);   
                                 }
                             else{
                          taxamt2=taxrate2*rate;   
                                 }
//                                  taxamt2=taxrate2*rate;
                                 }
            }
           }
//                id2=taxinfo2.getId();
           
                taxamt3=taxamt+taxamt1+taxamt2;
//          taxvalue.setText(String.valueOf(dmang.roundTwoDecimals(taxamt)));
 taxvalue.setText(String.valueOf(dmang.roundTwoDecimals(taxamt3)));
 
 jLabel15.setText(String.valueOf(dmang.roundTwoDecimals(taxamt)));
jLabel16.setText(String.valueOf(dmang.roundTwoDecimals(taxamt1)));
 jLabel17.setText(String.valueOf(dmang.roundTwoDecimals(taxamt2)));

//          trate.setText(String.valueOf(dmang.roundTwoDecimals(taxamt+rate)));
 trate.setText(String.valueOf(dmang.roundTwoDecimals(taxamt3+rate)));
          jLabel3.setText(String.valueOf(rate));
          period=fac.getrperiod();
          Periodicity p=dmang.getPerioicitybyid(period);
          period=p.getName();
//              System.out.println("period::::::::::"+period);
          jLabel5.setText(p.getName());
          fmodel = FacilityBillingTableModel.emptyinstance();
          jTable1.setModel(fmodel.getTableModel());
          jButton2.setEnabled(false);
          jButton2.setVisible(false);
             // jLabel6.setVisible(false);
            //  jLabel7.setVisible(false);
          jTextField1.setText(null);
          jTextField2.setText(null);
          }
        }
        }catch(Exception e){
         e.printStackTrace();
        }
    }//GEN-LAST:event_facilityTypeItemStateChanged

    private JRField[] getFields() throws JRException, UnsupportedOperationException {
		 JRField[]  fields = new JRField[7];
		fields[0] = (JRField)new JRBasicField("SlNo", "slno", java.lang.String.class, "java.lang.String");
		fields[1] = (JRField)new JRBasicField("Mem No", "memno", java.lang.String.class, "java.lang.String");
        fields[2] = (JRField)new JRBasicField("Mem Name", "mname", java.lang.String.class, "java.lang.String");
        fields[3] = (JRField)new JRBasicField("Start Date", "sdate", java.util.Date.class, "java.util.Date");
        fields[4] = (JRField)new JRBasicField("Last Bill Date", "lbilldate", java.util.Date.class, "java.util.Date");
        fields[5] = (JRField)new JRBasicField("Num", "no", java.lang.Integer.class, "java.lang.Integer");
        fields[6] = (JRField)new JRBasicField("Amount", "amount", java.lang.Double.class, "java.lang.Double");
		return fields;
	}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(name1==null){
            name1="Tax Standard";
        }
         if(name2==null){
            name2="Tax Standard";
        }
        try{
           Map reportparams = new HashMap();
           reportparams.put("companyName",m_App.getSession().getCompanyName());
           reportparams.put("companyAddress",m_App.getSession().getCompanyAddress());
           reportparams.put("Facility", this.getfacility());
           reportparams.put("Rate", this.getRate());
           reportparams.put("Period", this.getPeriod());
           reportparams.put("billnum", this.getBillNum());
           reportparams.put("billedby", m_App.getAppUserView().getUser().getName());
//           reportparams.put("tax", this.gettax());
           reportparams.put("tax", name);
//            reportparams.put("tax2", this.gettax2());
           reportparams.put("tax2", name1);
//             reportparams.put("tax3", this.gettax3());
           reportparams.put("tax3", name2);
           reportparams.put("taxvalue", this.gettaxValue());
           reportparams.put("totalrate", this.gettotalrate());
           DataSourceProvider data1=new DataSourceProvider(fmodel.getfacilityline());
           data1.setFields(getFields());
           DataSource ds=new DataSource(fmodel.getfacilityline());
           data1.setDataSource(ds);
           JasperPrint jp= JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/del.jrxml",reportparams,true,data1,false,null);
//             JOptionPane.showMessageDialog(null, "Do you want to reprint it?", "Do you want to reprint it?", JOptionPane.OK_OPTION);
//int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//       if (res == JOptionPane.YES_OPTION) {
//            ////////
////                    Object objg = new StaticSentence(m_App.getSession(), "SELECT TAXTOTAL FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
////                    double taxamt=Double.parseDouble(objg.toString());
////                    BillList.taxamt1=taxamt;
//                    ///////
////            printTicket("Printer.Ticket", binfo, binfo.getPlace());
// jButton3.setVisible(true);
//
//        }
//       else{
//           jButton3.setVisible(false);
//       }
           
           jButton3.setVisible(true);
//           jButton3.setVisible(false);
        }catch(Exception e){
         e.printStackTrace();
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

private void facilityTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityTypeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_facilityTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox facilityType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel tax;
    private javax.swing.JLabel taxvalue;
    private javax.swing.JLabel trate;
    // End of variables declaration//GEN-END:variables
//  private void TaxDetail() throws BasicException {
//      cascade1=facline.getCascade1();
//      System.out.println("cascade1:::::::"+cascade1);
//        cascade2=f1.getCascade1();
//      
//}
}
