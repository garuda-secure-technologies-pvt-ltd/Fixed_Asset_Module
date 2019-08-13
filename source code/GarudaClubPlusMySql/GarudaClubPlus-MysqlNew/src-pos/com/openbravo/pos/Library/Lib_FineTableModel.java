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
import com.openbravo.data.loader.SerializerReadString;
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
 * @author dev1
 */
public class Lib_FineTableModel {
    
     private static AppView m_App;
    private List<Lib_FineTableModel.lib_catgry> blist;
    private List<Lib_FineTableModel.lib_catgry> alist;
    private int size;
    private List<lib_catgry> lb1;
    private List<lib_catgry> lb;
    private final static String[] ISSUELIST = {"BookCategory","No_of_days","MemType","fineAmount","CreatedBy","CreatedHost"};
    private static List<Object> tblist = new ArrayList<Object>();
    private static List<Object> tblist1 = new ArrayList<Object>();
    private String[] catarry;
    private List<Object> catarry2;
    public Lib_FineTableModel() {
    }
    public static Lib_FineTableModel emptyinstance()
  {
      Lib_FineTableModel lib_a=new Lib_FineTableModel();
      lib_a.lb=new ArrayList<lib_catgry>();
      return lib_a;
  }
    
     public static Lib_FineTableModel loadInstance(AppView app) throws BasicException{
         Lib_FineTableModel l = new Lib_FineTableModel();
         m_App=app;
         //try
     // {
          
       l.alist = new StaticSentence(app.getSession()
                , "select ft.id,ft.category_id,ft.no_days,ft.catg_nrbooks,ft.fineamount,ft.createdby,ft.createdhost,ft.deactby,ft.deacthost,ft.deactreference,ft.active from lib_fineMaster ft where ft.active=true"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_FineTableModel.lib_catgry.class)).list();
          
       /*if(tbdata.size()>0){
           //l.alist.add(new lib_catgry());
           tblist.add(tbdata.get(0));
           tblist1.add(tbdata.get(1));
           
       }*/
                l.size=l.alist.size();
         if(l.alist==null)
         {
             l.lb1=new ArrayList<Lib_FineTableModel.lib_catgry>();
         }
         else
        {
            l.lb1=l.alist;
        }
      /*}catch(BasicException ex){
            Logger.getLogger(Lib_MemTypeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
         return l;
     }
     
     
     
     
    public static class lib_catgry implements SerializableRead,IKeyed{
        private String id;
       
        private String memshiptype;
        private String no_days;
        private String fineamount;
        private String no_of_days;
        private String catg_books;
        private String createdby;
        private String deactivatedby;
        private String createdhost;
        private String deactivatedhost;
        private String deactrefer;
        private boolean active;

        
        @Override
        public void readValues(DataRead dr) throws BasicException {
            id=dr.getString(1);
            catg_books=dr.getString(2);
            no_of_days=dr.getString(3);
            memshiptype=dr.getString(4);
           fineamount=dr.getString(5); 
            createdby=dr.getString(6);
            createdhost=dr.getString(7);
            deactivatedby=dr.getString(8);
            deactivatedhost=dr.getString(9);
            deactrefer=dr.getString(10);
            active=dr.getBoolean(11);
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemtype() {
            return memshiptype;
        }

        public void setMemtype(String memshiptype) {
            this.memshiptype = memshiptype;
        }

      
        public String getCatg_books() {
            return catg_books;
        }

        public void setCatg_books(String catg_books) {
            this.catg_books = catg_books;
        }

        public String getNo_Days() {
            return no_of_days;
        }

        public void setNo_Days(String no_days) {
            this.no_of_days = no_of_days;
        }
 
        public String getFine_Amount()
        {
            return fineamount;
        }
        public void setFine_Amount(String fineamount)
        {
            this.fineamount=fineamount;
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

        public String getDeactrefer() {
            return deactrefer;
        }

        public void setDeactrefer(String deactrefer) {
            this.deactrefer = deactrefer;
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
    
    public static class lib_catgline implements SerializableRead,IKeyed{ 
          
          private int numboks;
          private String category;
        private String[] catarry;
        private String[] catarry2;

        

        public int getNumboks() {
            return numboks;
        }

        public void setNumboks(int numboks) {
            this.numboks = numboks;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
          
          
        @Override
        public void readValues(DataRead dr) throws BasicException {
            
            //catarry2=catarry.
            category=dr.getString(1);
            numboks=dr.getInt(2);
            
        }

        @Override
        public Object getKey() {
            return null;
        }
        
    }
    /*public List getmemtypename(){
        
            String catgr=new lib_catgry().getMemtype();
            catarry=catgr.split("#");
            for(int i=0;i<catarry.length;i++){
                try {
                    catarry2.add((List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM memtype where id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(catarry[i]));
                } catch (BasicException ex) {
                    Logger.getLogger(Lib_MemTypeTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        return catarry2;
    }*/
     public int getSize()
      {
        return size;
      }
     public List<Lib_FineTableModel.lib_catgry> getList(){
           if(alist!=null)
        {
            return alist;
        }
        else
            return new ArrayList<Lib_FineTableModel.lib_catgry>();
      }
    public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            private String[] memarry;
            

            @Override
            public String getColumnName(int column) {
                //if(flag==1){
                return AppLocal.getIntString(ISSUELIST[column]);
                
            }
            @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return lb1.size();
            }
            public int getColumnCount() {
                 //if(flag==1){
                return ISSUELIST.length;
                 
            }
           
            
            public Object getValueAt(int row, int column) {
                Lib_FineTableModel.lib_catgry l1 = lb1.get(row);
                
                switch (column) {
                   
                case 0: String mem=l1.getMemtype().toString();
                        String list = "";
                        if(mem.equals("All")){
                            list="All";
                        }else{
                    memarry=mem.split("#");
                        for(int i=0;i<memarry.length;i++){
                    try {   
                        
                        catarry2=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM memtype where id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memarry[i]);
                        list=list+catarry2.get(0)+",";
                    } catch (BasicException ex) {
                        Logger.getLogger(Lib_MemTypeTableModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                        }
                    
                        }
                    return list;
                case 1:return l1.getCreatedby();
                case 2:return l1.getCreatedhost();
                case 3:return l1.getId();
                case 4:return l1.isActive();    
                default: return null;
                
                }
                
            }
        };
    }
    
}