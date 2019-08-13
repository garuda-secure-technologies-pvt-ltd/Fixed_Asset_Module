/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class AmcNNonamcTableModel extends BeanFactoryDataSingle {

    private Session s;
    private List<AmcNNonamcTableModel.AmcInfo> amclist;
    private List<AmcNNonamcTableModel.NonamcInfo> nonamclist;

    private List<AmcNNonamcTableModel.AmcInfo> data2;
    private List<AmcNNonamcTableModel.NonamcInfo> data;
    private int flag;
    private int size;
    public static String idoffa = null;
    public static String idfa = null;
    private final static String[] TABLEHEADERS1 = {"Sr No.", "CONTRACTOR", "START_DATE", "PERIOD", "END_DATE", " CREATED BY", "CREATED DATE"};
    private final static String[] TABLEHEADERS2 = {"Sr No.", "VENDOR_NAME", "CONTACT_PERSON", "CONTACT_DETAILS", "REMARKS", "CREATED_BY", "CREATED_DATE"};

    @Override
    public void init(Session s) {
        this.s = s;
    }

    public static AmcNNonamcTableModel GetAmcTableModel(AppView app) throws BasicException {
        AmcNNonamcTableModel EmailidInfo = new AmcNNonamcTableModel();

        try {
            EmailidInfo.data2 = new ArrayList<AmcNNonamcTableModel.AmcInfo>();
            if (FixedAsset2.idf != null) {
                idoffa = FixedAsset2.idf;
          

                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select v.name,a.particular,a.start_date,a.period,a.end_date,a.rate,a.account,a.amount,a.remark,a.reminder_date,a.doc_link,a.initiator,a.initiated_date,a.id,a.active,approved_by,a.approved_date from fa_amc a,vendor v  where a.active=true and  v.id=a.contractor and  a.fa_id=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(AmcNNonamcTableModel.AmcInfo.class)).list(idoffa);
            } else if (FixedAsset2.fixedid != null) {
                idfa = FixedAsset2.fixedid;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select v.name,a.particular,a.start_date,a.period,a.end_date,a.rate,a.account,a.amount,a.remark,a.reminder_date,a.doc_link,a.initiator,a.initiated_date,a.id,a.active,approved_by,a.approved_date from fa_amc a,vendor v  where a.active=true and  v.id=a.contractor and  a.fa_id=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(AmcNNonamcTableModel.AmcInfo.class)).list(idfa);

            } else {
                idoffa = "a";
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select v.name,a.particular,a.start_date,a.period,a.end_date,a.rate,a.account,a.amount,a.remark,a.reminder_date,a.doc_link,a.initiator,a.initiated_date,a.id,a.active,approved_by,a.approved_date from fa_amc a,vendor v  where a.active=true and  v.id=a.contractor and  a.fa_id=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(AmcNNonamcTableModel.AmcInfo.class)).list(idoffa);

            }

            EmailidInfo.size = EmailidInfo.data2.size();

        } catch (BasicException ex) {
            Logger.getLogger(AmcNNonamcTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmailidInfo;

    }

    public static AmcNNonamcTableModel GetNonamcTableModel(AppView app) throws BasicException {
        AmcNNonamcTableModel EmailidInfo = new AmcNNonamcTableModel();
       
        
        try {
            EmailidInfo.data = new ArrayList<AmcNNonamcTableModel.NonamcInfo>();
             if (FixedAsset2.idf != null) {
            idoffa = FixedAsset2.idf;
            
            
            EmailidInfo.data = new StaticSentence(app.getSession(), "select v.name,n.remarks,n.contact_person,n.contact_details,n.created_by,n.created_date,n.id,n.active from fa_nonamc n,vendor v where n.active=true and v.id=n.vendor_name and n.fa_id=?  ", SerializerWriteString.INSTANCE, new SerializerReadClass(AmcNNonamcTableModel.NonamcInfo.class)).list(idoffa);
            } else if(FixedAsset2.fixedid!=null){
                idfa=FixedAsset2.fixedid;
            EmailidInfo.data = new StaticSentence(app.getSession(), "select v.name,n.remarks,n.contact_person,n.contact_details,n.created_by,n.created_date,n.id,n.active from fa_nonamc n,vendor v where n.active=true and v.id=n.vendor_name and n.fa_id=?  ", SerializerWriteString.INSTANCE, new SerializerReadClass(AmcNNonamcTableModel.NonamcInfo.class)).list(idfa);
           
        }else{
            idoffa = "a";
            EmailidInfo.data = new StaticSentence(app.getSession(), "select v.name,n.remarks,n.contact_person,n.contact_details,n.created_by,n.created_date,n.id,n.active from fa_nonamc n,vendor v where n.active=true and v.id=n.vendor_name and n.fa_id=?  ", SerializerWriteString.INSTANCE, new SerializerReadClass(AmcNNonamcTableModel.NonamcInfo.class)).list(idoffa);
           
            }
            
            
            EmailidInfo.size = EmailidInfo.data.size();

        } catch (BasicException ex) {
            Logger.getLogger(AmcNNonamcTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmailidInfo;

    }

    
    public int getSize() {
        return size;
    }

    public List<AmcNNonamcTableModel.AmcInfo> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<AmcNNonamcTableModel.AmcInfo>();
        }
    }

    public List<AmcNNonamcTableModel.NonamcInfo> getList1() {
        if (data != null) {
            return data;
        } else {
            return new ArrayList<AmcNNonamcTableModel.NonamcInfo>();
        }
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            int i = 2, j = 0;

            public int getRowCount() {
                return data2.size();
            }

            public int getColumnCount() {
                return TABLEHEADERS1.length;
            }

            @Override
            public String getColumnName(int column) {

                return TABLEHEADERS1[column];
            }

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class

            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                AmcNNonamcTableModel.AmcInfo r = data2.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);
                    case 1:
                        return r.getContrator();
                    case 2:
                        return r.getStartDate();
                    case 3:
                        return r.getPeriod();
                    case 4:
                        return r.getEndDate();

                    case 5:
                        return r.getCreatedBy();
                    case 6:
                        return r.getCreatedDate();

                }
                return null;
            }

        };
    }

    public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {
            int i = 2, j = 0;

            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {
                return TABLEHEADERS2.length;
            }

            @Override
            public String getColumnName(int column) {

                return TABLEHEADERS2[column];
            }

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                AmcNNonamcTableModel.NonamcInfo r = data.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);
                    case 1:
                        return r.getvendor();
                    case 2:
                        return r.getcontact_per();
                    case 3:
                        return r.getcontact_det();
                    case 4:
                        return r.getRemark();

                    case 5:
                        return r.getCreatedBy();
                    case 6:
                        return r.getCreatedDate();

                }
                return null;
            }

        };
    }

    public static class AmcInfo implements SerializableRead, IKeyed {

        private String contracator;
        private String particular;
        private Date startdate;
        private Date enddate;
        private Date renewaldate;
        private String period;
        private Double amount;
        private String account;
        private Double rate;
        private String doc_link;
        private String remark;
        private String Id;
        private Boolean active;
        private String vendor;
        private String approvedby;
        private Date approveddate;

        private String createdby;
        private Timestamp createddate;

        public String getApprovedBy() {
            return approvedby;
        }

        public void setApprovedBy(String appby) {
            this.approvedby = appby;
        }

        public String getApprovedDate() {
            String date = Formats.TIMESTAMP.formatValue(approveddate);
            return date;
        }

        public void setApprovedDate(Date dateapp) {
            this.approveddate = dateapp;
        }

        public String getID() {
            return Id;
        }

        public void setID(String id) {
            this.Id = id;
        }

        public Boolean isActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public String getContrator() {
            return contracator;
        }

        public void setContractor(String contractor) {
            this.contracator = contractor;
        }

        public String getParticular() {
            return particular;

        }

        public void setParticular(String particular) {
            this.particular = particular;
        }

        public String getStartDate() {
            String sd = Formats.TIMESTAMP.formatValue(startdate);
            return sd;
        }

        public void setStartDate(Date startdate) {
            this.startdate = startdate;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getEndDate() {
            String ed = Formats.TIMESTAMP.formatValue(enddate);

            return ed;
        }

        public void setEndate(Date enddate) {
            this.enddate = enddate;
        }

        public Double getRate() {
           
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;

        }

        public String getaccount() {
            
            return account;

        }

        public void setaccount(String account) {
            this.account = account;

        }

        public Double getAmount() {
         

            return amount;

        }

        public void setAmount(Double amount) {
            this.amount = amount;

        }

        public String getRenewalDate() {
            String remin = Formats.TIMESTAMP.formatValue(renewaldate);
            return remin;
        }

        public void setRenewalDate(Date renewaldate) {
            this.renewaldate = renewaldate;
        }

        public String getDocLink() {
            return doc_link;
        }

        public void setDocLink(String doclink) {
            this.doc_link = doclink;
        }

        public String getCreatedBy() {
            return createdby;
        }

        public void setCreatedBy(String createdby) {
            this.createdby = createdby;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreatedDate() {
            String crddate = Formats.TIMESTAMP.formatValue(createddate);
            return crddate;
        }

        public void setCreatedDate(Timestamp createddate) {
            this.createddate = createddate;
        }

        public void readValues(DataRead dr) throws BasicException {
            contracator = dr.getString(1);
            particular = dr.getString(2);
            startdate = dr.getTimestamp(3);
            period = dr.getString(4);
            enddate = dr.getTimestamp(5);
            rate = dr.getDouble(6);
            account = dr.getString(7);
            amount = dr.getDouble(8);
            remark = dr.getString(9);
            renewaldate = dr.getTimestamp(10);
            doc_link = dr.getString(11);

            createdby = dr.getString(12);
            createddate = dr.getTimestamp(13);
            Id = dr.getString(14);
            active = dr.getBoolean(15);
            approvedby = dr.getString(16);
            approveddate = dr.getTimestamp(17);
        }

        public Object getKey() {
            return this;
        }

    }

    public static class NonamcInfo implements SerializableRead, IKeyed {

        private String Id;
        private Boolean active;

        private String remark;

        private String vendor;
        private String contact_per;

        private String contact_det;

        private String createdby;
        private Timestamp createddate;

        public String getID() {
            return Id;
        }

        public void setID(String id) {
            this.Id = id;
        }

        public Boolean isActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public String getvendor() {
            return vendor;
        }

        public void setvendor(String vendor) {
            this.vendor = vendor;
        }

        public String getcontact_per() {
            return contact_per;

        }

        public void setcontact_per(String contact_per) {
            this.contact_per = contact_per;
        }

        public String getcontact_det() {
            return contact_det;
        }

        public void setcontact_det(String contact_det) {
            this.contact_det = contact_det;
        }

        public String getCreatedBy() {
            return createdby;
        }

        public void setCreatedBy(String createdby) {
            this.createdby = createdby;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Timestamp getCreatedDate() {
            return createddate;
        }

        public void setCreatedDate(Timestamp createddate) {
            this.createddate = createddate;
        }

        public void readValues(DataRead dr) throws BasicException {
            vendor = dr.getString(1);
            remark = dr.getString(2);
            contact_per = dr.getString(3);
            contact_det = dr.getString(4);

            createdby = dr.getString(5);
            createddate = dr.getTimestamp(6);
            Id = dr.getString(7);
            active = dr.getBoolean(8);
        }

        public Object getKey() {
            return this;
        }

    }

}
