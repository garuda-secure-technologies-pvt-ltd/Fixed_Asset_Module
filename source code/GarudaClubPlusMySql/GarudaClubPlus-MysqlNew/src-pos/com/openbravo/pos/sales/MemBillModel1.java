/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSales;
import java.beans.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USER
 */
public class MemBillModel1 extends BeanFactoryDataSingle {
    
    
    
    
     static MemBillModel QtData;

   // static MemBillModel QtData;
    private Session s;
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    
     private List<MemBillModel1.QtData> data2;
     // private List<MemBillTableModel.QtData> data3;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private int size;
     private final static String[] TABLEHEADERS1= {"QtDate","Qt No.","Item","Qty","Remark"};
     //  private static DataLogicFacilities dlfac;
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    //////////////////////////////////////////////////////////////////////////
      //private final static String[] TABLEHEADERS2 = {"Bill No.","Qty","Rate","Tax1","Tax2","Tax3","Total"};
    //////////////////////////////////////////////////////////////////////////
       private AppView m_App;
           public static String searchkey;
             public static String memname3;
             private static DataLogicSales m_dlSales;
             private static DataLogicCustomers dlCustomers;
            //  private static DataLogicSales dlSales;
                private int flag;
                private static DataLogicFacilities dlfac;
                private static DataLogicSales dlSales;
        public void init(Session s) {
        this.s = s;
     
       
    }
    
    public static MemBillModel1 GetQt44(AppView app,String id) throws BasicException{
     
        int row = 0;
         // QtLimitTable.QtData l = data2.get(row);
         //List dlist = new StaticSentence(app.getSession()
          MemBillModel1  EmailidInfo = new MemBillModel1();
            EmailidInfo.data2 = new ArrayList<MemBillModel1.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select q.crdate as QTCrDate ,q.id as QTID , p.name as product, qt.dmultiply as qty,qt.attributes\n" +
"from qticket q, qtitems qt , products p , bill b\n" +
"where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.id=?\n" +
"union all\n" +
"select q.crdate as QTCrDate , q.id as QTID ,p.name as product, qt.dmultiply as qty,qt.attributes\n" +
"\n" +
"from qticket_arv q, qtitems_arv qt , products p , bill_arv b\n" +
" where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.id=? order by 1"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
              ,new SerializerReadClass( MemBillModel1.QtData.class )).list(new Object[]{id,id});
          
     return EmailidInfo;

  } 
       public static MemBillModel1 GetQt22(AppView app,String id) throws BasicException{
     
        int row = 0;
          MemBillModel1  EmailidInfo = new MemBillModel1();
            EmailidInfo.data2 = new ArrayList<MemBillModel1.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select  q.crdate,q.id,q.customer,p.name,b.dmultiply,qt.attributes from qticket q, products p,billitem b,qtitems qt where q.customer =?  limit 500"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
              ,new SerializerReadClass( MemBillModel1.QtData.class )).list(new Object[]{id});
          
     return EmailidInfo;

  }
        public List<MemBillModel1.QtData> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<MemBillModel1.QtData>();
        }
    }
    
        
           public int getSize() {
        return size;
    }
            public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS1[column]);
            }
            public int getRowCount() {
                return data2.size(); 
            }
            public int getColumnCount() {

                return TABLEHEADERS1.length;
            }
            public Object getValueAt(int row, int column) {
                MemBillModel1.QtData l = data2.get(row);
                switch (column) {
                    case 0: return l.getCrdDate();
                    
                    case 1: return l.getid();
               //     case 2: return dlSales.getPeopleListByName2(l.getBillref());
                    case 2: return l.getname();
                    
                    case 3: return l.getDmultiply();
                  case 4: return l.getRemarks();
                                        
                    default: return null;
                }
                //return null;
              
            }
        };
    }
     
            public static class QtData implements SerializableRead{

      private Timestamp CrDate;
     private String id;
    //  private String Billref;
     private String name;
      private Double Dmultiply;
      private Properties m_attributes = new Properties();
   
         
           public String getCrdDate(){
               String crddate=Formats.TIMESTAMP.formatValue(CrDate);
              return crddate;
           }

        public void setCreatedDate(Timestamp CrDate) {
            this.CrDate = CrDate;
        }
          public String getid() {
           //  id = Memname;
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }
         public String getname() {
           //  id = Memname;
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }
           public double getDmultiply() {
            return Dmultiply;
        }
   public void setDmultiply(double Dmultiply) {
            this.Dmultiply = Dmultiply;
        }
   
    public String getProperty(String key) {
        return m_attributes.getProperty(key);
    }

    public String getProperty(String key, String defaultvalue) {
        return m_attributes.getProperty(key, defaultvalue);
    }

    public void setProperty(String key, String value) {
        m_attributes.setProperty(key, value);
    }

    public void setProperties(Properties attributes) {
        m_attributes = attributes;
    }
     public String getRemarks() {
        return m_attributes.getProperty("qt.remarks");
    }

    public void setRemarks(String value) {
        m_attributes.setProperty("qt.remarks", value);
    }
        @Override
        public void readValues(DataRead dr) throws BasicException {
             //Memno=dr.getString(1);
             CrDate=dr.getTimestamp(1);
            
             id = dr.getString(2);
           //   Billref = dr.getString(3);
              name = dr.getString(3);
             
          Dmultiply =dr.getDouble(4);
          try {
            byte[] img = dr.getBytes(5);
            if (img != null) {
                m_attributes.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
            //TODO logging
        }
        
        
       
        }
    
} 
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public MemBillModel1() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

                            
    
}
