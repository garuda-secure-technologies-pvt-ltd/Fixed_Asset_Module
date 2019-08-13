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
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.FixedAssetRegistration.FixedAssetTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GuestlistTableModel;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSales;
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
public class QtLimitTable extends BeanFactoryDataSingle{

    private static DataLogicSales dlSales;

    
    
     private int flag;
    private Session s;
      //private DataLogicSales dlSales=null;
      private CustomerInfoExt skey;
      private CustomerInfoExt skey1;
      public static String id;
        Object[] fl;
         static String mid;
           private AppView m_App;
           public static String searchkey;
             public static String memname3;
             private static DataLogicSales m_dlSales;
             private static DataLogicCustomers dlCustomers;
    /**
     *
     */
    private List<QtData> data2;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private int size;
    private final static String[] TABLEHEADERS1 = {"Sr No.","Mem No.", "Mem Name",  "Add.Amount", "Valid From", "Valid To", "Created By", "Created Date", "Active"};
       private static DataLogicFacilities dlfac;
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    public void init(Session s) {
        this.s = s;
       
    }
    QtLimitTable()
   {
   }
    
    public static QtLimitTable GetQt(AppView app, int flag) throws BasicException {
        
     dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
      dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
          dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
       QtLimitTable  EmailidInfo = new QtLimitTable();
        EmailidInfo.flag = flag;
        
        EmailidInfo.data2 = new ArrayList<QtLimitTable.QtData>();
        if(flag==0){
            
            EmailidInfo.data2 = new StaticSentence(app.getSession(), "select q.id,q.customer,q.addamount,q.fromdate, q.todate, q.createdby,q.createddate,q.active from qtcr_limitauth q order by q.createddate", SerializerWriteString.INSTANCE, new SerializerReadClass(QtLimitTable.QtData.class)).list();
            
        }
        else if (flag==1){
             EmailidInfo.data2 = new StaticSentence(app.getSession(), "select q.id,q.customer,q.addamount,q.fromdate, q.todate, q.createdby,q.createddate,q.active from qtcr_limitauth q where q.active = '1' order by q.createddate", SerializerWriteString.INSTANCE, new SerializerReadClass(QtLimitTable.QtData.class)).list();
        }
        EmailidInfo.size = EmailidInfo.data2.size();
        return EmailidInfo;

    }
    
    //Added
    
      public static QtLimitTable GetQt1(AppView app,Date sdate,Date edate ) throws BasicException{
     
        int row = 0;
         // QtLimitTable.QtData l = data2.get(row);
         //List dlist = new StaticSentence(app.getSession()
          QtLimitTable  EmailidInfo = new QtLimitTable();
            EmailidInfo.data2 = new ArrayList<QtLimitTable.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select q.id,q.customer,q.addamount,q.fromdate, q.todate, q.createdby,q.createddate,q.active from qtcr_limitauth q  where q.fromdate>=? and q.todate<= ? order by q.createddate"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( QtLimitTable.QtData.class )).list(new Object[]{sdate,edate});
          
     return EmailidInfo;

  }
      public static QtLimitTable GetQt11(AppView app,Date sdate,Date edate ) throws BasicException{
     
        int row = 0;
         // QtLimitTable.QtData l = data2.get(row);
         //List dlist = new StaticSentence(app.getSession()
          QtLimitTable  EmailidInfo = new QtLimitTable();
            EmailidInfo.data2 = new ArrayList<QtLimitTable.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select q.id,q.customer,q.addamount,q.fromdate, q.todate, q.createdby,q.createddate,q.active from qtcr_limitauth q  where q.fromdate>=? and q.todate<= ? and q.active = '1' order by q.createddate"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( QtLimitTable.QtData.class )).list(new Object[]{sdate,edate});
          
     return EmailidInfo;

  }
    //ended
      
      
       public static QtLimitTable GetQt2(AppView app,String id) throws BasicException{
     
        int row = 0;
          QtLimitTable  EmailidInfo = new QtLimitTable();
            EmailidInfo.data2 = new ArrayList<QtLimitTable.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select q.id,q.customer,q.addamount,q.fromdate, q.todate, q.createdby,q.createddate,q.active from qtcr_limitauth q  where q.customer=? order by q.createddate"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
              ,new SerializerReadClass( QtLimitTable.QtData.class )).list(new Object[]{id});
          
     return EmailidInfo;

  }
       
       public static QtLimitTable GetQt22(AppView app,String id) throws BasicException{
     
        int row = 0;
          QtLimitTable  EmailidInfo = new QtLimitTable();
            EmailidInfo.data2 = new ArrayList<QtLimitTable.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select q.id,q.customer,q.addamount,q.fromdate, q.todate, q.createdby,q.createddate,q.active from qtcr_limitauth q  where q.customer=? and q.active ='1' order by q.createddate"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
              ,new SerializerReadClass( QtLimitTable.QtData.class )).list(new Object[]{id});
          
     return EmailidInfo;

  }
       public List<QtLimitTable.QtData> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<QtLimitTable.QtData>();
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
                    try {
                        QtLimitTable.QtData l = data2.get(row);
                       
                        switch (column) {
                            case 0:return (row+ 1);
                            
                            
                            case 1:
                                //  dlSales.loadCustomerExt(customerInfo.getId())
                                // return dlSales.loadCustomerExt(l.getMemname()).getSearchkey(); 
                               // return searchkey;
                               return dlSales.loadCustomerExt(l.getMemname()).getSearchkey();                      
                            case 2:
                               //  return dlSales.loadCustomerExt(l.getMemname()).getName();
                                // return memname3;
                             return   dlSales.loadCustomerExt(l.getMemname()).getName();
                               
                            case 3: return l.getAddAmount();
                            case 4: return l.getValidFrom();
                            case 5: return l.getValidTo();
                            case 6: 
                                return dlSales.getPeopleListByid(l.getCreatedBy());
                            case 7: return l.getCreatedDate();
                         case 8:
                                 if(l.getActive()==true)
                                 return "Active";
                                 else
                                     return "Deactive";
                                             
                       //   case 9: return l.getCreatedBy();
                            
                            
                            default: return null;
                        }
                        
                    } catch (BasicException ex) {
                        Logger.getLogger(QtLimitTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                return null;
              
            }
        };
    }

    void refresh(List<QtData> blist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
public static class QtData implements SerializableRead{
// private String SrNo;
    private String Memno;
    private String Memname;
    private Double AddAmount;
    private Timestamp ValidFrom;
    private Timestamp ValidTo;
    private String CreatedBy;
    private Timestamp CreatedDate;
   // private String Location;
   private Boolean Active;
      public String getMemno() {
            return Memno;
        }

        public void setMemno(String Memno) {
            this.Memno = Memno;
        }
         public String getMemname() {
             id = Memname;
            return Memname;
        }

        public void setMemname(String Memname) {
            this.Memname = Memname;
        }
         public double getAddAmount() {
            return AddAmount;
        }
   public void setAddAmount(double AddAmount) {
            this.AddAmount = AddAmount;
        }
          public String getValidFrom(){
               String crddate=Formats.TIMESTAMP.formatValue(ValidFrom);
              return crddate;
           }

        public void setValidFrom(Timestamp ValidFrom) {
            this.ValidFrom = ValidFrom;
        }
        
        public String getValidTo(){
               String crddate1=Formats.TIMESTAMP.formatValue(ValidTo);
              return crddate1;
           }

        public void setValidTo(Timestamp ValidTo) {
            this.ValidTo = ValidTo;
        }
       
         public String getCreatedBy() {
            return CreatedBy;
        }

        public void setCreatedBy(String CreatedBy) {
            this.CreatedBy = CreatedBy;
        }
        
         public String getCreatedDate(){
               String crddate=Formats.TIMESTAMP.formatValue(CreatedDate);
              return crddate;
           }

        public void setCreatedDate(Timestamp CreatedDate) {
            this.CreatedDate = CreatedDate;
        }
//         public String getLocation() {
//            return Location;
//        }
//
//        public void setLocation(String Location) {
//            this.Location = Location;
//        }
         public Boolean getActive() {
            return Active;
        }

        public void setActive(Boolean Active) {
            this.Active = Active;
        }
//       
        
        @Override
        public void readValues(DataRead dr) throws BasicException {
             Memno=dr.getString(1);
        Memname=dr.getString(2);
        AddAmount=dr.getDouble(3);
        ValidFrom=dr.getTimestamp(4);
        ValidTo =dr.getTimestamp(5);
        CreatedBy=dr.getString(6);
        CreatedDate=dr.getTimestamp(7);
    //    Location=dr.getString(8);
       Active=dr.getBoolean(8);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
}


    
}