package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;

import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class SmsTableModel {

  
    private List<SmsTableModel.smsgroup> list = new ArrayList<SmsTableModel.smsgroup>();
    private final static String[] TABLEHEADERS = {"Sr No. ", "Group Name" ," Cr. Date" , "No. Of Members" };
    private int size;
    
    
  private  SmsTableModel()
   {
   }

  public static  SmsTableModel emptyinstance()
  {
      SmsTableModel d=new  SmsTableModel();
      d.list=new ArrayList<SmsTableModel.smsgroup>();
      return d;
  }
  public  static SmsTableModel loadSmsGroupNameList(AppView m_app) throws BasicException{
       SmsTableModel d = new  SmsTableModel();
       d.list=new ArrayList<SmsTableModel.smsgroup>();
       
         d.list =  new StaticSentence(m_app.getSession(), "SELECT L.GROUPNAME,L.ACTIVE,L.Id,L.Date , (select count(*) from sms_grp_mem M where M.groupname = L.ID ) AS MEMBERS  FROM sms_group_list L WHERE L.ACTIVE=TRUE ORDER BY L.GROUPNAME", 
               SerializerWriteString.INSTANCE, new SerializerReadClass(SmsTableModel.smsgroup.class)).list();
       
         d.size = d.list.size();
         
     return d;

  }

  
  // METHOD TO GET DEACTIVATED GROUP .......................................................................................................... @ AAQKASH
  
   public  static SmsTableModel loadSmsGroupNameList_Deactivated(AppView m_app) throws BasicException{
       SmsTableModel d = new  SmsTableModel();
       d.list=new ArrayList<SmsTableModel.smsgroup>();
       
         d.list =  new StaticSentence(m_app.getSession(), "SELECT L.GROUPNAME,L.ACTIVE,L.Id,L.Date , (select count(*) from sms_grp_mem M where M.groupname = L.ID ) AS MEMBERS  FROM sms_group_list L  WHERE L.ACTIVE=FALSE ORDER BY L.GROUPNAME", 
               SerializerWriteString.INSTANCE, new SerializerReadClass(SmsTableModel.smsgroup.class)).list();
       
         d.size = d.list.size();
         
     return d;

  }
  
  
  
  
  
  
  
  
  public List<smsgroup> getsmsgroupList()
     {
         return list;
     }
  
  
  
  public int getSize(){
      return size;
  }
  
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS[column]);
            }
            public int getRowCount() {
                return list.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                smsgroup s = list.get(row);

                switch (column) {

                case 0: return (row+1);
                case 1: return s.getGroupname();
                case 2: return s.getDate();
                case 3: return s.getMem_Nos();
                    
                default: return null;
                }
            }
        };
    }


public static class smsgroup implements SerializableRead{
    private String names;
 
    private Date date;
    private int Active;
    private Double amount;
    private String createdby;
    private String group_name;
    private String id;
    private int Mem_Nos;

    public void readValues(DataRead dr) throws BasicException
    {
        
         group_name=dr.getString(1);
         Active=dr.getInt(2);
         id=dr.getString(3);
         date=dr.getTimestamp(4);
         Mem_Nos = dr.getInt(5);
   }

    public String getDate() {
        String D = Formats.TIMESTAMP.formatValue(date);
        return D;
    }
    public void setCrDate(Date date){
        this.date=date;
    }
    
    public String getGroupname(){
     return group_name;
    }
    
     public void setGroupname(String group_name){
        this.group_name=group_name;
    }
    
    public String getId(){
        return id;
    }
      public void setid(String id){
        this.id=id;
    }
    
    
    public int getActive(){
      return Active;
    }
    
    
   public void setActive(int Active){
        this.Active=Active;
    }
   
   
   public int getMem_Nos(){
       return Mem_Nos;
   }
   public void setMem_Nos(int Mem_Nos){
       this.Mem_Nos = Mem_Nos;
   }
    
    
 }
}

