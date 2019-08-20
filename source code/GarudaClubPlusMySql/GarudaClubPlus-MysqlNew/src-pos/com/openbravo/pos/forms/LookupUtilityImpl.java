/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.forms;

import com.openbravo.data.gui.ILookupUtility;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.FacilityInfo;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.mant.FloorsInfo;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.sales.DataLogicReceipts;
import com.openbravo.pos.sales.Qticket;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.PrintCategoryInfo;
import com.openbravo.pos.ticket.ProductInfo;
import com.openbravo.pos.ticket.TaxInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author swathi
 */
public class LookupUtilityImpl implements ILookupUtility {

    protected static LookupUtilityImpl singleton;

    public static LookupUtilityImpl getInstance(AppView app) {
        if (singleton == null) {
            singleton = new LookupUtilityImpl(app);
        }
        return singleton;
    }

    protected AppView app;
    protected DataLogicSales dlSales;
    protected DataLogicCustomers dlCustomers;
    protected DataLogicFacilities dlfac;
    protected DataLogicSystem dlSystem;
    protected BillLogic dlBill;
    protected Qticket dlQT;
    protected DataLogicReceipts dlReceipts;

    private Map<String, ProductInfo> productMap;
    private Map<String, PrintCategoryInfo> prCategoryMap = new HashMap<String, PrintCategoryInfo>();
    private List<TaxInfo> taxlist;
    private Map<String, PlacesInfo> placeMap = new HashMap<String, PlacesInfo>();
    private Map<String, WaiterInfo> waiterMap = new HashMap<String, WaiterInfo>();
    private Map<String, FloorsInfo> floorMap = new HashMap<String, FloorsInfo>();
    private Map<String, RoleInfo> roleMap = new HashMap<String, RoleInfo>();
    private Map<String, FacilityInfo> FacilityMap = new HashMap<String, FacilityInfo>();
    private Map<String, GeneralSettingInfo> GeneralSettingsMap = new HashMap<String, GeneralSettingInfo>();

    protected LookupUtilityImpl(AppView app) {
        this.app = app;
        this.dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        this.dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        this.dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        this.dlBill = (BillLogic) app.getBean("com.openbravo.pos.sales.BillLogic");
        this.dlfac=(DataLogicFacilities)app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlBill.setDataLogicSales(dlSales);
        dlBill.setAppView(app);

        dlQT = (Qticket) app.getBean("com.openbravo.pos.sales.Qticket");
        dlQT.setDataLogicSales(dlSales);
        dlQT.setAppView(app);

        dlReceipts = (DataLogicReceipts) app.getBean("com.openbravo.pos.sales.DataLogicReceipts");
    }

    public DataLogicCustomers getDataLogicCustomers() {
        return dlCustomers;
    }

    public DataLogicSystem getDataLogicSystem() {
        return dlSystem;
    }
    public DataLogicFacilities getDataLogicFacility() {
        return dlfac;
    }

    public AppView getAppView() {
        return app;
    }

    public BillLogic getDataLogicBill() {
        return dlBill;
    }

    public Qticket getDataLogicQT() {
        return dlQT;
    }

    public DataLogicReceipts getDataLogicReceipts() {
        return dlReceipts;
    }

    public DataLogicSales getDataLogicSales() {
        return dlSales;
    }

    public void refresh() throws BasicException {
//        reloadProductsMap();
//        reloadPRCategoriesMap();
//        reloadTaxList();
//        reloadPlacesMap();
//        reloadWaiterMap();
//        reloadFloorMap();
//        reloadRoleMap();
//        reloadFacilitiesMap();
 //       reloadGeneralSettingsMap();
    }


    private void reloadPlacesMap() throws BasicException {
        List<PlacesInfo> placesList = dlSales.getPlacesList().list();
        placeMap.clear();
        for (PlacesInfo pInfo: placesList) {
            placeMap.put(pInfo.getID(), pInfo);
        }
    }
      public void reloadGeneralSettingsMap() throws BasicException {
        List<GeneralSettingInfo> gsList = dlfac.getGeneralSettingsList().list();
        GeneralSettingsMap.clear();
        for (GeneralSettingInfo gsInfo: gsList) {
            GeneralSettingsMap.put(gsInfo.getName(), gsInfo);
        }
    }
     private void reloadFacilitiesMap() throws BasicException {
        List<FacilityInfo> facilityList = dlfac.getFacilityList().list();
        placeMap.clear();
        for (FacilityInfo fInfo: facilityList) {
            FacilityMap.put(fInfo.getID(), fInfo);
        }
    }

    private void reloadRoleMap() throws BasicException {
        List<RoleInfo> rolelist = dlSales.getRoleList().list();
        roleMap.clear();
        for (RoleInfo rInfo: rolelist) {
            roleMap.put(rInfo.getID(), rInfo);
        }
    }
    private void reloadWaiterMap() throws BasicException {
        List<WaiterInfo> waitersList = dlSales.getWaiterList1();
        waiterMap.clear();
        for (WaiterInfo wInfo: waitersList) {
            waiterMap.put(wInfo.getID(), wInfo);
        }
    }

    private void reloadFloorMap() throws BasicException {
        List<FloorsInfo> floorlist = dlSales.getFloorsList().list();
        floorMap.clear();
        for (FloorsInfo finfo: floorlist) {
            floorMap.put(finfo.getID(), finfo);
        }
    }


    private void reloadProductsMap() throws BasicException {
        productMap = new HashMap<String, ProductInfo>();
        List<ProductInfo> productList = dlSales.getProductList().list();
        productMap.clear();
        for (ProductInfo pInfo : productList) {
            productMap.put(pInfo.getID(), pInfo);
        }
    }

    private void reloadTaxList() throws BasicException {
        taxlist = dlSales.getTaxList().list();
    }

    public Map<String, ProductInfo> getProductsMap() {
        return productMap;
    }

    private void reloadPRCategoriesMap() throws BasicException {
        prCategoryMap = new HashMap<String, PrintCategoryInfo>();
        List<PrintCategoryInfo> prcatlist = dlSales.getprCategoriesList().list();
        prCategoryMap.clear();
        for (PrintCategoryInfo cInfo : prcatlist) {
            prCategoryMap.put(cInfo.getID(), cInfo);
        }
    }

    public Map<String, PrintCategoryInfo> getPRCategoriesMap() {
        return prCategoryMap;
    }

    public TaxInfo getTaxInfo(TaxCategoryInfo tc, CustomerInfoExt customer) {


        TaxInfo defaulttax = null;

        for (TaxInfo tax : taxlist) {
            if (tax.getParentID() == null && tax.getTaxCategoryID().equals(tc.getID())) {
                if ((customer == null || customer.getTaxCustCategoryID() == null) && tax.getTaxCustCategoryID() == null) {
                    return tax;
                } else if (customer != null && customer.getTaxCustCategoryID() != null && customer.getTaxCustCategoryID().equals(tax.getTaxCustCategoryID())) {
                    return tax;
                }

                if (tax.getTaxCustCategoryID() == null) {
                    defaulttax = tax;
                }
            }
        }

        // No tax found
        return defaulttax;
    }

    public Map<String, PlacesInfo> getPlacesMap() {
        return placeMap;
    }

    public Map<String, WaiterInfo> getWaiterMap() {
        return waiterMap;
    }

    public Map<String, FloorsInfo> getFloorMap() {
        return floorMap;
    }

    public Map<String, RoleInfo> getRoleMap() {
        return roleMap;
    }
    public Map<String, FacilityInfo> getFacilityMap() {
        return FacilityMap;
    }//GeneralSettingsMap
     public Map<String, GeneralSettingInfo> getGeneralSettingsMap() {
        return GeneralSettingsMap;
    }

}
