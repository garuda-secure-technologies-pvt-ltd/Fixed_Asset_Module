

package com.openbravo.pos.admin;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.sms.EmailMasterTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;


public class ProcesslistTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<ProcesslistTableModel.ProcessInfo> data;
    private List<ProcesslistTableModel.IPAddressListInfo> data2;
    private int size;
    private int size2;
    private final static String[] TABLEHEADERS = {"Process Id No." , "User" , "Host" , "DB" , "State" , "Command" , "Time" , "Info" , "System Desc"};
    private final static String[] TABLEHEADERS2 = {"Sr. No" , "Ip Address/ Host Name" , "Description"};
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    public static ProcesslistTableModel LoadProcessListAll(AppView app,List<String> ActiveIpList) throws BasicException{
        ProcesslistTableModel GuestInfo = new ProcesslistTableModel(); 
        
     try{
           GuestInfo.data = new ArrayList<ProcesslistTableModel.ProcessInfo>();
         //  GuestInfo.data = new StaticSentence(app.getSession(), " SELECT * FROM INFORMATION_SCHEMA.PROCESSLIST ", SerializerWriteString.INSTANCE, new SerializerReadClass(ProcesslistTableModel.ProcessInfo.class)).list();
         //  GuestInfo.size = GuestInfo.data.size();
         
         for(int i=0;i<ActiveIpList.size();i++){
             String ipaddress = ActiveIpList.get(i).toString();
             ipaddress="%"+ipaddress+"%";
             
             
             List<ProcesslistTableModel.ProcessInfo> temp = new StaticSentence(app.getSession(), " SELECT *,(select description from iplist where ipaddress like '" +ipaddress+ "' and active=1 ) \n" +
                                                                                                "FROM INFORMATION_SCHEMA.PROCESSLIST p where p.host like ? ",
                                                                                                SerializerWriteString.INSTANCE, new SerializerReadClass(ProcesslistTableModel.ProcessInfo.class)).list(ipaddress);
             
             GuestInfo.data.addAll(temp);
             
             
           /*  
             
             Object[] obj = (Object[]) new StaticSentence(app.getSession(),
                  " SELECT id,user,host,db,command,time,state,info FROM INFORMATION_SCHEMA.PROCESSLIST p where p.host like  ?  ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).find(ipaddress);
              
            Object[] obj2 = (Object[]) new StaticSentence(app.getSession(),
                  " SELECT description from iplist where ipaddress like ?  and active=1  ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ipaddress);
              
             
             if(obj!=null){
                ProcessInfo p = new ProcessInfo();
                p.setID(obj[0].toString());
                p.setUSER(obj[1].toString());
                p.setHOST(obj[2].toString());
                if(obj[3]!=null){
                    p.setDB(obj[3].toString());
                }
                else{
                    p.setDB("");
                }
                if(obj[4]!=null){
                    p.setCOMMAND(obj[4].toString());
                }
                else{
                    p.setCOMMAND("");
                }
                if(obj[5]!=null){
                    p.setTIME(obj[5].toString());
                }
                else{
                    p.setTIME("");
                }
                if(obj[6]!=null){
                   p.setSTATE(obj[6].toString()); 
                }
                else{
                    p.setSTATE("");
                }
                if(obj[7]!=null){
                   p.setINFO(obj[7].toString()); 
                }
                else{
                   p.setINFO("");
                }
                if(obj2!=null){
                    if(obj2[0]!=null){
                        p.setSystemDesc(obj2[0].toString());
                    }
                    else{
                        p.setSystemDesc("");
                    }
                }
                else{
                    p.setSystemDesc("");
                }
                
                GuestInfo.data.add(p);
            }
             
             
            */ 
             
             
             
         }
         
         
         GuestInfo.size = GuestInfo.data.size();
         
         
        }
        catch(BasicException ex){
            Logger.getLogger(ProcesslistTableModel.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            new MessageInf(ex).show(new JFrame());
        }
     return GuestInfo;
  
     }
    
    
    public static ProcesslistTableModel LoadIpAddressDetails(AppView app) throws BasicException{
        ProcesslistTableModel GuestInfo = new ProcesslistTableModel(); 
    
     try{
            GuestInfo.data2 = new ArrayList<ProcesslistTableModel.IPAddressListInfo>();
            GuestInfo.data2 = new StaticSentence(app.getSession(), "select id , ipaddress,description from iplist where active=true order by ipaddress ", SerializerWriteString.INSTANCE, new SerializerReadClass(ProcesslistTableModel.IPAddressListInfo.class)).list();
           
            GuestInfo.size2 = GuestInfo.data2.size();
        }
        catch(BasicException ex){
            Logger.getLogger(ProcesslistTableModel.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            new MessageInf(ex).show(new JFrame());
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
              ProcesslistTableModel.ProcessInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getID();
                   case 1: return r.getUSER();
                   case 2: return r.getHOST();
                   case 3: return r.getDB();
                   case 4: return r.getSTATE();
                   case 5: return r.getCOMMAND();    
                   case 6: return r.getTIME();    
                   case 7: return r.getINFO();
                   case 8: return r.getSystemDesc();
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
    
      public int getSize()
      {
        return size;
      }
     
     public List<ProcesslistTableModel.ProcessInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<ProcesslistTableModel.ProcessInfo>();
      }
      
    
    
    
    
    
    
    
     public static class ProcessInfo implements SerializableRead,IKeyed {

        private String ID;
        private String USER;
        private String HOST;
        private String DB;
        private String COMMAND;
        private String TIME;
        private String STATE;
        private String INFO;
        private String SystemDesc;
        
        
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getUSER(){
              return USER;
          }
          public void setUSER(String USER){
              this.USER = USER;
          }
          public String getHOST(){
              return HOST;
          }
          public void setHOST(String HOST){
              this.HOST =HOST;
          }
        
          public String getDB(){
              return DB;
          }
          public void setDB(String DB){
              this.DB = DB;
          }
          
         
          
          public String getCOMMAND(){
              return COMMAND;
          }
          public void setCOMMAND(String COMMAND){
              this.COMMAND=COMMAND;
          }
          
        
           public String getTIME(){
              //String x = Formats.TIMESTAMP.formatValue(TIME);
               return TIME;
          }
          public void setTIME(String TIME){
              this.TIME=TIME;
          }
          
          public String getSTATE(){
              return STATE;
          }
          public void setSTATE(String STATE){
              this.STATE=STATE;
          }
          
          public String getINFO(){
              return INFO;
          }
          public void setINFO(String INFO){
              this.INFO=INFO;
          }
          
          public String getSystemDesc(){
              return SystemDesc;
          }
          public void setSystemDesc(String SystemDesc){
              this.SystemDesc=SystemDesc;
          }
          
          
          public void readValues(DataRead dr) throws BasicException {
                ID = dr.getString(1);
                USER = dr.getString(2);
                HOST = dr.getString(3);
                DB = dr.getString(4);
                COMMAND = dr.getString(5);
                TIME = dr.getString(6);
                STATE = dr.getString(7);
                INFO = dr.getString(8);
                SystemDesc=dr.getString(9);
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
     public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data2.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS2.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS2[column];
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
              ProcesslistTableModel.IPAddressListInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return rowIndex+1;
                   case 1: return r.getIPAddress();
                   case 2: return r.getDesc();
                   case 3: return r.getID();
                 }
                return null;
            }
          
          
          };
        } 
     
     
     
     
     
     public static class IPAddressListInfo implements SerializableRead,IKeyed {

        private String ID;
        private String IPAddress;
        private String Desc;
        
        public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getIPAddress(){
              return IPAddress;
          }
          public void setIPAddress(String IPAddress){
              this.IPAddress = IPAddress;
          }
          public String getDesc(){
              return Desc;
          }
          public void setHOST(String Desc){
              this.Desc =Desc;
          }
        
          public void readValues(DataRead dr) throws BasicException {
                ID = dr.getString(1);
                IPAddress = dr.getString(2);
                Desc = dr.getString(3);
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
    
}
