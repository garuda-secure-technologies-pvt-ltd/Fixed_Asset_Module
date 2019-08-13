
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class EmailSentTableModel extends BeanFactoryDataSingle{
    
     private Session s;
     private final static String[] TABLEHEADERS = {"Sr No.", "Mem No." , "Recipient ID" , "Sent Date" , "Sent By" , "Sender ID" , "Subject" , "Attached Doc."};
     private int size;
     private List<EmailSentTableModel.EmailInfo> data;
     
     
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    public static EmailSentTableModel loadEmailInfo(AppView app , Date FmDate , Date ToDate) throws BasicException{
        EmailSentTableModel GuestInfo = new EmailSentTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<EmailSentTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT memno , emailid , date , crby , sentid , subject , IFNULL( DOCNAME , 'NOFILE' ) AS DOCNAME  FROM sentemail WHERE DATE >= ? AND DATE <=? ORDER BY DATE DESC  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) , new SerializerReadClass(EmailSentTableModel.EmailInfo.class)).list(new Object[]{ FmDate ,  ToDate   });
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(EmailSentTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
    
     public static EmailSentTableModel loadEmailByGroupNameInfo(AppView app , Date FmDate , Date ToDate , String GrpName) throws BasicException{
        EmailSentTableModel GuestInfo = new EmailSentTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<EmailSentTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT memno , emailid , date , crby , sentid , subject ,IFNULL( DOCNAME , 'NOFILE' ) AS DOCNAME   FROM sentemail WHERE DATE >= ? AND DATE <=?  AND MEMNO=? ORDER BY DATE DESC  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING }) , new SerializerReadClass(EmailSentTableModel.EmailInfo.class)).list(new Object[]{ FmDate ,  ToDate , GrpName  });
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(EmailSentTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
     
     public static EmailSentTableModel loadEmailByMembershipNoInfo(AppView app , Date FmDate , Date ToDate , String GrpName) throws BasicException{
        EmailSentTableModel GuestInfo = new EmailSentTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<EmailSentTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT memno , emailid , date , crby , sentid , subject , IFNULL( DOCNAME , 'NOFILE' ) AS DOCNAME  FROM sentemail WHERE DATE >= ? AND DATE <=?  AND MEMNO=? ORDER BY DATE DESC ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING }) , new SerializerReadClass(EmailSentTableModel.EmailInfo.class)).list(new Object[]{ FmDate ,  ToDate , GrpName  });

            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(EmailSentTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
// load email list by membership number.loadEmailByMemTypeInfo
     
     public static EmailSentTableModel loadEmailByMemTypeInfo(AppView app , Date FmDate , Date ToDate , String GrpName) throws BasicException{
        EmailSentTableModel GuestInfo = new EmailSentTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<EmailSentTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT m.memno , m.emailid , m.date , m.crby , m.sentid , m.subject , IFNULL( m.DOCNAME , 'NOFILE' ) AS DOCNAME \n" +
                                                                    " FROM sentemail m , Memtype t , customers c\n" +
                                                                    "WHERE m.DATE >= ? AND m.DATE <=?  AND c.memtype=t.id and m.memno=c.searchkey and t.name=?\n" +
                                                                    "ORDER BY m.DATE DESC  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING }) , new SerializerReadClass(EmailSentTableModel.EmailInfo.class)).list(new Object[]{ FmDate ,  ToDate , GrpName  });

            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(EmailSentTableModel.class.getName()).log(Level.SEVERE, null, ex);
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
              EmailSentTableModel.EmailInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getMEMNO();
                   case 2: return r.getRecipientID();
                   case 3: return r.getSENTDATE();
                   case 4: return r.getSENTBY();
                   case 5 :return r.getSenderID();
                   case 6: return r.getSubject();
                   case 7: if(r.getDOCNAME().equals("NOFILE")){
                              return "";
                            }
                            else{
                                return r.DOCNAME;
                            }
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
    
    
    
    
    
    
    
    
    public static class EmailInfo implements SerializableRead,IKeyed {

        private String MEMNO;
        private String RecipientID;
        private Date SENTDATE;
        private String SENTBY;
        
        private String Subject;
        private String SenderID;
        private String DOCNAME;
         
         public String getMEMNO(){
             return MEMNO;
         }
         public void setMEMNO(String MEMNO){
             this.MEMNO=MEMNO;
         }
         
         public String getRecipientID(){
             return RecipientID;
         }
         public void setRecipientID(String RecipientID){
             this.RecipientID=RecipientID;
         }
         public String getSENTBY(){
             return SENTBY;
         }
         public void setSENTBY(String SENTBY){
             this.SENTBY=SENTBY;
         }
         public String getSubject(){
             return Subject;
         }
         public void setSubject(String Subject){
             this.Subject=Subject;
         }
         
          public String getSenderID(){
             return SenderID;
         }
         public void setSenderID(String SenderID){
             this.SenderID=SenderID;
         }
         
         
         
         
        
           public String getSENTDATE(){
              String x = Formats.TIMESTAMP.formatValue(SENTDATE);
               return x;
          }
          public void setSENTDATE(Date SENTDATE){
              this.SENTDATE=SENTDATE;
          }
          public String getDOCNAME(){
              return  DOCNAME;
          }
          public void setDOCNAME(String DOCNAME){
              this.DOCNAME = DOCNAME;
          }
         
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                MEMNO = dr.getString(1);
                RecipientID = dr.getString(2);
                SENTDATE = dr.getTimestamp(3);
                SENTBY = dr.getString(4);
                SenderID = dr.getString(5);
                Subject = dr.getString(6);
                DOCNAME = dr.getString(7);
               
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
    
}
