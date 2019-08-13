/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on Oct 4, 2009, 2:06:31 PM
 */

package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class DependentEditor extends javax.swing.JDialog {

    /** Creates new form NewJDialog */
    //private AppView m_App;
    private Object[] odata;
    private Object[] newdata;
    private boolean edited;
    private String id;
    public DependentEditor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    public DependentEditor(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    private void init(){
        String datelist[]=new String[] {null, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        String monthlist[]=new String[] { null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        m_date.setModel(new javax.swing.DefaultComboBoxModel(datelist));
        month.setModel(new javax.swing.DefaultComboBoxModel(monthlist));
        type.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Daughter","Father","Mother","Son","Spouse"}));
    }

    public DependentEditor(Frame parent) {
        this(parent, true);
    }

    public DependentEditor(Dialog parent) {
        this(parent, true);
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }


    public static DependentEditor getDialog(Component parent) {

        Window window = getWindow(parent);

        DependentEditor ct;

        if (window instanceof Frame) {
            ct = new DependentEditor((Frame) window);
        } else {
            ct = new DependentEditor((Dialog) window);
        }

        return ct;
    }
   public boolean isEdited(){
      return edited;
   }
   public Object[] getEditedData(){
      return newdata;
   }
   public  void showDialog(Object[] param,String id) throws BasicException{
       this.id=id;
       edited=false;
       odata=param;
       dname.setText(String .valueOf(param[0]));
       dno.setText(String.valueOf(param[1]));
       if(param[2]!=null && !param[2].equals("") ){
            Date d=(Date)param[2];
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(d.getTime());
            m_date.setSelectedIndex(cal.get(Calendar.DATE));
            month.setSelectedIndex(cal.get(Calendar.MONTH)+1);
            year.setText(String.valueOf(cal.get(Calendar.YEAR)));
        }else{
           m_date.setSelectedIndex(-1);
           month.setSelectedIndex(-1);
           year.setText(null);
        }
       doj.setText(Formats.DATE.formatValue(param[3]));
       type.setSelectedItem(param[4]);
       
       setVisible(true);

   }
   private Date getDate(String date,int month,String year){
        Calendar cal=Calendar.getInstance();
        cal.set(Integer.parseInt(year), month-1,Integer.parseInt(date));
        Date d=new Date();
        d.setTime(cal.getTimeInMillis());
        return d;

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ok = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dname = new javax.swing.JTextField();
        m_date = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        year = new javax.swing.JTextField();
        doj = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        type = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        dno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(DependentEditor.class);
        ok.setText(resourceMap.getString("ok.text")); // NOI18N
        ok.setName("ok"); // NOI18N
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        dname.setText(resourceMap.getString("dname.text")); // NOI18N
        dname.setName("dname"); // NOI18N

        m_date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        m_date.setName("m_date"); // NOI18N

        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        month.setName("month"); // NOI18N

        year.setText(resourceMap.getString("year.text")); // NOI18N
        year.setName("year"); // NOI18N

        doj.setEditable(false);
        doj.setText(resourceMap.getString("doj.text")); // NOI18N
        doj.setName("doj"); // NOI18N

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        type.setName("type"); // NOI18N

        jLabel5.setName("jLabel5"); // NOI18N

        dno.setEditable(false);
        dno.setName("dno"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dno, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m_date, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(doj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addContainerGap(23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(m_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(doj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
     if(dname.getText().length()>0 && type.getSelectedIndex()!=-1 &&  dno.getText().length()>0){
            try {
                Date d = null;
                if (m_date.getSelectedIndex() > 0 && month.getSelectedIndex() > 0 && year.getText().length() > 0) {
                    d = getDate(m_date.getSelectedItem().toString(), month.getSelectedIndex(), year.getText());
                }
                newdata = new Object[]{dname.getText().trim(), dno.getText().trim(), d, Formats.TIMESTAMP.parseValue(doj.getText()), type.getSelectedItem(),id};
                for (int i = 0; i < 5; i++) {
                    if (!String.valueOf(odata[i]).trim().equals(String.valueOf(newdata[i]).trim())) {
                        edited=true;
                        break;
                    }
                }
                dispose();
            } catch (BasicException ex) {
                JOptionPane.showMessageDialog(this,"Error", "Error Changes not applied ", JOptionPane.ERROR_MESSAGE);
            }
     }else{
        JOptionPane.showMessageDialog(this,"Please fill the form with correct data", "Error Changes not applied ", JOptionPane.ERROR_MESSAGE);
     }
}//GEN-LAST:event_okActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          Date date;
        try {
            date = (Date) Formats.DATE.parseValue(doj.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            doj.setText(Formats.DATE.formatValue(date));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dname;
    private javax.swing.JTextField dno;
    private javax.swing.JTextField doj;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox m_date;
    private javax.swing.JComboBox month;
    private javax.swing.JButton ok;
    private javax.swing.JComboBox type;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables

}
