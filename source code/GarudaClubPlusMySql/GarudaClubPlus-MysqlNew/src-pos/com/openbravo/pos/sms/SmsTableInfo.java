/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.SmsTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class SmsTableInfo {
    
    
    private List<SmsTableInfo.smsgroup> list = new ArrayList<SmsTableInfo.smsgroup>();
    private final static String[] TABLEHEADERS = {"Group Name","GroupMember" };
    private int size;
    
    private  SmsTableInfo()
   {
   }
    
    public static  SmsTableInfo emptyinstance()
  {
      SmsTableInfo d=new  SmsTableInfo();
      d.list=new ArrayList<SmsTableInfo.smsgroup>();
      return d;
  }
  public  static SmsTableInfo loadInstance(AppView m_app) throws BasicException{
       SmsTableInfo d = new  SmsTableInfo();
       
       
         d.list =  new StaticSentence(m_app.getSession(), "SELECT GROUPNAME,ACTIVE,Id,Date FROM sms_group_list WHERE ACTIVE=TRUE ORDER BY GROUPNAME ", 
               SerializerWriteString.INSTANCE, new SerializerReadClass(SmsTableInfo.smsgroup.class)).list();
       
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

                case 0: return s.getGroupname();
                
                case 1: return s.getGroupMember();
                
                default: return null;
                }
            }
        };
    }


public static class smsgroup implements SerializableRead{
    private String names;
 
    private Timestamp date;
    private int num;
    private Double amount;
    private String createdby;
    private String group_name;
    private String id;
    private String group_member;

    public void readValues(DataRead dr) throws BasicException
    {
        
           group_name=dr.getString(1);
           group_member=dr.getString(2);
         //date=dr.getTimestamp(4);
      
   }

    
    public String getGroupname(){
     return group_name;
   }
    public String getGroupMember(){
        return group_member;
    }
    
    
 }
    




   public String getGroupIdByName(AppView m_app , String GrpName) throws BasicException{
       String ID=null;
       Object o = null;
        o =  new StaticSentence(m_app.getSession(), "SELECT ID  FROM sms_group_list WHERE GROUPNAME =  ?", 
                                 SerializerWriteString.INSTANCE,
                                 SerializerReadString.INSTANCE).find(GrpName);

        if(o!=null){
            ID = o.toString();
        }        
       
       
       
       return ID;
   }
    





    
}
