/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.SmsTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev1
 */
public class EmailMasterTableForCreateGroup {
    
    private List<EmailMasterTableForCreateGroup.Emailgroup> list = new ArrayList<EmailMasterTableForCreateGroup.Emailgroup>();
    private final static String[] TABLEHEADERS = {"Sr No. ", "Group Name" ," Cr. Date" , "No.of EmailId" };
    private int size;
    
    
    private  EmailMasterTableForCreateGroup()
   {
   }

  public static EmailMasterTableForCreateGroup emptyinstance()
  {
      EmailMasterTableForCreateGroup d=new  EmailMasterTableForCreateGroup();
      d.list=new ArrayList<EmailMasterTableForCreateGroup.Emailgroup>();
      return d;
  }
  public  static EmailMasterTableForCreateGroup loademailGroupNameList(AppView m_app) throws BasicException{
      EmailMasterTableForCreateGroup d = new  EmailMasterTableForCreateGroup();
       d.list=new ArrayList<EmailMasterTableForCreateGroup.Emailgroup>();
       
         d.list =  new StaticSentence(m_app.getSession(), "SELECT L.GROUP_NAME,L.ACTIVE,L.Id,L.Date , (select count(*) from email_grp_mem M where M.GroupNameId = L.ID ) AS MEMBERS  FROM  email_group_list L WHERE L.ACTIVE=TRUE ORDER BY L.GROUP_NAME", 
               SerializerWriteString.INSTANCE, new SerializerReadClass(EmailMasterTableForCreateGroup.Emailgroup.class)).list();
       
         d.size = d.list.size();
         
     return d;

  }

  public  static EmailMasterTableForCreateGroup loademailGroupNameList_Deactivated(AppView m_app) throws BasicException{
       EmailMasterTableForCreateGroup d = new  EmailMasterTableForCreateGroup();
       d.list=new ArrayList<EmailMasterTableForCreateGroup.Emailgroup>();
       
         d.list =  new StaticSentence(m_app.getSession(), "SELECT L.GROUP_NAME,L.ACTIVE,L.Id,L.Date , (select count(*) from email_grp_mem M where M.GroupNameId  = L.ID ) AS MEMBERS  FROM email_group_list L  WHERE L.ACTIVE=FALSE ORDER BY L.GROUP_NAME", 
               SerializerWriteString.INSTANCE, new SerializerReadClass(EmailMasterTableForCreateGroup.Emailgroup.class)).list();
       
         d.size = d.list.size();
         
     return d;

  }
    
    
    
  public List<Emailgroup> getEmailgroupList()
     {
         return list;
     }
    
    public int getSize(){
      return size;
  }
  
  public  AbstractTableModel getTableModel() {
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
               EmailMasterTableForCreateGroup.Emailgroup s = list.get(row);

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
  
  
  
    
   public static class Emailgroup implements SerializableRead{
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
