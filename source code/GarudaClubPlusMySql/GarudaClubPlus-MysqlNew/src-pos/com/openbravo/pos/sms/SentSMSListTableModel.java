
package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
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


public class SentSMSListTableModel extends BeanFactoryDataSingle{
    
    
     private Session s;
     private final static String[] TABLEHEADERS = {"Sr No.", "Mem No." , "SMS Sent Dt." , "Sent By" , "Mobile No." , "Message" };
     private int size;
     private List<SentSMSListTableModel.SMSInfo> data;
     
     
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    public static SentSMSListTableModel LoadBirthdaySMS(AppView app , Date FmDate , Date ToDate , int Flag) throws BasicException{
        SentSMSListTableModel GuestInfo = new SentSMSListTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<SentSMSListTableModel.SMSInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT ID , MEMNO , DATEOFSENT , CRBY , MESSAGE , MOBILE , FLAG FROM sentbirthdaywish \n" +
                                                                    "where DATEOFSENT>=? and DATEOFSENT <= ? and flag=? ORDER BY DATEOFSENT  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.INT }) , new SerializerReadClass(SentSMSListTableModel.SMSInfo.class)).list(new Object[]{ FmDate ,  ToDate  , Flag });

            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(SentSMSListTableModel.class.getName()).log(Level.SEVERE, null, ex);
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
              SentSMSListTableModel.SMSInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getMEMNO();
                   case 2: return r.getSENTDATE();
                   case 3: return r.getSENTBY();
                   case 4: return r.getMOBILE();
                   case 5 :return r.getMESSAGE();
                 
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
    
    
    
    
    
    
    
     public static class SMSInfo implements SerializableRead,IKeyed {

        private String MEMNO;
        private String ID;
        private Date SENTDATE;
        private String SENTBY;
        
        private String MESSAGE;
        private String MOBILE;
        private int FLAG;
       
         
         public String getMEMNO(){
             return MEMNO;
         }
         public void setMEMNO(String MEMNO){
             this.MEMNO=MEMNO;
         }
         
         public String getID(){
             return ID;
         }
         public void setID(String ID){
             this.ID=ID;
         }
         public String getSENTBY(){
             return SENTBY;
         }
         public void setSENTBY(String SENTBY){
             this.SENTBY=SENTBY;
         }
         public String getMESSAGE(){
             return MESSAGE;
         }
         public void setMESSAGE(String MESSAGE){
             this.MESSAGE=MESSAGE;
         }
         
          public String getMOBILE(){
             return MOBILE;
         }
         public void setMOBILE(String MOBILE){
             this.MOBILE=MOBILE;
         }
         
         public int getFLAG(){
             return FLAG;
         }
         public void setFLAG(int FLAG){
             this.FLAG = FLAG;
         }
         
         
        
           public String getSENTDATE(){
              String x = Formats.TIMESTAMP.formatValue(SENTDATE);
               return x;
          }
          public void setSENTDATE(Date SENTDATE){
              this.SENTDATE=SENTDATE;
          }
          
         
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                MEMNO = dr.getString(2);
                SENTDATE = dr.getTimestamp(3);
                SENTBY = dr.getString(4);
                MESSAGE = dr.getString(5);
                MOBILE = dr.getString(6);
                FLAG = dr.getInt(7);
               
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
    
    
}
