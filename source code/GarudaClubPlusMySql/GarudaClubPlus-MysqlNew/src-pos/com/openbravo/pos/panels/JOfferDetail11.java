/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JOfferDetail.java
 *
 * Created on Feb 4, 2009, 12:22:43 PM
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.clubmang.DataSource1;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.Datasource2;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author swathi
 */
public class JOfferDetail11  extends javax.swing.JPanel implements JPanelView, BeanFactoryApp  {

    /** Creates new form JOfferDetail */
    private DataLogicSales m_dlSales;
    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private OfferTableDetail offermodel = null;
    private ComboBoxValModel cmodel;
    public JOfferDetail11() {

        initComponents();
    }
    public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        jButton1.setText("Closed Sequence");
        jButton2.setText("Execute");
        jTextField4.setText(null);
        jTextField5.setText(null);
     

    }
     public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

   public String getTitle() {
        return "Offer Details";
    }

    public void activate() throws BasicException {
       // m_dlSales.getActivePeopleListWithOutCCAcc();
        List<PeopleInfo> pList=m_dlSales.getActivePeopleListWithOutCCAcc().list();
        List<PeopleInfo> pList1=new ArrayList<PeopleInfo>();
         for(int i=0;i<pList.size();i++){
               PeopleInfo pinfo=pList.get(i);
                 //warehouse changes -start
                Object[] obj2=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE,PRCATEGORIES FROM PEOPLE WHERE PEOPLE.ID=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})).find(pinfo.getID());

             if(obj2!=null){
                 String warehouse = null;
                if (obj2[11] != null) {
                    String[] wArr = obj2[11].toString().split("#");
                    warehouse = wArr[0];
                }
             AppUser appuser1=new AppUser(obj2[0].toString(), obj2[1].toString(),obj2[4].toString(),warehouse);
               //warehouse changes -end

             appuser1.fillPermissions(m_dlSystem);
             if(appuser1.hasPermission("sales")){
                  pList1.add(pinfo);
             }

             }
           }
        PeopleInfo pinfo=new PeopleInfo();
        pinfo.setID("ALL");
        pinfo.setName("ALL");
        pList1.add(0,pinfo);
     //   cmodel=new ComboBoxValModel(pList1);
     //   jComboBox1.setModel(cmodel);
        loadData();
    }

     private void loadData() throws BasicException {
        //jComboBox1.setModel(cmodel);
        jComboBox1.setSelectedIndex(-1);
        offermodel = OfferTableDetail.loadInstance(m_App,"RefCode");
      /*  offertable.setModel(offermodel.getdiscountTableModel());
        TableColumnModel jColumns = offertable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(100);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(60);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(60);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(50);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(50);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(120);
        jColumns.getColumn(5).setResizable(false);
        jColumns.getColumn(6).setPreferredWidth(120);
        jColumns.getColumn(6).setResizable(false);
        jColumns.getColumn(7).setPreferredWidth(60);
        jColumns.getColumn(7).setResizable(false); */
     }
   public boolean deactivate() {
        return true;
    }
   private void insertOffer() throws BasicException
   {
       
       String id=UUID.randomUUID().toString();
       String appto=String.valueOf(jComboBox1.getSelectedItem());
       String name;
         Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ID FROM PRODUCTS WHERE NAME = ? "
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(new Object[]{""});

         if(obj==null || obj[0]==null)
         {
             name="";
             //msg
         }
         else
         {
              name=obj[0].toString();
                Object[] coun =(Object[]) new StaticSentence(m_App.getSession()
                ,"SELECT ID "
                +"FROM OFFER WHERE PRODUCT=? AND OFFER.TODATE >= ? AND ACTIVE=TRUE AND APPTO=? "

                ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
              ,new SerializerReadBasic( new Datas[]{Datas.STRING} )).find(new Object[]{name,Formats.TIMESTAMP.parseValue(jTextField4.getText()),appto} );
                if(coun==null || coun[0]==null)
                {
                   Object[] values = new Object[] {id,name,Double.parseDouble(""),Double.parseDouble(""),Formats.TIMESTAMP.parseValue(jTextField4.getText()),Formats.TIMESTAMP.parseValue(jTextField5.getText()),Double.parseDouble(""),Double.parseDouble(""),true,appto};
                  Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.DOUBLE,Datas.DOUBLE,Datas.BOOLEAN,Datas.STRING};
                   new PreparedSentence(m_App.getSession()
                , "INSERT INTO OFFER (ID,PRODUCT,BUYQTY,GETQTY,FROMDATE,TODATE,BUYRATE,GETRATE,ACTIVE,APPTO) VALUES (?,?,?,?,?,?,?,?,?,?) "
                , new SerializerWriteBasicExt(datas, new int[] {0,1, 2, 3, 4, 5,6,7,8,9})).exec(values);

                }
                else
                   if( JOptionPane.showConfirmDialog(this, "Offer for the specified product already exit.Do u want to over ride ?", "Offer Exist", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                   {
                        Datas[] datas1 = new Datas[] {Datas.BOOLEAN, Datas.STRING, Datas.STRING};
                        // Object[] values1 = new Object[] {status,obj1[0].toString(),obj2[0].toString()};
                new PreparedSentence(m_App.getSession()
                , "UPDATE OFFER SET ACTIVE = ? WHERE PRODUCT=? AND APPTO=?"
                , new SerializerWriteBasicExt(datas1,new int[]{0,1,2})).exec(new Object[]{false,name,appto});
                        Object[] values = new Object[] {id,name,Double.parseDouble(""),Double.parseDouble(""),Formats.TIMESTAMP.parseValue(jTextField4.getText()),Formats.TIMESTAMP.parseValue(jTextField5.getText()),Double.parseDouble(""),Double.parseDouble(""),true};
                    Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.DOUBLE,Datas.DOUBLE,Datas.BOOLEAN};
                   new PreparedSentence(m_App.getSession()
                   , "INSERT INTO OFFER (ID,PRODUCT,BUYQTY,GETQTY,FROMDATE,TODATE,BUYRATE,GETRATE,ACTIVE,APPTO) VALUES (?,?,?,?,?,?,?,?,?,?) "
                  , new SerializerWriteBasicExt(datas, new int[] {0,1, 2, 3, 4, 5,6,7,8,9})).exec(values);
                   }

         }}
       

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        startdate = new javax.swing.JButton();
        enddate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel4.setText("From             :");

        jLabel5.setText("To                :");

        startdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        startdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startdateActionPerformed(evt);
            }
        });

        enddate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        enddate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enddateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField4)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enddate, 0, 0, Short.MAX_VALUE)
                    .addComponent(startdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(257, 257, 257))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(45, 45, 45)
                .addComponent(jButton2)
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enddateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enddateActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField5.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            // String[] arr=date.toString().split(" ");
            jTextField5.setText(Formats.TIMESTAMP.formatValue(date));
        }
}//GEN-LAST:event_enddateActionPerformed

    private void startdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startdateActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
             //String[] arr=date.toString().split(" ");
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField4.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
          //  String[] arr=date.toString().split(" ");
            jTextField4.setText(Formats.TIMESTAMP.formatValue(date));
        }
}//GEN-LAST:event_startdateActionPerformed

    public void displaysequence(){
        try{
            cmodel=new ComboBoxValModel();

             List<Object[]> tlist=(ArrayList<Object[]>) new PreparedSentence(m_App.getSession(), "select distinct closedcheck from ((select host +' : ' + hostsequence as closedcheck, datestart as sdate,dateend as edate from closedcash)) as ali,receipts_arv r where closedcheck = r.closecashseq and sdate> ? and edate< ?"
                    , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                    , new SerializerReadBasic(new Datas[]{Datas.STRING})).list(new Object[]{Formats.TIMESTAMP.parseValue(jTextField4.getText()),Formats.TIMESTAMP.parseValue(jTextField5.getText())});

               for(Object[] obj:tlist){
                  cmodel.add(obj[0]);
               }
               jComboBox1.setModel(cmodel);
             
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(jTextField4.getText().isEmpty() == true && jTextField5.getText().isEmpty() == true){

             JOptionPane.showMessageDialog(null, "Please Enter both start an end date");
         }
         else if(jTextField4.getText().isEmpty() == true){

             JOptionPane.showMessageDialog(null, "Please Enter Start Date");
         }
         else if(jTextField5.getText().isEmpty() == true){
             
             JOptionPane.showMessageDialog(null, "Please Enter End Date");
         }
         else if(jTextField4.getText().isEmpty() == false && jTextField5.getText().isEmpty() == false){
            displaysequence();  //wait dialog
         }
           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try{
          if(jComboBox1.getSelectedItem() != null){
             loadreport(jTextField4.getText(),jTextField5.getText(),jComboBox1.getSelectedItem().toString());
          }else{
            JOptionPane.showMessageDialog(null, "Please select closed sequence!!");
          }
      }catch(Exception e){
          e.printStackTrace();
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void loadreport(String sdateFrom, String edateTo,String closeseq){

            Date sdate = new Date();
            Date edate = new Date();
            try{
                sdate = getDate(sdateFrom);
                edate = getSecondDate(edateTo);
            }catch(Exception e1){
                e1.printStackTrace();
            }
        try{
            

             List<Object[]> tlist=(ArrayList<Object[]>) new PreparedSentence(m_App.getSession(), "select closedcheck,r.id,sdate,edate,p.receipt,p.total,p.payment,p.puser,p.customer from (select host +' : ' + hostsequence as closedcheck, datestart as sdate,dateend as edate from closedcash) as ali,receipts_arv r ,payments_arv p where closedcheck = r.closecashseq and sdate> ? and edate< ? and p.receipt = r.id  and closedcheck = ? order by r.id"
                    , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
                    , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING})).list(new Object[]{Formats.TIMESTAMP.parseValue(jTextField4.getText()),Formats.TIMESTAMP.parseValue(jTextField5.getText()),jComboBox1.getSelectedItem().toString()});

               for(Object[] obj:tlist){
                
                   System.out.println(obj[0]+","+obj[1]+","+obj[2]+","+obj[3]+","+obj[4]+","+obj[5]);
               }
               
              Map reportparam=new HashMap();
              reportparam.put("companyName",m_App.getSession().getCompanyName());
              reportparam.put("companyAddress",m_App.getSession().getCompanyAddress());
              reportparam.put("closedcheck", jComboBox1.getSelectedItem());
              reportparam.put("sdate", sdate);
              reportparam.put("edate", edate);

              DataSourceProvider dsource = new DataSourceProvider(tlist);

            //  Datasource2 ds=new Datasource2(tlist,edate,sdate);
            //  dsource.setDataSource(ds);
             JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/classic.jrxml", reportparam, false, dsource, true, "ClosedCash");


        }catch(Exception e){
            e.printStackTrace();
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
       d.setTime(cal.getTimeInMillis());
       return d;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enddate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton startdate;
    // End of variables declaration//GEN-END:variables



}
