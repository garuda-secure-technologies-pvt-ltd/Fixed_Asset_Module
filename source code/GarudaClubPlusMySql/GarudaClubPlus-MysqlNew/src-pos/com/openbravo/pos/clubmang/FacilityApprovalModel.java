

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author praveen
 */
public class FacilityApprovalModel {
    private List<FacilityApprovalLine> falist;
    private final static String[] APPROVALHEADERS = {"Member No","Member Name","Facility Name","Date","Amount","Status","Aut.By","Aut.Date","Init.Reason","Aut.Reason"};
    private final static String[] APPROVALHEADERS1 = {"Member No","Member Name","Facility Name","Date","Amount","Init.Reason"};

    private FacilityApprovalModel(){
        
    }
    public static FacilityApprovalModel emptyinstance(){
        FacilityApprovalModel f=new FacilityApprovalModel();
        f.falist=new ArrayList<FacilityApprovalLine>();
        return f;
    }
    public static FacilityApprovalModel loadinstance(AppView app,String user) throws BasicException{
        FacilityApprovalModel f=new FacilityApprovalModel();
        f.falist=new ArrayList<FacilityApprovalLine>();
        List alist = new StaticSentence(app.getSession(),"SELECT FA.MEMID,C.NAME,C.SEARCHKEY,FA.FACID,F.NAME,FA.RDATE,FA.STATUS_,FA.AMOUNTREACH,FA.APPROVEDBY,FA.ID,FA.APPROVEDDATE,FA.INITREASON,FA.AUTREASON FROM FACILITYLIMITAPPROVAL FA,CUSTOMERS C,FACILITY F,FACILITYAPPROVALRITES FR WHERE F.ID=FA.FACID AND FA.MEMID=C.ID AND FR.USERID=? AND FA.STATUS_ IS NULL",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(FacilityApprovalModel.FacilityApprovalLine.class)).list(user);
        if(alist==null){
            f.falist=new ArrayList<FacilityApprovalLine>();
        }else{
            f.falist=alist;
        }
        return f;
    }
    public static FacilityApprovalModel loadinstancehistory(AppView app,String memid) throws BasicException{
        FacilityApprovalModel f=new FacilityApprovalModel();
        List hlist = new StaticSentence(app.getSession(), "SELECT FA.MEMID,C.NAME,C.SEARCHKEY,FA.FACID,F.NAME,FA.RDATE,FA.STATUS_,FA.AMOUNTREACH,FA.APPROVEDBY,FA.ID,FA.APPROVEDDATE,FA.INITREASON,FA.AUTREASON FROM FACILITYLIMITAPPROVAL FA,CUSTOMERS C,FACILITY F WHERE F.ID=FA.FACID AND FA.MEMID=C.ID  AND FA.MEMID=? AND FA.STATUS_ IS NOT NULL ",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(FacilityApprovalModel.FacilityApprovalLine.class)).list(memid);
        if(hlist==null){
            f.falist=new ArrayList<FacilityApprovalLine>();
        }else{
            f.falist=hlist;
        }
        return f;
    }
    public List<FacilityApprovalLine> getApprovalLines(){
        return falist;
    }

    public AbstractTableModel getApprovalTableModel(){
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(APPROVALHEADERS[column]);
            }

            public int getRowCount() {
               return  falist.size();
            }

            public int getColumnCount() {
                return APPROVALHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                FacilityApprovalLine l=falist.get(row);
                switch(column){
                    case 0:return l.getSearchkey();
                    case 1:return l.getMemName();
                    case 2:return l.getFacName();
                    case 3:return l.getDate();
                    case 4:return l.getAmount();
                    case 5:if(l.getStatus()){
                        return "Approved";
                    }else{
                        return "Rejected";
                    }
                    case 6:return l.getApprovedBy();
                    case 7:return l.getAppdate();
                    case 8:return l.getInitreason();
                    case 9:return l.getAutreason();
                    case 10:return l.getMemID();
                    case 11:return l.getId();
                    default:return null;

                }

            }
        };
    }
    public AbstractTableModel getApprovalTableModel1(){
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(APPROVALHEADERS1[column]);
            }

            public int getRowCount() {
               return  falist.size();
            }

            public int getColumnCount() {
                return APPROVALHEADERS1.length;
            }

            public Object getValueAt(int row, int column) {
                FacilityApprovalLine l=falist.get(row);
                switch(column){
                    case 0:return l.getSearchkey();
                    case 1:return l.getMemName();
                    case 2:return l.getFacName();
                    case 3:return l.getDate();
                    case 4:return l.getAmount();
                    case 5:return l.getInitreason();
                    case 8:return l.getStatus();
                    case 6:return l.getApprovedBy();
                    case 7:return l.getAppdate();                    
                    case 9:return l.getAutreason();
                    case 10:return l.getMemID();
                    case 11:return l.getId();
                    default:return null;

                }

            }
        };
    }

    public static class FacilityApprovalLine implements SerializableRead{
        private String memID;
        private String facId;
        private String facName;
        private String memName;
        private String searchkey;
        private Date date;
        private Boolean status;
        private Double amount;
        private String approvedBy;
        private String id;
        private Date appdate;
        private String autreason;
        private String initreason;
        


        public void readValues(DataRead dr) throws BasicException {
            memID=dr.getString(1);
            memName=dr.getString(2);
            searchkey=dr.getString(3);
            facId=dr.getString(4);
            facName=dr.getString(5);
            date=dr.getTimestamp(6);
            status=dr.getBoolean(7);
            amount=dr.getDouble(8);
            approvedBy=dr.getString(9);
            id=dr.getString(10);
            appdate=dr.getTimestamp(11);
            initreason = dr.getString(12);
            autreason = dr.getString(13);

        }

        public String getAutreason() {
            return autreason;
        }

        public String getInitreason() {
            return initreason;
        }

        

        public Date getAppdate() {
            return appdate;
        }

        public String getId() {
            return id;
        }

        public String getFacName() {
            return facName;
        }

        public Double getAmount() {
            return amount;
        }

        public String getApprovedBy() {
            return approvedBy;
        }

        public Date getDate() {
            return date;
        }

        public String getFacId() {
            return facId;
        }

        public Boolean getStatus() {
            return status;
        }

        

        public String getMemID() {
            return memID;
        }

        public String getMemName() {
            return memName;
        }

        public String getSearchkey() {
            return searchkey;
        }

    }
}
