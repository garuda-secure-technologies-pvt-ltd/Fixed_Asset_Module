/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuestCategoryDefiner.java
 *
 * Created on May 18, 2009, 11:18:12 AM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class GuestCategoryDefiner extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form GuestCategoryDefiner */
    private AppView m_App;
    private DataLogicFacilities dlfac;
    private final static String[] TABLEHEADERS = {"Name", "HSN Code","Max Guest","Rate","Entrance Check","Member Kiosk Entry","Receipt Sequence","Max Receipt Sequence","Created By","Created Date","Deactivated By","Deactivated Date","Account", "TaxCategory"};
//    private GuestCategory gcat1;
       private List<GuestCategory> gcat;
    private ComboBoxValModel accmodel;
    private String id;
    public List<String> TaxCategoryList = new ArrayList<String>();
    
    public ComboBoxValModel GuestBoxValModel;
    
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
   
    int AllowTabBilling = 0;
//    private double TaxRate1;
//    private double TaxRate2;
//    private double TaxRate3;
//   
//    private double taxamt1;   
     private Object TotalAmount;
    private Boolean cascade1;
    private Boolean cascade2;
    private Boolean basic1;
    private Boolean basic2;
    
    
          
    
    
    public GuestCategoryDefiner() {
        initComponents();
    }
   public void init(AppView app) throws BeanFactoryException {
        m_App=app;
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         jButton2.setText("Deactivate");
         accmodel = new ComboBoxValModel();
   }
     public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

   public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        loadData();
    }
    private void loadData() throws BasicException {

       
           accmodel = new ComboBoxValModel(dlfac.getaccounts());
          account.setModel(accmodel);
          name.setText(null);
          hsncode.setText(null);
          guestmax.setText(null);
          rate.setText(null);
          jCheckBox1.setSelected(false);
          rseq.setText(null);
          rseq.setEditable(true);
          jButton4.setVisible(false);
          jButton1.setVisible(true); 
           jRadioButton1.setVisible(true);
          jRadioButton2.setVisible(true);
          jRadioButton3.setVisible(true);
          jRadioButton4.setVisible(true);
            
//            jRadioButton1.setSelected(true);
//          jRadioButton2.setSelected(false);
//          jRadioButton3.setSelected(true);
//          jRadioButton4.setSelected(false);
//           
          
          TotalAmount_Text.setText(null);
          id=null;
          TaxCategoryList = new ArrayList<String>();
          
          TaxCategoryList=getTaxCategoryList(m_App);
          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
          guestTaxCat_Combo.setModel(GuestBoxValModel);
         guestTaxCat_Combo.setSelectedIndex(0);  
           
           TaxCategoryList=getTaxCategoryList(m_App);
          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
          guestTaxCat1_Combo.setModel(GuestBoxValModel);
          guestTaxCat1_Combo.setSelectedIndex(-1); 
          
           TaxCategoryList=getTaxCategoryList(m_App);
          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
          guestTaxCat2_Combo.setModel(GuestBoxValModel);
          guestTaxCat2_Combo.setSelectedIndex(-1);
//          jRadioButton2.setSelected(gcategory.getcascade1());
//                     jRadioButton4.setSelected(gcategory.getcascade2());
//              
//                 jRadioButton1.setSelected(gcategory.getbasic1());
//                   jRadioButton3.setSelected(gcategory.getbasic2());
//GuestCategory gcategory=gcat.get(WIDTH);
//              if(gcategory.getbasic1()==true){
//                jRadioButton1.setSelected(gcategory.getbasic1());
//                }  
//                if(gcategory.getcascade1()==true){
//                jRadioButton2.setSelected(gcategory.getcascade1());
//                }
//                  if(gcategory.getbasic2()==true){
//                jRadioButton3.setSelected(gcategory.getbasic2());
//                } 
//                  
//                  if(gcategory.getcascade2()==true){
//                jRadioButton4.setSelected(gcategory.getcascade2());
//                }
         
    }
    
      public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
      private  AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(TABLEHEADERS[column]);
                //sanjeev :commented above line and added below line for missingresourceException
                return TABLEHEADERS[column];
            }
            public int getRowCount() {
                return gcat.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                GuestCategory l = gcat.get(row);

                switch (column) {

                case 0: return l.getname();
                case 1: return l.getHsncode();
                case 2: return l.getmaxguest();
                case 3: return l.getrate();
                case 4: return l.getecheck();
                case 5: return l.getKcheck();
                case 6: return l.getReceiptSeq();
                case 7: return l.getMaxReceiptSeq();
                case 8: return l.getCreatedBy();
                case 9: return l.getCrdate();
                case 10: return l.getDeactivatedBy();
                case 11: return l.getDeactDate();
                case 12: return getaccountName(l.getAccount());
                case 13: return getTaxNameByID(l.getTaxCategory());
//                 case 14: return GetTaxRateByTaxCatID(l.getTaxCategory());
//                  case 15: return GetTaxRateByTaxCatID1(l.getTaxCategory());
//                case 16: return GetTaxRateByTaxCatID2(l.getTaxCategory());
//                
                

                default: return null;
                }
            }
        };
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rate = new javax.swing.JTextField();
        guestmax = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rseq = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        account = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        kioskcontrol = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        guestTaxCat_Combo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        TotalAmount_Text = new javax.swing.JTextField();
        tab_checkBox = new javax.swing.JCheckBox();
        hsncode = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        guestTaxCat1_Combo = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        guestTaxCat2_Combo = new javax.swing.JComboBox<>();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();

        setAutoscrolls(true);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setText("Name ");

        jLabel3.setText("Maximum Guest");

        jLabel4.setText("Rate ");

        jLabel2.setText("Entrance Control");

        rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateActionPerformed(evt);
            }
        });
        rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rateKeyReleased(evt);
            }
        });

        guestmax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestmaxActionPerformed(evt);
            }
        });

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Yes");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Receipt Sequence");

        jLabel6.setText("Account");

        jButton4.setText("Save Changes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setText("Allow Through Member Kiosk");

        kioskcontrol.setText("Yes");

        jLabel8.setText("Tax Category 1 : ");

        guestTaxCat_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        guestTaxCat_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                guestTaxCat_ComboItemStateChanged(evt);
            }
        });
        guestTaxCat_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestTaxCat_ComboActionPerformed(evt);
            }
        });

        jLabel9.setText("Total Amount With Tax : ");

        TotalAmount_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmount_TextActionPerformed(evt);
            }
        });
        TotalAmount_Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TotalAmount_TextKeyReleased(evt);
            }
        });

        tab_checkBox.setText("Allow billing through android tabs ?");
        tab_checkBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tab_checkBoxItemStateChanged(evt);
            }
        });
        tab_checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tab_checkBoxActionPerformed(evt);
            }
        });

        hsncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsncodeActionPerformed(evt);
            }
        });

        jLabel10.setText("HSN/SAC Code");

        jLabel11.setText("Tax Category 2:");

        guestTaxCat1_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        guestTaxCat1_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                guestTaxCat1_ComboItemStateChanged(evt);
            }
        });
        guestTaxCat1_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestTaxCat1_ComboActionPerformed(evt);
            }
        });

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Basic");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Cascade");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jRadioButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRadioButton2KeyReleased(evt);
            }
        });

        jLabel12.setText("Tax Category 3:");

        guestTaxCat2_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        guestTaxCat2_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                guestTaxCat2_ComboItemStateChanged(evt);
            }
        });
        guestTaxCat2_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestTaxCat2_ComboActionPerformed(evt);
            }
        });

        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Basic");
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Cascade");
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jRadioButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRadioButton4KeyReleased(evt);
            }
        });

        jButton6.setText("Reset");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(41, 41, 41)
                .addComponent(jButton4)
                .addGap(89, 89, 89))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(rseq, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(guestmax, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hsncode, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(kioskcontrol))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(tab_checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(31, 31, 31)
                        .addComponent(TotalAmount_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRadioButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(guestTaxCat2_Combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(guestTaxCat_Combo, javax.swing.GroupLayout.Alignment.LEADING, 0, 265, Short.MAX_VALUE)
                                    .addComponent(guestTaxCat1_Combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(208, 208, 208))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(hsncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(guestmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rseq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(kioskcontrol))
                .addGap(8, 8, 8)
                .addComponent(tab_checkBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(guestTaxCat_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guestTaxCat1_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jButton6))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(guestTaxCat2_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalAmount_Text, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create New", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Show All");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(27, 27, 27))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        try{
            if(jCheckBox2.isSelected() == true){
         gcat=dlfac.getGuestCategoryALL();
         jTable1.setModel(getTableModel());}
            else
            {
                gcat=dlfac.getGuestCategoryActive();
                jTable1.setModel(getTableModel());
            }
//            reset2();
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int index=jTable1.getSelectedRow();
       if(index>=0){
           GuestCategory gcategory=gcat.get(index);
           try{
             if(gcategory.getDeactivatedBy()==null){
              if(JOptionPane.showConfirmDialog(this, "Do you want to deactivate the guest category", "Are you sure", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
               new PreparedSentence(m_App.getSession(), "UPDATE GUESTCAT SET DEACTBY=? ,DEACTDATE=?,ACTIVE=FALSE WHERE ID=?"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(),new Date(),gcategory.getid()});
               }
             }
            if(jCheckBox2.isSelected() == true){
         gcat=dlfac.getGuestCategoryALL();
         jTable1.setModel(getTableModel());}
            else
            {
                gcat=dlfac.getGuestCategoryActive();
                jTable1.setModel(getTableModel());
            }
           }catch(Exception e){
             e.printStackTrace();
           }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       int index=jTable1.getSelectedRow();
       if(index>=0){
           GuestCategory gcategory=gcat.get(index);
           try{
             if(gcategory.getDeactivatedBy()==null){
                   revalidate();  
                 jButton1.setVisible(false);
                 jButton4.setVisible(true);  
                  if(gcategory.getbasic1()==true){
                    jRadioButton1.setSelected(true);
                    jRadioButton2.setSelected(false);
                 }
                 else{
                     jRadioButton1.setSelected(false);
                     jRadioButton2.setSelected(true);
                 }   
                 
                  if(gcategory.getbasic2()==true){
                    jRadioButton3.setSelected(true);
                     jRadioButton4.setSelected(false);
                 }
                 else{
                     jRadioButton3.setSelected(false);
                      jRadioButton4.setSelected(true);
                 }    
                  
                  if(gcategory.getcascade1()==true){
                    jRadioButton2.setSelected(true);
                    jRadioButton1.setSelected(false);
                 }
                 else{
                     jRadioButton2.setSelected(false);
                     jRadioButton1.setSelected(true);
                 }    
                  
                  if(gcategory.getcascade2()==true){
                    jRadioButton4.setSelected(true);
                    jRadioButton3.setSelected(false);
                 }
                 else{
                     jRadioButton4.setSelected(false);
                     jRadioButton3.setSelected(true);
                 }  
//                 jRadioButton1.setVisible(true); 
//                 jRadioButton2.setVisible(true);
//                 jRadioButton3.setVisible(true); 
//                 jRadioButton4.setVisible(true); 
                 
                
                 jTabbedPane1.setSelectedIndex(0);
                 id = gcategory.getid();
                 name.setText(gcategory.getname());
                 hsncode.setText(gcategory.getHsncode());
                 guestmax.setText(String.valueOf(gcategory.getmaxguest()));
                 jCheckBox1.setSelected(gcategory.getecheck());
                 rseq.setEditable(false);
                 rseq.setText(gcategory.getReceiptSeq());
                 rate.setText(String.valueOf(gcategory.getrate()));
                 accmodel.setSelectedKey(gcategory.getAccount());
                 kioskcontrol.setSelected(gcategory.getKcheck());
              guestTaxCat_Combo.setSelectedIndex(0);
                  guestTaxCat1_Combo.setSelectedIndex(-1);
                   guestTaxCat2_Combo.setSelectedIndex(-1);
                 TotalAmount_Text.setText(String.valueOf(gcategory.getTotalAmount()));
                 
                 
                 



                 
                     
                 if(gcategory.getAllowTabBilling()==1){
                     tab_checkBox.setSelected(true);
                 }
                 else{
                     tab_checkBox.setSelected(false);
                 }  
                 

//                     jRadioButton2.setSelected(gcategory.getcascade1());
//                     jRadioButton4.setSelected(gcategory.getcascade2());
//              
//                 jRadioButton1.setSelected(gcategory.getbasic1());
//                   jRadioButton3.setSelected(gcategory.getbasic2());

                 
                 if(gcategory.getTaxCategory()!=null && gcategory.getTaxCategory().trim().length()>0){
                     String TaxCatID = gcategory.getTaxCategory();
                     String TaxName = getTaxNameByID(TaxCatID);  
                     
                     for(int i=0;i<TaxCategoryList.size();i++){
                         String x = TaxCategoryList.get(i).toString();
                         if(x.equals(TaxName)){
                             guestTaxCat_Combo.setSelectedIndex(i);
                            break;
                            
                         }
                     }
                     
                 }
                if(gcategory.getTaxCategory2()!=null && gcategory.getTaxCategory2().trim().length()>0){
                     String TaxCatID1 = gcategory.getTaxCategory2();
                     String TaxName1= getTaxNameByID1(TaxCatID1);
                     for(int i=0;i<TaxCategoryList.size();i++){
                         String x = TaxCategoryList.get(i).toString();
                         if(x.equals(TaxName1)){
                             guestTaxCat1_Combo.setSelectedIndex(i);
                             break;
                         }
                     }
                 } 

//                                 if(gcategory.getbasic1()!=null){
//                jRadioButton1.setSelected(gcategory.getbasic1());
//                }  
//                if(gcategory.getcascade1()!=null){
//                jRadioButton2.setSelected(gcategory.getcascade1());
//                }
               
                 if(gcategory.getTaxCategory3()!=null && gcategory.getTaxCategory3().trim().length()>0){
                     String TaxCatID2= gcategory.getTaxCategory3();
                     String TaxName2 = getTaxNameByID2(TaxCatID2);
                     for(int i=0;i<TaxCategoryList.size();i++){
                         String x = TaxCategoryList.get(i).toString();
                         if(x.equals(TaxName2)){
                             guestTaxCat2_Combo.setSelectedIndex(i);
                            break;
//                         }
                     
                         }
                     }
                  
             }   
                 
                
//                  if(gcategory.getbasic2()!=null){
//                jRadioButton3.setSelected(gcategory.getbasic2());
//                } 
//                  
//                  if(gcategory.getcascade2()!=null){
//                jRadioButton4.setSelected(gcategory.getcascade2());
//                }
                 
                     
//                 if(jRadioButton1.isSelected()){
//                 jRadioButton1.setSelected(gcategory.getbasic1());
//                 } 
//                 else if(jRadioButton3.isSelected()){
//                   jRadioButton3.setSelected(gcategory.getbasic2());
//                  }  
//                   else if(jRadioButton2.isSelected()){
//                     jRadioButton2.setSelected(gcategory.getcascade1());
//                   } 
//                   else {if(jRadioButton4.isSelected()){
//                     jRadioButton4.setSelected(gcategory.getcascade2());
//                    }
//                   }
           } 
             
           }catch(Exception e){
             e.printStackTrace();
           }
    }//GEN-LAST:event_jButton3ActionPerformed
    }
    
    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        // TODO add your handling code here:
         try {
            if(jCheckBox2.isSelected() == true){
         gcat=dlfac.getGuestCategoryALL();
         jTable1.setModel(getTableModel());}
            else if(jCheckBox2.isSelected() == false)
            {
                gcat=dlfac.getGuestCategoryActive();
                jTable1.setModel(getTableModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void hsncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsncodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hsncodeActionPerformed

    private void tab_checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tab_checkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tab_checkBoxActionPerformed

    private void tab_checkBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tab_checkBoxItemStateChanged
        if(tab_checkBox.isSelected()){
            AllowTabBilling=1;
        }
        else{
            AllowTabBilling=0;
        }
    }//GEN-LAST:event_tab_checkBoxItemStateChanged

    private void TotalAmount_TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalAmount_TextKeyReleased
  


// StringBuffer billnums=new StringBuffer();
//                                     double taxamt1=0.0;
//  double taxamt2=0.0;
//  double taxamt3=0.0;
//  double taxamt=0.0;
//  Double TaxRate=0.0;
//  Double TaxRate2=0.0;
//          Double TaxRate3=0.0;
//           Double GuestRate=0.0;
//  
//                                    if (guestTaxCat_Combo.getSelectedItem() != null) {
//                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
//                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
//                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
//                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
//                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
//                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
//                                    }
//if (guestTaxCat2_Combo.getSelectedItem() != null) {
//                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
//                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
//                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
//                                    }
// if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }
// if (guestTaxCat1_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      if(jRadioButton1.isSelected()){
//            taxamt2= Math.abs(GuestRate*TaxRate2);
//      }
//      else{
//           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//      }
//    
//      
// }

        

//        if(guestTaxCat2_Combo.getSelectedIndex()!=-1){
//            String TaxCatName = guestTaxCat2_Combo.getSelectedItem().toString();
//            String TaxCatID = getTaxCatIdByName2(m_App, TaxCatName);
//            Double TaxRate = GetTaxRateByTaxCatID2(TaxCatID);

            if(TotalAmount_Text.getText()!=null ){
                try{
                    Double TotalAmount = Double.parseDouble(TotalAmount_Text.getText());
//                    Double GuestFee = 0.00;
                    //if(rate.getText()!=null && rate.getText().trim().length()>0){
                        //   GuestFee=Double.parseDouble(rate.getText().trim());
                        //}
//                    GuestFee = TotalAmount;
//                    rate.setText(decimalFormat.format(TotalAmount));

                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Enter a valid number", "Wrong number Format", JOptionPane.WARNING_MESSAGE);
                    TotalAmount_Text.setText(null);

                }
            }
//        }
        else{
            JOptionPane.showMessageDialog(this, "Please select Tax first", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_TotalAmount_TextKeyReleased

    private void guestTaxCat_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_guestTaxCat_ComboItemStateChanged

double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
 double TaxRate=0.00;
 double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;   
            
                                              
                     
                      if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
                                    TotalAmount1 = GuestRate+taxamt ;

                                   TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }
//                      else{  
//                          JOptionPane.showMessageDialog(this, " Select Required correctly  ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
//                      }
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
                                                   TotalAmount1 = GuestRate+taxamt ;

                                                    TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
                                            TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }

//                                    }
// if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }
// if (guestTaxCat1_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      if(jRadioButton1.isSelected()){
//            taxamt2= Math.abs(GuestRate*TaxRate2);
//      }
//      else{
//           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//      }
//    
//      
// }

if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
}
      
}

//    double GuestRate=0.0;
//        
//        double  TaxRate=0.00;
//        double taxamt1=0.00;
//         double taxamt=0.00;
//        if (guestTaxCat_Combo.getSelectedItem() != null) {
//                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
//                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
//                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//  
//  if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }else{ taxamt1=0.00;}
//  taxamt=taxamt1;
// Double TotalAmount1 = GuestRate+taxamt ;
////
//                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//        if(guestTaxCat_Combo.getSelectedIndex()!=-1 && guestTaxCat1_Combo.getSelectedIndex()!=-1 && guestTaxCat1_Combo.getSelectedIndex()!=-1){
//            String TaxCatName1 = guestTaxCat_Combo.getSelectedItem().toString();
//            String TaxCatID1 = getTaxCatIdByName(m_App, TaxCatName1);
//            Double GuestRate = 0.00;
//            Double TaxRate1 = GetTaxRateByTaxCatID(TaxCatID1);
//            Double T1=0.00;
//            if(guestTaxCat_Combo.getSelectedIndex()!=-1 ){
//                    
//                    T1=TaxRate1;
//                    
//            }
//             
//        
//            }
//            try{
//
//                if(rate.getText()!=null && rate.getText().trim().length()>0){
//                    GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//                
//
//                Double TotalTax= T1+T2+T3;
//
//                Double TotalAmount = (GuestRate+TotalTax) ;
//
//                TotalAmount_Text.setText(decimalFormat.format(TotalAmount));
//            }
//            catch(NumberFormatException e){
//                JOptionPane.showMessageDialog(this, " Enter Guest charges correctly  ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//}
    }//GEN-LAST:event_guestTaxCat_ComboItemStateChanged


        
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          try{
           
           if(id!=null){
                if(rseq.getText().length()>0 && account.getSelectedIndex()!=-1){
                    Date d=new Date();
                  
                    AccountMasterExt acc = (AccountMasterExt) accmodel.getSelectedItem();
                    boolean cascade1=false;
                     boolean cascade2=false; 
                       boolean basic1 = false;     
                      boolean basic2 = false;
                       int index=jTable1.getSelectedRow();
//                         GuestCategory gcategory=gcat.get(index);
       if(index>=0){
           GuestCategory gcategory=gcat.get(index);
           
           if(jRadioButton2.isSelected()){
                     cascade1=true;
                       basic1=false;
//                      if(cascade1!=gcategory.getcascade1()){
//                          gcategory.setcascade1(cascade1);
//                           gcategory.setbasic1(basic1);
                          
                          
                     // }
                      }
                      if(jRadioButton4.isSelected()){
                       cascade2=true;
                       basic2=false;
//                       if(cascade2!=gcategory.getcascade2()){
//                          gcategory.setcascade2(cascade2);
//                           gcategory.setbasic2(basic2);
                          
                          
                    //  }
                      
                      }  
                     if(jRadioButton1.isSelected()){
                       basic1=true;
                    cascade1=false;
//                  if(basic1!=gcategory.getbasic1()){
//                          gcategory.setbasic1(basic1);
//                           gcategory.setcascade1(cascade1);
//                          
//                          
//                      }
                      
                      }  
                      if(jRadioButton3.isSelected()){
                       basic2=true;
                      cascade2=false;
//                      if(basic2!=gcategory.getbasic2()){
//                          gcategory.setbasic2(basic2);
//                           gcategory.setcascade2(cascade2);
//                          
//                          
//                      }
                      
                      }
           }
           
           
      
//  GuestCategory gcategory=new  GuestCategory();
//DataLogicFacilities.getGuestCategory(gcat.);
                       
                    String TaxCatName = null;
                    String TaxCatID = null;
                    if(guestTaxCat_Combo.getSelectedIndex()!=-1){
                        TaxCatName=guestTaxCat_Combo.getSelectedItem().toString();
                        TaxCatID=getTaxCatIdByName(m_App, TaxCatName);
                    }  
                    String TaxCatName1 = null;
                    String TaxCatID1 = null;
                    if(guestTaxCat1_Combo.getSelectedIndex()!=-1){
                        TaxCatName1=guestTaxCat1_Combo.getSelectedItem().toString();
                        TaxCatID1=getTaxCatIdByName1(m_App, TaxCatName1);
                    }  
                    String TaxCatName2= null;
                    String TaxCatID2 = null;
                    if(guestTaxCat2_Combo.getSelectedIndex()!=-1){
                        TaxCatName2=guestTaxCat2_Combo.getSelectedItem().toString();
                        TaxCatID2=getTaxCatIdByName2(m_App, TaxCatName2);
                    }
                      
                      
                   
                     

                         if(jRadioButton2.isSelected() && jRadioButton4.isSelected()){
                      
                    Object[] value=new Object[]{name.getText(),hsncode.getText(),jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),acc.getid().toString(),kioskcontrol.isSelected(),TaxCatID,AllowTabBilling ,TaxCatID1,TaxCatID2,true,true,false,false, id};
                    new PreparedSentence(m_App.getSession()
                        , "UPDATE GUESTCAT SET NAME=?,HSN_CODE=?,ENTRANCECONTROL=?,MAXGUEST=?,RATE=?,ACCOUNT=?, MKBILL=?,TAXCAT=?  ,TAB=?,  TAXCAT2=? ,TAXCAT3=?,CASCADE1=?,CASCADE2=?,BASIC1=?,BASIC2=?  WHERE ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.INT, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING})
                    ).exec(value);
                    
                    }   
                         
                          if(jRadioButton2.isSelected() && jRadioButton3.isSelected()){
                      
                    Object[] value=new Object[]{name.getText(),hsncode.getText(),jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),acc.getid().toString(),kioskcontrol.isSelected(),TaxCatID,AllowTabBilling ,TaxCatID1,TaxCatID2,false,true,false,true, id};
                    new PreparedSentence(m_App.getSession()
                        , "UPDATE GUESTCAT SET NAME=?,HSN_CODE=?,ENTRANCECONTROL=?,MAXGUEST=?,RATE=?,ACCOUNT=?, MKBILL=?,TAXCAT=?  ,TAB=?,  TAXCAT2=? ,TAXCAT3=?,CASCADE1=?,CASCADE2=?,BASIC1=?,BASIC2=?  WHERE ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.INT, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING})
                    ).exec(value);
                    
                    }   
                           if(jRadioButton1.isSelected() && jRadioButton4.isSelected()){
                      
                    Object[] value=new Object[]{name.getText(),hsncode.getText(),jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),acc.getid().toString(),kioskcontrol.isSelected(),TaxCatID,AllowTabBilling ,TaxCatID1,TaxCatID2,false,true,true,false, id};
                    new PreparedSentence(m_App.getSession()
                        , "UPDATE GUESTCAT SET NAME=?,HSN_CODE=?,ENTRANCECONTROL=?,MAXGUEST=?,RATE=?,ACCOUNT=?, MKBILL=?,TAXCAT=?  ,TAB=?,  TAXCAT2=? ,TAXCAT3=?,CASCADE1=?,CASCADE2=?,BASIC1=?,BASIC2=?  WHERE ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.INT, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING})
                    ).exec(value);
                    
                    } 
                             if(jRadioButton1.isSelected() && jRadioButton3.isSelected()){
                      
                    Object[] value=new Object[]{name.getText(),hsncode.getText(),jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),acc.getid().toString(),kioskcontrol.isSelected(),TaxCatID,AllowTabBilling ,TaxCatID1,TaxCatID2,false,false,true,true, id};
                    new PreparedSentence(m_App.getSession()
                        , "UPDATE GUESTCAT SET NAME=?,HSN_CODE=?,ENTRANCECONTROL=?,MAXGUEST=?,RATE=?,ACCOUNT=?, MKBILL=?,TAXCAT=?  ,TAB=?,  TAXCAT2=? ,TAXCAT3=?,CASCADE1=?,CASCADE2=?,BASIC1=?,BASIC2=?  WHERE ID=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.INT, Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING})
                    ).exec(value);
                    
                    } 
                          
                    
                    
                    
                    
                   if(jCheckBox2.isSelected() == true){
         gcat=dlfac.getGuestCategoryALL();
         jTable1.setModel(getTableModel());
                   }
            else
            {
                gcat=dlfac.getGuestCategoryActive();
                jTable1.setModel(getTableModel());
            }
                   reset1();
                    loadData();
                }
            } 
        }catch(Exception e){
            e.printStackTrace();
        }

//         try{
//            if(id!=null){
//                if(rseq.getText().length()>0 && account.getSelectedIndex()!=-1){
//                    Date d=new Date();
//                    AccountMasterExt acc = (AccountMasterExt) accmodel.getSelectedItem();
//                     String TaxCatName = null;
//                    String TaxCatID = null;
//                if(guestTaxCat_Combo.getSelectedIndex()!=-1){
//                    TaxCatName=guestTaxCat_Combo.getSelectedItem().toString();
//                    TaxCatID=getTaxCatIdByName(m_App, TaxCatName);
//                }
////                 String TaxCatName2 = null;
////                String TaxCatID2 = null;
////                if(guestTaxCat1_Combo.getSelectedIndex()!=-1){
////                    TaxCatName2=guestTaxCat1_Combo.getSelectedItem().toString();
////                    TaxCatID2=getTaxCatIdByName1(m_App, TaxCatName2);
////                }
////                 String TaxCatName3 = null;
////                String TaxCatID3= null;
////                if(guestTaxCat2_Combo.getSelectedIndex()!=-1){
////                    TaxCatName3=guestTaxCat2_Combo.getSelectedItem().toString();
////                    TaxCatID3=getTaxCatIdByName2(m_App, TaxCatName3);
////                }
//                
//
//                   Object[] value=new Object[]{name.getText(),hsncode.getText(),jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),acc.getid().toString(),kioskcontrol.isSelected(),TaxCatID,AllowTabBilling,id};
//                    new PreparedSentence(m_App.getSession()
//                        , "UPDATE GUESTCAT SET NAME=?,HSN_CODE=?,ENTRANCECONTROL=?,MAXGUEST=?,RATE=?,ACCOUNT=?, MKBILL=?,TAXCAT=? , TAB=?  WHERE ID=?"
//                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING})
//                    ).exec(value);
//                   if(jCheckBox2.isSelected() == true){
//         gcat=dlfac.getGuestCategoryALL();
//         jTable1.setModel(getTableModel());}
//            else
//            {
//                gcat=dlfac.getGuestCategoryActive();
//                jTable1.setModel(getTableModel());
//            }
//                    loadData() ;
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // TODO add your handling code here:
       try{
//      if (guestTaxCat_Combo.getSelectedItem() == null) {
//        JOptionPane.showMessageDialog(this, " Select Required Field..!!! ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
//        }
//        else   
if(rseq.getText().length()>0 && account.getSelectedIndex()!=-1){
                Date d=new Date();
                AccountMasterExt acc = (AccountMasterExt) accmodel.getSelectedItem(); 
//                 boolean cascade1=false;
//       boolean cascade2=false; 
//           boolean basic1 = false;     
//            boolean basic2 = false;
                                              
                
//                 if(jRadioButton2.isSelected()){
//                      cascade1=true;
//                      }
//                      if(jRadioButton4.isSelected()){
//                      cascade2=true;
//                      }  
//                      if(jRadioButton1.isSelected()){
//                      basic1=true;
//                      }  
//                      if(jRadioButton3.isSelected()){
//                      basic2=true;
//                      }
 

                String TaxCatName = null;
                String TaxCatID = null;
                if(guestTaxCat_Combo.getSelectedIndex()!=-1){
                    TaxCatName=guestTaxCat_Combo.getSelectedItem().toString();
                    TaxCatID=getTaxCatIdByName(m_App, TaxCatName);
                }
                 String TaxCatName2 = null;
                String TaxCatID2 = null;
                if(guestTaxCat1_Combo.getSelectedIndex()!=-1){
                    TaxCatName2=guestTaxCat1_Combo.getSelectedItem().toString();
                    TaxCatID2=getTaxCatIdByName1(m_App, TaxCatName2);
                }
                 String TaxCatName3 = null;
                String TaxCatID3= null;
                if(guestTaxCat2_Combo.getSelectedIndex()!=-1){
                    TaxCatName3=guestTaxCat2_Combo.getSelectedItem().toString();
                    TaxCatID3=getTaxCatIdByName2(m_App, TaxCatName3);
                }  
                
                
               if(jRadioButton2.isSelected() && jRadioButton4.isSelected()){
                Object[] value=new Object[]{UUID.randomUUID().toString(),true,name.getText(),hsncode.getText(),0,jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),m_App.getAppUserView().getUser().getName(),d,rseq.getText(),0,acc.getid().toString(), kioskcontrol.isSelected(),TaxCatID,  AllowTabBilling,TaxCatID2,TaxCatID3,true,true,false,false};
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GUESTCAT(ID,ACTIVE,NAME,HSN_CODE,DAYS,ENTRANCECONTROL,MAXGUEST,RATE,CREATEDBY,CRDATE,RECEIPTSEQ,MRECEIPTSEQ,ACCOUNT,MKBILL ,TAXCAT ,TAB ,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING,Datas.BOOLEAN,Datas.STRING , Datas.INT,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})
                ).exec(value); 
               }   
               
               
               else if(jRadioButton2.isSelected() && jRadioButton3.isSelected()){
                Object[] value=new Object[]{UUID.randomUUID().toString(),true,name.getText(),hsncode.getText(),0,jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),m_App.getAppUserView().getUser().getName(),d,rseq.getText(),0,acc.getid().toString(), kioskcontrol.isSelected(),TaxCatID,  AllowTabBilling,TaxCatID2,TaxCatID3,true,false,false,true};
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GUESTCAT(ID,ACTIVE,NAME,HSN_CODE,DAYS,ENTRANCECONTROL,MAXGUEST,RATE,CREATEDBY,CRDATE,RECEIPTSEQ,MRECEIPTSEQ,ACCOUNT,MKBILL ,TAXCAT ,TAB ,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING,Datas.BOOLEAN,Datas.STRING , Datas.INT,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})
                ).exec(value); 
               } 
                else if(jRadioButton1.isSelected() && jRadioButton4.isSelected()){
                Object[] value=new Object[]{UUID.randomUUID().toString(),true,name.getText(),hsncode.getText(),0,jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),m_App.getAppUserView().getUser().getName(),d,rseq.getText(),0,acc.getid().toString(), kioskcontrol.isSelected(),TaxCatID,  AllowTabBilling,TaxCatID2,TaxCatID3,false,true,true,false};
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GUESTCAT(ID,ACTIVE,NAME,HSN_CODE,DAYS,ENTRANCECONTROL,MAXGUEST,RATE,CREATEDBY,CRDATE,RECEIPTSEQ,MRECEIPTSEQ,ACCOUNT,MKBILL ,TAXCAT ,TAB ,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING,Datas.BOOLEAN,Datas.STRING , Datas.INT,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})
                ).exec(value); 
               }
                 
                else{
                    
                Object[] value=new Object[]{UUID.randomUUID().toString(),true,name.getText(),hsncode.getText(),0,jCheckBox1.isSelected(),Integer.parseInt(guestmax.getText()),Double.parseDouble(rate.getText()),m_App.getAppUserView().getUser().getName(),d,rseq.getText(),0,acc.getid().toString(), kioskcontrol.isSelected(),TaxCatID,  AllowTabBilling,TaxCatID2,TaxCatID3,false,false,true,true};
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GUESTCAT(ID,ACTIVE,NAME,HSN_CODE,DAYS,ENTRANCECONTROL,MAXGUEST,RATE,CREATEDBY,CRDATE,RECEIPTSEQ,MRECEIPTSEQ,ACCOUNT,MKBILL ,TAXCAT ,TAB ,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.INT,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING,Datas.BOOLEAN,Datas.STRING , Datas.INT,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN,Datas.BOOLEAN})
                ).exec(value); 
                                 
                }
                 
               
                 
                 if(jCheckBox2.isSelected() == true){
         gcat=dlfac.getGuestCategoryALL();
         jTable1.setModel(getTableModel());}
            else
            {
                gcat=dlfac.getGuestCategoryActive();
                jTable1.setModel(getTableModel());
            }  
                
                 
                 reset();
                loadData() ;
            }
        }

        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a proper number", "Cannot Insert", JOptionPane.OK_OPTION);
        }
        catch(Exception e){
            e.printStackTrace();
        }
//  if (guestTaxCat_Combo.getSelectedItem() == null) {
//        JOptionPane.showMessageDialog(this, " Select Required correctly  ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
//        }
  
    }//GEN-LAST:event_jButton1ActionPerformed
 private void reset() {
       jRadioButton1.setSelected(true);
          jRadioButton2.setSelected(false);
          jRadioButton3.setSelected(true);
          jRadioButton4.setSelected(false);
           guestTaxCat_Combo.setSelectedIndex(-1);
           guestTaxCat1_Combo.setSelectedIndex(-1);
        guestTaxCat2_Combo.setSelectedIndex(-1);
    }private void reset1() {
       jRadioButton1.setSelected(true);
          jRadioButton2.setSelected(false);
          jRadioButton3.setSelected(true);
          jRadioButton4.setSelected(false);
       guestTaxCat_Combo.setSelectedIndex(-1);
           guestTaxCat1_Combo.setSelectedIndex(-1);
        guestTaxCat2_Combo.setSelectedIndex(-1);
    }
//    private void reset2() {
////      accmodel = new ComboBoxValModel(dlfac.getaccounts());
////          account.setModel(accmodel);
//          name.setText(null);
//          hsncode.setText(null);
//          guestmax.setText(null);
//          rate.setText(null);
//          jCheckBox1.setSelected(false);
//          rseq.setText(null);
//          rseq.setEditable(true);
//          jButton4.setVisible(false);
//          jButton1.setVisible(true); 
//           jRadioButton1.setVisible(true);
//          jRadioButton2.setVisible(true);
//          jRadioButton3.setVisible(true);
//          jRadioButton4.setVisible(true);
//            
////            jRadioButton1.setSelected(true);
////          jRadioButton2.setSelected(false);
////          jRadioButton3.setSelected(true);
////          jRadioButton4.setSelected(false);
////           
//          
//          TotalAmount_Text.setText(null);
//          id=null;
//          TaxCategoryList = new ArrayList<String>();
//          
////          TaxCategoryList=getTaxCategoryList(m_App);
////          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
////          guestTaxCat_Combo.setModel(GuestBoxValModel);
//         guestTaxCat_Combo.setSelectedIndex(0);  
//           
////           TaxCategoryList=getTaxCategoryList(m_App);
////          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
////          guestTaxCat1_Combo.setModel(GuestBoxValModel);
//          guestTaxCat1_Combo.setSelectedIndex(-1); 
//          
////           TaxCategoryList=getTaxCategoryList(m_App);
////          GuestBoxValModel = new ComboBoxValModel(TaxCategoryList);
////          guestTaxCat2_Combo.setModel(GuestBoxValModel);
//guestTaxCat2_Combo.setSelectedIndex(-1); 
//    }
    
    
    
    
    
    
    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void rateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rateKeyReleased
        try{
            Double x = Double.parseDouble(rate.getText());
          
             
            guestTaxCat_Combo.setSelectedIndex(0);
              
           
            guestTaxCat1_Combo.setSelectedIndex(-1);
             guestTaxCat2_Combo.setSelectedIndex(-1);
//               TotalAmount_Text.setText(x);
  TotalAmount_Text.setText(decimalFormat.format(x));
             
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, " Enter only numbers ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_rateKeyReleased

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:  
//         if(jRadioButton1.isSelected()){
//             jRadioButton2.setSelected(false);
//         }
         if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
        }
        else{
            jRadioButton2.setSelected(true);
        }
         
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void guestTaxCat1_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestTaxCat1_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guestTaxCat1_ComboActionPerformed

    private void guestTaxCat_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestTaxCat_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guestTaxCat_ComboActionPerformed

    private void guestTaxCat2_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestTaxCat2_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guestTaxCat2_ComboActionPerformed

    private void guestTaxCat1_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_guestTaxCat1_ComboItemStateChanged
        // TODO add your handling code here: 

double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
 double TaxRate=0.00;
 double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;   
            
                                              
                     
                      if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
                                    TotalAmount1 = GuestRate+taxamt ;

                                   TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }
//                      else{  
//                          JOptionPane.showMessageDialog(this, " Select Required correctly  ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
//                      }
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
                                                   TotalAmount1 = GuestRate+taxamt ;

                                                    TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
                                            TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }

//                                    }
// if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }
// if (guestTaxCat1_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      if(jRadioButton1.isSelected()){
//            taxamt2= Math.abs(GuestRate*TaxRate2);
//      }
//      else{
//           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//      }
//    
//      
// }

if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
}
      
}
    }//GEN-LAST:event_guestTaxCat1_ComboItemStateChanged

    private void guestTaxCat2_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_guestTaxCat2_ComboItemStateChanged
        // TODO add your handling code here:                   
//        double taxamt1=0.00;
//        double taxamt2=0.00;
//  double taxamt3=0.00;
//  double taxamt=0.00;
// double TaxRate=0.00;
// double TaxRate2=0.00;
//          double TaxRate3=0.00;
//           double GuestRate=0.00;
//           double TotalAmount1 =0.00;   
//            
//                                              
//                     
//                      if (guestTaxCat_Combo.getSelectedItem() != null) {
//                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
//                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
//                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
////                                    }
////                                                                        
////                                if (guestTaxCat_Combo.getSelectedItem() != null) {
//                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                    GuestRate =  Double.parseDouble(rate.getText()+"");
//                                     taxamt1= Math.abs(GuestRate*TaxRate);
//                                      taxamt=taxamt1;
//                                    TotalAmount1 = GuestRate+taxamt ;
//
//                                   TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//      
//                                            }
//                                  
//                                              }else{ taxamt1=0.00;}
//    
//                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
//                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
//                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
//                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
//                                          GuestRate =  Double.parseDouble(rate.getText()+"");
////                                    }    
////                                     
////                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
//                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
////                                         GuestRate =  Double.parseDouble(rate.getText()+"");
////                                             }
//                                                  if(jRadioButton1.isSelected()){
//                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
//                                                   taxamt=taxamt1+taxamt2;
//                                                   TotalAmount1 = GuestRate+taxamt ;
//
//                                                    TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//                                             }
//                                           else{
////                                                 if(jRadioButton2.isSelected()){   
////                                          taxamt2= Math.abs(GuestRate*TaxRate2);
//                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//                                             taxamt=taxamt1+taxamt2;
//                                            TotalAmount1 = GuestRate+taxamt ;
//
//                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                                     }
//                                                  
////                                                  }
//                                        }
//    
//      
// }
//
////                                    }
//// if (guestTaxCat_Combo.getSelectedItem() != null) {
////      if(rate.getText()!=null && rate.getText().trim().length()>0){
////                     GuestRate =  Double.parseDouble(rate.getText()+"");
////                }
////      taxamt1= Math.abs(GuestRate*TaxRate);
////      
//// }
//// if (guestTaxCat1_Combo.getSelectedItem() != null) {
////      if(rate.getText()!=null && rate.getText().trim().length()>0){
////                     GuestRate =  Double.parseDouble(rate.getText()+"");
////                }
////      if(jRadioButton1.isSelected()){
////            taxamt2= Math.abs(GuestRate*TaxRate2);
////      }
////      else{
////           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
////      }
////    
////      
//// }
//
//if (guestTaxCat2_Combo.getSelectedItem() != null) {
//                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
//                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
//                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
//// if (guestTaxCat2_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                
//       if(jRadioButton3.isSelected()){
//            taxamt3= Math.abs(GuestRate*TaxRate3);
//             taxamt=taxamt1+taxamt2+taxamt3;
//  TotalAmount1 = GuestRate+taxamt ;
//
//                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
//      }
//      else{
////           if(jRadioButton4.isSelected()){
////taxamt3= Math.abs(GuestRate*TaxRate3);
//           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
//           taxamt=taxamt1+taxamt2+taxamt3;
//  TotalAmount1 = GuestRate+taxamt ;
//
//                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//////      }
// 
//      
//    }
//}
//      
//}
//              
////taxamt=taxamt1+taxamt2+taxamt3;
////TotalAmount1 = GuestRate+taxamt ;
////
////                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////          if(guestTaxCat2_Combo.getSelectedIndex()!=-1){
////            String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
////            String TaxCatID3 = getTaxCatIdByName2(m_App, TaxCatName3);
////            Double GuestRate3 = 0.00;
////            try{
////
////                if(rate.getText()!=null && rate.getText().trim().length()>0){
////                    GuestRate3 =  Double.parseDouble(rate.getText()+"");
////                }
////
////                Double TaxRate3 = GetTaxRateByTaxCatID(TaxCatID3);
////
////                Double TotalAmount = (GuestRate3*TaxRate3) + GuestRate3;
////
////                TotalAmount_Text.setText(decimalFormat.format(TotalAmount));
////            }
////            catch(NumberFormatException e){
////                JOptionPane.showMessageDialog(this, " Enter Guest charges correctly  ", " Guest Charges", JOptionPane.ERROR_MESSAGE);
////            }
////        }
double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
 double TaxRate=0.00;
 double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;   
            
                                              
                     
                      if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
                                    TotalAmount1 = GuestRate+taxamt ;

                                   TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }else{ taxamt1=0.00;}
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
                                                   TotalAmount1 = GuestRate+taxamt ;

                                                    TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
                                            TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }

//                                    }
// if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }
// if (guestTaxCat1_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      if(jRadioButton1.isSelected()){
//            taxamt2= Math.abs(GuestRate*TaxRate2);
//      }
//      else{
//           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//      }
//    
//      
// }

if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
}
      
}
    }//GEN-LAST:event_guestTaxCat2_ComboItemStateChanged

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
         if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
        }
        else{
            jRadioButton1.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here: 
//        if(jRadioButton3.isSelected()){
//             jRadioButton4.setSelected(false);
//         }
        if(jRadioButton3.isSelected()){
            jRadioButton4.setSelected(false);
        }
        else{
            jRadioButton4.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:  
         if(jRadioButton4.isSelected()){
             jRadioButton3.setSelected(false);
         }
//         if(jRadioButton4.isSelected()){
//            jRadioButton3.setSelected(false);
//        }
        else{
            jRadioButton3.setSelected(true);
        }  
          
         
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void TotalAmount_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmount_TextActionPerformed
        // TODO add your handling code here:  
         TotalAmount_Text.setText(decimalFormat.format(TotalAmount));
          
    }//GEN-LAST:event_TotalAmount_TextActionPerformed

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
 double TaxRate=0.00;
 double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;   
            
                                              
                     
                      if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
                                    TotalAmount1 = GuestRate+taxamt ;

                                   TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }else{ taxamt1=0.00;}
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
                                                   TotalAmount1 = GuestRate+taxamt ;

                                                    TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
                                            TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }

//                                    }
// if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }
// if (guestTaxCat1_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      if(jRadioButton1.isSelected()){
//            taxamt2= Math.abs(GuestRate*TaxRate2);
//      }
//      else{
//           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//      }
//    
//      
// }

if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
}
      
}
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        // TODO add your handling code here: 
         double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
  double TaxRate=0.00;
  double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;
  
                                    if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
                                     TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }else{ taxamt1=0.00;}
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
                                                  TotalAmount1 = GuestRate+taxamt ;
                                                  TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
                                             TotalAmount1 = GuestRate+taxamt ;
                                             TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }



if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
       
}
      
}
    }//GEN-LAST:event_jRadioButton3ItemStateChanged

    private void rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateActionPerformed
        // TODO add your handling code here:   
        rate.setText(decimalFormat.format(rate));
    }//GEN-LAST:event_rateActionPerformed

    private void guestmaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestmaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guestmaxActionPerformed

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        // TODO add your handling code here:
 double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
 double TaxRate=0.00;
 double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;   
            
                                              
                     
                      if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
                                    TotalAmount1 = GuestRate+taxamt ;

                                   TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
                                                   TotalAmount1 = GuestRate+taxamt ;

                                                    TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
                                            TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }

//                                    }
// if (guestTaxCat_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      taxamt1= Math.abs(GuestRate*TaxRate);
//      
// }
// if (guestTaxCat1_Combo.getSelectedItem() != null) {
//      if(rate.getText()!=null && rate.getText().trim().length()>0){
//                     GuestRate =  Double.parseDouble(rate.getText()+"");
//                }
//      if(jRadioButton1.isSelected()){
//            taxamt2= Math.abs(GuestRate*TaxRate2);
//      }
//      else{
//           taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
//      }
//    
//      
// }

if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
}
      
}
        
                                        
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
        // TODO add your handling code here:
                double taxamt1=0.00;
        double taxamt2=0.00;
  double taxamt3=0.00;
  double taxamt=0.00;
  double TaxRate=0.00;
  double TaxRate2=0.00;
          double TaxRate3=0.00;
           double GuestRate=0.00;
           double TotalAmount1 =0.00;
  
                                    if (guestTaxCat_Combo.getSelectedItem() != null) {
                                        String TaxCatName = guestTaxCat_Combo.getSelectedItem().toString();
                                        String TaxCatID = getTaxCatIdByName(m_App, TaxCatName);
                                         TaxRate = GetTaxRateByTaxCatID(TaxCatID);
//                                    }
//                                                                        
//                                if (guestTaxCat_Combo.getSelectedItem() != null) {
                                       if(rate.getText()!=null && rate.getText().trim().length()>0){
                                    GuestRate =  Double.parseDouble(rate.getText()+"");
                                     taxamt1= Math.abs(GuestRate*TaxRate);
                                      taxamt=taxamt1;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
      
                                            }
                                  
                                              }
    
                                     if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                        String TaxCatName2 = guestTaxCat1_Combo.getSelectedItem().toString();
                                        String TaxCatID2= getTaxCatIdByName1(m_App, TaxCatName2);
                                         TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                                          GuestRate =  Double.parseDouble(rate.getText()+"");
//                                    }    
//                                     
//                                        if (guestTaxCat1_Combo.getSelectedItem() != null) {
                                         if(rate.getText()!=null && rate.getText().trim().length()>0){
//                                         GuestRate =  Double.parseDouble(rate.getText()+"");
//                                             }
                                                  if(jRadioButton1.isSelected()){
                                                  taxamt2= Math.abs(GuestRate*TaxRate2);
                                                   taxamt=taxamt1+taxamt2;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//                                             taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             }
                                           else{
//                                                 if(jRadioButton2.isSelected()){   
//                                          taxamt2= Math.abs(GuestRate*TaxRate2);
                                            taxamt2= Math.abs((GuestRate+taxamt1)*TaxRate2);
                                             taxamt=taxamt1+taxamt2;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
                                                     }
                                                  
//                                                  }
                                        }
    
      
 }



if (guestTaxCat2_Combo.getSelectedItem() != null) {
                                        String TaxCatName3 = guestTaxCat2_Combo.getSelectedItem().toString();
                                        String TaxCatID3= getTaxCatIdByName2(m_App, TaxCatName3);
                                         TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);
// if (guestTaxCat2_Combo.getSelectedItem() != null) {
      if(rate.getText()!=null && rate.getText().trim().length()>0){
                     GuestRate =  Double.parseDouble(rate.getText()+"");
                
       if(jRadioButton3.isSelected()){
            taxamt3= Math.abs(GuestRate*TaxRate3);
             taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
//taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
      }
      else{
//           if(jRadioButton4.isSelected()){
//taxamt3= Math.abs(GuestRate*TaxRate3);
           taxamt3= Math.abs((GuestRate+taxamt1+taxamt2)*TaxRate3);
           taxamt=taxamt1+taxamt2+taxamt3;
  TotalAmount1 = GuestRate+taxamt ;

                TotalAmount_Text.setText(decimalFormat.format(TotalAmount1));
////      }
 
      
    }
}
      
}
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jRadioButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton2KeyReleased

    }//GEN-LAST:event_jRadioButton2KeyReleased

    private void jRadioButton4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4KeyReleased

    public String getaccountName(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM ACCOUNTMASTER WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
            }
        }catch(Exception e){

        }
        return accName;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TotalAmount_Text;
    private javax.swing.JComboBox account;
    private javax.swing.JComboBox<String> guestTaxCat1_Combo;
    private javax.swing.JComboBox<String> guestTaxCat2_Combo;
    private javax.swing.JComboBox<String> guestTaxCat_Combo;
    private javax.swing.JTextField guestmax;
    private javax.swing.JTextField hsncode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox kioskcontrol;
    private javax.swing.JTextField name;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField rseq;
    private javax.swing.JCheckBox tab_checkBox;
    // End of variables declaration//GEN-END:variables

    
    
    // CHANGES MADE FOR GUEST CATEGORY TAX 
    
     public List getTaxCategoryList(AppView app ) throws BasicException{
          List<Object> Tax_List = new ArrayList<Object>();
           Tax_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM TAXCATEGORIES  ORDER BY ID",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Tax_List;
      }
     public List getTaxCategoryList1(AppView app ) throws BasicException{
          List<Object> Tax_List1 = new ArrayList<Object>();
           Tax_List1  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM TAXCATEGORIES  ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Tax_List1;
      }
     public List getTaxCategoryList2(AppView app ) throws BasicException{
          List<Object> Tax_List2 = new ArrayList<Object>();
           Tax_List2 = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM TAXCATEGORIES  ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Tax_List2;
      }
    public String getTaxCatIdByName(AppView app , String Name ) {
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
    
    
    public String getTaxCatIdByName1(AppView app , String Name ) {
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
     public String getTaxCatIdByName2(AppView app , String Name ) {
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
     
     public String getTaxNameByID2(String id){
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
    
     
    public Double GetTaxRateByTaxCatID(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
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
    
   
     
     public Double GetTaxRateByTaxCatID1(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
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
     
      public Double GetTaxRateByTaxCatID2(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
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
     
     
     
    
}
