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
public class Lib_CategoryTableModel {
     private List<lib_Categoryline> lbc;
    private List<Lib_CategoryTableModel.lib_Categoryline> clist;
    private final static String[] CATEGORYHEADERS = {"Name","Address","DisplayName","RevenueHead","Facility","DisposalHead","CustCurrAcc","Createdby","CREATEDHOST",};
     private final static String[] CATEGORYHEADERS1 = {"Name","Address","DisplayName","RevenueHead","Facility","DisposalHead","CustCurrAcc","Createdby","CREATEDHOST","Deactivated by","DEACTHOST"};
    private int size;
    private int flag;
    private Lib_CategoryTableModel()
  {
  }
    
    public static Lib_CategoryTableModel emptyinstance()
  {
      Lib_CategoryTableModel lib_c=new Lib_CategoryTableModel();
      lib_c.lbc=new ArrayList<lib_Categoryline>();
      return lib_c;
  } 
    
    public static Lib_CategoryTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_CategoryTableModel lp = new Lib_CategoryTableModel();
          lp.flag = flag;
         try
      {
           if(flag==1){
       lp.clist = new StaticSentence(app.getSession()
                , "SELECT P.NAME,P.ADDRESS,P.DispName,P.PARENTID,a.name,f.name,P.CREATEDBY,P.CREATEDHOST,P.DEACTBY,P.DEACTHOST,P.ID,P.Primaryparent,P.DEACTREFERENCE,a1.name,a2.name,P.active,P.memtypes FROM LIB_CATEGORIES P,accountmaster a,accountmaster a1,accountmaster a2,facility f WHERE P.ACTIVE =TRUE and a.id=P.RevenueHead  and f.id=P.Facility and a1.id=P.disposalhead and a2.id=P.CustCurrAcct ORDER BY P.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_CategoryTableModel.lib_Categoryline.class)).list();
           }else{
               lp.clist = new StaticSentence(app.getSession()
                , "SELECT P.NAME,P.ADDRESS,P.DispName,P.PARENTID,a.name,f.name,P.CREATEDBY,P.CREATEDHOST,P.DEACTBY,P.DEACTHOST,P.ID,P.Primaryparent,P.DEACTREFERENCE,a1.name,a2.name,P.active,P.memtypes FROM LIB_CATEGORIES P,accountmaster a,accountmaster a1,accountmaster a2,facility f WHERE a.id=P.RevenueHead  and f.id=P.Facility and a1.id=P.disposalhead and a2.id=P.CustCurrAcct ORDER BY P.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_CategoryTableModel.lib_Categoryline.class)).list();
           }
                lp.size=lp.clist.size();
         if(lp.clist==null)
         {
             lp.lbc=new ArrayList<Lib_CategoryTableModel.lib_Categoryline>();
         }
         else
        {
            lp.lbc=lp.clist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_CategoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return lp;
     }
     public List<lib_Categoryline> getfacilityline()
     {
         return lbc;
     }
     
      public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if(flag==1){
                return AppLocal.getIntString(CATEGORYHEADERS[column]);
                }else{
                    return AppLocal.getIntString(CATEGORYHEADERS1[column]);
                }
            }
             @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return lbc.size();
            }
            public int getColumnCount() {
                    if(flag==1){
                return CATEGORYHEADERS.length;
                    }else{
                        return CATEGORYHEADERS1.length;
                    }
            }
            public Object getValueAt(int row, int column) {
                Lib_CategoryTableModel.lib_Categoryline l = lbc.get(row);

                switch (column) {
                case 0: return l.getName();
                case 1: return l.getAddress();
                case 2: return l.getDispname();
                case 3: return l.getRevenue();
                case 4: return l.getFacility(); 
                case 5: return l.getDisposal();
                case 6: return l.getCurcusacc();     
                case 7: return l.getCreatedby();
                case 8:return l.getCreatedhost();
                case 9:return l.getDeactivatedby();
                case 10:return l.getDeactivatedhost();
                case 11:return l.getDeactref();
                case 12:return l.getId();
                case 13:return l.isActive();
                case 14:return l.getMemtypes();    
                default: return null;
                }
            }
        };
    }
     
     
     public static class lib_Categoryline implements SerializableRead,IKeyed{
    private String id;
    private String name;
    private String address;
    private String dispname;
    private String parent;
    private String primaryparent;
    private String revenue;
    private String disposal;
    private String createdby;
    private String deactivatedby;
    private String curcusacc;
    private String facility;
    private String stock;
    private String createdhost;
    private String deactivatedhost;
    private String deactref;
    private boolean active;
    private String memtypes;
    
      @Override
        public void readValues(DataRead dr) throws BasicException {
            
            id=dr.getString(11);
        name=dr.getString(1);
        address=dr.getString(2);
        dispname=dr.getString(3);
        parent=dr.getString(4);
        revenue=dr.getString(5);
        facility=dr.getString(6);
        createdby=dr.getString(7);
        createdhost=dr.getString(8);
        deactivatedby=dr.getString(9);
        deactivatedhost=dr.getString(10);
        primaryparent= dr.getString(12);
        deactref= dr.getString(13);
        disposal=dr.getString(14);
        curcusacc=dr.getString(15);
        active=dr.getBoolean(16);
        memtypes=dr.getString(17);
        }

        public String getMemtypes() {
            return memtypes;
        }

        public void setMemtypes(String memtypes) {
            this.memtypes = memtypes;
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

        public String getDispname() {
            return dispname;
        }

        public void setDispname(String dispname) {
            this.dispname = dispname;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getPrimaryparent() {
            return primaryparent;
        }

        public void setPrimaryparent(String primaryparent) {
            this.primaryparent = primaryparent;
        }

        public String getRevenue() {
            return revenue;
        }

        public void setRevenue(String revenue) {
            this.revenue = revenue;
        }

        public String getDisposal() {
            return disposal;
        }

        public void setDisposal(String disposal) {
            this.disposal = disposal;
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

        public String getCurcusacc() {
            return curcusacc;
        }

        public void setCurcusacc(String curcusacc) {
            this.curcusacc = curcusacc;
        }

        public String getFacility() {
            return facility;
        }

        public void setFacility(String facility) {
            this.facility = facility;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
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

        public String getDeactref() {
            return deactref;
        }

        public void setDeactref(String deactref) {
            this.deactref = deactref;
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
     public List<Lib_CategoryTableModel.lib_Categoryline> getList(){
           if(clist!=null)
        {
            return clist;
        }
        else
            return new ArrayList<Lib_CategoryTableModel.lib_Categoryline>();
      }
}
