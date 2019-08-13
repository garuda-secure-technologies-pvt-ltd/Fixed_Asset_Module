package com.openbravo.pos.forms;

import RMI.ComputePi;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.openbravo.data.loader.*;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.inventory.DeliveryPeriodInfo;
import com.openbravo.pos.inventory.TaxCustCategoryInfo;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.inventory.MovementReason;
import com.openbravo.pos.inventory.MultipleWarehouseModel;
import com.openbravo.pos.inventory.MultipleWarehouseModel.MultipleWarehouse;
import com.openbravo.pos.inventory.OtherChargesInfo;
import com.openbravo.pos.inventory.PaymentModeInfo;
import com.openbravo.pos.inventory.PdtUnitInfo;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.inventory.TaxCategoryInfo1;
import com.openbravo.pos.inventory.TaxCategoryInfo2;
import com.openbravo.pos.inventory.TaxlineInfo;
import com.openbravo.pos.mant.FloorsInfo;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.BankInfo;
import com.openbravo.pos.payment.ChequeDetails;
import com.openbravo.pos.payment.JPaymentSelect;
import static com.openbravo.pos.payment.JPaymentSelect.txRefID;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.sales.Department1;
import com.openbravo.pos.sales.PeoplesRoleInfo;
import com.openbravo.pos.sales.QtLimitTable;
import com.openbravo.pos.ticket.PrintCategoryInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author adrianromero
 */
 public abstract class DataLogicSales extends BeanFactoryDataSingle {

    protected Session s;
    protected Datas[] stockdiaryDatas;
    protected Datas[] productcatDatas;
    protected Datas[] paymenttabledatas;
    protected Datas[] stockdatas;
    protected Datas[] stockdiaryDatas1;
    protected Datas[] stockdiaryDatas2;
    protected Datas[] stockdiaryDatas3;
    public static int gflag2=0;
    public static String greceiptid=null;
    
    /** Creates a new instance of SentenceContainerGeneric */
    public DataLogicSales() {
        productcatDatas = new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT, Datas.BYTES, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.INT, Datas.INT, Datas.INT, Datas.INT , Datas.STRING , Datas.STRING ,  Datas.BOOLEAN ,  Datas.BOOLEAN};
        stockdiaryDatas = new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING};
        paymenttabledatas = new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING};
        stockdatas = new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE};
        stockdiaryDatas1 = new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING};
        stockdiaryDatas2 = new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING};
        stockdiaryDatas3 = new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE};
    }

    public void init(Session s) {
        this.s = s;
    }

    // Utilidades de productos
    public final ProductInfoExt getProductInfo(String id) throws BasicException {

        /*  Object[] obj=(Object[]) new StaticSentence(s,
        "SELECT PRCATEGORIES FRPM PEOPLE WHERE NAME = ?"
        , SerializerWriteString.INSTANCE
        ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
         */
        return (ProductInfoExt) new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3  " +                                                                                             // edited by aakash 
                "FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(id);
    }

    public final ProductInfoExt getProductInfoByCode(String sCode) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION  , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                " FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.CODE = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(sCode);
    }

    public final ProductInfoExt getProductInfoByReference(String sReference) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES , P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION  , P.TAXCAT2 ,   P.TAXCAT3  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.REFERENCE = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(sReference);
    }

    // Catalogo de productos
    public final List<CategoryInfo> getRootCategories() throws BasicException {
        return new PreparedSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES WHERE PARENTID IS NULL  ORDER BY NAME", null, new SerializerReadClass(CategoryInfo.class)).list();
    }
//sameer:added getRootCategories(String id)
   public final List<Department1> getDepartments() throws BasicException {
        return(List<Department1>) new StaticSentence(s, "SELECT ID, NAME,ACTIVE FROM DEPARTMENT ORDER BY NAME", null, new SerializerReadClass(Department1.class)).list();
    }
    public final List<CategoryInfo> getRootCategories(String id) throws BasicException {
        return new PreparedSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES WHERE PARENTID IS NULL and id=?  ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(CategoryInfo.class)).list(id);
    }

    public final List<CategoryInfo> getMainWarehouses() throws BasicException {
        return new PreparedSentence(s, "SELECT C.ID, C.NAME,C.PARENTID, C.IMAGE FROM CATEGORIES C JOIN LOCATIONS L ON C.ID=L.ID AND L.PARENT IS NULL AND L.STOCK =TRUE  ORDER BY NAME", null, new SerializerReadClass(CategoryInfo.class)).list();
    }

   /* public final List<Department1> getDepartments() throws BasicException {
        return (List<Department1>) new StaticSentence(s, "SELECT ID, NAME,ACTIVE FROM DEPARTMENT ORDER BY NAME", null, new SerializerReadClass(Department1.class)).list();
    }*/

    public final List<Department1> getActiveDepartments() throws BasicException {
        return (List<Department1>) new StaticSentence(s, "SELECT ID, NAME,ACTIVE FROM DEPARTMENT WHERE ACTIVE=TRUE ORDER BY NAME", null, new SerializerReadClass(Department1.class)).list();
    }

    public final CategoryInfo getCategoryByID(String id) throws BasicException {
        return (CategoryInfo) new PreparedSentence(s, "SELECT C.ID, C.NAME,C.PARENTID, C.IMAGE FROM CATEGORIES C WHERE C.ID=?", null, new SerializerReadClass(CategoryInfo.class)).find();
    }

    public final List<CategoryInfo> getParticularCategories(String warehousename) throws BasicException {
        return new PreparedSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES WHERE PARENTID IS NULL AND NAME = ? ORDER BY NAME", null, new SerializerReadClass(CategoryInfo.class)).list(warehousename);
    }

    public final List<CategoryInfo> getSubcategories(String category) throws BasicException {
        return new PreparedSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES WHERE PARENTID = ? ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(CategoryInfo.class)).list(category);
    }

    public final List<ProductInfoExt> getProductCatalog(String category) throws BasicException {
        return new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES , P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION  , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID LEFT OUTER JOIN CATEGORIES C ON P.CATEGORY = C.ID, PRODUCTS_CAT O WHERE P.ID = O.PRODUCT AND P.CATEGORY = ?" +
                "ORDER BY C.NAME, O.CATORDER,p.name, P.REFERENCE", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).list(category);
    }

    public final List<ProductInfoExt> getProductComments(String id) throws BasicException {
        return new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES , P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION  , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3  " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID, PRODUCTS_CAT O, PRODUCTS_COM M WHERE P.ID = O.PRODUCT AND P.ID = M.PRODUCT2 AND M.PRODUCT = ? " +
                "ORDER BY O.CATORDER, P.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).list(id);
    }

    public final PrintCategoryInfo getprCategoryByID(String id) throws BasicException {
        return (PrintCategoryInfo) new PreparedSentence(s, "SELECT ID, NAME, PRINTER, IMAGE FROM PRCATEGORIES WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(PrintCategoryInfo.class)).find(id);
    }


    // Products list
    public final SentenceList getProductList() {
        return new StaticSentence(s, new QBFBuilder(
                "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE ?(QBF_FILTER) ORDER BY P.REFERENCE", new String[]{"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"}), new SerializerWriteBasic(new Datas[]{Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING }), new SerializerReadClass(ProductInfoExt.class));
    }

    //praveen:added to get locationwise products
    public final List<ProductInfoExt> getProductList(String location) throws BasicException {
        return (List<ProductInfoExt>) new StaticSentence(s,
                "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3)  , P.BASIC2 , P.BASIC3 " +
                "FROM CATEGORIES C,PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.CATEGORY  IN (SELECT ID FROM CATEGORIES WHERE PARENTID=?) GROUP BY P.ID ORDER BY P.REFERENCE", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).list(location);
    }

    public final Object[] getCompanyDetail(String name) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT FULLNAME, ADDRESS FROM COMPANY WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(name);
    }

    // Listados para combo        
    public final SentenceList getTaxList() {
        return new StaticSentence(s, "SELECT ID, NAME, CATEGORY, CUSTCATEGORY, PARENTID, RATE, RATECASCADE, RATEORDER FROM TAXES ORDER BY NAME", null, new SerializerReadClass(TaxInfo.class));
    }

    public final TaxInfo getTaxListById(String id) throws BasicException {
        return (TaxInfo) new StaticSentence(s, "SELECT T.ID, T.NAME, T.CATEGORY, TC.ACCOUNT, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER FROM TAXES T,TAXCATEGORIES TC WHERE TC.ID=T.CATEGORY AND TC.ID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(TaxInfo.class)).find(id);
    }

    public final Object[] getParentCategories(String id) {
        try {
            return (Object[]) new StaticSentence(s, "SELECT PARENTID,NAME,ID FROM CATEGORIES WHERE ID= ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(id);

        } catch (Exception e) {
        }
        return null;
    }

    public final Object getParentCategory(String id) {
        try {
            return (Object) new StaticSentence(s, "SELECT PARENTID FROM CATEGORIES WHERE ID= ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(id);

        } catch (Exception e) {
        }
        return null;
    }

    public final SentenceList getCategoriesList() {
        return new StaticSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES ORDER BY NAME", null, new SerializerReadClass(CategoryInfo.class));
    }

    public final List<CategoryInfo> getCategoriesListByParent(String parent) throws BasicException {
        return (List<CategoryInfo>) new StaticSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES WHERE LOCATION=? ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(CategoryInfo.class)).list(parent);
    }

    public final List<CategoryInfo> getCategoriesListByParent() throws BasicException {
        return (List<CategoryInfo>) new StaticSentence(s, "SELECT ID, NAME,PARENTID, IMAGE FROM CATEGORIES ORDER BY NAME", null, new SerializerReadClass(CategoryInfo.class)).list();
    }

    public final String getCategoriesbyname(String name) throws BasicException {
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT ID FROM CATEGORIES WHERE PARENTID IS NULL AND NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name);
        if (obj != null && obj[0] != null) {
            return obj[0].toString();
        } else {
            return null;
        }
    }

    public final String getCategorieNamebyID(String id) throws BasicException {
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT NAME FROM CATEGORIES WHERE  ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);
        if (obj != null && obj[0] != null) {
            return obj[0].toString();
        } else {
            return null;
        }
    }

    public final SentenceList getprCategoriesList() {
        return new StaticSentence(s, "SELECT ID, NAME, PRINTER, IMAGE FROM PRCATEGORIES ORDER BY NAME", null, new SerializerReadClass(PrintCategoryInfo.class));
    }

    public final List<WaiterInfo> getActiveWaiterList(String cname) throws BasicException {
        return new PreparedSentence(s, "SELECT W.ID,W.NAME,W.COUNTER,W.CARDNO FROM WAITER W WHERE W.COUNTER=? AND W.SHOW_=TRUE   ORDER BY W.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).list(cname);
    }
    //PRAVEEN:ADDED TO GET WAITER THROUGH CARD

    public final List<WaiterInfo> getActiveCardWaiter(String id) throws BasicException {
        return new PreparedSentence(s, "SELECT W.ID,W.NAME,W.COUNTER,W.CARDNO FROM WAITER W WHERE W.ID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).list(id);
    }

    public final WaiterInfo getActiveCardWaiterByCard(String card) throws BasicException {
        return (WaiterInfo) new PreparedSentence(s, "SELECT W.ID,W.NAME,W.COUNTER,W.CARDNO FROM WAITER W WHERE W.CARDNO=?", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(card);
    }

    public final List<WaiterInfo> getWaiterList(String cname) throws BasicException {
        return new PreparedSentence(s, "SELECT W.ID,W.NAME,W.COUNTER,W.CARDNO,W.SHOW_ FROM WAITER W WHERE W.COUNTER=?  ORDER BY W.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).list(cname);
    }

    public final List<WaiterInfo> getWaiterList1() throws BasicException {
        return new PreparedSentence(s, "SELECT W.ID, W.NAME,W.COUNTER,W.CARDNO,W.SHOW_ FROM WAITER W ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).list();
    }

    public final SentenceList getBankList() {
        return new StaticSentence(s, "SELECT ID, BANKNAME FROM BANK ORDER BY BANKNAME", null, new SerializerReadClass(BankInfo.class));
    }

    public final SentenceList getPaymentsList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM PAYMENTDETAILS ORDER BY NAME", null, new SerializerReadClass(PaymentModeInfo.class));
    }

    public final SentenceList getDeliveryPeriodList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM DELIVERYPERIOD ORDER BY NAME", null, new SerializerReadClass(DeliveryPeriodInfo.class));
    }

    public final SentenceList getTaxlineList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM TAXDETAILS ORDER BY NAME", null, new SerializerReadClass(TaxlineInfo.class));
    }

    public final SentenceList getOtherChargesList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM OTHERCHARGES ORDER BY NAME", null, new SerializerReadClass(OtherChargesInfo.class));
    }

    public final SentenceList getUnitList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM UNIT ORDER BY NAME", null, new SerializerReadClass(PdtUnitInfo.class));
    }

    public final WaiterInfo getWaiterByID(String id) throws BasicException {
        return (WaiterInfo) new PreparedSentence(s, "SELECT ID, NAME,SHOW_,CARDNO FROM WAITER WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(id);
    }

    public final SentenceList getTaxCustCategoriesList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM TAXCUSTCATEGORIES ORDER BY NAME", null, new SerializerReadClass(TaxCustCategoryInfo.class));
    }

    public final TaxCategoryInfo getTaxCategoryByid(String id) throws BasicException {
        return (TaxCategoryInfo) new StaticSentence(s, "SELECT ID, NAME,account FROM TAXCATEGORIES WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(TaxCategoryInfo.class)).find(id);
    }

    public final SentenceList getTaxCategoriesList() {
        return new StaticSentence(s, "SELECT ID, NAME,account FROM TAXCATEGORIES ORDER BY NAME", null, new SerializerReadClass(TaxCategoryInfo.class));
    }

        public final SentenceList getLocationsList() {
        return new StaticSentence(s, "SELECT ID, NAME, ADDRESS,RDISPLAYNAME,PARENT,STOCK FROM LOCATIONS ORDER BY NAME", null, new SerializerReadClass(LocationInfo.class));
    }

    public List<LocationInfo> getAllLocationsList() throws BasicException {
        return new StaticSentence(s, "SELECT ID, NAME, ADDRESS,RDISPLAYNAME,PARENT,STOCK FROM LOCATIONS ORDER BY NAME", null, new SerializerReadClass(LocationInfo.class)).list();
    }

    public List<LocationInfo> getParentLocationsList() throws BasicException {
        return new StaticSentence(s, "SELECT ID, NAME, ADDRESS,RDISPLAYNAME,PARENT,STOCK FROM LOCATIONS WHERE PARENT IS NULL ORDER BY NAME", null, new SerializerReadClass(LocationInfo.class)).list();
    }

     public List<MultipleWarehouse> getConsolidateReports() throws BasicException {
        List<MultipleWarehouse> li = null;
        List l = new StaticSentence(s, "SELECT ID,NAME,PARENTWARE,WAREHOUSE,REPORTWISE,REPORTTYPE,FLAG FROM CONSOLIDATEREPORT WHERE FLAG=TRUE", null, new SerializerReadClass(MultipleWarehouse.class)).list();
        if (l == null) {
            li = new ArrayList<MultipleWarehouse>();
        } else {
            li = l;
        }
        return li;
    }

    public List<LocationInfo> getChildLocationsList(List<LocationInfo> pid) throws BasicException {
        StringBuffer condition = new StringBuffer("");
        Object[] params = new Object[pid.size()];
        Datas[] data = new Datas[pid.size()];
        int i = 0;
        for (LocationInfo l : pid) {
            data[i] = Datas.STRING;
            params[i] = l.getID();
            condition.append(" ? ,");
            i++;
        }
        condition.deleteCharAt(condition.lastIndexOf(","));
        return new StaticSentence(s, "SELECT ID, NAME, ADDRESS,RDISPLAYNAME,PARENT,STOCK FROM LOCATIONS WHERE PARENT IN ( " + condition.toString() + " ) ORDER BY NAME", new SerializerWriteBasic(data), new SerializerReadClass(LocationInfo.class)).list(params);
    }

    public final LocationInfo getLocationsInfoByID(String id) throws BasicException {
        return (LocationInfo) new StaticSentence(s, "SELECT ID, NAME, ADDRESS,RDISPLAYNAME,PARENT,STOCK FROM LOCATIONS WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(LocationInfo.class)).find(new Object[]{id});
    }

    public List<LocationInfo> getLocationsInfoByParent(String id) throws BasicException {
        return new StaticSentence(s, "SELECT ID, NAME, ADDRESS,RDISPLAYNAME,PARENT,STOCK FROM LOCATIONS WHERE PARENT=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(LocationInfo.class)).list(new Object[]{id});
    }

    public final SentenceList getPlacesList() {
        return new StaticSentence(s, "SELECT ID, NAME, X, Y, FLOOR FROM PLACES ORDER BY NAME", null, new SerializerReadClass(PlacesInfo.class));
    }

    public final SentenceList getPlacesInfo(String id) {
        return new StaticSentence(s, "SELECT ID, NAME, X, Y, FLOOR FROM PLACES WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(PlacesInfo.class));
    }

    public final List<PlacesInfo> getPlaceList(String floor) throws BasicException {
        //List<PlacesInfo> plinfo=new List<PlacesInfo>();
        //  try{
        return new PreparedSentence(s, "SELECT ID, NAME, X, Y, FLOOR FROM PLACES WHERE FLOOR = ? ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(PlacesInfo.class)).list(floor);
    //  }
      /*   catch(BasicException e)
    {
    return plinfo;
    }*/
    }

    public final SentenceList getRoleList() {
        return new StaticSentence(s, "SELECT ID, NAME FROM ROLES ORDER BY NAME", null, new SerializerReadClass(RoleInfo.class));
    }

    public final List<RoleInfo> getCatalogListToRole(String role) throws BasicException {
        return (List<RoleInfo>) new StaticSentence(s, "SELECT CATEGORY,USERNAME FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(RoleInfo.class)).list(role);
    }

    public final boolean getDecimalWarehouseAllowed(String warehouse) throws BasicException{
        boolean flag=false;
        Object obj = new StaticSentence(s, "SELECT DECIMALALLOWED FROM LOCATIONS WHERE ID=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(warehouse);
        if(obj!=null){
            flag = Boolean.valueOf(obj.toString());
        }
        return flag;
    }

    public final SentenceList getPeopleList() {
        return new StaticSentence(s, "SELECT ID, NAME,ROLE FROM PEOPLE ORDER BY NAME", null, new SerializerReadClass(PeopleInfo.class));
    }

    public List<RoleInfo> getPeopleList1() throws BasicException {
        return new StaticSentence(s, "SELECT ID, NAME FROM ROLES ORDER BY NAME", null, new SerializerReadClass(RoleInfo.class)).list();
    }

    public final SentenceList getActivePeopleListWithOutCCAcc() {
        return new StaticSentence(s, "SELECT ID, NAME,ROLE FROM PEOPLE WHERE VISIBLE=TRUE AND CASHACCOUNT IS NULL ORDER BY NAME", null, new SerializerReadClass(PeopleInfo.class));
    }

    public final SentenceList getPeoplewithcashandcheque() {
        return new StaticSentence(s, "SELECT ID, NAME,ROLE FROM PEOPLE WHERE CASHACCOUNT IS NOT NULL AND CHEQUEACCOUNT IS NOT NULL AND VISIBLE = TRUE ORDER BY NAME ", null, new SerializerReadClass(PeopleInfo.class));
    }

    public final List<String> getCashierAccounts() throws BasicException {
        return new StaticSentence(s,
                "SELECT CASHACCOUNT FROM PEOPLE WHERE CASHACCOUNT IS NOT NULL " +
                "UNION SELECT CHEQUEACCOUNT FROM PEOPLE WHERE CHEQUEACCOUNT IS NOT NULL ", null, SerializerReadString.INSTANCE).list();
    }

    public final PlacesInfo getPlaceByID(String id) throws BasicException {
        return (PlacesInfo) new PreparedSentence(s, "SELECT ID, NAME, X, Y, FLOOR FROM PLACES WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(PlacesInfo.class)).find(id);
    }

    public final PlacesInfo getPlacebyfloor(String id) throws BasicException {
        return (PlacesInfo) new PreparedSentence(s, "SELECT ID, NAME, X, Y, FLOOR FROM PLACES WHERE FLOOR = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(PlacesInfo.class)).find(id);
    }

    public final SentenceList getFloorsList() {
        // AppView app=LookupUtilityImpl.getInstance(null).getAppView();
        //  boolean flag=app.getAppUserView().getUser().hasPermission("Bar");
        return new StaticSentence(s, "SELECT ID, NAME, BILLSL, LASTBILL FROM FLOORS ORDER BY NAME", null, new SerializerReadClass(FloorsInfo.class));
    }

    public final FloorsInfo getFloorByID(String id) throws BasicException {
        return (FloorsInfo) new PreparedSentence(s, "SELECT ID, NAME, BILLSL, LASTBILL FROM FLOORS WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(FloorsInfo.class)).find(id);
    }

    public CustomerInfoExt findCustomerExt(String card) throws BasicException {
        return (CustomerInfoExt) new PreparedSentence(s, "SELECT ID, TAXID, SEARCHKEY, NAME, CARD, TAXCATEGORY, NOTES, VISIBLE " +
                ",MEMTYPE,SOWO,SERVICETAXCAT,ENTTAXCAT ,JOINDATE,DOB,OPENINGBALANCE ,MOBILE,ACCOUNT " +
                " FROM CUSTOMERS WHERE CARD = ? AND VISIBLE = TRUE", SerializerWriteString.INSTANCE, new CustomerExtRead()).find(card);
    }
    //"MEMTYPE","FINGERPRINTDATA","SOWO"
    //, "MOBILE","SERVICETAXCAT","ENTTAXCAT","SIGNATURE","SPONSOR1","SPONSOR2","SPONSOR3"
    //, "JOINDATE","TERDATE","DOB"
  /*  public CustomerInfoExt loadCustomerExt(String id) throws BasicException {
    return (CustomerInfoExt) new PreparedSentence(s
    , "SELECT ID, TAXID, SEARCHKEY, NAME, CARD, TAXCATEGORY, NOTES, VISIBLE " +
    ", FIRSTNAME, LASTNAME, EMAIL, PHONE, PHONE2, FAX" +
    ", ADDRESS, ADDRESS2, POSTAL, CITY, REGION, COUNTRY,MEMTYPE,FINGERPRINTDATA , SOWO " +
    ",MOBILE,SERVICETAXCAT,ENTTAXCAT,SIGNATURE,SPONSOR1,SPONSOR2,SPONSOR3,JOINDATE,TERDATE,DOB,OPENINGBALANCE "+
    " FROM CUSTOMERS WHERE ID = ?"
    , SerializerWriteString.INSTANCE
    , new CustomerExtRead()).find(id);
    }*/

    public CustomerInfoExt loadCustomerExt(String id) throws BasicException {
        return (CustomerInfoExt) new PreparedSentence(s, "SELECT ID, TAXID, SEARCHKEY, NAME, CARD, TAXCATEGORY, NOTES, VISIBLE " +
                ",MEMTYPE,SOWO,SERVICETAXCAT,ENTTAXCAT ,JOINDATE,DOB,OPENINGBALANCE ,MOBILE,ACCOUNT " +
                " FROM CUSTOMERS WHERE ID = ?", SerializerWriteString.INSTANCE, new CustomerExtRead()).find(id);
    }

    public List<CustomerInfoExt> loadCustomerExt() throws BasicException {
        return  new PreparedSentence(s, "SELECT ID, TAXID, SEARCHKEY, NAME, CARD, TAXCATEGORY, NOTES, VISIBLE " +
                ",MEMTYPE,SOWO,SERVICETAXCAT,ENTTAXCAT ,JOINDATE,DOB,OPENINGBALANCE ,MOBILE,ACCOUNT " +
                " FROM CUSTOMERS", null,new SerializerReadClass(CustomerInfoExt.class)).list();
    }

    /*  public final boolean isCashActive(String id) throws BasicException {

    return new PreparedSentence(s,
    "SELECT MONEY FROM CLOSEDCASH WHERE DATEEND IS NULL AND MONEY = ?",
    SerializerWriteString.INSTANCE,
    SerializerReadString.INSTANCE).find(id)
    != null;
    }*/
    public final TicketInfo loadTicket(Integer ticketid) throws BasicException {
        TicketInfo ticket = (TicketInfo) new PreparedSentence(s, "SELECT T.ID, T.TICKETID, R.DATENEW, R.MONEY, R.ATTRIBUTES, P.ID, P.NAME, T.CUSTOMER FROM RECEIPTS R JOIN TICKETS T ON R.ID = T.ID LEFT OUTER JOIN PEOPLE P ON T.PERSON = P.ID WHERE T.TICKETID = ?", SerializerWriteInteger.INSTANCE, new SerializerReadClass(TicketInfo.class)).find(ticketid);
        if (ticket != null) {

            String customerid = ticket.getCustomerId();
            ticket.setCustomer(customerid == null
                    ? null
                    : loadCustomerExt(customerid));

            ticket.setLines(new PreparedSentence(s, "SELECT L.TICKET, L.LINE, L.PRODUCT, L.UNITS, L.PRICE, T.ID, T.NAME, T.CATEGORY, T.CUSTCATEGORY, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER, L.ATTRIBUTES " +
                    "FROM TICKETLINES L, TAXES T WHERE L.TAXID = T.ID AND L.TICKET = ? ORDER BY L.LINE", SerializerWriteString.INSTANCE, new SerializerReadClass(TicketLineInfo.class)).list(ticket.getId()));
            ticket.setPayments(new PreparedSentence(s, "SELECT PAYMENT, TOTAL FROM PAYMENTS WHERE RECEIPT = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(PaymentInfoTicket.class)).list(ticket.getId()));
        }
        return ticket;
    }
    //praveen:sequencedetail:inserting id instead of names--start

    public final String getNextReceiptID(String createdby) throws BasicException {

        String receiptnum;
       // System.out.println("getNextReceiptId1:::line 478 DATALogicSales");
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();

        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = (SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ID=? AND ROLES.ID=PEOPLE.ROLE)  AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});

            return receiptnum;
        } else {
         //   System.out.println("DATA Logic sales line 492::please::");
            JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
            return null;
        }
    }

    public final String getNextReceiptID1(String createdby) throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String receiptnum;
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();

        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = (SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ID=? AND ROLES.ID=PEOPLE.ROLE)  AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            greceiptid=receiptnum;
            return receiptnum;
        } else {
                
                JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
                return null;
        }
        
    }    
    
    
    //praveen:sequencedetail:inserting id instead of names--end
    private String receiptno;
    private boolean perror;
    private boolean berror;

    public final String payaccount(final BillInfo ticket, final String location, final boolean flag) throws BasicException {
        flag1 = 0;
        receiptno = null;
        perror = false;
        Transaction t = new Transaction(s) {
            //  boolean error=false;

            public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(userid);
                if (obj != null) {
                    if (obj[0] != null) {
                        Date d = (Date) obj[0];
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTimeInMillis(date.getTime());
                        cal2.setTimeInMillis(d.getTime());
                        if (cal1.before(cal2)) {
                            JOptionPane.showMessageDialog(null, "Bill Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                            perror = true;

                        }
                    }
                }
                if (perror == false) {
                    if (ticket.getReceiptRef() == null) {
                        final String rno = getNextReceiptID1(ticket.getCreatedBy());
                       // System.out.println("getNextReceiptId1:::line 578 DATALogicSales");
                        receiptno = rno;
                        ticket.setReceiptRef(rno);
                        if (rno == null) {
                            flag1 = 1;
                            return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                    }
                    // new receipt
                    if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, ticket.getPlace());//contains description for general receipts
                            }
                        });
                    } else {
                        new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                            }
                        });
                    }

                    SentenceExec paymentinsert = new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                    for (final PaymentInfo p : ticket.getPayments()) {
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                setString(3, p.getName());
                                setDouble(4, p.getTotal());
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });

                        /*     if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                        getDebtUpdate().exec(new Object[]{
                        ticket.getCustomer().getId(),
                        new Double(p.getTotal()),
                        date
                        });
                        }*/

                        if ("cheque".equals(p.getName())) {
                            SentenceExec chequeInsert = new PreparedSentence(s, "INSERT INTO CHEQUE(ID, CHEQUENO, BANK,RNO,HOLDER,AMOUNT) VALUES (?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                            final ChequeDetails cd = p.getChequeDetails();
                            chequeInsert.exec(new DataParams() {

                                @Override
                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, cd.getChequeno());
                                    setString(3, cd.getBank());
                                    setString(4, ticket.getReceiptRef());
                                    setString(5, cd.getholder());
                                    setDouble(6, cd.getAmount());
                                }
                            });
                            List<BankInfo> temp = getBankList().list();
                            boolean result = false;
                            for (BankInfo b : temp) {
                                if ((cd.getBank().equals(b.getName()))) {
                                    result = true;
                                    break;
                                }
                            }
                            if (!result) {
                                SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                bankInsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getBank());
                                    }
                                });
                            }
                        }
                        
                        
                        
                        
                         // IF PAYMENT TYPE IS CARD      ## BY AAKASH
                            if ("magcard".equals(p.getName())) {
                            
                                    if(location.equals("1")){
                                        SentenceExec cardDetailsInsert = new PreparedSentence(s, "INSERT INTO carddetails(ID, BANKID, RECEIPT, AMOUNT,OTHERCHARGES , transactionid , accountid , PAYMENTFLAG) VALUES (?, ?, ?, ?,?,?,?,?)", SerializerWriteParams.INSTANCE);
                                        if( p.getTrack2()!=null){
                                           cardDetailsInsert.exec(new DataParams() {

                                               @Override
                                               public void writeValues() throws BasicException {
                                                   setString(1, UUID.randomUUID().toString());
                                                   setString(2, p.getTrack2());
                                                   setString(3, ticket.getReceiptRef());
                                                   setDouble(4, p.getTotal());
                                                   setDouble(5,p.getOtherCharges());
                                                   setString(6, p.getTrack3());
                                                   setString(7, p.getTrack1());
                                                   setInt(8, 1);
                                               }
                                           });
                                       }



                                    }   
                                    else{
                                        SentenceExec cardDetailsInsert = new PreparedSentence(s, "INSERT INTO carddetails(ID, BANKID, RECEIPT, AMOUNT,OTHERCHARGES , transactionid , accountid) VALUES (?, ?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                                       if( p.getTrack2()!=null){
                                           cardDetailsInsert.exec(new DataParams() {

                                               @Override
                                               public void writeValues() throws BasicException {
                                                   setString(1, UUID.randomUUID().toString());
                                                   setString(2, p.getTrack2());
                                                   setString(3, ticket.getReceiptRef());
                                                   setDouble(4, p.getTotal());
                                                   setDouble(5,p.getOtherCharges());
                                                   setString(6, p.getTrack3());
                                                   setString(7, p.getTrack1());
                                               }
                                           });
                                       }
                                    }
                                
                            
                            }
                        
                        
                        
                        
                    }
                    //removed and moved to bill
                  /*  SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                    if (ticket.getTaxes() != null) {
                        for (final TicketTaxInfo tickettax : ticket.getTaxes()) {
                            taxlinesinsert.exec(new DataParams() {

                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, ticket.getReceiptRef());
                                    setString(3, tickettax.getTaxInfo().getId());
                                    setDouble(4, tickettax.getSubTotal());
                                    setDouble(5, tickettax.getTax());
                                }
                            });
                        }
                    }*/
                }//else{
                //  JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                //}
                return null;
            }
            //  }
        };
        t.execute();
        //  if(flag1==0)
        //  return true;
        // else
        //    return false;
        if (perror == false && flag1 == 0) {
            return receiptno;
        } else {
            return "false";
        }
    }
    
    
    // ****************************************** EDITED BY AAKASH *************************** FOR GUEST TAX ADDITION
    
    public final String payaccount2(final BillInfo ticket, final String location, final boolean flag , final String TaxCatid , final String TaxCatid2, final String TaxCatid3, final Double BaseAmount , final Double TaxAmount ) throws BasicException {
        flag1 = 0;
        receiptno = null;
        perror = false;
        Transaction t = new Transaction(s) {
            //  boolean error=false;

            public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(userid);
                if (obj != null) {
                    if (obj[0] != null) {
                        Date d = (Date) obj[0];
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTimeInMillis(date.getTime());
                        cal2.setTimeInMillis(d.getTime());
                        if (cal1.before(cal2)) {
                            JOptionPane.showMessageDialog(null, "Bill Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                            perror = true;

                        }
                    }
                }
                if (perror == false) {
                    if (ticket.getReceiptRef() == null) {
                        final String rno = getNextReceiptID1(ticket.getCreatedBy());
                        receiptno = rno;
                        ticket.setReceiptRef(rno);
                        if (rno == null) {
                            flag1 = 1;
                            return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                    }
                    // new receipt
                    if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, ticket.getPlace());//contains description for general receipts
                            }
                        });
                    } else {
                        new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                            }
                        });
                    }
                    
                    SentenceExec paymentinsert = new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                    for (final PaymentInfo p : ticket.getPayments()) {
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                setString(3, p.getName());
                                setDouble(4, p.getTotal());
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });

                        /*     if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                        getDebtUpdate().exec(new Object[]{
                        ticket.getCustomer().getId(),
                        new Double(p.getTotal()),
                        date
                        });
                        }*/

                        if ("cheque".equals(p.getName())) {
                            SentenceExec chequeInsert = new PreparedSentence(s, "INSERT INTO CHEQUE(ID, CHEQUENO, BANK,RNO,HOLDER,AMOUNT) VALUES (?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                            final ChequeDetails cd = p.getChequeDetails();
                            chequeInsert.exec(new DataParams() {

                                @Override
                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, cd.getChequeno());
                                    setString(3, cd.getBank());
                                    setString(4, ticket.getReceiptRef());
                                    setString(5, cd.getholder());
                                    setDouble(6, cd.getAmount());
                                }
                            });
                            List<BankInfo> temp = getBankList().list();
                            boolean result = false;
                            for (BankInfo b : temp) {
                                if ((cd.getBank().equals(b.getName()))) {
                                    result = true;
                                    break;
                                }
                            }
                            if (!result) {
                                SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                bankInsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getBank());
                                    }
                                });
                            }
                        }   
                        
                        
                        // IF PAYMENT TYPE IS CARD      ## BY AAKASH
                            if ("magcard".equals(p.getName())) {
                            
                                    SentenceExec cardDetailsInsert = new PreparedSentence(s, "INSERT INTO carddetails(ID, BANKID, RECEIPT, AMOUNT,OTHERCHARGES , transactionid , accountid) VALUES (?, ?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                                      if( p.getTrack2()!=null){
                                          cardDetailsInsert.exec(new DataParams() {

                                            @Override
                                            public void writeValues() throws BasicException {
                                                   setString(1, UUID.randomUUID().toString());
                                                   setString(2, p.getTrack2());
                                                   setString(3, ticket.getReceiptRef());
                                                   setDouble(4, p.getTotal());
                                                   setDouble(5,p.getOtherCharges());
                                                   setString(6, p.getTrack3());
                                                   setString(7, p.getTrack1());
                                             }
                                           });
                                       }
                                    
                                
                            
                            }
                        
                        
                        
                        
                        
                        
                        
                    }
                    //removed and moved to bill
                  if(TaxAmount>0){  
                    
                   SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT,TAXID2,TAXID3)  VALUES (?, ?, ?, ?, ?,?,?)", SerializerWriteParams.INSTANCE);
                    taxlinesinsert.exec(new DataParams() {

                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, ticket.getReceiptRef());
                                    setString(3, TaxCatid);
                                    setDouble(4, BaseAmount);
                                    setDouble(5, TaxAmount);
                                    setString(6, TaxCatid2);
                                    setString(7, TaxCatid3);
                                }
                            });
                  }
                    
                }//else{
                //  JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                //}
                return null;
            }
            //  }
        };
        t.execute();
        //  if(flag1==0)
        //  return true;
        // else
        //    return false;
        if (perror == false && flag1 == 0) {
            return receiptno;
        } else {
            return "false";
        }
    }
    
    
 // ************************************** END *********************** END ************************************************   
    
    
    
    
    
    
    
    
    private int flag1 = 0;

    public final void updatetosendMsg(String msg, String memid, String mobile, int priority) throws BasicException {
        try {
            String id = UUID.randomUUID().toString();
            new PreparedSentence(s, "INSERT INTO AUTOMSG(ID,MESSAGE,SENTDATE,MEMID) VALUES (?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{id, msg, new Date(), memid});
            new PreparedSentence(s, "INSERT INTO activemsgtable(ID,Message,SENDTO,PRIORITY,CNT) VALUES (?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT})).exec(new Object[]{id, msg, mobile, priority, 0});

            ComputePi.setvalue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    // } catch (RemoteException ex) {
    //   ex.printStackTrace();
    // }
    }

    public final boolean payDebtBill(final BillInfo ticket) throws BasicException {
        final Date date = new Date();
        if (ticket.getReceiptRef() == null) {
            String rno = getNextReceiptID1(ticket.getCreatedBy());
            ticket.setReceiptRef(rno);
            if (rno.equals("")) {
                flag1 = 1;
                return false;
            }
        }
        new PreparedSentence(s, "INSERT INTO DEBTRECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

            public void writeValues() throws BasicException {
                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                // setString(2, ticket.getActiveCash());
                setTimestamp(2, date); //Receipt date could be different from bill date
                setString(3, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
            }
        });
        ticket.setPaid(true);
        boolean berror1 = LookupUtilityImpl.getInstance(null).getDataLogicBill().markBillAsPaid(ticket);
        if (berror1 == false) {
            berror = true;
            throw new BasicException();
        } else {
            return true;
        }
    }

    public final boolean payBill(final BillInfo ticket, final String location) throws Exception {
        flag1 = 0;
        berror = false;
        Transaction t = new Transaction(s) {

            public Object transact() throws BasicException {
                Date date1 = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(date1.getTime());
                cal.add(Calendar.MINUTE, 10);
                Calendar bcal = Calendar.getInstance();
                bcal.setTimeInMillis(ticket.getCreatedDate().getTime());
                if (bcal.after(cal)) {
                    JOptionPane.showMessageDialog(null, "Present Time is less than billed time", "Error-Cannot Create Receipt", JOptionPane.OK_OPTION);
                    berror = true;
                }
                final Date date = new Date();
                AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                if (berror == false) {
                    Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                JOptionPane.showMessageDialog(null, "Present Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                                berror = true;

                            }
                        }
                    }
                }
                if (berror == false) {
                    if (ticket.getReceiptRef() == null) {
                        String rno = getNextReceiptID1(ticket.getCreatedBy());
                        ticket.setReceiptRef(rno);
                        if (rno.equals("")) {
                            flag1 = 1;
                            return false;
                        }
                    }
                    // new receipt
                    new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                        public void writeValues() throws BasicException {
                            setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                            // setString(2, ticket.getActiveCash());
                            setTimestamp(2, date); //Receipt date could be different from bill date
                            setString(3, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                        }
                    });

                    // new ticket
                    ticket.setPaid(true);
                    boolean berror1 = LookupUtilityImpl.getInstance(null).getDataLogicBill().markBillAsPaid(ticket);
                    if (berror1 == false) {
                        berror = true;
                        throw new BasicException();
                    } else {
                        SentenceExec paymentinsert = new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        for (final PaymentInfo p : ticket.getPayments()) {
                            paymentinsert.exec(new DataParams() {
                                
                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, ticket.getReceiptRef());
                                    setString(3, p.getName());
                                    //setDouble(4, (p.getTotal()-Billpage.interest1));
                                    setDouble(4, (BillInfo.stottax));
                                    //setDouble(4, (p.getTotal()));
                                    setString(5, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                                    setTimestamp(6, date);
                                    setString(7, ticket.getCustomerId());
                                }
                            });

                            /*   if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                            getDebtUpdate().exec(new Object[]{
                            ticket.getCustomer().getId(),
                            new Double(p.getTotal()),
                            date
                            });
                            }*/
                            /*  try{
                            if("debt".equals(p.getName())){
                            String str=  String.valueOf(new PreparedSentence(s
                            , "SELECT MOBILE FROM CUSTOMERS WHERE ID=?"
                            , SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(ticket.getCustomerId()));
                            String msg="Dear Member,\rYour a/c with us has been debited by "+Formats.CURRENCY.formatValue(p.getTotal())+" for bar usage.Bill no "+ticket.getID();
                            if(str!=null && str.length()==10)
                            updatetosendMsg(msg, ticket.getCustomerId(), str, 2);
                            }
                            }catch(Exception e){
                            }*/
                            if ("cheque".equals(p.getName())) {
                                SentenceExec chequeInsert = new PreparedSentence(s, "INSERT INTO CHEQUE(ID, CHEQUENO, BANK, RNO,HOLDER,AMOUNT) VALUES (?, ?, ?, ?,?,?)", SerializerWriteParams.INSTANCE);
                                final ChequeDetails cd = p.getChequeDetails();
                                chequeInsert.exec(new DataParams() {

                                    @Override
                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getChequeno());
                                        setString(3, cd.getBank());
                                        setString(4, ticket.getReceiptRef());
                                        setString(5, cd.getholder());
                                        setDouble(6, cd.getAmount());
                                    }
                                });
                                List<BankInfo> temp = getBankList().list();
                                boolean result = false;
                                for (BankInfo b : temp) {
                                    if ((cd.getBank().equals(b.getName()))) {
                                        result = true;
                                        break;
                                    }
                                }
                                if (!result) {
                                    SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                    bankInsert.exec(new DataParams() {

                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, cd.getBank());
                                        }
                                    });
                                }
                            }
                            
                            // IF PAYMENT TYPE IS CARD      ## BY AAKASH
                            if ("magcard".equals(p.getName())) {
                            
                            SentenceExec cardDetailsInsert = new PreparedSentence(s, "INSERT INTO carddetails(ID, BANKID, RECEIPT, AMOUNT,OTHERCHARGES , transactionid , accountid) VALUES (?, ?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                                if( p.getTrack2()!=null){
                                    cardDetailsInsert.exec(new DataParams() {

                                        @Override
                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, p.getTrack2());
                                            setString(3, ticket.getReceiptRef());
                                            setDouble(4, p.getTotal());
                                            setDouble(5,p.getOtherCharges());
                                            setString(6, p.getTrack3());
                                            setString(7, p.getTrack1());
                                        }
                                    });
                                }
                            }
                            
                            
                            
                        }
                        //removed and moved to bill
                    /*    SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        if (ticket.getTaxes() != null) {
                            for (final TicketTaxInfo tickettax : ticket.getTaxes()) {
                                taxlinesinsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, ticket.getReceiptRef());
                                        setString(3, tickettax.getTaxInfo().getId());
                                        setDouble(4, tickettax.getSubTotal());
                                        setDouble(5, tickettax.getTax());
                                    }
                                });
                            }
                        }*/
                    }
                }
                return null;
            //}
            }
        };
        t.execute();
        if (flag1 == 0 && berror == false) {
            return true;
        } else {
            return false;
        }
    }

    public final void saveTicket(final TicketInfo ticket, final String location) throws Exception {

        Transaction t = new Transaction(s) {

            public Object transact() throws BasicException {

                // Set Receipt Id
                if (ticket.getTicketId() == 0) {
                    ticket.setTicketId(getNextTicketIndex().intValue());
                }

                // new ticket
                new PreparedSentence(s, "INSERT INTO TICKETS (ID, TICKETID, PERSON, CUSTOMER, WAITER, PLACE, FLOOR) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                    public void writeValues() throws BasicException {
                        setString(1, ticket.getId());
                        setInt(2, ticket.getTicketId());
                        setString(3, ticket.getUser().getId());
                        setString(4, ticket.getCustomerId());
                        setString(5, ticket.getWaiterId());
                        setString(6, ticket.getPlaceId());
                        setString(7, ticket.getFloorId());
                    }
                });

                SentenceExec ticketlineinsert = new PreparedSentence(s, "INSERT INTO TICKETLINES (TICKET, LINE, PRODUCT, UNITS, PRICE, TAXID, ATTRIBUTES) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteBuilder.INSTANCE);

                for (TicketLineInfo l : ticket.getLines()) {
                    ticketlineinsert.exec(l);
                    if (l.getProductID() != null) {
                        // TODO update the stock from QT/Bill
//                        getStockDiaryInsert().exec(new Object[] {
//                            UUID.randomUUID().toString(),
//                            ticket.getDate(),
//                            l.getMultiply() < 0.0
//                                ? MovementReason.IN_REFUND.getKey()
//                                : MovementReason.OUT_SALE.getKey(),
//                            location,
//                            l.getProductID(),
//                            new Double(-l.getMultiply()),
//                            new Double(l.getPrice())
//                        });
                    }
                }

                return null;
            }
        };
        t.execute();
    }

    public final void deleteTicket(final TicketInfo ticket, final String location) throws Exception {

        Transaction t = new Transaction(s) {

            public Object transact() throws BasicException {

                // update the inventory
                Date d = new Date();
                for (int i = 0; i < ticket.getLinesCount(); i++) {
                    if (ticket.getLine(i).getProductID() != null) {
                        // Hay que actualizar el stock si el hay producto                              
                        getStockDiaryInsert().exec(new Object[]{
                                    UUID.randomUUID().toString(),
                                    d,
                                    ticket.getLine(i).getMultiply() >= 0.0
                                    ? MovementReason.IN_REFUND.getKey()
                                    : MovementReason.OUT_SALE.getKey(),
                                    location,
                                    ticket.getLine(i).getProductID(),
                                    new Double(ticket.getLine(i).getMultiply()),
                                    new Double(ticket.getLine(i).getPrice())
                                });
                    }
                }

                // update customer debts
              /*  for (PaymentInfo p : ticket.getPayments()) {
                if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                getDebtUpdate().exec(new Object[]{
                ticket.getCustomer().getId(),
                new Double(-p.getTotal()),
                ticket.getDate()
                });
                }
                }*/

                // and delete the receipt
                new StaticSentence(s, "DELETE FROM PAYMENTS WHERE RECEIPT = ?", SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s, "DELETE FROM TICKETLINES WHERE TICKET = ?", SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s, "DELETE FROM TICKETS WHERE ID = ?", SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s, "DELETE FROM RECEIPTS WHERE ID = ?", SerializerWriteString.INSTANCE).exec(ticket.getId());
                return null;
            }
        };
        t.execute();

    }
    //praveen:initiator changes--start

    public String getInitiator(String cid, String counter) throws BasicException {
        String ini = null;
        Object[] obj = (Object[]) new StaticSentence(s, "select initiator from sharedtickets where cid=? and counter=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{cid, counter});
        if (obj != null) {
            ini = String.valueOf(obj[0]);
        }
        return ini;

    }
    //praveen:initiator changes---end

    public abstract Integer getNextTicketIndex() throws BasicException;

    public abstract Integer getNextQTicketIndex() throws BasicException;

    public abstract Integer getNextBillNumberIndex() throws BasicException;

    public abstract Integer getNextReceiptNumberIndex() throws BasicException;

    public abstract Integer getNextTransNumberIndex() throws BasicException;

    public abstract SentenceList getProductCatQBF();

    public final SentenceExec getProductCatInsert() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                //praveen:added location and inventrymaintain field
                //sameer:added 4 new cols nad inserting values in products table
                int i = new PreparedSentence(s, "INSERT INTO PRODUCTS (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, IMAGE, HSN_Code, STOCKVOLUME, ATTRIBUTES, PRCATEGORY,UNITTYPE,PACCOUNT,SACCOUNT,LOCATION,INVENTRYMAINTAIN,MAXSTOCKLEVEL,MINSTOCKLEVEL,REORDERLEVEL,REORDERQUANTITY , TAXCAT2 , TAXCAT3) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26,27,28})).exec(params);
                int x = new PreparedSentence(s, "INSERT INTO PDT_PRCAT (ID,PRCAT,CATEGORY) VALUES (?,?,?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 16, 20})).exec(params);
                if (i > 0 && ((Boolean) values[13]).booleanValue()) {
                    return new PreparedSentence(s, "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 14})).exec(params);
                } else {
                    return i;
                }
            }
        };
    }

    public final SentenceExec getProductCatUpdate() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                int i = new PreparedSentence(s, "UPDATE PRODUCTS SET ID = ?, REFERENCE = ?, CODE = ?, NAME = ?, ISCOM = ?, ISSCALE = ?, PRICEBUY = ?, PRICESELL = ?, CATEGORY = ?, TAXCAT = ?, IMAGE = ?, HSN_Code = ?, STOCKVOLUME = ?, ATTRIBUTES = ?, PRCATEGORY =?, UNITTYPE =?, PACCOUNT=?, SACCOUNT=?, LOCATION=?,INVENTRYMAINTAIN=?,MAXSTOCKLEVEL=?,MINSTOCKLEVEL=?,REORDERQUANTITY=?,REORDERLEVEL=? , TAXCAT2=? , TAXCAT3=? , BASIC2=? , BASIC3=? WHERE ID = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26,27,28,29,30, 0})).exec(params);
                if (i > 0) {
                    if (new PreparedSentence(s, "UPDATE PDT_PRCAT SET CATEGORY = ? WHERE ID = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{20, 0})).exec(params) == 0) {
                        new PreparedSentence(s, "INSERT INTO PDT_PRCAT (ID,PRCAT, CATEGORY) VALUES (?, ?,?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 16, 20})).exec(params);
                    }
                    if (((Boolean) values[13]).booleanValue()) {

//                        if (new PreparedSentence(s, "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{14, 0})).exec(params) == 0) {
//                            new PreparedSentence(s, "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 14})).exec(params);
//                        }
                        if (new PreparedSentence(s, "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?", new SerializerWriteBasicExt(null, new int[]{14, 0})).exec(params) == 0) {
                            new PreparedSentence(s, "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)", new SerializerWriteBasicExt(null, new int[]{0, 14})).exec(params);
                        }
                    } else {
                        new PreparedSentence(s, "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{0})).exec(params);
                    /*   new PreparedSentence(s
                    , "DELETE FROM PDT_PRCAT WHERE ID = ?"
                    , new SerializerWriteBasicExt(productcatDatas, new int[] {0})).exec(params);*/
                    }
                }
                return i;
            }
        };
    }

     

    public final SentenceExec getProductCatDelete() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s, "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{0})).exec(params);
                new PreparedSentence(s, "DELETE FROM PDT_PRCAT WHERE ID = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{0})).exec(params);
                return new PreparedSentence(s, "DELETE FROM PRODUCTS WHERE ID = ?", new SerializerWriteBasicExt(productcatDatas, new int[]{0})).exec(params);
            }
        };
    }
    
    //added by pratima:to send request in products_approval table on add/edit of product
     public final ProductInfoExt getEditProductInfo(String id) throws BasicException {

        
        return (ProductInfoExt) new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +                                                                                             // edited by aakash 
                "FROM PRODUCTS_APPROVAL P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(id);
    }
 
public final SentenceExec getProductCatUpdateApprove() {
   
            return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                final String  prId=values[0].toString();
                int i=  new PreparedSentence(s, "INSERT INTO PRODUCTS_APPROVAL (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, IMAGE, HSN_Code, ATTRIBUTES, PRCATEGORY,UNITTYPE,PACCOUNT,SACCOUNT,LOCATION,INVENTRYMAINTAIN,MAXSTOCKLEVEL,MINSTOCKLEVEL,REORDERLEVEL,REORDERQUANTITY , TAXCAT2 , TAXCAT3,catalog_flag) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26,27,28,13})).exec(params);
               //field stockvolume is not added bcz it was not inserting in products table also.
                try{
                      
                    Transaction t = new Transaction(s) {
                    @Override
                    protected Object transact() throws BasicException { 
                        
                        Object[] param1 = new Object[]{UUID.randomUUID().toString(),LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(),LookupUtilityImpl.getInstance(null).getAppView().getProperties().getHost(),prId,"edit",prId};
                new PreparedSentence(s, "update PRODUCTS_APPROVAL set ID=?,CR_BY=?,CR_HOST=?,pr_id=?,requestType=? where ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING, Datas.STRING })).exec(param1);
                return null;
                           }};
                        t.execute();
                }catch(BasicException e){ 
                System.out.println(e);
                }
              if(i>0){
                   JOptionPane.showMessageDialog(null, "Request sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
              }
                    return i;
            }
        };
            
    }
public final SentenceExec getProductCatInsertApprove() {
   
            return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                final String  prId=values[0].toString();
//                int i=  new PreparedSentence(s, "INSERT INTO PRODUCTS_APPROVAL (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, IMAGE, HSN_Code, STOCKVOLUME, ATTRIBUTES, PRCATEGORY,UNITTYPE,PACCOUNT,SACCOUNT,LOCATION,INVENTRYMAINTAIN,MAXSTOCKLEVEL,MINSTOCKLEVEL,REORDERLEVEL,REORDERQUANTITY , TAXCAT2 , TAXCAT3) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26,27,28})).exec(params);
               int i=  new PreparedSentence(s, "INSERT INTO PRODUCTS_APPROVAL (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, IMAGE, HSN_Code, STOCKVOLUME, ATTRIBUTES, PRCATEGORY,UNITTYPE,PACCOUNT,SACCOUNT,LOCATION,INVENTRYMAINTAIN,MAXSTOCKLEVEL,MINSTOCKLEVEL,REORDERLEVEL,REORDERQUANTITY , TAXCAT2 , TAXCAT3,Catalog_flag) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26,27,28,13})).exec(params);
                try{
                      
                    Transaction t = new Transaction(s) {
                    @Override
                    protected Object transact() throws BasicException { 
                        
                        Object[] param1 = new Object[]{UUID.randomUUID().toString(),LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(),LookupUtilityImpl.getInstance(null).getAppView().getProperties().getHost(),prId,"new",prId};
                new PreparedSentence(s, "update PRODUCTS_APPROVAL set ID=?,CR_BY=?,CR_HOST=?,pr_id=?,requestType=? where ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING, Datas.STRING })).exec(param1);
                return null;
                           }};
                        t.execute();
                }catch(BasicException e){ 
                System.out.println(e);
                }
              if(i>0){
                   JOptionPane.showMessageDialog(null, "Request sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
              }
                    return i;
            }
        };
    }//ended by pratima


    /*   public final SentenceExec getDebtUpdate() {

    return new PreparedSentence(s
    , "UPDATE MEMDEBTTABLE SET " +
    " CURDEBT = CASE WHEN (COALESCE(CURDEBT, 0) + ?) <= 0 THEN NULL ELSE (COALESCE(CURDEBT, 0) + ?) END, " +
    " CURDATE = CASE WHEN (COALESCE(CURDEBT, 0) + ?) <= 0 THEN NULL WHEN CURDATE IS NULL THEN ? ELSE CURDATE END " +
    " WHERE ID = ?"
    , new SerializerWriteBasicExt(new Datas[] {Datas.STRING, Datas.DOUBLE, Datas.TIMESTAMP}, new int[]{1, 1, 1, 2, 0}));
    } */
    public final SentenceExec getStockDiaryInsert() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                /*  if (new PreparedSentence(s
                , "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ?"
                , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {5, 3, 4})).exec(params) == 0) {
                new PreparedSentence(s
                , "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, UNITS) VALUES (?, ?, ?)"
                , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {3, 4, 5})).exec(params);
                }*/
                //praveen:added to update stockcurrent
                new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdiaryDatas, new int[]{5, 3, 4})).exec(params);
                return new PreparedSentence(s, "INSERT INTO STOCKDIARY (ID, DATENEW, REASON, LOCATION, PRODUCT, UNITS, PRICE,REASON1, LOCATION1, PRODUCT1, UNITS1, PRICE1,NUM,CREATEDBY) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(stockdiaryDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})).exec(params);
            }
        };
    }
 public final SentenceExec getStockDiaryInsert1() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
               //int i= new PreparedSentence(s, "SELECT PRODUCT  FROM STOCKCURRENT WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdiaryDatas2, new int[]{1, 2})).exec(params);
                //if(i>0){
               if (new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdiaryDatas3, new int[]{5, 3, 4})).exec(params) == 0) {
                    new PreparedSentence(s, "INSERT INTO STOCKCURRENT (UNITS,LOCATION, PRODUCT) VALUES (?, ?, ?)", new SerializerWriteBasicExt(stockdiaryDatas3, new int[]{5, 3, 4})).exec(params);
                }
                return new PreparedSentence(s, "INSERT INTO STOCKDIARY (ID, DATENEW, REASON1, LOCATION1, PRODUCT1, UNITS1, PRICE1,RNO,CREATEDBY) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)", new SerializerWriteBasicExt(stockdiaryDatas1, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})).exec(params);
           
            }

        };
    }
    public Object getStockVolume(String ID) throws BasicException {
        return new PreparedSentence(s,
                "SELECT UNITS FROM STOCKCURRENT WHERE PRODUCT=?",
                SerializerWriteString.INSTANCE,
                SerializerReadString.INSTANCE).find(ID);


    }

    public final SentenceExec getWaiterAdd() {
        return new StaticSentence(s, "UPDATE WAITER SET SHOW_ =TRUE WHERE ID=? ", SerializerWriteString.INSTANCE);
    }

    public final SentenceExec getWaiterDel() {
        return new StaticSentence(s, "UPDATE WAITER SET SHOW_ =FALSE WHERE ID=? ", SerializerWriteString.INSTANCE);
    }

    public void updateStockVolume(Double Volume, String ID) throws BasicException {

        /*  new PreparedSentence(s
        , "UPDATE PRODUCTS SET STOCKVOLUME = (STOCKVOLUME + ?) WHERE ID = ? AND STOCKVOLUME IS NOT NULL"
        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE, Datas.STRING }))
        .exec(new Object[] {Volume, ID});

        new PreparedSentence(s
        , "UPDATE PRODUCTS SET STOCKVOLUME = ? WHERE ID = ? AND STOCKVOLUME IS NULL"
        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE, Datas.STRING }))
        .exec(new Object[] {Volume, ID});

         */
    }

    public void updateStockVolume2(Double Volume, String ID, String catid) throws BasicException {
        if (new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE PRODUCT = ? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Volume, ID}) <= 0) {
            new PreparedSentence(s, "INSERT INTO STOCKCURRENT (LOCATION,PRODUCT,UNITS) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{catid, ID, Volume});
        }
    }

    public void updateStockVolume1(Double Volume, String ID) throws BasicException {
        new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Volume, ID});


    }

    public int updateStockVolume3(Double Volume, String ID) throws BasicException {
        return new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS - ?) WHERE PRODUCT = ? AND UNITS>=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.DOUBLE})).exec(new Object[]{Volume, ID, Volume});


    }

    public Object[] getlocationFromStkCurrent(String pdt) throws BasicException {
        Object obj[] = (Object[]) new StaticSentence(s, "SELECT LOCATION FROM STOCKCURRENT WHERE PRODUCT=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(pdt);
        return obj;
    }

    public final SentenceExec getStockDiaryInsertinpdt() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdiaryDatas3, new int[]{5, 3, 4})).exec(params) == 0) {
                    new PreparedSentence(s, "INSERT INTO STOCKCURRENT (UNITS,LOCATION, PRODUCT) VALUES (?, ?, ?)", new SerializerWriteBasicExt(stockdiaryDatas3, new int[]{5, 3, 4})).exec(params);
                }
                return new PreparedSentence(s, "INSERT INTO STOCKDIARY (ID, DATENEW, REASON1, LOCATION1, PRODUCT1, UNITS1, PRICE1,RNO,CREATEDBY) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)", new SerializerWriteBasicExt(stockdiaryDatas1, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})).exec(params);
            }
        };
    }

    public final SentenceExec getStockCurrentInsert() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdiaryDatas2, new int[]{0, 1, 2})).exec(params) == 0) {
                    new PreparedSentence(s, "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, UNITS) VALUES (?, ?, ?)", new SerializerWriteBasicExt(stockdiaryDatas2, new int[]{1, 2, 0})).exec(params);
                }
                // else
                return 1;

            }
        };
    }

    public final SentenceExec getStockDiaryDelete() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s, "UPDATE STOCKCURRENT SET UNITS = (UNITS - ?) WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdiaryDatas, new int[]{5, 3, 4})).exec(params) == 0) {
                    new PreparedSentence(s, "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, UNITS) VALUES (?, ?, -(?))", new SerializerWriteBasicExt(stockdiaryDatas, new int[]{3, 4, 5})).exec(params);
                }
                return new PreparedSentence(s, "DELETE FROM STOCKDIARY WHERE ID = ?", new SerializerWriteBasicExt(stockdiaryDatas, new int[]{0})).exec(params);
            }
        };
    }

    public final SentenceExec getPaymentMovementInsert() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
               // System.out.println("DATA LLLLLLLLLLLL:::::"+gflag2);
                new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?,  ?, ?)", new SerializerWriteBasicExt(paymenttabledatas, new int[]{0, 2, 7})).exec(params);
                return new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME) VALUES (?, ?, ?, ?, ?, ?)", new SerializerWriteBasicExt(paymenttabledatas, new int[]{3, 0, 4, 5, 7, 2})).exec(params);
            }
        };
    }

    public final SentenceExec getPaymentMovementDelete() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
               // System.out.println("DataLogicSales ::line 1582:::::");
                new PreparedSentence(s, "DELETE FROM PAYMENTS WHERE ID = ?", new SerializerWriteBasicExt(paymenttabledatas, new int[]{3})).exec(params);
                return new PreparedSentence(s, "DELETE FROM RECEIPTS WHERE ID = ?", new SerializerWriteBasicExt(paymenttabledatas, new int[]{0})).exec(params);
            }
        };
    }

    public final double findProductStock(String id, String warehouse) throws BasicException {
        PreparedSentence p = new PreparedSentence(s, "SELECT UNITS FROM STOCKCURRENT WHERE PRODUCT=? AND LOCATION=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadDouble.INSTANCE);
        Double d = (Double) p.find(new Object[]{id, warehouse});
        return d == null ? 0.0 : d.doubleValue();
    }

    public final SentenceList getProductStock() {
        return new PreparedSentence(s, "SELECT L.ID, L.NAME, ?, COALESCE(S.UNITS, 0.0), S.STOCKSECURITY, S.STOCKMAXIMUM " +
                "FROM LOCATIONS L LEFT OUTER JOIN (" +
                "SELECT PRODUCT, LOCATION, STOCKSECURITY, STOCKMAXIMUM, UNITS FROM STOCKCURRENT WHERE PRODUCT = ?) S " +
                "ON L.ID = S.LOCATION", new SerializerWriteBasicExt(productcatDatas, new int[]{0, 0}), new SerializerReadBasic(stockdatas));
    }

    public final SentenceExec getStockUpdate() {
        return new SentenceExecTransaction(s) {

            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s, "UPDATE STOCKCURRENT SET STOCKSECURITY = ?, STOCKMAXIMUM = ? WHERE LOCATION = ? AND PRODUCT = ?", new SerializerWriteBasicExt(stockdatas, new int[]{4, 5, 0, 2})).exec(params) == 0) {
                    return new PreparedSentence(s, "INSERT INTO STOCKCURRENT(LOCATION, PRODUCT, UNITS, STOCKSECURITY, STOCKMAXIMUM) VALUES (?, ?, 0.0, ?, ?)", new SerializerWriteBasicExt(stockdatas, new int[]{0, 2, 4, 5})).exec(params);
                } else {
                    return 1;
                }
            }
        };
    }

    public final SentenceExec getCatalogCategoryAdd() {
        return new StaticSentence(s, "INSERT INTO PRODUCTS_CAT(PRODUCT, CATORDER) SELECT ID, NULL FROM PRODUCTS WHERE CATEGORY = ?", SerializerWriteString.INSTANCE);
    }

    public final SentenceExec getCatalogCategoryDel() {
        return new StaticSentence(s, "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ANY (SELECT ID FROM PRODUCTS WHERE CATEGORY = ?)", SerializerWriteString.INSTANCE);
    }

    public final TableDefinition getTableCategories() {
        return new TableDefinition(s,
                "CATEGORIES", new String[]{"ID", "NAME", "PARENTID", "IMAGE", "PACCOUNT", "SACCOUNT", "LOCATION"}, new String[]{"ID", AppLocal.getIntString("Label.Name"), "", AppLocal.getIntString("label.image"), "PACCOUNT", "SACCOUNT", "LOCATION"}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.STRING, Datas.STRING, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.NULL, Formats.STRING, Formats.STRING, Formats.STRING}, new int[]{0});
    }

    public final TableDefinition getTableTaxes() {
        return new TableDefinition(s,
                "TAXES", new String[]{"ID", "NAME", "CATEGORY", "CUSTCATEGORY", "PARENTID", "RATE", "RATECASCADE", "RATEORDER"}, new String[]{"ID", AppLocal.getIntString("Label.Name"), AppLocal.getIntString("label.taxcategory"), AppLocal.getIntString("label.custtaxcategory"), AppLocal.getIntString("label.taxparent"), AppLocal.getIntString("label.dutyrate"), AppLocal.getIntString("label.cascade"), AppLocal.getIntString("label.order")}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.PERCENT, Formats.BOOLEAN, Formats.INT}, new int[]{0});
    }

    public final TableDefinition getTableTaxCustCategories() {
        return new TableDefinition(s,
                "TAXCUSTCATEGORIES", new String[]{"ID", "NAME"}, new String[]{"ID", AppLocal.getIntString("Label.Name")}, new Datas[]{Datas.STRING, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING}, new int[]{0});
    }

    public final TableDefinition getTableTaxCategories() {
        return new TableDefinition(s,
                "TAXCATEGORIES", new String[]{"ID", "NAME", "ACCOUNT"}, new String[]{"ID", AppLocal.getIntString("Label.Name"), "ACCOUNT"}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING}, new int[]{0});
    }

    public final TableDefinition getTablePrCategories() {
        return new TableDefinition(s,
                "PRCATEGORIES", new String[]{"ID", "NAME", "PARENTID", "IMAGE", "PRINTER"}, new String[]{"ID", AppLocal.getIntString("Label.Name"), "", AppLocal.getIntString("label.image"), AppLocal.getIntString("label.printer")}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.NULL, Formats.STRING}, new int[]{0});
    }

    public final TableDefinition getWaiterCategories() {
        return new TableDefinition(s,
                "WAITER", new String[]{"ID", "NAME", "COUNTER", "CARDNO" , "CASHIER" , "ISSTEWARD" , "QT_OPT"}, new String[]{"ID", AppLocal.getIntString("Label.name"), "COUNTER", "CARDNO" , "CASHIER" , "ISSTEWARD" , "QT_OPT"}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING , Datas.STRING , Datas.INT , Datas.INT}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING , Formats.STRING , Formats.INT , Formats.INT}, new int[]{0});
    }

    public final TableDefinition getTableLocations() {
        return new TableDefinition(s,
                "LOCATIONS", new String[]{"ID", "NAME", "ADDRESS", "RDISPLAYNAME", "PARENT", "STOCK", "PRIMARYPARENT", "SALESACCOUNT", "CUSTOMERCURRENTACCOUNT", "LEVYACCOUNT", "LEVYVALUE", "FACILITY","DECIMALALLOWED","prefix"}, new String[]{"ID", AppLocal.getIntString("label.locationname"), AppLocal.getIntString("label.locationaddress"), "RDISPLAYNAME", "PARENT", "STOCK", "PRIMARYPARENT", "SALESACCOUNT", "CUSTOMERCURRENTACCOUNT", "LEVYACCOUNT", "LEVYVALUE", "FACILITY","DECIMALALLOWED","prefix"}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.BOOLEAN, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.BOOLEAN, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.INT, Formats.STRING, Formats.BOOLEAN, Formats.STRING}, new int[]{0});
    }

    public final Object[] getCategoryAccounts(String id) throws BasicException {
        return (Object[]) new StaticSentence(s, "SELECT PACCOUNT,SACCOUNT FROM CATEGORIES WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(id);
    }

    public final void updateProductsaccAll(String catid, Object accid) throws BasicException {
        new PreparedSentence(s, "UPDATE PRODUCTS SET PACCOUNT=? WHERE CATEGORY = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid});
    }

    public final void updateProductsaccSpecific(String catid, String accid, Object paccid) throws BasicException {
        new PreparedSentence(s, "UPDATE PRODUCTS SET PACCOUNT=? WHERE CATEGORY = ? AND PACCOUNT=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid, paccid});
    }

    public final void updateSubCataegorysaccAll(String catid, Object accid) throws BasicException {
        new PreparedSentence(s, "UPDATE CATEGORIES SET PACCOUNT=? WHERE PARENTID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid});
    }

    public final void updateSubCataegorysaccSpecific(String catid, String accid, Object paccid) throws BasicException {
        new PreparedSentence(s, "UPDATE CATEGORIES SET PACCOUNT=? WHERE PARENTID = ? AND PACCOUNT=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid, paccid});
    }

    public final void updateProductsaccAll1(String catid, Object accid) throws BasicException {
        new PreparedSentence(s, "UPDATE PRODUCTS SET SACCOUNT=? WHERE CATEGORY = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid});
    }

    public final void updateProductsaccSpecific1(String catid, String accid, Object paccid) throws BasicException {
        new PreparedSentence(s, "UPDATE PRODUCTS SET SACCOUNT=? WHERE CATEGORY = ? AND SACCOUNT=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid, paccid});
    }

    public final void updateSubCataegorysaccAll1(String catid, Object accid) throws BasicException {
        new PreparedSentence(s, "UPDATE CATEGORIES SET SACCOUNT=? WHERE PARENTID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid});
    }

    public final void updateSubCataegorysaccSpecific1(String catid, String accid, Object paccid) throws BasicException {
        new PreparedSentence(s, "UPDATE CATEGORIES SET SACCOUNT=? WHERE PARENTID = ? AND SACCOUNT=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{accid, catid, paccid});
    }

    protected static class CustomerExtRead implements SerializerRead {

        public Object readValues(DataRead dr) throws BasicException {
            CustomerInfoExt c = new CustomerInfoExt(dr.getString(1));
            c.setTaxid(dr.getString(2));
            c.setSearchkey(dr.getString(3));
            c.setName(dr.getString(4));
            c.setCard(dr.getString(5));
            c.setTaxCustomerID(dr.getString(6));
            c.setNotes(dr.getString(7));
            //  c.setMaxdebt(dr.getDouble(8));
            c.setVisible(dr.getBoolean(8).booleanValue());
            //   c.setCurdate(dr.getTimestamp(9));
            //    c.setCurdebt(dr.getDouble(10));
            //"MEMTYPE","FINGERPRINTDATA","SOWO"
            //, "MOBILE","SERVICETAXCAT","ENTTAXCAT","SIGNATURE","SPONSOR1","SPONSOR2","SPONSOR3"
            //, "JOINDATE","TERDATE","DOB"

            c.setMemType(dr.getString(9));

            c.setSoWo(dr.getString(10));
            c.setMobile(dr.getString(16));
            c.setSTax(dr.getString(11));
            c.setETax(dr.getString(12));

            c.setjDate(dr.getTimestamp(13));

            c.setDOB(dr.getTimestamp(14));
            c.setopeningbalance(dr.getDouble(15));
            c.setaccount(dr.getString(16));
            return c;
        }
    }

    public void addToPurchaseOrderFooterDetails(Object[] obj) throws BasicException {
        final String payment = obj[0].toString();
        final String deliveryDetails = obj[1].toString();
        final String taxlines = obj[2].toString();
        final String others = obj[3].toString();
        if (payment != null) {
            List<PaymentModeInfo> temp = getPaymentsList().list();
            boolean result = false;
            for (PaymentModeInfo b : temp) {
                if ((payment.equals(b.getName()))) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                //System.out.println("DATA LLLLLLLLLLLL:22::::"+gflag2);
                SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO PAYMENTDETAILS (ID, NAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                bankInsert.exec(new DataParams() {

                    public void writeValues() throws BasicException {
                        setString(1, UUID.randomUUID().toString());
                        setString(2, payment);
                    }
                });
            }
        }
        if (deliveryDetails != null) {
            List<DeliveryPeriodInfo> temp = getDeliveryPeriodList().list();
            boolean result = false;
            for (DeliveryPeriodInfo b : temp) {
                if ((deliveryDetails.equals(b.getName()))) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO DELIVERYPERIOD (ID, NAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                bankInsert.exec(new DataParams() {

                    public void writeValues() throws BasicException {
                        setString(1, UUID.randomUUID().toString());
                        setString(2, deliveryDetails);
                    }
                });
            }
        }
        if (taxlines != null) {
            List<TaxlineInfo> temp = getTaxlineList().list();
            boolean result = false;
            for (TaxlineInfo b : temp) {
                if ((taxlines.equals(b.getName()))) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO TAXDETAILS (ID, NAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                bankInsert.exec(new DataParams() {

                    public void writeValues() throws BasicException {
                        setString(1, UUID.randomUUID().toString());
                        setString(2, taxlines);
                    }
                });
            }
        }
        if (others != null) {
            List<OtherChargesInfo> temp = getOtherChargesList().list();
            boolean result = false;
            for (OtherChargesInfo b : temp) {
                if ((others.equals(b.getName()))) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO OTHERCHARGES(ID, NAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                bankInsert.exec(new DataParams() {

                    public void writeValues() throws BasicException {
                        setString(1, UUID.randomUUID().toString());
                        setString(2, others);
                    }
                });
            }
        }
    }
    
///////aaa    
    public final SentenceList getPeoplesRoleInfo(String id) {//in use
        return new StaticSentence(s, "SELECT NAME FROM PEOPLE WHERE ROLE = (SELECT ID FROM ROLES WHERE NAME = ?)", SerializerWriteString.INSTANCE, new SerializerReadClass(PeoplesRoleInfo.class));
    }
    
    public final SentenceList getRestRoleInfo(String id) {//in use
        return new StaticSentence(s, "SELECT RCOUNTERS FROM QTKASSIGN WHERE KNAME = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(PeoplesRoleInfo.class));
    }
    
    public final SentenceList getKitchenRoleInfo() {//in use
        return new StaticSentence(s, "SELECT NAME FROM ROLES WHERE NAME LIKE '%kitchen%'", null, new SerializerReadClass(PeoplesRoleInfo.class));
    }
    
    public final SentenceList getQtdMemInfo(String ct) {//in use
        return new StaticSentence(s, "SELECT distinct c.searchkey FROM qtkitchen_arv q join customers c where q.customer = c.id and q.counter = ? order by c.searchkey", SerializerWriteString.INSTANCE, new SerializerReadClass(PeoplesRoleInfo.class));
    }
    
    public final SentenceList getQtdWInfo(String ct) {//in use
        return new StaticSentence(s, "SELECT distinct w.name FROM qtkitchen_arv q join waiter w on q.waiter=w.id where q.counter = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(PeoplesRoleInfo.class));
    }
    
    public final SentenceList getRestAllInfo() {//in use
        return new StaticSentence(s, "SELECT KNAME FROM QTKASSIGN ", null, new SerializerReadClass(PeoplesRoleInfo.class));
    }
    
    public final SentenceList getRestWarehouse() {//in use
        return new StaticSentence(s, "select id from locations where name like '%restaurant%'", null, new SerializerReadClass(PeoplesRoleInfo.class));
    }
///////aaa    
    
//shiv    
    public final  java.util.List<ProductInfoExt> getProductCatalogs(String category, String rfc) throws BasicException {
     
        java.util.List<ProductInfoExt> acclist = new StaticSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME,\n" +
                                                                        " P.CATEGORY, P.IMAGE, P.ATTRIBUTES , P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,\n" +
                                                                        "P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , \n" +
                                                                        "(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) ,\n" +
                                                                        " (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3\n" +
                                                                        "FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID LEFT OUTER JOIN CATEGORIES C \n" +
                                                                        "ON P.CATEGORY = C.ID, PRODUCTS_CAT O WHERE P.ID = O.PRODUCT\n" +
                                                                        " AND c.parentid= ? and P.REFERENCE like ?  \n" +
                                                                        "ORDER BY P.REFERENCE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ProductInfoExt.class)).list(new Object[]{ category, rfc + "%"});
      return acclist;
    }
    
     public final   java.util.List<ProductInfoExt> getProductCatalog(String category, String code) throws BasicException {
        return new PreparedSentence(s, "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES , P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID LEFT OUTER JOIN CATEGORIES C ON P.CATEGORY = C.ID, PRODUCTS_CAT O WHERE P.ID = O.PRODUCT AND c.parentid =?  " +
                "ORDER BY P.REFERENCE", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).list(category);
    }
     ///end
//shiv ad
    
    
    //Added by guru for receipt changes
     
     public final boolean payAccount3(final BillInfo ticket, final String location) throws Exception {
        flag1 = 0;
        berror = false;
        Transaction t = new Transaction(s) {

            public Object transact() throws BasicException {
                Date date1 = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(date1.getTime());
                cal.add(Calendar.MINUTE, 10);
                Calendar bcal = Calendar.getInstance();
                bcal.setTimeInMillis(ticket.getCreatedDate().getTime());
                if (bcal.after(cal)) {
                    JOptionPane.showMessageDialog(null, "Present Time is less than billed time", "Error-Cannot Create Receipt", JOptionPane.OK_OPTION);
                    berror = true;
                }
                final Date date = new Date();
                AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                if (berror == false) {
                    Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                JOptionPane.showMessageDialog(null, "Present Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                                berror = true;

                            }
                        }
                    }
                }
                if (berror == false) {
                    if (ticket.getReceiptRef() == null) {
                        String rno = getNextReceiptID1(ticket.getCreatedBy());
                        ticket.setReceiptRef(rno);
                        if (rno.equals("")) {
                            flag1 = 1;
                            return false;
                        }
                    }
                    // new receipt
                    new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                        public void writeValues() throws BasicException {
                            setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                            // setString(2, ticket.getActiveCash());
                            setTimestamp(2, date); //Receipt date could be different from bill date
                            setString(3, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                        }
                    });

                    // new ticket
                    ticket.setPaid(true);
                    boolean berror1 = LookupUtilityImpl.getInstance(null).getDataLogicBill().markBillAsPaid(ticket);
                    if (berror1 == false) {
                        berror = true;
                        throw new BasicException();
                    } else {
                        SentenceExec paymentinsert = new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        for (final PaymentInfo p : ticket.getPayments()) {
                            paymentinsert.exec(new DataParams() {
                                
                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, ticket.getReceiptRef());
                                    setString(3, p.getName());
                                    //setDouble(4, (p.getTotal()-Billpage.interest1));
                                    //setDouble(4, (BillInfo.stottax));
                                    setDouble(4, (ticket.getTotal1()));
                                    setString(5, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                                    setTimestamp(6, date);
                                    setString(7, ticket.getCustomerId());
                                }
                            });

                            /*   if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                            getDebtUpdate().exec(new Object[]{
                            ticket.getCustomer().getId(),
                            new Double(p.getTotal()),
                            date
                            });
                            }*/
                            /*  try{
                            if("debt".equals(p.getName())){
                            String str=  String.valueOf(new PreparedSentence(s
                            , "SELECT MOBILE FROM CUSTOMERS WHERE ID=?"
                            , SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(ticket.getCustomerId()));
                            String msg="Dear Member,\rYour a/c with us has been debited by "+Formats.CURRENCY.formatValue(p.getTotal())+" for bar usage.Bill no "+ticket.getID();
                            if(str!=null && str.length()==10)
                            updatetosendMsg(msg, ticket.getCustomerId(), str, 2);
                            }
                            }catch(Exception e){
                            }*/
                            if ("cheque".equals(p.getName())) {
                                SentenceExec chequeInsert = new PreparedSentence(s, "INSERT INTO CHEQUE(ID, CHEQUENO, BANK, RNO,HOLDER,AMOUNT) VALUES (?, ?, ?, ?,?,?)", SerializerWriteParams.INSTANCE);
                                final ChequeDetails cd = p.getChequeDetails();
                                chequeInsert.exec(new DataParams() {

                                    @Override
                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getChequeno());
                                        setString(3, cd.getBank());
                                        setString(4, ticket.getReceiptRef());
                                        setString(5, cd.getholder());
                                        setDouble(6, ticket.getTotal1()+Billpage.interest1);
                                    }
                                });
                                List<BankInfo> temp = getBankList().list();
                                boolean result = false;
                                for (BankInfo b : temp) {
                                    if ((cd.getBank().equals(b.getName()))) {
                                        result = true;
                                        break;
                                    }
                                }
                                if (!result) {
                                    SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                    bankInsert.exec(new DataParams() {

                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, cd.getBank());
                                        }
                                    });
                                }
                            }
                            
                            // IF PAYMENT TYPE IS CARD      ## BY AAKASH
                            if ("magcard".equals(p.getName())) {
                            
                            SentenceExec cardDetailsInsert = new PreparedSentence(s, "INSERT INTO carddetails(ID, BANKID, RECEIPT, AMOUNT,OTHERCHARGES , transactionid , accountid) VALUES (?, ?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                                if( p.getTrack2()!=null){
                                    cardDetailsInsert.exec(new DataParams() {

                                        @Override
                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, p.getTrack2());
                                            setString(3, ticket.getReceiptRef());
                                            setDouble(4, ticket.getTotal1()+Billpage.interest1);
                                            setDouble(5,p.getOtherCharges());
                                            setString(6, p.getTrack3());
                                            setString(7, p.getTrack1());
                                        }
                                    });
                                }
                            }
                            
                            
                            
                        }
                        //removed and moved to bill
                    /*    SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        if (ticket.getTaxes() != null) {
                            for (final TicketTaxInfo tickettax : ticket.getTaxes()) {
                                taxlinesinsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, ticket.getReceiptRef());
                                        setString(3, tickettax.getTaxInfo().getId());
                                        setDouble(4, tickettax.getSubTotal());
                                        setDouble(5, tickettax.getTax());
                                    }
                                });
                            }
                        }*/
                    }
                }
                return null;
            //}
            }
        };
        t.execute();
        if (flag1 == 0 && berror == false) {
            return true;
        } else {
            return false;
        }
    }
/////////////////////
     ///Added By Ganesh
      public final boolean payAccount4(final BillInfo ticket, final String location) throws Exception {
        flag1 = 0;
        berror = false;
        Transaction t = new Transaction(s) {

            public Object transact() throws BasicException {
                Date date1 = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(date1.getTime());
                cal.add(Calendar.MINUTE, 10);
                Calendar bcal = Calendar.getInstance();
                bcal.setTimeInMillis(ticket.getCreatedDate().getTime());
                if (bcal.after(cal)) {
                    JOptionPane.showMessageDialog(null, "Present Time is less than billed time", "Error-Cannot Create Receipt", JOptionPane.OK_OPTION);
                    berror = true;
                }
                final Date date = new Date();
                AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                if (berror == false) {
                    Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                JOptionPane.showMessageDialog(null, "Present Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                                berror = true;

                            }
                        }
                    }
                }
                if (berror == false) {
                    if (ticket.getReceiptRef() == null) {
                        String rno = getNextReceiptID1(ticket.getCreatedBy());
                        ticket.setReceiptRef(rno);
                        if (rno.equals("")) {
                            flag1 = 1;
                            return false;
                        }
                    }
                    // new receipt
                    new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                        public void writeValues() throws BasicException {
                            setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                            // setString(2, ticket.getActiveCash());
                            setTimestamp(2, date); //Receipt date could be different from bill date
                            setString(3, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                        }
                    });

                    // new ticket
                    ticket.setPaid(true);
                    boolean berror1=true;
//                    if(Billpage.partial<=0){
                    berror1 = LookupUtilityImpl.getInstance(null).getDataLogicBill().markBillAsPaid(ticket);
                    if (berror1 == false) {
                        berror = true;
                        throw new BasicException();
                    } else {
                        SentenceExec paymentinsert = new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        for (final PaymentInfo p : ticket.getPayments()) {
                            paymentinsert.exec(new DataParams() {
                                
                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, ticket.getReceiptRef());
                                   // txRefID = ticket.getReceiptRef(); 
                                     setString(3, p.getName());
                                     if(ticket.getPayments().size()>1 && ticket.getPayments().size()<3){
                                    if(p.getName().equals("cash") && p.getName().equals("cheque"))
                                    {setDouble(4, (BillInfo.totalcash));
                                       // System.out.println("Cash amount is::::::::::   "+BillInfo.totalcash);}
                                   // else if(p.getName().equals("cheque"))
                                    //{setDouble(4, (BillInfo.totalchequeamt-Billpage.interest1));
                                       //  {setDouble(4, (BillInfo.totalchequeamt));}
                                    setDouble(4, (BillInfo.totalchequeamt-Billpage.interest1));}
                                    else if(p.getName().equals("magcard") && p.getName().equals("cheque"))
                                    {setDouble(4, (BillInfo.totalcardamt));
                                    setDouble(4, (BillInfo.totalchequeamt-Billpage.interest1));
                                    }
                                    else if(p.getName().equals("cash")&& p.getName().equals("magcard")){
                                            setDouble(4, (BillInfo.totalcash));
                                            setDouble(4, (BillInfo.totalcardamt-Billpage.interest1));
                                            }
                                  //  txRefID = ticket.getReceiptRef();
                                    //setString(3, p.getName());
                                 //  setDouble(4, BillInfo.totalcardOtherCharge);
                                   //  txRefID = ticket.getReceiptRef();  
//                                    setDouble(5,p.getOtherCharges());
//                                            setString(6, p.getTrack3());
//                                            setString(7, p.getTrack1());
                                    }
                                    // }
                                      if(ticket.getPayments().size()>1 && ticket.getPayments().size()<4){
                                    
                                          if(p.getName().equals("cash"))
                                    {setDouble(4, (BillInfo.totalcash));
                                        System.out.println("Cash amount is::::::::::   "+BillInfo.totalcash);}
                                    else if(p.getName().equals("cheque"))
                                    //{setDouble(4, (BillInfo.totalchequeamt-Billpage.interest1));
                                       //  {setDouble(4, (BillInfo.totalchequeamt));}
                                    {setDouble(4, (BillInfo.totalchequeamt-Billpage.interest1));}
                                    else if(p.getName().equals("magcard"))
                                    {setDouble(4, (BillInfo.totalcardamt));
                                    }
                                    
                                  //  txRefID = ticket.getReceiptRef();
                                    //setString(3, p.getName());
                                 //  setDouble(4, BillInfo.totalcardOtherCharge);
                                   //  txRefID = ticket.getReceiptRef();  
//                                    setDouble(5,p.getOtherCharges());
//                                            setString(6, p.getTrack3());
//                                            setString(7, p.getTrack1());
                                    //}
                                }
                                    //setDouble(4, (p.getTotal()-Billpage.interest1));
                                    //setDouble(4, (BillInfo.stottax));
//                                    setDouble(4, (ticket.getTotal()));
                                    setString(5, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                                    setTimestamp(6, date);
                                    setString(7, ticket.getCustomerId());
                                }
                            });

                         
                            if (p.getName().equals("cheque")) {
                                SentenceExec chequeInsert = new PreparedSentence(s, "INSERT INTO CHEQUE(ID, CHEQUENO, BANK, RNO,HOLDER,AMOUNT) VALUES (?, ?, ?, ?,?,?)", SerializerWriteParams.INSTANCE);
                                final ChequeDetails cd = p.getChequeDetails();
                                chequeInsert.exec(new DataParams() {

                                    @Override
                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getChequeno());
                                        setString(3, cd.getBank());
                                        setString(4, ticket.getReceiptRef());
                                        setString(5, cd.getholder());
//                                        setDouble(6, ticket.getTotal()+Billpage.interest1);
                                        setDouble(6, (BillInfo.totalchequeamt));
                                    }
                                });
                                List<BankInfo> temp = getBankList().list();
                                boolean result = false;
                                for (BankInfo b : temp) {
                                    if ((cd.getBank().equals(b.getName()))) {
                                        result = true;
                                        break;
                                    }
                                }
                                if (!result) {
                                    SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                    bankInsert.exec(new DataParams() {

                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, cd.getBank());
                                        }
                                    });
                                }
                            }
                            
                            // IF PAYMENT TYPE IS CARD      ## BY AAKASH
                            if (p.getName().equals("magcard")) {
                            
                            SentenceExec cardDetailsInsert = new PreparedSentence(s, "INSERT INTO carddetails(ID, BANKID, RECEIPT, AMOUNT,OTHERCHARGES , transactionid , accountid) VALUES (?, ?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
                                if( p.getTrack2()!=null){
                                    cardDetailsInsert.exec(new DataParams() {

                                        @Override
                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, p.getTrack2());
                                            setString(3, ticket.getReceiptRef());
//                                            setDouble(4, ticket.getTotal()+Billpage.interest1);

                                              setDouble(4, (BillInfo.totalcardamt));
                                            setDouble(5,p.getOtherCharges());
                                            setString(6, p.getTrack3());
                                            setString(7, p.getTrack1());
                                        }
                                    });
                                }
                            }
                            
                            
                            
                        }
                        //removed and moved to bill
                    /*    SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        if (ticket.getTaxes() != null) {
                            for (final TicketTaxInfo tickettax : ticket.getTaxes()) {
                                taxlinesinsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, ticket.getReceiptRef());
                                        setString(3, tickettax.getTaxInfo().getId());
                                        setDouble(4, tickettax.getSubTotal());
                                        setDouble(5, tickettax.getTax());
                                    }
                                });
                            }
                        }*/
                    }
                }
                
                return null;
            //}
            }
        };
        t.execute();
        if (flag1 == 0 && berror == false) {
            return true;
        } else {
            return false;
        }
    }
      //Ended by Ganesh
      
//Added by ganesh
      public final String getPeopleListByid(String id) {
        try {
            //Object obj = null;
            // try {
            return  (String)new StaticSentence(s, "SELECT name FROM people WHERE ID=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
            // } catch (BasicException ex) {
            //ex.printStackTrace();
            // }
            //return obj.toString();
        } catch (BasicException ex) {
            Logger.getLogger(DataLogicSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
       public  String getCreatedName(String id) throws BasicException {
         return (String) new StaticSentence(s, "SELECT name FROM people WHERE id=?" , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);  
    }
  
      public CustomerInfoExt loadCustomerExt2(String id) throws BasicException {
        return (CustomerInfoExt) new PreparedSentence(s, "SELECT NAME FROM CUSTOMERS WHERE ID = ?", SerializerWriteString.INSTANCE, new CustomerExtRead()).find(id);
    }
    public String getCustomerById(String id) throws BasicException{
    
        return (String)  new PreparedSentence(s, "SELECT SEARCHKEY FROM CUSTOMERS WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(QtLimitTable.class)).find(id);
    }
    public String getCustomerByName(String id) throws BasicException{
     return (String) new PreparedSentence(s, "SELECT NAME FROM CUSTOMERS WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(QtLimitTable.class)).find(id);
    }
    //Ended by ganesh
    
    /////////////////////////////////////////////////////
    //added by ganesh
   public final String getPeopleListByName(String id) {
        try {
            //Object obj = null;
            // try {
            return  (String)new StaticSentence(s, "SELECT name FROM locations WHERE ID=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
            // } catch (BasicException ex) {
            //ex.printStackTrace();
            // }
            //return obj.toString();
        } catch (BasicException ex) {
            Logger.getLogger(DataLogicSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
    
    
    
    /////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////
   
   public final String getPeopleListByName2(String id) {
        try {
           
            return  (String)new StaticSentence(s, "SELECT billref FROM qticket WHERE customer=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
            
        } catch (BasicException ex) {
            Logger.getLogger(DataLogicSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
   
   ///////////////////////////////////////////////////////////////////////////////
    public final String getPeopleListByName3(String id) {
        try {
           
            return  (String)new StaticSentence(s, "SELECT name FROM waiter WHERE id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
            
        } catch (BasicException ex) {
            Logger.getLogger(DataLogicSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
    /////////////////////////////////////////////////////////////////////////
    public final String getPeopleListByName4(String id) {
        try {
           
            return  (String)new StaticSentence(s, "SELECT searchkey FROM customers WHERE id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
            
        } catch (BasicException ex) {
            Logger.getLogger(DataLogicSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
  ///////////////////////////////////////////////////////////////////////////
    public final String getPeopleListByName5(String id) {
        try {
           
            return  (String)new StaticSentence(s, "SELECT name FROM customers WHERE id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
            
        } catch (BasicException ex) {
            Logger.getLogger(DataLogicSales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
    //added by shweta
       public final TaxCategoryInfo1 getTaxCategoryByid1(String id) throws BasicException {
        return (TaxCategoryInfo1) new StaticSentence(s, "SELECT ID, NAME,account FROM TAXCATEGORIES WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(TaxCategoryInfo1.class)).find(id);
    }
        public final TaxCategoryInfo2 getTaxCategoryByid2(String id) throws BasicException {
        return (TaxCategoryInfo2) new StaticSentence(s, "SELECT ID, NAME,account FROM TAXCATEGORIES WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(TaxCategoryInfo2.class)).find(id);
    }
//    public final SentenceList getTaxCategoriesList() {
//        return new StaticSentence(s, "SELECT ID, NAME,account FROM TAXCATEGORIES ORDER BY NAME", null, new SerializerReadClass(TaxCategoryInfo.class));
//    }
//    added by shweta
        //    added by shweta
     public final SentenceList getTaxCategoriesList1() {
        return new StaticSentence(s, "SELECT ID, NAME,account FROM TAXCATEGORIES ORDER BY ID", null, new SerializerReadClass(TaxCategoryInfo.class));
    }
//   
}
