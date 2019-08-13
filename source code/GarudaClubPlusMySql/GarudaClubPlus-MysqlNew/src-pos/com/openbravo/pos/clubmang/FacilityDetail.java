package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.catalog.WideComboBox;
import com.openbravo.pos.clubmang.FacilityApprovalRitesModel.AllFacilityLine;
import com.openbravo.pos.clubmang.FacilityApprovalRitesModel.AllUsersLine;
import com.openbravo.pos.clubmang.FacilityApprovalRitesModel.ApprovalRitesLine;
import com.openbravo.pos.clubmang.FacilityApprovalRitesModel.DeactivtionFacilityLine;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.CustomerInfo1;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.inventory.TaxCategoryInfo1;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.TaxInfo;
//import com.openbravo.pos.ticket.TaxInfo1;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.application.SessionStorage.TabbedPaneState;

/**
 *
 * @author praveen
 */
public class FacilityDetail extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicFacilities dmang;
    // private TableDefinition facilityTable;
    // private int[] idata=new int[15];
    // private int i=0;
    private FacilitytableModel fmodel;
    private ComboBoxValModel periodicitymodel;
    private ComboBoxValModel periodicitymodel1;
    //private ComboBoxValModel periodicitymodel2;
    private ComboBoxValModel debttypemodel;
    private ComboBoxValModel accounttype;
    private ComboBoxValModel renewalacctype;
    private ComboBoxValModel usageacctype;
    private ComboBoxValModel memtype;
    private ComboBoxValModel staxmodel;
    private DataLogicSales m_dlSales;
    private TaxesLogic taxeslogic;
    private List memtypelist;
    private MemTypeListModel mmodel;
    private FacilityApprovalRitesModel frmodel;
    private AllFacilityListModel afmodel;
    private AllUserListModel aumodel;
    private DeactFacilityListModel dfmodel;
    private AppRitesUserListModel appumodel;
    private String fid = null;
    private FacilitytableModel.Facility facline;
     private ComboBoxValModel taxcatmodel2;  
    private ComboBoxValModel taxcatmodel3;  
    private SentenceList taxcatsent2;
    private SentenceList taxcatsent3;
     public List<String> TaxCategoryList = new ArrayList<String>();
      public ComboBoxValModel GuestBoxValModel;
      public ComboBoxValModel GuestBoxValModel1;
      public static double rate1;
       public static double rate2;
         static boolean taxcatflag;
      
    // private ComboBoxValModel periodicitymodel1;

    /**Creates new form FacilityDetail */
    public FacilityDetail() {
        initComponents();
//        jRadioButton1.addActionListener(dirty);
//         jRadioButton2.addActionListener(dirty);
//        
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");


    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public String getTitle() {
        return "Facility";
    }

    public void activate() throws BasicException {
        // jButton4.setVisible(false);
        jLabel3.setVisible(false);
        loadData();
//         taxcatmodel2 = new ComboBoxValModel(taxcatsent2.list());
//        taxcatmodel3 = new ComboBoxValModel(taxcatsent3.list());
//        jComboBox1.setModel(taxcatmodel2);
//       jComboBox2.setModel(taxcatmodel3);
    }

    private List addNull(List list) {
        list.add(0, null);
        return list;
    }

    private void loadData() throws BasicException {
        debttypemodel = new ComboBoxValModel(dmang.getDebtType());
        //  acctype.setModel(accounttype);
        try {
            //List memtypelist=dmang.getMemType();
            /*graceperiod.setVisible(false);
            amountlimit.setVisible(false);
            jButton8.setVisible(false);
            jButton9.setVisible(false);
            jButton10.setVisible(false);
            jButton11.setVisible(false);
            allusers.setVisible(false);
            approvalusers.setVisible(false);
            faclist.setVisible(false);
            deactfaclist.setVisible(false);
            users.setVisible(false);
            approvalrites.setVisible(false);
            deactivatelist.setVisible(false);
            allfacility.setVisible(false);
            grace.setVisible(false);
            maxamount.setVisible(false);*/
            fmodel = FacilitytableModel.loadInstance(m_App, 1);
            jTable1.setModel(fmodel.getTableModel());
            frmodel = new FacilityApprovalRitesModel();
            List<FacilityApprovalRitesModel.AllFacilityLine> flist = frmodel.loadinstanceofallfacility(m_App);
            afmodel = new AllFacilityListModel(flist);
            List<FacilityApprovalRitesModel.AllUsersLine> alist = frmodel.loadinstanceofallusers(m_App);
            aumodel = new AllUserListModel(alist);
            faclist.setModel(afmodel);
            allroles.setModel(aumodel);
            taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
            List tyofmem = dmang.getMemType();
            tyofmem.add(0, "ALL");
            memtype = new ComboBoxValModel(tyofmem);
            typeOfMem.setModel(memtype);
            jTabbedPane1.setSelectedIndex(0);
            // List acc=addNull(dmang.getSalesaccounts());
            List acc = addNull(dmang.getaccounts());
            //   acc.add(0,null);
            accounttype = new ComboBoxValModel(acc);
            jfeeaccount.setModel(accounttype);
            renewalacctype = new ComboBoxValModel(acc);
            renewalaccount.setModel(renewalacctype);
            usageacctype = new ComboBoxValModel(acc);
            usageaccount.setModel(usageacctype);
            List period = addNull(dmang.getPeriodicity().list());
            // period.add(0, null);
            periodicitymodel = new ComboBoxValModel(period);
            periodicitymodel1 = new ComboBoxValModel(period);
            debttype.setModel(debttypemodel);
            List staxlist = m_dlSales.getTaxCategoriesList1().list();
            staxlist.add(0, null);
            staxmodel = new ComboBoxValModel(staxlist);
            servicetax.setModel(staxmodel);
            startfac.setVisible(false);
            confirm.setVisible(false);
             TaxCategoryList = new ArrayList<String>();
             
          
          TaxCategoryList=getTaxCategoryList1(m_App);
          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
          
          jComboBox1.setModel(GuestBoxValModel);
         jComboBox1.setSelectedIndex(-1);
              
          TaxCategoryList=getTaxCategoryList2(m_App);
          GuestBoxValModel1 = new ComboBoxValModel(TaxCategoryList);
         jComboBox2.setModel(GuestBoxValModel1);
         jComboBox2.setSelectedIndex(-1);
         
            // periodicitymodel2=new ComboBoxValModel(period);
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    fname.requestFocus();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        reset();
        set();
        set1();

    }

    private void reset() {
        fname.setText(null);
        hsncode.setText(null);
        billsequence.setText(null);
        smssform.setText(null);
        //  jCheckBox6.setSelected(false);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        cardcontrol.setSelected(false);
        jImageEditor1.setImage(null);
        jamount.setText("0");
        uamount.setText("0");
        ramount.setText("0");
        typeOfMem.setSelectedIndex(0);
        standard.setSelected(true);
        amountlimit.setText("0");
        graceperiod.setText(null);
        optional.setSelected(true);
         servicetax.setSelectedIndex(1);
        jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1);
//        servicetax.setSelectedIndex(10);
//        jComboBox1.setSelectedIndex(9);
//        jComboBox2.setSelectedIndex(9);
        memtypelist = new ArrayList();
        mmodel = new MemTypeListModel(memtypelist);
        jList1.setModel(mmodel);
        fid = null;
//         jRadioButton1.setSelected(true);
//          jRadioButton1.setSelected(true);
           jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(false);
        //dfmodel = new DeactFacilityListModel(memtypelist);
        // appumodel = new AppRitesUserListModel(memtypelist);
        //deactfaclist.setModel(dfmodel);
        //approvalusers.setModel(appumodel);

        //praveen:for every reset of panel der ll be a new uuid for facility
        loadpane();

    }

    private void reset1() {
        fname.setText(null);
        hsncode.setText(null);;
        billsequence.setText(null);
        billsequence.setEditable(false);
        smssform.setText(null);
        mem.setEnabled(false);
        memdep.setEnabled(false);
        jCheckBox1.setSelected(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setSelected(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setSelected(false);
        jCheckBox3.setEnabled(false);
        jCheckBox4.setSelected(false);
        jCheckBox4.setEnabled(false);
        cardcontrol.setSelected(false);
        cardcontrol.setEnabled(false);
        jImageEditor1.setImage(null);
        jImageEditor1.setEnabled(false);
        jamount.setText("0");
        uamount.setText("0");
        ramount.setText("0");
        typeOfMem.setSelectedIndex(0);
        typeOfMem.setEditable(false);
        standard.setSelected(true);
        standard.setEnabled(false);
        amountlimit.setText("0");
        graceperiod.setText(null);
        graceperiod.setEditable(false);
        optional.setSelected(true);
        optional.setEnabled(false);
        servicetax.setSelectedIndex(1);
        servicetax.setEditable(false);
//          servicetax.setEditable(true);
         jComboBox1.setSelectedIndex(-1);
       jComboBox1.setEditable(false);
         jComboBox2.setSelectedIndex(-1);
        jComboBox2.setEditable(false);
        debttype.setEditable(false);
        memtypelist = new ArrayList();
        mmodel = new MemTypeListModel(memtypelist);
        date.setEditable(false);
        jList1.setModel(mmodel);
        jList1.setEnabled(false);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton8.setEnabled(false);
        jButton9.setEnabled(false);
        jButton10.setEnabled(false);
        jButton11.setEnabled(false);
        jButton1.setVisible(false);
        jButton3.setVisible(false);
        allroles.setEnabled(false);
        faclist.setEnabled(false);
        deactfaclist.setEnabled(false);
        approvalusers.setEnabled(false);
        jfeeaccount.setEnabled(false);
        rperiod.setEnabled(false);
        renewalaccount.setEnabled(false);
        uperiod.setEnabled(false);
        usageaccount.setEnabled(false);
        typeOfMem.setEnabled(false);
        debttype.setEnabled(false);
        servicetax.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        savechanges.setVisible(true);
        cancel.setVisible(true);
        jRadioButton2.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton1.setSelected(false);
        jRadioButton3.setSelected(false);


    }

    private void set1() {
        fname.setText(null);
        hsncode.setText(null);
        fname.setEditable(true);
        hsncode.setEditable(true);
        billsequence.setText(null);
        billsequence.setEditable(true);
        smssform.setText(null);
        smssform.setEditable(true);
        mem.setEnabled(true);
        memdep.setEnabled(true);
        jCheckBox1.setSelected(false);
        jCheckBox1.setEnabled(true);
        jCheckBox2.setSelected(false);
        jCheckBox2.setEnabled(true);
        jCheckBox3.setSelected(false);
        jCheckBox3.setEnabled(true);
        jCheckBox4.setSelected(false);
        jCheckBox4.setEnabled(true);
        cardcontrol.setSelected(false);
        cardcontrol.setEnabled(true);
        jImageEditor1.setImage(null);
        jImageEditor1.setEnabled(true);
        jamount.setText("0");
        uamount.setText("0");
        ramount.setText("0");
        //typeOfMem.setSelectedIndex(0);
        typeOfMem.setEditable(true);
        standard.setSelected(true);
        standard.setEnabled(true);
        amountlimit.setText("0");
        graceperiod.setText(null);
        graceperiod.setEditable(true);
        optional.setSelected(true);
        optional.setEnabled(true);
        servicetax.setSelectedIndex(1);
        servicetax.setEditable(false);
        jComboBox1.setSelectedIndex(-1);
        jComboBox1.setEditable(false);
        jComboBox2.setSelectedIndex(-1);
        jComboBox2.setEditable(false);
//           jRadioButton2.setSelected(false);
//              jRadioButton4.setSelected(false);
        debttype.setEditable(true);
        memtypelist = new ArrayList();
        mmodel = new MemTypeListModel(memtypelist);
        date.setEditable(true);
        jList1.setModel(mmodel);
        jList1.setEnabled(true);
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton8.setEnabled(true);
        jButton9.setEnabled(true);
        jButton10.setEnabled(true);
        jButton11.setEnabled(true);
        jButton1.setVisible(true);
        jButton3.setVisible(true);
        allroles.setEnabled(true);
        faclist.setEnabled(true);
        deactfaclist.setEnabled(true);
        approvalusers.setEnabled(true);
        jfeeaccount.setEnabled(true);
        rperiod.setEnabled(true);
        renewalaccount.setEnabled(true);
        uperiod.setEnabled(true);
        usageaccount.setEnabled(true);
        typeOfMem.setEnabled(true);
        debttype.setEnabled(true);
        servicetax.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        
        savechanges.setVisible(false);
        cancel.setVisible(false);


    }

    private void set() {

        rperiod.setModel(periodicitymodel);
        uperiod.setModel(periodicitymodel1);
        //  dueperiod.setModel(periodicitymodel2);
        rperiod.setSelectedIndex(0);
        uperiod.setSelectedIndex(0);
        debttype.setSelectedIndex(-1);
        //  dueperiod.setSelectedIndex(0);
        //   acctype.setSelectedIndex(-1);
        jLabel12.setVisible(false);
        jamount.setVisible(false);
        jfeeaccount.setSelectedIndex(0);
        renewalaccount.setVisible(false);
        renewalaccount.setSelectedIndex(0);
        rperiod.setSelectedItem(null);
        uperiod.setSelectedItem(null);
        jLabel17.setVisible(false);
        usageaccount.setVisible(false);
        usageaccount.setSelectedIndex(0);
        jLabel16.setVisible(false);
        jLabel14.setVisible(false);
        jfeeaccount.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        ramount.setVisible(false);
        rperiod.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        uamount.setVisible(false);
        uperiod.setVisible(false);
        jRadioButton1.setSelected(true);
         jRadioButton3.setSelected(true);
    }

    public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }
    /*   private Date getDueDate(DebtTypeTableModel.DebtTypeline dueperiod,Date d){
    String type=dueperiod.getperiod();
    int num=dueperiod.getNum();
    Date duedate=new Date();
    duedate.setTime(d.getTime());
    Calendar cal=GregorianCalendar.getInstance();
    cal.setTimeInMillis(duedate.getTime());
    if(type.equals("Days"))
    cal.add(Calendar.DATE, num);
    if(type.equals("Months"))
    cal.add(Calendar.MONTH, num);
    if(type.equals("Years"))
    cal.add(Calendar.YEAR, num);
    duedate.setTime(cal.getTimeInMillis());
    return duedate;
    }*/

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
    /*     private int pnum=0;
    private Date calculateEndDate(Date d,Periodicity p,int billabledate){
    /*   Date edate=new Date();
    edate.setTime(d.getTime());
    Calendar cal=Calendar.getInstance();
    cal.setTimeInMillis(edate.getTime());
    if(p.gettype().equals("Days")){
    cal.add(Calendar.DATE, p.getno());
    }else if(p.gettype().equals("Months")){
    cal.add(Calendar.MONTH, p.getno());
    } if(p.gettype().equals("Quaterly")){
    cal.add(Calendar.MONTH, 3);
    } if(p.gettype().equals("Years")){
    cal.add(Calendar.YEAR, p.getno());
    }
    edate.setTime(cal.getTimeInMillis());
    return edate;   ////
    // temp=new Date();
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
    }else if(p.gettype().equals("Quaterly")){
    cal1.add(Calendar.MONTH, 3);
    pnum++;
    }else if(p.gettype().equals("Years")){
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
    cal1.set(Calendar.DATE, -1);
    edate.setTime(cal1.getTimeInMillis());
    return edate;
    }
    private Double calulaterenewalamt(Date lbdate,Date edate,Double ramt){
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
    }*/
    /*   private void validateinput(JCheckBox temp){

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
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
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        fname = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jamount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ramount = new javax.swing.JTextField();
        uamount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jImageEditor1 = new com.openbravo.data.gui.JImageEditor();
        jLabel11 = new javax.swing.JLabel();
        uperiod = new javax.swing.JComboBox();
        rperiod = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        billsequence = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        debttype = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jfeeaccount = new WideComboBox();
        jLabel16 = new javax.swing.JLabel();
        usageaccount = new WideComboBox();
        jLabel17 = new javax.swing.JLabel();
        renewalaccount = new WideComboBox();
        jLabel18 = new javax.swing.JLabel();
        typeOfMem = new WideComboBox();
        jLabel20 = new javax.swing.JLabel();
        standard = new javax.swing.JRadioButton();
        optional = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        servicetax = new WideComboBox();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel22 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        applicableto = new javax.swing.JLabel();
        mem = new javax.swing.JRadioButton();
        memdep = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        smssform = new javax.swing.JTextField();
        graceperiod = new javax.swing.JTextField();
        amountlimit = new javax.swing.JTextField();
        grace = new javax.swing.JLabel();
        maxamount = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        faclist = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        allroles = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        deactfaclist = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        approvalusers = new javax.swing.JList();
        deactivatelist = new javax.swing.JLabel();
        allfacility = new javax.swing.JLabel();
        users = new javax.swing.JLabel();
        approvalrites = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        cardcontrol = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        startfac = new javax.swing.JCheckBox();
        confirm = new javax.swing.JCheckBox();
        savechanges = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        hsncode = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setToolTipText("New");
        setAutoscrolls(true);
        setLayout(null);

        jPanel1.setLayout(null);

        jLabel3.setText("Amount :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 10, 44, 14);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 0, 0);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Joining Fee", "Renewal Fee", "Period", "Usage Fee", "Period", "Entrance Chk", "Created by", "Date", "Bill Sequence"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Edit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Deactivate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Show All");
        jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox5ItemStateChanged(evt);
            }
        });
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox5)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel3);

        jPanel2.setLayout(null);

        jLabel1.setText("Name");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 14, 100, 20);

        jLabel2.setText("Joining Fee");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 70, 79, 20);

        jLabel4.setText("Renewal Fee");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 100, 94, 20);

        jLabel7.setText("Usage Fee");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 130, 94, 14);

        jLabel10.setText("Entrance Control");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(10, 150, 100, 20);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(690, 550, 80, 30);

        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel2.add(fname);
        fname.setBounds(120, 10, 170, 20);

        jCheckBox1.setText("Yes");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jPanel2.add(jCheckBox1);
        jCheckBox1.setBounds(120, 60, 50, 40);

        jCheckBox2.setText("Yes");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });
        jPanel2.add(jCheckBox2);
        jCheckBox2.setBounds(120, 100, 50, 20);

        jCheckBox3.setText("Yes");
        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox3ItemStateChanged(evt);
            }
        });
        jPanel2.add(jCheckBox3);
        jCheckBox3.setBounds(120, 120, 60, 30);

        jCheckBox4.setText("Yes");
        jPanel2.add(jCheckBox4);
        jCheckBox4.setBounds(120, 150, 60, 20);

        jLabel12.setText("Amount");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(180, 70, 60, 20);

        jamount.setText("0");
        jPanel2.add(jamount);
        jamount.setBounds(250, 70, 60, 20);

        jLabel5.setText("Amount :");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(180, 100, 60, 20);

        ramount.setText("0");
        ramount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ramountActionPerformed(evt);
            }
        });
        jPanel2.add(ramount);
        ramount.setBounds(250, 100, 60, 20);

        uamount.setText("0");
        jPanel2.add(uamount);
        uamount.setBounds(250, 130, 60, 20);

        jLabel8.setText("Amount :");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(180, 130, 60, 14);

        jLabel9.setText("Usage Period");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(320, 130, 80, 14);

        jLabel6.setText("Renewal Period");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(320, 100, 90, 20);
        jPanel2.add(jImageEditor1);
        jImageEditor1.setBounds(120, 450, 200, 120);

        jLabel11.setText("Image");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 450, 70, 20);

        jPanel2.add(uperiod);
        uperiod.setBounds(410, 130, 120, 20);

        jPanel2.add(rperiod);
        rperiod.setBounds(410, 100, 120, 20);

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(610, 550, 70, 30);

        jLabel15.setText("Bill Sequence");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(10, 260, 90, 30);

        billsequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billsequenceActionPerformed(evt);
            }
        });
        jPanel2.add(billsequence);
        billsequence.setBounds(120, 270, 80, 20);

        jLabel13.setText("Credit Period");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(10, 240, 90, 20);

        jPanel2.add(debttype);
        debttype.setBounds(120, 240, 120, 20);

        jLabel14.setText(" Account");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(320, 70, 60, 20);

        jPanel2.add(jfeeaccount);
        jfeeaccount.setBounds(410, 70, 200, 20);

        jLabel16.setText("Account");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(540, 130, 60, 14);

        usageaccount.setAutoscrolls(true);
        jPanel2.add(usageaccount);
        usageaccount.setBounds(600, 130, 180, 20);

        jLabel17.setText("Account");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(540, 100, 50, 20);

        jPanel2.add(renewalaccount);
        renewalaccount.setBounds(600, 100, 180, 20);

        jLabel18.setText("Type Of Member");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(310, 214, 100, 20);

        typeOfMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOfMemActionPerformed(evt);
            }
        });
        jPanel2.add(typeOfMem);
        typeOfMem.setBounds(410, 220, 120, 20);

        jLabel20.setText("Type");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(10, 30, 70, 40);

        buttonGroup1.add(standard);
        standard.setText("Standard");
        standard.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                standardStateChanged(evt);
            }
        });
        jPanel2.add(standard);
        standard.setBounds(120, 30, 110, 30);

        buttonGroup1.add(optional);
        optional.setText("Optional");
        optional.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optionalStateChanged(evt);
            }
        });
        jPanel2.add(optional);
        optional.setBounds(230, 30, 120, 30);

        jLabel21.setText("Tax Category 1");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(10, 290, 90, 30);

        servicetax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                servicetaxItemStateChanged(evt);
            }
        });
        servicetax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicetaxActionPerformed(evt);
            }
        });
        jPanel2.add(servicetax);
        servicetax.setBounds(120, 300, 120, 20);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnplus.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(540, 220, 40, 20);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(410, 260, 120, 70);

        jLabel22.setText("List of Types");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(310, 264, 90, 10);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnminus.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6);
        jButton6.setBounds(540, 260, 30, 20);

        jLabel23.setText("Effective date");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(10, 420, 80, 20);
        jPanel2.add(date);
        date.setBounds(120, 420, 140, 20);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(270, 420, 40, 25);

        applicableto.setText("Applicable to    :");
        jPanel2.add(applicableto);
        applicableto.setBounds(350, 30, 100, 30);

        buttonGroup2.add(mem);
        mem.setText("Member");
        jPanel2.add(mem);
        mem.setBounds(460, 30, 90, 30);

        buttonGroup2.add(memdep);
        memdep.setText("Member Dependent");
        jPanel2.add(memdep);
        memdep.setBounds(570, 30, 150, 30);

        jLabel24.setText("SMS Short Form");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(310, 14, 100, 20);
        jPanel2.add(smssform);
        smssform.setBounds(410, 10, 130, 20);

        graceperiod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graceperiodActionPerformed(evt);
            }
        });
        jPanel2.add(graceperiod);
        graceperiod.setBounds(120, 180, 80, 20);
        jPanel2.add(amountlimit);
        amountlimit.setBounds(120, 210, 120, 20);

        grace.setText("Grace");
        jPanel2.add(grace);
        grace.setBounds(10, 170, 70, 20);

        maxamount.setText("Max.Debt.Allowed");
        jPanel2.add(maxamount);
        maxamount.setBounds(10, 210, 110, 20);

        jScrollPane3.setViewportView(faclist);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(610, 400, 160, 50);

        allroles.setSelectionBackground(new java.awt.Color(0, 51, 255));
        jScrollPane4.setViewportView(allroles);

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(610, 480, 160, 50);

        jScrollPane5.setViewportView(deactfaclist);

        jPanel2.add(jScrollPane5);
        jScrollPane5.setBounds(410, 400, 130, 50);

        jScrollPane6.setViewportView(approvalusers);

        jPanel2.add(jScrollPane6);
        jScrollPane6.setBounds(410, 480, 130, 50);

        deactivatelist.setText("DeactivateList");
        jPanel2.add(deactivatelist);
        deactivatelist.setBounds(410, 370, 100, 20);

        allfacility.setText(" Facilities");
        jPanel2.add(allfacility);
        allfacility.setBounds(610, 370, 80, 20);

        users.setText("Roles");
        jPanel2.add(users);
        users.setBounds(610, 460, 90, 20);

        approvalrites.setText("Approval Rites");
        jPanel2.add(approvalrites);
        approvalrites.setBounds(410, 460, 130, 20);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8);
        jButton8.setBounds(550, 430, 40, 20);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9);
        jButton9.setBounds(550, 400, 40, 20);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);
        jButton10.setBounds(550, 480, 40, 20);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11);
        jButton11.setBounds(550, 510, 40, 20);

        cardcontrol.setText("jCheckBox6");
        cardcontrol.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cardcontrolStateChanged(evt);
            }
        });
        cardcontrol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cardcontrolItemStateChanged(evt);
            }
        });
        cardcontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardcontrolActionPerformed(evt);
            }
        });
        jPanel2.add(cardcontrol);
        cardcontrol.setBounds(260, 150, 20, 30);

        jLabel25.setText("Card Control");
        jPanel2.add(jLabel25);
        jLabel25.setBounds(180, 150, 90, 20);

        jLabel26.setText("Yes");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(280, 150, 30, 30);

        startfac.setText("Iniat.Billing");
        jPanel2.add(startfac);
        startfac.setBounds(320, 150, 90, 30);

        confirm.setText("Credit Conf.");
        jPanel2.add(confirm);
        confirm.setBounds(410, 150, 110, 30);

        savechanges.setText("Save Changes");
        savechanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savechangesActionPerformed(evt);
            }
        });
        jPanel2.add(savechanges);
        savechanges.setBounds(480, 550, 120, 30);

        cancel.setText("cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel2.add(cancel);
        cancel.setBounds(370, 550, 100, 30);

        jLabel19.setText("HSN/SAC Code");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(570, 14, 90, 20);

        hsncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsncodeActionPerformed(evt);
            }
        });
        jPanel2.add(hsncode);
        hsncode.setBounds(660, 10, 110, 20);

        jLabel27.setText("Tax Category 2");
        jPanel2.add(jLabel27);
        jLabel27.setBounds(10, 320, 90, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(120, 330, 120, 20);

        jLabel28.setText("Tax Category 3");
        jPanel2.add(jLabel28);
        jLabel28.setBounds(10, 370, 90, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(120, 370, 120, 20);

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Basic");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton1);
        jRadioButton1.setBounds(120, 350, 100, 20);

        jRadioButton2.setText("Cascade");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton2);
        jRadioButton2.setBounds(230, 350, 110, 20);

        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Basic");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton3);
        jRadioButton3.setBounds(120, 390, 90, 20);

        jRadioButton4.setText("Cascade");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton4);
        jRadioButton4.setBounds(230, 390, 110, 23);

        jTabbedPane1.addTab("Create New", jPanel2);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 790, 620);
        jTabbedPane1.getAccessibleContext().setAccessibleName("tab");
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        if (jCheckBox1.isSelected() == true) {
            jLabel12.setVisible(true);
            jamount.setVisible(true);
            jLabel14.setVisible(true);
            jfeeaccount.setVisible(true);
            jfeeaccount.setSelectedIndex(0);
            jamount.setText("0");
        } else {
            jLabel12.setVisible(false);
            jamount.setVisible(false);
            jLabel14.setVisible(false);
            jfeeaccount.setVisible(false);
            jfeeaccount.setSelectedIndex(0);
            jamount.setText("0");
        }

    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        // TODO add your handling code here:
        if (jCheckBox2.isSelected() == true) {
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            ramount.setVisible(true);
            rperiod.setVisible(true);
            renewalaccount.setVisible(true);
            renewalaccount.setSelectedIndex(0);
            rperiod.setSelectedItem(null);
            jLabel17.setVisible(true);
            ramount.setText("0");
        } else {
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            ramount.setVisible(false);
            rperiod.setVisible(false);
            renewalaccount.setVisible(false);
            renewalaccount.setSelectedIndex(0);
            rperiod.setSelectedItem(null);
            jLabel17.setVisible(false);
            ramount.setText("0");
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
        // TODO add your handling code here:
        if (jCheckBox3.isSelected() == true) {
            jLabel8.setVisible(true);
            jLabel9.setVisible(true);
            uamount.setVisible(true);
            uperiod.setVisible(true);
            uperiod.setSelectedItem(null);
            usageaccount.setVisible(true);
            usageaccount.setSelectedIndex(0);
            jLabel16.setVisible(true);
            uamount.setText("0");
        } else {
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            uamount.setVisible(false);
            uperiod.setVisible(false);
            usageaccount.setVisible(false);
            uperiod.setSelectedItem(null);
            usageaccount.setSelectedIndex(0);
            jLabel16.setVisible(false);
            uamount.setText("0");
        }
    }//GEN-LAST:event_jCheckBox3ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //pnum=0;
        try {
            Transaction t;
            t = new Transaction(m_App.getSession()) {
                
                public Object transact() throws BasicException {
                    Date d = new Date();
                    Date effectivedate = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(d.getTime());
                    cal.set(Calendar.HOUR_OF_DAY, 00);
                    cal.set(Calendar.MINUTE, 00);
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    d.setTime(cal.getTimeInMillis());
                    int gracetime = 0;
                    Double maxdebitamount = null;
                    Boolean iniatbilling = false;
                    Boolean credtconfi = false;

                    if (fname.getText().length() > 0 && debttype.getSelectedIndex() != -1 && amountlimit.getText().length() > 0 && billsequence.getText().length() > 0 && smssform.getText().length() > 0) {
                        int count = Integer.valueOf(new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM FACILITY WHERE NAME=? AND ACTIVE=TRUE", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(fname.getText()).toString());
                        if (count == 0) {
                            Periodicity p = (Periodicity) rperiod.getSelectedItem();
                            String rtemp = null;
                            if (p != null) {
                                rtemp = p.getid();
                            }
                            Periodicity p1 = (Periodicity) uperiod.getSelectedItem();
                            String utemp = null;
                            if (p1 != null) {
                                utemp = p1.getid();
                            }
                            String type;
                            int appto = 0;
                            boolean flag = true;
                            if (standard.isSelected() == true) {
                                type = "Standard";
                                d = (Date) Formats.TIMESTAMP.parseValue(date.getText());
                                if (memtypelist.size() <= 0) {
                                    flag = false;
                                }
                            } else {
                                type = "Optional";
                                if (memdep.isSelected()) {
                                    appto = 1;
                                }
                            }
                            DebtTypeTableModel.DebtTypeline debt = (DebtTypeTableModel.DebtTypeline) debttype.getSelectedItem();
                            String jaccid = null, raccid = null, uaccid = null;
                            if (jCheckBox1.isSelected() == true && flag == true) {
                                if (jfeeaccount.getSelectedIndex() != -1) {
                                    AccountMaster amaster = (AccountMaster) jfeeaccount.getSelectedItem();
                                    jaccid = amaster.getid();
                                    flag = true;
                                } else {
                                    flag = false;
                                }
                            }

                            if (jCheckBox2.isSelected() == true && flag == true) {
                                if (renewalaccount.getSelectedIndex() != -1 && rperiod.getSelectedIndex() > 0) {
                                    AccountMaster renewalacc = (AccountMaster) renewalaccount.getSelectedItem();
                                    raccid = renewalacc.getid();
                                    flag = true;
                                } else {
                                    flag = false;
                                }
                            }
                            if (jCheckBox3.isSelected() == true && flag == true) {
                                if (usageaccount.getSelectedIndex() != -1 && uperiod.getSelectedIndex() > 0) {
                                    AccountMaster usageacc = (AccountMaster) usageaccount.getSelectedItem();
                                    uaccid = usageacc.getid();
                                    flag = true;
                                } else {
                                    flag = false;
                                }
                            }

                            //praveen:added graceperiod and amount and cardaccess

                            if (FacilityDetail.this.startfac.isSelected() == true && flag == true) {
                                iniatbilling = true;
                            }
                            if (FacilityDetail.this.confirm.isSelected() == true && flag == true) {
                                credtconfi = true;
                            }
                            if (graceperiod.getText().length() >= 1) {
                                try {
                                    gracetime = Integer.parseInt(graceperiod.getText().toString());
                                    flag = true;
                                } catch (Exception e) {
                                    flag = false;
                                    JOptionPane.showMessageDialog(null, "enter grace in numbers only", null, JOptionPane.OK_OPTION);
                                }
                            }

                            if (amountlimit.getText().length() >= 1) {
                                try {
                                    maxdebitamount = Double.parseDouble(amountlimit.getText().toString());
                                    flag = true;

                                } catch (Exception e) {
                                    flag = false;
                                    JOptionPane.showMessageDialog(null, "enter correctamount", null, JOptionPane.OK_OPTION);
                                }
                            }
                            if (FacilityDetail.this.startfac.isSelected() == true && flag == true) {
                                iniatbilling = true;
                            }
                            if (FacilityDetail.this.confirm.isSelected() == true && flag == true) {
                                credtconfi = true;
                            }
                            if (flag == true) {
                                String mtype = null;
                                MemTypeListModel mtmodel = (MemTypeListModel) jList1.getModel();
                                if (mtmodel.getSize() > 0) {
                                    for (int j = 0; j < mtmodel.getSize(); j++) {
                                        if (mtmodel.getElementAt(j).toString().equals("ALL")) {
                                            mtype = "ALL";
                                            break;
                                        } else {
                                            MemTypeTableModel.MemTypeline mem = (MemTypeTableModel.MemTypeline) mtmodel.getElementAt(j);
                                            if (mtype == null) {
                                                mtype = mem.getid();
                                            } else {
                                                mtype += "#" + mem.getid();
                                            }
                                        }
                                    }

                                }
                                //String val = maxdebt.getText();
                                //String fid = UUID.randomUUID().toString();
                                String staxid = null;
                                if (servicetax.getSelectedItem() != null) {
                                                    TaxCategoryInfo staxcat = (TaxCategoryInfo) servicetax.getSelectedItem();
                                    staxid = staxcat.getID();
                                }
//                                String staxid1 = null;
//                                  if (jComboBox1.getSelectedItem() != null) {
//                                    TaxCategoryInfo1 staxcat1 = (TaxCategoryInfo1) jComboBox1.getSelectedItem();
//                                    staxid1 = staxcat1.getID();
//                                }
String TaxCatName = null;
String TaxCatID = null;
if(jComboBox1.getSelectedIndex()!=-1){
    TaxCatName=jComboBox1.getSelectedItem().toString();
    TaxCatID=getTaxCatIdByName1(m_App, TaxCatName);
}

String TaxCatName1 = null;
String TaxCatID1 = null;
if(jComboBox2.getSelectedIndex()!=-1){
    TaxCatName1=jComboBox2.getSelectedItem().toString();
    TaxCatID1=getTaxCatIdByName2(m_App, TaxCatName1);
}

if (optional.isSelected() == true) {
    mtype = "ALL";
}
if(jRadioButton2.isSelected()&&jRadioButton4.isSelected()){
//Object[] value = new Object[]{fid, true, fname.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, Double.valueOf(val), type, staxid, appto, smssform.getText(), gracetime, maxdebitamount};
Object[] value = new Object[]{fid, true, fname.getText(),hsncode.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, 0.0, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi, TaxCatID, TaxCatID1,true,true,false,false };
//Object[] value = new Object[]{fid, true, fname.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi};

//new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE,SERVICETAX,APPTO,SMSFORM,GRACEPERIOD,AMOUNTLIMIT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.INT, Datas.DOUBLE})).exec(value);
new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,HSN_CODE,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING,Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);
//new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT,Datas.INT,Datas.DOUBLE, Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN})).exec(value);


}else if(jRadioButton2.isSelected()&&jRadioButton3.isSelected()){
    Object[] value = new Object[]{fid, true, fname.getText(),hsncode.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, 0.0, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi, TaxCatID, TaxCatID1,true,false,false,true };
//Object[] value = new Object[]{fid, true, fname.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi};

//new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE,SERVICETAX,APPTO,SMSFORM,GRACEPERIOD,AMOUNTLIMIT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.INT, Datas.DOUBLE})).exec(value);
new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,HSN_CODE,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING,Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.BOOLEAN, Datas.BOOLEAN})).exec(value);

}else if(jRadioButton1.isSelected()&&jRadioButton4.isSelected()){
    Object[] value = new Object[]{fid, true, fname.getText(),hsncode.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, 0.0, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi, TaxCatID, TaxCatID1,false,true,true,false };
//Object[] value = new Object[]{fid, true, fname.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi};

//new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE,SERVICETAX,APPTO,SMSFORM,GRACEPERIOD,AMOUNTLIMIT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.INT, Datas.DOUBLE})).exec(value);
new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,HSN_CODE,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING,Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.BOOLEAN, Datas.BOOLEAN})).exec(value);

}else{
    Object[] value = new Object[]{fid, true, fname.getText(),hsncode.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, 0.0, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi, TaxCatID, TaxCatID1, false, false, true, true };
//Object[] value = new Object[]{fid, true, fname.getText(), Double.valueOf(jamount.getText()), Double.valueOf(ramount.getText()), rtemp, Double.valueOf(uamount.getText()), utemp, jCheckBox4.isSelected(), m_App.getAppUserView().getUser().getName(), new Date(), jImageEditor1.getImage(), billsequence.getText(), 0, debt.getid(), jaccid, raccid, uaccid, mtype, type, staxid, appto, gracetime, maxdebitamount, smssform.getText(), iniatbilling, credtconfi};

//new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE,SERVICETAX,APPTO,SMSFORM,GRACEPERIOD,AMOUNTLIMIT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.INT, Datas.DOUBLE})).exec(value);
new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID, ACTIVE, NAME,HSN_CODE,  JOINAMT,  RENEWALAMT, RPERIODICITY,  USAGEAMT, UPERIODICITY, ENTRANCECONTROL, CREATEDBY, CRDATE,IMAGE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING,Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.IMAGE, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.BOOLEAN, Datas.BOOLEAN})).exec(value);

}
if (maxdebitamount > 0) {
    new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITYDEACTIVATIONMASTER(PFID,DFACID) VALUES (?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fid, fid});
}
if (standard.isSelected() == true) {
    List<CustomerInfo1> memlist = new ArrayList<CustomerInfo1>();
    if (mtype.equals("ALL")) {
        memlist = new StaticSentence(m_App.getSession(), "SELECT ID,SEARCHKEY,TAXID,NAME,MEMTYPE FROM CUSTOMERS WHERE VISIBLE=TRUE", null, new SerializerReadClass(CustomerInfo1.class)).list();
    } else {
        String[] mtypearr = mtype.split("#");
        if (mtypearr.length > 0) {
            for (int j = 0; j < mtypearr.length; j++) {
                List<CustomerInfo1> list = new StaticSentence(m_App.getSession(), "SELECT ID,SEARCHKEY,TAXID,NAME,MEMTYPE FROM CUSTOMERS WHERE MEMTYPE= ? AND VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(CustomerInfo1.class)).list(mtypearr[j]);
                memlist.addAll(list);
            }
        }
    }
    FacilityLogic flogic = new FacilityLogic(dmang);
    Date duedate = new Date();
    Date duedate1 = new Date();
    duedate1.setTime(flogic.getDueDate(debt, d).getTime());
    Double jamt = Double.parseDouble(jamount.getText());
    Double ramt = Double.parseDouble(ramount.getText());
    Double taxrate = 0.0;
    String name = null;
    // StringBuffer billnums=new StringBuffer();
    if (servicetax.getSelectedItem() != null) {
        TaxCategoryInfo tinfo = (TaxCategoryInfo) servicetax.getSelectedItem();
        name=tinfo.getName();
        TaxInfo tax = taxeslogic.getTaxInfo(tinfo);
        taxrate = tax.getRate();
        
    }
    Double taxrate1 = 0.0;
    // StringBuffer billnums=new StringBuffer();
    if (jComboBox1.getSelectedItem() != null) {
        TaxCatName=jComboBox1.getSelectedItem().toString();
//                    TaxCatID=getTaxCatIdByName1(m_App, TaxCatName);
taxrate1=getTaxRateByTaxCatID(TaxCatID);
//                                           TaxCategoryInfo1 tinfo = (TaxCategoryInfo1) jComboBox1.getSelectedItem();
//                                        TaxInfo1 tax = taxeslogic.getTaxInfo1(tinfo);
//                                        taxrate = tax.getRate1();
//                                        TaxCategoryInfo1 tinfo = (TaxCategoryInfo1) jComboBox1.getSelectedItem();
//                                        TaxInfo1 tax = taxeslogic.getTaxInfo1(tinfo);
//                                        taxrate1 = tax.getRate1();

    }
    Double taxrate2 = 0.0;
    // StringBuffer billnums=new StringBuffer();
    if (jComboBox2.getSelectedItem() != null) {
        TaxCatName1=jComboBox2.getSelectedItem().toString();
//                    TaxCatID1=getTaxCatIdByName1(m_App, TaxCatName);
taxrate2=getTaxRateByTaxCatID1(TaxCatID1);
//                                        TaxCategoryInfo tinfo = (TaxCategoryInfo) jComboBox2.getSelectedItem();
//                                        TaxInfo tax = taxeslogic.getTaxInfo(tinfo);
//                                        taxrate2 = tax.getRate();

    }
    Date edate = new Date();
    Date lbdate = new Date();
    lbdate.setTime(d.getTime());
    Calendar cal1 = Calendar.getInstance();
    cal1.setTimeInMillis(lbdate.getTime());
    int billabledate = cal1.get(Calendar.DATE);
    if (p.getqbtype() == true) {
        if (p.getdoj() == false) {
            cal1.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal1).getTimeInMillis());
        }
    }
    lbdate.setTime(cal1.getTimeInMillis());
    //temp=new Date();
    //temp.setTime(lbdate.getTime());
    billabledate = cal1.get(Calendar.DATE);
    flogic.setTemp(lbdate);
    edate.setTime(flogic.calculateEndDate(lbdate, p, billabledate, 1, new Date()).getTime());
    if (p.getqbtype() == false) {
        duedate.setTime(flogic.getDueDate(debt, new Date()).getTime());
    } else {
        duedate.setTime(flogic.getDueDate(debt, flogic.getTemp()).getTime());
    }
    if (p.getaccurate() == true) {
        ramt = flogic.calulaterenewalamt(lbdate, edate, ramt);
    } else {
        if (flogic.getPnum() > 0) {
            ramt = flogic.getPnum() * ramt;
        }
    }
    String billno = dmang.getnewbillno(fid);
    dmang.updatebillno(fid);
    //Service Tax Account
    String servicetaxacc = null;
    Object stacc = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(name);
    if (stacc != null) {
        servicetaxacc = stacc.toString();
    }
    String servicetaxacc1 = null;
    if(TaxCatName!=null){
        Object stacc1 = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(TaxCatName);
    if (stacc1 != null) {
        servicetaxacc1 = stacc1.toString();
    }
    }
    
    String servicetaxacc2 = null;
    if(TaxCatName1!=null){
        Object stacc2 = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(TaxCatName1);
    if (stacc2 != null) {
        servicetaxacc2 = stacc2.toString();
    }
    }
    
    for (int j = 0; j < memlist.size(); j++) {
        String cid = memlist.get(j).getId();
        int count1 = Integer.valueOf(new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMFACILITYUSAGE WHERE MEMNO=? AND FACILITYTYPE=? AND ACTIVE=TRUE AND USERID IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{cid, fid}).toString());
        if (count1 == 0) {
            int flag1 = 0;
            String caccount = null;
            try {
                caccount = dmang.getCustomerAccountByID(cid);
            } catch (Exception e1) {
                flag1 = 1;
                JOptionPane.showMessageDialog(null, "Member Account not present " + memlist.get(j).getName() + " .Please create one", null, JOptionPane.OK_OPTION);
            }
            if (flag1 == 0) {
                double taxamt1=0.0;
                double taxamt2=0.0;
                double taxamt3=0.0;
                double taxamt=0.0;
                double totalamt = 0.0;
                if (jCheckBox1.isSelected() == true) {
                   
                    if (jamt > 0) {
                        if(taxrate!=null){
                            taxamt1 = Math.abs(taxrate * ramt);
                        }
                        if(taxrate1!=null){
                            if(jRadioButton1.isSelected()){
                                taxamt2 = Math.abs(taxrate1 * ramt);
                                
                            }
                            else{
//                                ramt=ramt+taxamt1;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
                          taxamt2 = Math.abs(taxrate1 * (ramt+taxamt1));

                            }
//                            taxamt2 = Math.abs(taxrate1 * ramt);
                        }
                        if(taxrate2!=null){
                             if(jRadioButton3.isSelected()){
                                taxamt3 = Math.abs(taxamt2 * ramt);
                                
                            }
                            else{
//                                 ramt=ramt+taxamt1+taxamt2;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
                          taxamt3 = Math.abs(taxrate2 * (ramt+taxamt1+taxamt2));

                            }
//                            taxamt3 = Math.abs(taxrate2 * ramt);
                        }
//                                                        double taxamt = Math.abs(taxrate * ramt);
taxamt = taxamt1+taxamt2 +taxamt3;
// DebtTypeTableModel.DebtTypeline periodtype=(DebtTypeTableModel.DebtTypeline)debttype.getSelectedItem();
//double taxamt = Math.floor(taxrate * jamt);
//                                                        double taxamt = Math.abs(taxrate * jamt);
String tid = UUID.randomUUID().toString();
String facname = fname.getText();
if (taxamt1 > 0) {
//    if (servicetaxacc == null) {
//        JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//    }
//     if (servicetaxacc1 == null) {
//        JOptionPane.showMessageDialog(null, "Service tax account2 is missing", "Error", JOptionPane.OK_OPTION);
//    }
//      if (servicetaxacc2 == null) {
//        JOptionPane.showMessageDialog(null, "Service tax account3 is missing", "Error", JOptionPane.OK_OPTION);
//    }
//    Object[] value4 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Join Fees for " + facname, servicetaxacc, 0.0, d, new Date(), true,  servicetaxacc1, servicetaxacc2};
//    dmang.insertintoaccjoutnal31(value4);

if(servicetaxacc!=null){
     Object[] value4 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt1, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax1 on Join Fees for " + facname, servicetaxacc, 0.0, d, new Date(), true};
    dmang.insertintoaccjoutnal3(value4);
}


}
if(taxamt2>0){
    if(servicetaxacc1!=null){
         Object[] value41 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt2, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax2 on Join Fees for " + facname, servicetaxacc1, 0.0, d, new Date(), true};
    dmang.insertintoaccjoutnal3(value41); 
    }
   
}
if(taxamt3>0){
    if(servicetaxacc2!=null){
        Object[] value42 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt3, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax3 on Join Fees for " + facname, servicetaxacc2, 0.0, d, new Date(), true};
    dmang.insertintoaccjoutnal3(value42);
    }
}
   
    
   
     


double tjamt = dmang.roundTwoDecimals(jamt + taxamt);
//Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, jamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, jaccid, 0.0, d, new Date(), true, servicetaxacc1, servicetaxacc2};
//dmang.insertintoaccjoutnal31(value2);
Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, jamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, jaccid, 0.0, d, new Date(), true};
dmang.insertintoaccjoutnal3(value2);
//Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, cid, d, "D", fid, billno, tjamt, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, caccount, tjamt, new Date(), true, servicetaxacc1, servicetaxacc2};
//dmang.insertintoaccjoutnal01(value3);
Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, cid, d, "D", fid, billno, tjamt, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Join Fees for " + facname, caccount, tjamt, new Date(), true};
dmang.insertintoaccjoutnal0(value3);
totalamt = tjamt;
// dmang.updatebillno(fid);
                    }
                }
                
                // String billnum=null;
                
                if (p.getqbtype() == true) {
                    if (ramt > 0) {
                        //double taxamt = Math.floor(taxrate * ramt);
                        if(taxrate!=null){
                            
                            taxamt1 = Math.abs(taxrate * ramt);
//                               System.out.println("tax1 basic ::::::"+taxamt1);
                        }
                        if(taxrate1!=null){
                            if(jRadioButton1.isSelected()){
                                taxamt2 = Math.abs(taxrate1 * ramt);
//                                System.out.println("tax2 basic ::::::"+taxamt2);
                                
                            }
                            else{
//                                 ramt=ramt+taxamt1;
//                                    System.out.println("ramnt tax2 cascade ::::::"+ramt);
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
//                          taxamt2 = Math.abs(taxrate1 * ramt);
 taxamt2 = Math.abs(taxrate1 * (ramt+taxamt1));
//                             System.out.println("tax3 cascade ::::::"+taxamt2);

                            }
//                                                               taxamt2 = Math.abs(taxrate1 * ramt);
                        }
                        if(taxrate2!=null){
//                            taxamt3 = Math.abs(taxrate2 * ramt);
                       if(jRadioButton3.isSelected()){
                                taxamt3 = Math.abs(taxrate2 * ramt);
                                
                            }
                            else{
//                            ramt=ramt+taxamt2;
//                Double d1 = tax.getRate()*100;
//                Double d2 = tax2.getRate()*100;
//                Double d = (d2/100)*(100+d1);
//                
//                Tax2Rate = d/100;
//                TotalTaxRate = TotalTaxRate+Tax2Rate;
//                          taxamt3 = Math.abs(taxrate2 * ramt);
 taxamt3 = Math.abs(taxrate2 * (ramt+taxamt1+taxamt2));

                            }
                        }
//                                                        double taxamt = Math.abs(taxrate * ramt);
                taxamt = taxamt1+taxamt2 +taxamt3;
String tid = UUID.randomUUID().toString();

String facname = fname.getText();
if (taxamt1 > 0) {
//    if (servicetaxacc == null) {
//        JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//    }
//     if (servicetaxacc1 == null) {
//        JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//    }
//      if (servicetaxacc2 == null) {
//        JOptionPane.showMessageDialog(null, "Service tax account is missing", "Error", JOptionPane.OK_OPTION);
//    }
//    Object[] value4 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Service tax on Renewal Fees for " + facname, servicetaxacc, 0.0, d, new Date(), true, servicetaxacc1, servicetaxacc2};
//    dmang.insertintoaccjoutnal31(value4);
if(servicetaxacc!=null){
    Object[] value4 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt1, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(),name + " "+ "on renewal fees for" +" "+ facname, servicetaxacc, 0.0, d, new Date(), true};
    dmang.insertintoaccjoutnal3(value4);
}
}
if(taxamt2>0){
    if(servicetaxacc1!=null){
     Object[] value41 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt2, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(),TaxCatName + " "+"on renewal fees for" +" "+ facname, servicetaxacc1, 0.0, d, new Date(), true};
    dmang.insertintoaccjoutnal3(value41);
}
}
if(taxamt3>0){
   if(servicetaxacc2!=null){
      Object[] value42 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, taxamt3, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), TaxCatName1 +" "+"on renewal fees for"+" "+ facname, servicetaxacc2, 0.0, d, new Date(), true};
    dmang.insertintoaccjoutnal3(value42); 
  } 
}
  
     
     
    



double tramt = dmang.roundTwoDecimals(ramt + taxamt);

//Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, ramt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Renewal Fees for Period :" + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate), raccid, 0.0, d, new Date(), true, servicetaxacc1, servicetaxacc2};
//dmang.insertintoaccjoutnal31(value2);
Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, d, "C", fid, billno, ramt, d, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Renewal Fees for Period :" + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate), raccid, 0.0, d, new Date(), true};
dmang.insertintoaccjoutnal3(value2);
//Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, cid, d, "D", fid, billno, tramt, duedate, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Renewal Fees for Period :" + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate), caccount, tramt, new Date(), true, servicetaxacc1, servicetaxacc2};
//dmang.insertintoaccjoutnal01(value3);
Object[] value3 = new Object[]{UUID.randomUUID().toString(), tid, cid, d, "D", fid, billno, tramt, duedate, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Renewal Fees for Period :" + Formats.DATE.formatValue(lbdate) + " to " + Formats.DATE.formatValue(edate), caccount, tramt, new Date(), true};
dmang.insertintoaccjoutnal0(value3);
totalamt += tramt;
                    }
                }
                if (p.getqbtype() == false) {
                    Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, cid, fid, d, m_App.getAppUserView().getUser().getName(), d, 0};
                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,STATUS_) VALUES (?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.INT})).exec(value1);
                } else {
//                    Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, cid, fid, d, m_App.getAppUserView().getUser().getName(), new Date(), edate, new Date(), billno, 0};
//                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,LBILLDATE,NBILLDATE,BILLREF,STATUS_) VALUES (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.INT})).exec(value1);
                 Object[] value1 = new Object[]{UUID.randomUUID().toString(), true, cid, fid, d, m_App.getAppUserView().getUser().getName(), new Date(), edate, new Date(), billno, 0};
                    new PreparedSentence(m_App.getSession(), "INSERT INTO MEMFACILITYUSAGE(ID, ACTIVE, MEMNO, FACILITYTYPE, SDATE, CREATEDBY, CRDATE,LBILLDATE,NBILLDATE,BILLREF,STATUS_) VALUES (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.INT})).exec(value1);
               
                }
                dmang.setmemberDebt(cid, fid, totalamt);
            }
        }
    }
    /*    if(jCheckBox1.isSelected()==true && taxamt>0){
    taxamt= memlist.size()*taxamt;
    Object[] value1=new Object[]{UUID.randomUUID().toString(),"transid",d,"C",fid,billnums,taxamt,d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Service tax for bill numbers "+billnums,"",0.0,d};
    dmang.insertintoaccjoutnal1(value1);
    }*/
}
reset();
set();
                            } else {
                                JOptionPane.showMessageDialog(null, "Account might not be selected", null, JOptionPane.OK_OPTION);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Facility with the name " + fname.getText() + " already exist", null, JOptionPane.OK_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill the form completely", "Incomplete Form", JOptionPane.OK_OPTION);
                    }
                    return null;
                }
            };
            t.execute();
            //praveen:after saving a perticular facility wt ever we created then making uuid as null
            fid = null;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting values.Ensure Correct values are inserted", "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //praveen:this is because if we dont save the detail,it ll remains in facilitydeactivationmaster...so delete it
        try {
            new PreparedSentence(m_App.getSession(), "DELETE FROM FACILITYAPPROVALRITES WHERE PFID=?", SerializerWriteString.INSTANCE).exec(fid);
            new PreparedSentence(m_App.getSession(), "DELETE FROM FACILITYDEACTIVATIONMASTER WHERE PFID=?", SerializerWriteString.INSTANCE).exec(fid);
            List<FacilityApprovalRitesModel.DeactivtionFacilityLine> dflist1 = frmodel.loadinstanceofdeactacility(m_App, fid);
            dfmodel = new DeactFacilityListModel(dflist1);
            deactfaclist.setModel(dfmodel);
            List<FacilityApprovalRitesModel.ApprovalRitesLine> applist = frmodel.loadinstanceofappusers(m_App, fid);
            appumodel = new AppRitesUserListModel(applist);
            approvalusers.setModel(appumodel);
            set();
            reset();

        } catch (Exception e) {
            set();
            reset();
            //List<FacilityApprovalRitesModel.DeactivtionFacilityLine> dflist1=frmodel.loadinstanceofdeactacility(m_App,fid);
            List dflist1 = new ArrayList();
            dfmodel = new DeactFacilityListModel(dflist1);
            deactfaclist.setModel(dfmodel);
            List applist = new ArrayList();
            //List<FacilityApprovalRitesModel.ApprovalRitesLine> applist=frmodel.loadinstanceofappusers(m_App,fid);
            appumodel = new AppRitesUserListModel(applist);
            approvalusers.setModel(appumodel);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            int row = jTable1.getSelectedRow();
            if (row < jTable1.getRowCount() && row >= 0) {
                String id = jTable1.getModel().getValueAt(row, 20).toString();
                String fname1 = jTable1.getModel().getValueAt(row, 0).toString();
                if (JOptionPane.showConfirmDialog(this, "Do you want to deactivate " + fname1 + " facility ?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    new PreparedSentence(m_App.getSession(), "UPDATE FACILITY SET ACTIVE=FALSE,DEACTIVATEDBY =?,DEACTIVATEDDATE=? WHERE ID=? AND DEACTIVATEDBY IS NULL AND DEACTIVATEDDATE IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), id});
                    if (jCheckBox5.isSelected() == true) {
                        fmodel = FacilitytableModel.loadInstance(m_App, 2);
                        jTable1.setModel(fmodel.getTableModel());
                    } else if (jCheckBox5.isSelected() == false) {
                        fmodel = FacilitytableModel.loadInstance(m_App, 1);
                        jTable1.setModel(fmodel.getTableModel());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:        
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        //fid = UUID.randomUUID().toString();
        //set();
        //reset();
        try {
            set1();
            int tabno = tabpane.getSelectedIndex();
            //System.out.println("--------------  " + tabno + " ------------------");
            if (tabno == 1) {
                //praveen:calling when once we load this facility creation tab
                loadpane();

                if (jCheckBox5.isSelected() == true) {
                    fmodel = FacilitytableModel.loadInstance(m_App, 2);
                    jTable1.setModel(fmodel.getTableModel());
                } else if (jCheckBox5.isSelected() == false) {
                    fmodel = FacilitytableModel.loadInstance(m_App, 1);
                    jTable1.setModel(fmodel.getTableModel());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    public void loadpane() {
        //praveen:generating new primary key for facility
        fid = UUID.randomUUID().toString();

    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        try {
            if (row >= 0 && row < jTable1.getRowCount()) {
                BufferedImage img;
                ImageIcon icon;
                String id = jTable1.getModel().getValueAt(row, 20).toString();
                Object obj[] = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IMAGE FROM FACILITY WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.IMAGE})).find(id);
                if (obj != null) {
                    if (obj[0] != null) {
                        img = (BufferedImage) obj[0];

                        icon = new ImageIcon(img);
                        jLabel1.setIcon(icon);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        try {
            if (row >= 0 && row < jTable1.getRowCount()) {
                if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 21).toString())) {
                    String id = jTable1.getModel().getValueAt(row, 20).toString();
                    loadSelectedFacilityToEdit(id);
                } else {
                    JOptionPane.showMessageDialog(this, "selected facility is deactivated.cannot edit it");
                }
            } else {
                JOptionPane.showMessageDialog(this, "select any facility to edit");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
        // TODO add your handling code here:
        try {
            if (jCheckBox5.isSelected() == true) {
                fmodel = FacilitytableModel.loadInstance(m_App, 2);
                jTable1.setModel(fmodel.getTableModel());
            } else if (jCheckBox5.isSelected() == false) {
                fmodel = FacilitytableModel.loadInstance(m_App, 1);
                jTable1.setModel(fmodel.getTableModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged

    private void standardStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_standardStateChanged
        // TODO add your handling code here:
        if (standard.isSelected()) {
            typeOfMem.setVisible(true);
            jLabel18.setVisible(true);
            jLabel22.setVisible(true);
            jScrollPane2.setVisible(true);
            jButton5.setVisible(true);
            jButton6.setVisible(true);
            jLabel23.setVisible(true);
            jButton7.setVisible(true);
            date.setVisible(true);
            date.setText(Formats.TIMESTAMP.formatValue(new Date()));
            applicableto.setVisible(false);
            mem.setVisible(false);
            memdep.setVisible(false);
        }
    /*  else{
    typeOfMem.setVisible(false);
    jLabel18.setVisible(false);
    jLabel22.setVisible(false);
    jScrollPane2.setVisible(false);
    jButton5.setVisible(false);
    jButton6.setVisible(false);
    jLabel23.setVisible(false);
    jButton7.setVisible(false);
    date.setVisible(false);
    }*/
    }//GEN-LAST:event_standardStateChanged

    private void optionalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optionalStateChanged
        // TODO add your handling code here:
        if (optional.isSelected()) {
            typeOfMem.setVisible(false);
            jLabel18.setVisible(false);
            jLabel22.setVisible(false);
            jScrollPane2.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jLabel23.setVisible(false);
            jButton7.setVisible(false);
            date.setVisible(false);
            applicableto.setVisible(true);
            mem.setVisible(true);
            memdep.setVisible(true);
            mem.setSelected(true);
        }
    /*   else{
    typeOfMem.setVisible(true);
    jLabel18.setVisible(true);
    jLabel22.setVisible(true);
    jList1.setVisible(true);
    jButton5.setVisible(true);
    jButton6.setVisible(true);
    jLabel23.setVisible(true);
    jButton7.setVisible(true);
    date.setVisible(true);
    date.setText(Formats.TIMESTAMP.formatValue(new Date()));
    applicableto.setVisible(false);
    mem.setVisible(false);
    memdep.setVisible(false);
    }*/
    }//GEN-LAST:event_optionalStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (typeOfMem.getSelectedIndex() != -1) {
            if (!memtypelist.contains("ALL")) {
                // }else{
                if (typeOfMem.getSelectedItem().toString().equals("ALL")) {
                    memtypelist.clear();
                    memtypelist.add(typeOfMem.getSelectedItem());
                } else {
                    memtypelist.add(typeOfMem.getSelectedItem());
                }
                mmodel = new MemTypeListModel(memtypelist);
                jList1.setModel(mmodel);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int row = jList1.getSelectedIndex();
        if (row >= 0) {
            memtypelist.remove(row);
            mmodel = new MemTypeListModel(memtypelist);
            jList1.setModel(mmodel);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(date.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            // date=getDate(date);
            date.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void graceperiodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graceperiodActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_graceperiodActionPerformed

    private void billsequenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billsequenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billsequenceActionPerformed


    //praveen:added following lines of code for  approvalmaster  and deactivation master
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            int row = deactfaclist.getSelectedIndex();
            if (row >= 0) {
                FacilityApprovalRitesModel.DeactivtionFacilityLine dflist = (DeactivtionFacilityLine) dfmodel.getElementAt(row);
                String facid = dflist.getFid();
                new StaticSentence(m_App.getSession(), "DELETE FROM FACILITYDEACTIVATIONMASTER WHERE DFACID=? AND  PFID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{facid, fid});
                List<FacilityApprovalRitesModel.DeactivtionFacilityLine> dflist1 = frmodel.loadinstanceofdeactacility(m_App, fid);
                dfmodel = new DeactFacilityListModel(dflist1);
                deactfaclist.setModel(dfmodel);
            } else {
                JOptionPane.showMessageDialog(this, "Select any one facility to remove from deactivationfacilitylist");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
            int row = faclist.getSelectedIndex();
            if (row >= 0) {
                FacilityApprovalRitesModel.AllFacilityLine aflist = (AllFacilityLine) afmodel.getElementAt(row);
                String id = aflist.getId();
                new StaticSentence(m_App.getSession(), "INSERT INTO FACILITYDEACTIVATIONMASTER(PFID,DFACID) VALUES(?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fid, id});
                List<FacilityApprovalRitesModel.DeactivtionFacilityLine> dflist1 = frmodel.loadinstanceofdeactacility(m_App, fid);
                dfmodel = new DeactFacilityListModel(dflist1);
                deactfaclist.setModel(dfmodel);
            } else {
                JOptionPane.showMessageDialog(this, "Select any one facility to add from allfacilitylist");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            int row = allroles.getSelectedIndex();
            if (row >= 0) {
                FacilityApprovalRitesModel.AllUsersLine aulist = (AllUsersLine) aumodel.getElementAt(row);
                String id = aulist.getId();
                new StaticSentence(m_App.getSession(), "INSERT INTO FACILITYAPPROVALRITES(PFID,USERID) VALUES (?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fid, id});
                List<FacilityApprovalRitesModel.ApprovalRitesLine> applist = frmodel.loadinstanceofappusers(m_App, fid);
                appumodel = new AppRitesUserListModel(applist);
                approvalusers.setModel(appumodel);
            } else {
                JOptionPane.showMessageDialog(this, "Select any one user to add from alluserslist");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
            int row = approvalusers.getSelectedIndex();
            if (row >= 0) {
                FacilityApprovalRitesModel.ApprovalRitesLine appulist = (ApprovalRitesLine) appumodel.getElementAt(row);
                String userid = appulist.getUid();
                new StaticSentence(m_App.getSession(), "DELETE FROM FACILITYAPPROVALRITES WHERE USERID=? AND PFID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{userid, fid});
                List<FacilityApprovalRitesModel.ApprovalRitesLine> applist = frmodel.loadinstanceofappusers(m_App, fid);
                appumodel = new AppRitesUserListModel(applist);
                approvalusers.setModel(appumodel);
            } else {
                JOptionPane.showMessageDialog(this, "Select any one user to remove from usersriteslist");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void cardcontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardcontrolActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cardcontrolActionPerformed

    private void cardcontrolStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cardcontrolStateChanged
        // TODO add your handling code here:      
    }//GEN-LAST:event_cardcontrolStateChanged

    private void cardcontrolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cardcontrolItemStateChanged
        // TODO add your handling code here:
        if (cardcontrol.isSelected() == true) {
            startfac.setVisible(true);
            confirm.setVisible(true);
        } else {
            startfac.setVisible(false);
            confirm.setVisible(false);
        }

    }//GEN-LAST:event_cardcontrolItemStateChanged

    private void savechangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savechangesActionPerformed
        // TODO add your handling code here:
//        String staxid=null;
//        String TaxCatID=null;
//        String TaxCatID1=null;
        
        try {
            Transaction t = new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                    String newFacId = UUID.randomUUID().toString();
                    boolean flag = true;
           Boolean basic1=false;
          Boolean basic2=false;
           Boolean cascade1=false;
           Boolean cascade2=false;

                    try {
                        if (fname.getText().length() > 0 && smssform.getText().length() > 0) {
                            String fac = fname.getText().toString();
                            String hsn = hsncode.getText().toString();
                            String sms = smssform.getText().toString();
                            double joiamount = Double.parseDouble(jamount.getText());
                            double renamount = Double.parseDouble(ramount.getText());
                            double usaamount = Double.parseDouble(uamount.getText());
                            double maxamount = Double.parseDouble(amountlimit.getText());
//                              staxmodel.setSelectedKey(facline.getServicetax());
  String staxid=null;
        String TaxCatID=null;
        String TaxCatID1=null;
         
                    if (servicetax.getSelectedItem() != null) {
                                                    TaxCategoryInfo staxcat = (TaxCategoryInfo) servicetax.getSelectedItem();
                                    staxid = staxcat.getID();
//  String name=staxcat.getName();
                                }
                                    if(jComboBox1.getSelectedIndex()!=-1){
                        String TaxCatName=jComboBox1.getSelectedItem().toString();
                         TaxCatID=getTaxCatIdByName1(m_App, TaxCatName);
                    }
                             if(jComboBox2.getSelectedIndex()!=-1){
                        String TaxCatName1=jComboBox2.getSelectedItem().toString();
                         TaxCatID1=getTaxCatIdByName2(m_App, TaxCatName1);
                    }
                             if(jRadioButton2.isSelected()){
                                 cascade1=true;
                                 basic1=false;
                                   
                                  
//                                  jRadioButton2.getBoolean();
                                 
                             
                             }
                               if(jRadioButton4.isSelected()){
//                                 jRadioButton4.setSelected(true);
                                cascade2=true;
                                basic2=false;
                              
                             }
                                if(jRadioButton1.isSelected()){
//                                 jRadioButton4.setSelected(true);
                            basic1=true;
                            cascade1=false;
                            
                          
                             }
                                if(jRadioButton3.isSelected()){
//                                 jRadioButton4.setSelected(true);
                            basic2=true;
                            cascade2=false;
                             
                          
                             }
                                boolean cascade=facline.getCascade2();
                                System.out.println("cassssdd:::"+cascade);
//                             if(jRadioButton1.isSelected()){
//             jRadioButton2.setSelected(false);
//         }
//                                if(jRadioButton2.isSelected()){
//             jRadioButton1.setSelected(false);
//         }
//                             if(jRadioButton3.isSelected()){
//             jRadioButton4.setSelected(false);
//         }
//                             if(jRadioButton4.isSelected()){
//             jRadioButton3.setSelected(false);
//         }
//                    if(facline.getTaxcat_2()!=null && facline.getTaxcat_2().trim().length()>0){
//                     String TaxCatID = facline.getTaxcat_2();
//                     String Taxname=getTaxNameByID(TaxCatID);
////                   String TaxCatName=jComboBox1.getSelectedItem().toString();
//                     for(int i=0;i<TaxCategoryList.size();i++){
//                         String x = TaxCategoryList.get(i).toString();
//                         if(x.equals(Taxname)){
//                             jComboBox1.setSelectedIndex(i);
//                             break;
//                         }
//                     }
//                     
//                 }
//                    if(facline.getTaxcat_3()!=null && facline.getTaxcat_3().trim().length()>0){
//                     String TaxCatID = facline.getTaxcat_3();
//                     String Taxname1=getTaxNameByID1(TaxCatID);
////                      String TaxCatName1=jComboBox2.getSelectedItem().toString();
//                     for(int i=0;i<TaxCategoryList.size();i++){
//                         String x = TaxCategoryList.get(i).toString();
//                         if(x.equals(Taxname1)){
//                             jComboBox2.setSelectedIndex(i);
//                             break;
//                         }
//                     }
//                     
//                 }

                            //if (fac.equals(facline.getname()) || sms.equals(facline.getSmsform()) || joiamount == facline.getjamt() || renamount == facline.getramt() || usaamount == facline.getuamt() || maxamount == facline.getMaxDebt()) {
//                            if(renamount != facline.getramt()){
//                               facline.setRamt(renamount);
//                            }else if(maxamount != facline.getMaxDebt()){
//                                 facline.setMaxdebt(maxamount);
//                            }else if(!fac.equals(facline.getname())){
//                                  facline.setName(fac);
//                            }else if(!hsn.equals(facline.getHsncode())){
//                                  facline.setHsncode(hsn);
//                            }else if(!sms.equals(facline.getSmsform())){
//                                 facline.setSmsform(sms);
//                            }else if(!staxid.equals(facline.getServicetax())){
//                                 facline.setServicetax(staxid);
//                            }else if(!TaxCatID.equals( facline.getTaxcat_2())){
//                                 facline.setTaxcat_2(TaxCatID);
//                            }else if(!TaxCatID1.equals(facline.getTaxcat_3())){
//                                 facline.setTaxcat_3(TaxCatID1);
//                            } else if(cascade1 != facline.getCascade1()){
//                                 facline.setCascade1(cascade1);
//                                  facline.setBasic1(basic1);
//                                 
//                            } else  if(cascade2 != facline.getCascade2()){
//                                
//                                 facline.setCascade2(cascade2);
//                                facline.setBasic2(basic2);
//                            }else if(basic1 != facline.getBasic1()){
//                                 facline.setBasic1(basic1);
//                                 facline.setCascade1(cascade1);
//                            }else if(basic2 != facline.getBasic2()){
//                                 facline.setBasic2(basic2);
//                                 facline.setCascade2(cascade2);
//                                 
//                            }
//                           
//                        
//                            else{
//                                flag = false;
//                            }
if(flag=true){
     if(renamount != facline.getramt()){
                               facline.setRamt(renamount);
                            } if(maxamount != facline.getMaxDebt()){
                                 facline.setMaxdebt(maxamount);
                            } if(!fac.equals(facline.getname())){
                                  facline.setName(fac);
                            } if(!hsn.equals(facline.getHsncode())){
                                  facline.setHsncode(hsn);
                            } if(!sms.equals(facline.getSmsform())){
                                 facline.setSmsform(sms);
                            } if(!staxid.equals(facline.getServicetax())){
                                 facline.setServicetax(staxid);
                            } if(TaxCatID!=null){
                                 if(!TaxCatID.equals( facline.getTaxcat_2())){
                                 facline.setTaxcat_2(TaxCatID);
                            } 
                            }
                            if(TaxCatID1!=null){
                                 if(!TaxCatID1.equals(facline.getTaxcat_3())){
                                 facline.setTaxcat_3(TaxCatID1);
                            } 
                            }
                            
                           
                            
                            
                            
                            if(cascade1 != facline.getCascade1()){
                                 facline.setCascade1(cascade1);
                                  facline.setBasic1(basic1);
                                 
                            } if(cascade2 != facline.getCascade2()){
                                
                                 facline.setCascade2(cascade2);
                                facline.setBasic2(basic2);
                            }if(basic1 != facline.getBasic1()){
                                 facline.setBasic1(basic1);
                                 facline.setCascade1(cascade1);
                            }if(basic2 != facline.getBasic2()){
                                 facline.setBasic2(basic2);
                                 facline.setCascade2(cascade2);
                                 
                            }
    
    
}
                           
                           
                        
                            else{
                                flag = false;
                            }
//                            else {
//                                facline.setJamt(joiamount);
//                                facline.setRamt(renamount);
//                                facline.setUamt(usaamount);
//                                facline.setMaxdebt(maxamount);
//                                facline.setName(fac);
//                                facline.setSmsform(sms);
//                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Enter name and sms short name ");
                            throw new BasicException();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "enter correct value for amount field", "invalid field", JOptionPane.WARNING_MESSAGE);
                        throw new BasicException(e);
                    }
                    if (flag) {
                        new StaticSentence(m_App.getSession(), "UPDATE FACILITY SET ID=?,DEACTREFERENCE=?,ACTIVE=?,DEACTIVATEDBY=?,DEACTIVATEDDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.STRING}), null).exec(new Object[]{newFacId, facline.getid(), false, m_App.getAppUserView().getUser().getName(), new Date(),facline.getid()});
//new StaticSentence(m_App.getSession(), "UPDATE FACILITY SET ID=?,DEACTREFERENCE=?,DEACTIVATEDBY=?,DEACTIVATEDDATE=? WHERE ID=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING}), null).exec(new Object[]{newFacId, facline.getid(), m_App.getAppUserView().getUser().getName(), new Date(),facline.getid()});
                        new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID,ACTIVE,NAME,HSN_CODE,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,CREATEDBY,CRDATE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,MEMTYPE,TYPE_,JFEEACCOUNT,RENEWALACC,USAGEACC,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL,DEBTMAX,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2) " +
                                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                SerializerWriteParams.INSTANCE).exec(new DataParams() {
//                                     new PreparedSentence(m_App.getSession(), "INSERT INTO FACILITY(ID,NAME,HSN_CODE,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,CREATEDBY,CRDATE,BILLSEQUENCE,MAXBILLNO,DUEPERIOD,MEMTYPE,TYPE_,JFEEACCOUNT,RENEWALACC,USAGEACC,SERVICETAX,APPTO,GRACEPERIOD,AMOUNTLIMIT,SMSFORM,INITCONTROL,CONFIRMCONTROL,DEBTMAX,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2) " +
//                                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
//                                SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            @Override
                            public void writeValues() throws BasicException {
                                setString(1, facline.getid());
                                setBoolean(2, true);
                                setString(3, facline.getname());
                                setString(4, facline.getHsncode());
                                setDouble(5, facline.getjamt());
                                setDouble(6, facline.getramt());
                                setString(7, facline.getrperiod());
                                setDouble(8, facline.getuamt());
                                setString(9, facline.getuperiod());
                                setBoolean(10, facline.getecheck());
                                setString(11, m_App.getAppUserView().getUser().getName());
                                setTimestamp(12, facline.getdate());
                                setString(13, facline.getBillseq());
                                setInt(14, facline.getMaxbill());
                                setString(15, facline.getDuePeriod());
                                setString(16, facline.getMemType());
                                setString(17, facline.getType());
                                setString(18, facline.getJaccid());
                                setString(19, facline.getRaccid());
                                setString(20, facline.getUaccid());
                                setString(21, facline.getServicetax());
                                setInt(22, facline.getAppto());
                                setInt(23, facline.getGrace());
                                setDouble(24, facline.getMaxDebt());
                                setString(25, facline.getSmsform());
                                setBoolean(26, facline.isInit());
                                setBoolean(27, facline.isConf());
                                setDouble(28, facline.getdebtmax());
                                  setString(29, facline.getTaxcat_2());
                                      setString(30, facline.getTaxcat_3());
                                       setBoolean(31, facline.getCascade1());
                                        setBoolean(32, facline.getCascade2());
                                          setBoolean(33, facline.getBasic1());
                                           setBoolean(34, facline.getBasic2());
                                          
                            }
                        });
//  Object[] value=new Object[]{facline.getid(),true,facline.getHsncode(),facline.getjamt(),facline.getramt(),facline.getrperiod(),facline.getuamt(),facline.getuperiod(),facline.getecheck(),facline.getcrby(),facline.getdate(),facline.getBillseq(),facline.getMaxbill(),facline.};
//                    new PreparedSentence(m_App.getSession()
//                        , "UPDATE FACILITY SET ID=?,NAME=?,HSN_CODE=?,JOINAMT=?,JFEEACCOUNT=?,RENEWALACC=?,USAGEACC=?,RENEWALAMT=?,RPERIODICITY=?,USAGEAMT=?,UPERIODICITY=?,ENTRANCECONTROL=?,CREATEDBY=?,CRDATE=?,MEMTYPE=?,AMOUNTLIMIT=?,DEACTIVATEDBY=?,DEACTIVATEDDATE=?,TYPE_=?,DUEPERIOD=?,ACTIVE=?,SMSFORM=?,APPTO=?,INITCONTROL=?,CONFIRMCONTROL=?,GRACEPERIOD=?,BILLSEQUENCE=?,SERVICETAX=?,DEBTMAX=?,MAXBILLNO=?,TAXCAT_2=?,TAXCAT_3=?,CASCADE1=?,CASCADE2=?,BASIC1=?,BASIC2=?  WHERE ID=?"
//                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})
//                    ).exec(value);
                    } else {
                        JOptionPane.showMessageDialog(null, "you have not changed any field");
                    }
                    return null;
                }
            };
            t.execute();
            loadData();
            jTabbedPane1.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

}//GEN-LAST:event_savechangesActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        try {
            loadData();
            jTabbedPane1.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_cancelActionPerformed

    private void ramountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ramountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ramountActionPerformed

private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCheckBox5ActionPerformed

    private void hsncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsncodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hsncodeActionPerformed

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
             jRadioButton2.setSelected(false);
         }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton2.isSelected()){
             jRadioButton1.setSelected(false);
//             taxcatflag=true;
         }
       
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void servicetaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicetaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_servicetaxActionPerformed

    private void servicetaxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_servicetaxItemStateChanged
        // TODO add your handling code here:
//         jComboBox1.setSelectedIndex(9);
//      jComboBox2.setSelectedIndex(9);
// if(facline.getTaxcat_2()!=null && facline.getTaxcat_2().trim().length()>0){
//                     String TaxCatID = facline.getTaxcat_2();
//                     String Taxname=getTaxNameByID(TaxCatID);
////                   String TaxCatName=jComboBox1.getSelectedItem().toString();
//                     for(int i=0;i<TaxCategoryList.size();i++){
//                         String x = TaxCategoryList.get(i).toString();
//                         if(x.equals(Taxname)){
//                             jComboBox1.setSelectedIndex(i);
//                             break;
//                         }
//                     }
//                     
//                 }
//                    if(facline.getTaxcat_3()!=null && facline.getTaxcat_3().trim().length()>0){
//                     String TaxCatID = facline.getTaxcat_3();
//                     String Taxname1=getTaxNameByID1(TaxCatID);
////                      String TaxCatName1=jComboBox2.getSelectedItem().toString();
//                     for(int i=0;i<TaxCategoryList.size();i++){
//                         String x = TaxCategoryList.get(i).toString();
//                         if(x.equals(Taxname1)){
//                             jComboBox2.setSelectedIndex(i);
//                             break;
//                         }
//                     }
//                     
//                 }

    }//GEN-LAST:event_servicetaxItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton3.isSelected()){
             jRadioButton4.setSelected(false);
         }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton4.isSelected()){
             jRadioButton3.setSelected(false);
         }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void typeOfMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeOfMemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeOfMemActionPerformed

    private class AllFacilityListModel extends AbstractListModel {

        private java.util.List<FacilityApprovalRitesModel.AllFacilityLine> flist;

        public AllFacilityListModel(java.util.List<FacilityApprovalRitesModel.AllFacilityLine> flist) {
            this.flist = flist;
        }

        public int getSize() {
            return flist.size();
        }

        public Object getElementAt(int i) {
            return flist.get(i);
        }

        public void remove(int i) {
            flist.remove(i);
        }
    }

    private class AllUserListModel extends AbstractListModel {

        private java.util.List<FacilityApprovalRitesModel.AllUsersLine> flist;

        public AllUserListModel(java.util.List<FacilityApprovalRitesModel.AllUsersLine> flist) {
            this.flist = flist;
        }

        public int getSize() {
            return flist.size();
        }

        public Object getElementAt(int i) {
            return flist.get(i);
        }

        public void remove(int i) {
            flist.remove(i);
        }
    }

    private class DeactFacilityListModel extends AbstractListModel {

        private java.util.List<FacilityApprovalRitesModel.DeactivtionFacilityLine> flist;

        public DeactFacilityListModel(java.util.List<FacilityApprovalRitesModel.DeactivtionFacilityLine> flist) {
            this.flist = flist;
        }

        public int getSize() {
            return flist.size();
        }

        public Object getElementAt(int i) {
            return flist.get(i);
        }

        public void remove(int i) {
            flist.remove(i);
        }
    }

    private class AppRitesUserListModel extends AbstractListModel {

        private java.util.List<FacilityApprovalRitesModel.ApprovalRitesLine> flist;

        public AppRitesUserListModel(java.util.List<FacilityApprovalRitesModel.ApprovalRitesLine> flist) {
            this.flist = flist;
        }

        public int getSize() {
            return flist.size();
        }

        public Object getElementAt(int i) {
            return flist.get(i);
        }

        public void remove(int i) {
            flist.remove(i);
        }
    }

    public void loadSelectedFacilityToEdit(String id) throws BasicException {
        savechanges.setVisible(true);
        cancel.setVisible(true);
        fmodel = FacilitytableModel.loadInstanceToEdit(m_App, id);
        facline = fmodel.getFacility();
        if (facline.isActive()) {
            jTabbedPane1.setSelectedIndex(1);
            reset1();
            fname.setText(facline.getname());
            hsncode.setText(facline.getHsncode());
            smssform.setText(facline.getSmsform());
            
            if (facline.getType().equals("Standard")) {
                standard.setSelected(true);
            } else {
                optional.setSelected(true);
                if (facline.getAppto() == 0) {
                    mem.setSelected(true);
                } else {
                    memdep.setSelected(true);
                }
            }
            if (facline.getjamt() > 0.0) {
                jCheckBox1.setSelected(true);
                jamount.setText(facline.getjamt().toString());
                accounttype.setSelectedKey(facline.getJaccid());
            }
            if (facline.getramt() > 0.0) {
                jCheckBox2.setSelected(true);
                ramount.setText(facline.getramt().toString());
                periodicitymodel.setSelectedKey(facline.getrperiod());
                renewalacctype.setSelectedKey(facline.getRaccid());
            }
            if (facline.getuamt() > 0.0) {
                jCheckBox3.setSelected(true);
                uamount.setText(facline.getuamt().toString());
                periodicitymodel1.setSelectedKey(facline.getuperiod());
                usageacctype.setSelectedKey(facline.getUaccid());
            }
            jCheckBox4.setSelected(facline.getecheck());
            if (facline.isInit() || facline.isConf()) {
                cardcontrol.setSelected(true);
                startfac.setSelected(facline.isInit());
                confirm.setSelected(facline.isConf());
            }
            graceperiod.setText(String.valueOf(facline.getGrace()));
            amountlimit.setText(String.valueOf(facline.getMaxDebt()));
            debttypemodel.setSelectedKey(facline.getDuePeriod());
            billsequence.setText(facline.getBillseq());
            staxmodel.setSelectedKey(facline.getServicetax());
//            GuestBoxValModel.setSelectedKey(facline.getTaxcat_2());
//              GuestBoxValModel1.setSelectedKey(facline.getTaxcat_3());
//             String  TaxCatName=null;
//             String  TaxCatName1=null;
            jRadioButton2.setSelected(facline.getCascade1());
              jRadioButton4.setSelected(facline.getCascade2());
               jRadioButton1.setSelected(facline.getBasic1());
              jRadioButton3.setSelected(facline.getBasic2());
              if(facline.getTaxcat_2()!=null && facline.getTaxcat_2().trim().length()>0){
                 
                     String TaxCatID = facline.getTaxcat_2();
                     String Taxname=getTaxNameByID(TaxCatID);
//                   String TaxCatName=jComboBox1.getSelectedItem().toString();
                     for(int i=0;i<TaxCategoryList.size();i++){
                         String x = TaxCategoryList.get(i).toString();
                         if(x.equals(Taxname)){
                             jComboBox1.setSelectedIndex(i);
                             break;
                         }
                     }
                     
                 }
//              String TaxCatName2=null;
             if(facline.getTaxcat_3()!=null && facline.getTaxcat_3().trim().length()>0){
                     String TaxCatID = facline.getTaxcat_3();
                     String Taxname1=getTaxNameByID1(TaxCatID);
//                      String TaxCatName1=jComboBox2.getSelectedItem().toString();
                     for(int i=0;i<TaxCategoryList.size();i++){
                         String x = TaxCategoryList.get(i).toString();
                         if(x.equals(Taxname1)){
                             jComboBox2.setSelectedIndex(i);
                             break;
                         }
                     }
                     
                 }
            date.setText(String.valueOf(facline.getdate()));
            if ("ALL".equals(facline.getMemType())) {
                memtypelist.add(facline.getMemType());
            } else {
                String[] memtype1 = facline.getMemType().toString().split("#");
                Object[] params = new Object[memtype1.length];
                Datas[] data = new Datas[memtype1.length];
                StringBuffer condition = new StringBuffer("");
                for (int j = 0; j < memtype1.length; j++) {
                    data[j] = Datas.STRING;
                    params[j] = memtype1[j].toString();
                    condition.append(" ? , ");
                }
                if (condition.length() > 0) {
                    condition.deleteCharAt(condition.lastIndexOf(","));
                }
                fmodel = FacilitytableModel.loadMemtype(m_App, condition, params, data);
                memtypelist.addAll(fmodel.getMemType());
            }

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allfacility;
    private javax.swing.JList allroles;
    private javax.swing.JTextField amountlimit;
    private javax.swing.JLabel applicableto;
    private javax.swing.JLabel approvalrites;
    private javax.swing.JList approvalusers;
    private javax.swing.JTextField billsequence;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancel;
    private javax.swing.JCheckBox cardcontrol;
    private javax.swing.JCheckBox confirm;
    private javax.swing.JTextField date;
    private javax.swing.JList deactfaclist;
    private javax.swing.JLabel deactivatelist;
    private javax.swing.JComboBox debttype;
    private javax.swing.JList faclist;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel grace;
    private javax.swing.JTextField graceperiod;
    private javax.swing.JTextField hsncode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.openbravo.data.gui.JImageEditor jImageEditor1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jamount;
    private javax.swing.JComboBox jfeeaccount;
    private javax.swing.JLabel maxamount;
    private javax.swing.JRadioButton mem;
    private javax.swing.JRadioButton memdep;
    private javax.swing.JRadioButton optional;
    private javax.swing.JTextField ramount;
    private javax.swing.JComboBox renewalaccount;
    private javax.swing.JComboBox rperiod;
    private javax.swing.JButton savechanges;
    private javax.swing.JComboBox servicetax;
    private javax.swing.JTextField smssform;
    private javax.swing.JRadioButton standard;
    private javax.swing.JCheckBox startfac;
    private javax.swing.JComboBox typeOfMem;
    private javax.swing.JTextField uamount;
    private javax.swing.JComboBox uperiod;
    private javax.swing.JComboBox usageaccount;
    private javax.swing.JLabel users;
    // End of variables declaration//GEN-END:variables
 public void ButtonGroup(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(jRadioButton3);
        bg2.add(jRadioButton4);
        
                
    }
public List getTaxCategoryList1(AppView app ) throws BasicException{
          List<Object> Tax_List = new ArrayList<Object>();
           Tax_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM TAXCATEGORIES  ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Tax_List;
      }
public List getTaxCategoryList2(AppView app ) throws BasicException{
          List<Object> Tax_List = new ArrayList<Object>();
           Tax_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM TAXCATEGORIES  ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Tax_List;
      }

public String getTaxCatIdByName1(AppView app , String Name ) {
            Object  TaxID = null;
          String x = null;
//           String y = null;
          try{
                 TaxID  =  new StaticSentence(app.getSession(), "SELECT ID FROM TAXCATEGORIES WHERE NAME=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
                if(TaxID!=null){
                     x = TaxID.toString();
                     

                    return x;  
                }
                else{
                    return "";
                }
                
          }
          catch(BasicException e){
              e.printStackTrace();
          }
          
          return x;
      }
public  String getTaxCatIdByName2(AppView app , String Name ) {
          Object  TaxID = null;
          String x = null;
          try{
                TaxID  =  new StaticSentence(app.getSession(), "SELECT ID FROM TAXCATEGORIES WHERE NAME=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
                if(TaxID!=null){
                     x = TaxID.toString();  

                    return x;  
                }
                else{
                    return "";
                }
          }
          catch(BasicException e){
              e.printStackTrace();
          }
          
          return x;
      }
public  Double getTaxRateByTaxCatID(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
                    rate1=TaxRate;
                }
                else{
                    TaxRate=0.00;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return TaxRate;
    } 
public Double getTaxRateByTaxCatID1(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
                    rate2=TaxRate;
                }
                else{
                    TaxRate=0.00;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return TaxRate;
    } 

  public String getTaxNameByID(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }
  public String getTaxNameByID1(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }
    






}
