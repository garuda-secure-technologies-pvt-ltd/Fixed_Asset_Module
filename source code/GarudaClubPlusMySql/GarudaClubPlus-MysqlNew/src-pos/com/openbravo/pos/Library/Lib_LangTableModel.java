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
public class Lib_LangTableModel {
    private List<lib_Langline> lbl;
    private List<Lib_LangTableModel.lib_Langline> alist;
    private int size;
    private final static String[] LANGHEADERS = {"Name","Createdby","CREATEDHOST"}; 
    private final static String[] LANGHEADERS1 = {"Name","Createdby","CREATEDHOST","DEACTIVATEDBY","DEACTHOST"};
    private int flag;
    
    private Lib_LangTableModel()
  {
      
  }
    
    public static Lib_LangTableModel emptyinstance()
  {
      Lib_LangTableModel lib_a=new Lib_LangTableModel();
      lib_a.lbl=new ArrayList<lib_Langline>();
      return lib_a;
  } 
    
    public static Lib_LangTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_LangTableModel l = new Lib_LangTableModel();
         l.flag=flag;
         try
      {
          if(flag==1){
       l.alist = new StaticSentence(app.getSession()
                , "SELECT A.NAME,A.CREATEDBY,A.CREATEDHOST,A.DEACTBY,A.DEACTHOST,A.ID,A.DEACTREFERENCE,A.active FROM LIB_LANGUAGE A WHERE A.ACTIVE =TRUE ORDER BY A.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_LangTableModel.lib_Langline.class)).list();
          }else{
              l.alist = new StaticSentence(app.getSession()
                , "SELECT A.NAME,A.CREATEDBY,A.CREATEDHOST,A.DEACTBY,A.DEACTHOST,A.ID,A.DEACTREFERENCE,A.active FROM LIB_LANGUAGE A ORDER BY A.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_LangTableModel.lib_Langline.class)).list();
          }
                l.size=l.alist.size();
         if(l.alist==null)
         {
             l.lbl=new ArrayList<Lib_LangTableModel.lib_Langline>();
         }
         else
        {
            l.lbl=l.alist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_LangTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return l;
     }
     public List<lib_Langline> getfacilityline()
     {
         return lbl;
     }
     
      public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if(flag==1){
                return AppLocal.getIntString(LANGHEADERS[column]);
                }else{
                    return AppLocal.getIntString(LANGHEADERS1[column]);
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
                return LANGHEADERS.length;
                 }else{
                     return LANGHEADERS1.length;
                 }
            }
            public Object getValueAt(int row, int column) {
                Lib_LangTableModel.lib_Langline l = lbl.get(row);

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
      
      public static class lib_Langline implements SerializableRead,IKeyed{ 
          
            private String id;
            private String name;
            private String createdby;
            private String deactivatedby;
            private String createdhost;
            private String deactivatedhost;
            private String deactrefe;
            private boolean active;

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

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
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
        

        @Override
        public Object getKey() {
             return id;
        }
          
          
          
      }
      public int getSize()
      {
        return size;
      }
     
    public List<Lib_LangTableModel.lib_Langline> getList(){
           if(alist!=null)
        {
            return alist;
        }
        else
            return new ArrayList<Lib_LangTableModel.lib_Langline>();
      }
    
}
