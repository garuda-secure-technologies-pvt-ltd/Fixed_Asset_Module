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
import com.openbravo.data.loader.SerializerReadString;
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
import java.text.DecimalFormat;

/**
 *
 * @author dev3
 */
public class FALocationsTableModel extends BeanFactoryDataSingle {
     private int flag;
    private Session s;
    private List<FALocationsTableModel.FALocationsInfo> data2;
    private int size;
    private final static String[] TABLEHEADERS1 = {"SI","Name"  , "Building " ,  "Block","Floor"};
   private static AppView m_app;
    @Override
    public void init(Session s) {
        this.s = s;
    }

    public static FALocationsTableModel GetFALocationsTableModel(AppView app) throws BasicException {
        m_app=app;
        FALocationsTableModel EmailidInfo = new FALocationsTableModel();
        
        try {
            EmailidInfo.data2 = new ArrayList<FALocationsTableModel.FALocationsInfo>();
            EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT ID,NAME,FLOOR,BUILDING,BLOCK,TYPE FROM FA_LOCATIONS WHERE TYPE='LO' AND ACTIVE=1"
                  , null, new SerializerReadClass(FALocationsTableModel.FALocationsInfo.class)).list();
            EmailidInfo.size = EmailidInfo.data2.size();
            


        } catch (BasicException ex) {
            Logger.getLogger(FALocationsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmailidInfo;

    }

    public int getSize() {
        return size;
    }

    public List<FALocationsTableModel.FALocationsInfo> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<FALocationsTableModel.FALocationsInfo>();
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, 
                  };
            boolean[] canEdit = new boolean[]{
                false, false, false,
                false, false, 
                   };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                FALocationsTableModel.FALocationsInfo r = data2.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);

                    case 1:
                        return r.getName();
                    case 2:
                        return getNameById(r.getBuilding());
                    case 3:
                        return getNameById(r.getBlock());
                    case 4:
                        return getNameById(r.getFloor());
                    

                    
                   

                }
                return null;
            }

        };
    }

    public static class FALocationsInfo implements SerializableRead, IKeyed {

        private String id;
        private String name;
        private String floor;
        private String building;
        private String block;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
            this.block = block;
        }
          public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
       
        

        @Override
        public String toString() {
            return name;
        }

        @Override
        public Object getKey() {
            return id;
        }

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            name = dr.getString(2);
            floor = dr.getString(3);
            building = dr.getString(4);
            block = dr.getString(5);
            type=dr.getString(6);

        }

    }
public String getNameById(String id){
    String name="";
    try{
 name= (String) new StaticSentence(m_app.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE ID=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return name;
}
}
