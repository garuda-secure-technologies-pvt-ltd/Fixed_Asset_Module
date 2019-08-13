

package com.openbravo.pos.inventory;

import java.awt.Component;
import javax.swing.*;

import com.openbravo.pos.forms.AppLocal;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.forms.AppProperties;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.ReportUtils;

/**
 *
 * @author adrianromero
 */
public class prCategoriesEditor extends JPanel implements EditorRecord {
       
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;
    
    private SentenceExec m_sentadd;
    private SentenceExec m_sentdel;
    
    private Object m_id;
    
    /** Creates new form JPanelCategories */
    public prCategoriesEditor(AppView app, DirtyManager dirty) {
        
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
             
        initComponents();
             
        // El modelo de categorias
        m_sentcat = dlSales.getprCategoriesList();
        m_CategoryModel = new ComboBoxValModel();
        
        m_sentadd = dlSales.getCatalogCategoryAdd();
        m_sentdel = dlSales.getCatalogCategoryDel();
        
        m_jName.getDocument().addDocumentListener(dirty);
        m_jCategory.addActionListener(dirty);
        m_jImage.addPropertyChangeListener("image", dirty);
        
        writeValueEOF();
    }
    
    public void activate() throws BasicException {
        
        List a = m_sentcat.list();
        a.add(0, null); // The null item
        m_CategoryModel = new ComboBoxValModel(a);
        m_jCategory.setModel(m_CategoryModel);

        int i = 2;
        AppProperties props = LookupUtilityImpl.getInstance(null).getAppView().getProperties();
        while (props.getProperty("machine.printer." + i) != null) {
            jcboPrinter.addItem(AppLocal.getIntString("label.printer") + " " + i);
            i++;
        }
    }
    
    public void writeValueEOF() {
        m_id = null;
        m_jName.setText(null);
        m_CategoryModel.setSelectedKey(null);
        m_jImage.setImage(null);
        jcboPrinter.setSelectedItem(null);
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jImage.setEnabled(false);
        jcboPrinter.setEnabled(false);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
    }
    public void writeValueInsert() {
        m_id = null;
        m_jName.setText(null);
        m_CategoryModel.setSelectedKey(null);
        m_jImage.setImage(null);
        jcboPrinter.setSelectedItem(null);
        m_jName.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jImage.setEnabled(true);
        jcboPrinter.setEnabled(true);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
    }
    public void writeValueDelete(Object value) {
        Object[] cat = (Object[]) value;
        m_id = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));
        m_CategoryModel.setSelectedKey(cat[2]);
        m_jImage.setImage((BufferedImage) cat[3]);
        jcboPrinter.setSelectedItem(cat[4]);
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
        jcboPrinter.setEnabled(false);
    }    
    public void writeValueEdit(Object value) {
        Object[] cat = (Object[]) value;
        m_id = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));
        m_CategoryModel.setSelectedKey(cat[2]);
        m_jImage.setImage((BufferedImage) cat[3]);
        jcboPrinter.setSelectedItem(cat[4]);
        m_jName.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jImage.setEnabled(true);
        m_jCatalogDelete.setEnabled(true);
        m_jCatalogAdd.setEnabled(true);
        jcboPrinter.setEnabled(true);
    }

    public Object createValue() throws BasicException {
        
        Object[] cat = new Object[5];

        cat[0] = m_id == null ? UUID.randomUUID().toString() : m_id;
        cat[1] = m_jName.getText();
        cat[2] = m_CategoryModel.getSelectedKey();
        cat[3] = m_jImage.getImage();
        cat[4] = jcboPrinter.getSelectedItem();
        return cat;
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
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        m_jCatalogAdd = new javax.swing.JButton();
        m_jCatalogDelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jcboPrinter = new javax.swing.JComboBox();

        setLayout(null);

        jLabel2.setText(AppLocal.getIntString("Label.Name")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 20, 80, 14);
        add(m_jName);
        m_jName.setBounds(100, 20, 180, 20);

        jLabel3.setText(AppLocal.getIntString("label.image")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 110, 80, 14);
        add(m_jImage);
        m_jImage.setBounds(100, 110, 240, 150);

        m_jCatalogAdd.setText(AppLocal.getIntString("button.catalogadd")); // NOI18N
        m_jCatalogAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatalogAddActionPerformed(evt);
            }
        });
        add(m_jCatalogAdd);
        m_jCatalogAdd.setBounds(370, 20, 170, 23);

        m_jCatalogDelete.setText(AppLocal.getIntString("button.catalogdel")); // NOI18N
        m_jCatalogDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatalogDeleteActionPerformed(evt);
            }
        });
        add(m_jCatalogDelete);
        m_jCatalogDelete.setBounds(370, 50, 170, 23);

        jLabel5.setText(AppLocal.getIntString("label.prodcategory")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 50, 90, 14);
        add(m_jCategory);
        m_jCategory.setBounds(100, 50, 180, 20);

        jLabel1.setText("Printer");
        add(jLabel1);
        jLabel1.setBounds(20, 80, 32, 14);

        jcboPrinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboPrinterActionPerformed(evt);
            }
        });
        add(jcboPrinter);
        jcboPrinter.setBounds(100, 80, 180, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jCatalogDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatalogDeleteActionPerformed

        try {
            m_sentdel.exec(m_id);
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e));
        }
        
    }//GEN-LAST:event_m_jCatalogDeleteActionPerformed

    private void m_jCatalogAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatalogAddActionPerformed

        try {
            Object param = m_id;
            m_sentdel.exec(param); // primero borramos
            m_sentadd.exec(param); // y luego insertamos lo que queda
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e));
        }

    }//GEN-LAST:event_m_jCatalogAddActionPerformed

    private void jcboPrinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboPrinterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboPrinterActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox jcboPrinter;
    private javax.swing.JButton m_jCatalogAdd;
    private javax.swing.JButton m_jCatalogDelete;
    private javax.swing.JComboBox m_jCategory;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private javax.swing.JTextField m_jName;
    // End of variables declaration//GEN-END:variables
    
}
