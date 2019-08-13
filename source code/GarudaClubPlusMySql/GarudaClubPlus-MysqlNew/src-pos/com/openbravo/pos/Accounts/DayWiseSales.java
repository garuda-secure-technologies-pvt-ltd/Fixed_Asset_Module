

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.HallBookingMaster;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.DataSourceForProdCatelog2;
import com.openbravo.pos.inventory.ProductCatelog2;
import com.openbravo.pos.inventory.ProductCatelog2TableModel;
import com.openbravo.pos.sms.EmailMaster;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperPrint;
import java.lang.Object;
import java.lang.String;
import java.util.UUID;

public class DayWiseSales extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

  
    
    
     private AppView m_App;
     private List staxlist;
     private DataLogicSales m_dlSales;
     private ComboBoxValModel  staxmodel;
     private List<DaywiseSalesReportTableModel.SalesInfo> SaleList;
     private waitDialog w;
     
     
     DaywiseSalesReportTableModel DaywiseSalesReport_Table_Model;
     
     
    public DayWiseSales() {
        initComponents();
    }

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        taxcat_combo = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        daywise_radio = new javax.swing.JRadioButton();
        period_radio = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        Month_cal = new javax.swing.JButton();
        todate_cal = new javax.swing.JButton();
        fromdate_cal = new javax.swing.JButton();
        generate_Report = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(114, 27, 27));
        jLabel1.setText("Tax wise Sale Report ");

        taxcat_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        daywise_radio.setText("Daily");
        daywise_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                daywise_radioItemStateChanged(evt);
            }
        });

        period_radio.setText("Period");
        period_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_radioItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel5.setText("From :");

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("To:");

        Month_cal.setText("Day wise");
        Month_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calActionPerformed(evt);
            }
        });

        todate_cal.setText("To Date");
        todate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calActionPerformed(evt);
            }
        });

        fromdate_cal.setText("From Date");
        fromdate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calActionPerformed(evt);
            }
        });

        generate_Report.setText("Generate Report");
        generate_Report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_ReportActionPerformed(evt);
            }
        });

        jButton1.setText("Generate Report ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(152, 1, 1));
        jTextArea1.setRows(5);
        jTextArea1.setText("Note 1:    The total in the report for column 'No. of invoice & Product amount' \n                  may not be the total of individual figures above the total as some bill/product \n                  may attract multiple taxes.\n\nNote 2 :  This does not include bills generated through Rooms & Hall Booking Menu. \n                   For such bills go to menu \n\tRooms & Halls ==> Reports & Bills ==> Billed reports . \t ");
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(daywise_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(period_radio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fromdate_text, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addComponent(todate_text))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(taxcat_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(generate_Report, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daywise_radio)
                    .addComponent(period_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todate_cal))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taxcat_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generate_Report)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        taxcat_combo.setVisible(false);
        jButton2.setVisible(false);
        fromdate_text.setEditable(false);
        todate_text.setEditable(false);
        generate_Report.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    List SelectedList = new ArrayList();
    
    private void daywise_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_daywise_radioItemStateChanged
        if(daywise_radio.isSelected()){
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);

            fromdate_text.setText(null);
            todate_text.setText(null);
            
        }
        else{
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
    }//GEN-LAST:event_daywise_radioItemStateChanged

    private void period_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_radioItemStateChanged
        if(period_radio.isSelected()){
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
        else{
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
    }//GEN-LAST:event_period_radioItemStateChanged

    private void Month_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            //cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));

            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.DATE, 1);

            //cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());

            todate_text.setText(Formats.TIMESTAMP.formatValue(date));

           

        }
    }//GEN-LAST:event_Month_calActionPerformed

    private void todate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calActionPerformed
        Date date;
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                todate_text.setText(Formats.TIMESTAMP.formatValue(date));

               
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_todate_calActionPerformed

    private void fromdate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
            todate_text.setText(null);
        
        }
    }//GEN-LAST:event_fromdate_calActionPerformed

    Date FromDate = null;
    Date ToDate = null;
    List<Object> BillIdsList  = new ArrayList();
    
    private void generate_ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_ReportActionPerformed
     
     /*   
        
        if(fromdate_text.getText().trim().length()>0 && todate_text.getText().trim().length()>0){
          if(SelectedList.size()>0){
         
         try{
            FromDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
            ToDate =   (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
              
            
                BillIdsList = getBillIDList(m_App, FromDate, ToDate);
            
                 jList1.setModel(new DayWiseSales.ItemsListModel2(BillIdsList));
                
                
               String FinalStr = "('";
               
               
               for(int i=0;i<SelectedList.size();i++){
                   String name = SelectedList.get(i).toString();
                   String TaxCatID = getTaxCatID(name);
                   if(i==(SelectedList.size()-1)){
                       
                       FinalStr = FinalStr+TaxCatID+"')";
                       
                   }
                   else{
                     
                       FinalStr = FinalStr+TaxCatID+"','";
                   }
                   
               }
                
               String ID = null;
          
               System.out.println("Final Str : "+FinalStr);
              
          
                SaleList = new ArrayList<DaywiseSalesReportTableModel.SalesInfo>();
              
                try {
                      DaywiseSalesReport_Table_Model  = DaywiseSalesReportTableModel.LoadTaxSaleInfo(m_App , FromDate , ToDate  );
                    } 

                 catch (BasicException ex) {
                         Logger.getLogger(DayWiseSales.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 SaleList  =  (List<DaywiseSalesReportTableModel.SalesInfo>) DaywiseSalesReport_Table_Model.getList();
                         
          
          
                  
               String RPH = "From : "+fromdate_text.getText() + "  To  : "+todate_text.getText()+"." ;
                  
               DataSourceProvider data1 = new DataSourceProvider(SaleList);
               DataSourceForDaywiseSaleReport dsfc = new DataSourceForDaywiseSaleReport(SaleList , RPH);
               data1.setDataSource(dsfc);
               Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
               reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
              
               
               System.out.println(RPH);
               reportparams.put("date",new Date());
               reportparams.put("HEADER",RPH);
                
                
             JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/DaywiseTaxSaleReport.jrxml", reportparams, false, data1, true, null); 
            
           }
          catch(BasicException e)
          {
              
          }
          
          }
          else{
               JOptionPane.showMessageDialog(this, "Please select Tax..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
          }
      }
      else{
           JOptionPane.showMessageDialog(this, "Please select Date..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
          
      }
             */
    }//GEN-LAST:event_generate_ReportActionPerformed

    List<Object> BillItemIdList = new ArrayList();
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       if(fromdate_text.getText().trim().length()>0 && todate_text.getText().trim().length()>0){   
        
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
                                           GenerateReportMain();
					}
				}
			);
            t.start();
            w.showDialog("Please wait, generating report.");
         
         
         
        
      
           
      }
       
      else{
           JOptionPane.showMessageDialog(this, "Please select Date..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
          
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public void GenerateReportMain()
    {
        
        
        
         try{
            FromDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
            ToDate =   (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
            
            
            
            
       /*         BillIdsList = getBillIDList(m_App, FromDate, ToDate);
                
                String TempBillID = "(";
                for(int z=0;z<BillIdsList.size();z++){
                    TempBillID = TempBillID + "'";
                    String x = BillIdsList.get(z).toString();
                    if(z==BillIdsList.size()-1){
                        TempBillID = TempBillID +x+ "'";
                    }
                    else{
                        TempBillID = TempBillID +x+ "',";
                    }
                }
                TempBillID = TempBillID + ")";
                System.out.println(TempBillID);
                
                
             
                 int Delete_Taxline_SALE =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxlines_sale "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                
                
                 int   insert_data6 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxlines_sale (ID ,RECEIPT , TAXID , BASE , AMOUNT ) "
                                                     + " SELECT ID , RECEIPT , TAXID , BASE , AMOUNT  FROM taxlines WHERE RECEIPT in "+TempBillID+" "                           
                                                        , new SerializerWriteBasic(new Datas[]{ })                         
                                                         ).exec(new Object[]{      });                                                                                                

                
            */
                 Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                             @Override      
                             protected Object transact() throws BasicException {   

                                 
                                 
                                 
                                 
                                 
                                     int update_Email_master =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxsalereport "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                 
                                     
                                     
                                     int update_Email_master2 =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxsalereport1 "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                 
                                     
                                     
                                     /*
                                     
                                        for(int i=0 ; i< BillIdsList.size() ; i++){      

                                            String BillID = BillIdsList.get(i).toString();
                                            String WarehouseID = getWarehouse(m_App , BillID);
                                            Double TotBillAmt = getBillTotal(m_App, WarehouseID , FromDate , ToDate);
                                            Double TaxTotal = getTaxTotal(m_App, WarehouseID , FromDate , ToDate);
                                            int TotalBill  =    getTotalNoOfBill(m_App, WarehouseID, FromDate, ToDate);
                                            Double GrandBillTotal = getGrandBillAmount(m_App, FromDate, ToDate);
                                            Double GrandTaxTotals = getGrandTaxTotal(m_App, FromDate, ToDate);
                                           // BillItemIdList = getBillItemIds(m_App, BillID, FromDate, ToDate);
                                                    
                                                    
                                            int Flag = GetBillFromTaxLinesIfPresent(m_App, BillID);
                                            
                                            if(Flag==0){
                                                
                                                 Double BillAmtTemp =  getIndividualBill(m_App, BillID);
                                                 if(BillAmtTemp>0.00){
                                                            int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE , TOTBILLAMT , TOTTAXAMT , TOTALBILLS , GRANDBILLTOTAL , GRANDTAXTOTAL ) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE ,Datas.DOUBLE , Datas.INT , Datas.DOUBLE , Datas.DOUBLE})                         
                                                                                  ).exec(new Object[]{UUID.randomUUID().toString() ,   BillID , "001"  ,BillAmtTemp  , 0.00 , WarehouseID ,TotBillAmt , 0.00 ,  TotalBill , GrandBillTotal ,GrandTaxTotals });                                                                                                
                                                 
                                                 }
                                                
                                            }
                                            else{
                                                
                                                int   insert_data2 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE , TOTBILLAMT , TOTTAXAMT , TOTALBILLS  , GRANDBILLTOTAL , GRANDTAXTOTAL ) "
                                                     + " SELECT ID , RECEIPT , TAXID , BASE , AMOUNT , ? , ? , ? ,? ,? , ? FROM taxlines_sale WHERE RECEIPT=? AND AMOUNT>=0  "                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE , Datas.DOUBLE , Datas.INT , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })                         
                                                         ).exec(new Object[]{   WarehouseID ,TotBillAmt , TaxTotal , TotalBill , GrandBillTotal , GrandTaxTotals ,  BillID   });                                                                                                

 
                                                
                                                int   insert_data3 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE , TOTBILLAMT , TOTTAXAMT , TOTALBILLS  , GRANDBILLTOTAL , GRANDTAXTOTAL ) "
                                                     + " SELECT ID , RECEIPT , TAXID , BASE*(-1) , AMOUNT , ? , ? , ? ,? ,? , ? FROM taxlines_sale WHERE RECEIPT=? AND AMOUNT<0  "                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE , Datas.DOUBLE , Datas.INT , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })                         
                                                         ).exec(new Object[]{   WarehouseID ,TotBillAmt , TaxTotal , TotalBill , GrandBillTotal , GrandTaxTotals ,  BillID   });                                                                                                

                                                
                                              //  Double Amount=0.00;
                                              //  for(int y=0;y<BillItemIdList.size() ; y++){
                                              //      String BillItemID = BillItemIdList.get(y).toString();
                                               //      Amount =Amount + getBillItemAmountForTaxStandard(m_App, BillItemID);
                                                    
                                                    
                                               // }
                                               // if(Amount>0){
                                                        
                                                        
                                                //       int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE , TOTBILLAMT , TOTTAXAMT , TOTALBILLS , GRANDBILLTOTAL , GRANDTAXTOTAL ) VALUES (?,?,?,?,?,?,?,?,?,?,?)"                           
                                                  //                                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE ,Datas.DOUBLE , Datas.INT , Datas.DOUBLE , Datas.DOUBLE})                         
                                                  //                                ).exec(new Object[]{UUID.randomUUID().toString() ,   BillID , "001"  , Amount  , 0.00 , WarehouseID ,TotBillAmt , 0.00 ,  TotalBill , GrandBillTotal ,GrandTaxTotals });                                                                                                
                                                 
                                              //  }
                                                
                                            }
                                            
                                        }  
                                  
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        */
                                        
                                        
                                        
                  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
                  int update_bill_sales =  new PreparedSentence(m_App.getSession(), "DELETE FROM bill_sales "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                      int   insert_data6 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO bill_sales (ID ,customer,place,waiter,floor,amount,createdby,createddate,paid,receipt,taxtotal,closesaleseq,warehouse,initiator) \n" +
                                               "select b.ID ,b.customer,b.place,b.waiter,b.floor,b.amount,b.createdby,b.createddate,b.paid,b.receipt,b.taxtotal,b.closesaleseq,b.warehouse,b.initiator from bill b where b.createddate>=?   and b.createddate<= ?  "     , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP })                         
                                                         ).exec(new Object[]{   FromDate , ToDate   });                                                                                                                                               
                       int   insert_data61 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO bill_sales (id ,customer,place,waiter,floor,amount,createdby,createddate,paid,receipt,taxtotal,closesaleseq,warehouse,initiator) \n" +
                                               "select b.ID ,b.customer,b.place,b.waiter,b.floor,b.amount,b.createdby,b.createddate,b.paid,b.receipt,b.taxtotal,b.closesaleseq,b.warehouse,b.initiator from bill_arv b where b.createddate>=?   and b.createddate<= ?  "     , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP })                         
                                                         ).exec(new Object[]{   FromDate , ToDate   }); 
                     
                         int Delete_Taxline_SALE1 =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxlines_sale "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                
                
                 int   insert_data63 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxlines_sale (ID ,RECEIPT , TAXID , BASE , AMOUNT ) "
                                                     + " SELECT ID , RECEIPT , TAXID , BASE , AMOUNT  FROM taxlines WHERE RECEIPT in (select id from bill_sales) "                           
                                                        , new SerializerWriteBasic(new Datas[]{ })                         
                                                         ).exec(new Object[]{      });                                                                                                

                
                       
                       int   insert_data62 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE) select t.id, t.receipt, t.taxid,t.base,t.amount , b.warehouse   from taxlines_sale t,\n" +
"bill_sales b where b.id=t.receipt" , new SerializerWriteBasic(new Datas[]{ }) ).exec(new Object[]{      });
                      
                    //commented by pratima        
                /*  int   insert_data6 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE) \n" +
                                                                            "select t.id, t.receipt, t.taxid,t.base,t.amount , b.warehouse   from \n" +
                                                                            "taxlines_sale t, bill b\n" +
                                                                            "where b.id=t.receipt\n" +
                                                                            "union\n" +
                                                                            "select t.id , t.receipt, t.taxid,t.base,t.amount , b.warehouse       from \n" +
                                                                            "taxlines_sale t, bill_arv b\n" +
                                                                            "where b.id=t.receipt "                           
                                                        , new SerializerWriteBasic(new Datas[]{ })                         
                                                         ).exec(new Object[]{      });                                                                                                
*/
                     //commented ended by pratima /////////////////////////////////////////////////////////////////////////////////////////////   
                                        
                                      /*  int   insert_data7a =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE)\n" +
                                                                                                        "select id,id , '001' , 0.00,0.00 , warehouse from bill where id not in (select receipt from taxlines) and\n" +
                                                                                                        " createddate>= ?   and createddate<= ?   \n" +
                                                                                                        "union\n" +
                                                                                                        "select  id,id , '001' , 0.00,0.00 , warehouse from bill_arv where id not in (select receipt from taxlines)\n" +
                                                                                                        "and createddate>= ?   and createddate<= ? "                           
                                                        , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP })                         
                                                         ).exec(new Object[]{   FromDate , ToDate , FromDate , ToDate  });             */                                                                                   

                                 int   insert_data7a =  new PreparedSentence(m_App.getSession()  , "INSERT INTO taxsalereport (ID ,RECEIPT , TAXID , BASE , AMOUNT , WAREHOUSE) select id,id , '001' , 0.00,0.00 , warehouse from bill_sales where id not in (select receipt from taxlines_sale) "                           
                                                        ).exec();             
                                        
                                        ///////////////////////////////////////////////////
                                        int   insert_data7 =  new PreparedSentence(m_App.getSession()  , "insert into taxsalereport1 (warehouse,amount,tax,count)\n" +
                                                                        "select  b.warehouse , IFNULL(SUM(AMOUNT),00) , IFNULL(SUM(TAXTOTAL),0.00) ,  COUNT(*)  from bill_sales  b where\n" +
                                                                        "  b.createddate>= ?   and b.createddate<= ?  \n" +
                                                                        "  group by warehouse\n" 
                                                                                    
                                                        , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP })                         
                                                         ).exec(new Object[]{   FromDate , ToDate   });      
                                        //comented by pratima
                                 /*       int   insert_data7 =  new PreparedSentence(m_App.getSession()  , "insert into taxsalereport1 (warehouse,amount,tax,count)\n" +
                                                                        "select  b.warehouse , IFNULL(SUM(AMOUNT),00) , IFNULL(SUM(TAXTOTAL),0.00) ,  COUNT(*)  from bill  b where\n" +
                                                                        "  b.createddate>= ?   and b.createddate<= ?  \n" +
                                                                        "  group by warehouse\n" +
                                                                        "  union\n" +
                                                                        "  select  b.warehouse , IFNULL(SUM(AMOUNT),00) , IFNULL(SUM(TAXTOTAL),0.00) ,  COUNT(*)  from bill_arv  b where\n" +
                                                                        "  b.createddate>=?   and b.createddate<= ?  \n" +
                                                                        "  group by warehouse"                           
                                                        , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP })                         
                                                         ).exec(new Object[]{   FromDate , ToDate , FromDate , ToDate  });  */                                                                                              
                                        //commented ended by pratima
                                       //////////////////////////////////////////// 
                                        
                                        Double GrandBillTotal = getGrandBillAmount(m_App, FromDate, ToDate);
                                        Double GrandTaxTotals = getGrandTaxTotal(m_App, FromDate, ToDate);
                                        
                                        int update_taxsale_report =  new PreparedSentence(m_App.getSession(), " update taxsalereport  set totbillamt = (select ifnull(sum(amount),0.00)  from taxsalereport1 t1  \n" +
                                                                                                                "where t1.warehouse=taxsalereport.warehouse)"
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                        
                                        int update_taxsale_report2 =  new PreparedSentence(m_App.getSession(), " update taxsalereport  set tottaxamt = (select ifnull(sum(tax),0.00)  from taxsalereport1 t1\n" +
                                                                                                                "where t1.warehouse=taxsalereport.warehouse)"
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                
                                        int update_taxsale_report3 =  new PreparedSentence(m_App.getSession(), " update taxsalereport  set totalbills = (select ifnull(sum(count),0 )  from taxsalereport1 t1\n" +
                                                                                                                "where t1.warehouse=taxsalereport.warehouse)"
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                  
                                         int update_taxsale_report4 =  new PreparedSentence(m_App.getSession(), " update taxsalereport  set grandbilltotal = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE })).exec
                                                                            (new Object[]{ GrandBillTotal  });
                  
                                        
                                         int update_taxsale_report5 =  new PreparedSentence(m_App.getSession(), " update taxsalereport  set grandtaxtotal = ?  "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE })).exec
                                                                            (new Object[]{ GrandTaxTotals  });
                  
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                       generateReport(); 
                                          int update_Email_master1 =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxsalereport "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                 
                                     
                                     
                                     int update_Email_master21 =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxsalereport1 "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                      int update_bill_sales1 =  new PreparedSentence(m_App.getSession(), "DELETE FROM bill_sales "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                        int Delete_Taxline_SALE11 =  new PreparedSentence(m_App.getSession(), "DELETE FROM taxlines_sale "
                                                                           , new SerializerWriteBasic(new Datas[]{  })).exec
                                                                            (new Object[]{   });
                                       w.hideDialog(); 
                                  
                                   return null;                                      
                                     }                            
                                 };                 

                                 try {                 
                                     t.execute();          

                                   //  JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                   //  reset();
                                   //  loaddata();
                                     w.hideDialog();

                                 }
                              catch (BasicException ex) {                    
                                         Logger.getLogger(DayWiseSales.class.getName()).log(Level.SEVERE, null, ex);             
                                         ex.printStackTrace();
                                         new MessageInf(ex).show(new JFrame());
                                         w.hideDialog();
                              } 

        }
        catch(BasicException ex) {
             w.hideDialog();
             Logger.getLogger(DayWiseSales.class.getName()).log(Level.SEVERE, null, ex);             
             ex.printStackTrace();
             new MessageInf(ex).show(new JFrame());
        }
        
    }    
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(taxcat_combo.getSelectedIndex()!=-1){

            String SelectedTax = taxcat_combo.getSelectedItem().toString();
            List<Object> ProdListOfFstID = new ArrayList<Object>();
            if(SelectedList.size()==0){
                SelectedList.add(SelectedTax);
            }
            else{
                try{

                    Object[] obj15 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM TAXCATEGORIES where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(SelectedTax);
                    if(obj15!=null){

                        String SecondTaxCatID = obj15[0].toString();
                        String FstTaxSelected = SelectedList.get(0).toString();
                        String SecondTaxSelected = taxcat_combo.getSelectedItem().toString();
                        String FstTaxSelectedID = null;
                        Object[] obj16 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM TAXCATEGORIES where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(FstTaxSelected);
                        if(obj16!=null){

                            FstTaxSelectedID = obj16[0].toString();
                            ProdListOfFstID = new ArrayList<Object>();
                            try{
                                ProdListOfFstID = GetProductIdListWithFstTax(m_App, FstTaxSelectedID);
                            }
                            catch(BasicException ex){
                                ex.printStackTrace();
                                new MessageInf(ex).show(new JFrame());
                                Logger.getLogger(DayWiseSales.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            int Flag=1;

                            for(int i=0;i<ProdListOfFstID.size();i++){

                                String ProdId = ProdListOfFstID.get(i).toString();

                                ProdId = "'"+ProdId+"'";
                                // System.out.println ("Prod Id "+ProdId);
                                Object[] obj20 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS where TAXCAT=? AND ID= "+ProdId, SerializerWriteString.INSTANCE,  new SerializerReadBasic(new Datas[]{Datas.STRING})).find(SecondTaxCatID);
                                Object[] obj21 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS where TAXCAT2=? AND ID= "+ProdId, SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(SecondTaxCatID);
                                Object[] obj22 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS where TAXCAT3=? AND ID= "+ProdId, SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(SecondTaxCatID);

                                if(obj20==null && obj21==null && obj22==null){

                                    Flag=0;
                                }
                                else{
                                    Flag=1;
                                    String DupTaxProdId = null;
                                    if(obj20!=null){
                                        DupTaxProdId = obj20[0].toString();
                                    }
                                    if(obj21!=null){
                                        DupTaxProdId = obj21[0].toString();
                                    }
                                    if(obj22!=null){
                                        DupTaxProdId = obj22[0].toString();
                                    }
                                    if(DupTaxProdId!=null){
                                        Object[] obj99 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT REFERENCE FROM PRODUCTS where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DupTaxProdId);
                                        Object[] obj98 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PRODUCTS where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DupTaxProdId);

                                        if(obj99!=null)
                                        {
                                            String prodname = obj98[0].toString();
                                            String Reference = obj99[0].toString();

                                            JOptionPane.showMessageDialog(this, "Sorry , Product no : "+Reference+"  Name : "+prodname+" has same tax.  \n So unable to add tax with selected Tax ", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                                            break;
                                        }

                                    }

                                }

                            }

                            if(Flag==0){
                                SelectedList.add(SelectedTax);
                            }

                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Sorry , First Tax selected record not found..!! ", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Sorry , No Taxcategory Found..!! ", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                    }

                }
                catch(BasicException ex){
                    ex.printStackTrace();
                    new MessageInf(ex).show(new JFrame());
                    Logger.getLogger(DayWiseSales.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

           

        }
        else{

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public void generateReport() throws BasicException{
        
    
        
          SaleList = new ArrayList<DaywiseSalesReportTableModel.SalesInfo>();
              
                try {
                      DaywiseSalesReport_Table_Model  = DaywiseSalesReportTableModel.LoadTaxSaleInfo(m_App , FromDate , ToDate );
                    } 

                 catch (BasicException ex) {
                         Logger.getLogger(DayWiseSales.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 SaleList  =  (List<DaywiseSalesReportTableModel.SalesInfo>) DaywiseSalesReport_Table_Model.getList();
                         
          
          
                  
               String RPH = "From : "+fromdate_text.getText() + "  To  : "+todate_text.getText()+"." ;
                  
               DataSourceProvider data1 = new DataSourceProvider(SaleList);
               DataSourceForDaywiseSaleReport dsfc = new DataSourceForDaywiseSaleReport(SaleList , RPH , m_App);
               data1.setDataSource(dsfc);
               Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
               reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
              
               
               System.out.println(RPH);
               reportparams.put("date",new Date());
               reportparams.put("HEADER",RPH);
                
                
             JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/DaywiseTaxSaleReport.jrxml", reportparams, false, data1, true, null); 
            
        
             w.hideDialog();
        
        
    }
    
    
    
    
     private class ItemsListModel2 extends AbstractListModel {

        private java.util.List items2;

        public ItemsListModel2(java.util.List items2) {
            this.items2 = items2;
        }

        @Override
        public int getSize() {
            return items2.size();
        }

        @Override
        public Object getElementAt(int i) {
            
            return items2.get(i);
        }
     }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Month_cal;
    private javax.swing.JRadioButton daywise_radio;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton generate_Report;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JComboBox taxcat_combo;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    // End of variables declaration//GEN-END:variables


 public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
       
         reset();
        loaddata();
       
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       this.m_App = app;
     m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       
      
    }

    public Object getBean() {
         return this;
    }

 public void loaddata() throws BasicException{
     
     
            staxlist = m_dlSales.getTaxCategoriesList().list();
            staxlist.add(0, null);
            staxmodel = new ComboBoxValModel(staxlist);
            taxcat_combo.setModel(staxmodel);
            daywise_radio.setSelected(true);
            ButtnGrp();
     
 }
    
public void ButtnGrp(){
    
    ButtonGroup bg = new ButtonGroup();
    bg.add(daywise_radio);
    bg.add(period_radio);
    
}


public void reset(){
    SelectedList = new ArrayList();
    fromdate_text.setText(null);
    todate_text.setText(null);
    daywise_radio.setSelected(true);
    
}

  public List<Object> GetProductIdListWithFstTax(AppView app , String TaxCatID) throws BasicException{
       
      
       TaxCatID = "'"+TaxCatID+"'";
         
         List<Object> Prod_ID_List = new ArrayList<Object>();
          
         Prod_ID_List  = new PreparedSentence(app.getSession(), "SELECT ID FROM PRODUCTS  WHERE TAXCAT= "+TaxCatID+  " OR "+ " TAXCAT2= "+TaxCatID+" OR  TAXCAT3= "+TaxCatID , SerializerWriteString.INSTANCE ,  SerializerReadString.INSTANCE ).list();
       
         return Prod_ID_List;

        
      }
     
  public String getTaxCatID(String Name) throws BasicException{
      
       Object o = null;
       String t = null;
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM TAXCATEGORIES  WHERE NAME =? ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
                 
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
      
      
      
  }

  
  
   public List<Object> getBillIDList(AppView app , Date d1 , Date d2) throws BasicException{
       
      
         String BillID=null; 
         
         List<Object> Bill_ID_List = new ArrayList<Object>();
          
         Bill_ID_List  = new StaticSentence(app.getSession(), "select id from bill where \n" +
                                                                    "BILL.CREATEDDATE <=? AND BILL.CREATEDDATE >=?\n" +
                                                                    "union\n" +
                                                                    "select id from bill_ARV where \n" +
                                                                    "BILL_ARV.CREATEDDATE <=? AND BILL_ARV.CREATEDDATE >=? " ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP  , Datas.TIMESTAMP  , Datas.TIMESTAMP  , Datas.TIMESTAMP  }), SerializerReadString.INSTANCE).list(new Object[]{d2 , d1 , d2 , d1 } );

         return Bill_ID_List;

        
      }
     
  
   public String getWarehouse(AppView app , String BillID) throws BasicException{
       
        Object o = null;
       String t = null;
        
       
           o  = new StaticSentence(app.getSession(), "select WAREHOUSE from bill where \n" +
                                                                    "BILL.ID=?  \n" +
                                                                    "union\n" +
                                                                    "select WAREHOUSE from bill_ARV where \n" +
                                                                    "BILL_ARV.ID=?  " ,new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.STRING  }), SerializerReadString.INSTANCE).find(new Object[]{BillID , BillID } );

         if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
   }
  
  
   
   
   public Double getBillTotal(AppView app , String WarehouseID ,Date  FromDate ,Date ToDate) throws BasicException{
       
        Object o = null;
        Double t = null;
        Object o1 =null;
        Double t1= null;
        Double FinalT=0.00;
        
       
           o  = new StaticSentence(app.getSession(), "select IFNULL(SUM(AMOUNT),00) from bill where \n" +
                                                     "BILL.WAREHOUSE=? and bill.createddate>=? and bill.createddate<=?    " 
                                                    ,new SerializerWriteBasic(new Datas[]{ Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{ WarehouseID , FromDate , ToDate  } );

          o1  = new StaticSentence(app.getSession(), "select IFNULL(SUM(AMOUNT),0.00) from bill_ARV where \n" +
                                                     "bill_arv.WAREHOUSE=? and bill_arv.createddate>=? and bill_arv.createddate<=?  "
                                                      ,new SerializerWriteBasic(new Datas[]{ Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{ WarehouseID , FromDate , ToDate  } );
  
           
           
           
           
         if(o!=null){
            t = Double.parseDouble(o.toString());
            FinalT = FinalT+t;
         }
        
         if(o1!=null){
             t1 = Double.parseDouble(o1.toString());
             FinalT = FinalT+t1;
         }
         
         
        return FinalT;  
   }
  
      public Double getTaxTotal(AppView app , String WarehouseID , Date FromDate , Date ToDate) throws BasicException{
       
        Object o = null;
        Double t = null;
        Object o1 =null;
        Double t1= null;
        Double FinalT=0.00;
           o  = new StaticSentence(app.getSession(), "select IFNULL(SUM(TAXTOTAL),0.00) from bill where \n" +
                                                        "BILL.WAREHOUSE=? and bill.createddate>=? and bill.createddate<=?   " 
                                                        ,new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{WarehouseID , FromDate , ToDate  } );

           o1  = new StaticSentence(app.getSession(), "select IFNULL(SUM(TAXTOTAL),0.00) from bill_ARV where \n" +
                                                      "bill_arv.WAREHOUSE=? and bill_arv.createddate>=? and bill_arv.createddate<=?   " 
                                                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP  }), SerializerReadString.INSTANCE).find(new Object[]{WarehouseID , FromDate , ToDate  } );

           
           
         if(o!=null){
            t = Double.parseDouble(o.toString());
            FinalT = FinalT+t;
         }
        
         if(o1!=null){
             t1 = Double.parseDouble(o1.toString());
             FinalT = FinalT+t1;
         }
         
         
        return FinalT;  
       
   }
  
    public int getTotalNoOfBill(AppView app , String WarehouseID , Date FromDate , Date ToDate) throws BasicException{
       
        Object o = null;
        Object o1=null;
        int t = 0;
        int t1=0;
        int t2=0;
       
           o  = new StaticSentence(app.getSession(), "select COUNT(*) from bill where \n" +
                                                                    "BILL.WAREHOUSE=? and bill.createddate>=? and bill.createddate<=?  " 
                                                                        ,new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{WarehouseID , FromDate , ToDate  } );

         
           o1 = new StaticSentence(app.getSession(), "select COUNT(*) from bill_ARV where \n" +
                                                      "bill_arv.WAREHOUSE=? and bill_arv.createddate>=? and bill_arv.createddate<=?   " ,new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{WarehouseID , FromDate , ToDate  } );
  
           
           
           
         if(o!=null){
            t = Integer.parseInt(o.toString());
           
          }
         
         if(o1!=null){
            t1 = Integer.parseInt(o1.toString());
           
          }
          t2 = t1+t;
        return t2;
   }
  
   
    public Double getIndividualBill(AppView app , String BillID) throws BasicException{
       
        Object o = null;
       Double t = null;
        
       
           o  = new StaticSentence(app.getSession(), "select AMOUNT from bill where \n" +
                                                     "BILL.ID=? \n" +
                                                     "union\n" +
                                                     "select AMOUNT from bill_ARV where \n" +
                                                     "bill_arv.ID=?   " ,new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.STRING   }), SerializerReadString.INSTANCE).find(new Object[]{BillID , BillID  } );

         if(o!=null){
            t = Double.parseDouble(o.toString());
            return t;
        }
        else{
            return 0.00;
        }
   }
    
    
    
      public int GetBillFromTaxLinesIfPresent(AppView app , String BillID) throws BasicException{
       
        Object o = null;
       int t = 0;
        
       
           o  = new StaticSentence(app.getSession(), "select ID from TAXLINES where \n" +
                                                                    " RECEIPT=? " ,new SerializerWriteBasic(new Datas[]{ Datas.STRING  }), SerializerReadString.INSTANCE).find(new Object[]{ BillID } );

         if(o!=null){
           
            return 1;
        }
        else{
            return 0;
        }
   }
    

      
      
// GET GRAND BILL AND TAX TOTALS 
      
        public Double getGrandBillAmount(AppView app ,  Date FromDate , Date ToDate) throws BasicException{
       
        Object o = null;
        Double t = null;
        Object o1 =null;
        Double t1= null;
        Double FinalT=0.00;
           o  = new StaticSentence(app.getSession(), "select IFNULL(SUM(AMOUNT),0.00) from bill where \n" +
                                                        " bill.createddate>=? and bill.createddate<=?   " 
                                                        ,new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{FromDate , ToDate  } );

           o1  = new StaticSentence(app.getSession(), "select IFNULL(SUM(AMOUNT),0.00) from bill_ARV where \n" +
                                                      " bill_arv.createddate>=? and bill_arv.createddate<=?   " 
                                                       ,new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  }), SerializerReadString.INSTANCE).find(new Object[]{ FromDate , ToDate  } );

           
           
         if(o!=null){
            t = Double.parseDouble(o.toString());
            FinalT = FinalT+t;
         }
        
         if(o1!=null){
             t1 = Double.parseDouble(o1.toString());
             FinalT = FinalT+t1;
         }
         
         
        return FinalT;  
       
   }
      
      
        
  public Double getGrandTaxTotal(AppView app ,  Date FromDate , Date ToDate) throws BasicException{
       
        Object o = null;
        Double t = null;
        Object o1 =null;
        Double t1= null;
        Double FinalT=0.00;
           o  = new StaticSentence(app.getSession(), "select IFNULL(SUM(TAXTOTAL),0.00) from bill where \n" +
                                                        " bill.createddate>=? and bill.createddate<=?   " 
                                                        ,new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }), SerializerReadString.INSTANCE).find(new Object[]{FromDate , ToDate  } );

           o1  = new StaticSentence(app.getSession(), "select IFNULL(SUM(TAXTOTAL),0.00) from bill_ARV where \n" +
                                                      " bill_arv.createddate>=? and bill_arv.createddate<=?   " 
                                                       ,new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  }), SerializerReadString.INSTANCE).find(new Object[]{ FromDate , ToDate  } );

           
           
         if(o!=null){
            t = Double.parseDouble(o.toString());
            FinalT = FinalT+t;
         }
        
         if(o1!=null){
             t1 = Double.parseDouble(o1.toString());
             FinalT = FinalT+t1;
         }
         
         
        return FinalT;  
       
   }        
        
// GET LIST OF BILL ITEM IDS
  
   
   public List<Object> getBillItemIds(AppView app ,String BillID ,  Date d1 , Date d2) throws BasicException{
       
         
         List<Object> Bill_ID_List = new ArrayList<Object>();
          
         Bill_ID_List  = new StaticSentence(app.getSession(), "select BT.id from billitem BT , BILL B , PRODUCTS P where \n" +
                                                              "B.CREATEDDATE <=? AND B.CREATEDDATE >=?  and B.ID=BT.PARENTID AND BT.PRODUCT=P.ID AND P.TAXCAT='001' AND B.ID=? \n" +
                                                              "union\n" +
                                                              "select BT.id from billitem_arv BT , BILL_ARV B , PRODUCTS P where \n" +
                                                              "B.CREATEDDATE <=? AND B.CREATEDDATE >=?  and B.ID=BT.PARENTID AND BT.PRODUCT=P.ID AND P.TAXCAT='001' AND B.ID=? " ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.STRING }), SerializerReadString.INSTANCE).list(new Object[]{d2 , d1 , BillID , d2 , d1 , BillID } );

         return Bill_ID_List;

        
      } 

   // get amount from billItem total 
   
    public Double getBillItemAmountForTaxStandard(AppView app ,  String BillitemID) throws BasicException{
       
        Object o = null;
        Double t = null;
        Object o1 =null;
        Double t1= null;
        Double FinalT=0.00;
           o  = new StaticSentence(app.getSession(), "select IFNULL(TOTAL,0.00) from billitem where \n" +
                                                        " id=?   " 
                                                        ,new SerializerWriteBasic(new Datas[]{ Datas.STRING   }), SerializerReadString.INSTANCE).find(new Object[]{BillitemID  } );

           o1  = new StaticSentence(app.getSession(), "select IFNULL(TOTAL,0.00) from billitem_ARV where \n" +
                                                      " billitem_arv.id=? " 
                                                       ,new SerializerWriteBasic(new Datas[]{ Datas.STRING   }), SerializerReadString.INSTANCE).find(new Object[]{ BillitemID } );

           
           
         if(o!=null){
            t = Double.parseDouble(o.toString());
            FinalT = FinalT+t;
         }
        
         if(o1!=null){
             t1 = Double.parseDouble(o1.toString());
             FinalT = FinalT+t1;
         }
         
         
        return FinalT;  
       
   }        
   
   
   
}
