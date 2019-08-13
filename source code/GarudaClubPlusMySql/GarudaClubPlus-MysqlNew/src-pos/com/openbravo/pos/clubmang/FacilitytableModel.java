/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class FacilitytableModel {

    private List<Facilityline> fac;
    
    
    private int flag;
    private final static String[] FACILITYHEADERS = {"Name","HSN Code", "Joining Amt", "JFee Account", "Renewal Amt", "Period", "RFee Account", "Usage Amt", "Period", "UFee Account", "Entrance Check", "Due Period", "Member Type", "Debt max", "Type", "Created by", "Created Date", "Bill Seq."};
    private final static String[] FACILITYHEADERS1 = {"Name","HSN Code", "Joining Amt", "JFee Account", "Renewal Amt", "Period", "RFee Account", "Usage Amt", "Period", "UFee Account", "Entrance Check", "Due Period", "Member Type", "Debt max", "Type", "Created by", "Created Date", "Bill Seq.", "Deactivated By", "Deativated Date"};
    private Facility facLine;
    private List<Memtype> memtype;
    
    private FacilitytableModel() {
    }

    public static FacilitytableModel emptyinstance() {
        FacilitytableModel d = new FacilitytableModel();
        d.fac = new ArrayList<Facilityline>();
        return d;
    }

    public static FacilitytableModel loadInstance(AppView app, int flag) throws BasicException {
        FacilitytableModel d = new FacilitytableModel();
        d.flag = flag;
        List dlist;
        if (flag == 1) {
            dlist = new StaticSentence(app.getSession() //praveen:changed type to type_ //sameer:added billsequence also in select statement
                    , "SELECT F.ID,F.NAME,F.HSN_CODE,F.JOINAMT,CASE WHEN F.JOINAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.JFEEACCOUNT) END,CASE WHEN F.RENEWALAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.RENEWALACC) END,CASE WHEN F.USAGEAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.USAGEACC) END,F.RENEWALAMT,CASE WHEN F.RPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.RPERIODICITY) ELSE NULL END ,F.USAGEAMT,CASE WHEN F.UPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.UPERIODICITY) ELSE NULL END,F.ENTRANCECONTROL,F.CREATEDBY,F.CRDATE,CASE WHEN F.MEMTYPE='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE F.MEMTYPE=M.ID) END,F.DEBTMAX,F.DEACTIVATEDBY,F.DEACTIVATEDDATE,F.TYPE_,D.NAME,F.ACTIVE,F.BILLSEQUENCE " + " FROM FACILITY F,DEBTTYPE D WHERE D.ID=F.DUEPERIOD AND F.ACTIVE=TRUE ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilitytableModel.Facilityline.class)).list();
       
//        dlist = new StaticSentence(app.getSession() //praveen:changed type to type_ //sameer:added billsequence also in select statement
//                    , "SELECT F.ID,F.NAME,F.HSN_CODE,F.JOINAMT,CASE WHEN F.JOINAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.JFEEACCOUNT) END,CASE WHEN F.RENEWALAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.RENEWALACC) END,CASE WHEN F.USAGEAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.USAGEACC) END,F.RENEWALAMT,CASE WHEN F.RPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.RPERIODICITY) ELSE NULL END ,F.USAGEAMT,CASE WHEN F.UPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.UPERIODICITY) ELSE NULL END,F.ENTRANCECONTROL,F.CREATEDBY,F.CRDATE,CASE WHEN F.MEMTYPE='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE F.MEMTYPE=M.ID) END,F.DEBTMAX,F.DEACTIVATEDBY,F.DEACTIVATEDDATE,F.TYPE_,D.NAME,F.ACTIVE,F.BILLSEQUENCE,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASCADE2,F.BASIC1,F.BASIC2" + " FROM FACILITY F,DEBTTYPE D WHERE D.ID=F.DUEPERIOD AND F.ACTIVE=TRUE ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilitytableModel.Facilityline.class)).list();
//     
        
        } else {
            dlist = new StaticSentence(app.getSession(), "SELECT F.ID,F.NAME,F.HSN_CODE,F.JOINAMT,CASE WHEN F.JOINAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.JFEEACCOUNT) END,CASE WHEN F.RENEWALAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.RENEWALACC) END,CASE WHEN F.USAGEAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.USAGEACC) END,F.RENEWALAMT,CASE WHEN F.RPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.RPERIODICITY) ELSE NULL END ,F.USAGEAMT,CASE WHEN F.UPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.UPERIODICITY) ELSE NULL END,F.ENTRANCECONTROL,F.CREATEDBY,F.CRDATE,CASE WHEN F.MEMTYPE='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE F.MEMTYPE=M.ID) END,F.DEBTMAX,F.DEACTIVATEDBY,F.DEACTIVATEDDATE,F.TYPE_,D.NAME,F.ACTIVE,F.BILLSEQUENCE " + " FROM FACILITY F,DEBTTYPE D WHERE   D.ID=F.DUEPERIOD ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilitytableModel.Facilityline.class)).list();
//       dlist = new StaticSentence(app.getSession(), "SELECT F.ID,F.NAME,F.HSN_CODE,F.JOINAMT,CASE WHEN F.JOINAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.JFEEACCOUNT) END,CASE WHEN F.RENEWALAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.RENEWALACC) END,CASE WHEN F.USAGEAMT<1 THEN NULL ELSE (SELECT A.NAME FROM ACCOUNTMASTER A WHERE A.ID=F.USAGEACC) END,F.RENEWALAMT,CASE WHEN F.RPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.RPERIODICITY) ELSE NULL END ,F.USAGEAMT,CASE WHEN F.UPERIODICITY IS NOT NULL  THEN  (SELECT P.NAME FROM PERIODICITY P WHERE P.ID=F.UPERIODICITY) ELSE NULL END,F.ENTRANCECONTROL,F.CREATEDBY,F.CRDATE,CASE WHEN F.MEMTYPE='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE F.MEMTYPE=M.ID) END,F.DEBTMAX,F.DEACTIVATEDBY,F.DEACTIVATEDDATE,F.TYPE_,D.NAME,F.ACTIVE,F.BILLSEQUENCE,F.TAXCAT_2,F.TAXCAT_3,F.CASCADE1,F.CASCADE2,F.BASIC1,F.BASIC2 " + " FROM FACILITY F,DEBTTYPE D WHERE   D.ID=F.DUEPERIOD ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilitytableModel.Facilityline.class)).list();
//     
        }
        if (dlist == null) {
            d.fac = new ArrayList<Facilityline>();
        } else {
            d.fac = dlist;                                 
        }

        return d;

    }

    public static FacilitytableModel loadInstanceToEdit(AppView app, String id) throws BasicException {
        FacilitytableModel d = new FacilitytableModel();
        Facility dlist;
        dlist = (Facility) new StaticSentence(app.getSession(),
                "SELECT ID,NAME,HSN_CODE,JOINAMT,JFEEACCOUNT,RENEWALACC,USAGEACC,RENEWALAMT,RPERIODICITY,USAGEAMT,UPERIODICITY,ENTRANCECONTROL,CREATEDBY,CRDATE,MEMTYPE,AMOUNTLIMIT,DEACTIVATEDBY,DEACTIVATEDDATE,TYPE_,DUEPERIOD,ACTIVE,SMSFORM,APPTO,INITCONTROL,CONFIRMCONTROL,GRACEPERIOD,BILLSEQUENCE,SERVICETAX,DEBTMAX,MAXBILLNO,TAXCAT_2,TAXCAT_3,CASCADE1,CASCADE2,BASIC1,BASIC2 FROM FACILITY WHERE ID=?",
                SerializerWriteString.INSTANCE, new SerializerReadClass(FacilitytableModel.Facility.class)).find(id);
//         for(int i=0;i<=dlist.size();i++){
//System.out.println("list values are::"+dlist.getBasic1());
//System.out.println("list values are::"+dlist.getBasic2());
//            System.out.println("list values are::"+dlist.getCascade1());
//             System.out.println("list values are::"+dlist.getCascade2());
//       }
//  System.out.println("list values are::"+dlist.taxcat_2);
//  System.out.println("list values are::"+dlist.taxcat_3);
        if (dlist == null) {
            d.facLine = new Facility();
        } else {
            d.facLine = dlist;
//            System.out.println(""+dlist.getramt());
        }
        return d;
    }

    public static FacilitytableModel loadMemtype(AppView app, StringBuffer str, Object[] params, Datas[] data) throws BasicException {
        FacilitytableModel d = new FacilitytableModel();
        List mlist;
        mlist = new StaticSentence(app.getSession(), "SELECT NAME FROM MEMTYPE WHERE ID IN (" + str.toString() + ") ORDER BY NAME", new SerializerWriteBasic(data), new SerializerReadClass(FacilitytableModel.Memtype.class)).list(params);
        if (mlist == null) {
            d.memtype = new ArrayList<Memtype>();
        } else {
            d.memtype = mlist;
        }
        return d;
    }

    public List<Memtype> getMemType() {
        return memtype;
    }

    public Facility getFacility() {
        return facLine;
    }

    public List<Facilityline> getfacilityline() {
        return fac;
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                if (flag == 1) {
                    return AppLocal.getIntString(FACILITYHEADERS[column]);
                } else {
                    return AppLocal.getIntString(FACILITYHEADERS1[column]);
                }
            }

            public int getRowCount() {
                return fac.size();
            }

            public int getColumnCount() {
                if (flag == 1) {
                    return FACILITYHEADERS.length;
                } else {
                    return FACILITYHEADERS1.length;
                }
            }

            public Object getValueAt(int row, int column) {
                Facilityline l = fac.get(row);

                switch (column) {
                    case 0:
                        return l.getname();
                    case 1:
                        return l.getHsncode();
                    case 2:
                        return l.getjamt();
                    case 3:
                        return l.getJaccid();
                    case 4:
                        return l.getramt();
                    case 5:
                        return l.getrperiod();
                    case 6:
                        return l.getRaccid();
                    case 7:
                        return l.getuamt();
                    case 8:
                        return l.getuperiod();
                    case 9:
                        return l.getUaccid();
                    case 10:
                        return l.getecheck();
                    case 11:
                        return l.getDuePeriod();
                    case 12:
                        return l.getMemType();
                    case 13:
                        return l.getMaxDebt();
                    case 14:
                        return l.getType();
                    case 15:
                        return l.getcrby();
                    case 16:
                        return l.getdate();
                    case 17:
                        return l.getBillseq();
                    case 18:
                        return l.getDeactivatedby();
                    case 19:
                        return l.getDeactivatedDate();
                    case 20:
                        return l.getid();
                    case 21:
                        return l.isActive();

                    default:
                        return null;
                }
            }
        };
    }

    public static class Facilityline implements SerializableRead {

        private String id;
        private String name;
        private String hsncode;
        private Double jamt;
        private Double ramt;
        private Double uamt;
        private String rperiod;
        private String uperiod;
        private String createdby;
        private Timestamp crdate;
        private boolean echeck;
        private String deactivatedby;
        private Timestamp deactivateddate;
        private String memtype;
        private Double maxdebt;
        private String type;
        private String jaccid;
        private String raccid;
        private String uaccid;
        private String dueperiodname;
        private boolean active;
        private String billseq;
//           private String taxcat_2;
//              private String taxcat_3;
//              private boolean cascade1;
//              private boolean cascade2;
//              private boolean basic1;
//              private boolean basic2;

        public void readValues(DataRead dr) throws BasicException {

            id = dr.getString(1);
            name = dr.getString(2);
            hsncode=dr.getString(3);
            jamt = dr.getDouble(4);
            jaccid = dr.getString(5);
            raccid = dr.getString(6);
            uaccid = dr.getString(7);
            ramt = dr.getDouble(8);
            rperiod = dr.getString(9);
            uamt = dr.getDouble(10);
            uperiod = dr.getString(11);
            echeck = dr.getBoolean(12);
            createdby = dr.getString(13);
            crdate = dr.getTimestamp(14);
            memtype = dr.getString(15);
            maxdebt = dr.getDouble(16);
            deactivatedby = dr.getString(17);
            deactivateddate = dr.getTimestamp(18);
            type = dr.getString(19);
            dueperiodname = dr.getString(20);
            active = dr.getBoolean(21);
            billseq = dr.getString(22);
//            taxcat_2=dr.getString(23);
//             taxcat_3=dr.getString(24);
//             cascade1=dr.getBoolean(25);
//             cascade2=dr.getBoolean(26);
//             basic1=dr.getBoolean(27);
//             basic2=dr.getBoolean(28);
             
            
        }

        public String getHsncode() {
            return hsncode;
        }

//        public void setHsncode(String hsncode) {
//            this.hsncode = hsncode;
//        }

        public String getBillseq() {
            return billseq;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getDuePeriod() {
            return dueperiodname;
        }

        public String getType() {
            return type;
        }

        public String getJaccid() {
            return jaccid;
        }

        public String getRaccid() {
            return raccid;
        }

        public String getUaccid() {
            return uaccid;
        }

        public String getMemType() {
            return memtype;
        }

        public Double getMaxDebt() {
            return maxdebt;
        }

        public String getid() {
            return id;
        }

        public String getDeactivatedby() {
            return deactivatedby;
        }

        public Timestamp getDeactivatedDate() {
            return deactivateddate;
        }

        public boolean getecheck() {
            return echeck;
        }

        public String getcrby() {
            return createdby;
        }

        public Timestamp getdate() {
            return crdate;
        }

        public String getname() {
            return name;
        }

        public Double getjamt() {
            return jamt;
        }

        public Double getramt() {
            return ramt;
        }

        public Double getuamt() {

            return uamt;
        }

        public String getrperiod() {
            return rperiod;
        }

        public String getuperiod() {
            return uperiod;
        }
//        public String getTaxcat_2(){
//            return taxcat_2;
//        }
//         public String getTaxcat_3(){
//            return taxcat_3;
//        }
//         public Boolean getCascade1(){
//            return cascade1;
//        }
//          public Boolean getCascade2(){
//            return cascade2;
//        }
//           public Boolean getBasic1(){
//            return basic1;
//        }
//            public Boolean getBasic2(){
//            return basic2;
//        }
    }

    public static class Facility implements SerializableRead {

        private String id;
        private String name;
        private String hsncode;
        private Double jamt;
        private Double ramt;
        private Double uamt;
        private String rperiod;
        private String uperiod;
        private String createdby;
        private Timestamp crdate;
        private boolean echeck;
        private String deactivatedby;
        private Timestamp deactivateddate;
        private String memtype;
        private Double maxdebt;
        private String type;
        private String jaccid;
        private String raccid;
        private String uaccid;
        private String dueperiodname;
        private boolean active;
        private String smsform;
        private int appto;
        private boolean init;
        private boolean conf;
        private int grace;
        private String billseq;
        private String servicetax;
        private double debtmax;
        private int maxbill;
         private String taxcat_2;
          private String taxcat_3;
          private boolean cascade1;
            private boolean cascade2;
            private boolean basic1;
            private boolean basic2;

        public void readValues(DataRead dr) throws BasicException {

            id = dr.getString(1);
            name = dr.getString(2);
            hsncode=dr.getString(3);
            jamt = dr.getDouble(4);
            jaccid = dr.getString(5);
            raccid = dr.getString(6);
            uaccid = dr.getString(7);
            ramt = dr.getDouble(8);
            rperiod = dr.getString(9);
            uamt = dr.getDouble(10);
            uperiod = dr.getString(11);
            echeck = dr.getBoolean(12);
            createdby = dr.getString(13);
            crdate = dr.getTimestamp(14);
            memtype = dr.getString(15);
            if (dr.getDouble(16) == null) {
                maxdebt = 0.0;
            } else {
                maxdebt = dr.getDouble(16);
            }
            deactivatedby = dr.getString(17);
            deactivateddate = dr.getTimestamp(18);
            type = dr.getString(19);
            dueperiodname = dr.getString(20);
            active = dr.getBoolean(21);
            smsform = dr.getString(22);
            appto = dr.getInt(23);
            init = dr.getBoolean(24);
            conf = dr.getBoolean(25);
            if (dr.getInt(26) == null) {
                grace = 0;
            } else {
                grace = dr.getInt(26);
            }
            billseq = dr.getString(27);
            servicetax = dr.getString(28);
            debtmax = dr.getDouble(29);
            maxbill = dr.getInt(30);
            taxcat_2=dr.getString(31);
            taxcat_3=dr.getString(32);
            cascade1=dr.getBoolean(33);
             cascade2=dr.getBoolean(34);
             basic1=dr.getBoolean(35);
             basic2=dr.getBoolean(36);
            
            
        }

        public void setMaxdebt(Double maxdebt) {
            this.maxdebt = maxdebt;
        }

        public int getMaxbill() {
            return maxbill;
        }

        public Double getdebtmax() {
            return debtmax;
        }

        public String getServicetax() {
//              System.out.println("taxcat_1"+servicetax);
            return servicetax;
        }
         public void setServicetax(String servicetax) {
//              System.out.println("taxcat_1"+servicetax);
            this.servicetax=servicetax;
        }

        public String getBillseq() {
            return billseq;
        }

        public int getGrace() {
            return grace;
        }

        public boolean isConf() {
            return conf;
        }

        public void setConf(boolean conf) {
            this.conf = conf;
        }

        public boolean isInit() {
            return init;
        }

        public void setInit(boolean init) {
            this.init = init;
        }

        public int getAppto() {
            return appto;
        }

        public void setAppto(int appto) {
            this.appto = appto;
        }

        public String getSmsform() {
            return smsform;
        }

        public void setSmsform(String smsform) {
            this.smsform = smsform;
        }

        public String getHsncode() {
            return hsncode;
        }

        public void setHsncode(String hsncode) {
            this.hsncode = hsncode;
        }
        
        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getDuePeriod() {
            return dueperiodname;
        }

        public String getType() {
            return type;
        }

        public String getJaccid() {
            return jaccid;
        }

        public String getRaccid() {
            return raccid;
        }

        public String getUaccid() {
            return uaccid;
        }

        public String getMemType() {
            return memtype;
        }

        public Double getMaxDebt() {
            return maxdebt;
        }

        public String getid() {
            return id;
        }

        public String getDeactivatedby() {
            return deactivatedby;
        }

        public Timestamp getDeactivatedDate() {
            return deactivateddate;
        }

        public boolean getecheck() {
            return echeck;
        }

        public String getcrby() {
            return createdby;
        }

        public Timestamp getdate() {
            return crdate;
        }

        public String getname() {
            return name;
        }

        public Double getjamt() {
            return jamt;
        }

        public Double getramt() {
            return ramt;
        }

        public Double getuamt() {

            return uamt;
        }

        public String getrperiod() {
            return rperiod;
        }

        public String getuperiod() {
            return uperiod;
        }

        public void setJamt(Double jamt) {
            this.jamt = jamt;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRamt(Double ramt) {
            this.ramt = ramt;
        }

        public void setUamt(Double uamt) {
            this.uamt = uamt;
        }
        public String getTaxcat_2() {
//            System.out.println("taxcat_2"+taxcat_2);
            return taxcat_2;
        }
          public void setTaxcat_2(String taxcat_2) {
//            System.out.println("taxcat_2"+taxcat_2);
//            return taxcat_2;
           this.taxcat_2 = taxcat_2;
        }
        public String getTaxcat_3() {
//              System.out.println("taxcat_3"+taxcat_3);
            return taxcat_3;
        }
         public void setTaxcat_3(String taxcat_3) {
//            System.out.println("taxcat_2"+taxcat_2);
//            return taxcat_2;
           this.taxcat_3 = taxcat_3;
        }
         public Boolean getCascade1() {
//              System.out.println("taxcat_3"+taxcat_3);
System.out.println("cascade1"+cascade1);
            return cascade1;
        }
         public void setCascade1(Boolean cascade1) {
//           System.out.println("taxcat_2"+taxcat_2);
//            return taxcat_2;
           this.cascade1 = cascade1;
        }
         public Boolean getCascade2() {
             System.out.println("cascade2"+cascade2);
            return cascade2;
        }
         public void setCascade2(Boolean cascade2) {
//            System.out.println("taxcat_2"+taxcat_2);
//            return taxcat_2;

           this.cascade2 = cascade2;
        }
          public Boolean getBasic1() {
//              System.out.println("taxcat_3"+taxcat_3);
System.out.println("basic1"+basic1);
            return basic1;
        }
         public void setBasic1(Boolean basic1) {
//            System.out.println("taxcat_2"+taxcat_2);
//            return taxcat_2;
           this.basic1 = basic1;
        }
          public Boolean getBasic2() {
//              System.out.println("taxcat_3"+taxcat_3);
System.out.println("basic2"+basic2);
            return basic2;
        }
         public void setBasic2(Boolean basic2) {
//            System.out.println("taxcat_2"+taxcat_2);
//            return taxcat_2;
           this.basic2 = basic2;
        }
    }

    public static class Memtype implements SerializableRead {

        private String name;

        public void readValues(DataRead dr) throws BasicException {
            name = dr.getString(1);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
