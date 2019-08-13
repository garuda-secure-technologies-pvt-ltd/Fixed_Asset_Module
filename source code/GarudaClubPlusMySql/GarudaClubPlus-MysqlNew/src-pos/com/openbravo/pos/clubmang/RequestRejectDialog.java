/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacilitySuspensionDialog.java
 *
 * Created on Jul 3, 2009, 2:15:32 PM
 */

package com.openbravo.pos.clubmang;

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
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author swathi
 */
public class RequestRejectDialog extends javax.swing.JDialog {

    /** Creates new form FacilitySuspensionDialog */
    private AppView m_App;
    private Boolean resultok=false;
    private Date sfromdate;
    private Date stodate;
    private String reason;
    private DataLogicFacilities dlfac;
    private String periodicity;
    public RequestRejectDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    protected RequestRejectDialog(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public RequestRejectDialog(Frame parent,AppView app) {
        this(parent, true);
        this.m_App=app;
    }

    public RequestRejectDialog(Dialog parent,AppView app) {
        this(parent, true);
        this.m_App=app;
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


    public static RequestRejectDialog getDialog(Component parent,AppView app) {

        Window window = getWindow(parent);

        RequestRejectDialog ct;

        if (window instanceof Frame) {
            ct = new RequestRejectDialog((Frame) window,app);
        } else {
            ct = new RequestRejectDialog((Dialog) window,app);
        }

        return ct;
    }
    public Date getFromdate(){
      return sfromdate;
    }
    public Date getTodate(){
      return stodate;
    }
    public String getReason(){
      return reason;
    }
     public  boolean showDialog1(){
      //this.dlfac=dlfac;
      jLabel1.setVisible(false);
      jLabel2.setVisible(false);
      from.setVisible(false);
      to.setVisible(false);
      jButton1.setVisible(false);
      jButton2.setVisible(false);
      jSpinner1.setVisible(false);
      jSpinner2.setVisible(false);
      //periodicity=pid;
      setVisible(true);
      return resultok;
    }
    public  boolean showDialog(Date sdate,Date tdate,DataLogicFacilities dlfac,String pid){
      this.dlfac=dlfac;
      sfromdate=sdate;
      
      stodate=tdate;
      periodicity=pid;
      jSpinner1.setModel(new SpinnerNumberModel(0, 0, 200, 1));
      jSpinner2.setModel(new SpinnerNumberModel(0, -200, 0, 1));
      jButton1.setVisible(false);
      jButton2.setVisible(false);
      from.setText(Formats.DATE.formatValue(sfromdate));
      to.setText(Formats.DATE.formatValue(stodate));
      from.setEditable(false);
      to.setEditable(false);
      setVisible(true);
      return resultok;
    }
    private void incrementStartDate() throws BasicException{
            FacilityLogic flogic=new FacilityLogic(dlfac);
            Periodicity p=dlfac.getPerioicitybyid(periodicity);
            Calendar cal=Calendar.getInstance();
            //String oldDate=from.getText();
            Date date=new Date();
            date.setTime(sfromdate.getTime());
            cal.setTimeInMillis(date.getTime());
                         int billabledate=cal.get(Calendar.DATE);
                          if(p.getdoj()==false){
                               cal.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal).getTimeInMillis());
                         }
            cal.add(Calendar.DATE, 1);
            date.setTime(cal.getTimeInMillis());
            billabledate=cal.get(Calendar.DATE);
            date.setTime(flogic.calculateEndDate1(date,p,billabledate,Integer.valueOf(jSpinner1.getModel().getValue().toString())).getTime());
          //  if(validateSDate(date))
                   from.setText(Formats.DATE.formatValue(date));
           // else{
         //       JOptionPane.showMessageDialog(this, "", oldDate, billabledate);
         //   }
  }
    private boolean validateDate() throws BasicException{
        Calendar scal=Calendar.getInstance();
        scal.setTimeInMillis(((Date)Formats.TIMESTAMP.parseValue(from.getText())).getTime());
        scal.set(Calendar.HOUR_OF_DAY, 00);
        scal.set(Calendar.MINUTE, 00);
        scal.set(Calendar.SECOND, 00);
        scal.set(Calendar.MILLISECOND, 00);
        Calendar ecal=Calendar.getInstance();
        ecal.setTimeInMillis(((Date)Formats.TIMESTAMP.parseValue(to.getText())).getTime());
        ecal.set(Calendar.HOUR_OF_DAY, 00);
        ecal.set(Calendar.MINUTE, 00);
        ecal.set(Calendar.SECOND, 00);
        ecal.set(Calendar.MILLISECOND, 00);
        if(scal.before(ecal))
            return true;
        else
            return false;
    }
  /*  private boolean validateEDate(Date date) throws BasicException{
        Calendar ecal=Calendar.getInstance();
        ecal.setTimeInMillis(date.getTime());
        ecal.set(Calendar.HOUR_OF_DAY, 00);
        ecal.set(Calendar.MINUTE, 00);
        ecal.set(Calendar.SECOND, 00);
        ecal.set(Calendar.MILLISECOND, 00);
        Calendar scal=Calendar.getInstance();
        scal.setTimeInMillis(((Date)Formats.TIMESTAMP.parseValue(from.getText())).getTime());
        scal.set(Calendar.HOUR_OF_DAY, 00);
        scal.set(Calendar.MINUTE, 00);
        scal.set(Calendar.SECOND, 00);
        scal.set(Calendar.MILLISECOND, 00);
        if(ecal.after(scal))
            return true;
        else
            return false;
    }*/

    private void decrementEndDate() throws BasicException{
            FacilityLogic flogic=new FacilityLogic(dlfac);
            Periodicity p=dlfac.getPerioicitybyid(periodicity);
            Calendar cal=Calendar.getInstance();
            //String oldDate=to.getText();
            Date date=new Date();
            date.setTime(stodate.getTime());
            cal.setTimeInMillis(date.getTime());
           // int billabledate=cal.get(Calendar.DATE);
           // if(p.getdoj()==false){
           //      cal.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal).getTimeInMillis());
           // }
           // date.setTime(cal.getTimeInMillis());
            int billabledate=cal.get(Calendar.DATE);
            date.setTime(flogic.calculateStartDate(date,p,billabledate,Integer.valueOf(jSpinner2.getModel().getValue().toString())).getTime());
            to.setText(Formats.DATE.formatValue(date));
  }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        from = new javax.swing.JTextField();
        to = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        m_reason = new javax.swing.JTextArea();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("From");

        jLabel2.setText("To");

        jLabel3.setText("Reason");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        m_reason.setColumns(20);
        m_reason.setRows(5);
        jScrollPane1.setViewportView(m_reason);

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jSpinner1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jSpinner1InputMethodTextChanged(evt);
            }
        });

        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(to)
                                    .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1))
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
           Date date;
        try {
             //String[] arr=date.toString().split(" ");
            date = (Date) Formats.DATE.parseValue(to.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal=Calendar.getInstance();
            Calendar cal1=Calendar.getInstance();
            Calendar cal2=Calendar.getInstance();
            cal.setTimeInMillis(stodate.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);
            cal2.setTimeInMillis(sfromdate.getTime());
            cal2.set(Calendar.HOUR_OF_DAY, 00);
            cal2.set(Calendar.MINUTE, 00);
            cal2.set(Calendar.SECOND, 00);
            cal2.set(Calendar.MILLISECOND, 00);
            cal1.setTimeInMillis(date.getTime());
           /* cal1.set(Calendar.HOUR, 00);
            cal1.set(Calendar.MINUTE, 00);
            cal1.set(Calendar.SECOND, 00);
            cal1.set(Calendar.MILLISECOND, 00);*/
            if(cal1.after(cal) || cal1.before(cal2) ){
               JOptionPane.showMessageDialog(this, "Selected Date does not come under the requested period", "Please select a new date", JOptionPane.WARNING_MESSAGE);
            }else
               to.setText(Formats.DATE.formatValue(date));

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
      /*  try{
            boolean flag=true;
          sfromdate=(Date)Formats.DATE.parseValue(from.getText());
          stodate=(Date)Formats.DATE.parseValue(to.getText());
          if(jEditorString11.getText().length()>0)
             reason=jEditorString11.getText();
          else{
             JOptionPane.showMessageDialog(this, "Please Enter the reson for requesting the suspension of this facility ", null, JOptionPane.OK_OPTION);
             flag=false;
          }
          if(flag==true){
           resultok=true;
           dispose();
          }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Could not process your request","Error",JOptionPane.OK_OPTION);
            e.printStackTrace();
        }*/
       try{
            boolean flag=true;
            if(from.isVisible())
            if(!validateDate()&& from.getText().length()>0 && to.getText().length()>0)
               flag=false;
            if(m_reason.getText().length()>0  && flag ){

              sfromdate=(Date)Formats.TIMESTAMP.parseValue(from.getText());
              stodate=(Date)Formats.TIMESTAMP.parseValue(to.getText());
              reason=m_reason.getText();
              resultok=true;
              dispose();
            }else if(flag==false){
                 JOptionPane.showMessageDialog(null, "Please fill the form","Incorrect input",JOptionPane.WARNING_MESSAGE);
            }
          }catch(Exception e){
              e.printStackTrace();
              resultok=false;
          }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            //String[] arr=date.toString().split(" ");
            date = (Date) Formats.DATE.parseValue(from.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            //  String[] arr=date.toString().split(" ");
            Calendar cal=Calendar.getInstance();
            Calendar cal1=Calendar.getInstance();
            Calendar cal2=Calendar.getInstance();
            cal.setTimeInMillis(sfromdate.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);
            cal2.setTimeInMillis(stodate.getTime());
            cal2.set(Calendar.HOUR_OF_DAY, 00);
            cal2.set(Calendar.MINUTE, 00);
            cal2.set(Calendar.SECOND, 00);
            cal2.set(Calendar.MILLISECOND, 00);
            cal1.setTimeInMillis(date.getTime());
           /* cal1.set(Calendar.HOUR, 00);
            cal1.set(Calendar.MINUTE, 00);
            cal1.set(Calendar.SECOND, 00);
            cal1.set(Calendar.MILLISECOND, 00);*/
            if(cal1.before(cal) || cal1.after(cal2) ){
                JOptionPane.showMessageDialog(this, "Selected Date does not come under the requested period", "Please select a new date", JOptionPane.WARNING_MESSAGE);
            }else
                from.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jSpinner1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jSpinner1InputMethodTextChanged
      //  try {
           // incrementStartDate();
     //   } catch (BasicException ex) {
      //     ex.printStackTrace();
     //   }
    }//GEN-LAST:event_jSpinner1InputMethodTextChanged

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        try {
            incrementStartDate();
        } catch (BasicException ex) {
           ex.printStackTrace();
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        try {
            decrementEndDate();
        } catch (BasicException ex) {
           ex.printStackTrace();
        }
    }//GEN-LAST:event_jSpinner2StateChanged

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField from;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextArea m_reason;
    private javax.swing.JTextField to;
    // End of variables declaration//GEN-END:variables

}
