/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPanelMemberList.java
 *
 * Created on 20-Apr-2010, 10:23:44
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.DueListDataSource;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.clubmang.MemCat;
import com.openbravo.pos.clubmang.MemCat1;
import com.openbravo.pos.clubmang.MemCat2;
import com.openbravo.pos.clubmang.MemKey;
import com.openbravo.pos.clubmang.MemType;
import com.openbravo.pos.clubmang.MemberDataSource;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.clubmang.MemberDetails;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.sms.SmsSendernew;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class JPanelMemberList extends JPanel implements JPanelView, BeanFactoryApp{
    
    
    private List< MemberListReport .GetSetMethod> Member_List_all;
    private MemberListReport Member_List_Report;

    private ComboBoxValModel fModel;
    private ComboBoxValModel tmodel;
    private ComboBoxValModel catModel;
    private ComboBoxValModel taxmodel;
    private ComboBoxValModel cModel;
    private DataLogicFacilities dlfac;
    private DataLogicSales dlsales;
    private AppView m_app;
    private List<MemberDetails> details;
    private List<MemKey> mlist;
    private List<MemKey> mlist1;
    private List<MemCat2> mlist2;
    private List<MemCat1> mlist3;
    private List mlist4;
    
    private String name;
    private String memno;
    private String memtype;
    private String address;
    private String contactno;
    private Date doj;
    private String memtaxcat;
    private boolean visible;
    private Date doe;
    private Date dob;
    private String phone;
  
    List<Object> SelectedMemTypeList=new ArrayList<Object>();

    public JPanelMemberList() {

        initComponents();
    }
    public void init(AppView app) {
       m_app=app;
       dlfac=(DataLogicFacilities) m_app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       dlsales = (DataLogicSales) m_app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");

    }
    public void activate() throws BasicException {
       jPanel2.setVisible(false);
      jCheckBox11.setVisible(false);
      jCheckBox12.setVisible(false);
       jLabel16.setVisible(false);       
      jLabel17.setVisible(false);
       jRadioButton1.setSelected(true);
       
        jPanel3.setVisible(false);
       
       List mlist=dlfac.getMemberKey();
       mlist.add(1,"ALL");
       fModel=new ComboBoxValModel(mlist);
       jComboBox1.setModel(fModel);
       List<MemKey> mlist1=dlfac.getMemberKey();
       tmodel=new ComboBoxValModel(mlist1);
       jComboBox2.setModel(tmodel);
       List mlist2=dlfac.getMemberCategory2();
       mlist2.add(0,"ALL");
       catModel=new ComboBoxValModel(mlist2);
       jComboBox3.setModel(catModel);
       List taxcategorylist= dlsales.getTaxCategoriesList().list();
       taxmodel=new ComboBoxValModel(taxcategorylist);
       jComboBox4.setModel(taxmodel);
       jCheckBox1.setSelected(true);
       jLabel5.setVisible(false);
       jComboBox4.setVisible(false);
       
       
        List mlist4=dlfac.getMemberCategory2();
       mlist4.add(0,"ALL");
       cModel=new ComboBoxValModel(mlist4);
       jComboBox5.setModel(cModel);
       
       jRadioButton3.setSelected(true);
     }
    public String getTitle() {
         return AppLocal.getIntString("Label.MembersList");
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
     
    public JPanelMemberList(AppView app,String name,String memno,String memtype,String address,String contactno,Date doj,String memtaxcat,Boolean visible,Date doe) {
        this.name=name;
        this.memno=memno;
        this.memtype=memtype;
        this.address=address;
        this.contactno=contactno;
        this.doj=doj;
        this.memtaxcat=memtaxcat;
        this.visible=visible;
        this.doe=doe;
    }
    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox12 = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Membertype_JList = new javax.swing.JList();
        add_btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setName("jPanel1"); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Member Master");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Member's List");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Membership No.");
        jLabel3.setName("jLabel3"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jButton1.setText("Report");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setName("jRadioButton5"); // NOI18N
        jRadioButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton5ItemStateChanged(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel23.setText("Member Adress Details");
        jLabel23.setName("jLabel23"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton1, 0, 1, Short.MAX_VALUE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(jLabel3)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jRadioButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jRadioButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setName("jPanel2"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Membership Type");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Tax Category");
        jLabel5.setName("jLabel5"); // NOI18N

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Name");
        jLabel6.setName("jLabel6"); // NOI18N

        jCheckBox2.setText("jCheckBox2");
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        jCheckBox3.setText("jCheckBox3");
        jCheckBox3.setName("jCheckBox3"); // NOI18N

        jCheckBox4.setText("jCheckBox4");
        jCheckBox4.setName("jCheckBox4"); // NOI18N
        jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox4ItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Membership Type");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Contact No.");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Member Tax Category");
        jLabel9.setName("jLabel9"); // NOI18N

        jCheckBox5.setText("jCheckBox5");
        jCheckBox5.setName("jCheckBox5"); // NOI18N

        jCheckBox6.setText("jCheckBox6");
        jCheckBox6.setName("jCheckBox6"); // NOI18N

        jCheckBox7.setText("jCheckBox7");
        jCheckBox7.setName("jCheckBox7"); // NOI18N

        jCheckBox8.setText("jCheckBox8");
        jCheckBox8.setName("jCheckBox8"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Member No.");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Address");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("D.O.J");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("D.O.E");
        jLabel13.setName("jLabel13"); // NOI18N

        jCheckBox9.setText("jCheckBox9");
        jCheckBox9.setName("jCheckBox9"); // NOI18N

        jCheckBox10.setText("jCheckBox10");
        jCheckBox10.setName("jCheckBox10"); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Exit Reason");
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Visible Members");
        jLabel15.setName("jLabel15"); // NOI18N

        jCheckBox11.setText("jCheckBox11");
        jCheckBox11.setName("jCheckBox11"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Index On Membership Type");
        jLabel16.setName("jLabel16"); // NOI18N

        jCheckBox12.setText("jCheckBox12");
        jCheckBox12.setName("jCheckBox12"); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Index On Tax Category");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("D.O.B");
        jLabel18.setName("jLabel18"); // NOI18N

        jCheckBox13.setText("jCheckBox13");
        jCheckBox13.setName("jCheckBox13"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jCheckBox4, 0, 22, Short.MAX_VALUE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addComponent(jCheckBox2, 0, 1, Short.MAX_VALUE)
                        .addComponent(jCheckBox3, 0, 1, Short.MAX_VALUE)
                        .addComponent(jCheckBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addComponent(jCheckBox9, 0, 1, Short.MAX_VALUE))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel14))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCheckBox12, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jCheckBox10, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jCheckBox13, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jCheckBox8, javax.swing.GroupLayout.Alignment.LEADING, 0, 20, Short.MAX_VALUE)
                            .addComponent(jCheckBox7, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jCheckBox6, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jCheckBox12)
                    .addComponent(jCheckBox11))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox6)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox8)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel19.setText("Membership Type");
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel20.setText(" All");
        jLabel20.setName("jLabel20"); // NOI18N

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setName("jRadioButton3"); // NOI18N

        jLabel21.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel21.setText("Active Members");
        jLabel21.setName("jLabel21"); // NOI18N

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setName("jRadioButton4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Membertype_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        Membertype_JList.setName("Membertype_JList"); // NOI18N
        jScrollPane1.setViewportView(Membertype_JList);

        add_btn.setText("Add");
        add_btn.setName("add_btn"); // NOI18N
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        jButton2.setText("Remove");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jRadioButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(jRadioButton3))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(add_btn)
                        .addGap(35, 35, 35)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel19, jLabel20, jLabel21});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(add_btn))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jRadioButton3)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jRadioButton4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel19, jLabel20, jLabel21});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()==true){
            jPanel2.setVisible(false);
             jPanel3.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        // TODO add your handling code here:
        if(jRadioButton2.isSelected()==true){
            jPanel2.setVisible(true);
             jPanel3.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        //jCheckBox2.setVisible(false);
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        jCheckBox4.setVisible(false);
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String query=null;
        if(jRadioButton1.isSelected()==true)
        {
          if(jComboBox1.getSelectedItem().toString().equals("ALL")==true)
              {
                 try{
                List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
     //sameer: concatinating address,address2,city,pin into ADDRESS
                        "SELECT C.NAME,C.SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
               "C.VISIBLE,C.TERDATE,C.DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID ORDER BY SEARCHKEY "
           ,new SerializerWriteBasic(new Datas[]{})
            ,new SerializerReadClass(MemberDetails.class))
            .list(new Object[]{});
            //,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
            // .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
        }
            catch(Exception ex){
                ex.printStackTrace();
            }
            }

            else if(jComboBox1!=null && jComboBox2!=null)
            {
            try{
                List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
//sameer: concatinating address,address2,city,pin into ADDRESS
                        "SELECT C.NAME,C.SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
               "C.VISIBLE,C.TERDATE,C.DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID AND C.SEARCHKEY>=? AND C.SEARCHKEY<=? ORDER BY SEARCHKEY "
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
            ,new SerializerReadClass(MemberDetails.class))
                .list(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
          
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
        }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
           
        }
         //sameer:gave alias names a1,a2 to the tables wherever required
        else if(jRadioButton2.isSelected()==true)
        {
          if(jComboBox3.getSelectedItem().toString().equals("ALL"))
          {
          query="SELECT C.NAME AS NAME,C.SEARCHKEY AS SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
               "C.VISIBLE AS VISIBLE,C.TERDATE AS DOE,C.DOB AS DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID ORDER BY SEARCHKEY ";

          if(jCheckBox2.isSelected()==true && jCheckBox11.isSelected()==true)
            {
           query="SELECT C.NAME AS NAME,C.SEARCHKEY AS SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
                 "C.VISIBLE AS VISIBLE,C.TERDATE AS DOE,C.DOB AS DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID ORDER BY SEARCHKEY,TYPE_OF_MEMBER";
            }
           
           if(jCheckBox4.isSelected()==true && jCheckBox12.isSelected()==true)
            {
           query="SELECT C.NAME AS NAME,C.SEARCHKEY AS SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
                 "C.VISIBLE AS VISIBLE,C.TERDATE AS DOE,C.DOB AS DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID ORDER BY SEARCHKEY,MEMTYPE";
            }

            if(jCheckBox2.isSelected()==false && jComboBox3.getSelectedItem()==null)
            {
               query="SELECT NAME,SEARCHKEY,NULL AS TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1" ;
            }
             if(jCheckBox3.isSelected()==false)
            {
                query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,NULL AS CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1";
            }
            if(jCheckBox4.isSelected()==false)
            {               
                query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,NULL AS MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1";
            }
            if(jCheckBox5.isSelected()==false)
            {
               query="SELECT NAME,NULL AS SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1";
                
            }
            if(jCheckBox6.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,NULL AS ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1";
            }
             if(jCheckBox7.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,NULL AS DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1";
              }
            if(jCheckBox8.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,NULL AS DOE,DOB FROM("+query+") as a1";
            }
            if(jCheckBox13.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,NULL AS DOB FROM("+query+") as a1";
            }
            if(jCheckBox9.isSelected()==true)
            {
             query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a1 WHERE VISIBLE=TRUE" ;
            }
            if(jComboBox3.getSelectedItem()!=null)
            {
              if(jComboBox3.getSelectedItem().toString().equals("ALL"))
              {
                try{
             List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
               "SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a2"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
            ,new SerializerReadClass(MemberDetails.class))
                .list(new Object[]{(jComboBox3.getSelectedItem().toString())});

            //,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
             // .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
               }
            catch(Exception ex){
                ex.printStackTrace();
            }
              }
              else{
                  try{
             List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
               "SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a2 WHERE TYPE_OF_MEMBER=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
            ,new SerializerReadClass(MemberDetails.class))
                .list(new Object[]{(jComboBox3.getSelectedItem().toString())});

            //,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
             // .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
               }
            catch(Exception ex){
                ex.printStackTrace();
            }
              }
            }

            else{
           try{
                List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
             "SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a2"
            ,new SerializerWriteBasic(new Datas[]{})
            ,new SerializerReadClass(MemberDetails.class))
                .list(new Object[]{});
            //,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
             // .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
        }
            catch(Exception ex){
                ex.printStackTrace();
            }
            }
        
          }
          else if(jComboBox3.getSelectedItem()!=null)// && jComboBox4.getSelectedItem()!=null)
          {
            query="SELECT C.NAME AS NAME,C.SEARCHKEY AS SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
               "C.VISIBLE AS VISIBLE,C.TERDATE AS DOE,C.DOB AS DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID  ORDER BY SEARCHKEY ";

          if(jCheckBox2.isSelected()==true && jCheckBox11.isSelected()==true)
            {
           query="SELECT C.NAME AS NAME,C.SEARCHKEY AS SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL)AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
                 "C.VISIBLE AS VISIBLE,C.TERDATE AS DOE,C.DOB AS DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID ORDER BY SEARCHKEY,TYPE_OF_MEMBER";
            }
           if(jCheckBox4.isSelected()==true && jCheckBox12.isSelected()==true)
            {
           query="SELECT C.NAME AS NAME,C.SEARCHKEY AS SEARCHKEY,M.NAME AS TYPE_OF_MEMBER,C.JOINDATE AS DOJ,concat(C.ADDRESS,C.ADDRESS2,C.CITY,C.POSTAL) AS ADDRESS,C.MOBILE AS CONTACT_NO,NULL AS MEM_TAX_CATEGORY,"+
                 "C.VISIBLE AS VISIBLE,C.TERDATE AS DOE,C.DOB AS DOB FROM CUSTOMERS C,MEMTYPE M WHERE C.MEMTYPE=M.ID ORDER BY SEARCHKEY,MEMTYPE";
            }

            if(jCheckBox2.isSelected()==false && jComboBox3.getSelectedItem()==null)
            {
               query="SELECT NAME,SEARCHKEY,NULL AS TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+")as a1" ;
            }
             if(jCheckBox3.isSelected()==false)
            {
                query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,NULL AS CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+")as a1";
            }
            if(jCheckBox4.isSelected()==false)
            {
                query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,NULL AS MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+")as a1";
            }
            if(jCheckBox5.isSelected()==false)
            {
               query="SELECT NAME,NULL AS SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+")as a1";

            }
            if(jCheckBox6.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,NULL AS ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+") as a1";
            }
             if(jCheckBox7.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,NULL AS DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+") as a1";
              }
            if(jCheckBox8.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,NULL AS DOE,DOB AS DOB FROM("+query+") as a1";
            }
            if(jCheckBox13.isSelected()==false)
            {
               query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,NULL AS DOB FROM("+query+") as a1";
            }
            if(jCheckBox9.isSelected()==true)
            {
             query="SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB AS DOB FROM("+query+") as a1 WHERE VISIBLE=TRUE" ;
            }
            if(jComboBox3.getSelectedItem()!=null)
            {
              if(jComboBox3.getSelectedItem().toString().equals("ALL"))
              {
                try{
             List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
               "SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a2"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
            ,new SerializerReadClass(MemberDetails.class))
                .list(new Object[]{(jComboBox3.getSelectedItem().toString())});

            //,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
             // .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
               }
            catch(Exception ex){
                ex.printStackTrace();
            }
              }
              else{
                  try{
             List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
               "SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+")as a2 WHERE TYPE_OF_MEMBER=? ORDER BY SEARCHKEY "
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
            ,new SerializerReadClass(MemberDetails.class))
                .list(new Object[]{(jComboBox3.getSelectedItem().toString())});

            //,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
             // .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});
        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
               }
            catch(Exception ex){
                ex.printStackTrace();
            }
              }
            }
            else{
           try{
                List<MemberDetails> detail= (List<MemberDetails>) new StaticSentence(m_app.getSession(),
               "SELECT NAME,SEARCHKEY,TYPE_OF_MEMBER,DOJ,ADDRESS,CONTACT_NO,MEM_TAX_CATEGORY,VISIBLE,DOE,DOB FROM("+query+") as a2"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
            //,new SerializerReadClass(MemberDetails.class))
            //.list(new Object[]{(jComboBox3.getSelectedItem().toString())});
            ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.TIMESTAMP}))
            .find(new Object[]{(jComboBox1.getSelectedItem()).toString(),(jComboBox2.getSelectedItem()).toString()});

        if (detail == null) {
            details = new ArrayList<MemberDetails>();
        } else {
           details = detail;
        }
        }
            catch(Exception ex){
                ex.printStackTrace();
            }
            }

          }}  
        
        
        else if(jRadioButton5.isSelected()==true)
        {
            if(jComboBox5.getSelectedItem()!=null)
            {
                 if(jComboBox5.getSelectedItem().toString().equals("ALL"))
                 {
                    if(jRadioButton3.isSelected()==true)
                            {
                                
                            Member_List_all = new ArrayList<MemberListReport.GetSetMethod>();
                            
                             try {
                               Member_List_Report = MemberListReport.LoadAllMember(m_app);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(JPanelMemberList.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        Member_List_all  =  (List<MemberListReport.GetSetMethod>)  Member_List_Report.getMemberList();
                         
                        
                        DataSourceProvider data1 = new DataSourceProvider(Member_List_all);
                        DataSourceForMemberList dsfc = new DataSourceForMemberList(Member_List_all);
                        data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("companyName", m_app.getSession().getCompanyName());
                         reportparams.put("companyAddress", m_app.getSession().getCompanyAddress());
                         // Map reportparam=new HashMap();
                          //reportparams.put("column2",name);
                          
                          // reportparams.put("column6",address); 
                          // reportparams.put("column12",phone); 
                          // reportparams.put("column4",memtype);
                           reportparams.put("memtype",jComboBox5.getSelectedItem().toString());
                           JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/MemberReport3.jrxml", reportparams, false, data1, true, null);
                            
                            }else  if(jRadioButton4.isSelected()==true)
                            {
                                
                            Member_List_all = new ArrayList<MemberListReport.GetSetMethod>();
                            
                             try {
                               Member_List_Report = MemberListReport.LoadAllVisibleMembers(m_app);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(JPanelMemberList.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        Member_List_all  =  (List<MemberListReport.GetSetMethod>)  Member_List_Report.getMemberList();
                         
                        
                        DataSourceProvider data1 = new DataSourceProvider(Member_List_all);
                        DataSourceForMemberList dsfc = new DataSourceForMemberList(Member_List_all);
                        data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("companyName", m_app.getSession().getCompanyName());
                         reportparams.put("companyAddress", m_app.getSession().getCompanyAddress());
                         // Map reportparam=new HashMap();
                      //    reportparams.put("column2",name);
                         
                         //  reportparams.put("column6",address); 
                         //  reportparams.put("column12",phone);
                         //  reportparams.put("column4",memtype);
                           reportparams.put("memtype",jComboBox5.getSelectedItem().toString());
                           JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/MemberReport3.jrxml", reportparams, false, data1, true, null);
                            
                            }
                    }  else if(!jComboBox5.getSelectedItem().toString().equals("ALL"))
                    {
                        if(jRadioButton3.isSelected()==true)
                        {    
                            
                            String memty = "('";
                            
                            for(int i=0;i<SelectedMemTypeList.size();i++){ 
                            
                            // String memty=jComboBox5.getSelectedItem().toString();
                            
                             String Str =   SelectedMemTypeList.get(i).toString(); 
                             if(i==0){
                               memty=memty+SelectedMemTypeList.get(i).toString();  
                             }
                             else{
                               memty=memty+"'"+SelectedMemTypeList.get(i).toString();
                             }
                            
                             
                             
                             if((i+1)!=SelectedMemTypeList.size()){
                                 memty = memty+"'";
                                 memty = memty+",";
                             }
                            
                            }
                             memty = memty +"')";
                             System.out.println("Membershipt type selected : "+memty);
                             Member_List_all = new ArrayList<MemberListReport.GetSetMethod>();
                             try{
                                    Member_List_Report = MemberListReport. LoadAllMemberPerticularType(m_app,memty );
                             }
                             
                             catch (BasicException ex) {
                                 Logger.getLogger(JPanelMemberList.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                        Member_List_all  =  (List<MemberListReport.GetSetMethod>)  Member_List_Report.getMemberList();
                         
                        
                        DataSourceProvider data1 = new DataSourceProvider(Member_List_all);
                        DataSourceForMemberList dsfc = new DataSourceForMemberList(Member_List_all);
                        data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("companyName", m_app.getSession().getCompanyName());
                         reportparams.put("companyAddress", m_app.getSession().getCompanyAddress());
                         // Map reportparam=new HashMap();
                         // reportparams.put("column2",name);
                          
                         //  reportparams.put("column6",address); 
                          // reportparams.put("column12",phone); 
                         //  reportparams.put("column4",memtype);
                           reportparams.put("memtype",memty);
                           JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/MemberReport3.jrxml", reportparams, false, data1, true, null); 
                        } else if(jRadioButton4.isSelected()==true)
                        {     
                            // String memty=jComboBox5.getSelectedItem().toString();
                            
                            
                             String memty = "('";
                            
                            for(int i=0;i<SelectedMemTypeList.size();i++){ 
                            
                            // String memty=jComboBox5.getSelectedItem().toString();
                            
                             String Str =   SelectedMemTypeList.get(i).toString(); 
                             if(i==0){
                               memty=memty+SelectedMemTypeList.get(i).toString();  
                             }
                             else{
                               memty=memty+"'"+SelectedMemTypeList.get(i).toString();
                             }
                            
                             
                             
                             if((i+1)!=SelectedMemTypeList.size()){
                                 memty = memty+"'";
                                 memty = memty+",";
                             }
                            
                            }
                             memty = memty +"')";
                             System.out.println("Membershipt type selected : "+memty);
                            
                            
                            
                             Member_List_all = new ArrayList<MemberListReport.GetSetMethod>();
                             try{
                             Member_List_Report = MemberListReport.LoadAllVisibleMembersPerticularType(m_app,memty);
                             }
                             
                             catch (BasicException ex) {
                                 Logger.getLogger(JPanelMemberList.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        Member_List_all  =  (List<MemberListReport.GetSetMethod>)  Member_List_Report.getMemberList();
                         
                        
                        DataSourceProvider data1 = new DataSourceProvider(Member_List_all);
                        DataSourceForMemberList dsfc = new DataSourceForMemberList(Member_List_all);
                        data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                         reportparams.put("companyName", m_app.getSession().getCompanyName());
                         reportparams.put("companyAddress", m_app.getSession().getCompanyAddress());
                          //Map reportparam=new HashMap();
                         // reportparams.put("column2",name);
                          
                         //  reportparams.put("column6",address); 
                        //   reportparams.put("column12",phone); 
                         //  reportparams.put("column4",memtype);
                           reportparams.put("memtype",memty);
                           
                           JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/MemberReport3.jrxml", reportparams, false, data1, true, null); 
                        }
                   
                    
                    
                    
                    }
                 
                 }
            
       } 
        
        else{
                JOptionPane.showMessageDialog(this, "Please select Membership number range", "Error", JOptionPane.OK_OPTION);
               }
        
       if(jCheckBox9.isSelected()==true || jRadioButton2.isSelected()==true)
         {
          
            if(jComboBox3.getSelectedItem().toString().equals("ALL")){ 
                Map reportparam=new HashMap();
          //sameer adding company name and address
         
                reportparam.put("companyName",m_app.getSession().getCompanyName());
                reportparam.put("companyAddress",m_app.getSession().getCompanyAddress());
                reportparam.put("column2",name);
                reportparam.put("column3",memno);
                reportparam.put("column4",memtype);
                reportparam.put("column5",doj);
                reportparam.put("column6",address);
                reportparam.put("column7",memtype);
                reportparam.put("column8",memtaxcat);
                //reportparam.put("column9",visible);
                reportparam.put("column10",doe);
                reportparam.put("column11",dob);
                reportparam.put("memtype",jComboBox3.getSelectedItem().toString());
                //System.out.println(jComboBox3.getSelectedItem().toString());
                DataSourceProvider data1=new DataSourceProvider(details);
                MemberDataSource ds=new MemberDataSource(details);
                data1.setDataSource(ds);
                JasperPrint jp=JasperReportNew.runReport(m_app,"./reports/com/openbravo/reports/MemberReport1.jrxml", reportparam, false, data1, true,null);
            }
            else{
                
                Map reportparam=new HashMap();
          //sameer adding company name and address
         
                reportparam.put("companyName",m_app.getSession().getCompanyName());
                reportparam.put("companyAddress",m_app.getSession().getCompanyAddress());
                reportparam.put("column2",name);
                reportparam.put("column3",memno);
                reportparam.put("column4",memtype);
                reportparam.put("column5",doj);
                reportparam.put("column6",address);
                reportparam.put("column7",memtype);
                reportparam.put("column8",memtaxcat);
                //reportparam.put("column9",visible);
                reportparam.put("column10",doe);
                reportparam.put("column11",dob);
                reportparam.put("memtype",jComboBox3.getSelectedItem().toString());
                //System.out.println(jComboBox3.getSelectedItem().toString());
                DataSourceProvider data1=new DataSourceProvider(details);
                MemberDataSource ds=new MemberDataSource(details);
                data1.setDataSource(ds);
                JasperPrint jp=JasperReportNew.runReport(m_app,"./reports/com/openbravo/reports/MemberReport1_new.jrxml", reportparam, false, data1, true,null);
                
                
            }
         
         }
       else if(jRadioButton2.isSelected()==true)
     {
           if(jComboBox3.getSelectedItem().toString().equals("ALL")){  
         
                    Map reportparam=new HashMap();
                      //sameer:adding compny name and address
                     reportparam.put("companyName",m_app.getSession().getCompanyName());
                     reportparam.put("companyAddress",m_app.getSession().getCompanyAddress());
                     reportparam.put("column2",name);
                     reportparam.put("column3",memno);
                     reportparam.put("column4",memtype);
                     reportparam.put("column5",doj);
                     reportparam.put("column6",address);
                     reportparam.put("column7",memtype);
                     reportparam.put("column8",memtaxcat);
                     reportparam.put("column9",visible);
                     reportparam.put("column10",doe);
                     reportparam.put("column11",dob);

                     DataSourceProvider data1=new DataSourceProvider(details);

                     MemberDataSource ds=new MemberDataSource(details);

                     data1.setDataSource(ds);
                 JasperPrint jp=JasperReportNew.runReport(m_app,"./reports/com/openbravo/reports/MemberReport1.jrxml", reportparam, false, data1, true,null);
           }
           else{
               
                Map reportparam=new HashMap();
                      //sameer:adding compny name and address
                     reportparam.put("companyName",m_app.getSession().getCompanyName());
                     reportparam.put("companyAddress",m_app.getSession().getCompanyAddress());
                     reportparam.put("column2",name);
                     reportparam.put("column3",memno);
                     reportparam.put("column4",memtype);
                     reportparam.put("column5",doj);
                     reportparam.put("column6",address);
                     reportparam.put("column7",memtype);
                     reportparam.put("column8",memtaxcat);
                     reportparam.put("column9",visible);
                     reportparam.put("column10",doe);
                     reportparam.put("column11",dob);

                     DataSourceProvider data1=new DataSourceProvider(details);

                     MemberDataSource ds=new MemberDataSource(details);

                     data1.setDataSource(ds);
                 JasperPrint jp=JasperReportNew.runReport(m_app,"./reports/com/openbravo/reports/MemberReport1_new.jrxml", reportparam, false, data1, true,null);
               
               
           }
     
     
     }
        jCheckBox2.setVisible(true);
        jCheckBox4.setVisible(true);
            
            

    }//GEN-LAST:event_jButton1ActionPerformed
        
    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        // TODO add your handling code here:
        jCheckBox11.setVisible(true);
        jLabel16.setVisible(true);
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox4ItemStateChanged
        // TODO add your handling code here:
       jCheckBox12.setVisible(true);
       jLabel17.setVisible(true);
    }//GEN-LAST:event_jCheckBox4ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
       /* if(jComboBox1.getSelectedItem().toString().equals("All"))
        {
            jComboBox2.setVisible(false);
        }*/
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox1ActionPerformed
    {//GEN-HEADEREND:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jRadioButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton5ItemStateChanged
     if(jRadioButton5.isSelected()==true){
            jPanel3.setVisible(true);
             jPanel2.setVisible(false);
        }
        
    }//GEN-LAST:event_jRadioButton5ItemStateChanged

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
       if(jComboBox5.getSelectedIndex()!=-1){
           
           String Memtype = jComboBox5.getSelectedItem().toString();
           int flag=0;
           for(int i=0;i<SelectedMemTypeList.size();i++){
               String Str = SelectedMemTypeList.get(i).toString();
               if(Str.equals(Memtype)){
                   flag=1;
                   break;
               }
               
           }
           
           
           if(flag==0){
              SelectedMemTypeList.add(Memtype);
           
              Membertype_JList.setModel(new ItemsListModel(SelectedMemTypeList));
             
               
           }
           else{
               
               JOptionPane.showMessageDialog(this, "Membership type already selected.", "Error", JOptionPane.WARNING_MESSAGE); 
               
           }
           
           
       }
       else{
           JOptionPane.showMessageDialog(this, "Please select Membership Type first", "Error", JOptionPane.WARNING_MESSAGE); 
       }
    }//GEN-LAST:event_add_btnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      if(SelectedMemTypeList.size()>0){
          
           int row = Membertype_JList.getSelectedIndex();
           if(row>=0){
               
                String lst=Membertype_JList.getSelectedValue().toString();
                
                SelectedMemTypeList.remove(lst);
                
                Membertype_JList.setModel(new ItemsListModel(SelectedMemTypeList));
               
           }
          
           else{
                JOptionPane.showMessageDialog(this, "Select any one membership type to remove");
           }
          
          
          
      }
      else{
            JOptionPane.showMessageDialog(this, "No membership type to remove", "Error", JOptionPane.WARNING_MESSAGE); 
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
       if(jComboBox5.getSelectedIndex()!=-1){
           
           String X = jComboBox5.getSelectedItem().toString();
           if(X.equals("ALL")){
               Membertype_JList.setVisible(false);
               add_btn.setVisible(false);
               jButton2.setVisible(false);
               SelectedMemTypeList = new ArrayList<>();
               Membertype_JList.setModel(new ItemsListModel(SelectedMemTypeList));
           }
           else{
               Membertype_JList.setVisible(true);
               add_btn.setVisible(true);
               jButton2.setVisible(true);
           }
           
           
       }
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    
       private class ItemsListModel extends AbstractListModel {

        private java.util.List items;

        public ItemsListModel(java.util.List items) {
            this.items = items;
        }

        
        public int getSize() {
            return items.size();
        }

        public Object getElementAt(int i) {
            return items.get(i);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Membertype_JList;
    private javax.swing.JButton add_btn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
