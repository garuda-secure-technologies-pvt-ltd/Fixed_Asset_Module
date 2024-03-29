/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * salesreporttypechooser.java
 *
 * Created on Jan 12, 2009, 7:58:22 PM
 */

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author swathi
 */
public class salesreporttypechooser extends javax.swing.JPanel {//implements ReportEditorCreator {
    private SentenceList m_sentlocations;
     private ComboBoxValModel m_OrdersModel;

    /** Creates new form salesreporttypechooser */
    public salesreporttypechooser() {
        initComponents();
    }
     public void init(AppView app) {

        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");

        // El modelo de locales
        m_sentlocations = dlSales.getLocationsList();
       m_OrdersModel = new ComboBoxValModel();
    }
       public void activate() throws BasicException, BasicException {
        List a = m_sentlocations.list();
        addFirst(a);
        m_OrdersModel = new ComboBoxValModel(a);
        m_OrdersModel.setSelectedFirst();
        jComboBox1.setModel(m_OrdersModel); // refresh model
    }

    public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING});
    }

    public Component getComponent() {
        return this;
    }


    protected void addFirst(List a) {
        // do nothing
    }

    public void addActionListener(ActionListener l) {
        jComboBox1.addActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
       jComboBox1.removeActionListener(l);
    }

    public Object createValue() throws BasicException {

        return new Object[] {
            m_OrdersModel.getSelectedKey() == null ? QBFCompareEnum.COMP_NONE : QBFCompareEnum.COMP_EQUALS,
            m_OrdersModel.getSelectedKey()
        };
    }    
    /** This method is called fr

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "waiter", "product", "operator", "customer" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select the order  :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
