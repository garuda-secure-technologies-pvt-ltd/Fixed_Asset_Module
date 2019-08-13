/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author swathi
 */
public class MultipleWarehouseModel {

    private List<ConsolidateReport> cr;
    private Map<String, List<ConsolidateReport>> warehousereport = new HashMap<String, List<ConsolidateReport>>();
    ;
    private List<consolidate> cons;

    public MultipleWarehouseModel() {
    }

    public List<ConsolidateReport> getCr() {
        return cr;
    }

    public void setCr(List<ConsolidateReport> cr) {
        this.cr = cr;
    }

    public Map<String, List<ConsolidateReport>> getWarehousereport() {
        return warehousereport;
    }

    public void setWarehousereport(Map<String, List<ConsolidateReport>> warehousereport) {
        this.warehousereport = warehousereport;
    }

    public List<consolidate> getCons() {
        return cons;
    }

    public void setCons(List<consolidate> cons) {
        this.cons = cons;
    }

    public MultipleWarehouseModel loadInstance(AppView app, String warehouse, Date from, Date to, String parentloc) throws BasicException {

        MultipleWarehouseModel model = new MultipleWarehouseModel();
        //warehousereport = new HashMap<String, List<ConsolidateReport>>();
        String[] loc = null;
        Object[] obj = null;
        if (warehouse != null) {
            loc = warehouse.split("#");
        }
        for (int i = 0; i < loc.length; i++) {
            obj = new Object[]{parentloc, loc[i].toString(), from, to};
            List li = new StaticSentence(app.getSession(),
                " SELECT S.ID,S.NAME,SUM(SL.QTY),ca.name FROM CATEGORIES CA,PRODUCTS S JOIN CONVERTER C  ON S.ID=C.PRODUCTFST LEFT OUTER JOIN PRODUCTWISECLOSESALE SL ON C.PRODUCTSCN=SL.PRODUCT  AND  C.LOCATIONFST=? AND C.LOCATIONSCN=?  AND SL.CSDATE>=? AND SL.CSDATE<=? AND  C.ACTIVE=TRUE WHERE CA.ID=S.CATEGORY GROUP BY S.ID,S.NAME ORDER BY CA.NAME , S.NAME" , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}),
                    new SerializerReadClass(ConsolidateReport.class)).list(obj);
            if (li == null) {
                model.cr = new ArrayList<ConsolidateReport>();
            } else {
                model.cr = li;

            }
            model.warehousereport.put(loc[i], model.cr);
        }
        return model;
    }

    public MultipleWarehouseModel calculate(AppView app, String warehouse) throws BasicException {
        MultipleWarehouseModel model = new MultipleWarehouseModel();
        consolidate co = null;
        cons = new ArrayList<consolidate>();
        Map<String, List<ConsolidateReport>> map = getWarehousereport();//getWarehousereport();
        Map<String, Double> productQty = new HashMap<String, Double>();
        List<ConsolidateReport> list = new ArrayList<ConsolidateReport>();
        List<ConsolidateReport> list1 = new ArrayList<ConsolidateReport>();
        String[] loc = null;
        Object[] obj = null;
        if (warehouse != null) {
            loc = warehouse.split("#");
        }
        for (int i = 0; i < loc.length; i++) {
            list = map.get(loc[i].toString());
            for (ConsolidateReport c : list) {
                co = new consolidate();
                co.setId(c.getId());
                co.setProductName(c.getProduct());
                co.setCategoryName(c.getCategory());
                co.setQty(c.getQty());
          

                productQty.put(c.getId(), co.getQty());

                cons.add(co);
            }
            break;
        }
        Double d = null;
        Double d1 = null;
        for (int i = 1; i < loc.length; i++) {
            list1 = map.get(loc[i].toString());
            for (ConsolidateReport c : list1) {
              
                d = productQty.get(c.getId());
                 if(d!= null){
                productQty.remove(c.getId());
                d1 = d + c.getQty();
                productQty.put(c.getId(), d1);
                 }
               }
            
        }
        for (consolidate c : getCons()) {
            c.setQty(productQty.get(c.getId()));
        }
        model.setCons(getCons());
        return model;
    }

    public static class consolidate implements SerializableRead {

        private String id;
        private String productName;
        private double qty;
        private String categoryName;
        private List<String> warehouse;
        private String units;
      

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            productName = dr.getString(2);
            qty = dr.getDouble(3);
            categoryName = dr.getString(4);
   

        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public List<String> getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(List<String> warehouse) {
            this.warehouse = warehouse;
        }

       

        
    }

    public static class ConsolidateReport implements SerializableRead {

        private String id;
        private String product;
        private String units;
        private Double qty;
        private Double total;
        private String category;
        private String warehouse;
   

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            product = dr.getString(2);
            if (dr.getDouble(3) != null) {
                qty = dr.getDouble(3);
            } else {
                qty = 0.0;
            }
            category = dr.getString(4);
        


        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public String getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

       
    }

    public static class MultipleWarehouse implements SerializableRead {

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }
        private String id;
        private String name;
        private String parent;
        private String locations;
        private String reportwise;
        private String reporttype;
        private boolean flag;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            name = dr.getString(2);
            parent = dr.getString(3);
            locations = dr.getString(4);
            reportwise = dr.getString(5);
            reporttype = dr.getString(6);
            flag = dr.getBoolean(7);


        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocations() {
            return locations;
        }

        public void setLocations(String locations) {
            this.locations = locations;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReporttype() {
            return reporttype;
        }

        public void setReporttype(String reporttype) {
            this.reporttype = reporttype;
        }

        public String getReportwise() {
            return reportwise;
        }

        public void setReportwise(String reportwise) {
            this.reportwise = reportwise;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
