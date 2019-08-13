/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
//import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.StringUtils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class MemberStatementModel {

    private List<Facilityline> fac;
    private List<amountline> amou;
    private DataLogicFacilities dlfac;
    private Double creditamount;
    private Double debttotal = 0.0;
    private String creditentry;
    private String credittransno;
    private Double prevDues = 0.0;
    private Double prevBalance = 0.0;
    private Double amountReceivable = 0.0;
    private String address;
   

    private MemberStatementModel() {
    }

    public static MemberStatementModel emptyinstance() {
        MemberStatementModel d = new MemberStatementModel();
        d.fac = new ArrayList<Facilityline>();
        d.amou = new ArrayList<amountline>();
        d.creditamount = 0.0;
        d.debttotal = 0.0;
        d.creditentry = null;
        d.credittransno = null;
        d.prevDues = 0.0;
        d.amountReceivable = 0.0;
        d.address = null;
        return d;
    }

    public static MemberStatementModel loadInstance(AppView app, String ID, String accid, DataLogicFacilities dlfac, Date fromdate, Date todate,String searchkey,int Flag) throws BasicException {
        MemberStatementModel d = new MemberStatementModel();
        d.dlfac = dlfac;
        Date sdate = new Date();
        try {
            sdate = fromdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        GeneralSettingInfo sinfo = gs.get("Datestart");
        GeneralSettingInfo einfo = gs.get("Dateend");
        Date fsdate = (Date) Formats.DATE.parseValue(sinfo.getValue());
        Date fedate = (Date) Formats.DATE.parseValue(einfo.getValue());
        
        String temp_date = Formats.DATE.formatValue(todate);
        Date Temp_todate = (Date) Formats.DATE.parseValue(temp_date);
        
         List dlist = new ArrayList();
        
        
        //this is for a month details
         dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=?   " +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND A.DATE>=? AND A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID" +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.ACTIVE=TRUE " +
                " AND AJ.TRANSTYPE='C' AND AJ.DATE>=? AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ?  AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE>=? AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE  AND AJ.TRANSTYPE='D' AND AJ.DATE>=? AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=?  AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE>=? AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel.Facilityline.class)).list(new Object[]{accid, fromdate, todate,accid, fromdate, todate, accid, fromdate, todate, accid, fromdate, todate, accid, fromdate, todate});
        if (dlist == null) {
            d.fac = new ArrayList<Facilityline>();
        } else {
           
            if(dlist.size()==0){
                
               dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT 'No Transactions ' as NAME, '2012-10-23 00:00:00' as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, '2012-10-23 00:00:00' as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT=? GROUP BY C.ACCOUNT",
                
                new SerializerWriteBasic(new Datas[]{Datas.STRING}),
                new SerializerReadClass(MemberStatementModel.Facilityline.class)).list(new Object[]{accid});
         
            }
          
            d.fac = dlist;
        }
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
       
    
          if(dlist.size()!=0){
        
        
      
     
         List<amountline> previousDues  = new StaticSentence(app.getSession(),                                                                                                                                                                                                                                                                  // edited by aakash
                "SELECT SUM(AMT1),SUM(AMT2),SUM(AMT3),SKEY FROM (SELECT SUM(A.DEBIT-A.CREDIT) AS AMT1,0.0 AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM AJPERIODTOTALS A,CUSTOMERS C WHERE A.EDATE < ? AND A.ACCOUNTID=C.ACCOUNT AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY " +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,SUM(AMOUNT) AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE  A.DATE>=? AND A.DATE < ?  AND A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT  AND C.VISIBLE=TRUE AND A.ACTIVE=TRUE GROUP  BY C.SEARCHKEY" +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,0.0 AS AMT2,SUM(AMOUNT) AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE  A.DATE>=? AND A.DATE < ?  AND A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.VISIBLE=TRUE AND A.ACTIVE=TRUE GROUP BY C.SEARCHKEY)" +
                " AS FSDA GROUP BY SKEY",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,  Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel.amountline.class)).list(new Object[]{ fsdate, fsdate, sdate, fsdate, sdate});
     
      
        
        if (previousDues != null) {
            if(previousDues.size()<=0){
                amountline e = new amountline();
                e.setAmount1(0.0);
                e.setAmount2(0.0);
                e.setAmount3(0.0);
                e.setSearchKey(searchkey);
                previousDues.add(e);
            }
            for (amountline a : previousDues) {
                for (Facilityline f : d.getfacilityline()) {
                    if (a.getSearchKey().equals(f.getMemNo())) {
                        crtemp = a.getAmount2();
                        drtemp = a.getAmount3();
                        temp = a.getAmount();
                        temp = temp + drtemp - crtemp;
                         if (temp >= 0) {
                         dtemp = temp;
                         f.setPrDues(dlfac.roundTwoDecimals(dtemp));
                         f.setOptranstype("D");
                         } else {
                         ctemp = temp * -1;
                         f.setPrDues(dlfac.roundTwoDecimals(ctemp));
                         f.setOptranstype("C");
                        }
                        
                    }
                }
            }
        }

        List<addressline> adr = new StaticSentence(app.getSession(),
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE ACCOUNT=? AND VISIBLE=TRUE",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(MemberStatementModel.addressline.class)).list(accid);
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for (addressline a : adr) {
                addr = "Mem. No. " + a.getSearchKey() + " \r\n" + a.getMemname() + " \r\n" + a.getAddress() + " \r\n" + a.getAddress2() + " \r\n" + a.getCity() + " \r\n" + "Pin " + a.getPostal();
                adrmap.put(a.getSearchKey(), addr);
            }
        }
        for (Facilityline f : d.getfacilityline()) {
            f.setAddress(adrmap.get(f.getMemNo()));
        }
        return d;
       }
         
          
      // FOR MEMBER WHO DON'T HAVE ANY TRASACTION ----- AAKASH    
      else{
              List<amountline> previousDues;
              if(Flag==0)
              {
                     dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                        , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE A.ACCOUNTID = C.ACCOUNT AND c.visible=true and C.ACCOUNT=? GROUP BY C.ACCOUNT",

                        new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                        new SerializerReadClass(MemberStatementModel.Facilityline.class)).list(new Object[]{Temp_todate , Temp_todate , accid});


                      if (dlist == null) {
                      d.fac = new ArrayList<Facilityline>();
                      }
                   else {
                    d.fac = dlist;
                            } 
                   previousDues = new StaticSentence(app.getSession(),  "SELECT SUM(A.DEBIT) AS AMT1 , SUM(A.CREDIT) AS AMT2, 0.0 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ? AND A.ACCOUNTID=? and a.accountid = c.account group by a.accountid ",
                   new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING }),
                   new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate,accid });
                      
              }
              else
              {
                  dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT=? GROUP BY C.ACCOUNT having SUM(A.DEBIT-A.CREDIT)!='0.00' ",
                
                new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                new SerializerReadClass(MemberStatementModel.Facilityline.class)).list(new Object[]{Temp_todate , Temp_todate , accid});
        
        
               if (dlist == null) {
                d.fac = new ArrayList<Facilityline>();
              }
              else {
              d.fac = dlist;
                    } 
               previousDues = new StaticSentence(app.getSession(),  "SELECT SUM(A.DEBIT) AS AMT1 , SUM(A.CREDIT) AS AMT2, 0.0 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ? AND A.ACCOUNTID=? and a.accountid = c.account AND c.visible=true group by a.accountid having amt1!='0.00' ",
               new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING }),
               new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate,accid });
                      
               
               
              }
              
              
              
              
              
              
                     dtemp = 0; 
                     ctemp = 0;
                     crtemp = 0; 
                     drtemp = 0; 
                     temp = 0;
                     
                    if (previousDues != null) {
                        if(previousDues.size()<=0){
                            amountline e = new amountline();
                            e.setAmount1(0.0);
                            e.setAmount2(0.0);
                            e.setAmount3(0.0);
                            e.setSearchKey(searchkey);
                            previousDues.add(e);
                        }



                        for (amountline a : previousDues) {
                            for (Facilityline f : d.getfacilityline()) {
                               if (a.getSearchKey().equals(f.getMemNo())) {
                                    crtemp = a.getAmount2();
                                    drtemp = a.getAmount3();
                                    temp = a.getAmount();
                                    temp = temp + drtemp - crtemp;
                                     if (temp >= 0) {
                                     dtemp = temp;
                                     f.setPrDues(dlfac.roundTwoDecimals(dtemp));
                                     f.setOptranstype("D");
                                     } else {
                                     ctemp = temp * -1;
                                     f.setPrDues(dlfac.roundTwoDecimals(ctemp));
                                     f.setOptranstype("C");
                                    }

                                }
                            }
                        }
                    }

                    List<addressline> adr = new StaticSentence(app.getSession(),
                            "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE ACCOUNT=? AND VISIBLE=TRUE",
                            SerializerWriteString.INSTANCE,
                            new SerializerReadClass(MemberStatementModel.addressline.class)).list(accid);
                    String addr = null;
                    Map<String, String> adrmap = new HashMap<String, String>();
                    if (adr != null) {
                        for (addressline a : adr) {
                            addr = "Mem. No. " + a.getSearchKey() + " \r\n" + a.getMemname() + " \r\n" + a.getAddress() + " \r\n" + a.getAddress2() + " \r\n" + a.getCity() + " \r\n" + "Pin " + a.getPostal();
                            adrmap.put(a.getSearchKey(), addr);
                        }
                    }
                   for (Facilityline f : d.getfacilityline()) {
                     // for (Facilityline f : d.getfacilityline()) {
                        f.setAddress(adrmap.get(f.getMemNo()));
                    }

        return d;   
              
              
              
              
          }
        
    }

    public static MemberStatementModel loadInstance(AppView app, DataLogicFacilities dlfac, Date fromdate, Date todate, String memtype,int Flag) throws BasicException {
        MemberStatementModel d = new MemberStatementModel();
        d.dlfac = dlfac;
        Date sdate = new Date();
        try {
            sdate = fromdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        GeneralSettingInfo sinfo = gs.get("Datestart");
        GeneralSettingInfo einfo = gs.get("Dateend");
        Date fsdate = (Date) Formats.DATE.parseValue(sinfo.getValue());
        Date fedate = (Date) Formats.DATE.parseValue(einfo.getValue());
        //this is for a month details
        List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  C.MEMTYPE=? " +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND A.DATE>=? AND A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY A.ID" +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  C.MEMTYPE=? AND  AJ.ACTIVE=TRUE " +
                " AND AJ.TRANSTYPE='C' AND AJ.DATE>=? AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  C.MEMTYPE= ? AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE>=? AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  C.MEMTYPE=? " +
                " AND AJ.ACTIVE=TRUE AND AJ.TRANSTYPE='D' AND AJ.DATE>=? AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  C.MEMTYPE=? AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE>=? AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 12,8", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(MemberStatementModel.Facilityline.class)).list(new Object[]{memtype, fromdate, todate, memtype, fromdate, todate, memtype, fromdate, todate, memtype, fromdate, todate});
        if (dlist == null) {
            d.fac = new ArrayList<Facilityline>();
        } else {
            d.fac = dlist;
        }
       
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
       
      
        List<amountline> previousDues  = new StaticSentence(app.getSession(),                                                                                                                                                                                                                                                                                   // edited by akash 
                "SELECT SUM(AMT1),SUM(AMT2),SUM(AMT3),SKEY FROM (SELECT SUM(A.DEBIT-A.CREDIT) AS AMT1,0.0 AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM AJPERIODTOTALS A,CUSTOMERS C WHERE A.EDATE < ? AND A.ACCOUNTID=C.ACCOUNT AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY " +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,SUM(AMOUNT) AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE  A.DATE>=? AND A.DATE < ?  AND A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT  AND C.VISIBLE=TRUE AND A.ACTIVE=TRUE GROUP  BY C.SEARCHKEY" +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,0.0 AS AMT2,SUM(AMOUNT) AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE  A.DATE>=? AND A.DATE < ?  AND A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.VISIBLE=TRUE AND A.ACTIVE=TRUE GROUP BY C.SEARCHKEY)" +
                " AS FSDA GROUP BY SKEY",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,  Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel.amountline.class)).list(new Object[]{ fsdate, fsdate, sdate, fsdate, sdate});
        
     
        if (previousDues != null) {
            for (amountline a : previousDues) {
                for (Facilityline f : d.getfacilityline()) {
                    if (a.getSearchKey().equals(f.getMemNo())) {
                        crtemp = a.getAmount2();
                        drtemp = a.getAmount3();
                        temp = a.getAmount();
                        temp = temp + drtemp - crtemp;
                         if (temp >= 0) {
                         dtemp = temp;
                         f.setPrDues(dlfac.roundTwoDecimals(dtemp));
                         f.setOptranstype("D");
                         } else {
                         ctemp = temp * -1;
                         f.setPrDues(dlfac.roundTwoDecimals(ctemp));
                         f.setOptranstype("C");
                        }

                    }
                }
            }
        }

       

        List<addressline> adr = new StaticSentence(app.getSession(),
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE MEMTYPE=? ORDER BY SEARCHKEY",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(MemberStatementModel.addressline.class)).list(memtype);
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for (addressline a : adr) {
                addr = "Mem. No. " + a.getSearchKey() + " \r\n" + a.getMemname() + " \r\n" + a.getAddress() + " \r\n" + a.getAddress2() + " \r\n" + a.getCity() + " \r\n" + "Pin " + a.getPostal();
                adrmap.put(a.getSearchKey(), addr);
            }
        }
        for (Facilityline f : d.getfacilityline()) {
            f.setAddress(adrmap.get(f.getMemNo()));
        }
        return d;

    }

    public static MemberStatementModel loadInstanceforAllMembers(AppView app, DataLogicFacilities dlfac, Date fromdate, Date todate,int Flag) throws BasicException {
         MemberStatementModel d = new MemberStatementModel();
        d.dlfac = dlfac;
        Date sdate = new Date();
        try {
            sdate = fromdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        GeneralSettingInfo sinfo = gs.get("Datestart");
        GeneralSettingInfo einfo = gs.get("Dateend");
        Date fsdate = (Date) Formats.DATE.parseValue(sinfo.getValue());
        Date fedate = (Date) Formats.DATE.parseValue(einfo.getValue());

        //this is for a month details
        List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE C.VISIBLE=TRUE " +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND A.DATE>=? AND A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID" +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  C.VISIBLE=TRUE AND AJ.ACTIVE=TRUE " +
                " AND AJ.TRANSTYPE='C' AND AJ.DATE>=? AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  C.VISIBLE=TRUE AND  A.ACTIVE=TRUE " +
                " AND A.TRANSTYPE='D' AND A.DATE>=? AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  C.VISIBLE=TRUE " +
                " AND AJ.ACTIVE=TRUE AND AJ.TRANSTYPE='D' AND AJ.DATE>=? AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  C.VISIBLE=TRUE  AND AJ.ACTIVE=TRUE " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE>=? AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 12,8", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(MemberStatementModel.Facilityline.class)).list(new Object[]{fromdate, todate, fromdate, todate, fromdate, todate, fromdate, todate});
        if (dlist == null) {
            d.fac = new ArrayList<Facilityline>();
        } else {
            d.fac = dlist;
        }

        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
       
      
      
        List<amountline> previousDues = new StaticSentence(app.getSession(),                                                                                                                                                                                                                                                                    // edited by aakash
                "SELECT SUM(AMT1),SUM(AMT2),SUM(AMT3),SKEY FROM (SELECT SUM(A.DEBIT-A.CREDIT) AS AMT1,0.0 AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM AJPERIODTOTALS A,CUSTOMERS C WHERE A.EDATE < ? AND A.ACCOUNTID=C.ACCOUNT AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY " +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,SUM(AMOUNT) AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE  A.DATE>=? AND A.DATE < ?  AND A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT  AND C.VISIBLE=TRUE AND A.ACTIVE=TRUE GROUP  BY C.SEARCHKEY" +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,0.0 AS AMT2,SUM(AMOUNT) AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE  A.DATE>=? AND A.DATE < ?  AND A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.VISIBLE=TRUE AND A.ACTIVE=TRUE GROUP BY C.SEARCHKEY)" +
                " AS FSDA GROUP BY SKEY",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,  Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel.amountline.class)).list(new Object[]{ fsdate, fsdate, sdate, fsdate, sdate});
        
      
        
        if (previousDues != null) {
            for (amountline a : previousDues) {
                for (Facilityline f : d.getfacilityline()) {
                    if (a.getSearchKey().equals(f.getMemNo())) {
                        crtemp = a.getAmount2();
                        drtemp = a.getAmount3();
                        temp = a.getAmount();
                        temp = temp + drtemp - crtemp;
                         if (temp >= 0) {
                         dtemp = temp;
                         f.setPrDues(dlfac.roundTwoDecimals(dtemp));
                         f.setOptranstype("D");
                         } else {
                         ctemp = temp * -1;
                         f.setPrDues(dlfac.roundTwoDecimals(ctemp));
                         f.setOptranstype("C");
                        }

                    }
                }
            }
        }       

        List<addressline> adr = new StaticSentence(app.getSession(),
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE VISIBLE=TRUE ORDER BY SEARCHKEY",
                null,
                new SerializerReadClass(MemberStatementModel.addressline.class)).list();
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for (addressline a : adr) {
                addr = "Mem. No. " + a.getSearchKey() + " \r\n" + a.getMemname() + " \r\n" + a.getAddress() + " \r\n" + a.getAddress2() + " \r\n" + a.getCity() + " \r\n" + "Pin " + a.getPostal();
                adrmap.put(a.getSearchKey(), addr);
            }
        }
        for (Facilityline f : d.getfacilityline()) {
            f.setAddress(adrmap.get(f.getMemNo()));
        }
        return d;

    }

    public List<Facilityline> getfacilityline() {
        return fac;
    }

    public void setfacilityline(List<Facilityline> fac) {
        this.fac = fac;
    }

    public String getCreditEntry() {
        return creditentry;
    }

    public String getCreditTransno() {
        return credittransno;
    }

    public String getAddress() {
        return address;
    }

    public Double getcreditAmount() {
        return creditamount;
    }

    public Double getPrevBalance() {
        return prevBalance;
    }

    public Double getPrevDues() {
        return prevDues;
    }

    public Double getAmountReceivable() {
        return amountReceivable;
    }

    public Double getdebtAmount() {
        return debttotal;
    }

    public List<Facilityline> addfacility(Facilityline f) {
        fac.add(f);
        return fac;
    }

    public Facilityline newLine() {
        Facilityline f = new Facilityline();
        return f;
    }

    public static class Facilityline implements SerializableRead {

        private String fname;
        private Double amount;
        private Timestamp duedate;
        private String Narration;
        private Double amttobebilled;
        private String accountjournalid;
        private String transno;
        private String caccount;
        private Timestamp date;
        private String fid;
        private boolean selected;
        private Double amt;
        private Double bal;
        private String address;
        private String memNo;
        private String memName;
        private Double prDues = 0.0;
        private Double prBalance = 0.0;
        private Double amountRecived = 0.0;
        private String transType;
        private String optranstype;

        public String getOptranstype() {
            return optranstype;
        }

        public void setOptranstype(String optranstype) {
            this.optranstype = optranstype;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Double getAmountRecived() {
            return amountRecived;
        }

        public void setAmountRecived(Double amountRecived) {
            this.amountRecived = amountRecived;
        }

        public Double getPrBalance() {
            return prBalance;
        }

        public void setPrBalance(Double prBalance) {
            this.prBalance = prBalance;
        }

        public Double getPrDues() {
            return prDues;
        }

        public void setPrDues(Double prDues) {
            this.prDues = prDues;
        }

        public String getMemName() {
            return memName;
        }

        public String getMemNo() {
            return memNo;
        }

        public void readValues(DataRead dr) throws BasicException {
            fname = dr.getString(1);
            duedate = dr.getTimestamp(2);
            amount = dr.getDouble(3);
            amttobebilled = 0.0;
            accountjournalid = dr.getString(4);
            transno = dr.getString(5);
            caccount = dr.getString(6);
            Narration = dr.getString(7);
            date = dr.getTimestamp(8);
            fid = dr.getString(9);
            amt = dr.getDouble(10);
            memName = dr.getString(11);
            memNo = dr.getString(12);
            transType = dr.getString(13);
            selected = false;
            optranstype = "D";
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public Double getamt() {
            return amt;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setStatus(boolean status) {
            selected = status;
        }

        public String getFacilityId() {
            return fid;
        }

        public Timestamp getBilledDate() {
            return date;
        }

        public String printBilledDate() {
            return Formats.DATE.formatValue(date);
        }

        public String getNarration() {
            return Narration;
        }

        public String getcustomeraccount() {
            return caccount;
        }

        public String getaccid() {
            return accountjournalid;
        }

        public void setaccid(String accid) {
            accountjournalid = accid;
        }

        public String gettransno() {
            return transno;
        }

        public void settransno(String tno) {
            transno = tno;
        }

        public Timestamp getduedate() {
            return duedate;
        }

        public void setduedate(Timestamp duedate) {
            this.duedate = duedate;
        }

        public void setNarration(String Narration) {
            this.Narration = Narration;
        }

        public String printNarration() {
            return StringUtils.encodeXML(Narration);
        }

        public String getBalance() {
            return String.valueOf(roundTwoDecimals(amount - amttobebilled));
        }

        public Double getamount() {
            return amount;
        }

        public void setamount(Double amt) {
            this.amount = amt;
        }

        public String printfname() {
            return StringUtils.encodeXML(fname);
        }

        public void setfname(String name) {
            this.fname = name;
        }

        public String getfname() {
            return fname;
        }

        public Double getamountb() {
            return amttobebilled;
        }

        public String printamountb() {
            return String.valueOf(roundTwoDecimals(amttobebilled));
        }

        public String printamount() {
            return String.valueOf(roundTwoDecimals(amount));
        }

        public String printOrgamount() {
            return String.valueOf(roundTwoDecimals(amt));
        }

        public String printbalance() {

            return String.valueOf(roundTwoDecimals(amount - amttobebilled));
        }

        public String roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#,###0.00");
            return twoDForm.format(d);
        }

        public void setAmountb(Double amt) {
            try {
                amttobebilled = amt;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Error", "Error", JOptionPane.OK_OPTION);
            }
        }
    }

    public static class amountline implements SerializableRead {

        private Double amount1;
        private Double amount2;
        private Double amount3;
        private String searchKey;

        public void readValues(DataRead dr) throws BasicException {
            if (dr.getDouble(1) == null) {
                amount1 = 0.0;
            } else {
                amount1 = dr.getDouble(1);
            }
            amount2 = dr.getDouble(2);
            amount3 = dr.getDouble(3);
            searchKey = dr.getString(4);
        }

        public Double getAmount() {
            return amount1;
        }

        public Double getAmount2() {
            return amount2;
        }

        public Double getAmount3() {
            return amount3;
        }

        public String getSearchKey() {
            return searchKey;
        }

        public void setAmount1(Double amount1) {
            this.amount1 = amount1;
        }

        public void setAmount2(Double amount2) {
            this.amount2 = amount2;
        }

        public void setAmount3(Double amount3) {
            this.amount3 = amount3;
        }

        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }


    }

    public static class addressline implements SerializableRead {

        private String memname;
        private String searchKey;
        private String address,  address2,  postal,  city;
        private String faddress;
        private String phone,phone2;

        public void readValues(DataRead dr) throws BasicException {
            address = dr.getString(1);
            address2 = dr.getString(2);
            postal = dr.getString(3);
            city = dr.getString(4);
            searchKey = dr.getString(5);
            memname = dr.getString(6);
            phone = dr.getString(7);
            phone2 = dr.getString(8);
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        public String getPhone2()
        {
            return phone2;
        }

        public void setPhone2(String phone2)
        {
            this.phone2 = phone2;
        }


        public String getSearchKey() {
            return searchKey;
        }

        public String getAddress() {
            return address;
        }

        public String getAddress2() {
            return address2;
        }

        public String getCity() {
            return city;
        }

        public String getMemname() {
            return memname;
        }

        public String getPostal() {
            return postal;
        }

        public String getFaddress()
        {
            return faddress;
        }

        public void setFaddress(String faddress)
        {
            this.faddress = faddress;
        }

    }
}

