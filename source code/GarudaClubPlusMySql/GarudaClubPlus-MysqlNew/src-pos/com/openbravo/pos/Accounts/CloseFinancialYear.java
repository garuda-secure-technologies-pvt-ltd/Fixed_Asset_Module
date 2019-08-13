/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.GeneralSettingsAccounting;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class CloseFinancialYear {
    
    
   // Map<String, GeneralSettingInfo> settings = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();    
    private static Date startDateFromGT;// = (Date) Formats.DATE.parseValue(settings.get("Datestart").getValue());
     private static   Date endDateFromGT;//; = (Date) Formats.DATE.parseValue(settings.get("Dateend").getValue());
     
     
    public static void close(AppView app) throws BasicException {

        // get start and end date  of financial year
        Map<String, GeneralSettingInfo> settings = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        Date sdate = (Date) Formats.DATE.parseValue(settings.get("Datestart").getValue());
        Date edate = (Date) Formats.DATE.parseValue(settings.get("Dateend").getValue());
        
        Date osdate = (Date) Formats.DATE.parseValue(settings.get("Datestart").getValue());
        Date oedate = (Date) Formats.DATE.parseValue(settings.get("Dateend").getValue());
        Calendar ocal = Calendar.getInstance();
            ocal.setTimeInMillis(osdate.getTime());
            Calendar ocal1 = Calendar.getInstance();
            ocal1.setTimeInMillis(oedate.getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(sdate.getTime());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(cal.getTimeInMillis());

           // if (ocal1.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)) {
               // cal1.set(Calendar.DATE, ocal1.get(Calendar.DATE));
               // cal1.add(Calendar.DATE, 1);
          //  } else {
                cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                cal1.add(Calendar.DATE, 1);
          //  }
            sdate.setTime(cal.getTimeInMillis());
            edate.setTime(cal1.getTimeInMillis());
            TransferAndUpdate(app, cal, cal1, ocal1, sdate, edate);

    }
   

     public static void TransferAndUpdate(AppView app, Calendar cal, Calendar cal1, Calendar ocal1, Date sdate, Date edate) throws BasicException {
     TransferAndUpdate( app.getSession(),  cal,  cal1,  ocal1,  sdate,  edate);
     }

    public static void TransferAndUpdate(Session session, Calendar cal, Calendar cal1, Calendar ocal1, Date sdate, Date edate) throws BasicException {
        updateAjunadjusted(session);
        Map<String, GeneralSettingInfo> settings = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();    
   
        while (!cal.after(ocal1)) {
            String name = "AJ_" + cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK).toUpperCase() + cal.get(Calendar.YEAR);
            int count = 0;
            //to check if entries for this period exist in the live AJ table
            Object[] countobj = (Object[]) new PreparedSentence(session, "SELECT COUNT(*) FROM ACCOUNTJOURNAL WHERE DATE>=? AND DATE<?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{sdate, edate});
            if (countobj != null && countobj[0] != null) {
                count = Integer.parseInt(countobj[0].toString());
            }
            if (count > 0) {
                try {
                    //to check if the table exist
                  //  new PreparedSentence(session, "SELECT COUNT(*) FROM " + name, null, new SerializerReadBasic(new Datas[]{Datas.INT})).find();
                    
                    boolean tableExist = false;
        
                    try {
            DatabaseMetaData dbm = session.getConnection().getMetaData();
            
            ResultSet tables = dbm.getTables(null, null,  name , null);
            if(tables.next())
            {
              tableExist = true;  
            }
        } catch (SQLException ex) {
            Logger.getLogger(CloseFinancialYear.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    if(tableExist)
                    {    
                    TransferDataToRespectiveTables(name, session, sdate, edate, true);
                    }
                    else
                    {
                        int b =  new PreparedSentence(session, "drop table if exists " + name, null).exec();
                    System.out.println("Table droped count = " + b);
                    new PreparedSentence(session, "CREATE TABLE " + name +" like ACCOUNTJOURNAL" , null).exec();
//                            "(ID VARCHAR(255),MEMID VARCHAR(255) ,DATE TIMESTAMP  NULL,TRANSREF VARCHAR(510) NOT NULL,"+
//                                      "TRANSNO VARCHAR(255) NOT NULL,AMOUNT DOUBLE(64,2) NOT NULL,DUEDATE TIMESTAMP null,CLEARDATE TIMESTAMP null ,BALANCEAMOUNT DOUBLE(64,2),"+
//                                      "ADJUSTED BOOLEAN ,CREATEDBY VARCHAR(255) NOT NULL,COUNTER VARCHAR(255) NOT NULL,NARRATION VARCHAR(1020) NOT NULL,"+
//                                       "ACCOUNTID VARCHAR(255) NOT NULL,TID VARCHAR(255) NOT NULL,DATEOFENTRY TIMESTAMP ,TRANSTYPE VARCHAR(255),"+
//                                      "PAYMENTREF VARCHAR(1020),DEACTDATE TIMESTAMP,DEACTBY VARCHAR(255),ACTIVE BOOLEAN,DEACTREF VARCHAR(255),PRIMARY KEY(ID))", null).exec();
                    //CREATE  INDEX  ACC_INX_1 ON ACCOUNTJOURNAL (ACCOUNTID,DATE)
                    new PreparedSentence(session, "CREATE  INDEX  ACC_INX_" + name + " ON " + name + " (ACCOUNTID,DATE)", null).exec();
                    TransferDataToRespectiveTables(name, session, sdate, edate, false);
                    }
                    } catch (Exception e) {
                    //e.printStackTrace();
                    
                     e.printStackTrace();

                }
                cal1.add(Calendar.DATE, -1);
                try {
                    Date edatetemp = new Date(cal1.getTimeInMillis());
                    new PreparedSentence(session, "DELETE FROM AJPERIODTOTALS WHERE EDATE=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[]{edatetemp});


                    List<Object[]> dataList = new PreparedSentence(session, " SELECT CONCAT(A.ID,?),A.ID,?,(SELECT COALESCE(SUM(A1.AMOUNT),0.0) FROM " + name + " as A1  WHERE A1.ACCOUNTID=A.ID AND A1.TRANSTYPE='D' AND A1.ACTIVE=TRUE ) " +
                            ",(SELECT COALESCE(SUM(A1.AMOUNT),0.0) FROM " + name + " as A1 WHERE A1.ACCOUNTID=A.ID AND A1.TRANSTYPE='C'  AND A1.ACTIVE=TRUE )  FROM ACCOUNTMASTER as A", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.DOUBLE})).list(new Object[]{edatetemp.toString(), edatetemp});
                    for (int i = 0; i < dataList.size(); i++) {
                        Object[] obj = dataList.get(i);
                        double debit = 0.0;
                        double credit = 0.0;
                        if (obj[3] != null) {
                            debit = Double.valueOf(obj[3].toString());
                        }
                        if (obj[4] != null) {
                            credit = Double.valueOf(obj[4].toString());
                        }
                        if(debit>0.0 || credit>0.0){
                            System.out.println(debit+"-------------"+credit);
                        }
                        new PreparedSentence(session, "INSERT INTO AJPERIODTOTALS (ID,ACCOUNTID,EDATE,DEBIT,CREDIT) VALUES (?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{obj[0], obj[1], obj[2], debit, credit});
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }
            cal.setTimeInMillis(edate.getTime());

            sdate.setTime(cal.getTimeInMillis());
            cal1.setTime(cal.getTime());

            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            cal1.add(Calendar.DATE, 1);

            edate.setTime(cal1.getTimeInMillis());
        }
    }

    private static void TransferDataToRespectiveTables(String tablename, Session session, Date sdate, Date edate, boolean par) throws BasicException {
        int ajcount = 0;
           int ajmonth = 0;
           int del = 0;
        if(!par)
       {
           new PreparedSentence(session, "INSERT INTO " + tablename + "  SELECT * FROM ACCOUNTJOURNAL A1  WHERE A1.DATE >= ? AND A1.DATE < ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
        new PreparedSentence(session, "DELETE FROM ACCOUNTJOURNAL WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
       }
       else
       {
           if((sdate.after(startDateFromGT) || sdate.equals(startDateFromGT))&& (edate.equals(endDateFromGT)|| edate.before(endDateFromGT)))
           {
               Object[] countobj = (Object[]) new PreparedSentence(session, "SELECT COUNT(*) FROM " +tablename , null, new SerializerReadBasic(new Datas[]{Datas.INT})).find();
           if(countobj!=null && countobj[0]!=null)
           {
               ajmonth = Integer.parseInt(countobj[0].toString());
           }
           Object[] obj = (Object[]) new PreparedSentence(session, "SELECT COUNT(*) FROM ACCOUNTJOURNAL A1  WHERE A1.DATE >= ? AND A1.DATE < ? ",  new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{sdate, edate});
            if(obj!=null && obj[0]!=null)
           {
               ajcount = Integer.parseInt(obj[0].toString());
           }
            
           if(ajcount<ajmonth)
            {
                List<FinancialYearDefiner.AJBean> aj  = new StaticSentence(session ,"SELECT ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF from "+tablename ,SerializerWriteString.INSTANCE ,new SerializerReadClass(FinancialYearDefiner.AJBean.class )).list();
                
                 for (Iterator<FinancialYearDefiner.AJBean> it = aj.iterator(); it.hasNext();) {
                    FinancialYearDefiner.AJBean aJBean = it.next();
               
                    FinancialYearDefiner.AJBean accountJBean =  (FinancialYearDefiner.AJBean) new PreparedSentence(session, "SELECT ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF from accountjournal where id =? ",new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(FinancialYearDefiner.AJBean.class )).find(new Object[]{aJBean.getId()});
               if(accountJBean!=null)
               {
                   if(aJBean.getDate().equals(accountJBean.getDate()))
                   {
                       
                       new PreparedSentence(session, "DELETE FROM "+tablename+" WHERE id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{aJBean.getId()});
                       
                   /*    if(!aJBean.isActive())
                       {
                         new PreparedSentence(session
                         , "UPDATE "+tablename+" SET ACTIVE=FALSE,DEACTBY=?,DEACTDATE=? WHERE ID= ? "
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{"Financial Year Reloading",new Date(),aJBean.getId()});  
                         
                        
                       }*/
                       
                   }
                   else
                   {
                       new PreparedSentence(session, "INSERT INTO aj_month_err  SELECT * FROM "+tablename+" WHERE id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{aJBean.getId()});
                       
                      new PreparedSentence(session
                        , "UPDATE aj_month_err SET DEACTREF=concat(IFNULL(DEACTREF, ''),NOW()) WHERE ID= ? "
                       , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{aJBean.getId()});
                      new PreparedSentence(session, "DELETE FROM "+tablename+" WHERE id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{aJBean.getId()});
                      
                   }
               }
               
                    
                    
               }
                 
                 
             new PreparedSentence(session, "INSERT INTO " + tablename + "  SELECT * FROM ACCOUNTJOURNAL A1  WHERE A1.DATE >= ? AND A1.DATE < ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});    
            new PreparedSentence(session, "DELETE FROM ACCOUNTJOURNAL WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
            }
            
          /* else if(ajcount>ajmonth)
            {
                List<FinancialYearDefiner.AJBean> ajList  = new StaticSentence(session ,"SELECT ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF from accountjournal WHERE DATE >= ? AND DATE < ?" ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}) ,new SerializerReadClass(FinancialYearDefiner.AJBean.class )).list(new Object[]{sdate,edate});
                
                for (Iterator<FinancialYearDefiner.AJBean> it = ajList.iterator(); it.hasNext();) {
                    FinancialYearDefiner.AJBean accountjournalBean = it.next();
                    FinancialYearDefiner.AJBean maj = null;
                     maj = (FinancialYearDefiner.AJBean) new StaticSentence(session ,"SELECT ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF from  "+tablename + "  where id =?", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(FinancialYearDefiner.AJBean.class )).find(new Object[]{accountjournalBean.getId()});
                     
                     if(maj==null)
                     {
                        new PreparedSentence(session, "INSERT INTO " + tablename + "  SELECT * FROM ACCOUNTJOURNAL A1  WHERE A1.id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{accountjournalBean.getId()});
                     }
                }
                new PreparedSentence(session, "DELETE FROM ACCOUNTJOURNAL WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
                
            }*/
           else
           {
               new PreparedSentence(session, "DELETE FROM " + tablename + " WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
        new PreparedSentence(session, "INSERT INTO " + tablename + "  SELECT * FROM ACCOUNTJOURNAL A1  WHERE A1.DATE >= ? AND A1.DATE < ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
        new PreparedSentence(session, "DELETE FROM ACCOUNTJOURNAL WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});

           }
            
           }
           else
           {
               List<FinancialYearDefiner.AJBean> ajList  = new StaticSentence(session ,"SELECT ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF from accountjournal WHERE DATE >= ? AND DATE < ?" ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}) ,new SerializerReadClass(FinancialYearDefiner.AJBean.class )).list(new Object[]{sdate,edate});
                
                for (Iterator<FinancialYearDefiner.AJBean> it = ajList.iterator(); it.hasNext();) {
                    FinancialYearDefiner.AJBean accountjournalBean = it.next();
                    FinancialYearDefiner.AJBean maj = null;
                     maj = (FinancialYearDefiner.AJBean) new StaticSentence(session ,"SELECT ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF from  "+tablename + " where id =?", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(FinancialYearDefiner.AJBean.class )).find(new Object[]{accountjournalBean.getId()});
                     
                     if(maj==null)
                     {
                        if(new PreparedSentence(session, "INSERT INTO " + tablename + "  SELECT * FROM ACCOUNTJOURNAL A1  WHERE A1.id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{accountjournalBean.getId()})>0)
                        {
                         Object [] obj =  (Object[]) new PreparedSentence(session, "SELECT transref from ajunadjusted where id =? ",  new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{accountjournalBean.getId()});
                      //   Object[] obj = (Object[]) new PreparedSentence(session, "SELECT transref from ajunadjusted where id =? ",  new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{accountjournalBean.getId()}); 
                          //   = new PreparedSentence(session ,"SELECT transref from ajunadjusted where id =?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{accountjournalBean.getId()});
                         if(obj==null)
                         {
                         new PreparedSentence(session, "DELETE FROM ACCOUNTJOURNAL WHERE id= ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{accountjournalBean.getId()});
                        }}
                     }
                }
           }
          
           /*
           
           Object[] countobj = (Object[]) new PreparedSentence(session, "SELECT COUNT(*) FROM" +tablename , null, new SerializerReadBasic(new Datas[]{Datas.INT})).find();
           if(countobj!=null && countobj[0]!=null)
           {
               ajcount = Integer.parseInt(countobj[0].toString());
           }
           Object[] obj = (Object[]) new PreparedSentence(session, "SELECT COUNT(*) FROM ACCOUNTJOURNAL A1  WHERE A1.DATE >= ? AND A1.DATE < ? ",  new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{sdate, edate});
            if(obj!=null && obj[0]!=null)
           {
               ajmonth = Integer.parseInt(obj[0].toString());
           }
            List<FinancialYearDefiner.AJBean> aj  = new StaticSentence(session ,"SELECT * from accountjournal  WHERE DATE >= ? AND DATE < ? " ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}) ,new SerializerReadClass(FinancialYearDefiner.AJBean.class )).list(new Object[]{sdate, edate});
        
            for (Iterator<FinancialYearDefiner.AJBean> it = aj.iterator(); it.hasNext();) {
               FinancialYearDefiner.AJBean aJBean = it.next();
               
               
                       
               if (0 <= 0) {
            new PreparedSentence(session, "INSERT INTO AJPERIODTOTALS (ID,ACCOUNTID,EDATE,DEBIT,CREDIT,CURDEBIT,CURCREDIT) VALUES (?,?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{UUID.randomUUID().toString(), acc, edate, Formats.ApproxTo2Decimals(debit), Formats.ApproxTo2Decimals(credit), 0.0, 0.0});
            }
           }
            
            
            new PreparedSentence(session, "DELETE FROM " + tablename + " WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
        new PreparedSentence(session, "INSERT INTO " + tablename + "  SELECT * FROM ACCOUNTJOURNAL A1  WHERE A1.DATE >= ? AND A1.DATE < ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});
        new PreparedSentence(session, "DELETE FROM ACCOUNTJOURNAL WHERE DATE >= ? AND DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, edate});

           
           */
       }
        
    }

 /*   public void insertIntoMonthlyTables(Session session, String tableName, FinancialYearDefiner.AJBean accountjournalBean) throws BasicException
    {
      new PreparedSentence(session, "INSERT INTO "+tableName+"(ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.DOUBLE,Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.BOOLEAN, Datas.STRING})).exec();
                                                                                                                                                                                                                                                             // ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF
         
                // new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN})       
       // Object [] value = new Object[]{accountjournalBean.getId(), accountjournalBean.getMemid(),accountjournalBean.getDate(), accountjournalBean.getTransref(), accountjournalBean.getTransno(), accountjournalBean.getAmount(),accountjournalBean.getDuedate(), accountjournalBean.getCleardate(), accountjournalBean.getBalanceamount(), accountjournalBean.isAdjusted(), accountjournalBean.getCreatedby(), accountjournalBean.getCounter(), accountjournalBean.getNarration(),  };
                     //     new PreparedSentence(session, "INSERT INTO "+tableName+"(ID,MEMID,date,TRANSREF,TRANSNO,AMOUNT,DUEDATE,CLEARDATE,BALANCEAMOUNT,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,TID,DATEOFENTRY,TRANSTYPE,PAYMENTREF,DEACTDATE,DEACTBY,ACTIVE,DEACTREF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.DOUBLE,Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.BOOLEAN, Datas.STRING})).exec(value);
       
    }*/
    
    private static void updateAJPeriodTotals(double debit, double credit, Session session, String acc, Date edate) throws BasicException {
        // Formats.ConvertDoubleToString(debit)
        if (new PreparedSentence(session, "UPDATE AJPERIODTOTALS SET DEBIT=?,CREDIT=? WHERE ACCOUNTID=? AND EDATE=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP})).exec(new Object[]{Formats.ApproxTo2Decimals(debit), Formats.ApproxTo2Decimals(credit), acc, edate}) <= 0) {
            new PreparedSentence(session, "INSERT INTO AJPERIODTOTALS (ID,ACCOUNTID,EDATE,DEBIT,CREDIT,CURDEBIT,CURCREDIT) VALUES (?,?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{UUID.randomUUID().toString(), acc, edate, Formats.ApproxTo2Decimals(debit), Formats.ApproxTo2Decimals(credit), 0.0, 0.0});

        }
    }

    private static void createnewrow(Session session, String acc, double dtotal, double ctotal, Date edate) throws BasicException {

        new PreparedSentence(session, "INSERT INTO AJPERIODTOTALS (ID,ACCOUNTID,EDATE,DEBIT,CREDIT) VALUES (?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{UUID.randomUUID().toString(), acc, edate, dtotal, ctotal});

    }

    private static void updateAjunadjusted(Session session){
       try{
       new PreparedSentence(session, "DELETE FROM AJUNADJUSTED WHERE ADJUSTED= TRUE" , null).exec();
       } catch(Exception e){
         e.printStackTrace();
       }

    }

    public static Date getStartDateFromGT() {
        return startDateFromGT;
    }

    public static void setStartDateFromGT(Date startDateFromGT) {
        CloseFinancialYear.startDateFromGT = startDateFromGT;
    }

    public static Date getEndDateFromGT() {
        return endDateFromGT;
    }

    public static void setEndDateFromGT(Date endDateFromGT) {
        CloseFinancialYear.endDateFromGT = endDateFromGT;
    }
    
    
}
