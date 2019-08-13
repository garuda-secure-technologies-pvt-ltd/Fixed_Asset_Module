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
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USER
 */
public class MemBillModel extends BeanFactoryDataSingle  {

    static MemBillModel QtData;

   // static MemBillModel QtData;
    private Session s;
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    
     private List<MemBillModel.QtData> data2;
     // private List<MemBillTableModel.QtData> data3;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private int size;
     private final static String[] TABLEHEADERS1= {"Product","Qty","Rate","Tax1","Tax2","Tax3","Total"};
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
    
    
      public static MemBillModel GetQt22(AppView app,String id) throws BasicException{
     
        //int row = 0;
          MemBillModel  EmailidInfo = new MemBillModel();
            EmailidInfo.data2 = new ArrayList<MemBillModel.QtData>();
        //EmailidInfo.flag = flag;
         EmailidInfo.data2 = new StaticSentence(app.getSession()
                ,"select p.name, b.dmultiply,b.rate,b.tax1,b.tax2,b.tax3,((b.dmultiply*b.rate)+b.tax1+b.tax2+b.tax3) as 'total' from billitem b,products p  where b.product=p.id and b.parentid=?\n" +
   "union all\n" +
"\n" +
    "select p.name,b.dmultiply,b.rate,b.tax1,b.tax2,b.tax3,((b.dmultiply*b.rate)+b.tax1+b.tax2+b.tax3) as 'total' from billitem_arv b,products p where b.product=p.id and b.parentid=?"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
              ,new SerializerReadClass( MemBillModel.QtData.class )).list(new Object[]{id,id});
         
          
     return EmailidInfo;

  }
              public List<MemBillModel.QtData> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<MemBillModel.QtData>();
        }
    }
    
        
           public int getSize() {
        return size;
    }
 //   public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    ///////////////////////////////////////////////////////////////////////////////////////////
           
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
                MemBillModel.QtData l = data2.get(row);
                switch (column) {
                    case 0: return l.getParentid();
                    
                    case 1: return l.getDmultiply();
                    case 2: return l.getRate();
                  
                    case 3: return l.getTax1();
                   
                    case 4: return l.getTax2();
                    
                    case 5: return l.getTax3();
                    case 6: return decimalFormat.format((l.getDmultiply()*l.getRate())+l.getTax1()+l.getTax2()+l.getTax3());
                    case 7:return l.getTotal();
                    
                    
                    
                    
                    default: return null;
                }
             
            }
        };
    }
           
           
     
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public MemBillModel() {
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    
      public static class QtData implements SerializableRead{
// private String SrNo;
 //   private String Memno;
   
 
    ///////////////////////////////////////////////////////////////////////
     private String Parentid;
      private Double Dmultiply;
       private Double Rate;
      private Double Tax1;
        private Double Tax2;
          private Double Tax3;
       private Double Total;
  
          public String getParentid() {
           //  id = Memname;
            return Parentid;
        }

        public void setParentid(String Parentid) {
            this.Parentid = Parentid;
        }
        
           public double getDmultiply() {
            return Dmultiply;
        }
   public void setDmultiply(double Dmultiply) {
            this.Dmultiply = Dmultiply;
        }
     public double getRate() {
            return Rate;
        }
   public void setRate(double Rate) {
            this.Rate = Rate;
        }
     public double getTax1() {
         if(Tax1==null){
             Tax1=0.0;
         }
            return Tax1;
        }
   public void setTax1(double Tax1) {
            this.Tax1 = Tax1;
        }
     public double getTax2() {
         if(Tax2==null){
             Tax2=0.0;
         }
            return Tax2;
        }
   public void setTax2(double Tax2) {
            this.Tax2 = Tax2;
        }
     public double getTax3() {
         if(Tax3==null){
             Tax3=0.0;
         }
            return Tax3;
        }
   public void setTax3(double Tax3) {
            this.Tax3 = Tax3;
        }
     public double getTotal() {
            return Total;
        }
   public void setTotal(double Total) {
            this.Total = Total;
        }
      
        @Override
        public void readValues(DataRead dr) throws BasicException {
             //Memno=dr.getString(1);
             Parentid=dr.getString(1);
          Dmultiply =dr.getDouble(2);
        Rate=dr.getDouble(3);
        Tax1=dr.getDouble(4);
         Tax2=dr.getDouble(5);
          Tax3=dr.getDouble(6);
           Total=dr.getDouble(7);
        
        
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
}
    
  
    
}
