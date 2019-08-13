/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
//import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
//import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
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
public class FacilityRequestTableModel {

    private List<RequestLine> requestlist;
    private List<MinUsageRequestLine> minUsageRequestlist;
    private final static String[] Header = {"Member Name", "Search key", "Facility name", "Request for", "From ", "To ", "Reason"};
    private final static String[] Header1 = {"Member Name", "Search key", "Min Usage Type", "Initiated By", "Initiated Date"};

    public static FacilityRequestTableModel emptyinstance() {
        FacilityRequestTableModel mc = new FacilityRequestTableModel();
        mc.requestlist = new ArrayList<RequestLine>();

        return mc;
    }

    public static FacilityRequestTableModel loadInstance(AppView app) throws BasicException {
        FacilityRequestTableModel mc = new FacilityRequestTableModel();
        List<FacilityRequestTableModel.RequestLine> list = new StaticSentence(app.getSession(),
                " SELECT C.NAME,C.SEARCHKEY,F.NAME,FM.TYPE_,FM.FROM_,FM.TO_,FM.MESSAGE,M.ID,F.ID,C.ID,C.ACCOUNT,M.SDATE,M.EDATE,FM.ID,F.RPERIODICITY,M.USERID,CASE WHEN M.USERID IS NULL THEN NULL ELSE (SELECT D.DNAME FROM MEMDEPENDENT D WHERE D.ID=M.USERID) END FROM FACILITYMANAGER FM JOIN MEMFACILITYUSAGE M ON M.FACMANGREF=FM.ID JOIN CUSTOMERS C ON M.MEMNO=C.ID JOIN FACILITY F ON  M.FACILITYTYPE=F.ID  ", null, new SerializerReadClass(FacilityRequestTableModel.RequestLine.class)).list();

        if (list == null) {
            mc.requestlist = new ArrayList<FacilityRequestTableModel.RequestLine>();
        } else {
            mc.requestlist = list;
        }

        List<FacilityRequestTableModel.MinUsageRequestLine> minUsagelist = new StaticSentence(app.getSession(),
                " SELECT C.NAME,C.SEARCHKEY,M.NAME,FM.INITIATEDBY,FM.INITIATEDDATE,MM.ID,FM.ID FROM FACILITYMANAGER FM,CUSTOMERS C,MINUSAGE M,MEMMINUSAGE MM WHERE FM.STATUS_ IS NULL AND FM.MFUID=MM.ID AND MM.MEMNO=C.ID AND MM.USAGETYPE=M.ID  ", null, new SerializerReadClass(FacilityRequestTableModel.MinUsageRequestLine.class)).list();

        //SELECT C.NAME,C.SEARCHKEY,M.NAME,FM.INITIATEDBY,FM.INITIATEDDATE FROM FACILITYMANAGER FM,CUSTOMERS C,MINUSAGE M,MEMMINUSAGE MM WHERE FM.STATUS IS NULL AND FM.MFUID=MM.ID AND MM.MEMNO=C.ID AND MM.USAGETYPE=M.ID

         if (minUsagelist == null) {
            mc.minUsageRequestlist = new ArrayList<FacilityRequestTableModel.MinUsageRequestLine>();
        } else {
            mc.minUsageRequestlist = minUsagelist;
        }
        return mc;
    }

    public List<FacilityRequestTableModel.RequestLine> getRequestList() {
        return requestlist;
    }

     public List<FacilityRequestTableModel.MinUsageRequestLine> getMinUsageRequestLine() {
        return minUsageRequestlist;
    }

     public AbstractTableModel getMinUsageTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(Header1[column]);
            }

            public int getRowCount() {
                return minUsageRequestlist.size();
            }

            public int getColumnCount() {
                return Header1.length;
            }

            public Object getValueAt(int row, int column) {
                FacilityRequestTableModel.MinUsageRequestLine cd = minUsageRequestlist.get(row);
                switch (column) {
                    case 0 : return cd.getCname();
                    case 1 : return cd.getCsKey();
                    case 2 : return cd.getMinusage();
                    case 3 : return cd.getInitiatedBy();
                    case 4 : return cd.getInitiatedDate();
                    case 5 : return  cd.getMmuid();
                    case 6 : return cd.getFmid();
                    default:
                        return null;
                }
            }
        };
    }


    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(Header[column]);
            }

            public int getRowCount() {
                return requestlist.size();
            }

            public int getColumnCount() {
                return Header.length;
            }

            public Object getValueAt(int row, int column) {
                FacilityRequestTableModel.RequestLine cd = requestlist.get(row);
                switch (column) {
                    case 0:
                        return cd.getCname();
                    case 1:
                        return cd.getSearchkey();
                    case 2:
                        return cd.getFname();
                    case 3:
                        switch (cd.getStatus()) {
                            case 3:
                                return "Stop the facility ";
                            case 4:
                                return "Suspension of the facility";
                            case 5:
                                return "Start the facility";
                            default:
                                return null;//return l.getStatus();
                        }
                    case 4:
                        switch (cd.getStatus()) {
                            case 3:
                                return Formats.DATE.formatValue(cd.getEdate());
                            case 4:
                                return Formats.DATE.formatValue(cd.getFromdate());
                            case 5:
                                return Formats.DATE.formatValue(cd.getSdate());
                            default:
                                return null;//return l.getStatus();
                        }

                    case 5:
                        return Formats.DATE.formatValue(cd.getTodate());
                    case 6:
                        return cd.getReason();
                    case 7:
                        return cd.getId();
                    case 8:
                        return cd.getStatus();
                    case 9:
                        return cd.getfid();
                    case 10:
                        return cd.getcid();
                    case 11:
                        return cd.getCaccount();
                    case 12:
                        return cd.getSdate();
                    case 13:
                        return cd.getEdate();

                    default:
                        return null;
                }
            }
        };
    }

    public static class MinUsageRequestLine implements SerializableRead {
//C.NAME,C.SEARCHKEY,M.NAME,FM.INITIATEDBY,FM.INITIATEDDATE

        private String cname;
        private String csKey;
        private String minusage;
        private String initiatedBy;
        private Timestamp initiatedDate;
        private String memMinUsage;
        private String mmuid;
        private String fmid;

        public void readValues(DataRead dr) throws BasicException {
            cname = dr.getString(1);
            csKey = dr.getString(2);
            minusage = dr.getString(3);
            initiatedBy = dr.getString(4);
            initiatedDate = dr.getTimestamp(5);
            mmuid = dr.getString(6);
            fmid = dr.getString(7);
        }

       
        public String getFmid() {
            return fmid;
        }

        public String getMmuid() {
            return mmuid;
        }

        public String getMemMinUsage() {
            return memMinUsage;
        }

        public void setMemMinUsage(String memMinUsage) {
            this.memMinUsage = memMinUsage;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCsKey() {
            return csKey;
        }

        public void setCsKey(String csKey) {
            this.csKey = csKey;
        }

        public String getInitiatedBy() {
            return initiatedBy;
        }

        public void setInitiatedBy(String initiatedBy) {
            this.initiatedBy = initiatedBy;
        }

        public Timestamp getInitiatedDate() {
            return initiatedDate;
        }

        public void setInitiatedDate(Timestamp initiatedDate) {
            this.initiatedDate = initiatedDate;
        }

        public String getMinusage() {
            return minusage;
        }

        public void setMinusage(String minusage) {
            this.minusage = minusage;
        }



    }

    public static class RequestLine implements SerializableRead {

        private String cname;
        private String searchkey;
        private String fname;
        private int status;
        private Timestamp fromdate;
        private Timestamp todate;
        private String reason;
        private String id;
        private String fid;
        private String cid;
        private String caccount;
        private Timestamp sdate;
        private Timestamp edate;
        private String facMangID;
        private String rperiod;
        private String userid;
        private String username;

        public void readValues(DataRead dr) throws BasicException {
            cname = dr.getString(1);
            searchkey = dr.getString(2);
            fname = dr.getString(3);
            status = dr.getInt(4);
            fromdate = dr.getTimestamp(5);
            todate = dr.getTimestamp(6);
            reason = dr.getString(7);
            id = dr.getString(8);
            fid = dr.getString(9);
            cid = dr.getString(10);
            caccount = dr.getString(11);
            sdate = dr.getTimestamp(12);
            edate = dr.getTimestamp(13);
            facMangID = dr.getString(14);
            rperiod = dr.getString(15);
            userid = dr.getString(16);
            username = dr.getString(17);
        }

        public String getRenewalPeriodicty() {
            return rperiod;
        }

        public String getUserID() {
            return userid;
        }

        public String getUserName() {
            return username;
        }

        public String getFacilityManagerID() {
            return facMangID;
        }

        public Timestamp getEdate() {
            return edate;
        }

        public Timestamp getSdate() {
            return sdate;
        }

        public String getCaccount() {
            return caccount;
        }

        public String getSearchkey() {
            return searchkey;
        }

        public String getCname() {
            return cname;
        }

        public String getFname() {
            return fname;
        }

        public int getStatus() {
            return status;
        }

        public Timestamp getFromdate() {
            return fromdate;
        }

        public String getfid() {
            return fid;
        }

        public String getcid() {
            return cid;
        }

        public Timestamp getTodate() {
            return todate;
        }

        public String getReason() {
            return reason;
        }

        public String getId() {
            return id;
        }

        public String getFacilityId() {
            return fid;
        }
    }
}
