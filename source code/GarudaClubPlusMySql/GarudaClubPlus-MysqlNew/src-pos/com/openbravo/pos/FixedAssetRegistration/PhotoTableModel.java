/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;


import java.util.Date;

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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class PhotoTableModel extends BeanFactoryDataSingle {

    private Session s;
    private  List<PhotoTableModel.PhotoInfo> data2;

    private int size;
    public static String idoffa = null;
    public static String idfa = null;
    
    private final static String[] TABLEHEADERS1 = {"Sr No.", "Verification Date ", "Verified By", "Photo"};

    @Override
    public void init(Session s) {
        this.s = s;
    }

    public static PhotoTableModel GetPhotoTableModel(AppView app) throws BasicException {
        PhotoTableModel EmailidInfo = new PhotoTableModel();

        try {
            EmailidInfo.data2 = new ArrayList<PhotoTableModel.PhotoInfo>();

            if (FixedAsset2.idf != null) {
                idoffa = FixedAsset2.idf;
               
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT VER_DATE,VER_BY,PHOTO,ACTIVE  FROM FA_PHYSICALVERIFICATION WHERE FA_ID=? ORDER BY VER_DATE ", SerializerWriteString.INSTANCE, new SerializerReadClass(PhotoTableModel.PhotoInfo.class)).list(idoffa);

            } else if (FixedAsset2.fixedid != null) {
                idfa = FixedAsset2.fixedid;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT VER_DATE,VER_BY,PHOTO,ACTIVE   FROM FA_PHYSICALVERIFICATION WHERE FA_ID=? ORDER BY VER_DATE", SerializerWriteString.INSTANCE, new SerializerReadClass(PhotoTableModel.PhotoInfo.class)).list(idfa);

            } else {
                idoffa = "a";
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT VER_DATE,VER_BY,PHOTO,ACTIVE   FROM FA_PHYSICALVERIFICATION WHERE FA_ID=? ORDER BY VER_DATE", SerializerWriteString.INSTANCE, new SerializerReadClass(PhotoTableModel.PhotoInfo.class)).list(idoffa);

            }
           
            EmailidInfo.size = EmailidInfo.data2.size();

        } catch (BasicException ex) {
            Logger.getLogger(PhotoTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmailidInfo;

    }

    public int getSize() {
        return size;
    }

    public List<PhotoTableModel.PhotoInfo> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<PhotoTableModel.PhotoInfo>();
        }
    }
    

    public AbstractTableModel getTableModel2() {
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PhotoTableModel.PhotoInfo r = data2.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);

                    case 1:
                        return r.getVerDate();
                    
                    case 2:
                        return r.getVerBy();
                    case 3:
                        if(r.getPhotolink().contains("null")){
                        return "";
                    }else return r.getPhotolink();

                }
                return null;
            }

        };
    }

    public static class PhotoInfo implements SerializableRead, IKeyed {

       
        private String photolink;
        private Date verDate;
        private String verby;
        private boolean active;

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
        

        public String getPhotolink() {
            return photolink;
        }

        public void setPhotolink(String photolink) {
            this.photolink = photolink;
        }

        public String getVerBy() {
            return verby;
        }

        public void setVerBy(String verby) {
            this.verby = verby;
        }

       

        public String getVerDate() {
            String date = Formats.TIMESTAMP.formatValue(verDate);
            return date;
        }

        public void setVerDate(Date verDate) {
            this.verDate = verDate;
        }

       
        public void readValues(DataRead dr) throws BasicException {
            
          
            verDate = dr.getTimestamp(1);
           
            verby = dr.getString(2);
             photolink = dr.getString(3);
             active=dr.getBoolean(4);
        }

        public Object getKey() {
            return this;
        }

    }

}
