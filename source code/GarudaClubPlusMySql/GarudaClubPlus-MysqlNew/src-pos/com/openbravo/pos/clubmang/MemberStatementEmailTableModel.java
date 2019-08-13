

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.data.loader.SerializerReadClass;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class MemberStatementEmailTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private List<MemberStatementEmailTableModel.EmailInfo> data;
    private int size;
    private final static String[] TABLEHEADERS = {"Sr No." , "Member No." , "Mem Name" , "Email ID" , "Select"};
    
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    
     public static MemberStatementEmailTableModel LoadMemberListToSendEmail_VISIBLE(AppView app) throws BasicException{
        MemberStatementEmailTableModel GuestInfo = new MemberStatementEmailTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<MemberStatementEmailTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT ID , SEARCHKEY , NAME  , EMAIL , ACCOUNT FROM CUSTOMERS WHERE VISIBLE=TRUE AND EMAIL IS NOT NULL ORDER BY SEARCHKEY ", SerializerWriteString.INSTANCE, new SerializerReadClass(MemberStatementEmailTableModel.EmailInfo.class)).list();
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(MemberStatementEmailTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
    
    
     public static MemberStatementEmailTableModel LoadMemberListToSendEmail_MemType(AppView app , String MemType) throws BasicException{
        MemberStatementEmailTableModel GuestInfo = new MemberStatementEmailTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<MemberStatementEmailTableModel.EmailInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT C.ID , C.SEARCHKEY , C.NAME  , C.EMAIL , C.ACCOUNT  FROM CUSTOMERS C , MEMTYPE M WHERE C.VISIBLE=TRUE AND C.EMAIL IS NOT NULL AND M.NAME=? AND M.ID=C.MEMTYPE ORDER BY SEARCHKEY ", SerializerWriteString.INSTANCE, new SerializerReadClass(MemberStatementEmailTableModel.EmailInfo.class)).list(MemType);
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(MemberStatementEmailTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
     
      public int getSize()
    {
        return size;
    }
      
      
    public List<EmailInfo> getEmailMemberList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<MemberStatementEmailTableModel.EmailInfo>();
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
                
                EmailInfo r =data.get(row);
                
                if(column==4){
                    
                    r.setSelected(status);  
                    fireTableDataChanged();
                }
                
                 
                
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                MemberStatementEmailTableModel.EmailInfo r =data.get(rowIndex);
              
                
                    switch(columnIndex){

                        case 0: return (rowIndex+1);
                        case 1: return r.getSEARCHKEY();
                        case 2: return r.getNAME();
                        case 3: return r.getEMAIL();
                        case 4: return r.getSelect();
                        
                   
                            
                      }
                     return null;
                }
           };
        }
      
     
     
     
     
     
      public abstract class MyAbstractTableModel extends AbstractTableModel {

       
        public void settext() {
           
          }
       }
    
    
     
     
    
    
     
      public static class EmailInfo implements SerializableRead,IKeyed {

        private String ID;
        private String NAME;
        private String SEARCHKEY;
        private String EMAIL;
        private Boolean select = false;
        private String Account;
       
         
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
          public String getSEARCHKEY(){
              return SEARCHKEY;
          }
          public void setSEARCHKEY(String SEARCHKEY){
              this.SEARCHKEY =SEARCHKEY;
          }
        
        
         
          
          public String getEMAIL(){
              return EMAIL;
          }
          public void setEMAIL(String EMAIL){
              this.EMAIL=EMAIL;
          }
          
          public Boolean getSelect(){
              return select;
          }
          public void setSelected(Boolean select){
              this.select=select;
          }
          
          public String getAccount(){
              return Account;
          }
          public void setAccount(String Account){
              this.Account = Account;
          }
         
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                SEARCHKEY = dr.getString(2);
                NAME = dr.getString(3);
                EMAIL = dr.getString(4);
                Account = dr.getString(5);
               
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    
    
    // GET VALUES FOR EMAIL NOTIFICATION
    
    
    
      //Email Password
        public String getEmailIdUserName(AppView app ) throws BasicException{
          Object o = null;
          String Pass = null;
           o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable WHERE NAME='Mem Stat Email Account' ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find();
          
          if(o!=null){
              Pass = o.toString();
          } 
          else{
              Pass = null;
          }
           
           
          return Pass;
      }   
        
    
    
    
}
