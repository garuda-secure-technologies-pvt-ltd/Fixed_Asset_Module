/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StockDiaryReportType.java
 *
 * Created on Mar 5, 2009, 6:19:04 PM
 */

package com.openbravo.pos.ticket;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.panels.JProductFinder;
import com.openbravo.pos.reports.JParamsDatesInterval;
import com.openbravo.pos.reports.PanelReportBean;
import com.openbravo.pos.reports.ReportEditorCreator;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author swathi
 */
public class StockDiaryReportType extends javax.swing.JPanel implements ReportEditorCreator {

    /** Creates new form StockDiaryReportType */
    public static String pdtid="";
    public static Object category="";
    public static String pdtname="";
    public static String pdtcategory="";
    private PanelReportBean   report;
    private AppView m_App;
    private SentenceList m_sentcat;
    private  DataLogicSales dlSales;
    private ComboBoxValModel m_CategoryModel;
     private ComboBoxValModel m_productModel;

    public StockDiaryReportType(PanelReportBean report) {
        this.report=report;
        initComponents();
        /* report.setreporttoempty();
             report.setReport("/com/openbravo/reports/stockdiarymain");
              report.setResourceBundle("com/openbravo/reports/costing_messages");
             report.setSentence("SELECT COUNT(*) AS NUM FROM STOCKDIARY WHERE ?(QBF_FILTER)");
              report.clearlist();
             report.addField("NUM", com.openbravo.data.loader.Datas.STRING);
             //report.clearlist();
              report.init1(m_App,1);*/
    }
    public void init(AppView app) {
         m_App=app;
         dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         //m_sentcat = dlSales.getprCategoriesList();
         m_sentcat = dlSales. getLocationsList();
         m_CategoryModel = new ComboBoxValModel();
         m_productModel=new ComboBoxValModel();
        // El modelo de categorias
        // jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { null,"Product wise","Category wise" }));
      }
     public void activate() throws BasicException {
         m_sentcat = dlSales. getLocationsList();
         List catlist = m_sentcat.list();
         catlist.add(0, null);
         m_CategoryModel = new ComboBoxValModel(catlist);
         jComboBox1.setModel(m_CategoryModel);
      //   jTextField1.setText(null);
         m_productModel=new ComboBoxValModel();
         m_productModel.add(null);
         jComboBox2.setModel(m_productModel);
         pdtname="";
         category="";
         pdtid="";
         pdtcategory = "";
   }
   public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(
              new Datas[] {Datas.OBJECT, Datas.STRING});
    }
    public Component getComponent() {
        return this;
    }
    public Object createValue() throws BasicException {
        return new Object[] {
           QBFCompareEnum.COMP_NONE ,null
        };
    }
    private void setMainReport(){
            report.setreporttoempty();
             report.setReport("/com/openbravo/reports/stockdiarymain");
              report.setResourceBundle("com/openbravo/reports/costing_messages");
             report.setSentence("SELECT COUNT(*) AS NUM FROM STOCKDIARY WHERE ?(QBF_FILTER)");
              report.clearlist();
             report.addField("NUM", com.openbravo.data.loader.Datas.STRING);
             //report.clearlist();
              report.init1(m_App,1);
    }
    private void setPdtWiseReport(){
          report.setreporttoempty();
             report.setReport("/com/openbravo/reports/stockdiarymain1");
              report.setResourceBundle("com/openbravo/reports/costing_messages");
             report.setSentence("SELECT COUNT(*) AS NUM FROM STOCKDIARY WHERE ?(QBF_FILTER)");
              report.clearlist();
             report.addField("NUM", com.openbravo.data.loader.Datas.STRING);
             //report.clearlist();
              report.init1(m_App,1);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("By form"));

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Warehouse");

        jLabel2.setText("Product ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/search.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 160, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ProductInfoExt prod = JProductFinder.showMessage(this, dlSales);
        if (prod != null) {
           // buttonTransition(prod);
           // jTextField1.setText(prod.getName());
            m_productModel.add(prod);
            jComboBox2.setModel(m_productModel);
            jComboBox2.setSelectedItem((Object)prod);
            pdtid=prod.getID();
            pdtname=prod.getName();
            //pdtcategory
           setPdtWiseReport();
        }
        else{
            pdtname="";
            pdtid="";
            //pdtcategory="";
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if(jComboBox1.getSelectedItem()!=null){
            // category=jComboBox1.getSelectedItem();
            // Object temp=jComboBox1.getSelectedItem();

          //   CategoryInfo tem=jComboBox1.getSelectedItem().toString();
           //PrintCategoryInfo obj= (PrintCategoryInfo)m_CategoryModel.getSelectedItem();
             LocationInfo obj= (LocationInfo)m_CategoryModel.getSelectedItem();
             category=obj.getID();
              report.setreporttoempty();
             report.setReport("/com/openbravo/reports/stockdiarymain2");
             report.setResourceBundle("com/openbravo/reports/costing_messages");
             report.setSentence("SELECT COUNT(*) AS NUM FROM STOCKDIARY WHERE ?(QBF_FILTER)");
              report.clearlist();
             report.addField("NUM", com.openbravo.data.loader.Datas.STRING);
             //report.clearlist();
             report.init1(m_App,1);
        }
        /*else if(jComboBox1.getSelectedItem()==null)
        {
           setMainReport();
        } */
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
         if(jComboBox2.getSelectedItem()!=null ){
             ProductInfoExt prod=(ProductInfoExt)jComboBox2.getSelectedItem();
             pdtid=prod.getID();
             pdtname=prod.getName();
             setPdtWiseReport();
        }
        /* else
        {
           setMainReport();
        }*/
    }//GEN-LAST:event_jComboBox2ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
