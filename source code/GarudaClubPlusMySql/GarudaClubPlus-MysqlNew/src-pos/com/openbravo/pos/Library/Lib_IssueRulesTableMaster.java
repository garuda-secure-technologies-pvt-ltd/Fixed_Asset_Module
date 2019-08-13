/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santhosh
 */
public class Lib_IssueRulesTableMaster {
    
    private List<lib_Issueline> lb;
    private List<Lib_IssueRulesTableMaster.lib_Issueline> clist;
    private final static String[] ISSUEHEADERS1 = {"Name","AppMemType","Periodicity","Createdby","CREATEDHOST"};
     private final static String[] ISSUEHEADERS = {"Name","AppMemType","Periodicity","Createdby","CREATEDHOST","Deactivated by","DEACTHOST"};
    private int size;
    private int flag;
    
    private Lib_IssueRulesTableMaster()
  {
  }
    public static Lib_IssueRulesTableMaster emptyinstance()
  {
      Lib_IssueRulesTableMaster lib_i=new Lib_IssueRulesTableMaster();
      lib_i.lb=new ArrayList<lib_Issueline>();
      return lib_i;
  } 
    
   public static Lib_IssueRulesTableMaster loadInstance(AppView app,int flag) throws BasicException{
         Lib_IssueRulesTableMaster li = new Lib_IssueRulesTableMaster();
         li.flag = flag;
         try
      {
          if(flag==1){
              li.clist = new StaticSentence(app.getSession()
                , "SELECT b.NAME,a.name,p.name,b.CREATEDBY,b.CREATEDHOST,b.DEACTBY,b.DEACTHOST,b.DEACTREFERENCE,b.ID,b.active,b.Memberint FROM lib_issuerules b,memtype a,periodicity p WHERE b.ACTIVE =TRUE and a.id=b.AppMemType and p.id=b.periodicity ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_IssueRulesTableMaster.lib_Issueline.class)).list();
          }else{
              li.clist = new StaticSentence(app.getSession()
                , "SELECT b.NAME,a.name,p.name,b.CREATEDBY,b.CREATEDHOST,b.DEACTBY,b.DEACTHOST,b.DEACTREFERENCE,b.ID,b.active,b.Memberint FROM lib_issuerules b,memtype a,periodicity p WHERE a.id=b.AppMemType and p.id=b.periodicity ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_IssueRulesTableMaster.lib_Issueline.class)).list();
              
          }
              
                li.size=li.clist.size();
         if(li.clist==null)
         {
             li.lb=new ArrayList<Lib_IssueRulesTableMaster.lib_Issueline>();
         }
         else
        {
            li.lb=li.clist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_IssueRulesTableMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return li;
     } 
    public List<lib_Issueline> getfacilityline()
     {
         return lb;
     }
    public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if (flag == 1) {
                    
                return AppLocal.getIntString(ISSUEHEADERS1[column]);
                }else{
                    return AppLocal.getIntString(ISSUEHEADERS[column]);
                }
            }
             @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return lb.size();
            }
            public int getColumnCount() {
            if (flag == 1) {
                return ISSUEHEADERS1.length;
            }else{
                return ISSUEHEADERS.length;
            }
            }
            public Object getValueAt(int row, int column) {
                Lib_IssueRulesTableMaster.lib_Issueline l = lb.get(row);

                switch (column) {
                   
                case 0: return l.getName();
                case 1: return l.getMemtyp();
                case 2: return l.getPeriodicity();
                case 3: return l.getCreatedby();
                case 4: return l.getCreatedhost();
                case 5: return l.getDeactivatedby();
                case 6: return l.getDeactivatedhost();
                case 7: return l.getDeactreference();
                case 8: return l.getId();
                case 9: return l.isActive();
                case 10: return l.getMember();      
                default: return null;
                }
            }
        };
    }
     public static class lib_Issueline implements SerializableRead,IKeyed{
         
    private String id;
    private String name;
    private String memtyp;
    private String periodicity;
    private int member;    
    private String createdby;
    private String deactivatedby;
    private String createdhost;
    private String deactivatedhost;
    private String deactreference;
    private boolean active;
        @Override
        public void readValues(DataRead dr) throws BasicException {
            name=dr.getString(1);
            memtyp=dr.getString(2);
            periodicity=dr.getString(3);
            createdby=dr.getString(4);
            createdhost=dr.getString(5);
            deactivatedby=dr.getString(6);
            deactivatedhost=dr.getString(7);
            deactreference=dr.getString(8);
            id= dr.getString(9); 
            active= dr.getBoolean(10);
            member=dr.getInt(11);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMemtyp() {
            return memtyp;
        }

        public void setMemtyp(String memtyp) {
            this.memtyp = memtyp;
        }

        public String getPeriodicity() {
            return periodicity;
        }

        public void setPeriodicity(String periodicity) {
            this.periodicity = periodicity;
        }

        public int getMember() {
            return member;
        }

        public void setMember(int member) {
            this.member = member;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getDeactivatedby() {
            return deactivatedby;
        }

        public void setDeactivatedby(String deactivatedby) {
            this.deactivatedby = deactivatedby;
        }

        public String getCreatedhost() {
            return createdhost;
        }

        public void setCreatedhost(String createdhost) {
            this.createdhost = createdhost;
        }

        public String getDeactivatedhost() {
            return deactivatedhost;
        }

        public void setDeactivatedhost(String deactivatedhost) {
            this.deactivatedhost = deactivatedhost;
        }

        public String getDeactreference() {
            return deactreference;
        }

        public void setDeactreference(String deactreference) {
            this.deactreference = deactreference;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
        
        
        @Override
        public Object getKey() {
              return id;
        }
         
         
         
     }
     public int getSize()
      {
        return size;
      }
     public List< Lib_IssueRulesTableMaster.lib_Issueline> getList(){
           if(clist!=null)
        {
            return clist;
        }
        else
            return new ArrayList< Lib_IssueRulesTableMaster.lib_Issueline>();
      }
    
}
