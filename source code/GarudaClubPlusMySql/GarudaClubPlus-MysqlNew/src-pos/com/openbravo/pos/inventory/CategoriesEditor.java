

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
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.CategoryInfo;
import java.util.ArrayList;

/**
 *
 * @author adrianromero
 */
public class CategoriesEditor extends JPanel implements EditorRecord {
       
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;
    private ComboBoxValModel accoubtModel;
    private ComboBoxValModel saccountModel;
    private ComboBoxValModel m_LocationModel;
    private SentenceExec m_sentadd;
    private SentenceExec m_sentdel;
    private SentenceList m_sentLoc;
    private AppView m_App;
    
    private Object m_id;
    private DataLogicFacilities dlfac;
    private DataLogicSales dlSales;
    private List<CategoryInfo> catinfo;
    
    /** Creates new form JPanelCategories */
    public CategoriesEditor(AppView app, DirtyManager dirty) {
        
        dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate") ;
        initComponents();
             
        // El modelo de categorias
        m_sentcat = dlSales.getCategoriesList();
        m_sentLoc = dlSales.getLocationsList();
        m_LocationModel = new ComboBoxValModel();
        m_CategoryModel = new ComboBoxValModel();
        accoubtModel=new ComboBoxValModel();
        saccountModel=new ComboBoxValModel();
        m_sentadd = dlSales.getCatalogCategoryAdd();
        m_sentdel = dlSales.getCatalogCategoryDel();
        
        m_jName.getDocument().addDocumentListener(dirty);
        m_jCategory.addActionListener(dirty);
        m_jImage.addPropertyChangeListener("image", dirty);
        purchaseaccount.addActionListener(dirty);
        salesaccount.addActionListener(dirty);
        purchaseaccount.setVisible(true);
        salesaccount.setVisible(true);
        m_jlocation.addActionListener(dirty);
        writeValueEOF();
    }
    
    public void activate() throws BasicException {
        
        List a = m_sentcat.list();
        a.add(0, null);// The null item        
        m_LocationModel = new ComboBoxValModel(m_sentLoc.list());
        m_jlocation.setModel(m_LocationModel);
        m_CategoryModel = new ComboBoxValModel(a);
        m_jCategory.setModel(m_CategoryModel);
        List<AccountMasterExt> acclist=dlfac.getaccounts();
        List<AccountMasterExt> sacclist=new ArrayList<AccountMasterExt>();
        sacclist.addAll(acclist);
        accoubtModel=new ComboBoxValModel(acclist);
        purchaseaccount.setModel(accoubtModel);
        saccountModel=new ComboBoxValModel(sacclist);
        salesaccount.setModel(saccountModel);
        purchaseaccount.setVisible(true);
        salesaccount.setVisible(true);
    }
    
    public void writeValueEOF() {
        m_id = null;
        m_jName.setText(null);
        m_CategoryModel.setSelectedKey(null);
        accoubtModel.setSelectedKey(null);
        saccountModel.setSelectedKey(null);
        m_jImage.setImage(null);
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
        purchaseaccount.setVisible(false);
        salesaccount.setVisible(false);
        m_LocationModel.setSelectedKey(null);
        m_jlocation.setEnabled(false);
    }
    public void writeValueInsert() {
        m_id = null;
        m_jName.setText(null);
        m_LocationModel.setSelectedKey(null);
        m_CategoryModel.setSelectedKey(null);
        m_jImage.setImage(null);
        m_jName.setEnabled(true);
        m_jlocation.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jImage.setEnabled(true);
        accoubtModel.setSelectedKey(null);
        saccountModel.setSelectedKey(null);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
        purchaseaccount.setVisible(true);
        salesaccount.setVisible(true);
    }
    public void writeValueDelete(Object value) {
        Object[] cat = (Object[]) value;
        m_id = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));
        m_LocationModel.setSelectedKey(cat[6]);
        m_CategoryModel.setSelectedKey(cat[2]);
        m_jImage.setImage((BufferedImage) cat[3]);
        accoubtModel.setSelectedKey(cat[4]);
        saccountModel.setSelectedKey(cat[5]);
         try{
            if(m_CategoryModel.getSelectedItem()==null ){
         new PreparedSentence(m_App.getSession()
                , "DELETE FROM LOCATIONS WHERE ID=?"
                ,  SerializerWriteString.INSTANCE).exec(m_id.toString());
            }
        }
        catch(Exception e)
        {

        }
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
        purchaseaccount.setVisible(false);
        salesaccount.setVisible(false);
        m_jlocation.setEnabled(false);
    }    
    public void writeValueEdit(Object value) {
        Object[] cat = (Object[]) value;
        m_id = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));
        m_LocationModel.setSelectedKey(cat[6]);
        m_CategoryModel.setSelectedKey(cat[2]);
        m_jImage.setImage((BufferedImage) cat[3]);
        accoubtModel.setSelectedKey(cat[4]);
        saccountModel.setSelectedKey(cat[5]);
        m_jName.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jImage.setEnabled(true);
        m_jCatalogDelete.setEnabled(true);
        m_jCatalogAdd.setEnabled(true);
        purchaseaccount.setVisible(true);
        salesaccount.setVisible(true);
        m_jlocation.setEnabled(true);
    }
    private void updateAccountidAll(String id,String accid) throws BasicException{
             List<CategoryInfo> cinfolist =dlSales.getSubcategories(id);
            dlSales.updateSubCataegorysaccAll(id, accid);
            dlSales.updateProductsaccAll(id,accid);
            for(CategoryInfo cinfo:cinfolist){
               updateAccountidAll(cinfo.getID(),accid);
            }
    }
     private void updateAccountidSpecific(String id,String accid,String paccid) throws BasicException{
            List<CategoryInfo> cinfolist =dlSales.getSubcategories(id);
            dlSales.updateSubCataegorysaccSpecific(id, accid,paccid);
            dlSales.updateProductsaccSpecific(id,accid,paccid);
            for(CategoryInfo cinfo:cinfolist){
               updateAccountidSpecific(cinfo.getID(),accid,paccid);
            }
    }
      private void updateAccountidAll1(String id,String accid) throws BasicException{
             List<CategoryInfo> cinfolist =dlSales.getSubcategories(id);
            dlSales.updateSubCataegorysaccAll1(id, accid);
            dlSales.updateProductsaccAll1(id,accid);
            for(CategoryInfo cinfo:cinfolist){
               updateAccountidAll1(cinfo.getID(),accid);
            }
    }
     private void updateAccountidSpecific1(String id,String accid,String paccid) throws BasicException{
            List<CategoryInfo> cinfolist =dlSales.getSubcategories(id);
            dlSales.updateSubCataegorysaccSpecific1(id, accid,paccid);
            dlSales.updateProductsaccSpecific1(id,accid,paccid);
            for(CategoryInfo cinfo:cinfolist){
               updateAccountidSpecific1(cinfo.getID(),accid,paccid);
            }
    }
    public Object createValue() throws BasicException {
        
        Object[] cat = new Object[7];
         Object id=m_id == null ? UUID.randomUUID().toString() : m_id;
        cat[0] = id;
        if(m_jlocation.getSelectedIndex()!= -1){
           if(m_jCategory.getSelectedIndex()!=-1){
                 cat[2] = m_CategoryModel.getSelectedKey();
          }else{
               JOptionPane.showMessageDialog(this, "select category");
           throw new BasicException();
           }
        }else{
            JOptionPane.showMessageDialog(this, "select location");
            throw new BasicException();
        }
        cat[1] = m_jName.getText();
        cat[6] = m_LocationModel.getSelectedKey();
        //cat[2] = m_CategoryModel.getSelectedKey();
        cat[3] = m_jImage.getImage();

        cat[4]=accoubtModel.getSelectedKey();
        cat[5]=saccountModel.getSelectedKey();
        Object[] temparr=dlSales.getCategoryAccounts(id.toString());
        
        //temp1=temp;
        String pacc="";
      //  if(temp!=null){
         // pacc=temp.toString();
        try{
       if(temparr!=null){
        Object temp=temparr[0];
        Object temp1=temparr[1];
         if(accoubtModel.getSelectedItem()!=null){
          if(temp!=accoubtModel.getSelectedKey().toString()){
              if(jRadioButton1.isSelected()==true)
                updateAccountidAll(id.toString(), accoubtModel.getSelectedKey().toString());
              else if(jRadioButton2.isSelected()==true){
                updateAccountidSpecific(id.toString(), accoubtModel.getSelectedKey().toString(),temp.toString());
              }
          }
        // }
        }
         if(saccountModel.getSelectedItem()!=null){
          if(temp1!=saccountModel.getSelectedKey().toString()){
              if(jRadioButton3.isSelected()==true)
                updateAccountidAll1(id.toString(), saccountModel.getSelectedKey().toString());
              else if(jRadioButton4.isSelected()==true){
                updateAccountidSpecific1(id.toString(), saccountModel.getSelectedKey().toString(),temp1.toString());
              }
          }
         }
        }
        }catch(Exception e){
         e.printStackTrace();
        }
     try{
        if(m_CategoryModel.getSelectedItem()==null)
        {
         Object[] values = new Object[] {id.toString(),m_jName.getText()};
       // Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING};
        new PreparedSentence(m_App.getSession()
                , "INSERT INTO LOCATIONS (ID,NAME) VALUES (?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(values);

          }
         }catch(Exception e){
             e.printStackTrace();
         }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        m_jCatalogAdd = new javax.swing.JButton();
        m_jCatalogDelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        purchaseaccount = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        salesaccount = new javax.swing.JComboBox();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        m_jlocation = new javax.swing.JComboBox();

        setLayout(null);

        jLabel2.setText(AppLocal.getIntString("Label.Name")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 20, 80, 14);
        add(m_jName);
        m_jName.setBounds(130, 20, 180, 20);

        jLabel3.setText(AppLocal.getIntString("label.image")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(30, 300, 80, 14);
        add(m_jImage);
        m_jImage.setBounds(130, 300, 240, 180);

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
        jLabel5.setBounds(20, 80, 90, 20);
        add(m_jCategory);
        m_jCategory.setBounds(130, 80, 180, 20);

        jLabel1.setText("Purchase Account");
        add(jLabel1);
        jLabel1.setBounds(20, 110, 100, 20);

        add(purchaseaccount);
        purchaseaccount.setBounds(130, 110, 180, 20);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText(
            "<html>Update All Sub Categories <br>" +
            "and Products </html>" 
        );
        jRadioButton1.setToolTipText("Update All Sub Categories and Products");
        add(jRadioButton1);
        jRadioButton1.setBounds(110, 130, 190, 50);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(
            "<html>Update All Sub Categories and Products<br>" +
            "having same account </html>" 
        );
        jRadioButton2.setToolTipText("Update All Sub Categories and Products \\n having same account");
        add(jRadioButton2);
        jRadioButton2.setBounds(300, 130, 330, 40);

        jLabel4.setText("Sales Account");
        add(jLabel4);
        jLabel4.setBounds(20, 190, 100, 14);

        add(salesaccount);
        salesaccount.setBounds(130, 190, 180, 20);

        jRadioButton3.setSelected(true);
        jRadioButton3.setText(
            "<html>Update All Sub Categories <br>" +
            "and Products </html>" 
        );
        jRadioButton3.setToolTipText(
            "<html>Update All Sub Categories <br>" +
            "and Products </html>" 
        );
        add(jRadioButton3);
        jRadioButton3.setBounds(110, 230, 170, 40);

        jRadioButton4.setText(
            "<html>Update All Sub Categories and Products<br>" +
            "having same account </html>" 
        );
        jRadioButton4.setToolTipText(
            "<html>Update All Sub Categories and Products<br>" +
            "having same account </html>" 
        );
        add(jRadioButton4);
        jRadioButton4.setBounds(290, 230, 350, 40);

        jLabel6.setText("Warehouse");
        add(jLabel6);
        jLabel6.setBounds(20, 50, 110, 20);

        m_jlocation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jlocationItemStateChanged(evt);
            }
        });
        add(m_jlocation);
        m_jlocation.setBounds(130, 50, 180, 20);
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

    private void m_jlocationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jlocationItemStateChanged
        // TODO add your handling code here:
        Object loct1 = m_LocationModel.getSelectedItem();
        if (loct1 != null) {
            String selectedItem = loct1.toString();
            try {
                Object[] obj1 = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
                        , "SELECT ID FROM LOCATIONS WHERE NAME = ?"
                        ,SerializerWriteString.INSTANCE
                        , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedItem);
                if (obj1 != null && obj1[0]!=null) {
                    // The categories model

                     catinfo = dlSales.getCategoriesListByParent(obj1[0].toString());                   
                     m_CategoryModel = new ComboBoxValModel(catinfo);                     
                     m_jCategory.setModel(m_CategoryModel);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
           }
        
    }//GEN-LAST:event_m_jlocationItemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JButton m_jCatalogAdd;
    private javax.swing.JButton m_jCatalogDelete;
    private javax.swing.JComboBox m_jCategory;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private javax.swing.JTextField m_jName;
    private javax.swing.JComboBox m_jlocation;
    private javax.swing.JComboBox purchaseaccount;
    private javax.swing.JComboBox salesaccount;
    // End of variables declaration//GEN-END:variables
    
}
