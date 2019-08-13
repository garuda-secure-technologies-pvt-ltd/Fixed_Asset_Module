
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.format.Formats;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;

import com.openbravo.pos.Accounts.DueListTableModel.FacilityLine;
import com.openbravo.pos.Accounts.DueListTableModel.ReportLine;
import com.openbravo.pos.Accounts.NoticeMasterTableModel.NoticeMasterBean;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.StringUtils;
import java.awt.Button;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class DueListNoticeTableModel extends BeanFactoryDataSingle{

    
    protected Session s;
    private TableDefinition noticeList;
    private final static String[] TABLEHEADERS = {"Search Key","Name","Cr. Available","Facility","Due Amount","Over Due Amount", null};
    private final static String[] TABLEHEADERSFORMEMBERWISE = {"SL No.", "Member Name", "Notice Name","Due Amount" , "Generated Date", "Due Date", "PayBy Date", "Notice Status"};
    
    //private final static String[] TABLEHEADERSFOROTHERTHANSECONDNOTICE = {"Search Key","Name","PayBy Date", "Due Amount", "Cr. Available","Due Amount","Over Due Amount", "Net", null, "Active", "DueDate", "Gen. Date"};
    private final static String[] TABLEHEADERSFOROTHERTHANSECONDNOTICE = {"Search Key","Name","DueDate", "Gen. Date", "PayBy Date", "Due Amount", "Cr. Available","Due Amount","Over Due Amount", "Net", "All", "Mem. Active", };
    private final static String[] UPDATETABLEHEADERS = {"Search Key","Name", "Due Amount","Generated Date", "Payment Date", "Received Ref"};
    private List<Object[]> fac;
    private List<DueListNoticeTableModel.ReportLine> data;
    private List<DueListNoticeTableModel.ReportLine> dataAlreadyGenerated;
     private List<Facilityline2> facilityLine;
     private List<NoticeTrackedBean> noticeTrackerList;
     private List<NoticeTrackedBean> noticeTrackerList2;
     private List<NoticeTrackedBean> noticeTrackerListForSecondNoticeSelectedMembers;
     private List<NoticeMasterBean> noticeListForReport;
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
    private Date dueDateAsOnGD ;
    private List<NoticeTrackedBean> allNoticeListForIndividualMembers;
    private List<List<NoticeTrackedBean>> previousNoticeListForFinalNotice;
    
    
    @Override
    public void init(Session s) {
        this.s = s;
    }

    public DueListNoticeTableModel() {
    }
    
    

    public DueListNoticeTableModel(List<Facilityline2> facilityLine1) {
        this.facilityLine = facilityLine1;
    }
    
    public static DueListNoticeTableModel loadInstanceForReports(AppView app) {
        DueListNoticeTableModel dlntm = new DueListNoticeTableModel();
        try {
            
            dlntm.noticeListForReport = new ArrayList<NoticeMasterBean>();
            
            dlntm.noticeListForReport = new StaticSentence(app.getSession(), "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER,POSTALCHRGFLAG,POSTALCHRGAMT,ACCOUNTID,SIGNIMGFLG FROM NOTICEMASTER  WHERE ID IN(SELECT DISTINCT NOTICEID FROM NOTICETRACKER WHERE ACTIVE = TRUE) ORDER BY NAME ", SerializerWriteString.INSTANCE, new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list();
            
            
        } catch (BasicException ex) {
            Logger.getLogger(DueListNoticeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dlntm;
    }
    
     public AbstractTableModel getMemWiseTableModel()  {
     return new AbstractTableModel() {
            int i=2,j=0;
            
            public int getRowCount() {
                return allNoticeListForIndividualMembers.size();
            }

            public int getColumnCount() {
                return TABLEHEADERSFORMEMBERWISE.length;
            }
           
            @Override
             public String getColumnName(int column) {
                
                return TABLEHEADERSFORMEMBERWISE[column];
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class
            };

            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
             
            }
            
            
            public Object getValueAt(int rowIndex, int columnIndex) {
               // ReportLine r =data.get(rowIndex);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                
                NoticeTrackedBean ntb = allNoticeListForIndividualMembers.get(rowIndex);
                int temp=0;
                //TABLEHEADERSFORMEMBERWISE = {"SL No.", "Member Name", "Due Amount" , "Generated Date", "Due Date", "PayBy Date", "Notice Status"};
                switch(columnIndex){
                    case 0  : return ""+(rowIndex+1);
                    case 1  : return ntb.getMemberName();
                    case 2  : return ntb.getDisplayName();
                    case 3  : return Formats.ConvertDoubleToString(ntb.getDueAmount());
                    case 4  : return (sdf.format(ntb.getGeneratedDate())).toString();
                    case 5  : return (sdf.format(ntb.getDueDateAsOnGeneratedDate())).toString();
                    case 6  : return (sdf.format(ntb.getPayByDate())).toString();
                    case 7  : if(ntb.isActive())
                    {
                        return "Active";
                    }
                        else
                    {
                        return "Deactivated";
                    }

                }
                return null;
            }
        };
   }
   
    
    
    public static DueListNoticeTableModel loadInstanceForMemWiseReports(DataLogicFacilities dlfac, AppView app, CustomerInfo cusinfo, Date fDate, Date tDate) throws BasicException {
        DueListNoticeTableModel dlntm = new DueListNoticeTableModel();
        dlntm.data=new ArrayList<DueListNoticeTableModel.ReportLine>();
            dlntm.allNoticeListForIndividualMembers = new StaticSentence(app.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF, N.NOTES, N.ACTIVE FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND N.MEMID = ? AND N.GENERATEDDATE BETWEEN ? AND ? ORDER BY 6", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{cusinfo.getId(), fDate, tDate});
        String querytemp1=null,query1=null;
            String query=null;
           Date selectedDate = new Date();
           Date overduedate = new Date();
            String accid = null;
           Object obj1 = new StaticSentence(app.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(cusinfo.getId());
       
        if(obj1!=null)
        {
            accid = obj1.toString();
        }
        else
        {
            throw new BasicException("Error!! Unable to get the details.");
        }
       
       List<Object[]> obj =new ArrayList<Object[]>();
               query="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false  and  a.date<  DATE(NOW()) and a.transtype='D' and c.memtype=m.id  and c.account = '"+accid+"' group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue  "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false and a.date>= DATE(NOW()) and a.date<  DATE(NOW())  and a.transtype='D' and c.memtype=m.id and c.account = '"+accid+"'  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue ) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";   
         obj=new PreparedSentence(app.getSession(),
                      "select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'  and a.active = true    group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{new Date(), new Date(), new Date()});
          
       String memid=null,memname=null,memno=null,facentry=null,facilityname=null;
       double camt=0;
       List<FacilityLine> tlist=new ArrayList<FacilityLine>();
      
       for(Object[] objtemp:obj){
           memid=String.valueOf(objtemp[0]);
           memno=String.valueOf(objtemp[2]);
           memname=String.valueOf(objtemp[1]);
           facentry=String.valueOf(objtemp[4]);
           facilityname=String.valueOf(objtemp[3]);
           if(objtemp[13]!=null)
           camt=Double.parseDouble(String.valueOf(objtemp[13]));
           else
              camt=0;
           
              if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine( "All","All", Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
         
           if(memid!=null && memid.equals(String.valueOf(objtemp[0]))){
               
                  int flag=0;
                 for(FacilityLine fl:tlist){
                     if(fl.getFacilityid().equals("All")){
                         flag=1;
                         break;
                     }
                  }
                  if(flag!=1){
                      FacilityLine f1=new FacilityLine(null, null, 0.0, 0.0, "",null);
                      tlist.add(0,f1);
                  }
               }
               ReportLine r=new ReportLine(memid, memname, memno, tlist,camt);
               r.setAll(false);
               
               
                   dlntm.data.add(r);
              
              
               tlist=new ArrayList<FacilityLine>();
           }
      
       if(obj==null)
         {
             dlntm.fac=new ArrayList();
         }
         else
        {
            dlntm.fac=obj;
        }
       
            
        return dlntm;
    }
    
    public List<NoticeMasterBean> getNoticeList(){
        if(noticeListForReport!=null)
        {
            return noticeListForReport;
        }
        else
        {
            return new ArrayList<NoticeMasterBean>();
        }
    }
    
    public static DueListNoticeTableModel loadGeneratedNotice(AppView app,String noticeId)
    {
        DueListNoticeTableModel dNoticeList=new DueListNoticeTableModel();
        dNoticeList.noticeTrackerList  = new ArrayList<NoticeTrackedBean>();
        
        
        try {
         dNoticeList.noticeTrackerList  =  new StaticSentence(app.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND NM.ID=? AND ACTIVE = TRUE ORDER BY 5", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{noticeId});
        } catch (BasicException ex) {
            Logger.getLogger(DueListNoticeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return dNoticeList;
    }

    
    public static DueListNoticeTableModel loadData(AppView app,String fname,List<String> memtype,Date date,Date overduedate,DataLogicFacilities dlfac,String fid, NoticeMasterBean noticeMasterBean) throws BasicException{
    
       DueListNoticeTableModel dALL=new DueListNoticeTableModel();
      
       String query=null;
       String querytemp=null;
       String queryOthers=null;
       String memtypeid = null;
       
       String querytodifmem = "";
       dALL.data=new ArrayList<DueListNoticeTableModel.ReportLine>();
       dALL.dataAlreadyGenerated = new ArrayList<ReportLine>();
       dALL.fac=new ArrayList<Object[]>();
       dALL.noticeTrackerList = new ArrayList<NoticeTrackedBean>();
       
       
       
       for(int memtypesize = 0; memtypesize< memtype.size() ;memtypesize++)
       {
       memtypeid = memtype.get(memtypesize).toString();
       DueListNoticeTableModel d=new DueListNoticeTableModel();
       d.data=new ArrayList<DueListNoticeTableModel.ReportLine>();
       d.dataAlreadyGenerated = new ArrayList<ReportLine>();
       
       
       List<Object[]> obj =new ArrayList<Object[]>();
       String querytemp1=null,query1=null;
       //d.flist=flist;
        if(memtypeid==null) //praveen:active was ambiguous so changed to active1 in below statements
            query="SELECT C.ID as cid,C.NAME as cname,C.SEARCHKEY as ckey,F.NAME as fname,F.ID as fid,M.CURRENTDEBT as currdebt, MF.LBILLDATE as lbilldate,MF.SDATE as sdate,MF.ACTIVE as active1,MF.STATUS_ as status_,0.0 as overdue,0.0 as due,c.account as acc FROM CUSTOMERS C join  MEMDEBTTABLE M on c.id=m.memid join facility F on M.FACILITY=f.id JOIN MEMFACILITYUSAGE MF ON M.MEMID=MF.MEMNO AND F.id=MF.FACILITYTYPE   ";
       else{
            query="SELECT C.ID as cid,C.NAME as cname,C.SEARCHKEY as ckey,F.NAME as fname,F.ID as fid,M.CURRENTDEBT as currdebt, MF.LBILLDATE as lbilldate,MF.SDATE as sdate,MF.ACTIVE as active1,MF.STATUS_ as status_,0.0 as overdue,0.0 as due,c.account as acc   FROM Facility f join  MEMDEBTTABLE M on F.ID=M.FACILITY JOIN MEMFACILITYUSAGE MF ON M.MEMID=MF.MEMNO AND F.id=MF.FACILITYTYPE JOIN CUSTOMERS C ON M.MEMID=C.ID JOIN MEMTYPE M1 ON C.MEMTYPE=M1.ID ";
            querytemp=" WHERE M1.ID= '" +memtypeid+ "'";
       }
       int barfacflag=0;
       if(fid!=null){
           if(!fid.equals("All") && !fid.equals("others")){
               barfacflag=1;
               query=null;
               if(memtypeid==null)          // ************************************* EDITED BY AAKASH FOR ACCOUNTJOURNAL ACTIVE  ENTRIES ***************************************************** 
                  query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_,overdue, due,acc from( select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "' and a.date< ? and a.transtype='D'  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc) as duebill where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_, overdue, due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "' and a.date>= ?  and a.date< ? and a.active=true and a.transtype='D'  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc )as billdue where due>0 ) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc order by ckey ";
               else   
                   if(memtypeid.equals("All"))
                       query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,f.name as fname,f.id as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m,facility f where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "'  and a.date< ? and a.transtype='D' and c.memtype=m.id and a.transref=f.id group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,f.name as fname,f.id as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m,facility f where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "' and a.date>= ?  and a.date< ? and a.active=true  and a.transtype='D' and c.memtype=m.id and a.transref=f.id group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue where due>0) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";

                   //praveen:changed active to active1
                  else 
                   query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,f.name as fname,f.id as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m,facility f where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "' and m.id ='" +memtypeid+ "' and a.date< ? and a.transtype='D' and c.memtype=m.id and a.transref=f.id group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,f.name as fname,f.id as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m,facility f where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "' and m.id ='" +memtypeid+ "' and a.date>= ?  and a.date< ? and a.active=true and a.transtype='D' and c.memtype=m.id and a.transref=f.id group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue where due>0) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";
            }else if(fid.equals("All")){
               barfacflag=3;
               query=null;
               if(memtypeid==null)
                query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_,overdue, due,acc from( select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a where c.account=a.accountid and a.adjusted=false  and a.date< ? and a.transtype='D'  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc) as billdue where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_, overdue, due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a where c.account=a.accountid and a.adjusted=false and a.date>= ?  and a.date< ?  and a.active=true and a.transtype='D'  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc ) as billdue where due>0 ) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc order by ckey ";
            
               else   
                   if(memtypeid.equals("All"))
                        query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false  and  a.date< ? and a.transtype='D' and c.memtype=m.id  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue  where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false and a.date>= ? and a.date< ? and a.active=true and a.transtype='D' and c.memtype=m.id   group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue where due>0) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";

               else
                  query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false and m.id='" +memtypeid+ "' and  a.date< ? and a.transtype='D' and c.memtype=m.id  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue  where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false and a.date>= ? and m.id='" +memtypeid+ "'  and a.date< ? and a.active=true and a.transtype='D' and c.memtype=m.id   group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue where due>0) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";

              
           }

           else{
                  queryOthers="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_,overdue as overdue,due as due,acc from(SELECT C.ID AS CID,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,NULL AS FNAME,NULL AS FID,null as currdebt,null as lbilldate,null as sdate,null as active,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc FROM CUSTOMERS C,ACCOUNTJOURNAL A,FACILITY F where C.ACCOUNT=A.ACCOUNTID AND A.DATE<? and a.active=true AND A.TRANSTYPE='D' AND A.TRANSREF != F.ID Group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc order by ckey)as billdue  WHERE DUE>0";

           }
       
       }
       if(query==null)
           query=query1;
       else{
       if(querytemp!=null || querytemp1!=null){
           if(querytemp==null)
               query+=querytemp1;
           else if(querytemp1 != null){
               query+=querytemp+querytemp1;
           }
       }
       if(query1!=null)
       query+=" union all "+query1;
       }
       if(queryOthers!=null){
            query+=" union all "+queryOthers;
       }
       
       if(barfacflag==0){
         if(queryOthers==null)  //praveen:added alias name wr ever required and changed active to active1
            obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and  a.active = true  where currdebt >0   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
             , null, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list();
          else
               obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and  a.active = true  where  currdebt >0  group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{date});
      }else
           if(barfacflag==1){
          if(queryOthers==null)
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and   a.active = true   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,date});
          else
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and   a.active = true   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,date,date});
       }
       else if(barfacflag==2){
     
          if(queryOthers==null)
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'  and a.active = true       group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,date});
          else
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and   a.active = true             group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,date,date});
    
           }else
           if(barfacflag==3){
           if(queryOthers==null)
              obj=new PreparedSentence(app.getSession(),
                      "select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'  and a.active = true    group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,date});
          else
              obj=new PreparedSentence(app.getSession(),
                      "select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'     and a.active = true   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,date,date});
       }
       
      dALL.noticeTrackerList = new StaticSentence(app.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE,  N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND NM.ID=? AND ACTIVE = TRUE ORDER BY 5", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{noticeMasterBean.getId().toString()});
       
       String memid=null,memname=null,memno=null,facentry=null,facilityname=null;
       double camt=0;
       List<FacilityLine> tlist=new ArrayList<FacilityLine>();
      
       for(Object[] objtemp:obj){
           memid=String.valueOf(objtemp[0]);
           memno=String.valueOf(objtemp[2]);
           memname=String.valueOf(objtemp[1]);
           facentry=String.valueOf(objtemp[4]);
           facilityname=String.valueOf(objtemp[3]);
           if(objtemp[13]!=null)
           camt=Double.parseDouble(String.valueOf(objtemp[13]));
           else
              camt=0;
            if(barfacflag==1)
           {
             if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine(String.valueOf(objtemp[3]), String.valueOf(objtemp[4]), Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
          }else if(barfacflag==3){
              if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine( fid,fid, Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
          }else if(barfacflag==0){
              if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine( String.valueOf(objtemp[3]), String.valueOf(objtemp[4]), Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
          }
           if(memid!=null && memid.equals(String.valueOf(objtemp[0]))){
               
                  int flag=0;
                 for(FacilityLine fl:tlist){
                     if(fid.equals(fl.getFacilityid())){
                         flag=1;
                         break;
                     }
                  }
                  if(flag!=1){
                      FacilityLine f1=new FacilityLine(fname, fid, 0.0, 0.0, "",null);
                      tlist.add(0,f1);
                  }
               }
               ReportLine r=new ReportLine(memid, memname, memno, tlist,camt);
               r.setAll(false);
               
               if(dALL.noticeTrackerList.toString().contains((r.searchkey+"_"+noticeMasterBean.getName()).toString()))
               {
                   r.setGenerated(true);
                   d.dataAlreadyGenerated.add(r);
                   
               }
               else
               {
                   d.data.add(r);
               }
              
               tlist=new ArrayList<FacilityLine>();
           }
      
       if(obj==null)
         {
             d.fac=new ArrayList();
         }
         else
        {
            d.fac=obj;
        }
            boolean addAll = dALL.fac.addAll(d.fac);
            boolean addAll2 = dALL.data.addAll(d.data);
            dALL.dataAlreadyGenerated.addAll(d.dataAlreadyGenerated);
    }
       
       List<NoticeTrackedBean> removeNTB = new ArrayList<NoticeTrackedBean>();
        for (Iterator<NoticeTrackedBean> it = dALL.noticeTrackerList.iterator(); it.hasNext();) {
             int cnt =0;
            NoticeTrackedBean notTrB = it.next();
            
            //if(dALL.dataAlreadyGenerated.contains(it))
            
            for (int i = 0; i < dALL.dataAlreadyGenerated.size(); i++) {
                ReportLine repLine = dALL.dataAlreadyGenerated.get(i);
                
                if(dALL.dataAlreadyGenerated.get(i).getSearchkey().equals(notTrB.getMemSearchKey()))
                {
                    break;
                }
                else
                {
                    if(i==dALL.dataAlreadyGenerated.size()-1)
                    {
                        NoticeTrackedBean deactivateNTB = notTrB;
                        
                        cnt = new PreparedSentence(app.getSession(), "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=?, LOCATIONOFDEACT = ?, DEACTIVATEDREF =?, NOTES = CONCAT(IFNULL(NOTES,''), ?) WHERE MEMID=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), app.getProperties().getHost(), "Due Amount Cleared, hence automatically Deactivating while Generating " + noticeMasterBean.getName() + ". ", "    Due Amount Cleared, hence automatically Deactivating while Generating " + noticeMasterBean.getName() + ". ", notTrB.getMemid()});
                        removeNTB.add(notTrB);
                    }
                }
             }
            }
        dALL.noticeTrackerList.removeAll(removeNTB);
        return dALL;
    }
    
    
    public static DueListNoticeTableModel loadDataforSecondNotice(AppView app,Date selectedDate, String fid,String fname, DataLogicFacilities dlfac, NoticeMasterBean noticeMasterBean, List<NoticeTrackedBean> listOfMem) throws BasicException{
    
         DueListNoticeTableModel dALL=new DueListNoticeTableModel();
      Date overduedate = null;
      
       String querytemp=null;
       String queryOthers=null;
       String memtypeid = null;
       NoticeTrackedBean selectedMemberFirstNotice;
       dALL.dueDateAsOnGD = new Date();
       
       String querytodifmem = "";
       dALL.data=new ArrayList<DueListNoticeTableModel.ReportLine>();
       dALL.dataAlreadyGenerated = new ArrayList<ReportLine>();
       dALL.fac=new ArrayList<Object[]>();
       dALL.noticeTrackerList = new ArrayList<NoticeTrackedBean>();
       dALL.noticeTrackerList2 = new ArrayList<NoticeTrackedBean>();
       dALL.noticeTrackerListForSecondNoticeSelectedMembers = new ArrayList<NoticeTrackedBean>();
       
       for(int num = 0; num< listOfMem.size() ;num++)
       {
       selectedMemberFirstNotice = listOfMem.get(num);
       
       //selectedMemberFirstNotice.getPayByDate().before(new Date())
        if(selectedMemberFirstNotice.getPayByDate().before(new Date()))
       {
     
        String querytemp1=null,query1=null;
            String query=null;
           dALL.noticeTrackerListForSecondNoticeSelectedMembers.add(selectedMemberFirstNotice);
           
            selectedDate = selectedMemberFirstNotice.getSelectedDate();
            overduedate =selectedMemberFirstNotice.getDueDateAsOnGeneratedDate();
            String accid = null;
           Object obj1 = new StaticSentence(app.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(selectedMemberFirstNotice.getMemid());
       
        if(obj1!=null)
        {
            accid = obj1.toString();
        }
        else
        {
            throw new BasicException("Error!! Unable to get the details.");
        }
           
       DueListNoticeTableModel d=new DueListNoticeTableModel();
       d.data=new ArrayList<DueListNoticeTableModel.ReportLine>();
       d.dataAlreadyGenerated = new ArrayList<ReportLine>();
       
       List<Object[]> obj =new ArrayList<Object[]>();
       //d.flist=flist;
        
            query="SELECT C.ID as cid,C.NAME as cname,C.SEARCHKEY as ckey,F.NAME as fname,F.ID as fid,M.CURRENTDEBT as currdebt, MF.LBILLDATE as lbilldate,MF.SDATE as sdate,MF.ACTIVE as active1,MF.STATUS_ as status_,0.0 as overdue,0.0 as due,c.account as acc FROM CUSTOMERS C join  MEMDEBTTABLE M on c.id=m.memid join facility F on M.FACILITY=f.id JOIN MEMFACILITYUSAGE MF ON M.MEMID=MF.MEMNO AND F.id=MF.FACILITYTYPE   ";
       
       int barfacflag=0;
       if(fid!=null){
           if(!fid.equals("All") && !fid.equals("others")){
               barfacflag=1;
               query=null;
               query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,f.name as fname,f.id as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m,facility f where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "'  and a.date< ? and a.transtype='D' and c.memtype=m.id and a.transref=f.id and c.account = '"+accid+"' group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,f.name as fname,f.id as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m,facility f where c.account=a.accountid and a.adjusted=false  and a.transref='" +fid+ "' and a.date>= ?  and a.date< ?  and a.transtype='D' and c.memtype=m.id and a.transref=f.id and c.account = '"+accid+"' group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue where due>0) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";
            }else if(fid.equals("All"))
            {
               barfacflag=3;
               query=null;
               query1="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,sum(overdue) as overdue,sum(due) as due,acc from( "+
                         " select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,sum(a.balanceamount) as overdue,0.0 as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false  and  a.date< ? and a.transtype='D' and c.memtype=m.id  and c.account = '"+accid+"' group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc) as billdue  where overdue>0 "+
                         " union all  select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue as overdue,due as due,acc from(select c.id as cid,c.name as cname,c.searchkey as ckey,null as fname,null as fid,null as currdebt,null as lbilldate,null as sdate,null as active1,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc from customers c, accountjournal a,memtype m where c.account=a.accountid and a.adjusted=false and a.date>= ? and a.date< ?  and a.transtype='D' and c.memtype=m.id and c.account = '"+accid+"'  group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc)as billdue where due>0) as duebill group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active1,status_,acc order by ckey ";   
           }

           else{
                  queryOthers="select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active,status_,overdue as overdue,due as due,acc from(SELECT C.ID AS CID,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,NULL AS FNAME,NULL AS FID,null as currdebt,null as lbilldate,null as sdate,null as active,null as status_,0.0 as overdue,sum(a.balanceamount) as due,c.account as acc FROM CUSTOMERS C,ACCOUNTJOURNAL A,FACILITY F where C.ACCOUNT=A.ACCOUNTID and c.account = '"+accid+"' AND A.DATE<? AND A.TRANSTYPE='D' AND A.TRANSREF != F.ID Group by cid,ckey,cname,fname,fid,currdebt,lbilldate,sdate,active,status_,acc order by ckey)as billdue  WHERE DUE>0";

           }
       
       }
       if(query==null)
           query=query1;
       else{
       if(querytemp!=null || querytemp1!=null){
           if(querytemp==null)
               query+=querytemp1;
           else if(querytemp1 != null){
               query+=querytemp+querytemp1;
           }
       }
       if(query1!=null)
       query+=" union all "+query1;
       }
       if(queryOthers!=null){
            query+=" union all "+queryOthers;
       }
       
       if(barfacflag==0){
         if(queryOthers==null)  //praveen:added alias name wr ever required and changed active to active1
            obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and  a.active = true  where currdebt >0   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
             , null, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list();
          else
               obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and  a.active = true  where  currdebt >0  group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{selectedDate});
      }else
           if(barfacflag==1){
          if(queryOthers==null)
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and   a.active = true   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,selectedDate});
          else
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and   a.active = true   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,selectedDate,selectedDate});
       }
       else if(barfacflag==2){
     
          if(queryOthers==null)
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'  and a.active = true       group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,selectedDate});
          else
              obj=new PreparedSentence(app.getSession(),"select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C' and   a.active = true             group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,selectedDate,selectedDate});
    
           }else
           if(barfacflag==3){
           if(queryOthers==null)
              obj=new PreparedSentence(app.getSession(),
                      "select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'  and a.active = true    group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,selectedDate});
          else
              obj=new PreparedSentence(app.getSession(),
                      "select cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc,sum(a.balanceamount) from( "+query+" ) as billdue left join accountjournal a on a.accountid=acc and a.transtype='C'     and a.active = true   group by cid,cname,ckey,fname,fid,currdebt,lbilldate,sdate,active1,status_,overdue,due,acc order by 3,4"
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).list(new Object[]{overduedate,overduedate,selectedDate,selectedDate});
       }
       
      dALL.noticeTrackerList = new StaticSentence(app.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME,N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND NM.ID=? AND ACTIVE = TRUE ORDER BY 5", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{noticeMasterBean.getId().toString()});
       
       String memid=null,memname=null,memno=null,facentry=null,facilityname=null;
       double camt=0;
       List<FacilityLine> tlist=new ArrayList<FacilityLine>();
      
       for(Object[] objtemp:obj){
           memid=String.valueOf(objtemp[0]);
           memno=String.valueOf(objtemp[2]);
           memname=String.valueOf(objtemp[1]);
           facentry=String.valueOf(objtemp[4]);
           facilityname=String.valueOf(objtemp[3]);
           if(objtemp[13]!=null)
           camt=Double.parseDouble(String.valueOf(objtemp[13]));
           else
              camt=0;
            if(barfacflag==1)
           {
             if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine(String.valueOf(objtemp[3]), String.valueOf(objtemp[4]), Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
          }else if(barfacflag==3){
              if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine( fid,fid, Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
          }else if(barfacflag==0){
              if(objtemp[11]!=null || objtemp[10]!=null  ){
              FacilityLine f=new FacilityLine( String.valueOf(objtemp[3]), String.valueOf(objtemp[4]), Double.parseDouble(objtemp[11].toString()),Double.parseDouble(objtemp[10].toString()) , null,null);
              tlist.add(f);
            }
          }
           if(memid!=null && memid.equals(String.valueOf(objtemp[0]))){
               
                  int flag=0;
                 for(FacilityLine fl:tlist){
                     if(fid.equals(fl.getFacilityid())){
                         flag=1;
                         break;
                     }
                  }
                  if(flag!=1){
                      FacilityLine f1=new FacilityLine(fname, fid, 0.0, 0.0, "",null);
                      tlist.add(0,f1);
                  }
               }
               ReportLine r=new ReportLine(memid, memname, memno, tlist,camt);
               r.setAll(false);
               
               if(dALL.noticeTrackerList.toString().contains((r.searchkey+"_"+noticeMasterBean.getName()).toString()))
               {
                   r.setGenerated(true);
                   d.dataAlreadyGenerated.add(r);
                   
               }
               else
               {
                   d.data.add(r);
               }
              
               tlist=new ArrayList<FacilityLine>();
           }
      
       if(obj==null)
         {
             d.fac=new ArrayList();
         }
         else
        {
            d.fac=obj;
        }
       
            if(d.data.size()>0 || d.fac.size()>0 || d.dataAlreadyGenerated.size()>0 )
            {
            boolean addAll = dALL.fac.addAll(d.fac);
            boolean addAll2 = dALL.data.addAll(d.data);
            dALL.dataAlreadyGenerated.addAll(d.dataAlreadyGenerated);
            dALL.noticeTrackerList2.add(selectedMemberFirstNotice);
            }
            else
            {
              int  cnt = new PreparedSentence(app.getSession(), "UPDATE NOTICETRACKER SET ACTIVE=FALSE, DEACTIVATEDBY=?, DEACTIVATEDDATE=?, LOCATIONOFDEACT = ?, DEACTIVATEDREF =?, NOTES = CONCAT(IFNULL(NOTES,''), ?) WHERE MEMID=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), app.getProperties().getHost(), "Due Amount Cleared, hence automatically Deactivating while Generating " + noticeMasterBean.getName() + ". ",  "  Due Amount Cleared, hence automatically Deactivating while Generating " + noticeMasterBean.getName() + ". ", selectedMemberFirstNotice.getMemid()});
            }
    }}
    
    return dALL;
      
    }
    
    
  
    public List<DueListNoticeTableModel.ReportLine> getReportList1(){
        if(data!=null)
        {
      return data;
        }
        else
        {
            return new ArrayList<ReportLine>();
        }
   }
    public List<DueListNoticeTableModel.ReportLine> getReportListForAlreadyGenerated(){
        if(dataAlreadyGenerated!=null)
        {
      return dataAlreadyGenerated;
        }
        else
        {
            return new ArrayList<ReportLine>();
        }
   }
    
    public List<NoticeTrackedBean> getNoticeTrackerListForSecondNoticeSelectedMembers(){
        if(noticeTrackerListForSecondNoticeSelectedMembers!=null)
        {
            return noticeTrackerListForSecondNoticeSelectedMembers;
            
        }
        else
        {
            return new ArrayList<NoticeTrackedBean>();
        }
    }
    
    public List<NoticeTrackedBean> getFromNoticeTracker() {
        if(noticeTrackerList!=null)
        {
      return noticeTrackerList;
        }
        else
        {
            return new ArrayList<NoticeTrackedBean>();
        }
    }
    
    public List<NoticeTrackedBean> getAllNoticeListForIndividualMembers()
    {
        if(allNoticeListForIndividualMembers!=null)
        {
            return allNoticeListForIndividualMembers;
        }
        else
            return new ArrayList<NoticeTrackedBean>();
    }
    
     public List<Facilityline2> getfacilityline1() {
         if(facilityLine!=null)
        return facilityLine;
         else
             return new ArrayList<Facilityline2>();
    }
    
   public AbstractTableModel getTableModel()  {
     return new AbstractTableModel() {
            int i=2,j=0;
            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {
                return TABLEHEADERS.length;
            }
            
            @Override
             public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true
            };
            
            
            @Override
            public boolean isCellEditable(int row, int col)
            {
            
                return canEdit[col];
            }
            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                
                ReportLine rl = data.get(row);
                
            if(column==6)
            {
                 boolean status = Boolean.parseBoolean(aValue.toString());
             rl.setAll(status);
             data.get(row).setAll(status);
             fireTableDataChanged();
            }
            }
            
            public Object getValueAt(int rowIndex, int columnIndex) {
                ReportLine r =data.get(rowIndex);
                int temp=0;
                
                switch(columnIndex){
                    case 0  : return r.getSearchkey();
                    case 1  : return r.getName();
                    case 2 : return Formats.ConvertDoubleToString(r.getCreditAmt());
                    
                    case 6  : return r.isAll();   
                    default : temp=(columnIndex-3);
                              if(temp!=0){
                                   temp=temp/3;
                              }
                              FacilityLine f=r.getFacilityList().get(temp);
                              if( ((temp)*3+3)==columnIndex) {
                                      return f.getFacilityName();
                             }else if(f.getFacilityName().equals(f.getFacilityName())){
                                  if( ((temp)*3+4)==columnIndex) {
                                      if(f.getDueAmount()!=null)
                                      return Formats.ConvertDoubleToString(f.getDueAmount());
                                      else
                                          return 0.0;
                                  }else if( ((temp)*3+5)==columnIndex) {
                                      if(f.getRemark()!=null )
                                          return f.getRemark();
                                      else if(f.getOverDueAmount()>0){
                                          return Formats.ConvertDoubleToString(f.getOverDueAmount());
                                      }else
                                          return 0.0;
                                  }
                                  else return null;
                              }else return null;
                }
            }
        };
   }
   
   public AbstractTableModel getTableModelForSecondNotice()  {
     return new AbstractTableModel() {
            int i=2,j=0;
           
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {
                
                    return TABLEHEADERSFOROTHERTHANSECONDNOTICE.length;
                
                }
            
            @Override
             public String getColumnName(int column) {
                
                return TABLEHEADERSFOROTHERTHANSECONDNOTICE[column];
               
            }
            
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class,java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,  java.lang.Double.class, java.lang.Boolean.class, java.lang.String.class
            };
            
            boolean[] canEdit = new boolean[]{
                false, false,  false, false,false, false, false, false, false, false, true, false
            };
            
            
            @Override
            public boolean isCellEditable(int row, int col)
            {
            
                return canEdit[col];
            }
             @Override
            public void setValueAt(Object aValue, int row, int column) {
                
                 
                ReportLine rl = data.get(row);
                
            if(column==10)
            {
                 boolean status = Boolean.parseBoolean(aValue.toString());
             rl.setAll(status);
             data.get(row).setAll(status);
             fireTableDataChanged();
             
            }
           
            }
            public Object getValueAt(int rowIndex, int columnIndex) {
                ReportLine r =data.get(rowIndex);
                NoticeTrackedBean ntb1 = null;
                for (Iterator<NoticeTrackedBean> it = noticeTrackerList2.iterator(); it.hasNext();) {
                   NoticeTrackedBean ntb = it.next();
                    if(ntb.getMemSearchKey().equals(r.getSearchkey()))
                    {
                        ntb1 = ntb;
                        break;
                    }
                }
                
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                int temp=0;
                
                //private final static String[] TABLEHEADERSFOROTHERTHANSECONDNOTICE = {"Search Key","Name","DueDate", "Gen. Date", "PayBy Date", "Due Amount", "Cr. Available","Due Amount","Over Due Amount", "Net", null, "Active", };
                switch(columnIndex){
                    case 0 : return r.getSearchkey();
                    case 1 : return r.getName();
                    
                    case 4 : return (sdf1.format(ntb1.getPayByDate()).toString());
                    case 5 : return Formats.ConvertDoubleToString(ntb1.getDueAmount());
                    case 6 :  return Formats.ConvertDoubleToString(r.getCreditAmt());  
                    case 7 :
                        
                        double d = 0.0;
                        for (Iterator<FacilityLine> it = r.getFacilityList().iterator(); it.hasNext();) {
                            FacilityLine f = it.next();
                            d =d+f.getDueAmount();
                        }
                        return d;
                    case 8 : 
                        double d1 = 0.0;
                        for (Iterator<FacilityLine> it = r.getFacilityList().iterator(); it.hasNext();) {
                            FacilityLine f = it.next();
                            d1 =d1+f.getOverDueAmount();
                        }
                        return d1;
                    case 9 : 
                           Double d2 = (Double.parseDouble(getValueAt(rowIndex, 6).toString().replaceAll(",", "")))-(Double.parseDouble(getValueAt(rowIndex, 7).toString().replaceAll(",", "")))-(Double.parseDouble(getValueAt(rowIndex, 8).toString().replaceAll(",", "")));
                        return Formats.ConvertDoubleToString(-(d2));
                    case 10  : return r.isAll();  
                    case 11  : 
                                if(ntb1.isMemActive())
                                {
                                    return "Yes";
                                }
                                else
                                {
                                    return "No";    
                                }
                        case 2 : 
                        String dueda = sdf1.format(ntb1.getDueDateAsOnGeneratedDate()).toString();
                      return dueda;
                        
                    case 3 : return (sdf1.format(ntb1.getGeneratedDate()).toString());
                    default : temp=(columnIndex-3);
                              if(temp!=0){
                                   temp=temp/3;
                              }
                              FacilityLine f=r.getFacilityList().get(temp);
                              if( ((temp)*3+3)==columnIndex) {
                                      return f.getFacilityName();
                             }else if(f.getFacilityName().equals(f.getFacilityName())){
                                  if( ((temp)*3+4)==columnIndex) {
                                      return Formats.ConvertDoubleToString(f.getDueAmount());
                                  }else if( ((temp)*3+5)==columnIndex) {
                                      if(f.getRemark()!=null )
                                          return f.getRemark();
                                      else if(f.getOverDueAmount()>0){
                                          return Formats.ConvertDoubleToString(f.getOverDueAmount());
                                      }else
                                          return null;
                                  }
                                  else return null;
                              }else return null;
                }
            }
        };
   }
   
   public AbstractTableModel getTableModelForSecondNoticeForAG()  {
     return new AbstractTableModel() {
            int i=2,j=0;
           
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            public int getRowCount() {
                return dataAlreadyGenerated.size();
            }

            public int getColumnCount() {
                
                    return TABLEHEADERSFOROTHERTHANSECONDNOTICE.length;
                
                }
            
            @Override
             public String getColumnName(int column) {
                
                return TABLEHEADERSFOROTHERTHANSECONDNOTICE[column];
               
            }
            
           Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class,java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,  java.lang.Double.class, java.lang.Boolean.class, java.lang.String.class
            };
            
            boolean[] canEdit = new boolean[]{
                false, false,  false, false,false, false, false, false, false, false, true, false
            };
            
            @Override
            public boolean isCellEditable(int row, int col)
            {
            
                return canEdit[col];
            }
             @Override
            public void setValueAt(Object aValue, int row, int column) {
                
                ReportLine rl = dataAlreadyGenerated.get(row);
                
            if(column==10)
            {
                 boolean status = Boolean.parseBoolean(aValue.toString());
             rl.setAll(status);
             dataAlreadyGenerated.get(row).setAll(status);
             fireTableDataChanged();
             
            }
           
            }
             
             public Object getValueAt(int rowIndex, int columnIndex) {
                ReportLine r =dataAlreadyGenerated.get(rowIndex);
                NoticeTrackedBean ntb1 = null;
                for (Iterator<NoticeTrackedBean> it = noticeTrackerList2.iterator(); it.hasNext();) {
                   NoticeTrackedBean ntb = it.next();
                    if(ntb.getMemSearchKey().equals(r.getSearchkey()))
                    {
                        ntb1 = ntb;
                        break;
                    }
                }
                
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                int temp=0;
                
                //private final static String[] TABLEHEADERSFOROTHERTHANSECONDNOTICE = {"Search Key","Name","DueDate", "Gen. Date", "PayBy Date", "Due Amount", "Cr. Available","Due Amount","Over Due Amount", "Net", null, "Active", };
                switch(columnIndex){
                    case 0 : return r.getSearchkey();
                    case 1 : return r.getName();
                    
                    case 4 : return (sdf1.format(ntb1.getPayByDate()).toString());
                    case 5 : return Formats.ConvertDoubleToString(ntb1.getDueAmount());
                    case 6 :  return Formats.ConvertDoubleToString(r.getCreditAmt());  
                    case 7 :
                        
                        double d = 0.0;
                        for (Iterator<FacilityLine> it = r.getFacilityList().iterator(); it.hasNext();) {
                            FacilityLine f = it.next();
                            d =d+f.getDueAmount();
                        }
                        return d;
                    case 8 : 
                        double d1 = 0.0;
                        for (Iterator<FacilityLine> it = r.getFacilityList().iterator(); it.hasNext();) {
                            FacilityLine f = it.next();
                            d1 =d1+f.getOverDueAmount();
                        }
                        return d1;
                    case 9 : 
                           Double d2 = (Double.parseDouble(getValueAt(rowIndex, 6).toString().replaceAll(",", "")))-(Double.parseDouble(getValueAt(rowIndex, 7).toString().replaceAll(",", "")))-(Double.parseDouble(getValueAt(rowIndex, 8).toString().replaceAll(",", "")));
                        return Formats.ConvertDoubleToString(-(d2));
                    case 10  : return r.isAll();  
                    case 11  : 
                                if(ntb1.isMemActive())
                                {
                                    return "Yes";
                                }
                                else
                                {
                                    return "No";    
                                }
                        case 2 : 
                        String dueda = sdf1.format(ntb1.getDueDateAsOnGeneratedDate()).toString();
                      return dueda;
                        
                    case 3 : return (sdf1.format(ntb1.getGeneratedDate()).toString());
                    default : temp=(columnIndex-3);
                              if(temp!=0){
                                   temp=temp/3;
                              }
                              FacilityLine f=r.getFacilityList().get(temp);
                              if( ((temp)*3+3)==columnIndex) {
                                      return f.getFacilityName();
                             }else if(f.getFacilityName().equals(f.getFacilityName())){
                                  if( ((temp)*3+4)==columnIndex) {
                                      return Formats.ConvertDoubleToString(f.getDueAmount());
                                  }else if( ((temp)*3+5)==columnIndex) {
                                      if(f.getRemark()!=null )
                                          return f.getRemark();
                                      else if(f.getOverDueAmount()>0){
                                          return Formats.ConvertDoubleToString(f.getOverDueAmount());
                                      }else
                                          return null;
                                  }
                                  else return null;
                              }else return null;
                }
            }
        };
   }
   
   public AbstractTableModel getTableModel3()  {
     return new AbstractTableModel() {
            int i=2,j=0;
            
            public int getRowCount() {
                return noticeTrackerList.size();
            }

            public int getColumnCount() {
                return UPDATETABLEHEADERS.length;
            }
           
            @Override
             public String getColumnName(int column) {
                
                return UPDATETABLEHEADERS[column];
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
             
            }
            
            
            public Object getValueAt(int rowIndex, int columnIndex) {
               // ReportLine r =data.get(rowIndex);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                
                NoticeTrackedBean ntb = noticeTrackerList.get(rowIndex);
                int temp=0;
                
                switch(columnIndex){
                    case 0  : return ntb.getMemSearchKey();
                    case 1  : return ntb.getMemberName();
                    case 2  : return Formats.ConvertDoubleToString(ntb.getDueAmount());
                    case 3  : return (sdf.format(ntb.getGeneratedDate())).toString();
                    case 4  : return (sdf.format(ntb.getPayByDate())).toString();
                    case 5  : if(ntb.isReceivedRef())
                    {
                        return "Yes";
                    }
                        else
                    {
                        return "No";
                    }

                }
                return null;
            }
        };
   }
   
  
   public AbstractTableModel getTableModel2()  {
     return new AbstractTableModel() {
            int i=2,j=0;
            public int getRowCount() {
                return dataAlreadyGenerated.size();
            }

            public int getColumnCount() {
                return TABLEHEADERS.length;
            }
            
            @Override
             public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true
            };
            
            
            @Override
            public boolean isCellEditable(int row, int col)
            {
               
                return canEdit[col];
            }
            
            
            
            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                
                ReportLine rl = dataAlreadyGenerated.get(row);
                
            if(column==6)
            {
                 boolean status = Boolean.parseBoolean(aValue.toString());
             rl.setAll(status);
             dataAlreadyGenerated.get(row).setAll(status);
             fireTableDataChanged();
             
            }
            if(column==7)
            {
             boolean status = Boolean.parseBoolean(aValue.toString());
             rl.setAll(status);
             dataAlreadyGenerated.get(row).setAll(status);
             fireTableDataChanged();
             
            }
            
            }
            
            
            public Object getValueAt(int rowIndex, int columnIndex) {
                ReportLine r =dataAlreadyGenerated.get(rowIndex);
                int temp=0;
                
                switch(columnIndex){
                    case 0  : return r.getSearchkey();
                    case 1  : return r.getName();
                    case 2 : return Formats.ConvertDoubleToString(r.getCreditAmt());
                    
                    case 6  : return r.isAll();   
                    case 7 : return null;
                    default : temp=(columnIndex-3);
                              if(temp!=0){
                                   temp=temp/3;
                              }
                             // FacilityLine f=r.getFacilityList().get(temp);
                              FacilityLine f=r.getFacilityList().get(temp);
                              if( ((temp)*3+3)==columnIndex) {
                                      return f.getFacilityName();
                             }else if(f.getFacilityName().equals(f.getFacilityName())){
                                  if( ((temp)*3+4)==columnIndex) {
                                      if(f.getDueAmount()!=null)
                                      return Formats.ConvertDoubleToString(f.getDueAmount());
                                      else
                                          return 0.0;
                                  }else if( ((temp)*3+5)==columnIndex) {
                                      if(f.getRemark()!=null )
                                          return f.getRemark();
                                      else if(f.getOverDueAmount()>0){
                                          return Formats.ConvertDoubleToString(f.getOverDueAmount());
                                      }else
                                          return 0.0;
                                  }
                                  else return null;
                              }else return null;
                }
            }
        };
   }

   
 public static class ReportLine implements SerializableRead,IKeyed {
     private String name;
     private String searchkey;
     private String id;
     private List<FacilityLine> faclist;
     private double camt;
     private boolean All; 
     private double dueAndOverdue;
     private boolean generated;
     
     public ReportLine(String id,String name, String searchkey, List<FacilityLine> faclist,double camt) {
            this.name = name;
            this.id=id;
            this.searchkey = searchkey;
            this.faclist = faclist;
            this.camt=camt;
            dueAndOverdue = 0.0;
            for (Iterator<FacilityLine> it = faclist.iterator(); it.hasNext();) {
                        FacilityLine facility = it.next();
                        dueAndOverdue = dueAndOverdue + facility.getDueAmount();
                        dueAndOverdue = dueAndOverdue + facility.getOverDueAmount();
                    }
     }
    
     public String getName(){
       return name;
     }

        public boolean isGenerated() {
            return generated;
        }

        public void setGenerated(boolean generated) {
            this.generated = generated;
        }

     
        public boolean isAll() {
            return All;
        }

        public void setAll(boolean All) {
            this.All = All;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getDueAndOverdue() {
            return dueAndOverdue;
        }

        public void setDueAndOverdue(double dueAndOverdue) {
            this.dueAndOverdue = dueAndOverdue;
        }

        
     
     public String getSearchkey(){
         return searchkey;
     }
     public List<FacilityLine> getFacilityList(){
       return faclist;
     }
     public void addFacility(FacilityLine f){
        faclist.add(f);
     }
     public double getCreditAmt(){
      return camt;
     }

        public void readValues(DataRead dr) throws BasicException {
            searchkey = dr.getString(0);
            name = dr.getString(1);
            
        }

        public Object getKey() {
            return this;
        }
        
        @Override
        public String toString()
        {
            return searchkey;
        }
   }
    
    
 
 
    public static DueListNoticeTableModel loadInstanceByIndividualMemberForFirstNotice(AppView app, DataLogicFacilities dlfac,Date todate, NoticeMasterTableModel.NoticeMasterBean nmb, List<ReportLine> selectedList) throws BasicException {
        String accid = null;
        String fid = null;
        String searchkey = null;
        DueListNoticeTableModel noticeSelectedMember = new DueListNoticeTableModel();
        noticeSelectedMember.dlfac = dlfac;
        noticeSelectedMember.facilityLine = new ArrayList<Facilityline2>();
        String fidName = nmb.getFacility().toString().trim();
            
            List<Facility> flist=dlfac.getFacility();
            for (Iterator<Facility> it = flist.iterator(); it.hasNext();) {
            Facility facility = it.next();
            if(facility.getName().toString().trim().equals(nmb.getFacility().toString().trim()))
            {
                fid = facility.getid();
                break;
            }
        }
        for (Iterator<ReportLine> it = selectedList.iterator(); it.hasNext();) {
            ReportLine reportLine = it.next();
        accid = dlfac.getAccountForTaxID(reportLine.getId());
        Object obj = new StaticSentence(app.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(reportLine.getId());
       
        if(obj!=null)
        {
            accid = obj.toString();
        }
        else
        {
            throw new BasicException("Error!! Unable to get the details.");
        }
        
        searchkey = reportLine.getSearchkey();
        
        DueListNoticeTableModel noticeMember = new DueListNoticeTableModel();
        noticeMember.dlfac = dlfac;
        Date sdate = new Date();
        try {
            sdate = todate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        List dlist = new ArrayList<Object>();
        if(nmb.getFacility().toString().equals("All"))
        {
            dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? " +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND  A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID" +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.ACTIVE=TRUE " +
                " AND AJ.TRANSTYPE='C' AND  AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ?  AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE  AND AJ.TRANSTYPE='D'  AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=?  AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING,  Datas.TIMESTAMP,Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP}),
                new SerializerReadClass(DueListNoticeTableModel.Facilityline2.class)).list(new Object[]{accid, todate,accid,  todate, accid, todate, accid,  todate, accid,  todate});
        }
        else
        {
            
            
            dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? AND A.TRANSREF=?" +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND  A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID" +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.TRANSREF=? AND  AJ.ACTIVE=TRUE " +
                " AND AJ.TRANSTYPE='C' AND  AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ? AND A.TRANSREF=? AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE AND AJ.TRANSREF = ? AND AJ.TRANSTYPE='D'  AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=? AND AJ.TRANSREF=? AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,  Datas.TIMESTAMP,Datas.STRING, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadClass(DueListNoticeTableModel.Facilityline2.class)).list(new Object[]{accid, fid, todate,accid, fid,  todate, accid, fid, todate, accid, fid,  todate, accid, fid, todate});
        
            
            
        }
            
        if (dlist == null) {
            noticeMember.facilityLine = new ArrayList<Facilityline2>();
        } else {
            noticeMember.facilityLine = dlist;
        }
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
        List<amountline> previousDues = new StaticSentence(app.getSession(),
                "SELECT SUM(AMT1),SUM(AMT2),SUM(AMT3),SKEY FROM (SELECT SUM(A.DEBIT-A.CREDIT) AS AMT1,0.0 AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM AJPERIODTOTALS A,CUSTOMERS C WHERE A.EDATE < ? AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY " +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,SUM(AMOUNT) AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE   A.DATE < ?  AND A.TRANSTYPE='C' AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE and a.active = true GROUP BY C.SEARCHKEY" +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,0.0 AS AMT2,SUM(AMOUNT) AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE   A.DATE < ?  AND A.TRANSTYPE='D' AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY)" +
                " AS FSDA GROUP BY SKEY",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP,Datas.STRING}),
                new SerializerReadClass(DueListNoticeTableModel.amountline.class)).list(new Object[]{ todate,accid,  sdate, accid,  sdate, accid});
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
                for (Facilityline2 f : noticeMember.getfacilityline1()) {
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
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE ACCOUNT=? ",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(DueListNoticeTableModel.addressline.class)).list(accid);
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for (addressline a : adr) {
                
                String memname = "";
                String addre = "";
                String addre2 = "";
                String city = "";
                String pin = "";
                if(a.getMemname()!=null)
                {
                    memname = a.getMemname();
                }
                if(a.getAddress()!=null)
                {
                    addre = a.getAddress();
                }
                if(a.getAddress2()!=null)
                {
                    addre2 = a.getAddress2();
                }
                if(a.getCity()!=null)
                {
                   city =  a.getCity();
                }
                if(a.getPostal()!=null)
                {
                    pin = "Pin "+ a.getPostal();
                }
                addr = "Mem. No. " + a.getSearchKey() + " \r\n" + memname + " \r\n" + addre + " \r\n" +addre2 + " \r\n" + city + " \r\n" + pin;
                adrmap.put(a.getSearchKey(), addr);
            }
        }
        for (Facilityline2 f : noticeMember.getfacilityline1()) {
            f.setAddress(adrmap.get(f.getMemNo()));
        }
        
        
        noticeSelectedMember.facilityLine.addAll(noticeMember.getfacilityline1());
      

    }
        return noticeSelectedMember;
    
    }
    
    public static DueListNoticeTableModel loadInstanceByIndividualMemberForSecondNotice(AppView app, DataLogicFacilities dlfac,NoticeMasterTableModel.NoticeMasterBean nmb, List<ReportLine> selectedList, List<NoticeTrackedBean> ntBeans) throws BasicException {
        
        String accid = null;
        String fid = null;
        String searchkey = null;
        DueListNoticeTableModel noticeSelectedMember = new DueListNoticeTableModel();
        noticeSelectedMember.dlfac = dlfac;
        noticeSelectedMember.facilityLine = new ArrayList<Facilityline2>();
        noticeSelectedMember.allNoticeListForIndividualMembers = new ArrayList<NoticeTrackedBean>();
        String fidName = nmb.getFacility().toString().trim();
            
            List<Facility> flist=dlfac.getFacility();
            for (Iterator<Facility> it = flist.iterator(); it.hasNext();) {
            Facility facility = it.next();
            if(facility.getName().toString().trim().equals(nmb.getFacility().toString().trim()))
            {
                fid = facility.getid();
                break;
            }
        }
        for (int it =0; it < selectedList.size(); it++) 
        {
            NoticeTrackedBean previousNoticeForSelectedMember = new NoticeTrackedBean();
            ReportLine reportLine = selectedList.get(it);
            NoticeTrackedBean ntbfrselectedmem = ntBeans.get(it);
            
            
            Date todate = ntbfrselectedmem.getSelectedDate();
            
            
        accid = dlfac.getAccountForTaxID(reportLine.getId());
        Object obj = new StaticSentence(app.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(reportLine.getId());
       
        if(obj!=null)
        {
            accid = obj.toString();
        }
        else
        {
            throw new BasicException("Error!! Unable to get the details.");
        }
        
        searchkey = reportLine.getSearchkey();
        
        DueListNoticeTableModel noticeMember = new DueListNoticeTableModel();
        noticeMember.dlfac = dlfac;
        Date sdate = new Date();
        try {
            sdate = todate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        List dlist = new ArrayList<Object>();
        if(nmb.getFacility().toString().equals("All"))
        {
            dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? " +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND  A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID" +
           // " UNION ALL " +
          //  " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
         //   " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
         //   " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.ACTIVE=TRUE " +
          //  " AND AJ.TRANSTYPE='C' AND  AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ?  AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE  AND AJ.TRANSTYPE='D'  AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=?  AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID  AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING,  Datas.TIMESTAMP,Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP}),
                new SerializerReadClass(DueListNoticeTableModel.Facilityline2.class)).list(new Object[]{accid, todate,accid,  todate, accid, todate, accid,  todate, accid,  todate});
        }
        else
        {
            
            
            dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? AND A.TRANSREF=?" +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND  A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID" +
//                " UNION ALL " +
//                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
//                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
//                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.TRANSREF=? AND  AJ.ACTIVE=TRUE " +
//                " AND AJ.TRANSTYPE='C' AND  AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ? AND A.TRANSREF=? AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE AND AJ.TRANSREF = ? AND AJ.TRANSTYPE='D'  AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID  AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=? AND AJ.TRANSREF=? AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID  AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,  Datas.TIMESTAMP,Datas.STRING, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadClass(DueListNoticeTableModel.Facilityline2.class)).list(new Object[]{accid, fid, todate,accid, fid,  todate, accid, fid, todate, accid, fid,  todate, accid, fid, todate});
        
            
            
        }
            
        if (dlist == null) {
            noticeMember.facilityLine = new ArrayList<Facilityline2>();
        } else {
            noticeMember.facilityLine = dlist;
            previousNoticeForSelectedMember = (NoticeTrackedBean) new StaticSentence(app.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND C.ACCOUNT=? AND ACTIVE = TRUE ORDER BY GENERATEDDATE DESC LIMIT 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).find(new Object[]{accid});
        }
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
        List<amountline> previousDues = new StaticSentence(app.getSession(),
                "SELECT SUM(AMT1),SUM(AMT2),SUM(AMT3),SKEY FROM (SELECT SUM(A.DEBIT-A.CREDIT) AS AMT1,0.0 AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM AJPERIODTOTALS A,CUSTOMERS C WHERE A.EDATE < ? AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY " +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,SUM(AMOUNT) AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE   A.DATE < ?  AND A.TRANSTYPE='C' AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE and a.active = true GROUP BY C.SEARCHKEY" +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,0.0 AS AMT2,SUM(AMOUNT) AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE   A.DATE < ?  AND A.TRANSTYPE='D' AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY)" +
                " AS FSDA GROUP BY SKEY",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP,Datas.STRING}),
                new SerializerReadClass(DueListNoticeTableModel.amountline.class)).list(new Object[]{ todate,accid,  sdate, accid,  sdate, accid});
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
                for (Facilityline2 f : noticeMember.getfacilityline1()) {
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
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE ACCOUNT=? ",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(DueListNoticeTableModel.addressline.class)).list(accid);
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for (addressline a : adr) {
                String memname = "";
                String addre = "";
                String addre2 = "";
                String city = "";
                String pin = "";
                if(a.getMemname()!=null)
                {
                    memname = a.getMemname();
                }
                if(a.getAddress()!=null)
                {
                    addre = a.getAddress();
                }
                if(a.getAddress2()!=null)
                {
                    addre2 = a.getAddress2();
                }
                if(a.getCity()!=null)
                {
                   city =  a.getCity();
                }
                if(a.getPostal()!=null)
                {
                    pin = "Pin "+ a.getPostal();
                }
                addr = "Mem. No. " + a.getSearchKey() + " \r\n" + memname + " \r\n" + addre + " \r\n" +addre2 + " \r\n" + city + " \r\n" + pin;
                adrmap.put(a.getSearchKey(), addr);
            }
        }
        for (Facilityline2 f : noticeMember.getfacilityline1()) {
            f.setAddress(adrmap.get(f.getMemNo()));
        }
        
        
        noticeSelectedMember.facilityLine.addAll(noticeMember.getfacilityline1());
        noticeSelectedMember.allNoticeListForIndividualMembers.add(previousNoticeForSelectedMember);
      

    }
        return noticeSelectedMember;
    
    }
    
    
    public static DueListNoticeTableModel loadInstanceByIndividualMemberForFinalNotice(AppView app, DataLogicFacilities dlfac,NoticeMasterTableModel.NoticeMasterBean nmb, List<ReportLine> selectedList, List<NoticeTrackedBean> ntBeans) throws BasicException {
        
        String accid = null;
        String fid = null;
        String searchkey = null;
        DueListNoticeTableModel noticeSelectedMember = new DueListNoticeTableModel();
        noticeSelectedMember.dlfac = dlfac;
        noticeSelectedMember.previousNoticeListForFinalNotice = new ArrayList<List<NoticeTrackedBean>>();
        noticeSelectedMember.facilityLine = new ArrayList<Facilityline2>();
        //noticeSelectedMember.allNoticeListForIndividualMembers = new ArrayList<NoticeTrackedBean>();
        String fidName = nmb.getFacility().toString().trim();
            
            List<Facility> flist=dlfac.getFacility();
            for (Iterator<Facility> it = flist.iterator(); it.hasNext();) {
            Facility facility = it.next();
            if(facility.getName().toString().trim().equals(nmb.getFacility().toString().trim()))
            {
                fid = facility.getid();
                break;
            }
        }
        for (int it =0; it < selectedList.size(); it++) 
        {
            List<NoticeTrackedBean> previousNoticeForSelectedMember = new ArrayList<NoticeTrackedBean>();
            ReportLine reportLine = selectedList.get(it);
            NoticeTrackedBean ntbfrselectedmem = ntBeans.get(it);
            Date todate = ntbfrselectedmem.getSelectedDate();
        accid = dlfac.getAccountForTaxID(reportLine.getId());
        Object obj = new StaticSentence(app.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(reportLine.getId());
       
        if(obj!=null)
        {
            accid = obj.toString();
        }
        else
        {
            throw new BasicException("Error!! Unable to get the details.");
        }
        searchkey = reportLine.getSearchkey();
        
        DueListNoticeTableModel noticeMember = new DueListNoticeTableModel();
        noticeMember.dlfac = dlfac;
        Date sdate = new Date();
        try {
            sdate = todate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        List dlist = new ArrayList<Object>();
        if(nmb.getFacility().toString().equals("All"))
        {
            dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? " +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND  A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID" +
//                " UNION ALL " +
//                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
//                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
//                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.ACTIVE=TRUE " +
//                " AND AJ.TRANSTYPE='C' AND  AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ?  AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE  AND AJ.TRANSTYPE='D'  AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=?  AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID  AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING,  Datas.TIMESTAMP,Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING,  Datas.TIMESTAMP}),
                new SerializerReadClass(DueListNoticeTableModel.Facilityline2.class)).list(new Object[]{accid, todate,accid,  todate, accid, todate, accid,  todate, accid,  todate});
        }
        else
        {
            
            
            dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT,MEMNAME,MEMNO,TTYPE FROM(" +
                " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? AND A.TRANSREF=?" +
                " AND A.ACTIVE=TRUE AND A.TRANSTYPE='D' AND  A.DATE<=? AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID" +
//                " UNION ALL " +
//                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
//                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE  " +
//                " FROM CUSTOMERS C,ACCOUNTJOURNAL AJ WHERE  AJ.ACCOUNTID=? AND AJ.TRANSREF=? AND  AJ.ACTIVE=TRUE " +
//                " AND AJ.TRANSTYPE='C' AND  AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID AND C.VISIBLE=TRUE GROUP BY AJ.ID " +
                " UNION ALL" +
                " SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO," +
                " A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,A.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ? AND A.TRANSREF=? AND A.ACTIVE=TRUE  " +
                " AND A.TRANSTYPE='D' AND A.DATE<=?  AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=A.ACCOUNTID AND A.BALANCEAMOUNT > 0 GROUP BY A.ID " +
                " UNION ALL " +
                " SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=?   " +
                " AND AJ.ACTIVE=TRUE AND AJ.TRANSREF = ? AND AJ.TRANSTYPE='D'  AND AJ.DATE<=?  AND C.ACCOUNT=AJ.ACCOUNTID  AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " +
                " UNION ALL " +
                " SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO," +
                " AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT,C.NAME AS MEMNAME,C.SEARCHKEY AS MEMNO,AJ.TRANSTYPE AS TTYPE   " +
                " FROM CUSTOMERS C,AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=? AND AJ.TRANSREF=? AND AJ.ACTIVE=TRUE   " +
                " AND AJ.TRANSTYPE='D' AND AJ.DATE<=?  AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) AND C.ACCOUNT=AJ.ACCOUNTID  AND AJ.BALANCEAMOUNT > 0 GROUP BY AJ.ID " + " ) AS COLLTRANS ORDER BY 8,12 ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,  Datas.TIMESTAMP,Datas.STRING, Datas.STRING,  Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadClass(DueListNoticeTableModel.Facilityline2.class)).list(new Object[]{accid, fid, todate,accid, fid,  todate, accid, fid, todate, accid, fid,  todate, accid, fid, todate});
        
            
            
        }
            
        if (dlist == null) {
            noticeMember.facilityLine = new ArrayList<Facilityline2>();
        } else {
            noticeMember.facilityLine = dlist;
            previousNoticeForSelectedMember = new StaticSentence(app.getSession(), "SELECT N.ID, NM.ID, NM.NAME, N.MEMID, C.SEARCHKEY, N.GENERATEDDATE, N.DUEAMOUNT, N.PAYBYDATE, N.COMMMODE, N.DATAOFDISPATCH, N.RECEIVEDREF, N.RECEIVEDBY, N.RECEIVEDDATE, N.LINKTOACK, N.LINKTONOTICE, C.NAME, N.DUEDATEONGENDATE, N.PARENTNOTICE, N.DISPLAYNAME, N.SELECTEDDATE, C.VISIBLE, N.LOCATIONOFCREATION, N.LOCATIONOFDEACT, N.DEACTIVATEDREF FROM NOTICETRACKER N, NOTICEMASTER NM, CUSTOMERS C WHERE N.MEMID = C.ID AND N.NOTICEID = NM.ID AND C.ACCOUNT=? AND ACTIVE = TRUE ORDER BY GENERATEDDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(DueListNoticeTableModel.NoticeTrackedBean.class)).list(new Object[]{accid});
        }
        double dtemp = 0, ctemp = 0;
        double crtemp = 0, drtemp = 0, temp = 0;
        List<amountline> previousDues = new StaticSentence(app.getSession(),
                "SELECT SUM(AMT1),SUM(AMT2),SUM(AMT3),SKEY FROM (SELECT SUM(A.DEBIT-A.CREDIT) AS AMT1,0.0 AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM AJPERIODTOTALS A,CUSTOMERS C WHERE A.EDATE < ? AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY " +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,SUM(AMOUNT) AS AMT2,0.0 AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE   A.DATE < ?  AND A.TRANSTYPE='C' AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE and a.active = true GROUP BY C.SEARCHKEY" +
                " UNION ALL " +
                " SELECT 0.0 AS AMT1,0.0 AS AMT2,SUM(AMOUNT) AS AMT3,C.SEARCHKEY AS SKEY  FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE   A.DATE < ?  AND A.TRANSTYPE='D' AND A.ACCOUNTID=?  and c.account=a.accountid  AND C.VISIBLE=TRUE GROUP BY C.SEARCHKEY)" +
                " AS FSDA GROUP BY SKEY",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP,Datas.STRING}),
                new SerializerReadClass(DueListNoticeTableModel.amountline.class)).list(new Object[]{ todate,accid,  sdate, accid,  sdate, accid});
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
                for (Facilityline2 f : noticeMember.getfacilityline1()) {
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
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,PHONE,PHONE2 FROM CUSTOMERS WHERE ACCOUNT=? ",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(DueListNoticeTableModel.addressline.class)).list(accid);
        String addr = null;
        Map<String, String> adrmap = new HashMap<String, String>();
        if (adr != null) {
            for (addressline a : adr) {
                String memname = "";
                String addre = "";
                String addre2 = "";
                String city = "";
                String pin = "";
                if(a.getMemname()!=null)
                {
                    memname = a.getMemname();
                }
                if(a.getAddress()!=null)
                {
                    addre = a.getAddress();
                }
                if(a.getAddress2()!=null)
                {
                    addre2 = a.getAddress2();
                }
                if(a.getCity()!=null)
                {
                   city =  a.getCity();
                }
                if(a.getPostal()!=null)
                {
                    pin = "Pin "+ a.getPostal();
                }
                addr = "Mem. No. " + a.getSearchKey() + " \r\n" + memname + " \r\n" + addre + " \r\n" +addre2 + " \r\n" + city + " \r\n" + pin;
                adrmap.put(a.getSearchKey(), addr);
            }
        }
        for (Facilityline2 f : noticeMember.getfacilityline1()) {
            f.setAddress(adrmap.get(f.getMemNo()));
        }
        
        
        noticeSelectedMember.facilityLine.addAll(noticeMember.getfacilityline1());
        noticeSelectedMember.previousNoticeListForFinalNotice.add(previousNoticeForSelectedMember);
      

    }
        return noticeSelectedMember;
    
    }
    
    
    
    public List<List<NoticeTrackedBean>> getPreviousNoticeList(){
        if(previousNoticeListForFinalNotice!=null)
            return previousNoticeListForFinalNotice;
        else
            return new ArrayList<List<NoticeTrackedBean>>();
    }
    
    
    
    public static class FacilityLine{
     private String facid;
     private String facilityname;
     private Double dueamount;
     private Double overdueamount;
     private String remark;
     private Object lbillperiod;//last billed upto
    // private int no;

     public FacilityLine(String facilityname,String facid,Double dueamount,Double overdueamount,String remark,Object lbillperiod){
         this.facilityname=facilityname;
         this.facid=facid;
         this.dueamount=dueamount;
         this.overdueamount=overdueamount;
         this.remark=remark;
         this.lbillperiod=lbillperiod;
         //this.no=no;
     }
     public Object getLastBillPeriod(){
         return lbillperiod;
     }
     public String getFacilityid(){
         return facid;
     }
     public String getFacilityName(){
       return facilityname;
     }
     public Double getDueAmount(){
       return dueamount;
     }
     public Double getOverDueAmount(){
       return overdueamount;
     }
     public String getRemark(){
        return remark;
     }
   }
    
    
    
    
     public static class Facilityline2 implements SerializableRead {

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
    
    public static class NoticeTrackedBean implements SerializableRead
    {
        private String id;
        private String noticeId;
        private String noticeName;
        private String memberName;
        private String memid;
        private String memSearchKey;
        private Timestamp generatedDate;
        private double DueAmount;
        private Timestamp PayByDate;
        private Timestamp dueDateAsOnGeneratedDate;
        private String CommMadeBy;
        private Timestamp DataOfDispatch;
        private boolean ReceivedRef;
        private String ReceivedBy;
        private Timestamp ReceivedDate;
        private String LinkToAck;
        private String LinkToNotice;
        private String parentNotice;
        private String displayName;
        private Timestamp selectedDate;
        private boolean memActive;
        private String locOfCreation;
        private String locOfDeact;
        private String deactivateRef;
        private String aNotes;
        private boolean active;
        
        
        public void readValues(DataRead dr) throws BasicException {
            
            id = dr.getString(1);
            noticeId = dr.getString(2);
            noticeName = dr.getString(3);
            memid = dr.getString(4);
            memSearchKey = dr.getString(5);
            generatedDate = dr.getTimestamp(6);
            DueAmount = dr.getDouble(7);
            PayByDate = dr.getTimestamp(8);
            CommMadeBy = dr.getString(9);
            DataOfDispatch = dr.getTimestamp(10);
            ReceivedRef = dr.getBoolean(11);
            ReceivedBy = dr.getString(12);
            ReceivedDate = dr.getTimestamp(13);
            LinkToAck = dr.getString(14);
            LinkToNotice = dr.getString(15);
            memberName = dr.getString(16);
            dueDateAsOnGeneratedDate = dr.getTimestamp(17);
            parentNotice = dr.getString(18);
            displayName = dr.getString(19);
            selectedDate  = dr.getTimestamp(20);
            memActive = dr.getBoolean(21);
            locOfCreation = dr.getString(22);
            locOfDeact = dr.getString(23);
            deactivateRef = dr.getString(24);
            
            if(dr.getDataField().length>24)
            {
                 aNotes = dr.getString(25);
                 active = dr.getBoolean(26);
            }
            
           
        }

        public Timestamp getDueDateAsOnGeneratedDate() {
            return dueDateAsOnGeneratedDate;
        }

        public void setDueDateAsOnGeneratedDate(Timestamp dueDateAsOnGeneratedDate) {
            this.dueDateAsOnGeneratedDate = dueDateAsOnGeneratedDate;
        }

        public String getParentNotice() {
            return parentNotice;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getaNotes() {
            return aNotes;
        }

        public void setaNotes(String aNotes) {
            this.aNotes = aNotes;
        }

           
        public boolean isMemActive() {
            return memActive;
        }

        public void setMemActive(boolean memActive) {
            this.memActive = memActive;
        }

        public String getDeactivateRef() {
            return deactivateRef;
        }

        public void setDeactivateRef(String deactivateRef) {
            this.deactivateRef = deactivateRef;
        }

        public String getLocOfCreation() {
            return locOfCreation;
        }

        public void setLocOfCreation(String locOfCreation) {
            this.locOfCreation = locOfCreation;
        }

        public String getLocOfDeact() {
            return locOfDeact;
        }

        public void setLocOfDeact(String locOfDeact) {
            this.locOfDeact = locOfDeact;
        }

        
        public void setParentNotice(String parentNotice) {
            this.parentNotice = parentNotice;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
        
        public String getMemSearchKey() {
            return memSearchKey;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public void setMemSearchKey(String memSearchKey) {
            this.memSearchKey = memSearchKey;
        }

        public String getNoticeName() {
            return noticeName;
        }

        public void setNoticeName(String noticeName) {
            this.noticeName = noticeName;
        }

        
        
        public String getCommMadeBy() {
            return CommMadeBy;
        }

        public void setCommMadeBy(String CommMadeBy) {
            this.CommMadeBy = CommMadeBy;
        }

        public Timestamp getDataOfDispatch() {
            return DataOfDispatch;
        }

        public void setDataOfDispatch(Timestamp DataOfDispatch) {
            this.DataOfDispatch = DataOfDispatch;
        }

        public double getDueAmount() {
            return DueAmount;
        }

        public void setDueAmount(double DueAmount) {
            this.DueAmount = DueAmount;
        }

        public String getLinkToAck() {
            return LinkToAck;
        }

        public void setLinkToAck(String LinkToAck) {
            this.LinkToAck = LinkToAck;
        }

        public String getLinkToNotice() {
            return LinkToNotice;
        }

        public void setLinkToNotice(String LinkToNotice) {
            this.LinkToNotice = LinkToNotice;
        }

        public Timestamp getPayByDate() {
            return PayByDate;
        }

        public void setPayByDate(Timestamp PayByDate) {
            this.PayByDate = PayByDate;
        }

        public String getReceivedBy() {
            return ReceivedBy;
        }

        public void setReceivedBy(String ReceivedBy) {
            this.ReceivedBy = ReceivedBy;
        }

        public Timestamp getReceivedDate() {
            return ReceivedDate;
        }

        public void setReceivedDate(Timestamp ReceivedDate) {
            this.ReceivedDate = ReceivedDate;
        }

        public boolean isReceivedRef() {
            return ReceivedRef;
        }

        public void setReceivedRef(boolean ReceivedRef) {
            this.ReceivedRef = ReceivedRef;
        }

        public Timestamp getGeneratedDate() {
            return generatedDate;
        }

        public void setGeneratedDate(Timestamp generatedDate) {
            this.generatedDate = generatedDate;
        }

        public String getId() {
            return id;
        }

        public Timestamp getSelectedDate() {
            return selectedDate;
        }

        public void setSelectedDate(Timestamp selectedDate) {
            this.selectedDate = selectedDate;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemid() {
            return memid;
        }

        public void setMemid(String memid) {
            this.memid = memid;
        }

        public String getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }
        
        @Override
        public String toString()
        {
            return memSearchKey+"_"+noticeName;
        }
        
    }
    
    
    
    
   
}
