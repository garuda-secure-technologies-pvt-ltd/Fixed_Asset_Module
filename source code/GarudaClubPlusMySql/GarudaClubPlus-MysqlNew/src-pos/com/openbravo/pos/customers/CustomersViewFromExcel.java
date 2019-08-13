/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.LocalRes;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.BrowsableData;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DebtTypeTableModel;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.clubmang.FacilityLogic;
import com.openbravo.pos.clubmang.KIOSKSettings;
import com.openbravo.pos.clubmang.MemCat;
import com.openbravo.pos.clubmang.Periodicity;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.TaxInfo;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.RunnableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author user
 */
public class CustomersViewFromExcel extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /**
     * Creates new form CustomersViewFromExcel
     */
    
    private  AppView m_App;
    private  DataLogicFacilities dmang;
    private  ComboBoxValModel elementsModel;
    private DataLogicSales dlSales ;
    private DataLogicFacilities dlfac;
    private DataLogicCustomers dlcus;
    private Object[] customer = null;
    
    List<Object[]> custList;
    List<Object[]> custListNC;
    private ComboBoxValModel m_memtype;
    private ComboBoxValModel m_accountModel;
    private ComboBoxValModel m_CategoryModel;
    private SentenceList m_sentcat;
    private String accparent;
    private AccountMasterExt acc;
    private String servicetaxacc;
    private DataLogicSales m_dlSales;
    private TaxesLogic taxeslogic;
     private waitDialog w;
     private Map<String, String> memType;
  
     
     
    public CustomersViewFromExcel() {
        initComponents();
    }
    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlcus = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_sentcat = dlSales.getTaxCustCategoriesList();
        load();
    }
    public void activate() throws BasicException {
        //To change body of generated methods, choose Tools | Templates.
        reset();
        load();
    }
    
    private void load()
    {
        try {
            taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
            List mlist4 = dlfac.getMemberCategory();
            memType = new HashMap<String, String>();
            for (Iterator it = mlist4.iterator(); it.hasNext();) {
                MemCat memCat = (MemCat) it.next();
                memType.put(memCat.getMemberCategory().toString(), memCat.getID());
            }
            
            m_accountModel = new ComboBoxValModel(dlfac.getMemberReceivableAccount());
            acctype.setModel(m_accountModel);
             List a = m_sentcat.list();
            a.add(0, null); // The null item
            m_CategoryModel = new ComboBoxValModel(a);
        } catch (BasicException ex) {
            Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel28 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        joindate = new javax.swing.JTextField();
        confirmationdate = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        acctypelabel = new javax.swing.JLabel();
        acctype = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        noOfMem = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        savedMem = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();

        jLabel28.setText("Join Date");

        jLabel47.setText("Date Of Conf.");

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        acctypelabel.setText("Under Account");

        acctype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acctypeItemStateChanged(evt);
            }
        });

        jButton1.setText("Sample Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Select File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("No. Of Members: ");

        jButton3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("No of records Saved");

        jButton4.setText("Click here to see membership numbers which is not saved");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noOfMem, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savedMem, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(noOfMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(savedMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        noOfMem.setEditable(false);
        savedMem.setEditable(false);
        jButton4.setVisible(false);

        jLabel9.setText(AppLocal.getIntString("label.custtaxcategory")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(acctypelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acctype, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(m_jCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(262, 262, 262))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(acctypelabel)
                    .addComponent(acctype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(425, Short.MAX_VALUE))
        );

        jPanel2.setVisible(false);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 314, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(confirmationdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            date = getDate(date);
            confirmationdate.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
     if(acctype.getSelectedIndex()!=-1 )
        {
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new ExtensionsFilter(LocalRes.getIntString("label.imagefiles"), "xls", "xlsx"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            custList = new ArrayList<Object[]>();
            try {
                File sourceFile1 = fileChooser.getSelectedFile();

                FileInputStream fis = new FileInputStream(sourceFile1);
                //  srcLogo = sourceFile1;
                if(sourceFile1.getAbsoluteFile().toString().endsWith(".xls"))
                {
                    HSSFWorkbook   excel =   new HSSFWorkbook(fis);
                    HSSFSheet sheet = (HSSFSheet) excel.getSheetAt(0);

                    jPanel2.setVisible(true);
                    Iterator rows = sheet.rowIterator();
                    rows.next();
                    int rn= 1;
                    while (rows.hasNext()) {
                        HSSFRow row = (HSSFRow) rows.next();
                        rn++;
                       customer = new Object[38];
                       Date effDate = null;
                       Date joinDate = null;
                       Date confDate = null;
                        if(row.getCell((short)0) == null || row.getCell((short)1) == null || row.getCell((short)0).toString().trim().equals(""))
                        {
                            break; // If searchkey/Name/DOB is null then it wil break the iteration.
                        }
                        
                        if(row.getCell((short)19) == null || row.getCell((short)19).toString().trim().equals(""))
                        {
                            throw new BasicException("Membership type is not specified at the line "+rn);
                        }
                        //if(row.getCell((short)20) != null && row.getCell((short)20).toString().length()>0)
                        if(row.getCell((short)20) != null && row.getCell((short)20).toString().length()>0)
                        {
                            String effectiveDate = row.getCell((short)20).toString();
                            try
                            {
                                effDate = format1.parse(effectiveDate);
                            }
                            catch(ParseException ex)
                            {
                                throw new BasicException("The effective date format is wrong at line number "+ rn);
                            }
                        }
                        
                        if(row.getCell((short)21) != null)
                        {
                            String joinDDate = row.getCell((short)21).toString();
                            try
                            {
                                joinDate = format1.parse(joinDDate);
                            }
                            catch(ParseException ex)
                            {
                                throw new BasicException("The Join date format is wrong at line number "+ rn);
                            }
                        }
                        else
                        {
                            throw new BasicException("Join date should not be empty at line number "+ rn);
                        }
                         if(row.getCell((short)22) != null)
                        {
                            String confirmationDate = row.getCell((short)22).toString();
                            try
                            {
                                confDate = format1.parse(confirmationDate);
                            }
                            catch(ParseException ex)
                            {
                                throw new BasicException("The confirmation date format is wrong at line number "+ rn);
                            }
                        }
                        
                        
                        
                        String memtypeName = row.getCell((short)19).toString();
                        String memtypeId = memType.get(memtypeName);
                        if(memtypeId==null)
                        {
                            throw new BasicException("Entered Membership type at line "+rn+ " does not exist.");
                        }
                       String dob = null;
                       
                       if(row.getCell((short)4) != null || !row.getCell((short)4).toString().trim().equals(""))
                        {
                          dob =  row.getCell((short)4).toString().trim();
                        }
                        customer[22] = memtypeId;//Membership Type
                         try
                        {
                            if(!dob.equals(""))
                            {
                                customer[33] =   format1.parse(dob);//DOB
                            }
                            else
                            {
                                 customer[33] = null; 
                            }
                        
                        }
                        catch(Exception e)
                        {
                          throw new BasicException("Date of Birth is wrong at line "+rn+ " ");
                        }
                        customer[0] = UUID.randomUUID().toString(); //ID
                        customer[1] = "";//TAXID
                        customer[2] = row.getCell((short)0).toString().trim().toUpperCase();//SEARCHKEY

                        customer[3] = row.getCell((short)1).toString().trim().toUpperCase(); // NAME
                        customer[4] = row.getCell((short)18) == null ? "" : row.getCell((short)18).toString().trim();//NOTES

                        if(row.getCell((short)3)!=null)
                        {
                            if(row.getCell((short)3).toString().equals("No"))
                            customer[5] = false; // Visible
                            else
                            customer[5] = true; // By default Yes
                        }
                        else
                        { customer[5] = true;
                        }

                        customer[6] = "";// Card Num
                        customer[7] = row.getCell((short)5) == null ? "" : row.getCell((short)5).toString().trim();// FirstName
                        customer[8] = row.getCell((short)6) == null ? "" : row.getCell((short)6).toString().trim();//LASTNAME
                        customer[9] = row.getCell((short)7) == null ? "" : row.getCell((short)7).toString().trim();//EMAIL
                        customer[10] = row.getCell((short)8) == null ? "" : row.getCell((short)8).toString().trim();//PHONE
                        customer[11] = row.getCell((short)9) == null ? "" : row.getCell((short)9).toString().trim();//PHONE2
                        customer[12] = row.getCell((short)10) == null ? "" : row.getCell((short)10).toString().trim();//FAX

                        customer[13] = row.getCell((short)12) == null ? "" : row.getCell((short)12).toString().trim();//ADDRESS
                        customer[14] = row.getCell((short)13) == null ? "" : row.getCell((short)13).toString().trim();//ADDRESS2
                        customer[15] = row.getCell((short)14) == null ? "" : row.getCell((short)14).toString().trim();//POSTAL
                        customer[16] = row.getCell((short)15) == null ? "" : row.getCell((short)15).toString().trim();//CITY
                        customer[17] = row.getCell((short)16) == null ? "" : row.getCell((short)16).toString().trim();//REGION
                        customer[18] = row.getCell((short)17) == null ? "" : row.getCell((short)17).toString().trim();//COUNTRY
                        customer[19] = m_CategoryModel.getSelectedKey();//TAXCATEGORY
                        customer[24] = row.getCell((short)11) == null ? "" : row.getCell((short)11).toString().trim();//MOBILE
                        customer[20] = null;
                        customer[31] = joinDate;
                        
                        customer[23] = row.getCell((short)2) == null ? "" : row.getCell((short)2).toString().trim();//S/W
                        customer[25] = null;//Service tax
                        customer[26] = null;//ENTTAXCAT
                        customer[27] = null;//FINGERPRINTDATA
                        customer[28] = null;//SPONSOR1
                        customer[29] = null;//SPONSOR12
                        customer[30] = null;//SPONSOR3
                       
                        customer[32] = null;//TERDATE
                        customer[34] = 0.0;//OPENINGBALANCE
                        customer[36] = confDate;//CONFIRMDATE
                        customer[37] = effDate;//effectiveDate
                        custList.add(customer);
                        noOfMem.setText(custList.size()+"");
                    }

                    //      excel =   new HSSFWorkbook(fis);
                }
                else if(sourceFile1.getAbsoluteFile().toString().endsWith(".xlsx"))
                {
                    XSSFWorkbook   excel =   new XSSFWorkbook(fis);
                    XSSFSheet sheet = (XSSFSheet) excel.getSheetAt(0);
                    //noOfMem.setText(sheet.getLastRowNum()+"");
                    jPanel2.setVisible(true);
                    int rn= 1;
                    Iterator rows = sheet.rowIterator();
                    rows.next();
                    while (rows.hasNext()) {
                        XSSFRow row = (XSSFRow) rows.next();
                         rn++;
                       customer = new Object[38];
                       Date effDate = null;
                       Date joinDate = null;
                       Date confDate = null;
                        if(row.getCell((short)0) == null || row.getCell((short)1) == null || row.getCell((short)0).toString().trim().equals(""))
                        {
                            break; // If searchkey/Name/DOB is null then it wil break the iteration.
                        }
                        
                        if(row.getCell((short)19) == null || row.getCell((short)19).toString().trim().equals(""))
                        {
                            throw new BasicException("Membership type is not specified at the line "+rn);
                        }
                        if(row.getCell((short)20) != null && !row.getCell((short)20).equals(""))
                        {
                            String effectiveDate = row.getCell((short)20).toString();
                            try
                            {
                                effDate = format1.parse(effectiveDate);
                            }
                            catch(ParseException ex)
                            {
                                throw new BasicException("The effective date format is wrong at line number "+ rn);
                            }
                        }
                        
                        if(row.getCell((short)21) != null)
                        {
                            String joinDDate = row.getCell(21).toString();
                            try
                            {
                                joinDate = format1.parse(joinDDate);
                            }
                            catch(ParseException ex)
                            {
                                throw new BasicException("Join date should not be empty or Join date format is wrong at line number "+ rn);
                            }
                        }
                        else
                        {
                            throw new BasicException("Join date should not be empty at line number "+ rn);
                        }
                         if(row.getCell((short)22) != null)
                        {
                            String confirmationDate = row.getCell((short)22).toString();
                            try
                            {
                                confDate = format1.parse(confirmationDate);
                            }
                            catch(ParseException ex)
                            {
                                throw new BasicException("The confirmation date format is wrong at line number "+ rn);
                            }
                        }
                        
                        
                        
                        String memtypeName = row.getCell((short)19).toString();
                        String memtypeId = memType.get(memtypeName);
                        if(memtypeId==null)
                        {
                            throw new BasicException("Entered Membership type at line "+rn+ " does not exist.");
                        }
                       String dob = null;
                       
                       if(row.getCell((short)4) != null || !row.getCell((short)4).toString().trim().equals(""))
                        {
                         dob =   row.getCell((short)4).toString().trim();
                        }
                       
                        customer[22] = memtypeId;//Membership Type
                        try
                        {
                            if(!dob.equals(""))
                            {
                                customer[33] =   format1.parse(dob);//DOB
                            }
                            else
                            {
                                 customer[33] = null; 
                            }
                        
                        }
                        catch(Exception e)
                        {
                          throw new BasicException("Date of Birth is wrong at line "+rn+ " ");
                        }
                        customer[0] = UUID.randomUUID().toString(); //ID
                        customer[1] = "";//TAXID
                        customer[2] = row.getCell((short)0).toString().trim().toUpperCase();//SEARCHKEY

                        customer[3] = row.getCell((short)1).toString().trim().toUpperCase(); // NAME
                        customer[4] = row.getCell((short)18) == null ? "" : row.getCell((short)18).toString().trim();//NOTES

                        if(row.getCell((short)3)!=null)
                        {
                            if(row.getCell((short)3).toString().equals("No"))
                            customer[5] = false; // Visible
                            else
                            customer[5] = true; // By default Yes
                        }
                        else
                        { customer[5] = true;
                        }

                        customer[6] = "";// Card Num
                        customer[7] = row.getCell((short)5) == null ? "" : row.getCell((short)5).toString().trim();// FirstName
                        customer[8] = row.getCell((short)6) == null ? "" : row.getCell((short)6).toString().trim();//LASTNAME
                        customer[9] = row.getCell((short)7) == null ? "" : row.getCell((short)7).toString().trim();//EMAIL
                        customer[10] = row.getCell((short)8) == null ? "" : row.getCell((short)8).toString().trim();//PHONE
                        customer[11] = row.getCell((short)9) == null ? "" : row.getCell((short)9).toString().trim();//PHONE2
                        customer[12] = row.getCell((short)10) == null ? "" : row.getCell((short)10).toString().trim();//FAX

                        customer[13] = row.getCell((short)12) == null ? "" : row.getCell((short)12).toString().trim();//ADDRESS
                        customer[14] = row.getCell((short)13) == null ? "" : row.getCell((short)13).toString().trim();//ADDRESS2
                        customer[15] = row.getCell((short)14) == null ? "" : row.getCell((short)14).toString().trim();//POSTAL
                        customer[16] = row.getCell((short)15) == null ? "" : row.getCell((short)15).toString().trim();//CITY
                        customer[17] = row.getCell((short)16) == null ? "" : row.getCell((short)16).toString().trim();//REGION
                        customer[18] = row.getCell((short)17) == null ? "" : row.getCell((short)17).toString().trim();//COUNTRY
                        customer[19] = m_CategoryModel.getSelectedKey();//TAXCATEGORY
                        customer[24] = row.getCell((short)11) == null ? "" : row.getCell((short)11).toString().trim();//MOBILE
                        customer[20] = null;
                        customer[31] = joinDate;
                        
                        customer[23] = row.getCell((short)2) == null ? "" : row.getCell((short)2).toString().trim();//S/W
                        customer[25] = null;//Service tax
                        customer[26] = null;//ENTTAXCAT
                        customer[27] = null;//FINGERPRINTDATA
                        customer[28] = null;//SPONSOR1
                        customer[29] = null;//SPONSOR12
                        customer[30] = null;//SPONSOR3
                       
                        customer[32] = null;//TERDATE
                        customer[34] = 0.0;//OPENINGBALANCE
                        customer[36] = confDate;//CONFIRMDATE
                        customer[37] = effDate;//effectiveDate
                        custList.add(customer);
                        noOfMem.setText(custList.size()+"");
                    }
                }
                
                Object stacc = new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find("Service Tax Account");
                    if (stacc != null) {
                        servicetaxacc = stacc.toString();
                    }

            } catch (BasicException ex) {
                Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                
            } catch (IOException ex) {
                Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } 
            diableEnableAll(false);
        }
        }
   
    }//GEN-LAST:event_jButton2ActionPerformed
private  int numSaved = 0;

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(accparent!=null && !accparent.equals("")  )        
{ 
    custListNC = new ArrayList<Object[]>();
    w = new waitDialog(new JFrame(), true);
            int h = w.getSize().height;
            int w1 = w.getSize().width;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension scrnsize = toolkit.getScreenSize();
            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
    
   
       
       
          
     
           Thread t = new Thread(new Runnable() {

        public void run() {
            for (Iterator<Object[]> it = custList.iterator(); it.hasNext();) {
            try {
                Object[] customer = it.next();
                      
                   boolean b = insertIntoDataBase(customer);  
                   if(b)
                   {
                       
                       numSaved++;
                       savedMem.setText(numSaved+"");
                      
                   }else
                   {
                       custListNC.add(customer);
                       
                   }
            } catch (BasicException ex) {
                Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
               w.hideDialog();
            }
            
        }
        if(custListNC.size()>0)
        {
            jButton4.setVisible(true);
        }
        else
        {
            jButton4.setVisible(false);
        }
        
        savedMem.setText(numSaved+"");
       w.hideDialog();
       JOptionPane.showMessageDialog(null, "Saved Successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
        }
    });
           
           t.start();
            w.showDialog("Please wait saving records to data base!!!");
       
        
} 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {  
            Desktop.getDesktop().open(new File("reports\\MemberMaster.xlsx"));
        } catch (IOException ex) {
            Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), ex);
                msg.show(this);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        FileOutputStream fileOut = null;
        try {
            XSSFWorkbook wb =  new XSSFWorkbook();
              XSSFSheet sheet1 = (  XSSFSheet) wb.createSheet("new sheet");
              XSSFRow hr = null;
              XSSFCell cell = null;
              
              int [] array = new int[]{2,3,23,5,33,7,8,9,10,11,12,24,13,14,15,16,17,18,24};
              
              for (int i = 0; i < custListNC.size(); i++) {
                  Object [] c = custListNC.get(i);
                 hr = sheet1.createRow(i);
                  for (int j = 0; j < 18; j++) {
                      
                      cell =   hr.getCell(j);
                if(cell==null)
                    cell =hr.createCell(j);
                
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(c[array[j]]==null ? "":c[array[j]].toString());
                  }
              
            }
             
                
                
            File f =  new File(System.getProperty("user.home"));
            
            fileOut = new FileOutputStream(f+"\\workbook.xlsx");
            File file = new File(f+"\\workbook.xlsx");
            
            wb.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomersViewFromExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void acctypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acctypeItemStateChanged
        acc = (AccountMasterExt) acctype.getSelectedItem();
        accparent = acc.getSerachkey();
    }//GEN-LAST:event_acctypeItemStateChanged

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox acctype;
    private javax.swing.JLabel acctypelabel;
    private javax.swing.JTextField confirmationdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField joindate;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JTextField noOfMem;
    private javax.swing.JTextField savedMem;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Customers View From Excel"; //To change body of generated methods, choose Tools | Templates.
    }

    

    public boolean deactivate() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    public JComponent getComponent() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    

    public Object getBean() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    private Date getDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        return d;
    }

    private void reset() {
        
        diableEnableAll(true);
        m_jCategory.setSelectedIndex(-1);
        noOfMem.setText(null);
        custInfo = null;
        custList = null;
        customer = null;
        savedMem.setText(null);
        acc = null;
        jButton4.setVisible(false);
        
        
    }
    private Object[] custInfo = null;
    private boolean insertIntoDataBase(Object[] custom) throws BasicException {
       custInfo = custom;
        
        try
        {
        Transaction transac = new Transaction(m_App.getSession()) {
                    @Override
                    protected Object transact() throws BasicException {
                        Object[] pid = dlcus.getaccountofcustomer(custInfo[0].toString());
                        String id;
     /*                   int flag = 0;
                        if (pid != null) {
                            // if (pid[0] == accparent) {
                            if (pid[0].equals(accparent)) {  //praveen:added this line instead of above
                                customer[21] = pid[1];
                                flag = 1;
                                if (pid[2] != null) {
                                    id = pid[2].toString();
                                }
                            }
                        }
                        */
                        
                        // if (flag == 0) {
                            id = UUID.randomUUID().toString();
                            custInfo[21] = id;
                            String searchkey;
                            Object obj[] = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MAXIMUM FROM ACCOUNTMASTER WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(accparent);
                            if (obj == null && obj[0] == null) {
                                searchkey = accparent + ".1";
                                updateParent(1, accparent);
                            } else {
                                int temp = (Integer.parseInt(obj[0].toString()) + 1);
                                searchkey = accparent + "." + temp;
                                updateParent(temp, accparent);
                            }
                            Date dnow = new Date();
                            Double obal = 0.0;
                            
                            Object[] value = new Object[]{id, searchkey, custInfo[2].toString() + " - " + custInfo[3].toString(), custInfo[2].toString(), acc.gettype(), acc.getsign(), false, false, acc.getSerachkey(), "S", dnow, obal, true};
                            new PreparedSentence(m_App.getSession(), "INSERT INTO ACCOUNTMASTER(ID,SEARCHKEY,NAME,DESCRIPTION,TYPE_,SIGN,DOCUMENT,SUMMARY,PARENT,LEVEL_,STARTDATE,OPENINGBALANCE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.BOOLEAN})).exec(value);
                        //sanjeev:renamed type to type_,default to default_,level to level_
                    //    } //else if ((cus_skey != null && !cus_skey.equals(m_jSearchkey.getText().trim())) || (customerold[3] != null && !customerold[3].equals(m_jName.getText().trim()))) {
                          //  new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTMASTER SET NAME=?,DESCRIPTION=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{m_jSearchkey.getText() + " - " + m_jName.getText(), m_jSearchkey.getText(), customer[21]});
                       // }//
                        
                         if (custInfo[21] == null) {
                        JOptionPane.showMessageDialog(null, "Under Account field cannot be null", "Cannot save", JOptionPane.ERROR_MESSAGE);
                        throw new BasicException();
                    }
                        if(custInfo[37]!=null)
                        {
                        Date date1 = null;
                        date1 = new Date();
                            date1.setTime(getdate((Date)custInfo[37]).getTime());
                            //MemCat mtype1 = (MemCat) memtype.getSelectedItem();
                            List<Facility> fac = dlfac.getPermanentFacilitySpecifictToMemType().list("%" + custInfo[22].toString() + "%");
                            PermanentFacilities(date1, custInfo[21].toString(), custInfo[0].toString(), fac);
                            // Sanjev:minimum billing for new members
                            List<MinimumUsage> minusage = dlfac.getMinUsageSpecifictToMemType("%" + custInfo[22].toString() + "%");
                            Date d = (Date) custInfo[31];
                            insertMinimumUsageSpecificToMemType(date1, custInfo[0].toString(), minusage, d);
                        }
                       /*     
                            ID, TAXID, SEARCHKEY, NAME, NOTES, VISIBLE, CARD, FIRSTNAME, LASTNAME, EMAIL, PHONE, PHONE2, 
                            FAX, ADDRESS, ADDRESS2, POSTAL, CITY, REGION, COUNTRY, TAXCATEGORY, IMAGE, ACCOUNT, MEMTYPE, SOWO,
                            MOBILE, SERVICETAXCAT, ENTTAXCAT, SIGNATURE, SPONSOR1, SPONSOR2, SPONSOR3, JOINDATE, TERDATE, DOB, 
                            OPENINGBALANCE, REFERENCEID, CONFIRMDATE, EFFECTIVEDATE*/
                            new PreparedSentence(m_App.getSession(), "INSERT INTO CUSTOMERS (ID, TAXID, SEARCHKEY, NAME, NOTES, VISIBLE, CARD, FIRSTNAME, LASTNAME, EMAIL, PHONE, PHONE2, FAX, ADDRESS, ADDRESS2, POSTAL, CITY, REGION, COUNTRY, TAXCATEGORY, IMAGE, ACCOUNT, MEMTYPE, SOWO, MOBILE, SERVICETAXCAT, ENTTAXCAT, SIGNATURE, SPONSOR1, SPONSOR2, SPONSOR3, JOINDATE, TERDATE, DOB, OPENINGBALANCE, REFERENCEID, CONFIRMDATE, EFFECTIVEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING,  Datas.STRING,  Datas.STRING,  Datas.BOOLEAN,  Datas.STRING,  Datas.STRING, Datas.STRING, Datas.STRING,  Datas.STRING, Datas.STRING,Datas.STRING,  Datas.STRING, Datas.STRING, Datas.STRING,  Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE,Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,  Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.STRING, Datas.STRING,Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(custInfo);                  
                             
                        
                        return null;
                    }
                };
                transac.execute();
        }
        catch(BasicException e)
        {
            // MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
             ///   msg.show(this);
            //pratima
            e.printStackTrace();
                return false;
            
        }
        return true;
    }

    private void diableEnableAll(boolean b) {
        acctype.setEnabled(b);
        m_jCategory.setEnabled(b);
        //To change body of generated methods, choose Tools | Templates.
    }
    private Date getdate(Date d) {
        //Date d=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        return d;
    }
    private void updateParent(int max, String parent) throws BasicException {
        new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=?",
                new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING})).exec(new Object[]{max, parent});
    }
    private void PermanentFacilities(Date date1, String custacc, String custid, List<Facility> fac) throws BasicException {
        for (Facility f : fac) {
            if (f.getrperiod() != null) {
                Periodicity p = dlfac.getPerioicitybyid(f.getrperiod());
                if (p.getqbtype() == false) {
                    postbilling(f, date1, custacc, custid);
                } else {
                    prebilling(f, date1, p, custid, custacc);
                }
            }
        }
    }
    private void postbilling(Facility f, Date date1, String cust, String custid) throws BasicException {
        Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, custid, f.getid(), date1, m_App.getAppUserView().getUser().getName(), date1};
        new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP})).exec(value1);
        if (f.getjamt() > 0) {
            DebtTypeTableModel.DebtTypeline periodtype = dlfac.getDebtTypebyid(f.getdueperiod());
            Date duedate = getDueDate(periodtype);
            String billno = dlfac.getnewbillno(f.getid());
            String tid = UUID.randomUUID().toString();
            String facname = f.getName();

            Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, date1, "C", f.getid(), billno, f.getjamt(), date1, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), facname + " " + billno, f.getJoinfeeAccount(), 0.0, date1, true};
            dlfac.insertintoaccjoutnal1(value2);
            Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, custid, date1, "D", f.getid(), billno, f.getjamt(), duedate, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, cust, f.getjamt(), true};
            dlfac.insertintoaccjoutnal(value3);
            dlfac.updatebillno(f.getid());
        }
    }
    
      private void prebilling(Facility f, Date date1, Periodicity p, String cid, String caccount) throws BasicException {
        String fid = f.getid();
        String fname = f.getName();
        Date d = new Date();
        d.setTime(date1.getTime());
        if ((f.getrperiod() != null && f.getramt() > 0) || f.getjamt() > 0) {
            double totalamt = 0.0;
            //  String servicetaxacc=null;
            Double taxrate = 0.0;
            if (f.getservicetax() != null) {
                TaxCategoryInfo tinfo = (TaxCategoryInfo) m_dlSales.getTaxCategoryByid(f.getservicetax());
                TaxInfo tax = taxeslogic.getTaxInfo(tinfo);
                taxrate = tax.getRate();
            }

            String billno = dlfac.getnewbillno(f.getid());
            if (f.getrperiod() != null && f.getramt() > 0) {
                double ramt = 0.0;
                Date lbdate = new Date();
                lbdate.setTime(date1.getTime());
                Date edate = new Date();
                edate.setTime(date1.getTime());
                Date duedate = new Date();
                duedate.setTime(date1.getTime());
                DebtTypeTableModel.DebtTypeline periodtype = dlfac.getDebtTypebyid(f.getdueperiod());
                lbdate.setTime(date1.getTime());
                FacilityLogic flogic = new FacilityLogic(dlfac);
                Calendar cal1 = Calendar.getInstance();
                cal1.setTimeInMillis(lbdate.getTime());
                int billabledate = cal1.get(Calendar.DATE);
                if (p.getdoj() == false) {
                    cal1.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal1).getTimeInMillis());
                }
                lbdate.setTime(cal1.getTimeInMillis());
                billabledate = cal1.get(Calendar.DATE);
                edate.setTime(flogic.calculateEndDate(lbdate, p, billabledate, 1, new Date()).getTime());
                duedate.setTime(flogic.getDueDate(periodtype, flogic.getTemp()).getTime());
                if (p.getaccurate() == true) {
                    ramt = flogic.calulaterenewalamt(date1, edate, ramt);
                } else {
                    if (flogic.getPnum() > 0) {
                        ramt = f.getramt();
                        ramt = flogic.getPnum() * ramt;
                    }
                }
 //sameer:changing Math.floor() to Math.abs()
                double taxamt = Math.floor(taxrate * ramt);
                String tid = UUID.randomUUID().toString();
                if (taxamt > 0) {
                    if (servicetaxacc == null) {
                        JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
                    }
                    Object[] value4 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + f.getName(), servicetaxacc, 0.0, d, new Date(), true};
                    dlfac.insertintoaccjoutnal3(value4);
                }
                double tramt = dlfac.roundTwoDecimals(ramt + taxamt);
                if (tramt > 0) {
                    Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, ramt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Renewal Fees for Period :" + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate), f.getRenewalacc(), 0.0, d, new Date(), true};
                    dlfac.insertintoaccjoutnal3(value2);
                    Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, cid, d, "D", fid, billno, tramt, duedate, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Renewal Fees for Period :" + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate), caccount, tramt, new Date(), true};
                    dlfac.insertintoaccjoutnal0(value3);
                    totalamt = tramt;
                    Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, cid, fid, d, m_App.getAppUserView().getUser().getName(), new Date(), edate, new Date(), billno, 0};
                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,LBILLDATE,NBILLDATE,BILLREF,STATUS_) VALUES (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.INT})).exec(value1);
                } else {
                    Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, cid, fid, d, m_App.getAppUserView().getUser().getName(), new Date(), 0};
                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,STATUS_) VALUES (?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.INT})).exec(value1);
                }
            }
            if (f.getjamt() > 0) {
                // DebtTypeTableModel.DebtTypeline periodtype=(DebtTypeTableModel.DebtTypeline)debttype.getSelectedItem();
                double jamt = f.getjamt();
                double taxamt = Math.floor(taxrate * jamt);
                String tid = UUID.randomUUID().toString();
                String facname = f.getName();
                if (taxamt > 0) {
                    if (servicetaxacc == null) {
                        JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
                    }
                    Object[] value4 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facname, servicetaxacc, 0.0, d, new Date(), true};
                    dlfac.insertintoaccjoutnal3(value4);
                }
                double tjamt = dlfac.roundTwoDecimals(jamt + taxamt);
                Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, dlfac.roundTwoDecimals(jamt), d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, f.getJoinfeeAccount(), 0.0, d, new Date(), true};
                dlfac.insertintoaccjoutnal3(value2);
                Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, cid, d, "D", fid, billno, tjamt, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, caccount, tjamt, new Date(), true};
                dlfac.insertintoaccjoutnal0(value3);
                totalamt += tjamt;
            // dmang.updatebillno(fid);
            }
            dlfac.updatebillno(f.getid());
            dlfac.setmemberDebt(cid, f.getid(), totalamt);
        }
    }
    
    private Date getDueDate(DebtTypeTableModel.DebtTypeline dueperiod) {
        String type = dueperiod.getperiod();
        int num = dueperiod.getNum();
        Date duedate = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(duedate.getTime());
        if (type.equals("Days")) {
            cal.add(Calendar.DATE, num);
        }
        if (type.equals("Months")) {
            cal.add(Calendar.MONTH, num);
        }
        if (type.equals("Years")) {
            cal.add(Calendar.YEAR, num);
        }
        duedate.setTime(cal.getTimeInMillis());
        return duedate;
    }
    
     private void insertMinimumUsageSpecificToMemType(Date date1, String custid, List<MinimumUsage> minusage, Date lbilldate) throws BasicException {
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Date ldate = null;
        cal.setTime(lbilldate);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        for (MinimumUsage m : minusage) {
            cal1.setTime(m.getEffectiveFrom());
            cal1.set(Calendar.HOUR_OF_DAY, 00);
            cal1.set(Calendar.MINUTE, 00);
            cal1.set(Calendar.SECOND, 00);
            cal1.set(Calendar.MILLISECOND, 00);

            Object[] value1 = null;
            if (cal1.after(cal)) {
                cal1.add(Calendar.DATE, -1);
                cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                value1 = new Object[]{UUID.randomUUID().toString(), true, custid, m.getMid(), date1, m_App.getAppUserView().getUser().getName(), date1, 0, cal1.getTime()};

            } else {
                value1 = new Object[]{UUID.randomUUID().toString(), true, custid, m.getMid(), date1, m_App.getAppUserView().getUser().getName(), date1, 0, cal.getTime()};


            }
            new PreparedSentence(m_App.getSession(), "INSERT INTO MEMMINUSAGE(ID, ACTIVE, MEMNO, USAGETYPE, SDATE, CREATEDBY, CRDATE,STATUS_,LBILLDATE) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.TIMESTAMP})).exec(value1);
        }
    }
    
    private static class ExtensionsFilter extends FileFilter {
        
        private String message;
        private String[] extensions;
        
        public ExtensionsFilter(String message, String... extensions) {
            this.message = message;
            this.extensions = extensions;            
        }
        
        public boolean accept(java.io.File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                String sFileName = f.getName();
                int ipos = sFileName.lastIndexOf('.');
                if (ipos >= 0) {
                    String sExt = sFileName.substring(ipos + 1);
                    for(String s : extensions) {
                        if (s.equalsIgnoreCase(sExt)) {
                            return true;
                        }
                    }
                }                        
                return false;
            }   
        }
        
        public String getDescription() {
            return message;
        }      
    }
}
