/*
 * To change this template, choose Tools | Templates
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
import com.openbravo.pos.clubmang.VendorDetailTableModel;
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
public class Lib_vendorTableModel {
    private List<lib_Vendorline> fac;
     protected List<Lib_vendorTableModel.lib_Vendorline> dlist;
    private final static String[] VENDORHEADERS = {"Name","Address","Cnt Person","Cnt No","Account","Type of person","Createdby","CREATEDHOST"};
    private final static String[] VENDORHEADERS1 = {"Name","Address","Cnt Person","Cnt No","Account","Type of person","Createdby","CREATEDHOST","Deactivated by","DEACHOST"};
    private int size;
    private int flag;
    
    private Lib_vendorTableModel()
  {
  }
    
     public static Lib_vendorTableModel emptyinstance()
  {
      Lib_vendorTableModel d=new Lib_vendorTableModel();
      d.fac=new ArrayList<lib_Vendorline>();
      return d;
  }
     
      public static Lib_vendorTableModel loadInstance(AppView app,int flag) throws BasicException{
      Lib_vendorTableModel d = new Lib_vendorTableModel();
      d.flag=flag;
      try
      {
          if(flag==1){
       d.dlist = new StaticSentence(app.getSession()
                , "SELECT V.NAME,V.ADDRESS,V.CONTACTPERSON,V.CONTACTNUM,V.PANNO,V.TINNO,V.CST,A.NAME,V.INSTRUCTION,V.CREATEDBY,V.DEACTBY,V.CREATEDHOST,V.DEACHOST,V.ID,V.DEACTREFERENCE,V.active,V.Vendflag FROM LIB_VENDOR V,ACCOUNTMASTER A WHERE V.ACTIVE =TRUE AND A.ID=V.ACCOUNT ORDER BY V.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_vendorTableModel.lib_Vendorline.class)).list();
          }else{
               d.dlist = new StaticSentence(app.getSession()
                , "SELECT V.NAME,V.ADDRESS,V.CONTACTPERSON,V.CONTACTNUM,V.PANNO,V.TINNO,V.CST,A.NAME,V.INSTRUCTION,V.CREATEDBY,V.DEACTBY,V.CREATEDHOST,V.DEACHOST,V.ID,V.DEACTREFERENCE,V.active,V.Vendflag FROM LIB_VENDOR V,ACCOUNTMASTER A WHERE A.ID=V.ACCOUNT ORDER BY V.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_vendorTableModel.lib_Vendorline.class)).list();
          }
          d.size=d.dlist.size();
         if(d.dlist==null)
         {
             d.fac=new ArrayList<Lib_vendorTableModel.lib_Vendorline>();
         }
         else
        {
            d.fac=d.dlist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_vendorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     return d;
  }
      public List<lib_Vendorline> getfacilityline()
     {
         return fac;
     }
       
      
      public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if(flag==1){
                return AppLocal.getIntString(VENDORHEADERS[column]);
                }else{
                    return AppLocal.getIntString(VENDORHEADERS1[column]);
                }
            }
            @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {
                if(flag==1){
                return VENDORHEADERS.length;
                }else{
                     return VENDORHEADERS1.length;
                }
            }
            public Object getValueAt(int row, int column) {
                Lib_vendorTableModel.lib_Vendorline l = fac.get(row);

                switch (column) {
                case 0: return l.getName();
                case 1: return l.getAddress();
                case 2: return l.getCperson();
                case 3: return l.getCnumber(); 
                case 4: return l.getAccount();
                case 5: return getperson(l.getDonor1());
                case 6: return l.getCreatedby();
                case 7: return l.getCreatedhost();
                case 8: return l.getDeactivatedby();
                case 9: return l.getDeactivatedhost();
                case 10: return l.getDeactrefer();
                case 11: return l.getId();
                case 12: return l.isActive();   
                default: return null;
                }
            }
        };
    }
    
    public static class lib_Vendorline implements SerializableRead,IKeyed{
    private String id;
    private String name;
    private String address;
    private String cperson;
    private String cnumber;
    private String panno;
    private String tinno;
    private String cstno;
    private String createdby;
    private String deactivatedby;
    private String account;
    private String instruction;
    private String createdhost;
    private String deactivatedhost;
    private String deactrefer;
    private boolean active;
    private int donor1;
    
     public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(14);
        name=dr.getString(1);
        address=dr.getString(2);
        cperson=dr.getString(3);
        cnumber=dr.getString(4);
        panno=dr.getString(5);
        tinno=dr.getString(6);
        cstno=dr.getString(7);
        account=dr.getString(8);
        instruction=dr.getString(9);
        createdby=dr.getString(10);
        deactivatedby=dr.getString(11);
        createdhost=dr.getString(12);
        deactivatedhost=dr.getString(13);
        deactrefer=dr.getString(15);
        active=dr.getBoolean(16);
        donor1=dr.getInt(17);
   }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
        
        
        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCperson(String cperson) {
            this.cperson = cperson;
        }

        public void setCnumber(String cnumber) {
            this.cnumber = cnumber;
        }

        public void setPanno(String panno) {
            this.panno = panno;
        }

        public void setTinno(String tinno) {
            this.tinno = tinno;
        }

        public void setCstno(String cstno) {
            this.cstno = cstno;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public void setDeactivatedby(String deactivatedby) {
            this.deactivatedby = deactivatedby;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        public void setCreatedhost(String createdhost) {
            this.createdhost = createdhost;
        }

        public void setDeactivatedhost(String deactivatedhost) {
            this.deactivatedhost = deactivatedhost;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getCperson() {
            return cperson;
        }

        public String getCnumber() {
            return cnumber;
        }

        public String getPanno() {
            return panno;
        }

        public String getTinno() {
            return tinno;
        }

        public String getCstno() {
            return cstno;
        }

        public String getCreatedby() {
            return createdby;
        }

        public String getDeactivatedby() {
            return deactivatedby;
        }

        public String getAccount() {
            return account;
        }

        public String getInstruction() {
            return instruction;
        }

        public String getCreatedhost() {
            return createdhost;
        }

        public String getDeactivatedhost() {
            return deactivatedhost;
        }

        public String getDeactrefer() {
            return deactrefer;
        }

        public void setDeactrefer(String deactrefer) {
            this.deactrefer = deactrefer;
        }

        public int getDonor1() {
            return donor1;
        }

        public void setDonor1(int donor1) {
            this.donor1 = donor1;
        }
        
      
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
     public List<Lib_vendorTableModel.lib_Vendorline> getList(){
           if(dlist!=null)
        {
            return dlist;
        }
        else
            return new ArrayList<Lib_vendorTableModel.lib_Vendorline>();
      }
     public String getperson(int flag){
         if(flag==1){
             return "vendor";
         }
         else
             return "donor";
             
     }
      
}
