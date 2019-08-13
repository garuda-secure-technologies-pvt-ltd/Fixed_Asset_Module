/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MemFacility.java
 *
 * Created on Mar 16, 2009, 10:53:02 AM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.MemFacilityTableModel.Facilityline;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.inventory.TaxCategoryInfo1;
import com.openbravo.pos.inventory.TaxCategoryInfo2;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.TaxInfo;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.ComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class MemFacility extends javax.swing.JPanel implements JPanelView, BeanFactoryApp
{

    /** Creates new form MemFacility */
    private AppView m_App;
    private DataLogicFacilities dmang;
    private SentenceList flist;
    private ComboBoxValModel facilitytable;
    private ComboBoxValModel facilitytable2;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private MemFacilityTableModel memfac;
    private DataLogicSales m_dlSales;
    private TaxesLogic taxeslogic;
    private ComboBoxValModel depMemModel;

    public MemFacility()
    {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException
    {
        m_App = app;
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }

    public Object getBean()
    {
        return this;
    }

    public JComponent getComponent()
    {
        return this;
    }

    public String getTitle()
    {
        return null;
    }

    public void activate() throws BasicException
    {
        try
        {
            jButton6.setVisible(false);
            taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
            List temp1 = dmang.getFacility();
            if (temp1 != null)
            {
                temp1.add(0, null);
            }
            facilitytable2 = new ComboBoxValModel(temp1);
            ftype.setModel(facilitytable2);
            depMemModel = new ComboBoxValModel();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        loadData();
    }

    private void loadData() throws BasicException
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                mno.requestFocus();
            }
        });
        reset();
    }

    private void reset()
    {
        mno.setText(null);
        mname.setText(null);
        sdate.setText(null);
        facilitytable = new ComboBoxValModel(new ArrayList());
//        System.out.println("facilitytable::::::::"+facilitytable);
        facilitytype.setModel(facilitytable);
        jLabel7.setVisible(false);
        m_depnamelist.setModel(new ComboBoxValModel());
        m_depnamelist.setVisible(false);
        mem.setSelected(true);
    }

    public boolean deactivate()
    {
        return true;
    }
    /*  private void calculatenextbilldate(Facility facility,Object sdatetime){

    }*/
    /*    private Double calulaterenewalamt(Date lbdate,Date edate,Double ramt){
    Calendar caltemp1=Calendar.getInstance();
    Calendar caltemp2=Calendar.getInstance();
    caltemp1.setTimeInMillis(lbdate.getTime());
    caltemp2.setTimeInMillis(edate.getTime());
    Date d=new Date();
    //  Timestamp temp2=caltemp2.getTimeInMillis()
    long difMil1 = caltemp2.getTimeInMillis() - caltemp1.getTimeInMillis();
    int days1 = (int)((difMil1 + 12 * 3600000L) / (24 * 3600000L));
    caltemp1.set(Calendar.DATE, caltemp2.get(Calendar.DATE));
    long difMil = d.getTime() - caltemp1.getTimeInMillis();
    int days = (int)((difMil + 12 * 3600000L) / (24 * 3600000L));
    return dmang.roundTwoDecimals(Math.round(ramt * days/days1));
    }
    private Date getDueDate(DebtTypeTableModel.DebtTypeline dueperiod,Date d){
    String type=dueperiod.getperiod();
    int num=dueperiod.getNum();
    Date duedate=new Date();
    duedate.setTime(d.getTime());
    Calendar cal=Calendar.getInstance();
    cal.setTimeInMillis(duedate.getTime());
    if(type.equals("Days"))
    cal.add(Calendar.DATE, num);
    if(type.equals("Months"))
    cal.add(Calendar.MONTH, num);
    if(type.equals("Years"))
    cal.add(Calendar.YEAR, num);
    duedate.setTime(cal.getTimeInMillis());
    return duedate;
    }
    private int pnum=0;
    private Date temp;
    private Date calculateEndDate(Date d,Periodicity p,int billabledate){

    Date edate=new Date();
    // edate.setTime(d.getTime());
    Calendar cal1=Calendar.getInstance();
    cal1.setTimeInMillis(d.getTime());
    Calendar cal=Calendar.getInstance();
    cal.setTimeInMillis(edate.getTime());
    if(p.getdoj()==true){
    while(cal1.before(cal)){
    temp.setTime(cal1.getTimeInMillis());
    if(p.gettype().equals("Days")){
    cal1.add(Calendar.DATE, p.getno());
    pnum++;
    }else if(p.gettype().equals("Months")){
    cal1.add(Calendar.MONTH, p.getno());
    pnum++;
    } if(p.gettype().equals("Quaterly")){
    cal1.add(Calendar.MONTH, 3);
    pnum++;
    } if(p.gettype().equals("Years")){
    cal1.add(Calendar.YEAR, p.getno());
    pnum++;
    }
    }
    }else{
    while(cal1.before(cal)){
    temp.setTime(cal1.getTimeInMillis());
    if(p.gettype().equals("Days")){
    cal1.add(Calendar.DATE, p.getno());
    pnum++;
    }else if(p.gettype().equals("Months")){
    cal1.set(Calendar.DATE, billabledate);
    cal1.add(Calendar.MONTH, p.getno());
    pnum++;
    }else if(p.gettype().equals("Quaterly")){
    cal1.set(Calendar.DATE, billabledate);
    cal1.add(Calendar.MONTH, 3);
    pnum++;
    }else if(p.gettype().equals("Years")){
    cal1.set(Calendar.DATE, billabledate);
    cal1.set(Calendar.MONTH, p.getmonth());
    cal1.add(Calendar.YEAR, p.getno());
    pnum++;
    }
    }
    }
    cal1.set(Calendar.DATE,-1);
    edate.setTime(cal1.getTimeInMillis());
    return edate;
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        mno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        facilitytype = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        sdate = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        editIndicator = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mem = new javax.swing.JRadioButton();
        memdep = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        m_depnamelist = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
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
        jLabel5 = new javax.swing.JLabel();
        ftype = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setAutoscrolls(true);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setAutoscrolls(true);

        jLabel1.setText("Member Name");

        jLabel2.setText("Member No");

        mno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnoKeyPressed(evt);
            }
        });

        jLabel3.setText("Facility Name");

        facilitytype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilitytypeActionPerformed(evt);
            }
        });

        jLabel4.setText("Start Date");

        jButton1.setText("save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        editIndicator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N

        jLabel6.setText("Applicable for");

        buttonGroup1.add(mem);
        mem.setText("Member");
        mem.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                memStateChanged(evt);
            }
        });
        mem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memItemStateChanged(evt);
            }
        });
        mem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memActionPerformed(evt);
            }
        });

        buttonGroup1.add(memdep);
        memdep.setText("Member Dependent");
        memdep.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                memdepStateChanged(evt);
            }
        });
        memdep.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memdepItemStateChanged(evt);
            }
        });

        jLabel7.setText("Dependent Name");

        m_depnamelist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_depnamelistItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(facilitytype, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mno))
                        .addGap(10, 10, 10)
                        .addComponent(editIndicator)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(mem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(memdep, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_depnamelist, 0, 173, Short.MAX_VALUE)
                    .addComponent(mname, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jButton1)
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(mno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(editIndicator))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(mem)
                            .addComponent(jLabel7)
                            .addComponent(m_depnamelist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(memdep))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(facilitytype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3))
                .addContainerGap(274, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create New", jPanel1);

        jPanel2.setAutoscrolls(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mem No", "Mem Name", "Facility Type", "Start date", "Last billed date", "Created By"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setText("Facility Type");

        jButton4.setText("load");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Deactivate");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftype, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(31, 31, 31)
                        .addComponent(jButton6)))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(650, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jTabbedPane1.addTab("List View", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null)
        {
            try
            {
                mname.setText(customerInfo.toString());
                mno.setText(customerInfo.getSearchkey());
                /*  List temp=dmang.getOptionalFacilitySpecificToType(0);
                facilitytable=new ComboBoxValModel(temp);
                facilitytype.setModel(facilitytable);*/
                List<Facility> temp = dmang.getOptionalFacilitySpecificToType(0);
//                String id=customerInfo.getId();
             
                List<Facility> temp1 = dmang.getmemberfacility(customerInfo.getId());
//                    List<Facility> temp1 = dmang.getmemberfacility(id);
                List<Facility> temp2 = new ArrayList<Facility>();
                temp2.addAll(temp);
                for (Facility f : temp)
                {
                    for (Facility f2 : temp1)
                    {
                        if (f.getid().equals(f2.getid()))
                        {
                            temp2.remove(f);
                            break;
                        }
                    }
                }
                facilitytable = new ComboBoxValModel(temp2);
//                System.out.println("facilitytable:::::::"+facilitytable);
                facilitytype.setModel(facilitytable);
                depMemModel = new ComboBoxValModel();
                m_depnamelist.setModel(depMemModel);
                editIndicator.setVisible(false);
                mem.setSelected(true);
                m_depnamelist.setVisible(false);
            } catch (Exception e)
            {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Date date;
        try
        {
            date = (Date) Formats.TIMESTAMP.parseValue(sdate.getText());
        } catch (BasicException e)
        {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null)
        {
            sdate.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
            Transaction t = new Transaction(m_App.getSession())
            {

                public Object transact() throws BasicException
                {
                    double totalamt = 0.0;
                    Date d = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(d.getTime());
                    cal.set(Calendar.HOUR_OF_DAY, 00);
                    cal.set(Calendar.MINUTE, 00);
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    d.setTime(cal.getTimeInMillis());
                    boolean flag1 = true;
                    if (memdep.isSelected())
                    {
                        if (m_depnamelist.getSelectedIndex() == -1)
                        {
                            flag1 = false;
                        }
                    }
                    if (mno.getText().length() > 0 && facilitytype.getSelectedItem() != null && sdate.getText().length() > 0 && mname.getText().length() > 0 && editIndicator.isVisible() == false && flag1 == true)
                    {
                        //  if(mno.getText().toUpperCase().equals(customerInfo.getSearchkey().toUpperCase())){
                        String user = null;
                        if (memdep.isSelected())
                        {
                            MemberDependent dep = (MemberDependent) m_depnamelist.getSelectedItem();
                            user = dep.getID();
                        }
                        Facility facility = (Facility) facilitytype.getSelectedItem();
                        int count = Integer.valueOf(new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMFACILITYUSAGE WHERE MEMNO=? AND FACILITYTYPE=? AND USERID=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]
                                {
                                    Datas.STRING, Datas.STRING, Datas.STRING
                                }), SerializerReadInteger.INSTANCE).find(new Object[]
                                {
                                    customerInfo.getId(), facility.getid(), user
                                }).toString());
                        if (count == 0)
                        {
                            Date sdatetime = (Date) Formats.TIMESTAMP.parseValue(sdate.getText());
                            int flag = 0;
                            String caccount = null;
                            try
                            {
                                caccount = dmang.getCustomerAccountByID(customerInfo.getId());
                            } catch (Exception e1)
                            {
                                flag = 1;
                                JOptionPane.showMessageDialog(null, "Member Account not present.Please create one", null, JOptionPane.OK_OPTION);
                            }
                            String servicetaxacc = null;
                            Double taxrate = 0.0;
                            String taxname=null;
                            if (facility.getservicetax() != null)
                            {
                                TaxCategoryInfo tinfo = (TaxCategoryInfo) m_dlSales.getTaxCategoryByid(facility.getservicetax());
                                TaxInfo tax = taxeslogic.getTaxInfo(tinfo);
                                taxname=tax.getName();
                                taxrate = tax.getRate();
                            }
                            String servicetaxacc1 = null;
                            Double taxrate1 = 0.0;
                            String taxname1=null;
                            if (facility.getTaxcat_2()!= null)
                            {
                                TaxCategoryInfo1 tinfo1 = (TaxCategoryInfo1) m_dlSales.getTaxCategoryByid1(facility.getTaxcat_2());
                                TaxInfo tax1 = taxeslogic.getTaxInfo(tinfo1);
                                taxname1=tax1.getName();
                                taxrate1 = tax1.getRate();
                            }
                            String servicetaxacc2 = null;
                            Double taxrate2 = 0.0;
                            String taxname2=null;
                            if (facility.getTaxcat_3() != null)
                            {
                                TaxCategoryInfo2 tinfo2 = (TaxCategoryInfo2) m_dlSales.getTaxCategoryByid2(facility.getTaxcat_3());
                                TaxInfo tax2 = taxeslogic.getTaxInfo(tinfo2);
                                taxname2=tax2.getName();
                                taxrate2 = tax2.getRate();
                            }
                            if (taxrate > 0)
                            {
                                Object stacc = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(taxname);
                                if (stacc != null)
                                {
                                    servicetaxacc = stacc.toString();
                                    
                                }
                            }
                            if (taxrate1 > 0)
                            {
                                Object stacc1 = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(taxname1);
                                if (stacc1 != null)
                                {
                                    servicetaxacc1 = stacc1.toString();
                                }
                            }
                            if (taxrate2 > 0)
                            {
                                Object stacc2 = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(taxname2);
                                if (stacc2!= null)
                                {
                                    servicetaxacc2 = stacc2.toString();
                                }
                            }
                            if (flag == 0)
                            {

                                DebtTypeTableModel.DebtTypeline periodtype = dmang.getDebtTypebyid(facility.getdueperiod());
                                FacilityLogic flogic = new FacilityLogic(dmang);
                                Date duedate = flogic.getDueDate(periodtype, sdatetime);
                                  double taxamt=0.0;
                                    double taxamt1=0.0;
                                    double taxamt2=0.0;
                                      double taxamt3=0.0;
                                if (facility.getjamt() > 0)
                                {
                                    if (facility.getjamt() > 0)
                                    {
                                       
                                       
                                        String billno = dmang.getnewbillno(facility.getid());
                                        String tid = UUID.randomUUID().toString();
                                        //double taxamt = Math.floor(taxrate * facility.getjamt());
                                         taxamt = Math.abs(taxrate * facility.getjamt());
                                        if(taxrate1!=null){
                            if(facility.getBasic1()){
                               taxamt1 = Math.abs(taxrate1 *facility.getjamt());
                                
                            }
                            else{
//                                ramt=ramt+taxamt1;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
                           taxamt1 = Math.abs(taxrate1 * (facility.getjamt()+taxamt));

                            }
                        }
                        if(taxrate2!=null){
                            if(facility.getBasic2()){
                               taxamt2 = Math.abs(taxrate2 * facility.getjamt());
                                
                            }
                            else{
//                                ramt=ramt+taxamt1;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
                           taxamt2 = Math.abs(taxrate2 * (facility.getjamt()+taxamt+taxamt1));

                            }
                        }
                        taxamt3=taxamt+taxamt1+taxamt2;
                                        if (taxamt > 0)
                                        {
//                                            if (servicetaxacc == null)
//                                            {
//                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
                                            if(servicetaxacc!=null){
                                                Object[] value4 = new Object[]
                                            {
                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
                                            };
                                            dmang.insertintoaccjoutnal3(value4);
                                            }
                                            
                                        }
                                        if (taxamt1 > 0)
                                        {
//                                            if (servicetaxacc == null)
//                                            {
//                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
                                            if(servicetaxacc1!=null){
                                                Object[] value41 = new Object[]
                                            {
                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, taxamt1, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facility.getName(), servicetaxacc1, 0.0, d, new Date(), true
                                            };
                                            dmang.insertintoaccjoutnal3(value41);
                                            }
                                            
                                        }
                                        if (taxamt2 > 0)
                                        {
//                                            if (servicetaxacc == null)
//                                            {
//                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
                                            if(servicetaxacc2!=null){
                                                  Object[] value42 = new Object[]
                                            {
                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, taxamt2, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facility.getName(), servicetaxacc2, 0.0, d, new Date(), true
                                            };
                                            dmang.insertintoaccjoutnal3(value42);
                                            }
                                          
                                        }
                                        String jnarration = "Join Fees for " + facility.getName();
                                        if (memdep.isSelected())
                                        {
                                            MemberDependent md = (MemberDependent) m_depnamelist.getSelectedItem();
                                            jnarration += md.getName();
                                        }
//                                        double tjamt = dmang.roundTwoDecimals(facility.getjamt() + taxamt);
                                      double tjamt = dmang.roundTwoDecimals(facility.getjamt() + taxamt3);

                                        Object[] value1 = new Object[]
                                        {
                                            UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billno, facility.getjamt(), d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), facility.getName(), facility.getJoinfeeAccount(), 0.0, d, true
                                        };
                                        dmang.insertintoaccjoutnal1(value1);
                                        Object[] value2 = new Object[]
                                        {
                                            UUID.randomUUID().toString(), tid, customerInfo.getId(), d, "D", facility.getid(), billno, tjamt, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), jnarration, caccount, tjamt, true
                                        };
                                        dmang.insertintoaccjoutnal(value2);
                                        dmang.updatebillno(facility.getid());
                                        totalamt = tjamt;
                                    }
                                }

                                Date lbdate = new Date();
                                Periodicity p = dmang.getPerioicitybyid(facility.getrperiod());
                                Date edate = new Date();
                                String billnum = null;
                                if (p.getqbtype() == true)
                                {
                                    lbdate.setTime(sdatetime.getTime());
                                    Calendar cal1 = Calendar.getInstance();
                                    cal1.setTimeInMillis(lbdate.getTime());
                                    int billabledate = cal1.get(Calendar.DATE);
                                    if (p.getdoj() == false)
                                    {
                                        cal1.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal1).getTimeInMillis());
                                    }
                                    lbdate.setTime(cal1.getTimeInMillis());
                                    billabledate = cal1.get(Calendar.DATE);
                                    flogic.setTemp(lbdate);
                                    if(new Date().after(lbdate))
                                    {
                                    edate.setTime(flogic.calculateEndDate(lbdate, p, billabledate, 1, new Date()).getTime());
                                    }
                                    else
                                    {
                                        Calendar tempCalender = Calendar.getInstance();
                                        tempCalender.setTimeInMillis(lbdate.getTime());
                                        tempCalender.add(Calendar.DATE, 1);
                                        edate.setTime(flogic.calculateEndDate(lbdate, p, billabledate, 1, tempCalender.getTime()).getTime());
                                    }
                                    if (p.getqbtype() == true)
                                    {
                                        duedate.setTime(flogic.getDueDate(periodtype, flogic.getTemp()).getTime());
                                    }
                                    Double ramt = facility.getramt();
                                    if (p.getaccurate() == true)
                                    {
                                        ramt = flogic.calulaterenewalamt(lbdate, edate, ramt);
                                    } else
                                    {
                                        if (flogic.getPnum() > 0)
                                        {
                                            ramt = flogic.getPnum() * ramt;
                                        }
                                    }
//                                    double taxamt=0.0;
//                                    double taxamt1=0.0;
//                                    double taxamt2=0.0;
//                                    double taxamt3=0.0;
                                    if (ramt > 0)
                                    {
                                        billnum = dmang.getnewbillno(facility.getid());
                                        String tid = UUID.randomUUID().toString();
                                        //double taxamt = Math.floor(taxrate * ramt);
                                         taxamt = Math.abs(taxrate * ramt);
//                                        if(taxrate1!=null){
//                           double taxamt1 = Math.abs(taxrate1 * ramt);
//                        }
                        if(taxrate1!=null){
                            if(facility.getBasic1()){
                               taxamt1 = Math.abs(taxrate1 * ramt);
                                
                            }
                            else{
//                                ramt=ramt+taxamt1;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
                           taxamt1 = Math.abs(taxrate1 * (ramt+taxamt));

                            }
                        }
                        if(taxrate2!=null){
                            if(facility.getBasic2()){
                               taxamt2 = Math.abs(taxrate2 * ramt);
                                
                            }
                            else{
//                                ramt=ramt+taxamt1;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
                           taxamt2 = Math.abs(taxrate2 * (ramt+taxamt+taxamt1));

                            }
                        }
                        taxamt3=taxamt+taxamt1+taxamt2;
//                                        if (taxamt > 0)
//                                        {
//                                            if (servicetaxacc == null)
//                                            {
//                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//                                                throw new BasicException();
//                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
//                                            dmang.insertintoaccjoutnal3(value4);
//                                        }
                                       if (taxamt > 0)
                                        {
                                            if (servicetaxacc == null)
                                            {
                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
                                                throw new BasicException();
                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
                        if(servicetaxacc!=null){
                                     Object[] value4 = new Object[]
                                            {
                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
                                            };
                                            dmang.insertintoaccjoutnal3(value4);
}
                                           
                                        }
                                        if (taxamt1 > 0)
                                        {
                                            if (servicetaxacc == null)
                                            {
                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
                                                throw new BasicException();
                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
if(servicetaxacc1!=null){
      Object[] value41 = new Object[]
                                            {
                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt1, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc1, 0.0, d, new Date(), true
                                            };
                                            dmang.insertintoaccjoutnal3(value41);
}
                                           
                                        }
                                        if (taxamt2 > 0)
                                        {
                                            if (servicetaxacc == null)
                                            {
                                                JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
                                                throw new BasicException();
                                            }
//                                            Object[] value4 = new Object[]
//                                            {
//                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc, 0.0, d, new Date(), true
//                                            };
if(servicetaxacc2!=null){
      Object[] value42 = new Object[]
                                            {
                                                UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, taxamt2, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facility.getName(), servicetaxacc2, 0.0, d, new Date(), true
                                            };
                                            dmang.insertintoaccjoutnal3(value42);
}
                                           
                                        }
                                        String rnarration = "Renewal Fees for period " + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate);
                                        if (memdep.isSelected())
                                        {
                                            MemberDependent md = (MemberDependent) m_depnamelist.getSelectedItem();
                                            rnarration += " - " + md.getName();
                                        }
//                                        double tramt = dmang.roundTwoDecimals(ramt + taxamt);
                                   double tramt = dmang.roundTwoDecimals(ramt + taxamt3);
                                        //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,ACTIVE
                                        Object[] value1 = new Object[]
                                        {
                                            UUID.randomUUID().toString(), tid, d, "C", facility.getid(), billnum, ramt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), facility.getName(), facility.getRenewalacc(), 0.0, d, true
                                        };
                                        dmang.insertintoaccjoutnal1(value1);
                                        Object[] value2 = new Object[]
                                        {
                                            UUID.randomUUID().toString(), tid, customerInfo.getId(), d, "D", facility.getid(), billnum, tramt, duedate, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), rnarration, caccount, tramt, true
                                        };
                                        dmang.insertintoaccjoutnal(value2);
                                        dmang.updatebillno(facility.getid());
                                        totalamt += tramt;
                                    }
                                }
                                if (p.getqbtype() == false)
                                {
                                    Object[] value = new Object[]
                                    {
                                        UUID.randomUUID().toString(), true, customerInfo.getId(), facility.getid(), sdatetime, m_App.getAppUserView().getUser().getName(), d, 0, user
                                    };
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,STATUS_,USERID) VALUES (?,?,?,?,?,?,?,?,?)" //praveen:changed status to status_
                                            , new SerializerWriteBasic(new Datas[]
                                            {
                                                Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING
                                            })).exec(value);
                                } else
                                {
                                    Object[] value = new Object[]
                                    {
                                        UUID.randomUUID().toString(), true, customerInfo.getId(), facility.getid(), sdatetime, m_App.getAppUserView().getUser().getName(), d, edate, d, billnum, 0, user
                                    };
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,LBILLDATE,NBILLDATE,BILLREF,STATUS_,USERID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" //praveen:changed status to status_
                                            , new SerializerWriteBasic(new Datas[]
                                            {
                                                Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.INT, Datas.STRING
                                            })).exec(value);
                                }
                                dmang.setmemberDebt(customerInfo.getId(), facility.getid(), totalamt);
                                reset();
                            }
                        //  }else{
                        //      JOptionPane.showMessageDialog(this, "Fields member number and member name dont match", "Wrong member number or name", JOptionPane.OK_OPTION);
                        //  }
                        } else
                        {
                            JOptionPane.showMessageDialog(null, customerInfo.getName() + " is already using the facility " + facility.getName(), null, JOptionPane.OK_OPTION);
                        }
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Enter all fields", "Cannot Create", JOptionPane.OK_OPTION);
                    }
                    return null;
                }
            };
            t.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        try
        {
            ftype.setSelectedIndex(0);
            //  memfac = MemFacilityTableModel.loadInstance(m_App,null);
            //  jTable1.setModel(memfac.getTableModel());
            jTable1.setVisible(false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try
        {
            if (ftype.getSelectedItem() != null)
            {
                jTable1.setVisible(true);
                jButton6.setVisible(true);
                Facility fac = (Facility) ftype.getSelectedItem();
                memfac = MemFacilityTableModel.loadInstance(m_App, fac.getid());
                jTable1.setModel(memfac.getTableModel());
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void mnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnoKeyPressed
        // TODO add your handling code here:
        //  String del=(String)evt.getKeyText(evt.getKeyCode());

        if (evt.getKeyText(evt.getKeyCode()).equals("Enter"))
        {
            editIndicator.setVisible(false);

            try
            {
                Object[] obj = dmang.getMamberbySkey(mno.getText().toUpperCase());
                if (obj == null)
                {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else
                {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(mno.toString().toUpperCase());
                    // customerInfo.setMemType(obj[2]);
                    //   if(obj[2]!=null){
                 /*  List temp=dmang.getOptionalFacilitySpecificToType(0);
                    facilitytable=new ComboBoxValModel(temp);
                    facilitytype.setModel(facilitytable);*/
                    List<Facility> temp = dmang.getOptionalFacilitySpecificToType(0);
                    List<Facility> temp1 = dmang.getmemberfacility(customerInfo.getId());
                    List<Facility> temp2 = new ArrayList<Facility>();
                    temp2.addAll(temp);
                    for (Facility f : temp)
                    {
                        for (Facility f2 : temp1)
                        {
                            if (f.getid().equals(f2.getid()))
                            {
                                temp2.remove(f);
                                break;
                            }
                        }
                    }
                    facilitytable = new ComboBoxValModel(temp2);
                    facilitytype.setModel(facilitytable);
                    //   }else{
                    //     JOptionPane.showMessageDialog(this,"Please specify members type", null, JOptionPane.OK_OPTION);
                    //     facilitytable=new ComboBoxValModel(new ArrayList());
                    //   }
                    depMemModel = new ComboBoxValModel();
                    m_depnamelist.setModel(depMemModel);
                    // facilitytype.setModel(facilitytable);
                    mname.setText(obj[1].toString());
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            editIndicator.setVisible(true);
            mname.setText(null);
            customerInfo = null;
        }
    }//GEN-LAST:event_mnoKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try
        {
            int row = jTable1.getSelectedRow();
            if (row < jTable1.getRowCount() && row >= 0)
            {
                String id = jTable1.getModel().getValueAt(row, 6).toString();
                String mname1 = jTable1.getModel().getValueAt(row, 1).toString();
                String fname = jTable1.getModel().getValueAt(row, 2).toString();

                if (JOptionPane.showConfirmDialog(this, "Do you want to deactivate " + fname + " facility for " + mname1 + " ?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    new PreparedSentence(m_App.getSession(), "UPDATE MEMFACILITYUSAGE SET ACTIVE=FALSE,DEACTIVATEDBY =?,DEACTIVATEDDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]
                            {
                                Datas.STRING, Datas.TIMESTAMP, Datas.STRING
                            })).exec(new Object[]
                            {
                                m_App.getAppUserView().getUser().getName(), new Date(), id
                            });
                    Facility f = (Facility) ftype.getSelectedItem();
                    memfac = MemFacilityTableModel.loadInstance(m_App, f.getid());
                    jTable1.setModel(memfac.getTableModel());
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();
        if (tabno == 1)
        {
            try
            {
                ftype.setSelectedIndex(0);
                jTable1.setVisible(false);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void memStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_memStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_memStateChanged

    private void memdepStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_memdepStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_memdepStateChanged

    private void memdepItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memdepItemStateChanged
        // TODO add your handling code here:
        try
        {
            if (memdep.isSelected() == true)
            {
                jLabel7.setVisible(true);
                m_depnamelist.setVisible(true);
                List<Facility> temp = dmang.getOptionalFacilitySpecificToType(1);
                //List<Facility> temp1=dmang.getmembersDepfacility(customerInfo.getId());
                facilitytable = new ComboBoxValModel(temp);
                facilitytype.setModel(facilitytable);
                List memDepList = dmang.getDependentsOfMember().list(customerInfo.getId());
                depMemModel = new ComboBoxValModel(memDepList);
                m_depnamelist.setModel(depMemModel);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_memdepItemStateChanged

    private void memItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memItemStateChanged
        // TODO add your handling code here:
        try
        {
            if (mem.isSelected() == true && customerInfo != null)
            {

                jLabel7.setVisible(false);
                m_depnamelist.setVisible(false);
                m_depnamelist.setModel(new ComboBoxValModel());
                List<Facility> temp = dmang.getOptionalFacilitySpecificToType(0);
                List<Facility> temp1 = dmang.getmemberfacility(customerInfo.getId());
                List<Facility> temp2 = new ArrayList<Facility>();
                temp2.addAll(temp);
                for (Facility f : temp)
                {
                    for (Facility f2 : temp1)
                    {
                        if (f.getid().equals(f2.getid()))
                        {
                            temp2.remove(f);
                            break;
                        }
                    }
                }
                facilitytable = new ComboBoxValModel(temp2);
                facilitytype.setModel(facilitytable);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_memItemStateChanged

    private void m_depnamelistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_depnamelistItemStateChanged
        // TODO add your handling code here:
        try
        {
            if (m_depnamelist.getSelectedIndex() != -1)
            {
                MemberDependent depmem = (MemberDependent) m_depnamelist.getSelectedItem();
                List<Facility> temp = dmang.getOptionalFacilitySpecificToType(1);
                List<Facility> temp2 = new ArrayList<Facility>();
                temp2.addAll(temp);
                List<Facility> temp1 = dmang.getmembersDepfacility(customerInfo.getId());
                for (Facility f : temp)
                {
                    for (Facility f2 : temp1)
                    {
                        if (depmem.getID().equals(f2.getMemtype()) && f.getid().equals(f2.getid()))
                        {
                            temp2.remove(f);
                            break;
                        }
                    }
                }
                facilitytable = new ComboBoxValModel(temp2);
                facilitytype.setModel(facilitytable);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_m_depnamelistItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6ActionPerformed
    {//GEN-HEADEREND:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try
        {
            if (ftype.getSelectedIndex() != -1 && jTable1.getRowCount() > 0)
            {

                genarateReport(ftype.getSelectedItem().toString());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void memActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memActionPerformed

    private void facilitytypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilitytypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facilitytypeActionPerformed

    private void genarateReport(String facname)
    {
        Map reportparam = new HashMap();
        //sameer adding company name and address
        reportparam.put("companyName", m_App.getSession().getCompanyName());
        reportparam.put("companyAddress", m_App.getSession().getCompanyAddress());
        reportparam.put("memtype", facname);
        reportparam.put("date",new Date());
        DataSourceFacilityUsage ds = new DataSourceFacilityUsage( memfac.getfacilityline());
        DataSourceProvider data1 = new DataSourceProvider( memfac.getfacilityline());
        data1.setDataSource(ds);
        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/facilityusage.jrxml", reportparam, false, data1, true, null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel editIndicator;
    private javax.swing.JComboBox facilitytype;
    private javax.swing.JComboBox ftype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox m_depnamelist;
    private javax.swing.JRadioButton mem;
    private javax.swing.JRadioButton memdep;
    private javax.swing.JTextField mname;
    private javax.swing.JTextField mno;
    private javax.swing.JTextField sdate;
    // End of variables declaration//GEN-END:variables
}
