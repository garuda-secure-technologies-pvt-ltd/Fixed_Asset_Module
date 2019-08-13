/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* SHIV CREATED
 *  ResetQtBillReceipt.java
 *
 * Created on sept 25, 2012, 1:15:01 PM
 * WORKS FOR BILL/QT/RECEIPT UPDATE RANGE FROM 
 * GREATER THAN 99 AND LESS THAN 99999 DIGITS
 * 
 */
package com.openbravo.pos.sales;
import com.openbravo.pos.mant.*;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.TicketInfo;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author SHIV
 */
public class ResetQtBillReceipt extends JPanel implements EditorRecord {

    private String m_sid;
    private List m_sentcat;
    private SentenceExec m_sentadd;
    private SentenceExec m_sentdel;
    private ComboBoxValModel rolesmodel;
    AppView m_App;
    private String portNumber;
    private CardReader cr;
    private ComboBoxValModel umodel;
     private DataLogicSales m_dlSales;
     private Double qtmax=0.0;
    private Double billmax=0.0;
    private Double receiptmax=0.0;
    
    private DataLogicSystem m_dlSystem;
    private String qt=null;
    private String bill=null;
    private String receipt=null;
    private String receipt1=null;
    private String valueqt;
         public String qtseries1;
         public String bseries1;
        public String  rseries1;
       

    /** Creates new form ResetQtBillReceip */
    public ResetQtBillReceipt(AppView app, DirtyManager dirty) {
    //    DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_App = app;
        initComponents();
          
          
          
      
       

       
    }
    
    
    
     public void init(AppView app) throws BeanFactoryException {
             m_App = app;
             m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
             m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
             jLabel1.setVisible(false);
             jLabel3.setVisible(false);
             jLabel4.setVisible(false);
             jLabel6.setVisible(false);
             jLabel7.setVisible(false);
             jLabel8.setVisible(false);
             qtseries.setVisible(false);
             billseries.setVisible(false);
            receiptseries.setVisible(false);
             lqtno.setVisible(false);
             lbillno.setVisible(false);
             lrno.setVisible(false);
             QT.setVisible(false);
             Bill.setVisible(false);
             Receipt.setVisible(false);
             jLabel2.setVisible(true);
                     
        try{
         umodel=new ComboBoxValModel();
                           List<RoleInfo> roleslist=m_dlSales.getRoleList().list();
           for(int i=0;i<roleslist.size();i++){
               RoleInfo rinfo=roleslist.get(i);
                //warehouse changes -start
                Object[] obj2=(Object[])   new StaticSentence(m_App.getSession()//changed the query because id was in ambiguous
                        , "SELECT P.ID,P.NAME,P.APPPASSWORD,P.CARD,P.ROLE,P.IMAGE,P.LOGINTIME,P.CLOSECASHTIME,P.OPENCASHTIME,P.CLOSESALE,P.OPENSALE,P.PRCATEGORIES FROM PEOPLE P,ROLES WHERE P.ROLE=ROLES.ID AND ROLES.NAME=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})).find(rinfo.getName());

             if(obj2!=null){
                 String warehouse = null;
                if (obj2[11] != null) {
                    String[] wArr = obj2[11].toString().split("#");
                    warehouse = wArr[0];
                }
             AppUser appuser1=new AppUser(obj2[0].toString(), obj2[1].toString(),obj2[4].toString(),warehouse);
              //warehouse changes -end
             appuser1.fillPermissions(m_dlSystem);
             boolean sflag=appuser1.hasPermission("sales");
             boolean pflag=appuser1.hasPermission("payment");
             if(sflag==true || pflag==true){
                 umodel.add(rinfo);
                
             }
             }
           }
        }catch(Exception e){
            e.printStackTrace();
        }
     }
     

    public void activate() throws BasicException {
       loadData();
       
  
    }
    
     private void loadData() throws BasicException {
         jComboBox2.setModel(umodel);
         jComboBox2.setSelectedIndex(-1);
         jLabel1.setVisible(false);
             jLabel3.setVisible(false);
             jLabel4.setVisible(false);
             jLabel6.setVisible(false);
             jLabel7.setVisible(false);
             jLabel8.setVisible(false);
             qtseries.setVisible(false);
             billseries.setVisible(false);
            receiptseries.setVisible(false);
             lqtno.setVisible(false);
             lbillno.setVisible(false);
             lrno.setVisible(false);
             QT.setVisible(false);
             Bill.setVisible(false);
             Receipt.setVisible(false);
             jLabel2.setVisible(true);
     }
    
     
     
       public String getNextBillID(String createdby) throws BasicException {
  ////       int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update Bill ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
  /////      if (res == JOptionPane.YES_OPTION) {
        String billnum;
        Object name=jComboBox2.getSelectedItem();
           try{
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
        
                   obj[0].toString();
                   System.out.println(obj[0].toString());
      //               Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RMAX  FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND  CATEGORY="+obj[0].toString()+"  AND ACTIVE=TRUE",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
    //   Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE AND  CATEGORY="+obj[0].toString()+"",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
       
         Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?  AND  ACTIVE=TRUE"  ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
       Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.BSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
  
        System.out.println(obj1[0]);
        System.out.println(obj2[0]);
        
          bcut=obj1[0].toString();
                     split2=bcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                          
                 
                   

 Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
             
        
        
        
        
       //  Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj2[0].toString()+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                 String bseries1=obj9.toString();
                  System.out.println(bseries1);
       
        
         if (obj1 != null && obj2 != null) { 
            Double max = Double.parseDouble(obj1[0].toString());
           
   ////////////////////new         
String mqcut1=obj9[0].toString();
                        String bring1=obj2[0].toString();
                        String[] split4=mqcut1.split(bring1);
                    
                       for (int i = 0; i < split4.length; i++) {
                       System.out.println(split4[i]);
                       }
                    
                         String sp4=split4[1].toString();
                    
                          Double dObj4 = Double.valueOf(sp4);
                      //    Double max1 = Double.parseDouble(obj1[0].toString());
                          if(dObj4>max){
                              
                             Double dObj5=dObj4-max;
                             
                             if(dObj5==2){
                               //  max++;
                             //  int res1 = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//     if (res1 == JOptionPane.YES_OPTION) {
                                         
                                      max++;
                                      new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY = ? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max,obj[0].toString(),obj[0].toString()});
                                      //   } else{
         
                                        //       }
                                       }
                                   
                                       }
                         
  ////////////////////////////new end   
                           max++;
            billnum = obj[0].toString() + max.intValue();
            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY = ? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max,obj[0].toString(),obj[0].toString()});
            
            String value=obj2[0].toString()+max.intValue();
            lbillno.setText(value);
           
             JOptionPane.showMessageDialog(null, "Updated Bill to: " + value);
             
             
                       /////////////////////////////////////////////////////////////////5thoct 
         Double bser=   (Double) max;
       
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj2[0].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=bser.toString();
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                Bill.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             Bill.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                  Bill.setEnabled(false);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else{
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
             
             
             
             
             
            return billnum;
           
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
           
        }
         
     
           }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
                  }
  
          return "";
          
        
    }
       
       
       
       
       
       
       
         private String getNextQTicketID(String createdby) throws BasicException {
        //shiv:sequencedetail:inserting id instead of names
 ////        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update QT ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
 /////       if (res == JOptionPane.YES_OPTION) {
             try{
             
             String qtnum;
         Object name=jComboBox2.getSelectedItem();
      
       
           Object[] obj = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
        
           //    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RMAX  FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND  CATEGORY="+obj[0].toString()+"  AND ACTIVE=TRUE",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
     //  Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE AND  CATEGORY="+obj[0].toString()+"",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
      //  SELECT SEQUENCEDETAIL.QTMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND  CATEGORY="+obj[0].toString()+"  AND ACTIVE=TRUE
       //    SELECT SEQUENCEDETAIL.QTMAX  FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?   AND  ACTIVE=TRUE  
       Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.QTMAX  FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?   AND  ACTIVE=TRUE  ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
       Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.QTSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
       Object[] obj5= (Object[]) new StaticSentence(m_App.getSession(),"SELECT CONCAT(QTSERIES,QTMAX) AS QTICKET FROM SEQUENCEDETAIL WHERE QTSERIES=?",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj2[0].toString());
        System.out.println(obj1[0]);
        System.out.println(obj2[0]);
        String qt1=obj2[0].toString()+obj1[0].toString();
       // "+(obj2[0].toString())+"
     //   (SELECT CONCAT(QTSERIES,QTMAX) AS QTICKET FROM SEQUENCEDETAIL WHERE QTSERIES="+(obj2[0].toString())+")
     ///////////////////////////////////////SHIV:CREATED///////////////////////////////////////////////////////////////////////////////////////////////////////////   
      Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT * FROM QTICKET Q,SEQUENCEDETAIL S WHERE  S.QTMAX="+(obj1[0].toString())+" AND Q.ID=?" ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj5.toString());        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     // Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM QTICKET Q,SEQUENCEDETAIL S WHERE Q.ID = ? AND S.QTMAX=?",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());        
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
            qcut=obj1[0].toString();
                     split2=qcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                          
                 
                   

 Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM qticket where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                                                      
     //   String S = obj2[0].toString();  
      // Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM qticket where id like '"+obj2[0].toString()+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                 String qtseries1=obj9.toString();
                  System.out.println(qtseries1);
              /////////////////////////////////////////////////////////////////////////////////////
                 String bmaga= obj9[0].toString();
              
                    String split4[]=bmaga.split("(?<=\\G..)");
                    
                    for (int i = 0; i < split4.length; i++) {
                       System.out.println(split4[i]);
                    }
                  
                  
                  
                  
              ////////////////////////////////////////////////////////////////////////////////////
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    if(obj1 != null && obj2 != null) {
             Double max = Double.parseDouble(obj1[0].toString());
             
             ////////////////////new         
String mbcut1=obj9[0].toString();
                        String bring1=obj2[0].toString();
                        String[] split5=mbcut1.split(bring1);
                    
                       for (int i = 0; i < split5.length; i++) {
                       System.out.println(split5[i]);
                       }
                    
                         String sp4=split5[1].toString();
                    
                          Double dObj4 = Double.valueOf(sp4);
                      //    Double max1 = Double.parseDouble(obj1[0].toString());
                          if(dObj4>max){
                              
                             Double dObj5=dObj4-max;
                             
                             if(dObj5==2){
                               //  max++;
                //                int res1 = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update QT Again by 1 ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   //  if (res1 == JOptionPane.YES_OPTION) {
                                         
                                      max++;
                                     new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET QTMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY= ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING,Datas.STRING})).exec(new Object[]{max,obj[0].toString(),obj[0].toString()});
           
                            ///             } else{
         
                            //                   }
                                       }
                                   
                                       }
                         
  ////////////////////////////new end
             
             
           max++;
           qtnum = obj[0].toString() + max.intValue();
           new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET QTMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY= ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING,Datas.STRING})).exec(new Object[]{max,obj[0].toString(),obj[0].toString()});
           
            String value=obj2[0].toString()+max.intValue();
             lqtno.setText(value);
             JOptionPane.showMessageDialog(null, "Updated QT to: " + value);
             
             
                                                 /////////////////////////////////////////////////////////////////5thoct 
         Double qser= (Double) max;
      
         ////this is for seqdet
       
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj2[0].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=qser.toString();
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                QT.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             QT.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                    QT.setEnabled(false);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else {
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
             
             
           return qtnum;
           
        } else {
           JOptionPane.showMessageDialog(null, "Please Specify the QT Series", "Cannot Create QT", JOptionPane.OK_OPTION);
            
        }
                    
            
             }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "Please Specify the QT Series", "Cannot Create QT", JOptionPane.OK_OPTION);
                  }
              
             
             
             
  ////      }///end of optionpane
  ////      else{
   ////           QT.setEnabled(true);
    ////         }
        return "";
             
             
    }
         
         
         
          public final String getNextReceiptID(String createdby) throws BasicException {
   ////            int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update Receipt ?"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
  ////             if (res == JOptionPane.YES_OPTION) {
              
              try{

        String receiptnum;
        
         Object name=jComboBox2.getSelectedItem();
           Object[] obj = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
        
                   obj[0].toString();
                   System.out.println(obj[0].toString());
                   
        
  Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT QTSERIES,QTMAX,BSERIES,BMAX,RSERIES,RMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE AND CATEGORY=? "
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).find(new Object[]{((RoleInfo)jComboBox2.getSelectedItem()).getID(),((RoleInfo)jComboBox2.getSelectedItem()).getID()});
   //     Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RMAX  FROM SEQUENCEDETAIL WHERE  USERNAME="+obj[0].toString()+" AND ACTIVE=1",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
    //    Object[] cut[]=(Object[][]) (Object[]) new StaticSentence(m_App.getSession(), "",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      // Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=1 AND  CATEGORY="+obj[0].toString()+"",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
        rcut=obj1[5].toString();
                     split2=rcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
       
       Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM receipts where id like '"+obj1[4].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                 String rseries1=obj9.toString();
                  System.out.println(rseries1);
       
       
       
       

        if (obj1 != null) {
            Double max = Double.parseDouble(obj1[5].toString());
                   ////////////////////new         
String mbcut1=obj9[0].toString();
                        String bring1=obj1[4].toString();
                        String[] split5=mbcut1.split(bring1);
                    
                       for (int i = 0; i < split5.length; i++) {
                       System.out.println(split5[i]);
                       }
                    
                         String sp4=split5[1].toString();
                    
                          Double dObj4 = Double.valueOf(sp4);
                      //    Double max1 = Double.parseDouble(obj1[0].toString());
                          if(dObj4>max){
                              
                             Double dObj5=dObj4-max;
                             
                             if(dObj5==2){
                               //  max++;
                 //               int res1 = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    // if (res1 == JOptionPane.YES_OPTION) {
                                         
                                      max++;
                                       new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ? AND CATEGORY=?",  new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING,Datas.STRING})).exec(new Object[]{max,obj[0].toString(),obj[0].toString()});
        
                                    //     } else{
         
                                  //             }
                                       }
                                   
                                       }
                         
  ////////////////////////////new end
            
            
            
            
            
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = (SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ID=? AND ROLES.ID=PEOPLE.ROLE)  AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ? AND CATEGORY=?",  new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING,Datas.STRING})).exec(new Object[]{max,obj[0].toString(),obj[0].toString()});
        
           String value=obj1[4].toString()+max.intValue();
              lrno.setText(value);
             JOptionPane.showMessageDialog(null, "Updated Receipt to: " + value);
                                          /////////////////////////////////////////////////////////////////5thoct 
         Double rser=   (Double) max;
         obj1[5].toString();
         ////this is for seqdet
      //   qtseries1.concat(split2[0].toString());
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj1[4].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=rser.toString();
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                Receipt.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             Receipt.setEnabled(true);
                            System.out.println("dif==2");
                            
                        }      
                        else if(dObj>2){
                                    Receipt.setEnabled(false);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              Receipt.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else {
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Receipt.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
            return receiptnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
            
            
        }
        
         
        
              }   catch(Exception e){
                JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
                                    }
     ////          } else{
    /////               Receipt.setEnabled(true);
     ////          }
              return null;
              
    }
    
    

    public void writeValueEOF() {

    }
    //praveen:cardreader function

   

    public void writeValueInsert() {
      
    }

    public void writeValueDelete(Object value) {
       
    }

    public void writeValueEdit(Object value) {

       
    }

    public Object createValue() throws BasicException {
      
        return null;
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

        Receipt = new javax.swing.JButton();
        Bill = new javax.swing.JButton();
        QT = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        qtseries = new javax.swing.JTextField();
        billseries = new javax.swing.JTextField();
        receiptseries = new javax.swing.JTextField();
        lqtno = new javax.swing.JTextField();
        lbillno = new javax.swing.JTextField();
        lrno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        Receipt.setText("UpdateReceipt");
        Receipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReceiptActionPerformed(evt);
            }
        });

        Bill.setText("UpdateBill");
        Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillActionPerformed(evt);
            }
        });

        QT.setText("UpdateQT");
        QT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTActionPerformed(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Counter :");

        qtseries.setEditable(false);

        billseries.setEditable(false);

        receiptseries.setEditable(false);

        lqtno.setEditable(false);

        lbillno.setEditable(false);

        lrno.setEditable(false);

        jLabel1.setText("QT as per QT table :");

        jLabel3.setText("Bill as per Bill table:");

        jLabel4.setText("Receipt as per Receipt table :");

        jLabel6.setText("QT as per Sequence Detail  :");

        jLabel7.setText(" Bill as per Sequence Detail : ");

        jLabel8.setText(" Receipt as per Sequence Detail :");

        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Note: Be careful while using this menu. Only use when you are sure that Bill/QT/Receipt creation is not happening  due to 'Duplicate Entry' ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(receiptseries, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(billseries, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(qtseries))))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbillno, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lqtno, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lrno, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Receipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Bill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(QT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(117, 117, 117))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qtseries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lqtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billseries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbillno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bill))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(receiptseries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lrno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Receipt))
                .addGap(63, 63, 63)
                .addComponent(jLabel5)
                .addGap(86, 86, 86))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiptActionPerformed
          //    Receipt.setText("Receipt");   
              String createdby=null;
        try {
               Object name=jComboBox2.getSelectedItem();
          Object[] obj = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
          Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT QTSERIES,QTMAX,BSERIES,BMAX,RSERIES,RMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE AND CATEGORY=? "
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).find(new Object[]{((RoleInfo)jComboBox2.getSelectedItem()).getID(),((RoleInfo)jComboBox2.getSelectedItem()).getID()});
                    
        
              rcut=obj1[5].toString();
                     split2=rcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                         
        Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM receipts where id like '"+obj1[4].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
        if (obj1 != null) {
            Double max = Double.parseDouble(obj1[5].toString());
                                        /////////////////////////////////////////////////////////////////5thoct 
         Double rser=   (Double) max;
         obj1[5].toString();
         ////this is for seqdet
      //   qtseries1.concat(split2[0].toString());
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj1[4].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=rser.toString();
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                  int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update Receipt ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            getNextReceiptID(createdby);
          //  Receipt.setEnabled(false);
        }else{
            Receipt.setEnabled(true);
        }
                                 }
                              
                              
                              else if(dObj==2){
                              int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            getNextReceiptID(createdby);
          //  Receipt.setEnabled(false);
        }else{
            Receipt.setEnabled(true);
        }
                            System.out.println("dif==2");
                            
                        }      
                        else if(dObj>2){
                                   Receipt.setEnabled(true);
                                  System.out.println("dif>2"); 
                                JOptionPane.showMessageDialog(this, "Difference is greater than TWO.\n"
                                          + "\t Cannot Update " 
                                          + "\n\t Contact Administrator"
                                          , "Error Message", JOptionPane.OK_OPTION); 
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              Receipt.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else{
                               Receipt.setEnabled(true);
                                  System.out.println("dif>2"); 
                                 JOptionPane.showMessageDialog(this, "Difference is greater than TWO.\n"
                                          + "\t Cannot Update" 
                                          + "\n\t Contact Administrator"
                                          , "Error Message", JOptionPane.OK_OPTION); 
                               }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
        }
         
                 
            
          
            
            
            
        } catch (BasicException ex) {
            Logger.getLogger(ResetQtBillReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_ReceiptActionPerformed

private void BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillActionPerformed
// TODO add your handling code here:
    //  Bill.setText("Bill");
      String createdby=null;
        try {
       Object name=jComboBox2.getSelectedItem();
       Object[] obj = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
       Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?  AND  ACTIVE=TRUE"  ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
       Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.BSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
             bcut=obj1[0].toString();
                     split2=bcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                          
                 
                   
       Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
              if (obj1 != null && obj2 != null) {
                 Double max = Double.parseDouble(obj1[0].toString());
                  /////////////////////////////////////////////////////////////////5thoct 
         Double bser=   (Double) max;
       
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj2[0].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=bser.toString();
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                           Double dObj=null;
                          dObj2.compareTo(dObj3);
                          if(dObj2>dObj3){
                            dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              if(dObj==2){
  int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update?"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            
            getNextBillID(createdby);
          ///  Bill.setEnabled(false);
        }else{
             Bill.setEnabled(true);
             }
                                         }  
                              
                             
                        if(dObj>2){
                                  Bill.setEnabled(true);
                                  System.out.println("dif>2"); 
                              JOptionPane.showMessageDialog(this, "Difference is greater than TWO.\n"
                                          + "\t Cannot Update" 
                                          + "\n\t Contact Administrator"
                                          , "Error Message", JOptionPane.OK_OPTION); 
                                  }
                        
                         if(dObj==1){
                              
                            int res1 = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update Bill ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res1 == JOptionPane.YES_OPTION) {
            
            getNextBillID(createdby);
                              
                              }
               else{
             Bill.setEnabled(true);
                              }
        
        
        
                         }
                              
                          }  else if(dObj2<dObj3){
                               dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(true);
                                  System.out.println("dif<2"); 
                                 JOptionPane.showMessageDialog(this, "Contact Admin.Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          
                              
                      
        ///////////////////////////////////////////////////////////////5thoctend 
            
        
              }
                 
       
        } catch (BasicException ex) {
            Logger.getLogger(ResetQtBillReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}//GEN-LAST:event_BillActionPerformed

private void QTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTActionPerformed
// TODO add your handling code here:
    // QT.setText("QT");
     String createdby = null;
          try {
       Object name=jComboBox2.getSelectedItem();
       Object[] obj = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
       Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.QTMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?  AND  ACTIVE=TRUE"  ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
       Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.QTSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj[0].toString());
             qcut=obj1[0].toString();
                     split2=qcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                          
                 
                   
       Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM QTICKET where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
              if (obj1 != null && obj2 != null) {
                 Double max = Double.parseDouble(obj1[0].toString());
                  /////////////////////////////////////////////////////////////////5thoct 
         Double bser=   (Double) max;
       
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj2[0].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=bser.toString();
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                           Double dObj=null;
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                            dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              if(dObj==2){
  int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            
                getNextQTicketID(createdby);
          ///  Bill.setEnabled(false);
        }else{
            QT.setEnabled(true);
                              }
                             
                                 }
                               if(dObj==1){
    int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update QT ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            
                getNextQTicketID(createdby);
          ///  Bill.setEnabled(false);
        }else{
            QT.setEnabled(true);
                              }
                               }
        
        
           
                        if(dObj>2){
                                 QT.setEnabled(true);
                                  System.out.println("dif>2"); 
                                  JOptionPane.showMessageDialog(this, "Difference is greater than TWO.\n"
                                          + "\t Cannot Update" 
                                          + "\n\t Contact Administrator"
                                          , "Error Message", JOptionPane.OK_OPTION); 
                                  }
                              
                            
                              
                              
                              
                      
                          
                      
        ///////////////////////////////////////////////////////////////5thoctend 
            
        
     
                   }else if(dObj2<dObj3){
                               dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(true);
                                  System.out.println("dif<2"); 
                                 JOptionPane.showMessageDialog(this, "Contact Admin.Difference is -1. CANNOT UPDATE", "Error Message", JOptionPane.OK_OPTION);
                          }
              }
          } catch (BasicException ex) {
            Logger.getLogger(ResetQtBillReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_QTActionPerformed


 private int temp=0;
    private int sales=0;
    private int payment=0;
   private String rcut=null;
    private String bcut=null;
       private String qcut=null;
        
    public String split2[];

private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        // TODO add your handling code here:
    
     qtmax=0.0;
     billmax=0.0;
     receiptmax=0.0;
     if(jComboBox2.getSelectedIndex()!=-1 ){
        
         try{
             
    
            String role=jComboBox2.getSelectedItem().toString();
            Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT QTSERIES,QTMAX,BSERIES,BMAX,RSERIES,RMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE AND CATEGORY=? "
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).find(new Object[]{((RoleInfo)jComboBox2.getSelectedItem()).getID(),((RoleInfo)jComboBox2.getSelectedItem()).getID()});
       if(obj!=null){
           ///////////shiv:For QT
           if(obj[0]!=null){
               Object name=jComboBox2.getSelectedItem();
               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
               obj3[0].toString();
               System.out.println(obj3[0].toString());
                qcut=obj[1].toString();
                     split2=qcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                    //     if(odj)
                          
               
       Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM qticket where id like '"+obj[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
         qtseries1=obj[0].toString();
       //////////
         
       if(obj9[0]!=null){
      
      
       
                qtseries1=obj9[0].toString();
                System.out.println(qtseries1);
               qtseries.setText(qtseries1);
               qt=obj[0].toString();
               ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(sales==1 || payment==0){
      
          String s1=obj[0].toString();
             
                    String s2=obj[1].toString();
                    String split1[]=s2.split("\\.");
                    
                    for (int i = 0; i < split1.length; i++) {
                       System.out.println(split1[i]);
                         }
                    String s5=s1+split1[0];
                    
                    String s3=s1+s2;
                    String s4=obj9[0].toString(); 
                    if(s5.equals(s4)){
                     
                  QT.setEnabled(false);
                //  Bill.setEnabled(false);
                    }else{
                          // QT.setEnabled(true);
                      //     Bill.setEnabled(true);
                            /////////////////////////////////////////////////////////////////5thoct 
         Double qser=   (Double) obj[1];
         obj[1].toString();
         ////this is for seqdet
         qtseries1.concat(split2[0].toString());
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj[0].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=qcut;
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                QT.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             QT.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                    QT.setEnabled(true);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else if( dObj2==dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                        }
                 
                         }
 
  if(sales==1 && payment==1){
                  
                  
                  
                 String s1=obj[0].toString();
             
                    String s2=obj[1].toString();
                    String split1[]=s2.split("\\.");
                    
                    for (int i = 0; i < split1.length; i++) {
                       System.out.println(split1[i]);
                         }
                    String s5=s1+split1[0];
                    
                    String s3=s1+s2;
                    String s4=obj9[0].toString(); 
                    if(s5.equals(s4)){
                    QT.setEnabled(false);
           //    //   Bill.setEnabled(false);
          //    //    Receipt.setEnabled(false); 
                
                    }else{
                  //    QT.setEnabled(false);
                  ///    Bill.setEnabled(false);
                   //   Receipt.setEnabled(false);
                                         /////////////////////////////////////////////////////////////////5thoct 
         Double qser=   (Double) obj[1];
         obj[1].toString();
         ////this is for seqdet
         qtseries1.concat(split2[0].toString());
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj[0].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=qcut;
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                QT.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             QT.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                    QT.setEnabled(false);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else if( dObj2==dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               QT.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                      
                      
                        }
                  
                  
                  
                  
      }
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
               
               
             if(obj[1]!=null)
                   qtmax=Double.parseDouble(obj[1].toString());
       } else{
     qtseries.setText("No QT");
     QT.setEnabled(false);
             }
          
                     }else qtseries.setText("");
                         qtmax=Double.parseDouble(obj[1].toString());
                         lqtno.setText(obj[0].toString()+qtmax.intValue());
                
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
                    
           if(obj[2]!=null){
                     
                 ///////////shiv:For Bill
               
               Object name=jComboBox2.getSelectedItem();

               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
        
                   obj3[0].toString();
                   System.out.println(obj3[0].toString());
                   
                    bcut=obj[3].toString();
                     split2=bcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                          
                 
                   

 Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj[2].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
             
              // bseries1=obj[2].toString();
 
               if(obj9[0]!=null){
 
                  bseries1=obj9[0].toString();
                  System.out.println(bseries1);
                 //   double bsd = Double.valueOf(obj9[0].toString()).doubleValue();
                 bseries1.split(obj9[0].toString(),3);
               billseries.setText(bseries1);
               bill=obj[2].toString();
               
                   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(sales==1 || payment==0){
      
          String s1=obj[2].toString();
             
                    String s2=obj[3].toString();
                    String split1[]=s2.split("\\.");
                    
                    for (int i = 0; i < split1.length; i++) {
                       System.out.println(split1[i]);
                         }
                    String s5=s1+split1[0];
                    
                    String s3=s1+s2;
                    String s4=obj9[0].toString(); 
                    if(s5.equals(s4)){
                     
                 // QT.setEnabled(false);
                  Bill.setEnabled(false);
                    }else{
                        
                      //     Bill.setEnabled(true);
                            /////////////////////////////////////////////////////////////////5thoct 
         Double qser=   (Double) obj[3];
         obj[3].toString();
         
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj[2].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=bcut;
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                Bill.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             Bill.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                  Bill.setEnabled(true);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else if( dObj2==dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                        
                        
                        
                        
                        
                        }
                 
                         }
 
  if(sales==1 && payment==1){
                  
                  
                  
                 String s1=obj[2].toString();
             
                    String s2=obj[3].toString();
                    String split1[]=s2.split("\\.");
                    
                    for (int i = 0; i < split1.length; i++) {
                       System.out.println(split1[i]);
                         }
                    String s5=s1+split1[0];
                    
                    String s3=s1+s2;
                    String s4=obj9[0].toString(); 
                    if(s5.equals(s4)){
                //    QT.setEnabled(false);
                  Bill.setEnabled(false);
               //   Receipt.setEnabled(false); 
                                                 
                    }else{
                   //   QT.setEnabled(false);
                   ////   Bill.setEnabled(false);
                  //    Receipt.setEnabled(true);
                           /////////////////////////////////////////////////////////////////5thoct 
         Double qser=   (Double) obj[3];
         obj[3].toString();
         
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj[2].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=bcut;
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                Bill.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             Bill.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                  Bill.setEnabled(false);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else if( dObj2==dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Bill.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                        
                      
                      
                      
                      
                      
                        }
                  
                  
                  
                  
      }
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
            
               if(obj[3]!=null)
                   billmax=Double.parseDouble(obj[3].toString());
               } else{
                 billseries.setText("No Bill");
                 Bill.setEnabled(false);
                     }
               
           }else billseries.setText("");
                     billmax=Double.parseDouble(obj[3].toString());
                         String sw=  obj[3].toString();
                   lbillno.setText(obj[2].toString()+billmax.intValue());
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           if(obj[4]!=null){
               
                                            ///////////shiv:For Receipt
               
               Object name=jComboBox2.getSelectedItem();

  Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM ROLES WHERE NAME=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());
        
                   obj3[0].toString();
                   
                    rcut=obj[5].toString();
                     split2=rcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                         Object sp=split2[0].toString();
                          
                 
                   System.out.println(obj3[0].toString());
           //     Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RMAX  FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE AND CATEGORY="+jComboBox2.getSelectedItem()+"",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING})).find(obj3[0].toString());
// Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE AND CATEGORY="+obj3[0].toString()+"",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj3[0].toString());
 Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM receipts where id like '"+obj[4].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
              receipt1=obj[4].toString();
              
   //    Object[] cut[]=(Object[][]) (Object[]) new StaticSentence(m_App.getSession(), "",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();                
 if(obj9[0]!=null){
     
          
 rseries1=obj9[0].toString();
                    //  rseries1.split(obj9[0].toString(),3);
                 //  double rs = Double.valueOf(obj9[0].toString()).doubleValue();
                  System.out.println(rseries1);

               receiptseries.setText(rseries1);
               receipt=obj[2].toString();
                 receipt1=obj[4].toString();
               
              
              
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
  if(sales==0 || payment==1){
             
               
                 String s1=obj[4].toString();
             
                    String s2=obj[5].toString();
                    String split1[]=s2.split("\\.");
                    
                    for (int i = 0; i < split1.length; i++) {
                       System.out.println(split1[i]);
                         }
                    String s5=obj[4].toString()+split1[0];
                    
                    String s3=s1+s2;
                    String s4=obj9[0].toString(); 
                    if(s5.equals(s4)){
                    Receipt.setEnabled(false);   
                
                    }else{
                         // Receipt.setEnabled(true);
                                            // QT.setEnabled(true);
                      //     Bill.setEnabled(true);
                            /////////////////////////////////////////////////////////////////5thoct 
         Double qser=   (Double) obj[5];
         obj[5].toString();
         ////this is for seqdet
      //   qtseries1.concat(split2[0].toString());
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj[4].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=rcut;
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                Receipt.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             Receipt.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                    Receipt.setEnabled(true);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                              }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              Receipt.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else if( dObj2==dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Receipt.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                        
                        
                        
                        }
                 
               
               
                }
  if(sales==1 && payment==1){
                  
                  
                  
                 String s1=obj[4].toString();
             
                    String s2=obj[5].toString();
                    String split1[]=s2.split("\\.");
                    
                    for (int i = 0; i < split1.length; i++) {
                       System.out.println(split1[i]);
                         }
                    String s5=s1+split1[0];
                    
                    String s3=s1+s2;
                    String s4=obj9[0].toString(); 
                    if(s5.equals(s4)){
               //     QT.setEnabled(false);
              //   Bill.setEnabled(false);
                  Receipt.setEnabled(false); 
                
                    }else{
                     // QT.setEnabled(false);
                   //   Bill.setEnabled(false);
                    //  Receipt.setEnabled(true);
                                         /////////////////////////////////////////////////////////////////5thoct 
         Double qser=   (Double) obj[5];
         obj[5].toString();
         ////this is for seqdet
      //   qtseries1.concat(split2[0].toString());
         /////////end
         
         obj9[0].toString();
        String qcut1=obj9[0].toString();
                        String bring=obj[4].toString();
                        String[] split3=qcut1.split(bring);
                    
                       for (int i = 0; i < split3.length; i++) {
                       System.out.println(split3[i]);
                       }
                         String sp1=split3[1].toString();
                         String sp3=rcut;
                         Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                             Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              
                              if(dObj==1){
                                Receipt.setEnabled(true);  
                                 }
                              
                              
                              else if(dObj==2){
                             Receipt.setEnabled(true);
                            System.out.println("dif==2");
                        }      
                        else if(dObj>2){
                                    Receipt.setEnabled(true);
                                  System.out.println("dif>2"); 
                               //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                  
                                       }
                              
                            
                              
                              
                              
                          }else if(dObj2<dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              Receipt.setEnabled(false);
                                  System.out.println("dif>2"); 
                           //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          else if( dObj2==dObj3){
                               Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                               Receipt.setEnabled(false);
                          }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                      
                        }
                  
                  
                  
                  
      }
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
              
              
            
               
               if(obj[5]!=null)
                   receiptmax=Double.parseDouble(obj[5].toString());
      } else{
     receiptseries.setText("No Receipts");
                 Receipt.setEnabled(false);
            } 
           }else receiptseries.setText("");
        //  jButton2.setVisible(false);
           receiptmax=Double.parseDouble(obj[5].toString());
           
          lrno.setText(receipt1+receiptmax.intValue());
       // lbillno.setText(bill+billmax.intValue());
        //  lqtno.setText(qt+qtmax.intValue());
         
          qtseries.setEditable(false);
          billseries.setEditable(false);
          receiptseries.setEditable(false);
          
          
          
          
        
       }else{
         
           qtseries.setVisible(false);
           billseries.setEditable(true);
           receiptseries.setVisible(false);
           QT.setVisible(false);
           Bill.setVisible(false);
           Receipt.setVisible(false);
           lrno.setVisible(false);
           lbillno.setVisible(false);
           lqtno.setVisible(false);
           jLabel6.setVisible(false);
           jLabel7.setVisible(false);
           jLabel8.setVisible(false);
           jLabel4.setVisible(false);
       }
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             ///if condition for accountant coordinator where he is not assisgned the role
             
         if(obj!=null){    
             
             
          payment=0;
           sales=0;
           temp=0;
          Object name=jComboBox2.getSelectedItem();
           //warehouse changes -start
          Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()//shiv:CHANGED BELOW QUERY
                       // , "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE FROM PEOPLE,ROLES WHERE PEOPLE.ROLE=ROLES.ID AND ROLES.NAME=?"
                       ,"SELECT P.ID,P.NAME,P.APPPASSWORD,P.CARD,P.ROLE,P.IMAGE,P.LOGINTIME,P.CLOSECASHTIME,P.OPENCASHTIME,P.CLOSESALE,P.OPENSALE,P.PRCATEGORIES FROM PEOPLE P,ROLES R WHERE P.ROLE=R.ID AND R.NAME=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})).find(jComboBox2.getSelectedItem().toString());
           
           
            
           
       
        
            
                       
       
          
          
             if(obj1!=null){
                 String warehouse = null;
                if (obj1[11] != null) {
                    String[] wArr = obj1[11].toString().split("#");
                    warehouse = wArr[0];
                }
             AppUser appuser=new AppUser(obj1[0].toString(), obj1[1].toString(),obj1[4].toString(),warehouse);
              //warehouse changes -end
             appuser.fillPermissions(m_dlSystem);
             boolean pflag= appuser.hasPermission("payment");
             boolean qflag=appuser.hasPermission("sales");
              String del=jComboBox2.getSelectedItem().toString();
               if(pflag==true && qflag==true){
                     payment=1;
        
           java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                receiptseries.requestFocus();
            }
           });
           sales=1;
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                qtseries.requestFocus();
            }
                });
                   
                   
                       }
               
               
               
                else
                  if(pflag==false && qflag==false){
                      
                       payment=0;
        
           java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                receiptseries.requestFocus();
                 }
                  });
                      
                            qtseries.setVisible(false);
                            jLabel1.setVisible(false);
                            jLabel6.setVisible(false);
                            lqtno.setVisible(false);
                            QT.setVisible(false);
                            jLabel3.setVisible(false);
                            billseries.setVisible(false);
                            jLabel7.setVisible(false);
                            lbillno.setVisible(false);
                            Bill.setVisible(false);
                            jLabel4.setVisible(false);
                            receiptseries.setVisible(false);
                            jLabel8.setVisible(false);
                            lrno.setVisible(false);
                            Receipt.setVisible(false);
                            
                            
                            
                            
                         }
               
               
               else
                  if(pflag==true || qflag==false){
                      
                       payment=1;
        
           java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                receiptseries.requestFocus();
                 }
                  });
                      
                            qtseries.setVisible(false);
                            jLabel1.setVisible(false);
                            jLabel6.setVisible(false);
                            lqtno.setVisible(false);
                            QT.setVisible(false);
                            jLabel3.setVisible(false);
                            billseries.setVisible(false);
                            jLabel7.setVisible(false);
                            lbillno.setVisible(false);
                            Bill.setVisible(false);
                            jLabel4.setVisible(false);
                            receiptseries.setVisible(false);
                            jLabel8.setVisible(false);
                            lrno.setVisible(false);
                            Receipt.setVisible(false);
                            
                            
                            
                            
                         }else
                             if(qflag==true || pflag==false)
                                 {
                                     
                             java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                     qtseries.requestFocus();
                       }
                         });        
                                    sales=1;
                                    jLabel4.setVisible(false);
                                    receiptseries.setVisible(false);
                                    jLabel8.setVisible(false);
                                    lrno.setVisible(false);
                                    Receipt.setVisible(false);
                                    
                                     }
               
               
 
               
               
             
              }else{
              JOptionPane.showMessageDialog(this, "User doesnot exist for this role.Create a user first", "cannot", JOptionPane.OK_OPTION);
             }
              
              
                if(payment == 1 && sales==1){
          jLabel4.setVisible(true);
            jLabel3.setVisible(true);
          receiptseries.setVisible(true);
          Receipt.setVisible(true);
           lrno.setVisible(true);
           jLabel8.setVisible(true);
           jLabel1.setVisible(true);
              jLabel2.setVisible(true);
              qtseries.setVisible(true);
              billseries.setVisible(true);
              QT.setVisible(true);
              Bill.setVisible(true);
             
              lqtno.setVisible(true);
         lbillno.setVisible(true);
         jLabel6.setVisible(true);
            jLabel7.setVisible(true);

      }else
      if(payment==0 && sales == 0){
         jLabel4.setVisible(true);
            jLabel3.setVisible(false);
          //  billseries.setVisible(true);
         // receiptseries.setVisible(true);
          receiptseries.setVisible(false);
          Receipt.setVisible(false);
           jLabel1.setVisible(false);
           jLabel2.setVisible(false);
            qtseries.setVisible(false);
            billseries.setVisible(false);
            QT.setVisible(false);
            Bill.setVisible(false);
            lrno.setVisible(true);
            lqtno.setVisible(false);
            lbillno.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(true);
      }
                
                
                
                
                else
      if(payment==1 || sales == 0){
         jLabel4.setVisible(true);
            jLabel3.setVisible(false);
          //  billseries.setVisible(true);
         // receiptseries.setVisible(true);
          receiptseries.setVisible(true);
          Receipt.setVisible(true);
           jLabel1.setVisible(false);
           jLabel2.setVisible(true);
            qtseries.setVisible(false);
            billseries.setVisible(false);
            QT.setVisible(false);
            Bill.setVisible(false);
            lrno.setVisible(true);
            lqtno.setVisible(false);
            lbillno.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(true);
      }else
    if(sales==1 || payment==0){
             jLabel1.setVisible(true);
              jLabel2.setVisible(true);
              qtseries.setVisible(true);
              billseries.setVisible(true);
              
              
              
              QT.setVisible(true);
              
              
            
              Bill.setVisible(true);
               jLabel4.setVisible(false);
            jLabel3.setVisible(true);
            
           receiptseries.setVisible(false);
          Receipt.setVisible(false);
         lrno.setVisible(false);
         lqtno.setVisible(true);
         lbillno.setVisible(true);
         jLabel6.setVisible(true);
            jLabel7.setVisible(true);

            jLabel8.setVisible(false);
    }
             
             
             
             
             
             
         }  else{
             jLabel1.setVisible(false);
             jLabel3.setVisible(false);
             jLabel4.setVisible(false);
             jLabel6.setVisible(false);
             jLabel7.setVisible(false);
             jLabel8.setVisible(false);
             qtseries.setVisible(false);
             billseries.setVisible(false);
            receiptseries.setVisible(false);
             lqtno.setVisible(false);
             lbillno.setVisible(false);
             lrno.setVisible(false);
             QT.setVisible(false);
             Bill.setVisible(false);
             Receipt.setVisible(false);
             jLabel2.setVisible(true);
                  JOptionPane.showMessageDialog(this, "User doesnot exist for this role.Create a user first", "cannot", JOptionPane.OK_OPTION);
                    }   
             
             
             
         }catch(Exception e){
              jLabel1.setVisible(false);
             jLabel3.setVisible(false);
             jLabel4.setVisible(false);
             jLabel6.setVisible(false);
             jLabel7.setVisible(false);
             jLabel8.setVisible(false);
             qtseries.setVisible(false);
             billseries.setVisible(false);
            receiptseries.setVisible(false);
             lqtno.setVisible(false);
             lbillno.setVisible(false);
             lrno.setVisible(false);
             QT.setVisible(false);
             Bill.setVisible(false);
             Receipt.setVisible(false);
             jLabel2.setVisible(true);
             
        
         JOptionPane.showMessageDialog(this, "User doesnot exist for this role.Create a user first", "cannot", JOptionPane.OK_OPTION);
         
         }
         
         
     }
  

        
        
}//GEN-LAST:event_jComboBox2ItemStateChanged
 
private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
   
                                     
 
}//GEN-LAST:event_jComboBox2ActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bill;
    private javax.swing.JButton QT;
    private javax.swing.JButton Receipt;
    private javax.swing.JTextField billseries;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField lbillno;
    private javax.swing.JTextField lqtno;
    private javax.swing.JTextField lrno;
    private javax.swing.JTextField qtseries;
    private javax.swing.JTextField receiptseries;
    // End of variables declaration//GEN-END:variables
}
