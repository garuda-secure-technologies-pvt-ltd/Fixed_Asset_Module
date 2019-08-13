/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClubMangIntroPage.java
 *
 * Created on Mar 16, 2009, 1:49:33 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
//import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author swathi
 */
public class ClubMangIntroPage extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form ClubMangIntroPage */
  //  private Thread thread1;
 //   private Thread thread2;
 //   private Thread thread3;
    private AppView m_App;
    private waitDialog w;
    
    public ClubMangIntroPage() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

   public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        jLabel13.setText(null);
     /*   Object[] count=(Object[])new  StaticSentence(m_App.getSession(),
                , "SELECT COUNT(*) FROM GENERALTABLE WHERE NAME=? OR NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                ,new SerializerReadBasic(new Datas[]{Datas.STRING}));*/
        String cacc=m_App.getAppUserView().getUser().getcashaccount();
        String chacc=m_App.getAppUserView().getUser().getchequeaccount();
        
   // CODE FOR SETTING DEACTIVATED KITCHEN PRODUCTS ACTIVE......................................................................................................
        
      Date Datetemp = new Date();
      String xTemp = Formats.DATE.formatValue(Datetemp);                                                                                      // sms sending code......
      Datetemp = (Date) Formats.DATE.parseValue(xTemp);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(Datetemp);
      calendar.set(Calendar.MILLISECOND, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.HOUR, 0);
      
        
      
      // Check for deactivation of member if pending bills 
      
    
      
      
      
      
        
      
       
     int UpdateKitchen_Prod =  new PreparedSentence(m_App.getSession(), "  UPDATE deactiveproduct SET ACTIVE=0 , ACTIVATEDBY=? , ACTIVATEDHOST=? , ACTIVATEDATE=? WHERE DATE(DEACDATE) != DATE(?) AND ACTIVE=1  "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP })).exec
                                                                            (new Object[]{ "Auto"  , m_App.getProperties().getHost() , new Date(),  Datetemp });
                          





// CODE FOR BIRTHDAY WISH EDITED BY AKASH-------------------------------------------------------------------------------------------------------
        
        
            Date D = new Date();
            String x = Formats.DATE.formatValue(D);                                                                                      // sms sending code......
            D = (Date) Formats.DATE.parseValue(x);
            Calendar cal = Calendar.getInstance();
            cal.setTime(D);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int Month = cal.get(Calendar.MONTH)+1;
        
        
        Boolean flag = m_App.getAppUserView().getUser().hasPermission("BirthdaySMSPermission");
        if(flag){
            birthday_notification_label.setVisible(true);
            birthday_notification_label.setText("You Have birthday Permission");
            
        }
        else{
            birthday_notification_label.setVisible(false);
        }
        
        
         Object[] Birthdy_Message= (Object[]) new StaticSentence(m_App.getSession()
                , "SELECT PREFIX , MESSAGE1 , MEMFLAG , AUTOFLAG , MEMNAME FROM sms_greetings WHERE ACTIVE=1 AND MEMFLAG=1  "
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.INT ,  Datas.INT ,  Datas.INT })).find();
        
       
         
         
         if(Birthdy_Message!=null){
         
         
         String Prefix = Birthdy_Message[0].toString();
         String MESSAGE1 = Birthdy_Message[1].toString();
         int AUTOFLAG = Integer.parseInt(Birthdy_Message[3].toString());
         int MEMNAME = Integer.parseInt(Birthdy_Message[4].toString());
        
         String Message = Prefix+MESSAGE1;
         System.out.println("Message lenght : " + Message.length());
         
         // DELETE SMS_BIRTHSY _ CURRENTLIST
         
         int sms_birthdy_currlist =  new PreparedSentence(m_App.getSession(), "  DELETE FROM sms_birthdy_currlist WHERE  DAY(DATE)!=? OR MONTH(DATE)!=? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.INT , Datas.INT})).exec
                                                                            (new Object[]{ day ,  Month });
                  
         
         
         
         int   insert_data =  new PreparedSentence(m_App.getSession()  , "insert into sms_birthdy_currlist (ID , DATE , MESSAGE , MEMNO , ACTIVE , FLAG)  \n" +
                                                                            "select c.ID , c.DOB  , ? , c.searchkey , 1 ,1 from  customers c       \n" +
                                                                            "where DAY(dob)=? AND MONTH(dob)=?      \n" +
                                                                            "and (SELECT COUNT(*) FROM sms_birthdy_currlist l WHERE l.ID = c.ID)=0"                           
                                                                             , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.INT, Datas.INT })                         
                                                                           ).exec( new Object[]{ Message , day ,  Month   });                                                                                                


         
         
         if(AUTOFLAG==1){
             
              String t = UUID.randomUUID().toString();
              int   ActiveMsgTable =  new PreparedSentence(m_App.getSession()  , "insert into activemsgtable (ID , MESSAGE , SENDTO , CNT , PRIORITY , FLAG)\n" +
                                                                                    "SELECT S.ID , S.MESSAGE , C.MOBILE , ? , ? , ? FROM sms_birthdy_currlist S , CUSTOMERS C\n" +
                                                                                    "WHERE C.SEARCHKEY = S.MEMNO AND S.ACTIVE=1 AND LENGTH(C.MOBILE)=10  "                           
                                                                                , new SerializerWriteBasic(new Datas[]{ Datas.INT , Datas.INT, Datas.INT })                         
                                                                              ).exec( new Object[]{  0 , 2 ,  0   });                                                                                                

              
              int   sentbirthdaywish =  new PreparedSentence(m_App.getSession()  , "insert into sentbirthdaywish (ID , MEMNO , DATEOFSENT , CRBY , MESSAGE , MOBILE , FLAG)\n" +
                                                                                    "SELECT S.ID ,S.MEMNO , ? , ? ,  S.MESSAGE , C.MOBILE , ?  FROM sms_birthdy_currlist S , CUSTOMERS C\n" +
                                                                                    "WHERE C.SEARCHKEY = S.MEMNO AND S.ACTIVE=1  AND LENGTH(C.MOBILE)=10 "                           
                                                                                , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.STRING ,  Datas.INT })                         
                                                                              ).exec( new Object[]{  new Date() , m_App.getAppUserView().getUser().getName() ,  1   });   
             
              
              

             int UPDATE_SMSCURRLIST =  new PreparedSentence(m_App.getSession(), "  UPDATE sms_birthdy_currlist SET ACTIVE=?  "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.INT })).exec
                                                                            (new Object[]{ 0 });
                  
             
             
             
             
             
             
             
             
          }
         
         
         
         
         
         
         Object[] CountTodayBirthdy= (Object[]) new StaticSentence(m_App.getSession()
                , "SELECT COUNT(*) FROM sms_birthdy_currlist WHERE ACTIVE=1   "
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[]{ Datas.INT })).find();
        
         
         
        int PendingNo = Integer.parseInt(CountTodayBirthdy[0].toString());
         
        Boolean flag2 = m_App.getAppUserView().getUser().hasPermission("BirthdaySMSPermission");
        if(flag2 && PendingNo>0){
            
            birthday_notification_label.setVisible(true);
            birthday_notification_label.setText("*  You have "+PendingNo+" pending Birthdy wishes to be sent to member today.  \n    Please go to birthday wish list to wish them by SMS.  ");
            
        }
        else{
            birthday_notification_label.setVisible(false);
        }
         
         
         
         
         }
         else{
             birthday_notification_label.setText(null);
             birthday_notification_label.setVisible(false);
         }
        
        
        
        
         
         
         
         // CODE FOR SENDING FESTIVAL MESSAGES........................................................................................................
         
         
         
          Object[] Festival_Message= (Object[]) new StaticSentence(m_App.getSession()
                , "SELECT PRIFIX , MESSAGE  , AUTOFLAG , SENT , ACTIVE , ID , DATE FROM sms_festival WHERE ACTIVE=1 AND SENT=0  "
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.INT ,  Datas.INT ,  Datas.INT , Datas.STRING , Datas.TIMESTAMP })).find();
        
         if(Festival_Message!=null){
             
               int autoFlag = Integer.parseInt(Festival_Message[2].toString());
               if(autoFlag==0){
                   
                  String Message  =  Festival_Message[1].toString();
                  String Prifix =  Festival_Message[0].toString();
                  final String FullMessgae =  Prifix+Message;
                  final String Fest_ID =  Festival_Message[5].toString();
                  
                  
                  Date festDate = null;
                  try{
                    festDate = (Date) Formats.TIMESTAMP.parseValue(Festival_Message[6].toString());
                  }
                  catch(Exception e){
                      
                  }
                  
                  String x1 = Formats.DATE.formatValue(D);                                                                                      // sms sending code......
                  festDate = (Date) Formats.DATE.parseValue(x1);
                  Calendar cal1 = Calendar.getInstance();
                  cal1.setTime(festDate);
                  int festday = cal1.get(Calendar.DAY_OF_MONTH);
                  int festMonth = cal1.get(Calendar.MONTH)+1;        
                          
                  if(day==festday && Month== festMonth)        
                          
                  {
                   
                  
                     Thread t = new Thread(
                        new Runnable() {

                            public void run() { 
                      
                      
                      try{
                            w = new waitDialog(new JFrame(), true);
                            int h = w.getSize().height;
                            int w1 = w.getSize().width;
                            Toolkit toolkit = Toolkit.getDefaultToolkit();
                            Dimension scrnsize = toolkit.getScreenSize();
                            w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);
                      
                      Transaction t1 = new Transaction(m_App.getSession()) {

                        @Override
                        protected Object transact() throws BasicException {
                       
                            
                       
                            System.out.println("Same to day");
                 
                                int update_Festival =  new PreparedSentence(m_App.getSession(), "  UPDATE sms_festival  SET SENT=1 , ACTIVE=0 WHERE ID=?   "
                                                                                       , new SerializerWriteBasic(new Datas[]{ Datas.STRING })).exec
                                                                                       (new Object[]{ Fest_ID });


                                List<Object> MobileNoList = new ArrayList<Object>();

                                try{
                                    MobileNoList = getMemberNos();
                                }
                                catch(Exception e){

                                }


                                for(int i=0;i<MobileNoList.size() ; i++){

                                    String Mobileno = MobileNoList.get(i).toString();

                                     int   insert_data3 =  new PreparedSentence(m_App.getSession()  , "insert into activemsgtable (ID , MESSAGE , SENDTO  , CNT , PRIORITY , FLAG) VALUES(?,?,?,?,?,?) " 
                                                                                                     , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING  , Datas.STRING  ,  Datas.INT, Datas.INT , Datas.INT })                         
                                                                                                      ).exec( new Object[]{ UUID.randomUUID().toString() , FullMessgae , Mobileno , 0,2,0   });                                                                                                




                                }
                                
                                w.hideDialog();
                       
                        return null;
                        }
                    };
                       t1.execute();
                      
                        
                   }
                catch (Exception ex)
	        {
                    w.hideDialog();
                    ex.printStackTrace();
                    new MessageInf(ex).show(getParent());
                    
                }
                      
                      
                      
                }
             });
                t.start();
                w.showDialog("Please wait. Sending Auto Messages");
                w.hideDialog();
                
                  
                  
                  
                  
                       
                 
                   
            }
                   
                   
                   
                   
                   
               }
               
             
             
             
         }
         
         
         
        
        
        
        
        
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        GeneralSettingInfo pinfo=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("Petty Cash Incharge");
        GeneralSettingInfo pcaccinfo=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("Petty Cash Account");
     //   if(cacc!=null && chacc!=null){
        int total=0;
        Object[] count=new Object[1];
         if(pinfo!=null && pinfo.getValue()!=null && pcaccinfo!=null && pcaccinfo.getValue()!=null && m_App.getAppUserView().getUser().getId().equals(pinfo.getValue())){
           count=(Object[])new StaticSentence(m_App.getSession()
                , "SELECT COUNT(*) FROM ACCOUNTJOURNALDUP A1,ACCOUNTEDITDETAIL A WHERE A.TID=A1.TID AND ((A.CONFIRMEDBY IS NULL AND A1.ACTIVE=TRUE AND (A1.ACCOUNTID=? OR A1.ACCOUNTID=? OR A1.ACCOUNTID=?) AND A.CONFIRMER=A1.ACCOUNTID) OR (A1.ACTIVE=FALSE AND A.FLAG IS NULL  AND ((A1.ACCOUNTID=? OR A1.ACCOUNTID=? OR A1.ACCOUNTID=?) AND A.CONFIRMER=A1.ACCOUNTID) ))  "
                , new  SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})
                ,new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{cacc,chacc,pcaccinfo.getValue(),cacc,chacc,pcaccinfo.getValue()});
         }else
          count=(Object[])new StaticSentence(m_App.getSession()
                , "SELECT COUNT(*) FROM ACCOUNTJOURNALDUP A1,ACCOUNTEDITDETAIL A WHERE A.TID=A1.TID AND ((A.CONFIRMEDBY IS NULL AND A1.ACTIVE=TRUE AND (A1.ACCOUNTID=? OR A1.ACCOUNTID=?) AND A.CONFIRMER=A1.ACCOUNTID) OR (A1.ACTIVE=FALSE AND A.FLAG IS NULL  AND ((A1.ACCOUNTID=? OR A1.ACCOUNTID=?) AND A.CONFIRMER=A1.ACCOUNTID) ))  "
                , new  SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})
                ,new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{cacc,chacc,cacc,chacc,m_App.getAppUserView().getUser().getId()});
         List<Object[]> count1=new StaticSentence(m_App.getSession()
                , "SELECT DISTINCT A1.TID,(SELECT COUNT(*) FROM ACCOUNTEDITDETAIL A WHERE A1.TID=A.TID AND A.FLAG IS NULL  AND A.CONFIRMER=?) FROM ACCOUNTJOURNALDUP A1 WHERE  A1.ACTIVE=FALSE   "
                , new  SerializerWriteBasic(new Datas[]{Datas.STRING})
                ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.INT})).list(new Object[]{m_App.getAppUserView().getUser().getId()});
         if(count!=null){
          if(count[0]!=null){
              total=Integer.valueOf(count[0].toString());
          }
         }
         if(count1!=null){
           for(Object[] line:count1){
              total += Integer.valueOf(line[1].toString());
           }
         }
          if(total>0){
               if(total>1)
                 jLabel13.setText("There are "+total+" Entries waiting for your confirmation");
               else
                 jLabel13.setText("There is "+total+" Entry waiting for your confirmation");
           }
      //  }
        loadData();
    }
    private void loadData() throws BasicException {
       //reset();
      /*   thread1 =
			new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
                            while(true){
                               jLabel4.setVisible(false);
                               thread1.sleep(300);
							   threadjLabel4.setVisible(true);
                               thread1.sleep(1000);
                              
                            }
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);

		thread1.start();
          thread2 =
			new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
                            while(true){
                                 jLabel5.setVisible(false);
                               thread2.sleep(300);
							   jLabel5.setVisible(true);
                               thread2.sleep(750);
                              
                            }
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);

		thread2.start();
          thread3 =
			new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
                            while(true){
                                jLabel6.setVisible(false);
                               thread3.sleep(300);
							   jLabel6.setVisible(true);
                               thread3.sleep(500);
                               
                            }
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);

		thread3.start();*/
    }
     public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
      /*  thread1.stop();
        thread2.stop();
        thread3.stop();*/
        return true;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        birthday_notification_label = new javax.swing.JEditorPane();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome");
        add(jLabel1);
        jLabel1.setBounds(60, 40, 550, 70);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 64)); // NOI18N
        jLabel3.setText("Garuda");
        add(jLabel3);
        jLabel3.setBounds(100, 140, 220, 60);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(220, 230, 14, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(220, 270, 14, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(220, 320, 14, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Restaurant & Bar Billing");
        add(jLabel7);
        jLabel7.setBounds(270, 222, 230, 40);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Club Management ");
        add(jLabel8);
        jLabel8.setBounds(270, 270, 200, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Financial Management ");
        add(jLabel9);
        jLabel9.setBounds(270, 310, 220, 40);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        add(jPanel1);
        jPanel1.setBounds(0, 0, 0, 0);

        jPanel2.setLayout(null);
        add(jPanel2);
        jPanel2.setBounds(0, 0, 0, 0);

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 64)); // NOI18N
        jLabel10.setText("Club Plus");
        add(jLabel10);
        jLabel10.setBounds(320, 140, 320, 60);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("* Please choose from the menu on the left");
        add(jLabel2);
        jLabel2.setBounds(210, 510, 390, 20);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/greenled.png"))); // NOI18N
        add(jLabel11);
        jLabel11.setBounds(220, 360, 30, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Inventory Management");
        add(jLabel12);
        jLabel12.setBounds(270, 360, 210, 20);

        jLabel13.setForeground(new java.awt.Color(153, 153, 0));
        jLabel13.setText("jLabel13");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        add(jLabel13);
        jLabel13.setBounds(100, 560, 560, 17);
        add(jSeparator1);
        jSeparator1.setBounds(0, 540, 700, 10);

        birthday_notification_label.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        birthday_notification_label.setForeground(new java.awt.Color(227, 12, 12));
        jScrollPane2.setViewportView(birthday_notification_label);

        add(jScrollPane2);
        jScrollPane2.setBounds(100, 580, 670, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane birthday_notification_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    
    
     public List getMemberNos() throws BasicException{
       List<Object> GrpNameList = new ArrayList<Object>();
       
        GrpNameList=new PreparedSentence(m_App.getSession(), "SELECT MOBILE FROM CUSTOMERS WHERE LENGTH(MOBILE)=10 OR LENGTH(MOBILE)=13  AND VISIBLE=TRUE", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).list();
       
        return GrpNameList;
        
   }
    
     
     
     
  
    
    
}
