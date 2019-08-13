/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OBAdjustment.java
 *
 * Created on Jul 2, 2009, 12:24:59 PM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.data.loader.DataRead;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.OBSource;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class OBAdjustment extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form OBAdjustment */
    //  private CustomerInfo customerInfo;
    private DataLogicFacilities dmang;
    private Timestamp initialobdate;
    private AppView m_App;
    private AccountMaster acc;
    private List<Object[]> oblist;
    private List<OBData> list;
    private Double initialOB;
    private JTextComponent editor;
    private ComboBoxValModel accmodel;

    public OBAdjustment() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jComboBox1.setEditable(true);
        editor = (JTextComponent) jComboBox1.getEditor().getEditorComponent();
        editor.setText(null);
        editor.addKeyListener(new Comboboxlistenner());
        jButton1.setText("Date");
        pob.setEditable(false);
        pob_date.setEditable(false);
        cob_date.setEditable(false);
        jComboBox2.setModel(new DefaultComboBoxModel(new Object[]{
                    "Debit", "Credit"
                }));
        jButton3.setText("Financial Year OB");
    }

    public String getTitle() {
        return null;
    }

    private void load() {
        initialOB = 0.0;
        editor.setText(null);
        oblist = new ArrayList<Object[]>();
        acc = null;
        pob.setText(null);
        jLabel3.setText(null);
        pob_date.setText(null);
        cob.setText(null);
        cob_date.setText(null);
        jButton2.setEnabled(false);
    }

    public void activate() throws BasicException {
        accmodel = new ComboBoxValModel(dmang.getsubAccountsExceptOB(""));
        jComboBox1.setModel(accmodel);
        jComboBox1.setSelectedIndex(-1);
        load();
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

    private boolean isAlpha(String s) {
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            int c = (int) s.charAt(i);
            if ((c < 65 || c > 90) && (c < 47 || c > 58)) {
                return false;
            }
        }
        return true;
    }

    public class Comboboxlistenner extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            try {
                String text = editor.getText();
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (accmodel.getSize() <= 0) {
                        editor.setText(null);
                    }
                } else if (isAlpha(String.valueOf(e.getKeyChar())) || e.getKeyText(e.getKeyCode()).equals("Backspace")) {//|| !e.getKeyText(e.getKeyCode()).equals("Backspace")){
                    accmodel = new ComboBoxValModel(dmang.getsubAccountsExceptOB(text.toUpperCase()));
                    jComboBox1.setModel(accmodel);
                    editor.setText(text);
                    jComboBox1.showPopup();
                    jButton2.setEnabled(false);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void getOB(String id) throws BasicException {
        initialOB = 0.0;
        File f = new File(".");
        Date d = new Date();
        List<Object[]> obj = new StaticSentence(m_App.getSession(), "SELECT AMOUNT,TRANSTYPE,BALANCEAMOUNT,DATE,TID FROM ACCOUNTJOURNAL WHERE   ACCOUNTID=? AND TRANSREF='Opening Balance' AND ACTIVE=1 ", new SerializerWriteBasic(new Datas[]{
                    Datas.STRING
                }), new SerializerReadBasic(new Datas[]{
                    Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING
                })).list(new Object[]{
                    id
                });
        if (obj != null) {
            oblist = obj;
            for (Object[] obj1 : obj) {
                if (obj1[1].toString().equals("C")) {
                    initialOB -= Double.parseDouble(obj1[0].toString());
                //  presentOB-=Double.parseDouble(obj1[2].toString());
                } else if (obj1[1].toString().equals("D")) {
                    initialOB += Double.parseDouble(obj1[0].toString());
                //  presentOB+=Double.parseDouble(obj1[2].toString());
                }
                d = (Date) obj1[3];
            }
            //  Object obj1[]=obj.get(0);
            //  d=(Date)obj1[3];
            if (initialOB < 0) {
                initialOB = initialOB * -1;
                String t = new BigDecimal(initialOB).setScale(2,BigDecimal.ROUND_UP).toPlainString();
               // BigDecimal bg = new BigDecimal(initialOB);
               // pob.setText(String.valueOf(initialOB));
                pob.setText(t);
                jLabel3.setText("Credit");
            } else {
                String t = new BigDecimal(initialOB).setScale(2,BigDecimal.ROUND_UP).toPlainString();
              //  BigDecimal bg = new BigDecimal(initialOB);
              //  pob.setText(String.valueOf(initialOB));
                pob.setText(t);
                jLabel3.setText("Debit");
            }
            String datestr = Formats.DATE.formatValue(d);
            initialobdate = new Timestamp(d.getTime());
            pob_date.setText(datestr);
            cob_date.setText(datestr);
        }
    }

    private void update(String type, String atype, double newOB, double OB) throws BasicException {
        Object date = Formats.TIMESTAMP.parseValue(cob_date.getText());
        Date dtemp = (Date) Formats.TIMESTAMP.parseValue(cob_date.getText());
        boolean flag = true;
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.setTimeInMillis(initialobdate.getTime());
        cal1.setTimeInMillis(dtemp.getTime());
        //if(cal.equals(cal1)){
        //  flag=false;
        //}
        if (flag == true) {
            for (Object[] obj1 : oblist) {
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTJOURNAL SET DATE =? WHERE TID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.TIMESTAMP, Datas.STRING
                        })).exec(new Object[]{
                            Formats.TIMESTAMP.parseValue(cob_date.getText()), obj1[4].toString()
                        });
            }
            Date d = new Date();
            for (Object[] obj1 : oblist) {
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTJOURNAL SET DEACTDATE =?,DEACTBY=?,ACTIVE=FALSE WHERE TID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.TIMESTAMP, Datas.STRING, Datas.STRING
                        })).exec(new Object[]{
                            d, m_App.getAppUserView().getUser().getName(), obj1[4].toString()
                        });
            }
            if (newOB >= 1) {
                String tid = UUID.randomUUID().toString();
                Object[] value1 = new Object[]{
                    UUID.randomUUID().toString(), tid, date, type, "Opening Balance", "Opening bal", newOB, null, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Opening Balance", acc.getid(), newOB, null, d, true
                };
                dmang.insertintoaccjoutnal3(value1);
                Object[] value2 = new Object[]{
                    UUID.randomUUID().toString(), tid, date, atype, "Opening Balance", "Opening bal", newOB, null, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Opening Balance", "5", 0.0, null, d, true
                };
                dmang.insertintoaccjoutnal3(value2);
                double temp = 0;
                if (type.equals("C")) {
                    temp = OB * -1;
                } else {
                    temp = OB;
                }
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTMASTER SET OPENINGBALANCE=?,DATEOFOPENINGBAL=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING
                        })).exec(new Object[]{
                            temp, date, acc.getid()
                        });
            } else {
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTMASTER SET DATEOFOPENINGBAL=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.TIMESTAMP, Datas.STRING
                        })).exec(new Object[]{
                            date, acc.getid()
                        });
            }
        }
    }

    private void updateNewOB(String type, String atype, double OB) throws BasicException {
        Object date = Formats.TIMESTAMP.parseValue(cob_date.getText());
        Date dtemp = (Date) Formats.TIMESTAMP.parseValue(cob_date.getText());
        boolean flag = true;
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.setTimeInMillis(initialobdate.getTime());
        cal1.setTimeInMillis(dtemp.getTime());
        String tid = null;
        if (flag == true) {
            if (OB >= 1) {
                tid = UUID.randomUUID().toString();
            }
            Date d = new Date();
            for (Object[] obj1 : oblist) {
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTJOURNAL SET DEACTDATE =?,DEACTBY=?,DEACTREF=?,ACTIVE=FALSE WHERE TID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING
                        })).exec(new Object[]{
                            d, m_App.getAppUserView().getUser().getName(),tid, obj1[4].toString()
                        });
            }
            if (OB >= 1) {
                //tid = UUID.randomUUID().toString();
                Object[] value1 = new Object[]{
                    UUID.randomUUID().toString(), tid, date, type, "Opening Balance", "Opening bal", OB, null, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Opening Balance", acc.getid(), OB, null, d, true
                };
                dmang.insertintoaccjoutnal3(value1);
                Object[] value2 = new Object[]{
                    UUID.randomUUID().toString(), tid, date, atype, "Opening Balance", "Opening bal", OB, null, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Opening Balance", "5", 0.0, null, d, true
                };
                dmang.insertintoaccjoutnal3(value2);
                double temp = 0;
                if (type.equals("C")) {
                    temp = OB * -1;
                } else {
                    temp = OB;
                }
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTMASTER SET OPENINGBALANCE=?,DATEOFOPENINGBAL=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING
                        })).exec(new Object[]{
                            temp, date, acc.getid()
                        });
            } else {
                new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTMASTER SET DATEOFOPENINGBAL=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{
                            Datas.TIMESTAMP, Datas.STRING
                        })).exec(new Object[]{
                            date, acc.getid()
                        });
            }
        }
    }

    private void treestructure(List<OBData> dlist, List<OBData> clist) throws BasicException {
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        List<String> templist = new ArrayList();
        int flag = 0;
        int obindex = 0;
        //String parentskey="";
        //double pdebit=0.0,pcredit=0.0;
        for (int i = 0; i < dlist.size(); i++) {
            OBData obd = dlist.get(i);//debit value
            if (obd.getID().equals("5")) {
                obindex = i;
            }
            OBData obc = clist.get(i);//credit value
            OBData obObj = new OBData(obd);
            double amt = obd.getAmount() - obc.getAmount();
            obObj.setAmount(amt);
            if (map1.get(obd.getParent()) != null) {
                // System.out.println(obd.getAccount());
                try {
                    int index = map1.get(obd.getParent());
                    OBData o = list.get(index);
                    o.addAmount(amt);
                    list.remove(index);
                    list.add(index, o);
                    OBData o1 = new OBData(o);
                    while (map1.get(o1.getParent()) != null) {
                          int inx = map1.get(o1.getParent());
                        o1 = new OBData(list.get(inx));
                        o1.addAmount(amt);
                        list.remove(inx);
                        list.add(inx, o1);
                    }
                } catch (Exception e) {
                }
            }

            map1.put(obd.getSearchkey(), i);
            list.add(obObj);
        //  }
        }
        list.remove(obindex);
    }

    private void obReportCalc() throws BasicException {
        List<OBData> dlist = new PreparedSentence(m_App.getSession(), "select am.name,sum(a.amount),am.searchkey,am.parent,am.summary,am.id from accountmaster am left outer join accountjournal a on am.id=a.accountid and a.transref='Opening Balance' and a.transtype='D'  group by am.id,am.name,am.searchkey,am.parent,am.summary order by am.searchkey ", null, new SerializerReadClass(OBData.class)).list();
        List<OBData> clist = new PreparedSentence(m_App.getSession(), "select am.name,sum(a.amount),am.searchkey,am.parent,am.summary,am.id from accountmaster am left outer join accountjournal a on am.id=a.accountid and a.transref='Opening Balance' and a.transtype='C'  group by am.id,am.name,am.searchkey,am.parent,am.summary order by am.searchkey ", null, new SerializerReadClass(OBData.class)).list();
        list = new ArrayList<OBData>();
        treestructure(dlist, clist);
    }

    public static class OBData implements SerializableRead {

        private String account;
        private double amount;
        private String skey;
        private String parent;
        private boolean summary;
        private String id;

        public OBData() {
        }

        public OBData(OBData o) {
            //OBData newdata=new OBData();
            this.account = o.account;
            this.amount = o.amount;
            this.parent = o.parent;
            this.skey = o.skey;
            this.summary = o.getSummary();
            this.id = o.getID();
        // return newdata;
        }

        public void readValues(DataRead dr) throws BasicException {
            account = dr.getString(1);
            if (dr.getDouble(2) == null) {
                amount = 0.0;
            } else {
                amount = dr.getDouble(2);
            }
            skey = dr.getString(3);
            parent = dr.getString(4);
            summary = dr.getBoolean(5);
            id = dr.getString(6);
        }

        public String getID() {
            return id;
        }

        public String getAccount() {
            return account;
        }

        public boolean getSummary() {
            return summary;
        }

        public String getSearchkey() {
            return skey;
        }

        public double getAmount() {
            return amount;
        }

        public String getParent() {
            return parent;
        }

        public void setAmount(double amt) {
            this.amount = amt;
        }

        public void addAmount(double amt) {
            this.amount = this.amount + amt;
        }

        public int getLevel() {
            /*String str1="1.1";
            String[] aar1=str1.split(".");
            String aar2[]=str1.split(".");
            String str=skey;*/
            String[] arr = skey.split("\\.");
            return arr.length;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        pob = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cob = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pob_date = new javax.swing.JTextField();
        cob_date = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("Change Opening Balance");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Account");
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel2.setText("Current Opening Balance");
        jLabel2.setName("jLabel2"); // NOI18N

        pob.setName("pob"); // NOI18N

        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Corrected Opening Balance");
        jLabel4.setName("jLabel4"); // NOI18N

        cob.setName("cob"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jLabel5.setText("Date");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Date");
        jLabel6.setName("jLabel6"); // NOI18N

        pob_date.setName("pob_date"); // NOI18N

        cob_date.setName("cob_date"); // NOI18N

        jButton1.setText("jButton1");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cob, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, 0, 75, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pob)
                                    .addComponent(jComboBox1, 0, 190, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cob_date)
                    .addComponent(pob_date, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(pob_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cob_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(301, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            double newOB = Double.parseDouble(cob.getText());
            double OB = newOB;
            String ctype = jComboBox2.getSelectedItem().toString();
            String type;
            String atype;
            if (jLabel3.getText().equals(ctype)) {
                if (ctype.equals("Credit")) {
                    type = "C";
                    atype = "D";
                } else {
                    type = "D";
                    atype = "C";
                }
                newOB = initialOB - newOB;
                if (newOB > 0) {
                    if (type.equals("C")) {
                        type = "D";
                        atype = "C";
                    } else {
                        type = "C";
                        atype = "D";
                    }
                } else {
                    newOB = newOB * -1;
                }
                //update(type, atype, newOB, OB);
                updateNewOB(type, atype, OB);
            } else {
                if (ctype.equals("Credit")) {
                    type = "C";
                    atype = "D";
                    newOB = newOB + initialOB;
                } else {
                    type = "D";
                    atype = "C";
                    newOB = newOB + initialOB;
                }
                //update(type, atype, newOB, OB);
                 updateNewOB(type, atype, OB);
            }
            activate();
        } catch (BasicException e) {
            JOptionPane.showMessageDialog(this, "Please Enter Correct Values", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        try {
            if (jComboBox1.getSelectedIndex() >= 0) {
                acc = (AccountMaster) accmodel.getSelectedItem();
                editor.setText(acc.getName());
                getOB(acc.getid());
                jButton2.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(cob_date.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            String temp = Formats.DATE.formatValue(date);
            cob_date.setText(temp);
            pob_date.setText(temp);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            obReportCalc();
            DataSourceProvider data1 = new DataSourceProvider(list);
            OBSource ds = new OBSource(list, false);
            data1.setDataSource(ds);
            Map reportparam = new HashMap();
            Session s = LookupUtilityImpl.getInstance(null).getAppView().getSession();
            reportparam.put("cname", s.getCompanyName());
            reportparam.put("caddress", s.getCompanyAddress());
            JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/OpeningBalance.jrxml", reportparam, false, data1, true, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cob;
    private javax.swing.JTextField cob_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField pob;
    private javax.swing.JTextField pob_date;
    // End of variables declaration//GEN-END:variables
}
