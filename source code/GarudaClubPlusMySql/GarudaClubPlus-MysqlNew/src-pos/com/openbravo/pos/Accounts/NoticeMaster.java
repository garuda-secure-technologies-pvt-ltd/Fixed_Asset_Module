
package com.openbravo.pos.Accounts;

import com.openbravo.pos.admin.*;
import com.openbravo.pos.forms.BeanFactoryException;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.openbravo.pos.forms.AppLocal;
import java.util.UUID;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel; 
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.*;
import com.openbravo.pos.Accounts.NoticeMasterTableModel.NoticeMasterBean;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.clubmang.MemTypeTableModel.MemTypeline;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 *
 * @author adrianromero
 */
public class NoticeMaster extends JPanel implements JPanelView, BeanFactoryApp {
    private ComboBoxValModel memmodel;
    private Session s;
    private ComboBoxValModel noticeModel;
    private ComboBoxValModel noticeModel2;
    private Object m_oId;
    private String m_sPassword;
     private static AppView m_App;
    private DirtyManager m_Dirty;
    private SentenceList m_sentrole;
    private ComboBoxValModel m_RoleModel;
    private ComboBoxValModel accModel;
    private ComboBoxValModel cheaccModel;
    private DataLogicFacilities dlfac;
    private DataLogicAdmin dladmin;
    private DataLogicCustomers dlcus;
    private String cashinhand;
    private String chequeinhand;
    private NoticeMasterTableModel ntmt;
    private List<NoticeMasterBean> noticeList;
    private List<NoticeMasterBean> noticeList2;
    List<Facility> flist;
    private ComboBoxValModel fModel;
    
    private ComboBoxValModel PostalAccModel;
     private DataLogicSystem m_dlSystem;
    List<AccountMasterExt> TempAccList = new ArrayList();
    
    
    private Double PostalAmount = 0.00;
    private String PostalAccountName = null;
    private String PostalAccountId = null;
    private Image signImg=null;
    private Boolean signImgFlg=false;
    
    public NoticeMaster() {
        initComponents();
        
        reset();
    }

    public void activate() throws BasicException {
       
        
        
        List<MemTypeline> mlist;
 try {
     mlist = dlfac.getMemType();
     MemTypeline m1=new MemTypeline();
        m1.setID("All");
       m1.setName("All");
       mlist.add(0,m1);
        
        memmodel=new ComboBoxValModel(mlist);
       jComboBox1.setModel(memmodel);
       jComboBox1.setSelectedIndex(-1);
       flist=dlfac.getFacility();
       Facility f1=new Facility();
       f1.setID("All");
       f1.setName("All");
       flist.add(0,f1);
       
       fModel=new ComboBoxValModel(flist);
       jComboBox4.setModel(fModel);
       loaddata();
       typeOfMem.setEditable(false);
        First.setSelected(true);
        jPanel2.setVisible(true);
        jPanel3.setVisible(false);
       
        activateAll();
        } catch (BasicException ex) {
            Logger.getLogger(NoticeMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
       // EDITED BY AAKASH FOR POSTAL CHARGES
 
       ButtonGroup();
       postal_no.setSelected(true);
       postal_yes.setSelected(false);
 
       
       TempAccList = dlfac.getaccounts();
       PostalAccModel= new ComboBoxValModel(TempAccList);
       account_combo.setModel(PostalAccModel);
       
 
    }
    
    private void loaddata() {
      
        
      try {
             ntmt=NoticeMasterTableModel.loadInstance(m_App,2);
            String textareadata=typeOfMem.getText();
      String [] str=textareadata.split("\r\n");
      jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(str));
            //noticeList = dlfac.getNoticeType();
            
      noticeList = ntmt.getNoticeType(s);
      
      noticeModel=new ComboBoxValModel(noticeList);
       jComboBox3.setModel(noticeModel);
       jComboBox3.setSelectedIndex(-1);
       
       noticeList2 = ntmt.getNoticeType2(s);
       noticeModel2=new ComboBoxValModel(noticeList2);
       pNoticeName.setModel(noticeModel2);
  
      jRadioText.setSelected(true);
        BufferedImage imgicon = m_dlSystem.getResourceAsImage("signature.secretary");
                            Image newimg = imgicon.getScaledInstance(jLabelSign.getWidth(),jLabelSign.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon image = new ImageIcon(newimg);
                             jLabelSign.setIcon(image);
                              signImg=newimg;
      
        } catch (BasicException ex) {
            Logger.getLogger(NoticeMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
    }
    
    public void init(AppView app) throws BeanFactoryException {
        
        groupButton();
        groupButton1();
         groupButton3();groupButton4();
         ButtonGroup1();
        m_App=app;
         dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
           m_dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        s=m_App.getSession();
    }
    
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Month2 = new javax.swing.JRadioButton();
        Payment = new javax.swing.JTextField();
        First = new javax.swing.JRadioButton();
        Day2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        Other = new javax.swing.JRadioButton();
        Final = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1.setVisible(false);
        jScrollPane1 = new javax.swing.JScrollPane();
        typeOfMem = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        DP = new javax.swing.JTextField();
        Day1 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        Month1 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddNotes = new javax.swing.JTextArea(4, 100);
        jLabel12 = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        NoticeName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        SaveChanges = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        NoticeId = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        pNoticeName = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Signature = new javax.swing.JTextField();
        Designation = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        nNtoDisplayInNotice = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        dNo = new javax.swing.JRadioButton();
        dYes = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        charCount = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AddNotes1 = new javax.swing.JTextArea(4, 100);
        jLabel27 = new javax.swing.JLabel();
        charCount1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        postal_yes = new javax.swing.JRadioButton();
        postal_no = new javax.swing.JRadioButton();
        postal_panel = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        postalamt_text = new javax.swing.JTextField();
        account_combo = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jRadioImage = new javax.swing.JRadioButton();
        jRadioText = new javax.swing.JRadioButton();
        jLabelSign = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
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
        jButton6 = new javax.swing.JButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        jLabel17.setText("jLabel17");

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        Month2.setText("Months");

        Payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PaymentKeyReleased(evt);
            }
        });

        First.setText("First");
        First.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FirstItemStateChanged(evt);
            }
        });
        First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstActionPerformed(evt);
            }
        });

        Day2.setText("Days");

        jLabel8.setText("Payment Date");

        Other.setText("Other than 1st & Final");
        Other.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OtherItemStateChanged(evt);
            }
        });
        Other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherActionPerformed(evt);
            }
        });

        Final.setText("Final");
        Final.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FinalItemStateChanged(evt);
            }
        });
        Final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalActionPerformed(evt);
            }
        });

        jLabel9.setText("from date of this notice.");

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        typeOfMem.setColumns(20);
        typeOfMem.setRows(5);
        jScrollPane1.setViewportView(typeOfMem);

        jLabel2.setText("of Members");

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox2MouseEntered(evt);
            }
        });

        jButton5.setText("Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Applicable to type");

        jLabel13.setText("Facility");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        DP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DPKeyReleased(evt);
            }
        });

        Day1.setText("Days");
        Day1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Day1ActionPerformed(evt);
            }
        });

        jLabel10.setText("/");

        Month1.setText("Months");

        jLabel7.setText("from date of this notice.");

        jLabel4.setText("Due For");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel13)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(60, 60, 60)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jComboBox4, 0, 221, Short.MAX_VALUE)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jComboBox1, 0, 252, Short.MAX_VALUE)
                            .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton5)
                            .add(jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(116, 116, 116))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(DP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(Day1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(Month1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(238, 238, 238))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel13)
                        .add(18, 18, 18)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(7, 7, 7)
                        .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton5))
                            .add(jScrollPane1))))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(DP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Day1)
                    .add(jLabel10)
                    .add(Month1)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel5.setText("Type of Notice");

        jLabel11.setText("/");

        AddNotes.setColumns(20);
        AddNotes.setRows(5);
        jScrollPane2.setViewportView(AddNotes);
        AddNotes.setWrapStyleWord(true);
        AddNotes.setLineWrap(true);
        AddNotes.setDocument(new FixedSizeDocument(650));

        jLabel12.setText("Additional Notes");

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jLabel1.setText(AppLocal.getIntString("label.peoplename")); // NOI18N

        SaveChanges.setText("Save Changes");
        SaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChangesActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel6.setText("Select parent Notice ");

        pNoticeName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel6)
                .add(36, 36, 36)
                .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pNoticeName, 0, 242, Short.MAX_VALUE)
                .add(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pNoticeName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pNoticeName.setEnabled(false);
        pNoticeName.setVisible(false);

        jLabel14.setText("Signature");

        jLabel15.setText("Designation");

        jLabel16.setText("Notice Name To Display In Notice");

        jLabel18.setText("Deactivate Member");

        dNo.setText("No");
        dNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dNoActionPerformed(evt);
            }
        });

        dYes.setText("Yes");

        jLabel19.setText("(Max Characters");

        jLabel20.setText("jLabel20");

        jLabel21.setText("You have ");

        jLabel22.setText("Characters left.");

        jLabel23.setText("in the Notice");

        jLabel24.setText("Header to List ");

        jLabel25.setText("(Max Characters");

        jLabel26.setText("500)");

        AddNotes1.setColumns(20);
        AddNotes1.setRows(5);
        jScrollPane5.setViewportView(AddNotes1);
        AddNotes1.setWrapStyleWord(true);
        AddNotes1.setLineWrap(true);
        String str = AddNotes1.getText();
        AddNotes1.setDocument(new FixedSizeDocument2(500));
        AddNotes1.setText(str);

        jLabel27.setText("You have ");

        jLabel28.setText("Characters left.");

        jLabel29.setText("Do you want to debit postal charges from member(s) account  : ");

        postal_yes.setText("Yes");
        postal_yes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                postal_yesItemStateChanged(evt);
            }
        });

        postal_no.setText("No");
        postal_no.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                postal_noItemStateChanged(evt);
            }
        });

        jLabel30.setText("Select Account :  ");

        account_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jLabel31.setText("Postal Amount : ");

        org.jdesktop.layout.GroupLayout postal_panelLayout = new org.jdesktop.layout.GroupLayout(postal_panel);
        postal_panel.setLayout(postal_panelLayout);
        postal_panelLayout.setHorizontalGroup(
            postal_panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(postal_panelLayout.createSequentialGroup()
                .add(21, 21, 21)
                .add(postal_panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel30)
                    .add(jLabel31))
                .add(39, 39, 39)
                .add(postal_panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(account_combo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(postalamt_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 169, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        postal_panelLayout.setVerticalGroup(
            postal_panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(postal_panelLayout.createSequentialGroup()
                .addContainerGap()
                .add(postal_panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(postalamt_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel31))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(postal_panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(account_combo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel30))
                .add(0, 12, Short.MAX_VALUE))
        );

        jRadioImage.setText("Image");
        jRadioImage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioImageItemStateChanged(evt);
            }
        });

        jRadioText.setText("Text");
        jRadioText.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioTextItemStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel12))
                            .add(jLabel19)
                            .add(jLabel20)
                            .add(jLabel8)
                            .add(jLabel14)
                            .add(jLabel15)
                            .add(jLabel18))
                        .add(45, 45, 45)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(Payment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(Day2)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel11)
                                        .add(6, 6, 6)
                                        .add(Month2)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(dNo)
                                        .add(18, 18, 18)
                                        .add(dYes)))
                                .add(0, 0, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(Designation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 211, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jRadioText)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 29, Short.MAX_VALUE)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(jLabel27)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(charCount1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jLabel28))
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                    .add(Signature, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(jPanel1Layout.createSequentialGroup()
                                                        .add(jLabel21)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(charCount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jLabel22)))))
                                .add(10, 10, 10)
                                .add(jRadioImage)
                                .add(29, 29, 29)
                                .add(jLabelSign, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(53, 53, 53)))
                        .add(3994, 3994, 3994))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(Cancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(SaveChanges)
                                .add(18, 18, 18)
                                .add(Save, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel26)
                                    .add(jLabel24)
                                    .add(jLabel25))
                                .add(58, 58, 58)
                                .add(jScrollPane5)))
                        .add(4078, 4078, 4078))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel29)
                                .add(18, 18, 18)
                                .add(postal_yes)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(postal_no))
                            .add(postal_panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, NoticeId)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(28, 28, 28)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(First, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(Other)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(Final))
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(NoticeName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 194, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jLabel16)
                                                .add(4, 4, 4)
                                                .add(nNtoDisplayInNotice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                            .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(NoticeId)
                    .add(NoticeName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel16)
                    .add(nNtoDisplayInNotice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(First)
                    .add(Final)
                    .add(Other))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Day2)
                            .add(Payment, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Month2)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel11))))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(7, 7, 7)
                        .add(jLabel12)
                        .add(2, 2, 2)
                        .add(jLabel23)
                        .add(4, 4, 4)
                        .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(12, 12, 12))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel21)
                        .add(jLabel22)
                        .add(charCount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(25, 25, 25)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelSign, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel14)
                                    .add(jRadioText)
                                    .add(Signature, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jRadioImage))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel15)
                                    .add(Designation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                .add(3, 3, 3)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(9, 9, 9)
                        .add(jLabel18)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel27)
                            .add(jLabel28)
                            .add(charCount1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(dNo)
                        .add(dYes)))
                .add(5, 5, 5)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel24)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel25)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel26)))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel29)
                    .add(postal_yes)
                    .add(postal_no))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(postal_panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(SaveChanges)
                    .add(Save)
                    .add(Cancel))
                .addContainerGap(313, Short.MAX_VALUE))
        );

        SaveChanges.setVisible(false);
        NoticeId.setVisible(false);
        jPanel3.setVisible(false);
        dNo.setSelected(true);
        charCount.setEditable(false);
        charCount1.setEditable(false);

        jTabbedPane1.addTab("Create New", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Due/Pass", "Period from date of notice", "Day/Month", "Payment Date Period from date of Notice", "Day / Month", "Additional Notes", "Memeber Type", "Final Notice", "Parent Reference", "Parent Id", "Cr. Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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

        jButton2.setText("Deactivate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                .add(3974, 3974, 3974))
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(630, 630, 630)
                        .add(jCheckBox5))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(502, 502, 502)
                        .add(jButton6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jButton2)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jCheckBox5)
                .add(18, 18, 18)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 439, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton6))
                .addContainerGap(595, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel4);

        jScrollPane4.setViewportView(jTabbedPane1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 801, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
    
    jTabbedPane1.setTitleAt(0, "Create Notice");
    javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
   reset();
       int tabno=tabpane.getSelectedIndex();
       
       if(tabno==1){
        try{
         
            showAll();
            activate();
        }
        catch(Exception e){
         e.printStackTrace();
        }
       }
    
}//GEN-LAST:event_jTabbedPane1StateChanged

private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        try {
            ntmt=NoticeMasterTableModel.loadInstance(m_App,2);
            int num = 0;
            List<NoticeMasterBean> lis2 = ntmt.getfacilityline();
            for (Iterator<NoticeMasterBean> it = lis2.iterator(); it.hasNext();) {
                NoticeMasterBean noticeMasterBean = it.next();
                if(noticeMasterBean.getName().toString().equals(NoticeName.getText()))
                {
                    num++;
                }
            }
            if(num==0)
            {
            saveData();
            activate();
            reset();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Notice name " + NoticeName.getText() + " already exist. Enter new Name. ");
                assignExistingValues();
                NoticeName.requestFocus(true);
            }
        } catch (BasicException ex) {
            Logger.getLogger(NoticeMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_SaveActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
          String selectedpr=jComboBox2.getSelectedItem().toString();
        String data=typeOfMem.getText();
        String newdata="";
        String[] temparr=data.split("\r\n");
        for(int i=0;i<temparr.length; i++)
        {
            if(!temparr[i].equals(selectedpr))
                newdata += temparr[i]+"\r\n";

        }
        typeOfMem.setText(newdata);
        loaddata();
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String selectedpr=jComboBox1.getSelectedItem().toString();
        int flag=0;
        String data=typeOfMem.getText();
        String[] temparr=data.split("\r\n");
       
        if(!typeOfMem.getText().contains("All"))
        {
        for(int i=0;i<temparr.length; i++)
        {
            
            if(temparr[i].trim().equals(selectedpr.trim()) )
            {
                 JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.prcatwarning"), AppLocal.getIntString("message.prcattitle"), JOptionPane.WARNING_MESSAGE);
                 flag=1;
            }
        }
        }
        else
        {
            JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.prcatwarning"), AppLocal.getIntString("message.prcattitle"), JOptionPane.WARNING_MESSAGE);
                 flag=1;
        }
        if(flag==0){
            if(!selectedpr.equals("All"))
            {
                data += selectedpr+"\r\n";
            }
            else
            {
                if(typeOfMem.getText().equals(""))
                {
                    data += selectedpr+"\r\n";
                }
                else
                {
                    JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.prcatwarning"), AppLocal.getIntString("message.prcattitle"), JOptionPane.WARNING_MESSAGE);
                }
            }
        
        typeOfMem.setText(data);
        loaddata();
        }
}//GEN-LAST:event_jButton4ActionPerformed

private void DPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DPKeyReleased

    char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
    if(!Character.isDigit(c))
    {
        JOptionPane.showMessageDialog(Payment, "Please enter only numbers..");
            
            DP.setText(null);
    }
    }
}//GEN-LAST:event_DPKeyReleased

private void FinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalActionPerformed

    
    if(Save.isVisible())
    {
    jPanel2.setVisible(false);
    jPanel3.setVisible(true);
    }
}//GEN-LAST:event_FinalActionPerformed

private void Day1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Day1ActionPerformed

}//GEN-LAST:event_Day1ActionPerformed

private void FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstActionPerformed

    if(Save.isVisible())
    {
   jPanel2.setVisible(true);
   jPanel3.setVisible(false);
    }
}//GEN-LAST:event_FirstActionPerformed

private void PaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaymentKeyReleased
char c = evt.getKeyChar();
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT)
    {
    if(!Character.isDigit(c))
    {
        JOptionPane.showMessageDialog(Payment, "Please enter only numbers..");
            
            Payment.setText(null);
    }
    }
}//GEN-LAST:event_PaymentKeyReleased

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
        
    int row11  = jTable1.getSelectedRow();
        int r111  = jTable1.getSelectedRow();
    if(evt.getClickCount()==2)
    {
        int row  = jTable1.getSelectedRow();
        int r1  = jTable1.getSelectedRow();
    }
}//GEN-LAST:event_jTable1MouseClicked

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        int row = jTable1.getSelectedRow();
        try {
            if (row >= 0 && row < jTable1.getRowCount()) {
                String name = jTable1.getModel().getValueAt(row, 0).toString().trim();
               jTabbedPane1.setTitleAt(0, "Edit Notice");
              activateNoticeEdit();
              jTabbedPane1.setSelectedIndex(0);
               editNotice(name);
      
            } else {
                JOptionPane.showMessageDialog(this, "selec any notice to edit");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
    try {
        int row = jTable1.getSelectedRow();
        List<NoticeMasterBean> list = new ArrayList<NoticeMasterBean>();
        NoticeMasterBean nmb2 = null;
        
        if (row < jTable1.getRowCount() && row >= 0) {
           
            List<NoticeMasterBean> lis2 = ntmt.getfacilityline();
            String name = jTable1.getModel().getValueAt(row, 0).toString();
            for (Iterator<NoticeMasterBean> it = lis2.iterator(); it.hasNext();) {
                NoticeMasterBean nmb = it.next();
                if(nmb.getName().toString().equals(name))
                {
                    nmb2 = nmb;
                    list.add(nmb); 
                }
                else if(nmb.getReferencetoparent()!=null)
                {
                    if(nmb.getReferencetoparent().toString().equals(name))
                    {
                        list.add(nmb); 
                    }
                }
            }
            
           int res = deleteNotice(nmb2, name);   
        }
        
        activate();
        showAll();
    } catch (Exception ex) {
        if(ex.getMessage().toString().equals("CompleteForm"))
            {
                JOptionPane.showMessageDialog(this, "Please completely fill the application.");
            }
            else
            {
            Logger.getLogger(NoticeMaster.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
        }}
   
}//GEN-LAST:event_jButton2ActionPerformed

private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed

    
    
}//GEN-LAST:event_jCheckBox5ActionPerformed

private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged

   
        try{
         
            showAll();
        }
        catch(Exception e){
         e.printStackTrace();
        }
}//GEN-LAST:event_jCheckBox5ItemStateChanged

private void OtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherActionPerformed
if(Save.isVisible()){
    jPanel2.setVisible(false);
    jPanel3.setVisible(true);}
}//GEN-LAST:event_OtherActionPerformed

private void SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesActionPerformed
 int row = jTable1.getSelectedRow();
    String name = jTable1.getModel().getValueAt(row, 0).toString().trim();
    jTabbedPane1.setTitleAt(0, "Create Notice");
        try {
            int num = 0;
            List<NoticeMasterBean> lis2 = ntmt.getfacilityline();
    for (Iterator<NoticeMasterBean> it = lis2.iterator(); it.hasNext();) {
        NoticeMasterBean noticeMasterBean = it.next();
        if(noticeMasterBean.getName().toString().equals(NoticeName.getText()))
        {
            num++;
        }}
            if(num<2)
            {
            updateNoticeMaster();
            activate();
            reset();
            activateNoticeCreate();
         }
            else
            {
                JOptionPane.showMessageDialog(null, "Notice name " + NoticeName.getText() + " already exist. Enter new Name. ");
                assignExistingValues();
                NoticeName.requestFocus(true);
            }
        } catch (BasicException ex) {
            
           if(ex.getMessage().toString().equals("completeForm"))
            {
                JOptionPane.showMessageDialog(this,"Please Fill the form Completely", null, JOptionPane.WARNING_MESSAGE);
                jPanel1.requestFocusInWindow();
            }
            else
            {
            Logger.getLogger(NoticeMaster.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
            }
        }
    
}//GEN-LAST:event_SaveChangesActionPerformed

private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
jTabbedPane1.setTitleAt(0, "Create Notice");
  // jTabbedPane1.removeTabAt(1);
  //jTabbedPane1.addTab("Create New", jPanel1);
            try{
                activate();
            }
            catch(BasicException e){
                
            }
            
            
            
            reset();
            activateNoticeCreate();
            
   
}//GEN-LAST:event_CancelActionPerformed

private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

   
    if(evt.getClickCount()==2)
    {
        ActionEvent ae = new ActionEvent(evt.getSource(), evt.getID(), evt.paramString());
        jButton6ActionPerformed(ae);
    }
    
}//GEN-LAST:event_jTable1MousePressed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged

    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseEntered
       
    }//GEN-LAST:event_jComboBox2MouseEntered

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        String textareadata=typeOfMem.getText();
      String [] str=textareadata.split("\r\n");
      jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(str));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void dNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dNoActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        
        NoticeMasterBean nmb1 = (NoticeMasterBean) jComboBox3.getSelectedItem();
        if(nmb1.isDeactivateMem())
        {
            dYes.setSelected(true);
            dNo.setSelected(false);
            dNo.setEnabled(false);
        }
        else
        {
            dNo.setSelected(true);
//            dYes.setSelected(false);
//            dYes.setEnabled(false);
        }
        
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void FinalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FinalItemStateChanged
       String str = AddNotes.getText();
        if(Final.isSelected())
       {
           jLabel20.setText("1300)");
           AddNotes.setDocument(new FixedSizeDocument(1300));
           AddNotes.setText(str);
       }
       
       
    }//GEN-LAST:event_FinalItemStateChanged

    private void OtherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OtherItemStateChanged
    String str = AddNotes.getText();       
        if(Other.isSelected())
       {
           jLabel20.setText("650)");
           AddNotes.setDocument(new FixedSizeDocument(650));
          
           AddNotes.setText(str);
       }
       
        
    }//GEN-LAST:event_OtherItemStateChanged

    private void FirstItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FirstItemStateChanged
        String str = AddNotes.getText();
        if(First.isSelected())
       {
           jLabel20.setText("650)");
           AddNotes.setDocument(new FixedSizeDocument(650));
           AddNotes.setText(str);
       }
    }//GEN-LAST:event_FirstItemStateChanged

    private void postal_yesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_postal_yesItemStateChanged
       if(postal_yes.isSelected()){
           postal_panel.setVisible(true);
       }
       else{
            postal_panel.setVisible(false);
       }
    }//GEN-LAST:event_postal_yesItemStateChanged

    private void postal_noItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_postal_noItemStateChanged
        if(postal_no.isSelected()){
           postal_panel.setVisible(false);
       }
       else{
            postal_panel.setVisible(true);
       }
    }//GEN-LAST:event_postal_noItemStateChanged

    private void jRadioTextItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioTextItemStateChanged
        // TODO add your handling code here:
        if(jRadioText.isSelected()){
        Signature.setVisible(true);
        signImgFlg=false;
        jLabelSign.setVisible(false);
        }else
            Signature.setVisible(false);
        signImgFlg=true;
        jLabelSign.setVisible(true);
    }//GEN-LAST:event_jRadioTextItemStateChanged

    private void jRadioImageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioImageItemStateChanged
        // TODO add your handling code here:
//         if(jRadioImage.isSelected()){
//             signImgFlg=true;
//              Signature.setVisible(false);
//         jLabelSign.setVisible(true);
//         }else{  Signature.setVisible(true);
//         signImgFlg=false;
//          jLabelSign.setVisible(false);
//         }
    }//GEN-LAST:event_jRadioImageItemStateChanged

private void reset(){
    NoticeName.setText(null);
    typeOfMem.setText(null);
    DP.setText(null);
    Payment.setText(null);
    AddNotes.setText(null);
    jLabel20.setText("400)");
    First.setSelected(true);
        jPanel2.setVisible(true);
        jPanel3.setVisible(false);
       Day1.setSelected(true);
       Day2.setSelected(true);
       Signature.setText(null);
       Designation.setText(null);
       nNtoDisplayInNotice.setText(null);
       dNo.setEnabled(true);
       dYes.setEnabled(true);
       dNo.setSelected(true);
       dYes.setSelected(false);
       jComboBox3.setVisible(true);
       pNoticeName.setVisible(false);
       jLabel20.setText("650)");
       AddNotes.setDocument(new FixedSizeDocument(650));
       AddNotes1.setText(null);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AddNotes;
    private javax.swing.JTextArea AddNotes1;
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField DP;
    private javax.swing.JRadioButton Day1;
    private javax.swing.JRadioButton Day2;
    private javax.swing.JTextField Designation;
    private javax.swing.JRadioButton Final;
    private javax.swing.JRadioButton First;
    private javax.swing.JRadioButton Month1;
    private javax.swing.JRadioButton Month2;
    private javax.swing.JLabel NoticeId;
    private javax.swing.JTextField NoticeName;
    private javax.swing.JRadioButton Other;
    private javax.swing.JTextField Payment;
    private javax.swing.JButton Save;
    private javax.swing.JButton SaveChanges;
    private javax.swing.JTextField Signature;
    private javax.swing.JComboBox account_combo;
    private javax.swing.JTextField charCount;
    private javax.swing.JTextField charCount1;
    private javax.swing.JRadioButton dNo;
    private javax.swing.JRadioButton dYes;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSign;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioImage;
    private javax.swing.JRadioButton jRadioText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nNtoDisplayInNotice;
    private javax.swing.JComboBox pNoticeName;
    private javax.swing.JRadioButton postal_no;
    private javax.swing.JPanel postal_panel;
    private javax.swing.JRadioButton postal_yes;
    private javax.swing.JTextField postalamt_text;
    private javax.swing.JTextArea typeOfMem;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Notice Master";
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public boolean showDialog() {
       AppView ap = LookupUtilityImpl.getInstance(null).getAppView();
       init(ap);
            setVisible(true);
        return true;
    }
    public Object getBean() {
       return this;
    }

    private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(Month1);
        bg1.add(Day1);
    }
     
    
    private void groupButton1() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(Day2);
        bg1.add(Month2);
    }
    
    private void checkNumber(JTextField tf)
    {
        for(int i=0; i>tf.getText().length(); i++)
        {
        if(Character.isDigit(tf.getText().charAt(i)));
        {
            JOptionPane.showMessageDialog(tf, "Please enter only numbers..");
            tf.setText(null);
        }
        }
    }

    private void groupButton3() {
        
         ButtonGroup bg1 = new ButtonGroup();
         bg1.add(First);
         bg1.add(Other);bg1.add(Final);
    }
    
    private void groupButton4() {
        
         ButtonGroup bg1 = new ButtonGroup();
         bg1.add(dNo);
         bg1.add(dYes);
    }
    
    public void showAll() throws BasicException
    {
        if(jCheckBox5.isSelected()==true){
             ntmt=NoticeMasterTableModel.loadInstance(m_App,2);
           jTable1.setModel(ntmt.getTableModel());
        }else
        {
           ntmt=NoticeMasterTableModel.loadInstance(m_App,1);
           jTable1.setModel(ntmt.getTableModel());
        }
    }
    
    public void saveData()
    {
        try{
            
    Transaction t = new Transaction(m_App.getSession()) {
        int finalnote = 0;
     Object [] values = fetchValues();
            @Override
            protected Object transact() throws BasicException {
//               new PreparedSentence(m_App.getSession()
//                  , "INSERT INTO NoticeMaster(ID,NAME,DUE_PASS,DP_DM,DP_NUM,PAYMENT_DATE_DM,PAYMENT_DATE_NUM,ADDITIONALNOTES,TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, ACTIVATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG ,POSTALCHRGAMT , ACCOUNTID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
//                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING , Datas.BOOLEAN , Datas.DOUBLE , Datas.STRING})
//                  ).exec(values);
 new PreparedSentence(m_App.getSession()
                  , "INSERT INTO NoticeMaster(ID,NAME,DUE_PASS,DP_DM,DP_NUM,PAYMENT_DATE_DM,PAYMENT_DATE_NUM,ADDITIONALNOTES,TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, ACTIVATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG ,POSTALCHRGAMT , ACCOUNTID,SignImgFlg) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING , Datas.BOOLEAN , Datas.DOUBLE , Datas.STRING,Datas.BOOLEAN})
                  ).exec(values);//pratima
               
               JOptionPane.showMessageDialog(null, "Notice Created Successfully.");
             return null;
             }
        };
            t.execute();
    }
    catch(Exception ex){
        
            if(ex.getMessage().toString().equals("completeForm"))
            {
                JOptionPane.showMessageDialog(null,"Please Fill the form Completely", null, JOptionPane.WARNING_MESSAGE);
            }
            else
            {
            Logger.getLogger(NoticeMaster.class.getName()).log(Level.SEVERE, null, ex);
            new MessageInf(ex).show(getParent());
            }
        }
        reset();
    }

    private void editNotice(String name) throws BasicException {
       
        NoticeName.setText(name);
        String accid;
        String nName;
        jPanel3.setVisible(true);
        ntmt=NoticeMasterTableModel.loadInstance(m_App,2);
        
        List<NoticeMasterBean> allNotice = ntmt.getfacilityline();
        
        for (Iterator it = allNotice.iterator(); it.hasNext();) {
                       NoticeMasterBean nmb = (NoticeMasterBean) it.next();
                       if(nmb.getName().trim().equals(name))
                       {
                           accid = nmb.getId();
                           for(int i=0; i<jComboBox4.getItemCount(); i++)
                            {
                                if(jComboBox4.getItemAt(i).toString().equals(nmb.getFacility()))
                                {
                                    jComboBox4.setSelectedIndex(i);
                                    break;
                                }
                            }
                          if(nmb.getReferencetoparent()==null)
                          {
                              First.setVisible(true);
                              First.setSelected(true);
                              Other.setVisible(false);
                              Final.setVisible(true);
                              jPanel3.setVisible(false);
                              jComboBox4.setEnabled(true);
                              DP.setEnabled(true);
                              Day1.setEnabled(true);
                              Month1.setEnabled(true);
                          }
                          else if(nmb.isFinalnotice())
                          {
                              Final.setSelected(true);
                              First.setVisible(false);
                              jComboBox1.setVisible(false);
                              jButton4.setVisible(false);
                              jComboBox2.setVisible(false);
                              jButton5.setVisible(false);
                              jLabel13.setVisible(true);
                              DP.setEnabled(false);
                              Day1.setEnabled(false);
                              Month1.setEnabled(false);
                              jComboBox4.setEnabled(false);
                          }
                          else
                          {
                              Other.setSelected(true);
                              First.setVisible(false);
                              jComboBox1.setVisible(false);
                              jButton4.setVisible(false);
                              jComboBox2.setVisible(false);
                              jButton5.setVisible(false);
                              jLabel13.setVisible(true);
                              jComboBox4.setEnabled(false);
                              DP.setEnabled(false);
                              Day1.setEnabled(false);
                              Month1.setEnabled(false);
                          }
                          String []typeOfmem = nmb.getTypeofmem().split(",");
                          String typeOf = "";
                          for(int i=0; i<typeOfmem.length;i++)
                          {
                              typeOf = typeOf+typeOfmem[i];
                          }
                          NoticeId.setText(nmb.getId());
                          typeOfMem.setText(typeOf);
                          Signature.setText(nmb.getSign());
                          //by pratima:to add signature image
                         if(nmb.isSignImgFlg()){  
                             BufferedImage imgicon = m_dlSystem.getResourceAsImage("signature.secretary");
                            Image newimg = imgicon.getScaledInstance(jLabelSign.getWidth(),jLabelSign.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon image = new ImageIcon(newimg);
                             jLabelSign.setIcon(image);
                              signImg=newimg;
                              jRadioImage.setSelected(true);
                         }
                          Designation.setText(nmb.getDesg());
                          nNtoDisplayInNotice.setText(nmb.getNoticeNameToDisplay());
                          AddNotes1.setText(nmb.getHeaderToList());
                          
                         
                          
                           for (Iterator<NoticeMasterBean> it1 = allNotice.iterator(); it1.hasNext();) {
                               NoticeMasterBean noticeMasterBean1 = it1.next();
                               
                               if(nmb.getParentId()!=null)
                               {
                               if(nmb.getParentId().equals(noticeMasterBean1.getId()))
                               {
                                   if(noticeMasterBean1.isDeactivateMem())
                                   {
                                       dYes.setSelected(true);
                                       dNo.setSelected(false);
                                       dNo.setEnabled(false);
                                       break;
                                   }
                                   else
                                   {
                                        if(nmb.isDeactivateMem())
                                        {
                                            dYes.setSelected(true);
                                            dNo.setSelected(false);
                                            break;
                                        }
                                         else
                                           {
                                            dYes.setSelected(false);
                                            dNo.setSelected(true);
                                            break;
                                           }
                                   }
                                   
                               }
                               }
                           }
                           
                           for (Iterator<NoticeMasterBean> it1 = allNotice.iterator(); it1.hasNext();) {
                               NoticeMasterBean noticeMasterBean5 = it1.next();
                               if(nmb.getId().equals(noticeMasterBean5.getParentId()))
                               {
                                   if(!noticeMasterBean5.isDeactivateMem())
                                   {
                                            dYes.setSelected(false);
                                            dYes.setEnabled(false);
                                            dNo.setSelected(true);
                                            break;
                                   }
                               }
                               
                           }
                          
                            jComboBox3.setVisible(false);
                            pNoticeName.setVisible(true);
                            
                            //pNoticeName.setText(nmb.getReferencetoparent());
                          for(int i=0; i<pNoticeName.getItemCount(); i++)
                    {
                   if(pNoticeName.getItemAt(i).toString().equals(nmb.getReferencetoparent()))
                           {
                              pNoticeName.setSelectedIndex(i);
                              
                              break;
                          }
                      }
                           int dp = nmb.getDp_num();
                           DP.setText(""+dp);
                           if(nmb.isDp_dm())
                           {
                               Month1.setSelected(true);
                           }
                           Payment.setText(""+nmb.getPayment_date_num());
                           if(nmb.isPayment_date_dm())
                           {
                               Month2.setSelected(true);
                           }
                           AddNotes.setText(nmb.getAddNotes());
                           
                           
                           
                                                                                                // edited by aakash
                           
                           
                           Double PostalAmt = nmb.getPostalAmount();
                           postalamt_text.setText(PostalAmt+"");
                           if(nmb.getPostalChargeFlag()){
                               postal_yes.setSelected(true);
                           }
                           else{
                               postal_no.setSelected(false);
                           }
                           String AccountId  = nmb.getAccountid();
                           String AccountName = null;
                           for(int i=0;i<TempAccList.size();i++){
                               String id = TempAccList.get(i).getId().toString();
                               if(id.equals(AccountId)){
                                   AccountName = TempAccList.get(i).getName().toString();
                                   account_combo.setSelectedIndex(i);
                                   break;
                               }
                           }
                           
                          
                           break;
                           
                           
                           
                            
                           
                           
                           
                           
                           
                       }
                       
                       
                       
                       
                       
                     }
    }

    private void activateNoticeEdit() {
        Save.setVisible(false);
        SaveChanges.setVisible(true);
        Cancel.setVisible(true);
    }
    private void activateNoticeCreate() {
        Save.setVisible(true);
        SaveChanges.setVisible(false);
        Cancel.setVisible(false);
    }
    
    private void activateAll()
{
    NoticeName.setVisible(true);
    First.setVisible(true);
    Other.setVisible(true);
    Final.setVisible(true);
    typeOfMem.setVisible(true);
    jComboBox1.setVisible(true);
    Other.setVisible(true);
    First.setVisible(true);
    jComboBox1.setVisible(true);
    jButton4.setVisible(true);
    jComboBox2.setVisible(true);
    jButton5.setVisible(true);
    jComboBox3.setVisible(true);
    jComboBox3.setEnabled(true);
    jLabel13.setVisible(true);
    jComboBox4.setVisible(true);
    jComboBox4.setEnabled(true);
    DP.setEnabled(true);
    Day1.setEnabled(true);
    Month1.setEnabled(true);
    DP.setVisible(true);
    Day1.setVisible(true);
    Month1.setVisible(true);
      
}

    private void updateNoticeMaster() throws BasicException {
       Object [] values = fetchValues();
       
       if(values[13]==null)
       {
            List<NoticeMasterBean> n = ntmt.getfacilityline();
           
           for (Iterator<NoticeMasterBean> it = n.iterator(); it.hasNext();) {
               NoticeMasterBean noticeMasterBean5 = it.next();
               if(noticeMasterBean5.getId().toString().equals(values[0].toString()))
               {
                   values[13]=noticeMasterBean5.getCrdate();
                   break;
               }
           }
       }
       int row = jTable1.getSelectedRow();
    String NoticeN = jTable1.getModel().getValueAt(row, 0).toString().trim();
        Object[] val = new Object[values.length];
        int n =0;
        int num =0;
        int cnt =0;
        int transaction = 0;
        int deactivatedcount = 0;
        int deactivatedcount2 = 0;
       for(int i=1; i<values.length; i++)
       {
           val[i-1] = values[i];
           System.out.println("i-1"+(i-1)+"   "+val[i-1]);
       }
       val[values.length-1]=values[0];
       System.out.println("values.length-1"+val[values.length-1]);
       List<NoticeMasterBean> updateList = ntmt.getNoticeListToDelete1(values[0].toString(), s);
       
      Boolean finalNo=  (Boolean) val[9];
      
      if(finalNo==true)
      {
          String ss = "?";
          if(updateList.size()>0)
          {
                 ss = "  " + updateList.toString();
             
                if(JOptionPane.showConfirmDialog(this, "Further Notices " + ss +"  will be deactivated!!  "+ " Do you want to Continue??", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        for (Iterator<NoticeMasterBean> it = updateList.iterator(); it.hasNext();) {
                            NoticeMasterBean noticeMasterBean = it.next();
                            int cnt1 =  new PreparedSentence(s, "UPDATE NOTICEMASTER SET ACTIVATE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING,})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), noticeMasterBean.getId()});
                            deactivatedcount = deactivatedcount+cnt1;
                        }
                     transaction = 1;
                    }
                 else
                {
                    transaction = -1;
                }
          }
        } 
     else if(First.isSelected())
        {
        String typeofmem = typeOfMem.getText();
        String facil = jComboBox4.getSelectedItem().toString();
        int dpNum = Integer.parseInt(DP.getText().toString());
        boolean b = false;
        if(Month1.isSelected())
        {
            b = true;
        }
          
       for (Iterator<NoticeMasterBean> it = updateList.iterator(); it.hasNext();) {
              NoticeMasterBean noticeMasterBean = it.next();
              Object [] valforFirstNotice = new Object[]{typeofmem, facil, dpNum, b,  noticeMasterBean.getId() };
              new PreparedSentence(m_App.getSession(), "UPDATE NOTICEMASTER SET TYPEOFMEM=?, FACILITY=?, DP_NUM=?, DP_DM=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.BOOLEAN, Datas.STRING})).exec(valforFirstNotice);
          }
        }
      if(transaction != -1)
        {
//        int exec = new PreparedSentence(m_App.getSession(), "UPDATE NOTICEMASTER SET NAME=?,DUE_PASS=?,DP_DM=?,DP_NUM=?,PAYMENT_DATE_DM=?, PAYMENT_DATE_NUM=?, ADDITIONALNOTES=?,TYPEOFMEM=?, FACILITY =?, FINALNOTICE=?, REFTOPARENT=?, PARENTID=?, CRDATE=? , ACTIVATE = ?, SIGN = ?, DESG = ?, NOTICENAMETODISPLAY = ?, DEACTIVATEMEM=?, LISTHEADER=? , POSTALCHRGFLAG=? , POSTALCHRGAMT=? WHERE ID=? ",
//                           new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP,Datas.BOOLEAN, Datas.STRING,Datas.STRING,Datas.STRING,  Datas.BOOLEAN, Datas.STRING , Datas.BOOLEAN , Datas.DOUBLE, Datas.STRING })).exec(val);
//    pratima           
             int exec = new PreparedSentence(m_App.getSession(), "UPDATE NOTICEMASTER SET NAME=?,DUE_PASS=?,DP_DM=?,DP_NUM=?,PAYMENT_DATE_DM=?, PAYMENT_DATE_NUM=?, ADDITIONALNOTES=?,TYPEOFMEM=?, FACILITY =?, FINALNOTICE=?, REFTOPARENT=?, PARENTID=?, CRDATE=? , ACTIVATE = ?, SIGN = ?, DESG = ?, NOTICENAMETODISPLAY = ?, DEACTIVATEMEM=?, LISTHEADER=? , POSTALCHRGFLAG=? , POSTALCHRGAMT=?,ACCOUNTID=?, SIGNIMGFLG=? WHERE ID=? ",
                           new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP,Datas.BOOLEAN, Datas.STRING,Datas.STRING,Datas.STRING,  Datas.BOOLEAN, Datas.STRING , Datas.BOOLEAN , Datas.DOUBLE,Datas.STRING, Datas.BOOLEAN  , Datas.STRING })).exec(val);
   //   int exec1 = new PreparedSentence(m_App.getSession(), "UPDATE NOTICEMASTER SET ,SIGNIMGFLG=?  WHERE ID=? ",new SerializerWriteBasic(new Datas[]{,Datas.BOOLEAN, Datas.STRING })).exec(val[23]);
                           JOptionPane.showMessageDialog(null, "Notice " +val[0]+" updated Successfully.");
        }
      else
      {
          JOptionPane.showMessageDialog(this, "Notice Update canceled.");
      }
    }
    private Object [] fetchValues() throws BasicException
    {
            Object [] values = null;
             boolean flag;
             boolean due_pass=false;
             boolean dp_dm=false;
             boolean date_dm=false;
             boolean deactivateMem = false;
             int finalnote = 0;
             Date dt=null;
             int res=0;
             int dp = 0;
             String facility = null;
             String headerToList = "";
             
             boolean PostalFlagValue = false;
             
             
             
             
             if(NoticeName.getText().length()>0  && (Day1.isSelected() || Month1.isSelected()) && Payment.getText().length()>0 && (Day2.isSelected() || Month2.isSelected()) && (nNtoDisplayInNotice.getText().length()>0))
             {
                 if(First.isSelected())
                 {
                     if(jComboBox4.getSelectedIndex()==-1)
                     {
                         throw new BasicException("completeForm");
                     }
                     if(!(DP.getText().length()>0))
                     {
                         throw new BasicException("completeForm");
                     }
                     
                     if(typeOfMem.getText().length()<0)
                     {
                         throw new BasicException("completeForm");
                     }
                     else if(typeOfMem.getText().equals(",") ||typeOfMem.getText().trim().equals("") )
                     {
                          throw new BasicException("completeForm");
                     }
                 }
                 flag = false;
              if(Month2.isSelected())
              {
                  date_dm=true;
              }
              
               String accountid=null;
                 if(Save.isVisible())
                 {
                    accountid=UUID.randomUUID().toString();
                 }
                 else
                 {
                     accountid = NoticeId.getText();
                 }
                    String noticeName = NoticeName.getText();
                 int payment = Integer.parseInt(Payment.getText());
                 String addNote = AddNotes.getText();
                 String typeOfmember [] = typeOfMem.getText().split("\r\n");
                 String typeMem = "";
             for(int i =0 ; i<typeOfmember.length ; i++)
               {
                    typeMem = typeMem+typeOfmember[i]+"\r\n,";
                }
             
             if(First.isSelected())
             {
                facility = jComboBox4.getSelectedItem().toString();
                if(Month1.isSelected())
              {
                  dp_dm=true;
              }
                dp = Integer.parseInt(DP.getText());
             }   
                 boolean finaln = false;
                 String refToParent=null;
                String parentId=null;
                String sign = "";
                String desg = "";
              
                Boolean signImgFlg=false;//pratima
                 if(!First.isSelected())
                 {
                     
                     String name1 = null;
                     
                     if(jComboBox3.getSelectedIndex()==-1)
                     {
                       name1 =   pNoticeName.getSelectedItem().toString();
                     }
                     else
                     {
                         name1 = jComboBox3.getSelectedItem().toString();
                     }
                     
                     for (Iterator it = noticeList2.iterator(); it.hasNext();) {
                       NoticeMasterBean nmb = (NoticeMasterBean) it.next();
                       if(nmb.getName().equals(name1))
                       {
                           dp = nmb.getDp_num();
                           dp_dm = nmb.isDp_dm();
                           refToParent = nmb.getName();
                           typeMem = nmb.getTypeofmem(); 
                           facility = nmb.getFacility();
                           parentId=nmb.getId();
                           break;
                       }
                     }
                 }
                 if(!Save.isVisible())
                 {
                     for (Iterator it = noticeList.iterator(); it.hasNext();) {
                       NoticeMasterBean nmb = (NoticeMasterBean) it.next();
                       if(nmb.getId().trim().contains(NoticeId.getText()))
                       {
                           dt = nmb.getCrdate();
                           break;
                       }
                     }
                 }
                 
                 if(!Final.isSelected())
                    {
                        finalnote = JOptionPane.showConfirmDialog(null, "Is it Final Notice? ", "Final Notice", JOptionPane.YES_NO_OPTION);
                    } 
                 if(finalnote==0)
                 {
                    finaln=true; 
                 }
                if(jRadioText.isSelected()){//pratima
                 if(Signature.getText().length()>0)
                 {
                     sign = Signature.getText();
                 }}
                if(jRadioImage.isSelected()){
                 signImgFlg=true;
                }
                 if(Designation.getText().length()>0)
                 {
                     desg = Designation.getText();
                 }
                 if(Save.isVisible())
                 {
                dt = new Date();
                 }
                 if(dYes.isSelected())
                 {
                     deactivateMem = true;
                 }   
                 
                 if(AddNotes1.getText()!=null)
                 {
                     headerToList = AddNotes1.getText();
                 }
                 
                 
 // edited by aakash .........
                 
                 
                 if(postalamt_text.getText()!=null && postalamt_text.getText().length()>0){
                      PostalAmount = Double.parseDouble(postalamt_text.getText());
                 }
                 
                 if(postal_yes.isSelected()){
                     PostalFlagValue = true;
                 }
                 else{
                     PostalFlagValue=false;
                 }
                 
                 
                 
                 
                 
                 if(account_combo.getSelectedIndex()!=-1){
                     PostalAccountName = account_combo.getSelectedItem().toString();
                     
                     for(int i=0;i<TempAccList.size();i++){
                         String Name = TempAccList.get(i).getName().toString();
                         if(Name.equals(PostalAccountName)){
                             PostalAccountId = TempAccList.get(i).getId().toString();
                             System.out.println("PostalAccountId  " +PostalAccountId);
                             break;
                         }
                     }
                     
                     
                 }
                 else{
                     PostalAccountName = null;
                     PostalAccountId=null;
                 }
                 
                 
                 
                 
                 
                //values = new Object [] { accountid, noticeName, due_pass, dp_dm, dp,  date_dm, payment, addNote,  typeMem,facility, finaln, refToParent, parentId, dt, true, sign, desg, nNtoDisplayInNotice.getText(), deactivateMem, headerToList , PostalFlagValue ,PostalAmount , PostalAccountId};  
                      values = new Object [] { accountid, noticeName, due_pass, dp_dm, dp,  date_dm, payment, addNote,  typeMem,facility, finaln, refToParent, parentId, dt, true, sign, desg, nNtoDisplayInNotice.getText(), deactivateMem, headerToList , PostalFlagValue ,PostalAmount , PostalAccountId,signImgFlg};  //pratima
    }
        else
             {
                 throw new BasicException("completeForm");
             }
             return values;
    }
    
    public void assignExistingValues()
    {
        NoticeName.setText(NoticeName.getText());
        NoticeId.setText(NoticeId.getText());
        First.setSelected(First.isSelected());
        Other.setSelected(Other.isSelected());
        Final.setSelected(Final.isSelected());
        typeOfMem.setText(typeOfMem.getText());
        jComboBox3.setSelectedItem(jComboBox3.getSelectedItem());
        DP.setText(""+DP.getText());
        Day1.setSelected(Day1.isSelected());
        Day2.setSelected(Day2.isSelected());
        Month1.setSelected(Month1.isSelected());
        Month2.setSelected(Month2.isSelected());
        Payment.setText(""+Payment.getText());
        AddNotes.setText(AddNotes.getText());
        Signature.setText(Signature.getText());
        Designation.setText(Designation.getText());
    }

    private int deleteNotice(NoticeMasterBean nmb2, String name) throws BasicException {
        int num =0;
        int cnt =0;
        String id = nmb2.getId();
        List<NoticeMasterBean> updateList =  ntmt.getNoticeListToDeactivate(id, s);//new StaticSentence(s, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE " + "FROM NOTICEMASTER  WHERE PARENTID=? and CRDATE>=? AND ACTIVATE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(new Object[]{id,ts});
                
                 String ss = "?";
                if(updateList.size()>0)
                {
                 ss = "  along with another "+   (updateList.size())+ " related notices " + updateList.toString() + " ?";
                }
                if(JOptionPane.showConfirmDialog(this, "Do you want to deactivate " + name + ss, null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    num = new PreparedSentence(s, "UPDATE NOTICEMASTER SET ACTIVATE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=? WHERE ID = ? AND ACTIVATE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), id});
                    for (Iterator<NoticeMasterBean> it = updateList.iterator(); it.hasNext();) {
                        NoticeMasterBean noticeMasterBean = it.next();
                      num = num + new PreparedSentence(s, "UPDATE NOTICEMASTER SET ACTIVATE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=? WHERE PARENTID=? OR ID = ? AND ACTIVATE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), noticeMasterBean.getId(),noticeMasterBean.getId()});
                    }
                    JOptionPane.showMessageDialog(this, "Number of notices deactivated = " + num);
                }
                else
                {
                     JOptionPane.showMessageDialog(this, "Notice Update Canceled.");
                }
                 return cnt;
    }
    
class FixedSizeDocument extends PlainDocument{
private int max = 10;
public FixedSizeDocument(int max)
{
this.max = max;
charCount.setText(""+(max-getLength()));
}

@Override
public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
{
    if(!str.equals("\n"))
    {
if (getLength()+str.length()>max)
{
// If it does, then truncate it
str = str.substring(0, max - getLength());
}

super.insertString(offs, str, a);
charCount.setText(""+(max-getLength()));

}}

@Override
protected void removeUpdate(DefaultDocumentEvent chng) {
charCount.setText(""+(max-getLength()+1));
}

}

class FixedSizeDocument2 extends PlainDocument{
private int max = 10;
public FixedSizeDocument2(int max)
{
this.max = max;
charCount1.setText(""+(max-getLength()));
}

@Override
public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
{
    if(!str.equals("\n"))
    {
if (getLength()+str.length()>max)
{
// If it does, then truncate it
str = str.substring(0, max - getLength());
}

super.insertString(offs, str, a);
charCount1.setText(""+(max-getLength()));

}}

@Override
protected void removeUpdate(DefaultDocumentEvent chng) {
charCount1.setText(""+(max-getLength()+1));
}

}




//  EDITED BY AKASH FOR POSTAL CHARGES......


public void ButtonGroup(){
    ButtonGroup bg = new ButtonGroup();
    bg.add(postal_yes);
    bg.add(postal_no);
}
public void ButtonGroup1(){
    ButtonGroup bg = new ButtonGroup();
    bg.add(jRadioText);
     bg.add(jRadioImage);
        }

}



    

