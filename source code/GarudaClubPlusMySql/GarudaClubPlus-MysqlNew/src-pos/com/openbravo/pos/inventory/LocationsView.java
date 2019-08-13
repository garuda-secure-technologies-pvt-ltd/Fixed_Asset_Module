package com.openbravo.pos.inventory;

import java.awt.Component;
import java.util.UUID;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;                         
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author adrianromero
 */
public class LocationsView extends javax.swing.JPanel implements EditorRecord {

    // private DirtyManager m_Dirty = new DirtyManager();    
    private String m_sID;
    private DataLogicSales dlSales;
    private ComboBoxValModel wmodel;
    private boolean stkStatus;
    private boolean decimalReqd;
    private ComboBoxValModel accmodel;
    private ComboBoxValModel accmodel1;
    private ComboBoxValModel accmodel2;
    private DataLogicFacilities dlfac;
    private ComboBoxValModel facmodel;
 

    /** Creates new form LocationsEditor */
    public LocationsView(DirtyManager dirty) {
        initComponents();
        dlSales = (DataLogicSales) LookupUtilityImpl.getInstance(null).getAppView().getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) LookupUtilityImpl.getInstance(null).getAppView().getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_jName.getDocument().addDocumentListener(dirty);
         jTextField2.getDocument().addDocumentListener(dirty);
        m_jAddress.getDocument().addDocumentListener(dirty);
        jTextField1.getDocument().addDocumentListener(dirty);
        jLabel1.setText("Display Name");
        jLabel2.setText("Name");
        jLabel3.setText("Address");
        jLabel4.setText("Parent");
        jLabel5.setText("Maintain Stock");
        jCheckBox1.setText("Yes");
        jCheckBox1.addActionListener(dirty);
        accmodel = new ComboBoxValModel();
        accmodel1 = new ComboBoxValModel();
        accmodel2 = new ComboBoxValModel();
        facmodel = new ComboBoxValModel();
        load();
        jComboBox1.addActionListener(dirty);
        salesaccount.addActionListener(dirty);
        customeraccount.addActionListener(dirty);
        levyaccount.addActionListener(dirty);
        levyvalue.getDocument().addDocumentListener(dirty);
        facility.addActionListener(dirty);
        jCheckBox2.setText("Yes");
        jCheckBox2.addActionListener(dirty);
        jTextField2.setColumns(2);
        jTextField2.setSize(2, 1);
        writeValueEOF();
    }

    public void writeValueEOF() {
        m_sID = null;
        m_jName.setText(null);
        jTextField2.setText(null);
        m_jAddress.setText(null);
        jTextField1.setText(null);
        m_jName.setEnabled(false);
        m_jAddress.setEnabled(false);
        jTextField1.setEnabled(false);
         jTextField2.setColumns(2);
          jTextField2.setSize(2, 1);
        wmodel.setSelectedKey(null);
        jComboBox1.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jCheckBox1.setSelected(false);
        jCheckBox2.setEnabled(false);
        jCheckBox2.setSelected(false);
        accmodel.setSelectedKey(null);
        salesaccount.setEnabled(false);
        accmodel1.setSelectedKey(null);
        customeraccount.setEnabled(false);
        accmodel2.setSelectedKey(null);
        levyaccount.setEnabled(false);
        levyvalue.setText(null);
        facmodel.setSelectedKey(null);
        facility.setEnabled(false);
    //jComboBox1.sets
    }

    public void writeValueInsert() {
//        try{
//           m_sID =  dlSales.getCategoriesbyname(m_jName.getText());
//        }
//        catch(Exception e){
//        }
        load();
        m_sID = null;
        m_jName.setText(null);
        m_jAddress.setText(null);
        jTextField1.setText(null);
        jTextField1.setEnabled(true);
        jTextField2.setText(null);
        jTextField2.setEnabled(true);
        jTextField2.setColumns(2);
         jTextField2.setSize(2, 1);
        m_jName.setEnabled(true);
        m_jAddress.setEnabled(true);
        wmodel.setSelectedKey(null);
        jComboBox1.setEnabled(true);
        jCheckBox1.setEnabled(true);
        jCheckBox1.setSelected(true);
        jCheckBox2.setEnabled(true);
        jCheckBox2.setSelected(false);
        stkStatus = true;
        decimalReqd = false;
        accmodel.setSelectedKey(null);
        salesaccount.setEnabled(true);
        accmodel1.setSelectedKey(null);
        customeraccount.setEnabled(true);
        accmodel2.setSelectedKey(null);
        levyaccount.setEnabled(true);
        levyvalue.setText(null);
        facmodel.setSelectedKey(null);
        facility.setEnabled(true);
    }

    public void writeValueDelete(Object value) {

        Object[] location = (Object[]) value;
        m_sID = Formats.STRING.formatValue(location[0]);
        m_jName.setText(Formats.STRING.formatValue(location[1]));
        m_jAddress.setText(Formats.STRING.formatValue(location[2]));
        jTextField1.setText(Formats.STRING.formatValue(location[3]));
        m_jName.setEnabled(false);
        m_jAddress.setEnabled(false);
        jTextField1.setEnabled(false);
        wmodel.setSelectedKey(location[4]);
        jComboBox1.setEnabled(false);
        stkStatus = Boolean.valueOf(String.valueOf(location[5]));
        jCheckBox1.setSelected(stkStatus);
        //jCheckBox1.setSelected(Boolean.valueOf(String.valueOf(location[5])));
        jCheckBox1.setEnabled(false);
        accmodel.setSelectedKey(location[7]);
        salesaccount.setModel(accmodel);
        salesaccount.setEnabled(false);
        accmodel1.setSelectedKey(location[8]);
        customeraccount.setEnabled(false);
        accmodel2.setSelectedKey(location[9]);
        levyaccount.setEnabled(false);
        levyvalue.setText(Formats.STRING.formatValue(location[10]));
        levyvalue.setEnabled(false);
        facmodel.setSelectedKey(location[11]);
        facility.setEnabled(false);
        decimalReqd = Boolean.valueOf(String.valueOf(location[12]));
        jCheckBox2.setSelected(decimalReqd);        
        jCheckBox2.setEnabled(false);

    }

    public void writeValueEdit(Object value) {
        Object[] location = (Object[]) value;
        m_sID = Formats.STRING.formatValue(location[0]);
        m_jName.setText(Formats.STRING.formatValue(location[1]));
        m_jAddress.setText(Formats.STRING.formatValue(location[2]));
       
        jTextField1.setText(Formats.STRING.formatValue(location[3]));
        m_jName.setEnabled(true);
        m_jAddress.setEnabled(true);
        jTextField2.setEnabled(true);
        jTextField1.setEnabled(true);
        //if(location[4]!=null)
        wmodel.setSelectedKey(location[4]);
        jComboBox1.setEnabled(true);
        stkStatus = Boolean.valueOf(String.valueOf(location[5]));
        jCheckBox1.setSelected(stkStatus);
        jCheckBox1.setEnabled(true);
        accmodel.setSelectedKey(location[7]);
        salesaccount.setSelectedItem(accmodel.getSelectedItem());
        //salesac
        salesaccount.setEnabled(true);
        accmodel1.setSelectedKey(location[8]);
        customeraccount.setEnabled(true);
        accmodel2.setSelectedKey(location[9]);
        levyaccount.setEnabled(true);
        if (location[10] != null) {
            levyvalue.setText(String.valueOf(location[10]));
        }
        levyvalue.setEnabled(true);
        facmodel.setSelectedKey(location[11]);
        facility.setEnabled(true);
        decimalReqd = Boolean.valueOf(String.valueOf(location[12]));
          jTextField2.setText(Formats.STRING.formatValue(location[13]));
        jCheckBox2.setSelected(decimalReqd);
        jCheckBox2.setEnabled(true);
        
        //jTextField2.setText(location[13]);
    }
    
    
    public Object createValue() throws BasicException {
        Object[] location = new Object[14];
        try {
            AppView app = LookupUtilityImpl.getInstance(null).getAppView();
            if (m_sID == null) {
                m_sID = dlSales.getCategoriesbyname(m_jName.getText());
                if (m_sID == null) {
                    m_sID = UUID.randomUUID().toString();
                    JOptionPane.showMessageDialog(this, "A Root Product Category will be created with the same name", "Message", JOptionPane.INFORMATION_MESSAGE);
                    new PreparedSentence(app.getSession(), "INSERT INTO CATEGORIES(ID,NAME,LOCATION) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{m_sID, m_jName.getText(), m_sID});
                }
            } else {
                String name = dlSales.getCategorieNamebyID(m_sID);
                if (!name.equals(m_jName.getText())) {
                    new PreparedSentence(app.getSession(), "UPDATE CATEGORIES SET NAME=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{m_jName.getText(), m_sID});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        location[0] = m_sID;
        location[1] = m_jName.getText();
        location[2] = m_jAddress.getText();
        location[3] = jTextField1.getText();
        location[4] = wmodel.getSelectedKey();

        if (stkStatus == true && stkStatus != jCheckBox1.isSelected()) {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            Object[] obj = (Object[]) new PreparedSentence(m_App.getSession(), "SELECT SUM(STK),SUM(PUR) FROM (SELECT COUNT(S.ID) AS STK,0.0 AS PUR FROM PRODUCTS P JOIN PDT_PRCAT PR  ON P.ID=PR.ID AND PR.CATEGORY=? JOIN STOCKDIARY S ON  S.PRODUCT=P.ID OR S.PRODUCT1=P.ID union All " +
                    " SELECT 0.0  AS STK,COUNT(P.ID) AS PUR FROM PRODUCTS P JOIN PDT_PRCAT PR  ON P.ID=PR.ID AND PR.CATEGORY=? JOIN PURCHASEJOURNAL PJ ON PJ.ITEM=P.ID) as stscheck", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.INT, Datas.INT})).find(new Object[]{m_sID, m_sID});
            if (obj != null) {
                int cnt1 = Integer.valueOf(String.valueOf(obj[0]));
                int cnt = Integer.valueOf(String.valueOf(obj[1]));
                if (cnt > 0 || cnt1 > 0) {
                    location[5] = stkStatus;
                    JOptionPane.showMessageDialog(null, "Sorry Maintain stock cannot be set to false", "Entry exists in purchasejournal or stockdiary", JOptionPane.WARNING_MESSAGE);
                } else {
                    location[5] = jCheckBox1.isSelected();
                }
            } else {
                location[5] = jCheckBox1.isSelected();
            }
        } else {
            location[5] = jCheckBox1.isSelected();
        }
        //praveen:added to fill primay parent
        if (wmodel.getSelectedItem() != null) {
            location[6] = ((LocationInfo) wmodel.getSelectedItem()).getParentID();
        }        
        if (salesaccount.getSelectedItem() != null) {
            location[7] = ((AccountMasterExt) salesaccount.getSelectedItem()).getid();
        }
        if (customeraccount.getSelectedItem() != null) {
            location[8] = ((AccountMasterExt) customeraccount.getSelectedItem()).getid();
        }
        if (levyaccount.getSelectedItem() != null) {
            location[9] = ((AccountMasterExt) levyaccount.getSelectedItem()).getid();
        }
        if (levyvalue.getText().length() > 0 && levyvalue.getText() != null) {
            location[10] = Integer.parseInt(levyvalue.getText());
        } else {
            location[10] = 0;
        }
        System.out.println(salesaccount.getSelectedItem() + "----selec");
        System.out.println(facmodel.getSelectedItem() + "----selec");
        if (facmodel.getSelectedItem() != null) {
            location[11] = ((Facility) facmodel.getSelectedItem()).getid();
        }
        location[12] = jCheckBox2.isSelected();
        //location[13]="za";
         location[13] =jTextField2.getText().toUpperCase();
        m_sID = null;
        return location;
    }

    public Component getComponent() {
        return this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        m_jAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        salesaccount = new javax.swing.JComboBox();
        customeraccount = new javax.swing.JComboBox();
        levyaccount = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        levyvalue = new javax.swing.JTextField();
        facility = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        jLabel2.setText(AppLocal.getIntString("label.locationname")); // NOI18N

        jLabel3.setText(AppLocal.getIntString("label.locationaddress")); // NOI18N

        jLabel1.setText("jLabel1");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jCheckBox1.setText("jCheckBox1");

        jLabel6.setText("SalesAccount");

        jLabel7.setText("CustomerCurrentAcc.");

        jLabel8.setText("Levy account");

        jLabel9.setText("Levy %");

        jLabel10.setText("Facility");

        jLabel11.setText("Decimal Reqd.");

        jCheckBox2.setText("jCheckBox2");

        jLabel12.setText("ProductPrefix");

        jLabel13.setText("Enter two Alphabets");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(m_jName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(m_jAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(salesaccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(customeraccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(levyaccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(facility, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jCheckBox1)
                        .add(19, 19, 19)
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jCheckBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel12)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jTextField2)
                            .add(levyvalue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
                .addContainerGap(36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(m_jName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(m_jAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(salesaccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(customeraccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(levyaccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(facility, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBox1)
                    .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBox2))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(levyvalue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel13)))
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel12)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox customeraccount;
    private javax.swing.JComboBox facility;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox levyaccount;
    private javax.swing.JTextField levyvalue;
    private javax.swing.JTextField m_jAddress;
    private javax.swing.JTextField m_jName;
    private javax.swing.JComboBox salesaccount;
    // End of variables declaration//GEN-END:variables

    private void load() {
        StringBuffer parentString = new StringBuffer();
        try {
            //praveen:to get  root warehouses
            List<LocationInfo> list1 = dlSales.getParentLocationsList();
            //System.out.println(parentString);
            if (list1 != null && list1.size() > 0) {
                List<LocationInfo> list = dlSales.getChildLocationsList(list1);
                list1.addAll(list);
            }
            list1.add(0, null);
            wmodel = new ComboBoxValModel(list1);

            List<AccountMasterExt> acc = dlfac.getaccounts();
            acc.add(0, null);

            accmodel = new ComboBoxValModel(acc);
            accmodel1 = new ComboBoxValModel(acc);
            accmodel2 = new ComboBoxValModel(acc);
            jComboBox1.setModel(wmodel);
            salesaccount.setModel(accmodel);
            customeraccount.setModel(accmodel1);
            levyaccount.setModel(accmodel2);
            List<Facility> facList = dlfac.getFacility();
            facmodel = new ComboBoxValModel(facList);
            facility.setModel(facmodel);
        } catch (BasicException ex) {
            ex.printStackTrace();
            wmodel = new ComboBoxValModel();
        }
    }
}
