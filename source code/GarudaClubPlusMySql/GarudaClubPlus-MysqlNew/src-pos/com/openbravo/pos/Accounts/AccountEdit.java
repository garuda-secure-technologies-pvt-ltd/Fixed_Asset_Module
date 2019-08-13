/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */////

/*
 * AccountEdit.java
 *
 * Created on Feb 25, 2011, 2:18:13 PM
 */

package com.openbravo.pos.Accounts;

import com.openbravo.pos.clubmang.*;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class AccountEdit extends javax.swing.JDialog {

    /** Creates new form AccountEdit */
     private AppView m_App;
     private AccountMasterExt elementvalue;
     private AccountMasterExt mainheadvalue;
     private AccountMasterExt breakdownvalue;
  //   private AccountTable.AccountLine prevselecelementvalue;
  //   private AccountTable.AccountLine prevselecmainheadvalue;
  //   private AccountTable.AccountLine prevselecbreakdownvalue;
     private DataLogicFacilities dlfac;
     private ComboBoxValModel emodel;
     private ComboBoxValModel mmodel;
     private ComboBoxValModel bmodel;
     private AccountTable.AccountLine account;
     private String level;
     private int type=0;
     private boolean status=false;
     private String oldElementID;
     private String oldMainheadID;
     private String oldBreakdownID;
    public AccountEdit(java.awt.Frame parent, boolean modal,DataLogicFacilities dlfac) {
        super(parent, modal);
        this.dlfac=dlfac;
        initComponents();
    }


    protected AccountEdit(Dialog parent, boolean modal,DataLogicFacilities dlfac) {
        super(parent, modal);
         this.dlfac=dlfac;
        initComponents();
    }

    public AccountEdit(Frame parent,AppView app,DataLogicFacilities dlfac) {
        this(parent, true,dlfac);
        this.m_App=app;
    }

    public AccountEdit(Dialog parent,AppView app,DataLogicFacilities dlfac) {
        this(parent, true,dlfac);
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


    public static AccountEdit getDialog(Component parent,AppView app,DataLogicFacilities dlfac) {

        Window window = getWindow(parent);

        AccountEdit ct;

        if (window instanceof Frame) {
            ct = new AccountEdit((Frame) window,app,dlfac);
        } else {
            ct = new AccountEdit((Dialog) window,app,dlfac);
        }

        return ct;
    }
    private void hideComponents(boolean flag,boolean flag1,boolean  flag2){
       jLabel2.setVisible(flag);
       jLabel3.setVisible(flag1);
       jLabel4.setVisible(flag2);
       element.setVisible(flag);
       mainhead.setVisible(flag1);
       breakdown.setVisible(flag2);
       //sameer:hiding jLabel6
       jLabel6.setVisible(false);
    }
    private void updateAccount(String searchkey,String parent,String id,String type,String sign,String name,String desc) throws BasicException{
       new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET SEARCHKEY=?,PARENT=?,TYPE_=?,SIGN=?,NAME=?,DESCRIPTION=? WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{searchkey,parent,type,sign,name,desc,id});
    }
    private void updateParent(int max,String id) throws BasicException{
       new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{max,id});
    }
    private void updateSubAccounts(String nparent,String oparent,String type,String sign) throws BasicException{
         List<AccountMasterExt> acclist=dlfac.getSubaccountswithparentid(oparent);
         int flag=0,i=0;
      for(AccountMasterExt accm:acclist){
               new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET PARENT=?,SEARCHKEY=?,TYPE_=?,SIGN=? WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{nparent,nparent+"."+i,type,sign,accm.getid()});
               i++;
       }
     new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{i+"",nparent});

    }
     private void updateBreakpoints(String nparent,String oparent,String type,String sign) throws BasicException{
         List<AccountMasterExt> acclist=dlfac.getBreakpointswithparentid(oparent);
         int flag=0;
         int i=1;
         for(AccountMasterExt accm:acclist){
               new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET PARENT=?,SEARCHKEY=?,TYPE_=?,SIGN=? WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{nparent,nparent+"."+i,type,sign,accm.getid()});
               updateSubAccounts(accm.getSerachkey(), nparent+"."+i,type,sign);
               updateBreakpoints(accm.getSerachkey(), nparent+"."+i,type,sign);
               i++;
         }
         new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{i+"",nparent});
    }
     private void updateAccounts(String nparent,String oparent,String type,String sign) throws BasicException{
         List<AccountMasterExt> acclist=dlfac.getAccountswithparentid(oparent);
         int flag=0,i=0;

      for(AccountMasterExt accm:acclist){

               new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET PARENT=?,SEARCHKEY=?,TYPE_=?,SIGN=? WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{nparent,nparent+"."+i,type,sign,accm.getid()});
               updateSubAccounts(accm.getSerachkey(), nparent+"."+i,type,sign);
               updateBreakpoints(accm.getSerachkey(), nparent+"."+i,type,sign);
               i++;
      }
       new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{i+"",nparent});
    }
   public  void showDialog(AccountTable.AccountLine acc){
       try{
           account=acc;
       emodel=new ComboBoxValModel(dlfac.getaccountElements());
       name.setText(acc.getname());
       description.setText(acc.getdesc());
       if(acc.getLevel().equals("C")){
           type=1;
           hideComponents(true,false,false);
           AccountMasterExt obj=dlfac.getaccountbysearchkey(acc.getparent());
           elementvalue=obj;
           jLabel2.setVisible(true);
           element.setVisible(true);
           element.setModel(emodel);
           element.setSelectedItem(elementvalue);
           emodel.setSelectedKey(elementvalue.getid());
           oldElementID=elementvalue.getid();
           breakdownvalue=null;
           mainheadvalue=null;
       }else if(acc.getLevel().equals("D")){
           type=2;
           hideComponents(true,true,false);
           AccountMasterExt obj=dlfac.getaccountbysearchkey(acc.getparent());
           mainheadvalue=obj;
         AccountMasterExt obj1=dlfac.getaccountbysearchkey(obj.getparent());
           elementvalue=obj1;
           element.setModel(emodel);
           emodel.setSelectedKey(elementvalue.getid());
           mmodel=new ComboBoxValModel(dlfac.getaccountMainHeads(elementvalue.getSerachkey()));
           mainhead.setModel(mmodel);
           oldElementID=elementvalue.getid();
           oldMainheadID=mainheadvalue.getid();
           mmodel.setSelectedKey(mainheadvalue.getid());
           breakdownvalue=null;
       }else if(acc.getLevel().equals("S")){
           type=3;
           hideComponents(true,true,true);
      /*AccountMasterExt obj=dlfac.getaccountbysearchkey(acc.getparent());
           breakdownvalue=obj;
          AccountMasterExt obj1=dlfac.getBreakdownsparentAcc(obj.getparent());
           mainheadvalue=obj1;
          AccountMasterExt obj2=dlfac.getaccountbysearchkey(obj1.getparent());
           elementvalue=obj2;
            emodel.setSelectedKey(elementvalue.getid());
            oldElementID=elementvalue.getid();
            element.setModel(emodel);
           mmodel=new ComboBoxValModel(dlfac.getaccountMainHeads(elementvalue.getSerachkey()));
           mainhead.setModel(mmodel);
           mmodel.setSelectedKey(mainheadvalue.getid());
           oldMainheadID=mainheadvalue.getid();
            bmodel=new ComboBoxValModel(dlfac.getaccountBreakpoints(mainheadvalue.getSerachkey()));
           breakdown.setModel(bmodel);
           oldBreakdownID=breakdownvalue.getid();
           bmodel.setSelectedKey(breakdownvalue.getid());
   */
           AccountMasterExt obj=dlfac.getaccountbysearchkey(acc.getparent());
           mainheadvalue=obj;
           AccountMasterExt obj1=dlfac.getaccountbysearchkey(obj.getparent());
           elementvalue=obj1;
           AccountMasterExt obj2=dlfac.getBreakdownsparentAcc(obj1.getparent());
           breakdownvalue=obj2;
           element.setModel(emodel);
           emodel.setSelectedKey(elementvalue.getid());
           mmodel=new ComboBoxValModel(dlfac.getaccountMainHeads(elementvalue.getSerachkey()));
           mainhead.setModel(mmodel);
           oldElementID=elementvalue.getid();
           oldMainheadID=mainheadvalue.getid();
           mmodel.setSelectedKey(mainheadvalue.getid());
            bmodel=new ComboBoxValModel(dlfac.getaccountBreakpoints(mainheadvalue.getSerachkey()));
           breakdown.setModel(bmodel);
           oldBreakdownID=breakdownvalue.getid();
           bmodel.setSelectedKey(breakdownvalue.getid());
       }
       setVisible(true);
       }catch(Exception e){
           new MessageInf(e).show(getParent());
         e.printStackTrace();
       }
   }
     public boolean getStatus(){
        return status;
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
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        element = new javax.swing.JComboBox();
        mainhead = new javax.swing.JComboBox();
        breakdown = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name ");

        jLabel2.setText("Under Element :");

        jLabel3.setText("Under MainHead :");

        jLabel4.setText("Under BreakDown :");

        name.setNextFocusableComponent(description);

        jLabel5.setText("Description");

        description.setNextFocusableComponent(element);

        element.setNextFocusableComponent(mainhead);
        element.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elementItemStateChanged(evt);
            }
        });

        mainhead.setNextFocusableComponent(breakdown);
        mainhead.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainheadItemStateChanged(evt);
            }
        });

        breakdown.setNextFocusableComponent(jButton1);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(breakdown, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(description)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(mainhead, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(element, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))))))
                        .addContainerGap(122, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(489, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(element, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mainhead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(breakdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void elementItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elementItemStateChanged
        // TODO add your handling code here:
        try{
        AccountMasterExt pvalue=(AccountMasterExt)element.getSelectedItem();
      //  if(prevselecelementvalue!=pvalue){
            mmodel=new ComboBoxValModel(dlfac.getaccountMainHeads(pvalue.getSerachkey()));
            bmodel=new ComboBoxValModel(new ArrayList());
            mainhead.setModel(mmodel);
            breakdown.setModel(bmodel);
      //  }
        }catch(Exception e){
            new MessageInf(e).show(getParent());
          e.printStackTrace();
        }
    }//GEN-LAST:event_elementItemStateChanged

    private void mainheadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainheadItemStateChanged
        // TODO add your handling code here:
         try{
        AccountMasterExt pvalue=(AccountMasterExt)mainhead.getSelectedItem();
      //  if(prevselecmainheadvalue!=pvalue){
            bmodel=new ComboBoxValModel(dlfac.getaccountBreakpoints(pvalue.getSerachkey()));
            breakdown.setModel(bmodel);

       // }
        }catch(Exception e){
            new MessageInf(e).show(getParent());
          e.printStackTrace();
        }
    }//GEN-LAST:event_mainheadItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            Transaction t=new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                    AccountMasterExt acc=new AccountMasterExt();
                    int flag1=0,flag2=1;
            acc=(AccountMasterExt)element.getSelectedItem();
            if(acc!=null){
                if(oldElementID.equals(acc.getid()))
                    flag2=0;
                //else
                    flag1=1;
                acc=(AccountMasterExt)mainhead.getSelectedItem();
                if(acc!=null){
                    if(oldMainheadID.equals(acc.getid()) && flag2==1)//{
                      flag2=0;
                    else
                        flag2=1;
                      flag1=2;
                    //}
                   acc=(AccountMasterExt)breakdown.getSelectedItem();
                   if(acc!=null){
                       if(oldBreakdownID.equals(acc.getid())&& flag2==1)
                          flag2=0;
                       else
                           flag2=1;
                       //else
                          flag1=3;
                   }else
                      acc=(AccountMasterExt)mainhead.getSelectedItem();
                }else
                    acc=(AccountMasterExt)element.getSelectedItem();
            }else{
                flag1=0;
                flag2=0;
            }
          if(flag1==type || (type==3 && flag1==2)){
            if(flag2>=1){
             String parent=acc.getSerachkey();
             String search=acc.getSerachkey()+"."+(String.valueOf(Integer.valueOf(acc.getmax())+1));
             if(type==1){
                updateAccount(search.toString(),parent,account.getid(),acc.gettype(),acc.getsign(),name.getText(),description.getText());
                updateAccounts(search.toString(), account.getSearchkey(),acc.gettype(),acc.getsign());//type
                updateSubAccounts(search.toString(), account.getSearchkey(),acc.gettype(),acc.getsign());//type
                updateBreakpoints(search.toString(), account.getSearchkey(),acc.gettype(),acc.getsign());
                updateParent(Integer.valueOf(acc.getmax())+1, acc.getid());
             }else if(type==2){
                updateAccount(search.toString(),parent,account.getid(),acc.gettype(),acc.getsign(),name.getText(),description.getText());
             //updateAccounts(search.toString(), account.getSearchkey(),acc.gettype(),acc.getsign());//type
                updateSubAccounts(search.toString(), account.getSearchkey(),acc.gettype(),acc.getsign());//type
                updateBreakpoints(search.toString(), account.getSearchkey(),acc.gettype(),acc.getsign());
                updateParent(Integer.valueOf(acc.getmax())+1,acc.getid());
             }else if(type==3){
                 updateAccount(search.toString(),parent,account.getid(),acc.gettype(),acc.getsign(),name.getText(),description.getText());
                 updateParent(Integer.valueOf(acc.getmax())+1, acc.getid());
             }
             status=true;
           }else{
              new PreparedSentence(m_App.getSession()
               , "UPDATE ACCOUNTMASTER SET NAME=?,DESCRIPTION=? WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{name.getText(),description.getText(),account.getid()});
              status=true;
          // }
            }
          }else{
              JOptionPane.showMessageDialog(null, "Sorry cannot change the account level");
          }
            return null;
                }
            };
            t.execute();
           dispose();

        }catch(Exception e){
            new MessageInf(e).show(getParent());
         e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox breakdown;
    private javax.swing.JTextField description;
    private javax.swing.JComboBox element;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox mainhead;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables

}
