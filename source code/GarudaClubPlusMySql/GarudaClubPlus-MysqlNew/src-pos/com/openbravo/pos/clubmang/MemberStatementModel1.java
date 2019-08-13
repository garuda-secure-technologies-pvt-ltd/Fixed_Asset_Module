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
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.DueListNoticeTableModel.addressline;
import com.openbravo.pos.clubmang.FacilityBillingTableModel.Facilityline;
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
     public class MemberStatementModel1 {
    private static Object memtype;
   private List< MemberStatementModel1> memberstatementmodel ;
    private Session s;
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
    private Date datefm;
    private Date dateto;
    
    
    
    
    public  MemberStatementModel1()
   {
   }
    
  public static  MemberStatementModel1  emptyinstance()
  {
       MemberStatementModel1  d=new MemberStatementModel1(); 
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
  
  
  
  
     public static MemberStatementModel1 loadInstance(AppView app, String ID, String accid, DataLogicFacilities dlfac, Date fromdate, Date todate,String searchkey,int Flag) throws BasicException {
        MemberStatementModel1 d = new MemberStatementModel1();
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
        //this is for a month details
         List<amountline>  previousDues;
        if(Flag==0)
        {
            List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                    , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT=? and c.visible=true  GROUP BY C.ACCOUNT order by c.searchkey",

                    new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                    new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate , Temp_todate , accid});


            if (dlist == null) {
                d.fac = new ArrayList<Facilityline>();
            }
            else {
                d.fac = dlist;
            }   
            
               previousDues = new StaticSentence(app.getSession(),  "SELECT SUM(A.DEBIT) AS AMT1 , SUM(A.CREDIT) AS AMT2, 0.0 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ? AND A.ACCOUNTID=? and a.accountid = c.account and c.visible=true  group by a.accountid order by c.searchkey ",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING }),
                new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate,accid });
       
        }   
        
        else
        {
            
            
                     List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                            , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT=? and c.visible=true  GROUP BY C.ACCOUNT having  SUM(A.DEBIT-A.CREDIT)!='0.00' order by c.searchkey",

                            new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                            new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate , Temp_todate , accid});


                    if (dlist == null) {
                        d.fac = new ArrayList<Facilityline>();
                    }
                    else {
                        d.fac = dlist;
                    } 
                previousDues = new StaticSentence(app.getSession(),  "SELECT SUM(A.DEBIT) AS AMT1 , SUM(A.CREDIT) AS AMT2, 0.0 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ? AND A.ACCOUNTID=? and a.accountid = c.account and c.visible=true  group by a.accountid having amt1!='0.00' order by c.searchkey ",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING }),
                new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate,accid });
       
            
            
        }
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
        
        
     
       
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
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE ACCOUNT=? AND VISIBLE=TRUE order by searchkey",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(MemberStatementModel1.addressline.class)).list(accid);
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for(addressline a : adr) {
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

  
     
   // FOR ALL MEMBERS HAVING NULL TRANSACTIONS.... 
     
   public static MemberStatementModel1 loadInstanceforAllMembers(AppView app, DataLogicFacilities dlfac, Date fromdate, Date todate,int Flag) throws BasicException {
        MemberStatementModel1 d = new MemberStatementModel1();
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
        //this is for a month details
       System.out.println(Temp_todate);
        List dlist_temp = new ArrayList();
        List<Object> Accid_List=new ArrayList<Object>();
            try {
           Accid_List = (List<Object>) new StaticSentence(app.getSession(), "SELECT c.account   FROM ajperiodtotals a, customers c where a.debit='0.00' and a.credit = '0.00' and a.edate= '2011-12-31 00:00:00' and a.accountid=c.account and c.visible=true  order by c.searchkey", SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE ).list();
            
            } catch (BasicException ex) {
              
            } 
            List<amountline>   previousDues ;
            if(Flag==1)
            {

                      
                        List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                                , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE c.visible=true AND  A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT in (SELECT c.account   FROM ajperiodtotals a, customers c where a.debit='0.00' and a.credit = '0.00' and a.curdebit='0.00' and a.curcredit = '0.00' and a.edate= ?  and a.accountid=c.account order by c.searchkey) GROUP BY C.ACCOUNT order by c.searchkey",

                                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP}),
                                new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate , Temp_todate});

                      List dlist1 = new StaticSentence(app.getSession()//Added to replace above commented qry
                            , "SELECT 'No Transactions ' as NAME, ? as DUEDATE,'0.00' as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, '0.00' as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c  WHERE c.visible=true AND  C.ACCOUNT  not in (select accountid from ajperiodtotals where edate = ?) GROUP BY C.ACCOUNT order by c.searchkey",

                              new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP }),
                               new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate , Temp_todate  });

                        if(dlist1.size()!=0 && dlist.size()!=0){
                             dlist.addAll(dlist1);
                       }   





                        
                        if (dlist == null) {
                            d.fac = new ArrayList<Facilityline>();
                        } else {

                           if(dlist1.size()==0){
                                d.fac = dlist;  
                           }
                           else if(dlist.size()==0){
                               d.fac = dlist1;  
                           }
                            else{
                                 d.fac = dlist;  
                           }   
                        }

                   
               previousDues = new StaticSentence(app.getSession(),  "SELECT SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) ) AS AMT1 , 0.00  AS AMT2, 0.00 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ?  and a.accountid = c.account AND c.visible=true  group by a.accountid order by c.searchkey",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate });
               
                        
        
            }
            else
            {
                
                
                        List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                                , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) ) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) ) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE     A.ACCOUNTID = C.ACCOUNT AND c.visible=true AND C.ACCOUNT in (SELECT c.account   FROM ajperiodtotals a, customers c where  a.curdebit='0.00' and a.curcredit = '0.00' and a.edate = ?  and a.accountid=c.account AND c.visible=true order by c.searchkey) GROUP BY C.ACCOUNT HAVING SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) )!='0.00' order by c.searchkey",

                                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP}),
                                new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate , Temp_todate});

                      List dlist1 = new StaticSentence(app.getSession()//Added to replace above commented qry
                         , "SELECT 'No Transactions ' as NAME, ? as DUEDATE,'0.00' as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, '0.00' as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c  WHERE  c.visible=true  and C.ACCOUNT  not in (select accountid from ajperiodtotals where edate = ?)  GROUP BY C.ACCOUNT order by c.searchkey",

                              new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP }),
                               new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate , Temp_todate  });

                     
                        if(dlist1.size()!=0 && dlist.size()!=0){
                            dlist.addAll(dlist1);
                        }   

                        if (dlist == null) {
                            d.fac = new ArrayList<Facilityline>();
                        } else {

                            if(dlist1.size()==0){
                                d.fac = dlist;  
                           }
                           else if(dlist.size()==0){
                                d.fac = dlist1;  
                           }
                            else{
                                 d.fac = dlist;  
                            }   
                        }

                       
                previousDues = new StaticSentence(app.getSession(),  "SELECT SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) ) AS AMT1 , 0.00  AS AMT2, 0.00 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ?  and a.accountid = c.account AND c.visible=true AND C.ACCOUNT in (SELECT c.account   FROM ajperiodtotals a, customers c where a.curdebit='0.00' and a.curcredit = '0.00' and a.edate <= ?  and a.accountid=c.account order by c.searchkey)group by a.accountid having SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) )!=0.00  order by c.searchkey",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{Temp_todate,Temp_todate });
        
                        
         
   

                double dtemp = 0, ctemp = 0;
                double crtemp = 0, drtemp = 0, temp = 0;
      
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
      
        ArrayList<Facilityline>lst=new ArrayList<Facilityline>();
                
           List<Facilityline>lst1=d.getfacilityline();    

              for (Facilityline f : d.getfacilityline()) {
              
                  if(f.getPrDues()==null ||f.getPrDues().equals("null"))
                  {
                    lst.add(f);
                  }
                }   
                 
               lst1.removeAll(lst);  
                 
                 
              System.out.println(lst1.size());   
                 
                 
             d.fac=lst1;    
            }         
                 
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
        
    
    
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
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS   ORDER BY SEARCHKEY",
                null,
                new SerializerReadClass(MemberStatementModel1.addressline.class)).list();
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
 
   
   
   // LOAD FOR PERTICULAR MEMBER.... 
     public static MemberStatementModel1 loadInstance(AppView app, DataLogicFacilities dlfac, Date fromdate, Date todate, String memtype,int Flag) throws BasicException {
        MemberStatementModel1 d = new MemberStatementModel1();
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
        
        //this is for a month details 
        List<amountline> previousDues;
        
        if(Flag==0)
        {
                            List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                                    , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE c.visible=true and A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT in (SELECT c.account   FROM ajperiodtotals a, customers c where a.debit='0.00' and a.credit = '0.00' and a.curdebit='0.00' and a.curcredit = '0.00' and a.edate= ?  and a.accountid=c.account AND c.memtype=? order by c.searchkey) GROUP BY C.ACCOUNT order by c.searchkey",

                                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                                    new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate , Temp_todate , memtype});


                           List dlist1 = new StaticSentence(app.getSession()//Added to replace above commented qry
                                    , "SELECT 'No Transactions ' as NAME, ? as DUEDATE,'0.00' as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, '0.00' as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c  WHERE c.visible=true and c.memtype=? and  C.ACCOUNT  not in (select accountid from ajperiodtotals where edate = ?) GROUP BY C.ACCOUNT order by c.searchkey",

                                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.STRING ,  Datas.TIMESTAMP  }),
                                    new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate ,memtype , Temp_todate });






                           if(dlist1.size()!=0 && dlist.size()!=0){
                                 dlist.addAll(dlist1);
                            }   







                            if (dlist == null) {
                                d.fac = new ArrayList<Facilityline>();
                            } else {

                                if(dlist1.size()==0){
                                    d.fac = dlist;  
                                }
                                else if(dlist.size()==0){
                                     d.fac = dlist1;  
                                }
                                else{
                                     d.fac = dlist;  
                                }
                            }  
                            
                            
                previousDues = new StaticSentence(app.getSession(),  "SELECT SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) ) AS AMT1 , 0.00  AS AMT2, 0.00 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ?  and a.accountid = c.account and c.visible=true group by a.accountid order by c.searchkey",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate });
        
                            }
        else
        {
              List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                                    , "SELECT 'No Transactions ' as NAME, ? as DUEDATE, SUM(A.DEBIT-A.CREDIT) as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, SUM(A.DEBIT-A.CREDIT) as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c , AJPERIODTOTALS A WHERE c.visible=true and A.ACCOUNTID = C.ACCOUNT AND C.ACCOUNT in (SELECT c.account   FROM ajperiodtotals a, customers c where a.debit='0.00' and a.credit = '0.00' and a.curdebit='0.00' and a.curcredit = '0.00' and a.edate= ?  and a.accountid=c.account AND c.memtype=? order by c.searchkey) GROUP BY C.ACCOUNT having SUM(A.DEBIT-A.CREDIT)!='0.00'  order by c.searchkey",

                                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                                    new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate , Temp_todate , memtype});


              List dlist1 = new StaticSentence(app.getSession()//Added to replace above commented qry
                                   , "SELECT 'No Transactions ' as NAME, ? as DUEDATE,'0.00' as AMOUNT,'--' as AID, '--' as TRANSNO, C.NAME as ACCOUNT, 'No Transactions' as NARRATION, ? as DATE, '' as FID, '0.00' as AMT, C.NAME as MEMNAME, C.SEARCHKEY as MEMNO, '' as TTYPE from customers c  WHERE c.visible=true and c.memtype=? and  C.ACCOUNT  not in (select accountid from ajperiodtotals where edate = ?) GROUP BY C.ACCOUNT order by c.searchkey",

                                 new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.STRING ,  Datas.TIMESTAMP  }),
                                 new SerializerReadClass(MemberStatementModel1.Facilityline.class)).list(new Object[]{Temp_todate  , Temp_todate ,memtype , Temp_todate });






                           if(dlist1.size()!=0 && dlist.size()!=0){
                                 dlist.addAll(dlist1);
                           }   







                            if (dlist == null) {
                                d.fac = new ArrayList<Facilityline>();
                            } else {

                                if(dlist1.size()==0){
                                d.fac = dlist;  
                                }
                               else if(dlist.size()==0){
                                    d.fac = dlist1;  
                                }
                                else{
                                    d.fac = dlist;  
                               }
                            }   
                previousDues = new StaticSentence(app.getSession(),  "SELECT SUM( (A.DEBIT+A.CURDEBIT) - (A.CREDIT+A.CURCREDIT) ) AS AMT1 , 0.00  AS AMT2, 0.00 AS AMT3  , C.searchkey FROM AJPERIODTOTALS A , customers c WHERE A.EDATE < ?  and a.accountid = c.account and c.visible=true group by a.accountid having amt1!='0.00' order by c.searchkey",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),
                new SerializerReadClass(MemberStatementModel1.amountline.class)).list(new Object[]{ todate });
        
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
       
                
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
                    else
                    {
                        
                    }
                }
            }
        }
 
        
                
                
        }

        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
       
     
        
        
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
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE MEMTYPE=? and visible=true ORDER BY SEARCHKEY",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(MemberStatementModel1.addressline.class)).list(memtype);
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
   
   
  
  
    public List getAccountId_Having_nullTrans(AppView app) throws BasicException {
     
        
        //  CALCULATION FOR FINDING MEMBER ID WITHOUT TRANSACTION ... 
        
         List<Object> CUST_Id_list = new ArrayList<Object>();
         List<Object> Mem_id_AJ=new ArrayList<Object>();
         List<Object> Final_Cust_List=new ArrayList<Object>();
         List<Object> Acc_ID_List = new ArrayList<Object>();
         List dlist = new ArrayList();
        datefm = null;
        dateto = null;
        /*  LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        GeneralSettingInfo sinfo = gs.get("Datestart");
        GeneralSettingInfo einfo = gs.get("Dateend");
        Date fsdate = (Date) Formats.DATE.parseValue(sinfo.getValue());
        Date fedate = (Date) Formats.DATE.parseValue(einfo.getValue());

        datefm = fsdate;
        dateto = fedate;*/
           try {
           Mem_id_AJ = (List<Object>) new StaticSentence(app.getSession(), "SELECT c.account   FROM ajperiodtotals a, customers c where a.debit='0.00' and a.credit = '0.00' and a.edate= '2011-12-31 00:00:00' and a.accountid=c.account and c.visible=true order by c.searchkey", SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE ).list();
            
         
         
            } catch (BasicException ex) {
              
          }
            
        
       return Mem_id_AJ; 
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
        private Double prDues ;
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
