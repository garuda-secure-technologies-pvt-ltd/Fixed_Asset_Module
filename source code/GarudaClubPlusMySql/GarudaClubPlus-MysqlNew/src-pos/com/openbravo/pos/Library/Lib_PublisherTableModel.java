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
 * @author Santhosh
 */
public class Lib_PublisherTableModel {
    private List<lib_Publisherline> lbp;
    private List<Lib_PublisherTableModel.lib_Publisherline> plist;
    private final static String[] PUBLISHERHEADERS = {"Name","Address","Address2","POSTAL","CITY","STATE","COUNTRY","PHONE","Language","vendor","Createdby","CREATEDHOST"};
    private final static String[] PUBLISHERHEADERS1 = {"Name","Address","Address2","POSTAL","CITY","STATE","COUNTRY","PHONE","Language","vendor","Createdby","CREATEDHOST","Deactivated by","DEACTHOST"};
    private int size;
    private int flag;
    private Lib_PublisherTableModel()
  {
  }
    public static Lib_PublisherTableModel emptyinstance()
  {
      Lib_PublisherTableModel lib_p=new Lib_PublisherTableModel();
      lib_p.lbp=new ArrayList<lib_Publisherline>();
      return lib_p;
  } 
     public static Lib_PublisherTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_PublisherTableModel lp = new Lib_PublisherTableModel();
         lp.flag=flag;
         try
      {
          if(flag==1){
       lp.plist = new StaticSentence(app.getSession()
                , "SELECT P.NAME,P.ADDRESS,P.ADDRESS2,P.POSTAL,P.CITY,P.STATE,P.COUNTRY,P.PHONE,l.Name,V.NAME,P.CREATEDBY,P.CREATEDHOST,P.DEACTBY,P.DEACTHOST,P.ID,P.DEACTREFERENCE,P.active FROM LIB_PUBLISHER P,LIB_VENDOR V,lib_language l WHERE P.ACTIVE =TRUE AND V.ID=P.VENDOR_ID and l.ID=P.language ORDER BY P.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_PublisherTableModel.lib_Publisherline.class)).list();
          }else{
             lp.plist = new StaticSentence(app.getSession()
                , "SELECT P.NAME,P.ADDRESS,P.ADDRESS2,P.POSTAL,P.CITY,P.STATE,P.COUNTRY,P.PHONE,l.Name,V.NAME,P.CREATEDBY,P.CREATEDHOST,P.DEACTBY,P.DEACTHOST,P.ID,P.DEACTREFERENCE,P.active FROM LIB_PUBLISHER P,LIB_VENDOR V,lib_language l WHERE V.ID=P.VENDOR_ID and l.ID=P.language ORDER BY P.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_PublisherTableModel.lib_Publisherline.class)).list(); 
              
          }
                lp.size=lp.plist.size();
         if(lp.plist==null)
         {
             lp.lbp=new ArrayList<Lib_PublisherTableModel.lib_Publisherline>();
         }
         else
        {
            lp.lbp=lp.plist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_PublisherTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return lp;
     }
     public List<lib_Publisherline> getfacilityline()
     {
         return lbp;
     }
     
      public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if(flag==1){
                return AppLocal.getIntString(PUBLISHERHEADERS[column]);
                }else{
                     return AppLocal.getIntString(PUBLISHERHEADERS1[column]);
                }
            }
            @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                
                return lbp.size();
            }
            public int getColumnCount() {
                if(flag==1){

                return PUBLISHERHEADERS.length;
                }else{
                    return PUBLISHERHEADERS1.length;
                }
            }
            public Object getValueAt(int row, int column) {
                Lib_PublisherTableModel.lib_Publisherline l = lbp.get(row);

                switch (column) {
                case 0: return l.getName();
                case 1: return l.getAddress();
                case 2: return l.getAddress2();
                case 3: return l.getPostal();
                case 4: return l.getCity();
                case 5: return l.getState();
                case 6: return l.getCountry();
                case 7: return l.getPhone();
                case 8: return l.getLanguage();    
                case 9: return l.getVendor();
                case 10: return l.getCreatedby();
                case 11:return l.getCreatedhost();
                case 12:return l.getDeactivatedby();
                case 13:return l.getDeactivatedhost();
                case 14:return l.getDeactrefer();
                case 15:return l.getId();
                case 16:return l.isActive();    
                default: return null;
                }
            }
        };
    }
     
     
      public static class lib_Publisherline implements SerializableRead,IKeyed{
    private String id;
    private String name;
    private String address;
    private String address2;
    private String postal;
    private String city;
    private String state;
    private String country;
    private String createdby;
    private String deactivatedby;
    private String phone;
    private String language;
    private String vendor;
    private String createdhost;
    private String deactivatedhost;
    private String deactrefer;
    private boolean active;

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

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getPostal() {
            return postal;
        }

        public void setPostal(String postal) {
            this.postal = postal;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
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
        

        @Override
        public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(15);
        name=dr.getString(1);
        address=dr.getString(2);
        address2=dr.getString(3);
        postal=dr.getString(4);
        city=dr.getString(5);
        state=dr.getString(6);
        country=dr.getString(7);
        phone=dr.getString(8);
        language=dr.getString(9);
        vendor=dr.getString(10);
        createdby=dr.getString(11);
        createdhost=dr.getString(12);
        deactivatedby=dr.getString(13);
        deactivatedhost=dr.getString(14);
        deactrefer=dr.getString(16);
        active=dr.getBoolean(17);
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
     public List<Lib_PublisherTableModel.lib_Publisherline> getList(){
           if(plist!=null)
        {
            return plist;
        }
        else
            return new ArrayList<Lib_PublisherTableModel.lib_Publisherline>();
      }
     
     
     
}
