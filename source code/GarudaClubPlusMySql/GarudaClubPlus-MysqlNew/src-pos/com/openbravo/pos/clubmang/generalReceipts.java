/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * generalReceipts.java
 *
 * Created on May 4, 2009, 3:48:42 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
//import org.apache.velocity.runtime.directive.Foreach;

/**
 *
 * @author swathi
 */
public class generalReceipts extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /** Creates new form generalReceipts */
    private ComboBoxValModel accmodel;
    private AppView m_App;
    private DataLogicFacilities dlfac;
    private JTextComponent editor;
    private DataLogicSales m_dlSales;
    private DataLogicSystem dlsystem;
    private TicketParser ttp;
    private GeneralReceiptTableModel grmodel;
    private double totalamt=0;
    
    
    List<AccountMasterExt> TempAccList = new ArrayList();
    
    
    public generalReceipts() {
        initComponents();
    }

     public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        ttp = new TicketParser(app.getDeviceTicket(), dlsystem);
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        description.setColumns(10);
        
    }

    public String getTitle() {
        return "General Receipt Entry";
    }
     private boolean isAlpha(String s){
      s = s.toUpperCase();
      for (int i = 0; i < s.length(); i ++){
      int c = (int) s.charAt(i);
      if ((c < 65 || c > 90) && (c<47 || c>58) )
       return false;
    }
     return true;
    }

    public void activate() throws BasicException {
       
       // edited for restricting number of accounts to user ...............................................................by aakash 
        String CurrRoleID = m_App.getAppUserView().getUser().getRole();
        TempAccList = new ArrayList();
           try{
               TempAccList = getAccountList(m_App, CurrRoleID);
           }
           catch(BasicException e){
           }
         
         if(TempAccList.size()>0){
             accmodel = new ComboBoxValModel(TempAccList);
             accounts.setModel(accmodel);  
         }  
         else{
             accmodel=new ComboBoxValModel(dlfac.getsubAccountsExceptMemAccandCashAcc(""));
             accounts.setModel(accmodel); 
         }
        
           
        
        accounts.setEditable(false);
       //accmodel=new ComboBoxValModel(dlfac.getsubAccountsExceptMemAccandCashAcc(""));
       // accounts.setModel(accmodel);
       // editor=(JTextComponent)accounts.getEditor().getEditorComponent();
      //  editor.setText(null);
      //  editor.addKeyListener(new Comboboxlistenner());
        name.setText(null);
        description.setText(null);
        amount.setText(null);
        total.setText(null);
        grmodel=GeneralReceiptTableModel.emptyinstance();
        jTable1.setModel(grmodel.getTableModel());
        totalamt=0;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               name.requestFocus();
            }
        });
        
        
        
        
        
    }
     public class Comboboxlistenner extends  KeyAdapter{
         public void keyReleased(KeyEvent e) {
             try{
                 //!e.getKeyText(e.getKeyCode()).equals("Enter")
                 String text=editor.getText();
                 if(isAlpha(String.valueOf(e.getKeyChar())) || e.getKeyText(e.getKeyCode()).equals("Backspace")){//|| !e.getKeyText(e.getKeyCode()).equals("Backspace")){
                   accmodel=new ComboBoxValModel(dlfac.getsubAccountsExceptMemAccandCashAcc(text.toUpperCase()));
                   accounts.setModel(accmodel);
                   editor.setText(text);
                   accounts.showPopup();

                 }else if(e.getKeyText(e.getKeyCode()).equals("Enter")){
                    if(accmodel.getSize()<=0){
                       editor.setText(null);
                    }
                 }
              }
             catch(Exception e1){
               e1.printStackTrace();
             }
         }
     }
    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public Object getBean() {
        return this;
    }
    private void printTicket(String receiptno,String cname,List<PaymentInfo> pinfo,String desc,List<GeneralReceiptTableModel.Receiptline> list) throws ScriptException {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GeneralReceipts");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                desc=StringUtils.encodeXML(desc);
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", totalamt);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("cname", StringUtils.encodeXML(cname));
                script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
                script.put("desc", desc);
                script.put("receipt", receiptno);
                script.put("detail", list);
                ttp.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            }
            catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
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
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accounts = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();

        setLayout(null);

        jLabel1.setText("Name");
        add(jLabel1);
        jLabel1.setBounds(10, 30, 80, 14);

        name.setNextFocusableComponent(accounts);
        add(name);
        name.setBounds(100, 30, 220, 20);

        jLabel2.setText("Narration");
        add(jLabel2);
        jLabel2.setBounds(10, 360, 80, 14);

        jLabel3.setText("Account");
        add(jLabel3);
        jLabel3.setBounds(10, 70, 80, 14);

        add(accounts);
        accounts.setBounds(100, 70, 220, 20);

        description.setColumns(20);
        description.setRows(5);
        description.setNextFocusableComponent(jButton1);
        jScrollPane1.setViewportView(description);

        add(jScrollPane1);
        jScrollPane1.setBounds(90, 360, 390, 120);

        jLabel4.setText("Amount");
        add(jLabel4);
        jLabel4.setBounds(10, 110, 80, 14);

        amount.setNextFocusableComponent(add);
        add(amount);
        amount.setBounds(100, 110, 130, 20);

        jButton1.setText("Pay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(450, 500, 70, 30);

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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        add(jScrollPane2);
        jScrollPane2.setBounds(90, 160, 390, 160);

        jLabel5.setText("Detail List");
        add(jLabel5);
        jLabel5.setBounds(10, 160, 80, 14);

        add.setText("Add");
        add.setNextFocusableComponent(description);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        add(add);
        add.setBounds(280, 130, 80, 23);

        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        add(remove);
        remove.setBounds(500, 160, 90, 23);

        jLabel6.setText("Total");
        add(jLabel6);
        jLabel6.setBounds(280, 330, 80, 14);

        total.setEditable(false);
        add(total);
        total.setBounds(380, 330, 100, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            boolean bill;
            String rnum;
            Date dNow=new Date();
             List<PaymentInfo> pinfo=new ArrayList<PaymentInfo>();
          if(name.getText().length()>0 && jTable1.getRowCount()>0){
             Double amt=totalamt;
             //AccountMaster acc=(AccountMaster)accounts.getSelectedItem();
             //String accname=acc.getName();
             CustomerInfoExt cinfo=new CustomerInfoExt(name.getText());
             cinfo.setName(name.getText());
             JPaymentSelect paymentdialog =JPaymentSelectReceipt.getDialog(this);
             paymentdialog.init(m_App);
             bill= paymentdialog.showDialog(amt, cinfo, m_App.getAppUserView().getUser().getName(),false);
             if(bill==true){
             pinfo=paymentdialog.getSelectedPayments();
             List<GeneralReceiptTableModel.Receiptline> list=grmodel.getReceiptlist();
             String ids=null;
             for(GeneralReceiptTableModel.Receiptline line:list){
                if(ids==null)
                    ids=line.getaccount()+"#"+line.getAmount();
                else
                    ids+="#"+line.getaccount()+"#"+line.getAmount();
             }
             BillInfo ticket=new BillInfo();
             ticket.setID(UUID.randomUUID().toString());
             ticket.setPayments(pinfo);
             ticket.setCustomer(cinfo);
             ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
             ticket.setCreatedDate(dNow);
             ticket.setActiveCash(m_App.getActiveCashIndex());
             ticket.setFloor(ids);
             ticket.setPlace(description.getText());
             rnum=m_dlSales.payaccount(ticket, m_App.getInventoryLocation(),true);
             if(!(rnum==null || rnum.equals("false"))){
             printTicket( rnum,cinfo.getName(), pinfo,StringUtils.encodeXML(description.getText()),list);
             activate();
             }else{
             if(rnum.equals("false")){
                  JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
             }else
                 JOptionPane.showMessageDialog(this, "Please fill the form", "Error", JOptionPane.OK_OPTION);
          }
             }
          }
        }catch(NumberFormatException e1){
           JOptionPane.showMessageDialog(this, "Please enter a proper value for number", null, JOptionPane.OK_OPTION);
        }
        catch(Exception e){
           e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        if(accounts.getSelectedIndex()!=-1 && amount.getText().length()>0){
          GeneralReceiptTableModel.Receiptline rline=grmodel.getReceiptline();
          AccountMaster acc=(AccountMaster)accounts.getSelectedItem();
          double value=Double.parseDouble(amount.getText());
          rline.setAmount(value);
          rline.setAccountName(acc.getName());
          rline.setAccountid(acc.getid());
          grmodel.addReceiptLine(rline);
          jTable1.setModel(grmodel.getTableModel());
          totalamt += value;
          total.setText(String.valueOf(totalamt));
          accounts.setSelectedIndex(-1);
          amount.setText(null);
          java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                  accounts.requestFocus();
                }
            });
        }else{
           JOptionPane.showMessageDialog(this, "Please Enter Correct Values", "Incomplete form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        if(row > -1){
            GeneralReceiptTableModel.Receiptline rline=grmodel.getReceiptlist().get(row);
            totalamt-=rline.getAmount();
           grmodel.RemoveReceiptLine(row);
           total.setText(String.valueOf(totalamt));
           jTable1.setModel(grmodel.getTableModel());
        }else{
          JOptionPane.showMessageDialog(this, "Please select a row to be deleted", "Cannot Process",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accounts;
    private javax.swing.JButton add;
    private javax.swing.JTextField amount;
    private javax.swing.JTextArea description;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JButton remove;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables


    
  // edited by aakash   
    
    public List<AccountMasterExt> getAccountList(AppView app , String RoleId) throws BasicException{
         Object o = null; 
         
         List<Object> TempList = new ArrayList<Object>();
         List<AccountMasterExt> TempAccList = new ArrayList<AccountMasterExt>();
         
          
         o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generalreceiptperm WHERE NAME =? AND ACTIVE=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(RoleId);
         if(o!=null){
            String o2 = o.toString();
            String []Str = o2.split("#");
            for(int i=0;i<Str.length;i++){
                String x = Str[i].toString();
                
                Object AccName = null;
                AccName  = new StaticSentence(app.getSession(), "SELECT  NAME FROM ACCOUNTMASTER WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(x);
                String AccStr = AccName.toString();
                TempList.add(AccStr);
                
                AccountMasterExt acc = (AccountMasterExt) new StaticSentence(app.getSession(), "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.ID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).find(x);
                TempAccList.add(acc);
                
                
            }
             
             
         }         
         else{
             TempList = new ArrayList<Object>();
         }
           
          return TempAccList;
      } 
    
    
    

}
