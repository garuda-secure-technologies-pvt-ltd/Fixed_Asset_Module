

package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.MemLinkTableModel;
import com.openbravo.pos.clubmang.MemberDependentTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author  adrianromero
 */
public class CustomersView1 extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    private Object m_oId;
    
    private SentenceList m_sentcat;

    private DataLogicFacilities dlfac;
    private ComboBoxValModel m_memdtype;
   // private ComboBoxValModel dtyprModel
  //  private ComboBoxValModel m_memltype;
    private String skey;
    private String mtype;
    private AppView m_App;
    private DirtyManager dirty;
    private DataLogicCustomers dlcus;
    private String customerid;
    private MemberDependentTableModel mdmodel;
    private Object[] initialdata;
    private AppUser user;
    private String[] labels;
   // private List<Object[]> newdeplist;
    private List<MemberDependentTableModel.Dependentline> deplist;
    private List<MemberDependentTableModel.Dependentline> odeplist;
    private String[] llist=new String[]{"Name","Type","DOB","DOJ"};
    private boolean flag=true;
   // private String memtype;
        
    /** Creates new form CustomersView */
    public CustomersView1() {

        initComponents();
        labels=new String[]{"TaxID","SearchKey","Name","Card","FirstName","LastName","Email","Phone(O)","Phone(R)","Fax","Address","Address2","Postal","City","Region","Country","Son of/Wife of","Mobile","Date Of Birth"};
    }
    
    public void activate() throws BasicException {
       // newdeplist=new ArrayList<Object[]>();
       /* int cnt=0;
            AppUser user1=m_App.getAppUserView().getUser();
            if(user1.getMemid()!=null){
                try {
                    Object[] count = (Object[]) new PreparedSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMPROFILEEDIT WHERE CUSTOMER = ? AND CONFIRMEDBY IS NULL", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(user1.getMemid());
                    if (count != null && count[0] != null) {
                        cnt = Integer.valueOf(String.valueOf(count[0]));
                    }
                    if(cnt==0)
                      flag=true;
                    else flag=false;
                }catch(Exception e){
                  e.printStackTrace();
                }

            }*/
    //  if(flag==true){
        deplist=new ArrayList<MemberDependentTableModel.Dependentline>();
        odeplist=new ArrayList<MemberDependentTableModel.Dependentline>();
        user=m_App.getAppUserView().getUser();
        if(user.getMemid()!=null){
         customerid=user.getMemid();
         searchkey.setVisible(false);
         jLabel1.setVisible(false);
        }
        List mem=dlcus.getCustomerList().list();
        mem.add(0,null);
        m_memdtype=new ComboBoxValModel();
       initialdata=(Object[]) new StaticSentence(m_App.getSession()
                        , "SELECT TAXID,SEARCHKEY,NAME,CARD,FIRSTNAME,LASTNAME,EMAIL,PHONE,PHONE2,FAX,ADDRESS,ADDRESS2, "
                        +"POSTAL,CITY,REGION,COUNTRY,SOWO,MOBILE,DOB FROM CUSTOMERS WHERE ID=?",SerializerWriteString.INSTANCE
                        ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP})
                        ).find(user.getMemid());
        if(initialdata!=null)
        writeValueEdit(initialdata);
        mdmodel=MemberDependentTableModel.loadInstance(m_App, customerid);
        jTable1.setModel(mdmodel.getTableModel());
        odeplist.addAll(mdmodel.getDependentList());
        int rowno=jTable1.getRowCount();
        skey=String.valueOf(initialdata[1]);
        dnum.setText(skey+"\\"+rowno);
       // jButton5.setVisible(true);
     // }else{
      //   jButton5.setVisible(false);
     // }
    }
    public void writeValueEdit(Object value) {
        Object[] customer = (Object[]) value;
        m_jTaxID.setText((String) customer[0]);
        searchkey.setText((String)customer[1]);
        m_jName.setText((String) customer[2]);
        jcard.setText((String) customer[3]);
        txtFirstName.setText(Formats.STRING.formatValue(customer[4]));
        txtLastName.setText(Formats.STRING.formatValue(customer[5]));
        txtEmail.setText(Formats.STRING.formatValue(customer[6]));
        txtPhone.setText(Formats.STRING.formatValue(customer[7]));
        txtPhone2.setText(Formats.STRING.formatValue(customer[8]));
        txtFax.setText(Formats.STRING.formatValue(customer[9]));
       
        txtAddress.setText(Formats.STRING.formatValue(customer[10]));
        txtAddress2.setText(Formats.STRING.formatValue(customer[11]));
        txtPostal.setText(Formats.STRING.formatValue(customer[12]));
        txtCity.setText(Formats.STRING.formatValue(customer[13]));
        txtRegion.setText(Formats.STRING.formatValue(customer[14]));
        txtCountry.setText(Formats.STRING.formatValue(customer[15]));
        
        sowo.setText(Formats.STRING.formatValue(customer[16]));
        mobile.setText(Formats.STRING.formatValue(customer[17]));

        if(customer[18]!=null && !customer[18].equals("") ){
            Date d=(Date)customer[18];
            Calendar cal=GregorianCalendar.getInstance();
            cal.setTimeInMillis(d.getTime());
            date.setSelectedIndex(cal.get(Calendar.DATE));
            month.setSelectedIndex(cal.get(Calendar.MONTH)+1);
            year.setText(String.valueOf(cal.get(Calendar.YEAR)));
        }else{
           date.setSelectedIndex(-1);
           month.setSelectedIndex(-1);
           year.setText(null);
        }
       reset();
    }
  
 
 // private void getNewPermanentFacility(){

  //}
    public Object[] createValue() throws BasicException {
        Object[] customer = new Object[19];
        try{
        
        customer[0] = m_jTaxID.getText();
        customer[1] = searchkey.getText().trim().toUpperCase();
        customer[2] = m_jName.getText();
        customer[3] = Formats.STRING.parseValue(jcard.getText()); // Format to manage NULL values

        customer[4] = Formats.STRING.parseValue(txtFirstName.getText());
        customer[5] = Formats.STRING.parseValue(txtLastName.getText());
        customer[6] = Formats.STRING.parseValue(txtEmail.getText());
        customer[7] = Formats.STRING.parseValue(txtPhone.getText());
        customer[8] = Formats.STRING.parseValue(txtPhone2.getText());
        customer[9] = Formats.STRING.parseValue(txtFax.getText());
       
        customer[10] = Formats.STRING.parseValue(txtAddress.getText());
        customer[11] = Formats.STRING.parseValue(txtAddress2.getText());
        customer[12] = Formats.STRING.parseValue(txtPostal.getText());
        customer[13] = Formats.STRING.parseValue(txtCity.getText());
        customer[14] = Formats.STRING.parseValue(txtRegion.getText());
        customer[15] = Formats.STRING.parseValue(txtCountry.getText());
        customer[16]=sowo.getText();
        customer[17]=mobile.getText();
        Object d=null;
        if(date.getSelectedItem()!=null){
         d = getDate(date.getSelectedItem().toString(),month.getSelectedIndex(),year.getText());
        }
        customer[18]=d;
        }
        catch(Exception e){
            customer=null;
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error ", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return customer;
    }   
    

    public String getTitle() {
        return "Profile Editor";
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        if(flag)
            return this;
        else
            return null;
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
         /*int cnt=0;
        try {
            AppUser user1=m_App.getAppUserView().getUser();
            if(user1.getMemid()!=null){
              Object[] count = (Object[])new PreparedSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMPROFILEEDIT WHERE CUSTOMER = ? AND CONFIRMEDBY IS NULL", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(user1.getMemid());
              if(count!=null && count[0]!=null){
                cnt=Integer.valueOf(String.valueOf(count[0]));
              }
            }
        } catch (BasicException ex) {
            Logger.getLogger(CustomersView1.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(cnt==0){*/
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlcus=(DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        String datelist[]=new String[] {null, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        String monthlist[]=new String[] { null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        date.setModel(new javax.swing.DefaultComboBoxModel(datelist));
        month.setModel(new javax.swing.DefaultComboBoxModel(monthlist));
        ddate.setModel(new javax.swing.DefaultComboBoxModel(datelist));
        dmonth.setModel(new javax.swing.DefaultComboBoxModel(monthlist));
        dtypelist.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Daughter","Father","Mother","Son","Spouse"}));
     //   editIndicator1.setVisible(false);
        m_sentcat = dlSales.getTaxCustCategoriesList();
        jButton5.setText("Request Changes");

      /*  dirty = new DirtyManager();
        m_jTaxID.getDocument().addDocumentListener(dirty);
       // m_jSearchkey.getDocument().addDocumentListener(dirty);
        sowo.getDocument().addDocumentListener(dirty);
        m_jName.getDocument().addDocumentListener(dirty);
        m_jCategory.addActionListener(dirty);
        date.addActionListener(dirty);
        month.addActionListener(dirty);
        year.getDocument().addDocumentListener(dirty);
        mobile.getDocument().addDocumentListener(dirty);
      /*  serviceTaxcat.addActionListener(dirty);
        entTaxcat.addActionListener(dirty);
        sponsor1.addActionListener(dirty);
        sponsor2.addActionListener(dirty);
        sponsor3.addActionListener(dirty);
        joindate.getDocument().addDocumentListener(dirty);
        terminationdate.getDocument().addDocumentListener(dirty);
        txtFirstName.getDocument().addDocumentListener(dirty);
        txtLastName.getDocument().addDocumentListener(dirty);
        txtEmail.getDocument().addDocumentListener(dirty);
        txtPhone.getDocument().addDocumentListener(dirty);
        txtPhone2.getDocument().addDocumentListener(dirty);
        txtFax.getDocument().addDocumentListener(dirty);

        txtAddress.getDocument().addDocumentListener(dirty);
        txtAddress2.getDocument().addDocumentListener(dirty);
        txtPostal.getDocument().addDocumentListener(dirty);
        txtCity.getDocument().addDocumentListener(dirty);
        txtRegion.getDocument().addDocumentListener(dirty);
        txtCountry.getDocument().addDocumentListener(dirty);*/
        flag=true;
      /* }else{
         flag=false;
         JOptionPane.showMessageDialog(this, "Request for Editing the profile is under Processing", "Sorry Cannot load the page", JOptionPane.INFORMATION_MESSAGE);
         deactivate();
       }*/
    }

    public Object getBean() {
       /* int cnt=0;
            AppUser user1=m_App.getAppUserView().getUser();
            if(user1.getMemid()!=null){
                try {
                    Object[] count = (Object[]) new PreparedSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMPROFILEEDIT WHERE CUSTOMER = ? AND CONFIRMEDBY IS NULL", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(user1.getMemid());
                    if (count != null && count[0] != null) {
                        cnt = Integer.valueOf(String.valueOf(count[0]));
                    }
                    if(cnt==0)
                      flag=true;
                    else flag=false;
                } catch (BasicException ex) {
                    Logger.getLogger(CustomersView1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        if(flag){
            return this;
        }else
            return null;*/
        return this;
    }
    private Date getDate(String date,int month,String year){
       // Date d=new Date();
        Calendar cal=Calendar.getInstance();
       // cal.setTimeInMillis(d.getTime());
        cal.set(Integer.parseInt(year), month-1,Integer.parseInt(date));
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,00);
        Date d=new Date();
        d.setTime(cal.getTimeInMillis());
        return d;

    }
    private Date getDate(Date d){
      Calendar cal=Calendar.getInstance();
      cal.setTimeInMillis(d.getTime());
      cal.set(Calendar.MINUTE,00);
      cal.set(Calendar.HOUR_OF_DAY,00);
      cal.set(Calendar.SECOND,00);
      cal.set(Calendar.MILLISECOND,00);
      d.setTime(cal.getTimeInMillis());
      return d;
    }
    private void reset(){
      dname.setText(null);
      ddate.setSelectedIndex(-1);
      dmonth.setSelectedIndex(-1);
      dyear.setText(null);
      ddoj.setText(null);
      dnum.setText(null);
      dtypelist.setSelectedIndex(-1);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcard = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        m_jTaxID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtPhone2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        mobile = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtAddress2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPostal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtRegion = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        dname = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        dtypelist = new javax.swing.JComboBox();
        ddoj = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        dyear = new javax.swing.JTextField();
        ddate = new javax.swing.JComboBox();
        dmonth = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        dnum = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        year = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        sowo = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchkey = new javax.swing.JTextField();

        setAutoscrolls(true);
        setLayout(null);

        jLabel3.setText(AppLocal.getIntString("label.name")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 40, 140, 14);
        add(m_jName);
        m_jName.setBounds(200, 40, 270, 20);

        jLabel5.setText(AppLocal.getIntString("label.card")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 140, 140, 14);

        jcard.setEditable(false);
        add(jcard);
        jcard.setBounds(200, 140, 270, 20);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/color_line16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(480, 140, 49, 25);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileclose.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(550, 140, 49, 25);
        add(m_jTaxID);
        m_jTaxID.setBounds(200, 220, 140, 20);

        jLabel7.setText(AppLocal.getIntString("label.taxid")); // NOI18N
        add(jLabel7);
        jLabel7.setBounds(20, 220, 140, 14);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setLayout(null);

        jLabel14.setText(AppLocal.getIntString("label.fax")); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(10, 130, 140, 14);
        jPanel1.add(txtFax);
        txtFax.setBounds(150, 130, 270, 20);

        jLabel15.setText(AppLocal.getIntString("label.lastname")); // NOI18N
        jPanel1.add(jLabel15);
        jLabel15.setBounds(10, 40, 140, 14);
        jPanel1.add(txtLastName);
        txtLastName.setBounds(150, 40, 270, 20);

        jLabel16.setText(AppLocal.getIntString("label.email")); // NOI18N
        jPanel1.add(jLabel16);
        jLabel16.setBounds(10, 70, 140, 14);
        jPanel1.add(txtEmail);
        txtEmail.setBounds(150, 70, 270, 20);

        jLabel17.setText(AppLocal.getIntString("label.phone")); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(10, 100, 140, 14);
        jPanel1.add(txtPhone);
        txtPhone.setBounds(150, 100, 150, 20);

        jLabel18.setText(AppLocal.getIntString("label.phone2")); // NOI18N
        jPanel1.add(jLabel18);
        jLabel18.setBounds(310, 100, 70, 14);
        jPanel1.add(txtPhone2);
        txtPhone2.setBounds(380, 100, 160, 20);

        jLabel19.setText(AppLocal.getIntString("label.firstname")); // NOI18N
        jPanel1.add(jLabel19);
        jLabel19.setBounds(10, 10, 140, 14);
        jPanel1.add(txtFirstName);
        txtFirstName.setBounds(150, 10, 270, 20);

        jLabel11.setText("Mobile");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(10, 160, 110, 14);
        jPanel1.add(mobile);
        mobile.setBounds(150, 160, 270, 20);

        jTabbedPane1.addTab(AppLocal.getIntString("label.contact"), jPanel1); // NOI18N

        jPanel2.setLayout(null);

        jLabel13.setText(AppLocal.getIntString("label.address")); // NOI18N
        jPanel2.add(jLabel13);
        jLabel13.setBounds(10, 10, 140, 14);
        jPanel2.add(txtAddress);
        txtAddress.setBounds(150, 10, 270, 20);

        jLabel20.setText(AppLocal.getIntString("label.country")); // NOI18N
        jPanel2.add(jLabel20);
        jLabel20.setBounds(10, 160, 140, 14);
        jPanel2.add(txtCountry);
        txtCountry.setBounds(150, 160, 270, 20);

        jLabel21.setText(AppLocal.getIntString("label.address2")); // NOI18N
        jPanel2.add(jLabel21);
        jLabel21.setBounds(10, 40, 140, 14);
        jPanel2.add(txtAddress2);
        txtAddress2.setBounds(150, 40, 270, 20);

        jLabel22.setText(AppLocal.getIntString("label.postal")); // NOI18N
        jPanel2.add(jLabel22);
        jLabel22.setBounds(10, 70, 140, 14);
        jPanel2.add(txtPostal);
        txtPostal.setBounds(150, 70, 270, 20);

        jLabel23.setText(AppLocal.getIntString("label.city")); // NOI18N
        jPanel2.add(jLabel23);
        jLabel23.setBounds(10, 100, 140, 14);
        jPanel2.add(txtCity);
        txtCity.setBounds(150, 100, 270, 20);

        jLabel24.setText(AppLocal.getIntString("label.region")); // NOI18N
        jPanel2.add(jLabel24);
        jLabel24.setBounds(10, 130, 140, 14);
        jPanel2.add(txtRegion);
        txtRegion.setBounds(150, 130, 270, 20);

        jTabbedPane1.addTab(AppLocal.getIntString("label.location"), jPanel2); // NOI18N

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jPanel6.setLayout(null);

        jLabel32.setText("Name");
        jPanel6.add(jLabel32);
        jLabel32.setBounds(10, 10, 90, 14);
        jPanel6.add(dname);
        dname.setBounds(110, 10, 190, 20);

        jLabel33.setText("Type");
        jPanel6.add(jLabel33);
        jLabel33.setBounds(10, 100, 80, 14);

        jLabel34.setText("Date Of Joining");
        jPanel6.add(jLabel34);
        jLabel34.setBounds(10, 70, 90, 20);

        jPanel6.add(dtypelist);
        dtypelist.setBounds(110, 100, 190, 20);
        jPanel6.add(ddoj);
        ddoj.setBounds(110, 70, 190, 20);

        jLabel35.setText("Date Of Birth");
        jPanel6.add(jLabel35);
        jLabel35.setBounds(10, 40, 80, 14);
        jPanel6.add(dyear);
        dyear.setBounds(260, 40, 40, 20);

        jPanel6.add(ddate);
        ddate.setBounds(110, 40, 40, 20);

        jPanel6.add(dmonth);
        dmonth.setBounds(160, 40, 90, 20);

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4);
        jButton4.setBounds(270, 140, 73, 23);

        jLabel39.setText("Number");
        jPanel6.add(jLabel39);
        jLabel39.setBounds(330, 10, 70, 14);

        dnum.setEditable(false);
        jPanel6.add(dnum);
        dnum.setBounds(410, 10, 80, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);
        jButton1.setBounds(320, 70, 40, 25);

        jTabbedPane2.addTab("Add", jPanel6);

        jPanel7.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel7.add(jScrollPane2);
        jScrollPane2.setBounds(1, 5, 490, 170);
        jPanel7.add(jTabbedPane3);
        jTabbedPane3.setBounds(380, -20, 5, 5);

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6);
        jButton6.setBounds(270, 180, 100, 23);

        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7);
        jButton7.setBounds(400, 180, 90, 23);

        jTabbedPane2.addTab("List", jPanel7);

        jTabbedPane1.addTab("Member Dependent", jTabbedPane2);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 290, 570, 240);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Contacts");

        jLabel9.setText(AppLocal.getIntString("label.custtaxcategory")); // NOI18N
        add(jLabel9);
        jLabel9.setBounds(20, 180, 170, 14);
        add(m_jCategory);
        m_jCategory.setBounds(200, 180, 270, 20);

        jLabel30.setText("Date Of Birth");
        add(jLabel30);
        jLabel30.setBounds(20, 260, 110, 14);

        add(date);
        date.setBounds(200, 260, 40, 20);

        add(month);
        month.setBounds(250, 260, 90, 20);
        add(year);
        year.setBounds(360, 260, 60, 20);

        jLabel31.setText("Son Of/Wife Of");
        add(jLabel31);
        jLabel31.setBounds(20, 100, 110, 14);
        add(sowo);
        sowo.setBounds(200, 100, 270, 20);

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5);
        jButton5.setBounds(470, 10, 150, 23);

        jLabel1.setText("Search key");
        add(jLabel1);
        jLabel1.setBounds(20, 70, 90, 14);
        add(searchkey);
        searchkey.setBounds(200, 70, 270, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardnew"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {            
            jcard.setText("c" + StringUtils.getCardNumber());
            dirty.setDirty(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardremove"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            jcard.setText(null);
            dirty.setDirty(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    private CustomerInfo lcusInfo;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         try{
            if(dname.getText().length()>0 && dtypelist.getSelectedIndex()!=-1 &&  dnum.getText().length()>0){
            Date d=null;

            if(ddate.getSelectedIndex()>0 && dmonth.getSelectedIndex()>0 && dyear.getText().length()>0)
                    d= getDate(ddate.getSelectedItem().toString(),dmonth.getSelectedIndex(),dyear.getText());
             Calendar cal=Calendar.getInstance();
             cal.setTimeInMillis(d.getTime());
             Calendar cal1=Calendar.getInstance();
             cal1.setTimeInMillis(new Date().getTime());
             if(cal.before(cal1)){
             Object params[]=new Object[]{dname.getText(),dnum.getText(),d,Formats.TIMESTAMP.parseValue(ddoj.getText()),dtypelist.getSelectedItem(),UUID.randomUUID().toString(),"new entry"};
             //newdeplist.add(params);
             MemberDependentTableModel.Dependentline line=MemberDependentTableModel.Dependentline.CreateInstance(params);
             mdmodel.addDependentLine(line);
             dname.setText(null);
             ddate.setSelectedIndex(-1);
             dmonth.setSelectedIndex(-1);
             dyear.setText(null);
             ddoj.setText(null);
             dnum.setText(null);
             dtypelist.setSelectedIndex(-1);
             jTable1.setModel(mdmodel.getTableModel());
             int rowno=jTable1.getRowCount();
             dnum.setText(skey+"\\"+rowno);
             }else{
                JOptionPane.showMessageDialog(this,"Please select a correct DOB", "The selected date is not less the present date", JOptionPane.ERROR_MESSAGE);
             }
            }else{
              JOptionPane.showMessageDialog(this,"Please select a member first", "Error Not Saved", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
    /*  try{
        javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int num=tabpane.getSelectedIndex();
        if(num==2){
           dtypelist.setSelectedIndex(-1);
           jTabbedPane2.setSelectedIndex(0);
           revalidate();
        }
      }
        catch(Exception e){
            e.printStackTrace();
        }*/
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(ddoj.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            date=getDate(date);
            ddoj.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        // TODO add your handling code here:
      /*  try{
          if(customerid!=null){
            mdmodel=MemberDependentTableModel.loadInstance(m_App, customerid);
            jTable1.setModel(mdmodel.getTableModel());
            int rowno=jTable1.getRowCount();
            dnum.setText(skey+"\\"+rowno);
          }
        }catch(Exception e){
          e.printStackTrace();
        }*/
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
            try {
            int cnt=0;
            Object[] count = (Object[])new PreparedSentence(m_App.getSession(), "SELECT COUNT(*) FROM MEMPROFILEEDIT WHERE CUSTOMER = ? AND CONFIRMEDBY IS NULL", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(user.getMemid());
              if(count!=null && count[0]!=null){
                cnt=Integer.valueOf(String.valueOf(count[0]));
              }
            if(cnt==0){
            if(JOptionPane.showConfirmDialog(this, "Do you want to save the changes ?", "Save", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                String temp=null,temp1=null;
                Object[] chageddata=createValue();
                for(int i=0;i<initialdata.length;i++){
                   if(!(initialdata[i]==null && chageddata[i]==null) &&( initialdata[i]==null || chageddata[i]==null || !initialdata[i].toString().equals(chageddata[i]))){
                      if(temp==null)
                          temp=labels[i]+" : "+String.valueOf(initialdata[i])+" : "+String.valueOf(chageddata[i]);
                      else
                          temp+=" # "+labels[i]+" : "+String.valueOf(initialdata[i])+" : "+String.valueOf(chageddata[i]);
                   }/*else {
                      if(temp==null)
                          temp=chageddata[i].toString();
                      else
                          temp+=" # "+chageddata[i];
                   }*/
                }
                deplist=mdmodel.getDependentList();
                for(MemberDependentTableModel.Dependentline dline:deplist){
                  if(dline.getStatus()!=null){
                      if(dline.getStatus().equals("new entry"))
                         if(temp1==null)
                            temp1="new : "+String.valueOf(dline.getdname())+":"+String.valueOf(dline.gettype())+":"+String.valueOf(dline.getDob())+":"+String.valueOf(dline.getDoj());
                         else
                            temp1+=" # "+"new : "+String.valueOf(dline.getdname())+":"+String.valueOf(dline.gettype())+":"+String.valueOf(dline.getDob())+":"+String.valueOf(dline.getDoj());
                      else if(dline.getStatus().equals("request for deactivation"))
                         if(temp1==null)
                            temp1="deactivate : "+String.valueOf(dline.getdname())+" : "+String.valueOf(dline.getID());
                         else
                            temp1+=" # "+"deactivate : "+String.valueOf(dline.getdname())+" : "+String.valueOf(dline.getID());
                      else if(dline.getStatus().equals("edited")){
                          int flag1=0;
                          MemberDependentTableModel.Dependentline dline1=null;
                          for(int i=0;i<odeplist.size();i++){
                              dline1=odeplist.get(i);
                              if(dline1.getID().equals(dline.getID())){
                                  flag1=1;
                                  break;
                              }
                          }
                           String temp2=null;
                        if(dline1!=null && flag1==1){
                          //if(String.valueOf(dline1.getdname()).equals(String.valueOf(dline.getdname())))
                           // if(temp2==null)
                          temp2=String.valueOf(dline.getID());
                          temp2+=" : "+"Name : "+String.valueOf(dline.getdname())+" : "+String.valueOf(dline1.getdname());
                          //  else
                            //     temp2+=" : "+"Name : "+String.valueOf(dline.getdname())+" : "+String.valueOf(dline1.getdname());
                          if(!String.valueOf(dline1.gettype()).equals(String.valueOf(dline.gettype())))
                                temp2+=" : "+"Type : "+String.valueOf(dline1.gettype())+":"+String.valueOf(dline.gettype());
                          if(!String.valueOf(dline1.getDoj()).equals(String.valueOf(dline.getDoj())))
                                temp2+=" : "+"DOJ : "+String.valueOf(dline1.getDoj())+" : "+String.valueOf(dline.getDoj());
                           if(!String.valueOf(dline1.getDob()).equals(String.valueOf(dline.getDob())))
                                temp2+=" : "+"DOB : "+String.valueOf(dline1.getDob())+" : "+String.valueOf(dline.getDob());
                        }
                          if(temp2!=null){
                             if(temp1==null)
                                 temp1="edited : "+temp2;
                             else
                                 temp1+=" # "+"edited : "+temp2;
                          }
                      }
                   }
                }
                String uid=null;
                if(user.getMemid()==null)
                    uid=user.getId();
                else
                    uid=user.getMemid();
                Object[] param=new Object[]{UUID.randomUUID().toString(),new Date(),user.getMemid(),temp,temp1,uid};
                new StaticSentence(m_App.getSession(), "INSERT INTO MEMPROFILEEDIT (ID,DATE,CUSTOMER,CHANGEDDATA,DEPDATA,INITIATOR) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING})
                        ).exec(param);
             }
            }else{
               JOptionPane.showMessageDialog(this, "A Profile Edit Request is already under processing", "Sorry cannot send profile edit request", JOptionPane.WARNING_MESSAGE);
            }
            } catch (BasicException ex) {
                Logger.getLogger(CustomersView1.class.getName()).log(Level.SEVERE, null, ex);
            }
       // }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
        int row=jTable1.getSelectedRow();
         if(row>=0){
                deplist=mdmodel.getDependentList();
                MemberDependentTableModel.Dependentline dline = mdmodel.getDependentList().get(row);
                DependentEditor de = DependentEditor.getDialog(this);
                Object[] obj = new Object[]{dline.getdname(), dline.getdno(), dline.getDob(), dline.getDoj(), dline.gettype()};
                de.showDialog(obj,dline.getID());
                if(de.isEdited()){
                   for(MemberDependentTableModel.Dependentline obj1:deplist){
                       if( obj1.getID().equals(dline.getID())){
                           deplist.remove(obj1);
                           if(obj1.getStatus()!=null && obj1.getStatus().equals("new entry")){
                            Object[] obj2=de.getEditedData();
                            Object[] values={obj2[0],obj2[1],obj2[2],obj2[3],obj2[4],obj2[5],"new entry"};
                            MemberDependentTableModel.Dependentline dline1=MemberDependentTableModel.Dependentline.CreateInstance(values);
                            deplist.add(dline1);
                          }else{
                            Object[] obj2=de.getEditedData();
                            Object[] values={obj2[0],obj2[1],obj2[2],obj2[3],obj2[4],obj2[5],"edited"};
                            MemberDependentTableModel.Dependentline dline1=MemberDependentTableModel.Dependentline.CreateInstance(values);
                            deplist.add(dline1);
                          }  
                           /*for(Object[] obj2:newdeplist){
                              if(obj2[5].toString().equals(dline.getID())){
                                  newdeplist.remove(obj2);
                                  newdeplist.add(de.getEditedData());
                                  break;
                              }
                           }*/
                           break;

                       }

                   }
                   mdmodel.setDependentList(deplist);
                   jTable1.setModel(mdmodel.getTableModel());
                }
           }
        } catch (BasicException ex) {
                ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here: delete
        int row=jTable1.getSelectedRow();
        if(row>=0){
         if(JOptionPane.showConfirmDialog(this, "Do you want to deactivate the dependent ?", "Deactivate the dependent", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
           MemberDependentTableModel.Dependentline dline=mdmodel.getDependentList().get(row);
           deplist=mdmodel.getDependentList();
           if(dline.getStatus()!=null && dline.getStatus().equals("new entry")){
              deplist.remove(dline);
           }else{
              deplist.remove(dline);
              dline.setStatus("request for deactivation");
              deplist.add(dline);
           }
           mdmodel.setDependentList(deplist);
           jTable1.setModel(mdmodel.getTableModel());
         }
        }
    }//GEN-LAST:event_jButton7ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox date;
    private javax.swing.JComboBox ddate;
    private javax.swing.JTextField ddoj;
    private javax.swing.JComboBox dmonth;
    private javax.swing.JTextField dname;
    private javax.swing.JTextField dnum;
    private javax.swing.JComboBox dtypelist;
    private javax.swing.JTextField dyear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jcard;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jTaxID;
    private javax.swing.JTextField mobile;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField searchkey;
    private javax.swing.JTextField sowo;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhone2;
    private javax.swing.JTextField txtPostal;
    private javax.swing.JTextField txtRegion;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables


    
}
