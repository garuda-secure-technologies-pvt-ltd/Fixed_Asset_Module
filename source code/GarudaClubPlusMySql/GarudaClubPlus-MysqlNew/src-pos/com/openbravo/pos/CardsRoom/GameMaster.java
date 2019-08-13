/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJPanel.java
 *
 * Created on 27-Jan-2010, 16:37:18
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import java.awt.Component;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author swathi
 */
public class GameMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private DataLogicFacilities dlfac;
    private AppView m_App;
    private ComboBoxValModel gmodel;
    private ComboBoxValModel tmodel;
    private String id;
    private List<GameInfoExt> glist;
    private String[] HEADER=new String[]{"NAME","PARENT","CLUB COLLECTION","MIN","MAX","SEQ","PRINTREF","CREATEDBY","CRDATE","DEACTBY","DEACTDATE"};
    private ComboBoxValModel taxmodel;
    private DataLogicSales m_dlSales;
    /** Creates new form NewJPanel */
    public GameMaster() {
        initComponents();
    }
   
    public void writeValueInsert() throws BasicException {
        name.setText(null);
        clubCollection.setText(null);
        _min.setText(null);
        _max.setText(null);
        sequence.setText(null);
        printref.setText(null);
        gameAmount.setText(null);
        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);
        jImageEditor1.setImage(null);
        //jCheckBox2.setText("per Player");
        //jRadioButton1.setText("Begining");
        //jRadioButton2.setText("End");
        jLabel10.setText("Tax Category");
        jLabel11.setText("Game Amount");
        jLabel12.setText("Default Token");

        //jRadioButton1.setSelected(true);
        glist=new ArrayList<GameInfoExt>();
        List<GameInfo> list=new ArrayList<GameInfo>(dlfac.getActiveGamesList());
        gmodel=new ComboBoxValModel(list);
        List<TokenCombinationInfo> list1=new ArrayList<TokenCombinationInfo>(dlfac.getTokenCombination());
        list1.add(0,null);
        tmodel=new ComboBoxValModel(list1);
        jComboBox2.setModel(tmodel);
        jComboBox2.setSelectedIndex(-1);
        List<TaxCategoryInfo> taxlist=m_dlSales.getTaxCategoriesList().list();
        taxlist.add(0,null);
        taxmodel=new ComboBoxValModel(taxlist);
        jComboBox1.setModel(taxmodel);
        jComboBox1.setSelectedIndex(-1);
        parent.setModel(gmodel);
        parent.setSelectedIndex(-1);
        glist.addAll(dlfac.getALLGamesList());
        jTable1.setModel(getTableModel());
        //setEnableComponents(true);
    }

     public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        writeValueInsert();
        jTabbedPane1.setSelectedIndex(0);
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         jLabel1.setText("Name");
         jLabel2.setText("Parent");
         jLabel3.setText("Club Collection");
         jLabel4.setText("Minimum");
         jLabel5.setText("Maximum");
         jLabel6.setText("Active");
         jLabel7.setText("ID Sequence");
         jLabel8.setText("Print Ref");
         jButton1.setText("Save");
         jButton2.setText("Deactivate");
         jButton3.setVisible(false);
         jLabel9.setText("Per Player");
         jCheckBox2.setText("Yes");
         jCheckBox1.setText("True");
         jTabbedPane1.setTitleAt(0, "Create New");
         jTabbedPane1.setTitleAt(1, "List View");
    }

    public Object getBean() {
       return this;
    }
   private AbstractTableModel getTableModel(){
      return new AbstractTableModel() {

            public int getRowCount() {
                return glist.size();
            }

            public int getColumnCount() {
                return HEADER.length;
            }

            @Override
            public String getColumnName(int column) {
                return HEADER[column];
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                GameInfoExt g=glist.get(rowIndex);
                switch(columnIndex){
                    case 0: return g.getName();
                    case 1: return g.getParent();
                    case 2: return g.getClubCollection();
                    case 3: return g.getMax();
                    case 4: return g.getMin();
                    case 5: return g.getSequence();
                    case 6: return g.getprintref();
                    case 7: return g.getCreatedBy();
                    case 8: return g.getCreatedDate();
                    case 9: return g.getDeactBy();
                    case 10: return g.getDeactDate();
                    default : return null;
                }
            }
        };
   }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        printref = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sequence = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        _max = new javax.swing.JTextField();
        _min = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        clubCollection = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        parent = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        gameAmount = new javax.swing.JTextField();
        jImageEditor1 = new com.openbravo.data.gui.JImageEditor();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setLayout(null);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setLayout(null);
        jPanel1.add(printref);
        printref.setBounds(150, 300, 210, 20);

        jLabel8.setText("jLabel8");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 300, 130, 14);

        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 260, 130, 14);
        jPanel1.add(sequence);
        sequence.setBounds(150, 260, 210, 20);

        jCheckBox1.setText("jCheckBox1");
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(150, 230, 200, 23);

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 230, 120, 14);

        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 200, 130, 14);
        jPanel1.add(_max);
        _max.setBounds(150, 200, 210, 20);
        jPanel1.add(_min);
        _min.setBounds(150, 160, 210, 20);

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 160, 120, 14);
        jPanel1.add(clubCollection);
        clubCollection.setBounds(150, 100, 210, 20);

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 100, 120, 14);

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 60, 130, 14);

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 130, 14);
        jPanel1.add(name);
        name.setBounds(150, 20, 210, 20);

        jPanel1.add(parent);
        parent.setBounds(150, 60, 210, 20);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(440, 10, 73, 30);

        jLabel9.setText("jLabel9");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 130, 110, 14);

        jCheckBox2.setText("jCheckBox2");
        jPanel1.add(jCheckBox2);
        jCheckBox2.setBounds(150, 130, 81, 23);

        jLabel10.setText("jLabel10");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(10, 340, 100, 14);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(150, 340, 210, 20);

        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(10, 380, 100, 14);

        jLabel12.setText("jLabel12");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(10, 410, 120, 14);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(150, 410, 210, 20);
        jPanel1.add(gameAmount);
        gameAmount.setBounds(150, 380, 210, 20);
        jPanel1.add(jImageEditor1);
        jImageEditor1.setBounds(400, 55, 230, 160);

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(7, 5, 530, 390);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(423, 400, 120, 23);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(293, 400, 110, 23);

        jTabbedPane1.addTab("tab2", jPanel2);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 650, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
          if(JOptionPane.showConfirmDialog(null, "Do you want to save ?", "Save Dialog",JOptionPane.YES_NO_OPTION )==JOptionPane.YES_OPTION){
             GameInfo g=(GameInfo) parent.getSelectedItem();
             //IMAGE,TAXCATEGORY,PERPLAYER,PAYMENTTIME,TOKENREF
             String taxcat=null;
             String tokenID=null;
             TaxCategoryInfo tinfo=(TaxCategoryInfo)jComboBox1.getSelectedItem();
             if(tinfo!=null)
                 taxcat=tinfo.getID();
             TokenCombinationInfo tokeninfo=(TokenCombinationInfo)jComboBox2.getSelectedItem();
             if(tokeninfo!=null)
                 tokenID=tokeninfo.getTokenID();
             double gameAmt=0;
             if(gameAmount.getText().length()>0){
                gameAmt=Double.parseDouble(gameAmount.getText());
             }
             //    paymenttime=1;
             int min=0,max=0,parenttemp=-1;
             if(_min.getText().length()>0)
               min=Integer.parseInt(_min.getText());
             if(_max.getText().length()>0)
               max=Integer.parseInt(_max.getText());
             //String parent=null;
             if(g!=null)
                 parenttemp=g.getID();
             double ccollection=0.0;
             if(clubCollection.getText().length()>0){
                ccollection=Double.parseDouble(clubCollection.getText());
             }
             //NAME, PARENT,CCAMOUNT,_MIN,_MAX,WITHTOKEN,CRDATE,CREATEDBY,GSEQ,SEQMAX,PRINTREF,ACTIVE,IMAGE,TAXCAT,PERPLAYER,PAYMENTTIME,TOKENREF
            Object[] obj=new Object[]{name.getText().toUpperCase().trim(),parenttemp,ccollection,min,max,new Date(),m_App.getAppUserView().getUser().getName(),sequence.getText(),0
                         ,printref.getText(),jCheckBox1.isSelected(),jImageEditor1.getImage(),taxcat,jCheckBox2.isSelected(),gameAmt,tokenID};
            dlfac.insertNewGame(obj);
            activate();
          }
        } catch (BasicException ex) {
          // JOptionPane.showMessageDialog(null, "Error occured while saving", null, JOptionPane.OK_OPTION);
            JOptionPane.showMessageDialog(null, "Error game name must be unique", null, JOptionPane.OK_OPTION);
           ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try {
        int row=jTable1.getSelectedRow();
        if(row>=0){
            GameInfo g=glist.get(row);
            Object[] obj = new Object[]{m_App.getAppUserView().getUser().getName(),new Date(),g.getID()};
            dlfac.deactivateGame(obj);
            activate();
        }
      } catch (BasicException ex) {
            ex.printStackTrace();
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if(jTabbedPane1.getSelectedIndex()==1){
            try {
                glist.clear();
                glist.addAll(dlfac.getALLGamesList());
                jTable1.setModel(getTableModel());
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField _max;
    private javax.swing.JTextField _min;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField clubCollection;
    private javax.swing.JTextField gameAmount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.openbravo.data.gui.JImageEditor jImageEditor1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox parent;
    private javax.swing.JTextField printref;
    private javax.swing.JTextField sequence;
    // End of variables declaration//GEN-END:variables




}
