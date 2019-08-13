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
 * @author dev1
 */
public class Lib_CostTableModel {
    private List<lib_Costline> lb;
    private List<Lib_CostTableModel.lib_Costline> clist;
    private final static String[] COSTHEADERS1 = {"BookName","AllocatedNor","Cost","ReplcmntCost","Vendor","Createdby","Createdhost"};
    private final static String[] COSTHEADERS = {"BookName","AllocatedNor","Cost","ReplcmntCost","Vendor","Createdby","Createdhost","Deactivated by","Deacthost"};
    private int size;
    private int flag;
    
    private Lib_CostTableModel()
  {
  }
    
    public static Lib_CostTableModel emptyinstance()
  {
      Lib_CostTableModel lib_b=new Lib_CostTableModel();
      lib_b.lb=new ArrayList<lib_Costline>();
      return lib_b;
  } 
    
    public static Lib_CostTableModel loadInstance(AppView app,int flag) throws BasicException{
         Lib_CostTableModel la = new Lib_CostTableModel();
         la.flag = flag;
         try
      {
          if(flag==1){
              la.clist = new StaticSentence(app.getSession()
                , "SELECT b.NAME,c.allocatednor,c.cost,c.replmtcost,v.Name,c.CREATEDBY,c.CREATEDHOST,c.DEACBY,c.DEACHOST,c.DEACTREFERENCE,c.ID,c.active FROM lib_bookmaster b,lib_cost c,lib_vendor v WHERE c.ACTIVE =TRUE and b.id=c.book_id and v.id=c.vendor_id ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_CostTableModel.lib_Costline.class)).list();
          }else{
              la.clist = new StaticSentence(app.getSession()
                , "SELECT b.NAME,c.allocatednor,c.cost,c.replmtcost,v.Name,c.CREATEDBY,c.CREATEDHOST,c.DEACBY,c.DEACHOST,c.DEACTREFERENCE,c.ID,c.active FROM lib_bookmaster b,lib_cost c,lib_vendor v WHERE b.id=c.book_id and v.id=c.vendor_id ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_CostTableModel.lib_Costline.class)).list();
              
          }
              
                la.size=la.clist.size();
         if(la.clist==null)
         {
             la.lb=new ArrayList<Lib_CostTableModel.lib_Costline>();
         }
         else
        {
            la.lb=la.clist;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_CostTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return la;
     }
    
    public List<lib_Costline> getfacilityline()
     {
         return lb;
     }
    
    public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                if (flag == 1) {
                    
                return AppLocal.getIntString(COSTHEADERS1[column]);
                }else{
                    return AppLocal.getIntString(COSTHEADERS[column]);
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
                return COSTHEADERS1.length;
            }else{
                return COSTHEADERS.length;
            }
            }
            public Object getValueAt(int row, int column) {
                Lib_CostTableModel.lib_Costline l = lb.get(row);

                switch (column) {
                   
                case 0: return l.getBook();
                case 1: return l.getAllocnr();
                case 2: return l.getCost();
                case 3: return l.getRepmtcost();
                case 4: return l.getVendor();    
                case 5: return l.getCreatedby();
                case 6: return l.getCreatedhost();
                case 7: return l.getDeactivatedby();
                case 8: return l.getDeactivatedhost();
                case 9:return l.getDeactreference();
                case 10: return l.getId();    
                case 11:return l.isActive();
                   
                default: return null;
                }
            }
        };
    }
    
    public static class lib_Costline implements SerializableRead,IKeyed{
    
    private String id;
    private String book; 
    private int allocnr;
    private double cost;
    private double repmtcost;
    private String vendor;
    private String createdby;
    private String deactivatedby;
    private String createdhost;
    private String deactivatedhost;
    private String deactreference;
    private boolean active;

        @Override
        public void readValues(DataRead dr) throws BasicException {
            book=dr.getString(1);
            allocnr=dr.getInt(2);
            cost=dr.getDouble(3);
            repmtcost=dr.getDouble(4);
            vendor=dr.getString(5);
            createdby=dr.getString(6);
            createdhost=dr.getString(7);
            deactivatedby=dr.getString(8);
            deactivatedhost=dr.getString(9);
            deactreference=dr.getString(10);
            id=dr.getString(11);
            active=dr.getBoolean(12);
        }
    
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }

        public int getAllocnr() {
            return allocnr;
        }

        public void setAllocnr(int allocnr) {
            this.allocnr = allocnr;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public double getRepmtcost() {
            return repmtcost;
        }

        public void setRepmtcost(double repmtcost) {
            this.repmtcost = repmtcost;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
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
     public List< Lib_CostTableModel.lib_Costline> getList(){
           if(clist!=null)
        {
            return clist;
        }
        else
            return new ArrayList< Lib_CostTableModel.lib_Costline>();
      }
}
