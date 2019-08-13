/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import RMI.ComputePi;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
//import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
//import com.openbravo.data.loader.SerializerReadBuilder;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteInteger;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.TableDefinition;
//import com.openbravo.format.Formats;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountAudit;
import com.openbravo.pos.Accounts.BankReconcilation;
import com.openbravo.pos.Accounts.LastBillDate;
import com.openbravo.pos.Accounts.MinUsageCustomer;

import com.openbravo.pos.Accounts.NoticeMasterTableModel;
import com.openbravo.pos.CardsRoom.CancelRequestInfo;
import com.openbravo.pos.CardsRoom.GameInfo;
import com.openbravo.pos.CardsRoom.GameInfoExt;
import com.openbravo.pos.CardsRoom.GameLog;
import com.openbravo.pos.CardsRoom.PlayeresData;
import com.openbravo.pos.CardsRoom.TokenCombinationInfo;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.customers.MinimumUsage;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSystem;

import com.openbravo.pos.sales.Department1;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Locale;
//import org.apache.velocity.runtime.directive.Foreach;

/**
 *
 * @author swathi
 */
public abstract class DataLogicFacilities extends BeanFactoryDataSingle {

    protected Session s;
    private TableDefinition facility;
    private TableDefinition tvendor;

    public double getCustomerUsage(String cid, Date sdate, Date edate) throws BasicException {
        double usage = 0.0;
        double otheramt = 0.0;
        String[] fac=null;
        String fid=null;
       double tax=0.0;
       double amount=0.0;
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SUM(AMT) FROM (SELECT SUM(AMOUNT) AS AMT FROM BILL WHERE CUSTOMER=? AND CREATEDDATE>=? AND CREATEDDATE<=? UNION ALL SELECT SUM(AMOUNT) AS AMT FROM BILL_ARV WHERE CUSTOMER=? AND CREATEDDATE>=? AND CREATEDDATE<=?) as amt",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{cid,sdate,edate,cid,sdate,edate});
        if(facility!=null){
          //  fac = facility.split("#");
            for(int i=0;i<fac.length;i++){
                fid = fac[i];
               Object obj1 = new StaticSentence(s, "SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL A,CUSTOMERS C,memfacilityusage m WHERE A.ACCOUNTID=C.ACCOUNT AND A.TRANSREF=? AND C.ID=? AND A.DATE>=? AND A.DATE<=? AND A.TRANSTYPE='D'  AND A.ACTIVE=TRUE and m.facilitytype=a.transref and m.active=true and m.memno=c.id GROUP BY m.facilitytype,a.TRANSREF",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),SerializerReadString.INSTANCE).find(new Object[]{fid,cid,sdate,edate});
               if(obj1!=null){
                   amount =  Double.valueOf(obj1.toString());
                   tax = (amount)*(10.3/100);
                   //amount = amount - Math.floor(tax);
                   amount = amount - Math.abs(tax);
                   otheramt = otheramt + amount;
               }
            }
        }
        if (obj != null) {
            if(obj[0]!=null)
            usage = (Double) obj[0];
        }
        usage = usage + otheramt;
        return usage;
    }

    public void init(Session s) {
        this.s = s;
        //ID,ACTIVE,NAME,ADDRESS,CONTACTPERSON,CONTACTNUM,PANNO,TINNO,CST,ACCOUNT,INSTRUCTION,CREATEDBY,CRDATE
        tvendor = new TableDefinition(s, "VENDOR", new String[]{"ID", "ACTIVE", "NAME", "ADDRESS", "CONTACTPERSON", "CONTACTNUM", "PANNO", "TINNO", "CST", "ACCOUNT", "INSTRUCTION", "CREATEDBY", "CRDATE", "DEACTBY", "DEACTDATE"}, new String[]{"ID", "ACTIVE", "NAME", "ADDRESS", "CONTACTPERSON", "CONTACTNUM", "PANNO", "TINNO", "CST", "ACCOUNT", "INSTRUCTION", "CREATEDBY", "CRDATE", "DEACTBY", "DEACTDATE"}, new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP}, new Formats[]{Formats.STRING, Formats.BOOLEAN, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.TIMESTAMP, Formats.STRING, Formats.TIMESTAMP
                }, new int[]{0});
    }

    public final Facility getFacilitybyID(String id) throws BasicException {
        return (Facility) new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM FACILITY WHERE ACTIVE =TRUE AND ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).find(id);
    }

    public final SentenceList getPeriodicity() {
        return new StaticSentence(s, "SELECT ID,NAME,TYPE_,NO,DATE,FMONTH,BILLTYPE,DOJ,ACCURATE,CREATEDBY FROM PERIODICITY WHERE ACTIVE =TRUE ORDER BY NAME", null, new SerializerReadClass(Periodicity.class));
    }
//by vineeth
//    public final SentenceList getAllNames() throws BasicException{
//      return new StaticSentence(s, " SELECT ID,NAME,TYPE,SIGN FROM accountmaster where parent='1.1.2'" ,null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING}));  
//    }
//    
// public final SentenceList getAllNames1() throws BasicException{
//      return new StaticSentence(s, " SELECT ID,NAME,TYPE,SIGN FROM accountmaster where parent='2.1.2'" ,null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING}));  
//    }
//        
    
    
    
    public final Object[] getMamberbySkey(String mno) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT ID,NAME,MEMTYPE,MOBILE,ACCOUNT FROM CUSTOMERS WHERE SEARCHKEY = ? and VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(mno);
    }
    public final Object[] getMamberbycard(String card) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT ID,SEARCHKEY,NAME,MEMTYPE,MOBILE,ACCOUNT FROM CUSTOMERS WHERE CARD = ? and VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING})).find(card);
    }
    public final Object[] getMemberbyID(String mid) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT SEARCHKEY,NAME,MEMTYPE FROM CUSTOMERS WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(mid);
    }

//    public final List<Facility> getFacility() throws BasicException {
//        return new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,TAXCAT_2,TAXCAT_3 FROM FACILITY WHERE ACTIVE =TRUE ORDER BY NAME", null, new SerializerReadClass(Facility.class)).list();
//    }
    //added by shweta
     public final List<Facility> getFacility() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2  FROM FACILITY WHERE ACTIVE =TRUE ORDER BY NAME", null, new SerializerReadClass(Facility.class)).list();
    }
     
//praveen":
    public final List<PeopleInfo> getAllPeoples() throws BasicException{
        return new StaticSentence(s, "select id,name,role from people where visible=true",null,new SerializerReadClass(PeopleInfo.class)).list();
    }

    public final List<Minusage> getMinUsage() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,LASTBILLEDDATE,DEACTIVATEDDATE,DEACTIVATEDBY,MAXNO,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM MINUSAGE WHERE ACTIVE=TRUE AND ACHEAD is not NULL ORDER BY NAME", null, new SerializerReadClass(Minusage.class)).list();
    }
    public final Minusage getMinUsageById(String id) throws BasicException {
        return (Minusage) new StaticSentence(s, "SELECT ID,NAME,PERIOD,FACILITIES,AMOUNT,MINUSAGETYPE,CREATEDDATE,CHARGE,ACHEAD,MEMBERS,ACTIVE,CREDITPERIOD,CREATEDBY,BILLSEQUENCE,SERVICETAX,MAXDEBT,SMS,LASTBILLEDDATE,DEACTIVATEDDATE,DEACTIVATEDBY,MAXNO,SERVICETAX2,SERVICETAX3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM MINUSAGE WHERE ID=?",SerializerWriteString.INSTANCE, new SerializerReadClass(Minusage.class)).find(id);
    }

    public final List<Facility> getFacility1() throws BasicException {
        return new StaticSentence(s, "SELECT NAME FROM FACILITY WHERE ACTIVE =TRUE ORDER BY NAME", null, new SerializerReadClass(Facility.class)).list();
    }
    public final List<LastBillDate> getLastBillDates(String usagetype) throws BasicException {
       return new StaticSentence(s, "SELECT DISTINCT M.LBILLDATE FROM MEMMINUSAGE M,CUSTOMERS C WHERE C.VISIBLE = ? AND M.MEMNO=C.ID AND M.USAGETYPE=? ",new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING}), new SerializerReadClass(LastBillDate.class)).list(new Object[]{true,usagetype});              
    }

     public final LastBillDate getLastBillDateOfMinUsageCustomer(String usagetype,String mid) throws BasicException {
        return (LastBillDate) new StaticSentence(s, "SELECT M.LBILLDATE FROM MEMMINUSAGE M,CUSTOMERS C WHERE C.ID = ? AND M.MEMNO=C.ID AND M.USAGETYPE=? ",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(LastBillDate.class)).find(new Object[]{mid,usagetype});
    }

    public final double getCustomerUsage(String cid,Date sdate,Date edate,String facility) throws BasicException {
        double usage = 0.0;
        double otheramt = 0.0;
        String[] fac=null;
        String fid=null;
       double tax=0.0;
       double amount=0.0;
       double GuestAmount=0.00;
       String GuestCustId= cid+"#guest%";
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SUM(AMT) FROM (SELECT SUM(AMOUNT) AS AMT FROM BILL WHERE CUSTOMER=? AND CREATEDDATE>=? AND CREATEDDATE<=? UNION ALL SELECT SUM(AMOUNT) AS AMT FROM BILL_ARV WHERE CUSTOMER=? AND CREATEDDATE>=? AND CREATEDDATE<=?) as amt",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{cid,sdate,edate,cid,sdate,edate});
        Object[] objMinimum = (Object[]) new StaticSentence(s, "SELECT VALUE FROM GENERALTABLE where NAME='Minimum Usage Guest Billing'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
        if(objMinimum!=null){ 
            Boolean v21 = (Boolean)objMinimum[0];
                if(v21){
                     Object[] obj_Guest = (Object[]) new StaticSentence(s, "SELECT SUM(AMT) FROM (SELECT SUM(AMOUNT) AS AMT FROM BILL WHERE CUSTOMER like ?  AND CREATEDDATE>=? AND CREATEDDATE<=? UNION ALL SELECT SUM(AMOUNT) AS AMT FROM BILL_ARV WHERE CUSTOMER like ?  AND CREATEDDATE>=? AND CREATEDDATE<=?) as amt",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{GuestCustId,sdate,edate,GuestCustId,sdate,edate});   
                     if(obj_Guest!=null){
                        if(obj_Guest[0]!=null){
                            GuestAmount=(Double)obj_Guest[0]; 
                        } 
                     }
                
                }
                
            
        }
        
        
        
        
        
        if(facility!=null){
            fac = facility.split("#");
            for(int i=0;i<fac.length;i++){ 
                fid = fac[i];
               Object obj1 = new StaticSentence(s, "SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL A,CUSTOMERS C,memfacilityusage m WHERE A.ACCOUNTID=C.ACCOUNT AND A.TRANSREF=? AND C.ID=? AND A.DATE>=? AND A.DATE<=? AND A.TRANSTYPE='D'  AND A.ACTIVE=TRUE and m.facilitytype=a.transref and m.active=true and m.memno=c.id GROUP BY m.facilitytype,a.TRANSREF",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),SerializerReadString.INSTANCE).find(new Object[]{fid,cid,sdate,edate});
               if(obj1!=null){
                   amount =  Double.valueOf(obj1.toString());
                   tax = (amount)*(10.3/100);
                   //amount = amount - Math.floor(tax);
                   amount = amount - Math.abs(tax);
                   otheramt = otheramt + amount;
               }
            }
        }
        if (obj != null) {
            if(obj[0]!=null)
            usage = (Double) obj[0];
        }
        usage = usage + otheramt;
        usage=usage+GuestAmount;
        
        return usage;
    }

    public final List<MinUsageCustomer> getMinusageCustomers(String usagetype) throws BasicException {
        return new StaticSentence(s, "SELECT C.ID,C.NAME,C.SEARCHKEY,C.ACCOUNT,C.MOBILE,M.LBILLDATE,M.EDATE,M.NEWMINUSAGEREF,MU.FACILITIES  FROM CUSTOMERS C,MEMMINUSAGE M,MINUSAGE MU WHERE C.ID=M.MEMNO AND M.USAGETYPE=? AND MU.ID=M.USAGETYPE AND  M.ACTIVE=TRUE AND C.VISIBLE=TRUE  ORDER BY C.SEARCHKEY",SerializerWriteString.INSTANCE, new SerializerReadClass(MinUsageCustomer.class)).list(usagetype);
    }   
     public final List<MinUsageCustomer> getMinusageCustomers1(String usagetype) throws BasicException {
        return new StaticSentence(s, "SELECT C.ID,C.NAME,C.SEARCHKEY,C.ACCOUNT,C.MOBILE,M.LBILLDATE,M.EDATE,M.NEWMINUSAGEREF,MU.FACILITIES  FROM CUSTOMERS C,MEMMINUSAGE M,MINUSAGE MU WHERE C.ID=M.MEMNO AND M.USAGETYPE=? AND MU.ID=M.USAGETYPE AND  M.ACTIVE=TRUE   ORDER BY C.SEARCHKEY",SerializerWriteString.INSTANCE, new SerializerReadClass(MinUsageCustomer.class)).list(usagetype);
    }


    public final List<PeopleInfo> getCashHolders() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,ROLE FROM PEOPLE WHERE CASHACCOUNT IS NOT NULL", null, new SerializerReadClass(Facility.class)).list();
    }

    public final SentenceList getFacilityList() {
        return new StaticSentence(s, "SELECT ID, NAME,SMSFORM  FROM FACILITY WHERE ACTIVE=TRUE ORDER BY NAME", null, new SerializerReadClass(FacilityInfo.class));
    }
   
    public final SentenceList getGeneralSettingsList() {
        return new StaticSentence(s, "SELECT ID, NAME,VALUE  FROM GENERALTABLE ORDER BY NAME", null, new SerializerReadClass(GeneralSettingInfo.class));
    }

    public final SentenceList getOtherFacility() {
        //deactivateddate and deactivatedby is used just to ensure null is read
        return new StaticSentence(s, "SELECT F.NAME,F.DEACTIVATEDDATE,F.DEACTIVATEDDATE,null,F.JOINAMT,F.RENEWALAMT,F.USAGEAMT,(SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.RPERIODICITY),F.TYPE_,F.ID,F.DEACTIVATEDBY,F.DEACTIVATEDBY,F.APPTO,F.DEACTIVATEDBY,F.DEACTIVATEDBY,NULL,NULL FROM FACILITY F WHERE F.ACTIVE =TRUE AND F.TYPE_='Optional' AND (F.ID NOT IN (SELECT M.FACILITYTYPE FROM MEMFACILITYUSAGE M WHERE M.MEMNO=? AND M.ACTIVE=TRUE) OR F.APPTO=1)  ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(MembersFacilityTableModel.Facilityline.class));
    }
    //praveen:added to taxcategories other standard facility
    public final SentenceList getOtherStandardFacility() {
        //deactivateddate and deactivatedby is used just to ensure null is read
        return new StaticSentence(s, "SELECT F.NAME,F.DEACTIVATEDDATE,F.DEACTIVATEDDATE,null,F.JOINAMT,F.RENEWALAMT,F.USAGEAMT,(SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.RPERIODICITY),F.TYPE_,F.ID,F.DEACTIVATEDBY,F.DEACTIVATEDBY,F.APPTO,F.DEACTIVATEDBY,F.DEACTIVATEDBY,NULL,NULL FROM FACILITY F WHERE F.ACTIVE =TRUE AND F.TYPE_='Standard' AND (F.ID NOT IN (SELECT M.FACILITYTYPE FROM MEMFACILITYUSAGE M WHERE M.MEMNO=? AND M.ACTIVE=TRUE) OR F.APPTO=1)  ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(MembersFacilityTableModel.Facilityline.class));
    }
    //end...........
    public final List<BankReconcilation> getAllNames() throws BasicException {
        return new StaticSentence(s, "SELECT name from accountmaster where parent='1.1.2' or parent='2.1.3'  ORDER BY name", null, new SerializerReadClass(BankReconcilation.class)).list();
    }
    
    
     public final List<BankReconcilation> getAllNames1() throws BasicException {
        return new StaticSentence(s, "SELECT name from accountmaster where parent='2.1.3' ORDER BY name", null, new SerializerReadClass(BankReconcilation.class)).list();
    }
    public final List<MemKey> getMemberKey() throws BasicException {
        return new StaticSentence(s, "SELECT SEARCHKEY FROM CUSTOMERS ORDER BY SEARCHKEY", null, new SerializerReadClass(MemKey.class)).list();
    }
    public final List<AllRoles> getAllRoles() throws BasicException {
        return new StaticSentence(s, "SELECT NAME,ID FROM ROLES ORDER BY NAME", null, new SerializerReadClass(AllRoles.class)).list();
    }
    public final List<AllUsers> getAllusers() throws BasicException {
        return new StaticSentence(s, "SELECT NAME,ID FROM PEOPLE ORDER BY NAME", null, new SerializerReadClass(AllUsers.class)).list();
    }

    public final List<MemCat> getMemberCategory() throws BasicException {
        return new StaticSentence(s, "SELECT NAME,ID FROM MEMTYPE  where active=1 ORDER BY NAME", null, new SerializerReadClass(MemCat.class)).list();
    }

    public final List<MemCat2> getMemberCategory2() throws BasicException {
        return new StaticSentence(s, "SELECT NAME FROM MEMTYPE ORDER BY NAME", null, new SerializerReadClass(MemCat2.class)).list();
    }

    public final List<MemCat1> getMemberCategory1() throws BasicException {
        return new StaticSentence(s, "SELECT null as NAME FROM MEMTYPE", null, new SerializerReadClass(MemCat1.class)).list();
    }
//added by shweta
//    public List<Facility> getmemberfacility(String memid) throws BasicException {
//        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
//        return mflist;
//    }
     public List<Facility> getmemberfacility(String memid) throws BasicException {
        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASCADE2,F.BASIC1,F.BASIC2 FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
//         System.out.println("mflist:::::::::::::"+mflist);
        
        return mflist;
    }
//     ended by shweta
//added by shweta
//    public List<Facility> getmembersDepfacility(String memid) throws BasicException {
//        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,M.USERID,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NOT NULL ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
//        return mflist;
//    }
    public List<Facility> getmembersDepfacility(String memid) throws BasicException {
        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,M.USERID,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASCADE2,F.BASIC1,F.BASIC2 FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NOT NULL ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
        return mflist;
    }
//added by shweta
//    public List<Facility> getmemberscurrentfacilities(String memid) throws BasicException {
//        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NULL  ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
//        return mflist;
//    }
    
//    public List<Facility> getmemberscurrentfacilities(String memid) throws BasicException {
//        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASCADE2,F.BASIC1,F.BASIC2 FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NULL  ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
//        return mflist;
//    }
    public List<Facility> getmemberscurrentfacilities(String memid) throws BasicException {
        List<Facility> mflist = new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASCADE2,F.BASIC1,F.BASIC2 FROM FACILITY F,MEMFACILITYUSAGE M WHERE F.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NULL  ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
        return mflist;
    }
    

//    public List<Facility> getmembersDependentscurrentfacilities(String memid) throws BasicException {
//        List<Facility> mflist = new StaticSentence(s, "SELECT DISTINCT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NOT NULL ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
//        return mflist;
//    }
     public List<Facility> getmembersDependentscurrentfacilities(String memid) throws BasicException {
        List<Facility> mflist = new StaticSentence(s, "SELECT DISTINCT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.RPERIODICITY,F.USAGEAMT,F.UPERIODICITY,F.ENTRANCECONTROL,F.DUEPERIOD,F.JFEEACCOUNT,F.RENEWALACC,F.USAGEACC,F.MEMTYPE,F.DEBTMAX,F.TYPE_,F.SERVICETAX,F.APPTO,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASECADE2,F.BASIC1,F.BASIC2 FROM FACILITY F,MEMFACILITYUSAGE M WHERE M.ACTIVE =TRUE AND M.MEMNO=? AND M.FACILITYTYPE=F.ID AND M.USERID IS NOT NULL ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class)).list(memid);
        return mflist;
    }
//     ended by shweta

    public List<MemberDependent> getmembersDependentsUsingfacility(String memid, String fid) throws BasicException {
        List<MemberDependent> mflist = new StaticSentence(s, "SELECT M.ID,M.DNAME,M.DMEMNO FROM MEMDEPENDENT M,MEMFACILITYUSAGE MF WHERE MF.MEMNO=? AND MF.FACILITYTYPE=? AND MF.MEMNO=M.MEMNO AND MF.USERID=M.ID   AND  MF.ACTIVE =TRUE   ORDER BY M.DNAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(MemberDependent.class)).list(new Object[]{memid, fid});
        return mflist;
    }
//added by shweta
//    public final List<Facility> getOptionalFacilitySpecificToType(Object type) throws BasicException {
//        return new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO FROM FACILITY WHERE ACTIVE =TRUE AND TYPE_='Optional' AND APPTO=?  ORDER BY NAME", SerializerWriteInteger.INSTANCE, new SerializerReadClass(Facility.class)).list(type);
//    }
      public final List<Facility> getOptionalFacilitySpecificToType(Object type) throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM FACILITY WHERE ACTIVE =TRUE AND TYPE_='Optional' AND APPTO=?  ORDER BY NAME", SerializerWriteInteger.INSTANCE, new SerializerReadClass(Facility.class)).list(type);
    }
//      ended by shweta

    public final SentenceList getDependentsOfMember() {
        return new StaticSentence(s, "SELECT ID,DNAME,DMEMNO FROM MEMDEPENDENT WHERE ACTIVE =TRUE AND MEMNO=?  ORDER BY DNAME", SerializerWriteString.INSTANCE, new SerializerReadClass(MemberDependent.class));
    }

    public final SentenceList getOtherDependentsOfMember() {
        return new StaticSentence(s, "SELECT M.ID,M.DNAME,M.DMEMNO FROM MEMDEPENDENT M WHERE  M.MEMNO=? AND M.ACTIVE =TRUE AND M.ID NOT IN (SELECT USERID FROM MEMFACILITYUSAGE WHERE MEMNO=M.MEMNO AND FACILITYTYPE=? AND ACTIVE=1 ) ORDER BY M.DNAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(MemberDependent.class));
    }

    public final SentenceList getPermanentFacilitySpecifictToMemType() {
        return new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM FACILITY WHERE ACTIVE =TRUE AND TYPE_='Standard' AND (MEMTYPE LIKE ? OR MEMTYPE='ALL') ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(Facility.class));
    }
    //Sanjeev:min billling for new members
    public List<MinimumUsage> getMinUsageSpecifictToMemType(String membertype) throws BasicException {
        
        List<MinimumUsage> mulist=new StaticSentence(s, "SELECT ID,NAME,LASTBILLEDDATE,null,null,EFFECTIVEFROM FROM MINUSAGE WHERE ACTIVE =TRUE  AND  ACHEAD!='NULL' AND  (MEMBERS LIKE '%"+membertype+"%' OR MEMBERS='ALL') ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(MinimumUsage.class)).list();
        return mulist;
    }
    public List<MinimumUsage> getActiveMinUsageSpecifictToMem(String mid) throws BasicException {
        List<MinimumUsage> mulist=new StaticSentence(s, "SELECT M.ID,M.NAME,M.LASTBILLEDDATE,MU.EDATE,MU.NEWMINUSAGEREF,M.EFFECTIVEFROM FROM MINUSAGE M ,MEMMINUSAGE MU  WHERE M.ID=MU.USAGETYPE AND MU.ACTIVE=TRUE AND M.ACTIVE =TRUE  AND  M.ACHEAD!='NULL'   AND MU.MEMNO=? ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(MinimumUsage.class)).list(mid);
        return mulist;
    }
    public final SentenceList getPermanentFacilitiesTobeBilled() {
        return new StaticSentence(s, "SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,P.TYPE_,P.NO,P.DATE,MF.SDATE,MF.LBILLDATE,F.RENEWALACC,F.JFEEACCOUNT,F.DUEPERIOD FROM FACILITY F,MEMFACILITYUSAGE MF,PERIODICITY P WHERE MF.ACTIVE =TRUE AND F.ACTIVE=TRUE AND MF.MEMNO = ? AND MF.FACILITYTYPE=F.ID AND P.ID=F.RPERIODICITY AND F.TYPE_='Standard' ", SerializerWriteString.INSTANCE, new SerializerReadClass(TempClass.class));
    }

    public final Periodicity getPerioicitybyid(String id) throws BasicException {
        return (Periodicity) new StaticSentence(s, "SELECT ID,NAME,TYPE_,NO,DATE,FMONTH,BILLTYPE,DOJ,ACCURATE,CREATEDBY FROM PERIODICITY WHERE ACTIVE =TRUE AND ID=? ORDER BY NAME ", SerializerWriteString.INSTANCE, new SerializerReadClass(Periodicity.class)).find(id);
    }

    public  List<FacilityBillingTableModel.Facilityline> getMembersToBeBilled(String type, Date dnow, int no, String facility) throws BasicException {
        



        return new StaticSentence(s, "SELECT M.ID,C.SEARCHKEY,C.NAME,F.RENEWALAMT,M.SDATE,M.LBILLDATE,CASE WHEN M.LBILLDATE IS NULL THEN  " +type+ "  ELSE " +type+ " END AS NUMB ,F.DUEPERIOD,C.ACCOUNT,M.MEMNO,CASE WHEN M.USERID IS NULL THEN NULL ELSE (SELECT DNAME FROM MEMDEPENDENT WHERE ID=M.USERID)END,M.USERID,C.MOBILE " + "FROM MEMFACILITYUSAGE M,FACILITY F ,CUSTOMERS C WHERE M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE AND C.ID=M.MEMNO AND C.VISIBLE=TRUE AND NUMB >= ? AND M.FACILITYTYPE=? ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP,Datas.STRING, Datas.INT, Datas.STRING}), new SerializerReadClass(FacilityBillingTableModel.Facilityline.class)).list(new Object[]{  dnow,dnow,  no, facility});
    }

    public  List<FacilityBillingTableModel.Facilityline> getMembersToBeBilled1(Date dnow,Date edatePlusOne, String facility, String type) throws BasicException {
        //DATEDIFF(M.LBILLDATE,?)
         
 return new StaticSentence(s, "SELECT M.ID,C.SEARCHKEY,C.NAME,F.RENEWALAMT,M.SDATE,M.LBILLDATE, "+type+" ,F.DUEPERIOD,C.ACCOUNT,M.MEMNO,CASE WHEN M.USERID IS NULL THEN NULL ELSE (SELECT DNAME FROM MEMDEPENDENT WHERE ID=M.USERID) END,M.USERID,C.MOBILE " + "FROM MEMFACILITYUSAGE M,FACILITY F ,CUSTOMERS C WHERE M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE AND C.ID=M.MEMNO AND C.VISIBLE=TRUE AND M.FACILITYTYPE=? AND M.LBILLDATE<? ORDER BY C.SEARCHKEY", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,  Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(FacilityBillingTableModel.Facilityline.class)).list(new Object[]{ edatePlusOne, facility, dnow});
    }
//    Added by shweta
//     public  List<FacilityBillingTableModel.Facilityline> getMembersToBeBilled(String type, Date dnow, int no, String facility) throws BasicException {
//        //DATEDIFF(M.LBILLDATE,?)
//         
// return new StaticSentence(s, "SELECT M.ID,C.SEARCHKEY,C.NAME,F.RENEWALAMT,M.SDATE,M.LBILLDATE, "+type+" ,F.DUEPERIOD,C.ACCOUNT,M.MEMNO,CASE WHEN M.USERID IS NULL THEN NULL ELSE (SELECT DNAME FROM MEMDEPENDENT WHERE ID=M.USERID) END,M.USERID,C.MOBILE " + "FROM MEMFACILITYUSAGE M,FACILITY F ,CUSTOMERS C WHERE M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE AND C.ID=M.MEMNO AND C.VISIBLE=TRUE AND M.FACILITYTYPE=? AND M.LBILLDATE<? ORDER BY C.SEARCHKEY", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,  Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(FacilityBillingTableModel.Facilityline.class)).list(new Object[]{ edatePlusOne, facility, dnow});
//    }
//     

    public final List<AccountMasterExt> getaccountElements() throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.MAXIMUM AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='E' AND A.OPERANDS IS NULL AND A.PARENT IS NOT NULL ORDER BY A.NAME", null, new SerializerReadClass(AccountMasterExt.class)).list();
    }

    public final List<AccountMaster> getaccountElementswithoutParent() throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT FROM ACCOUNTMASTER A WHERE  A.LEVEL_='E' AND A.OPERANDS IS NULL AND A.PARENT IS NULL ORDER BY A.NAME", null, new SerializerReadClass(AccountMaster.class)).list();
    }
    

    public final List<AccountAudit> getaccountswithTB() throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT,COALESCE(T.CURDEBIT+T.OBDEBIT,0.0),COALESCE(T.CURCREDIT+T.OBCREDIT,0.0),A.ACTIVE,A.LEVEL_,A.SUMMARY FROM ACCOUNTMASTER A LEFT OUTER JOIN TRAILBALANCE T on a.id=t.accountid  ORDER BY A.SEARCHKEY,A.NAME", null, new SerializerReadClass(AccountAudit.class)).list();
    }

    public final List<AccountMaster> getaccountsElement(String accid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT FROM ACCOUNTMASTER A WHERE  A.LEVEL_='E' AND A.OPERANDS IS NULL AND A.PARENT =? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(accid);
    }

    public final List<Object> getMembersAccountIDsALL() throws BasicException {
        return new StaticSentence(s, "SELECT ACCOUNT FROM CUSTOMERS WHERE ACCOUNT IS NOT NULL", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
    }

    public final List<AccountMaster> getaccountsMainhead1(String accid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='C' AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(accid);
    }

    public final List<AccountMaster> getaccountsBreakdown1(String accid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='D' AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(accid);
    }

    public final List<AccountMaster> getSubaccounts1(String accid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='S' AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(accid);
    }

    public final List<AccountMasterExt> getaccountMainHeads(String elementid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.MAXIMUM AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='C' AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(elementid);
    }

    public final List<AccountMasterExt> getaccountMainHeads1(String elementid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN, A.MAXIMUM  AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='C' AND A.OPERANDS IS NULL AND A.SUMMARY=TRUE ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(elementid);
    }

    public final AccountMasterExt getaccountbysearchkey(String elementid) throws BasicException {
        return (AccountMasterExt) new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN, A.MAXIMUM  AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.SEARCHKEY=? AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).find(elementid);
    }

    public final AccountMasterExt getaccountbyid(String elementid) throws BasicException {
        return (AccountMasterExt) new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SIGN AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.ID=? AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).find(elementid);
    }
    //getaccountswithparent

    public final List<AccountMasterExt> getSubaccountswithparentid(String elementid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,MAXIMUM AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='S' ORDER BY A.SEARCHKEY", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(elementid);
    }

    public final List<AccountMasterExt> getBreakpointswithparentid(String elementid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,MAXIMUM AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='D' ORDER BY A.SEARCHKEY", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(elementid);
    }

    public final List<AccountMasterExt> getAccountswithparentid(String elementid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,MAXIMUM AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='C' ORDER BY A.SEARCHKEY", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(elementid);
    }

    public final AccountMasterExt getBreakdownsparentAcc(String elementid) throws BasicException {
        AccountMasterExt accmaster;
        do {
            accmaster = (AccountMasterExt) new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,(SELECT A1.MAXIMUM FROM ACCOUNTMASTER A1 WHERE A1.SEARCHKEY=A.PARENT) AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.SEARCHKEY=? AND A.OPERANDS IS NULL ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).find(elementid);
        //if(acc=null)
        } while (accmaster == null);
        return accmaster;
    }

    public final List<AccountMaster> getBankAccount() throws BasicException {
        List<AccountMaster> acclist = new ArrayList<AccountMaster>();
        List<AccountMaster> acclist2 = new StaticSentence(s, "SELECT A.ID,A.NAME ,A.SEARCHKEY,A.TYPE_ FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND (A.PARENT= '2.1.3' OR A.PARENT='1.1.2')  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list();
        List<AccountMaster> headinglist = new StaticSentence(s, "SELECT A.ID,A.NAME ,A.SEARCHKEY,A.TYPE_ FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND (A.PARENT='2.1.3' OR A.PARENT='1.1.2')  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list();
        acclist.addAll(acclist2);
        for (int j = 0; j < headinglist.size(); j++) {
            AccountMaster acch = headinglist.get(j);
            List<AccountMaster> acclist1 = new StaticSentence(s, "SELECT A.ID,A.NAME ,A.SEARCHKEY,A.TYPE_ FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.PARENT= ?  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(acch.getSerachkey());
            List<AccountMaster> headinglist1 = new StaticSentence(s, "SELECT A.ID,A.NAME ,A.SEARCHKEY,A.TYPE_ FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND A.PARENT=?  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(acch.getSerachkey());
            acclist.addAll(acclist1);
            headinglist.addAll(headinglist1);
        }
        // }
        return acclist;
    }

    public final List<AccountMasterExt> getaccountBreakpoints(String elementid) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN, A.MAXIMUM  AS MAX_VALUE,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE A.PARENT=? AND A.LEVEL_='D' ", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(elementid);
    }

    public final List<AccountMasterExt> getaccounts() throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  LEVEL_='S' AND ACTIVE=TRUE ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list();
    }
   public final List<Department1> getDepartment() throws BasicException {
        return new StaticSentence(s, "SELECT id,name,active FROM department d where Active=TRUE", null, new SerializerReadClass(Department1.class)).list();
    }
    public final List<AccountMasterExt> getSalesaccounts() throws BasicException {
        List<AccountMasterExt> acclist = new ArrayList<AccountMasterExt>();
        List<AccountMasterExt> acclistmain = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='C' AND A.PARENT='4.1'   ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list();
        for (int i = 0; i < acclistmain.size(); i++) {
            AccountMasterExt acc = acclistmain.get(i);
            List<AccountMasterExt> acclist2 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.PARENT= ?  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(acc.getSerachkey());
            List<AccountMasterExt> headinglist = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND A.PARENT=?  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(acc.getSerachkey());
            acclist.addAll(acclist2);
            for (int j = 0; j < headinglist.size(); j++) {
                AccountMasterExt acch = headinglist.get(j);
                List<AccountMasterExt> acclist1 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.PARENT= ?  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(acch.getSerachkey());
                List<AccountMasterExt> headinglist1 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND A.PARENT=?  ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(acch.getSerachkey());
                acclist.addAll(acclist1);
                headinglist.addAll(headinglist1);
            }
        }
        return acclist;
    }

    public final List<AccountMasterExt> getMainAccountAndAccountHeadings() throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  LEVEL_='C' OR LEVEL_='D' ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list();
    }

    public final List<AccountMasterExt> getMemberReceivableAccount() throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  ID='1'", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list();
    }

public final AccountMasterExt getMemberReceivableAccount1() throws BasicException {
        return (AccountMasterExt) new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  ID='1'", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).find();
    }
    public final String getaccountidByName(String name) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID FROM ACCOUNTMASTER A WHERE A.NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(name).toString();
    }

    public final String getAccountForTaxID(String id) throws BasicException {
        Object obj = new StaticSentence(s, "SELECT ACCOUNT FROM TAXCATEGORIES  WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(id);
        if (obj == null) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }

    public final String getparentaccountbysearchkey(String id) throws BasicException {
        String parent = null;
        while (parent == null) {
            Object[] obj = (Object[]) new StaticSentence(s, "SELECT A.PARENT ,(SELECT A1.SEARCHKEY FROM ACCOUNTMASTER A1 WHERE A1.SEARCHKEY=A.PARENT AND A1.LEVEL_='C') FROM ACCOUNTMASTER A WHERE A.SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(id);
            if (obj != null && obj[0] != null) {
                if (obj[0] != null) {
                    id = obj[0].toString();
                }
                if (obj[1] != null) {
                    parent = obj[1].toString();
                }
            } else {
                parent = "";
            }

        }
        return parent;
    }

    public final String getnewbillno(String id) throws BasicException {
        String bill = "";
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT BILLSEQUENCE,MAXBILLNO FROM FACILITY WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(id);
        if (obj != null) {
            bill = obj[0].toString() + (Integer.parseInt(obj[1].toString()) + 1);
        }
        return bill;
    }

    public final Double getOpeningBalance(String name) throws BasicException {
        Double amt = 0.0;
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENINGBALANCE FROM ACCOUNTMASTER WHERE NAME LIKE 'Cash in hand '+?+'%'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(name);
        if (obj != null) {
            amt = (Double) obj[0];
        }
        return amt;
    }

    public final Double getTokenIssuedTotal() throws BasicException {
        Double amt = 0.0;
        Object[] obj = (Object[]) new StaticSentence(s, "select sum(rate) from tokenstockdetail where issuetype='out' and closedayseq is null", null, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find();
        if (obj != null) {
            amt = (Double) obj[0];
        }
        return amt;
    }

    public final Double getOpeningBalanceToken() throws BasicException {
        Double amt = 0.0;
        Object[] obj = (Object[]) new StaticSentence(s, "select sum(rate) from tokenopeningbalance", null, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find();
        if (obj != null) {
            amt = (Double) obj[0];
        }
        return amt;
    }

    public final Double getTokenReturnedTotal() throws BasicException {
        Double amt = 0.0;
        Object[] obj = (Object[]) new StaticSentence(s, "select sum(rate) from tokenstockdetail where issuetype='in' and closedayseq is null", null, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find();
        if (obj != null) {
            amt = (Double) obj[0];
        }
        return amt;
    }

    public final Double getClubCollectionTotal() throws BasicException {
        Double amt = 0.0;
        Object[] obj = (Object[]) new StaticSentence(s, "select sum(g.clubcollection) from gamelog g,receipts r where g.paymentref=r.id and r.closecashseq is null", null, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find();
        if (obj != null) {
            amt = (Double) obj[0];
        }
        return amt;
    }

    public final String getnewbillno1(String id) throws BasicException {
        String bill = "";
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT BILLSEQUENCE,MAXNO FROM MINUSAGE WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(id);
        if (obj != null) {
            bill = obj[0].toString() + (Integer.parseInt(obj[1].toString()) + 1);
        }
        return bill;
    }

    public final String getNextBillno(String name) throws BasicException {
        String bill = "";
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SERIES,MAX_VALUE FROM BILLSEQUENCE WHERE NAME = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(name);
        if (obj != null) {
            bill = obj[0].toString() + (Integer.parseInt(obj[1].toString()) + 1);
        }
        return bill;
    }

    public final void updatebillno(String id) throws BasicException {
        new PreparedSentence(s, "UPDATE FACILITY SET MAXBILLNO=(MAXBILLNO + 1) WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{id});
    }

    public final void updatebillno1(String id) throws BasicException {
        new PreparedSentence(s, "UPDATE MINUSAGE SET MAXNO=(MAXNO + 1) WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{id});
    }

    public final void updateLastBilledDate(Date bdate, String id) throws BasicException {
        new PreparedSentence(s, "UPDATE MINUSAGE SET LASTBILLEDDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{bdate, id});
    }
    public final void updateLastBilledDateOfMember(Date bdate, String memno,String usagetype) throws BasicException {
        new PreparedSentence(s, "UPDATE MEMMINUSAGE SET LBILLDATE=? WHERE MEMNO=? AND USAGETYPE=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bdate, memno,usagetype});
    }

    public final void updateBillNum(String name) throws BasicException {
        new PreparedSentence(s, "UPDATE BILLSEQUENCE SET MAX_VALUE=(MAX_VALUE + 1) WHERE NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{name});
    }

    public final void updateCardsRoomGuestCat(String name, String value) throws BasicException {
        int count = new PreparedSentence(s, "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{value, name});
        if (count < 1) {
            new PreparedSentence(s, "INSERT INTO GENERALTABLE (ID,NAME,VALUE) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), name, value});
        }
    }
//Guest cat changes-start
//    public final List<GuestCategory> getGuestCategory() throws BasicException {
//        return new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT,MKBILL,TAXCAT , TAB FROM GUESTCAT WHERE ACTIVE=TRUE ORDER BY NAME", null, new SerializerReadClass(GuestCategory.class)).list();
//    }
    //ADDED BY LIPI-
    
     public final List<GuestCategory> getGuestCategory() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT,MKBILL,TAXCAT , TAB,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM GUESTCAT WHERE ACTIVE=TRUE ORDER BY NAME", null, new SerializerReadClass(GuestCategory.class)).list();
    }

    public final GuestCategory getGuestCategoryByID(String id) throws BasicException {
        return (GuestCategory) new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT FROM GUESTCAT WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(GuestCategory.class)).find(id);
    }
//public final List<GuestCategory> getGuestCategoryActive() throws BasicException {
//        return new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT,MKBILL,TAXCAT,TAB  FROM GUESTCAT WHERE ACTIVE = TRUE ORDER BY ACTIVE,NAME", null, new SerializerReadClass(GuestCategory.class)).list();
//    }
    
    //ADDED BY LIPI
    public final List<GuestCategory> getGuestCategoryActive() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT,MKBILL,TAXCAT,TAB,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2  FROM GUESTCAT WHERE ACTIVE = TRUE ORDER BY ACTIVE,NAME", null, new SerializerReadClass(GuestCategory.class)).list();
    }
//    public final List<GuestCategory> getGuestCategoryALL() throws BasicException {
//        return new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT,MKBILL,TAXCAT,TAB  FROM GUESTCAT  ORDER BY ACTIVE,NAME", null, new SerializerReadClass(GuestCategory.class)).list();
//    }
    
    //ADDED BY LIPI
     public final List<GuestCategory> getGuestCategoryALL() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,HSN_CODE,DAYS,MAXGUEST,RATE,ENTRANCECONTROL,RECEIPTSEQ,MRECEIPTSEQ,CREATEDBY,CRDATE,DEACTBY,DEACTDATE,ACCOUNT,MKBILL,TAXCAT,TAB,TAXCAT2,TAXCAT3,CASCADE1,CASCADE2,BASIC1,BASIC2  FROM GUESTCAT  ORDER BY ACTIVE,NAME", null, new SerializerReadClass(GuestCategory.class)).list();
    }
//Guest cat changes-end
    public final List<DebtTypeTableModel.DebtTypeline> getDebtType() throws BasicException {
        return new StaticSentence(s, "SELECT D.ID,D.NAME,D.PERIODTYPE,D.NUMBER,D.CREATEDBY " + "FROM DEBTTYPE D WHERE ACTIVE = TRUE ORDER BY NAME ", null, new SerializerReadClass(DebtTypeTableModel.DebtTypeline.class)).list();
    }

    public final DebtTypeTableModel.DebtTypeline getDebtTypebyid(String id) throws BasicException {
        return (DebtTypeTableModel.DebtTypeline) new StaticSentence(s, "SELECT ID,NAME,PERIODTYPE,NUMBER,CREATEDBY " + "FROM DEBTTYPE WHERE ACTIVE = TRUE AND ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(DebtTypeTableModel.DebtTypeline.class)).find(id);
    }

    public final void setmemberDebt(String mid, String facility, Double amt) {
        try {
            String id = UUID.randomUUID().toString();
            if (new PreparedSentence(s, "UPDATE MEMDEBTTABLE SET CURRENTDEBT=(CURRENTDEBT + ?) WHERE MEMID=? AND FACILITY=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{amt, mid, facility}) == 0) {
                Object[] value = new Object[]{id, mid, facility, amt};
                new PreparedSentence(s, "INSERT INTO MEMDEBTTABLE(ID,MEMID,FACILITY,CURRENTDEBT) VALUES (?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(value);
            }
        } catch (Exception e) {
        }
    }
    //added by pratima:to get the debt to add in msg
     public final Double getmemberDebt(String mid, String facility)  throws BasicException{
     Object[] totalDebt=(Object[]) new PreparedSentence(s, "SELECT CURRENTDEBT from MEMDEBTTABLE WHERE MEMID=? AND FACILITY=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING, Datas.STRING}),new SerializerReadBasic(new Datas[]{ Datas.DOUBLE})).find(new Object[]{ mid, facility}) ;
       return Double.parseDouble(totalDebt[0].toString());
     }
//ended by pratima

    public final boolean updateaccountjournal(String id, Date d, String rno, Double bamt, Boolean adjusted) throws BasicException {
        int cnt = 0;
        int cnt1 = 0;
        boolean flag = true;
        /*if( new PreparedSentence(s
        ,"UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=?  WHERE ID = ? AND PAYMENTREF IS NULL AND ADJUSTED=FALSE"
        ,new SerializerWriteBasic(new Datas[] {Datas.DOUBLE,Datas.BOOLEAN,Datas.TIMESTAMP,Datas.STRING,Datas.STRING})
        ).exec(new Object[]{bamt,adjusted,d,rno,id})<=0){
        cnt=  new PreparedSentence(s
        ,"UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND ADJUSTED=FALSE"
        ,new SerializerWriteBasic(new Datas[] {Datas.DOUBLE,Datas.BOOLEAN,Datas.TIMESTAMP,Datas.STRING,Datas.STRING})
        ).exec(new Object[]{bamt,adjusted,d,rno,id});
        if(cnt==0){
        flag=false;
        }
        }
        return flag;*/

        //this code is added to update AJUNADJUSTED line:706-787
        if (new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=?  WHERE ID = ? AND PAYMENTREF IS NULL AND ADJUSTED=FALSE", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bamt, adjusted, d, rno, id}) <= 0) {
            cnt = new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND ADJUSTED=FALSE", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bamt, adjusted, d, rno, id});
            if (cnt == 0) {
                flag = false;
            }
        }

        if (flag == false) {
            flag = true;
            if (new PreparedSentence(s, "UPDATE AJUNADJUSTED SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=?  WHERE ID = ? AND PAYMENTREF IS NULL AND ADJUSTED=FALSE", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bamt, adjusted, d, rno, id}) <= 0) {
                cnt1 = new PreparedSentence(s, "UPDATE AJUNADJUSTED SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND ADJUSTED=FALSE", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bamt, adjusted, d, rno, id});
                if (cnt1 == 0) {
                    flag = false;
                }
            }


            if (flag == true) {
                Object[] obj = (Object[]) new StaticSentence(s, "SELECT a.DATE FROM AJUNADJUSTED a WHERE a.ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.OBJECT})).find(id);
                //Object[] obj = (Object[]) new StaticSentence(s, "SELECT a.DATE FROM AJUNADJUSTED a WHERE a.ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);

                 System.out.println(obj[0].toString());
                Date d1 = (Date) obj[0];

                Calendar caldr = Calendar.getInstance();
                caldr.setTimeInMillis(d1.getTime());

                String name1 = "AJ_" + caldr.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK).toUpperCase() + caldr.get(Calendar.YEAR);

                int cnt2;
                if (new PreparedSentence(s, "UPDATE " + name1 + " SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=?  WHERE ID = ? AND PAYMENTREF IS NULL AND ADJUSTED=FALSE", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bamt, adjusted, d, rno, id}) <= 0) {
                    cnt2 = new PreparedSentence(s, "UPDATE " + name1 + " SET BALANCEAMOUNT = ?,ADJUSTED=?,CLEARDATE=?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND ADJUSTED=FALSE", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{bamt, adjusted, d, rno, id});
                    if (cnt2 == 0) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public final void updateaccountjournal1(String id, Double bamt, String rnum, Double payableamt) throws BasicException {
        int cnt = 0;
        int cnt1 = 0;
        boolean flag = true;

        if (new PreparedSentence(s, " UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT = ?,PAYMENTREF=? WHERE ID = ?  AND PAYMENTREF IS NULL AND BALANCEAMOUNT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{bamt, rnum, id, payableamt}) <= 0) {
            cnt = new PreparedSentence(s, " UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT = ?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND BALANCEAMOUNT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{bamt, rnum, id, payableamt});
            if (cnt <= 0) {
                flag = false;
            }
        }

        //this code is added to update AJUNADJUSTED and Monthly table:
        if (flag == false) {
            flag = true;
            if (new PreparedSentence(s, " UPDATE AJUNADJUSTED SET BALANCEAMOUNT = ?,PAYMENTREF=? WHERE ID = ?  AND PAYMENTREF IS NULL AND BALANCEAMOUNT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{bamt, rnum, id, payableamt}) <= 0) {
                cnt1 = new PreparedSentence(s, " UPDATE AJUNADJUSTED SET BALANCEAMOUNT = ?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND BALANCEAMOUNT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{bamt, rnum, id, payableamt});
                if (cnt1 <= 0) {
                    flag = false;
                }
            }

            if (flag == true) {
                Object[] obj = (Object[]) new StaticSentence(s, "SELECT DATE FROM AJUNADJUSTED WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(id);

                Date d1 = (Date) obj[0];

                Calendar caldr = Calendar.getInstance();
                caldr.setTimeInMillis(d1.getTime());

                String name1 = "AJ_" + caldr.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK).toUpperCase() + caldr.get(Calendar.YEAR);

                if (new PreparedSentence(s, " UPDATE " + name1 + " SET BALANCEAMOUNT = ?,PAYMENTREF=? WHERE ID = ?  AND PAYMENTREF IS NULL AND BALANCEAMOUNT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{bamt, rnum, id, payableamt}) <= 0) {
                    cnt1 = new PreparedSentence(s, " UPDATE " + name1 + " SET BALANCEAMOUNT = ?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID = ? AND BALANCEAMOUNT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{bamt, rnum, id, payableamt});
                    if (cnt1 <= 0) {
                        flag = false;
                    }
                }
            }
        }
    }

    public final void updateTrailBalance(Object[] value) throws BasicException {
//        System.out.println("praveen:"+value[0].toString()+"  "+value[1].toString()+"  "+value[2].toString()+"  "+value[3].toString());
        Object[] value1 = new Object[]{UUID.randomUUID().toString(), value[2], value[3], 0.0, 0.0, value[0], value[1]};
//       System.out.println("Account id "+value[2].toString());
        if (new PreparedSentence(s, "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?),CURCREDIT=(CURCREDIT+?) WHERE ACCOUNTID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{value[0], value[1], value[2]}) <= 0) {
            new PreparedSentence(s, "INSERT INTO TRAILBALANCE (ID,ACCOUNTID,OBDEBIT,OBCREDIT,CURDEBIT,CURCREDIT) VALUES(?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).exec(new Object[]{UUID.randomUUID().toString(), value[2], 0.0, 0.0, value[0], value[1]});
       
        }
        if (new PreparedSentence(s, "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?),CURCREDIT=(CURCREDIT+?) WHERE ACCOUNTID=? AND EDATE=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP})).exec(value) <= 0) {
            new PreparedSentence(s, "INSERT INTO AJPERIODTOTALS (ID,ACCOUNTID,EDATE,DEBIT,CREDIT,CURDEBIT,CURCREDIT) VALUES(?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).exec(value1);
        }
    }

    public final void insertintoaccjoutnal(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN})).exec(value);
        double debit = 0, credit = 0;
        if (value[4].toString().equals("D")) {
            debit = Double.parseDouble(value[7].toString());
        } else {
            credit = Double.parseDouble(value[7].toString());
        }
        Date d1 = (Date) value[3];
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        Object[] param = new Object[]{debit, credit, value[13], d};
        updateTrailBalance(param);
    }

    public final void updatetosendMsg(String msg, String memid, String mobile, int priority) throws BasicException {

        String id = UUID.randomUUID().toString();
        new PreparedSentence(s, "INSERT INTO AUTOMSG(ID,MESSAGE,SENTDATE,MEMID) VALUES (?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{id, msg, new Date(), memid});
        new PreparedSentence(s, "INSERT INTO activemsgtable(ID,Message,SENDTO,PRIORITY,CNT) VALUES (?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT})).exec(new Object[]{id, msg, mobile, priority, 0});
        //try {
//        try {
//            //ComputePi.setvalue(true);
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }
    }
    
    // INSERT  INTO ACTIVE MSG TABLE ..... # AAKASH
     public final void InsertActiveMsgTable(String msg, String memid, String mobile, int priority) throws BasicException {

        String id = UUID.randomUUID().toString();
       new PreparedSentence(s, "INSERT INTO activemsgtable(ID,Message,SENDTO,PRIORITY,CNT) VALUES (?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT})).exec(new Object[]{id, msg, mobile, priority, 0});
    
    }
     // get customer mobile number by id
     public final String getcustMobileNoByCustID(String CustId ) throws BasicException {
        Object val = new StaticSentence(s, " SELECT MOBILE FROM CUSTOMERS WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{CustId});
        String MobNo = null;
        if(val!=null){
            if(val.toString().trim().length()==10 || val.toString().trim().length()==13){
               MobNo = val.toString();
            }
            else{
                MobNo = null;
            }
        }
        return MobNo;
    }

    

    public final void insertintoaccjoutnal0(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,DATEOFENTRY,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(value);
        double debit = 0, credit = 0;
        if (value[4].toString().equals("D")) {
            debit = Double.parseDouble(value[7].toString());
        } else {
            credit = Double.parseDouble(value[7].toString());
        }
        Date d1 = (Date) value[3];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        System.out.println(d.toString());
        Object[] param = new Object[]{debit, credit, value[13], d};
        updateTrailBalance(param);
    }
//    public final void insertintoaccjoutnal01(Object[] value) throws BasicException {
//        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,DATEOFENTRY,ACTIVE,TAXCAT_2,TAXCAT_3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).exec(value);
//        double debit = 0, credit = 0;
//        if (value[4].toString().equals("D")) {
//            debit = Double.parseDouble(value[7].toString());
//        } else {
//            credit = Double.parseDouble(value[7].toString());
//        }
//        Date d1 = (Date) value[3];
//        System.out.println(d1.toString());
//        Date d = new Date();
//        d.setTime(d1.getTime());
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(d.getTime());
//        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//        cal.set(Calendar.HOUR_OF_DAY, 00);
//        cal.set(Calendar.MINUTE, 00);
//        cal.set(Calendar.SECOND, 00);
//        cal.set(Calendar.MILLISECOND, 00);
//        d.setTime(cal.getTimeInMillis());
////        System.out.println(d.toString());
//        Object[] param = new Object[]{debit, credit, value[13], d};
//        updateTrailBalance(param);
//    }

    public final void cc(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(value);
        double debit = 0, credit = 0;
        if (value[3].toString().equals("D")) {
            debit = Double.parseDouble(value[6].toString());
        } else {
            credit = Double.parseDouble(value[6].toString());
        }
        Date d1 = (Date) value[2];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        Object[] param = new Object[]{debit, credit, value[12], d};
        updateTrailBalance(param);
    }
 public final void insertintoaccjoutnal1(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(value);
        double debit = 0, credit = 0;
        if (value[3].toString().equals("D")) {
            debit = Double.parseDouble(value[6].toString());
        } else {
            credit = Double.parseDouble(value[6].toString());
        }
        Date d1 = (Date) value[2];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        Object[] param = new Object[]{debit, credit, value[12], d};
        updateTrailBalance(param);
    }
//  public final void insertintoaccjoutnal11(Object[] value) throws BasicException {
//        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,ACTIVE,TAXCAT_2,TAXCAT_3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).exec(value);
//        double debit = 0, credit = 0;
//        if (value[3].toString().equals("D")) {
//            debit = Double.parseDouble(value[6].toString());
//        } else {
//            credit = Double.parseDouble(value[6].toString());
//        }
//        Date d1 = (Date) value[2];
//        System.out.println(d1.toString());
//        Date d = new Date();
//        d.setTime(d1.getTime());
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(d.getTime());
//        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//        cal.set(Calendar.HOUR_OF_DAY, 00);
//        cal.set(Calendar.MINUTE, 00);
//        cal.set(Calendar.SECOND, 00);
//        cal.set(Calendar.MILLISECOND, 00);
//        d.setTime(cal.getTimeInMillis());
//        Object[] param = new Object[]{debit, credit, value[12], d};
//        updateTrailBalance(param);
//    }
    public final void insertintoaccjoutnal3(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(value);
        double debit = 0, credit = 0;
        if (value[3].toString().equals("D")) {
            debit = Double.parseDouble(value[6].toString());
        } else {
            credit = Double.parseDouble(value[6].toString());
        }
        Date d1 = (Date) value[2];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        System.out.println(d.toString());
        Object[] param = new Object[]{debit, credit, value[12], d};
        updateTrailBalance(param);
    }
//     public final void insertintoaccjoutnal31(Object[] value) throws BasicException {
//        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE,TAXCAT_2,TAXCAT_3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).exec(value);
//        double debit = 0, credit = 0;
//        if (value[3].toString().equals("D")) {
//            debit = Double.parseDouble(value[6].toString());
//        } else {
//            credit = Double.parseDouble(value[6].toString());
//        }
//        Date d1 = (Date) value[2];
////        System.out.println(d1.toString());
//        Date d = new Date();
//        d.setTime(d1.getTime());
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(d.getTime());
//        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//        cal.set(Calendar.HOUR_OF_DAY, 00);
//        cal.set(Calendar.MINUTE, 00);
//        cal.set(Calendar.SECOND, 00);
//        cal.set(Calendar.MILLISECOND, 00);
//        d.setTime(cal.getTimeInMillis());
////        System.out.println(d.toString());
//        Object[] param = new Object[]{debit, credit, value[12], d};
//        updateTrailBalance(param);
//    }

    public final void insertintoaccjoutnal6(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE,DEACTREF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING})).exec(value);
        double debit = 0, credit = 0;
        if (value[3].toString().equals("D")) {
            debit = Double.parseDouble(value[6].toString());
        } else {
            credit = Double.parseDouble(value[6].toString());
        }
        Date d1 = (Date) value[2];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        System.out.println(d.toString());
        Object[] param = new Object[]{debit, credit, value[12], d};
        updateTrailBalance(param);
    }

    public final void insertintoaccjoutnal4(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE,PAYMENTREF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING})).exec(value);
        double debit = 0, credit = 0;
        if (value[3].toString().equals("D")) {
            debit = Double.parseDouble(value[6].toString());
        } else {
            credit = Double.parseDouble(value[6].toString());
        }
        Date d1 = (Date) value[2];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        System.out.println(d.toString());
        Object[] param = new Object[]{debit, credit, value[12], d};
        updateTrailBalance(param);
    }

    public final void insertintoaccjoutnal5(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE,DEACTREF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING})).exec(value);
        double debit = 0, credit = 0;
        if (value[3].toString().equals("D")) {
            debit = Double.parseDouble(value[6].toString());
        } else {
            credit = Double.parseDouble(value[6].toString());
        }
        Date d1 = (Date) value[2];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        System.out.println(d.toString());
        Object[] param = new Object[]{debit, credit, value[12], d};
        updateTrailBalance(param);
    }

    public final void insertintoaccjoutnal3dup(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNALDUP(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(value);
    }

    public final void insertintoaccjoutnal4dup(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNALDUP(ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,PAYMENTREF,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.BOOLEAN})).exec(value);
    }

    public final void insertintoaccjoutnal2(Object[] value) throws BasicException {
        new PreparedSentence(s, "INSERT INTO ACCOUNTJOURNAL(ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,PAYMENTREF,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING, Datas.BOOLEAN})).exec(value);
        double debit = 0, credit = 0;
        if (value[4].toString().equals("D")) {
            debit = Double.parseDouble(value[7].toString());
        } else {
            credit = Double.parseDouble(value[7].toString());
        }
        Date d1 = (Date) value[3];
        System.out.println(d1.toString());
        Date d = new Date();
        d.setTime(d1.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        System.out.println(d.toString());
        Object[] param = new Object[]{debit, credit, value[13], d};
        updateTrailBalance(param);
    }

    public final void updatecredit(Double amt, Boolean flag, String id, String ref) throws BasicException {
        if ((new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT=?,ADJUSTED=?,PAYMENTREF=? WHERE ID=? AND ADJUSTED=FALSE AND PAYMENTREF IS  NULL", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).exec(new Object[]{amt, flag, ref, id})) <= 0) {
           new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT=?,ADJUSTED=?,PAYMENTREF=CONCAT(PAYMENTREF,' : ',?) WHERE ID=? AND ADJUSTED=FALSE AND PAYMENTREF IS NOT NULL", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).exec(new Object[]{amt, flag, ref, id});

            
          //new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET BALANCEAMOUNT=?,ADJUSTED=?,PAYMENTREF=concat_ws(' : ',paymentref,?) WHERE ID=? AND ADJUSTED=FALSE AND PAYMENTREF IS NOT NULL", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).exec(new Object[]{amt, flag, ref, id});
        }

    }

    public final void updateDebit(String ref, Double amt, String id) throws BasicException {
        // if(amt>0){
        if ((new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET PAYMENTREF=?,BALANCEAMOUNT=? WHERE ID=? AND PAYMENTREF IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{ref, amt, id})) <= 0) {
            new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET PAYMENTREF=CONCAT(PAYMENTREF,' : ',?),BALANCEAMOUNT=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{ref, amt, id});
        }
    //  }
    }

    public final void updateDebit1(String ref, Double amt, String id) throws BasicException {
        // if(amt>0){
        Date d = new Date();
        if ((new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET PAYMENTREF=?,BALANCEAMOUNT=?,CLEARDATE=?, ADJUSTED=TRUE WHERE ID=? AND PAYMENTREF IS NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{ref, amt, d, id})) <= 0) {
            new PreparedSentence(s, "UPDATE ACCOUNTJOURNAL SET PAYMENTREF=?,BALANCEAMOUNT=?,CLEARDATE=? , ADJUSTED=TRUE  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{ref, amt, d, id});
        }
    //  }
    }

    public final void updatechequeholder(String id, String holder) throws BasicException {
        new PreparedSentence(s, "UPDATE CHEQUE SET HOLDER=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{holder, id});
    }

    public final void updatechequeholder1(String id, String holder, String ajid) throws BasicException {
        new PreparedSentence(s, "UPDATE CHEQUE SET HOLDER=?,REF=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{holder, ajid, id});
    }

    public final AppUser getnewuserdetail(DataLogicSystem dlSystem, String id) throws BasicException {
         //warehouse changes -start
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE,CASHACCOUNT,CHEQUEACCOUNT,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).find(id);
        String warehouse = null;
        if (obj[11] != null) {
            String[] wArr = obj[11].toString().split("#");
            warehouse = wArr[0];
        }
            AppUser appuser = new AppUser(obj[0].toString(), obj[1].toString(), obj[4].toString(), obj[11].toString(), obj[12].toString(),warehouse);
            appuser.fillPermissions(dlSystem);
             //warehouse changes -end
            return appuser;
        }

    public

    final List<AccountMasterExt> getsubAccounts(String name) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.ACTIVE=TRUE AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(name + "%");
    }

    public final List<AccountMasterExt> getsubAccounts1(String name) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.SUMMARY=FALSE AND A.ACTIVE=TRUE AND  UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(name + "%");
    }

    public final List<AccountMaster> getsubAccountsExceptOB(String name) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.PARENT FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.SEARCHKEY !='1.1.7' AND A.ACTIVE=TRUE AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMaster.class)).list(name + "%");
    }

    public final List<AccountMasterExt> getsubAccountsExceptMemAccandCashAcc(String name) throws BasicException {
        return new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.ACTIVE=TRUE AND A.PARENT!='1.1.5' AND A.PARENT!='1.1.1' AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(name + "%");
    }

    public final List<AccountMasterExt> getCashAndBanksubAccounts(String name) throws BasicException {

        List<AccountMasterExt> acclist = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND (A.PARENT='1.1.1' OR A.PARENT='1.1.2' OR A.PARENT='2.1.3') AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(name + "%");
        List<AccountMasterExt> headinglist = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND (A.PARENT='1.1.1' OR A.PARENT='1.1.2' OR A.PARENT='2.1.3')  ORDER BY A.NAME", null, new SerializerReadClass(AccountMasterExt.class)).list();
        for (int i = 0; i < headinglist.size(); i++) {
            AccountMasterExt acch = headinglist.get(i);
            List<AccountMasterExt> acclist1 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.PARENT= ? AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(AccountMasterExt.class)).list(new Object[]{acch.getSerachkey(), name + "%"});
            List<AccountMasterExt> headinglist1 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND A.PARENT=? AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(AccountMasterExt.class)).list(new Object[]{acch.getSerachkey(), name + "%"});
            acclist.addAll(acclist1);
            headinglist.addAll(headinglist1);
        }
        return acclist;
    }

    public final List<AccountMasterExt> getsubAccountsExceptCashAndBank(String name) throws BasicException {
        List<AccountMasterExt> acclist = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND (A.PARENT !='1.1.1' AND A.PARENT !='1.1.2' AND A.PARENT != '2.1.3') AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(name + "%");
        List<AccountMasterExt> headinglist = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND (A.PARENT != '1.1.1' AND A.PARENT != '1.1.2' AND A.PARENT != '2.1.3') AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(AccountMasterExt.class)).list(name + "%");
        for (int i = 0; i < headinglist.size(); i++) {
            AccountMasterExt acch = headinglist.get(i);
            List<AccountMasterExt> acclist1 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.PARENT= ? AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(AccountMasterExt.class)).list(new Object[]{acch.getSerachkey(), name + "%"});
            List<AccountMasterExt> headinglist1 = new StaticSentence(s, "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE_,A.SIGN,A.SUMMARY,A.PARENT,A.EDITABLE FROM ACCOUNTMASTER A WHERE  A.LEVEL_='D' AND A.PARENT=? AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(AccountMasterExt.class)).list(new Object[]{acch.getSerachkey(), name + "%"});
            acclist.removeAll(acclist1);
            headinglist.addAll(headinglist1);

        }
        return acclist;
    }

    public final void updateTransNumber(String transref, Date d, int transno) throws BasicException {
        if (new StaticSentence(s, "UPDATE vouchersequence SET " + transref + "=? WHERE DATE=?", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.TIMESTAMP})).exec(new Object[]{transno, d}) <= 0) {
            int journal = 0, receipt = 0, contra = 0, payment = 0;
            if (transref.equals("Journal")) {
                journal = 1;
            } else if (transref.equals("Receipt")) {
                receipt = 1;
            } else if (transref.equals("Contra")) {
                contra = 1;
            } else if (transref.equals("Payments")) {
                payment = 1;
            }
            new StaticSentence(s, "INSERT INTO vouchersequence(date,journal,contra,payments,receipt) VALUES (?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.INT, Datas.INT, Datas.INT, Datas.INT})).exec(new Object[]{d, journal, contra, payment, receipt});

        }
    }

    public final String getnextTranscationNum(Date d, String transref) throws BasicException {
        Object val = new StaticSentence(s, "SELECT " + transref + " FROM vouchersequence  WHERE   DATE=? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), SerializerReadInteger.INSTANCE).find(new Object[]{d});
        int temp;
        if (val == null) {

            temp = 1;
        } else {
            temp = Integer.parseInt(val.toString()) + 1;

        }
        return Integer.toString(temp);
    }

    public final List<MemTypeTableModel.MemTypeline> getMemType() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,PERIOD,DATE,CREATEDBY,DEACTIVATEDDATE,DEACTIVATEDBY,DEBTMAX " + "FROM MEMTYPE  WHERE ACTIVE = TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(MemTypeTableModel.MemTypeline.class)).list();
    }
  //Lokesha  
//    public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeType() throws BasicException {
//        return new StaticSentence(s, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG " + "FROM NOTICEMASTER  WHERE FINALNOTICE = FALSE AND ACTIVATE = TRUE ORDER BY CRDATE", SerializerWriteString.INSTANCE, new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list();
//    }
 //Lokesha  
    //public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeListToDelete(Object [] obj) throws BasicException {
     //   return new StaticSentence(s, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE " + "FROM NOTICEMASTER  WHERE ID=? OR PARENTID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(obj);
   // }
    
    // Lokesha
    
//    public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeListToDelete1(Object [] obj) throws BasicException {
//        return new StaticSentence(s, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND PARENTID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(obj);
//    }
    
    //Lokesha
    
    //public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeListToDeleteForOtherThanFirstNotice(Object [] obj) throws BasicException {
       // return new StaticSentence(s, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE " + "FROM NOTICEMASTER  WHERE PARENTID=? and CRDATE>=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(obj);
    //}
    //Lokesha
    //public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeListToDeleteForOtherThanFirstNotice1(Object [] obj) throws BasicException {
     //   return new StaticSentence(s, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE " + "FROM NOTICEMASTER  WHERE PARENTID=? and CRDATE>?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list(obj);
    //}

    public final List<MemTypeTableModel.MemTypeline> getAllMemType() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,PERIOD,DATE,CREATEDBY,DEACTIVATEDDATE,DEACTIVATEDBY,DEBTMAX " + "FROM MEMTYPE  ", SerializerWriteString.INSTANCE, new SerializerReadClass(MemTypeTableModel.MemTypeline.class)).list();
    }

    public final List<MemType> getMemTypeBasic() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME " + "FROM MEMTYPE  WHERE ACTIVE = TRUE ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(MemType.class)).list();
    }

    public final List<MemLinkType> getMemLinkType() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME FROM MEMLINKTYPE WHERE ACTIVE = TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(MemLinkType.class)).list();
    }

    public final void UpdateMemberFacilityUsage(Object[] params) throws BasicException {
        new PreparedSentence(s, "UPDATE MEMFACILITYUSAGE SET LBILLDATE=?,NBILLDATE=?,BILLREF=? WHERE MEMNO=? AND FACILITYTYPE=? AND USERID=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(params);
    }

    public final void UpdateMemberFacilityUsage1(Object[] params) throws BasicException {
        new PreparedSentence(s, "UPDATE MEMFACILITYUSAGE SET LBILLDATE=?,NBILLDATE=?,BILLREF=? WHERE MEMNO=? AND FACILITYTYPE=? AND USERID IS NULL AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(params);
    }

    public final void DeactivateMemberFacility(Object[] params) throws BasicException {
        new PreparedSentence(s, "UPDATE MEMFACILITYUSAGE SET ACTIVE=FALSE WHERE MEMNO=? AND FACILITYTYPE=? AND USERID IS NULL AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(params);
    }

    public final String getCustomerAccountByID(String id) throws BasicException {
        return new StaticSentence(s, "SELECT ACCOUNT FROM CUSTOMERS WHERE ID = ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(id).toString();
    }

    public final Object[] getCustomerbyAccount(String id) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT NAME,SEARCHKEY FROM CUSTOMERS WHERE ACCOUNT = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(id);
    }

    public final List<Vendor> getVendorList() throws BasicException {
        return new StaticSentence(s, "SELECT ID,NAME,ACCOUNT FROM VENDOR WHERE ACTIVE =TRUE", null, new SerializerReadClass(Vendor.class)).list();
    }

    public final String getPurchasetransactionnum(Date sdate, Date edate) throws BasicException {
        String id;
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT MAX(TNO) FROM PURCHASEJOURNALMAIN WHERE CRDATE >= ? AND CRDATE <=? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{sdate, edate});
        if (obj == null || obj[0] == null) {
            id = "1";
        } else {

            id = String.valueOf(Long.parseLong(obj[0].toString()) + 1);
        }
        return id;
    }

    public final List getUsersCashAndChequeAccount() throws BasicException {
        return new StaticSentence(s, "SELECT CASHACCOUNT FROM PEOPLE  WHERE  CASHACCOUNT IS NOT NULL " +
                " UNION ALL SELECT CHEQUEACCOUNT FROM PEOPLE  WHERE  CHEQUEACCOUNT IS NOT NULL ", null, SerializerReadString.INSTANCE).list();
    }

    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return new Double(twoDForm.format(d)).doubleValue();
    }

    public String ConvertDoubleToString(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#0.00");
        return twoDForm.format(d);
    }

    public final Object getMemberTypebycid(String customerid) throws BasicException {
        return new StaticSentence(s, "SELECT MEMTYPE FROM CUSTOMERS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(customerid);
    }

    public final Object getPendingRequestCountForFacility(String facid) throws BasicException {
        return new StaticSentence(s, "SELECT COUNT(*) FROM MEMFACILITYUSAGE WHERE FACILITYTYPE=? AND STATUS_=? AND FACMANGREF IS NOT NULL", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT}), SerializerReadInteger.INSTANCE).find(new Object[]{facid, 0});
    }

    public TableDefinition getTableVendor() {
        return tvendor;
    }

    public final List<GameInfo> getActiveGamesList() throws BasicException {
        return new StaticSentence(s, "SELECT G.ID, G.NAME, G.PARENT,G.CCAMOUNT,G.MIN_,G.MAX_,G.CRDATE,G.CREATEDBY,G.GSEQ,G.SEQMAX,G.PRINTREF,G.ACTIVE,G.DEACTBY,G.DEACTDATE,CASE WHEN G.PARENT IS NULL THEN NULL ELSE (SELECT G1.NAME FROM GAMES G1 WHERE G1.ID=G.PARENT) END,NULL,0,G.TAXCAT,G.PERPLAYER,G.TOKENREF,G.GAMEAMOUNT FROM GAMES G  WHERE G.ACTIVE=TRUE", null, new SerializerReadClass(GameInfoExt.class)).list();
    }

    public final List<GameInfoExt> getALLGamesList() throws BasicException {
        return new StaticSentence(s, "SELECT G.ID, G.NAME, G.PARENT,G.CCAMOUNT,G.MIN_,G.MAX_,G.CRDATE,G.CREATEDBY,G.GSEQ,G.SEQMAX,G.PRINTREF,G.ACTIVE,G.DEACTBY,G.DEACTDATE,CASE when G.PARENT IS NULL THEN NULL ELSE (SELECT G1.NAME FROM GAMES G1 WHERE G1.ID=G.PARENT) END,NULL,0,G.TAXCAT,G.PERPLAYER,G.TOKENREF,G.GAMEAMOUNT FROM GAMES G  ORDER BY ACTIVE", null, new SerializerReadClass(GameInfoExt.class)).list();
    }

    public final Object getPaymentRef(String gameLogID) throws BasicException {
        return new StaticSentence(s, "SELECT PAYMENTREF FROM GAMELOG WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(gameLogID);
    }

    public final String getGameSeq(int gameType) throws BasicException {
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT G.GSEQ,G.SEQMAX FROM GAMES G WHERE G.ID=? ", SerializerWriteInteger.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.INT})).find(gameType);
        String seq = "";
        if (obj != null) {
            if (obj[0] != null) {
                seq = obj[0] + "";
            }
            if (obj[1] != null) {
                int val = Integer.parseInt(obj[1] + "") + 1;
                seq += val + "";
            } else {
                seq += 1;
            }
        }
        new PreparedSentence(s, "UPDATE GAMES SET SEQMAX=(SEQMAX+?) WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.INT})).exec(new Object[]{1, gameType});
        return seq;
    }

    public final List<CancelRequestInfo> getPendingCancelRequests() throws BasicException {
        return new StaticSentence(s, "SELECT GR.ID,GR.INITIATEDBY,GR.INITIATEDDATE,GL.ID,P.NAME,GL.NOP,GL.CLUBCOLLECTION,GL.GAMESEQ,G.NAME,C.NAME,C.SEARCHKEY FROM GAMECANCELREQUEST GR JOIN GAMELOG GL ON GR.GAMELOGID=GL.ID JOIN GAMES G ON GL.GAMEID=G.ID JOIN CUSTOMERS C  ON GL.JACKID=C.ID JOIN PLACES P ON GL.TABLES=P.ID WHERE GR.STATUS_='0' ORDER BY INITIATEDDATE,C.SEARCHKEY", null, new SerializerReadClass(CancelRequestInfo.class)).list();
    }

    public final boolean allowGameInstanceCancellationRequest(Object[] obj) throws BasicException {
        int cnt = new PreparedSentence(s, "UPDATE GAMECANCELREQUEST SET STATUS_='1' WHERE ID=? AND STATUS_='0'", SerializerWriteString.INSTANCE).exec(obj[0]);
        if (cnt > 0) {
            new PreparedSentence(s, "UPDATE GAMELOG SET CLUBCOLLECTION='0',REF=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(obj);
            return true;
        } else {
            return false;
        }
    }

    public final boolean rejectGameInstanceCancellationRequest(String id) throws BasicException {
        int cnt = new PreparedSentence(s, "UPDATE GAMECANCELREQUEST SET STATUS_='2' WHERE ID=? AND STATUS_='0'", SerializerWriteString.INSTANCE).exec(id);
        if (cnt > 0) {

            return true;
        } else {
            return false;
        }
    }

    public final boolean deleteGameInstanceCancellationRequest(String id) throws BasicException {
        int cnt = new PreparedSentence(s, "DELETE FROM GAMECANCELREQUEST WHERE ID=? AND STATUS_='0'", SerializerWriteString.INSTANCE).exec(id);
        if (cnt > 0) {

            return true;
        } else {
            return false;
        }
    }

    public final void updateGamesPayment(Object[] obj) throws BasicException {
        new PreparedSentence(s, "UPDATE GAMELOG SET PAYMENTREF=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(obj);
    }

    public final void updateGamesPlayercount(Object[] obj) throws BasicException {
        new PreparedSentence(s, "UPDATE GAMELOG SET NOP=NOP+? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING})).exec(obj);
    }

    public final void updateGamesPlayercountWithCC(Object[] obj) throws BasicException {
        new PreparedSentence(s, "UPDATE GAMELOG SET NOP=NOP+?,CLUBCOLLECTION=(NOP+?)*? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.INT, Datas.DOUBLE, Datas.STRING})).exec(obj);
    }

    public final List<GameInfoExt> getActiveParentGamesList() throws BasicException {
        return new StaticSentence(s, "SELECT G.ID, G.NAME, G.PARENT,G.CCAMOUNT,G.MIN_,G.MAX_,G.CRDATE,G.CREATEDBY,G.GSEQ,G.SEQMAX,G.PRINTREF,G.ACTIVE,G.DEACTBY,G.DEACTDATE,CASE when G.PARENT IS NULL THEN NULL ELSE (SELECT G1.NAME FROM GAMES G1 WHERE G1.ID=G.PARENT) END,NULL,0,G.TAXCAT,G.PERPLAYER,G.TOKENREF,G.GAMEAMOUNT FROM GAMES G WHERE ACTIVE=TRUE AND PARENT='-1'  ", null, new SerializerReadClass(GameInfoExt.class)).list();
    }

    public final GameInfoExt getGamesInfoByID(int id) throws BasicException {
        return (GameInfoExt) new StaticSentence(s, "SELECT G.ID, G.NAME, G.PARENT,G.CCAMOUNT,G.MIN_,G.MAX_,G.CRDATE,G.CREATEDBY,G.GSEQ,G.SEQMAX,G.PRINTREF,G.ACTIVE,G.DEACTBY,G.DEACTDATE,CASE when G.PARENT IS NULL THEN NULL ELSE (SELECT G1.NAME FROM GAMES G1 WHERE G1.ID=G.PARENT) END,IMAGE,0,G.TAXCAT,G.PERPLAYER,G.TOKENREF,G.GAMEAMOUNT FROM GAMES G  WHERE ID=?", SerializerWriteInteger.INSTANCE, new SerializerReadClass(GameInfoExt.class)).find(id);
    }

    public final List<PlayeresData> getPlayersFromGameLogID(String id) throws BasicException {
        return new StaticSentence(s, "SELECT C.ID,C.NAME,T.TOTALVALUE,C.SEARCHKEY,T.GUEST,T.ID FROM TOKENLOG T JOIN CUSTOMERS C ON T.MEMID=C.ID WHERE T.GAMEREF=? ORDER BY C.SEARCHKEY", SerializerWriteString.INSTANCE, new SerializerReadClass(PlayeresData.class)).list(id);
    }

    public final List<GameLog> getActiveGameLog() throws BasicException {
        return new StaticSentence(s, "SELECT G.ID,G.JACKID,G.GAMEID,G.NOP,G.PAYMENTREF,G.CRDATE,G.CRBY,C.NAME,C.SEARCHKEY,G1.NAME,P.ID,P.NAME,G.CLUBCOLLECTION,G.GAMESEQ FROM GAMELOG G JOIN CUSTOMERS C ON G.JACKID=C.ID JOIN GAMES G1 ON G.GAMEID=G1.ID JOIN PLACES P On P.ID=G.TABLES WHERE G.COMPLETEDON IS NULL  ", null, new SerializerReadClass(GameLog.class)).list();
    }

    public final List<GameLog> getActiveGameLog1(String id) throws BasicException {
        return new StaticSentence(s, "SELECT G.ID,G.JACKID,G.GAMEID,G.NOP,G.PAYMENTREF,G.CRDATE,G.CRBY,C.NAME,C.SEARCHKEY,G1.NAME,P.ID,P.NAME,G.CLUBCOLLECTION,G.GAMESEQ FROM GAMELOG G JOIN CUSTOMERS C ON G.JACKID=C.ID JOIN GAMES G1 ON G.GAMEID=G1.ID JOIN PLACES P On P.ID=G.TABLES WHERE G.COMPLETEDON IS NULL  AND C.ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(GameLog.class)).list(id);
    }

    public final List<GameLog> getGameLogByID() throws BasicException {
        return new StaticSentence(s, "SELECT G.ID,G.JACKID,G.GAMEID,G.NOP,G.PAYMENTREF,G.CRDATE,G.CRBY,C.NAME,C.SEARCHKEY,G1.NAME,P.ID,P.NAME,G.CLUBCOLLECTION,G.GAMESEQ FROM GAMELOG G JOIN CUSTOMERS C ON G.JACKID=C.ID JOIN GAMES G1 ON G.GAMEID=G1.ID JOIN PLACES P On P.ID=G.TABLES WHERE G.COMPLETEDON IS NULL ", null, new SerializerReadClass(GameLog.class)).list();
    }

    public final int checkForIssuedTokens(String gamelogid) throws BasicException {
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT COUNT(T.ID) FROM TOKENLOG T WHERE T.GAMEREF=? AND T.TOTALVALUE>0 ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(gamelogid);
        if (obj != null && obj[0] != null) {
            //if()
            return Integer.parseInt(obj[0] + "");
        } else {
            return 0;
        }

    }

    public final boolean checkForPendingCancelRequest(String gamelogid) {
        try {
            int cnt = (Integer) new StaticSentence(s, "SELECT COUNT(G.ID) FROM GAMECANCELREQUEST G WHERE G.GAMELOGID=? AND STATUS_='0'", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(gamelogid);
            if (cnt >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public final boolean checkForGameLog(String gamelogid) {
        try {
            //int cnt = (Integer) new StaticSentence(s, "SELECT COUNT(ID) FROM GAMELOG WHERE JACKID=? AND COMPLETEDON IS NULL"
            int cnt = (Integer) new StaticSentence(s, "SELECT COUNT(G.ID) FROM TOKENLOG T,GAMELOG G WHERE G.ID=T.GAMEREF AND T.MEMID=? AND G.COMPLETEDON IS NULL", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(gamelogid);
            if (cnt >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public final boolean checkForMembersAlreadyAdded(String id) {
        try {
            int cnt = (Integer) new StaticSentence(s, "SELECT COUNT(G.ID) FROM TOKENLOG T,GAMELOG G WHERE G.ID=T.GAMEREF AND T.MEMID=? AND G.COMPLETEDON IS NULL", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(id);
            if (cnt >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public final void addCancelRequest(Object[] obj) throws BasicException {
        //in status 0: pending request 1: approved 2:rejected
        new PreparedSentence(s, "INSERT INTO GAMECANCELREQUEST (ID,GAMELOGID,INITIATEDBY,INITIATEDDATE,STATUS_) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.INT})).exec(obj);

    }

    public final void deleteGameLog(String id) throws BasicException {
        new StaticSentence(s, "DELETE FROM GAMELOG WHERE ID=? ", SerializerWriteString.INSTANCE).exec(id);
    }

    public final void deleteTokenLog(String id) throws BasicException {
        new StaticSentence(s, "DELETE FROM TOKENLOG WHERE ID=? ", SerializerWriteString.INSTANCE).exec(id);
    }

    public final List<GameInfoExt> getGamesWithParent(int id) throws BasicException {
        return new StaticSentence(s, "SELECT G.ID, G.NAME, G.PARENT,G.CCAMOUNT,G.MIN_,G.MAX_,G.CRDATE,G.CREATEDBY,G.GSEQ,G.SEQMAX,G.PRINTREF,G.ACTIVE,G.DEACTBY,G.DEACTDATE,CASE when G.PARENT IS NULL THEN NULL ELSE (SELECT G1.NAME FROM GAMES G1 WHERE G1.ID=G.PARENT) END,G.IMAGE,COUNT(G1.ID),G.TAXCAT,G.PERPLAYER,G.TOKENREF,G.GAMEAMOUNT FROM GAMES G LEFT OUTER JOIN GAMES G1 ON G.ID=G1.PARENT  WHERE G.PARENT=? AND G.ACTIVE=TRUE   GROUP BY G.ID, G.NAME, G.PARENT,G.CCAMOUNT,G.MIN_,G.MAX_,G.CRDATE,G.CREATEDBY,G.GSEQ,G.SEQMAX,G.PRINTREF,G.ACTIVE,G.IMAGE,G.DEACTBY,G.DEACTDATE,G.TAXCAT,G.PERPLAYER,G.TOKENREF,G.GAMEAMOUNT,G.IMAGE", SerializerWriteInteger.INSTANCE, new SerializerReadClass(GameInfoExt.class)).list(id);
    }

    public final void insertNewGame(Object[] obj) throws BasicException {
        new PreparedSentence(s, "INSERT INTO GAMES ( NAME, PARENT,CCAMOUNT,MIN_,MAX_,CRDATE,CREATEDBY,GSEQ,SEQMAX,PRINTREF,ACTIVE,IMAGE,TAXCAT,PERPLAYER,GAMEAMOUNT,TOKENREF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.INT, Datas.INT, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.BOOLEAN, Datas.IMAGE, Datas.STRING, Datas.BOOLEAN, Datas.DOUBLE, Datas.STRING})).exec(obj);
    }

    public final void insertGameInstance(Object[] obj) throws BasicException {
        new PreparedSentence(s, "INSERT INTO GAMELOG ( ID,JACKID,GAMEID,NOP,PAYMENTREF,CRDATE,CRBY,TABLE,CLUBCOLLECTION) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(obj);
    }

    public final GameLog getGameInstanceByID(String id) throws BasicException {
        return (GameLog) new PreparedSentence(s, "SELECT G.ID,G.JACKID,G.GAMEID,G.NOP,G.PAYMENTREF,G.CRDATE,G.CRBY,C.NAME,C.SEARCHKEY,G1.NAME,P.ID,P.NAME,G.CLUBCOLLECTION,G.GAMESEQ FROM GAMELOG G JOIN CUSTOMERS C ON G.JACKID=C.ID JOIN GAMES G1 ON G.GAMEID=G1.ID JOIN PLACES P On P.ID=G.TABLES WHERE G.ID=? AND G.COMPLETEDON IS NULL ", SerializerWriteString.INSTANCE, new SerializerReadClass(GameLog.class)).find(id);
    }

    public final void insertNewGameInstance(Object[] obj) throws BasicException {
        //gameInstanceID,crdate,crby,1,gamelog.getJackID(),selectedGame.getClubCollection(),pinfo.getID(),gameSeq
        new PreparedSentence(s, "INSERT INTO GAMELOG ( ID,CRDATE,CRBY,NOP,JACKID,GAMEID,CLUBCOLLECTION,TABLES,GAMESEQ) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.INT, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(obj);
    }

    public final void updateGameInstance(Object[] obj) throws BasicException {
        new PreparedSentence(s, "UPDATE GAMELOG SET NOP=?,TABLE=?,PAYMENTREF=?,GAMEID=?,CLUBCOLLECTION=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.STRING})).exec(obj);
    }

    public final void updateGamePrintref(Object[] obj) throws BasicException {
        new PreparedSentence(s, "UPDATE GAMELOG SET PAYMENTREF=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(obj);
    }

    public final void setGameInstanceAsCompleted(Object[] obj) throws BasicException {
        new PreparedSentence(s, "UPDATE GAMELOG SET COMPLETEDON=? WHERE Id=? AND COMPLETEDON IS NULL ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(obj);
    }

    public final void deactivateGame(Object[] obj) throws BasicException {
        new PreparedSentence(s, " UPDATE GAMES SET ACTIVE=FALSE,DEACTBY=?,DEACTDATE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.INT})).exec(obj);
    }

    public final void insertTokenCombination(Object[] obj) throws BasicException {
        new PreparedSentence(s, " INSERT INTO TOKENCOMBINATION(ID,NAME,TOTALVAL,CREATEDBY,CRDATE,ACTIVE,IMAGE) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.IMAGE})).exec(obj);
    }

    public final List<TokenCombinationInfo> getTokenCombination() throws BasicException {
        return new PreparedSentence(s, " SELECT ID,NAME,TOTALVAL FROM TOKENCOMBINATION WHERE ACTIVE=TRUE ", null, new SerializerReadClass(TokenCombinationInfo.class)).list();
    }

    public final void insertTokenCombinationDetail(Object[] obj) throws BasicException {
        new PreparedSentence(s, " INSERT INTO TOKENCOMBINATIONDETAIL(ID,PDTID,QTY,PARENT) VALUES (?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING})).exec(obj);
    }

    public final List<Object[]> getTokenCombinationDetail() throws BasicException {
        return new PreparedSentence(s, " SELECT T.ID,T.NAME,T.TOTALVAL,TD.ID,TD.PDTID,P.NAME,TD.QTY,P.PRICESELL,TD.PARENT FROM TOKENCOMBINATION T " +
                " JOIN TOKENCOMBINATIONDETAIL TD ON T.ID=TD.PARENT JOIN PRODUCTS P ON TD.PDTID=P.ID WHERE ACTIVE=TRUE ORDER BY T.NAME,P.PRICESELL ", null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.STRING})).list();
    }

    public final List<Object[]> getTokenCombinationDetailByTokenLogID(String tokenLogID) throws BasicException {
        return new PreparedSentence(s, " SELECT TL.ID,CONCAT(?,TD.LINE) as TLINE,TD.RATE*TD.QTY,TD.ID,TD.PDTID,P.NAME,TD.QTY,TD.RATE,TD.TLID,TD.LINE FROM TOKENLOG TL " +
                " JOIN TOKENLOGDETAIL TD ON TL.ID=TD.TLID JOIN PRODUCTS P ON TD.PDTID=P.ID WHERE TL.ID=?   ORDER BY TD.LINE,TD.RATE ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.STRING, Datas.STRING})).list(new Object[]{"Token ", tokenLogID});
    }

    public final void insertIntoTokenLog(Object[] obj) throws BasicException {
        //type indicates issue or return
        new PreparedSentence(s, " INSERT INTO TOKENLOG(ID,MEMID,TOTALVALUE,CRDATE,CREATEDBY,GAMEREF,TYPE_,GUEST) VALUES (?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING})).exec(obj);
    }

    public final void doesJackExist(Object[] obj) throws BasicException {
        //type indicates issue or return
        new PreparedSentence(s, " SELECT COUNT(*) FROM TOKENLOG WHERE GAMEREF=? AND MEMID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.INT})).exec(obj);
    }

    public final void updateTokenLog(Object[] obj) throws BasicException {
        new PreparedSentence(s, " UPDATE TOKENLOG SET TOTALVALUE=(TOTALVALUE+?) WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(obj);
    }

    public final void insertIntoTokenLogDetail(Object[] obj) throws BasicException {
        new PreparedSentence(s, " INSERT INTO TOKENLOGDETAIL(ID,TLID,PDTID,RATE,QTY,LINE) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.INT, Datas.INT})).exec(obj);
    }

    public final void insertIntoTokenStockDetail(Object[] obj) throws BasicException {
        new PreparedSentence(s, " INSERT INTO TOKENSTOCKDETAIL(ID,PDID,RATE,QTY,ISSUETYPE) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.INT, Datas.STRING})).exec(obj);
    }

    public final List<CancelRequestInfo> LoadPendingGameCancellationRequest() throws BasicException {
        return new PreparedSentence(s, " SELECT ID,GAMEID,INITIATEDBY,INITIATEDDATE,PROCESSEDBY,PROCESSEDDATE,STATUS FROM GAMECANCELREQUEST WHERE STATUS_='0'", null, new SerializerReadClass(CancelRequestInfo.class)).list();
    }

    public final List<String> getGuestDetail(List<String> list, String memid) throws BasicException {
        Date sdate = new Date();
        Date edate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(sdate.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        sdate.setTime(cal.getTimeInMillis());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 00);
        edate.setTime(cal.getTimeInMillis());
        String condition = null;
        List<String> list1 = new ArrayList<String>();
        Object[] obj = new Object[list.size() + 3];
        Datas[] data = new Datas[list.size() + 3];
        int i = 3;
        obj[0] = sdate;
        obj[1] = edate;
        obj[2] = memid;
        data[0] = Datas.TIMESTAMP;
        data[1] = Datas.TIMESTAMP;
        data[2] = Datas.STRING;
        for (String line : list) {
            obj[i] = line;
            data[i] = Datas.STRING;
            if (condition == null) {
                condition = " ( guestcat=? ";
            } else {
                condition += " or guestcat=? ";
            }
            i++;
        }
        if (list.size() > 0) {
            condition += " )";
        }
        if (condition == null) {
            list = new PreparedSentence(s, "SELECT NAMES FROM GUESTLOG WHERE DATE>? AND DATE <= ? AND MEMNO=? ", new SerializerWriteBasic(data), SerializerReadString.INSTANCE).list(obj);
        } else {
            list = new PreparedSentence(s, "SELECT NAMES FROM GUESTLOG WHERE DATE>? AND DATE <= ? AND MEMNO=? AND " + condition, new SerializerWriteBasic(data), SerializerReadString.INSTANCE).list(obj);
        }
        for (String s1 : list) {
            String[] arr = s1.split(":");
            for (int j = 0; j < arr.length; j++) {
                list1.add(arr[j]);
            }
        }
        return list1;

    }

   
    
    public final Object[] getMemberByCard1(String card) throws BasicException{
        return (Object[]) new StaticSentence(s, "SELECT C.ID,C.SEARCHKEY,C.NAME,C.MEMTYPE,C.MOBILE, C.ACCOUNT FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND (M.CARD=? OR C.CARD=?) AND C.VISIBLE = TRUE",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING})).find(new Object[]{card, card});
    }
    
    
    public final Object[] getMamberbycard1(String card) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT ID,SEARCHKEY,NAME,MEMTYPE,MOBILE,ACCOUNT,NOTES,CARD FROM CUSTOMERS WHERE CARD = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING})).find(card);
    }
    
}
