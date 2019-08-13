

package com.openbravo.pos.mant;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.UUID;
import javax.swing.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;


/**
 *
 * @author adrianromero
 */
public class FloorsEditor extends JPanel implements EditorRecord {
    
//    private DirtyManager m_Dirty = new DirtyManager();    
    private String m_sID;
    private ComboBoxValModel accountlist;
    private ComboBoxValModel pdtcatlist;
    private DataLogicSales m_dlSales;
    private DataLogicFacilities dlfac;
    
    /** Creates new form FloorsEditor */
    public FloorsEditor(DirtyManager dirty,AppView app) {
        initComponents();
          m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
          dlfac= (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         accountlist=new ComboBoxValModel();
         pdtcatlist=new ComboBoxValModel();
        m_jName.getDocument().addDocumentListener(dirty);
        m_jImage.addPropertyChangeListener("image", dirty);
        jBillSeries.getDocument().addDocumentListener(dirty);
        jTextlastBill.getDocument().addDocumentListener(dirty);
        pdtcat.addActionListener(dirty);
        account.addActionListener(dirty);
        
        writeValueEOF();
    }
     public void activate() throws BasicException {
           try{
            accountlist=new ComboBoxValModel(dlfac.getaccounts());
            pdtcatlist=new ComboBoxValModel(m_dlSales.getRootCategories());
            account.setModel(accountlist);
            pdtcat.setModel(pdtcatlist);
            }catch(Exception e){
                e.printStackTrace();
          }
     }
    public void writeValueEOF() {
        
        m_sID = null;
        m_jName.setText(null);
        jBillSeries.setText(null);
        jTextlastBill.setText(null);
        m_jImage.setImage(null);
        pdtcatlist.setSelectedKey(null);
        accountlist.setSelectedKey(null);

        m_jName.setEnabled(false);
        jBillSeries.setEnabled(false);
        jTextlastBill.setEnabled(false);
        m_jImage.setEnabled(false);
        pdtcat.setEnabled(false);
        account.setEnabled(false);
    }  
    public void writeValueInsert() {
        
        m_sID = UUID.randomUUID().toString(); 
        m_jName.setText(null);
        jBillSeries.setText(null);
        jTextlastBill.setText("001");
        m_jImage.setImage(null);
        pdtcatlist.setSelectedKey(null);
        accountlist.setSelectedKey(null);

        m_jName.setEnabled(true);
        jBillSeries.setEnabled(true);
        jTextlastBill.setEnabled(true);
        m_jImage.setEnabled(true);
         pdtcat.setEnabled(true);
        account.setEnabled(true);
    }
    public void writeValueDelete(Object value) {
        
        Object[] floor = (Object[]) value;
        m_sID = Formats.STRING.formatValue(floor[0]);
        m_jName.setText(Formats.STRING.formatValue(floor[1]));
        jBillSeries.setText(Formats.STRING.formatValue(floor[2]));
        jTextlastBill.setText(Formats.STRING.formatValue(floor[3]));
        m_jImage.setImage((BufferedImage) floor[4]);
        pdtcatlist.setSelectedKey(floor[5]);
        accountlist.setSelectedKey(floor[6]);

        m_jName.setEnabled(false);
        jBillSeries.setEnabled(false);
        jTextlastBill.setEnabled(false);
        m_jImage.setEnabled(false);
         pdtcat.setEnabled(false);
        account.setEnabled(false);
    }    
    public void writeValueEdit(Object value) {
        
        Object[] floor = (Object[]) value;
        m_sID = Formats.STRING.formatValue(floor[0]);
        m_jName.setText(Formats.STRING.formatValue(floor[1]));
        jBillSeries.setText(Formats.STRING.formatValue(floor[2]));
        jTextlastBill.setText(Formats.STRING.formatValue(floor[3]));
        m_jImage.setImage((BufferedImage) floor[4]);
        pdtcatlist.setSelectedKey(floor[5]);
        accountlist.setSelectedKey(floor[6]);

        m_jName.setEnabled(true);
        jBillSeries.setEnabled(true);
        jTextlastBill.setEnabled(true);
        m_jImage.setEnabled(true);
          pdtcat.setEnabled(true);
        account.setEnabled(true);
   }

    public Object createValue() throws BasicException {
        
        Object[] floor = new Object[7];

        floor[0] = m_sID;
        floor[1] = m_jName.getText();
        floor[2] = jBillSeries.getText();
        floor[3] = jTextlastBill.getText();
        floor[4] = m_jImage.getImage();
        floor[5] = pdtcatlist.getSelectedKey();
        floor[6] = accountlist.getSelectedKey();
        return floor;
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

        jPanel3 = new javax.swing.JPanel();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jBillSeries = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextlastBill = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        account = new javax.swing.JComboBox();
        pdtcat = new javax.swing.JComboBox();

        setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(m_jImage, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(150, 100));
        jPanel1.setLayout(null);

        jLabel3.setText(AppLocal.getIntString("Label.Name")); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 10, 90, 20);
        jPanel1.add(m_jName);
        m_jName.setBounds(110, 10, 180, 20);

        jLabel1.setText("Bill Series");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 40, 70, 14);
        jPanel1.add(jBillSeries);
        jBillSeries.setBounds(110, 40, 180, 20);

        jLabel2.setText("Last Bill No");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(310, 40, 100, 14);

        jTextlastBill.setEditable(false);
        jPanel1.add(jTextlastBill);
        jTextlastBill.setBounds(420, 40, 130, 20);

        jLabel4.setText("Product Category");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(310, 10, 100, 14);

        jLabel5.setText("Account");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 70, 80, 14);

        jPanel1.add(account);
        account.setBounds(110, 70, 180, 20);

        jPanel1.add(pdtcat);
        pdtcat.setBounds(420, 10, 130, 20);

        jPanel3.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox account;
    private javax.swing.JTextField jBillSeries;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextlastBill;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private javax.swing.JTextField m_jName;
    private javax.swing.JComboBox pdtcat;
    // End of variables declaration//GEN-END:variables
    
}
