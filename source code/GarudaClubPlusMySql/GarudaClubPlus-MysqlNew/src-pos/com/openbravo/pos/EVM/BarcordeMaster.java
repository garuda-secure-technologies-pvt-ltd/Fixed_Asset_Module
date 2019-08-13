

package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;



import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.DevicePrinter;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import javax.swing.JComponent;
import com.openbravo.pos.util.BarcodeImage;
import com.openbravo.pos.util.StringUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.openbravo.pos.printer.ticket.PrintItemImage;
import net.sf.jasperreports.engine.util.JRFontUtil;
import org.xml.sax.Attributes;
import java.util.Random;
import javax.swing.JOptionPane;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.QTDetails;
import com.openbravo.pos.reports.QTDetailsReportTableModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.io.File;
import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;


public class BarcordeMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

      private AppView m_App;
      private CustomerInfo customerInfo;
      private DataLogicSales m_dlSales = null;
      private DataLogicCustomers dlCustomers;
      private DataLogicFacilities dmang;
      public Date MinDateForDue = null;
      public Double MinDueAmountForVote=0.00;
      
      
      
    public BarcordeMaster() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        clublogo_label = new javax.swing.JLabel();
        clubname_label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name_text = new javax.swing.JTextField();
        memno_text = new javax.swing.JTextField();
        memtype_text = new javax.swing.JTextField();
        due_text = new javax.swing.JTextField();
        updateDue_btn = new javax.swing.JButton();
        poweredby_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        barcode_btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Barcode_label = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        cardno_text = new javax.swing.JTextField();
        reset_btn = new javax.swing.JButton();
        memPhoto_label = new javax.swing.JLabel();
        memSign_label = new javax.swing.JLabel();

        clubname_label.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        clubname_label.setForeground(new java.awt.Color(168, 22, 22));
        clubname_label.setText("Club Name");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Membership Name : ");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Membership No. : ");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Membership Type : ");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Due Amount : ");

        name_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        memno_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        memno_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memno_textActionPerformed(evt);
            }
        });
        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        memtype_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        due_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        due_text.setForeground(new java.awt.Color(69, 143, 51));

        updateDue_btn.setForeground(new java.awt.Color(6, 42, 239));
        updateDue_btn.setText("Update Due Amt.");
        updateDue_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDue_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 39, 241));
        jLabel1.setText("Member Details ");

        barcode_btn.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        barcode_btn.setForeground(new java.awt.Color(152, 22, 22));
        barcode_btn.setText("Generate Voting Slip");
        barcode_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcode_btnActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(9, 102, 10));
        jButton2.setText("Swipe Member Card");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Enter Membership no.");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cardno_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardno_textActionPerformed(evt);
            }
        });

        reset_btn.setText("Reset");
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(179, 179, 179)
                                .addComponent(memPhoto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(due_text, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(memtype_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(memno_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(memSign_label, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(204, 204, 204))
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(updateDue_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(Barcode_label, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(barcode_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(poweredby_label, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clublogo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clubname_label, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(cardno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGap(264, 264, 264)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clubname_label)
                    .addComponent(clublogo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(9, 9, 9)
                        .addComponent(cardno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(name_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(memtype_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(due_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(updateDue_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(memPhoto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memSign_label, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)))
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcode_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poweredby_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Barcode_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        clublogo_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/clublogoevm.jpg"))); // NOI18N
        name_text.setEditable(false);
        memno_text.setEditable(false);
        memtype_text.setEditable(false);
        due_text.setEditable(false);
        updateDue_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        poweredby_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N
        barcode_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/barcode.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void memno_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memno_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memno_textActionPerformed
   
    PrintItemImage pg ;
    int Srno = 0;
    String RecSeries = null;
    String PsSequenceNo=null;
    
    String AlreadyPresId=null;
    FileInputStream BarcodeFileInput = null;
    
    private void barcode_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_btnActionPerformed
        
      
       if(customerInfo !=null  ){
           if(due_text.getText()!=null && due_text.getText().trim().length()>0){
               if(MinDueAmountForVote!=-100.96 ){
                  Double CurrDebt = Double.parseDouble(due_text.getText());
                  if(CurrDebt<MinDueAmountForVote){
               
               
        
            String UserID = m_App.getAppUserView().getUser().getId();
            
            try{
                Srno = getSequencePSNO();
                RecSeries = getReceiptSeries();
                PsSequenceNo = RecSeries+"-"+Srno;
                AlreadyPresId = getMemIdAlreadyBarCodeGenerated(customerInfo.getId());
            }
            catch(BasicException e){
                
            }
            catch(ParseException e){
                
            }
       
            
              
                
            
            
            
            if(Srno!=0 && RecSeries!=null ){
       
                if(AlreadyPresId==null){
                
                
                
                Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                          
                            Random r = new Random();
                            int max=999999999;
                            int min=111111111;
                            int randomnumber = r.nextInt(max - min + 1) + min;
                            String barcodecods = String.valueOf(randomnumber);

                            Image bg = BarcodeImage.getBarcode128(barcodecods);
                            BufferedImage buffered = (BufferedImage) bg;
                            ImageIcon icon = new ImageIcon(bg); 
                            File outputfile = new File("out.jpg");
                            
                            
                             private List<QTDetailsReportTableModel.QTInfo> QtInfo_list;
                             DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                                DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                                //data1.setDataSource(dsfc);
                                Map reportparams = new HashMap();
                               // reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                              //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                //String h = "( QT details for QTs  From : "+qtFromdate_text.getText()+" to  : "+ QtToDate_Text.getText()+ "  )";
                               // reportparams.put("HEADING",h);
                               // reportparams.put("WAREHOUSE",WarehouseName);
                               // reportparams.put("date",new Date());
                                

                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/barcode2.jrxml", reportparams, false, data1, true, null); 
                           

                            //try {
                                   
                                    
                                   

                              //} catch (FileNotFoundException ex) {
                             //     Logger.getLogger(BarcordeMaster.class.getName()).log(Level.SEVERE, null, ex);
                             // }

                    
                             int active=1;
                     
                           @Override      
                           protected Object transact() throws BasicException { 
          
                                    Barcode_label.setIcon(icon);
                                    
                                    try{
                                            ImageIO.write(buffered, "jpg",outputfile);
                                       }
                                     catch(IOException e){

                                    }
                                    
                                    
                                    try{
                                    
                                     BarcodeFileInput = new FileInputStream(outputfile.getAbsoluteFile());
                                    }
                                    catch(FileNotFoundException ex){
                                        
                                    }
                                    
                                    
                                   
                                    
                                     int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO evm_barcode (ID  , PSID , PS_SRNO , BARCODE ,CRBY , CRDATE, CRHOST  ) VALUES (?,?,?,?,?,?,?)"                           
                                              , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING  })                         
                                               ).exec(new Object[]{UUID.randomUUID().toString(),"1", PsSequenceNo , barcodecods ,  m_App.getAppUserView().getUser().getId(),new Date(), m_App.getProperties().getHost() });                                                                                                


                                    int   insert_data2 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO evm_membervote (ID  , MEMID , PSID , SRNO ,ACTIVE  ) VALUES (?,?,?,?,?)"                           
                                              , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.INT })                         
                                               ).exec(new Object[]{UUID.randomUUID().toString(),customerInfo.getId(),  m_App.getAppUserView().getUser().getId(), PsSequenceNo , 1 });                                                                                                

                                     
                                    int   insert_data3 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO evm_slip (BARCODE  , PSID , SRNO  ) VALUES (?,?,?)"                           
                                              , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.STRING  })                         
                                               ).exec(new Object[]{barcodecods ,  m_App.getAppUserView().getUser().getId() , PsSequenceNo });                                                                                                

                                    
                                    
                                //     int x3 = new StaticSentence(m_App.getSession()
                                    //            , "DELETE  FROM resources  WHERE NAME=?"
                                    //            , new SerializerWriteBasic(new Datas[] { Datas.STRING}))
                                    //            .exec(new Object[] {  "Barcode.Image.TC"});
                                    
                                    
                                    
                                    
                                    int x = new StaticSentence(m_App.getSession()
                                                , "UPDATE sequencedetail SET RMAX=RMAX+1 WHERE USERNAME=?"
                                                , new SerializerWriteBasic(new Datas[] {Datas.STRING }))
                                                .exec(new Object[] { m_App.getAppUserView().getUser().getId()});
                                    
                                    
                                    
                                 //   int   insert_data6 =  new PreparedSentence(m_App.getSession()  , "INSERT INTO resources (ID  , NAME , RESTYPE , CONTENT  ) VALUES (?,?,?,?)"                           
                                   //         , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.INT  , Datas.OBJECT})                         
                                    //           ).exec(new Object[]{UUID.randomUUID().toString() ,"Barcode.Image.TC", 1 , BarcodeFileInput });                                                                                                

                                    
                                    
                                    printbarcoder(barcodecods , PsSequenceNo);

                                    JOptionPane.showMessageDialog(null, " Updated Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);


                                   return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                        reset();   
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
                    
                     
                }
                else{
                     JOptionPane.showMessageDialog(null, " Voting Slip Already Issued to Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
           
            }
            else{
                  JOptionPane.showMessageDialog(null, " Please enter Sequence Series", "Warning", JOptionPane.WARNING_MESSAGE);
            }
           
           
            
            
            
            
            
            
            
            
                  }
                  else{
                       JOptionPane.showMessageDialog(null, "Sorry , due amount exceeds the min. amount.  \n Please update due amount.", "Warning", JOptionPane.WARNING_MESSAGE);
                  }
               }
               else{
                    JOptionPane.showMessageDialog(null, "Please enter minimum due amount in master", "Warning", JOptionPane.WARNING_MESSAGE);
               }
           
           }
           else{
                JOptionPane.showMessageDialog(null, " Due Amount not updated. Please enter due amount first.", "Warning", JOptionPane.WARNING_MESSAGE);
           }
       }
       else{
             JOptionPane.showMessageDialog(null, " Please enter membership details", "Warning", JOptionPane.WARNING_MESSAGE);
       }
       
       
    //  BufferedImage image = new BufferedImage(bg.getWidth(null), bg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    //    pg = new PrintItemImage(image);
       
     
       
    }//GEN-LAST:event_barcode_btnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            cardno_text.requestFocus();
            cardno_text.setText(null);
            memno_text.setText(null);
            name_text.setText(null);
            memno_text.setEditable(false);
            reset();
            
    }//GEN-LAST:event_jButton2ActionPerformed

  
    
    private void cardno_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardno_textActionPerformed
            String custoid;
            String cust = cardno_text.getText().trim();
            if(cust!=null && cust.length()>0)
            {
                try {
                        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), " SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID , M.NAME FROM CUSTOMERS C , MEMTYPE M  WHERE  C.MEMTYPE=M.ID AND C.CARD=?",
                                         new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING , Datas.STRING})).find(new Object[]{cust});
                        if (obj == null) {
                             JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                             cardno_text.setText(null);
                              cardno_text.requestFocus();
                         } else {
                             custoid = obj[0].toString();
                             customerInfo = m_dlSales.loadCustomerExt(custoid);

                             memno_text.setText(obj[1].toString());
                             name_text.setText(obj[2].toString());
                             memtype_text.setText(obj[5].toString());
                         }
                } catch (Exception e) {
                    e.printStackTrace();
                }
          }    
    }//GEN-LAST:event_cardno_textActionPerformed

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
        reset();
    }//GEN-LAST:event_reset_btnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         
        reset();
        memno_text.setEditable(true);
        
         Random r = new Random();
                            int max=999999999;
                            int min=111111111;
                            int randomnumber = r.nextInt(max - min + 1) + min;
                            String barcodecods = String.valueOf(randomnumber);

                            Image bg = BarcodeImage.getBarcode128(barcodecods+"sdjkfskdf");
                            BufferedImage buffered = (BufferedImage) bg;
                            ImageIcon icon = new ImageIcon(bg); 
                            File outputfile = new File("out.jpg");
                            try{
                               ImageIO.write(buffered, "JPEG", new File("./reports/com/openbravo/reports/myfile.JPG"));
                            }
                            catch(IOException e){
                                
                            }
                            
                            
                            
                              QTDetailsReportTableModel QTDetailsReport_Table_Model = null;
                              List<QTDetailsReportTableModel.QTInfo> QtInfo_list = new ArrayList<QTDetailsReportTableModel.QTInfo>();
                            
                              try {
                               QTDetailsReport_Table_Model  = QTDetailsReportTableModel.LoadQTInfo(m_App, "ACB6001");
                                } 

                              catch (BasicException ex) {
                                  Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              
                           
                              
                        //     String reportSource = "./reports/com/openbravo/reports/report2.jrxml"; /* Report Path*/
                         //   Map<String, Object> params = new HashMap<String, Object>();
                        //    params.put("printid", barcodecods);

                        //    JasperReport jasperReport = JasperCompileManager.compileReport(reportSource); /*Compiling  */

                         //   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                         //           params, params);  /*Filling report*/
                         //   JasperViewer.viewReport(jasperPrint, false);   /*For Print*/ 

                             
                              
                              
                              
                            
                              
                              QtInfo_list  =  (List<QTDetailsReportTableModel.QTInfo>) QTDetailsReport_Table_Model.getQtList();
                              DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                                DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                                data1.setDataSource(dsfc);
                                Map reportparams = new HashMap();
                               // reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                              //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                //String h = "( QT details for QTs  From : "+qtFromdate_text.getText()+" to  : "+ QtToDate_Text.getText()+ "  )";
                               // reportparams.put("HEADING",h);
                               // reportparams.put("WAREHOUSE",WarehouseName);
                               // reportparams.put("date",new Date());
                                
                               reportparams.put("DIR","./reports/com/openbravo/reports/myfile.JPG");
                                

                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/report_1.jrxml", reportparams, false, data1, true, null); 
                           

        
        
         
    }//GEN-LAST:event_jButton3ActionPerformed

    Double MemberDueAmount = -100.00;
    String MemDueName = null;
    
    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
          // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno_text.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                    reset();
                    memno_text.setText(null);
                } else {
                    
                    
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno_text.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    name_text.setText(obj[1].toString());
                    String MemTypeId=obj[2].toString();
                    String MemTypeName=null;
                    if(MemTypeId!=null){
                       MemTypeName =  getMemtypeByID(MemTypeId);
                    }
                    
                    memtype_text.setText(MemTypeName);
                    
     //////////////// check for member dues ...............
                   MemDueName =  getDueMemberNameFromExcel(customerInfo.getSearchkey());
                   if(MemDueName!=null){
                       MemberDueAmount = getMemberDuesFromExcel(customerInfo.getSearchkey());
                       if(MemberDueAmount!=null && MemberDueAmount!=-100.00){
                       
                           due_text.setText(MemberDueAmount+"");
                       
                       
                       }
                       else{
                           JOptionPane.showMessageDialog(null, " Membership Due is not updated. \n Please load membership dues.", "Warning", JOptionPane.WARNING_MESSAGE);
                           due_text.setText(null);
                       }
                   } 
                   else{
                        JOptionPane.showMessageDialog(null, " Membership details not found in Due list. \n Please enter membership dues.", "Warning", JOptionPane.WARNING_MESSAGE);
                         due_text.setText(null);
                   }
                    
                   
                   
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            name_text.setText(null);
            customerInfo = null;
            
        }
    }//GEN-LAST:event_memno_textKeyPressed

    UpdateDueAmt Update_Due_Amt;
    
    
    private void updateDue_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDue_btnActionPerformed
       if(customerInfo!=null && due_text.getText()!=null && due_text.getText().trim().length()>0){
           
           try{
           Double DueAmt= Double.parseDouble(due_text.getText().toString());
           
           Update_Due_Amt=UpdateDueAmt.getDialog(main_panel, m_App,DueAmt , customerInfo.getSearchkey());
           Update_Due_Amt.showDialog();
           
           
           }
           catch(BasicException e){
               
           }
           
       }
       else{
             if(customerInfo==null){   
                JOptionPane.showMessageDialog(null, "Please enter membership details.", "Warning", JOptionPane.WARNING_MESSAGE);
             }
             else{
                 JOptionPane.showMessageDialog(null, "Member dues not updated. Please update it first.", "Warning", JOptionPane.WARNING_MESSAGE);
             }
             
       }
    }//GEN-LAST:event_updateDue_btnActionPerformed
   private TicketParser m_TTP;
   
    public void printbarcoder(String barcodecods , String PsSequenceNo){
         String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.Barcoder");
        
         
         
         
         
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            
            String x = m_App.getAppUserView().getUser().getRole();
            
            script.put("date", new Date());
           
            script.put("cname", name_text.getText());
            script.put("cno", memno_text.getText());
           
            
           // script.put("type","CODE128");
          //  script.put("position","bottom");
           // script.put("code", "asdkahsdk");
           
             script.put("bar", barcodecods);  
              script.put("srno", PsSequenceNo);  
         
            
           // m_TTP.TicketParserBarcode("CODE128", "bottom" ,barcodecods );
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (Exception e) {
            }
        }   
      
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Barcode_label;
    private javax.swing.JButton barcode_btn;
    private javax.swing.JTextField cardno_text;
    private javax.swing.JLabel clublogo_label;
    private javax.swing.JLabel clubname_label;
    private javax.swing.JTextField due_text;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel memPhoto_label;
    private javax.swing.JLabel memSign_label;
    private javax.swing.JTextField memno_text;
    private javax.swing.JTextField memtype_text;
    private javax.swing.JTextField name_text;
    private javax.swing.JLabel poweredby_label;
    private javax.swing.JButton reset_btn;
    private javax.swing.JButton updateDue_btn;
    // End of variables declaration//GEN-END:variables

 public String getTitle() {
       return "Barcoded Voting Slip Generating Menu";
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
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
    }

    public Object getBean() {
         return this;
    }


    public void loaddata() throws BasicException{
        
        String Clubname = m_App.getSession().getCompanyName();
        clubname_label.setText(Clubname);
        
         Object[] obj15 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Date For Voting'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        if(obj15!=null){
            String Strdate = obj15[0].toString();
            
             MinDateForDue = (Date) Formats.TIMESTAMP.parseValue(Strdate);
             
        }
        else{
            MinDateForDue=null;
        }
        
        Object[] obj16 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Min DueAmt For Voting'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
        if(obj16!=null){
            String Str = obj16[0].toString();
            MinDueAmountForVote = Double.parseDouble(Str);
            
        }
        else{
            MinDueAmountForVote=-100.96;
        }
        
        
        
    }

   public void reset(){
       
       name_text.setText(null);
       memno_text.setText(null);
       memtype_text.setText(null);
       due_text.setText(null);
       memPhoto_label.setIcon(null);
       memSign_label.setIcon(null);
       Barcode_label.setText(null);
       memno_text.setEditable(false);
       cardno_text.setText(null);
       Barcode_label.setIcon(null);
   }


    // Get Old Id of no of contestant    
 public String  getMemtypeByID(String Id) throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select NAME from MEMTYPE WHERE ID=? AND ACTIVE=1  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(Id);
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
   
   
   // get RMAX from sequence details
     
      public int  getSequencePSNO() throws ParseException , BasicException{
      
       int opass = 0;
       Object o = null;
       Double Temp =0.00;
       o =(Object) new PreparedSentence(m_App.getSession(), "SELECT RMAX FROM sequencedetail where USERNAME=? AND ACTIVE=1", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(m_App.getAppUserView().getUser().getId());
       if(o!=null){
            Temp =  Double.parseDouble(o.toString());
            opass = Temp.intValue();
            return opass; 
       }
       else{
           
          return 0; 
       }
      }

      
         // Get Receipt name from sequence details..
 public String  getReceiptSeries() throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select RSERIES from sequencedetail WHERE USERNAME=? AND ACTIVE=1  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(m_App.getAppUserView().getUser().getId());
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
    
      
 
     // Get Receipt name from sequence details..
 public String  getMemIdAlreadyBarCodeGenerated(String Id) throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select MEMID from evm_membervote WHERE MEMID=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(Id);
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
 
 
 /////// get due member name from excel sheet 
 
 public String  getDueMemberNameFromExcel(String Id) throws  BasicException{
      
       String opass = null;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession() , "select MEMNO from evm_member_dues WHERE MEMNO=?  ", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(Id);
       if(o!=null){
            opass = o.toString();
            return opass; 
       }
       else{
          return null; 
       }
      }
 
 
  // get member dues from excel sheet ............
     
      public Double  getMemberDuesFromExcel(String ID) throws ParseException , BasicException{
      
       Double opass = -100.00;
       Object o = null;
       
       o =(Object) new PreparedSentence(m_App.getSession(), "SELECT DUEAMOUNT FROM evm_member_dues where MEMNO=?", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(ID);
       if(o!=null){
            opass =  Double.parseDouble(o.toString());
           
            return opass; 
       }
       else{
           
          return -100.00; 
       }
      }

 
 
 
 
   
}
