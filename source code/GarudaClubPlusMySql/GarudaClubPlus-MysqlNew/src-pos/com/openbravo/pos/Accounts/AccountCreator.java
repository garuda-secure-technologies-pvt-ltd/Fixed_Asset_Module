/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AccountMasterManager.java
 *
 * Created on May 19, 2009, 10:11:47 AM
 */

package com.openbravo.pos.Accounts;

import com.openbravo.pos.clubmang.*;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteInteger;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class AccountCreator extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form AccountMasterManager */
    private static AppView m_App;
    private static boolean document;
    private static boolean summary;
    private static String level=null;
    private static DataLogicFacilities dmang;
    private static ComboBoxValModel elementsModel;
    private static ComboBoxValModel mainheadsModel;
    private static ComboBoxValModel breakdownsModel;
    private static ComboBoxValModel elementsModel1;
    private static ComboBoxValModel mainheadsModel1;
    private static ComboBoxValModel breakdownsModel1;
    private static AccountTable acctablemodel;

    public AccountCreator() {
        initComponents();
        
        reset();
        jButton5.setEnabled(true);
        buttonGroup2.add(jRadioButton3);
        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Name");
        jRadioButton3.setText("SearchKey");
    }
     public void init(AppView app) throws BeanFactoryException {
       m_App=app;
       dmang=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

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
    private void reset(){
      acname.setText(null);
      desc.setText(null);
      buttonGroup1.clearSelection();
      acctablemodel=acctablemodel.emptyinstance();
      jTable1.setModel(acctablemodel.getTableModel());
      hidefields();
    }
    private void hidefields(){
      elements.setVisible(false);
      breakdowns.setVisible(false);
      mainheads.setVisible(false);
      jLabel4.setVisible(false);
      jLabel5.setVisible(false);
      jLabel6.setVisible(false);
      jLabel7.setVisible(false);
      jLabel8.setVisible(false);
      jLabel9.setVisible(false);
      jCheckBox1.setVisible(false);
      jCheckBox1.setSelected(false);
      openingbalance.setVisible(false);
       openingBalType.setVisible(false);
      doopeningbalance.setVisible(false);
      jLabel10.setVisible(false);
      jButton2.setVisible(false);
      jButton3.setVisible(false);
      elements.setModel(new ComboBoxValModel());
      mainheads.setModel(new ComboBoxValModel());
      breakdowns.setModel(new ComboBoxValModel());
      jLabel14.setVisible(false);
      jLabel15.setVisible(false);
      jLabel16.setVisible(false);
      jTextField1.setVisible(false);
      jTextField2.setVisible(false);
      jTextField3.setVisible(false);
    /*  elements.setSelectedIndex(-1);
      mainheads.setSelectedIndex(-1);
      breakdowns.setSelectedIndex(-1);
      elements.setSelectedIndex(-1);
      mainheads.setSelectedIndex(-1);
      breakdowns.setSelectedIndex(-1);*/
      openingbalance.setText("0");
      summary=false;
      document=false;
    }
    public void activate() throws BasicException {
       // loadData();
      //  acctablemodel=new AccountTable();
         breakdownsModel1=new ComboBoxValModel(new ArrayList());
         brakdown1.setModel(breakdownsModel1);
         brakdown1.setSelectedIndex(-1);
         mainheadsModel1=new ComboBoxValModel(new ArrayList());
         mainhead1.setModel(mainheadsModel1);
         mainhead1.setSelectedIndex(-1);
        List<AccountMasterExt> temp=dmang.getaccountElements();
        temp.add(0,null);
         elementsModel1=new ComboBoxValModel(temp);
          element.setModel(elementsModel1);
        reset();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               acname.requestFocus();
            }
        });
    }

   
     public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }

  private void updateParent(int max,String parent) throws BasicException{
     new PreparedSentence(m_App.getSession()
             , "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=?",
             new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{max,parent});
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        acname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        desc = new javax.swing.JTextField();
        mainhead = new javax.swing.JRadioButton();
        breakdown = new javax.swing.JRadioButton();
        subledger = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        elements = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        mainheads = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        breakdowns = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        openingbalance = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        doopeningbalance = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        openingBalType = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        element = new javax.swing.JComboBox();
        mainhead1 = new javax.swing.JComboBox();
        brakdown1 = new javax.swing.JComboBox();
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
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setAutoscrolls(true);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setAutoscrolls(true);

        jLabel1.setText("Name");

        jLabel3.setText("Description");

        buttonGroup1.add(mainhead);
        mainhead.setText("Main Head");
        mainhead.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainheadItemStateChanged(evt);
            }
        });

        buttonGroup1.add(breakdown);
        breakdown.setText("Break Down");
        breakdown.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                breakdownItemStateChanged(evt);
            }
        });

        buttonGroup1.add(subledger);
        subledger.setText("Sub Ledger");
        subledger.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subledgerItemStateChanged(evt);
            }
        });

        jLabel2.setText("Type");

        jLabel5.setText("Under Element");

        elements.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elementsItemStateChanged(evt);
            }
        });

        jLabel6.setText("Under MainHead");

        mainheads.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainheadsItemStateChanged(evt);
            }
        });

        jLabel4.setText("Allow System Generated Entry :");

        jCheckBox1.setText("Yes");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel8.setText("Cannot enter manually. For manual entry choose Sub ledger");

        breakdowns.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                breakdownsItemStateChanged(evt);
            }
        });

        jLabel7.setText("Under Breakdown");

        jLabel9.setText("Opening Balance");

        jLabel10.setText("Date Of Opening Balance");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("check");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        openingBalType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Credit", "Debit" }));

        jLabel14.setText("Debit");

        jLabel15.setText("Credit");

        jLabel16.setText("Balance");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jButton1)
                .addContainerGap(507, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(89, 89, 89))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addGap(53, 53, 53))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(openingbalance, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(doopeningbalance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                .addGap(410, 410, 410))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(openingBalType, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(111, 111, 111))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(mainhead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(breakdown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subledger)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(breakdowns, javax.swing.GroupLayout.Alignment.LEADING, 0, 273, Short.MAX_VALUE)
                            .addComponent(acname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(mainheads, javax.swing.GroupLayout.Alignment.LEADING, 0, 273, Short.MAX_VALUE)
                            .addComponent(elements, javax.swing.GroupLayout.Alignment.LEADING, 0, 273, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jCheckBox1))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(mainhead)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(elements, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(mainheads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(breakdowns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(openingbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jButton2)
                                    .addComponent(openingBalType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(doopeningbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addComponent(jButton3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jCheckBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(149, 149, 149))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(breakdown)
                            .addComponent(subledger)
                            .addComponent(jLabel2))
                        .addContainerGap(425, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Create New", jPanel1);

        jLabel11.setText("Element");

        jLabel12.setText("Main Head");

        jLabel13.setText("Breakdown");

        element.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elementItemStateChanged(evt);
            }
        });

        mainhead1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainhead1ItemStateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Display");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Edit");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("jRadioButton2");

        jRadioButton3.setSelected(true);
        jRadioButton3.setText("  M");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(brakdown1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainhead1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(element, 0, 145, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(element, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(mainhead1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(brakdown1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jTabbedPane1.addTab("List View", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
           Transaction t=new Transaction(m_App.getSession()) {

                @Override
           protected Object transact() throws BasicException {
            boolean flag;
            if(acname.getText().length()>0 && openingbalance.getText().length()>0 && (mainhead.isSelected() || breakdown.isSelected() || subledger.isSelected())  ){
                flag=false;
                String searchkey="",type="",sign="",parent="";
                String search;
               if(mainhead.isSelected() && elements.getSelectedIndex() != -1) {
                 /*  Object[] obj=(Object[]) new StaticSentence(m_App.getSession()
                           , "SELECT SEARCHKEY,TYPE_,SIGN,(SELECT MAX(SEARCHKEY) FROM ACCOUNTMASTER A1 WHERE A1.PARENT=A.SEARCHKEY) AS MAXVALUE FROM ACCOUNTMASTER A WHERE A.ID=?"
                           , SerializerWriteString.INSTANCE
                           , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).find()*/
                  AccountMasterExt ele=(AccountMasterExt) elements.getSelectedItem();
                  parent=ele.getSerachkey();
                   if(ele.getmax()==null){
                   search=ele.getSerachkey()+".1";
                    updateParent(1, ele.getSerachkey());
                   } else{
                          String del=ele.getSerachkey()+"."+ele.getmax();

                          String[] arr=del.split("\\.");
                          int temp=Integer.parseInt(arr[arr.length-1])+1;
                          arr[arr.length-1]= String.valueOf(temp);
                          search=null;
                          search=arr[0];
                          for(int j=1;j<arr.length;j++){
                            search+="."+arr[j];
                          }
                          updateParent(temp, ele.getSerachkey());
                                     // search.aparr.;
                    }
                 // search=String.valueOf(Long.parseLong(ele.getmax())+1);
                  searchkey=search;
                  type=ele.gettype();
                  sign=ele.getsign();
                  flag=true;
               }
                if(breakdown.isSelected() && mainheads.getSelectedIndex() != -1 && elements.getSelectedIndex() != -1){
                    AccountMasterExt mele=(AccountMasterExt) mainheadsModel.getSelectedItem();
                  parent=mele.getSerachkey();
                  if(mele.getmax()==null){
                   search=mele.getSerachkey()+ ".1";
                   updateParent(1, mele.getSerachkey());
                  }
                  else{
                     String del=mele.getSerachkey()+"."+mele.getmax();

                          String[] arr=del.split("\\.");
                          int temp=Integer.parseInt(arr[arr.length-1])+1;
                          arr[arr.length-1]= String.valueOf(temp);
                          search=null;
                          search=arr[0];
                          for(int j=1;j<arr.length;j++){
                            search+="."+arr[j];
                          }
                          updateParent(temp, mele.getSerachkey());
                  }
                  searchkey=search.toString();
                  type=mele.gettype();
                  sign=mele.getsign();
                  flag=true;
                }
                if(subledger.isSelected() && mainheads.getSelectedIndex() != -1 && elements.getSelectedIndex() != -1 ){
                 if(breakdowns.getSelectedItem() == null){
                  AccountMasterExt mele=(AccountMasterExt) mainheads.getSelectedItem();
                  parent=mele.getSerachkey();
                  if(mele.getmax()==null){
                   search=mele.getSerachkey()+ ".1";
                   updateParent(1, mele.getSerachkey());
                  }
                  else{
                     String del=mele.getSerachkey()+"."+mele.getmax();

                          String[] arr=del.split("\\.");
                          int temp=Integer.parseInt(arr[arr.length-1])+1;
                          arr[arr.length-1]= String.valueOf(temp);
                          search=null;
                          search=arr[0];
                          for(int j=1;j<arr.length;j++){
                            search+="."+arr[j];
                          }
                          updateParent(temp, mele.getSerachkey());
                  }
                  searchkey=search.toString();
                  type=mele.gettype();
                  sign=mele.getsign();
                  flag=true;
                 }
                 else {
                  AccountMasterExt mele=(AccountMasterExt) breakdowns.getSelectedItem();
                  parent=mele.getSerachkey();
                  if(mele.getmax()==null){
                   search=mele.getSerachkey()+ ".1";
                   updateParent(1, mele.getSerachkey());
                  }
                  else{
                     String del=mele.getSerachkey()+"."+mele.getmax();

                          String[] arr=del.split("\\.");
                          int temp=Integer.parseInt(arr[arr.length-1])+1;
                          arr[arr.length-1]= String.valueOf(temp);
                          search=null;
                          search=arr[0];
                          for(int j=1;j<arr.length;j++){
                            search+="."+arr[j];
                          }
                          updateParent(temp, mele.getSerachkey());
                  }
                  searchkey=search.toString();
                  type=mele.gettype();
                  sign=mele.getsign();
                  flag=true;
                 }
                }
              Date d=new Date();
                if(flag==true){
               Double openingbal;
               String atranstype,transtype;
              if(openingBalType.getSelectedItem().toString().equals("Credit")){
                openingbal=Double.parseDouble(openingbalance.getText())*-1;
                atranstype="C";
                transtype="D";
              } else{
                 openingbal=Double.parseDouble(openingbalance.getText())* +1;
                  atranstype="D";
                  transtype="C";
              }
               String accountid=UUID.randomUUID().toString();
              
            
             if(doopeningbalance.getText().length()<0){

             Object[] value=new Object[]{accountid,searchkey,acname.getText(),desc.getText(),type,sign,document,summary,null,parent,level,null,d,openingbal,true};
             new PreparedSentence(m_App.getSession()
                  , "INSERT INTO ACCOUNTMASTER(ID,SEARCHKEY,NAME,DESCRIPTION,TYPE_,SIGN,DOCUMENT,SUMMARY,DEFAULT_,PARENT,LEVEL_,OPERANDS,STARTDATE,OPENINGBALANCE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.DOUBLE,Datas.BOOLEAN})
                  ).exec(value);
             //sanjeev:renamed type to type_,default to default_,level to level_
              }else{
                   Object obdate=Formats.TIMESTAMP.parseValue(doopeningbalance.getText());
                  Object[] value=new Object[]{accountid,searchkey,acname.getText(),desc.getText(),type,sign,document,summary,null,parent,level,null,d,openingbal,obdate,true};
             new PreparedSentence(m_App.getSession()
                  , "INSERT INTO ACCOUNTMASTER(ID,SEARCHKEY,NAME,DESCRIPTION,TYPE_,SIGN,DOCUMENT,SUMMARY,DEFAULT_,PARENT,LEVEL_,OPERANDS,STARTDATE,OPENINGBALANCE,DATEOFOPENINGBAL,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.DOUBLE,Datas.TIMESTAMP,Datas.BOOLEAN})
                  ).exec(value);
             //sanjeev:renamed type to type_,default to default_,level to level_
                   
                }
                if(openingbal!=0&& doopeningbalance.getText().length()>0){
               String tid=UUID.randomUUID().toString();
               Double openbal=openingbal;
                if(openingbal<0){
                    openbal=openbal*-1;
                }
               //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY
                Object[] value1=new Object[]{UUID.randomUUID().toString(),tid,Formats.TIMESTAMP.parseValue(doopeningbalance.getText()),atranstype,"Opening Balance","Opening bal",openbal,null,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Opening Balance",accountid,0.0,d,d,true};
                dmang.insertintoaccjoutnal3(value1);
                 Object[] value2=new Object[]{UUID.randomUUID().toString(),tid,Formats.TIMESTAMP.parseValue(doopeningbalance.getText()),transtype,"Opening Balance","Opening bal",openbal,null,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),"Opening Balance","5",0.0,d,d,true};
                dmang.insertintoaccjoutnal3(value2);
               }
                }

          }else{
            JOptionPane.showMessageDialog(null,"Please Fill the form Completely", null, JOptionPane.OK_OPTION);
          }
            return null;
              }
            };
            t.execute();
        }
        catch(Exception e){
            new MessageInf(e).show(getParent());
            e.printStackTrace();
        }
        reset();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void subledgerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subledgerItemStateChanged
        // TODO add your handling code here:
        if(subledger.isSelected()){
           jLabel4.setVisible(false);
           jLabel8.setVisible(false);
           jCheckBox1.setVisible(false);
           jLabel5.setVisible(true);
           jLabel6.setVisible(true);
           jLabel7.setVisible(true);
           elements.setVisible(true);
           breakdowns.setVisible(true);
           mainheads.setVisible(true);
           openingbalance.setVisible(true);
           openingBalType.setVisible(true);
          // openingBalType.setSelectedIndex(-1);
           doopeningbalance.setVisible(true);
           jLabel10.setVisible(true);
           jLabel9.setVisible(true);
           jButton2.setVisible(true);
           jButton3.setVisible(true);
           document=true;
           summary=false;
           level="S";
           try{
               
               elementsModel=new ComboBoxValModel(dmang.getaccountElements());
               elements.setModel(elementsModel);
           }
           catch(Exception e){
             e.printStackTrace();
           }
        }
    }//GEN-LAST:event_subledgerItemStateChanged

    private void breakdownItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_breakdownItemStateChanged
        // TODO add your handling code here:
         if(breakdown.isSelected()){
           hidefields();
           jLabel5.setVisible(true);
           jLabel6.setVisible(true);
           elements.setVisible(true);
            mainheads.setVisible(true);
           document=false;
           summary=true;
           level="D";
           try{
                elementsModel=new ComboBoxValModel(dmang.getaccountElements());
               elements.setModel(elementsModel);
           }
           catch(Exception e){
             e.printStackTrace();
           }
          // summary=false;
        }
    }//GEN-LAST:event_breakdownItemStateChanged

    private void mainheadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainheadItemStateChanged
        // TODO add your handling code here:
         if(mainhead.isSelected()){
           hidefields();
           jLabel5.setVisible(true);
           jLabel4.setVisible(true);
           jCheckBox1.setVisible(true);
           elements.setVisible(true);
           document=false;
           summary=true;
           level="C";
          // summary=false;
        try{
                elementsModel=new ComboBoxValModel(dmang.getaccountElements());
               elements.setModel(elementsModel);
           }
           catch(Exception e){
             e.printStackTrace();
           }
        }
    }//GEN-LAST:event_mainheadItemStateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()==false){
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            jCheckBox1.setVisible(true);
            jLabel4.setVisible(true);
            openingbalance.setVisible(false);
            openingBalType.setVisible(false);
            doopeningbalance.setVisible(false);
            jLabel10.setVisible(false);
            jButton2.setVisible(false);
             summary=true;
        }
        else{
            jLabel8.setVisible(true);
            jCheckBox1.setVisible(true);
            jLabel4.setVisible(true);
            openingbalance.setVisible(true);
            openingBalType.setVisible(true);
            doopeningbalance.setVisible(true);
           jLabel10.setVisible(true);
           jLabel9.setVisible(true);
           jButton2.setVisible(true);
            summary=false;
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(doopeningbalance.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            doopeningbalance.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void mainheadsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainheadsItemStateChanged
        // TODO add your handling code here:
        if(elements.getSelectedIndex()!=-1 && mainheads.getSelectedIndex()!=-1){
           try{
               AccountMasterExt mele=(AccountMasterExt)mainheads.getSelectedItem();
               breakdownsModel=new ComboBoxValModel(dmang.getaccountBreakpoints(mele.getSerachkey()));
               breakdowns.setModel(breakdownsModel);
           }
           catch(Exception e){
             e.printStackTrace();
           }
          }

      
    }//GEN-LAST:event_mainheadsItemStateChanged

    private void breakdownsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_breakdownsItemStateChanged
        // TODO add your handling code here:
       /*   if(breakdowns.getSelectedIndex()!=-1 && elements.getSelectedIndex()!=-1 && mainheads.getSelectedIndex()!=-1){
         try{
               AccountMaster mele=(AccountMaster)mainheads.getSelectedItem();
               breakdownsModel=new ComboBoxValModel(dmang.getaccountMainHeads(mele.getSerachkey()));
               breakdowns.setModel(breakdownsModel);
           }
           catch(Exception e){
             e.printStackTrace();
           }
          }*/
    }//GEN-LAST:event_breakdownsItemStateChanged

    private void elementsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elementsItemStateChanged
        // TODO add your handling code here:
          if(elements.getSelectedIndex()!=-1 ){
          try{
              AccountMasterExt mele=(AccountMasterExt)elements.getSelectedItem();
           mainheadsModel=new ComboBoxValModel(dmang.getaccountMainHeads1(mele.getSerachkey()));
           mainheads.setModel(mainheadsModel);
           }
           catch(Exception e){
             e.printStackTrace();
           }
        }
    }//GEN-LAST:event_elementsItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
      jLabel14.setVisible(true);
      jLabel15.setVisible(true);
      jLabel16.setVisible(true);
      jTextField1.setVisible(true);
      jTextField2.setVisible(true);
      jTextField3.setVisible(true);
      double credit=0.0;
      double debit=0.0;
      double balance=0.0;
      try{
      Object[] obj=(Object[])new StaticSentence(m_App.getSession()
                           , "SELECT SUM(OPENINGBALANCE),(SELECT SUM(OPENINGBALANCE) FROM ACCOUNTMASTER WHERE OPENINGBALANCE > 0 ),(SELECT SUM(OPENINGBALANCE) FROM ACCOUNTMASTER WHERE OPENINGBALANCE < 0 ) FROM ACCOUNTMASTER"
                           , null
                           , new SerializerReadBasic(new Datas[]{Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE})).find();
      if(obj!=null){
         if(obj[0]!=null)
             balance=Double.parseDouble(obj[0].toString());
         if(obj[1]!=null)
             debit=Double.parseDouble(obj[1].toString());
         if(obj[2]!=null)
             credit=Double.parseDouble(obj[2].toString());
      }
      if(openingBalType.getSelectedItem().toString().equals("Credit")){
          credit =credit + Double.parseDouble(openingbalance.getText()) ;
          balance=balance - Double.parseDouble(openingbalance.getText()) ;
      }
      else{
          debit =debit + Double.parseDouble(openingbalance.getText()) ;
          balance=balance+Double.parseDouble(openingbalance.getText()) ;
      }
      jTextField1.setText(String.valueOf(debit));
      jTextField2.setText(String.valueOf(credit));
      jTextField3.setText(String.valueOf(balance));
      }
      catch(Exception e){
          new MessageInf(e).show(getParent());
       e.printStackTrace();
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        if(brakdown1.getSelectedIndex()!=-1 && brakdown1.getSelectedItem()!=null){
            AccountMasterExt acc=(AccountMasterExt)brakdown1.getSelectedItem();
            if(jRadioButton3.isSelected() == true){
            acctablemodel=AccountTable.loadInstance(m_App,acc.getSerachkey());
            }else if(jRadioButton2.isSelected() == true){
            acctablemodel=AccountTable.loadInstanceName(m_App,acc.getSerachkey());
            }
            jTable1.setModel(acctablemodel.getTableModel());
        }else if(mainhead1.getSelectedIndex()!=-1 && mainhead1.getSelectedItem()!=null){
            AccountMasterExt acc=(AccountMasterExt)mainhead1.getSelectedItem();
            if(jRadioButton3.isSelected() == true){
            acctablemodel=AccountTable.loadInstance(m_App,acc.getSerachkey());
            }else if(jRadioButton2.isSelected() == true){
            acctablemodel=AccountTable.loadInstanceName(m_App,acc.getSerachkey());
            }
            jTable1.setModel(acctablemodel.getTableModel());
        }else if(element.getSelectedIndex()!=-1 && element.getSelectedItem()!=null){
            AccountMasterExt acc=(AccountMasterExt)element.getSelectedItem();
            if(jRadioButton3.isSelected() == true){
            acctablemodel=AccountTable.loadInstance(m_App,acc.getSerachkey());
            }else if(jRadioButton2.isSelected() == true){
            acctablemodel=AccountTable.loadInstanceName(m_App,acc.getSerachkey());
            }
            jTable1.setModel(acctablemodel.getTableModel());
        }
        }catch(Exception e){
            new MessageInf(e).show(getParent());
         e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void elementItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elementItemStateChanged
        // TODO add your handling code here:
         if(element.getSelectedIndex()!=-1 ){
          try{
              AccountMasterExt mele=(AccountMasterExt)element.getSelectedItem();
              List<AccountMasterExt> acc=dmang.getaccountMainHeads(mele.getSerachkey());
              acc.add(0, null);
           mainheadsModel1=new ComboBoxValModel(acc);
           mainhead1.setModel(mainheadsModel1);
           mainhead1.setSelectedIndex(-1);
           brakdown1.setSelectedIndex(-1);
            acctablemodel=AccountTable.emptyinstance();
            jTable1.setModel(acctablemodel.getTableModel());
           }
           catch(Exception e){
               new MessageInf(e).show(getParent());
             e.printStackTrace();
           }
        }
    }//GEN-LAST:event_elementItemStateChanged

    private void mainhead1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainhead1ItemStateChanged
        // TODO add your handling code here:
         if(element.getSelectedIndex()!=-1 && mainhead1.getSelectedIndex()!=-1){
           try{
               AccountMasterExt mele=(AccountMasterExt)mainhead1.getSelectedItem();
               List<AccountMasterExt> acc=dmang.getaccountBreakpoints(mele.getSerachkey());
               acc.add(0, null);
               breakdownsModel1=new ComboBoxValModel(acc);
               brakdown1.setModel(breakdownsModel1);
               brakdown1.setSelectedIndex(-1);
               acctablemodel=AccountTable.emptyinstance();
               jTable1.setModel(acctablemodel.getTableModel());
           }
           catch(Exception e){
               new MessageInf(e).show(getParent());
             e.printStackTrace();
           }
          }
    }//GEN-LAST:event_mainhead1ItemStateChanged

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        javax.swing.JTabbedPane tab=(javax.swing.JTabbedPane)evt.getSource();
        if(tab.getSelectedIndex()==1){
           element.setSelectedIndex(-1);
           mainhead1.setSelectedIndex(-1);
           brakdown1.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        List<AccountTable.AccountLine> accline=acctablemodel.getAccountline();
        if(row>=0){
            try {
                AccountTable.AccountLine acctemp = accline.get(row);
                if(acctemp.isEditable()){
                  AccountEdit editacc = AccountEdit.getDialog(this, m_App, dmang);
                  editacc.showDialog(acctemp);
                  if(editacc.getStatus())
                  activate();
                }else{
                  JOptionPane.showMessageDialog(null, "Sorry default accounts cannot be edited");
                }
            } catch (BasicException ex) {
                new MessageInf(ex).show(getParent());
               ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField acname;
    private javax.swing.JComboBox brakdown1;
    private javax.swing.JRadioButton breakdown;
    private javax.swing.JComboBox breakdowns;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField desc;
    private javax.swing.JTextField doopeningbalance;
    private javax.swing.JComboBox element;
    private javax.swing.JComboBox elements;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton mainhead;
    private javax.swing.JComboBox mainhead1;
    private javax.swing.JComboBox mainheads;
    private javax.swing.JComboBox openingBalType;
    private javax.swing.JTextField openingbalance;
    private javax.swing.JRadioButton subledger;
    // End of variables declaration//GEN-END:variables

}
