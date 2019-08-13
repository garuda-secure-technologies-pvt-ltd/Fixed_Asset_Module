
package com.openbravo.pos.inventory;

import java.awt.Component;
import java.util.UUID;
import javax.swing.*;

import com.openbravo.pos.forms.AppLocal;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.util.RoundUtils;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaxEditor extends JPanel implements EditorRecord {
    
    private Object m_oId;
    
    private SentenceList taxcatsent;
    private ComboBoxValModel taxcatmodel;    
    
    private SentenceList taxcustcatsent;
    private ComboBoxValModel taxcustcatmodel;   
    
    private SentenceList taxparentsent;
    private ComboBoxValModel taxparentmodel;    
        protected AppView m_App;
    /** Creates new form taxEditor */
    public TaxEditor(AppView app, DirtyManager dirty) {
         m_App = app;
        
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        
        initComponents();
        
        taxcatsent = dlSales.getTaxCategoriesList();
        taxcatmodel = new ComboBoxValModel();        
        
        taxcustcatsent = dlSales.getTaxCustCategoriesList();
        taxcustcatmodel = new ComboBoxValModel();    
        
        taxparentsent = dlSales.getTaxList();
        taxparentmodel = new ComboBoxValModel();    

        m_jName.getDocument().addDocumentListener(dirty);
        m_jTaxCategory.addActionListener(dirty);
        m_jCustTaxCategory.addActionListener(dirty);
        m_jTaxParent.addActionListener(dirty);
        m_jRate.getDocument().addDocumentListener(dirty);
        jCascade.addActionListener(dirty);
       
        jOrder.getDocument().addDocumentListener(dirty);
        
        writeValueEOF();
    }
    
    public void activate() throws BasicException {
         
        
         List a = taxcatsent.list();
        taxcatmodel = new ComboBoxValModel(a);
        m_jTaxCategory.setModel(taxcatmodel);
        
        a = taxcustcatsent.list();
        a.add(0, null); // The null item
        taxcustcatmodel = new ComboBoxValModel(a);
        m_jCustTaxCategory.setModel(taxcustcatmodel);    
        
        a = taxparentsent.list();
        a.add(0, null); // The null item
        taxparentmodel = new ComboBoxValModel(a);
        m_jTaxParent.setModel(taxparentmodel);  
        groupButton();
        groupButton1();
     //   jRadioButton2.setSelected(true);
      
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        
         jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false); 
    
       
    }
    
    public void writeValueEOF() {
     m_oId = null;
        m_jName.setText(null);
        taxcatmodel.setSelectedKey(null);
        taxcustcatmodel.setSelectedKey(null);
        taxparentmodel.setSelectedKey(null);
        m_jRate.setText(null);
        jCascade.setSelected(false);
        jOrder.setText(null);
        
        m_jName.setEnabled(false);
        m_jTaxCategory.setEnabled(false);
        m_jCustTaxCategory.setEnabled(false);
        m_jTaxParent.setEnabled(false);
        m_jRate.setEnabled(false);
        jCascade.setEnabled(false);
        jOrder.setEnabled(false);
     
    }
    public void writeValueInsert() {
         m_oId = null;
        m_jName.setText(null);
        taxcatmodel.setSelectedKey(null);
        taxcustcatmodel.setSelectedKey(null);
        taxparentmodel.setSelectedKey(null);
        m_jRate.setText(null);
        jCascade.setSelected(false);
        jOrder.setText(null);
        
        m_jName.setEnabled(true);
        m_jTaxCategory.setEnabled(true);
        m_jCustTaxCategory.setEnabled(true);
        m_jTaxParent.setEnabled(true);        
        m_jRate.setEnabled(true);
        jCascade.setEnabled(true);    
        jOrder.setEnabled(true);
    }
    public void writeValueDelete(Object value) {

     Object[] tax = (Object[]) value;
       m_oId = null;
        m_jName.setText(null);
        taxcatmodel.setSelectedKey(null);
        taxcustcatmodel.setSelectedKey(null);
        taxparentmodel.setSelectedKey(null);
        m_jRate.setText(null);
        jCascade.setSelected(false);
        jOrder.setText(null);
        
        m_jName.setEnabled(true);
        m_jTaxCategory.setEnabled(true);
        m_jCustTaxCategory.setEnabled(true);
        m_jTaxParent.setEnabled(true);        
        m_jRate.setEnabled(true);
        jCascade.setEnabled(true);    
        jOrder.setEnabled(true);
    }    
    public void writeValueEdit(Object value) {

        Object[] tax = (Object[]) value;
        m_oId = tax[0];
        m_jName.setText(Formats.STRING.formatValue(tax[1]));
        taxcatmodel.setSelectedKey(tax[2]);
        taxcustcatmodel.setSelectedKey(tax[3]);
        taxparentmodel.setSelectedKey(tax[4]);        
        m_jRate.setText(Formats.PERCENT.formatValue(tax[5]));
        jCascade.setSelected((Boolean) tax[6]);
        jOrder.setText(Formats.INT.formatValue(tax[7]));
        
        m_jName.setEnabled(true);
        m_jTaxCategory.setEnabled(true);
        m_jCustTaxCategory.setEnabled(true);
        m_jTaxParent.setEnabled(true);        
        m_jRate.setEnabled(true);
        jCascade.setEnabled(true);
        jOrder.setEnabled(true);
            radioactive();
        
    }

    public Object createValue() throws BasicException {
        
         Object[] tax = new Object[8];

        tax[0] = m_oId == null ? UUID.randomUUID().toString() : m_oId;
        tax[1] = m_jName.getText();
        tax[2] = taxcatmodel.getSelectedKey();
        tax[3] = taxcustcatmodel.getSelectedKey(); 
        tax[4] = taxparentmodel.getSelectedKey(); 
        tax[5] = Formats.PERCENT.parseValue(m_jRate.getText());
        tax[6] = Boolean.valueOf(jCascade.isSelected());
        tax[7] = Formats.INT.parseValue(jOrder.getText());
        
        return tax;
    }    
     
    public Component getComponent() {
        return this;
    }
    
    
    public void radioactive(){
                 
         String nrate =(String) m_oId;
        try {
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nrate);
              if(obj[0].equals("yes")){
                 System.out.println(obj[0]);
                 System.out.println("yes");
                   jRadioButton1.setSelected(true); 
                    
                    jRadioButton3.setVisible(true);
        jRadioButton4.setVisible(true);
        jRadioButton5.setVisible(true);
         jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);  
       
           }
              
              else if(obj[0].equals("yesnearest")){
                   jRadioButton1.setSelected(true); 
                   jRadioButton3.setSelected(true);
                   jRadioButton3.setVisible(true);
        jRadioButton4.setVisible(true);
        jRadioButton5.setVisible(true);
         jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);  
                     }
               else if(obj[0].equals("yesnext")){
                jRadioButton1.setSelected(true); 
                jRadioButton4.setSelected(true);   
                jRadioButton3.setVisible(true);
        jRadioButton4.setVisible(true);
        jRadioButton5.setVisible(true);
         jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);       
               }
                else if(obj[0].equals("yesprevious")){
                     jRadioButton1.setSelected(true); 
                     jRadioButton5.setSelected(true);
                     jRadioButton3.setVisible(true);
                  jRadioButton4.setVisible(true);
                jRadioButton5.setVisible(true);
                   jLabel9.setVisible(true);
                   jLabel10.setVisible(true);
                  jLabel11.setVisible(true);  
                     }
              
              
              else{
                 System.out.println("no");
                 System.out.println(obj[0]);
        jRadioButton2.setSelected(true);  
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);  
                 
            }  
           
        } catch (BasicException ex) {
            Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
          
                }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        m_jName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        m_jRate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCascade = new javax.swing.JCheckBox();
        m_jTaxCategory = new javax.swing.JComboBox();
        m_jTaxParent = new javax.swing.JComboBox();
        m_jCustTaxCategory = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jOrder = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();

        setLayout(null);
        add(m_jName);
        m_jName.setBounds(240, 20, 200, 20);

        jLabel2.setText(AppLocal.getIntString("Label.Name")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 20, 220, 14);

        jLabel3.setText(AppLocal.getIntString("label.dutyrate")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 140, 220, 14);

        m_jRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jRateActionPerformed(evt);
            }
        });
        add(m_jRate);
        m_jRate.setBounds(240, 140, 60, 20);

        jLabel1.setText(AppLocal.getIntString("label.taxcategory")); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 50, 220, 14);

        jLabel4.setText(AppLocal.getIntString("label.custtaxcategory")); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(20, 80, 220, 14);

        jLabel5.setText(AppLocal.getIntString("label.taxparent")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 110, 220, 14);

        jCascade.setText(AppLocal.getIntString("label.cascade")); // NOI18N
        jCascade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCascadeActionPerformed(evt);
            }
        });
        add(jCascade);
        jCascade.setBounds(320, 140, 110, 23);

        m_jTaxCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jTaxCategoryActionPerformed(evt);
            }
        });
        add(m_jTaxCategory);
        m_jTaxCategory.setBounds(240, 50, 200, 20);

        m_jTaxParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jTaxParentActionPerformed(evt);
            }
        });
        add(m_jTaxParent);
        m_jTaxParent.setBounds(240, 110, 200, 20);
        add(m_jCustTaxCategory);
        m_jCustTaxCategory.setBounds(240, 80, 200, 20);

        jLabel6.setText(AppLocal.getIntString("label.order")); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(20, 170, 220, 14);
        add(jOrder);
        jOrder.setBounds(240, 170, 60, 20);

        jLabel7.setText("Rounding of to whole Rupee :");
        add(jLabel7);
        jLabel7.setBounds(20, 240, 190, 14);

        jRadioButton1.setText("Yes");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        add(jRadioButton1);
        jRadioButton1.setBounds(220, 240, 80, 23);

        jRadioButton2.setText("No");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        add(jRadioButton2);
        jRadioButton2.setBounds(320, 240, 80, 23);

        jLabel8.setText("Note: Tax would be rounded of to two decimal points even if no is selected");
        add(jLabel8);
        jLabel8.setBounds(20, 400, 610, 20);

        jLabel9.setText("Round of to nearest Rupee :");
        add(jLabel9);
        jLabel9.setBounds(20, 270, 210, 14);

        jLabel10.setText("Rounding of to next Rupee :");
        add(jLabel10);
        jLabel10.setBounds(20, 300, 210, 14);

        jLabel11.setText("Rounding of to previous Rupee:");
        add(jLabel11);
        jLabel11.setBounds(20, 330, 210, 14);

        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        add(jRadioButton3);
        jRadioButton3.setBounds(230, 270, 21, 20);

        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        add(jRadioButton4);
        jRadioButton4.setBounds(230, 300, 20, 20);

        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        add(jRadioButton5);
        jRadioButton5.setBounds(230, 330, 21, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jTaxCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jTaxCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jTaxCategoryActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
            // TODO add your handling code here:
        //  groupButton1();
         jRadioButton2.setSelected(false);
       //   jRadioButton3.setSelected(false);
           jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
            jRadioButton3.setSelected(true);    
          jRadioButton3.setVisible(true);
        jRadioButton4.setVisible(true);
        jRadioButton5.setVisible(true);
        jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true); 
              try {
               String nrate =(String) m_oId;
             new PreparedSentence(m_App.getSession(), "UPDATE TAXES SET RATEROUNDOFF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{"yesnearest", nrate});
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nrate);
            System.out.println(obj);
         
            if(obj[0].equals("yes")){
                 System.out.println(obj[0]);
                 System.out.println("yes");
                
            }else{
                 System.out.println("no");
                 System.out.println(obj[0]);
            }
             } catch (BasicException ex) {
            Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
  // TODO add your handling code here:
       // m_jRate.getText()
          jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
            
              // jRadioButton3.setSelected(true);
            
              ///////////////////////////////////////////////shiv
        System.out.println("radio1");
          try {
            String nrate =(String) m_oId;
            new PreparedSentence(m_App.getSession(), "UPDATE TAXES SET RATEROUNDOFF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{"yesnearest", nrate});
            Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nrate);
            System.out.println(obj1);
         
            if(obj1[0].equals("yes")){
                 System.out.println(obj1[0]);
                 System.out.println("yes");
                 
                
            }else{
                 System.out.println("no");
                 System.out.println(obj1[0]);
            }
             } catch (BasicException ex) {
            Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      /*
            try {
              Object o= Formats.DOUBLE.parseValue( m_jRate.getText());
              
              String str = o.toString();
             Double d= Double.valueOf(str).doubleValue();
              //Object o2=RoundUtils.getValue(d); 
              //System.out.println(o2);
              System.out.println(new Float( Math.round(d)));
          //    d= Double.valueOf(str).doubleValue();
             Object f= new Float( Math.round(d));
             String st= f.toString();
               m_jRate.setText(st);
                
                Object o1= Formats.PERCENT.parseValue(m_jRate.getText());
                 System.out.println(o1);
             //   m_jRate.setText();
            } catch (BasicException ex) {
                Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    
        //////////////////////////////////////////////
         * /
         */
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void m_jRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jRateActionPerformed

    private void m_jTaxParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jTaxParentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jTaxParentActionPerformed

    
      ///shiv created method
  double RoundTo2Decimals(double val) {
            DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
}
  
  
  ///shiv created method
public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}

    
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
       
        jRadioButton1.setSelected(false);
         jRadioButton3.setSelected(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
    
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);  
         String nrate =(String) m_oId;
         System.out.println(nrate);
      
        try {
             new PreparedSentence(m_App.getSession(), "UPDATE TAXES SET RATEROUNDOFF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{"no", nrate});
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nrate);
            System.out.println(obj);
         
            if(obj[0].equals("yes")){
                 System.out.println(obj[0]);
                 System.out.println("yes");
                
            }else{
                 System.out.println("no");
                 System.out.println(obj[0]);
            }
             } catch (BasicException ex) {
            Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
           
         /*   ///////////////////////////////////////////////shiv
          
        
        
        
              try {
                Object o= Formats.DOUBLE.parseValue(m_jRate.getText());
                
                String str = o.toString();
               Double d= Double.valueOf(str).doubleValue();
            
                System.out.println(new Float( Math.scalb(d,2)));
    
                 Object ob=new Double(round(d,2));
               String st= ob.toString();
                     m_jRate.setText(st);
                  
                  Object o1= Formats.PERCENT.parseValue(m_jRate.getText());
                   System.out.println(o1);
               //   m_jRate.setText();
              } catch (BasicException ex) {
                  Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
              }
              
      
          //////////////////////////////////////////////
          * 
          */
       
       
        
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
        jRadioButton4.setSelected(false);
            jRadioButton3.setSelected(false);
                   ///////////////////////////////////////////////shiv
        System.out.println("radio1");
      
      String nrate =(String) m_oId;
         System.out.println(nrate);
      
       try {
             new PreparedSentence(m_App.getSession(), "UPDATE TAXES SET RATEROUNDOFF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{"yesprevious", nrate});
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nrate);
            System.out.println(obj);
         
            if(obj[0].equals("yesprevious")){
                 System.out.println(obj[0]);
                 System.out.println("yes");
                
            }else{
                 System.out.println("no");
                 System.out.println(obj[0]);
            }
             } catch (BasicException ex) {
            Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
      /*
            try {
              Object o= Formats.DOUBLE.parseValue(m_jRate.getText());
              
              String str = o.toString();
             Double d= Double.valueOf(str).doubleValue();
              Double d1=d-1;
              //Object o2=RoundUtils.getValue(d); 
              //System.out.println(o2);
              System.out.println(new Float( Math.round(d1)));
          //    d= Double.valueOf(str).doubleValue();
             Object f= new Float( Math.round(d1));
             String st= f.toString();
                   m_jRate.setText(st);
                
                Object o1= Formats.PERCENT.parseValue(m_jRate.getText());
                 System.out.println(o1);
             //   m_jRate.setText();
            } catch (BasicException ex) {
                Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    
        //////////////////////////////////////////////
            */
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
         jRadioButton5.setSelected(false);
            jRadioButton3.setSelected(false);
                   ///////////////////////////////////////////////shiv
        System.out.println("radio1");
         String nrate =(String) m_oId;
         System.out.println(nrate);
        try {
             new PreparedSentence(m_App.getSession(), "UPDATE TAXES SET RATEROUNDOFF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{"yesnext", nrate});
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(nrate);
            System.out.println(obj);
         
            if(obj[0].equals("yesnext")){
                 System.out.println(obj[0]);
                 System.out.println("yes");
                
            }else{
                 System.out.println("no");
                 System.out.println(obj[0]);
            }
             } catch (BasicException ex) {
            Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      
          
      /*
            try {
              Object o= Formats.DOUBLE.parseValue(m_jRate.getText());
              
              String str = o.toString();
             Double d= Double.valueOf(str).doubleValue();  //17.5
              Double d1=d+1;  //18.5
              //Object o2=RoundUtils.getValue(d); 
              //System.out.println(o2);
              System.out.println(new Float( Math.round(d1)));
          //    d= Double.valueOf(str).doubleValue();
              
             Object f= new Float( Math.round(d1));  //19.0
             
             String st= f.toString();
                   m_jRate.setText(st);
                
                Object o1= Formats.PERCENT.parseValue(m_jRate.getText());
                 System.out.println(o1);
             //   m_jRate.setText();
            } catch (BasicException ex) {
                Logger.getLogger(TaxEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        //////////////////////////////////////////////
                */
    }//GEN-LAST:event_jRadioButton4ActionPerformed

private void jCascadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCascadeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jCascadeActionPerformed
    
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(jRadioButton1);
        bg1.add(jRadioButton2);
        bg1.add(jRadioButton3);
        bg1.add(jRadioButton4);
        bg1.add(jRadioButton5);
        
    }
   
  private void groupButton1() {
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(jRadioButton3);
        bg2.add(jRadioButton4);
        bg2.add(jRadioButton5);
    }
//   
//   private void groupButton3(){
//       ButtonGroup bg3 = new ButtonGroup();
//      
//   }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JCheckBox jCascade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jOrder;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JComboBox m_jCustTaxCategory;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jRate;
    private javax.swing.JComboBox m_jTaxCategory;
    private javax.swing.JComboBox m_jTaxParent;
    // End of variables declaration//GEN-END:variables
    
}
