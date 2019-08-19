

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


public class BirthdayWishListTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<BirthdayWishListTableModel.BirthdayWishesTableInfo> data;
    private int size;
      private final static String[] TABLEHEADERS = {"Sr No." , "Member No" , "Member Name" , "DOB"  , "Select"};
      private final static String[] TABLEHEADERS1 = {"Sr No." , "Member No" , "Member Name" , "Type"  , "D.NAME" , "DOB" , "SELECT" };
    private List<BirthdayWishListTableModel.BirthdayWishesDeptTableInfo> dataDept;
    
    @Override
    public void init(Session s) {
      this.s=s;
    }
    
    
      public static BirthdayWishListTableModel loadBirthdayWishToday(AppView app) throws BasicException{
        BirthdayWishListTableModel GuestInfo = new BirthdayWishListTableModel(); 
    
     try{
            Date D = new Date();
            String x = Formats.DATE.formatValue(D);
            D = (Date) Formats.DATE.parseValue(x);
            Calendar cal = Calendar.getInstance();
            cal.setTime(D);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int Month = cal.get(Calendar.MONTH)+1;
            
            
            
            System.out.println(day+"++++"+Month);
            GuestInfo.data = new ArrayList<BirthdayWishListTableModel.BirthdayWishesTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT B.ID , B.MEMNO, C.NAME  , B.DATE  FROM sms_birthdy_currlist B , CUSTOMERS C  WHERE B.ACTIVE=1 AND  C.ID=B.ID AND C.VISIBLE=true ", new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.INT}), new SerializerReadClass(BirthdayWishListTableModel.BirthdayWishesTableInfo.class)).list(new Object[]{day , Month});
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(BirthdayWishListTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
      
      
      public int getSize()
      {
        return size;
      }
      public List<BirthdayWishListTableModel.BirthdayWishesTableInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<BirthdayWishListTableModel.BirthdayWishesTableInfo>();
      }
      
      
        public MyAbstractTableModel getTableModel2() {
        return new MyAbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(HEADERS[column]);
                return (TABLEHEADERS[column]);
            }

            @Override
            public void settext( ) {
                
            }

            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class 
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false , true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                boolean status = Boolean.parseBoolean(aValue.toString());
                
                BirthdayWishesTableInfo r =data.get(row);
                
                if(column==4){
                    
                    r.setSelected(status);  
                    fireTableDataChanged();
                }
                
                 
                
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                BirthdayWishListTableModel.BirthdayWishesTableInfo r =data.get(rowIndex);
              
                
                    switch(columnIndex){

                        case 0: return (rowIndex+1);
                        case 1: return r.getMEMNO();
                        case 2: return r.getMEMNAME();
                        case 3: return r.getDOB();
                        case 4: return r.getSelect();
                        
                   
                            
                      }
                     return null;
                }
           };
        }
      
      
      
      
      
      
      
      public static class BirthdayWishesTableInfo implements SerializableRead,IKeyed {

        private String ID;
        private String NAME;
        private String MEMNO;
        private String MEMNAME;
        private Date DOB;
        private Boolean select=false;
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getNAME(){
              return NAME;
          }
          public void setNAME(String NAME){
              this.NAME = NAME;
          }
         public String getMEMNO(){
              return MEMNO;
          }
          public void setMEMNO(String MEMNO){
              this.MEMNO = MEMNO;
          }
          public String getMEMNAME(){
              return MEMNAME;
          }
          public void setMEMNAME(String MEMNAME){
              this.MEMNAME = MEMNAME;
          }
          
          public String getDOB(){
              String x = Formats.DATE.formatValue(DOB);
              return x;
          }
          public void setDOB(Date DOB){
              this.DOB = DOB;
          }
          
          public Boolean getSelect(){
              return select;
          }
          public void setSelected(Boolean select){
              this.select=select;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                MEMNO = dr.getString(2);
                MEMNAME = dr.getString(3);
                DOB = dr.getTimestamp(4);
               
              
          }

        public Object getKey() {
           return this;
        }

        
        
     }   
     
     public abstract class MyAbstractTableModel extends AbstractTableModel {

       
        public void settext() {
           
          }
       }
      
       
     
     
     
    // LOAD DATA FOR DEPENDENTS AND SPOUSE -----------------------------------------------------------------------------------------
     
     
       public static BirthdayWishListTableModel loadBirthdayWishTodayDept(AppView app) throws BasicException{
        BirthdayWishListTableModel GuestInfo = new BirthdayWishListTableModel(); 
    
        try{
               Date D = new Date();
               String x = Formats.DATE.formatValue(D);
               D = (Date) Formats.DATE.parseValue(x);
               Calendar cal = Calendar.getInstance();
               cal.setTime(D);
               int day = cal.get(Calendar.DAY_OF_MONTH);
               int Month = cal.get(Calendar.MONTH)+1;



               System.out.println(day+"++++"+Month);
               GuestInfo.dataDept = new ArrayList<BirthdayWishListTableModel.BirthdayWishesDeptTableInfo>();
               GuestInfo.dataDept = new StaticSentence(app.getSession(), "select  d.id , c.searchkey , c.name , d.dtype ,  d.dname , d.dob\n" +
                                                                            "from memdependent d, customers c\n" +
                                                                            "where c.id=d.memno and c.visible=1 and day(d.dob)=? and month(d.dob)=? ", new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.INT}), new SerializerReadClass(BirthdayWishListTableModel.BirthdayWishesDeptTableInfo.class)).list(new Object[]{day , Month});

               GuestInfo.size = GuestInfo.dataDept.size();



           }
           catch(BasicException ex){
               Logger.getLogger(BirthdayWishListTableModel.class.getName()).log(Level.SEVERE, null, ex);
           }
        return GuestInfo;
  
     }
    
     
     
     
         public int getSizeDept()
            {
              return size;
            }
      public List<BirthdayWishListTableModel.BirthdayWishesDeptTableInfo> getListdept(){
           if(dataDept!=null)
            {
                return dataDept;
            }
            else
                return new ArrayList<BirthdayWishListTableModel.BirthdayWishesDeptTableInfo>();
          }
      
      
        public MyAbstractTableModel2 getTableModel3() {
        return new MyAbstractTableModel2() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(HEADERS[column]);
                return (TABLEHEADERS1[column]);
            }

            @Override
            public void settext( ) {
                
            }

            public int getRowCount() {
                return dataDept.size();
            }

            public int getColumnCount() {

                return TABLEHEADERS1.length;
            }
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class 
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false ,false , false , true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                boolean status = Boolean.parseBoolean(aValue.toString());
                
                BirthdayWishesDeptTableInfo r =dataDept.get(row);
                
                if(column==6){
                    
                    r.setSelected(status);  
                    fireTableDataChanged();
                }
                
                 
                
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                BirthdayWishListTableModel.BirthdayWishesDeptTableInfo r =dataDept.get(rowIndex);
              
                
                    switch(columnIndex){

                        case 0: return (rowIndex+1);
                        case 1: return r.getMEMNO();
                        case 2: return r.getMEMNAME();
                        case 3: return r.getDType();
                        case 4: return r.getNAME();
                        case 5: return r.getDOB();
                        case 6: return r.getSelect();
                   
                            
                      }
                     return null;
                }
           };
        }
      
      
       
       
       
       
       
       
       
     
     
      public static class BirthdayWishesDeptTableInfo implements SerializableRead,IKeyed {

        private String ID;
        private String NAME;
        private String MEMNO;
        private String MEMNAME;
        private Date DOB;
        private String DTYPE;
       
        private Boolean select=false;
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getNAME(){
              return NAME;
          }
          public void setNAME(String NAME){
              this.NAME = NAME;
          }
         public String getMEMNO(){
              return MEMNO;
          }
          public void setMEMNO(String MEMNO){
              this.MEMNO = MEMNO;
          }
          public String getMEMNAME(){
              return MEMNAME;
          }
          public void setMEMNAME(String MEMNAME){
              this.MEMNAME = MEMNAME;
          }
          
          public String getDOB(){
              String x = Formats.DATE.formatValue(DOB);
              return x;
          }
          public void setDOB(Date DOB){
              this.DOB = DOB;
          }
          
          public Boolean getSelect(){
              return select;
          }
          public void setSelected(Boolean select){
              this.select=select;
          }
          
          public String getDType(){
              return DTYPE;
          }
          public void setDtype(String DTYPE){
              this.DTYPE=DTYPE;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                MEMNO = dr.getString(2);
                MEMNAME = dr.getString(3);
                DTYPE=dr.getString(4);
                NAME = dr.getString(5);
                DOB = dr.getTimestamp(6);
                
              
          }

        public Object getKey() {
           return this;
        }

        
        
     }   
     
     public abstract class MyAbstractTableModel2 extends AbstractTableModel {

       
        public void settext() {
           
          }
       }
}
