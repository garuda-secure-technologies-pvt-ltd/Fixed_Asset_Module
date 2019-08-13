/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class  NoticeMasterTableModel extends BeanFactoryDataSingle {

    protected Session s;
    private TableDefinition noticeList;
    private int flag;
    private List<NoticeMasterBean> fac;
   
    
    private final static String[] TABLEHEADERS = {"Name","Due/Pass","Period from date of notice","Day/Month","Payment Date Period from date of Notice", "Day / Month", "Additional Notes","Member Type", "Facility","Final Notice"};
    private final static String[] TABLEHEADERS1 = {"Name","Due/Pass","Period from date of notice","Day/Month","Payment Date Period from date of Notice", "Day / Month", "Additional Notes","Member Type", "Facility","Final Notice", "Parent Reference" , "Cr. Date", "Signature", "Designation"};
    private final static String[] TABLEHEADERS2 = {"Name","Due/Pass","Period from date of notice","Day/Month","Payment Date Period from date of Notice", "Day / Month", "Additional Notes","Member Type", "Facility","Final Notice", "Parent Reference" , "Parent Id", "Cr. Date", "DeactivatedBy", "Deactivated Date", "Active"  };
    
    
    public void init(Session s) {
        this.s = s; 
    }
    
    
//    public final List<Facility> getFacility() throws BasicException {
//        return new StaticSentence(s, "SELECT ID,NAME,JOINAMT,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,DUEPERIOD,JFEEACCOUNT,RENEWALACC,USAGEACC,MEMTYPE,DEBTMAX,TYPE_,SERVICETAX,APPTO FROM FACILITY WHERE ACTIVE =TRUE ORDER BY NAME", null, new SerializerReadClass(Facility.class)).list();
//    }
    
    public static NoticeMasterTableModel loadInstance(AppView app,int flag) throws BasicException{
      NoticeMasterTableModel d = new NoticeMasterTableModel();
         d.flag=flag;
         List dlist=new ArrayList();
         if(flag==1){
           dlist = new StaticSentence(app.getSession()
                ,"SELECT ID, NAME, DUE_PASS, DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID, SIGNIMGFLG FROM NOTICEMASTER WHERE ACTIVATE = TRUE order by CRDATE"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( NoticeMasterTableModel.NoticeMasterBean.class )).list();
         }else
         {
            dlist = new StaticSentence(app.getSession()
                ,"SELECT ID, NAME, DUE_PASS, DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT, ACCOUNTID,SIGNIMGFLG FROM NOTICEMASTER WHERE ACTIVATE = TRUE order by CRDATE"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( NoticeMasterTableModel.NoticeMasterBean.class )).list();
         }
         if(dlist==null)
         {
             d.fac=new ArrayList<NoticeMasterBean>();
         }
         else
        {
            d.fac=dlist;
        }



     return d;

  }
    
    public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeType(Session s2) throws BasicException {
    return new StaticSentence(s2, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID,SIGNIMGFLG  " + "FROM NOTICEMASTER  WHERE FINALNOTICE = FALSE AND ACTIVATE = TRUE AND ID NOT IN (SELECT PARENTID FROM NOTICEMASTER WHERE ACTIVATE = TRUE AND PARENTID IS NOT NULL ORDER BY CRDATE) ORDER BY CRDATE", SerializerWriteString.INSTANCE, new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list();
        }
    public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeType2(Session s2) throws BasicException {
    return new StaticSentence(s2, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID,SIGNIMGFLG " + "FROM NOTICEMASTER  WHERE FINALNOTICE = FALSE AND ACTIVATE = TRUE ORDER BY CRDATE", SerializerWriteString.INSTANCE, new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).list();
        }
    
    public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeListToDelete1(String id, Session s2) throws BasicException {
        List<NoticeMasterBean> allList = new ArrayList<NoticeMasterBean>();
        NoticeMasterBean noticeMasterBean = new NoticeMasterBean();
        noticeMasterBean.setId(id);
        do
        {
            noticeMasterBean = (NoticeMasterBean) new StaticSentence(s2, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER  , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID,  SIGNIMGFLG  " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND PARENTID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).find(new Object[]{noticeMasterBean.getId()});
            if(noticeMasterBean!=null)
            {
            allList.add(noticeMasterBean);
            }
        }
        while(noticeMasterBean!=null);
                
        return allList;
    }
    
    public final List<NoticeMasterTableModel.NoticeMasterBean> getNoticeListToDeactivate(String id, Session s2) throws BasicException {
        List<NoticeMasterBean> allList = new ArrayList<NoticeMasterBean>();
        NoticeMasterBean noticeMasterBean = new NoticeMasterBean();
        noticeMasterBean.setId(id);
        do
        {
            noticeMasterBean = (NoticeMasterBean) new StaticSentence(s2, "SELECT ID, NAME,DUE_PASS,DP_DM, DP_NUM, PAYMENT_DATE_DM, PAYMENT_DATE_NUM, ADDITIONALNOTES, TYPEOFMEM, FACILITY, FINALNOTICE, REFTOPARENT, PARENTID, CRDATE, SIGN, DESG, NOTICENAMETODISPLAY, DEACTIVATEMEM, LISTHEADER , POSTALCHRGFLAG , POSTALCHRGAMT , ACCOUNTID,SIGNIMGFLG " + "FROM NOTICEMASTER  WHERE ACTIVATE = TRUE AND PARENTID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(NoticeMasterTableModel.NoticeMasterBean.class)).find(new Object[]{noticeMasterBean.getId()});
            
            if(noticeMasterBean!=null)
            {
            allList.add(noticeMasterBean);
            }
        }
        while(noticeMasterBean!=null);
                
        return allList;
    }
    
    
    
    public List<NoticeMasterBean> getfacilityline()
     {
         return fac;
     }
    
   
    
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                if(flag==1)
                  return AppLocal.getIntString(TABLEHEADERS[column]);
                else
                  return AppLocal.getIntString(TABLEHEADERS1[column]);
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {
                if(flag==1)
                 return TABLEHEADERS.length;
                else
                 return TABLEHEADERS1.length;
            }
            public Object getValueAt(int row, int column) {
                NoticeMasterBean l = fac.get(row);

                switch (column) {

                case 0: return l.getName();
               // case 1: return l.getrperiod();
                case 1: 
                    if(l.isDue_pass()==false)
                    {
                        return "Due";
                    }
                    else
                    {
                    return "Pass Due";
                    }
                case 2: 
                    return l.getDp_num();
                    
                case 3: 
                    if(l.isDp_dm()==false && l.getDp_num()>1)
                    {
                        return "Days";
                    }
                    else if(l.isDp_dm()==false && l.getDp_num()<=1)
                    {
                        return "Day";
                    }
                    
                    else if(l.isDp_dm()==true && l.getDp_num()<=1)
                    {
                        return "Month";
                    }
                    else
                    {
                        return "Months";
                    }
                        
                        
                    
                case 4: return l.getPayment_date_num();
                case 5: 
                    if(l.isPayment_date_dm()==false && l.getPayment_date_num()>1)
                    {
                        return "Days";
                    }
                    else if(l.isPayment_date_dm()==false && l.getPayment_date_num()<=1)
                    {
                        return "Day";
                    }
                    
                    else if(l.isPayment_date_dm()==true && l.getPayment_date_num()<=1)
                    {
                        return "Month";
                    }
                    else
                    {
                        return "Months";
                    }
                    
                    
                case 6: return l.getAddNotes();
                case 7:return l.getTypeofmem().toString();
                case 8:return l.getFacility().toString();
                case 9: 
                    if(l.isFinalnotice()==true)
                    {
                        return "Yes";
                    }
                    else
                        return "No";
                case 10:
                    return l.getReferencetoparent();
                case 11: return l.getCrdate();
                
                case 12: return l.getSign();
                    case 13: return l.getDesg();
//                        case 14: return l.getDeactivateddate();
//                            case 15: return l.isActivate();
                default: return null;
                }
            }
        };
    }

    public static class NoticeMasterBean implements SerializableRead, IKeyed {

    private String id;
    private String name;
    private boolean due_pass;
    private boolean dp_dm;
    private int dp_num;
    private boolean payment_date_dm;
    private int payment_date_num;
    private String  addNotes;
    private String  typeofmem;
    private String  facility;
    private boolean finalnotice;
    private String referencetoparent;
    private String parentId;
    private Timestamp crdate;
    private String deactivatedby;
    private Timestamp deactivateddate;
    private boolean activate;
    private String sign;
    private String desg;
    private String noticeNameToDisplay;
    private boolean deactivateMem;
    private String headerToList;
    private boolean PostalChargeFlag;
    private Double PostalAmount;
    private String Accountid;
    private boolean  signImgFlg;

        public boolean isSignImgFlg() {
            return signImgFlg;
        }

        public void setSignImgFlg(boolean signImgFlg) {
            this.signImgFlg = signImgFlg;
        }
    
    
     public String getFacility() {
            return facility;
        }

        public void setFacility(String facility) {
            this.facility = facility;
        }
        
        public boolean isActivate() {
            return activate;
        }

        public String getDesg() {
            return desg;
        }

        public String getNoticeNameToDisplay() {
            return noticeNameToDisplay;
        }

        public void setNoticeNameToDisplay(String noticeNameToDisplay) {
            this.noticeNameToDisplay = noticeNameToDisplay;
        }

        public void setDesg(String desg) {
            this.desg = desg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
 
        public void setActivate(boolean activate) {
            this.activate = activate;
        }

        public String getParentId() {
            return parentId;
        }

        public String getDeactivatedby() {
            return deactivatedby;
        }

        public void setDeactivatedby(String deactivatedby) {
            this.deactivatedby = deactivatedby;
        }

        public Timestamp getDeactivateddate() {
            return deactivateddate;
        }

        public void setDeactivateddate(Timestamp deactivateddate) {
            this.deactivateddate = deactivateddate;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    
    
     
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        name = dr.getString(2);
        due_pass = dr.getBoolean(3);
        dp_dm = dr.getBoolean(4);
        dp_num = dr.getInt(5);
        payment_date_dm = dr.getBoolean(6);
        payment_date_num = dr.getInt(7);
        addNotes = dr.getString(8);
        typeofmem = dr.getString(9);
        facility = dr.getString(10);
        finalnotice = dr.getBoolean(11);
        referencetoparent = dr.getString(12);
        parentId = dr.getString(13);
        crdate = dr.getTimestamp(14);
        sign = dr.getString(15);
        desg = dr.getString(16);
        noticeNameToDisplay = dr.getString(17);
        deactivateMem = dr.getBoolean(18);
        headerToList = dr.getString(19);
        PostalChargeFlag = dr.getBoolean(20);
        PostalAmount = dr.getDouble(21);
        Accountid = dr.getString(22);
        signImgFlg=dr.getBoolean(23);
    }

        @Override
    public String toString(){
       return name;
    }
    
        public Timestamp getCrdate() {
            return crdate;
        }

        public void setCrdate(Timestamp crdate) {
            this.crdate = crdate;
        }

        public boolean isDeactivateMem() {
            return deactivateMem;
        }

        public void setDeactivateMem(boolean deactivateMem) {
            this.deactivateMem = deactivateMem;
        }

        public String getHeaderToList() {
            return headerToList;
        }

        public void setHeaderToList(String headerToList) {
            this.headerToList = headerToList;
        }

        


    public String getAddNotes() {
        return addNotes;
    }

    public boolean isDp_dm() {
        return dp_dm;
    }

    public int getDp_num() {
        return dp_num;
    }

    public boolean isDue_pass() {
        return due_pass;
    }

    public boolean isFinalnotice() {
        return finalnotice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPayment_date_dm() {
        return payment_date_dm;
    }

    public int getPayment_date_num() {
        return payment_date_num;
    }

    public String getReferencetoparent() {
        return referencetoparent;
    }

    public String getTypeofmem() {
        return typeofmem;
    }

    public void setAddNotes(String addNotes) {
        this.addNotes = addNotes;
    }

    public void setDp_dm(boolean dp_dm) {
        this.dp_dm = dp_dm;
    }

    public void setDp_num(int dp_num) {
        this.dp_num = dp_num;
    }

    public void setDue_pass(boolean due_pass) {
        this.due_pass = due_pass;
    }

    public void setFinalnotice(boolean finalnotice) {
        this.finalnotice = finalnotice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayment_date_dm(boolean payment_date_dm) {
        this.payment_date_dm = payment_date_dm;
    }

    public void setPayment_date_num(int payment_date_num) {
        this.payment_date_num = payment_date_num;
    }

    public void setReferencetoparent(String referencetoparent) {
        this.referencetoparent = referencetoparent;
    }

    public void setTypeofmem(String typeofmem) {
        this.typeofmem = typeofmem;
    }
    
   
    public Object getKey() {
        return this;
    }
    
    
    
    
    // edited by aakash 
    public boolean getPostalChargeFlag(){
        return PostalChargeFlag;
        
    }
    public void setPostalChargeFlag(boolean PostalChargeFlag){
        this.PostalChargeFlag = PostalChargeFlag;
    } 
    
    public Double getPostalAmount(){
        return PostalAmount;
    }
    public void setPostalAmount(Double PostalAmount){
        this.PostalAmount = PostalAmount;
    }
    
    
    public String getAccountid(){
        return Accountid;
    }
    public void setAccountid(String Accountid){
        this.Accountid = Accountid;
    }
    
    
    
}
    
    

    
}
