/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VoucherEntry1.java
 *
 * Created on Mar 31, 2009, 10:25:37 AM
 */

package com.openbravo.pos.Accounts;

import com.openbravo.pos.clubmang.*;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.format.FormatsException;

import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
//import com.openbravo.pos.Accounts.AccountMasterExt;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.ComponentInputMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


/**
 *
 * @author swathi
 */
public class VoucherEntry1 extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /** Creates new form VoucherEntry */
    private DataLogicFacilities  dlfac;
    private DefaultComboBoxModel fmodel;
    private DefaultComboBoxModel smodel;
    private List<javax.swing.JTextField> textboxlist=new ArrayList<javax.swing.JTextField>();
    private List<javax.swing.JTextField> Tobylist=new ArrayList<javax.swing.JTextField>();
    private List<Object[]> odetail;
    private List<AccountMasterExt> pacclist=new ArrayList<AccountMasterExt>();
    private double oCreditTotal;
    private double oDebitTotal;
    private String transno;
    private AppView m_App;
    private int edited=0;
    private Color LIGHT_GREEN,LIGHT_YELLOW,VERY_LIGHT_BLUE;
    private int num=0;
    private javax.swing.JTextField activetextbox;
    private boolean vedit;
    private String otid;
    private javax.swing.JDialog dialog;
    private List cashieracc;
    public VoucherEntry1() {
        initComponents();
//        LIGHT_GREEN = new Color(0x40, 0xFF, 0x40);
//        LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x40);
//        VERY_LIGHT_BLUE = new Color(0x80, 0x80, 0xFF);
        LIGHT_GREEN = new Color(0xCC, 0xFF, 0xCC);
        LIGHT_YELLOW = new Color(0xFF, 0xFF, 0xCC);
        VERY_LIGHT_BLUE = new Color(0x99, 0xCC, 0xFF);
    }

    public void setValues(List<Object[]> detail,String tid,javax.swing.JDialog dialog,int status) throws BasicException{
        this.dialog=dialog;
        otid=tid;
        vedit=true;
        odetail=new ArrayList<Object[]>();
        if(status==1)
            jLabel9.setVisible(true);
        else
            jLabel9.setVisible(false);
        odetail.addAll(detail);
        Object[] arr=detail.get(0);
        jLabel4.setText(arr[0].toString());
        narration.setText(arr[1].toString());
        jLabel6.setText(arr[3].toString());
        jLabel8.setText(Formats.TIMESTAMP.formatValue(arr[6]));
        jList1.setVisible(false);
        scrollpaneforlist.setVisible(false);
        textboxlist=new ArrayList<javax.swing.JTextField>();
        Tobylist=new ArrayList<javax.swing.JTextField>();
        pacclist=new ArrayList<AccountMasterExt>();
        cashieracc=new ArrayList();
        activetextbox=null;
        num=0;
        edited=0;
        jPanel1.removeAll();
        setPanel1();
        String toby=null;
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        if(jLabel4.getText().equals("Receipt") ){
            toby="To";
            jPanel1.setBackground(LIGHT_GREEN);
        }else if(jLabel4.getText().equals("Payments") ){
            toby="By";
            jPanel1.setBackground(LIGHT_YELLOW);
        } else if(jLabel4.getText().equals("Journal") ){
            toby="To";
            jPanel1.setBackground(VERY_LIGHT_BLUE);
        } else if(jLabel4.getText().equals("Contra") ){
            toby="To";
            jPanel1.setBackground(Color.white);
        }
        int j=0;
        double tcredit=0.0;
        double tdebit=0.0;
        for(int i=0;i<detail.size();i++){
           // if()
            Object arr1[]=detail.get(i);
           if(arr1[2].toString().equals("C")){
               toby="To";
               tcredit +=Double.parseDouble(arr1[5].toString());
           }
           else{
               toby="By";
               tdebit +=Double.parseDouble(arr1[5].toString());
           }
            createNewEntry(num, toby);
            int t=j;
            if(status==1){
              Tobylist.get(i).setEditable(false);
              textboxlist.get(t++).setEditable(false);
              textboxlist.get(t++).setEditable(false);
            }

            AccountMasterExt acc= dlfac.getaccountbyid(arr1[4].toString());
            textboxlist.get(j++).setText(acc.getName());
            textboxlist.get(j++).setText(Formats.CURRENCY.formatValue(arr1[5]));
            Tobylist.get(i).setEditable(true);
            pacclist.add(acc);
        }
        oCreditTotal=tcredit;
        oDebitTotal=tdebit;
        credittotal.setText(Formats.CURRENCY.formatValue(tcredit));
        debittotal.setText(Formats.CURRENCY.formatValue(tdebit));
        for(JTextField txtField:textboxlist){
           txtField.setBackground(Color.lightGray);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    textboxlist.get(0).requestFocus();
                }
            });

    }

     public void activate() throws BasicException {
        jList1.setVisible(false);
        jLabel9.setVisible(false);
        jLabel4.setText("Receipt");
        scrollpaneforlist.setVisible(false);
        fmodel=new javax.swing.DefaultComboBoxModel(new String[] { "To", "By" });
        fmodel.setSelectedItem(null);
        vedit=false;
        jPanel1.setBackground(LIGHT_GREEN);
        textboxlist=new ArrayList<javax.swing.JTextField>();
        Tobylist=new ArrayList<javax.swing.JTextField>();
        pacclist=new ArrayList<AccountMasterExt>();
        cashieracc=new ArrayList();
        activetextbox=null;
        num=0;
        edited=0;
        jPanel1.removeAll();
        setPanel1();
        jPanel1.validate();
        jPanel1.repaint();
        createNewEntry( num,"To");
        Date date=new Date();
        date.setTime(getDate(date).getTime());
        transno=dlfac.getnextTranscationNum(date, jLabel4.getText());
        jLabel6.setText(transno);
         jLabel8.setText(Formats.TIMESTAMP.formatValue(date));
        // Color LIGHT_GREEN = new Color(0x40, 0xFF, 0x40);
         //.addComponent(scrollpaneforlist, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        jPanel1.setBackground(LIGHT_GREEN);
        debittotal.setText(Formats.CURRENCY.formatValue(0.0));
        credittotal.setText(Formats.CURRENCY.formatValue(0.0));
    }
    private Date getDate(Date d) throws BasicException{
      Calendar cal=Calendar.getInstance();
      cal.setTimeInMillis(d.getTime());
      cal.set(Calendar.HOUR_OF_DAY, 00);
      cal.set(Calendar.MINUTE, 00);
      cal.set(Calendar.SECOND, 00);
      cal.set(Calendar.MILLISECOND, 00);
      d.setTime(cal.getTimeInMillis());

      return d;
    }
    private void createNewline(int count,String type){
     String pname="particulars_"+count;
    // String dname="debit_"+count;
    // String cname="credit_"+count;
     int incr=30 * count;
     javax.swing.JTextField textbox=new javax.swing.JTextField();//70, 30, 280, 14
     textbox.setBounds(70, 30+incr, 280, 14);
     textbox.setBorder(null);
     textbox.setText(null);
     textbox.setBackground(Color.gray);
     textbox.setName(pname);
     jPanel1.add(textbox);
     textboxlist.add(textbox);
     textboxlist.get(textboxlist.size()-1).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                    textboxKeyReleased(evt,(JTextField)evt.getSource());
            }
        });
     // textbox.setBackground(Color.lightGray);
     textbox=new javax.swing.JTextField();
     if(type.equals("By"))
     textbox.setBounds(390, 30+incr, 100, 14);//390, 30, 100, 14
     else  if(type.equals("To"))
         textbox.setBounds(510, 30+incr, 90, 14);
     textbox.setBorder(null);
     textbox.setText(null);
     textbox.setHorizontalAlignment(JTextField.RIGHT);
    // textbox.setName("debit_"+count);
     jPanel1.add(textbox);
     textbox.setBackground(Color.gray);
     textboxlist.add(textbox);
     textboxlist.get(textboxlist.size()-1).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxKeyReleasedcd(evt,(JTextField)evt.getSource());

            }
        });
        jPanel1.validate();
        jPanel1.repaint();
    }

    private void createNewEntry(int count,String settext){
        javax.swing.JTextField Toby=new javax.swing.JTextField();
        Toby.setBounds(10, 30+(count * 30), 40, 14);
       // Toby.setModel(model);
     //   combobox.setSelectedIndex(-1);
        Toby.setBorder(null);
        if(num==0){
         Toby.setEditable(false);
        }
        Toby.setBackground(Color.lightGray);
        Toby.setName("toby_"+count);
        Toby.setText(settext);
        jPanel1.add(Toby);
        Tobylist.add(Toby);
        Tobylist.get(Tobylist.size()-1).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
               try{
                TobyKeyPressed(evt,Tobylist.indexOf(evt.getSource()));
               }catch(Exception e){
                 e.printStackTrace();
               }
            }
        });
        focusindex=num;
        if(num==0){
          textboxFocus();
        }else
        tobyTextboxFocus();
        num++;
        if(settext.equals("To") ){
          // Tobylist.get(i).setText("TO");
           // temp.setText("TO");
           createNewline(num-1, "To");
        }else{
           createNewline(num-1, "By");
        }
    }

    public String getTitle() {
       return null;
    }


    private void setPanel1(){
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(scrollpaneforlist, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(607, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollpaneforlist, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(928, Short.MAX_VALUE))
        );
    }

  /*  private Date getDate(Date d){
     // Date d=new Date();
      Calendar cal=GregorianCalendar.getInstance();
      cal.setTimeInMillis(d.getTime());
      cal.set(Calendar.HOUR, 00);
      cal.set(Calendar.MINUTE, 00);
      cal.set(Calendar.SECOND, 00);
      cal.set(Calendar.MILLISECOND, 00);
      d.setTime(cal.getTimeInMillis());
      return d;
    }*/
    public boolean deactivate() {
      return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         Action Saveaction=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                save();
            }
        };
        Action Recpaction=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                 buttonClicked(jButton1.getText(), LIGHT_GREEN, num, "To");
            }
        };
        //nnnn
        Action Paymentaction=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                buttonClicked(jButton2.getText(), LIGHT_YELLOW, num, "By");
            }
        };
        Action journalaction=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                buttonClicked(jButton3.getText(), VERY_LIGHT_BLUE, num, "By");
            }
        };
        Action Contraaction=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                buttonClicked(jButton4.getText(), Color.white, num, "To");
            }
        };
        Action Dateaction=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                date();
            }
        };
        KeyStroke key=KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK);
        ComponentInputMap map=new ComponentInputMap(save);
        map.put(key, "Saveactionperformed");
        map.setParent(save.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        save.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        save.getActionMap().put("Saveactionperformed", Saveaction);
        key=KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK);
        map=new ComponentInputMap(jButton1);
        map.put(key, "Recpactionperformed");
        map.setParent(jButton1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton1.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        jButton1.getActionMap().put("Recpactionperformed", Recpaction);
        key=KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK);
        map=new ComponentInputMap(jButton2);
        map.put(key, "Paymentactionperformed");
        map.setParent(jButton2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton2.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        jButton2.getActionMap().put("Paymentactionperformed", Paymentaction);
        key=KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_DOWN_MASK);
        map=new ComponentInputMap(jButton3);
        map.put(key, "Journalactionperformed");
        map.setParent(jButton3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton3.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        jButton3.getActionMap().put("Journalactionperformed", journalaction);
        key=KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK);
        map=new ComponentInputMap(jButton4);
        map.put(key, "Contraactionperformed");
        map.setParent(jButton4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton4.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        jButton4.getActionMap().put("Contraactionperformed", Contraaction);
        key=KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK);
        map=new ComponentInputMap(jButton7);
        map.put(key, "Dateactionperformed");
        map.setParent(jButton7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton7.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        jButton7.getActionMap().put("Dateactionperformed", Dateaction);

     //   smodel=new javax.swing.DefaultComboBoxModel(new String[] { "By", "To" });
     //   smodel.setSelectedItem(null);

      //  createNewline(num-1, "TO");
    }

    public Object getBean() {
       return this;
    }
     private class ItemsListModel extends AbstractListModel {
        private java.util.List items;
        public ItemsListModel(java.util.List items) {
            this.items = items;
        }
        public int getSize() {
            return items.size();
        }
        public Object getElementAt(int i) {
            return items.get(i);
        }


    }
     private void Calculate() throws BasicException{
         JTextField texbox;
         Double debit=0.0,credit=0.0;
        for(int i=0;i<textboxlist.size();i++){
                  texbox=new javax.swing.JTextField();
                  texbox=textboxlist.get(i+1);
                   i++;
                  Double amount;
                  String del=texbox.getText();
                  if(del.length()>0)
                      amount=Double.valueOf(Formats.CURRENCY.parseValue(del).toString());
                  else
                       amount=0.0;
                  del=null;
                  if(texbox.getX()==390){
                       debit+=amount;
                  }else if(texbox.getX()==510){
                      credit+=amount;
                  }
              }
         credittotal.setText(Formats.CURRENCY.formatValue(credit));
         debittotal.setText(Formats.CURRENCY.formatValue(debit));
     }

     private void textboxKeyReleasedcd(java.awt.event.KeyEvent evt,javax.swing.JTextField textboxp)  {

         JTextField textbox=(JTextField)evt.getSource();
          try{
          if(evt.getKeyText(evt.getKeyCode()).equals("Enter") ||evt.getKeyCode()==KeyEvent.VK_TAB){
              textbox.setBackground(Color.lightGray);
              if(textbox.getText().length()<=0){
                 textbox.setText("0.0");
              }
              textbox.setText(Formats.CURRENCY.formatValue((Formats.CURRENCY.parseValue(textbox.getText()))));
              Double debit=0.0;
              Double credit=0.0;
              javax.swing.JTextField texbox;
              for(int i=0;i<textboxlist.size();i++){
                  texbox=new javax.swing.JTextField();
                   texbox=textboxlist.get(i+1);
                   i++;
                  Double amount;
                  String del=texbox.getText();
                   if(del.length()>0)
                      amount=Double.valueOf(Formats.CURRENCY.parseValue(del).toString());
                   else
                       amount=0.0;
                  del=null;
                   if(texbox.getX()==390){
                       debit+=amount;
                   }else if(texbox.getX()==510){
                      credit+=amount;
                   }
              }
               credittotal.setText(Formats.CURRENCY.formatValue(credit));
               debittotal.setText(Formats.CURRENCY.formatValue(debit));
               if(credittotal.getText().equals(debittotal.getText())){
                    int size1=textboxlist.size();
                    String del=textboxlist.get(textboxlist.size()-1).getText();
                    int del2=textboxlist.get(textboxlist.size()-1).getText().length();
                   if(textboxlist.get(textboxlist.size()-1).getText().length()<=0){
                       int tobysize=Tobylist.size();
                       int size=textboxlist.size();
                       JTextField t1=textboxlist.get(size-1);
                       JTextField t2=textboxlist.get(size-2);
                       JTextField t3=Tobylist.get(tobysize-1);
                       jPanel1.remove(t1);
                       jPanel1.remove(t2);
                       jPanel1.remove(t3);
                       textboxlist.remove(size-1);
                       textboxlist.remove(size-2);
                       Tobylist.remove(tobysize-1);
                       if(pacclist.size()==size/2){
                           pacclist.remove(pacclist.size());
                       }
                       num--;
                       jPanel1.validate();
                       jPanel1.repaint();
                   }
               }
               if(textboxlist.indexOf(textboxp)==textboxlist.size()-1){
               if(credit>debit){
                     createNewEntry(num, "By");
               }else if(credit<debit){
                     createNewEntry(num, "To");
               }
               if(credittotal.getText().equals(debittotal.getText())){
                 /*  if(textboxlist.get(textboxlist.size()-1).getText().length()<=0){
                       int size=textboxlist.size();
                       textboxlist.remove(size-1);
                       textboxlist.remove(size-2);
                       if(pacclist.size()==size/2){
                           pacclist.remove(pacclist.size());
                       }
                   }*/
                   boolean flag=false;
                   int i=0;

                   try{
                  if(jLabel4.getText().equals("Receipt")){
                       for(JTextField toby: Tobylist){
                        if(toby.getText().equals("By")){
                           String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                           if((parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3"))){
                              flag=true;
                              break;
                           }
                        }
                        i++;
                       }
                   }else if(jLabel4.getText().equals("Payments")){
                      //  int i=0;
                     for(JTextField toby: Tobylist){
                        if(toby.getText().equals("To")){
                           String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                           if((parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3"))){
                              flag=true;
                              break;
                          }

                        }
                      i++;
                     }
                   }else{
                      flag=true;
                   }
                   }catch(Exception e){
                     e.printStackTrace();
                      MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, "message.nosave", e);
                         msg.show(this);
                   }
                   if(flag==true){
                   java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        narration.requestFocus();
                    }
                });
                   }else{
                        focusindex=textboxlist.indexOf(textbox);
                      textbox.setFocusable(false);
                     JOptionPane.showMessageDialog(this, " please select a cash and bank account ");
                     // this.setFocusable(true);

                      textbox.setFocusable(true);
                      textboxFocus();
                    //   textbox.setFocusable(true);
                   }
               }
               }else
               {
                   focusindex=textboxlist.indexOf(textboxp)/2 +1;
                   tobyTextboxFocus();
               }
           /*   int tempindex=textboxlist.indexOf(textbox)-1;
            if(tempindex==0)
                focusindex=0;
            else
                focusindex=tempindex/2;
                tobyTextboxFocus();*/
          }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){

               focusindex=textboxlist.indexOf(textbox)-1;//will get the coressponding particulars index
               textboxFocus();
          }else{
             textbox.setBackground(Color.gray);
          }
          }catch(Exception e){
               textbox.setFocusable(false);
            JOptionPane.showMessageDialog(this, "Invalid value.Please enter a proper value", null, JOptionPane.OK_OPTION);
             focusindex=textboxlist.indexOf(textbox);
                      textboxFocus();
                       textbox.setFocusable(true);
                       e.printStackTrace();
          }
     }

    // private String enteredtext=null;
     private void textboxFocus(){

        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try{
                   textboxlist.get(focusindex).requestFocus();
                    }catch(Exception e){
                      e.printStackTrace();
                    }
                }
            });
     }

     private void tobyTextboxFocus(){
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try{
                   Tobylist.get(focusindex).requestFocus();
                   }catch(Exception e){
                      e.printStackTrace();
                    }
                }
            });
     }
     private int focusindex;
   //  private int flag=0;
      private void textboxKeyReleased(java.awt.event.KeyEvent evt,javax.swing.JTextField textboxp) {
        // TODO add your handling code here:
          edited=1;
          activetextbox=textboxp;
          String name=textboxp.getText();
           JTextField textbox=(JTextField)evt.getSource();
        try {
            if(evt.getKeyCode()!=KeyEvent.VK_LEFT && evt.getKeyCode()!=KeyEvent.VK_UP && evt.getKeyCode()!=KeyEvent.VK_DOWN && evt.getKeyCode()!= KeyEvent.VK_ESCAPE && evt.getKeyCode()!=KeyEvent.VK_ENTER && evt.getKeyCode()!=KeyEvent.VK_CONTROL){
              activetextbox.setBackground(Color.GRAY);
             List acclist;
            if(jLabel4.getText().equals("Journal"))
                 acclist=dlfac.getsubAccountsExceptCashAndBank(name.toUpperCase());
            else if(jLabel4.getText().equals("Contra"))
                acclist=dlfac.getCashAndBanksubAccounts(name.toUpperCase());
            else
                acclist= dlfac.getsubAccounts(name.toUpperCase());
            jList1.setVisible(true);
            scrollpaneforlist.setVisible(true);
            jList1.removeAll();
            jList1.setModel(new ItemsListModel(acclist));
            jList1.setSelectedIndex(0);
            if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
              java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   jList1.requestFocus();
                }
            });
          }
            jPanel1.validate();
            jPanel1.repaint();
        }
          else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){

          if(scrollpaneforlist.isVisible()==false){
            int tempindex=textboxlist.indexOf(textbox);
            if(tempindex==0)
                focusindex=0;
            else
                focusindex=tempindex/2;               //will get the coressponding toby textbox's index
            tobyTextboxFocus();
          }
          //  jScrollPane1.setVisible(false);
        }else if(evt.getKeyCode()==KeyEvent.VK_CONTROL){
           scrollpaneforlist.setVisible(false);
           activetextbox.setText(null);
           jList1.setVisible(false);
           activetextbox.setBackground(Color.GRAY);
        }
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
             int index=textboxlist.indexOf(activetextbox);
             focusindex=index+1;
           if(jList1.isVisible()==true && jList1.getModel().getSize()>0){
             activetextbox.setText(jList1.getSelectedValue().toString());
             scrollpaneforlist.setVisible(false);
             jList1.setVisible(false);
             if((index/2)<pacclist.size())
              pacclist.set(index/2,(AccountMasterExt)jList1.getSelectedValue() );
             else
              pacclist.add(index/2,(AccountMasterExt)jList1.getSelectedValue() );
             activetextbox.setBackground(Color.lightGray);
           }


             java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    textboxlist.get(focusindex).requestFocus();
                }
            });
          }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}
     private void TobyKeyPressed(java.awt.event.KeyEvent evt,int index) throws BasicException{
         JTextField temp=(JTextField)evt.getSource();
         if(evt.getKeyCode()==KeyEvent.VK_ENTER || evt.getKeyCode()==KeyEvent.VK_TAB){
          if((textboxlist.size()/2)!=Tobylist.size()){
            if(temp.getText().toUpperCase().equals("TO") || temp.getText().toUpperCase().equals("T")){
              temp.setText("To");
              createNewline(num-1, "To");
              }else{
              temp.setText("By");
              createNewline(num-1, "By");
           }
          }else{
                JTextField newtextbox;
                newtextbox=textboxlist.get(index*2+1);
                Double textboxamount=0.0;
                if(newtextbox.getText().length()>0){
                    try{
                      textboxamount=Double.valueOf(Formats.CURRENCY.parseValue(newtextbox.getText()).toString());
                    }catch(NumberFormatException e){
                       textboxamount=0.0;
                    }
                }
                Rectangle bounds=newtextbox.getBounds();
                //  try{
                if(temp.getText().toUpperCase().equals("TO") || temp.getText().toUpperCase().equals("T")){
                 //debit
                  // newtextbox.getX()==390
                    temp.setText("To");
                   if(newtextbox.getX()==390){
                      
                      newtextbox.setBounds(510, bounds.y, 90, bounds.height);
                      textboxlist.remove(index*2+1);
                      textboxlist.add(index*2+1, newtextbox);
                      jPanel1.add(newtextbox);
                      jPanel1.validate();
                      Calculate();
                    //  credittotal.setText(Formats.CURRENCY.formatValue(Double.valueOf(Formats.CURRENCY.parseValue(credittotal.getText()).toString())+textboxamount));
                   //   debittotal.setText(Formats.CURRENCY.formatValue(Double.valueOf(Formats.CURRENCY.parseValue(debittotal.getText()).toString())-textboxamount));
                   // }
                }}
                  else {//if(newtextbox.getX()==510){
                  //  if(temp.getText().toUpperCase().equals("BY") || temp.getText().toUpperCase().equals("B")){
                      temp.setText("By");
                      newtextbox.setBounds(390, bounds.y, 100, bounds.height);
                      textboxlist.remove(index*2+1);
                      textboxlist.add(index*2+1, newtextbox);
                      jPanel1.add(newtextbox);
                      jPanel1.validate();
                       Calculate();
                      //debittotal.setText(Formats.CURRENCY.formatValue(Double.valueOf(Formats.CURRENCY.parseValue(debittotal.getText()).toString())+textboxamount));
                     // credittotal.setText(Formats.CURRENCY.formatValue(Double.valueOf(Formats.CURRENCY.parseValue(credittotal.getText()).toString())-textboxamount));
                  //  }
                  }
          }
           JTextField textbox=(JTextField)evt.getSource();
            focusindex=Tobylist.indexOf(textbox)*2;
            textboxFocus();
         }if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
          JTextField textbox=(JTextField)evt.getSource();
            focusindex=Tobylist.indexOf(textbox)*2-1;
            textboxFocus();

         }

     }

       private void updateaccountjournaldup(Boolean Flag , String Type) throws BasicException{
          String id=null;
          String tid=UUID.randomUUID().toString();
          if(credittotal.getText().equals(debittotal.getText())){
          int i=0;
           Date date = (Date) Formats.TIMESTAMP.parseValue(jLabel8.getText());
           Date d=new Date();
           String narration1=narration.getText();
       //   if(vedit==false){
           boolean flag=false;
           AppUser appuser=m_App.getAppUserView().getUser();
           for(JTextField toby: Tobylist){
                   String accid=pacclist.get(i).getid();
                   String del=textboxlist.get((i*2)).getText();
                   /*if(toby.getText().equals("By")){
               Double amt=Double.parseDouble(Formats.CURRENCY.parseValue(textboxlist.get((i*2)+1).getText()).toString());
               Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"D",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,accid,amt,null,d,otid,true};
               dlfac.insertintoaccjoutnal4dup(value);
               }else if(toby.getText().equals("To")){
               Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"C",jLabel4.getText(),transno,Double.parseDouble(Formats.CURRENCY.parseValue(textboxlist.get((i*2)+1).getText()).toString()),d,true,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,accid,0.0,d,d,otid,true};
               dlfac.insertintoaccjoutnal4dup(value);
               }*/
                   //sanjeev:changed else if for fixing ledger and receipt table diff
                   
                   
                   Double amt=Double.parseDouble(Formats.CURRENCY.parseValue(textboxlist.get((i*2)+1).getText()).toString());
                   if(toby.getText().equals("By")){
                       if(PettycashAccStr.equals(accid)){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"D",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,accid,amt,null,d,otid,true};
                       dlfac.insertintoaccjoutnal4dup(value);
                   }else if(toby.getText().equals("To")){
                       if(PettycashAccStr.equals(accid)){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"C",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,accid,amt,null,d,otid,true};
                       dlfac.insertintoaccjoutnal4dup(value);
                   }
                   
                   
                   
                   
                   if(cashieracc.contains(accid)){
                       new PreparedSentence(m_App.getSession()
                     , "INSERT INTO ACCOUNTEDITDETAIL(ID,TID,INITIATOR,CONFIRMER) VALUES (?,?,?,?)"
                     , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),tid,m_App.getAppUserView().getUser().getId(),pacclist.get(i).getid()});
                   }else if(appuser.getcashaccount()!=null && appuser.getchequeaccount()!=null){
                       if(appuser.getcashaccount().equals(accid) || appuser.getchequeaccount().equals(accid)){
                           flag=true;
                       new PreparedSentence(m_App.getSession()
                     , "INSERT INTO ACCOUNTEDITDETAIL(ID,TID,INITIATOR,CONFIRMER,CONFIRMEDBY) VALUES (?,?,?,?,?)"
                     , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),tid,appuser.getId(),appuser.getcashaccount(),appuser.getId()});
                   }
                 }
              i++;
             }
             // EDITED BY AKASH 
             if(Flag){
                 InsertintoVoucherEntryConfirmation(tid, Type);
             } 
           
           if(flag==false){
               new PreparedSentence(m_App.getSession()
                     , "INSERT INTO ACCOUNTEDITDETAIL(ID,TID,INITIATOR,CONFIRMER,CONFIRMEDBY) VALUES (?,?,?,?,?)"
                     , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),tid,appuser.getId(),appuser.getId(),appuser.getId()});
           }

            }else{
             JOptionPane.showMessageDialog(this, "The total credit is not equal to total debit", "Error", JOptionPane.OK_OPTION);
          }
      }

      private void updateaccountjournal(Boolean Flag , String Type ) throws BasicException{
          String id=null;
          String tid=UUID.randomUUID().toString();
          
          
          if(credittotal.getText().equals(debittotal.getText())){
              if(vedit==true){
                                    new PreparedSentence(m_App.getSession()
                                            , "UPDATE ACCOUNTJOURNAL SET ACTIVE=FALSE,DEACTBY=?,DEACTDATE=? WHERE TID= ? "
                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(),new Date(),otid});
                                     List<Object[]> objList=new PreparedSentence(m_App.getSession(), "SELECT ACCOUNTID,AMOUNT,TRANSTYPE,DATE FROM ACCOUNTJOURNAL WHERE TID=?"
                                                        , SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).list(otid.toString());
                              for(Object[] obj:objList){
                                  if(obj!=null){
                                       Date d1=(Date)obj[3];
                                       Calendar cal=Calendar.getInstance();
                                       cal.setTimeInMillis(d1.getTime());
                                       cal.set(Calendar.HOUR_OF_DAY, 00);
                                       cal.set(Calendar.MINUTE, 00);
                                       cal.set(Calendar.SECOND, 00);
                                       cal.set(Calendar.MILLISECOND, 00);
                                       cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                                       d1.setTime(cal.getTimeInMillis());
                                        if(obj[2].toString().equals("C")){
                                           new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT-?) where ACCOUNTID=? AND EDATE=? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{obj[1],obj[0],d1});
                                           new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT-?) where ACCOUNTID=?  "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{obj[1],obj[0]});
                                       }else{
                                            new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT-?) where ACCOUNTID=? AND EDATE=? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{obj[1],obj[0],d1});
                                            new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT-?) where ACCOUNTID=?  "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{obj[1],obj[0]});
                                       }
                                  }
                              }
                    // EDITED BY AKASH            
                     new PreparedSentence(m_App.getSession()
                         , "UPDATE VOUCHERENTRY SET APPROVED=3,REJECTBY=?,REJECTDATE=? , REJECTHOST=?  WHERE TID= ? "
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING ,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(),new Date(), m_App.getProperties().getHost(),otid});
           
           
           
              }
              int i=0;
           Date date = (Date) Formats.TIMESTAMP.parseValue(jLabel8.getText());
            Date d=new Date();
            String narration1=narration.getText().trim();
          if(jLabel9.isVisible()==false){

        if(vedit==false){
          for(JTextField toby: Tobylist){
               Double amt=Double.parseDouble(Formats.CURRENCY.parseValue(textboxlist.get((i*2)+1).getText()).toString());
                   if(toby.getText().equals("By")){
                       if(PettycashAccStr.equals(pacclist.get(i).getid())){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"D",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,pacclist.get(i).getid(),amt,null,d,true};
                       dlfac.insertintoaccjoutnal3(value);
                   }else if(toby.getText().equals("To")){
                       if(PettycashAccStr.equals(pacclist.get(i).getid())){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"C",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,pacclist.get(i).getid(),amt,null,d,true};
                       dlfac.insertintoaccjoutnal3(value);
                   }
           i++;
           }
          
           //////////// EDITED BY AKASH 
           if(Flag){
                InsertintoVoucherEntryConfirmation(tid, Type);
           }
          
          
        }else{
            for(JTextField toby: Tobylist){
        //   String del=textboxlist.get((i*2)).getText();
                 Double amt=Double.parseDouble(Formats.CURRENCY.parseValue(textboxlist.get((i*2)+1).getText()).toString());
                   if(toby.getText().equals("By")){
                        if(PettycashAccStr.equals(pacclist.get(i).getid())){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"D",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,pacclist.get(i).getid(),amt,null,d,true,otid};
                       dlfac.insertintoaccjoutnal5(value);
                   }else if(toby.getText().equals("To")){
                        if(PettycashAccStr.equals(pacclist.get(i).getid())){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"C",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,pacclist.get(i).getid(),amt,null,d,true,otid};
                       dlfac.insertintoaccjoutnal5(value);
                   }
           i++;
           }
            
           //////////// EDITED BY AKASH  
           if(Flag){
                InsertintoVoucherEntryConfirmation(tid, Type);
           } 
            
        }
                   dlfac.updateTransNumber(jLabel4.getText(), date, Integer.parseInt(transno));
          }else{

              for(JTextField toby: Tobylist){
        //   String del=textboxlist.get((i*2)).getText();
                  Object[] obj=odetail.get(i);
                  String temp;
                  if(obj[8]==null)
                    temp="";
                  else
                      temp=obj[8].toString();
                   Double amt=Double.parseDouble(Formats.CURRENCY.parseValue(textboxlist.get((i*2)+1).getText()).toString());
                   if(toby.getText().equals("By")){
                       if(PettycashAccStr.equals(pacclist.get(i).getid())){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"D",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,pacclist.get(i).getid(),Double.parseDouble(obj[7].toString()),null,d,true,otid+temp};
                       dlfac.insertintoaccjoutnal4(value);
                   }else if(toby.getText().equals("To")){
                       if(PettycashAccStr.equals(pacclist.get(i).getid())){                 ////////////////////// edited by akash
                           Flag=false;
                       }
                       Object[] value=new Object[]{UUID.randomUUID().toString(),tid,date,"C",jLabel4.getText(),transno,amt,d,false,m_App.getAppUserView().getUser().getName(),m_App.getProperties().getHost(),narration1,pacclist.get(i).getid(),Double.parseDouble(obj[7].toString()),null,d,true,otid+temp};
                       dlfac.insertintoaccjoutnal4(value);
                   }
           i++;
           }
               
             //////////// EDITED BY AKASH   
              if(Flag){
                InsertintoVoucherEntryConfirmation(tid, Type);
                } 
              
                dlfac.updateTransNumber(jLabel4.getText(), date, Integer.parseInt(transno));
          }
          }else{
             JOptionPane.showMessageDialog(this, "The total credit is not equal to total debit", "Error", JOptionPane.OK_OPTION);
          }
      }
      private void clearpanel(String name) {
          try{
           int del=jPanel1.getComponentCount();
           scrollpaneforlist.setVisible(false);
           jList1.setVisible(false);
           num=0;
           textboxlist=new ArrayList<JTextField>();
           Tobylist=new ArrayList<JTextField>();
           narration.setText(null);
          
           Date date=(Date) Formats.TIMESTAMP.parseValue(jLabel8.getText());
           date.setTime(getDate(date).getTime());
           jLabel4.setText(null);
           jLabel4.setText(name);
           transno=dlfac.getnextTranscationNum(date, name);
           jLabel6.setText(transno);
           //jLabel8.setText(Formats.TIMESTAMP.formatValue(date));                                       // EDITED BY AKASH                
           debittotal.setText(Formats.CURRENCY.formatValue(0.0));
           credittotal.setText(Formats.CURRENCY.formatValue(0.0));
           jPanel1.removeAll();
           setPanel1();
           jPanel1.validate();
           jPanel1.repaint();
          }
          catch(Exception e){
             MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, "message.nosave", e);
              msg.show(this);
          }
      }
      private boolean checkValueEdited() throws BasicException{
           boolean vValueEdited=false;
          // try{

           Object[] arr=odetail.get(0);
           Date d=(Date)Formats.TIMESTAMP.parseValue(jLabel8.getText());
           Date d1=(Date)arr[6];
           Calendar cal=Calendar.getInstance();
           cal.setTimeInMillis(d.getTime());
           Calendar cal1=Calendar.getInstance();
           cal1.setTimeInMillis(d1.getTime());

           if(!cal.equals(cal1) || odetail.size()!=pacclist.size() || !arr[1].toString().equals(narration.getText())){
             vValueEdited=true;
           }
           if(vValueEdited==false && jLabel9.isVisible()==false){
            //    Double camt1=Double.valueOf(credittotal.getText());
            //   Double camt=(credittotal.getText());
           //  Double damt=Double.valueOf(Formats.CURRENCY.parseValue(debittotal.getText()).toString());
          //   boolean t1=oCreditTotal==camt;
          //   boolean t3=oCreditTotal==camt1;
          //   boolean t2=oDebitTotal!=damt;
            String oc=   Formats.CURRENCY.formatValue(oCreditTotal);
             String od=   Formats.CURRENCY.formatValue(oDebitTotal);
            if(!credittotal.getText().equals(oc) || !debittotal.getText().equals(od)){
               vValueEdited=true;
             }
            if(vValueEdited==false){

             for(int i=0;i<pacclist.size();i++){
               //   boolean f2=false;
               if(vValueEdited==false){
                    boolean f1=false;
                 for(int j=0;j<odetail.size();j++){
                    Object[] arr1=odetail.get(j);

                      if(pacclist.get(i).getid().equals(arr1[4])){
                          f1=true;
                        //  f2=true;
                          String toby=null;
                          if(arr1[2].toString().equals("C"))
                              toby="To";
                          else
                              toby="By";
                        if(Tobylist.get(i).getText().equals(toby)){
                              if(textboxlist.get((i*2)+1).getText().equals(Formats.CURRENCY.formatValue(arr1[5]))){
                                vValueEdited=false;
                              }else{
                                vValueEdited=true;
                              }
                        }else{
                            vValueEdited=true;
                        }

                      }
                 }
                    if(f1==false)
                        vValueEdited=true;
                 }else{
                   break;
                 }
             }
            }

           }
        //   }catch(Exception e){
       //      e.printStackTrace();
      //     }
           return vValueEdited;
      }
      String PettycashAccStr;
      private void Evaluate() throws BasicException{
       //   try{
          oDebitTotal=Double.valueOf(Formats.CURRENCY.parseValue(debittotal.getText()).toString());
          oCreditTotal=Double.valueOf(Formats.CURRENCY.parseValue(credittotal.getText()).toString());
          if(oDebitTotal == oCreditTotal){
            boolean flag;
            boolean flag1=false;
            String cashiercashacc=m_App.getAppUserView().getUser().getcashaccount();
            String cashierchequeacc=m_App.getAppUserView().getUser().getchequeaccount();
            GeneralSettingInfo pettycasfacc=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("Petty Cash Account");
            PettycashAccStr = pettycasfacc.getValue();
            cashieracc=dlfac.getUsersCashAndChequeAccount();
             cashieracc.add(pettycasfacc.getValue());
            cashieracc.remove(cashiercashacc);
            cashieracc.remove(cashierchequeacc);

        if(jLabel4.getText().equals("Receipt")){
            flag=false;
            int i=0;
            for(JTextField toby: Tobylist){
                String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                        if(toby.getText().equals("By")){
                           if((parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3"))){
                              flag=true;
                             // break;
                           }
                           if(cashieracc.contains(pacclist.get(i).getid())){
                              flag1=true;
                           }
                        }else{
                            // String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                           System.out.println(parentname);
                           if((parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3"))){
                              flag=false;
                              break;
                           }
                          // if(cashieracc.contains(pacclist.get(i).getid())){
                          //    flag1=true;
                          // }
                        }
                        i++;
                       }
             if(flag==true && flag1==false){
                
                 
     //  edited by akash               
               Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Receipt Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Receipt");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){
                       updateaccountjournal(true ,"Receipt" ); 
                   }
                   else{
                        JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                  updateaccountjournal(false ,"Receipt" ); 
               }
               
               
                 
              if(vedit==false){
                clearpanel("Receipt");
                createNewEntry( num,"To");
              }//else{
               //}
             }else if(flag==false){
                 JOptionPane.showMessageDialog(null, " Please Debit a cash or bank account and do not credit cash or bank account",null,JOptionPane.OK_OPTION);
             }
             if(flag==true && flag1==true){
              
    //  edited by akash             
              Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Receipt Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Receipt");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){   
                             updateaccountjournaldup(true ,"Receipt");
                             JOptionPane.showMessageDialog(this, " This voucher is sent for approval. \n Voucher No. is allotted after approval. ", "Information", JOptionPane.INFORMATION_MESSAGE);
                   }
                   else{
                       JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                    updateaccountjournaldup(false ,"Receipt");
                    JOptionPane.showMessageDialog(this, " This voucher is sent for approval. \n Voucher No. is allotted after approval. ", "Information", JOptionPane.INFORMATION_MESSAGE);
               }
              
              
              
              if(vedit==false){
               clearpanel("Receipt");
               createNewEntry( num,"To");
              }
             }
        }
        
        
        
        
        
        
        else  if(jLabel4.getText().equals("Payments")){
             flag=false;
                int i=0;

                     for(JTextField toby: Tobylist){
                         String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                        if(toby.getText().equals("To")){
                           if((parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3"))){
                              flag=true;
                            //  break;
                          }
                           if(cashieracc.contains(pacclist.get(i).getid())){
                              flag1=true;
                           }
                        }else{
                            // String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                           System.out.println(parentname);
                           if((parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3"))){
                              flag=false;
                              break;
                           }
                          // if(cashieracc.contains(pacclist.get(i).getid())){
                          //    flag1=true;
                          // }
                        }
                      i++;
                     }

             if(flag==true && flag1==false){
                 
      //  edited by akash             
              Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Payment Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Payments");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){              
                            updateaccountjournal(true, "Payments");
                   }
                   else{
                        JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                  updateaccountjournal(false , "Payments"); 
               }
              
              
              if(vedit==false){
               clearpanel("Payments");
               createNewEntry( num,"By");
              }
             }else if(flag==false){
                 JOptionPane.showMessageDialog(null, " Please Credit a cash or bank account and do not debit cash or bank account",null,JOptionPane.OK_OPTION);
             }
             if(flag==true && flag1==true){
                 
       //  edited by akash             
              Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Payment Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Payments");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){              
                 
                                updateaccountjournaldup(true, "Payments");
                                JOptionPane.showMessageDialog(this, " This voucher is sent for approval. \n Voucher No. is allotted after approval. ", "Information", JOptionPane.INFORMATION_MESSAGE);
                   }
                    else{
                      JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                    updateaccountjournaldup(false, "Payments");
                    JOptionPane.showMessageDialog(this, " This voucher is sent for approval. \n Voucher No. is allotted after approval. ", "Information", JOptionPane.INFORMATION_MESSAGE);
               }
              
              
              if(vedit==false){
               clearpanel("Payments");
               createNewEntry( num,"By");
              }
             }

        }
        
        
        
        
        
        
        
        else  if(jLabel4.getText().equals("Journal")){
             flag1=false;
            int i=0;

                     for(JTextField toby: Tobylist){
                         String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                          if(parentname.equals("1.1.1")||parentname.equals("1.1.2")||parentname.equals("2.1.3")){
                              flag1=true;
                              break;
                          }
                          i++;
                     }
           if(flag1==false){
               
               
       //  edited by akash             
              Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Journal Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Journal");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){             
                         updateaccountjournal(true,"Journal");
                    }
                   else{
                       JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                    updateaccountjournal(false,"Journal");
               }
            
            
            
            if(vedit==false){
              clearpanel("Journal");
              createNewEntry( num,"By");
            }
           }else{
                JOptionPane.showMessageDialog(null, "Sorry Journal Entry should not include bank or cash account", "Warning", JOptionPane.WARNING_MESSAGE);
           }

        }
        
        
        
        
        
        
        else  if(jLabel4.getText().equals("Contra")){
              flag1=false;
              flag=false;
              int i=0;
              for(JTextField toby: Tobylist){
                   String parentname=dlfac.getparentaccountbysearchkey(pacclist.get(i).getSerachkey());
                   if(!parentname.equals("1.1.1")&& !parentname.equals("1.1.2") && !parentname.equals("2.1.3")){
                       flag=true;
                      // break;
                   }
                   if(cashieracc.contains(pacclist.get(i).getid())){
                        flag1=true;
                    }
                    i++;
               }
          if(flag1==false && flag==false){
              
        //  edited by akash             
              Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Contra Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Contra");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){        
              
                            updateaccountjournal(true , "Contra");
                   }
                   else{
                       JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                    updateaccountjournal(false , "Contra");
               }
            
            
            
          }else{
           if(flag==true)
               JOptionPane.showMessageDialog(null, "Sorry Contra Entry should include bank or cash accounts only", "Warning", JOptionPane.WARNING_MESSAGE);
           else{
            
             //  edited by akash             
              Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Contra Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj24!=null){
                   int MaxNoAllowed = Integer.parseInt(obj24[0].toString());
                   Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT IFNULL(COUNT(*),0) FROM voucherentry where TYPE=? and approved=0", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find("Contra");
                   int PendingEntries_No = Integer.parseInt(obj25[0].toString());
                   if(PendingEntries_No<MaxNoAllowed){     
                            updateaccountjournaldup(true , "Contra");
                            JOptionPane.showMessageDialog(this, " This voucher is sent for approval. \n Voucher No. is allotted after approval. ", "Information", JOptionPane.INFORMATION_MESSAGE);
                   }
                   else{
                       JOptionPane.showMessageDialog(this, "Entry without approval. \n Limit Reached.", "Error", JOptionPane.OK_OPTION);
                   }
               }
               else{
                    updateaccountjournaldup(false , "Contra");
                    JOptionPane.showMessageDialog(this, " This voucher is sent for approval. \n Voucher No. is allotted after approval. ", "Information", JOptionPane.INFORMATION_MESSAGE);
               }
           
           }
          }
          if(vedit==false){
               clearpanel("Contra");
               createNewEntry( num,"To");
          }
        }

      //  }
      //  catch(Exception e){
     //       e.printStackTrace();
     //   }
          }else{
              JOptionPane.showMessageDialog(null, "Total Debit is not equal to total credit ");
          }
      }

      private void save(){
         try{
      Date d=(Date)Formats.DATE.parseValue(jLabel8.getText());
      Calendar cal=Calendar.getInstance();
      cal.setTimeInMillis(d.getTime());
      Calendar fy_scal=Calendar.getInstance();
      Calendar fy_ecal=Calendar.getInstance();
      Map<String,GeneralSettingInfo> gsinfo=LookupUtilityImpl.getInstance(m_App).getGeneralSettingsMap();
      Date fy_sdate=(Date)Formats.DATE.parseValue((gsinfo.get("Datestart").getValue()));
      Date fy_edate=(Date)Formats.DATE.parseValue((gsinfo.get("Dateend").getValue()));
      fy_ecal.setTimeInMillis(fy_edate.getTime());
      fy_ecal.add(Calendar.DATE, 1);
      fy_scal.setTimeInMillis(fy_sdate.getTime());
     // boolean del=cal.before(fy_scal);
     // boolean del1=cal.before(fy_ecal);
      if(cal.before(fy_scal) || (!cal.before(fy_ecal))){
         JOptionPane.showMessageDialog(this, "Please check the date.The Specified Date does not come under the present financial year", "Sorry Cannot Save", JOptionPane.WARNING_MESSAGE);
      }else{
     Transaction t = new Transaction(m_App.getSession()) {
       public Object transact() throws BasicException {
     if(textboxlist.size()/2==Tobylist.size()){
       if(vedit==false){
        if(JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Do you want to save", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            Evaluate();
        }else{
           java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    textboxlist.get(0).requestFocus();
                }
            });
        }
       }else{
       // try{
            boolean flag=checkValueEdited();
              if(flag==true){
              Date d=(Date)Formats.TIMESTAMP.parseValue(jLabel8.getText());
              transno=dlfac.getnextTranscationNum(d, jLabel4.getText());
              Evaluate();
              dialog.setVisible(false);
              dialog.dispose();
             }
              else
              {
                  JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "No Changes to save!!", null,JOptionPane.OK_OPTION);
              }
          //  }catch(Exception e){
         //    e.printStackTrace();
         //   }
       }
        }else{
           JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please fill the form with appropriate values", null,JOptionPane.OK_OPTION);
        }
        return null;
       }
     };
     t.execute();
      }
    }catch(Exception e){
      e.printStackTrace();
       MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, "message.nosave", e);
              msg.show(this);
    }
      }
      public void buttonClicked(String btntext,Color color,int count,String text){
          count=0;
          clearpanel(btntext);
          jPanel1.setBackground(color);
          createNewEntry( count,text);
      }
      private boolean checkforValidity(Date d){
          try{
          Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
          GeneralSettingInfo sinfo=gs.get("Datestart");
          GeneralSettingInfo einfo=gs.get("Dateend");
          //String prevFySubIncome=gs.get("prevFySubIncome").getValue();
         // String nextFySubIncome=gs.get("nextFySubIncome").getValue();
          Date fsdate=(Date)Formats.DATE.parseValue(sinfo.getValue());
          Date fedate=(Date)Formats.DATE.parseValue(einfo.getValue());
          Calendar fscal=Calendar.getInstance();//financial year sdate
          fscal.setTime(fsdate);
          Calendar fecal=Calendar.getInstance();//financial year edate
          fecal.setTime(fedate);
          Calendar caltemp=Calendar.getInstance();
          caltemp.setTime(d);
          if(!caltemp.before(fscal) && !caltemp.after(fecal)){
             return true;
          }else
              return false;
          }catch(Exception e){
              return false;
          }
      }
      
      
      
      private void date(){
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jLabel8.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
             date.setTime(getDate(date).getTime());
            boolean status=checkforValidity(date);
            if(status==false){
                date=new Date();
                JOptionPane.showMessageDialog(this, "Please select a date within this financial year", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            transno=dlfac.getnextTranscationNum(date, jLabel4.getText());
            jLabel6.setText(transno);
            jLabel8.setText(Formats.TIMESTAMP.formatValue(date));
            revalidate();
        }
          }catch(Exception e1){
              e1.printStackTrace();
               MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, "message.nosave", e1);
              msg.show(this);
          }
      }
      
      
      
      
      
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        narration = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        credittotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        debittotal = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        scrollpaneforlist = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1409, 1468));

        narration.setColumns(20);
        narration.setRows(5);
        narration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                narrationKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(narration);

        jPanel3.setLayout(null);

        credittotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel3.add(credittotal);
        credittotal.setBounds(510, 0, 110, 30);

        jLabel10.setText("Total");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(0, 10, 90, 17);

        debittotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel3.add(debittotal);
        debittotal.setBounds(380, 0, 110, 27);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jPanel4.setLayout(null);

        jLabel5.setText("No :");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(10, 10, 40, 17);

        jLabel6.setText("jLabel6");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(60, 10, 60, 17);

        jLabel1.setText("Particulars");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(10, 50, 140, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(139, 18, 18));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Receipt");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(130, 20, 90, 20);

        jLabel7.setText("Date ");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(340, 10, 50, 17);

        jLabel8.setText("jLabel8");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(410, 10, 210, 17);

        jLabel2.setText("Debit");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(400, 50, 70, 17);

        jLabel3.setText("Credit");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(540, 50, 70, 17);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(127, 26, 26));
        jLabel11.setText("Voucher");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(230, 20, 80, 20);

        jLabel9.setText("Only Date & Narration can be  Edited");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(400, 30, 210, 20);

        jButton7.setText("Change Date");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setAutoscrolls(true);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jList1KeyReleased(evt);
            }
        });
        scrollpaneforlist.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addComponent(scrollpaneforlist, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(606, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollpaneforlist, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(928, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel1);

        jPanel2.setLayout(null);

        jButton1.setText("Receipt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(0, 0, 100, 30);

        jButton2.setText("Payments");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(0, 40, 100, 30);

        jButton3.setText("Journal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(0, 80, 100, 30);

        jButton4.setText("Contra");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(0, 120, 100, 30);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(700, 700, 700))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(687, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(473, 473, 473)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //num=0;
       buttonClicked(jButton1.getText(),LIGHT_GREEN, num, "To");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         //num=0;
         buttonClicked(jButton2.getText(), LIGHT_YELLOW, num, "By");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // num=0;
         buttonClicked(jButton3.getText(), VERY_LIGHT_BLUE, num, "By");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // num=0;
         buttonClicked(jButton4.getText(), Color.white, num, "To");
    }//GEN-LAST:event_jButton4ActionPerformed
     private Double ctotal=0.0,dtotal=0.0;
     
     Date VoucherEntryRestricted_From_DT;
     Date VoucherEntryRestricted_TO_DT;
     
     
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
       String Temp=jLabel8.getText();
        
       date();
       
       try{
           Date SelectedDate = (Date) Formats.TIMESTAMP.parseValue(jLabel8.getText());
           
           Object[] obj21 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Restricted From'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
           if(obj21!=null){
               VoucherEntryRestricted_From_DT = (Date) Formats.TIMESTAMP.parseValue(obj21[0].toString());
               Object[] obj22 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Restricted Upto'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj22!=null){
                   VoucherEntryRestricted_TO_DT = (Date) Formats.TIMESTAMP.parseValue(obj22[0].toString());
                   
                   if(SelectedDate.after(VoucherEntryRestricted_From_DT) && SelectedDate.before(VoucherEntryRestricted_TO_DT)){
                       
                   }
                   else{
                        JOptionPane.showMessageDialog(this, " Selected Date is out of restricted range .\n Please select date in range between "+obj21[0].toString() + " to  "+obj22[0].toString()+" . ", "Warning", JOptionPane.WARNING_MESSAGE);
                        jLabel8.setText(Temp);
                   }
                  
                   
                   
                   
               }
           }
       }
       catch(Exception e){
           
       }
       
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
 // EDITED BY AKASH FOR RESTRICTING VOUCHER ENTRIES FOR PERIOD       
     
           
       try{
           Date SelectedDate = (Date) Formats.TIMESTAMP.parseValue(jLabel8.getText());
           
           Object[] obj21 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Restricted From'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
           if(obj21!=null){
               VoucherEntryRestricted_From_DT = (Date) Formats.TIMESTAMP.parseValue(obj21[0].toString());
               Object[] obj22 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Restricted Upto'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
               if(obj22!=null){
                   VoucherEntryRestricted_TO_DT = (Date) Formats.TIMESTAMP.parseValue(obj22[0].toString());
                   
                   if(SelectedDate.after(VoucherEntryRestricted_From_DT) && SelectedDate.before(VoucherEntryRestricted_TO_DT)){
                       
                        // original save operation
                         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                save.setEnabled(false);
                                boolean status=true;
                                for(JTextField textfield:textboxlist){
                                   if(textfield.getBackground()!=Color.lightGray){
                                       status=false;
                                   }
                                }
                                if(status)
                                   save();
                                else
                                    JOptionPane.showMessageDialog(null, "Sorry Cannot save");


                                save.setEnabled(true);
                          //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
                       
                   }
                   else{
                        JOptionPane.showMessageDialog(this, " Selected Date is out of restricted range .\n Please select date in range between "+obj21[0].toString() + " to  "+obj22[0].toString()+" . ", "Warning", JOptionPane.WARNING_MESSAGE);
                        
                   }
                  
                   
                   
                   
               }
           }
           else{
               
                         // original save operation
                         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                save.setEnabled(false);
                                boolean status=true;
                                for(JTextField textfield:textboxlist){
                                   if(textfield.getBackground()!=Color.lightGray){
                                       status=false;
                                   }
                                }
                                if(status)
                                   save();
                                else
                                    JOptionPane.showMessageDialog(null, "Sorry Cannot save");


                                save.setEnabled(true);
                          //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
               
               
           }
       }
       catch(Exception ex){
            ex.printStackTrace();
            new MessageInf(ex).show(new JFrame());
            Logger.getLogger(VoucherEntry1.class.getName()).log(Level.SEVERE, null, ex); 
       }    
        
        
        
        
        
        
        
     
            
            
            
            
            
            
            
            
            
            
    }//GEN-LAST:event_saveActionPerformed

    private void jList1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             activetextbox.setBackground(Color.lightGray);
            //  particulars1.setText(jList1.getSelectedValue().toString());
            activetextbox.setText(jList1.getSelectedValue().toString());
            int index=textboxlist.indexOf(activetextbox);
            focusindex=index+1;
            if(index/2>pacclist.size()-1)
            pacclist.add(index/2,(AccountMasterExt)jList1.getSelectedValue() );
            else
                pacclist.set(index/2,(AccountMasterExt)jList1.getSelectedValue() );
            // flag=1;
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    textboxlist.get(focusindex).requestFocus();
                }
            });
            // acclist.add(num,(AccountMaster)jList1.getSelectedValue());
            jList1.setVisible(false);
            scrollpaneforlist.setVisible(false);
        }
}//GEN-LAST:event_jList1KeyReleased

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
          activetextbox.setText(jList1.getSelectedValue().toString());
        activetextbox.setBackground(Color.lightGray);
        // flag=1;
        int index=textboxlist.indexOf(activetextbox);
        focusindex=index+1;
         if(index/2>pacclist.size()-1)
            pacclist.add(index/2,(AccountMasterExt)jList1.getSelectedValue() );
            else
                pacclist.set(index/2,(AccountMasterExt)jList1.getSelectedValue() );
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                textboxlist.get(focusindex).requestFocus();
            }
        });
        jList1.setVisible(false);
        scrollpaneforlist.setVisible(false);
     /*   try{
        if(acclist.get)
        acclist.add(num,(AccountMaster)jList1.getSelectedValue());
        }
        catch(IndexOutOfBoundsException  e){

        }*/
}//GEN-LAST:event_jList1MouseClicked

    private void narrationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_narrationKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
           java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                   textboxlist.get(textboxlist.size()-1).requestFocus();
                }
            });
        }
    }//GEN-LAST:event_narrationKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField credittotal;
    private javax.swing.JTextField debittotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea narration;
    private javax.swing.JButton save;
    private javax.swing.JScrollPane scrollpaneforlist;
    // End of variables declaration//GEN-END:variables

    
    public void InsertintoVoucherEntryConfirmation(String Tid , String Type) throws BasicException{
         new PreparedSentence(m_App.getSession()
                     , "INSERT INTO voucherentry(ID,TID,type,approved) VALUES (?,?,?,?)"
                     , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.INT})).exec(new Object[]{ UUID.randomUUID().toString() , Tid , Type ,0});
    }
    
    
    
}
