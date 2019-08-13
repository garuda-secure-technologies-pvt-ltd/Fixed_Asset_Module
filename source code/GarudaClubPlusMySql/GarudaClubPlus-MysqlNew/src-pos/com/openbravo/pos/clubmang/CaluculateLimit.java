package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaluculateLimit {

    private   List<ActiveMembers> memberList;
    private   List<FacilityList> facilityList;
    private   List<deactivationFacilityList> deactfaclist;
    public static  final String DATE_FORMAT = "dd/MM/yyyy";
    //CaluculateLimit cl = new CaluculateLimit();

    /*public List<ActiveMembers> getAllMembers(Session s) throws BasicException {

    memberList = new PreparedSentence(s, "SELECT C.ID,C.SEARCHKEY,C.ACCOUNT FROM CUSTOMERS C WHERE C.VISIBLE=TRUE ORDER BY C.SEARCHKEY", null, new SerializerReadClass(ActiveMembers.class)).list();
    return memberList;
    }*/
    public   List<FacilityList> getAllFacilityToAllMember(Session s) throws BasicException {

        facilityList = new StaticSentence(s, "SELECT C.ID,C.SEARCHKEY,C.ACCOUNT,F.ID,F.NAME,F.AMOUNTLIMIT,F.GRACEPERIOD FROM FACILITY F,MEMFACILITYUSAGE M,CUSTOMERS C WHERE F.ID=M.FACILITYTYPE AND M.MEMNO=C.ID AND M.ACTIVE=TRUE AND F.ACTIVE=TRUE AND C.VISIBLE=TRUE ORDER BY C.SEARCHKEY",
                null,
                new SerializerReadClass(FacilityList.class)).list();
        return facilityList;

    }

    public   List<FacilityList> getAllFacilityToSpecificMember(Session s, String memId) throws BasicException {

        facilityList = new StaticSentence(s, "SELECT C.ID,C.SEARCHKEY,C.ACCOUNT,F.ID,F.NAME,F.AMOUNTLIMIT,F.GRACEPERIOD FROM FACILITY F,MEMFACILITYUSAGE M,CUSTOMERS C WHERE F.ID=M.FACILITYTYPE AND M.MEMNO=C.ID AND c.id=? AND M.ACTIVE=TRUE AND F.ACTIVE=TRUE AND C.VISIBLE=TRUE",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(FacilityList.class)).list(memId);
        return facilityList;

    }

    public   void calculateFacilityLimitToMemberAndInsert(Session s, String memid, String accid) {
        try {
            calculate(s, memid, false);
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
    }

    public   void calculateFacilityLimitToMemberAndUpdate(Session s, String memid, String accid) {
        try {
            calculate(s, memid, true);
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
    }

    public   void calculate(Session s, String memid, Boolean bool) throws BasicException {
        try {
            Double amount = null;
            Double camount = null;
            Boolean flag = null;
            Date insertdate = new Date();
            Date date1 = new Date();
            Date date = new Date();
            CaluculateLimit cl = new CaluculateLimit();
            if (memid == null) {
                cl.facilityList = getAllFacilityToAllMember(s);
            } else {
                cl.facilityList = getAllFacilityToSpecificMember(s, memid);
            }
            for (FacilityList fl : facilityList) {
                 amount = fl.getAmountLimit();
               camount = null;
                 flag = null;                 
                Calendar now = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                //System.out.println(fl.getFName() + "-" + fl.getAmountLimit() + "-" + fl.getGracePeriod());

                if (fl.getGracePeriod() != null) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    now.add(Calendar.DATE, -fl.getGracePeriod());
                    now.set(Calendar.HOUR_OF_DAY, 00);
                    now.set(Calendar.MINUTE, 00);
                    now.set(Calendar.SECOND, 00);
                    now.set(Calendar.MILLISECOND, 00);
                    //now.getTime();
                    String date2 = sdf.format(now.getTime());
                    date1 = df.parse(date2);
                //System.out.println(date1);
                } else {
                    date1 = date;
                }
                if (amount != null) {
                    Object[] obj = (Object[]) new StaticSentence(s, "SELECT SUM(A.BALANCEAMOUNT) FROM ACCOUNTJOURNAL A WHERE A.TRANSREF=? AND A.ACCOUNTID=? AND DATE<=? AND ACTIVE = TRUE ",
                            new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{fl.getFId(), fl.getAccount(), date1});
                    if (obj != null) {
                        if (obj[0] != null) {
                            camount = (Double) obj[0];
                            if (camount > amount) {
                                flag = false;
                                amount = camount;
                            //System.out.println(camount);
                            } else {
                                flag = true;
                                amount = camount;
                            }
                        } else {
                            amount = 0.0;
                            flag = true;
                        }
                    }
                } else {
                    amount = 0.0;
                    flag = true;
                }

                cl.deactfaclist = getAllDeactivationFacilities(s, fl.getFId(), fl.getCid());

                if (deactfaclist != null) {

                    for (deactivationFacilityList d : deactfaclist) {
                        Object[] value = new Object[]{fl.getCid(), d.getPfid(), d.getFacid(), flag, amount, insertdate};
                        if (bool == true) {
                            updateFacilityLimitMaster(s, value);
                        } else {
                            insertFacilityLimitMaster(s, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println(memid+"----------");
            e.printStackTrace();
        }
    }

    public   List<deactivationFacilityList> getAllDeactivationFacilities(Session s, String pid, String memno) throws BasicException {

        Object[] val = new Object[]{memno, pid};

        deactfaclist = new PreparedSentence(s, "SELECT D.DFACID,F.NAME,D.PFID FROM FACILITYDEACTIVATIONMASTER D,FACILITY F WHERE D.DFACID IN( SELECT M.FACILITYTYPE  FROM MEMFACILITYUSAGE M,CUSTOMERS C WHERE M.MEMNO=? AND M.ACTIVE=TRUE) AND D.PFID=? AND F.ID=D.DFACID",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}),
                new SerializerReadClass(deactivationFacilityList.class)).list(val);


        return deactfaclist;
    }

    public   void insertFacilityLimitMaster(Session s, Object[] value) throws BasicException {
        try {
            new PreparedSentence(s, "INSERT INTO FACILITYLIMITMASTER(MEMID,PFID,FACID,ELIGEBLE,AMOUNT,CALDATE) VALUES(?,?,?,?,?,?)",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.DOUBLE, Datas.TIMESTAMP})).exec(value);
        } catch (Exception e) {
            System.out.println(value[0] + "----------" + value[1] + "---------" + value[2]);
            e.printStackTrace();

        }
    }

    public   void updateFacilityLimitMaster(Session s, Object[] value) throws BasicException {
        String mid = (String) value[0];
        String pfid = (String) value[1];
        String fid = (String) value[2];
        Boolean flag = (Boolean) value[3];
        Double amount = (Double) value[4];
        Date date = (Date) value[5];
        Object[] res = new Object[]{flag, amount, date, mid, fid, pfid};
        try {
            new PreparedSentence(s, "UPDATE FACILITYLIMITMASTER SET ELIGEBLE=?,AMOUNT=?,CALDATE=? WHERE MEMID=? AND FACID=? AND PFID=?",
                    new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(res);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public   void calculateFacilityLimitToAll(Session session) {
        try {
            //CaluculateLimit cl = new CaluculateLimit();
            //cl.memberList = getAllMembers(app.getSession());
            //for (ActiveMembers m : memberList) {

            //System.out.println(m.getSearchKey() + "----------");
            calculate(session, null, false);
        //caluculateFacilityLimitToAll(app);
        // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static class ActiveMembers implements SerializableRead {

        private String mId;
        private String searchKey;
        private String accountId;

        public String getAccountId() {
            return accountId;
        }

        public String getMId() {
            return mId;
        }

        public String getSearchKey() {
            return searchKey;
        }

        public void readValues(DataRead dr) throws BasicException {
            mId = dr.getString(1);
            searchKey = dr.getString(2);
            accountId = dr.getString(3);

        }
    }

    public static class FacilityList implements SerializableRead {

        private String cid;
        private String csearchkey;
        private String account;
        private String fId;
        private String fName;
        private Double amountLimit;
        private Integer gracePeriod;

        public String getAccount() {
            return account;
        }

        public String getCid() {
            return cid;
        }

        public String getCsearchkey() {
            return csearchkey;
        }

        public Integer getGracePeriod() {
            return gracePeriod;
        }

        public Double getAmountLimit() {
            return amountLimit;
        }

        public String getFId() {
            return fId;
        }

        public String getFName() {
            return fName;
        }

        public void readValues(DataRead dr) throws BasicException {
            cid = dr.getString(1);
            csearchkey = dr.getString(2);
            account = dr.getString(3);
            fId = dr.getString(4);
            fName = dr.getString(5);
            amountLimit = dr.getDouble(6);
            gracePeriod = dr.getInt(7);

        }
    }

    public static class deactivationFacilityList implements SerializableRead {

        private String facid;
        private String fname;
        private String pfid;

        public void readValues(DataRead dr) throws BasicException {
            facid = dr.getString(1);
            fname = dr.getString(2);
            pfid = dr.getString(3);
        }

        public String getPfid() {
            return pfid;
        }

        public String getFacid() {
            return facid;
        }

        public String getFname() {
            return fname;
        }
    }
    
///aaa
    public void calculateLimit1(Session s) throws BasicException{
        try {
            new PreparedSentence(s, "DELETE FROM BILLINGMEMBER WHERE ID NOT IN (SELECT CID FROM SHAREDTICKETS)").exec();
          // new StaticSentence(s, " DELETE FROM BILLINGMEMBER WHERE ID NOT IN (SELECT CID FROM SHAREDTICKETS)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
///aaa
}

