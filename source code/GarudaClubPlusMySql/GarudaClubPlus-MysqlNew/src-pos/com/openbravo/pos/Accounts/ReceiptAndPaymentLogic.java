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
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.TreeTableModel;
import java.sql.SQLException;
import java.util.Date;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
/**
 *
 * @author swathi
 */
public class ReceiptAndPaymentLogic {
   private Date sdate;
   private Date edate;
   private Date edateincr;
   private Date sdateincr;
   private AppView m_App;
   private List<Account> OBacclist;
   private List<Account> CLacclist;
   private List<Account> dcAcclist;
   private Account mainele;
   private Account maineledetail;
  // private Map<String ,Account> mapdetail;
   private Map<String ,Account> mapcondensed;
   private Map<String,Account> map1;
   private final static String[] HEADERS = {"Account","Amount"};
    private boolean zeroIgnoreFlag=false;//pratima
    public ReceiptAndPaymentLogic(Date sdate, Date edate,AppView app) {
        this.sdate = sdate;
        this.edate = edate;
        edateincr=new Date();
        sdateincr=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(edate.getTime());
        cal.add(Calendar.DATE, 1);
        edateincr.setTime(cal.getTimeInMillis());
        cal.setTimeInMillis(sdate.getTime());
        cal.add(Calendar.DATE, 1);
        sdateincr.setTime(cal.getTimeInMillis());
        m_App=app;
     //   mapdetail=new HashMap<String, Account>();
        mapcondensed=new HashMap<String, Account>();
        map1=new HashMap<String, Account>();
        OBacclist=new ArrayList<Account>();
        CLacclist=new ArrayList<Account>();
        dcAcclist=new ArrayList<Account>();
    }

    public void getOpeningAndClosingBalance() throws BasicException{
      OBacclist=new PreparedSentence(m_App.getSession(), "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0),COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) FROM ACCOUNTMASTER AM JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID WHERE (AM.PARENT='1.1.1.2' OR AM.parent='1.1.2' or am.parent='2.1.3' ) AND AJ.EDATE<? GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),new SerializerReadClass(Account.class) ).list(new Object[]{sdateincr});
      CLacclist=new PreparedSentence(m_App.getSession(), "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0),COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) FROM ACCOUNTMASTER AM LEFT JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID  WHERE (AM.PARENT='1.1.1.2' OR AM.parent='1.1.2' or am.parent='2.1.3' ) AND AJ.EDATE<?   GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),new SerializerReadClass(Account.class) ).list(new Object[]{edateincr});
    }
    /*
     *  public List loadReport(Calendar rscal,Calendar recal,Calendar fscal,Calendar fecal,String accid,List<String> list) throws BasicException{

        List<AccountsList> datalist=new ArrayList<AccountsList>();
        //Calendar rscalorg=new Date(rscal.getTimeInMillis());
       // Date redate=new Date(recal.getTimeInMillis());
      //  Date fsdate=new Date(fscal.getTimeInMillis());
      //  Date fedate=new Date(fecal.getTimeInMillis());
        List<Date> values=new ArrayList<Date>();
        String sentence=null;
        if(rscal.before(fscal)){
        while(rscal.before(fscal) && rscal.before(recal)){
            String name="AJ_"+rscal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+rscal.get(Calendar.YEAR);
            boolean flag=list.contains(name);
            if(flag){
            String  sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby"+
                   "from "+name+" a join "+name+" a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid  where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true ";
            if(sentence== null)
                sentence=sent;
            else
                sentence=sentence+" union all "+sent;
           // values.add(accid);
            Date d=new Date(rscal.getTimeInMillis());
            values.add(d);

            }
             rscal.set(Calendar.DATE, rscal.getActualMaximum(Calendar.DATE));
            if(!rscal.before(fscal)  ){
                rscal.set(Calendar.DATE, fscal.get(Calendar.DATE));
            }
            if(!rscal.before(recal) ){
                rscal.set(Calendar.DATE, recal.get(Calendar.DATE));
            }
            Date d=new Date(rscal.getTimeInMillis());
            if(flag)
            values.add(d);
            rscal.add(Calendar.DATE, 1);
        }
        //case 1
        if(!recal.after(fscal)){//report end date is before or equals financial year start date
        }//case 2
        else if(recal.after(fscal) && !recal.after(fecal)){
            //report end date is between financial year start date and financial years end date
            String  sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from accountjournal a join accountjournal a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true ";
                 if(sentence== null)
               sentence=sent;
           else
               sentence=sentence+" union all "+sent;
           Date d=new Date(fscal.getTimeInMillis());

           values.add(d);
            d=new Date(recal.getTimeInMillis());
           values.add(d);

        }//case 3
        else if(rscal.before(fecal)){
           //report end date is after financial years end date
             String  sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from accountjournal a join accountjournal a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true ";
                 if(sentence== null)
               sentence=sent;
           else
               sentence=sentence+" union all "+sent;
            Date d=new Date(fscal.getTimeInMillis());
           values.add(d);
            d=new Date(fecal.getTimeInMillis());
           values.add(d);
           while(fecal.before(recal)){
               String name="AJ_"+fecal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+fecal.get(Calendar.YEAR);
              boolean flag=list.contains(name);
            if(flag){
               String sent1="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from "+name+" a join "+name+" a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype  join accountmaster am on am.id=a1.accountid  where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true  order by a.date,a.tid";
                 if(sentence== null)
                sentence=sent1;
            else
                sentence=sentence+" union all "+sent1;
            //values.add(accid);
              d=new Date(fecal.getTimeInMillis());
            values.add(d);

            }
              fecal.set(Calendar.DATE, fecal.getActualMaximum(Calendar.DATE));
            if(!fecal.before(recal)  ){
                fecal.set(Calendar.DATE, recal.get(Calendar.DATE));
            }

            d=new Date(fecal.getTimeInMillis());
            if(flag)
            values.add(d);
            fecal.add(Calendar.DATE, 1);
           }
        }
    }else {
            if(!recal.after(fecal) && !rscal.before(fscal)){//if requested period is in between fy start date and fy end date
                  String  sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from accountjournal a join accountjournal a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid  where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true  ";
                 if(sentence== null)
                     sentence=sent;
                 else
                     sentence=sentence+" union all "+sent;
                 Date d=new Date(rscal.getTimeInMillis());
                 values.add(d);
                  d=new Date(recal.getTimeInMillis());
                 values.add(d);
            }else if(rscal.before(fecal) && !recal.before(fecal)){
                  String  sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from accountjournal a join accountjournal a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid  where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true  ";
                 if(sentence== null)
                     sentence=sent;
                 else
                     sentence=sentence+" union all "+sent;
                 Date d=new Date(rscal.getTimeInMillis());
                 values.add(d);
                  d=new Date(fecal.getTimeInMillis());
                 values.add(d);
                 while(!recal.before(fecal)){
                      String name="AJ_"+fecal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+fecal.get(Calendar.YEAR);
                       boolean flag=list.contains(name);
                  if(flag){
                      sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from "+name+" a join "+name+" a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true ";
                 if(sentence== null)
                     sentence=sent;
                 else
                     sentence=sentence+" union all "+sent;
                  d=new Date(fecal.getTimeInMillis());
                 values.add(d);

                  }
                        fecal.set(Calendar.DATE, fecal.getActualMaximum(Calendar.DATE));
                 if(!fecal.before(recal))
                     fecal.set(Calendar.DATE, recal.get(Calendar.DATE));
                  d=new Date(fecal.getTimeInMillis());
                 if(flag)
                 values.add(d);
                 fecal.add(Calendar.DATE, 1);
                 }
            }else if(!rscal.before(fecal)){
                while(!rscal.after(recal)){
                   String name="AJ_"+rscal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+rscal.get(Calendar.YEAR);
                    boolean flag=list.contains(name);
            if(flag){
                   String  sent="select  a.tid as tid, "+
                  " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id ,am.name as name,a.paymentref as pref,a.createdby as cby "+
                   "from "+name+" a join "+name+" a1 on  a.tid=a1.tid and a1.active=true and a.accountid != a1.accountid  and a.transtype!=a1.transtype join accountmaster am on am.id=a1.accountid  where a.accountid=? and a.date>=? and a.date<=? and a.active=true and a1.active=true ";
                 if(sentence== null)
                     sentence=sent;
                 else
                     sentence=sentence+" union all "+sent;
                 Date d =new Date(rscal.getTimeInMillis());
                 values.add(d);

            }
                    rscal.set(Calendar.DATE, rscal.getActualMaximum(Calendar.DATE));
                 if(!rscal.before(recal))
                     rscal.set(Calendar.DATE, recal.get(Calendar.DATE));
                 Date d=new Date(rscal.getTimeInMillis());
                 if(flag)
                 values.add(d);
                 rscal.add(Calendar.DATE, 1);
                 }
            }


    }
        Object[] params=new Object[values.size()+values.size()/2];
        Datas[] data=new Datas[values.size()+values.size()/2];
        int k=0;
        for(int i=0;i<values.size();i++){
            data[k]=Datas.STRING;
            params[k++]=accid;
            data[k]=Datas.TIMESTAMP;
            params[k++]=values.get(i++);
            data[k]=Datas.TIMESTAMP;
            params[k++]=values.get(i);
        }
        List<AccountsList> ledgerlist=new StaticSentence(m_app.getSession()
                  ,"SELECT TID,DATE,AMOUNT,TRANSTYPE,TRANSNO,TRANSREF,NARRATION,TRANSTYPE1,ID,NAME,pref,cby FROM ( "
                   +sentence+") ORDER BY 2,1"
                  , new SerializerWriteBasic(data)
                  , new SerializerReadClass(AccountsList.class)).list(params);
         int i=0,j=0,s=0;
        int size=ledgerlist.size();
        while(i<size){
            AccountsList line=ledgerlist.get(i);
            if(i+1!=size){
                s=1;
             int flag=0;
            for(j=i+1;j<size;j++){
              AccountsList line1=ledgerlist.get(j);
              if(!line.getTid().equals(line1.getTid()) && flag==0 ){
                  datalist.add(line);
                  flag=1;
                  i++;
                  break;
              }else if(line.getTid().equals(line1.getTid()) && flag==0 && !line.getID().equals(line1.getID())){
                  datalist.add(line);
                  flag=1;
                  i++;
                 // break;
                  //if(line.getTid().equals(line1.getTid()) && flag==1 && !line.getID().equals(line1.getID()))
              }else {
                  i++;
                  break;
              }
            
            }
            }else{
               if(ledgerlist.size()>0 && s==0){
                 datalist.add(line);
                 i++;
               }else{
                   AccountsList line1=ledgerlist.get(i-1);
                  if(!line.getTid().equals(line1.getTid())){
                     datalist.add(line);
                     i++;
                     break;
                 }else if(line.getTid().equals(line1.getTid()) && line.getID().equals(line1.getID())){
                  datalist.add(line);
                  i++;
                 }else {
                  i++;
                  break;
                 }
               }
            }
       }
        return datalist;
    }
     * */
    public void getCreditAndDebitList() throws BasicException, SQLException{
        Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        GeneralSettingInfo sinfo=gs.get("Datestart");
        GeneralSettingInfo einfo=gs.get("Dateend");
        Date fsdate=(Date)Formats.DATE.parseValue(sinfo.getValue());
        Date fedate=(Date)Formats.DATE.parseValue(einfo.getValue());
        Calendar fscal=Calendar.getInstance();//financial year sdate
        fscal.setTime(fsdate);
        Calendar fecal=Calendar.getInstance();//financial year edate
        fecal.setTime(fedate);
        Calendar scal=Calendar.getInstance();
        Calendar ecal=Calendar.getInstance();
        scal.setTimeInMillis(sdate.getTime());
        ecal.setTimeInMillis(edate.getTime());
        List<String> sentList=new ArrayList<String>();
        String barsent=null;
        //List<Datas[]> sentListdata=new ArrayList<Datas[]>();
        List<Object[]> sentListarg=new ArrayList<Object[]>();
        List<Date> barArgList=new ArrayList<Date>();
        //String statement=null;
        //scal.set(Calendar.DATE, scal.getActualMaximum(Calendar.DATE));
            List values=new ArrayList();
        String sentence=null;
        List<String> list=new ArrayList<String>();
          ResultSet rs=m_App.getSession().getConnection().getMetaData().getTables(null,null, "AJ%", null);
          while(rs.next()){
             list.add(rs.getString("TABLE_NAME"));
          }
          
         DropCreateAccountJournal();
         
         
        if(scal.before(fscal)){
        while(scal.before(fscal) && scal.before(ecal)){
            String name="AJ_"+scal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+scal.get(Calendar.YEAR);
            boolean flag=list.contains(name);//checks if the table exist
            if(flag){//if table is present
                //select am.name,am.id,sum(a.amount),a.transtype from accountjournal a join accountmaster am on am.id=a.accountid where a.tid in (select a.tid from accountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1' or am.parent='1.1.2') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') group by am.id,am.name,a.transtype
                String sent=    "insert into rptable1 (name,accid,amount,type_)  select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  "+name+" a join "+name+" a1 on  a.tid=a1.tid and a1.active=true  and a.id != a1.id  join accountmaster am on am.id=a1.accountid where a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";
                if(barsent==null)
                   barsent=" SELECT SUM(AMOUNT) as amt FROM "+name+" where transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true  ";
                else
                    barsent+=" union all SELECT SUM(AMOUNT) as amt FROM "+name+" where transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
                sentList.add(sent);
                sentListarg.add(new Object[]{"acc"});
            }
            scal.add(Calendar.MONTH, 1);
            scal.set(Calendar.DATE, scal.getActualMinimum(Calendar.DATE));
        }
        //case 1
        if(!ecal.after(fscal)){//report end date is before or equals financial year start date
        }//case 2
        else if(ecal.after(fscal) && !ecal.after(fecal)){
            //report end date is between financial year start date and financial years end date
             String sent=    "insert into rptable1 (name,accid,amount,type_)  select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  rpaccountjournal a   join accountmaster am on am.id=a.accountid  where   a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where a.date>=? and a.date< ? and a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2'  and am.parent!='2.1.3'  and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";
           
           Date d=new Date(fscal.getTimeInMillis());
          // values.add(d);
           Calendar tempcal=Calendar.getInstance();//incr by 1 day,to ensure all entries are considered
           tempcal.setTimeInMillis(ecal.getTimeInMillis());
           tempcal.add(Calendar.DATE, 1);
           Date d1=new Date(tempcal.getTimeInMillis());
            sentList.add(sent);
            sentListarg.add(new Object[]{d,d1,"acc"});
             if(barsent==null)
                    barsent=" SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where date>= ? and date< ? transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true  ";
                else
                    barsent+=" union all SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where date>= ? and date< ? and transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2'  or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
            barArgList.add(d);
            barArgList.add(d1);
        }//case 3
        else if(scal.before(fecal)){
           //report end date is after financial years end date
             String sent=    "insert into rptable1 (name,accid,amount,type_)  select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  rpaccountjournal a   join accountmaster am on am.id=a.accountid  where   a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where a.date>=? and a.date< ? and a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";

           Date d=new Date(fscal.getTimeInMillis());
           Calendar tempcal=Calendar.getInstance();//incr by 1 day,to ensure all entries are considered
           tempcal.setTimeInMillis(ecal.getTimeInMillis());
           tempcal.add(Calendar.DATE, 1);
           Date d1=new Date(tempcal.getTimeInMillis());
           sentList.add(sent);
           sentListarg.add(new Object[]{d,d1,"acc"});
           while(fecal.before(ecal) || fecal.equals(ecal)){
            String name="AJ_"+fecal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+fecal.get(Calendar.YEAR);
            boolean flag=list.contains(name);
            if(flag){
                 String sent1=    "insert into rptable1 (name,accid,amount,type_)  select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  "+name+" a   join accountmaster am on am.id=a.accountid  where a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";
            
                 sentList.add(sent1);
                 sentListarg.add(new Object[]{"acc"});
                 if(barsent==null)
                    barsent=" SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where  transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
                else
                    barsent+=" union all SELECT SUM(AMOUNT) as amt FROM rpaccountjournal   where transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";

            }
            
            fecal.add(Calendar.MONTH, 1);
            fecal.set(Calendar.DATE, fecal.getActualMaximum(Calendar.DATE));
            
           }

        }
    }else {
            if(!ecal.after(fecal) && !scal.before(fscal)){//if requested period is in between fy start date and fy end date
                  
                
                  
                 String sent=    "insert into rptable1 (name,accid,amount,type_)  select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  rpaccountjournal a   join accountmaster am on am.id=a.accountid  where   a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where a.date>=? and a.date< ? and a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1'  group by am.id,am.name,a.transtype  ";
            
                  
                  Date d=new Date();
                  d.setTime(scal.getTimeInMillis());
                 //Date d=new Date(scal.getTimeInMillis());
                 Calendar tempcal=Calendar.getInstance();//incr by 1 day,to ensure all entries are considered
                 tempcal.setTimeInMillis(ecal.getTimeInMillis());
                 tempcal.add(Calendar.DATE, 1);
                 Date d1=new Date(tempcal.getTimeInMillis());
                 sentList.add(sent);
                 sentListarg.add(new Object[]{d,d1,"acc"});
                 if(barsent==null)
                    barsent=" SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where date>= ? and date< ? and transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true  ";
                else
                    barsent+=" union all SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where date>= ? and date< ? and transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
                   barArgList.add(d);
                   barArgList.add(d1);
            }else if(scal.before(fecal) && !ecal.before(fecal)){
                 String sent=    "insert into rptable1 (name,accid,amount,type_)  select am.name,am.id,sum(a.amount),a.transtype "+
                   " from  rpaccountjournal a  join accountmaster am on am.id=a.accountid  where   a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where a.date>=? and a.date< ? and a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";

                 Date d=new Date(scal.getTimeInMillis());
                 Calendar tempcal=Calendar.getInstance();//incr by 1 day,to ensure all entries are considered
                 tempcal.setTimeInMillis(fscal.getTimeInMillis());
                 tempcal.add(Calendar.DATE, 1);
                 Date d1=new Date(tempcal.getTimeInMillis());
                  sentList.add(sent);
                  sentListarg.add(new Object[]{d,d1,"acc"});
                  if(barsent==null)
                    barsent=" SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where date>= ? and date< ? and transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
                else
                    barsent+=" union all SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where date>= ? and date< ?  and transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
                    barArgList.add(d);
                    barArgList.add(d1);
                 while(!ecal.before(fecal)){
                      String name="AJ_"+fecal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+fecal.get(Calendar.YEAR);
                       boolean flag=list.contains(name);
                  if(flag){
                       sent=    "insert into rptable1 (name,accid,amount,type_) select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  "+name+" a   join accountmaster am on am.id=a.accountid  where  a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";
                        sentList.add(sent);
                        sentListarg.add(new Object[]{"acc"});
                        if(barsent==null)
                          barsent=" SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where  transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true  ";
                        else
                          barsent+=" union all SELECT SUM(AMOUNT) as amt FROM rpaccountjournal  where transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";

                  }

                  fecal.add(Calendar.MONTH, 1);
                  fecal.set(Calendar.DATE, fecal.getActualMaximum(Calendar.DATE));
                 }
            }else if(!scal.before(fecal)){
                while(!scal.after(ecal)){
                   String name="AJ_"+scal.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.UK).toUpperCase()+scal.get(Calendar.YEAR);
                    boolean flag=list.contains(name);
            if(flag){
                    String sent=    "insert into rptable1 (name,accid,amount,type_) select   am.name,am.id,sum(a.amount),a.transtype "+
                   " from  "+name+" a join accountmaster am on am.id=a.accountid  where  a.tid in (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and a.active=true and am.parent != '1.1.2' and am.parent!='2.1.3' and am.parent !='1.1.1.2' and a.transref != '1' group by am.id,am.name,a.transtype  ";

                    sentList.add(sent);
                    sentListarg.add(new Object[]{"acc"});
                    if(barsent==null)
                          barsent=" SELECT SUM(AMOUNT) as amt FROM rpaccountjournal where  transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3' ) where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
                        else
                          barsent+=" union all SELECT SUM(AMOUNT) as amt FROM rpaccountjournal  where transref='1' and tid in  (select a.tid from rpaccountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2' or am.parent='2.1.3') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') and active=true ";
            }
                    scal.add(Calendar.MONTH, 1);
                    scal.set(Calendar.DATE, scal.getActualMaximum(Calendar.DATE));
                
                 }
            }


    }
     
          Object[] params=new Object[values.size()];
        Datas[] data=new Datas[values.size()];
        int k=0;
        System.out.println(sentence);
        int i=0;
        List<AccountMaster> acclist=new ArrayList<AccountMaster>();
        acclist=new PreparedSentence(m_App.getSession(), "SELECT AM.ID,AM.NAME,AM.SEARCHKEY,AM.PARENT FROM ACCOUNTMASTER AM WHERE AM.PARENT='1.1.1.2' OR AM.PARENT='1.2.1'  or am.parent='2.1.3'  "
                , null,new SerializerReadClass(AccountMaster.class)).list();
        new PreparedSentence(m_App.getSession(),"DELETE FROM RPTABLE1").exec();
      //  for(AccountMaster accm:acclist){
        for(String sent:sentList){
            Object[] obj;
            Datas[] data1;
            Object[] objtemp=sentListarg.get(i);
           if(objtemp.length>1){
                data1=new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP};
                obj=new Object[]{objtemp[0],objtemp[1]};
                System.out.println(sent);
                System.out.println(objtemp[0]+"and"+objtemp[1]);
                Date d1 = (Date)objtemp[0];
                Date d2 = (Date)objtemp[1];
                
                List<String> TidList = new ArrayList<String>();
                TidList=getTIDList(m_App, d1,d2);
                String TIDStr = "";
                TIDStr=TIDStr+"('";//by pratima
                if(TidList.size()>0){
                   // TIDStr=TIDStr+"('";
                    for(int t=0;t<TidList.size();t++){
                           
                        String tempTID = TidList.get(t).toString();
                        if(t==(TidList.size()-1)){
                          //   TIDStr=TIDStr+tempTID+"')";
                          TIDStr=TIDStr+tempTID;//by pratima
                        }
                        else{
                             TIDStr=TIDStr+tempTID+"','";
                        }
                       
                    }
                }
                 TIDStr=TIDStr+"')";
                System.out.println("New TID LIst   : "+TIDStr);
                String SentTemp = "insert into rptable1 (name,accid,amount,type_)  \n" +
                                    "select   am.name,am.id,sum(a.amount),a.transtype from  rpaccountjournal a   \n" +
                                    "join accountmaster am on am.id=a.accountid  where   a.tid in " +TIDStr+ " and a.active=true and am.parent != '1.1.2' and am.parent !='1.1.1.2' and am.parent!='2.1.3'  \n" +
                                    "and a.transref != '1' group by am.id,am.name,a.transtype";
                new PreparedSentence(m_App.getSession(),  SentTemp , SerializerWriteString.INSTANCE ).exec();
                
                System.out.println("Query Executed : "+SentTemp);
                
                //EDITED BY AAKASH ******************************************************************************
                
              //  new PreparedSentence(m_App.getSession(),  sent , new SerializerWriteBasic(data1) ).exec(obj);
                
                
                
           }else{
                // data1=new Datas[]{Datas.STRING};
                // obj=new Object[]{accm.getid()};
              
                 new PreparedSentence(m_App.getSession(),  sent ).exec();
           }
           i++;

        }
        int j=0;
        Object[] obj1=new Object[barArgList.size()];
        Datas[] datas=new Datas[barArgList.size()];
        for(Date dvar:barArgList){
           datas[j]=Datas.TIMESTAMP;
           obj1[j]=dvar;
           j++;
        }
        Object[] value=(Object[])new PreparedSentence(m_App.getSession(),  "select sum(amt) as amt from ( "+ barsent +"  )as amt1" , new SerializerWriteBasic(datas)
                ,new SerializerReadBasic(new Datas[]{Datas.DOUBLE}) ).find(obj1);
         if(value!=null && value[0]!=null){
           new PreparedSentence(m_App.getSession(), "INSERT INTO RPTABLE1 (NAME,ACCID,AMOUNT,TYPE_) VALUES (?,?,?,?)"
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING})).exec(new Object[]{"Bar Customer Current Account","3",value[0],"D"});
         }
       // }

        //select a.name,sum(r.amount),sum(r1.amount) from rptable1 r right outer join accountmaster a on a.id=r.accid and r.type_='D' join rptable1 r1 on r1.accid=a.id and r1.type_='C'  group by a.name
        dcAcclist=new PreparedSentence(m_App.getSession(), "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(r.AMOUNT),0.0),COALESCE(SUM(r1.AMOUNT),0.0) from rptable1 r right outer join accountmaster am on am.id=r.accid and r.type_='D' left outer join rptable1 r1 on r1.accid=am.id and r1.type_='C' GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
                , null,new SerializerReadClass(Account.class)).list();
    }
    public void compute() throws BasicException, SQLException{
      map1=new HashMap<String,Account>();
      mapcondensed=new HashMap<String, Account>();
      getOpeningAndClosingBalance();
      getCreditAndDebitList();
      if(OBacclist==null)
          OBacclist=new ArrayList<Account>();
      if(CLacclist==null)
          CLacclist=new ArrayList<Account>();
      if(dcAcclist==null)
          dcAcclist=new ArrayList<Account>();
      Account recp=new Account();
      Account recpdetail=new Account();
      recp.setName("Receipt");
      recp.setSKey("R");
      recp.setParent("RP");
      recp.setDC(0, 0);
      recpdetail.setName("Receipt");
      recpdetail.setSKey("RD");
      recpdetail.setParent("RPD");
      recpdetail.setDC(0, 0);
      mainele=new Account();
      maineledetail=new Account();
      mainele.setName("Receipt And Payment Account");
      mainele.setSKey("RP");
      maineledetail.setName("Receipt And Payment Account");
      maineledetail.setSKey("RPD");
      Account payments=new Account();
      Account paymentsdetail=new Account();
      Account ob=new Account();
      Account cb=new Account();
      payments.setName("Payments");
      payments.setSKey("P");
      payments.setParent("RP");
      payments.setDC(0, 0);
      paymentsdetail.setName("Payments");
      paymentsdetail.setSKey("PD");
      paymentsdetail.setParent("RPD");
      paymentsdetail.setDC(0, 0);
      ob.setName("Opening Balance");
      ob.setSKey("OB");
      ob.setParent("R");
      ob.setDC(0, 0);
      //map1.put(recpdetail.getSKey(), recpdetail);
      //map1.put(paymentsdetail.getSKey(), paymentsdetail);
      for(Account acc:OBacclist){
         acc.setParent("OB");
         acc.setSKey(acc.getSKey()+"O");
         map1.put(acc.getSKey(), acc);
         ob.addAccount(acc);
      }
      
      List<String> templist=new ArrayList();
      int flag=0;
       for(Account acc: dcAcclist){
           Account accCopyR=new Account(acc);
           Account accCopyP=new Account(acc);
           accCopyP.setParent(accCopyP.getParent()+"P");
           accCopyP.setSKey(accCopyP.getSKey()+"P");
           accCopyR.setParent(accCopyR.getParent()+"R");
           accCopyR.setSKey(accCopyR.getSKey()+"R");
      /*     if(acc.getSKey().equals("4.1.1.3")){
               String rag="";
           }
           if(acc.getSKey().equals("4.1.1")){
               String rag="";
           }
       */
       if(map1.containsKey(acc.getParent())){
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             String tparent=null;
             Account at;
             Account atP;
             Account atR;
             flag=3;
          while(map1.containsKey(parent)){
             at=map1.get(parent);
             atP=map1.get(parent+"P");
             atR=map1.get(parent+"R");
             //dat=map1.get(parent)
             if(at.getSKey().equals("4.1.1")){
               String rag="";
           }
             if(flag==3){
               at.addAccount(acc);
              // if((accCopyP.getAmount()-accCopyP.getCamt())>0){
              //    atP.addAccount(accCopyP);
             //  }else{
              //    atR.addAccount(accCopyR);
             //  }
               atP.addAccount(accCopyP);
               atR.addAccount(accCopyR);
             }
            if(tparent==null || !tparent.equals(parent))
              if(flag!=3){
                at.addDC(debit, credit);
                 atP.addDC(debit, credit);
                  atR.addDC(debit, credit);
              }else
                  flag=0;
             tparent=parent;
            map1.put(parent, at);
            map1.put(parent+"P", atP);
             map1.put(parent+"R", atR);
             map1.put(acc.getSKey(), acc);
             map1.put(accCopyP.getSKey(), accCopyP);
             map1.put(accCopyR.getSKey(), accCopyR);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                 parent="";
              flag=2;
            }  else if(flag==2){
                flag=3;
               break;
            }
          }
         }else{
             map1.put(acc.getSKey(), acc);
             map1.put(accCopyP.getSKey(), accCopyP);
             map1.put(accCopyR.getSKey(), accCopyR);
            /* double diff=acc.getAmount()-acc.getCamt();
             if(diff>0){
                Account atemp=new Account(acc);
                atemp.setSKey(atemp.getSKey()+"D");
                atemp.setParent(atemp.getParent()+"D");
                map1.put(atemp.getSKey(), atemp);
             }else if(diff<0){
                Account atemp=new Account(acc);
                atemp.setSKey(atemp.getSKey()+"C");
                atemp.setParent(atemp.getParent()+"C");
                map1.put(atemp.getSKey(), atemp);
             }*/
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             Account at;
             Account atP;
             Account atR;
             flag=3;
             String tparent=null;
             int temp=0;
          while(map1.containsKey(parent)){
              at=map1.get(parent);
              atP=map1.get(parent+"P");
              atR=map1.get(parent+"R");
              if(temp==0){
                 at.addAccount(acc);
                 atP.addAccount(accCopyP);
                 atR.addAccount(accCopyR);
              }
              if(flag==3){
               flag=0;
              }
             if(tparent==null || !tparent.equals(parent))
               if(temp!=0){
                  at.addDC(debit, credit);
                  atP.addDC(debit, credit);
                  atR.addDC(debit, credit);
               }else
                  temp=1;
             tparent=parent;
             map1.put(parent, at);
             map1.put(parent+"P", atP);
             map1.put(parent+"R", atR);
             map1.put(acc.getSKey(), acc);
             map1.put(accCopyP.getSKey()+"P", accCopyP);
             map1.put(accCopyR.getSKey()+"R", accCopyR);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                flag=1;
            } else if(flag==1){
                parent="";
              flag=2;
            } else if(flag==2){
                flag=3;
               break;
            }

          }


            /* if(acc.getSign().equals("S")){
                 if(!templist.contains(acc.getSKey())){
                     templist.add(acc.getSKey());
                 }
             }*/
         }



      }
      /*for(String temp:templist){
           Account at=map1.get(temp);
           Collections.sort(at.getAccountList());
           map1.put(temp, at);
      }*/
      for(Account acc:dcAcclist){
          if(acc.getSign().equals("C")){
              if(!acc.getAccountName().equals("Opening Balance")){
              Account atemp=map1.get(acc.getSKey());
              Account atempP=map1.get(acc.getSKey()+"P");
              Account atempR=map1.get(acc.getSKey()+"R");
              if(atemp!=null &&(atemp.getAmount()>0 || atemp.getCamt()>0)){
                 double tempamt=atemp.getAmount()-atemp.getCamt();
                 if(tempamt<0){
                    recp.addAccount(atemp);
                 }else if(tempamt>0){
                    payments.addAccount(atemp);
                 }
              }
              if(atemp!=null &&(atemp.getCamt()>0 )){
                  recpdetail.addAccount(atempR);
              }
              if(atemp!=null &&(atemp.getAmount()>0 )){
                  paymentsdetail.addAccount(atempP);
              }
                 Collections.sort(atemp.getAccountList());
                 Collections.sort(atempP.getAccountList());
                 Collections.sort(atempR.getAccountList());
                 map1.put(atemp.getSKey(), atemp);
                 map1.put(atempP.getSKey(), atempP);
                 map1.put(atempR.getSKey(), atempR);
              }else{
                  ob.addDC( acc.getCamt(),acc.getAmount());
              }

          }else if(acc.getSign().equals("S") || acc.getSign().equals("D")){
              Account atemp=map1.get(acc.getSKey());
              double amt=atemp.getAmount()-atemp.getCamt();
              if(amt==0 || amt ==-0){
                  Account temp=map1.get(atemp.getParent());
                  temp.getAccountList().remove(atemp);
                  map1.put(temp.getSKey(), temp);
              }
              Account atempP=map1.get(acc.getSKey()+"P");
              Account atempR=map1.get(acc.getSKey()+"R");
              if(atempP!=null && atempP.getAmount()<=0){
                  Account temp=map1.get(atempP.getParent());
                  temp.getAccountList().remove(atempP);
                  map1.put(temp.getSKey(), temp);
              }
              if(atempR!=null && atempR.getCamt()<=0){
                  Account temp=map1.get(atempR.getParent());
                  temp.getAccountList().remove(atempR);
                  map1.put(temp.getSKey(), temp);
              }
          }
      }
      
      
      double amt=ob.getAmount()-ob.getCamt();
      ob.setDC( 0.0,amt);
      recp.addAccount(ob,0);
      ob.setParent("RD");
      recpdetail.addAccount(ob,0);
      ob.setParent("R");
      ob.setDC( amt,0.0);
      map1.put(ob.getSKey(), ob);
      //recpdetail.addAmount(ob.getCamt());
      //recpdetail.addCredit(ob.getAmount());
     // double amt=ob.getCamt()-ob.getAmount();
      //if(amt<0)
      //    amt=amt*-1;
     // recpdetail.addCredit(amt);
      cb.setName("Closing Balance");
      cb.setSKey("CB");
      cb.setParent("P");
      cb.setDC(0, 0);
      for(Account acc:CLacclist){
         //acc.setParent("CB");
         acc.setSKey(acc.getSKey()+"C");
         cb.addAccount(acc);
         map1.put(acc.getSKey(), acc);
        // cb.addCredit(acc.getAmount());
        // cb.addAmount(acc.getCamt());11991660.7
      }
      amt=cb.getAmount()-cb.getCamt();
      cb.setDC(amt, 0.0);
      map1.put(cb.getSKey(), cb);
     // map1.put(cb.getSKey(), cb);
      payments.addAccount(cb);
      cb.setParent("PD");
      paymentsdetail.addAccount(cb);
      //paymentsdetail.addAmount(cb.getCamt());
      //paymentsdetail.addCredit(cb.getAmount());
     // amt=cb.getAmount()-cb.getCamt();
     // paymentsdetail.addAmount(amt);
   
      map1.put("R",recp);
      map1.put("P", payments);
      map1.put("RD",recpdetail);
      map1.put("PD", paymentsdetail);
      mainele.addAccountOnly(recp);
      mainele.addAccountOnly(payments);
      maineledetail.addAccountOnly(recpdetail);
      maineledetail.addAccountOnly(paymentsdetail);
      map1.put("RP", mainele);
      map1.put("RPD", maineledetail);
      Account ad=map1.get("1.1.16R");
      Account ad1=map1.get("1.1.16P");
        ///////////////////////////////by pratima to remove zero amount entries from table if zero ignor flag is selected
   if(zeroIgnoreFlag){ 
       Account recpAcc=map1.get("RD");
     for(int i=0;i<recpAcc.getSubAccList().size();i++){
        Account subAccR=recpAcc.getSubAccList().get(i); 
        System.out.println("subAccat names"+subAccR.getAccountName());
        for(int j=0;j<subAccR.getSubAccList().size();j++){
        Account subOfsubAccR=subAccR.getSubAccList().get(j);
               if((subOfsubAccR.getCamt()-subOfsubAccR.getAmount())==0){
                    map1.remove(subOfsubAccR.getSKey());
                    subAccR.getSubAccList().remove(j);
                    j--;
               }}}
      Account pymtAcc=map1.get("PD");
     for(int i=0;i<pymtAcc.getSubAccList().size();i++){
        Account subAccP=pymtAcc.getSubAccList().get(i); 
        System.out.println("subAccat names"+subAccP.getAccountName());
        for(int j=0;j<subAccP.getSubAccList().size();j++){
        Account subOfsubAccP=subAccP.getSubAccList().get(j);
               if((subOfsubAccP.getCamt()-subOfsubAccP.getAmount())==0){
                    map1.remove(subOfsubAccP.getSKey());
                    subAccP.getSubAccList().remove(j);
                    j--;
               }}}
   }
     ////////////////////////////////////////////////////ended by pratima
    }
    public Account getMainElement(){
     return mainele;
    }
    public Account getMainElementDetail(){
     return maineledetail;
    }
   public Map<String,Account> getMap(){
     return map1;
   }
   public void refresh(){
      for(Account acc:map1.values()){
         acc.setVisible(false);
         map1.put(acc.getSKey(), acc);
      }
   }
     public  AbstractTreeTableModel treetableModel(Object data) {
        return new AbstractTreeTableModel(data) {
        public int getColumnCount() {
           return HEADERS.length;
        }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false
            };
             @Override
         public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
         }


        @Override
         public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
         }

        public Object getValueAt(Object node, int column) {
            Account a=(Account) node;
            double amount=0;
            try{
                if(map1.containsKey(a.getSKey())){
                    Account at=map1.get(a.getSKey());
                     amount=at.getAmount()-at.getCamt();
                }/*else
              if(map1.containsKey(a.getSKey())){
                Account at=map1.get(a.getSKey());
                //amount=at.getCredit()-at.getDebit();
              }else{
                amount=a.getCamt()-a.getAmount();
              }*/
                a.setVisible(true);
                map1.put(a.getSKey(), a);
            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1: //if(amount>=0)
                        //  return Formats.ConvertDoubleToString(amount)+" Cr";
                       // else
                          return Formats.ConvertDoubleToString(amount );
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;
            if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null){
                System.out.println("Sub Account " +map1.get(a.getSKey()).getAccountList().get(index).toString());
                return map1.get(a.getSKey()).getAccountList().get(index);
            }
              else
                  return null;
           
        }

        public int getChildCount(Object parent) {
           Account a=(Account) parent;
           if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null)
            
               return map1.get(a.getSKey()).getAccountList().size();
           else
               return 0;
        }
        };
    }
      public  AbstractTreeTableModel treetableModel1(Object data) {
        return new AbstractTreeTableModel(data) {
        public int getColumnCount() {
           return HEADERS.length;
        }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false
            };
             @Override
         public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
         }


        @Override
         public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
         }

        public Object getValueAt(Object node, int column) {
            Account a=(Account) node;
            double amount=0;
            int status=0;
            try{
              //  if(map1.containsKey(a.getSKey())){
               //     Account at=map1.get(a.getSKey());
               //      amount=at.getCamt()-at.getAmount();
              //  }
                /*else
              if(map1.containsKey(a.getSKey())){
                Account at=map1.get(a.getSKey());
                //amount=at.getCredit()-at.getDebit();
              }else{
                amount=a.getCamt()-a.getAmount();
              }*/
                Account at=map1.get(a.getSKey());
                Account at1=map1.get(a.getSKey());
                String parent=at.getParent();
                /*while(!parent.equals("RPD") && !parent.equals("")){
                   if(at.getParent().equals("RD")){
                       status=1;
                       break;
                   }else if(at.getParent().equals("PD")){
                       status=2;
                       break;
                   }
                   at1=map1.get(at1.getParent());
                   parent=at1.getParent();
                }*/
                if(at.getSKey().equals("OB")){
                   String aa="";
                }
                if(at.getSKey().contains("R"))
                    amount=at.getCamt();
               else if(at.getSKey().contains("P"))
                   amount=at.getAmount();
                else{

                    amount=at.getAmount()-at.getCamt();
                    if(amount<0)
                        amount=amount*-1;
                }
                /*if(status==0){
                  amount=at.getCamt()-at.getAmount();
                }else if(status==1){
                  amount=at.getAmount();
                } else if(status==2){
                  amount=at.getCamt();
                }*/
                a.setVisible(true);
                map1.put(a.getSKey(), a);
            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1://if(amount>=0)
                          return Formats.ConvertDoubleToString(amount);
                       // else
                       //   return Formats.ConvertDoubleToString(amount * -1)+"Dr";
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;
            if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null){
               return map1.get(a.getSKey()).getAccountList().get(index);
            }else
                  return null;

        }

        public int getChildCount(Object parent) {
           Account a=(Account) parent;
           if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null)
             return map1.get(a.getSKey()).getAccountList().size();
           else
               return 0;
        }
        };
    }
      
      public void DropCreateAccountJournal() throws SQLException, BasicException{
          
                Calendar StartDate=Calendar.getInstance();
                Calendar EndDate=Calendar.getInstance();
                StartDate.setTimeInMillis(sdate.getTime());
                EndDate.setTimeInMillis(edate.getTime());
                EndDate.add(Calendar.DATE, 1);
                
                 Date d1=new Date(StartDate.getTimeInMillis());
                 Date d2=new Date(EndDate.getTimeInMillis());
                
                int size=0;
                List<String> list=new ArrayList<String>();
                
                
                ResultSet rs=m_App.getSession().getConnection().getMetaData().getTables(null,null, "rpaccountjournal%", null);
                 while(rs.next()){
                   list.add(rs.getString("TABLE_NAME"));
                 }
                
               if(list.size()>0){
                    new PreparedSentence(m_App.getSession(),"DROP TABLE rpaccountjournal").exec();
               }
               
               new PreparedSentence(m_App.getSession(),"create table rpaccountjournal as select * from accountjournal where 1=0;").exec();
              
               int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO rpaccountjournal SELECT * FROM accountjournal where date>= ?  and date < ? "                           
                        , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP})                         
                        ).exec(new Object[]{ d1 , d2});                                                                                                
                         
               
                
      }

      // ADddedd By AAKASH
      public List getTIDList(AppView app ,Date d1 , Date d2) throws BasicException{
          List<Object> TID_List = new ArrayList<Object>();
           TID_List  = (List<Object>) new StaticSentence(app.getSession(), "select a.tid from rpaccountjournal a  , accountmaster am \n" +
                                                                            "WHERE  a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2'  or am.parent='2.1.3' ) \n" +
                                                                            "and a.date>=?  and a.date< ? and a.transref!='Contra' \n" +
                                                                            "and a.transref !='Cash handover' and a.transref !='Cheque handover'",  new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP}) , SerializerReadString.INSTANCE).list(new Object[]{ d1,d2});
          
          return TID_List;
      }
      //added by pratima to remove zero balance entries
     public void setZeroIgnoreFlag(boolean flag){
    if(flag)
    {
        this.zeroIgnoreFlag=true;
    }
    else {
        this.zeroIgnoreFlag=false;
    }
    }  //ended by pratima
      
}
