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
public class Lib_MediaTableModel {
    
     private List<lib_Medialine> lbl;
    private List<Lib_MediaTableModel.lib_Medialine> alist;
    private int size;
    private final static String[] ISSUEHEADERS = {"Name","Createdby","CREATEDHOST"}; 
    private final static String[] ISSUEHEADERS1 = {"Name","Createdby","CREATEDHOST","DEACTIVATEDBY","DEACTHOST"};
    private int flag;
    
    
    private Lib_MediaTableModel()
  {
      
  }
    
    public static Lib_MediaTableModel emptyinstance()
  {
      Lib_MediaTableModel lib_i=new Lib_MediaTableModel();
      lib_i.lbl=new ArrayList<lib_Medialine>();
      return lib_i;
  } 
    
    public static Lib_MediaTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_MediaTableModel l = new Lib_MediaTableModel();
         l.flag=flag;
         try
      {
          if(flag==1){
       l.alist = new StaticSentence(app.getSession()
                , "SELECT A.NAME,A.CREATEDBY,A.CREATEDHOST,A.DEACTBY,A.DEACTHOST,A.ID,A.DEACTREFERENCE,A.active FROM LIB_MEDIA A WHERE A.ACTIVE =TRUE ORDER BY A.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_MediaTableModel.lib_Medialine.class)).list();
          }else{
              l.alist = new StaticSentence(app.getSession()
                , "SELECT A.NAME,A.CREATEDBY,A.CREATEDHOST,A.DEACTBY,A.DEACTHOST,A.ID,A.DEACTREFERENCE,A.active FROM LIB_MEDIA A ORDER BY A.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_MediaTableModel.lib_Medialine.class)).list();
          }
                l.size=l.alist.size();
         if(l.alist==null)
         {
             l.lbl=new ArrayList<Lib_MediaTableModel.lib_Medialine>();
         }
         else
        {
            l.lbl=l.alist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_MediaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return l;
     }
    
    public List<lib_Medialine> getfacilityline()
     {
         return lbl;
     }
    
    public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if(flag==1){
                return AppLocal.getIntString(ISSUEHEADERS[column]);
                }else{
                    return AppLocal.getIntString(ISSUEHEADERS1[column]);
                }
            }
            @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return lbl.size();
            }
            public int getColumnCount() {
                 if(flag==1){
                return ISSUEHEADERS.length;
                 }else{
                     return ISSUEHEADERS1.length;
                 }
            }
            public Object getValueAt(int row, int column) {
                Lib_MediaTableModel.lib_Medialine l = lbl.get(row);

                switch (column) {
                case 0: return l.getName();
                case 1: return l.getCreatedby();
                case 2:return l.getCreatedhost();
                case 3:return l.getDeactivatedby();
                case 4:return l.getDeactivatedhost();
                case 5:return l.getDeactrefe();
                case 6:return l.getId();
                case 7:return l.isActive();
                default: return null;
                }
            }
        };
    }
    
    
    public static class lib_Medialine implements SerializableRead,IKeyed{ 
          
            private String id;
            private String name;
            private String createdby;
            private String deactivatedby;
            private String createdhost;
            private String deactivatedhost;
            private String deactrefe;
            private boolean active;

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

        public String getDeactrefe() {
            return deactrefe;
        }

        public void setDeactrefe(String deactrefe) {
            this.deactrefe = deactrefe;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
            
            
            
        @Override
        public void readValues(DataRead dr) throws BasicException {
          
            id=dr.getString(6);
            name=dr.getString(1);
            createdby=dr.getString(2);
            createdhost=dr.getString(3);
            deactivatedby=dr.getString(4);
            deactivatedhost=dr.getString(5);
            deactrefe=dr.getString(7);
            active=dr.getBoolean(8);
            
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
     
    public List<Lib_MediaTableModel.lib_Medialine> getList(){
           if(alist!=null)
        {
            return alist;
        }
        else
            return new ArrayList<Lib_MediaTableModel.lib_Medialine>();
      }
    
    }
