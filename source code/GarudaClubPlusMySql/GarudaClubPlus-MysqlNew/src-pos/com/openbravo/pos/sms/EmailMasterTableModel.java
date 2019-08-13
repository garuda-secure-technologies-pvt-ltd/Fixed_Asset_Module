
package com.openbravo.pos.sms;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;






public class EmailMasterTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<EmailMasterTableModel.EmailInfo> data;
    private List<EmailMasterTableModel.EmailIDInfo> data2;
    
    private int size;
    private final static String[] TABLEHEADERS = {"Sr No." , "UserName 1" , "CrBy" , "CrDate" , "No. of Emails/Hour(s)"};
    private final static String[] TABLEHEADERS1 = {"Sr No." , "Mem No." , "Member Name " , "Email ID" };
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
     public static EmailMasterTableModel loadEmailInfo(AppView app) throws BasicException{
        EmailMasterTableModel GuestInfo = new EmailMasterTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<EmailMasterTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT ID , USERNAME, PASSWORD , ACTIVE ,CRBY , CRDATE , SMTPSERVER , PORT , IFNULL(NOOFMAILS,0.00) , IFNULL(HOURS,0.00) FROM email_master  WHERE ACTIVE=1 ", SerializerWriteString.INSTANCE, new SerializerReadClass(EmailMasterTableModel.EmailInfo.class)).list();
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(EmailMasterTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
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
              EmailMasterTableModel.EmailInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getNAME();
                   case 2: return r.getCRBY();
                   case 3: return r.getCRDATE();
                   case 4: return (r.getNoOfEmails()+"/"+r.getHours());
                       
                       
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
      public int getSize()
      {
        return size;
      }
     
     public List<EmailMasterTableModel.EmailInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<EmailMasterTableModel.EmailInfo>();
      }
      
     
       public static class EmailInfo implements SerializableRead,IKeyed {

        private String ID;
        private String NAME;
        private String PASSWORD;
        private int ACTIVE;
        private String CRBY;
        private Date CRDATE;
        private String SMTPSERVER;
        private String PORT;
        private Double NoOfEmails;
        private Double Hours;
         
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
          public String getPASSWORD(){
              return PASSWORD;
          }
          public void setPASSWORD(String PASSWORD){
              this.PASSWORD =PASSWORD;
          }
        
          public int getACTIVE(){
              return ACTIVE;
          }
          public void setACTIVE(int ACTIVE){
              this.ACTIVE = ACTIVE;
          }
          
         
          
          public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
        
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
          public String getSMTPSERVER(){
              return SMTPSERVER;
          }
          public void setSMTPSERVER(String SMTPSERVER){
              this.SMTPSERVER=SMTPSERVER;
          }
          
           public String getPORT(){
              return PORT;
          }
          public void setPORT(String PORT){
              this.PORT=PORT;
          }
          
          public Double getNoOfEmails(){
              return NoOfEmails;
          }
          public void setNoOfEmails(Double NoOfEmails){
              this.NoOfEmails=NoOfEmails;
          }
          public Double getHours(){
              return Hours;
          } 
          public void setHours(Double Hours){
              this.Hours=Hours;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                NAME = dr.getString(2);
                PASSWORD = dr.getString(3);
                ACTIVE = dr.getInt(4);
                CRBY = dr.getString(5);
                CRDATE = dr.getTimestamp(6);
                SMTPSERVER = dr.getString(7);
                PORT = dr.getString(8);
                NoOfEmails = dr.getDouble(9);
                Hours = dr.getDouble(10);
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     // data to retrive ...............................
       
       
        //LIST OF USERNAME  FROM EMAIL
        public List getUsername(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT username FROM email_master  WHERE active=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
     
     
      //Email Password
        public String getEmailPassword(AppView app , String username) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT PASSWORD FROM email_master  WHERE active=1 AND USERNAME=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(username);
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }   
        
     
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      // MEMBERS NOT HAVING EMAIL ID ...............................................................................................
        
        
        
        
        
     public static EmailMasterTableModel GetMailIdofMembers(AppView app) throws BasicException{
     EmailMasterTableModel EmailidInfo = new EmailMasterTableModel(); 
    
     try{
            EmailidInfo.data2 = new ArrayList<EmailMasterTableModel.EmailIDInfo>();
            EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT SEARCHKEY , NAME , EMAIL FROM CUSTOMERS WHERE EMAIL IS  NULL  ORDER BY SEARCHKEY ", SerializerWriteString.INSTANCE, new SerializerReadClass(EmailMasterTableModel.EmailIDInfo.class)).list();
           
            EmailidInfo.size = EmailidInfo.data2.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(EmailMasterTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return EmailidInfo;
  
     }
    
        
        
        
        public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
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
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              EmailMasterTableModel.EmailIDInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getMEMNO();
                   case 2: return r.getNAME();
                   case 3: return r.getEMAIL();
                       
                       
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
       
        
        
        
        
        
        
        public static class EmailIDInfo implements SerializableRead,IKeyed {

        private String MEMNO;
        private String NAME;
        private String EMAIL;
      
        
       
         
         public String getMEMNO(){
              return MEMNO;
          }
          public void setMEMNO(String MEMNO){
              this.MEMNO=MEMNO;
          }
          public String getNAME(){
              return NAME;
          }
          public void setNAME(String NAME){
              this.NAME = NAME;
          }
          public String getEMAIL(){
              return EMAIL;
          }
          public void setEMAIL(String EMAIL){
              this.EMAIL =EMAIL;
          }
        
          
         
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                MEMNO = dr.getString(1);
                NAME = dr.getString(2);
                EMAIL = dr.getString(3);
                
               
               
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
        
        
        
        
        
        
}
