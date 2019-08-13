package com.openbravo.pos.Accounts;

//import LastBillDate;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.DebtTypeTableModel;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.clubmang.FacilityLogic;
import com.openbravo.pos.clubmang.JRBasicField;
import com.openbravo.pos.clubmang.MemCat;
import com.openbravo.pos.clubmang.Minusage;
import com.openbravo.pos.clubmang.Periodicity;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.TaxInfo;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperPrint;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.CustomerInfo1;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author swathi
 */
public class JPanelMinUsage extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private ComboBoxValModel periodtypemodel;
    private ComboBoxValModel facilitiesmodel;
    private ComboBoxValModel minusagetypemodel;
    private ComboBoxValModel MemType;
    private ComboBoxValModel accmodel;
    private DataLogicFacilities dfac;
    private MinUsageDataModel mmodel1;
    private ComboBoxValModel debttypemodel;
    private ComboBoxValModel staxmodel;
        private ComboBoxValModel staxmodel2;
    private ComboBoxValModel staxmodel3;

    private DataLogicSales m_dlSales;
    private Date dnow;
    private Date billingDate;
    private List facilitylist;
    private List periods;
    private List memusagelist;
    private List mlist1;
    private List mlist2;
    private List<MemCat> mlist4;
    private List<AccountMasterExt> alist;
    private List<Minusage> mlist3;
    private FacilityTypeListModel mltmodel;
    private FacilityTypeListModel mltmodel1;
    private MemTypeListModel mltmodel2;
    private List memtypelist;
    private List memtypelist1;
    private List memtypelist2;
    //private double taxrate;
    // private double tax1 = .103;
    private List memtype;
    private String ftype;
    private String utype;
    private String type;
    private String mtype;
    private String billnum;
    private String period;
    private String name;
    private MinUsageModel minmodel;
    private TaxesLogic taxeslogic;
    private Double totalsum = 0.0;
    private Double taxrate1 = 0.0;
    private Double taxtotal = 0.0;
    private Double rate = 0.0;
    private Double tax = 0.0;
    private boolean billed = false;
    Map chargableCustomers = new HashMap();
    List<MinUsageCustomer> custAll = new ArrayList<MinUsageCustomer>();
    String showBillNos = ""; 
     private Boolean cascade1;
    private Boolean cascade2;
    private Boolean basic1;
    private Boolean basic2;

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        iuamt.setEnabled(false);
        ouamt.setEnabled(false);


    }

    public void activate() throws BasicException {        
        jList2.setVisible(false);
        jButton7.setVisible(false);
        jLabel30.setVisible(false);
        jTextField12.setVisible(false);
        jTextField4.setVisible(false);
        jLabel17.setVisible(false);
        jButton11.setVisible(false);
        jTextField7.setVisible(false);
        jLabel23.setVisible(false);
        taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
        dnow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dnow.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        dnow.setTime(cal.getTimeInMillis());
        loadData();
        jButton13.setEnabled(false);
    }

    /** Creates new form JPanelMinUsage */
    public JPanelMinUsage() {
        initComponents();
    }

    public double getTotal() {
        return totalsum;
    }

    public void setTaxrate1(Double taxrate1) {
        this.taxrate1 = taxrate1;
    }

    public void setTaxtotal(Double taxtotal) {
        this.taxtotal = taxtotal;
    }

    public void setTotalsum(Double totalsum) {
        this.totalsum = totalsum;
    }

    public double getTaxtotal() {
        return taxtotal;
    }

    public String getBillNo() {
        return billnum;
    }

    public String getPeriod() {
        return period;
    }

    public String getName1() {
        return name;
    }


    public void loadData() throws BasicException {
        
        jTabbedPane1.removeTabAt(2);
        List mlist1 = dfac.getFacility();
        facilitiesmodel = new ComboBoxValModel(mlist1);
        facilities.setModel(facilitiesmodel);
        List mlist2 = dfac.getPeriodicity().list();
        periodtypemodel = new ComboBoxValModel(mlist2);
        usageperiod.setModel(periodtypemodel);
        List mlist3 = dfac.getMinUsage();
        minusagetypemodel = new ComboBoxValModel(mlist3);
        jComboBox3.setModel(minusagetypemodel);

        List alist = dfac.getaccounts();
        accmodel = new ComboBoxValModel(alist);
        jComboBox5.setModel(accmodel);
        List mlist4 = dfac.getMemberCategory();
        mlist4.add(0, "ALL");
        MemType = new ComboBoxValModel(mlist4);
        membertype.setModel(MemType);
        jTabbedPane1.setSelectedIndex(0);
        debttypemodel = new ComboBoxValModel(dfac.getDebtType());
        jComboBox6.setModel(debttypemodel);
        List staxlist = m_dlSales.getTaxCategoriesList().list();
         List staxlist2 = m_dlSales.getTaxCategoriesList().list();
          List staxlist3 = m_dlSales.getTaxCategoriesList().list();
        staxlist.add(0, null);
        staxlist2.add(0, null);
        staxlist3.add(0, null);
        staxmodel = new ComboBoxValModel(staxlist);
        staxmodel2 = new ComboBoxValModel(staxlist2);
        staxmodel3 = new ComboBoxValModel(staxlist3);
        jComboBox7.setModel(staxmodel);
         jComboBox1.setModel(staxmodel2);
          jComboBox2.setModel(staxmodel3);
        //list2.add(0,"Monthly");
        //list2.add(1,"Quarterly");
        //list2.add(2,"Yearly");
        //periodtypemodel=new ComboBoxValModel(list2);
        //jComboBox2.setModel(periodtypemodel);
        memtypelist = new ArrayList();
        memtypelist1 = new ArrayList();
        memtype = new ArrayList();
        sdate.setText(Formats.TIMESTAMP.formatValue(new Date()));
        jTextField5.setText(Formats.TIMESTAMP.formatValue(new Date()));
        minmodel = MinUsageModel.loadInstance(m_App, 1);
        jTable2.setModel(minmodel.getMinUsageModel());
    }

    private void createbill(MinUsageCustomer cust, String billno, Minusage mintemp, String tid, DebtTypeTableModel.DebtTypeline dueperiod, TaxInfo taxinfo, TaxInfo taxinfo2, TaxInfo taxinfo3) throws BasicException {
        //  Date d=new Date();
        Object mobile = cust.getMobile();
        String customeraccount = cust.getAccount();
        String user = cust.getName();
        String memid = cust.getCid();
        Date bdate = cust.getBillDate();
        String narration = cust.getNarration();
        Timestamp bt = new Timestamp(bdate.getTime());
        FacilityLogic fl = new FacilityLogic();
        Date duedate = fl.getDueDate(dueperiod, bt);
        Double camount = cust.getCamtTotal();
        rate = dfac.roundTwoDecimals(camount);
        totalsum += camount;

        tax = taxinfo.getRate();
        taxrate1 = tax * rate;
        taxtotal += taxrate1;
        camount = camount + taxrate1;
        Object[] value = new Object[]{UUID.randomUUID().toString(), tid, memid, new Date(), "D", mintemp.getName(), billno, camount, duedate, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, customeraccount, camount, true};
        dfac.insertintoaccjoutnal(value);
        
        String smsmsg = "Dear Member,\rYour a/c with us has been debited by Rs " + dfac.ConvertDoubleToString(camount) + " for " + mintemp.getName() + " on " + Formats.DATE.formatValue( new Date()) + " bill no " + billno + ".Thank u";
        if (mobile != null && mobile.toString().trim().length() == 10) {
            dfac.updatetosendMsg(smsmsg, memid, mobile.toString(), 2);
        }
        if(cust.isDeactivate())
        {
        Object[] value1 = new Object[]{memid,mintemp.getid()};
        new PreparedSentence(m_App.getSession(), "UPDATE MEMMINUSAGE SET ACTIVE='FALSE' WHERE MEMNO=? AND USAGETYPE=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING, Datas.STRING})).exec(value1);
        }

    }

    public void showDialog(MinUsageDataModel mmodel1, AppView m_App) {

        this.m_App = m_App;
        jScrollPane4.setAutoscrolls(true);
        /*if(rflist.size()>1)
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        else
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_SUBSEQUENT_COLUMNS);*/
        jTable1.setModel(mmodel1.getUsageModel());
        this.mmodel1 = mmodel1;
        int columncnt = jTable1.getColumnModel().getColumnCount();
        if (columncnt > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            for (int k = 2; k < columncnt; k++) {
                if ((k - 2) % 3 == 0) {
                    jTable1.getColumnModel().getColumn(k).setMaxWidth(150);
                    jTable1.getColumnModel().getColumn(k).setPreferredWidth(150);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
                } else if ((k - 2) % 3 == 1) {
                    jTable1.getColumnModel().getColumn(k).setMaxWidth(60);
                    jTable1.getColumnModel().getColumn(k).setPreferredWidth(60);
                //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
                } else if ((k - 2) % 3 == 2) {
                    jTable1.getColumnModel().getColumn(k).setMaxWidth(60);
                    jTable1.getColumnModel().getColumn(k).setPreferredWidth(60);
                // jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
                }
            }
        }
        setVisible(true);
    }

    private void manageButtons(boolean value) {
        jButton1.setEnabled(value);
        jButton2.setEnabled(value);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        minusagename = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        includedfac = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        facilities = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        usageperiod = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        iuamt = new javax.swing.JTextField();
        ouamt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        usagecharge = new javax.swing.JTextField();
        membertype = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        includedmemtype = new javax.swing.JList();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        sdate = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        billseq = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox7 = new javax.swing.JComboBox();
        maxdebt = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        smsform = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        effdate = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();

        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "period", "usage amt", "charge", "created date", "acc head", "applicable to", "deativated by", "deactivated date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setName("jTable2"); // NOI18N
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton11.setText("Edit");
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Deactivate");
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jCheckBox1InputMethodTextChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Show All");
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(428, 428, 428)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(487, 487, 487)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1292, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel12))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List", jPanel3);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setText("Name");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 40, 100, 20);

        minusagename.setName("minusagename"); // NOI18N
        jPanel1.add(minusagename);
        minusagename.setBounds(120, 40, 110, 20);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        includedfac.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        includedfac.setName("includedfac"); // NOI18N
        jScrollPane1.setViewportView(includedfac);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(160, 240, 150, 60);

        jLabel2.setText("Facilities Included");
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 200, 140, 20);

        jLabel3.setText("List Of Facilities Included ");
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 240, 150, 20);

        facilities.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        facilities.setName("facilities"); // NOI18N
        facilities.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                facilitiesItemStateChanged(evt);
            }
        });
        facilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilitiesActionPerformed(evt);
            }
        });
        jPanel1.add(facilities);
        facilities.setBounds(160, 200, 150, 20);

        jLabel4.setText("Period");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(250, 40, 80, 20);

        usageperiod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        usageperiod.setName("usageperiod"); // NOI18N
        jPanel1.add(usageperiod);
        usageperiod.setBounds(300, 40, 110, 20);

        jLabel5.setText("Overall Minimum Usage amount");
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 160, 180, 30);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jRadioButton1);
        jRadioButton1.setBounds(190, 160, 20, 30);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });
        jPanel1.add(jRadioButton2);
        jRadioButton2.setBounds(240, 160, 20, 30);

        jLabel6.setText("Individual Minimum Usage Amt");
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 124, 180, 30);

        jLabel7.setText("Yes");
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(210, 160, 40, 30);

        jLabel8.setText("No");
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(260, 160, 30, 30);

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("jRadioButton3");
        jRadioButton3.setName("jRadioButton3"); // NOI18N
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });
        jPanel1.add(jRadioButton3);
        jRadioButton3.setBounds(190, 130, 20, 23);

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("jRadioButton4");
        jRadioButton4.setName("jRadioButton4"); // NOI18N
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });
        jPanel1.add(jRadioButton4);
        jRadioButton4.setBounds(240, 130, 20, 23);

        jLabel9.setText("Yes");
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(210, 130, 30, 20);

        jLabel10.setText("No");
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(260, 130, 20, 20);

        iuamt.setName("iuamt"); // NOI18N
        jPanel1.add(iuamt);
        iuamt.setBounds(290, 130, 80, 20);

        ouamt.setName("ouamt"); // NOI18N
        jPanel1.add(ouamt);
        ouamt.setBounds(290, 170, 80, 20);

        jLabel13.setText("Charges full if minimum usage not achieved");
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(30, 390, 320, 20);

        jLabel15.setText("Proportionate Charges to deficit ");
        jLabel15.setName("jLabel15"); // NOI18N
        jPanel1.add(jLabel15);
        jLabel15.setBounds(30, 430, 230, 20);

        jLabel14.setText("(Stipulated Minimum Usage) -(Usage) OR Minimum Usage whichever is greater");
        jLabel14.setName("jLabel14"); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 460, 590, 20);

        jButton3.setText("Save");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(220, 540, 240, 23);

        jButton5.setText("Add");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(320, 200, 70, 23);

        jButton7.setText("Remove");
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(320, 310, 90, 23);

        jButton6.setText("Remove");
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(320, 240, 80, 23);

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setText("jRadioButton5");
        jRadioButton5.setName("jRadioButton5"); // NOI18N
        jPanel1.add(jRadioButton5);
        jRadioButton5.setBounds(10, 390, 20, 23);

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setText("jRadioButton6");
        jRadioButton6.setName("jRadioButton6"); // NOI18N
        jPanel1.add(jRadioButton6);
        jRadioButton6.setBounds(10, 460, 20, 23);

        buttonGroup3.add(jRadioButton7);
        jRadioButton7.setText("jRadioButton7");
        jRadioButton7.setName("jRadioButton7"); // NOI18N
        jPanel1.add(jRadioButton7);
        jRadioButton7.setBounds(10, 430, 20, 23);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setName("jList2"); // NOI18N
        jScrollPane3.setViewportView(jList2);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(160, 310, 150, 70);

        jLabel19.setText("Minimum Usage Charge");
        jLabel19.setName("jLabel19"); // NOI18N
        jPanel1.add(jLabel19);
        jLabel19.setBounds(350, 440, 170, 20);

        usagecharge.setName("usagecharge"); // NOI18N
        usagecharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usagechargeActionPerformed(evt);
            }
        });
        jPanel1.add(usagecharge);
        usagecharge.setBounds(520, 440, 130, 20);

        membertype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        membertype.setName("membertype"); // NOI18N
        membertype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                membertypeActionPerformed(evt);
            }
        });
        jPanel1.add(membertype);
        membertype.setBounds(520, 80, 130, 20);

        jLabel20.setName("jLabel20"); // NOI18N
        jPanel1.add(jLabel20);
        jLabel20.setBounds(0, 0, 0, 0);

        jLabel21.setText("Applicable to Type of Member");
        jLabel21.setName("jLabel21"); // NOI18N
        jPanel1.add(jLabel21);
        jLabel21.setBounds(310, 80, 210, 20);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        includedmemtype.setName("includedmemtype"); // NOI18N
        jScrollPane5.setViewportView(includedmemtype);

        jPanel1.add(jScrollPane5);
        jScrollPane5.setBounds(520, 110, 130, 70);

        jButton9.setText("Add");
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);
        jButton9.setBounds(660, 70, 70, 23);

        jButton10.setText("Remove");
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);
        jButton10.setBounds(660, 110, 90, 23);

        jLabel22.setText("A/C Head");
        jLabel22.setName("jLabel22"); // NOI18N
        jPanel1.add(jLabel22);
        jLabel22.setBounds(450, 490, 70, 20);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setName("jComboBox5"); // NOI18N
        jPanel1.add(jComboBox5);
        jComboBox5.setBounds(520, 490, 130, 20);

        jLabel11.setText("Member Type List");
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(390, 110, 110, 20);

        jLabel24.setText("Start Date");
        jLabel24.setName("jLabel24"); // NOI18N
        jPanel1.add(jLabel24);
        jLabel24.setBounds(440, 40, 60, 20);

        sdate.setName("sdate"); // NOI18N
        jPanel1.add(sdate);
        sdate.setBounds(520, 40, 130, 20);

        jLabel25.setText("Credit Period");
        jLabel25.setName("jLabel25"); // NOI18N
        jPanel1.add(jLabel25);
        jLabel25.setBounds(410, 220, 110, 20);

        jLabel26.setText("Bill Sequence");
        jLabel26.setName("jLabel26"); // NOI18N
        jPanel1.add(jLabel26);
        jLabel26.setBounds(410, 250, 110, 20);

        jLabel27.setText("Tax category1");
        jLabel27.setName("jLabel27"); // NOI18N
        jPanel1.add(jLabel27);
        jLabel27.setBounds(410, 280, 110, 20);

        jLabel28.setText("Max Debt Allowed");
        jLabel28.setName("jLabel28"); // NOI18N
        jPanel1.add(jLabel28);
        jLabel28.setBounds(380, 410, 140, 20);

        billseq.setName("billseq"); // NOI18N
        jPanel1.add(billseq);
        billseq.setBounds(520, 250, 130, 20);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setName("jComboBox6"); // NOI18N
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox6);
        jComboBox6.setBounds(520, 220, 130, 20);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.setName("jComboBox7"); // NOI18N
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox7);
        jComboBox7.setBounds(520, 280, 130, 20);

        maxdebt.setName("maxdebt"); // NOI18N
        jPanel1.add(maxdebt);
        maxdebt.setBounds(520, 410, 130, 20);

        jLabel29.setText("SMS Short Form");
        jLabel29.setName("jLabel29"); // NOI18N
        jPanel1.add(jLabel29);
        jLabel29.setBounds(10, 90, 100, 20);

        smsform.setName("smsform"); // NOI18N
        jPanel1.add(smsform);
        smsform.setBounds(120, 90, 110, 20);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(660, 40, 49, 25);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);
        jButton14.setBounds(660, 190, 49, 25);

        jLabel31.setText("Effct've.Date");
        jLabel31.setName("jLabel31"); // NOI18N
        jPanel1.add(jLabel31);
        jLabel31.setBounds(410, 190, 110, 30);

        effdate.setName("effdate"); // NOI18N
        jPanel1.add(effdate);
        effdate.setBounds(520, 190, 130, 20);

        jLabel32.setText("Tax Categore2");
        jLabel32.setName("jLabel32"); // NOI18N
        jPanel1.add(jLabel32);
        jLabel32.setBounds(410, 310, 110, 20);

        jLabel33.setText("Tax Category3");
        jLabel33.setName("jLabel33"); // NOI18N
        jPanel1.add(jLabel33);
        jLabel33.setBounds(410, 360, 110, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(520, 310, 130, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(520, 360, 130, 20);

        jRadioButton8.setSelected(true);
        jRadioButton8.setText("Basic");
        jRadioButton8.setName("jRadioButton8"); // NOI18N
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton8);
        jRadioButton8.setBounds(520, 340, 70, 20);

        jRadioButton9.setText("Cascade");
        jRadioButton9.setName("jRadioButton9"); // NOI18N
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton9);
        jRadioButton9.setBounds(595, 340, 90, 20);

        jRadioButton10.setSelected(true);
        jRadioButton10.setText("Basic");
        jRadioButton10.setName("jRadioButton10"); // NOI18N
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton10);
        jRadioButton10.setBounds(520, 390, 49, 20);

        jRadioButton11.setText("Cascade");
        jRadioButton11.setName("jRadioButton11"); // NOI18N
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton11);
        jRadioButton11.setBounds(595, 390, 140, 20);

        jTabbedPane1.addTab("Define Minimum Usage", jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel16.setText("Minimum Usage Type");
        jLabel16.setName("jLabel16"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N

        jLabel17.setText("UpTo Date");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText("Date");
        jLabel18.setName("jLabel18"); // NOI18N

        jButton2.setText("Bill");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField5.setName("jTextField5"); // NOI18N

        jButton1.setText("Generate");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane4.setViewportView(jTable1);

        jTextField7.setName("jTextField7"); // NOI18N

        jLabel23.setText("Bill No");
        jLabel23.setName("jLabel23"); // NOI18N

        jButton13.setText("Test Print");
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel30.setText("Period");
        jLabel30.setName("jLabel30"); // NOI18N

        jTextField12.setName("jTextField12"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23)
                                .addComponent(jLabel18)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(269, 269, 269))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(1268, 1268, 1268))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton13))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Billing", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();
        if (tabno == 2) {
            try {
                if (jCheckBox1.isSelected() == true) {
                    minmodel = MinUsageModel.loadInstance(m_App, 2);
                    jTable2.setModel(minmodel.getMinUsageModel());
                } else if (jCheckBox1.isSelected() == false) {
                    minmodel = MinUsageModel.loadInstance(m_App, 1);
                    jTable2.setModel(minmodel.getMinUsageModel());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        try {
            Minusage usage = (Minusage) jComboBox3.getSelectedItem();
            Map reportparams = new HashMap();
            reportparams.put("companyName",m_App.getSession().getCompanyName());
            reportparams.put("companyAddress",m_App.getSession().getCompanyAddress());
            reportparams.put("UsageType", this.getName1());
            reportparams.put("Rate", this.rate);
            reportparams.put("Period", this.getPeriod());
            reportparams.put("billnum", this.getBillNo());
            reportparams.put("billedby", m_App.getAppUserView().getUser().getName());
            reportparams.put("tax", this.tax);
            reportparams.put("taxvalue", this.getTaxtotal());
            reportparams.put("totalrate", this.getTotal());
            DataSourceProvider data1 = new DataSourceProvider(mmodel1.getMinUsageCustomer());
            data1.setFields(getFields());
            DataSource4 ds = new DataSource4(mmodel1.getMinUsageCustomer());
            data1.setDataSource(ds);
            //JasperPrint jp= JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MinUse.jrxml",reportparams,true,data1,false,null);
            JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MinUse.jrxml", reportparams, false, data1, true, null);
            jButton3.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         /*try{
        jTable1.setVisible(true);
        Minusage fac=(Minusage)jComboBox3.getSelectedItem();

        if(fac.getCharge()>0){
        Periodicity p=dfac.getPerioicitybyid(fac.getPeriod());
        if(p.getqbtype()==false){
        try{
        mmodel = MinUsageTableModel.loadInstance(m_App,fac,dfac);
        jTable1.setModel(mmodel.getTableModel());
        // jTable1.getModel().addTableModelListener(this);
        }
        catch(Exception e){
        e.printStackTrace();
        }
        jButton2.setEnabled(true);
        jButton2.setVisible(true);
        }else{
        mmodel = MinUsageTableModel.loadInstance1(m_App,fac,dfac,m_dlSales,taxrate);
        jTable1.setModel(mmodel.getTableModel());
        jButton2.setEnabled(true);
        jButton2.setVisible(true);
        }
        }else{
        mmodel = MinUsageTableModel.emptyinstance();
        jTable1.setModel(mmodel.getTableModel());
        }
        }catch(Exception e){
        e.printStackTrace();
        }*/
        try{
            newMinUsageLogic();
        }catch(Exception e){
            e.printStackTrace();
        }
    /*try {
    newMinUsageLogic();
    jButton13.setEnabled(false);
    manageButtons(false);
    billed = false;
    totalsum = 0.0;
    taxtotal = 0.0;
    Minusage usage = (Minusage) jComboBox3.getSelectedItem();
    if (usage.getMinusagetype().equals("1")) {
    type = "1";
    } else if (usage.getMinusagetype().equals("2")) {
    type = "2";
    } else if (usage.getMinusagetype().equals("3")) {
    type = "3";
    }
    String faci = usage.getFacilities();
    String u = usage.getid();
    double a = usage.getAmount();
    double a1 = usage.getCharge();
    FacilityLogic flogic = new FacilityLogic();
    Date dnow = new Date();
    Date edate = usage.getLastbilleddate();
    String id = usage.getPeriod();
    Periodicity p1;
    p1 = dfac.getPerioicitybyid(id);
    Date sdate = flogic.calculateStartDate1(p1, edate);
    edate = flogic.calculateEndDate2(sdate, p1);
    /*if(edate.compareTo(dnow)<0)
    {
    edate=flogic.calculateEndDate2(edate, p1);
    if(edate.compareTo(dnow)<0){
    edate=flogic.calculateEndDate2(edate, p1);
    }
    }
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(sdate.getTime());
    cal.set(Calendar.HOUR_OF_DAY, 00);
    cal.set(Calendar.MINUTE, 00);
    cal.set(Calendar.SECOND, 00);
    cal.set(Calendar.MILLISECOND, 01);
    cal.set(Calendar.AM_PM, Calendar.AM);
    sdate.setTime(cal.getTimeInMillis());
    Calendar cal1 = Calendar.getInstance();
    cal1.setTimeInMillis(edate.getTime());
    cal1.set(Calendar.HOUR_OF_DAY, 23);
    cal1.set(Calendar.MINUTE, 59);
    cal1.set(Calendar.SECOND, 59);
    cal1.set(Calendar.MILLISECOND, 59);
    cal1.set(Calendar.AM_PM, Calendar.PM);
    edate.setTime(cal1.getTimeInMillis());
    if (edate.compareTo(dnow) < 0) {
    jTextField4.setText(Formats.TIMESTAMP.formatValue(edate));
    mmodel1 = MinUsageDataModel.loadData(m_App, type, a, sdate, edate, a1, u);
    showDialog(mmodel1, m_App);
    } else {
    JOptionPane.showMessageDialog(this, "Already billed", null, JOptionPane.OK_OPTION);
    }
    } catch (Exception e) {
    e.printStackTrace();

    }
    manageButtons(true);*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        newBillLogic();
        // TODO add your handling code here:
/*int noofrows = mmodel1.getUsageModel().getRowCount();
    if (billed == false) {
    if (noofrows > 0 && JOptionPane.showConfirmDialog(this, "Do u want to bill ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    manageButtons(false);
    billed = true;
    totalsum = 0.0;
    try {

    String tid = UUID.randomUUID().toString();
    Minusage mintemp = new Minusage();
    mintemp = (Minusage) jComboBox3.getSelectedItem();
    String billno = dfac.getnewbillno1(mintemp.getid());
    jTextField7.setText(billno);
    billnum = billno;

    int j = 0;

    for (int i = 0; i < noofrows; i++) {
    // FacilityBillingTableModel.Facilityline fline=fmodel.getfacilityline().get(i);
    boolean selected = (Boolean) mmodel1.getUsageModel().getValueAt(i, 12);
    String amt = mmodel1.getUsageModel().getValueAt(i, 3).toString();
    if (selected == true && !amt.equals("0.0")) {

    //updatememusage(i, billno, mintemp, tid);
    j++;
    }
    }


    dfac.updatebillno1(mintemp.getid());
    String servicetaxacc = null;
    Object stacc = new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find("Service Tax Account");
    if (stacc != null) {
    servicetaxacc = stacc.toString();
    }
    //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE
    Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", mintemp.getName(), billno, getTotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), mintemp.getName() + " on " + Formats.DATE.formatValue(dnow), mintemp.getAcchead(), 0.0, dnow, true};
    dfac.insertintoaccjoutnal1(value1);
    if (getTaxtotal() > 0 && servicetaxacc != null) {
    Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", mintemp.getName(), billno, getTaxtotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax for bill number " + billno, servicetaxacc, 0.0, dnow, true};
    dfac.insertintoaccjoutnal1(value2);
    } else {
    if (servicetaxacc == null) {
    JOptionPane.showMessageDialog(this, "Please Create a service tax account", null, JOptionPane.OK_OPTION);
    }
    }
    //  mmodel1 = MinUsageDataModel.loadEmptyInstance();
    // jTable1.setModel(mmodel1.getUsageModel());
    jButton13.setEnabled(true);
    }
    catch




    (


    BasicExceptione) {
    e.printStackTrace();
    }
    manageButtons(


    true);

    }
    }

    else {
    JOptionPane.showMessageDialog(this, "Already billed. Please generate if u want to bill again");
    }

 */
}//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        if (jComboBox3.getSelectedItem() != null) {
            Minusage pid = (Minusage) jComboBox3.getSelectedItem();
            String id = pid.getPeriod();
            Periodicity p;
            try {
                p = dfac.getPerioicitybyid(id);
                jTextField12.setText(p.getName());
                jTextField12.setVisible(true);
                jLabel30.setVisible(true);
                period = p.getName();
            } catch (BasicException ex) {
                ex.printStackTrace();
            }


        }
}//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(sdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTime(this, date);
        if (date != null) {
            sdate.setText(Formats.TIMESTAMP.formatValue(date));
        }
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        int row = includedmemtype.getSelectedIndex();
        if (row >= 0) {
            memtype.remove(row);
            mltmodel2 = new MemTypeListModel(memtype);
            includedmemtype.setModel(mltmodel2);
        }
}//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (membertype.getSelectedItem() != null) {
            memtype.add(membertype.getSelectedItem());
            mltmodel2 = new MemTypeListModel(memtype);
            includedmemtype.setModel(mltmodel2);

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int row = includedfac.getSelectedIndex();
        if (row >= 0) {
            memtypelist.remove(row);
            mltmodel = new FacilityTypeListModel(memtypelist);
            includedfac.setModel(mltmodel);
        }
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int row = jList2.getSelectedIndex();
        String name1 = minusagename.getText();
        if (row >= 0) {
            try {
                /*new PreparedSentence(m_App.getSession()
                , "DELETE FROM MINUSAGETYPEIN WHERE NAME=?"
                , SerializerWriteString.INSTANCE).exec(name1);*/
                memtypelist1.remove(row);
                mltmodel1 = new FacilityTypeListModel(memtypelist1);
                jList2.setModel(mltmodel1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (facilities.getSelectedItem() != null) {
            if (jRadioButton3.isSelected()) {
                if (iuamt != null && minusagename != null) {
                    try {
                        String fid1 = null;
                        /*Object[] value=new Object[]{UUID.randomUUID().toString(),jTextField1.getText(),Double.valueOf(jTextField2.getText()),fid1};
                        new PreparedSentence(m_App.getSession()
                        , "INSERT INTO MINUSAGETYPEIN(ID,NAME,AMOUNT,MINUSAGETYPE) VALUES (?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING})
                        ).exec(value);*/
                        memtypelist1.add(facilities.getSelectedItem());
                        mltmodel1 = new FacilityTypeListModel(memtypelist1);
                        jList2.setModel(mltmodel1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            memtypelist.add(facilities.getSelectedItem());
            mltmodel = new FacilityTypeListModel(memtypelist);
            includedfac.setModel(mltmodel);
            //jRadioButton3.isFocused(false);
            iuamt.setEnabled(false);
        }
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            Transaction t = new Transaction(m_App.getSession()) {

                public Object transact() throws BasicException {
                    Date d = new Date();

                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(d.getTime());
                    cal.set(Calendar.HOUR_OF_DAY, 00);
                    cal.set(Calendar.MINUTE, 00);
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    d.setTime(cal.getTimeInMillis());

                    String fid = UUID.randomUUID().toString();
                    if (minusagename.getText().length() > 0 && usageperiod.getSelectedItem() != null && jComboBox5.getSelectedItem() != null && memtypelist1 != null && ouamt.getText().length() > 0&& sdate.getText().length() > 0 && effdate.getText().length() > 0) {
                        int count = Integer.valueOf(new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM MINUSAGE WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(minusagename.getText()).toString());
                        Date effectivedate =(Date) Formats.TIMESTAMP.parseValue(sdate.getText());
                        Date efftdate = (Date) Formats.TIMESTAMP.parseValue(effdate.getText());
                        Calendar cal1 = Calendar.getInstance();                        
                         cal1.setTime(efftdate);
                         cal1.set(Calendar.HOUR_OF_DAY,00);
                         cal1.set(Calendar.MINUTE, 00);
                         cal1.set(Calendar.SECOND, 00);
                         cal1.set(Calendar.MILLISECOND,00);
                         cal1.add(Calendar.MONTH, -1);
                         cal1.set(Calendar.DATE,cal1.getActualMaximum(Calendar.DATE));                        

                        if (count == 0) {
                            Periodicity p = (Periodicity) usageperiod.getSelectedItem();
                            String rtemp = null;
                            if (p != null) {
                                rtemp = p.getid();
                            }
                            DebtTypeTableModel.DebtTypeline q = (DebtTypeTableModel.DebtTypeline) jComboBox6.getSelectedItem();
                            String rtemp1 = null;
                            if (q != null) {
                                rtemp1 = q.getid();
                            }
                            AccountMasterExt ac = (AccountMasterExt) jComboBox5.getSelectedItem();
                            String atemp = null;
                            if (ac != null) {
                                atemp = ac.getid();
                            }
                            FacilityTypeListModel fmodel = (FacilityTypeListModel) includedfac.getModel();
                            if (fmodel.getSize() > 0) {
                                for (int j = 0; j < fmodel.getSize(); j++) {
                                    // FacilitytableModel.Facilityline fac =(FacilitytableModel.Facilityline)fmodel.getElementAt(j);
                                    Facility fac = (Facility) fmodel.getElementAt(j);
                                    if (ftype == null) {
                                        ftype = fac.getid();
                                    } else {
                                        ftype += "#" + fac.getid();
                                    }
                                }
                            }

                            TaxCategoryInfo staxcat = (TaxCategoryInfo) jComboBox7.getSelectedItem();
                             TaxCategoryInfo staxcat2 = (TaxCategoryInfo) jComboBox1.getSelectedItem();
                              TaxCategoryInfo staxcat3 = (TaxCategoryInfo) jComboBox2.getSelectedItem();
                            String staxid = staxcat.getID();
                            String staxid2 = staxcat2.getID();
                            String staxid3 = staxcat3.getID();
                            String sms = smsform.getText();
                            String mtype = null;
                            String bill = billseq.getText();
                            String val = maxdebt.getText();
                            MemTypeListModel mtmodel = (MemTypeListModel) includedmemtype.getModel();
                            if (mtmodel.getSize() > 0) {
                                for (int j = 0; j < mtmodel.getSize(); j++) {
                                    if (mtmodel.getElementAt(j).toString().equals("ALL")) {
                                        mtype = "ALL";
                                        break;
                                    } else {
                                        MemCat mem = (MemCat) mtmodel.getElementAt(j);
                                        if (mtype == null) {
                                            mtype = mem.getID();
                                        } else {
                                            mtype += "#" + mem.getID();
                                        }
                                    }
                                }
                            }
                            boolean flag = true;
                            Integer i = 0;  
                            if(jRadioButton8.isSelected() &&jRadioButton10.isSelected()){
                            if (jRadioButton5.isSelected()) {
                                utype = "1";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,false,false,true,true};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            } else if (jRadioButton6.isSelected()) {
                                utype = "2";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,false,false,true,true};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);

                            } else if (jRadioButton7.isSelected()) {
                                utype = "3";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,false,false,true,true};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            }
                            } 
//                            -----------------------------------------------------------
                            else if(jRadioButton8.isSelected() &&jRadioButton11.isSelected()){
                            if (jRadioButton5.isSelected()) {
                                utype = "1";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,false,true,true,false};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            } else if (jRadioButton6.isSelected()) {
                                utype = "2";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,false,true,true,false};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);

                            } else if (jRadioButton7.isSelected()) {
                                utype = "3";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,false,true,true,false};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            }
                            }   
//                            ---------------------------------------------------------
                            else if(jRadioButton10.isSelected() &&jRadioButton9.isSelected()){ 
                            if (jRadioButton5.isSelected()) {
                                utype = "1";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,true,false,false,true};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            } else if (jRadioButton6.isSelected()) {
                                utype = "2";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,true,false,false,true};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);

                            } else if (jRadioButton7.isSelected()) {
                                utype = "3";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,true,false,false,true};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            }
                            }  
//                            ----------------------------------------------------------
                            else{
                                if(jRadioButton11.isSelected() &&jRadioButton9.isSelected()){
                                    if (jRadioButton5.isSelected()) {
                                utype = "1";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,true,true,false,false};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            } else if (jRadioButton6.isSelected()) {
                                utype = "2";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,true,true,false,false};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);

                            } else if (jRadioButton7.isSelected()) {
                                utype = "3";
                                Object[] value = new Object[]{fid, minusagename.getText(), rtemp, ftype, Double.valueOf(ouamt.getText()), utype, new Date(), Double.valueOf(usagecharge.getText()), atemp, mtype, flag, rtemp1, m_App.getAppUserView().getUser().getName(), bill, staxid, val, sms, i,cal1.getTime(),efftdate,staxid2,staxid3,true,true,false,false};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MINUSAGE(ID, NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,MAXNO,LASTBILLEDDATE,EFFECTIVEFROM,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
                            }
                                }
                            }
                            
                            
//                            List<CustomerInfo1> memlist = new ArrayList<CustomerInfo1>();
//                            if (mtype.equals("ALL")) {
//                                memlist = new StaticSentence(m_App.getSession(), "SELECT ID,SEARCHKEY,TAXID,NAME,MEMTYPE FROM CUSTOMERS WHERE VISIBLE=TRUE", null, new SerializerReadClass(CustomerInfo1.class)).list();
//                            } else {
//                                String[] mtypearr = mtype.split("#");
//                                if (mtypearr.length > 0) {
//                                    for (int j = 0; j < mtypearr.length; j++) {
//                                        List<CustomerInfo1> list = new StaticSentence(m_App.getSession(), "SELECT ID,SEARCHKEY,TAXID,NAME,MEMTYPE FROM CUSTOMERS WHERE MEMTYPE= ? AND VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(CustomerInfo1.class)).list(mtypearr[j]);
//                                        memlist.addAll(list);
//                                    }
//                                }
//                            }    
 List<CustomerInfo1> memlist = new ArrayList<CustomerInfo1>();
                            if (mtype.equals("ALL")) {
                                memlist = new StaticSentence(m_App.getSession(), "SELECT ID,SEARCHKEY,TAXID,NAME,MEMTYPE FROM CUSTOMERS", null, new SerializerReadClass(CustomerInfo1.class)).list();
                            } else {
                                String[] mtypearr = mtype.split("#");
                                if (mtypearr.length > 0) {
                                    for (int j = 0; j < mtypearr.length; j++) {
                                        List<CustomerInfo1> list = new StaticSentence(m_App.getSession(), "SELECT ID,SEARCHKEY,TAXID,NAME,MEMTYPE FROM CUSTOMERS WHERE MEMTYPE= ? ", SerializerWriteString.INSTANCE, new SerializerReadClass(CustomerInfo1.class)).list(mtypearr[j]);
                                        memlist.addAll(list);
                                    }
                                }
                            } 
                            
                            for (int j = 0; j < memlist.size(); j++) {
                                String cid = memlist.get(j).getId();
                                int count1 = Integer.valueOf(new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMFACILITYUSAGE WHERE MEMNO=? AND FACILITYTYPE=? AND ACTIVE=TRUE AND USERID IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{cid, fid}).toString());
                                if (count1 == 0) {
                                    int flag1 = 0;
                                    String caccount = null;
                                    try {
                                        caccount = dfac.getCustomerAccountByID(cid);
                                    } catch (Exception e1) {
                                        flag1 = 1;
                                        JOptionPane.showMessageDialog(null, "Member Account not present " + memlist.get(j).getName() + " .Please create one", null, JOptionPane.OK_OPTION);
                                    }
                                }                                 
                                Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, cid, fid, d, m_App.getAppUserView().getUser().getName(), d, 0,cal1.getTime()};
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MEMMINUSAGE(ID, ACTIVE, MEMNO, USAGETYPE, SDATE, CREATEDBY, CRDATE,STATUS_,LBILLDATE) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.TIMESTAMP})).exec(value1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Following usage type already exists", null, JOptionPane.OK_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Fill the form completely", null, JOptionPane.OK_OPTION);
                    }
                    /*Object[] value=new Object[]{fid,jTextField1.getText()};
                    new PreparedSentence(m_App.getSession()
                    , "UPDATE MINUSAGETYPEIN SET MINUSAGETYPE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                    ).exec(value);*/

                    return null;
                }
            };
            t.execute();
            //loadData();
            reset();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting values.Ensure Correct values are inserted", "Error", JOptionPane.OK_OPTION);
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton4.isSelected()) {
            iuamt.setEnabled(false);
        }
}//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton3.isSelected()) {
            iuamt.setEnabled(true);
        }
}//GEN-LAST:event_jRadioButton3ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton2.isSelected()) {
            ouamt.setEnabled(false);

        }
}//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton1.isSelected()) {
            ouamt.setEnabled(true);

        }
}//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void facilitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilitiesActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_facilitiesActionPerformed

    private void facilitiesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_facilitiesItemStateChanged
        // TODO add your handling code here:
        jRadioButton4.setEnabled(true);
        iuamt.setEnabled(false);
}//GEN-LAST:event_facilitiesItemStateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        try {
            if (jCheckBox1.isSelected() == true) {
                minmodel = MinUsageModel.loadInstance(m_App, 2);
                jTable2.setModel(minmodel.getMinUsageModel());
            } else if (jCheckBox1.isSelected() == false) {
                minmodel = MinUsageModel.loadInstance(m_App, 1);
                jTable2.setModel(minmodel.getMinUsageModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        try {
            int row = jTable2.getSelectedRow();
            if (row < jTable2.getRowCount() && row >= 0) {
                String id = jTable2.getModel().getValueAt(row, 17).toString();
                String fname1 = jTable2.getModel().getValueAt(row, 0).toString();
                if (JOptionPane.showConfirmDialog(this, "Do you want to deactivate " + fname1 + " UsageType ?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    new PreparedSentence(m_App.getSession(), "UPDATE MINUSAGE SET ACTIVE=FALSE,DEACTIVATEDBY =?,DEACTIVATEDDATE=? WHERE ID=? AND DEACTIVATEDBY IS NULL AND DEACTIVATEDDATE IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), id});
                    if (jCheckBox1.isSelected() == true) {
                        minmodel = MinUsageModel.loadInstance(m_App, 2);
                        jTable2.setModel(minmodel.getMinUsageModel());
                    } else if (jCheckBox1.isSelected() == false) {
                        minmodel = MinUsageModel.loadInstance(m_App, 1);
                        jTable2.setModel(minmodel.getMinUsageModel());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(sdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTime(this, date);
        if (date != null) {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            effdate.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton8.isSelected()){
            jRadioButton9.setSelected(false);
        }
        else{
            jRadioButton9.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton9.isSelected()){
            jRadioButton8.setSelected(false);
        }
        else{
            jRadioButton8.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton10.isSelected()){
            jRadioButton11.setSelected(false);
        }
        else{
            jRadioButton11.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton11.isSelected()){
            jRadioButton10.setSelected(false);
        }
        else{
            jRadioButton10.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void usagechargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usagechargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usagechargeActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();
        if (tabno == 2) {
            try {
                if (jCheckBox1.isSelected() == true) {
                    minmodel = MinUsageModel.loadInstance(m_App, 2);
                    jTable2.setModel(minmodel.getMinUsageModel());
                } else if (jCheckBox1.isSelected() == false) {
                    minmodel = MinUsageModel.loadInstance(m_App, 1);
                    jTable2.setModel(minmodel.getMinUsageModel());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:  
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();
        if (tabno == 2) {
            try {
                if (jCheckBox1.isSelected() == true) {
                    minmodel = MinUsageModel.loadInstance(m_App, 2);
                    jTable2.setModel(minmodel.getMinUsageModel());
                } else if (jCheckBox1.isSelected() == false) {
                    minmodel = MinUsageModel.loadInstance(m_App, 1);
                    jTable2.setModel(minmodel.getMinUsageModel());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jCheckBox1InputMethodTextChanged
        // TODO add your handling code here:  
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();
        if (tabno == 2) {
            try {
                if (jCheckBox1.isSelected() == true) {
                    minmodel = MinUsageModel.loadInstance(m_App, 2);
                    jTable2.setModel(minmodel.getMinUsageModel());
                } else if (jCheckBox1.isSelected() == false) {
                    minmodel = MinUsageModel.loadInstance(m_App, 1);
                    jTable2.setModel(minmodel.getMinUsageModel());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jCheckBox1InputMethodTextChanged

    private void membertypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_membertypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_membertypeActionPerformed

    public void newMinUsageLogic() {
        try {

            Minusage usage = (Minusage) jComboBox3.getSelectedItem();
            FacilityLogic flogic = new FacilityLogic();
            Date edate = usage.getLastbilleddate();

            String id = usage.getPeriod();
            Periodicity p1;
            p1 = dfac.getPerioicitybyid(id);
            Date sdate = flogic.calculateStartDate1(p1, edate);
            edate = flogic.calculateEndDate2(sdate, p1);
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String dateOnly = "";
            String enddate = df.format(usage.getLastbilleddate());
            Map lastBillPeriodsMap = new HashMap();


           if (edate.compareTo(dnow) < 0) {
                MinUsageLogic mulogic = new MinUsageLogic();
                String uid = usage.getid();
                double limit = usage.getAmount();
                double charge = usage.getCharge();
                String mutype = usage.getMinusagetype();
                double camt, camtTotal, limitTotal, usageTotal;
                String usageDisplay;
                String limitDisplay;
                String chargeDisplay;
                List<BillPeriods> bperiods;

                 Date lbilledDate = usage.getLastbilleddate();
                // List<BillPeriods> bperiods = mulogic.findBillPeriods(p1, lbilledDate);





                List<LastBillDate> lbillDates = dfac.getLastBillDates(usage.getid());
                //System.out.println(usage.getid());
                for (LastBillDate lbillDate : lbillDates) {
                    System.out.println(lbillDate.toString());
                    bperiods = mulogic.findBillPeriods(p1, lbillDate.getDate());
                    dateOnly = df.format(lbillDate.getDate());
                    //lastBillPeriodsMap.put(lbillDate.getDate(), bperiods);
                    //chargableCustomers.put(lbillDate.getDate(), new ArrayList<MinUsageCustomer>());
                    lastBillPeriodsMap.put(dateOnly, bperiods);
                    chargableCustomers.put(dateOnly, new ArrayList<MinUsageCustomer>());
                }
                List<MinUsageCustomer> muCustomers = dfac.getMinusageCustomers(usage.getid());
                List<MinUsageCustomer> temp = new ArrayList<MinUsageCustomer>();
                int c = 0;
                
                for (MinUsageCustomer cust : muCustomers) {
                    c=c+1;

                    System.out.println(c);
                    System.out.println(cust.getName()+"-----"+cust.getSearchkey());

                    String cid = cust.getCid();
                   String custLBillDate = df.format(cust.getLastBillDate());                  
                    
                    bperiods = (ArrayList<BillPeriods>) lastBillPeriodsMap.get(custLBillDate);
                    List<BillPeriods> tempBp = new ArrayList<BillPeriods>();
                    Date ed = cust.getEnddate();
                    String newMinUsageRef = cust.getNewMinUsageRef();

                    if (ed != null) {
                        if (newMinUsageRef != null) {
                            for (BillPeriods bp2 : bperiods) {
                                if (ed.after(bp2.getStartDate()) && ed.after(bp2.getEndDate())) {
                                    tempBp.add(bp2);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            for (BillPeriods bp2 : bperiods) {
                                if (bp2.getStartDate().before(ed)) {
                                    tempBp.add(bp2);
                                } else {
                                    break;
                                }
                            }
                        }
                        bperiods = tempBp;
                        cust.setDeactivate(true);
                    }
                    camtTotal = 0.0;
                    limitTotal = 0.0;
                    usageTotal = 0.0;
                    usageDisplay = "";
                    limitDisplay = "";
                    chargeDisplay = "";

                    for (BillPeriods bp : bperiods) {

                        camt = 0.0;
                        
                        double custUsage = dfac.getCustomerUsage(cid, bp.getStartDate(), bp.getEndDate(),cust.getFacilities());

                        if (mutype.equals("1")) {
                            if (custUsage >= limit) {
                                camt = 0.0;
                            } else if (custUsage < limit) {
                                camt = charge;
                            }
                        } else if (mutype.equals("2")) {
                            if (custUsage >= limit) {
                                camt = 0.0;
                            } else if (custUsage < limit) {
                                double a2 = limit - custUsage;
                                if (a2 > charge) {
                                    camt = charge;
                                } else if (a2 < charge) {
                                    camt = a2;
                                }
                            }

                        } else if (mutype.equals("3")) {
                            if (custUsage >= limit) {
                                camt = 0.0;
                            } else if (custUsage < limit) {
                                double a13 = limit / 3;
                                double a23 = a13 * 2;
                                if (custUsage < a13) {
                                    camt = charge;
                                } else if (custUsage >= a13 && custUsage < a23) {
                                    camt = (charge / 3) * 2;
                                } else if (custUsage >= a23 && custUsage < limit) {
                                    camt = charge / 3;
                                }
                            }

                        }
                        camtTotal += camt;
                        limitTotal += limit;
                        usageTotal += custUsage;

                        if (!"".equals(usageDisplay)) {
                            usageDisplay += " / ";
                        }
                        usageDisplay += custUsage;

                        if (!"".equals(limitDisplay)) {
                            limitDisplay += " / ";
                        }
                        limitDisplay += limit;

                        if (!"".equals(chargeDisplay)) {
                            chargeDisplay += " / ";
                        }
                        chargeDisplay += camt;

                    }

                    cust.setCamtTotal(camtTotal);
                    cust.setLimitTotal(limitTotal);
                    cust.setUsageDisplay(usageDisplay);
                    cust.setLimitDisplay(limitDisplay);
                    cust.setChargeDisplay(chargeDisplay);
                    cust.setUsageTotal(usageTotal);
                    
                  
                   if(bperiods.size()>=1){
                    cust.setBillDate(bperiods.get(bperiods.size() - 1).getEndDate());
                   }
                   else{
                        System.out.print("for this member"+cust.getSearchkey());
                        cust.setBillDate(cust.getLastBillDate());
                        //cust.setBillDate(bperiods.get(bperiods.size() - 1).getEndDate());
                   }
                       
                    
                    if (camtTotal > 0) {
                        cust.setNarration("Minimum usage charge for period : " + bperiods.get(0).getStartDate().toString() + "To" + bperiods.get(bperiods.size() - 1).getEndDate().toString() + ":" + cust.getSearchkey());

                        //System.out.println(c);
                        // c++;
                        temp = (List<MinUsageCustomer>) chargableCustomers.get(custLBillDate);
                        temp.add(cust);
                        chargableCustomers.put(custLBillDate, temp);

                    }
                    custAll.add(cust);

                }
                bperiods = (ArrayList<BillPeriods>) lastBillPeriodsMap.get(enddate);
                billingDate = bperiods.get(bperiods.size() - 1).getEndDate();
                jTextField4.setText(Formats.TIMESTAMP.formatValue(billingDate));
                jTextField4.setVisible(true);
                jLabel17.setVisible(true);
                mmodel1 = MinUsageDataModel.loadData(m_App, custAll);
                showDialog(mmodel1, m_App);
            } else {
                JOptionPane.showMessageDialog(this, "Already billed", null, JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        manageButtons(true);

    }
//not in use
    public void newBillLogic() {
        if (billed == false) {
            if (chargableCustomers.size() > 0 && JOptionPane.showConfirmDialog(this, "Do u want to bill ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                manageButtons(false);
                try {
                    Transaction t = new Transaction(m_App.getSession()) {

                        @Override
                        protected Object transact() throws BasicException {

                            String servicetaxacc = null;
                            Object stacc = new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find("Service Tax Account");
                            if (stacc != null) {
                                servicetaxacc = stacc.toString();
                            }

                            if (servicetaxacc == null) {
                                JOptionPane.showMessageDialog(null, "Please Create a service tax account", null, JOptionPane.OK_OPTION);
                            } else {
                                Minusage mintemp = new Minusage();                                
                                mintemp = (Minusage) jComboBox3.getSelectedItem();
                                name = mintemp.getName();
                                DebtTypeTableModel.DebtTypeline dueperiod = dfac.getDebtTypebyid(mintemp.getCreditperiod());
                                TaxCategoryInfo tinfo = (TaxCategoryInfo) m_dlSales.getTaxCategoryByid(mintemp.getServiceTax());
                                TaxInfo taxinfo = taxeslogic.getTaxInfo(tinfo);
                                 TaxCategoryInfo tinfo2 = (TaxCategoryInfo) m_dlSales.getTaxCategoryByid(mintemp.getServiceTax());
                                TaxInfo taxinfo2 = taxeslogic.getTaxInfo(tinfo2);
                                TaxCategoryInfo tinfo3 = (TaxCategoryInfo) m_dlSales.getTaxCategoryByid(mintemp.getServiceTax());
                                TaxInfo taxinfo3 = taxeslogic.getTaxInfo(tinfo3);
                                totalsum = 0.0;
                                for (Object key : chargableCustomers.keySet()) {

                                    List<MinUsageCustomer> mucustomers = (ArrayList<MinUsageCustomer>) chargableCustomers.get(key);
                                    if (mucustomers.size() > 0) {

                                        
                                        String tid = UUID.randomUUID().toString();
                                        //praveen:added to set amount as zero
                                        setTotalsum(0.0);
                                        setTaxtotal(0.0);
                                        //end
                                        String billno = dfac.getnewbillno1(mintemp.getid());
                                        if (!billno.equals("")) {
                                            if (!"".equals(showBillNos)) {
                                                showBillNos += " / ";
                                            }
                                            showBillNos += billno;
                                            jTextField7.setText(showBillNos);
                                            jTextField7.setVisible(true);
                                            jLabel23.setVisible(true);

                                            billnum = billno;

                                            for (MinUsageCustomer cust : mucustomers) {
                                                boolean selected = (Boolean) cust.getBillit();
                                                String amt = cust.getCamtTotal().toString();
                                                if (selected == true && !amt.equals("0.0")) {                                                    
                                                    createbill(cust, billno, mintemp, tid, dueperiod, taxinfo ,taxinfo2,taxinfo3);
                                                //System.out.println(count++);
                                                }
                                            }

                                            dfac.updatebillno1(mintemp.getid());


                                            //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE
                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", mintemp.getName(), billno, getTotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), mintemp.getName() + " on " + Formats.DATE.formatValue(dnow), mintemp.getAcchead(), 0.0, dnow, true};
                                            dfac.insertintoaccjoutnal1(value1);

                                            if (getTaxtotal() > 0) {
                                                Double sum1 = dfac.roundTwoDecimals(getTaxtotal());
                                                Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", mintemp.getName(), billno , sum1 , dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax for bill number " + billno, servicetaxacc, 0.0, dnow, true};
                                                dfac.insertintoaccjoutnal1(value2);
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Define a bill sequence", "Create bill", JOptionPane.OK_OPTION);
                                        }
                                    }
                                }
                                for (MinUsageCustomer mucust : custAll) {
                                    dfac.updateLastBilledDateOfMember(mucust.getBillDate(), mucust.getCid(), mintemp.getid());
                                }
                                dfac.updateLastBilledDate(billingDate, mintemp.getid());

                                jButton13.setEnabled(true);
                                manageButtons(true);
                            // int count = 0;

                            }

                            return null;
                        }
                    };
                    t.execute();

                } catch (BasicException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Already billed. Please generate if u want to bill again");
        }
    }
         private JRField[] getFields() throws JRException, UnsupportedOperationException {
        JRField[] fields = new JRField[4];
        fields[0] = (JRField) new JRBasicField("Mem No", "memno", java.lang.String.class, "java.lang.String");
        fields[1] = (JRField) new JRBasicField("Mem Name", "mname", java.lang.String.class, "java.lang.String");
        fields[2] = (JRField) new JRBasicField("Amount", "amount", java.lang.Double.class, "java.lang.Double");
        fields[3] = (JRField) new JRBasicField("Charge", "Charge", java.lang.Double.class, "java.lang.Double");
        fields[2] = (JRField) new JRBasicField("showUsage", "showUsage", java.lang.String.class, "java.lang.String");
        fields[2] = (JRField) new JRBasicField("showCharge", "showCharge", java.lang.String.class, "java.lang.String");
        
        return fields;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField billseq;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField effdate;
    private javax.swing.JComboBox facilities;
    private javax.swing.JList includedfac;
    private javax.swing.JList includedmemtype;
    private javax.swing.JTextField iuamt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField maxdebt;
    private javax.swing.JComboBox membertype;
    private javax.swing.JTextField minusagename;
    private javax.swing.JTextField ouamt;
    private javax.swing.JTextField sdate;
    private javax.swing.JTextField smsform;
    private javax.swing.JTextField usagecharge;
    private javax.swing.JComboBox usageperiod;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "MinimumUsage";
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

    private class FacilityTypeListModel extends AbstractListModel {

        private java.util.List facilitiesmodel;

        public FacilityTypeListModel(java.util.List facilitiesmodel) {
            this.facilitiesmodel = facilitiesmodel;
        }

        public int getSize() {
            return facilitiesmodel.size();
        }

        public Object getElementAt(int i) {
            return facilitiesmodel.get(i);
        }

        public void remove(int i) {
            facilitiesmodel.remove(i);
        }
    }

    private class MemTypeListModel extends AbstractListModel {

        private java.util.List memtype;

        public MemTypeListModel(java.util.List memtype) {
            this.memtype = memtype;
        }

        public int getSize() {
            return memtype.size();
        }

        public Object getElementAt(int i) {
            return memtype.get(i);
        }

        public void remove(int i) {
            memtype.remove(i);
        }
    }
    public void reset() throws BasicException{
         minusagename.setText(null);
         ouamt.setText(null);
         smsform.setText(null);
         usagecharge.setText(null);
         iuamt.setText(null);
         billseq.setText(null);
         maxdebt.setText(null);
         List mlist1 = dfac.getFacility();
         facilitiesmodel = new ComboBoxValModel(mlist1);
         facilities.setModel(facilitiesmodel);
         List mlist2 = dfac.getPeriodicity().list();
         periodtypemodel = new ComboBoxValModel(mlist2);
         usageperiod.setModel(periodtypemodel);
         List mlist3 = dfac.getMinUsage();
         minusagetypemodel = new ComboBoxValModel(mlist3);
        jComboBox3.setModel(minusagetypemodel);
        List alist = dfac.getaccounts();
        accmodel = new ComboBoxValModel(alist);
        jComboBox5.setModel(accmodel);
        List mlist4 = dfac.getMemberCategory();
        mlist4.add(0, "ALL");
        MemType = new ComboBoxValModel(mlist4);
        membertype.setModel(MemType);        
        debttypemodel = new ComboBoxValModel(dfac.getDebtType());
        jComboBox6.setModel(debttypemodel);
        List staxlist = m_dlSales.getTaxCategoriesList().list();
        staxlist.add(0, null);
         List staxlist1 = m_dlSales.getTaxCategoriesList().list();
        staxlist1.add(0, null); 
         List staxlist2 = m_dlSales.getTaxCategoriesList().list();
        staxlist2.add(0, null);
        staxmodel = new ComboBoxValModel(staxlist);
        staxmodel2 = new ComboBoxValModel(staxlist);
        staxmodel3 = new ComboBoxValModel(staxlist);
 jComboBox7.setModel(staxmodel);
  jComboBox1.setModel(staxmodel2);
         jComboBox2.setModel(staxmodel3);
        memtypelist = new ArrayList();
        memtypelist1 = new ArrayList();        
        memtype = new ArrayList();
        sdate.setText(Formats.TIMESTAMP.formatValue(new Date()));
        jTextField5.setText(Formats.TIMESTAMP.formatValue(new Date()));
        minmodel = MinUsageModel.loadInstance(m_App, 1);
        jTable2.setModel(minmodel.getMinUsageModel());
        mltmodel2 = new MemTypeListModel(memtype);
        includedmemtype.setModel(mltmodel2);
        //mltmodel1 = new FacilityTypeListModel(memtypelist1);
        //jList2.setModel(mltmodel1);
        mltmodel = new FacilityTypeListModel(memtypelist);
        includedfac.setModel(mltmodel);
        effdate.setText(null); 
        jRadioButton8.setSelected(true);
          jRadioButton9.setSelected(false);
          jRadioButton10.setSelected(true);
          jRadioButton11.setSelected(false);
          jRadioButton3.setSelected(false);
          jRadioButton5.setSelected(false);
            jRadioButton1.setSelected(false);
//           jRadioButton8.setSelected(false);
//          jRadioButton9.setSelected(false);
//          jRadioButton10.setSelected(false);
//          jRadioButton11.setSelected(false);
    
    }

}
