package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.util.Hashcypher;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class CustomerPassword extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicFacilities dmang;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private GeneratePassword genpass;
    int i=1;

    public CustomerPassword() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        mno = new javax.swing.JTextField();
        mname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jRadioButton1.setText("All");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                AllStateChanged(evt);
            }
        });

        jRadioButton2.setText("Individual");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                IndividualStateChanged(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        mno.setName("mno"); // NOI18N
        mno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnoKeyPressed(evt);
            }
        });

        mname.setName("mname"); // NOI18N
        mname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnameActionPerformed(evt);
            }
        });

        jLabel1.setText("Member  No  ");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Member Name");
        jLabel2.setName("jLabel2"); // NOI18N

        jButton1.setText("Generate");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Generate");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig32.png"))); // NOI18N
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
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mno, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jButton1))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2)
                            .addComponent(jLabel1)
                            .addComponent(mno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jButton3)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(mno.getText().toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(mno.getText().toString().toUpperCase());
                    System.out.println(customerInfo.getSearchkey());
                    mname.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mname.setText(null);
            customerInfo = null;
        }

    }//GEN-LAST:event_mnoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String m = null;
        try {
            if (JOptionPane.showConfirmDialog(this, "Do you want generate password to all member", "Confirm password generation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                List<CustomerPasswordInfo> cpinfo = getCustomerIds();
                m = generatePasswordToAll(cpinfo);
            } else {
                jRadioButton1.setSelected(false);
                jButton1.setVisible(false);
            }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
        if ("success".equals(m)) {
            JOptionPane.showMessageDialog(this, "Password generated successfully.");
            jRadioButton1.setSelected(false);
            jButton1.setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String m = null;
        try {
            //mno.getText().toUpperCase();
            if (customerInfo != null && customerInfo.getId() != null) {
                if (JOptionPane.showConfirmDialog(this, "Do you want generate password to this member", "Confirm password generation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    String m1 = customerInfo.getSearchkey();
                    Object[] o = getCustomerId(m1);
                    for (int i = 0; i < o.length; i++) {
                        customerInfo.setId((String) o[0]);
                    }
                    String id = customerInfo.getId();
                    String phone = (String) o[1];
                    String name = (String) o[2];
                    //genpass = new GeneratePassword();
                    m = generatePasswordForIndividual(id, phone, name);
                } else {
                    jRadioButton2.setSelected(false);
                    jButton2.setVisible(false);
                    jButton3.setVisible(false);
                    jLabel1.setVisible(false);
                    jLabel2.setVisible(false);
                    mno.setVisible(false);
                    mname.setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select member to generate password.");
            }

        } catch (BasicException ex) {
            ex.printStackTrace();
        }
        if ("success".equals(m)) {
            JOptionPane.showMessageDialog(this, "Password generated successfully.");
            jRadioButton2.setSelected(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            mno.setVisible(false);
            mname.setVisible(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:       
        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname.setText(customerInfo.toString());
                mno.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public List<CustomerPasswordInfo> getCustomerIds() throws BasicException {
        List<CustomerPasswordInfo> cplist = new PreparedSentence(m_App.getSession(),
                "SELECT ID,MOBILE,NAME FROM CUSTOMERS ORDER BY SEARCHKEY",
                null, new SerializerReadClass(CustomerPasswordInfo.class)).list();
        return cplist;
    }

    public Object[] getCustomerId(String sKey) throws BasicException {
        Object[] stacc = (Object[]) new StaticSentence(m_App.getSession(),
                "SELECT ID,MOBILE,NAME FROM CUSTOMERS WHERE SEARCHKEY=? ",
                SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(sKey);
        return stacc;
    }

    public String generatePasswordToAll(List<CustomerPasswordInfo> cust) throws BasicException {
        String msg = null;
        for (CustomerPasswordInfo c : cust) {
            generatePasswordTrans(c.getCustId(),c.getPhoneNo(), c.getCName());
        }
        
        msg = "success";
        return msg;
    }

    public String generatePasswordForIndividual(String custid, String phone, String cname) throws BasicException {
        String msg = null;
        generatePasswordTrans(custid, phone, cname);
        msg = "success";
        return msg;
    }

    public void updateCustomers(String custid, String password) throws BasicException {
        new PreparedSentence(m_App.getSession(), "UPDATE CUSTOMERS SET PASSWORD=?,PASSREST=FALSE WHERE ID=?",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{password, custid});
    }

    public void generatePasswordTrans(String memid, String mobile, String name) throws BasicException {
        java.sql.Date d = new java.sql.Date(new Date().getTime());
        String password1 = UUID.randomUUID().toString().substring(0, 8);        
        String password = Hashcypher.hashString(password1);
        new PreparedSentence(m_App.getSession(), "INSERT INTO PASSWORDLOG(ID,CUSTID,PASSWORD,CRDATE,CREATEDBY) VALUES(?,?,?,?,?)",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), memid, password, d, m_App.getAppUserView().getUser().getName()});
        updateCustomers(memid, password);
        String msg = "Dear Member,\rYour new password is " + password1 +  " generated on "+new Date()+".\r Link http://localhost:8080/busc/ \r Accesable only in club premisis";
        if (mobile != null && mobile.length() == 10) {
            dmang.updatetosendMsg(msg, memid, mobile, 2);            
        }
        else {
            System.out.println(memid);
        }
    }

    /*public void sendSMSToMember( String msg,String id,String mobile,int pri) throws BasicException{
    dmang.updatetosendMsg(msg, id, mobile, 2);
    }*/
    private void AllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_AllStateChanged
        // TODO add your handling code here:
       
        if (jRadioButton1.isSelected()) {
             reset();
            jButton1.setVisible(true);
            jRadioButton2.setSelected(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            mno.setVisible(false);
            mname.setVisible(false);            
        }
    }//GEN-LAST:event_AllStateChanged

    private void IndividualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_IndividualStateChanged
        // TODO add your handling code here:
       
        if (jRadioButton2.isSelected()) {            
            jRadioButton1.setSelected(false);
            jButton1.setVisible(false);
            jButton2.setVisible(true);
            jButton3.setVisible(true);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            mno.setVisible(true);
            mname.setVisible(true);
        }
    }//GEN-LAST:event_IndividualStateChanged

    private void mnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnameActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField mname;
    private javax.swing.JTextField mno;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        loadData();

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");

    //m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }

    private void loadData() throws BasicException {
        // jRadioButton1.setSelected(true);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        mno.setVisible(false);
        mname.setVisible(false);
        reset();
    }

    private void reset() {
        mno.setText(null);
        mname.setText(null);

    }

    public Object getBean() {
        return this;
    }
}