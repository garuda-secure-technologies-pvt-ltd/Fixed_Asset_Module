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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santhosh
 */
public class Lib_AuthorTableModel {
    private List<lib_Authorline> lba;
    private List<Lib_AuthorTableModel.lib_Authorline> alist;
    private int size;
    private final static String[] AUTHORHEADERS = {"Name","Address","CATEGORIES","WRITEUP","language","CITY","COUNTRY","DOB","PHONE","EMAIL","Createdby","CREATEDHOST"}; 
    private final static String[] AUTHORHEADERS1 = {"Name","Address","CATEGORIES","WRITEUP","language","CITY","COUNTRY","DOB","PHONE","EMAIL","Createdby","CREATEDHOST","Deactivated by","DEACTHOST"};
    private int flag;
    
    
    
    private Lib_AuthorTableModel()
  {
      
  }
    public static Lib_AuthorTableModel emptyinstance()
  {
      Lib_AuthorTableModel lib_a=new Lib_AuthorTableModel();
      lib_a.lba=new ArrayList<lib_Authorline>();
      return lib_a;
  } 
    
    
    public static Lib_AuthorTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_AuthorTableModel la = new Lib_AuthorTableModel();
         la.flag=flag;
         try
      {
          if(flag==1){
       la.alist = new StaticSentence(app.getSession()
                , "SELECT A.NAME,A.ADDRESS,C.Name,A.WRITEUP,l.name,A.CITY,A.COUNTRY,A.DOB,A.PHONE,A.EMAIL,A.CREATEDBY,A.CREATEDHOST,A.DEACTBY,A.DEACTHOST,A.ID,A.PHOTO,A.REGION,A.DEACTREFERENCE,A.ACTIVE FROM LIB_AUTHOR A,LIB_CATEGORIES C,lib_language l WHERE A.ACTIVE =TRUE AND C.ID=A.categories and l.ID=A.language ORDER BY A.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_AuthorTableModel.lib_Authorline.class)).list();
          }else{
              la.alist = new StaticSentence(app.getSession()
                , "SELECT A.NAME,A.ADDRESS,C.Name,A.WRITEUP,l.name,A.CITY,A.COUNTRY,A.DOB,A.PHONE,A.EMAIL,A.CREATEDBY,A.CREATEDHOST,A.DEACTBY,A.DEACTHOST,A.ID,A.PHOTO,A.REGION,A.DEACTREFERENCE,A.ACTIVE FROM LIB_AUTHOR A,LIB_CATEGORIES C,lib_language l WHERE C.ID=A.categories and l.ID=A.language ORDER BY A.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_AuthorTableModel.lib_Authorline.class)).list();
          }
                la.size=la.alist.size();
         if(la.alist==null)
         {
             la.lba=new ArrayList<Lib_AuthorTableModel.lib_Authorline>();
         }
         else
        {
            la.lba=la.alist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_AuthorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return la;
     }
     public List<lib_Authorline> getfacilityline()
     {
         return lba;
     }
     
     public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if(flag==1){
                return AppLocal.getIntString(AUTHORHEADERS[column]);
                }else{
                    return AppLocal.getIntString(AUTHORHEADERS1[column]);
                }
            }
            @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return lba.size();
            }
            public int getColumnCount() {
                 if(flag==1){
                return AUTHORHEADERS.length;
                 }else{
                     return AUTHORHEADERS1.length;
                 }
            }
            public Object getValueAt(int row, int column) {
                Lib_AuthorTableModel.lib_Authorline l = lba.get(row);

                switch (column) {
                case 0: return l.getName();
                case 1: return l.getAddress();
                case 2: return l.getCategories();
                case 3: return l.getWriteup();
                case 4: return l.getLanguage();
                case 5: return l.getCity();
                case 6: return l.getCountry();
                case 7: return l.getDob();
                case 8: return l.getPhone();
                case 9: return l.getEmail();
                case 10: return l.getCreatedby();
                case 11:return l.getCreatedhost();
                case 12:return l.getDeactivatedby();
                case 13:return l.getDeactivatedhost();
                case 14:return l.getDeactrefe();
                case 15:return l.getId();
                case 16:return l.isActive();    
                default: return null;
                }
            }
        };
    }
    
    
   public static class lib_Authorline implements SerializableRead,IKeyed{ 
       
       
    private String id;
    private String name;
    private String address;
    private String categories;
    private String writeup;
    private String city;
    private String region;
    private Date dob;
    private String email;
    private String country;
    private String createdby;
    private String deactivatedby;
    private String phone;
    private String language;
    private String notes;
    private String photo;
    private String createdhost;
    private String deactivatedhost;
    private String deactrefe;
    private boolean active;
    
     @Override
        public void readValues(DataRead dr) throws BasicException {
            
        id=dr.getString(15);
        name=dr.getString(1);
        address=dr.getString(2);
        categories=dr.getString(3);
        writeup=dr.getString(4);
        language=dr.getString(5);
        city=dr.getString(6);
        country=dr.getString(7);
        dob=(Date)dr.getObject(8);
        phone=dr.getString(9);
        email=dr.getString(10);
        createdby=dr.getString(11);
        createdhost=dr.getString(12);
        deactivatedby=dr.getString(13);
        deactivatedhost=dr.getString(14);
        photo=dr.getString(16);
        region=dr.getString(17);
        deactrefe=dr.getString(18);
        active=dr.getBoolean(19);
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getWriteup() {
            return writeup;
        }

        public void setWriteup(String writeup) {
            this.writeup = writeup;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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
       @Override
         public String toString(){
            return name;
        }
       
       
   } 
    public int getSize()
      {
        return size;
      }
     
    public List<Lib_AuthorTableModel.lib_Authorline> getList(){
           if(alist!=null)
        {
            return alist;
        }
        else
            return new ArrayList<Lib_AuthorTableModel.lib_Authorline>();
      }
    
}
