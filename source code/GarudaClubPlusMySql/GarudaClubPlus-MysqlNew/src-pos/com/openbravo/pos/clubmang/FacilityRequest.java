/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacilityRequest.java
 *
 * Created on Jul 3, 2009, 4:58:02 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.TaxInfo;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class FacilityRequest extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /** Creates new form FacilityRequest */
    private FacilityRequestTableModel frmodel;
    private AppView m_App;
    private DataLogicFacilities dmang;
    private DataLogicSales m_dlSales;
    private TaxesLogic taxeslogic;
    private RequestRejectDialog rrdialog;
    public FacilityRequest() {
        initComponents();
    }
    public String getTitle() {
       return null;
    }

    public void activate() throws BasicException {
         frmodel=FacilityRequestTableModel.loadInstance(m_App);
         jTable1.setModel(frmodel.getTableModel());
         jTable2.setModel(frmodel.getMinUsageTableModel());
         taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       m_App=app;
       dmang=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }

    public Object getBean() {
        return this;
    }
    private Calendar setTimetoZero(Calendar cal){
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return cal;
    }
    private void billFacility(String fid,String id,Date edate,int row,FacilityRequestTableModel.RequestLine rline,Calendar fscal,Calendar fecal,String prevFySubIncome,String nextFySubIncome) throws BasicException{
        Facility f=dmang.getFacilitybyID(fid);
        String cid=jTable1.getModel().getValueAt(row, 10).toString();
        String caccount=jTable1.getModel().getValueAt(row, 11).toString();
        if(f.getramt()>0){
                     Date d=new Date();
                     d.setTime(getDate().getTime());
                     Object[] obj=(Object[])new StaticSentence(m_App.getSession()
                           , "SELECT LBILLDATE,SDATE FROM MEMFACILITYUSAGE WHERE ID=?"
                           ,SerializerWriteString.INSTANCE
                           ,new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})).find(id);
                     if(obj!=null){
                         Date lbdate=new Date();
                        if(obj[0]!=null){
                            lbdate=(Date)(obj[0]);
                            //Formats.TIMESTAMP.parseValue
                        }
                        else if(obj[1]!=null){
                            lbdate=(Date)Formats.TIMESTAMP.parseValue(obj[1].toString());
                        }
                            Periodicity p=dmang.getPerioicitybyid(f.getrperiod());
                            Date dnow=new Date();
                            FacilityLogic flogic=new FacilityLogic(dmang);
                            Calendar calnow=Calendar.getInstance();
                            calnow.setTimeInMillis(dnow.getTime());
                            calnow=setTimetoZero(calnow);
                            Calendar cal=Calendar.getInstance();
                            cal.setTimeInMillis(lbdate.getTime());
                            cal=setTimetoZero(cal);
                            Calendar ecal=Calendar.getInstance();
                            ecal.setTimeInMillis(edate.getTime());
                            ecal=setTimetoZero(ecal);
                            //ecal.add(Calendar.MONTH, -1);
                            int billabledate=ecal.get(Calendar.DATE);
                            //ecal.add(Calendar.MONTH, 1);
                            //ecal.set(Calendar.DATE, -1);
                            
//                            if(p.getdoj()==false){
//                                cal.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal).getTimeInMillis());
//                             }
                             lbdate.setTime(cal.getTimeInMillis());
                             flogic.setTemp(lbdate);
                             edate.setTime(flogic.calculateEndDate(lbdate,p,billabledate,1,edate).getTime());
                             double taxrate=0.0;
                             String servicetaxacc=null;
                             if(f.getservicetax()!=null){
                                TaxCategoryInfo tinfo=(TaxCategoryInfo)m_dlSales.getTaxCategoryByid(f.getservicetax());
                                TaxInfo tax = taxeslogic.getTaxInfo(tinfo);
                                taxrate=tax.getRate();
                             }
                             if(taxrate>0){
                                Object stacc=new StaticSentence(m_App.getSession()
                                , "SELECT VALUE FROM GENERALTABLE WHERE NAME = ? "
                                , SerializerWriteString.INSTANCE
                                ,SerializerReadString.INSTANCE).find("Service Tax Account");
                             if(stacc!=null)
                                  servicetaxacc=stacc.toString();
                             }else
                                  servicetaxacc="tax free";
                             Date duedate=new Date();
                             DebtTypeTableModel.DebtTypeline periodtype=dmang.getDebtTypebyid(f.getdueperiod());
                             duedate.setTime(flogic.getDueDate(periodtype,flogic.getTemp()).getTime());
                      Double ramt=f.getramt();
                      if(p.getaccurate()==true){
                         ramt=flogic.calulaterenewalamt(lbdate,edate,ramt);
                      }else{
                      if(flogic.getPnum()>0)
                           ramt=flogic.getPnum()*ramt;
                      }
                      String billnum=dmang.getnewbillno(f.getid());
                      String tid =UUID.randomUUID().toString();
                      int noOfMOnths=0;
                      while(cal.before(ecal)){
                          noOfMOnths++;
                          cal.add(Calendar.MONTH, 1);
                          cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                      }
                 //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,ACTIVE
                     double amt=ramt+(taxrate*ramt);
                     double amtwithoutTax=ramt;
                     double pamt=amtwithoutTax/noOfMOnths;// value for 1 period
             Calendar rscal=Calendar.getInstance();
             Calendar recal=Calendar.getInstance();
             rscal.setTimeInMillis(lbdate.getTime());
             recal.setTimeInMillis(edate.getTime());
             String fnarrationbegin=null;
             String fnarrationend=null;
             String narration="";
             if(rscal.before(fscal)){
                 int cnt=0;
                 while(rscal.before(fscal)){
                     cnt++;
                     rscal.add(Calendar.MONTH, 1);
                 }
                 double prevamt=pamt*cnt;
                 amtwithoutTax=amtwithoutTax-prevamt;
                 Calendar caltemp=Calendar.getInstance();
                 caltemp.setTimeInMillis(fscal.getTimeInMillis());
                 caltemp.add(Calendar.DATE, -1);
                 fnarrationbegin="Renewal fees Period: "+Formats.DATE.formatValue(rline.getFromdate());
                 
                 if(rline.getUserID()!=null)
                   narration="Renewal fees Period: "+Formats.DATE.formatValue(rline.getFromdate())+" to "+Formats.DATE.formatValue(caltemp.getTime())+"- "+rline.getUserName();
                 else
                   narration="Renewal fees Period: "+Formats.DATE.formatValue(rline.getFromdate())+" to "+Formats.DATE.formatValue(caltemp.getTime());
                 Object[] value=new Object[]{UUID.randomUUID().toString(),tid,null,new Date(),"C",rline.getFacilityId(),billnum,prevamt,duedate,true,rline.getUserName(),m_App.getProperties().getHost(),narration,prevFySubIncome,0.0,true};
                 dmang.insertintoaccjoutnal(value);
             }if(!recal.before(fecal)){
                 int cnt=0;
                 Calendar caltemp1=Calendar.getInstance();//copy of financial year end
                 caltemp1.setTimeInMillis(fecal.getTimeInMillis());
                 while(caltemp1.before(recal)){
                     cnt++;
                     caltemp1.add(Calendar.MONTH, 1);
                     caltemp1.set(Calendar.DATE, rscal.getActualMaximum(Calendar.DATE));
                 }

                 double advamt=pamt*cnt;
                 amtwithoutTax=amtwithoutTax-advamt;
                 Calendar caltemp=Calendar.getInstance();
                 caltemp.setTimeInMillis(fecal.getTimeInMillis());
                 caltemp.add(Calendar.DATE, 1);
                 if(rline.getUserID()!=null){
                    fnarrationend=" to "+Formats.DATE.formatValue(rline.getFromdate())+"- "+rline.getUserName();
                    narration="Renewal fees Period: "+Formats.DATE.formatValue(caltemp.getTime())+" to "+Formats.DATE.formatValue(edate)+"- "+rline.getUserName();
                 }else{
                    fnarrationend=" to "+Formats.DATE.formatValue(rline.getFromdate());
                    narration="Renewal fees Period: "+Formats.DATE.formatValue(caltemp.getTime())+" to "+Formats.DATE.formatValue(edate);
                 }
                 Object[] value=new Object[]{UUID.randomUUID().toString(),tid,null,new Date(),"C",rline.getFacilityId(),billnum,advamt,duedate,true,rline.getUserName(),m_App.getProperties().getHost(),narration,nextFySubIncome,0.0,true};
                 dmang.insertintoaccjoutnal(value);
             }
             //totalamt += amt;
             if(fnarrationbegin==null)
                 fnarrationbegin="Renewal fees Period: "+Formats.DATE.formatValue(rline.getFromdate());
             if(rline.getUserID()!=null){
                 narration="Renewal fees Period: "+Formats.DATE.formatValue(rline.getFromdate())+" to "+Formats.DATE.formatValue(edate)+"- "+rline.getUserName();
                 if(fnarrationend==null)
                     fnarrationend=" to "+Formats.DATE.formatValue(edate)+"- "+rline.getUserName();
             }else{
                 narration="Renewal fees Period: "+Formats.DATE.formatValue(rline.getFromdate())+" to "+Formats.DATE.formatValue(edate);
                 if(fnarrationend==null)
                     fnarrationend=" to "+Formats.DATE.formatValue(edate);
             }

                     double taxamt=taxrate*ramt;
                     if(taxamt>0){
                      if(servicetaxacc==null)
                        JOptionPane.showMessageDialog(null, "Service tax account is missing","Error",JOptionPane.OK_OPTION);
                       Object[] value4=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billnum,taxamt,d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Service tax on Renewal Fees for "+f.getName(),servicetaxacc,0.0,d,new Date(),true};
                       dmang.insertintoaccjoutnal3(value4);
                     }
                     if(amtwithoutTax>0){
                      Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billnum,amtwithoutTax,d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),fnarrationbegin+fnarrationend,f.getRenewalacc(),0.0,d,true};
                      dmang.insertintoaccjoutnal1(value1);
                     }
                     if(rline.getUserName()==null){
                       Object[] value2=new Object[]{UUID.randomUUID().toString(),tid,cid,d,"D",f.getid(),billnum,ramt+taxamt,duedate,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Renewal Fees for period "+lbdate+" to "+edate,caccount,ramt+taxamt,true};
                       dmang.insertintoaccjoutnal(value2);
                     }else{
                       Object[] value2=new Object[]{UUID.randomUUID().toString(),tid,cid,d,"D",f.getid(),billnum,ramt+taxamt,duedate,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Renewal Fees for period "+lbdate+" to "+edate+" "+rline.getUserName(),caccount,ramt+taxamt,true};
                       dmang.insertintoaccjoutnal(value2);
                     }
                     dmang.updatebillno(f.getid());
                       // }
                     }
        }
         

    }
    private void stopFacility(String id,String facmid,String remark,Date edate) throws BasicException{
       new PreparedSentence(m_App.getSession()
                   ,"UPDATE MEMFACILITYUSAGE SET ACTIVE=FALSE,STATUS_=?,FACMANGREF=?,EDATE=? WHERE ID=? "
                   ,new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{2,null,edate,id});
         updateFacilityManager(facmid,remark);
    }
    private void updateFacilityManager(String id,String remark) throws BasicException{
        new PreparedSentence(m_App.getSession()
                   ,"UPDATE FACILITYMANAGER SET STATUS_=?,APPROVEDBY=?,APPROVEDDATE=?,REMARK=? WHERE ID=? "
                   ,new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING})).exec(new Object[]{true,m_App.getAppUserView().getUser().getId(),new Date(),remark,id});
    }
     private Date getDate(){
         Date d=new Date();
         Calendar cal=Calendar.getInstance();
         cal.setTimeInMillis(d.getTime());
         cal.set(Calendar.HOUR_OF_DAY, 00);
         cal.set(Calendar.MINUTE, 00);
         cal.set(Calendar.MILLISECOND, 00);
         cal.set(Calendar.SECOND, 00);
         d.setTime(cal.getTimeInMillis());
         return d;
     }
     private void allowSuspension(String id) throws BasicException{
        new PreparedSentence(m_App.getSession()
                            ,"UPDATE MEMFACILITYUSAGE SET STATUS_=? WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{1,id});
     }
     private int validate(Date from,Date to,Date ofrom,Date oto){
         int status=0;
        Calendar nfcal=Calendar.getInstance();
        nfcal.setTimeInMillis(from.getTime());
        nfcal.set(Calendar.HOUR_OF_DAY, 00);
        nfcal.set(Calendar.SECOND, 00);
        nfcal.set(Calendar.MILLISECOND, 00);
        nfcal.set(Calendar.MINUTE, 00);
        Calendar ntcal=Calendar.getInstance();
        ntcal.setTimeInMillis(to.getTime());
        ntcal.set(Calendar.HOUR_OF_DAY, 00);
        ntcal.set(Calendar.SECOND, 00);
        ntcal.set(Calendar.MILLISECOND, 00);
        ntcal.set(Calendar.MINUTE, 00);
        Calendar ofcal=Calendar.getInstance();
        ofcal.setTimeInMillis(ofrom.getTime());
        ofcal.set(Calendar.HOUR_OF_DAY, 00);
        ofcal.set(Calendar.SECOND, 00);
        ofcal.set(Calendar.MILLISECOND, 00);
        ofcal.set(Calendar.MINUTE, 00);
        Calendar otcal=Calendar.getInstance();
        otcal.setTimeInMillis(oto.getTime());
        otcal.set(Calendar.HOUR_OF_DAY, 00);
        otcal.set(Calendar.SECOND, 00);
        otcal.set(Calendar.MILLISECOND, 00);
        otcal.set(Calendar.MINUTE, 00);
        if(nfcal.equals(ofcal) && ntcal.equals(otcal)){
           status=1;
        }else if(nfcal.equals(ofcal)){
           status=2;
        }else
            status=3;
        return status;
     }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton3.setText("jButton3");

        jLabel1.setText("Request List");

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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Allow");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reject");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton4.setText("Reject");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Allow");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("Facility Request List");

        jLabel3.setText("Minimum Usage Request List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
         Transaction t=new Transaction(m_App.getSession()) {

          @Override
          protected Object transact() throws BasicException {
            int row=jTable1.getSelectedRow();
            if(row>=0){
                Date e = null;///aaa
                String b = null;///aaa
                Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
                GeneralSettingInfo sinfo=gs.get("Voucher Entry Restricted From");
                GeneralSettingInfo einfo=gs.get("Voucher Entry Restricted Upto");
                String prevFySubIncome=gs.get("prevFySubIncome").getValue();
                String nextFySubIncome=gs.get("nextFySubIncome").getValue();
                Date fsdate=(Date)Formats.DATE.parseValue(sinfo.getValue());
                Date fedate=(Date)Formats.DATE.parseValue(einfo.getValue());
                Calendar fscal=Calendar.getInstance();//financial year sdate
                fscal.setTime(fsdate);
                Calendar fecal=Calendar.getInstance();//financial year edate
                fecal.setTime(fedate);
                FacilityRequestTableModel.RequestLine rline=frmodel.getRequestList().get(row);
                 int status=rline.getStatus();
                 String id=rline.getId();
                 String fid=rline.getFacilityId();
                 String cid=rline.getcid();
                 String caccount=rline.getCaccount();//jTable1.getModel().getValueAt(row, 11).toString();
                 Date fromdate=rline.getFromdate();
                 Date todate=rline.getTodate();
                 Calendar ffcal=Calendar.getInstance();//facility from date
                 ffcal.setTimeInMillis(fromdate.getTime());
                 //ffcal.set(Calendar.h, status);
                 Calendar ftcal=Calendar.getInstance();//facility to date
                 if(todate==null)
                     ftcal=null;
                 else
                     ftcal.setTimeInMillis(todate.getTime());
                 if(status==5){//start a facility
               //  {
                     Date d=new Date();
                     d.setTime(getDate().getTime());
                     Facility f=dmang.getFacilitybyID(fid);
                     double taxrate=0.0;
                     String servicetaxacc=null;
                     if(f.getservicetax()!=null){
                       TaxCategoryInfo tinfo=(TaxCategoryInfo)m_dlSales.getTaxCategoryByid(f.getservicetax());
                       TaxInfo tax = taxeslogic.getTaxInfo(tinfo);
                       taxrate=tax.getRate();
                     }
                    if(taxrate>0){
                       Object stacc=new StaticSentence(m_App.getSession()
                       , "SELECT VALUE FROM GENERALTABLE WHERE NAME = ? "
                       , SerializerWriteString.INSTANCE
                       ,SerializerReadString.INSTANCE).find("Service Tax Account");
                       if(stacc!=null)
                          servicetaxacc=stacc.toString();
                    }else
                        servicetaxacc="tax free";
                    // if(f.getjfee()==true){
                       if(f.getjamt()>0){
                          String billno=dmang.getnewbillno(f.getid());
                          String tid =UUID.randomUUID().toString();
                          double taxamt=taxrate*f.getjamt();
                          if(taxamt>0){
                             if(servicetaxacc==null){
                               JOptionPane.showMessageDialog(null, "Service tax account is missing","Error",JOptionPane.OK_OPTION);
                               throw new BasicException();
                             }
                             Object[] value4=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billno,taxamt,d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Service tax on Join Fees for "+f.getName(),servicetaxacc,0.0,d,new Date(),true};
                               dmang.insertintoaccjoutnal3(value4);
                          }
                          if(ffcal.after(fecal)){//future year income
                              if(nextFySubIncome!=null){
                                    Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billno,f.getjamt(),d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),f.getName(),nextFySubIncome,0.0,d,true};
                                    dmang.insertintoaccjoutnal1(value1);
                              }else
                                  throw new BasicException("Please specify an account for Future Year Subscription");
                          }else if(ffcal.before(fscal)){//previous year income
                            if(prevFySubIncome!=null){
                              Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billno,f.getjamt(),d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),f.getName(),prevFySubIncome,0.0,d,true};
                              dmang.insertintoaccjoutnal1(value1);
                            }else
                                throw new BasicException("Please specify an account for Previous Year Subscription");
                          }else {
                            Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billno,f.getjamt(),d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),f.getName(),f.getJoinfeeAccount(),0.0,d,true};
                            dmang.insertintoaccjoutnal1(value1);
                          }
                          Object[] value2=new Object[]{UUID.randomUUID().toString(),tid,cid,d,"D",f.getid(),billno,f.getjamt()+taxamt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Join Fees for "+f.getName(),caccount,f.getjamt()+taxamt,true};
                          dmang.insertintoaccjoutnal(value2);
                          dmang.updatebillno(f.getid());
                       }
                    //  }
                      Date sdatetime=(Date)jTable1.getModel().getValueAt(row, 12);
                      DebtTypeTableModel.DebtTypeline periodtype=dmang.getDebtTypebyid(f.getdueperiod());
                      FacilityLogic flogic=new FacilityLogic(dmang);
                      Date duedate=flogic.getDueDate(periodtype,sdatetime);
                      Periodicity p=dmang.getPerioicitybyid(f.getrperiod());
                      if(f.getramt()>0 && p.getqbtype()==true){//post billing
                         Date lbdate=new Date();
                         Date edate=new Date();
                         String billnum=null;
                     //    if(p.getqbtype()==true){//post billing
                             lbdate.setTime(sdatetime.getTime());
                             Calendar cal1=Calendar.getInstance();
                             cal1.setTimeInMillis(lbdate.getTime());
                             int billabledate=cal1.get(Calendar.DATE);
                          if(p.getdoj()==false){
                               cal1.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal1).getTimeInMillis());
                         }
                      lbdate.setTime(cal1.getTimeInMillis());
                      //temp=new Date();
                      flogic.setTemp(lbdate);//temp.setTime(lbdate.getTime());
                      edate.setTime(flogic.calculateEndDate(lbdate,p,billabledate,1,new Date()).getTime());
                      if(p.getqbtype()==true)
                      duedate.setTime(flogic.getDueDate(periodtype,flogic.getTemp()).getTime());
                      Double ramt=f.getramt();
                      if(p.getaccurate()==true){
                         ramt=flogic.calulaterenewalamt(lbdate,edate,ramt);
                      }else{
                         if(flogic.getPnum()>0)
                           ramt=flogic.getPnum()*ramt;
                      }
                      billnum=dmang.getnewbillno(f.getid());
                      String tid =UUID.randomUUID().toString();
                      e=edate;///aaa
                      b=billnum;///aaa
                 //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,ACTIVE
                     double taxamt=taxrate*ramt;
                     if(taxamt>0){
                      if(servicetaxacc==null){
                        JOptionPane.showMessageDialog(null, "Service tax account is missing","Error",JOptionPane.OK_OPTION);
                        throw new BasicException();
                      }
                      Object[] value4=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billnum,taxamt,d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Service tax on Renewal Fees for "+f.getName(),servicetaxacc,0.0,d,new Date(),true};
                      dmang.insertintoaccjoutnal3(value4);
                     }
                     Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,d,"C",f.getid(),billnum,ramt,d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),f.getName(),f.getRenewalacc(),0.0,d,true};
                     dmang.insertintoaccjoutnal1(value1);
                     Object[] value2=new Object[]{UUID.randomUUID().toString(),tid,cid,d,"D",f.getid(),billnum,ramt+taxamt,duedate,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Renewal Fees for period "+lbdate+" to "+edate,caccount,ramt+taxamt,true};
                     dmang.insertintoaccjoutnal(value2);
                     dmang.updatebillno(f.getid());
                   }
///aaa
                else{
                      Date lbdate=new Date();
                         Date edate=new Date();
                             lbdate.setTime(sdatetime.getTime());
                             Calendar cal1=Calendar.getInstance();
                             cal1.setTimeInMillis(lbdate.getTime());
                             int billabledate=cal1.get(Calendar.DATE);
                          if(p.getdoj()==false){
                               cal1.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal1).getTimeInMillis());
                         }
                      lbdate.setTime(cal1.getTimeInMillis());
                      //temp=new Date();
                      flogic.setTemp(lbdate);//temp.setTime(lbdate.getTime());
                      if(p.gettype().equals("Months")){
                      edate.setTime(flogic.calculateEndDate(lbdate,p,billabledate,1,new Date()).getTime());
                      e=edate;
                      }else{
                      edate.setTime(flogic.calculateEndDate(new Date(),p,billabledate,1,lbdate).getTime());
                      e=edate;
                      }
                      }
       new PreparedSentence(m_App.getSession()
        ,"UPDATE MEMFACILITYUSAGE SET ACTIVE=TRUE,CREATEDBY=?,CRDATE=?,LBILLDATE=?,NBILLDATE=?,BILLREF=?,STATUS_=?,FACMANGREF=? WHERE ID=? "
        ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(),d,e,e,b,0,null,id});
///aaa
       
  //                 new PreparedSentence(m_App.getSession()
  //                 ,"UPDATE MEMFACILITYUSAGE SET ACTIVE=TRUE,CREATEDBY=?,CRDATE=?,STATUS_=?,FACMANGREF=? WHERE ID=? "
  //                 ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.INT,Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getId(),d,0,null,id});
                   updateFacilityManager(rline.getFacilityManagerID(),null);
                 } else if(status==3){//deactivate a facility
                   Date edatetime=rline.getSdate();
                   //billFacility(fid,id,edatetime,row);
                   stopFacility(id, rline.getFacilityManagerID(),null,edatetime);
                 }else if(status==4){//suspend a facility
                    int result=JOptionPane.showConfirmDialog(null, " Click Yes to allow suspension for the requested period \r\n Click No to allow partial suspension ", null, JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result==JOptionPane.YES_OPTION){
                        new PreparedSentence(m_App.getSession()
                            ,"UPDATE MEMFACILITYUSAGE SET LBILLDATE=?,FACMANGREF=? WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING,Datas.STRING})).exec(new Object[]{rline.getTodate(),null,id});
                        updateFacilityManager(rline.getFacilityManagerID(),null);
                    }else if(result==JOptionPane.NO_OPTION){
                        RequestRejectDialog rrdialog=RequestRejectDialog.getDialog(new JFrame(), m_App);
                        String sfrom=jTable1.getModel().getValueAt(row, 4).toString();
                        String sto=jTable1.getModel().getValueAt(row, 5).toString();
                        rrdialog.setTitle("Suspension is requested from "+sfrom+" to "+sto);
                        boolean flag=false;
                        flag=rrdialog.showDialog((Date)Formats.TIMESTAMP.parseValue(sfrom), (Date)Formats.TIMESTAMP.parseValue(sto),dmang,rline.getRenewalPeriodicty());
                        if(flag==true){
                            String remark=rrdialog.getReason();
                            int status1=validate(rrdialog.getFromdate(),rrdialog.getTodate(),(Date)Formats.TIMESTAMP.parseValue(sfrom),(Date)Formats.TIMESTAMP.parseValue(sto));
                            if(status1==1){
                                 new PreparedSentence(m_App.getSession()
                                 ,"UPDATE MEMFACILITYUSAGE SET LBILLDATE=?,FACMANGREF=? WHERE ID=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING,Datas.STRING})).exec(new Object[]{rline.getTodate(),null,id});
                                 updateFacilityManager(rline.getFacilityManagerID(),null);
                            }else if(status1>1 ){
                                if(status1!=2){
                                    Calendar cal=Calendar.getInstance();
                                    cal.setTimeInMillis(rrdialog.getFromdate().getTime());
                                    cal.add(Calendar.DATE, -1);
                                   billFacility(fid,id,cal.getTime(),row,rline,fscal,fecal,prevFySubIncome,nextFySubIncome);
                                }
                                new PreparedSentence(m_App.getSession()
                                ,"UPDATE MEMFACILITYUSAGE SET LBILLDATE=?,FACMANGREF=? WHERE ID=?"
                               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING,Datas.STRING})).exec(new Object[]{rrdialog.getTodate(),null,id});
                                new PreparedSentence(m_App.getSession()
                                ,"UPDATE FACILITYMANAGER SET STATUS_=?,APPROVEDBY=?,APPROVEDDATE=?,REMARK=?,FROM_=?,TO_=? WHERE ID=? "
                                ,new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
                                     ).exec(new Object[]{true,m_App.getAppUserView().getUser().getId(),new Date(),remark,rrdialog.getFromdate(),rrdialog.getTodate(),rline.getFacilityManagerID()});
                            }
                            /*new PreparedSentence(m_App.getSession()
                            ,"UPDATE MEMFACILITYUSAGE SET LBILLDATE=?,FACMANGREF=? WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{id});
                             new PreparedSentence(m_App.getSession()
                             ,"UPDATE FACILITYMANAGER SET STATUS_=?,APPROVEDBY=?,APPROVEDDATE=?,REMARK=?,FROM_=?,TO_=? WHERE ID=? "
                             ,new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
                                     ).exec(new Object[]{true,m_App.getAppUserView().getUser().getId(),new Date(),remark,id});*/
                        }
                    }
                 }
            }
            return null;
           }
            };
            t.execute();
            activate();
        }catch(Exception e){
           e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
       
        int row=jTable1.getSelectedRow();
        if(row>=0){
             rrdialog=RequestRejectDialog.getDialog(this, m_App);
             boolean flag=false;
             flag=rrdialog.showDialog1();
            
           // int status=Integer.parseInt(jTable1.getModel().getValueAt(row, 8).toString());
            //new PreparedSentence(m_App.getSession()
             //               ,"UPDATE MEMFACILITYUSAGE SET STATUS_=?,MESSAGE=? WHERE ID=? "
            //                , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{-1*status,rrdialog.getReason(),id,5});
             Transaction t=new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        int row=jTable1.getSelectedRow();
                        FacilityRequestTableModel.RequestLine rline=frmodel.getRequestList().get(row);
                        if(rline.getStatus()==5){
                            new PreparedSentence(m_App.getSession()
                                             , "DELETE FROM MEMFACILITYUSAGE  WHERE ID=?",
                                             new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{rline.getId()});
                        }else
                        new PreparedSentence(m_App.getSession()
                                             , "UPDATE MEMFACILITYUSAGE SET FACMANGREF=? WHERE ID=?",
                                             new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{null,rline.getId()});
                        new PreparedSentence(m_App.getSession()
                                             , "UPDATE FACILITYMANAGER SET APPROVEDBY=?,APPROVEDDATE=?,STATUS_=?,REMARK=? WHERE ID=?",
                                             new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getId(),new Date(),false,rrdialog.getReason(),rline.getFacilityManagerID()});

                        return null;
                    }
                };
                t.execute();
                activate();
        }else{
            JOptionPane.showMessageDialog(null, "Please select a row", "Sorry cannot process", JOptionPane.INFORMATION_MESSAGE);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        int row=jTable2.getSelectedRow();
        if(row>=0){
            FacilityRequestTableModel.MinUsageRequestLine line=frmodel.getMinUsageRequestLine().get(row);
            String fmid=(String) frmodel.getMinUsageTableModel().getValueAt(row, 6);
            System.out.println(fmid);
            new StaticSentence(m_App.getSession(), "UPDATE FACILITYMANAGER SET APPROVEDBY=?,APPROVEDDATE=?,STATUS_=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(),new Date(),false,fmid});
            activate();
        }else{
            JOptionPane.showMessageDialog(null, "Please select a row", "Sorry cannot process", JOptionPane.INFORMATION_MESSAGE);
        }
        
        }catch(Exception e){
            e.printStackTrace();

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try{
        int row=jTable2.getSelectedRow();
        if(row>=0){
            FacilityRequestTableModel.MinUsageRequestLine line=frmodel.getMinUsageRequestLine().get(row);
            String fmid=(String) frmodel.getMinUsageTableModel().getValueAt(row, 6);
            String mmuid=(String) frmodel.getMinUsageTableModel().getValueAt(row, 5);
            System.out.println(fmid);
            new StaticSentence(m_App.getSession(), "UPDATE FACILITYMANAGER SET APPROVEDBY=?,APPROVEDDATE=?,STATUS_=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(),new Date(),true,fmid});
            new StaticSentence(m_App.getSession(), "UPDATE MEMMINUSAGE SET ACTIVE=?,STATUS_=?,FACMANREF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.INT,Datas.STRING,Datas.STRING})).exec(new Object[]{false,1,fmid,mmuid});
            activate();
            
        }else{
            JOptionPane.showMessageDialog(null, "Please select a row", "Sorry cannot process", JOptionPane.INFORMATION_MESSAGE);
        }

        }catch(Exception e){
            e.printStackTrace();

        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables


}
