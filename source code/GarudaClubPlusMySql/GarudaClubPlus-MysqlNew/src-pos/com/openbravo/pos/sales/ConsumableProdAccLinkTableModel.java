

package com.openbravo.pos.sales;


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
import com.openbravo.pos.sms.EmailMasterTableForCreateGroup;
import com.openbravo.pos.sms.EmailMasterTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ConsumableProdAccLinkTableModel extends BeanFactoryDataSingle{
    
    
    private List<ConsumableProdAccLinkTableModel.ConsumableProdInfo> list = new ArrayList<ConsumableProdAccLinkTableModel.ConsumableProdInfo>();
   // private final static String[] TABLEHEADERS = {"Sr No. ", "Warehouse" ,"Product name" , "Department name" , "Sale Account" };
    private final static String[] TABLEHEADERS = {"Sr No. ", "Warehouse" ,"Product name" , "Department name" , "Account Dr. on issue" };//added by pratima
    private int size;
    private Session s;
    private List<ConsumableProdAccLinkTableModel.ConsumableProdInfo> data;
    
    
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    public  static ConsumableProdAccLinkTableModel LoadProdInfoOrderbyProduct(AppView m_app) throws BasicException{
      ConsumableProdAccLinkTableModel GuestInfo = new  ConsumableProdAccLinkTableModel();
      GuestInfo.data = new ArrayList<ConsumableProdAccLinkTableModel.ConsumableProdInfo>();
       
      GuestInfo.data =  new StaticSentence(m_app.getSession(), " SELECT C.ID , L.NAME , P.NAME , D.NAME , A.NAME \n" +
                                                            "FROM DEPARTMENT D , PRODUCTS P , LOCATIONS L , ACCOUNTMASTER A , consprdacc C  \n" +
                                                            "WHERE C.ACTIVE=1 AND P.ID=C.PROID AND L.ID=P.LOCATION AND A.ID=C.ACCID AND C.DEPTID=D.ID\n" +
                                                            "ORDER BY L.NAME , P.NAME , D.NAME , A.NAME", 
                                                             SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableProdAccLinkTableModel.ConsumableProdInfo.class)).list();
       
      GuestInfo.size = GuestInfo.data.size();
         
     return GuestInfo;

  }
    
    public  static ConsumableProdAccLinkTableModel LoadProdInfoOrderbyDepartment(AppView m_app) throws BasicException{
      ConsumableProdAccLinkTableModel GuestInfo = new  ConsumableProdAccLinkTableModel();
      GuestInfo.data = new ArrayList<ConsumableProdAccLinkTableModel.ConsumableProdInfo>();
       
      GuestInfo.data =  new StaticSentence(m_app.getSession(), " SELECT C.ID , L.NAME , P.NAME , D.NAME , A.NAME \n" +
                                                            "FROM DEPARTMENT D , PRODUCTS P , LOCATIONS L , ACCOUNTMASTER A , consprdacc C  \n" +
                                                            "WHERE C.ACTIVE=1 AND P.ID=C.PROID AND L.ID=P.LOCATION AND A.ID=C.ACCID AND C.DEPTID=D.ID\n" +
                                                            "ORDER BY L.NAME ,  D.NAME , P.NAME ,  A.NAME", 
                                                             SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableProdAccLinkTableModel.ConsumableProdInfo.class)).list();
       
      GuestInfo.size = GuestInfo.data.size();
         
     return GuestInfo;

    }
    
    public  static ConsumableProdAccLinkTableModel LoadProdInfoOrderbyAccountwise(AppView m_app) throws BasicException{
      ConsumableProdAccLinkTableModel GuestInfo = new  ConsumableProdAccLinkTableModel();
      GuestInfo.data = new ArrayList<ConsumableProdAccLinkTableModel.ConsumableProdInfo>();
       
      GuestInfo.data =  new StaticSentence(m_app.getSession(), " SELECT C.ID , L.NAME , P.NAME , D.NAME , A.NAME \n" +
                                                            "FROM DEPARTMENT D , PRODUCTS P , LOCATIONS L , ACCOUNTMASTER A , consprdacc C  \n" +
                                                            "WHERE C.ACTIVE=1 AND P.ID=C.PROID AND L.ID=P.LOCATION AND A.ID=C.ACCID AND C.DEPTID=D.ID\n" +
                                                            "ORDER BY L.NAME ,   A.NAME , D.NAME , P.NAME    ", 
                                                             SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableProdAccLinkTableModel.ConsumableProdInfo.class)).list();
       
      GuestInfo.size = GuestInfo.data.size();
         
     return GuestInfo;

    }
    
    
     public int getSize()
      {
        return size;
      }
     
     public List<ConsumableProdAccLinkTableModel.ConsumableProdInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<ConsumableProdAccLinkTableModel.ConsumableProdInfo>();
      }
    
    
    
   public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              ConsumableProdAccLinkTableModel.ConsumableProdInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getWAREHOUSE();
                   case 2: return r.getPRODUCTNAME();
                   case 3: return r.getDEPARTMENT();
                   case 4: return r.getACCOUNTNAME();
                       
                       
                       
                       
                 }
                return null;
            }
          
          
          };
        }  
    
    
    
    
    
    
    
      public static class ConsumableProdInfo implements SerializableRead,IKeyed {

        private String ID;
        private String PRODUCTNAME;
        private String ACCOUNTNAME;
        private String WAREHOUSE;
        private String DEPARTMENT;
        
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getPRODUCTNAME(){
              return PRODUCTNAME;
          }
          public void setPRODUCTNAME(String PRODUCTNAME){
              this.PRODUCTNAME = PRODUCTNAME;
          }
          public String getACCOUNTNAME(){
              return ACCOUNTNAME;
          }
          public void setACCOUNTNAME(String ACCOUNTNAME){
              this.ACCOUNTNAME =ACCOUNTNAME;
          }
        
          public String getWAREHOUSE(){
              return WAREHOUSE;
          }
          public void setWAREHOUSE(String WAREHOUSE){
              this.WAREHOUSE = WAREHOUSE;
          }
          
         
          
          public String getDEPARTMENT(){
              return DEPARTMENT;
          }
          public void setDEPARTMENT(String DEPARTMENT){
              this.DEPARTMENT=DEPARTMENT;
          }
          
        
          
          
          public void readValues(DataRead dr) throws BasicException {
                ID = dr.getString(1);
                WAREHOUSE = dr.getString(2);
                PRODUCTNAME = dr.getString(3);
                DEPARTMENT = dr.getString(4);
                ACCOUNTNAME = dr.getString(5);
           }
        public Object getKey() {
           return this;
        }

       
     }   
    
    
    
    
    
}
