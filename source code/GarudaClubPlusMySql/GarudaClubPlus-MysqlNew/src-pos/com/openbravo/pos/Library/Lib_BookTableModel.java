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
public class Lib_BookTableModel {
    
    private List<lib_Bookline> lb;
    private List<Lib_BookTableModel.lib_Bookline> clist;
    private final static String[] BOOKHEADERS1 = {"Name","RefNo","Author","Publisher","Language","Category","IssueRules","Createdby","CREATEDHOST"};
    private final static String[] BOOKHEADERS = {"Name","RefNo","Author","Publisher","Language","Category","IssueRules","Createdby","CREATEDHOST","Deactivated by","DEACTHOST"};
    private int size;
    private int flag;
    private Lib_BookTableModel()
  {
  }
    
    public static Lib_BookTableModel emptyinstance()
  {
      Lib_BookTableModel lib_b=new Lib_BookTableModel();
      lib_b.lb=new ArrayList<lib_Bookline>();
      return lib_b;
  } 
    
     public static Lib_BookTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_BookTableModel la = new Lib_BookTableModel();
         la.flag = flag;
         try
      {
          if(flag==1){
              la.clist = new StaticSentence(app.getSession()
                , "SELECT b.NAME,b.RefNo,a.name,p.name,l.Name,b.sms,c.name,b.Edition,b.Keywords,b.media,i.Name,b.AllocatedNor,b.copies,b.CREATEDBY,b.CREATEDHOST,b.DEACBY,b.DEACHOST,b.DEACTREFERENCE,b.ID,b.active,b.vend_doid,available_flag FROM lib_bookmaster b,lib_author a,lib_categories c,lib_publisher p,lib_language l,lib_issuerules i WHERE b.ACTIVE =TRUE and a.id=b.Author and p.id=b.publisher and c.id=b.category and l.id=b.language and i.id=b.IssueRules and b.available_flag=true ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_BookTableModel.lib_Bookline.class)).list();
          }else{
              la.clist = new StaticSentence(app.getSession()
                , "SELECT b.NAME,b.RefNo,a.name,p.name,l.Name,b.sms,c.name,b.Edition,b.Keywords,b.Media,i.Name,b.AllocatedNor,b.copies,b.CREATEDBY,b.CREATEDHOST,b.DEACBY,b.DEACHOST,b.DEACTREFERENCE,b.ID,b.active,b.vend_doid,available_flag FROM lib_bookmaster b,lib_author a,lib_categories c,lib_publisher p,lib_language l,lib_issuerules i WHERE a.id=b.Author and p.id=b.publisher and c.id=b.category and l.id=b.language and i.id=b.IssueRules and b.available_flag=true ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_BookTableModel.lib_Bookline.class)).list();
              
          }
              
                la.size=la.clist.size();
         if(la.clist==null)
         {
             la.lb=new ArrayList<Lib_BookTableModel.lib_Bookline>();
         }
         else
        {
            la.lb=la.clist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_BookTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return la;
     }
      public List<lib_Bookline> getfacilityline()
     {
         return lb;
     }
      
      public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if (flag == 1) {
                    
                return AppLocal.getIntString(BOOKHEADERS1[column]);
                }else{
                    return AppLocal.getIntString(BOOKHEADERS[column]);
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
                return BOOKHEADERS1.length;
            }else{
                return BOOKHEADERS.length;
            }
            }
            public Object getValueAt(int row, int column) {
                Lib_BookTableModel.lib_Bookline l = lb.get(row);

                switch (column) {
                   
                case 0: return l.getName();
                case 1: return l.getRefno();
                case 2: return l.getAuthor();
                case 3: return l.getPublisher();
                case 4: return l.getLanguage();    
                case 5: return l.getCategory();
                case 6: return l.getIssuerules();
                case 7: return l.getCreatedby();
                case 8: return l.getCreatedhost();
                case 9: return l.getDeactivatedby();
                case 10: return l.getDeactivatedhost();
                case 11:return l.getDeactreference();
                case 12: return l.getId();
                case 13:return l.isActive();
                case 14:return l.getVendfg(); 
                case 15:return l.getMedia();
                case 16:return l.getAvlfg();    
                default: return null;
                }
            }
        };
    }
      
      public static class lib_Bookline implements SerializableRead,IKeyed{
          
          
    private String id;
    private String name;
    private String refno;
    private String author;
    private String publisher;
    private String category;
    private String language;
    private String allocatedno;
    private String nocopies;
    private String edition;
    private String sms;
    private String keywords;
    private String issuerules;
    private String media;
    private String createdby;
    private String deactivatedby;
    private String createdhost;
    private String deactivatedhost;
    private String deactreference;
    private boolean active;
    private String vendfg;
    private int avlfg;
       

        @Override
        public void readValues(DataRead dr) throws BasicException {
        name=dr.getString(1);
        refno=dr.getString(2);
        author=dr.getString(3);
        publisher=dr.getString(4);
        language=dr.getString(5);
        sms=dr.getString(6);
        category=dr.getString(7);
        edition=dr.getString(8);
        keywords=dr.getString(9);
        media=dr.getString(10);
        issuerules=dr.getString(11);
        allocatedno=dr.getString(12);
        nocopies=dr.getString(13);
        createdby=dr.getString(14);
        createdhost=dr.getString(15);
        deactivatedby=dr.getString(16);
        deactivatedhost=dr.getString(17);
        deactreference=dr.getString(18);
        id= dr.getString(19); 
        active= dr.getBoolean(20);
        vendfg=dr.getString(21);
        avlfg=dr.getInt(22);
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

        public String getRefno() {
            return refno;
        }

        public void setRefno(String refno) {
            this.refno = refno;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getAllocatedno() {
            return allocatedno;
        }

        public void setAllocatedno(String allocatedno) {
            this.allocatedno = allocatedno;
        }

        public String getNocopies() {
            return nocopies;
        }

        public void setNocopies(String nocopies) {
            this.nocopies = nocopies;
        }

        public String getEdition() {
            return edition;
        }

        public void setEdition(String edition) {
            this.edition = edition;
        }

        public String getSms() {
            return sms;
        }

        public void setSms(String sms) {
            this.sms = sms;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
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

        public String getIssuerules() {
            return issuerules;
        }

        public void setIssuerules(String issuerules) {
            this.issuerules = issuerules;
        }
        
         public String getDeactreference() {
            return deactreference;
        }

        public void setDeactreference(String deactreference) {
            this.deactreference = deactreference;
        }

        public String getVendfg() {
            return vendfg;
        }

        public void setVendfg(String vendfg) {
            this.vendfg = vendfg;
        }

        public int getAvlfg() {
            return avlfg;
        }

        public void setAvlfg(int avlfg) {
            this.avlfg = avlfg;
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
     public List< Lib_BookTableModel.lib_Bookline> getList(){
           if(clist!=null)
        {
            return clist;
        }
        else
            return new ArrayList< Lib_BookTableModel.lib_Bookline>();
      }
    
    
}
