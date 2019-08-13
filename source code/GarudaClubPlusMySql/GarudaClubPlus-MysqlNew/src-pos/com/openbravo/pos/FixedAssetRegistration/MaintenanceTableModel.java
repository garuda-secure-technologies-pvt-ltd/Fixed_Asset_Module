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
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import com.openbravo.format.Formats;

/**
 *
 * @author dev3
 */
public class MaintenanceTableModel extends BeanFactoryDataSingle {

    private Session s;
    private List<MaintenanceTableModel.MaintenanceInfo> data2;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public static String idoffa = null;
    FixedAsset2 fx = new FixedAsset2();
    public static String idfa = null;
    private int size;
    private final static String[] TABLEHEADERS1 = {"Sr No.", "DATE", "AMOUNT", "ACCOUNT_HEAD", "VOCHER_DETAILS", "CREATED_BY", "CREATED_DATE"};

    @Override
    public void init(Session s) {
        this.s = s;
    }

    public static MaintenanceTableModel GetMaintenanceTableModel(AppView app) throws BasicException {
        MaintenanceTableModel EmailidInfo = new MaintenanceTableModel();

        try {
            EmailidInfo.data2 = new ArrayList<MaintenanceTableModel.MaintenanceInfo>();
            if (FixedAsset2.idf != null) {
                idoffa = FixedAsset2.idf;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select  m.id,m.date,m.amount,a.name,m.vocher_details,m.scanned_copy,m.created_by,m.created_date from fa_maintenance m,accountmaster a where a.id=m.account_head and m.active=true and m.fa_id=?",
                        
                        SerializerWriteString.INSTANCE, new SerializerReadClass(MaintenanceTableModel.MaintenanceInfo.class)).list(idoffa);
            } else if (FixedAsset2.fixedid != null) {
                idfa = FixedAsset2.fixedid;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select  m.id,m.date,m.amount,a.name,m.vocher_details,m.scanned_copy,m.created_by,m.created_date from fa_maintenance m,accountmaster a where a.id=m.account_head and m.active=true and m.fa_id=?",
                        
                        SerializerWriteString.INSTANCE, new SerializerReadClass(MaintenanceTableModel.MaintenanceInfo.class)).list(idfa);

            } else {
                idoffa = "a";
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select  m.id,m.date,m.amount,a.name,m.vocher_details,m.scanned_copy,m.created_by,m.created_date from fa_maintenance m,accountmaster a where a.id=m.account_head and m.active=true and m.fa_id=?"
                        
                        , SerializerWriteString.INSTANCE, new SerializerReadClass(MaintenanceTableModel.MaintenanceInfo.class)).list(idoffa);

            }
            EmailidInfo.size = EmailidInfo.data2.size();

        } catch (BasicException ex) {
            Logger.getLogger(MaintenanceTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmailidInfo;

    }

    public int getSize() {
        return size;
    }

    public List<MaintenanceTableModel.MaintenanceInfo> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<MaintenanceTableModel.MaintenanceInfo>();
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                MaintenanceTableModel.MaintenanceInfo r = data2.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);

                    case 1:
                        return r.getDate();
                    case 2:
                        return decimalFormat.format(r.getAmount());
                    case 3:
                        return r.getAccountHead();
                    case 4:
                        return r.getVoucherDetail();

                    case 5:
                        return r.getCreatedBy();
                    case 6:
                        return r.getCreatedDate();

                }
                return null;
            }

        };
    }

    public static class MaintenanceInfo implements SerializableRead, IKeyed {

        private String id;
        private String createdby;
        private Timestamp createddate;
        private Date DATE;
        private Double AMOUNT;
        private String ACCOUNT_HEAD;
        private String VOCHER_DETAILS;
        private String SCANNED_COPY;

        public String getDate() {
            String dt = Formats.TIMESTAMP.formatValue(DATE);
            return dt;
        }

        public void setDate(Date DATE) {
            this.DATE = DATE;

        }

        public Double getAmount() {
            return AMOUNT;
        }

        public void setAmount(Double AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getAccountHead() {
            return ACCOUNT_HEAD;
        }

        public void setAccountHead(String ACCOUNT_HEAD) {
            this.ACCOUNT_HEAD = ACCOUNT_HEAD;
        }

        public String getVoucherDetail() {
            return VOCHER_DETAILS;
        }

        public void setVoucherDetail(String VOCHER_DETAILS) {
            this.VOCHER_DETAILS = VOCHER_DETAILS;
        }

        public String getScannedCopy() {
            return SCANNED_COPY;
        }

        public void setScannedCopy(String SCANNED_COPY) {
            this.SCANNED_COPY = SCANNED_COPY;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedBy() {
            return createdby;
        }

        public void setCreatedBy(String createdby) {
            this.createdby = createdby;
        }

        public String getCreatedDate() {
            String crddate = Formats.TIMESTAMP.formatValue(createddate);
            return crddate;
        }

        public void setCreatedDate(Timestamp createddate) {
            this.createddate = createddate;
        }

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            DATE = dr.getTimestamp(2);
            AMOUNT = dr.getDouble(3);
            ACCOUNT_HEAD = dr.getString(4);
            VOCHER_DETAILS = dr.getString(5);
            SCANNED_COPY = dr.getString(6);

            createdby = dr.getString(7);
            createddate = dr.getTimestamp(8);

        }

        public Object getKey() {
            return this;
        }

    }
}
