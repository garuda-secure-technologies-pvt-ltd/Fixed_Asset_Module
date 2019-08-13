package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author praveen
 */
public class FacilityApprovalRitesModel {

    private List<AllFacilityLine> faclist;
    private List<AllUsersLine> userlist;
    private List<DeactivtionFacilityLine> deactfaclist;
    private List<ApprovalRitesLine> appuserlist;
    private List<CardConfirmationFacilitiesline> cardconfaclist;

    public FacilityApprovalRitesModel() {
    }

    public List<AllFacilityLine> loadinstanceofallfacility(AppView app) throws BasicException {
        FacilityApprovalRitesModel f = new FacilityApprovalRitesModel();
        List flist = new PreparedSentence(app.getSession(), "SELECT NAME,ID FROM FACILITY WHERE ACTIVE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilityApprovalRitesModel.AllFacilityLine.class)).list();
        if (flist != null) {
            f.faclist = flist;
        } else {
            f.faclist = new ArrayList<AllFacilityLine>();
        }
        return f.faclist;
    }

    public List<AllUsersLine> loadinstanceofallusers(AppView app) throws BasicException {
        FacilityApprovalRitesModel f = new FacilityApprovalRitesModel();
        List ulist = new PreparedSentence(app.getSession(), "SELECT NAME,ID FROM ROLES", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilityApprovalRitesModel.AllUsersLine.class)).list();
        if (ulist != null) {
            f.userlist = ulist;
        } else {
            f.userlist = new ArrayList<AllUsersLine>();
        }
        return f.userlist;
    }

    public List<DeactivtionFacilityLine> loadinstanceofdeactacility(AppView app,String pfid) throws BasicException {
        FacilityApprovalRitesModel f = new FacilityApprovalRitesModel();
        List dflist = new PreparedSentence(app.getSession(), "SELECT F.NAME,F1.PFID,F1.DFACID FROM FACILITY F,FACILITYDEACTIVATIONMASTER F1 WHERE F1.DFACID=F.ID AND F1.PFID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilityApprovalRitesModel.DeactivtionFacilityLine.class)).list(pfid);
        if (dflist != null) {
            f.deactfaclist = dflist;
        } else {
            f.deactfaclist = new ArrayList<DeactivtionFacilityLine>();
        }
        return f.deactfaclist;
    }

    public List<ApprovalRitesLine> loadinstanceofappusers(AppView app,String pfid) throws BasicException {
        FacilityApprovalRitesModel f = new FacilityApprovalRitesModel();
        List alist = new PreparedSentence(app.getSession(), "SELECT P.NAME,F.PFID,F.USERID FROM FACILITYAPPROVALRITES F,PEOPLE P WHERE P.ID=F.USERID AND F.PFID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(FacilityApprovalRitesModel.ApprovalRitesLine.class)).list(pfid);
        if (alist != null) {
            f.appuserlist = alist;
        } else {
            f.appuserlist = new ArrayList<ApprovalRitesLine>();
        }
        return f.appuserlist;
    }

    public List<CardConfirmationFacilitiesline> loadinstanceofcardfacilities(AppView app,int i) throws BasicException{
        FacilityApprovalRitesModel f = new FacilityApprovalRitesModel();
        List cardlist = null;
        //List cardlist = new PreparedSentence(app.getSession(), "SELECT FC.ID,FC.FACID,FC.ACTIVE,F.NAME FROM FACILITY F,FACILITYCARDCONFIRM FC WHERE FC.ACTIVE=TRUE AND FC.FACID=F.ID", null, new SerializerReadClass(FacilityApprovalRitesModel.CardConfirmationFacilitiesline.class)).list();
        if(i==0){
            cardlist = new PreparedSentence(app.getSession(), "SELECT F.ID,F.ID,F.ACTIVE,F.NAME FROM FACILITY F WHERE  F.INITCONTROL=TRUE", null, new SerializerReadClass(FacilityApprovalRitesModel.CardConfirmationFacilitiesline.class)).list();
        }else{
            cardlist = new PreparedSentence(app.getSession(), "SELECT F.ID,F.ID,F.ACTIVE,F.NAME FROM FACILITY F WHERE  F.CONFIRMCONTROL=TRUE", null, new SerializerReadClass(FacilityApprovalRitesModel.CardConfirmationFacilitiesline.class)).list();
        }

        if (cardlist!=null){
            f.cardconfaclist = cardlist;
        }else{
            f.cardconfaclist = new ArrayList<CardConfirmationFacilitiesline>();
        }
        return f.cardconfaclist;
    }




    public static class AllFacilityLine implements SerializableRead {

        private String id;
        private String fname;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(2);
            fname = dr.getString(1);
        }

        public String getFname() {
            return fname;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return fname;
        }

    }

    public static class AllUsersLine implements SerializableRead {

        private String id;
        private String name;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(2);
            name = dr.getString(1);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class DeactivtionFacilityLine implements SerializableRead {

        private String did;
        private String fid;
        private String dfname;

        public void readValues(DataRead dr) throws BasicException {
            did = dr.getString(2);
            fid = dr.getString(3);
            dfname = dr.getString(1);
        }

        public String getDfname() {
            return dfname;
        }

        public String getDid() {
            return did;
        }

        public String getFid() {
            return fid;
        }

        @Override
        public String toString() {
            return dfname;
        }

    }

    public static class ApprovalRitesLine implements SerializableRead {

        private String aid;
        private String uid;
        private String aname;

        public void readValues(DataRead dr) throws BasicException {
            aid = dr.getString(2);
            uid = dr.getString(3);
            aname = dr.getString(1);
        }

        public String getAid() {
            return aid;
        }

        public String getUid() {
            return uid;
        }

        public String getAname() {
            return aname;
        }

        @Override
        public String toString() {
            return aname;
        }

    }

    public static class CardConfirmationFacilitiesline implements SerializableRead{

        private String id;
        private String facid;
        private Boolean active;
        private String name;


        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            facid = dr.getString(2);
            active = dr.getBoolean(3);
            name = dr.getString(4);

        }

        public Boolean getActive() {
            return active;
        }

        public String getFacid() {
            return facid;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }


    }
}
