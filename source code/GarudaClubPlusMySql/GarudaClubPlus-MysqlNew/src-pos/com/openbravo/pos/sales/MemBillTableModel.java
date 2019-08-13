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
import static com.openbravo.pos.sales.QtLimitTable.id;
import java.beans.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USER
 */



public class MemBillTableModel extends BeanFactoryDataSingle {
    
     private Session s;
    
    
      private List<MemBillTableModel.QtData> data2;
      private List<MemBillTableModel.QtData> data3;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private int size;
    private final static String[] TABLEHEADERS1 = {"Created Date","Bill No","Mem No.", "Mem Name",  "Amount", "Receipt No.","WareHouse"};
       private static DataLogicFacilities dlfac;
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    //////////////////////////////////////////////////////////////////////////
//      private final static String[] TABLEHEADERS2 = {"Bill No.","Qty","Rate","Tax1","Tax2","Tax3","Total"};
    //////////////////////////////////////////////////////////////////////////
       private AppView m_App;
           public static String searchkey;
             public static String memname3;
             private static DataLogicSales m_dlSales;
             private static DataLogicCustomers dlCustomers;
              private static DataLogicSales dlSales;
                private int flag;
              //  public static String id1;
      public void init(Session s) {
        this.s = s;
       
    }
     private MemBillTableModel()
   {
   }
     
     
     
      public static MemBillTableModel GetQt(AppView app, int flag) throws BasicException {
        
     dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
      dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
          dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
       MemBillTableModel  EmailidInfo = new MemBillTableModel();
        EmailidInfo.flag = flag;
        
        EmailidInfo.data2 = new ArrayList<MemBillTableModel.QtData>();
        if(flag==0){
          
            EmailidInfo.data2 = new StaticSentence(app.getSession(), "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter from bill b\n" +
"union all\n" +
"select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse, b.waiter from bill_arv b order by 1", SerializerWriteString.INSTANCE, new SerializerReadClass(MemBillTableModel.QtData.class)).list();
            
        }
        EmailidInfo.size = EmailidInfo.data2.size();
        return EmailidInfo;

    }
      
        public static MemBillTableModel GetQt22(AppView app,String id) throws BasicException{
     
        int row = 0;
          MemBillTableModel  EmailidInfo = new MemBillTableModel();
            EmailidInfo.data2 = new ArrayList<MemBillTableModel.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                , "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal) from bill b where b.id =?\n" +
"union all\n" +
"select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)   from bill_arv b where b.id=? order by 1"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
              ,new SerializerReadClass( MemBillTableModel.QtData.class )).list(new Object[]{id,id});
          
     return EmailidInfo;

  }
     
        
        
 public static MemBillTableModel GetQt33(AppView app,String id,Date sdate,Date edate,String id1) throws BasicException{
     
        int row = 0;
          MemBillTableModel  EmailidInfo = new MemBillTableModel();
            EmailidInfo.data2 = new ArrayList<MemBillTableModel.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                , "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)   from bill b where b.customer =? and b.createddate>=? and b.createddate<= ? and b.warehouse=?\n" +
"union all\n" +
"select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)  from bill_arv b where b.customer=? and  b.createddate>=? and b.createddate<= ? and b.warehouse=? order by 1"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
              ,new SerializerReadClass( MemBillTableModel.QtData.class )).list(new Object[]{id,sdate,edate,id1,id,sdate,edate,id1});
          
     return EmailidInfo;

  }
    public static MemBillTableModel GetQt44(AppView app,String  id,Date sdate,Date edate ) throws BasicException{
     
        int row = 0;
         
          MemBillTableModel  EmailidInfo = new MemBillTableModel();
            EmailidInfo.data2 = new ArrayList<MemBillTableModel.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)   from bill b where b.customer=? and b.createddate>=? and b.createddate<= ?\n" +
"union all\n" +
"select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)   from bill_arv b where b.customer=? and  b.createddate>=? and b.createddate<= ? order by 1"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( MemBillTableModel.QtData.class )).list(new Object[]{id,sdate,edate,id,sdate,edate});
          
     return EmailidInfo;

  }
 public static MemBillTableModel GetQt333(AppView app,Date sdate,Date edate) throws BasicException{
     
        int row = 0;
          MemBillTableModel  EmailidInfo = new MemBillTableModel();
            EmailidInfo.data2 = new ArrayList<MemBillTableModel.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                , "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter ,(b.amount+b.taxtotal) from bill b where b.createddate>=? and b.createddate<= ?\n" +
"union all\n" +
  "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)  from bill_arv b where b.createddate>=? and b.createddate<= ? order by 1"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( MemBillTableModel.QtData.class )).list(new Object[]{sdate,edate,sdate,edate});
          
     return EmailidInfo;

  }
 public static MemBillTableModel GetQt444(AppView app,Date sdate,Date edate,String iid) throws BasicException{
     
        int row = 0;
          MemBillTableModel  EmailidInfo = new MemBillTableModel();
            EmailidInfo.data2 = new ArrayList<MemBillTableModel.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                , "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal) from bill b where b.createddate>=? and b.createddate<= ? and b.warehouse=? \n" +
    "union all\n" +
      "select b.createddate,b.id,b.customer,b.amount,b.receipt,b.warehouse,b.waiter,(b.amount+b.taxtotal)   from bill_arv b where  b.createddate>=? and b.createddate<= ? and b.warehouse=? order by 1"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
              ,new SerializerReadClass( MemBillTableModel.QtData.class )).list(new Object[]{sdate,edate,iid,sdate,edate,iid});
          
     return EmailidInfo;

  }
     public List<MemBillTableModel.QtData> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<MemBillTableModel.QtData>();
        }
    }
        
        
        
         public List<MemBillTableModel.QtData> getList1() {
        if (data3 != null) {
            return data3;
        } else {
            return new ArrayList<MemBillTableModel.QtData>();
        }
    }
     public int getSize() {
        return size;
    }
      
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
  
    
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

    ///////////////////////////////////////////////////////////////////////////////////
    
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
                MemBillTableModel.QtData l = data2.get(row);
                switch (column) {
                    case 0: return l.getCreatedDate();
                    
                    case 1: return l.getId();
                    case 2:
                        return dlSales.getPeopleListByName4(l.getMemname());
                    case 3:
                        return dlSales.getPeopleListByName5(l.getMemname());
                        
                    case 4: return l.getAmount();
                    
                    case 5: return l.getReceipt();
                    case 6: return dlSales.getPeopleListByName(l.getName());
                    case 7: return dlSales.getPeopleListByName3(l.getWaiter());
                    case 8: return l.getTotalAmount();
                    
                    
                    default: return null;
                }
             
              
            }
        };
    }
    
    public static class QtData implements SerializableRead{
// private String SrNo;
 //   private String Memno;
    private String Memname;
    private Double Amount;
    private   String Id;
     private String Receipt;
    private Timestamp CreatedDate;
     private String Name;
      private String Waiter;
      private Double TotalAmount;
         public String getMemname() {
             id = Memname;
            return Memname;
        }

        public void setMemname(String Memname) {
            this.Memname = Memname;
        }
         public double getAmount() {
            return Amount;
        }
   public void setAmount(double Amount) {
            this.Amount = Amount;
        }
     public double getTotalAmount() {
            return TotalAmount;
        }
   public void setTotalAmount(double TotalAmount) {
            this.TotalAmount = TotalAmount;
        }
            public String getId() {
             
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }
                 public String getReceipt() {
             
            return Receipt;
        }

        public void setReceipt(String Receipt) {
            this.Receipt = Receipt;
        }
        
       
       
        
         public String getCreatedDate(){
               String crddate=Formats.TIMESTAMP.formatValue(CreatedDate);
              return crddate;
           }

        public void setCreatedDate(Timestamp CreatedDate) {
            this.CreatedDate = CreatedDate;
        }
         public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
       /////////////////////////////////
          public String getWaiter() {
            return Waiter;
        }

        public void setWaiter(String Waiter) {
            this.Waiter = Waiter;
        }
        @Override
        public void readValues(DataRead dr) throws BasicException {
             //Memno=dr.getString(1);
             CreatedDate=dr.getTimestamp(1);
          Id =dr.getString(2);
        Memname=dr.getString(3);
        Amount=dr.getDouble(4);
        
        Receipt=dr.getString(5);
        Name =dr.getString(6);
        Waiter =dr.getString(7);
        TotalAmount=dr.getDouble(8);
               }
    
}
}
