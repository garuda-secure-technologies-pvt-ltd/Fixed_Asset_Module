/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class CreateProductsForWarehouseModel extends BeanFactoryDataSingle {

     protected Session session;
     private List<LocationBean> lbList;
     private List<ProductInfoExt> pList = new ArrayList<ProductInfoExt>();
     private HashMap<String, List<ProductInfoExt>> hm = new HashMap<String, List<ProductInfoExt>>();
    
    @Override
    public void init(Session s) {
        this.session = s;
    }
    
    public List<ProductInfoExt> getProductByLocation(String id)
    {
         try {
             pList = new StaticSentence(session, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES , P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION,   P.TAXCAT2,  P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3), P.BASIC2,  P.BASIC3  FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID  where p.location = ? order by category, reference, code", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ProductInfoExt.class)).list(new Object[]{id});
             
             Collection<ProductInfoExt> pList2 = new ArrayList<ProductInfoExt>();
             pList2.addAll(pList);
             
             
             for (Iterator<ProductInfoExt> it = pList2.iterator(); it.hasNext();) {
                 ProductInfoExt locationBean = it.next();
                 Object obj1 = new StaticSentence(session, "SELECT product FROM products_cat WHERE product=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(locationBean.getID());
                 if(obj1==null)
                 {
                    pList.remove(locationBean);
                 }
                 
             }
             if(pList.size()>0)
             {
             ProductInfoExt p = pList.get(0);
             String cat = p.getCategoryID();
             List<ProductInfoExt> listOf = new ArrayList<ProductInfoExt>();
             hm = new HashMap<String, List<ProductInfoExt>>();
             hm.put(cat, listOf);
             
             for (Iterator<ProductInfoExt> it = pList.iterator(); it.hasNext();) {
                 ProductInfoExt ProductInfoExt = it.next();
                 
                 if(cat.equals(ProductInfoExt.getCategoryID()))
                 {
                     if(hm.get(cat)!=null)
                      {
                          hm.get(cat).add(ProductInfoExt);
                      }
                     else
                     {
                         hm.put(cat, listOf);
                     }
                 }
                 else
                 {
                     cat = ProductInfoExt.getCategoryID();
                     listOf = new ArrayList<ProductInfoExt>();
                     listOf.add(ProductInfoExt);
                     hm.put(cat, listOf);
                 }
                 
             }
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "There are no products under this warehouse", "No Products", JOptionPane.ERROR_MESSAGE);
                 return new ArrayList<ProductInfoExt>();
             }
             
             
         } catch (BasicException ex) {
             Logger.getLogger(CreateProductsForWarehouseModel.class.getName()).log(Level.SEVERE, null, ex);
         }
          return pList;
    }
    
    public static CreateProductsForWarehouseModel loadinstance(AppView app)
    {
         CreateProductsForWarehouseModel cp = new CreateProductsForWarehouseModel();
         List dlist  = new ArrayList();
         try {
            
             
             dlist = new StaticSentence(app.getSession()
                     ,"SELECT ID, NAME, RDISPLAYNAME, PARENT, SALESACCOUNT, CUSTOMERCURRENTACCOUNT, FACILITY, PREFIX FROM LOCATIONS WHERE PREFIX IS NOT NULL AND PREFIX != '' ORDER BY PREFIX"
                   ,SerializerWriteString.INSTANCE
                   ,new SerializerReadClass( CreateProductsForWarehouseModel.LocationBean.class )).list();
             
         } catch (BasicException ex) {
             Logger.getLogger(CreateProductsForWarehouseModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         cp.lbList = dlist;
         return cp;
    }
    
    
    public List<LocationBean> getLB()
    {
        if(this.lbList!=null)
        {
            return this.lbList;
        }
        else
        {
            return new ArrayList<LocationBean>();
        }
    }
    
    public HashMap<String, List<ProductInfoExt>> getProdByLocation()
    {
        if(this.hm!=null)
        {
            return this.hm;
        }
        else
        {
            return new HashMap<String, List<ProductInfoExt>>();
        }
    }
    
    public static class LocationBean implements SerializableRead, IKeyed {
           
        private String id;
        private String NAME; 
        private String RDISPLAYNAME;
        private String PARENT;
        private String SALESACCOUNT;
        private String CUSTOMERCURRENTACCOUNT;
        private String facility;
        private String Prefix;
        
        
        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            NAME = dr.getString(2);
            RDISPLAYNAME  = dr.getString(3);
            PARENT = dr.getString(4);
            SALESACCOUNT = dr.getString(5);
            CUSTOMERCURRENTACCOUNT = dr.getString(6);
            facility = dr.getString(7);
            Prefix = dr.getString(8);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + (this.NAME != null ? this.NAME.hashCode() : 0);
            hash = 79 * hash + (this.Prefix != null ? this.Prefix.hashCode() : 0);
            return hash;
        }

        @Override
        public String toString() {
            return NAME + " - " + Prefix ;
        }

      
        
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getRDISPLAYNAME() {
            return RDISPLAYNAME;
        }

        public void setRDISPLAYNAME(String RDISPLAYNAME) {
            this.RDISPLAYNAME = RDISPLAYNAME;
        }

        public String getPARENT() {
            return PARENT;
        }

        public void setPARENT(String PARENT) {
            this.PARENT = PARENT;
        }

        public String getSALESACCOUNT() {
            return SALESACCOUNT;
        }

        public void setSALESACCOUNT(String SALESACCOUNT) {
            this.SALESACCOUNT = SALESACCOUNT;
        }

        public String getCUSTOMERCURRENTACCOUNT() {
            return CUSTOMERCURRENTACCOUNT;
        }

        public void setCUSTOMERCURRENTACCOUNT(String CUSTOMERCURRENTACCOUNT) {
            this.CUSTOMERCURRENTACCOUNT = CUSTOMERCURRENTACCOUNT;
        }

        public String getFacility() {
            return facility;
        }

        public void setFacility(String facility) {
            this.facility = facility;
        }

        public String getPrefix() {
            return Prefix;
        }

        public void setPrefix(String Prefix) {
            this.Prefix = Prefix;
        }

        
        public Object getKey() {
            return this;
        }
 
 }
    
}


 